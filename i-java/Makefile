JC = javac
JFLAGS = -Xlint

SRCDIR = src
BINDIR = bin

SOURCES = $(wildcard src/*.java)
CLASSES = $(basename $(notdir $(SOURCES)))
BYTECODENODIR = $(addsuffix .class,$(CLASSES))
BYTECODE = $(addprefix bin/,$(BYTECODENODIR))

all: $(BYTECODE)

$(BYTECODE): $(BINDIR)/%.class : $(SRCDIR)/%.java
	@echo $<
	cd src; \
	$(JC) $(JFLAGS) -d ../$(BINDIR) $(notdir $<)
