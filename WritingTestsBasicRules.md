# Detecting Executable Tests #

When Spectacular reads your specification document, it first looks for tables.  If it finds a table, it catalogs it and moves on to the next table in the document.

So, when you're writing your document and you are ready to write portions of it that you want to be _executable_, put it in a table format.  Each of the various types of executable tests are placed in tables of various types and formats.

# What kind of test is it? #

After cataloging each of the tables, Spectacular then runs through them all and tries to determine if the table contains an executable test, and if so, it tries to decide on what type of test the table contains.  In Spectacular-speak, this process is called "decisioning" and many types of "decisioners" vote on what type of test the table is.  Most of the time they don't know and they _Abstain_ and some decisioners know for sure (more details on decisioners can be found in the ExtendSpectacular section); each type of test has its own rules and format for the author to follow - click below on the format for each test Spectacular currently supports.


  * [Writing Executable Use Cases](WritingExecutableUseCases.md)
  * [Writing BDD Tests (Gherkin)](WritingBDDTests.md)
  * [Writing FIT Tests](WritingFITTests.md)