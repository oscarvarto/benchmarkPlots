package umich.jzy3d.surface

import umich.jzy3d.AbstractDemo
import umich.jzy3d.Launcher
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

object Ackley {
  def main(args: Array[String]) {
    Launcher.openDemo(new Ackley)
  }
}

class Ackley extends AbstractDemo {

  def init() {
    import math._

    val mapper: Mapper = (x: Double, y: Double) ⇒ {
      val exponent1 = -0.2 * sqrt((x * x + y * y) / 2.0)
      val exponent2 = (cos(2 * Pi * x) + cos(2 * Pi * y)) / 2.0
      val z = -20.0 * exp(exponent1) - exp(exponent2) + 20 + E
      z
    }
    val range = new Range(-30.0, 30.0)
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
