package Value;

import Type.StringType;
import Type.Type;

public class StringValue implements Value{
    String val;
    public StringValue(String v){
        val = v;
    }

    public StringValue(){
        val = "";
    }

    public String getVal(){
        return val;
    }

    @Override
    public String toString(){
        return val;
    }

    @Override
    public Value deepCopy() {
        return new StringValue(val);
    }

    @Override
    public Type getType() {
        return new StringType();
    }

}
