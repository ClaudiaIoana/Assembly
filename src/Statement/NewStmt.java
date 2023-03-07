package Statement;

import ADT.MyIDictionary;
import ADT.MyIHeap;
import ADT.MyIStack;
import ADT.PrgState;
import Exception.MyException;
import Expression.Expression;
import Type.RefType;
import Type.Type;
import Value.RefValue;
import Value.Value;

public class NewStmt implements Statement{
    String var_name;
    Expression expression;

    public NewStmt(String s, Expression e){
        this.var_name = s;
        this.expression = e;
    }

    public PrgState execute(PrgState state) throws MyException {
        MyIStack<Statement> stack = state.getExeStack();
        MyIDictionary<String, Value> symTable = state.getSymTable();
        MyIHeap heap = state.getHeap();

        if(symTable.isVarDef(var_name)) {
            Value var_value = symTable.lookup(var_name);
            if(var_value.getType() instanceof RefType){
                Value eval = expression.eval(symTable, heap);
                Type locationType = ((RefValue) var_value).getLocationType();
                if(locationType.equals(eval.getType())){
                    int new_poz = heap.add(eval);
                    symTable.put(var_name, new RefValue(new_poz, locationType));
                    state.setSymTable(symTable);
                    state.setHeap(heap);
                }
                else
                    throw new MyException("not equal");
            }else
                throw new MyException("not of type ref");
        } else
            throw new MyException("not in symtable");

        return null;
    }

    @Override
    public Statement deepCopy() {
        return new NewStmt(var_name, expression.deepCopy());
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typevar = typeEnv.lookup(var_name);
        Type typeexp = expression.typecheck(typeEnv);
        if(typevar.equals(new RefType(typeexp)))
            return typeEnv;
        else
            throw new MyException("NEW stmt: right hand side and left hand side have different types ");
    }

    @Override
    public String toString(){
        return "(new "+var_name+","+expression.toString()+")";
    }
}
