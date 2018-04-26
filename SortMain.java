/**
 * SortMain.java - Handles JVM warmup and initializes testing and display of results
 * Begun 03/31/18
 * @author Andrew Eissen
 */
package insertionsort;

/**
 * This class invokes the major methods of the various classes in the benchmarking program, as well
 * as running the JVM update method progression. It contains a single <code>main</code> method.
 * Originally, the author attempted to include the JVM warmup classes in this main class, but
 * eventually elected to move them to separate files.
 * <br />
 * <br />
 * @author Andrew Eissen
 */
final class SortMain {

    // See below for commentary
    static {

        //Declarations
        long startTime, endTime;

        startTime = System.nanoTime();
        ManualClassLoader.load();
        endTime = System.nanoTime();
        System.out.println("Warm up time: " + (endTime - startTime));
    }

    /**
     * Main method simply warms up the JVM and begins the benchmarking process, supplying
     * <code>BenchmarkSorts</code> with a diverse set of test cases as required by the Project 1
     * rubric. After the sorts are accomplished, the GUI is displayed to show the results.
     *
     * @param args <code>String[]</code>
     * @return void
     */
    public static void main(String[] args) {

        // Declarations
        int[] sizes;
        long startTime, endTime;
        BenchmarkSorts newBenchmark;

        // Intial JVM warmup, see class Javadoc for details
        startTime = System.nanoTime();
        ManualClassLoader.load();
        endTime = System.nanoTime();
        System.out.println("Total time: " + (endTime - startTime));

        // Definitions, test case sizes
        sizes = new int[] {10, 25, 50, 75, 100, 250, 500, 750, 1000, 1250};
        newBenchmark = new BenchmarkSorts(sizes);

        // Run and display tests
        newBenchmark.runSorts();
        newBenchmark.displayReport();
    }
}