package Statement;

import ADT.MyIDictionary;
import ADT.MyIList;
import ADT.PrgState;
import Exception.MyException;
import Expression.Expression;
import Type.Type;
import Value.Value;

public class PrintStmt implements Statement {
    Expression exp;

    public PrintStmt(Expression e){
        this.exp = e;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDictionary<String, Value> symTable = state.getSymTable();
        MyIList<Value> out = state.getOut();
        out.add(exp.eval(symTable, state.getHeap()));
        state.setOut(out);
        return null;
    }

    @Override
    public String toString(){
        return "print("+exp.toString()+")";
    }

    @Override
    public Statement deepCopy() {
        return new PrintStmt(exp.deepCopy());
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        exp.typecheck(typeEnv);
        return typeEnv;
    }
}
