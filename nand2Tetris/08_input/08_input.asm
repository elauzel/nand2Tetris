@256
D=A
@SP
M=D
@null$Sys.init
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
(null$Sys.init)
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
@Sys.init$Main.fibonacci
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
(Sys.init$Main.fibonacci)
	//label WHILE             
@32003// sync
(Sys.init$WHILE)
	//goto WHILE              
@32004// sync
@Sys.init$WHILE
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
@$LT_0
D;JLT
@0
D=A
@$NLT_1
0;JMP
($LT_0)
@0
D=A-1
($NLT_1)
@SP
A=M-1
M=D
	//if-goto IF_TRUE         
@32009// sync
@SP
AM=M-1
D=M
@IF_TRUE
A=M
D;JNE
	//goto IF_FALSE           
@32010// sync
@Main.fibonacci$IF_FALSE
0;JMP
	//label IF_TRUE           
@32011// sync
(Main.fibonacci$IF_TRUE)
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
@32014// sync
(Main.fibonacci$IF_FALSE)
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
@Main.fibonacci$Main.fibonacci
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
(Main.fibonacci$Main.fibonacci)
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
@Main.fibonacci$Main.fibonacci
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
(Main.fibonacci$Main.fibonacci)
	//add                     
@32023// sync
@SP
AM=M-1
D=M
A=A-1
M=M+D
	//return                  
@32024// sync
@$RETURN
0;JMP
