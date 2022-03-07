package SimplePrediction

import SimplePrediction.Cluster.{predictCluster, trainKMeans}
import SimplePrediction.Distance.makeHaversineDsirance
import SimplePrediction.Prediction.trainFarePredictionModel
import SimplePrediction.getData.{getHolidayData, getTrainData}
import SimplePrediction.preProcess.{filterOutliers, makeHolidays, processTime}
import org.apache.spark.sql.functions.broadcast
import Util.SparkSessionBase

object Main extends SparkSessionBase {
  def main(args: Array[String]): Unit = {
    //    val conf = new SparkConf().setAppName("Taxi").setMaster("local[*]")
    //    val sc = new SparkContext(conf)
    //    sc.stop()

    val trainDataLink = "data/train.csv"
    val holidayDataLink = "data/usholidays.csv"
    val kmeansModelLink = "data/kmeans.model"
    val sample_fraction = 0.9

    var trainData = getTrainData(sparkSession, trainDataLink)
//    trainData.show(10)

    trainData = processTime(trainData)
    val holidayData = getHolidayData(sparkSession, holidayDataLink)

    trainData = trainData.join(broadcast(holidayData), Seq("Date"), "left_outer")
    trainData = makeHolidays(trainData)

    trainData = makeHaversineDsirance(trainData)
    trainKMeans(trainData, kmeansModelLink)

    trainData = predictCluster(trainData, kmeansModelLink)
    trainData = filterOutliers(trainData)
    //    trainData.show(10)
    trainFarePredictionModel(trainData, sample_fraction)

    println(trainData.count())

    println("Done")
  }
}
