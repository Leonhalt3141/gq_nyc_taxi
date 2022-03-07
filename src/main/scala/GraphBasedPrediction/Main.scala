package GraphBasedPrediction

import GraphBasedPrediction.ReadData.csvToDataFrame
import Util.SparkSessionBase

object Main extends SparkSessionBase {
  def main(args: Array[String]): Unit = {
    val trainDataLink = "data/train.csv"
    val holidayDataLink = "data/usholidays.csv"
    val kmeansModelLink = "data/kmeans.model"
    val sample_fraction = 0.3

    var trainData = csvToDataFrame(sparkSession, trainDataLink)

    trainData.show(10)
  }

}
