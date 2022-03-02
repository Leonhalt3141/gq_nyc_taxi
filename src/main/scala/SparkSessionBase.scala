

trait SparkSessionBase {
  val sparkSession = org.apache.spark.sql.SparkSession.builder
    .master("local[*]")
    .appName("KaggleNYCTaxi")
    .getOrCreate()

  sparkSession.sparkContext.setLogLevel("ERROR")
}
