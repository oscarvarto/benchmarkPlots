Benchmark Problem Plots for Computational Intelligence
======================================================

This project uses jzy3d (that depends on Java Binding for the OPENGL API) to make some beautiful 3D interactive plots for classical benchmark functions used to evaluate the performance of optimization algorithms for unconstrained optimization.

Requirements
------------

Check that your hardware meets the minimum requirements (http://jogamp.org/jogl/www/). Hopefully this won't be an issue for you.

* You need sbt 0.12.0 or higher.

Building and watching some beautiful & interactive plots
--------------------------------------------------------

`sbt` (simple build tool) can download everything for you. So go on and try!

1. If you don't have sbt, you can use Paul Philips's [sbt-extras](https://github.com/paulp/sbt-extras) Once you have cloned the repo, just add an environment variable to point to the sbt script
2. Clone this repo with `git clone https://github.com/oscarvarto/benchmarkPlots.git`
3. `cd benchmarkPlots`
4. `sbt run`. Choose one of the plots and follow the instructions on the command line.

```
Multiple main classes detected, select one to run:

 [1] umich.jzy3d.surface.Easom
 [2] umich.jzy3d.surface.Schwefel
 [3] umich.jzy3d.surface.Ackley
 [4] umich.jzy3d.surface.Griewank
```

6. You can also issue `sbt ";run-main umich.jzy3d.surface.Schwefel"` to choose the Schwefel plot.
     
		 * Rotate     : Left click and drag mouse
     * Scale      : Roll mouse wheel
     * Z Shift    : Right click and drag mouse
     * Animate    : Double left click
     * Screenshot : Press 's' 

You can watch some images in the [wiki](https://github.com/oscarvarto/benchmarkPlots/wiki).

Acknowledgements
----------------

* **Martin Pernollet** is a great guy! [jzy3d](http://www.jzy3d.org/index.php) is an open source (BSD) java library that allows a rapid display of 3d scientific data.

* This code is based on [retronym's](https://github.com/retronym/jzy3d-demo)
* I used Heiko Seeberger's [hseeberger/vanilla.g8](https://github.com/hseeberger/vanilla.g8) template with a few changes.

Questions? (Suggestions or comments?)
-------------------------------------

* oscarvarto at gmail dot com
