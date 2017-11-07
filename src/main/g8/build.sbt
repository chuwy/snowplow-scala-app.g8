lazy val root = project.in(file("."))
  .settings(
    name := "$name;format="lower,word"$",
    version := "$version$",
    organization := "com.snowplowanalytics",
    scalaVersion := "2.11.11",
    initialCommands := "import com.snowplowanalytics.$name;format="lower,word"$._"
  )
  .settings(BuildSettings.assemblySettings)
  .settings(BuildSettings.buildSettings)
  .settings(BuildSettings.scalifySettings)
  .settings(
    resolvers ++= Seq(
      "Sonatype OSS Snapshots" at "http://oss.sonatype.org/content/repositories/snapshots/"
    ),
    libraryDependencies ++= Seq(
      Dependencies.spark,
      Dependencies.hadoop,

      Dependencies.scopt,
      Dependencies.analyticsSdk,

      Dependencies.specs2,
      Dependencies.scalaCheck
    )
  )
  .settings(BuildSettings.helpersSettings)

