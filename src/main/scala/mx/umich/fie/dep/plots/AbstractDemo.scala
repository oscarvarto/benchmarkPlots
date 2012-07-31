package mx.umich.fie.dep.plots

import org.jzy3d.chart.Chart

abstract class AbstractDemo extends IDemo {
  def getName: String = {
    this.getClass.getSimpleName
  }

  def getPitch: String = {
    ""
  }

  def isInitialized: Boolean = {
    chart != null
  }

  def getChart: Chart = {
    chart
  }

  protected var chart: Chart = null
}