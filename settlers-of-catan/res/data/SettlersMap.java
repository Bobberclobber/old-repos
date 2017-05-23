package se.scramble_studios.nehro.settlers_of_catan.res.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Josef on 07/02/2015.
 * <p>
 *     A custom data type used to store Coordinate-Tile pairs for the game board
 * </p>
 */
public class SettlersMap<K, V> {
    private List<SettlersMapElement> elemList;

    public SettlersMap() {
        elemList = new ArrayList<>();
    }

    public void insert(K key, V value) {
        elemList.add(new SettlersMapElement(key, value));
    }

    public V get(int index) {
        return elemList.get(index).getValue();
    }

    public V get(K key) {
        for (SettlersMapElement elem : elemList)
            if(key.equals(elem.getKey()))
                return elem.getValue();
        return null;
    }

    public boolean containsKey(K key) {
        for (SettlersMapElement elem : elemList)
            if(key.equals(elem.getKey()))
                return true;
        return false;
    }

    class SettlersMapElement {
        private K key;
        private V value;

        public SettlersMapElement(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }
}