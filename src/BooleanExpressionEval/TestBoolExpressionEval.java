package BooleanExpressionEval;

/**
 * Created by dcmeade on 7/14/2016.
 *
 * Takes in a statement as a String and executes it.
 *
 *
 */
public class TestBoolExpressionEval {



    public static void main(String args[]) {



        // Tests for the boolean operator
        // TODO make into junit test cases
        System.out.println("Returned result: " + String.valueOf(BooleanExpressionEval.coreEval("(true and (( true and (         true or false)) and (true or false)))")));


        //System.out.println("Returned result: " + String.valueOf(BooleanExpressionEval.coreEval("(false)")));
    }
}