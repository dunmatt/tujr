package com.github.dunmatt.tujr

import alice.tuprolog.{Prolog, Theory}
import de.jreality.geometry.Primitives
import de.jreality.plugin.JRViewer

object Demo {
  def main (args: Array[String]): Unit = {
    val engine = new Prolog
    engine.setTheory(new Theory("man(socrates).  mortal(X) :- man(X). "))

    val viewer = new JRViewer
    viewer.setContent(Primitives.icosahedron())
    viewer.addBasicUI
    viewer.registerPlugin(new PrologConsole(engine))
    viewer.startup
  }
}
