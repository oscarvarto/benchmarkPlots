package mx.umich.fie.dep.plots.surface

import mx.umich.fie.dep.plots.AbstractDemo
import mx.umich.fie.dep.plots.Launcher
import org.jzy3d.chart.Chart
import org.jzy3d.plot3d.primitives.Shape
import org.jzy3d.plot3d.builder.Builder
import org.jzy3d.plot3d.builder.Mapper
import org.jzy3d.plot3d.builder.concrete.OrthonormalGrid
import org.jzy3d.plot3d.rendering.legends.colorbars.ColorbarLegend
import org.jzy3d.colors.ColorMapper
import org.jzy3d.colors.Color
import org.jzy3d.colors.colormaps.ColorMapRainbow
import org.jzy3d.maths.Range
import org.jzy3d.plot3d.rendering.canvas.Quality

object Griewank {
  def main(args: Array[String]) {
    Launcher.openDemo(new Griewank)
  }
}

class Griewank extends AbstractDemo {
  import math._
  val sqrt2 = sqrt(2.0)

  def init() {
    val mapper: Mapper = (x: Double, y: Double) => {
      val term1 = (x * x + y * y) / 4000.0
      val term2 = cos(x) * cos(y / sqrt2)
      val z = 1 + term1 - term2
      z
    }
    val range = new Range(-10.0, 10.0)
    val steps = 80
    val surface: Shape = Builder.buildOrthonormal(new OrthonormalGrid(range, steps, range, steps), mapper).asInstanceOf[Shape]
    import surface._
    setColorMapper(new ColorMapper(new ColorMapRainbow, getBounds.getZmin, getBounds.getZmax))
    setFaceDisplayed(true)
    setWireframeDisplayed(false)
    //setWireframeColor(Color.BLACK)
    chart = new Chart(Quality.Advanced)
    chart.getScene.getGraph.add(surface)
    setLegend(new ColorbarLegend(surface, chart.getView.getAxe.getLayout))
  }
}