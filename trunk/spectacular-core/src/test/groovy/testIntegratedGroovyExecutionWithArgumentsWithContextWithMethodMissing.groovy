
Flow("User does something basic") {

  Selenium("localhost", 444, "*safari", "http://www.infoq.com/")
  open "/"
  click "//div/h1/a", "whatButton"


}