# Assembler

The assembler translates assembly into (pseudo) binary code (.hack) for the hack platform.

```
    .asm   == assembly ==>   .hack
```


The assembler is the last tool in the toolchain which involves two stage compilation of
jack high level programmming language into hack assembly.
- stage one: jack high level programs (.jack) are compiled into vm code (.vm)
- stage two: vm-translator translates vm code into hack assembly language (.asm).

VM code is a simple stack based programming language

```
    .jack   == compilation ==>   .vm   == translation ==>   .asm 
```


From https://www.nand2tetris.org/


## Install

```
make install
```


## Usage

Assembles the given .asm file into a .hack pseudo binary file

```
make FILE=src/test/resources/6/Pong.asm gen
```