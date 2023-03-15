package tutorial.lab7;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 1)
@Measurement(iterations = 1)
@Fork(3)
public class Test {
    public static void main(String[] args) throws RunnerException {
    Options opts = new OptionsBuilder()
        .include(Test.class.getSimpleName())
        .resultFormat(ResultFormatType.CSV)
        .build();
    new Runner(opts).run();
    }
    @Benchmark
    public void testAccountSync(){
        Account account = new Account();
        ExecutorService service = Executors.newFixedThreadPool(100);

        for(int i = 1; i <= 100; i++) {
            service.execute(new DepositThread(account, 10));
        }
        service.shutdown();
        while(!service.isTerminated()) {}
        System.out.println("Balance: " + account.getBalance());
    }
    @Benchmark
    public void testAccountLock(){
        AccountLock account = new AccountLock();
        ExecutorService service = Executors.newFixedThreadPool(100);

        for(int i = 1; i <= 100; i++) {
            service.execute(new DepositThreadLock(account, 10));
        }
        service.shutdown();
        while(!service.isTerminated()) {}
        System.out.println("Balance: " + account.getBalance());
    }
}
