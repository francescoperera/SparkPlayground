import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by francescoperera on 7/16/17.
  */
object WordCounter extends App {

  val input: List[String] = List("A simple sentence is not that simple for a non simple person",
                            "A repetitive string is not a repetitive sentence")

  val conf = new SparkConf().setAppName("wordCount").setMaster("local")
  // Create a Scala Spark Context.
  val sc = new SparkContext(conf)
  val i = sc.parallelize(input)
  println(i)
  // Split up into words.
  val words = i.flatMap(line => line.split(" "))
  // Transform into word and count.
  val counts = words.map(word => (word, 1)).reduceByKey( _ + _).collect().toList
  println(counts)
  sc.stop

  // Save the word count back out to a text file, causing evaluation.


}
