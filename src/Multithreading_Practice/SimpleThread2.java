package Multithreading_Practice;

/**
 * Created by dcmeade on 7/13/2016.
 *
 */

public class SimpleThread2 implements Runnable {

    int index;

    SimpleThread2(int index) {

        this.index = index;
    }

    public void run() {
        String importantInfo[] = {
                "Message 1",
                "Message 2",
                "Message 3",
                "Message 4",
                "Message 5",
                "Message 6",
                "Message 7",
                "Message 8",
                "Message 9",
                "Message 10",
                "Message 11",
                "Message 12",
                "Message 13",
                "Message 14",
                "Message 15",
                "Message 16",
                "Message 17",
                "Message 18",
                "Message 19",
                "Message 20"
        };

        try {
            for (String anImportantInfo : importantInfo) {

                Thread.sleep(0);

                // Print a message
                printThreadMessage(anImportantInfo);

                // End the threads activity when the massage is at a certain index
                if(anImportantInfo.contentEquals("Message " + index + 10)) {
                    return;
                }
            }
        } catch (InterruptedException e) {
            printThreadMessage("I wasn't done!");
        }
    }

    // Display a message, preceded by
    // the name of the current thread
    void printThreadMessage(String message) {

        String threadName = Thread.currentThread().getName();
        System.out.format("Index: %s, %s: %s%n", index, threadName, message);
    }
}