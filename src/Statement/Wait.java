package Statement;

import ADT.MyIDictionary;
import ADT.MyIStack;
import ADT.PrgState;
import Expression.ValueExp;
import Type.Type;
import Exception.*;
import Value.IntValue;

public class Wait implements Statement{
    private int value;

    public Wait(int value){
        this.value = value;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        if(value != 0){
            MyIStack<Statement> exeStack = state.getExeStack();
            exeStack.push(new CompStmt(new PrintStmt(new ValueExp(new IntValue(value))), new Wait(value - 1)));
            state.setExeStack(exeStack);
        }
        return null;
    }

    @Override
    public Statement deepCopy() {
        return new Wait(value);
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return typeEnv;
    }

    @Override
    public String toString(){
        return String.format("Wait " + value);
    }
}
