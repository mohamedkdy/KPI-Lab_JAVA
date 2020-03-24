import java.util.concurrent.Callable;

public class MonteCarlo implements Callable<Integer> {

    private final int N;

    public MonteCarlo(int n) {
        this.N = n;
    }

    @Override
    public Integer call() {
        int counter = 0;
        for (int i = 0; i < this.N; ++ i) {
            double x = Math.random();
            double y = Math.random();
            if (x * x + y * y <= 1) {
                counter++;
            }
        }
        return counter;
    }
}
