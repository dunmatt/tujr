package com.github.dunmatt.tujr

import alice.tuprolog.{MalformedGoalException, Prolog}
import de.jtem.jterm.{InterpreterResult, StringEvaluator}
// import org.apache.commons.logging.LogFactory

class PrologEvaluator() extends StringEvaluator {
  // val log = LogFactory.getLog(classOf[PrologEvaluator])
  var engine: Option[Prolog] = None

  def setProlog(prolog: Prolog) = engine = Option(prolog)

  def completeCommand(cmd: String): Array[String] = Array.empty[String]

  def evaluate(goal: String) = engine match {
    case None => new InterpreterResult("Prolog instance not set!", true)
    case Some(prolog) => {
      try {
        val result = prolog.solve(goal)
        if (result.isSuccess) {
          new InterpreterResult(result.toString, false)
        } else {
          new InterpreterResult("no.", false)
        }
      } catch {
        case mge: MalformedGoalException => new InterpreterResult("Syntax error: %s".format(mge), true)
      }
    }
  }
}
