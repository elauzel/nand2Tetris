@256
D=A
@SP
M=D
@$Return_Sys.init_0_0
D=A
@SP
AM=M+1
A=A-1
M=D
@LCL
D=M
@SP
AM=M+1
A=A-1
M=D
@ARG
D=M
@SP
AM=M+1
A=A-1
M=D
@THIS
D=M
@SP
AM=M+1
A=A-1
M=D
@THAT
D=M
@SP
AM=M+1
A=A-1
M=D
@SP
D=M
@0
D=D-A
@5
D=D-A
@ARG
M=D
@SP
D=M
@LCL
M=D
@Sys.init
0;JMP
($Return_Sys.init_0_0)
	//function Sys.init 0     
@32000// sync
(Sys.init)
	//push constant 4         
@32001// sync
@4
D=A
@SP
AM=M+1
A=A-1
M=D
	//call Main.fibonacci 1   
@32002// sync
@$Return_Main.fibonacci_1_1
D=A
@SP
AM=M+1
A=A-1
M=D
@LCL
D=M
@SP
AM=M+1
A=A-1
M=D
@ARG
D=M
@SP
AM=M+1
A=A-1
M=D
@THIS
D=M
@SP
AM=M+1
A=A-1
M=D
@THAT
D=M
@SP
AM=M+1
A=A-1
M=D
@SP
D=M
@1
D=D-A
@5
D=D-A
@ARG
M=D
@SP
D=M
@LCL
M=D
@Main.fibonacci
0;JMP
($Return_Main.fibonacci_1_1)
	//label WHILE             
@32003// sync
(Sys.init_WHILE)
	//goto WHILE              
@32004// sync
@Sys.init_WHILE
0;JMP
	//function Main.fibonacci 0
@32005// sync
(Main.fibonacci)
	//push argument 0         
@32006// sync
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
@32007// sync
@2
D=A
@SP
AM=M+1
A=A-1
M=D
	//lt                      
@32008// sync
@SP
AM=M-1
D=M
A=A-1
D=M-D
@$LT_2
D;JLT
@0
D=A
@$NLT_3
0;JMP
($LT_2)
@0
D=A-1
($NLT_3)
@SP
A=M-1
M=D
	//if-goto IF_TRUE         
@32009// sync
@SP
AM=M-1
D=M
@Main.fibonacci_IF_TRUE
D=M
D;JNE
	//goto IF_FALSE           
@32010// sync
@Main.fibonacci_IF_FALSE
0;JMP
	//label IF_TRUE           
@32011// sync
(Main.fibonacci_IF_TRUE)
	//push argument 0         
@32012// sync
@ARG
D=M
@0
A=D+A
D=M
@SP
AM=M+1
A=A-1
M=D
	//return                  
@32013// sync
($RETURN_)
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
@ARG
D=M
@1
D=D+A
@SP
M=D
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
	//label IF_FALSE          
@32014// sync
(Main.fibonacci_IF_FALSE)
	//push argument 0         
@32015// sync
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
@32016// sync
@2
D=A
@SP
AM=M+1
A=A-1
M=D
	//sub                     
@32017// sync
@SP
AM=M-1
D=M
A=A-1
M=M-D
	//call Main.fibonacci 1   
@32018// sync
@$Return_Main.fibonacci_1_4
D=A
@SP
AM=M+1
A=A-1
M=D
@LCL
D=M
@SP
AM=M+1
A=A-1
M=D
@ARG
D=M
@SP
AM=M+1
A=A-1
M=D
@THIS
D=M
@SP
AM=M+1
A=A-1
M=D
@THAT
D=M
@SP
AM=M+1
A=A-1
M=D
@SP
D=M
@1
D=D-A
@5
D=D-A
@ARG
M=D
@SP
D=M
@LCL
M=D
@Main.fibonacci
0;JMP
($Return_Main.fibonacci_1_4)
	//push argument 0         
@32019// sync
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
@32020// sync
@1
D=A
@SP
AM=M+1
A=A-1
M=D
	//sub                     
@32021// sync
@SP
AM=M-1
D=M
A=A-1
M=M-D
	//call Main.fibonacci 1   
@32022// sync
@$Return_Main.fibonacci_1_5
D=A
@SP
AM=M+1
A=A-1
M=D
@LCL
D=M
@SP
AM=M+1
A=A-1
M=D
@ARG
D=M
@SP
AM=M+1
A=A-1
M=D
@THIS
D=M
@SP
AM=M+1
A=A-1
M=D
@THAT
D=M
@SP
AM=M+1
A=A-1
M=D
@SP
D=M
@1
D=D-A
@5
D=D-A
@ARG
M=D
@SP
D=M
@LCL
M=D
@Main.fibonacci
0;JMP
($Return_Main.fibonacci_1_5)
	//add                     
@32023// sync
@SP
AM=M-1
D=M
A=A-1
M=M+D
	//return                  
@32024// sync
@$RETURN_
0;JMP
