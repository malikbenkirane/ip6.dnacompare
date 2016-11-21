JC = javac
BIN = ../bin
JFLAGS = -Xlint -d $(BIN)

.SUFFIXES: .java .class

.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = $(wildcard *.java)

default: classes

classes: $(CLASSES:.java=.class)
