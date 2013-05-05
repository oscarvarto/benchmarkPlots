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
import org.jzy3d.chart.controllers.keyboard.camera.CameraKeyController
//import org.jzy3d.chart.controllers.keyboard.ChartKeyController

object Schwefel {
  def main(args: Array[String]) {
    Launcher.openDemo(new Schwefel)
  }
}

class Schwefel extends AbstractDemo {
  def init() {
    import math._
    import scalaz._, Scalaz._

    val f = (sin _ ⋘ sqrt ⋘ abs)
    val mapper: Mapper = (x: Double, y: Double) ⇒ x * f(x) + y * f(y) + 837.9658
    val range = new Range(-500.0, 500.0)
    val steps = 80
    val surface: Shape = Builder.buildOrthonormal(new OrthonormalGrid(range, steps, range, steps), mapper).asInstanceOf[Shape]
    import surface._
    setColorMapper(new ColorMapper(new ColorMapRainbow, getBounds.getZmin, getBounds.getZmax))
    setFaceDisplayed(true)
    setWireframeDisplayed(false)
    //setWireframeColor(Color.BLACK)
    chart = new Chart(Quality.Advanced)
    chart.getScene.getGraph.add(surface)
    chart.addController(new CameraKeyController())
    //setLegend(new ColorbarLegend(surface, chart.getView.getAxe.getLayout))
  }
}
