package concurrent_programming.beatles;

/**
 * Created by anirudh on 25/9/16.
 */
public class BeatlesThread extends Thread{

    protected Beatles beatle = null;

    String potentialBeatle = "";

    public BeatlesThread(Beatles beatle, String potentialBeatle){
        this.beatle = beatle;
        this.potentialBeatle = potentialBeatle;
    }

    public void run() {
        System.out.println(potentialBeatle + ": " + beatle.isBeatle(potentialBeatle));
    }
}
