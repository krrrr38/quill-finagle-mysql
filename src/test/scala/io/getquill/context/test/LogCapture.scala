package io.getquill.context.test

import java.io.{ByteArrayOutputStream, PrintStream}
import scala.util.control.Exception.ultimately

trait LogCapture {

  def logCapture[A](f: => A): (String, A) = {
    val outputStream = new ByteArrayOutputStream()
    val printStream  = new PrintStream(outputStream)
    val sysOut       = System.out
    val ret = ultimately(System.setOut(sysOut)) {
      System.setOut(printStream)
      Console.withOut(printStream)(f)
    }
    val logs = outputStream.toString
    println(logs)
    (logs, ret)
  }

}
