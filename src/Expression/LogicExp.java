package Expression;

import ADT.MyIDictionary;
import ADT.MyIHeap;
import Exception.MyException;
import Type.BoolType;
import Type.Type;
import Value.BoolValue;
import Value.Value;


public class LogicExp implements Expression {
    Expression e1;
    Expression e2;
    int op;
    //op = 1, AND
    //op = 2, OR

    public LogicExp(Expression e1, Expression e2, int o){
        this.e1 = e1;
        this.e2 = e2;
        this.op = o;
    }
    @Override
    public Value eval(MyIDictionary<String, Value> tbl, MyIHeap heap) throws MyException {
        Value v1, v2;
        v1 = e1.eval(tbl, heap);
        if(v1.getType().equals(new BoolType())){
            v2 = e2.eval(tbl, heap);
            if(v2.getType().equals(new BoolType())){
                BoolValue i1 = (BoolValue) v1;
                BoolValue i2 = (BoolValue) v2;
                boolean n1, n2;
                n1 = i1.getVal();
                n2 = i2.getVal();
                if(op == 1)
                    return new BoolValue(n1 && n2);
                else if(op == 2)
                    return new BoolValue(n1 || n2);
                else
                    throw new MyException("invalid operand");
            }else
                throw new MyException("invalid operand");
        }
        else
            throw new MyException("first operand is not a boolean");
    }

    @Override
    public Expression deepCopy() {
        return new LogicExp(e1.deepCopy(), e2.deepCopy(), op);
    }

    @Override
    public String toString(){
        String s;
        if (op == 1)
            s = "AND";
        else
            s = "OR";
        return e1.toString()+s+e2.toString();
    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typ1, typ2;
        typ1 = e1.typecheck(typeEnv);
        typ2 = e2.typecheck(typeEnv);

        if(typ1.equals(new BoolType())){
            if (typ2.equals(new BoolType())){
                return new BoolType();
            }
            else throw new MyException("second operand is not a boolean");
        }else
            throw new MyException("first operand is not a boolean");
    }

}
