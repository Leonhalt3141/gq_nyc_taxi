package SimplePrediction

import org.apache.spark.ml.clustering.{KMeans, KMeansModel}
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.sql.DataFrame

import java.io.File
import scala.reflect.io.Directory

object Cluster {

  def makeClusteringDf(df: DataFrame): DataFrame = {
    val colsToIndex = Array("pickup_latitude", "pickup_longitude", "dropoff_latitude", "dropoff_longitude")

    val vectorizedDf = new VectorAssembler()
      .setInputCols(colsToIndex)
      .setOutputCol("features")
      .setHandleInvalid("skip")
      .transform(df)

    vectorizedDf

  }

  def trainKMeans(df: DataFrame, saveModelPath: String): Unit = {
    val directory = new Directory(new File(saveModelPath))
    directory.deleteRecursively()

    val vectorizedDf = makeClusteringDf(df)

    val kmeans = new KMeans()
      .setK(10)
      .setSeed(1L)
      .setFeaturesCol("features")
      .fit(vectorizedDf.sample(true, 0.0005))

    kmeans.save(saveModelPath)
  }

  def predictCluster(df: DataFrame, saveModelPath: String): DataFrame = {

    val vectorizedDf = makeClusteringDf(df)
    val model = KMeansModel.load(saveModelPath)
    val dfWithClusters = model.transform(vectorizedDf)
      .withColumnRenamed("prediction", "clusters")

    dfWithClusters
  }

}
