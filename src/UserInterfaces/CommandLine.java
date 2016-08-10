package UserInterfaces;

import java.util.Scanner;

/**
 *
 * Created by dcmeade on 8/5/2016.
 */
public class CommandLine {

    private static String welcomeMessage = "----------------------------------------------------\n" +
                                           "| Welcome to the Java Native Database Command Line |\n" +
                                           "----------------------------------------------------";

    private static String helpMessage = "\t\"h\" or \"help\" for help menu\n" +
                                        "\t\"run\" <script name> to run a script\n" +
                                        "\t\"record\" <filename> to record output to a file\n" +
                                        "\t\"q\" or \"quit\" to quit";

    public static void main(String[] args) {

        System.out.println(welcomeMessage);

        boolean stillUsing = true;

        while (stillUsing) {

            // Get input from user
            Scanner scanner = new Scanner(System.in);
            System.out.print("> ");
            String userInput = scanner.nextLine();


            switch (userInput) {

                case "h": case "help":
                    System.out.println(helpMessage);
                    break;

                case "q": case "Q": case "Quit": case "quit":
                    stillUsing = false;
                    System.out.println("Goodbye");
                    break;

                default:

                    // Section to catch regular expressions
                    if (userInput.matches("(run)( +)(\\S+)")) {

                        runScript(userInput);

                        break;
                    }

                    System.out.println("Not a valid Option");
                    break;
            }
        }
    }

    private static void runScript(String userInput) {

        // Strip "Run"
        String scriptName = userInput.replace("run ", "");

        // Strip all white space leading to script name and after script name
        boolean badWhitespaceRemoved = false;
        boolean frontOK = false;
        boolean backOK = false;

        while(!badWhitespaceRemoved) {

            // Check front
            if(scriptName.charAt(0) == ' ') {
                scriptName = scriptName.substring(1,scriptName.length()-1);

                System.out.println(scriptName);

            } else {
                frontOK = true;
            }

            // Check back
            if(scriptName.charAt(scriptName.length()-1) == ' ') {
                scriptName = scriptName.substring(1,0);
            } else {
                backOK = true;
            }

            if ((frontOK == true) && (backOK == true)) {
                badWhitespaceRemoved = true;
            }
        }
    }
}
