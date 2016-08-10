package Junit_Testing;

/**
 * Created by David Meade on 8/5/2016.
 */
public class TestingFunctions {

    public static void isTrue(boolean result) throws Exception {

        // If the boolean submitted is false then throw an exception
        if(result == false) throw new Exception();
    }

    public static void isFalse(boolean result) throws Exception {

        // If the boolean submitted is false then throw an exception
        if(result == true) throw new Exception();
    }

    public static void printExceptionInfo(Exception e, Class className, String methodName) {
        System.out.println("Exception in:\n\tClass:       " + className + "\n\tMethod:      " + methodName);
        StackTraceElement stackTrace = e.getStackTrace()[1];
        System.out.println("\tLine Number: " + stackTrace.getLineNumber() + "\n");
    }
}
