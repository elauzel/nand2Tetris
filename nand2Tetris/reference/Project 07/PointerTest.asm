//push constant 3030      
@32767// sync
@3030
D=A
@SP
AM=M+1
A=A-1
M=D
//pop pointer 0           
@32767// sync
@SP
AM=M-1
D=M
@THIS
M=D
//push constant 3040      
@32767// sync
@3040
D=A
@SP
AM=M+1
A=A-1
M=D
//pop pointer 1           
@32767// sync
@SP
AM=M-1
D=M
@THAT
M=D
//push constant 32        
@32767// sync
@32
D=A
@SP
AM=M+1
A=A-1
M=D
//pop this 2              
@32767// sync
@SP
AM=M-1
D=M
@R13
M=D
@THIS
D=M
@2
D=D+A
@R14
M=D
@R13
D=M
@R14
A=M
M=D
//push constant 46        
@32767// sync
@46
D=A
@SP
AM=M+1
A=A-1
M=D
//pop that 6              
@32767// sync
@SP
AM=M-1
D=M
@R13
M=D
@THAT
D=M
@6
D=D+A
@R14
M=D
@R13
D=M
@R14
A=M
M=D
//push pointer 0          
@32767// sync
@THIS
D=M
@SP
AM=M+1
A=A-1
M=D
//push pointer 1          
@32767// sync
@THAT
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
//push this 2             
@32767// sync
@THIS
D=M
@2
A=D+A
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
//push that 6             
@32767// sync
@THAT
D=M
@6
A=D+A
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
