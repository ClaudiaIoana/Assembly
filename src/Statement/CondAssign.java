package Statement;


import ADT.MyIDictionary;
import ADT.MyIStack;
import Exception.MyException;
import Expression.*;
import Type.BoolType;
import Type.Type;
import ADT.PrgState;

public class CondAssign implements Statement{
    private String variable;
    private Expression exp1;
    private Expression exp2;
    private Expression exp3;
    public CondAssign(String var, Expression e1, Expression e2, Expression e3){
        variable = var;
        exp1 = e1;
        exp2 = e2;
        exp3 = e3;
    }
    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIStack<Statement> exeStack = state.getExeStack();
        Statement st = new IfStmt(exp1, new AssignStmt(variable, exp2), new AssignStmt(variable, exp3));
        exeStack.push(st);
        state.setExeStack(exeStack);
        return null;
    }

    @Override
    public Statement deepCopy() {
        return new CondAssign(variable, exp1.deepCopy(), exp2.deepCopy(), exp3.deepCopy());
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type variableType = new VarExp(variable).typecheck(typeEnv);
        Type type1 = exp1.typecheck(typeEnv);
        Type type2 = exp2.typecheck(typeEnv);
        Type type3 = exp3.typecheck(typeEnv);
        if (type1.equals(new BoolType()) && type2.equals(variableType) && type3.equals(variableType))
            return typeEnv;
        else
            throw new MyException("The conditional assignment is invalid!");

    }

    public String toString(){
        return "(" + variable + "=(" + exp1.toString() + ")?" + exp2.toString() + ":" + exp3.toString() + ")";
    }
}
