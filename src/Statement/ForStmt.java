package Statement;

import ADT.MyIDictionary;
import ADT.MyIStack;
import Exception.MyException;
import Expression.*;
import Type.IntType;
import Type.Type;
import Value.Value;
import ADT.PrgState;
import Statement.Statement;

public class ForStmt implements Statement {
    private Expression exp1;
    private Expression exp2;
    private Expression exp3;
    private Statement stmt;
    private String var;
    public ForStmt(String value, Expression e1, Expression e2, Expression e3, Statement st){
        exp1 = e1;
        exp2 = e2;
        exp3 = e3;
        stmt = st;
        var = value;
    }
    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIStack<Statement> exeStack = state.getExeStack();
        Statement st = new CompStmt(new AssignStmt("v", exp1), new WhileStmt(new RelationalExp(new VarExp("v"), exp2, 1),
                new CompStmt(stmt, new AssignStmt("v", exp3))));
        exeStack.push(st);
        state.setExeStack(exeStack);
        return null;
    }

    @Override
    public Statement deepCopy() {
        return new ForStmt(var, exp1.deepCopy(), exp2.deepCopy(), exp3.deepCopy(), stmt.deepCopy());
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type t1 = exp1.typecheck(typeEnv);
        Type t2 = exp2.typecheck(typeEnv);
        Type t3 = exp3.typecheck(typeEnv);
        if (t1.equals(new IntType()) && t2.equals(new IntType()) && t3.equals(new IntType()))
            return typeEnv;
        else
            throw new MyException("invalid types for 'for' stmt");
    }
    public String toString(){
        return "for(" + var + "=" + exp1.toString() + ";" +
                var + "<" + exp2.toString() + ";" + var + "=" + exp3.toString() + "){" + stmt.toString() + "})";
    }

}
