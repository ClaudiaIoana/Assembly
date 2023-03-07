package ADT;

import Exception.*;
import Value.*;

import java.util.HashMap;
import java.util.Map;

public class MyHeap implements MyIHeap{
    Map<Integer, Value> heap;
    Integer freeLocation;

    public MyHeap(){
        this.heap = new HashMap<>();
        freeLocation = 1;
    }

    @Override
    public int newFreeLocation() {
        freeLocation++;
        while(freeLocation == 0 || heap.containsKey(freeLocation))
            freeLocation++;
        return freeLocation;
    }

    @Override
    public Map<Integer, Value> getContent() {
        return heap;
    }

    @Override
    public void setContent(Map<Integer, Value> neew) {
        this.heap = neew;
    }

    @Override
    public Value get(Integer position) throws MyException {
        if(!heap.containsKey(position))
            throw new MyException("invalid position");
        return heap.get(position);
    }

    @Override
    public boolean containsKey(Integer position) {
        return this.heap.containsKey(position);
    }

    @Override
    public void remove(Integer key) throws MyException {
        if(!containsKey(key))
            throw new MyException("the key is invalid");
        freeLocation = key;
        this.heap.remove(key);
    }

    @Override
    public void update(Integer poz, Value val) throws MyException {
        if(!heap.containsKey(poz))
            throw new MyException("invalid position");
        heap.put(poz, val);
    }

    @Override
    public int add(Value val) {
        heap.put(freeLocation, val);
        Integer returning = freeLocation;
        freeLocation = newFreeLocation();
        return returning;
    }

    public String toString(){
        return this.heap.toString();
    }
}
