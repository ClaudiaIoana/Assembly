package Type;

import Value.IntValue;
import Value.Value;

public class IntType implements Type {
    public IntType() {
    }

    @Override
    public boolean equals(Object another){
        if(another instanceof IntType)
            return true;
        else
            return false;
    }

    @Override
    public String toString(){
        return "int";
    }

    @Override
    public Type deepCopy() {
        return new IntType();
    }

    @Override
    public Value defaultValue() {
        return new IntValue();
    }

}
