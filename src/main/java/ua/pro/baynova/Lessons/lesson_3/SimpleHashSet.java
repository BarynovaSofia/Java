package ua.pro.baynova.Lessons.lesson_3;

import java.util.HashMap;

public class SimpleHashSet<E> {
    private HashMap<E, Object> map;
    private static final Object PRESENT = new Object();

    public SimpleHashSet(){
        map = new HashMap<>();
    }

    public boolean add(E element){
        return map.put(element, PRESENT) == null;
    }

    public boolean remove (E element){
        return map.remove(element) != null;
    }

    public boolean contains (E element){
        return map.containsKey(element);
    }

    public int size(){
        return map.size();
    }

    @Override
    public String toString(){
        return map.keySet().toString();
    }
}
