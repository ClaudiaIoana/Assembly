package ADT;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MyDictionary<K, V> implements MyIDictionary<K, V>{
    private Map<K,V> map;

    public MyDictionary(){
        map = new HashMap<K, V>();
    }

    @Override
    public void put(K key, V value) {
        map.put(key, value);
    }

    @Override
    public boolean isVarDef(K key) {
        return map.containsKey(key);
    }

    @Override
    public void update(K key, V value) {
        map.put(key, value);
    }

    @Override
    public V lookup(K key) {
        return map.get(key);
    }

    @Override
    public String toString(){
        return map.toString();
    }

    @Override
    public Set<K> getKeys() {
        return this.map.keySet();
    }

    public void delete(K key){
        map.remove(key);
    }

    @Override
    public Map<K, V> getContent(){
        return this.map;
    }

    @Override
    public MyIDictionary<K, V> cloneH() {
        MyIDictionary<K, V> clone = new MyDictionary<K, V>();
        for (K e : map.keySet())
            clone.put(e, lookup(e));
        return clone;
    }

}
