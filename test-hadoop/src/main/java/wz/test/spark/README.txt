执行spark app：
    1. 打包： mvn package
    2. spark提交任务 ：
         ~/Documents/soft/spark-2.2.0-bin-hadoop2.6/bin/spark-submit \
         --class "wz.test.spark.SparkAppTest" \
         --master local  test-hadoop-1.0-SNAPSHOT.jar
