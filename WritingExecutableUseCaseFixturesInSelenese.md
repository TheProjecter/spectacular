# Writing Executable Use Case Fixtures in Selenese #

_(this creates a Selenium browser automatically and drives the browser through script)_

Spectacular supports writing Executable Use Case fixtures in [Selenese](http://seleniumhq.org/docs/04_selenese_commands.html) within plain text documents (wikis to be supported soon).  The format is simple and is similar to the Groovy and Ruby way of doing things:

```

Flow: User navigates to home page

  open /Home
  waitForPageToLoad 5000

Expectation: User sees home page with articles

  verifyText "xpath=//div[@class='featured today']/h2" "Articles for Today"

Flow: User clicks "login" link

  click "id=login"

# ETC



```


**Couple things to take note:**

  * Selenese scripts follow the pattern of "Flow:" or "Expectation:" followed by the use case text to match.  Subsequent lines are selenium commands in selenese, one per line
  * Selenese commands are fairly straight forward; they consist of some kind of command name (the first word) followed by up to 2 arguments.  If you want to enclose several words in one command argument, surround the arguments in double-quotes (`"`)
  * Right now, Spectacular does not support using regex in the use case text to pass to the selenese script;  this will be added in the very next version.  Promise!  :)
  * More documentation on the available Selenese commands is at the [Selenium HQ](http://seleniumhq.org/docs/04_selenese_commands.html) site.