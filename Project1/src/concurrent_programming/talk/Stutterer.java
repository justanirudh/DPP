package concurrent_programming.talk;

/**
 * Created by anirudh on 1/9/16.
 */
class Stutterer extends Thread
{	 static final int ITERS = 20;
    private String m;

    public Stutterer(String m)
    {  this.m = m;
    }

    //overrides run method @Override
    @Override
    public void run()
    {  for (int i = 0; i != ITERS; i++)
    {  System.out.println(m);  }
    }
}
