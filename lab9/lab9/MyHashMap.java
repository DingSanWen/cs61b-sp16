package lab9;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.ArrayList;

/**
 * Created by 丁天庆 on 2017/6/27.
 */
public class MyHashMap<K,V> implements Map61B<K, V> {

    private class Entry {
        private K key;
        private V value;
        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return this.key;
        }

        public V getValue() {
            return this.value;
        }

        public void setValue (V v) {
            this.value = v;
        }
    }

    int items; //number of key-value mappings
    int size; //number of buckets
    double loadFactor; //load factor
    HashSet<K> keys;
    ArrayList<Entry>[] map;

    public MyHashMap() {
        this(997,2);//this is a call to the third constructor
    }

    public MyHashMap(int initialSize) {
        this(initialSize, 2);
    }

    public MyHashMap(int initialSize, double loadFactor) {
        this.size = initialSize;
        this.items = 0;
        this.loadFactor = loadFactor;
        keys = new HashSet<K>();
        map = (ArrayList<Entry>[]) new ArrayList [size];
        for (int i = 0; i < size; i++) {
            map[i] = new ArrayList<Entry>();
        }
    }

    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff % size);//copy from p465, don't know what it means.
    }

    private void resize(int chains) {
        MyHashMap<K, V> temp = new MyHashMap<K, V>(chains);
        for (int i = 0; i < size; i++) {
            for(Entry en : map[i]) {
                temp.put(en.getKey(), en.getValue());
            }
        }
        this.size = temp.size;
        this.items = temp.items;
        this.map = temp.map;
    }

    @Override
    public void clear() {
        items = 0;
        keys = new HashSet<K>();
        map = (ArrayList<Entry>[]) new ArrayList [size];
        for (int i = 0; i < size; i++) {
            map[i] = new ArrayList<Entry>();
        }
    }

    @Override
    public boolean containsKey(K key) {
        return keys.contains(key);
    }

    @Override
    public V get(K key) {
        int hashCode = hash(key);
        ArrayList<Entry> bucket = map[hashCode];
        for(Entry temp : bucket) {
            if (temp.getKey().equals(key)) {
                return temp.getValue();
            }
        }
        return null;
    }

    @Override
    public int size() {
        return items;
    }

    @Override
    public void put(K key, V value) {
        int hashCode = hash(key);
        ArrayList<Entry> bucket = map[hashCode];
        int findKey = 0;
        for(Entry temp : bucket) {
            if (temp.getKey().equals(key)) {
                temp.setValue(value);
                findKey = 1;
            }
        }
        if (findKey == 0) {
            bucket.add(new Entry(key, value));
            items += 1;
        }
        keys.add(key);

        if(items / size > loadFactor) {
            resize(2 * size);
        }
    }

    @Override
    public Set<K> keySet() {
        return keys;
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        return new KeyIterator<K>();
    }

    public class KeyIterator<K> implements Iterator <K> {
        private int index;
        private K [] keyArray;
        public KeyIterator() {
            index = 0;
            keyArray = (K [])keys.toArray();
        }

        @Override
        public boolean hasNext() {
            return (index != size);
        }

        @Override
        public K next() {
            K returnItem = keyArray[index];
            index += 1;
            return returnItem;
        }
    }
}
