package Junit_Testing;

import DataStorage.Table;

/**
 * Created by David Meade on 8/5/2016.
 */

public class TableTest {

    private void sampleTest() {

        String methodName = "Sample Test";

        try {

            TestingFunctions.isTrue( String.valueOf(Table.class).equals("class DataStorage.Table") );

        }
        catch(Exception e) { TestingFunctions.printExceptionInfo(e, getClass(), methodName); }
    }





    public void run() {
        System.out.println("...Table Testing Started...");

        // Test Methods
        sampleTest();

        System.out.println("...Table Testing Completed...");
    }
}