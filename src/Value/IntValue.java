package Value;

import Type.IntType;
import Type.Type;

public class IntValue implements Value {
    int val;
    public IntValue(int v){
        val = v;
    }

    public IntValue(){
        val = 0;
    }

    public int getVal(){
        return val;
    }

    @Override
    public String toString(){
        return Integer.toString(val);
    }

    @Override
    public Value deepCopy() {
        return new IntValue(val);
    }

    @Override
    public Type getType() {
        return new IntType();
    }

}
