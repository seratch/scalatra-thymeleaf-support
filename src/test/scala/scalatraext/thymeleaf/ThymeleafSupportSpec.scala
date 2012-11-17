/*
 * Copyright 2012 Kazuhiro Sera
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package scalatraext.thymeleaf

import scala.collection.JavaConverters._

import org.scalatest._
import org.scalatest.matchers._

import org.scalatra.ScalatraServlet
import org.scalatra.test.scalatest.ScalatraFlatSpec

import org.thymeleaf.dialect.IDialect

class MyServlet extends ScalatraServlet with ThymeleafSupport {
  override lazy val thymeleafDialects: Set[_ <: IDialect] = {
    Set(new nz.net.ultraq.web.thymeleaf.LayoutDialect)
  }
  get("/") { render("index", "value" -> "OK!") }
}

class ThymeleafSupportSpec extends ScalatraFlatSpec with ShouldMatchers {

  addServlet(classOf[MyServlet], "/*")

  behavior of "ThymeleafSupport"

  it should "be available" in {
    get("/") {
      body should equal("""<!DOCTYPE html>
        |<html><head>
        |</head><body>
        |<div>OK!</div>
        |
        |
        |</body></html>""".stripMargin)
    }
  }

  it should "have defined attributes" in {
    val servlet = new MyServlet
    servlet.thymeleafCacheable should equal(false)
    servlet.thymeleafautoContentType should equal(true)
    servlet.thymeleafResolverTemplateMode should equal("LEGACYHTML5")
    servlet.thymeleafResolverPrefix should equal("/WEB-INF/layouts/")
    servlet.thymeleafResolverSuffix should equal(".html")
    servlet.thymeleafCharacterEncoding should equal("utf-8")
    servlet.thymeleafResolverCacheTTLMs should equal(3600000L)
    servlet.thymeleafResolver should not be (null)
    servlet.thymeleafTemplateEngine should not be (null)

    servlet.thymeleafTemplateEngine.getDialects.size should equal(2)
    val dialects = servlet.thymeleafTemplateEngine.getDialects.asScala.toSeq
    dialects(0).isInstanceOf[org.thymeleaf.standard.StandardDialect] should be(true)
    dialects(1).isInstanceOf[nz.net.ultraq.web.thymeleaf.LayoutDialect] should be(true)
  }

}

