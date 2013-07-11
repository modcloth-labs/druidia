import org.scalatra._

import javax.servlet.ServletContext

import com.modcloth.druidia._

class ScalatraBootstrap extends LifeCycle {
  override def init(context: ServletContext) {
    context.mount(new LoggingServlet, "/*")

    context.setInitParameter(ScalatraBase.ForceHttpsKey, "true")
  }
}
