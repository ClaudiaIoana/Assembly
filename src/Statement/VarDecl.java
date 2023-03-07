package Statement;

import ADT.MyIDictionary;
import ADT.MyIStack;
import ADT.PrgState;
import Exception.MyException;
import Type.Type;
import Value.Value;

public class VarDecl implements Statement{
    String id;
    Type typ;

    public VarDecl(String i, Type t){
        this.id = i;
        this.typ = t;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIStack<Statement> stack = state.getExeStack();
        MyIDictionary<String, Value> symTable = state.getSymTable();
        if(symTable.isVarDef(id))
            throw new MyException("the variable is already declared");
        else{
            symTable.put(id, typ.defaultValue());
            return null;
        }
    }

    @Override
    public String toString(){
        return "Type "+id;
    }

    @Override
    public Statement deepCopy() {
        return new VarDecl(id, typ.deepCopy());
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        typeEnv.put(id, typ);
        return typeEnv;
    }
}
