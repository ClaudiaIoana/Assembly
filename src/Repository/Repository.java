package Repository;

import ADT.PrgState;
import Exception.MyException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;


public class Repository implements IRepository{
    List<PrgState> repo;
    String filePath = "try1.txt";

    public Repository(PrgState prgState, String file){
        this.repo = new LinkedList<PrgState>();
        this.addPrgState(prgState);
        filePath = file;
    }

    @Override
    public void addPrgState(PrgState state) {
        this.repo.add(state);
    }

    @Override
    public void logPrgStateExec(PrgState state) throws MyException {
        try {
            PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(filePath, true)));
            logFile.write(state.toString());
            logFile.flush();
        }catch(IOException ios){
            throw new MyException("error: "+ios);
        }
    }

    @Override
    public List<PrgState> getPrgList() {
        return repo;
    }

    @Override
    public void setPrgList(List<PrgState> list) {
        repo = list;
    }

    @Override
    public PrgState getProgramWithId(int id) {
        for (PrgState p: repo)
            if(p.getId()==id)
                return p;
        return null;
    }
}
