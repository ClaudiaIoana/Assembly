package Controller;

import ADT.MyIHeap;
import ADT.PrgState;
import Exception.MyException;
import Repository.IRepository;
import Value.RefValue;
import Value.Value;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;


public class Controller {
    IRepository repo;
    ExecutorService executor;

    public Controller(IRepository r){
        this.repo = r;
    }

    public void currentProgramState(PrgState state){
        System.out.println(state);
    }

    List<PrgState> removeCompletedPrg(List<PrgState> inPrgList){
        return inPrgList.stream()
                .filter(e -> e.isNotCompleted())
                .collect(Collectors.toList());
    }

    public void executeOneStep() throws MyException {
        executor = Executors.newFixedThreadPool(2);
        repo.setPrgList(removeCompletedPrg(repo.getPrgList()));
        List<PrgState> programStates = repo.getPrgList();
        if(programStates.size() > 0)
        {
            try {
                oneStepForAllPrg(repo.getPrgList());
            } catch (MyException e) {
                System.out.println();
            }
            repo.setPrgList(removeCompletedPrg(repo.getPrgList()));
            executor.shutdownNow();
            callGarbageCollector(programStates);
        }
    }

    public void oneStepForAllPrg(List<PrgState> prgList) throws MyException{
        prgList.forEach(prgL -> {
            try {
                repo.logPrgStateExec(prgL);
            } catch (MyException e) {
                System.out.println(e);
            }
        });

        List<Callable<PrgState>> callList = prgList.stream()
                .map((PrgState p) -> (Callable<PrgState>)(p::oneStep))
                .collect(Collectors.toList());
        try{
            List<PrgState> newPrgList = executor.invokeAll(callList).stream()
                    .map(future ->{
                        try{
                            return future.get();
                        }catch(ExecutionException | InterruptedException e){
                            System.out.println("error: "+ e);
                        }
                        return null;
                    })
                    .filter(Objects::nonNull).toList();
            prgList.addAll(newPrgList);
            repo.setPrgList(prgList);
        }catch (InterruptedException ee){
            throw new MyException("error- "+ ee);
        }
        prgList.forEach(prg ->{
            try{
                repo.logPrgStateExec(prg);
            }catch(MyException e){
                System.out.println(e);
            }
        });
    }

    public Map<Integer, Value> safeGarbageCollector(List<Integer> symTableAddr, Map<Integer, Value> heap){
        return heap.entrySet().stream()
                .filter(e->symTableAddr.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public void callGarbageCollector(List<PrgState> programStates){
        programStates.forEach(
                p-> {p.getHeap().setContent(safeGarbageCollector(getAllAddresses(p.getSymTable().getContent().values(), (MyIHeap) p.getHeap().getContent().values()),p.getHeap().getContent()));}
        );
    }

    public List<Integer> getAllAddresses(Collection<Value> symTableValues, MyIHeap heapTableValues){
        ConcurrentLinkedDeque<Integer> result = symTableValues.stream()
                .filter(v -> v instanceof RefValue)
                .map(v ->{
                    RefValue v1 = (RefValue) v;
                    return v1.getAddress();
                })
                .collect(Collectors.toCollection(ConcurrentLinkedDeque::new));

        result.stream()
                .forEach(a ->{
                    Value v = heapTableValues.getContent().get(a);
                    if(v instanceof RefValue)
                        if(!result.contains(((RefValue) v).getAddress()))
                            result.add(((RefValue) v).getAddress());
                });
        return result.stream().toList();
    }
    public void allStep() throws MyException{
        executor = Executors.newFixedThreadPool(2);
        List<PrgState> prgList = removeCompletedPrg(repo.getPrgList());
        while(prgList.size() > 0){
            HashMap<String, Value> res = (HashMap<String, Value>) prgList.get(0).getSymTable().getContent();
            for(int i = 1; i < prgList.size() - 1; i++)
                res.putAll(prgList.get(i).getSymTable().getContent());

            prgList.get(prgList.size() - 1).getHeap().setContent(
                    safeGarbageCollector(getAllAddresses(res.values(), prgList.get(prgList.size()-1).getHeap()),
                            prgList.get(prgList.size()-1).getHeap().getContent()));
            oneStepForAllPrg(prgList);
            prgList = removeCompletedPrg(repo.getPrgList());
        }
        executor.shutdownNow();
        repo.setPrgList(prgList);
    }

    public void oneStepAll() throws MyException {
        this.executor = Executors.newFixedThreadPool(2);
        List<PrgState> prgList = this.removeCompletedPrg(this.repo.getPrgList());
        HashMap<String, Value> res = (HashMap)((PrgState)prgList.get(0)).getSymTable().getContent();
        this.safeGarbageCollector(this.getAllAddresses(res.values(), ((PrgState)prgList.get(prgList.size() - 1)).getHeap()), ((PrgState)prgList.get(prgList.size() - 1)).getHeap().getContent());
        this.oneStepForAllPrg(prgList);
        prgList = this.removeCompletedPrg(this.repo.getPrgList());
        this.executor.shutdownNow();
        this.repo.setPrgList(prgList);
    }

    public IRepository getRepo(){
        return repo;
    }
    public void addProgram(PrgState progState){this.repo.addPrgState(progState);}

    public void displayCurrentProgramState(PrgState state){
        System.out.println(state.toString());
    }
}
