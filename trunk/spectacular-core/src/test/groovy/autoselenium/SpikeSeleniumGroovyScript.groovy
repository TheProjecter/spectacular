import com.thoughtworks.selenium.DefaultSelenium
import com.thoughtworks.selenium.CommandProcessor





class Sel extends DefaultSelenium {

  def Sel(CommandProcessor proc) {
    super(proc)
  }

  def Sel(String host, int port, String startCmd, String url) {
    super(host, port, startCmd, url)
  }

  def executeCommand(String name, String...args) {
    return(this.commandProcessor.doCommand(name, args))
  }


  
}

this.selenium = new Sel("localhost", 4444, "*safari", "http://www.cnet.com/")
this.selenium.start()


def methodMissing(String name, args) {


  this.selenium.executeCommand(name, args)

}



open "/"

