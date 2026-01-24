package ua.pro.baynova.Lessons.lesson_6;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SearchHistory {

    private final int maxSize = 5;

    private final LinkedHashMap<String, Boolean> history = new LinkedHashMap<>(8, 0.75f, true) {
        @Override
        protected boolean removeEldestEntry(Map.Entry<String, Boolean> eldest) {
            return size() > maxSize;
        }
    };

    public void addQuery(String query) {
        history.put(query, true);
    }

    public List<String> getHistory(){
        return new ArrayList<>(history.keySet());
    }
}

