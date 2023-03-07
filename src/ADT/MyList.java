package ADT;

import java.util.ArrayList;
import java.util.List;

public class MyList<T> implements MyIList<T>{
    private List<T> out;

    public MyList(){
        out = new ArrayList<T>();
    }

    @Override
    public void add(T e) {
        out.add(e);
    }

    @Override
    public String toString(){
        return out.toString();
    }

    @Override
    public List getContent() {
        return out;
    }
}
