package Value;

import Type.Type;

public interface Value {
    boolean equals(Object another);
    Type getType();

    String toString();

    Value deepCopy();
}
