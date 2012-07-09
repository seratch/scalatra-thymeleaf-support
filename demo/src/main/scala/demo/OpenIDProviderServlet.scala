package demo

import org.scalatra._
import scalatraext.thymeleaf.ThymeleafSupport

class ThymeleafServlet extends ScalatraServlet with ThymeleafSupport {

  get("/") {
    render("index")
  }

}
