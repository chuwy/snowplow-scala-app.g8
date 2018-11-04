/*
 * Copyright (c) 2018 Snowplow Analytics Ltd. All rights reserved.
 *
 * This program is licensed to you under the Apache License Version 2.0,
 * and you may not use this file except in compliance with the Apache License Version 2.0.
 * You may obtain a copy of the Apache License Version 2.0 at http://www.apache.org/licenses/LICENSE-2.0.
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the Apache License Version 2.0 is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the Apache License Version 2.0 for the specific language governing permissions and limitations there under.
 */
import sbt._

object Dependencies {

  object V {
    // Scala
    val decline       = "0.5.0"
    val scalaz7       = "7.0.9"
    val json4sJackson = "3.2.11"
    val analyticsSdk  = "0.3.2"
    // Scala (test only)
    val specs2        = "4.3.5"
    val scalaCheck    = "1.14.0"
  }

  // Scala
  val decline       = "com.monovore"               %% "decline"                      % V.decline
  val json4sJackson = "org.json4s"                 %% "json4s-jackson"               % V.json4sJackson
  val analyticsSdk  = "com.snowplowanalytics"      %% "snowplow-scala-analytics-sdk" % V.analyticsSdk
  // Scala (test only)
  val specs2        = "org.specs2"                 %% "specs2-core"               % V.specs2         % "test"
  val scalaCheck    = "org.scalacheck"             %% "scalacheck"                % V.scalaCheck     % "test"
}
