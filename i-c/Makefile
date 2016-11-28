#FLAGS=-m64 --std=c11 -O2 -Wall -Wextra -Werror -Wformat-nonliteral -Wcast-align -Wpointer-arith -Wbad-function-cast -Wmissing-prototypes -Wstrict-prototypes -Wmissing-declarations -Winline -Wundef -Wnested-externs -Wcast-qual -Wshadow -Wwrite-strings -Wno-unused-parameter -Wfloat-equal -pedantic -Wundef -Wcast-align -Wwrite-strings -Wno-maybe-uninitialized

all: prod clean

prod: prod.o
	gcc -o prod prod.c lecture_instances.c cout_sol_1.c cout_2.c

clean:
	rm -f *.o

remove:
	rm -f *.o prod
