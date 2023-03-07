package Expression;

import ADT.MyIDictionary;
import ADT.MyIHeap;
import Exception.MyException;
import Type.Type;
import Value.Value;

public class VarExp implements Expression {
    String id;

    public VarExp(String i){
        id = i;
    }
    @Override
    public Value eval(MyIDictionary<String, Value> tbl, MyIHeap heap) throws MyException {
        return (Value)tbl.lookup(id);
    }

    @Override
    public Expression deepCopy() {
        return new VarExp(id);
    }

    @Override
    public String toString(){
        return id;
    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return typeEnv.lookup(id);
    }
}
