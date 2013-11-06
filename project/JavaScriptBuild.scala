import play.Project._
import sbt._
import sbt.Keys._
import com.typesafe.sbt.packager.Keys._

/**
 * Build of UI in JavaScript
 */
object JavaScriptBuild {
  val uiDirectory = SettingKey[File]("ui-directory")

  val gruntBuild = TaskKey[Int]("grunt-build")

  val javaScriptUiSettings = Seq(

    // the JavaScript application resides in "ui"
    uiDirectory <<= (baseDirectory in Compile) { _ / "ui" },

    // add "npm" and "grunt" commands in sbt
    commands <++= uiDirectory { base => Seq(Grunt.gruntCommand(base), npmCommand(base))},

    gruntBuild := Grunt.gruntProcess(uiDirectory.value).run().exitValue(),

    // runs grunt before staging the application
    stage <<= stage dependsOn (gruntBuild),

    // Turn off play's internal less compiler
    lessEntryPoints := Nil,

    // Turn off play's internal JavaScript and CoffeeScript compiler
    javascriptEntryPoints := Nil,
    coffeescriptEntryPoints := Nil,

    // integrate JavaScript build into play build
    playRunHooks <+= uiDirectory.map(ui => Grunt(ui))
  )

  def npmCommand(base: File) = Command.args("npm", "<npm-command>") { (state, args) =>
    Process("npm" :: args.toList, base) !;
    state
  }

}