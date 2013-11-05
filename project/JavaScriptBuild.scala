import play.Project._
import sbt._
import sbt.Keys._

/**
 * Build of UI in JavaScript
 */
object JavaScriptBuild {
  val uiDirectory = SettingKey[File]("ui-directory")

  val javaScriptUiSettings = Seq(

    // the JavaScript application resides in "ui"
    uiDirectory <<= (baseDirectory in Compile) { _ / "ui" },

    // add "npm" and "grunt" commands in sbt
    commands <++= uiDirectory { base => Seq(Grunt.gruntCommand(base), npmCommand(base))},

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