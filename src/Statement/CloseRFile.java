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
import java.io.IOException;

public class CloseRFile implements Statement{
    Expression exp;

    public CloseRFile(Expression e){
        exp = e;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        Value res = exp.eval(state.getSymTable(), state.getHeap());
        if(res.getType().equals(new StringType())){
            StringValue result = (StringValue) exp.eval(state.getSymTable(), state.getHeap());
            if(state.getFileTable().isVarDef(result)){
                try{
                    BufferedReader br = state.getFileTable().lookup(result);
                    br.close();
                    state.getFileTable().delete(result);
                }catch (IOException e){
                    throw new MyException("error "+e);
                }
            }
        }else
            throw new MyException("Expression is not a string type");


        return null;
    }

    @Override
    public Statement deepCopy() {
        return new CloseRFile(exp.deepCopy());
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typexp = exp.typecheck(typeEnv);
        if(typexp.equals(new StringType()))
            return typeEnv;
        else throw new MyException("The close file needs a string");
    }

    public String toString(){
        return "closeFile ( "+exp.toString()+")";
    }
}
