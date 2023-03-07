package Expression;

import ADT.MyIDictionary;
import ADT.MyIHeap;
import Exception.MyException;
import Type.Type;
import Value.Value;

public interface Expression {
    Value eval(MyIDictionary<String, Value> tbl, MyIHeap heap) throws MyException;

    Expression deepCopy();

    String toString();

    Type typecheck(MyIDictionary<String, Type> typeEnv) throws MyException;
}
