package concurrent_programming.expandablearray;

/**
 * Created by anirudh on 27/9/16.
 */
public class Client {

    static int CAPACITY = 10;

    static ExpandableArray array;

    public static void main(String[] args) throws InterruptedException{
        Thread t = new Thread(new UseExpandableArrayThread());
        t.start();  //both t and main thread access array.
        array = new ExpandableArray(CAPACITY);
        Thread.sleep(100); //without this, no time for context switch to child thread, hence NPE for get(1)
        System.out.println(array.get(1));
    }
}
