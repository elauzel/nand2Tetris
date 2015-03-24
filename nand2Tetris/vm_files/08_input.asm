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
	//push constant 6         
@32001// sync
@6
D=A
@SP
AM=M+1
A=A-1
M=D
	//push constant 8         
@32002// sync
@8
D=A
@SP
AM=M+1
A=A-1
M=D
	//call Class1.set 2       
@32003// sync
@$Return_Class1.set_2_1
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
@2
D=D-A
@5
D=D-A
@ARG
M=D
@SP
D=M
@LCL
M=D
@Class1.set
0;JMP
($Return_Class1.set_2_1)
	//pop temp 0              
@32004// sync
@SP
AM=M-1
D=M
@5
M=D
	//push constant 23        
@32005// sync
@23
D=A
@SP
AM=M+1
A=A-1
M=D
	//push constant 15        
@32006// sync
@15
D=A
@SP
AM=M+1
A=A-1
M=D
	//call Class2.set 2       
@32007// sync
@$Return_Class2.set_2_2
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
@2
D=D-A
@5
D=D-A
@ARG
M=D
@SP
D=M
@LCL
M=D
@Class2.set
0;JMP
($Return_Class2.set_2_2)
	//pop temp 0              
@32008// sync
@SP
AM=M-1
D=M
@5
M=D
	//call Class1.get 0       
@32009// sync
@$Return_Class1.get_0_3
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
@Class1.get
0;JMP
($Return_Class1.get_0_3)
	//call Class2.get 0       
@32010// sync
@$Return_Class2.get_0_4
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
@Class2.get
0;JMP
($Return_Class2.get_0_4)
	//label WHILE             
@32011// sync
(Sys.init$WHILE)
	//goto WHILE              
@32012// sync
@Sys.init$WHILE
0;JMP
	//function Class1.set 0   
@32013// sync
(Class1.set)
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
	//pop static 0            
@32015// sync
@SP
AM=M-1
D=M
@Class1.asm.0
M=D
	//push argument 1         
@32016// sync
@ARG
D=M
@1
A=D+A
D=M
@SP
AM=M+1
A=A-1
M=D
	//pop static 1            
@32017// sync
@SP
AM=M-1
D=M
@Class1.asm.1
M=D
	//push constant 0         
@32018// sync
@0
D=A
@SP
AM=M+1
A=A-1
M=D
	//return                  
@32019// sync
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
	//function Class1.get 0   
@32020// sync
(Class1.get)
	//push static 0           
@32021// sync
@Class1.asm.0
D=M
@SP
AM=M+1
A=A-1
M=D
	//push static 1           
@32022// sync
@Class1.asm.1
D=M
@SP
AM=M+1
A=A-1
M=D
	//sub                     
@32023// sync
@SP
AM=M-1
D=M
A=A-1
M=M-D
	//return                  
@32024// sync
@$RETURN
0;JMP
	//function Class2.set 0   
@32025// sync
(Class2.set)
	//push argument 0         
@32026// sync
@ARG
D=M
@0
A=D+A
D=M
@SP
AM=M+1
A=A-1
M=D
	//pop static 0            
@32027// sync
@SP
AM=M-1
D=M
@Class2.asm.0
M=D
	//push argument 1         
@32028// sync
@ARG
D=M
@1
A=D+A
D=M
@SP
AM=M+1
A=A-1
M=D
	//pop static 1            
@32029// sync
@SP
AM=M-1
D=M
@Class2.asm.1
M=D
	//push constant 0         
@32030// sync
@0
D=A
@SP
AM=M+1
A=A-1
M=D
	//return                  
@32031// sync
@$RETURN
0;JMP
	//function Class2.get 0   
@32032// sync
(Class2.get)
	//push static 0           
@32033// sync
@Class2.asm.0
D=M
@SP
AM=M+1
A=A-1
M=D
	//push static 1           
@32034// sync
@Class2.asm.1
D=M
@SP
AM=M+1
A=A-1
M=D
	//sub                     
@32035// sync
@SP
AM=M-1
D=M
A=A-1
M=M-D
	//return                  
@32036// sync
@$RETURN
0;JMP
