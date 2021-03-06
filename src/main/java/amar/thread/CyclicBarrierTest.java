package amar.thread;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by amarendra on 06/09/17.
 */
public class CyclicBarrierTest {

    private static int MAX_THREAD = 5;

    public static void main(final String[] args) {

        final CyclicBarrier cyclicBarrier = new CyclicBarrier(MAX_THREAD, new Runnable() {

            private int i;

            @Override
            public void run() {
                System.out.println("Cyclic Barrier : " + i++);
            }
        });

        System.out.println("Spawning Threads");
        for (int i = 0; i < MAX_THREAD; i++) {
            final Thread t = new Thread(new WorkerThread(cyclicBarrier, String.format("Thread-%d", i)));
            t.start();
        }
        System.out.println("Spawning Finished");

    }

    private static class WorkerThread implements Runnable {
        private CyclicBarrier cyclicBarrier;

        private String name;

        public WorkerThread(final CyclicBarrier cyclicBarrier, final String name) {
            this.name = name;
            this.cyclicBarrier = cyclicBarrier;
        }

        public void run() {
            try {
                final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                System.out.printf("%s : Doing Step 1 Work on %s\n", getFormattedDate(sdf), name);
                Thread.sleep(getRandomWaitTime());
                System.out.printf("%s : Doing Step 1 more work on %s\n", getFormattedDate(sdf), name);
                Thread.sleep(getRandomWaitTime());
                System.out.printf("%s : Finished Step 1 work on %s\n", getFormattedDate(sdf), name);
                int count = cyclicBarrier.await(); // Await returns an int which is the arrival index 1 means first 0 means last
                System.out.printf("%s : Cyclic Barrier count on %s is %d\n", getFormattedDate(sdf), name, count);
                if (count == 0) {
                    cyclicBarrier.reset();
                }
                System.out.printf("%s : Doing Step 2 Batch of Work on %s\n", getFormattedDate(sdf), name);
                Thread.sleep(getRandomWaitTime());
                System.out.printf("%s : Doing Some more Step 2 Batch of work on %s\n", getFormattedDate(sdf), name);
                Thread.sleep(getRandomWaitTime());
                System.out.printf("%s : Finished Step 2 Batch of work on %s\n", getFormattedDate(sdf), name);
                count = cyclicBarrier.await();
                System.out.printf("%s : Cyclic Barrier count end of Step 2 Batch of work on %s is %d\n", getFormattedDate(sdf), name, count);
            } catch (final Exception e) {
                e.printStackTrace();
            }
        }

        private String getFormattedDate(final SimpleDateFormat sdf) {
            return sdf.format(new Date());
        }

        private int getRandomWaitTime() {
            return (int) ((Math.random() + 1) * 1000);
        }

    }
}
