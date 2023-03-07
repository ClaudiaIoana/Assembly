package Statement;

import ADT.MyIDictionary;
import ADT.MyIStack;
import ADT.PrgState;
import Exception.MyException;
import Type.Type;

public class CompStmt implements Statement {
    Statement first;
    Statement second;

    public CompStmt(Statement f, Statement s){
        this.first = f;
        this.second = s;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIStack<Statement> stk = state.getExeStack();
        stk.push(second);
        stk.push(first);
        return null;
    }

    @Override
    public String toString(){
        return "("+first.toString()+";"+second.toString()+")";
    }

    @Override
    public Statement deepCopy() {
        return new CompStmt(first.deepCopy(), second.deepCopy());
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return second.typecheck(first.typecheck(typeEnv));
    }
}
