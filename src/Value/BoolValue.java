package Value;

import Type.BoolType;
import Type.Type;

public class BoolValue implements Value {
    boolean val;

    public BoolValue(boolean v){
        val = v;
    }

    public BoolValue(){
        val = false;
    }

    public boolean getVal(){
        return val;
    }

    public String toString(){
        return Boolean.toString(val);
    }

    @Override
    public Value deepCopy() {
        return new BoolValue(val);
    }

    @Override
    public Type getType() {
        return new BoolType();
    }

}
