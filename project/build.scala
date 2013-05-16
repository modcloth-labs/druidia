import sbt._
import Keys._
import org.scalatra.sbt._
import org.scalatra.sbt.PluginKeys._
import com.mojolly.scalate.ScalatePlugin._
import sbtassembly.Plugin._
import AssemblyKeys._
import ScalateKeys._

object DruidiaBuild extends Build {
  val ScalatraVersion = "2.2.0"

  lazy val project = Project ("druidia", file("."),
    settings = Defaults.defaultSettings ++ ScalatraPlugin.scalatraWithJRebel ++ scalateSettings ++ assemblySettings ++ Seq(
      resolvers += Classpaths.typesafeReleases,
      scalateTemplateConfig in Compile <<= (sourceDirectory in Compile){ base =>
        Seq(
          TemplateConfig(
            base / "webapp" / "WEB-INF" / "templates",
            Seq.empty,  /* default imports should be added here */
            Seq(
              Binding("context", "_root_.org.scalatra.scalate.ScalatraRenderContext", importMembers = true, isImplicit = true)
            ),  /* add extra bindings here */
            Some("templates")
          )
        )
      }
    )
  ) settings(
      mergeStrategy in assembly <<= (mergeStrategy in assembly) { (old) =>
        {
          case "about.html" => MergeStrategy.discard
          case x => old(x)
        }
      }
    )
}
