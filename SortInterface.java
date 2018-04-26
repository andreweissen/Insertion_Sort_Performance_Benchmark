/**
 * SortInterface.java - Interface for various sort algorithms
 * Begun 03/31/18
 * @author Andrew Eissen
 */
package insertionsort;

/**
 * This interface contains four required methods that can be useful in all conventional sort
 * operation algorithms, allowing users to benchmark various important operations and the time
 * required to sorted both iteratively and recursively.
 * <br />
 * <br />
 * @author Andrew Eissen
 */
interface SortInterface {

    /**
     * Method should return an <code>int</code> array containing the properly sorted contents of the
     * parameter array, <code>list</code>. Method should employ recursion to properly sort the array
     * and may use an overloaded method to assist in accomplishing this.
     *
     * @param list <code>int[]</code>
     * @return <code>int[]</code>
     */
    public int[] recursiveSort(int[] list);

    /**
     * Method should return an <code>int</code> array containing the properly sorted contents of the
     * parameter array, <code>list</code>. Method should employ an iterative approach to sorting the
     * array, using a number of <code>for</code> or <code>while</code> loops as needed.
     *
     * @param list <code>int[]</code>
     * @return <code>int[]</code>
     */
    public int[] iterativeSort(int[] list);

    /**
     * This method is a <code>public</code> accessor for the <code>int</code> field,
     * <code>count</code>.
     * @return count <code>int</code>
     */
    public int getCount();

    /**
     * This method is a <code>public</code> accessor for the <code>long</code> field,
     * <code>time</code>.
     * @return time <code>long</code>
     */
    public long getTime();
}