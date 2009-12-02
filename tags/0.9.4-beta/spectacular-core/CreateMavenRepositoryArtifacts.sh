#!/bin/sh

export MAVEN_OPTS=-Xmx1024m
export JAVA_HOME=/System/Library/Frameworks/JavaVM.framework/Versions/1.6.0/Home
mvn clean source:jar javadoc:jar repository:bundle-create


