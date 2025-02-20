// TARGET_BACKEND: JVM
// JVM_TARGET: 1.8
// WITH_STDLIB
// FILE: javaDefaultMethod.kt
@Suppress("OPTIONAL_DECLARATION_USAGE_IN_NON_COMMON_SOURCE")
@kotlin.jvm.JvmInline
value class K(val k: String) : J {
    override fun get2() = k
}

fun box(): String {
    val k = K("K")

    val test1 = k.get1() + k.get2()
    if (test1 != "OK") throw AssertionError("test1: $test1")

    val j: J = k
    val test2 = j.get1() + j.get2()
    if (test2 != "OK") throw AssertionError("test2: $test2")

    val test3 = JT.test(k)
    if (test3 != "OK") throw AssertionError("test3: $test3")

    return "OK"
}

// FILE: J.java
public interface J {
    default String get1() { return "O"; }
    default String get2() { return "Failed"; }
}

// FILE: JT.java
public class JT {
    public static String test(J j) {
        return j.get1() + j.get2();
    }
}