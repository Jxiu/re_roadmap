package lc101.sort.jmh;

import lc101.sort.BasicSort;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class BasicSortTest {


    public static void main(String[] args) throws RunnerException, IOException {

        String outFileFullName = createOutputFile("/benchmark/sort.log");
        System.out.println(outFileFullName);

        Options options = new OptionsBuilder()
                // include:benchmark所在类的名字，可以使用正则表达
                .include(QuickSortBenchMarkTest.class.getSimpleName())
                // warmupIteration:预热的迭代次数，这里为什么要预热的原因是由于JIT的存在，随着代码的运行，会动态对代码的运行进行优化。因此在测试过程中需要先预热几轮，让代码运行稳定后再实际进行测试
                .warmupIterations(5)
                // measurementIterations:实际测试轮次
                .measurementIterations(5)
//                .output(outFileFullName)
                .build();
        new Runner(options).run();
    }

    private static String createOutputFile(String subPath) throws IOException {
        String projectPath = System.getProperty("user.dir");
        String outFile = projectPath + subPath;
        File file = new File(outFile);
        if (!file.exists()){
            File parentDir = file.getParentFile();
            if (!parentDir.exists()){
                parentDir.mkdirs();
            }
            file.createNewFile();
        }
        return outFile;
    }

    /**
     * 快速排序基准测试类
     */
    @State(Scope.Benchmark)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public static class QuickSortBenchMarkTest{
        private static final int ARRAY_LENGTH = 100000;
        private static final int TEST_TIMES = 1000;
        /**
         * 共享变量需要设置共享的范围
         */
        private AtomicLong runTime = new AtomicLong(0);
//        @Setup
//        public void setUp(){
//
//        }

        // @Benchmark注解：标识在某个具体方法上，表示这个方法将是一个被测试的最小方法，在JMH中成为一个OPS
        @Benchmark
        // 基准测试类型
        //   Throughput:整体吞吐量 例如“1秒内可以执行多少次调用”
        //   AverageTime:调用的平均时间，每次OPS执行的时间 例如“每次调用平均耗时xxx毫秒”
        //   SampleTime:取样，给出不同比例的ops时间，例如99%的ops时间，99.99%的ops时间
        //   SingleShotTime: 以上模式都是默认一次 iteration 是 1s，唯有 SingleShotTime 是只运行一次。往往同时把 warmup 次数设为0，用于测试冷启动时的性能。
        @BenchmarkMode(Mode.AverageTime)
        // fork：fork的次数，如果设置为2，JMH会fork出两个进程来测试
        @Fork(1)
        // 线程数量
        @Threads(1)
        public void dualPivotSort_test(){
            int[] a = BasicSort.generateTestDta(ARRAY_LENGTH);
            BasicSort.QuickSort.traditionalSort(a);
            runTime.addAndGet(1L);
            System.out.println(runTime.get());
        }

        @Benchmark
        @BenchmarkMode(Mode.AverageTime)
        @Fork(1)
        @Threads(1)
        public void JDKQuickSort_test(){
            int[] a = BasicSort.generateTestDta(ARRAY_LENGTH);
            Arrays.sort(a);
        }
    }

}
