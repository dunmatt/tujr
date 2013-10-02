package com.github.dunmatt.tujr

import de.jreality.plugin.basic.View;
import de.jtem.jrworkspace.plugin.PluginInfo
import de.jtem.jrworkspace.plugin.sidecontainer.template.ShrinkPanelPlugin
import de.jtem.jterm.{JTerm, Session}
import java.awt.{Dimension, GridLayout}
import java.net.URL
import javax.swing.{BorderFactory, JScrollPane}

class PrologConsole() extends ShrinkPanelPlugin {
  def getPerspectivePluginClass: Class[View] = classOf[View]
  override def getPluginInfo = {
    val res = new PluginInfo("tujr", "M@ Dunlap")
    res.documentationURL = new URL("https://github.com/dunmatt/tujr")
    res.email = "mattdunlap+tujr@gmail.com"
    res
  }

  val evaluator = new PrologEvaluator
  val jterm = new JTerm(new Session(evaluator))
  // val d = new Dimension(400, 200)
  val contentPanel = new JScrollPane(jterm)
  contentPanel.setBorder(BorderFactory.createEmptyBorder)
  // contentPanel.setPreferredSize(d)
  // contentPanel.setMinimumSize(d)
  shrinkPanel.setLayout(new GridLayout)
  shrinkPanel.add(contentPanel)
  shrinkPanel.setShrinked(true)
  def getComponent = contentPanel

}
