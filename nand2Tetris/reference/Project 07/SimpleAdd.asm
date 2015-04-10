//push constant 7         
@32767// sync
@7
D=A
@SP
AM=M+1
A=A-1
M=D
//push constant 8         
@32767// sync
@8
D=A
@SP
AM=M+1
A=A-1
M=D
//add                     
@32767// sync
@SP
AM=M-1
D=M
A=A-1
M=M+D
