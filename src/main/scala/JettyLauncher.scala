import org.eclipse.jetty.server.Server
import org.eclipse.jetty.servlet.{DefaultServlet, ServletContextHandler}
import org.eclipse.jetty.webapp.WebAppContext
import org.scalatra.servlet.ScalatraListener
import org.eclipse.jetty.util.ssl.SslContextFactory
import org.eclipse.jetty.server.ssl.SslSocketConnector

object JettyLauncher {
  def main(args: Array[String]) {
    val port = (Option[String](System.getenv("PORT")).getOrElse("8080")).toInt

    val server = new Server(port)
    val factory = new SslContextFactory(System.getProperty("user.dir") + "/temp/keystore")
    factory.setKeyStorePassword("modcloth")
    factory.setTrustStore(System.getProperty("user.dir") + "/temp/keystore")
    factory.setTrustStorePassword("modcloth")

    val connector = new SslSocketConnector(factory)
    connector.setPort(8443)

    server.setConnectors(Array(connector))
    val context = new WebAppContext()
    context setContextPath "/"
    context.setResourceBase("src/main/webapp")
    context.addEventListener(new ScalatraListener)
    context.addServlet(classOf[DefaultServlet], "/")

    server.setHandler(context)

    server.start
    server.join
  }
}
