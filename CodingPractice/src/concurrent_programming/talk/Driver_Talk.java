package concurrent_programming.talk;

/**
 * Created by anirudh on 1/9/16.
 */
class Driver_Talk
{	public static void main(String[] args){
    //create two Thread objects
    Thread s = new Stutterer("Go ");
    Thread t = new Stutterer("Gators");
    //start the threads
    s.start();
    t.start();
}
}
