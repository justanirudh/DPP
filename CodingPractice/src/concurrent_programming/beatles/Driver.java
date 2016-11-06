package concurrent_programming.beatles;

/**
 * Created by anirudh on 25/9/16.
 */
public class Driver {
    public static void main(String[] args){
        //passing the same object to all threads. Alternatively, could have made the class static
        //and just called the method from the run() class
        Beatles beatle = new Beatles();
        Thread  threadA = new BeatlesThread(beatle, "Hendrix");
        Thread  threadB = new BeatlesThread(beatle, "John");

        threadA.start();
        threadB.start();
    }
}
