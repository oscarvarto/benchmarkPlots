package umich.jzy3d

import org.jzy3d.plot3d.builder.Mapper

package object surface {
  implicit def functionToMapper(func: (Double, Double) ⇒ Double): Mapper = new Mapper {
    def f(p1: Double, p2: Double): Double = func(p1, p2)
  }
}
