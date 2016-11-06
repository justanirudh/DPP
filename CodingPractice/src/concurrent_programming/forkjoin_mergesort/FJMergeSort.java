package concurrent_programming.forkjoin_mergesort;

import java.util.Arrays;
import java.util.concurrent.RecursiveAction;

import static java.util.concurrent.ForkJoinTask.invokeAll;

/**
 * Created by anirudh on 30/10/16.
 */
public class FJMergeSort {
    private static class MergeSortTask extends RecursiveAction {
        final long[] input;
        final int start;
        final int end;
        final long[] scratch;
        final int arg;

        public MergeSortTask(long[] input, int start, int end, long[] scratch, int arg){
            this.input = input;
            this.start = start;
            this.end = end;
            this.scratch = scratch;
            this.arg = arg;
        }
        @Override
        protected void compute(){
            int size = end - start;
            if (size < 2)
                return;
            if (arg < 2) {
                Arrays.sort(input, start, end);
                return;
            }
            final int pivot = (end - start) / 2 + start;
            MergeSortTask left =
                    new MergeSortTask(input, start, pivot, scratch, arg/2);
            MergeSortTask right =
                    new MergeSortTask(input, pivot, end, scratch, arg/2);
            invokeAll(left,right);
            System.arraycopy(input, start, scratch, start, size);
//            merge(scratch, input, start, pivot, end);
        }
    }

    public  static void main(String arg[]){
//        int groupSize = 4; //number of threads
//        FJTaskRunnerGroup group = new FJTaskRunnerGroup(groupSize);
//        group.invoke(new FJTask()
//        {  public void run() {  synchronized(this)
////        {  sort(A,0, A.length); }
//        } });
    }
}
