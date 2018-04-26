/**
 * ManualClassLoader.java - Class for creating <code>Dummy</code> objects for warmup
 * Begun 03/31/18
 * @author Andrew Eissen
 */
package insertionsort;

/**
 * This class was suggested by the above article as the means of warming up the JVM via the creation
 * of a hundred thousand dummy class objects and the invocation of each instance's own no-op method,
 * <code>Dummy.m</code>. As such, it simply possess one <code>static</code> method,
 * <code>load</code>, that employs a <code>for</code> loop to create each instance.
 * <br />
 * <br />
 * The various code herein related to the JVM warmup operation implementation was retrieved from
 * <a href="http://www.baeldung.com/java-jvm-warmup">"How to Warm up the JVM"</a> by
 * <a href="http://www.baeldung.com/author/baeldung/">Baeldung</a>.
 * <br />
 * <br />
 * @author Andrew Eissen
 */
final class ManualClassLoader {

    /**
     * This method simply creates over a hundred thousand <code>Dummy</code> class objects via the
     * use of a <code>for</code> loop, calling the no-op <code>Dummy.noop</code> method.
     *
     * @return void
     */
    protected static void load() {

        // Declaration
        Dummy dummy;

        for (int i = 0; i < 150000; i++) {
            dummy = new Dummy();
            dummy.noop();
        }
    }
}