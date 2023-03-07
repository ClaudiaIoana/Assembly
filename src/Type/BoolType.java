package Type;

import Value.BoolValue;
import Value.Value;

public class BoolType implements Type {
    @Override
    public boolean equals(Object another){
        if(another instanceof BoolType)
            return true;
        else
            return false;
    }

    @Override
    public String toString(){
        return "boolean";
    }

    @Override
    public Type deepCopy() {
        return new BoolType();
    }

    @Override
    public Value defaultValue() {
        return new BoolValue();
    }
}
