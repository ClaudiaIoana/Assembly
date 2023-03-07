package ADT;

import java.util.Map;
import java.util.Set;

public interface MyIDictionary <K, V>{
    void put(K key, V value);
    boolean isVarDef(K key);
    void update(K key, V value);
    V lookup(K key);
    String toString();
    Set<K> getKeys();
    void delete(K key);
    public Map<K, V> getContent();
    MyIDictionary <K, V> cloneH();
}
