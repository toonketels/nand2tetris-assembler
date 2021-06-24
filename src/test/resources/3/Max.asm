// This file is part of www.nand2tetris.org
// and the book "The Elements of Computing Systems"
// by Nisan and Schocken, MIT Press.
// File name: projects/06/max/Max.asm

// Computes R2 = max(R0, R1)  (R0,R1,R2 refer to RAM[0],RAM[1],RAM[2])

   @R0
   D=M              // D = first number
   @R1
   D=D-M            // D = first number - second number
   @10
   D;JGT            // if D>0 (first is greater) goto output_first
   @R1
   D=M              // D = second number
   @12
   0;JMP            // goto output_d
   @R0
   D=M              // D = first number
   @R2
   M=D              // M[2] = D (greatest number)
   @14
   0;JMP            // infinite loop