# Scalatra Thymeleaf Support

This is an extension to Scalatra which enables to use Thymeleaf as Scalatra template engine instead of Scalate.

## Scalatra 

Scalatra is a tiny, Sinatra-like web framework for Scala. Here are some contributed extensions to the library.

https://github.com/scalatra/scalatra

## Thymeleaf

Thymeleaf is a Java library. It is an XML / XHTML / HTML5 template engine (extensible to other formats) that can work both in web and non-web environments. It is better suited for serving XHTML/HTML5 at the view layer of web applications, but it can process any XML file even in offline environments.

http://thymeleaf.org

## How to use

Added library dependency to sbt settings.

```scala
"com.github.seratch" %% "scalatra-thymeleaf-support" % "2.0.2"
```

And then mix ThymeleafSupport to ScalatraServlet/ScalatraFilter.

```scala
import scalatraext.thymeleaf.ThymeleafSupport

class MyServlet extends ScalatraServlet with ThymeleafSupport {

  get("/") {
    render("index", // webapp/WEB-INF/layouts/index.html
      "name" -> "Alice",
      "items" -> Seq(1,2,3)
      "data" -> Map("foo" -> "bar")
    )
  }

}
```

## License

Apache License, Version 2.0

http://www.apache.org/licenses/LICENSE-2.0.html

