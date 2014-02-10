import org.eclipse.jetty.server.Server
import org.eclipse.jetty.webapp.WebAppContext
import org.eclipse.jetty.servlet.DefaultServlet
import org.scalatra.servlet.ScalatraListener

package claude {
  object WebApp extends App {
    val webApp = new WebApp
    sys.ShutdownHookThread {
      webApp.stop
    }
    webApp.start
  }

  class WebApp(val port : Int = 8080) {

    val server = new Server(port)
    val context = new WebAppContext

    context.setContextPath("/")
    context.setResourceBase("src/main/webapp")
    context.addEventListener(new ScalatraListener)
    context.addServlet(classOf[DefaultServlet], "/")
    server.setHandler(context)

    def start = server.start

    def stop = server.stop
  }
}
