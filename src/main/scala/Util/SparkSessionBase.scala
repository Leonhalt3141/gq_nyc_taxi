package Util
import org.locationtech.geomesa.spark.jts._

import org.apache.spark.sql.SparkSession

trait SparkSessionBase {
  val sparkSession: SparkSession = org.apache.spark.sql.SparkSession.builder
    .master("local[*]")
    .appName("KaggleNYCTaxi")
    .getOrCreate()
  sparkSession.withJTS
  sparkSession.sparkContext.setLogLevel("ERROR")
}
