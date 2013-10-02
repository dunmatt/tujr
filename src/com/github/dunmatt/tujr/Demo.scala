package com.github.dunmatt.tujr

import de.jreality.geometry.Primitives
import de.jreality.plugin.JRViewer

object Demo {
  def main (args: Array[String]): Unit = {
    val viewer = new JRViewer
    viewer.setContent(Primitives.icosahedron())
    viewer.addBasicUI
    viewer.startup
  }
}
