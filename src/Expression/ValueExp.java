package Expression;

import ADT.MyIDictionary;
import ADT.MyIHeap;
import Exception.MyException;
import Type.Type;
import Value.Value;

public class ValueExp implements Expression {
    Value e;

    public ValueExp(Value v){
        this.e = v;
    }
    @Override
    public Value eval(MyIDictionary<String, Value> tbl, MyIHeap heap) throws MyException {
        return e;
    }

    @Override
    public Expression deepCopy() {
        return new ValueExp(e.deepCopy());
    }

    @Override
    public String toString(){
        return e.toString();
    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return e.getType();
    }
}
