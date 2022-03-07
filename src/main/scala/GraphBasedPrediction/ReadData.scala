package GraphBasedPrediction

import org.locationtech.jts.geom._
import org.locationtech.geomesa.spark.jts._
import org.apache.spark.sql.functions.{col, to_date, unix_timestamp}
import org.apache.spark.sql.types._
import org.apache.spark.sql.functions._

import org.apache.spark.sql.{DataFrame, SparkSession}

object ReadData {

  def csvToDataFrame(sparkSession: SparkSession, link: String): DataFrame = {
    val customSchema = StructType(Array(
      StructField("key", StringType, true),
      StructField("fare_amount", DoubleType, true),
      StructField("pickup_datetime", StringType, true),
      StructField("pickup_longitude", DoubleType, true),
      StructField("pickup_latitude", DoubleType, true),
      StructField("dropoff_longitude", DoubleType, true),
      StructField("dropoff_latitude", DoubleType, true),
      StructField("passenger_count", IntegerType, true)
    ))

    val dfRaw = sparkSession.read
      .format("csv")
      .option("header", "true")
      .option("mode", "DROPMALFORMED")
      .schema(customSchema)
      .load(link)
      .withColumn("pickup_datetime", unix_timestamp(col("pickup_datetime"), "yyyy-MM-dd HH:mm:ss zzz")
      .cast(TimestampType))

    val df = dfRaw
      .withColumn("pickup_point", st_makePoint(col("pickup_longitude"), col("pickup_latitude")))
      .withColumn("dropoff_point", st_makePoint(col("dropoff_longitude"), col("dropoff_latitude")))
    df
  }


}
