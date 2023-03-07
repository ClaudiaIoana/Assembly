package Statement;

import ADT.*;
import Exception.MyException;
import Type.Type;
import Value.StringValue;
import Value.Value;

import java.io.BufferedReader;

public class ForkStmt implements Statement{
    Statement statement;

    public ForkStmt(Statement statement){
        this.statement = statement;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDictionary<String, Value> symTbl = state.getSymTable().cloneH();
        MyIStack<Statement> stk = new MyStack<Statement>();
        MyIList<Value> out = state.getOut();
        MyIDictionary<StringValue, BufferedReader> fileMap = state.getFileTable();
        MyIHeap heap = state.getHeap();
        PrgState prg = new PrgState(stk, symTbl, out, fileMap, heap, statement, PrgState.getNumberThread());
        return prg;
    }

    @Override
    public Statement deepCopy() {
        return new ForkStmt(statement.deepCopy());
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        statement.typecheck(typeEnv.cloneH());
        return typeEnv;
    }

    @Override
    public String toString(){
        return "Fork "+statement.toString();
    }
}
