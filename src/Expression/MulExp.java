package Expression;


import ADT.MyIDictionary;
import ADT.MyIHeap;
import Exception.MyException;
import Type.IntType;
import Type.Type;
import Value.Value;

public class MulExp implements Expression{
    private Expression exp1;
    private Expression exp2;
    public MulExp(Expression e1, Expression e2){
        exp1 = e1;
        exp2 = e2;
    }

    @Override
    public Value eval(MyIDictionary<String, Value> tbl, MyIHeap heap) throws MyException {
        Expression converted = new ArithExp(
                new ArithExp(exp1, exp2, '*'),
                new ArithExp(exp1, exp2, '+'), '-');
        return converted.eval(tbl, heap);
    }

    @Override
    public Expression deepCopy() {
        return new MulExp(exp1.deepCopy(), exp2.deepCopy());
    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type type1 = exp1.typecheck(typeEnv);
        Type type2 = exp2.typecheck(typeEnv);
        if (type1.equals(new IntType()) && type2.equals(new IntType()))
            return new IntType();
        else
            throw new MyException("Expressions in the MUL should be int!");

    }
    public String toString(){
        return "MUL(" + exp1.toString() + "," + exp2.toString() + ")";
    }
}

