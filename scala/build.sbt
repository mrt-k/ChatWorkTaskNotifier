name := "ChatWrokTaskbot"

version := "1.0"

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  "org.scalaj" %% "scalaj-http" % "2.2.0",
  "org.json4s" % "json4s-native_2.11" % "3.3.0",
  "org.joda" % "joda-convert" % "1.8.1",
  "joda-time" % "joda-time" % "2.9.1",
  //"org.json4s" % "json4s-jackson_2.10" % "3.3.0",
  "org.scalatest" % "scalatest_2.11" % "2.2.4" % "test"
)
