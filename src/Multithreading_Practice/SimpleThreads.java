package Multithreading_Practice;

/**
 * Created by dcmeade on 7/13/2016.
 */

public class SimpleThreads {

    // Display a message, preceded by
    // the name of the current thread
    private static void printThreadMessage(String message) {

        String threadName = Thread.currentThread().getName();
        System.out.format("%s: %s%n", threadName, message);
    }

    public static class MessageLoop implements Runnable {

        int index;

        public void run() {
            String importantInfo[] = {
                    "Message 1",
                    "Message 2",
                    "Message 3",
                    "Message 4"
            };

            try {
                for (String anImportantInfo : importantInfo) {

                    // Pause for 1 second
                    Thread.sleep(1000);

                    // Print a message
                    printThreadMessage(anImportantInfo);
                }
            } catch (InterruptedException e) {
                printThreadMessage("I wasn't done!");
            }
        }
    }

    public static void main(String args[]) throws InterruptedException {

        // Delay, in milliseconds before
        // we interrupt MessageLoop
        // thread (default one hour).
        long patience = 1000 * 60 * 60;

        printThreadMessage("Starting MessageLoop thread");
        long startTime = System.currentTimeMillis();


        Thread t1 = new Thread(new MessageLoop());
        t1.start();

        Thread t2 = new Thread(new MessageLoop());
        t2.start();

        printThreadMessage("Waiting for MessageLoop thread to finish");

        // loop until MessageLoop
        // thread exits
        while (t1.isAlive() && t2.isAlive()) {

            printThreadMessage("Still waiting...");

            // Wait maximum of .5 second for
            // MessageLoop thread to finish.
            // http://www.journaldev.com/1024/java-thread-join-example
            t1.join( 500 );
            t2.join( 500 );

            if (((System.currentTimeMillis() - startTime) > patience) && (t1.isAlive() || t2.isAlive())) {

                printThreadMessage("Tired of waiting!");

                t1.interrupt();
                t2.interrupt();

                // Shouldn't be long now
                // -- wait indefinitely
                t1.join();
                t2.join();


            }
        }
        printThreadMessage("Thread t1 Dead");
        printThreadMessage("Thread t2 Dead");
        printThreadMessage("Main Thread Dead");
    }
}