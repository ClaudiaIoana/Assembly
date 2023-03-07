package Statement;

import ADT.MyIDictionary;
import ADT.PrgState;
import Exception.MyException;
import Expression.Expression;
import Type.StringType;
import Type.Type;
import Value.StringValue;
import Value.Value;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class OpenRFile implements Statement{
    Expression exp;

    public  OpenRFile(Expression e){
        exp = e;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        Value result = exp.eval(state.getSymTable(), state.getHeap());
        MyIDictionary<StringValue, BufferedReader> file = state.getFileTable();
        if(!result.getType().equals(new StringType()))
            throw new MyException("invalid");
        if(state.getFileTable().isVarDef((StringValue)result))
            throw new MyException("invalid");
        try{
            BufferedReader br = new BufferedReader(new FileReader(result.toString()));
            file.put((StringValue) result, br);
        }catch(IOException e){
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Statement deepCopy() {
        return new OpenRFile(exp.deepCopy());
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        if(exp.typecheck(typeEnv).equals(new StringType())){
            return typeEnv;
        }
        else
            throw new MyException("It is required a string for the OpenReadFile.");
    }

    public String toString(){
        return "openRFile ( "+ exp.toString() + ")";
    }
}