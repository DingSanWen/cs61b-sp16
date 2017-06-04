package lab8;

import java.util.Iterator;
import java.util.Set;

/**
 * Created by 丁天庆 on 2017/6/4.
 */
public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private node root;

    private class node {
        private K key;
        private V val;
        private node left, right;
        private int size;

        private node(K k, V v, int s) {
            this.key = k;
            this.val = v;
            this.size = s;
        }
    }

    @Override
    public void clear() {
        root = null;
    }

    @Override
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    @Override
    public V get(K key) {
        return get(root, key);
    }

    private V get(node n, K key) {
        if (n == null) return null;
        int cmp = key.compareTo(n.key);
        if (cmp < 0) return get(n.left, key);
        else if (cmp > 0) return get(n.right, key);
        else return n.val;
    }

    @Override
    public int size() {
        return size(root);
    }

    private int size(node n) {
        if (n == null) {
            return 0;
        } else {
            return n.size;
        }
    }

    @Override
    public void put(K key, V value) {
        root = put(root, key, value);
    }

    private node put(node n, K key, V value){
        if(n == null) return new node(key, value, 1);
        int cmp = key.compareTo(n.key);
        if(cmp < 0) n.left = put(n.left, key, value);
        else if (cmp > 0) n.right = put(n.right, key, value);
        else n.val = value;
        n.size = size(n.left) + size(n.right) + 1;
        return n;
    }

    public void printInOrder() {

    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
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
        throw new UnsupportedOperationException();
    }
}
