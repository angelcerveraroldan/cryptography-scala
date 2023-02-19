import algorithms.HillCypher
import cli.Encrypt
import org.backuity.clist.Cli

object Main {
  def main(args: Array[String]): Unit =
    Cli.parse(args).withCommand(new Encrypt) { config => config.run() }
}
