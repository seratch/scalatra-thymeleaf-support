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
import org.scalatra.servlet.ServletBase

import org.thymeleaf.context.WebContext
import org.thymeleaf.templateresolver._
import org.thymeleaf.TemplateEngine

/**
 * Thymeleaf support
 */
trait ThymeleafSupport {

  this: ServletBase =>

  /*
  // isDevelopmentMode : `org.scalatra.Environment` is looked up as a system property
  lazy val thymeleafCacheable: Boolean = !isDevelopmentMode
  */
  // TODO above doesn't work with 2.1.0-SNAPSHOT (2012/07/09)
  lazy val thymeleafCacheable: Boolean = false

  lazy val thymeleafautoContentType: Boolean = true

  lazy val thymeleafResolverTemplateMode: String = "LEGACYHTML5"

  lazy val thymeleafResolverPrefix: String = "/WEB-INF/layouts/"

  lazy val thymeleafResolverSuffix: String = ".html"

  lazy val thymeleafCharacterEncoding: String = "utf-8"

  lazy val thymeleafResolverCacheTTLMs: Long = 3600000L

  lazy val thymeleafResolver: TemplateResolver = {
    val resolver = new ServletContextTemplateResolver
    resolver.setCacheable(thymeleafCacheable)
    resolver.setTemplateMode(thymeleafResolverTemplateMode)
    resolver.setPrefix(thymeleafResolverPrefix)
    resolver.setSuffix(thymeleafResolverSuffix)
    resolver.setCharacterEncoding(thymeleafCharacterEncoding)
    resolver.setCacheTTLMs(thymeleafResolverCacheTTLMs)
    resolver
  }

  lazy val thymeleafTemplateEngine: TemplateEngine = {
    val engine = new TemplateEngine
    engine.setTemplateResolver(thymeleafResolver)
    engine
  }

  def render(templateName: String, attributes: (String, Any)*): String = {
    if (thymeleafautoContentType) {
      contentType = "text/html; charset=" + thymeleafCharacterEncoding
    }
    val context = new WebContext(request, applicationContext)
    attributes.foreach {
      case (key, value: Map[_, _]) => context.setVariable(key, value.asJava)
      case (key, value: Iterable[_]) => context.setVariable(key, value.asJava)
      case (key, value) => context.setVariable(key, value)
    }
    thymeleafTemplateEngine.process(templateName, context)
  }

}

