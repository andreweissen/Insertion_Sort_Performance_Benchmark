/**
 * BenchmarkSorts.java - Class for handling the actual testing and display of results
 * Begun 03/31/18
 * @author Andrew Eissen
 */
package insertionsort;

import java.util.Random;
import java.awt.*;
import javax.swing.*;

/**
 * This class is the main class of the program, containing methods related to the creation of new
 * data sets of various sizes, the sorting of elements, the calculations of various operations, and
 * the display of data in a Swing-driven GUI.
 * <br />
 * <br />
 * @author Andrew Eissen
 */
final class BenchmarkSorts {

    // Declarations
    private int[] sizes;
    private int[][] countIterative, countRecursive;
    private long[][] timeIterative, timeRecursive;

    /**
     * Rubric-required public parameterized constructor
     * @param sizes <code>int[]</code>
     */
    public BenchmarkSorts(int[] sizes) {
        this.setSizes(sizes);
        this.setCountIterative(new int[this.getSizes().length][50]);
        this.setCountRecursive(new int[this.getSizes().length][50]);
        this.setTimeIterative(new long[this.getSizes().length][50]);
        this.setTimeRecursive(new long[this.getSizes().length][50]);
    }

    /**
     * Setter for <code>sizes</code>
     * @param sizes <code>int[]</code>
     * @return void
     */
    private void setSizes(int[] sizes) {
        this.sizes = sizes;
    }

    /**
     * Setter for <code>countIterative</code>
     * @param countIterative <code>int[][]</code>
     * @return void
     */
    private void setCountIterative(int[][] countIterative) {
        this.countIterative = countIterative;
    }

    /**
     * Setter for <code>countRecursive</code>
     * @param countRecursive <code>int[][]</code>
     * @return void
     */
    private void setCountRecursive(int[][] countRecursive) {
        this.countRecursive = countRecursive;
    }

    /**
     * Setter for <code>timeIterative</code>
     * @param timeIterative <code>long[][]</code>
     * @return void
     */
    private void setTimeIterative(long[][] timeIterative) {
        this.timeIterative = timeIterative;
    }

    /**
     * Setter for <code>timeRecursive</code>
     * @param timeRecursive <code>long[][]</code>
     * @return void
     */
    private void setTimeRecursive(long[][] timeRecursive) {
        this.timeRecursive = timeRecursive;
    }

    /**
     * Getter for <code>sizes</code>
     * @return sizes <code>int[]</code>
     */
    protected int[] getSizes() {
        return this.sizes;
    }

    /**
     * Getter for <code>countIterative</code>
     * @return countIterative <code>int[][]</code>
     */
    protected int[][] getCountIterative() {
        return this.countIterative;
    }

    /**
     * Getter for <code>countRecursive</code>
     * @return countRecursive <code>int[][]</code>
     */
    protected int[][] getCountRecursive() {
        return this.countRecursive;
    }

    /**
     * Getter for <code>timeIterative</code>
     * @return timeIterative <code>long[][]</code>
     */
    protected long[][] getTimeIterative() {
        return this.timeIterative;
    }

    /**
     * Getter for <code>timeRecursive</code>
     * @return timeRecursive <code>long[][]</code>
     */
    protected long[][] getTimeRecursive() {
        return this.timeRecursive;
    }

    /**
     * This method is one of two required by the rubric UML diagram, and is used to assemble 50 test
     * cases of input for each array size, passing the data to the iterative and recursive insertion
     * sort methods.
     * <br />
     * <br />
     * Originally, the author attempted to use reflection to consolidate the identical code for
     * running and testing iterative and recursive methods into one method, though this provided to
     * be unwieldy and unworkable. As such, the contents of the second nested for loop are a bit
     * <span style="font-family:'Comic Sans MS'; color:yellow;">sPaGheTti</span> in nature, much to
     * the author's chagrin.
     *
     * @return void
     */
    public void runSorts() {

        // Declarations
        InsertionSort newSort;
        int[] newDataSet, result;

        for (int i = 0; i < this.getSizes().length; i++) {
            for (int j = 0; j < 50; j++) {
                newSort = new InsertionSort();
                newDataSet = this.assembleDataSet(this.getSizes()[i]);

                // Iterative runs
                result = newSort.iterativeSort(newDataSet.clone());
                newSort.checkSort(result);
                this.getCountIterative()[i][j] = newSort.getCount();
                this.getTimeIterative()[i][j] = newSort.getTime();

                // We must remember to set count and time to 0 after iterative
                newSort.reset();

                // Recursive runs
                result = newSort.recursiveSort(newDataSet.clone());
                newSort.checkSort(result);
                this.getCountRecursive()[i][j] = newSort.getCount();
                this.getTimeRecursive()[i][j] = newSort.getTime();
            }
        }
    }

    /**
     * This method simply assembles an array of random values for use in the recursive and iterative
     * methods of <code>InsertionSort</code>. Its size is determined by parameter.
     *
     * @see java.util.Random
     * @param size <code>int</code> number of desired elements in array
     * @return dataSet <code>int[]</code> containing random integers
     */
    private int[] assembleDataSet(int size) {

        // Declarations
        int[] dataSet;
        Random newNumber;

        // Definitions
        dataSet = new int[size];
        newNumber = new Random();

        for (int i = 0; i < size; i++) {
            dataSet[i] = newNumber.nextInt(10000);
        }

        return dataSet;
    }

    /**
     * This method is the second of two required methods required by the rubric UML diagram, and is
     * simply used to display the various data related to the iterative and recursive tests. The
     * author originally attempted a simplified command line-based approach, but eventually decided
     * to use Swing to display the data a little better.
     *
     * @return void
     */
    public void displayReport() {

        // Declarations
        JFrame mainFrame;
        JPanel mainPanel, headersPanel;
        JTextArea textArea;
        String[] headers;
        double[][] dataSets;

        // Definitions
        mainPanel = new JPanel(new GridLayout(0, 1));
        headersPanel = new JPanel(new GridLayout(1, 9));
        headers = new String[] {"Size", "Iterative\nAverage\nCount",
            "Iterative\nCount\nVariance", "Iterative\nAverage\nTime (ns)",
            "Iterative\nTime\nVariance", "Recursive\nAverage\nCount",
            "Recursive\nCount\nVariance", "Recursive\nAverage\nTime (ns)",
            "Recursive\nTime\nVariance"};
        dataSets = this.assembleReport();

        /**
         * This approach to using a <code>JTextArea</code> to imitate a <code>JLabel</code> design
         * while still retaining the text-manipulation abilities of the text area was suggested
         * <a href="https://stackoverflow.com/questions/26420428">here</a>.
         */
        for (String header : headers) {
            textArea = new JTextArea(header);
            textArea.setWrapStyleWord(true);
            textArea.setLineWrap(true);
            textArea.setOpaque(false);
            textArea.setEditable(false);
            textArea.setFocusable(false);
            textArea.setBackground(UIManager.getColor("Label.background"));
            textArea.setFont(UIManager.getFont("Label.font"));
            textArea.setBorder(UIManager.getBorder("Label.border"));
            headersPanel.add(textArea);
        }

        // Add the header panel to the main panel
        mainPanel.add(headersPanel);

        for (int i = 0; i < dataSets.length; i++) {
            mainPanel.add(this.assembleRow(dataSets[i], this.getSizes()[i]));
        }

        // Assemble Frame
        mainFrame = new JFrame("Insertion Sort Benchmark Results");
        mainFrame.setContentPane(mainPanel);
        mainFrame.setSize(1280, 720);
        mainFrame.setVisible(true);
        mainFrame.setResizable(false);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * This method is used to create "rows" to be appended to the GUI representing the data related
     * to each specific data set size and the results thereof. The author got the idea from his
     * previous CMSC 335 SeaPort project series implementation, wherein GUIs were dynamically
     * assembled in a piecemeal fashion as needed.
     *
     * @param cellContents <code>double[]</code> containing the eight data pieces
     * @param size <code>int</code> that represents the data size in question
     * @return rowPanel <code>JPanel</code>
     */
    private JPanel assembleRow(double[] cellContents, int size) {

        // Declarations
        JPanel rowPanel;
        JLabel cellLabel;

        // Definitions
        rowPanel = new JPanel(new GridLayout(1, 9));
        cellLabel = new JLabel(String.valueOf(size));
        cellLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        rowPanel.add(cellLabel);

        for (double cell : cellContents) {
            cellLabel = new JLabel(String.format("%.7g%n", cell));
            cellLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
            rowPanel.add(cellLabel);
        }

        return rowPanel;
    }

    /**
     * Helper method for <code>BenchmarkSorts.displayReport</code> used to process the various
     * averages, standard deviations, and coefficients of variance required by the project for
     * display in the GUI. Making use of a number of calculation-based helper methods, this method
     * simply acquires the correct figures, pushes them into a two dimensional array and returns the
     * results.
     *
     * @return results <code>double[][]</code> containing the collected calculations
     */
    private double[][] assembleReport() {

        // Declaration
        double[][] results;

        // Definition
        results = new double[this.getSizes().length][8];

        /*
         * results[i][0] -> Iterative Average Count   (Column 2)
         * results[i][1] -> Iterative Count Variance  (Column 3)
         * results[i][2] -> Iterative Average Time    (Column 4)
         * results[i][3] -> Iterative Time Variance   (Column 5)
         * results[i][4] -> Recursive Average Count   (Column 6)
         * results[i][5] -> Recursive Count Variance  (Column 7)
         * results[i][6] -> Recursive Average Time    (Column 8)
         * results[i][7] -> Recursive Time Variance   (Column 9)
         */
        for (int i = 0; i < this.getSizes().length; i++) {
            results[i][0] = this.calculateAverage(this.getCountIterative()[i]);
            results[i][1] = this.calculateVariance(this.getCountIterative()[i], results[i][0]);
            results[i][2] = this.calculateAverage(this.getTimeIterative()[i]);
            results[i][3] = this.calculateVariance(this.getTimeIterative()[i], results[i][2]);
            results[i][4] = this.calculateAverage(this.getCountRecursive()[i]);
            results[i][5] = this.calculateVariance(this.getCountRecursive()[i], results[i][4]);
            results[i][6] = this.calculateAverage(this.getTimeRecursive()[i]);
            results[i][7] = this.calculateVariance(this.getTimeRecursive()[i], results[i][6]);
        }

        return results;
    }

    /**
     * One of two overloaded methods used to calculate the average of a set of input values included
     * in an <code>int</code> array. Originally the author attempted a generic class approach to
     * minimize code repetition, but this approach required a fair bit of primitive class wrapper
     * class usage, which the author attempts to minimize whenever possible.
     *
     * @param array <code>int[]</code> contents
     * @return <code>double</code>
     */
    private double calculateAverage(int[] array) {

        // Declaration
        double result;

        // Definition
        result = 0.0;

        for (int element : array) {
            result += element;
        }

        return result / array.length;
    }

    /**
     * One of two overloaded methods used to calculate the average of a set of input values included
     * in an <code>int</code> array. Originally the author attempted a generic class approach to
     * minimize code repetition, but this approach required a fair bit of primitive class wrapper
     * class usage, which the author attempts to minimize whenever possible.
     *
     * @param array <code>long[]</code> contents
     * @return <code>double</code>
     */
    private double calculateAverage(long[] array) {

        // Declaration
        double result;

        // Definition
        result = 0.0;

        for (long element : array) {
            result += element;
        }

        return result / array.length;
    }

    /**
     * One of two overloaded methods of the same name used to calculate the coefficient of variance
     * using the average and the standard deviation thereof. As with the <code>mean</code>
     * methods, the author first attempted a generic class approach, but eventually abandoned it to
     * minimize unnecessary wrapper class usage.
     * <br />
     * <br />
     * Inspiration for this method came from the example implementation suggested by GeeksForGeeks
     * <a href="https://www.geeksforgeeks.org/program-coefficient-variation/">here</a>. Method was
     * tested by manually checking results against the results provided by the variance calculator
     * <a href="http://ncalculators.com/statistics/coefficient-of-variance-calculator.htm">here</a>.
     *
     * @param array <code>int[]</code>
     * @param average <code>double</code> previously calculated average value
     * @return <code>double</code>
     */
    private double calculateVariance(int[] array, double average) {

        // Declaration
        double standardDeviation;

        // Definition
        standardDeviation = 0.0;

        for (int element : array) {
            standardDeviation += Math.pow((element - average), 2);
        }

        standardDeviation = Math.sqrt(standardDeviation / (array.length - 1));

        /*
         * Per statisticshowto.com/probability-and-statistics/how-to-find-a-coefficient-of-variation
         * could also be (standardDeviation / average) * 100.0;
         */
        return standardDeviation / average;
    }

    /**
     * One of two overloaded methods of the same name used to calculate the coefficient of variance
     * using the average and the standard deviation thereof. As with the <code>mean</code>
     * methods, the author first attempted a generic class approach, but eventually abandoned it to
     * minimize unnecessary wrapper class usage.
     * <br />
     * <br />
     * Inspiration for this method came from the example implementation suggested by GeeksForGeeks
     * <a href="https://www.geeksforgeeks.org/program-coefficient-variation/">here</a>. Method was
     * tested by manually checking results against the results provided by the variance calculator
     * <a href="http://ncalculators.com/statistics/coefficient-of-variance-calculator.htm">here</a>.
     *
     * @param array <code>long[]</code>
     * @param average <code>double</code> previously calculated average value
     * @return <code>double</code>
     */
    private double calculateVariance(long[] array, double average) {

        // Declaration
        double standardDeviation;

        // Definition
        standardDeviation = 0.0;

        for (long element : array) {
            standardDeviation += Math.pow((element - average), 2);
        }

        standardDeviation = Math.sqrt(standardDeviation / (array.length - 1));

        /*
         * Per statisticshowto.com/probability-and-statistics/how-to-find-a-coefficient-of-variation
         * could also be (standardDeviation / average) * 100.0;
         */
        return standardDeviation / average;
    }
}