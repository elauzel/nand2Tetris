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
	//function Main.fibonacci 0
@32000// sync
(Main.fibonacci)
	//push argument 0         
@32001// sync
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
@32002// sync
@2
D=A
@SP
AM=M+1
A=A-1
M=D
	//lt                      
@32003// sync
@SP
AM=M-1
D=M
A=A-1
D=M-D
@$LT_1
D;JLT
@0
D=A
@$NLT_2
0;JMP
($LT_1)
@0
D=A-1
($NLT_2)
@SP
A=M-1
M=D
	//if-goto IF_TRUE         
@32004// sync
@SP
AM=M-1
D=M
@Main.fibonacci$IF_TRUE
D;JNE
	//goto IF_FALSE           
@32005// sync
@Main.fibonacci$IF_FALSE
0;JMP
	//label IF_TRUE           
@32006// sync
(Main.fibonacci$IF_TRUE)
	//push argument 0         
@32007// sync
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
@32008// sync
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
	//label IF_FALSE          
@32009// sync
(Main.fibonacci$IF_FALSE)
	//push argument 0         
@32010// sync
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
@32011// sync
@2
D=A
@SP
AM=M+1
A=A-1
M=D
	//sub                     
@32012// sync
@SP
AM=M-1
D=M
A=A-1
M=M-D
	//call Main.fibonacci 1   
@32013// sync
@$Return_Main.fibonacci_1_3
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
($Return_Main.fibonacci_1_3)
	//push argument 0         
@32014// sync
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
@32015// sync
@1
D=A
@SP
AM=M+1
A=A-1
M=D
	//sub                     
@32016// sync
@SP
AM=M-1
D=M
A=A-1
M=M-D
	//call Main.fibonacci 1   
@32017// sync
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
	//add                     
@32018// sync
@SP
AM=M-1
D=M
A=A-1
M=M+D
	//return                  
@32019// sync
@$RETURN
0;JMP
	//function Sys.init 0     
@32020// sync
(Sys.init)
	//push constant 4         
@32021// sync
@4
D=A
@SP
AM=M+1
A=A-1
M=D
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
	//label WHILE             
@32023// sync
(Sys.init$WHILE)
	//goto WHILE              
@32024// sync
@Sys.init$WHILE
0;JMP
