package Statement;

import ADT.MyIDictionary;
import ADT.MyIStack;
import ADT.PrgState;
import Exception.MyException;
import Expression.Expression;
import Type.BoolType;
import Type.Type;
import Value.BoolValue;
import Value.Value;

public class IfStmt implements Statement {
    Expression exp;
    Statement thenS;
    Statement elseS;

    public IfStmt(Expression e, Statement t, Statement el){
        exp = e;
        thenS = t;
        elseS = el;
    }

    @Override
    public String toString(){
        return "(IF("+exp.toString()+")THEN("+thenS.toString()+")ELSE("+elseS.toString()+"))";
    }

    @Override
    public Statement deepCopy() {
        return new IfStmt(exp.deepCopy(), thenS.deepCopy(), elseS.deepCopy());
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typexp = exp.typecheck(typeEnv);
        if(typexp.equals(new BoolType())){
            thenS.typecheck(typeEnv.cloneH());
            elseS.typecheck(typeEnv.cloneH());
            return typeEnv;
        }else
            throw new MyException("The condition of IF has not the type bool");
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIStack<Statement> stack = state.getExeStack();
        MyIDictionary<String, Value> symTable = state.getSymTable();
        Value cond = exp.eval(symTable, state.getHeap());
        Type val = new BoolType();
        if (!cond.getType().equals(new BoolType()))
            throw new MyException("conditional expr is not a boolean");
        else{
            BoolValue cond2 = (BoolValue) cond;
            if (cond2.getVal()) {
                stack.push(thenS);
            } else
                stack.push(elseS);
        }
        return null;
    }
}
