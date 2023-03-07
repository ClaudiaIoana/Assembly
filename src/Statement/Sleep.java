package Statement;

import ADT.MyIDictionary;
import ADT.MyIStack;
import Exception.MyException;
import Type.Type;
import ADT.PrgState;

public class Sleep implements Statement{
    private int value;
    public Sleep(int value){
        this.value = value;
    }
    @Override
    public PrgState execute(PrgState state) throws MyException {
        if(value != 0){
            MyIStack<Statement> exeStack = state.getExeStack();
            exeStack.push(new Sleep(this.value - 1));
            state.setExeStack(exeStack);
        }
        return null;
    }
    @Override
    public Statement deepCopy() {
        return new Sleep(this.value);
    }
    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return typeEnv;
    }
    public String toString(){
        return "sleep(" + value + ")";
    }
}
