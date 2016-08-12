package Multithreading_Practice;

/**
 * Created by dcmeade on 7/18/2016.
 *
 */
public class TestSimpleThreads2 {

    public static void main(String args[]) throws InterruptedException {

        System.out.println("Starting MessageLoop thread");

        int threadCount = 10;

        Thread[] threadArray = new Thread[threadCount];

        for (int i = 0; i < threadCount; i++) {
            SimpleThread2 msgLoop1 = new SimpleThread2(i);

            threadArray[i] = new Thread(msgLoop1);
            threadArray[i].start();
        }

        boolean resultFound = false;

        while(!resultFound) {

            for (Thread thread : threadArray) {

                if ( !thread.isAlive() ) {
                    stopAllThreads(threadArray);



                    resultFound = true;
                    break;
                }
            }
        }
    }

    private static void stopAllThreads(Thread[] threadArray) {

        // Loop through the threads and end them
        for (Thread thread : threadArray) {
            thread.interrupt();
        }
    }
}
