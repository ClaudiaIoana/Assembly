package Expression;

import ADT.MyIDictionary;
import ADT.MyIHeap;
import Exception.MyException;
import Type.RefType;
import Type.Type;
import Value.RefValue;
import Value.Value;

public class ReadHeap implements Expression{
    Expression expression;

    public ReadHeap(Expression e){
        this.expression = e;
    }

    @Override
    public Value eval(MyIDictionary<String, Value> tbl, MyIHeap heap) throws MyException {
        Value evaluation = expression.eval(tbl, heap);
        if(!(evaluation instanceof RefValue))
            throw new MyException("not a ref value");
        RefValue refValue = (RefValue) evaluation;
        return heap.get(refValue.getAddress());
    }

    @Override
    public Expression deepCopy() {
        return new ReadHeap(expression.deepCopy());
    }

    @Override
    public String toString(){
        return "ReadHeap( "+expression.toString()+" )";
    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type type = expression.typecheck(typeEnv);
        if(type instanceof RefType){
            RefType refType = (RefType) type;
            return refType.getInner();
        }else
            throw new MyException("The rH argument is not a RefType.");
    }
}
