package concurrent_programming.expandablearray;

/**
 * Created by anirudh on 27/9/16.
 */
public class Client {

    static int CAPACITY = 10;

    static ExpandableArray array;

    public static void main(String[] args) throws InterruptedException{
        array = new ExpandableArray(CAPACITY);
        Thread t = new Thread(new UseExpandableArrayRunnable());
        t.start();  //both t and main thread access array.
        Thread.sleep(100); //without this, no time for context switch to child thread, hence NPE for get(1)
        System.out.println(array.get(0));
    }
}
