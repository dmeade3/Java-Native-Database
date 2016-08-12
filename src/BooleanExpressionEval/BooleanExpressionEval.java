package BooleanExpressionEval;

/**
 * Created by dcmeade on 7/14/2016.
 *
 *
 */


/**
 *
 * Visually how i want to evaluate the expressions
 * Should all be contained in this class:
 *
 *
 *      other class sends the linked list:
 *
 *
 *
 *      (false and ((false) or ((false) and ((true) and (false)))))
 *
 *      (false and ((false) or ((false) and (true and false))))
 *
 *      (false and ((false) or ((false) and (false))))
 *
 *      (false and ((false) or (false and false)))
 *
 *      (false and ((false) or (false)))
 *
 *      (false and (false or false))
 *
 *      (false and (false))
 *
 *      (false and false)
 *
 *      (false)
 *
 *      false
 *
 *
 *
 */


/**
 * Can as of right now only support expressions that are given as booleans
 *
 * must format into only booleans before entering this class
 *
 */




public class BooleanExpressionEval {

    // All Machine Recognized Words, that ar'nt in other lists
    /*private static final String[] keyWords = { "select", "project", "join", "rename", "divide", "times", "union", "intersection",
            "difference", "alias", "load", "show", "help", "quit" };*/

    private static final String[] booleanOperators = { "and", "or"/*, "not"*/ };

    private static final String[] booleans = { "true", "false" };

    /*private static final String[] comparators = { ">", "<", ">=", "<=", "==" };

    private static final String asign = ":=";*/


    public static boolean coreEval(String in) {

        in = normalizeString(in);

        String[] inArray =  in.split("\\s+");

        // Check validity of expression
        verifyBooleanExpression(inArray);

        return evaluateMain(inArray);
    }

    // Will Throw an error if there is an issue with the Boolean Expression
    private static void verifyBooleanExpression(String[] inArray) {

        // Check parenthesis
        int openParen = 0;
        for (String boolExpressionEntity: inArray) {

            if (boolExpressionEntity.contentEquals("(")) {
                openParen++;
            }
            else if (boolExpressionEntity.contentEquals(")")) {
                openParen--;
            }
        }

        // Balance check
        // If unbalanced it will be zero because more ')' will exist
        // and one set will inevitably be unclosed
        if (openParen != 0) {
            throw new Error("Unbalanced Parenthesis: Open Parenthesis Count" + openParen);
        }

        // Check if Contents correct
        for (String boolExpressionEntity: inArray) {

            boolean valid = false;

            for (String boolOperator:booleanOperators) {
                if (boolOperator.contentEquals(boolExpressionEntity)) {
                    valid = true;
                }
            }

            for (String bool:booleans) {
                if (bool.contentEquals(boolExpressionEntity)) {
                    valid = true;
                }
            }

            // check if is a space, blank or parenthesis
            if (boolExpressionEntity.contentEquals(" ")) valid = true;
            if (boolExpressionEntity.contentEquals("")) valid = true;
            if (boolExpressionEntity.contentEquals("(")) valid = true;
            if (boolExpressionEntity.contentEquals(")")) valid = true;

            if (!valid) {
                throw new Error(boolExpressionEntity + " not allowed in boolean Expression");
            }
        }
    }

    // Returns the end result of the boolean expression
    private static boolean evaluateMain(String[] in) {

        boolean finalResult = false;

        boolean finished = false;

        while(!finished) {

            for (int i = 0; i < in.length; i++) {

                if(in[i].contains(")")) {


                    printExpression(in);

                    int y = i;

                    // find the location of the next open paren behind the closed paren here
                    while(true) {

                        if (in[y].contains( "(" )) {

                            // ++ to not include the ( in the expression
                            y++;
                            break;
                        }

                        y--;
                    }

                    String[] expression = new String[i-y];
                    // pack the expression
                    for (int j = 0; j < i-y; j++) {

                        expression[j] = in[y+j];

                    }

                    printExpression(expression);

                    String result = evaluateIndividualExpression(expression);

                    System.out.println(result);



                    // -1 to overwrite the parenthesis
                    in[y-1] = result;

                    // Replace the expression that was just solved with blank spaces
                    for (int j = 0; j <= i-y; j++) {
                        in[j + y] = "";
                    }

                    // Make a new smaller sized in basically the same but minus the blank indices
                    in = stripExcessWhiteIndices(in);

                    //printExpression(in);

                    if (checkSingleBool(in) || (in.length == 1)) {

                        System.out.print("Finished: ");
                        printExpression(in);

                        finalResult = Boolean.parseBoolean(in[0]);

                        finished = true;
                    }
                }
            }
        }

        return finalResult;
    }

    private static String[] stripExcessWhiteIndices(String[] in) {

        // Get the size needed for new array
        int sizeNeeded = 0;

        for (int i = 0; i < in.length; i++) {

            if ((in[i].length() > 0)) {
                sizeNeeded++;
            }
        }

        String[] out = new String[sizeNeeded];

        int loadIndex = 0;

        // Load the array
        for (int i = 0; i < in.length; i++) {

            if(in[i].length() > 0) {
                out[loadIndex] = in[i];

                loadIndex++;
            }
        }

        return out;
    }

    private static String evaluateIndividualExpression(String[] expression) {

        boolean bool1;
        boolean bool2;
        boolean result = false;

        bool1 = Boolean.parseBoolean(expression[0]);
        bool2 = Boolean.parseBoolean(expression[2]);

        switch(expression[1]) {

            case "and":

                result = bool1 && bool2;
                break;

            case "or":

                result = bool1 || bool2;
                break;

            // TODO Figure out how to handle this case
            // Im thinking just having     (not bool)
            case "not":


                break;


            default:
                throw new Error("Invalid format for expression");

        }

        return String.valueOf(result);
    }

    private static String normalizeString(String in) {

        StringBuilder out;

        out = new StringBuilder(addWhiteSpace(in));
        out = new StringBuilder(stripExcessWhiteSpace(out.toString()));

        return out.toString();
    }

    private static String addWhiteSpace(String in) {

        StringBuilder out = new StringBuilder();

        for (int i = 0; i < in.length(); i++) {

            switch(in.charAt(i)) {

                case '>': case '<': case '=': case ':':
                    if (in.charAt(i+1) == '=') {
                        out.append(" ");
                        out.append(in.charAt(i));
                        out.append(in.charAt(i+1));
                        out.append(" ");
                        i++;
                    }
                    else {
                        out.append(" ");
                        out.append(in.charAt(i));
                        out.append(" ");
                    }
                    break;

                case '(':case ')':
                    out.append(" ");
                    out.append(in.charAt(i));
                    out.append(" ");

                    break;

                default:
                    out.append(in.charAt(i));
                    break;

            }
        }

        return out.toString();
    }

    private static String stripExcessWhiteSpace(String in) {

        StringBuilder out = new StringBuilder("");

        int whiteCount = 0;

        for (int i = 0; i < in.length(); i++) {

            if(in.charAt(i) != ' ') {
                out.append(in.charAt(i));

                whiteCount = 0;
            }
            else {

                whiteCount++;

                if(whiteCount < 2) {
                    out.append(in.charAt(i));
                }
            }
        }

        return out.toString();
    }

    // Returns true if it is a single boolean
    private static boolean checkSingleBool(String[] in) {

        StringBuilder comp2 = new StringBuilder();

        for (int i = 0; i < in.length; i++) {
            comp2.append(in[i]);
        }

        for (String bool:booleans) {

            StringBuilder compareString = new StringBuilder();

            compareString.append(" ( ");
            compareString.append(bool);
            compareString.append(" ) ");

            if (compareString.toString().contentEquals(comp2.toString())) {
                return true;
            }
        }

        return false;
    }

    private static void printExpression(String[] in) {

        System.out.println();

        for (int i = 0; i < in.length; i++) {

            if (i != in.length-1) {
                System.out.print(in[i] + " ");
            }
            else {
                System.out.print(in[i]);
            }

        }

        System.out.println("\n");

    }
}
