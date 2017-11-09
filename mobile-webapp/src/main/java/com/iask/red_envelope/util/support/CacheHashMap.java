package com.iask.red_envelope.util.support;

import java.io.Serializable;

/**
 * for concurrent CacheHashMap
 * <p/>
 * 借鉴com.alibaba.fastjson.util.IdentityHashMap并实现CacheHashMap
 * <p/>
 * key还是使用equals方法做比较，但这个Map的大小是固定的，不会扩容，以便应对高并发下transfer死循环的问题
 */
@SuppressWarnings("unchecked")
public class CacheHashMap<K, V> implements Serializable {

    public static final int DEFAULT_TABLE_SIZE = 1024;
    static final int MAXIMUM_CAPACITY = 1 << 30;

    private final Entry<K, V>[] buckets;
    private final int indexMask;

    public CacheHashMap() {
        this(DEFAULT_TABLE_SIZE);
    }

    public CacheHashMap(int tableSize) {
        if (tableSize < 0) {
            throw new IllegalArgumentException("Illegal initial tableSize: " + tableSize);
        }
        int roundTableSize = roundUpToPowerOf2(tableSize);
        if (roundTableSize < 4) {
            roundTableSize = 4;
        }
        this.indexMask = tableSize - 1;
        this.buckets = new Entry[roundTableSize];
    }

    public final V get(K key) {
        if (key == null) {
            return null;
        }
        final int hash = hash(key);
        final int bucket = hash & indexMask;

        for (Entry<K, V> entry = buckets[bucket]; entry != null; entry = entry.next) {
            if (key.equals(entry.key)) {
                return (V) entry.value;
            }
        }

        return null;
    }

    public boolean put(K key, V value) {
        if (key == null) {
            throw new NullPointerException("key");
        }
        final int hash = hash(key);
        final int bucket = hash & indexMask;

        for (Entry<K, V> entry = buckets[bucket]; entry != null; entry = entry.next) {
            if (key.equals(entry.key)) {
                entry.value = value;
                return true;
            }
        }

        Entry<K, V> entry = new Entry<K, V>(key, value, hash, buckets[bucket]);
        buckets[bucket] = entry;  // 并发是处理时会可能导致缓存丢失，但不影响正确性

        return false;
    }

    public int size() {
        int size = 0;
        for (int i = 0; i < buckets.length; ++i) {
            for (Entry<K, V> entry = buckets[i]; entry != null; entry = entry.next) {
                size++;
            }
        }
        return size;
    }

    protected static final class Entry<K, V> {

        public final int hashCode;
        public final K key;
        public V value;

        public final Entry<K, V> next;

        public Entry(K key, V value, int hash, Entry<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
            this.hashCode = hash;
        }
    }

    final int hash(Object k) {
        int h = 0;

        h ^= k.hashCode();

        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }

    private static int roundUpToPowerOf2(int number) {
        // assert number >= 0 : "number must be non-negative";
        return number >= MAXIMUM_CAPACITY
                ? MAXIMUM_CAPACITY
                : (number > 1) ? Integer.highestOneBit((number - 1) << 1) : 1;
    }
}