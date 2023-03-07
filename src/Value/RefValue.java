package Value;

import Type.RefType;
import Type.Type;

public class RefValue implements Value{
    int address;
    Type locationType;

    public RefValue(int ad, Type t){
        this.address = ad;
        this.locationType = t;
    }

    public int getAddress(){
        return address;
    }

    public Type getLocationType(){
        return locationType;
    }

    @Override
    public Type getType(){
        return new RefType(locationType);
    }

    @Override
    public Value deepCopy() {
        return new RefValue(address, locationType.deepCopy());
    }

    @Override
    public String toString(){
        return address+" "+locationType.toString();
    }
}
