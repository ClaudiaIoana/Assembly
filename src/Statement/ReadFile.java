package Statement;

import ADT.MyIDictionary;
import ADT.PrgState;
import Exception.MyException;
import Expression.Expression;
import Type.IntType;
import Type.StringType;
import Type.Type;
import Value.IntValue;
import Value.StringValue;
import Value.Value;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadFile implements Statement{
    Expression exp;
    String var_name;

    public ReadFile(Expression e, String s){
        this.exp = e;
        this.var_name = s;
    }


    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDictionary<String, Value> symtbl = state.getSymTable();
        if(!symtbl.isVarDef(var_name))
            throw new MyException(var_name + "not declared in SymTable");
        Value res = symtbl.lookup(var_name);
        if(!res.getType().equals(new IntType()))
            throw new MyException(var_name+ "not an integer type");
        Value res2 = exp.eval(symtbl, state.getHeap());
        if(res2.getType().equals(new StringType())){
            MyIDictionary<StringValue, BufferedReader> file = state.getFileTable();
            if(file.isVarDef((StringValue) res2)){
                BufferedReader br = file.lookup((StringValue) res2);
                try{
                    String content = br.readLine();
                    symtbl.update(var_name, new IntValue(Integer.parseInt(content)));
                }catch(NumberFormatException nfe){
                    symtbl.update(var_name, new IntValue(0));
                }catch(IOException io){
                    throw new MyException("error "+io);
                }
            }else{
                throw new MyException(res2.toString() + "is not operand");
        }
        }else
            throw new MyException(res2.toString()+ "file name is not a String type");
        return null;
    }

    @Override
    public Statement deepCopy() {
        return new ReadFile(exp.deepCopy(), var_name);
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typevar = typeEnv.lookup(var_name);
        Type typexp = exp.typecheck(typeEnv);
        if (typexp.equals(new StringType()))
            if(typevar.equals(new IntType()))
                return typeEnv;
            else
                throw new MyException("ReadFile requires an int as a variable parameter");
        else
            throw new MyException("ReadFile requires a string as an expression parameter.");
    }

    public String toString(){
        return "open file("+exp.toString()+")";
    }
}
