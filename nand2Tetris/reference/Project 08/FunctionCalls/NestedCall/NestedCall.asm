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
	//push constant 4000      
@32001// sync
@4000
D=A
@SP
AM=M+1
A=A-1
M=D
	//pop pointer 0           
@32002// sync
@SP
AM=M-1
D=M
@THIS
M=D
	//push constant 5000      
@32003// sync
@5000
D=A
@SP
AM=M+1
A=A-1
M=D
	//pop pointer 1           
@32004// sync
@SP
AM=M-1
D=M
@THAT
M=D
	//call Sys.main 0         
@32005// sync
@$Return_Sys.main_0_1
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
@Sys.main
0;JMP
($Return_Sys.main_0_1)
	//pop temp 1              
@32006// sync
@SP
AM=M-1
D=M
@6
M=D
	//label LOOP              
@32007// sync
(Sys.init$LOOP)
	//goto LOOP               
@32008// sync
@Sys.init$LOOP
0;JMP
	//function Sys.main 5     
@32009// sync
(Sys.main)
@0
D=A
@SP
AM=M+1
A=A-1
M=D
@0
D=A
@SP
AM=M+1
A=A-1
M=D
@0
D=A
@SP
AM=M+1
A=A-1
M=D
@0
D=A
@SP
AM=M+1
A=A-1
M=D
@0
D=A
@SP
AM=M+1
A=A-1
M=D
	//push constant 4001      
@32010// sync
@4001
D=A
@SP
AM=M+1
A=A-1
M=D
	//pop pointer 0           
@32011// sync
@SP
AM=M-1
D=M
@THIS
M=D
	//push constant 5001      
@32012// sync
@5001
D=A
@SP
AM=M+1
A=A-1
M=D
	//pop pointer 1           
@32013// sync
@SP
AM=M-1
D=M
@THAT
M=D
	//push constant 200       
@32014// sync
@200
D=A
@SP
AM=M+1
A=A-1
M=D
	//pop local 1             
@32015// sync
@SP
AM=M-1
D=M
@R13
M=D
@LCL
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
	//push constant 40        
@32016// sync
@40
D=A
@SP
AM=M+1
A=A-1
M=D
	//pop local 2             
@32017// sync
@SP
AM=M-1
D=M
@R13
M=D
@LCL
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
	//push constant 6         
@32018// sync
@6
D=A
@SP
AM=M+1
A=A-1
M=D
	//pop local 3             
@32019// sync
@SP
AM=M-1
D=M
@R13
M=D
@LCL
D=M
@3
D=D+A
@R14
M=D
@R13
D=M
@R14
A=M
M=D
	//push constant 123       
@32020// sync
@123
D=A
@SP
AM=M+1
A=A-1
M=D
	//call Sys.add12 1        
@32021// sync
@$Return_Sys.add12_1_2
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
@Sys.add12
0;JMP
($Return_Sys.add12_1_2)
	//pop temp 0              
@32022// sync
@SP
AM=M-1
D=M
@5
M=D
	//push local 0            
@32023// sync
@LCL
D=M
@0
A=D+A
D=M
@SP
AM=M+1
A=A-1
M=D
	//push local 1            
@32024// sync
@LCL
D=M
@1
A=D+A
D=M
@SP
AM=M+1
A=A-1
M=D
	//push local 2            
@32025// sync
@LCL
D=M
@2
A=D+A
D=M
@SP
AM=M+1
A=A-1
M=D
	//push local 3            
@32026// sync
@LCL
D=M
@3
A=D+A
D=M
@SP
AM=M+1
A=A-1
M=D
	//push local 4            
@32027// sync
@LCL
D=M
@4
A=D+A
D=M
@SP
AM=M+1
A=A-1
M=D
	//add                     
@32028// sync
@SP
AM=M-1
D=M
A=A-1
M=M+D
	//add                     
@32029// sync
@SP
AM=M-1
D=M
A=A-1
M=M+D
	//add                     
@32030// sync
@SP
AM=M-1
D=M
A=A-1
M=M+D
	//add                     
@32031// sync
@SP
AM=M-1
D=M
A=A-1
M=M+D
	//return                  
@32032// sync
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
	//function Sys.add12 0    
@32033// sync
(Sys.add12)
	//push constant 4002      
@32034// sync
@4002
D=A
@SP
AM=M+1
A=A-1
M=D
	//pop pointer 0           
@32035// sync
@SP
AM=M-1
D=M
@THIS
M=D
	//push constant 5002      
@32036// sync
@5002
D=A
@SP
AM=M+1
A=A-1
M=D
	//pop pointer 1           
@32037// sync
@SP
AM=M-1
D=M
@THAT
M=D
	//push argument 0         
@32038// sync
@ARG
D=M
@0
A=D+A
D=M
@SP
AM=M+1
A=A-1
M=D
	//push constant 12        
@32039// sync
@12
D=A
@SP
AM=M+1
A=A-1
M=D
	//add                     
@32040// sync
@SP
AM=M-1
D=M
A=A-1
M=M+D
	//return                  
@32041// sync
@$RETURN
0;JMP
