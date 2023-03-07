package Statement;
import ADT.MyIDictionary;
import ADT.PrgState;
import Exception.MyException;
import Type.Type;

public class NopStmt implements Statement {
    public NopStmt(){
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        return null;
    }

    @Override
    public Statement deepCopy() {
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return null;
    }
}
