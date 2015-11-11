# Step 1:  Download Spectacular #

## Manual Download ##

You can download Spectacular from the [Google Code Downloads](http://code.google.com/p/spectacular/downloads/list) tab.  Download the distribution appropriate for your platform.

## Maven 2 Download ##

If you're using Maven2, add the Minderupt repository to your `pom.xml`:

```

<repository>
  <id>com.minderupt.spectacular.release</id>
  <name>Minderupt Spectacular Repository</name>
  <url>http://minderupt.com/maven2</url>
</repository>

```

Then add the following dependency:

```

<dependency>
  <groupId>com.minderupt</groupId>
  <artifactId>spectacular-core</artifactId>
  <version>1.0</version>     
</dependency>

```


# Step 2:  Install Spectacular #

In the future, I may decide to write some kind of installer specific to each platform.  But until then, simply download the distribution and unpack/unzip to some directory in your filesystem.  Suggested locations include:

  1. For Windows OS:  C:\Program Files\Spectacular-XXX
  1. For Mac OSX:  /Users/YourUserName/Applications/Spectacular-XXX
  1. For Linux/Unix: /opt/Spectacular-XXX

Replace "XXX" with the most recent version.

# Step 3:  Setup Paths #

Optional (but recommended), set your path to include Spectacular's BIN directory so that you can directly execute Spectacular from any location on your system.  You can do this in various ways depending on your OS:

  1. For Windows OS: Go to Start -> Control Panel -> System -> Advanced
    * I would suggest creating a %SPECTACULAR\_HOME% variable and point to wherever you installed Spectacular in step 2.  Then add to the PATH variable %SPECTACULAR\_HOME%\bin
  1. For Mac OSX, Linux, Unix:  Edit your user's .bash\_profile and add the following:
    * export SPECTACULAR\_HOME=/Path/To/Spectacular/Install/Directory
    * export PATH=$PATH:$SPECTACULAR\_HOME/bin

# Step 4:  Write a Specification #

You can write a specification in any HTML WYSIWYG editor, or even in MS Word.  If you choose to write it in MS Word, be sure to save the document as HTML for the time being (still working on a Word 2007 parser - Microsoft is a pain in the ass).

For more details on writing your spec, see the section on [Writing Test Specs](WritingTestSpecs.md).

# Step 5:  Write Test Fixtures #

Next, you'll need to write some test fixtures to connect your test spec to the System Under Test.  Personally, I prefer to write true User Acceptance Tests that drive a browser to execute the GUI by using tools such as [Selenium](http://seleniumhq.org/).  YMMV.

# Step 6:  Write Your Implementation Code #

Unless you've already written your implementation code - and if you did, you are naughty naughty NAUGHTY for not writing your tests first! :) - go ahead and do so now, using your new specifications as a guide for what is considered "Done"

# Step 7:  Run Spectacular #

Run Spectacular against your new code!

`> Spectacular -fixtures some.base.package -specLocation file:/path/to/spec`

You can pass multiple base packages (for Java-written fixtures) by delineating them with a comma (","):

`> Spectacular -fixtures some.base.pkg,some.other.base.pkg -specLocation file:/path/to/spec`

You can also pass Ruby/Groovy/Selenese scripts along with your Java packages (or just the scripts alone):

`> Spectacular -fixtures some.base.pkg,ruby:/path/to/declaration/script.rb,groovy:/path/to/script.groovy,selenese:/path/to/selenium.selenese -specLocation file:/path/to/spec`

`> Spectacular -fixtures selenese:/path/to/declaration/script.selenese -specLocation file:/path/to/spec`


**Other command-line arguments:**

`> Spectacular -help`

<pre>

-help                             Help Menu<br>
<br>
-fixtures <arg>                   Base Package for Executable Use Case<br>
fixtures.<br>
-projectXml <arg>                 Location of XML file for project<br>
command line arguments.<br>
-specLocation <arg>               Location of the specification to test.<br>
<br>
-config <arg>                     Beans file that configures Spectacular<br>
and wires the spine together<br>
<br>
-artifactExecutorAgent <arg>      Bean name for Artifact Executor Agent<br>
-artifactExtractor <arg>          Bean name for artifact extractor<br>
-decisionerAgent <arg>            Bean name for Decisioner Agent<br>
-documentReader <arg>             Bean name for document reader.<br>
-preexecutorAgent <arg>           Bean name for Pre-Executor Agent<br>
-reportBuilder <arg>              Bean name for Report Builder<br>
-reportLocation <arg>             Location of the report to write.<br>
-reportWriter <arg>               Bean name for Report Writer<br>
<br>
-seleniumRCHost <arg>             Hostname of Remote Control server<br>
-seleniumRCInitialUrl <arg>       Initial URL for Selenium<br>
-seleniumRCPort <arg>             Port of Remote Control server<br>
-seleniumRCStartupCommand <arg>   Startup command for Selenium (typically<br>
the browser)<br>
<br>
</pre>


# Step 8:  View Results #

(insert viewing results here)