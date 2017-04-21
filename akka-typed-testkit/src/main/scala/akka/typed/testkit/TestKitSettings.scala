/**
 * Copyright (C) 2017 Lightbend Inc. <http://www.lightbend.com>
 */
package akka.typed.testkit

import com.typesafe.config.Config
import scala.concurrent.duration.FiniteDuration
import akka.util.Timeout
import akka.typed.ActorSystem

object TestKitSettings {
  def apply(system: ActorSystem[_]): TestKitSettings =
    apply(system.settings.config)

  def apply(config: Config): TestKitSettings =
    new TestKitSettings(config)
}

class TestKitSettings(val config: Config) {

  import akka.util.Helpers._

  val TestTimeFactor = config.getDouble("akka.test.timefactor").
    requiring(tf ⇒ !tf.isInfinite && tf > 0, "akka.test.timefactor must be positive finite double")
  val SingleExpectDefaultTimeout: FiniteDuration = config.getMillisDuration("akka.test.single-expect-default")
  val DefaultTimeout: Timeout = Timeout(config.getMillisDuration("akka.test.default-timeout"))
}
