# What is Spectacular? #

Spectacular is an [Acceptance Test Driven Development](http://www.slideshare.net/nashjain/acceptance-test-driven-development-350264) (ATDD) / [Behavior Driven Development](http://dannorth.net/introducing-bdd) (BDD) tool that aggregates several different types of testing frameworks into 1, and adds the capability to write fully Executable Use Cases (EUC).

The idea is that a Business Analyst (BA), Quality Assurance engineer (QA), or Developer can write a spec in any way they feel comfortable with, including diagrams and business rules if necessary.  They can write tests in the form of Executable Use Cases, BDD-style tests ([Gherkin/Given-When-Then](http://wiki.github.com/aslakhellesoy/cucumber/gherkin) scenarios), and/or [FIT](http://fit.c2.com/wiki.cgi?IntroductionToFit)-style tests all within the same document.  The tool will run through the document, "detect" test cases, execute them, and print out the results in the same format as it was input.

# Why build Spectacular? #

I work at a large financial services company, where compliance with [Sarbanes-Oxley](http://www.sarbanes-oxley.com/) (SOX) and [Office of the Comptroller of the Currency](http://www.occ.treas.gov/) (OCC) rules and regulations is of utmost importance, specifically with the audit requirements.

I suspect that there are many, many organizations out there who have to comply with the same set of rules, so I took what I learned about SOX and OCC audit requirements, combined them with what I learned at [Elisabeth Hendrickson's](http://www.testobsessed.com/) AMAZING ATDD course, and created Spectacular to make writing requirements more natural while also making those same documents executable, while not restricting any team to any single tool.

Also, I found through practical application of various ATDD tools that they are all pretty amazing and useful, but it was really annoying use all these various tools to write specs and tests in different ways depending on the tool. Plus, I would suspect that most teams would prefer to write story tests in a single document in a single format rather than having them live all over the place.

# Isn't this whole idea...  un-Agile? #

I don't think so.  Actually, I think something like Spectacular will help any large organization with an established corporate hierarchy and previous experience writing specifications in a use-case format adopt Agile more easily. In my experience, these large organizations can be like "steering the Titanic with a small rudder" when it comes to transitioning to a more Agile process - allowing teams to continue writing requirements in the form of Use Case flows and documenting the tests in the same document will let them experiment with the various ATDD best-practices while remaining compliant with their regulatory bodies.