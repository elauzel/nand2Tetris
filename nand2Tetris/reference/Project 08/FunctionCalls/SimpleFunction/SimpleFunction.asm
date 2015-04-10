	//function SimpleFunction.test 2
@32000// sync
(SimpleFunction.test)
@0
D=A
@SP
AM=M+1
A=A-1
M=D
@0
D=A
@SP
AM=M+1
A=A-1
M=D
	//push local 0            
@32001// sync
@LCL
D=M
@0
A=D+A
D=M
@SP
AM=M+1
A=A-1
M=D
	//push local 1            
@32002// sync
@LCL
D=M
@1
A=D+A
D=M
@SP
AM=M+1
A=A-1
M=D
	//add                     
@32003// sync
@SP
AM=M-1
D=M
A=A-1
M=M+D
	//not                     
@32004// sync
@SP
A=M-1
M=!M
	//push argument 0         
@32005// sync
@ARG
D=M
@0
A=D+A
D=M
@SP
AM=M+1
A=A-1
M=D
	//add                     
@32006// sync
@SP
AM=M-1
D=M
A=A-1
M=M+D
	//push argument 1         
@32007// sync
@ARG
D=M
@1
A=D+A
D=M
@SP
AM=M+1
A=A-1
M=D
	//sub                     
@32008// sync
@SP
AM=M-1
D=M
A=A-1
M=M-D
	//return                  
@32009// sync
($RETURN)
@LCL
D=M
@R13
M=D
D=M
@5
A=D-A
D=M
@R14
M=D
@SP
AM=M-1
D=M
@ARG
A=M
M=D
D=A
@SP
M=D+1
@R13
D=M
@1
A=D-A
D=M
@THAT
M=D
@R13
D=M
@2
A=D-A
D=M
@THIS
M=D
@R13
D=M
@3
A=D-A
D=M
@ARG
M=D
@R13
D=M
@4
A=D-A
D=M
@LCL
M=D
@R14
A=M
0;JMP
