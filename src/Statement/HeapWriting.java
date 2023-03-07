package Statement;

import ADT.MyIDictionary;
import ADT.MyIHeap;
import ADT.PrgState;
import Exception.MyException;
import Expression.Expression;
import Type.RefType;
import Type.Type;
import Value.RefValue;
import Value.Value;

public class HeapWriting implements Statement{
    String var_name;
    Expression expression;

    public HeapWriting(String v, Expression e){
        this.var_name = v;
        this.expression = e;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDictionary<String, Value> symtable = state.getSymTable();
        MyIHeap heap = state.getHeap();
        if(!symtable.isVarDef(var_name))
            throw new MyException("Not defined");
        Value value = symtable.lookup(var_name);
        if(!(value instanceof RefValue))
            throw new MyException("not of type ref");
        RefValue refValue = (RefValue) value;
        Value evaluated = expression.eval(symtable, heap);
        if(!evaluated.getType().equals(refValue.getLocationType()))
            throw new MyException("not equal");
        heap.update(refValue.getAddress(), evaluated);
        state.setHeap(heap);
        return null;
    }

    @Override
    public Statement deepCopy() {
        return new HeapWriting(var_name, expression.deepCopy());
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typevar = typeEnv.lookup(var_name);
        Type typexp = expression.typecheck(typeEnv);
        if (typevar.equals(new RefType(typexp)))
            return typeEnv;
        else
            throw new MyException("WriteHeap: right hand side and left hand side have different types.");
    }

    @Override
    public String toString(){
        return "WriteHeap( "+var_name +", "+ expression.toString()+" )";
    }
}
