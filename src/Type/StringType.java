package Type;

import Value.StringValue;
import Value.Value;

public class StringType implements Type{
    public StringType(){}

    @Override
    public boolean equals(Object another){
        if(another instanceof StringType)
            return true;
        else
            return false;
    }

    @Override
    public String toString(){
        return "string";
    }


    @Override
    public Type deepCopy() {
        return new StringType();
    }

    @Override
    public Value defaultValue() {
        return new StringValue();
    }
}
