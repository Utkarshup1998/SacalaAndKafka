name := "ScalaAndKafka"

version := "0.1"

scalaVersion := "2.12.15"



libraryDependencies ++= Seq(
  "org.apache.kafka" %% "kafka" % "0.10.2.0",
  "org.apache.kafka" % "kafka-clients" % "0.10.2.0"
)