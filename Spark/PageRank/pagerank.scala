val lines = sc.textFile("gs://pyspark-bucket2/input/pagerank.txt")
val links = lines.map{ s =>
         val parts = s.split("\\s+")
         (parts(0), parts(1))
       }.distinct().groupByKey().persist()
var ranks = links.mapValues(v => 1.0)
for (i <- 1 to 2) {
          val contribs = links.join(ranks).values.flatMap{ case (urls, rank) =>
          urls.map(url => (url, rank / urls.size))
          }
          ranks = contribs.reduceByKey(_ + _).mapValues(0.15 + 0.85 * _)
      }

val output = ranks.collect()
output.foreach(tup => println(tup._1 + " has rank: " + tup._2 + "."))
