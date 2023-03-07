package ADT;

import Exception.*;
import Value.*;

import java.util.Map;

public interface MyIHeap{
    int newFreeLocation();
    Map<Integer, Value> getContent();
    void setContent(Map<Integer, Value> neew);
    int add(Value val);
    void update(Integer poz, Value val) throws MyException;
    Value get(Integer position) throws MyException;
    boolean containsKey(Integer position);
    void remove(Integer key) throws MyException;
    String toString();

}
