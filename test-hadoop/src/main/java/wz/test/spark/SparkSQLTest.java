package wz.test.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.function.FilterFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SQLContext;

/**
 * 在spark-shell local的方式下可直接执行（需定义好spark的环境变量）
 */
public class SparkSQLTest {
    public static void main(String[] args) {
        String logFile = "/Users/wangzhuang1/Documents/soft/spark-2.2.0-bin-hadoop2.6/README.md"; // Should be some file on your system


        SparkConf sparkConf = new SparkConf().setAppName("12312as").setMaster("local[2]");
        SparkContext sparkContext = new SparkContext(sparkConf);
        SQLContext sqlContext = new SQLContext(sparkContext);

        Dataset<String> logData = sqlContext.read().textFile(logFile).cache();

        long numAs = logData.filter((FilterFunction<String>) s -> s.contains("a")).count();
        long numBs = logData.filter((FilterFunction<String>) s -> s.contains("b")).count();

        System.out.println("Lines with a: " + numAs + ", lines with b: " + numBs);

    }
}
