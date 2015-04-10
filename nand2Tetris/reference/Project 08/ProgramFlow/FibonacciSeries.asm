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
//pop pointer 1           
@32767// sync
@SP
AM=M-1
D=M
@THAT
M=D
//push constant 0         
@32767// sync
@0
D=A
@SP
AM=M+1
A=A-1
M=D
//pop that 0              
@32767// sync
@SP
AM=M-1
D=M
@R13
M=D
@THAT
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
//push constant 1         
@32767// sync
@1
D=A
@SP
AM=M+1
A=A-1
M=D
//pop that 1              
@32767// sync
@SP
AM=M-1
D=M
@R13
M=D
@THAT
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
//push argument 0         
@32767// sync
@ARG
D=M
@0
A=D+A
D=M
@SP
AM=M+1
A=A-1
M=D
//push constant 2         
@32767// sync
@2
D=A
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
//pop argument 0          
@32767// sync
@SP
AM=M-1
D=M
@R13
M=D
@ARG
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
//label MAIN_LOOP_START   
@32767// sync
(MAIN_LOOP_START)
//push argument 0         
@32767// sync
@ARG
D=M
@0
A=D+A
D=M
@SP
AM=M+1
A=A-1
M=D
//if-goto COMPUTE_ELEMENT 
@32767// sync
@SP
AM=M-1
D=M
@COMPUTE_ELEMENT
D;JNE
//goto END_PROGRAM        
@32767// sync
@END_PROGRAM
0;JMP
//label COMPUTE_ELEMENT   
@32767// sync
(COMPUTE_ELEMENT)
//push that 0             
@32767// sync
@THAT
D=M
@0
A=D+A
D=M
@SP
AM=M+1
A=A-1
M=D
//push that 1             
@32767// sync
@THAT
D=M
@1
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
//push pointer 1          
@32767// sync
@THAT
D=M
@SP
AM=M+1
A=A-1
M=D
//push constant 1         
@32767// sync
@1
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
//pop pointer 1           
@32767// sync
@SP
AM=M-1
D=M
@THAT
M=D
//push argument 0         
@32767// sync
@ARG
D=M
@0
A=D+A
D=M
@SP
AM=M+1
A=A-1
M=D
//push constant 1         
@32767// sync
@1
D=A
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
//pop argument 0          
@32767// sync
@SP
AM=M-1
D=M
@R13
M=D
@ARG
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
//goto MAIN_LOOP_START    
@32767// sync
@MAIN_LOOP_START
0;JMP
//label END_PROGRAM       
@32767// sync
(END_PROGRAM)
