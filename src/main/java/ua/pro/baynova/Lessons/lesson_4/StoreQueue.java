package ua.pro.baynova.Lessons.lesson_4;

import java.util.LinkedList;
import java.util.List;

public class StoreQueue {

    List<String> queue = new LinkedList<>();

    public void addCustomer(String name){
        queue.addLast(name);
        System.out.println(name + " добавлен(а) в очередь.");

    }

    // Обслужить первого покупателя
    public void serveCustomer(){
        if (!queue.isEmpty()){
            String served = queue.removeFirst();
            System.out.println(served + " обслужен(а).");
        } else {
            System.out.println("Очередь пуста. Некого обслуживать.");
        }
    }

    // Кто сейчас первый?
    public String peekCustomer(){
        if (!queue.isEmpty()){
            return queue.getFirst();
        } else {
           return "Очередь пуста.";
        }
    }

    // Очередь пустая?
    public boolean isEmpty(){
        return queue.isEmpty();
    }

}
