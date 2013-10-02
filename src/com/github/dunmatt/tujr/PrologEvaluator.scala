package com.github.dunmatt.tujr

import alice.tuprolog.Prolog
import de.jtem.jterm.{InterpreterResult, StringEvaluator}
// import org.apache.commons.logging.LogFactory

class PrologEvaluator() extends StringEvaluator {
  // val log = LogFactory.getLog(classOf[PrologEvaluator])
  var engine: Option[Prolog] = None

  def setProlog(prolog: Prolog) = engine = Option(prolog)

  def completeCommand(cmd: String): Array[String] = Array.empty[String]

  def evaluate(cmd: String) = engine match {
    case None => new InterpreterResult("Prolog instance not set!", true)
    case Some(prolog) => new InterpreterResult("It worked!!", false)
  }
}
