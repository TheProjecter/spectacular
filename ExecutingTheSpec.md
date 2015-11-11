# From the Command Line #

**First, put your test fixture (and any related/required libs) in Spectacular's classpath:**

For Windows:
`> set CLASSPATH_PREFIX=c:\path\to\your\libs.jar`

For Linux/Unix/Mac OS:
`> export CLASSPATH_PREFIX=/path/to/your/libs.jar`

**Then, run Spectacular with a few key command-line options:**

`> Spectacular -fixtures some.sample.package -specLocation file:path/to/spec.html`

(you did remember to follow the install instructions in the QuickStartGuide right?)

Available command line options:



| **Command-Line Option** | **Required?** | **Explanation** |
|:------------------------|:--------------|:----------------|
| -help                   | No            | Prints out a help menu of available command line options |
| -specLocation 

&lt;uri&gt;

| Yes           | Location of the spec to test.  Must be in URI format (for local filesystem, use `file:/path/to/spec`, for specs on the web use `http://host.com/path/to/spec`, etc) |
| -fixtures <package/scripts> | Yes, for EUC.  No for others | If you have any executable use cases, this is the parent Java package that contains all your fixture code; for EUC fixture code in Groovy, prepend with `groovy:` (i.e. `groovy:/path/to/script.groovy`); for Selenese, prepend with `selenese:` (i.e. `selenese:/path/to/script.selenese`); for Ruby, prepend with `ruby:` (i.e. `ruby:/path/to/script.rb`) |
| -config <path to config> | No            | Location of a Spring wiring config file if you want to replace any of the beans for any of the Spectacular steps (see [Spectacular Architecture](Architecture.md) for more information).  You generally won't be using this. |
| -projectXml <path to xml> | No            | Location of a project-level XML file which can specify all of these command-line parameters for you.  See [Using a Project XML](ProjectXML.md) documentation for more details.|
| -reportLocation 

&lt;path&gt;

 | No.           | Path to the location where you would like the report written. |

If you are using [Selenese](WritingExecutableUseCaseFixturesInSelenese.md) test fixtures, the following must be supplied to the command line (or in the [Project XML file](ProjectXML.md)):

| **Command-Line Option** | **Required?** | **Explanation** |
|:------------------------|:--------------|:----------------|
| -seleniumRCHost 

&lt;hostname&gt;

 | Yes, for Selenese tests.  No otherwise. | If you're using Selenese scripts, this is the hostname of the Selenium Remote Control Server. |
| -seleniumRCPort <port number> | Yes, for Selenese tests.  No otherwise. | If you're using Selenese scripts, this is the port number the Selenium Remote Control Server is listening on. |
| -seleniumRCStartupCommand 

&lt;command&gt;

 | Yes, for Selenese tests.  No otherwise. | If you're using Selenese scripts, this is [the startup command to selenium](http://seleniumhq.org/docs/05_selenium_rc.html#specifying-the-path-to-a-specific-browser).  i.e. **firefox or**safari or **iehta**|
| -seleniumRCInitialUrl 

&lt;url&gt;

 | Yes, for Selenese tests.  No otherwise. | If you're using Selenese scripts, this is the base URL of the system under test. |

If you are [extending](ExtendSpectacular.md) Spectacular with your own custom phase handlers (see [Spectacular Architecture](Architecture.md)), you can specify which handler should be used with the following command-line arguments (not commonly used unless you're extending Spectacular or using a different plugin):


| **Command-Line Option** | **Required?** | **Explanation** |
|:------------------------|:--------------|:----------------|
| -documentReader 

&lt;arg&gt;

 | No, unless you're _extending_ Spectacular | Bean name for document reader. |
| -artifactExtractor 

&lt;arg&gt;

 | No, unless you're _extending_ Spectacular | Bean name for artifact extractor |
| -decisionerAgent 

&lt;arg&gt;

 | No, unless you're _extending_ Spectacular | Bean name for Decisioner Agent |
| -preexecutorAgent 

&lt;arg&gt;

 | No, unless you're _extending_ Spectacular | Bean name for Pre-Executor Agent |
| -artifactExecutorAgent 

&lt;arg&gt;

 |  No, unless you're _extending_ Spectacular | Bean name for Artifact Executor Agent |
| -reportBuilder 

&lt;arg&gt;

 |  No, unless you're _extending_ Spectacular | Bean name for Report Builder |
| -reportWriter 

&lt;arg&gt;

 |  No, unless you're _extending_ Spectacular | Bean name for Report Writer |