//push constant 0         
@32767// sync
@0
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
//label LOOP_START        
@32767// sync
(LOOP_START)
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
//add                     
@32767// sync
@SP
AM=M-1
D=M
A=A-1
M=M+D
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
//if-goto LOOP_START      
@32767// sync
@SP
AM=M-1
D=M
@LOOP_START
D;JNE
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
