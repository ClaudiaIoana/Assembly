package ADT;

import Exception.*;

import java.util.List;
import java.util.Stack;

public interface MyIStack <T>{
    void push(T e);
    T pop() throws MyException;
    boolean isEmpty();
    List<T> get_reversed();
    String toString();
    Stack<T> getStack();
    List getValues();
}
