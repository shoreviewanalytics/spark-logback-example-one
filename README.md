# sparkjobspecificlogging

An example of how to create a Spark job specific log file using Logback.  This example is setup to be run on a single node DataStax Enterprise (DSE) cluster with Analytics enabled or a multi-node (DSE) cluster with Analytics enabled.  For additional information on this example please see my blog [post](https://shoreviewanalytics.github.io/Spark-Job-Specific-Logging-with-Logback-and-DataStax-Enterprise-Analytics/).


To run:

dse -u username_goes_here -p password_goes_here spark-submit --class com.java.spark.LoggingSample --master dse://? --driver-java-options "-Dlogback.configurationFile=/path/logback.xml" /path/LoggingSample1.0.jar
