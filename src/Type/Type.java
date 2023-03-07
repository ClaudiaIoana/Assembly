package Type;

import Value.Value;

public interface Type {
    String toString();
    boolean equals(Object a);
    Type deepCopy();

    public Value defaultValue();
}
