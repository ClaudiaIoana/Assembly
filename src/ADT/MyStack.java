package ADT;

import Exception.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class MyStack<T> implements MyIStack<T>{
    private Stack<T> stack;

    public MyStack(){
        stack = new Stack<T>();
    }

    @Override
    public void push(T e) {
        stack.push(e);
    }

    @Override
    public T pop() throws MyException {
        T e = stack.pop();
        if(e == null)
            throw new MyException("Empty stack");
        return e;
    }

    @Override
    public boolean isEmpty() {
        return stack.empty();
    }

    @Override
    public List<T> get_reversed() {
        List<T> l = Arrays.asList((T[]) stack.toArray());
        Collections.reverse(l);
        return l;
    }

    @Override
    public String toString(){
           return stack.toString();
    }

    @Override
    public Stack<T> getStack() {
        return stack;
    }
    @Override
    public List getValues() {
        return stack.subList(0,stack.size());
    }
}
