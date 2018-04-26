/**
 * InsertionSort.java - Class for benchmarking insertion sort
 * Begun 03/31/18
 * @author Andrew Eissen
 */
package insertionsort;

/**
 * This class is one of five required by the Project 1 design rubric's UML diagram, and is includes
 * both an iterative and recursive version of the insertion sort diagram, constructed from several
 * composite algorithms and assembled by the author. Implementations were derived and modified from
 * those suggested by <a href="https://www.geeksforgeeks.org/">GeeksForGeeks</a>, as well as from
 * the CMSC 451 Homework 3 document.
 * <br />
 * <br />
 * Class implements <code>SortInterface</code>
 * @see insertionsort.SortInterface
 * @author Andrew Eissen
 */
final class InsertionSort implements SortInterface {

    // Declarations
    private int count;
    private long time;

    /**
     * Default constructor
     */
    protected InsertionSort() {
        this.setCount(0);
        this.setTime(0);
    }

    // Setters

    /**
     * Setter for <code>this.count</code>
     * @param count <code>int</code>
     * @return void
     */
    private void setCount(int count) {
        this.count = count;
    }

    /**
     * Setter for <code>this.time</code>
     * @param time <code>int</code>
     * @return void
     */
    private void setTime(long time) {
        this.time = time;
    }

    // Utility methods

    /**
     * Reset method for <code>count</code> and <code>time</code>, called only from within the body
     * of <code>BenchmarkSorts.runSorts</code>.
     *
     * @return void
     */
    protected void reset() {
        this.setCount(0);
        this.setTime(0);
    }

    /**
     * Method is used to determine whether or not to throw a new <code>UnsortedException</code>, as
     * per the Project 1 rubric's requirement to have the <code>InsertionSort</code> class be the
     * one to throw the <code>UnsortedException</code>. This method is called only by
     * <code>BenchmarkSorts.runSorts</code>.
     *
     * @param list <code>int[]</code>
     * @return void
     */
    protected void checkSort(int[] list) {
        try {
            for (int i = 0; i < list.length - 1; i++) {
                if (list[i] > list[i + 1]) {
                    throw new UnsortedException();
                }
            }
        } catch (UnsortedException ex) {
            System.out.println("Error: A sorting error has occurred.");
        }
    }

    // Interface-required methods

    /**
     * This method is one of those required by the <code>SortInterface</code>, though the author has
     * elected to use this method in tandem with an overloaded helper method. This method begins the
     * timer, calls the recursive helper method below, sets the time at completion, and finally
     * returns the sorted list.
     * <br />
     * <br />
     * Originally, the author made use of a recursive insertion sort algorithm implemented by
     * GeeksForGeeks <a href="https://www.geeksforgeeks.org/recursive-insertion-sort/">here</a> as
     * the helper method this method calls, but eventually decided to replace this method with a
     * two-method approach used on the first page of the CMSC 451 Homework 3 document. The reason
     * for this change was that the author wanted a purely recursive implementation bereft of any
     * loops to keep the two methods clearly distinct.
     *
     * @param list <code>int[]</code>
     * @return list Sorted list, same elements as input array
     */
    @Override
    public int[] recursiveSort(int[] list) {

        // Declarations
        long startTime, endTime;

        // Definitions
        startTime = System.nanoTime();
        list = this.recursiveSort(list, 1);
        endTime  = System.nanoTime();
        this.setTime(endTime - startTime);

        return list;
    }

    /**
     * This method is one of two overloaded methods of <code>recursiveSort</code> used to represent
     * the two nested <code>for</code> loops present in the iterative version. As is the case with
     * the method below, this method was modified from the CMSC 451 Homework 3 assignment and
     * replaces a GeeksForGeeks implementation that made use of a <code>while</code> loop. This
     * method is named <code>insert</code> in the Homework 3 implementation.
     *
     * @param list <code>int[]</code>
     * @param i <code>int</code>
     * @return list <code>int[]</code>
     */
    private int[] recursiveSort(int[] list, int i) {

        // Declarations
        int j, k;

        if (i < list.length) {
            j = list[i];
            k = this.recursiveSort(list, i, j);
            list[k] = j;
            this.recursiveSort(list, i + 1);
        }

        return list;
    }

    /**
     * This method is one of two overloaded methods of <code>recursiveSort</code> used to represent
     * the two nested <code>for</code> loops present in the iterative version. As is the case with
     * the method above, this method was modified from the CMSC 451 Homework 3 assignment and
     * replaces a GeeksForGeeks implementation that made use of a <code>while</code> loop. This
     * method is named <code>shift</code> in the Homework 3 implementation.
     * <br />
     * <br />
     * As is the case with the iterative implementation, this method counts the shift operation as
     * the critical operation. Due to the shared dataset and the number of times various values are
     * moved about in the list, we should expect the values of <code>count</code> to be equal in
     * both the iterative and recursive cases.
     *
     * @param list <code>int[]</code>
     * @param i <code>int</code>
     * @param j <code>int</code>
     * @return k <code>int</code>
     */
    private int recursiveSort(int[] list, int i, int j) {

        // Declaration
        int k;

        // Definition
        k = i;

        if (i > 0 && list[i - 1] > j) {
            list[i] = list[i - 1];
            this.setCount(this.getCount() + 1);
            k = this.recursiveSort(list, i - 1, j);
        }

        return k;
    }

    /**
     * As the name implies, this method is used to sort the parameter array iteratively. It is one
     * of the required methods of the <code>SortInterface</code>, and simply returns the sorted
     * contents of the array parameter. As is the case with the recursive implementation above, we
     * count the shift operation as the critical operation of the method, and as dataset and shifted
     * values are moved about in a similar manner, we should expect the same values of
     * <code>count</code> to be shared between the iterative and recursive approaches.
     * <br />
     * <br />
     * This method was adapted and modified from the GeeksForGeeks implementation suggested
     * <a href="https://www.geeksforgeeks.org/insertion-sort/">here</a>.
     *
     * @param list <code>int[]</code>
     * @return list <code>int[]</code>
     */
    @Override
    public int[] iterativeSort(int[] list) {

        // Declarations
        int i, j, k;
        long startTime, endTime;

        // Definition
        startTime = System.nanoTime();

        for (i = 1; i < list.length; i++) {
            k = list[i];

            for (j = i - 1; j >= 0 && list[j] > k; j--) {
                list[j + 1] = list[j];
                this.setCount(this.getCount() + 1);
            }

            list[j + 1] = k;
        }

        endTime = System.nanoTime();
        this.setTime(endTime - startTime);

        return list;
    }

    /**
     * Required getter for <code>this.count</code>
     * @return count <code>int</code>
     */
    @Override
    public int getCount() {
        return this.count;
    }

    /**
     * Required getter for <code>this.time</code>
     * @return time <code>long</code>
     */
    @Override
    public long getTime() {
        return this.time;
    }
}