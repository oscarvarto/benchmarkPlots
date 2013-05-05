package umich.jzy3d.surface

import umich.jzy3d.Launcher
import org.jzy3d.chart.Chart
import org.jzy3d.plot3d.primitives.Shape
import org.jzy3d.plot3d.builder.Builder
import org.jzy3d.plot3d.builder.Mapper
import org.jzy3d.plot3d.builder.concrete.OrthonormalGrid
import org.jzy3d.plot3d.rendering.legends.colorbars.ColorbarLegend
import org.jzy3d.colors.ColorMapper
import org.jzy3d.plot3d.rendering.canvas.Quality
import org.jzy3d.maths.Range
import org.jzy3d.plot3d.rendering.canvas.Quality
import org.jzy3d.colors.colormaps.ColorMapRainbow
import umich.jzy3d.AbstractDemo

object Easom {
  def main(args: Array[String]) {
    Launcher.openDemo(new Easom)
  }
}

class Easom extends AbstractDemo {
  def init() {
    import math._

    val mapper: Mapper = (x: Double, y: Double) ⇒ {
      val term1 = x - Pi
      val term2 = y - Pi
      val exponent = -term1 * term1 - term2 * term2
      val z = -cos(x) * cos(y) * exp(exponent)
      z
    }
    val range = new Range(0.0, 6.2832)
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
