import sbt._
import java.net._
import java.io.File
import play.PlayRunHook

/*
  Grunt runner
*/
object Grunt {
  def apply(base: File): PlayRunHook = {

    object GruntProcess extends PlayRunHook {

      var process: Option[Process] = None

      override def afterStarted(addr: InetSocketAddress): Unit = {
        // call grunt to generate public assets
        gruntProcess(base).run
        // watch for modifications
        process = Some(gruntProcess(base, "watch").run)
      }

      override def afterStopped(): Unit = {
        // Stop grunt when play run stops
        process.map(p => p.destroy())
        process = None
      }

    }

    GruntProcess
  }

  def gruntCommand(base: File) = Command.args("grunt", "<grunt-command>") { (state, args) =>
    gruntProcess(base, args:_*) !;
    state
  }
  def gruntProcess(base: File, args: String*) = Process("node" :: "node_modules/.bin/grunt" :: args.toList, base)
}