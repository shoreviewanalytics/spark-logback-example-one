# sparkjobspecificlogging
An example of how to do spark job specific logging with logback.

To run:

dse -u username_goes_here -p password_goes_here spark-submit --class com.java.spark.LoggingSample --master dse://? --driver-java-options "-Dlogback.configurationFile=/path/logback.xml" /path/LoggingSample1.0.jar
