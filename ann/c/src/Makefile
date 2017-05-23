# Set compiler and include directories
IDIR=../include
CC=gcc
CFLAGS=-I$(IDIR)

# Set object, library and executable directories
EDIR=../bin
ODIR=../bin/obj
LDIR=../lib
# Set library compile option TODO: Check what this actually does
LIBS=-lm

# Create the paths to the dependencies
_DEPS=mnistloader.h
DEPS=$(patsubst %,$(IDIR)/%,$(_DEPS))

# Create the paths to the objects
_OBJ=mnistloader.o
OBJ=$(patsubst %,$(ODIR)/%,$(_OBJ))

# This rule says that the generation of o-files depends on the
# c-file with the same name as well as all filer listed in DEPS.
# It then says that in order to generate the o-file, make needs to compile
# the c-file. The -c flag says to generate an object file, the -o $@ says to
# put the output of the compilation in the file on the left hand side of the
# :, the $< is the first name in the dependencies list.
$(ODIR)/%.o: %.c $(DEPS)
	$(CC) -c -o $@ $< $(CFLAGS)

# Define rule annmake.
# Putting file names after the ':' tells make that this rule 
# needs to be executed if any of those files change.
# By putting the object files as dependencies, make knows it must first 
# compile the c-files individually and then build the executable. 
$(EDIR)/ann: $(OBJ)
	$(CC) -o $@ $^ $(CFLAGS) $(LIBS)

# Define clean option
.PHONY: clean
clean:
	rm -f $(ODIR)/*.o *~ core $(INCDIR)/*~