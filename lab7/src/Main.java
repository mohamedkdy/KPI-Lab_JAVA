import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {
    public static void main (String[] args) {

        int threads = 8;
        int iterations = 100000;
        int threadIterations = iterations/threads;
        int counter = 0;

        long startTime = System.nanoTime();
        ExecutorService executor = Executors.newFixedThreadPool(threads);
        List<Future<Integer>> list = new ArrayList<>();
        Callable<Integer> callable = new MonteCarlo(threadIterations);
        for (int i = 0; i < threads; i++) {
            Future<Integer> future = executor.submit(callable);
            list.add(future);
        }
        for (Future<Integer> fut : list) {
            try {
                counter += fut.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        executor.shutdown();
        double pi = (4.0 * counter) / iterations;
        long endTime = System.nanoTime();
        long time = (endTime - startTime);
        System.out.println("PI is " + pi);
        System.out.println("THREADS " + threads);
        System.out.println("ITERATIONS " + iterations);
        System.out.println("TIME " + (time/1000000) + " ms");
    }

 }
