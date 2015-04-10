// Multiplies R0 and R1 and stores the result in R2.
// (R0, R1, R2 refer to RAM[0], RAM[1], and RAM[3], respectively.)

// set sum = 0
@R2
M=0

// make two variables for R0 and R1
@R0
D=M 		// D = value of R0
@x
M=D 		// x = value of R0

@R1
D=M 		// D = value of R1
@y
M=D 		// y = value of R1

// subtract them from each other
@x
D=D-M 		// D = value of R1 - value of R0

// if (R1 > R0)
@BIGGER
D;JGE 		// jump to BIGGER
// else set number = R0 & counter = R1
@R0
D=M 		// D = value of R0
@number
M=D 		// number = value of R0
@R1
D=M 		// D = value of R1
@counter
M=D 		// counter = value of R1
@WHILE
0;JMP 		// jump to WHILE

(BIGGER)		// set number = R1 & counter = R0
	@R1
	D=M 		// D = value of R1
	@number
	M=D 		// number = value of R1
	@R0
	D=M 		// D = value of R0
	@counter
	M=D 		// counter = value of R0

(WHILE)		// WHILE label
	// begin loop condition
	@counter
	D=M 		// D = value of counter
	@END
	D;JLE		// if (D<=0) jump to END
	// end loop condition

	// begin body of while
	@number
	D=M 		// D = value of number
	@R2
	M=D+M 		// R2 += D
	@1
	D=A 		// D = 1
	@counter
	M=M-D 		// counter--
	// end body of while

	@WHILE
	0;JMP 		// jump to WHILE
(END)		// END label
	@END
	0;JMP   	// Infinite WHILE

// D - stores data values
// A - doubles as data and address register
// M - refers to the memory location bound in A