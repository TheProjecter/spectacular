# Introduction #

Spectacular is _highly customizable_ and has a lot of options you can pass to it in order to write good tests and the fixtures to execute them.  Unfortunately, all of those command-line parameters can become a little unwieldy.  So, if you choose to do so, you can specify command-line parameters for a project that are used every time (and check into a source control system! :) ) in a "Project XML file."

# Example #

Here is an example of a fairly common command-line set for tests that include Executable Use Cases written in Groovy, Java, and Selenese:

`> Spectacular -specLocation file:./PrimaryFlow.html -fixtures selenese:./cnet_navigate.selenese -reportLocation ./report.html -seleniumRCHost localhost -seleniumRCPort 4444 -seleniumRCStartupCommand *safari -seleniumRCInitialUrl http://www.cnet.com/`

Yikes!  That's a lot to grok.  And, it's a challenge to make sure other members of your development team (or product team!) remember all this.  So instead, you can specify all these parameters into Project XML files.  The format is simple:

```

<!-- my_tests_project.xml -->
<spectacular>
	
	<for-spec spec="file:./PrimaryFlow.html">
		<option name="fixtures" value="selenese:./cnet_navigate.selenese" />
		<option name="reportLocation" value="./report.html" />
		<option name="seleniumRCHost" value="localhost" />
		<option name="seleniumRCPort" value="4444" />
		<option name="seleniumRCStartupCommand" value="*safari" />
		<option name="seleniumRCInitialUrl" value="http://www.cnet.com/" />
	</for-spec>

        <for-spec spec="http://intranet.com/path/to/Spec.html">
                <!-- 
                For every for-spec element, Spectacular is run once. 
                You can have as many for-spec elements as you like, as 
                each Spec may have different requirements 
                -->
		<option name="fixtures" value="some.java.package" />
		<option name="reportLocation" value="./OtherSpec.html" />
        </for-spec>
	
</spectacular>

```

Executing Spectacular will then look like this:

`> Spectacular -projectXml ./my_tests_project.xml`

**Much more concise!**