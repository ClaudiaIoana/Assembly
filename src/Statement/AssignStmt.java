package Statement;

import ADT.MyIDictionary;
import ADT.MyIStack;
import ADT.PrgState;
import Exception.MyException;
import Expression.Expression;
import Type.Type;
import Value.Value;

public class AssignStmt implements Statement {
    String id;
    Expression exp;

    public AssignStmt(String i, Expression e){
        this.id = i;
        this.exp = e;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIStack<Statement> stk = state.getExeStack();
        MyIDictionary<String, Value> symTable = state.getSymTable();

        if (symTable.isVarDef(id)){
            Value val = exp.eval(symTable, state.getHeap());
            Type typId = (symTable.lookup(id)).getType();
            if((val.getType()).equals(typId))
                symTable.update(id, val);
            else throw new MyException("declared type of variable"+id+"and type of the assigned expression do not match");
        }else throw new MyException("the used variable"+id+"was not declared before");

        return null;
    }

    @Override
    public String toString(){
        return id+"="+exp.toString();
    }

    @Override
    public Statement deepCopy() {
        return new AssignStmt(id, exp.deepCopy());
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typevar = typeEnv.lookup(id);
        Type typexp = exp.typecheck(typeEnv);
        if(typevar.equals(typexp))
            return typeEnv;
        else
            throw new MyException("Assignment: right hand side have different types");
    }
}
