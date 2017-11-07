/*
 * Copyright (c) 2017 Snowplow Analytics Ltd. All rights reserved.
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

package $organization$.$name;format="lower,word"$

import org.apache.spark.{SparkConf, SparkContext}

import com.snowplowanalytics.snowplow.analytics.scalasdk.json.EventTransformer

object Main {

  /**
   * Raw CLI configuration used to extract options from command line
   * Created solely for private `rawCliConfig` value and can contain
   * incorrect state that should be handled by `transform` function
   */
  private case class CliConfig(input: String, output: String)

  /**
   * Starting raw value, required by `parser`
   */
  private val rawCliConfig = CliConfig("", "")

  /**
   * End application config parsed from CLI. Unlike `CliConfig`
   */
  case class AppConfig(input: String, output: String)


  /**
   * Check that raw config contains valid stat
   */
  def transform(raw: CliConfig): Either[String, AppConfig] = 
    Right(AppConfig(raw.input, raw.output))

  /**
   * Scopt parser providing necessary argument annotations and basic validation
   */
  private val parser = new scopt.OptionParser[CliConfig](generated.ProjectMetadata.name) {
    head(generated.ProjectMetadata.name, generated.ProjectMetadata.version)

    opt[String]('i', "input").required().
      action( (x, c) => c.copy(input = x) ).
      text("Input path")

    opt[String]('o', "output").required().
      action( (x, c) => c.copy(output = x) ).text("Input")

    help("help").text("prints this usage text")
  }


  def main(args: Array[String]): Unit = {
    println("Hello from Spark Example!")

    parser.parse(args, rawCliConfig).map(transform) match {
      case Some(Right(appConfig)) =>

        val config = new SparkConf()
          .setAppName("spark-example")
          .setIfMissing("spark.master", "local[*]")

        val sc = new SparkContext(config)
        // Always assuming correct enriched TSV input
        val output = sc.textFile(appConfig.input).map(EventTransformer.transform).map(x => x.right.toOption.get)
        output.saveAsTextFile(appConfig.output)

      case Some(Left(error)) => 
        // Failed transformation
        println(error)
        sys.exit(1)
      case None =>
        // Invalid arguments
        sys.exit(1)
    }
  }
}
