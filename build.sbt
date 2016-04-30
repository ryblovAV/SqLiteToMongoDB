name := "SqLiteToMongoDB"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "org.xerial" % "sqlite-jdbc" % "3.8.11.2",
  "org.mongodb" %% "casbah" % "3.1.1",
  "org.slf4j" % "slf4j-nop" % "1.7.12"
)