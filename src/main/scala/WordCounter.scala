import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}


object WordCounter {

  private val input: List[String] = List("A simple sentence is not that simple for a non simple person",
                            "A repetitive string is not a repetitive sentence")

  def main(args: Array[String]): Unit = {

    /** local Spark Configuration. Use this for local/ test work */
    val conf = new SparkConf().setAppName("wordCount").setMaster("local")

    // Create a Scala Spark Context.
    /**  Databricks has already a spark context. Invoke it using .getOrCreate*/
    val sc = SparkContext.getOrCreate(conf)
    val rddSentences: RDD[String] = sc.parallelize(input)
    println(rddSentences)
    // Split up into words.
    val words: RDD[String] = rddSentences.flatMap(line => line.split(" "))
    // Transform into word and count.
    val counts: List[(String,Int)] = words.map(word => (word, 1)).reduceByKey( _ + _).collect().toList
    println(counts)
  }







}
