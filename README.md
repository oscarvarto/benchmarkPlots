Benchmark Problem Plots for Computational Intelligence
======================================================

This project uses jzy3d (that depends on Java Binding for the OPENGL API) to make some beautiful 3D interactive plots for classical benchmark functions used to evaluate the performance of optimization algorithms for unconstrained optimization.

I hope this helps the CIlib community http://cilib.net/


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
 [1] mx.umich.fie.dep.plots.surface.Schwefel
 [2] mx.umich.fie.dep.plots.surface.Easom
 [3] mx.umich.fie.dep.plots.surface.Ackley
 [4] mx.umich.fie.dep.plots.surface.Griewank
```

6. You can also issue `sbt ";run-main mx.umich.fie.dep.plots.surface.Schwefel"` to choose the Schwefel plot.
7. If you want a jar to run it with java from the command line, then

   *  `sbt assembly` You'll get `target/jzy3d-assembly-0.1.0-SNAPSHOT.jar`
   * `cd target`
   
     * Rotate     : Left click and drag mouse
     * Scale      : Roll mouse wheel
     * Z Shift    : Right click and drag mouse
     * Animate    : Double left click
     * Screenshot : Press 's' 

You can watch some images in the [wiki](https://github.com/oscarvarto/benchmarkPlots/wiki).

Using Eclipse to add more plots
-------------------------------

If you want to use [Scala IDE](http://scala-ide.org/) you can use Eclipse 3.7 Indigo and a [nightly update site](http://scala-ide.org/download/nightly.html) for Scala 2.10 (trunk).

1. Just issue `sbt eclipse` and you'll get a Eclipse project that you can use to add more plots.
2. File > Import > Existing Projects into Workspace and then choose the directory where you ran `sbt eclipse`.

Unfortunately, on Mac OS X UTF-8 is not the default encoding (AFAIK it is MacRoman) and it would be useful to add `-Dfile.encoding=UTF-8` to your `eclipse.ini` in order to use unicode characters in your code (for example, if you're using `scalaz`).

**You don't have to use Eclipse, though** Use your favorite editor (Emacs is a great choice! Check [scala-tool-support](http://www.scala-lang.org/downloads) for Emacs and gedit. Read the README, please).

Acknowledgements
----------------

* **Martin Pernollet** is a great guy! [jzy3d](http://www.jzy3d.org/index.php) is an open source (BSD) java library that allows a rapid display of 3d scientific data.

* This code is based on [retronym's](https://github.com/retronym/jzy3d-demo)
* I used Heiko Seeberger's [hseeberger/vanilla.g8](https://github.com/hseeberger/vanilla.g8) template with a few changes (I updated library versions and I'm using scala 2.10.0-M6). Heiko has always been really friendly and I'd like to thank his willingness to help others. I must recognize his great job with [sbteclipse plugin](https://github.com/typesafehub/sbteclipse)
* I must thank [Eugene Yokota](https://groups.google.com/forum/?fromgroups#!topic/simple-build-tool/R2fTt_QOXPs) for his help: [sbt-assembly](https://github.com/sbt/sbt-assembly) rocks! 
* I want to thank the whole **Scala community** for helping with my questions!

Questions? (Suggestions or comments?)
-------------------------------------

* oscarvarto at gmail dot com
* ovargas at dep dot fie dot umich dot mx (Universidad Michoacana de San Nicolás de Hidalgo, Posgrado de Ingeniería Eléctrica)
* `#scala` irc channel on `FreeNode` network. 
