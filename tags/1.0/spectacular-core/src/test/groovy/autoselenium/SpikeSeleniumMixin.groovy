import com.thoughtworks.selenium.DefaultSelenium
import com.thoughtworks.selenium.CommandProcessor


def selenium = null;
def Selenium(String rcHost, int port, String startCmd, String url) {
  selenium = new DefaultSelenium(rcHost, port, startCmd, url);
  selenium.start();
}


def methodMissing(String name, args) {

  this.selenium.commandProcessor.doCommand(name, (String[])args)


}

Selenium("localhost", 4444, "*safari", "http://www.infoq.com/")

open "/"
waitForPageToLoad "5000"
click "link=Login"
waitForElementPresent "//div[@class='popup-wrapper']"
type "identifier=username", "mdowling@yahoo.com"
type "identifier=password", "somepassword"
click 

