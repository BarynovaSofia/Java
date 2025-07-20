package ua.pro.baynova.ThreadsExample.lesson_12.BlockingQueue.DelayQueue;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DeliveryPackage implements Delayed {
    private final String customerName;
    private final long deliveryTime;

    public DeliveryPackage(String customerName, long deliveryTime){
        this.customerName = customerName;
        this.deliveryTime = deliveryTime;
    }

    public String getCustomerName(){
        return customerName;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        long remainingTime = deliveryTime - System.currentTimeMillis();
        return unit.convert(remainingTime, TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed other) {
        if (other instanceof DeliveryPackage){
            return Long.compare(this.deliveryTime, ((DeliveryPackage) other).deliveryTime);
        }
        return 0;
    }

    @Override
    public String toString() {
        return "Посылка для " + customerName + " (время: " + deliveryTime + ")";
    }
}
