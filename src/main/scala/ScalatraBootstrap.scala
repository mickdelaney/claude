import org.scalatra.LifeCycle
import javax.servlet.ServletContext
import claude.UpdateServlet

class ScalatraBootstrap extends LifeCycle {
  override def init(context: ServletContext) {
    context.mount(new UpdateServlet, "/update")
  }
}
