package cop5618;
public class Timer {

    /** array of raw times from System.nanoTime(); */
    long[] rawNanoTimes;

    /** labels used by toString */
    final String[] labels;
    final int numValues;
    private int index = 0;

    /**
     * Creates a timer which labels.length consecutive durations.
     * Precondition, labels.length > 0
     *
     * @param labels
     */
    public Timer(String... labels) {
        numValues = labels.length + 1;
        rawNanoTimes = new long[numValues];
        this.labels = labels;
    }


    /**
     * Invoked to record the start or end of a time duration
     * This must be called labels.length+1 times in the timed code.
     */
    public void now() {
        rawNanoTimes[index++] = System.nanoTime();
    }

    /**
     * Computes the elapsed time in nanoseconds between the two points indicated by the indices.
     * For example, to obtain the duration between the first call to now() and the second call to now(),
     * use elapsed(0,1);
     *
     * @param startIndex
     * @param endIndex
     * @return
     */
    public long elapsedNanos(int startIndex, int endIndex) {
        return rawNanoTimes[endIndex] - rawNanoTimes[startIndex];
    }


    /**
     * Computes the elapsed time in milliseconds between the two points indicated by the indices.
     * For example, to obtain the duration between the first call to now() and the second call to now(),
     * use elapsed(0,1);
     *
     * @param startIndex
     * @param endIndex
     * @return
     */
    public double elapsedMillis(int startIndex, int endIndex) {
        return toMillis(elapsedNanos(startIndex, endIndex));
    }

    //for conversion from nanoseconds to milliseconds
    private static long toMillis(long d)  { return d/(C2/C0); }
    private static double toMillis(double d)  { return d/(C2/C0); }
    private static final long C0 = 1L;
    private static final long C1 = C0 * 1000L;
    private static final long C2 = C1 * 1000L;


    /**
     * Returns the raw times recorded by calls to now()
     * @return
     */
    public long[] getRawTimes() {
        return rawNanoTimes;
    }

    /**
     * Computes statistics for the given arrray of timers.
     * If instantiated arrays are passed in, they are filled in.  If not,
     * a temporary array is created in this method.
     *
     * @param timers
     * @param meanDurationsNanos
     * @param meanPercentOfTotal
     * @param stdDevNanos
     */
    public static void computeStats(Timer[] timers, double[] meanDurationsNanos, double [] meanPercentOfTotal, double[] stdDevNanos){
        int numTimers = timers.length;
        int numValues = (timers[0]).numValues;
        int totalIndex = numValues-1;
        //if array not passed in, create temp array for calculation.  It has an element for each label, plus the total.
        //if passed in, initialize to zero
        if (meanDurationsNanos == null){ meanDurationsNanos = new double[numValues];}
        else {for (int i=0; i < meanDurationsNanos.length; i++) meanDurationsNanos[i] = 0.0;}
        if (meanPercentOfTotal == null) { meanPercentOfTotal = new double[numValues]; }
        else {for (int i=0; i < meanPercentOfTotal.length; i++) meanPercentOfTotal[i] = 0.0;}
        if (stdDevNanos == null){ stdDevNanos = new double[numValues]; }
        else {for (int i=0; i < meanPercentOfTotal.length; i++) meanPercentOfTotal[i] = 0.0;}


        //compute total durations and durations squared
        double[] meanDurationsSquared = new double[numValues];
        for (int t = 0; t < numTimers; t++) {
            double duration = timers[t].elapsedNanos(0, numValues - 1);
            meanDurationsNanos[totalIndex] += duration;
            meanDurationsSquared[totalIndex] += duration*duration;
        }
        meanDurationsNanos[totalIndex] /= numTimers;
        meanDurationsSquared[totalIndex] /= numTimers;
        stdDevNanos[totalIndex] = Math.sqrt(meanDurationsSquared[totalIndex] - meanDurationsNanos[totalIndex]*meanDurationsNanos[totalIndex]);
        meanPercentOfTotal[totalIndex]=1.0;
        //compute durations between timers
        if (numValues > 2){//haven't already printed timers
            for (int i = 0; i < numValues-1; i++) {
                meanDurationsNanos[i] = 0;
                for (int t = 0; t < numTimers; t++) {
                    double duration = timers[t].elapsedNanos(i, i + 1);
                    assert duration>0: "duration for " + timers[t].labels[i] + "<=0.  Probably missing a call to timer now() method";
                    meanDurationsNanos[i] += duration;
                    meanDurationsSquared[i] += duration*duration;
                    meanPercentOfTotal[i] += (double)duration/(double)timers[t].elapsedNanos(0, numValues - 1);
                }
                meanDurationsNanos[i] /= numTimers;
                meanDurationsSquared[i] /= numTimers;
                meanPercentOfTotal[i] /=numTimers;
                stdDevNanos[i] = Math.sqrt(meanDurationsSquared[i] - meanDurationsNanos[i]*meanDurationsNanos[i]);
            }
        }

    }


    /**
     * Computes statistics for the given array of timers and formats the results in a String.
     * The mean total duration is set since this is often useful to the caller in later speedup calculations.
     *
     * @param timers
     * @param meanDurationNanos //1 element array to return computed total value
     * @return
     */
    public static String statsToString(Timer[] timers, double[] meanDurationNanos) {
        int numValues = (timers[0]).numValues;
        int totalIndex = numValues-1;
        double[] meanDurations = new double[numValues];
        double[] meanPercentOfTotal = new double[numValues];
        double[] stdDev = new double[numValues];
        computeStats(timers, meanDurations, meanPercentOfTotal, stdDev);
        StringBuffer sb = new StringBuffer();
        if (numValues > 2){
            for (int i = 0; i < numValues - 1; i++) {
                String s1 = String.format("%s mean duration=%.1f msecs",timers[0].labels[i], toMillis(meanDurations[i]));
                String s2 = String.format(", stddev=%.1f, mean percent of total=%.0f%n",toMillis(stdDev[i]), meanPercentOfTotal[i]*100);
                sb.append(s1).append(s2);
            }
        }
        sb.append(String.format("total mean duration=%.0f msecs, stddev=%.1f%n", toMillis(meanDurations[totalIndex]), toMillis(stdDev[totalIndex])));
        meanDurationNanos[0]=meanDurations[totalIndex];
        return sb.toString();
    }

    /**
     * Computes statistics for the given array of timers and formats the results in a String.
     * serialDurationNanos is an input value used to compute speedup.
     *
     * @param timers
     * @param serialDurationNanos
    //	 * @param [in] timers
    //   * @param [in] serialDurationNanos
     * @return Stats in printable string format
     */
    public static String statsToString(Timer[] timers, double serialDurationNanos) {
        int numValues = (timers[0]).numValues;
        int totalIndex = numValues-1;
        double[] meanDurations = new double[numValues];
        double[] meanPercentOfTotal = new double[numValues];
        double[] stdDev = new double[numValues];
        computeStats(timers, meanDurations, meanPercentOfTotal, stdDev);
        StringBuffer sb = new StringBuffer();
        if (numValues > 2){
            for (int i = 0; i < numValues-1; i++) {
                String s1 = String.format("%s mean duration=%.1f msecs",timers[0].labels[i], toMillis(meanDurations[i]));
                String s2 = String.format(", stddev=%.1f, mean percent of total=%.0f%n",toMillis(stdDev[i]), meanPercentOfTotal[i]*100);
                sb.append(s1).append(s2);
            }}
        sb.append(String.format("total mean duration=%.0f msecs, stddev=%.1f%n", toMillis(meanDurations[totalIndex]), toMillis(stdDev[totalIndex])));
        sb.append(String.format("speedup=%.1f%n", serialDurationNanos/meanDurations[totalIndex]));
        return sb.toString();
    }



    /**
     * Returns a string formatting the information contained in this Timer
     */
    @Override
    public String toString() {
        double total = elapsedMillis(0, index - 1);
        StringBuffer sb = new StringBuffer();
        if(numValues>2){
            for (int i = 0; i < numValues - 1; i++) {
                double duration = elapsedMillis(i, i + 1);
                String s1 = String.format("%s duration=%.0f msecs",labels[i], duration);
                String s2 = String.format(", percent of total=%.0f%n", (duration / total)*100 ) ;
                sb.append(s1).append(s2);
            }
        }
        sb.append(String.format("total duration=%.0f msecs%n", total));
        return sb.toString();
    }


}