/**
 * Dummy.java - Class for warming up the JVM
 * Begun 03/31/18
 * @author Andrew Eissen
 */
package insertionsort;

/**
 * A simple no-op class used by the aforementioned <code>ManualClassLoader</code> class to warm up
 * the JVM.
 * <br />
 * <br />
 * The various code herein related to the JVM warmup operation implementation was retrieved from
 * <a href="http://www.baeldung.com/java-jvm-warmup">"How to Warm up the JVM"</a> by
 * <a href="http://www.baeldung.com/author/baeldung/">Baeldung</a>.
 * <br />
 * <br />
 * @author Andrew Eissen
 */
final class Dummy {

    /**
     * A simple no-op method called within <code>ManualClassLoader.load</code>
     * @return void
     */
    protected void noop() {}
}