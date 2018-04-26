/**
 * UnsortedException.java - User-defined checked exception
 * Begun 03/31/18
 * @author Andrew Eissen
 */
package insertionsort;

/**
 * This user-defined exception class extends <code>java.lang.Exception</code> and is thrown in cases
 * wherein input/output may not have been properly sorted.
 * <br />
 * <br />
 * Class extends <code>Exception</code>
 * @see java.lang.Exception
 * @author Andrew Eissen
 */
final class UnsortedException extends Exception {

    /**
     * Default constructor
     */
    protected UnsortedException() {
        super();
    }

    /**
     * Parameterized constructor
     * @param message <code>String</code>
     */
    protected UnsortedException(String message) {
       super(message);
    }

    /**
     * Parameterized constructor
     * @param cause <code>Throwable</code>
     */
    protected UnsortedException(Throwable cause) {
        super(cause);
    }

    /**
     * Parameterized constructor
     * @param message <code>String</code>
     * @param cause <code>Throwable</code>
     */
    protected UnsortedException(String message, Throwable cause) {
        super(message, cause);
    }
}