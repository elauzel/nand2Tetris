//push constant 10        
@32767// sync
@10
D=A
@SP
AM=M+1
A=A-1
M=D
//pop local 0             
@32767// sync
@SP
AM=M-1
D=M
@R13
M=D
@LCL
D=M
@0
D=D+A
@R14
M=D
@R13
D=M
@R14
A=M
M=D
//push constant 21        
@32767// sync
@21
D=A
@SP
AM=M+1
A=A-1
M=D
//push constant 22        
@32767// sync
@22
D=A
@SP
AM=M+1
A=A-1
M=D
//pop argument 2          
@32767// sync
@SP
AM=M-1
D=M
@R13
M=D
@ARG
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
//pop argument 1          
@32767// sync
@SP
AM=M-1
D=M
@R13
M=D
@ARG
D=M
@1
D=D+A
@R14
M=D
@R13
D=M
@R14
A=M
M=D
//push constant 36        
@32767// sync
@36
D=A
@SP
AM=M+1
A=A-1
M=D
//pop this 6              
@32767// sync
@SP
AM=M-1
D=M
@R13
M=D
@THIS
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
//push constant 42        
@32767// sync
@42
D=A
@SP
AM=M+1
A=A-1
M=D
//push constant 45        
@32767// sync
@45
D=A
@SP
AM=M+1
A=A-1
M=D
//pop that 5              
@32767// sync
@SP
AM=M-1
D=M
@R13
M=D
@THAT
D=M
@5
D=D+A
@R14
M=D
@R13
D=M
@R14
A=M
M=D
//pop that 2              
@32767// sync
@SP
AM=M-1
D=M
@R13
M=D
@THAT
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
//push constant 510       
@32767// sync
@510
D=A
@SP
AM=M+1
A=A-1
M=D
//pop temp 6              
@32767// sync
@SP
AM=M-1
D=M
@11
M=D
//push local 0            
@32767// sync
@LCL
D=M
@0
A=D+A
D=M
@SP
AM=M+1
A=A-1
M=D
//push that 5             
@32767// sync
@THAT
D=M
@5
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
//push argument 1         
@32767// sync
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
@32767// sync
@SP
AM=M-1
D=M
A=A-1
M=M-D
//push this 6             
@32767// sync
@THIS
D=M
@6
A=D+A
D=M
@SP
AM=M+1
A=A-1
M=D
//push this 6             
@32767// sync
@THIS
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
//sub                     
@32767// sync
@SP
AM=M-1
D=M
A=A-1
M=M-D
//push temp 6             
@32767// sync
@11
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
