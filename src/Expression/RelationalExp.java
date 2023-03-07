package Expression;

import ADT.MyIDictionary;
import ADT.MyIHeap;
import Exception.MyException;
import Type.BoolType;
import Type.IntType;
import Type.Type;
import Value.BoolValue;
import Value.IntValue;
import Value.Value;

public class RelationalExp implements Expression{

    Expression e1;
    Expression e2;
    int op;

    public RelationalExp(Expression ex1, Expression ex2, int op) {
        this.e1 = ex1;
        this.e2 = ex2;
        this.op = op;
    }
    //op = 1 <
    //op = 2 <=
    //op = 3 ==
    //op = 4 !=
    //op = 5 >
    //op = 6 >=

    @Override
    public Value eval(MyIDictionary<String, Value> tbl, MyIHeap heap) throws MyException {
        Value v1, v2;
        v1 = e1.eval(tbl, heap);
        if(v1.getType().equals(new IntType())){
            v2 = e2.eval(tbl, heap);
            if(v2.getType().equals(new IntType())){
                IntValue i1 = (IntValue) v1;
                IntValue i2 = (IntValue) v2;
                int n1, n2;
                n1 = i1.getVal();
                n2 = i2.getVal();
                if(op == 1)
                    return new BoolValue(n1<n2);
                else if(op == 2)
                    return new BoolValue(n1<=n2);
                else if(op == 3)
                    return new BoolValue(n1==n2);
                else if(op == 4)
                    return new BoolValue(n1!=n2);
                else if(op == 5)
                    return new BoolValue(n1>n2);
                else if(op == 6)
                    return new BoolValue(n1>=n2);
                else
                    throw new MyException("invalid operand");
            }else
                throw new MyException("second operand is not an integer");
        }else
            throw new MyException("first operand is not an integer");
    }

    @Override
    public Expression deepCopy() {
        return new RelationalExp(e1.deepCopy(), e2.deepCopy(), op);
    }
    
    public String toString(){
        String f = null;
        if(op == 1)
            f = "<";
        else if(op == 2)
            f = "<=";
        else if(op == 3)
            f = "==";
        else if(op == 4)
            f = "!=";
        else if(op == 5)
            f = ">";
        else if(op == 6)
            f = ">=";
        return e1.toString()+f+e2.toString();
    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typ1, typ2;
        typ1 = e1.typecheck(typeEnv);
        typ2 = e2.typecheck(typeEnv);

        if(typ1.equals(new IntType())){
            if (typ2.equals(new IntType())){
                return new BoolType();
            }
            else throw new MyException("second operand is not an integer");
        }else
            throw new MyException("first operand is not an integer");
    }
}
