import cli.HillCypherCommand
import org.backuity.clist.Cli

object Main {
  def main(args: Array[String]): Unit =
    Cli
      .parse(args)
      .withHelpCommand("help")
      .withProgramName("Basic encryption of files / strings")
      .withDescription("""
          |Implementation of basic encryption algorithms along with notes on how they work.
          |
          |To see the notes: https://github.com/angelcerveraroldan/cryptography-scala
          |""".stripMargin)
      .withCommands(new HillCypherCommand()) match {
      case Some(hillCypher: HillCypherCommand) => hillCypher.run()
      case None                                =>
    }
}
