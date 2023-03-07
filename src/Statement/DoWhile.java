package Statement;

import ADT.MyIDictionary;
import ADT.PrgState;
import Type.*;
import Exception.*;
import Expression.*;

public class DoWhile implements Statement{
    private Statement statement;
    private Expression expression;

    public DoWhile(Expression expression, Statement statement) {
        this.statement = statement;
        this.expression = expression;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        Statement converted = new CompStmt(statement, new WhileStmt(expression, statement));
        state.getExeStack().push(converted);
        return null;
    }

    @Override
    public Statement deepCopy() {
        return new DoWhile(expression.deepCopy(), statement.deepCopy());
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typeExpression = expression.typecheck(typeEnv);
        if (typeExpression.equals(new BoolType())) {
            statement.typecheck(typeEnv);
            return typeEnv;
        } else
            throw new MyException("Condition in the do while statement must be bool!");
    }

    @Override
    public String toString() {
        return String.format("do {%s} while (%s)", statement, expression);
    }
}
