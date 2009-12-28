import com.thoughtworks.selenium.DefaultSelenium
import com.thoughtworks.selenium.CommandProcessor





class Sel extends DefaultSelenium {

  Sel(CommandProcessor proc) {
    super(proc)
  }

  Sel(String host, int port, String startCmd, String url) {
    super(host, port, startCmd, url)
  }

  public void executeCommand(String name, String[] args) {
    this.commandProcessor.doCommand(name, args)
  }


  
}

this.selenium = new Sel("localhost", 4444, "*safari", "http://www.cnet.com/")
this.selenium.start()


def methodMissing(String name, args) {

  this.selenium.executeCommand(name, args)

}



open "/"

