package com.github.dunmatt.tujr

import alice.tuprolog.{MalformedGoalException, Prolog}
import de.jtem.jterm.{InterpreterResult, StringEvaluator}
import scala.collection.JavaConversions._
import scala.collection.parallel.immutable.ParSet

class PrologEvaluator() extends StringEvaluator {
  val theoryParser = """[^'\w]('[$\w_]{4,}'|[a-z][\w_]{3,})""".r
  val primitiveParser = """method\s([\w_]+)_\d""".r
  var engine: Option[Prolog] = None
  var commands = ParSet.empty[String]

  def setProlog(prolog: Prolog) = {
    engine = Option(prolog)
    populateCommandCompletion
  }

  def populateCommandCompletion = engine match {
    case None => Unit
    case Some(prolog) => {
      val libraries = prolog.getCurrentLibraries.toSeq.map(prolog.getLibrary(_))
      commands = commands ++ theoryParser.findAllMatchIn(libraries.map(_.getTheory).mkString).map(_.group(1))
      commands = commands ++ primitiveParser.findAllMatchIn(libraries.map(_.getPrimitives).mkString).map(_.group(1))
    }
  }

  def completeCommand(cmd: String): Array[String] = commands.filter(_.startsWith(cmd)).toArray

  def evaluate(goal: String) = (goal, engine) match {
    case (_, None) => new InterpreterResult("Prolog instance not set!", true)
    case (";", Some(prolog)) => {
      if (prolog.hasOpenAlternatives) {
        new InterpreterResult(prolog.solveNext.toString, false)
      } else {
        new InterpreterResult("no.", false)
      }
    }
    case (_, Some(prolog)) => {
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
