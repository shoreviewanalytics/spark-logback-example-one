package com.java.spark;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SparkSession;
import java.util.Arrays;

import org.apache.spark.SparkConf;
import org.slf4j.LoggerFactory;
import scala.Tuple2;
import org.slf4j.Logger;
import java.util.regex.Pattern;

public class LoggingSample 
{
	
	private static final Logger logger = LoggerFactory.getLogger(LoggingSample.class);
	
	
	
	private static final Pattern SPACE = Pattern.compile(" ");
	
	public static void main( String[] args )
    {
	   	

	SparkConf conf = new SparkConf().setAppName("LoggerSample");
    JavaSparkContext sc = new JavaSparkContext(conf);
    
    
    	
   	SparkSession spark = SparkSession
		      .builder()
		      .appName("LoggerSample")
		      .getOrCreate();	
   	
   	SysStreamsLogger.bindSystemStreams();
   	
   	    	
   	// sample info logging 
   	
   	logger.info("Sample Info Logging: Starting up the job.");
   	    
    /**
     * place the words.csv file on each node in the spark cluster in the same location.  
     * 
     */
   	JavaRDD<String> lines = spark.read().textFile("file:///home/one/data/words.csv").javaRDD();
	
	JavaRDD<String> words = lines.flatMap(s -> Arrays.asList(SPACE.split(s)).iterator());
	
	logger.info("Sample Info Logging of transformation of words after lines.flatMap.");
	
	words.take(5).forEach(System.out::println);
	
	JavaPairRDD<String, Integer> ones = words.mapToPair(s -> new Tuple2<>(s,1));
	
	logger.info("Sample Info Logging of transformation of ones RDD after being set to words.mapToPair.");
	
	ones.take(5).forEach(System.out::println);
	
	JavaPairRDD<String, Integer> counts = ones.reduceByKey((i1, i2) -> i1 + i2);
	
	
	logger.info("Sample Info Logging of the RDD - counts.toDebugString() " + counts.toDebugString());
	
	
				
	// sample info logging of transformation using SysStreamsLogger 
	logger.info("Sample Info Logging: Print out the first five key / value pairs of counts RDD");
	
	counts.take(5).forEach(System.out::println);	
	
	// sample warn logging
	
	logger.warn("Sample Warning Logging: Exiting the job.");
	
	// sample error logging
	
	logger.error("Sample Error Logging: There's an error to address!");
        
   	    
    sc.close();
    
    }
	

}
