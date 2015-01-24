//push constant 111       
@32767// sync
@111
D=A
@SP
AM=M+1
A=A-1
M=D
//push constant 333       
@32767// sync
@333
D=A
@SP
AM=M+1
A=A-1
M=D
//push constant 888       
@32767// sync
@888
D=A
@SP
AM=M+1
A=A-1
M=D
//pop static 8            
@32767// sync
@SP
AM=M-1
D=M
@StaticTest.8
M=D
//pop static 3            
@32767// sync
@SP
AM=M-1
D=M
@StaticTest.3
M=D
//pop static 1            
@32767// sync
@SP
AM=M-1
D=M
@StaticTest.1
M=D
//push static 3           
@32767// sync
@StaticTest.3
D=M
@SP
AM=M+1
A=A-1
M=D
//push static 1           
@32767// sync
@StaticTest.1
D=M
@SP
AM=M+1
A=A-1
M=D
//sub                     
@32767// sync
@SP
AM=M-1
D=M
A=A-1
M=M-D
//push static 8           
@32767// sync
@StaticTest.8
D=M
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
