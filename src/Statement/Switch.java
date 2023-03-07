package Statement;

import ADT.MyIDictionary;
import ADT.MyIStack;
import ADT.PrgState;
import Expression.*;
import Type.Type;
import Exception.*;

public class Switch implements Statement{
    private Expression mainExp;
    private Expression exp1;
    private Statement stm1;
    private Expression exp2;
    private Statement stm2;
    private Statement defaultStm;

    public Switch(Expression main_exp, Expression e1, Statement s1, Expression e2, Statement s2, Statement ds) {
        this.mainExp = main_exp;
        this.exp1 = e1;
        this.stm1 = s1;
        this.exp2 = e2;
        this.stm2 = s2;
        this.defaultStm= ds;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIStack<Statement> exeStack = state.getExeStack();
        Statement coverted = new IfStmt(new RelationalExp(mainExp, exp1, 3), stm1, new IfStmt(new RelationalExp(mainExp, exp2, 3), stm2, defaultStm));
        exeStack.push(coverted);
        state.setExeStack(exeStack);
        return null;
    }

    @Override
    public Statement deepCopy() {
        return new Switch(mainExp.deepCopy(), exp1.deepCopy(), stm1.deepCopy(), exp2.deepCopy(), stm2.deepCopy(), defaultStm.deepCopy());
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type mainType = mainExp.typecheck(typeEnv);
        Type type1 = exp1.typecheck(typeEnv);
        Type type2 = exp2.typecheck(typeEnv);
        if (mainType.equals(type1) && mainType.equals(type2)){
            stm1.typecheck(typeEnv);
            stm2.typecheck(typeEnv);
            defaultStm.typecheck(typeEnv);
            return typeEnv;
        }else {
            throw new MyException("The expression types don't match in the switch statement!");
        }
    }

    @Override
    public String toString() {
        return String.format("switch(%s){(case(%s): %s)(case(%s): %s)(default: %s)}", mainExp, exp1, stm1, exp2, stm2, defaultStm);
    }
}
