package jni;

public class DalJNI {
    private final String mongoDbConnUrl = "";
    private final String postgresDbConnUrl = "";

    static {
        System.loadLibrary("native");
    }

    public static void main(String[] args) {
        new DalJNI().sayHello();
    }

    // Declare a native method sayHello() that receives no arguments and returns void
    private native void sayHello();
}
