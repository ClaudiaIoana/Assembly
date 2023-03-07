package GarbageCollector;

public class GarbageCollector {
    public GarbageCollector(){}
    /*
    public static Map<Integer, Value> unsafeGarbageCollector(List<Integer> symTableAddr, Map<Integer, Value> heap){
        return (Map)heap.entrySet().stream().filter((e)->{
            return symTableAddr.contains(e.getKey());
        }).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static List<Integer> getAddrFromSymTable(Collection<Value> symTableVales, Collection<Value> heapTableVales){
        List<Integer> result = new ArrayList<>();
        result.addAll((Collection)symTableVales.stream().filter((v)->{
            return v instanceof RefValue;
        }).map((v)->{
            RefValue v1 = (RefValue) v;
            return v1.getAddress();
        }).collect(Collectors.toList()));
        result.addAll((Collection)heapTableVales.stream().filter((v)->{
            return v instanceof RefValue;
        }).map((v)->{
            return ((RefValue)v).getAddress();
        }).collect(Collectors.toList()));
        return result;
    }*/
}
