package Util

import org.apache.spark.sql.SparkSession

trait SparkSessionBase {
  val sparkSession: SparkSession = org.apache.spark.sql.SparkSession.builder
    .master("local[*]")
    .appName("KaggleNYCTaxi")
    .getOrCreate()

  sparkSession.sparkContext.setLogLevel("ERROR")
}
