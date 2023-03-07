package Statement;

import ADT.MyIDictionary;
import ADT.PrgState;
import Exception.MyException;
import Type.Type;


public interface Statement {
    PrgState execute(PrgState state) throws MyException;
    String toString();
    Statement deepCopy();
    MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException;
}
