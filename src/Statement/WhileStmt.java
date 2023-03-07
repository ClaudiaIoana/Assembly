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

public class WhileStmt implements Statement{
    private Expression expression;
    private Statement statement;

    public WhileStmt(Expression e, Statement s){
        this.expression = e;
        this.statement = s;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        Value value = expression.eval(state.getSymTable(), state.getHeap());
        MyIStack<Statement> stack = state.getExeStack();
        if(!(value instanceof BoolValue))
            throw new MyException("It is not a BoolType");
        BoolValue boolValue = (BoolValue) value;
        if(boolValue.getVal()){
            stack.push(this);
            stack.push(statement);
        }
        return null;
    }

    @Override
    public Statement deepCopy() {
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typeexp = expression.typecheck(typeEnv);
        if(typeexp.equals(new BoolType())) {
            statement.typecheck(typeEnv.cloneH());
            return typeEnv;
        }
        else throw new MyException("The condition of WHILE does not have the type Bool");
    }

    @Override
    public String toString() {
        return String.format("(While (%s) %s)", expression, statement);
    }
}
