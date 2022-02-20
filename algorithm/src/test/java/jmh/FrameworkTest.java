package jmh;

import com.sun.javafx.binding.StringFormatter;
import org.junit.jupiter.api.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.math.BigInteger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * jmh 框架学习测试类
 */
public class FrameworkTest {

    @Test
    public void test(){

    }


    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    @Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
    @Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
    @Fork(1)
    @State(Scope.Benchmark)
    public static class Params{
        /**
         * In many cases, the experiments require walking the configuration space
         * for a benchmark. This is needed for additional control, or investigating
         * how the workload performance changes with different settings.
         */

        @Param({"1", "31", "65", "101", "103"})
        public int arg;

        @Param({"0", "1", "2", "4", "8", "16", "32"})
        public int certainty;

        @Benchmark
        public boolean bench() {
            System.out.printf("arg: %s, certainty: %s \n",arg,certainty);
            return BigInteger.valueOf(arg).isProbablePrime(certainty);
        }

        /*
         * ============================== HOW TO RUN THIS TEST: ====================================
         *
         * Note the performance is different with different parameters.
         *
         * You can run this test:
         *
         * a) Via the command line:
         *    $ mvn clean install
         *    $ java -jar target/benchmarks.jar JMHSample_27
         *
         *    You can juggle parameters through the command line, e.g. with "-p arg=41,42"
         *
         * b) Via the Java API:
         *    (see the JMH homepage for possible caveats when running from IDE:
         *      http://openjdk.java.net/projects/code-tools/jmh/)
         */

        @Test
        public void test() throws RunnerException {
            Options opt = new OptionsBuilder()
                    .include(Params.class.getSimpleName())
//                .param("arg", "41", "42") // Use this to selectively constrain/override parameters
                    .build();

            new Runner(opt).run();
        }
    }


    /**
     * @BenchmarkMode(Mode.SingleShotTime) 测量单次操作的时间：此模式下
     *
     */
    @BenchmarkMode(Mode.SingleShotTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    @Warmup(iterations = 1, batchSize = 5)
    @Measurement(iterations = 3, batchSize = 2, time = 1, timeUnit = TimeUnit.SECONDS)
    @Fork(1)
    @State(Scope.Benchmark)
    public static class Params1{
        /**
         * In many cases, the experiments require walking the configuration space
         * for a benchmark. This is needed for additional control, or investigating
         * how the workload performance changes with different settings.
         */

        @Param({"dev", "prod"})
        private String arg;
        private AtomicLong doCount = new AtomicLong(0);
        private AtomicLong doCount2 = new AtomicLong(0);


        @Benchmark
        public void singleArgsPrintDoCount(){
            doCount.addAndGet(1);
            System.out.printf("arg: %s, doCount: %s \n", arg, doCount.get());
        }

        @Benchmark
        public void singleArgsWithSleep(){
            doCount2.addAndGet(1);
            System.out.printf("arg: %s, doCount: %s \n", arg, doCount2.get());
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Test
        public void test() throws RunnerException {
            Options opt = new OptionsBuilder()
                    .include(this.getClass().getSimpleName())
                    .build();
            new Runner(opt).run();
        }
    }
}
