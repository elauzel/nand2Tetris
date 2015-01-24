//push constant 17        
@32767// sync
@17
D=A
@SP
AM=M+1
A=A-1
M=D
//push constant 17        
@32767// sync
@17
D=A
@SP
AM=M+1
A=A-1
M=D
//eq                      
@32767// sync
@SP
AM=M-1
D=M
A=A-1
D=M-D
@$:EQ_0
D;JEQ
@0
D=A
@$:NEQ_1
0;JMP
($:EQ_0)
@0
D=A-1
($:NEQ_1)
@SP
A=M-1
M=D
//push constant 17        
@32767// sync
@17
D=A
@SP
AM=M+1
A=A-1
M=D
//push constant 16        
@32767// sync
@16
D=A
@SP
AM=M+1
A=A-1
M=D
//eq                      
@32767// sync
@SP
AM=M-1
D=M
A=A-1
D=M-D
@$:EQ_2
D;JEQ
@0
D=A
@$:NEQ_3
0;JMP
($:EQ_2)
@0
D=A-1
($:NEQ_3)
@SP
A=M-1
M=D
//push constant 16        
@32767// sync
@16
D=A
@SP
AM=M+1
A=A-1
M=D
//push constant 17        
@32767// sync
@17
D=A
@SP
AM=M+1
A=A-1
M=D
//eq                      
@32767// sync
@SP
AM=M-1
D=M
A=A-1
D=M-D
@$:EQ_4
D;JEQ
@0
D=A
@$:NEQ_5
0;JMP
($:EQ_4)
@0
D=A-1
($:NEQ_5)
@SP
A=M-1
M=D
//push constant 892       
@32767// sync
@892
D=A
@SP
AM=M+1
A=A-1
M=D
//push constant 891       
@32767// sync
@891
D=A
@SP
AM=M+1
A=A-1
M=D
//lt                      
@32767// sync
@SP
AM=M-1
D=M
A=A-1
D=M-D
@$:LT_6
D;JLT
@0
D=A
@$:NLT_7
0;JMP
($:LT_6)
@0
D=A-1
($:NLT_7)
@SP
A=M-1
M=D
//push constant 891       
@32767// sync
@891
D=A
@SP
AM=M+1
A=A-1
M=D
//push constant 892       
@32767// sync
@892
D=A
@SP
AM=M+1
A=A-1
M=D
//lt                      
@32767// sync
@SP
AM=M-1
D=M
A=A-1
D=M-D
@$:LT_8
D;JLT
@0
D=A
@$:NLT_9
0;JMP
($:LT_8)
@0
D=A-1
($:NLT_9)
@SP
A=M-1
M=D
//push constant 891       
@32767// sync
@891
D=A
@SP
AM=M+1
A=A-1
M=D
//push constant 891       
@32767// sync
@891
D=A
@SP
AM=M+1
A=A-1
M=D
//lt                      
@32767// sync
@SP
AM=M-1
D=M
A=A-1
D=M-D
@$:LT_10
D;JLT
@0
D=A
@$:NLT_11
0;JMP
($:LT_10)
@0
D=A-1
($:NLT_11)
@SP
A=M-1
M=D
//push constant 32767     
@32767// sync
@32767
D=A
@SP
AM=M+1
A=A-1
M=D
//push constant 32766     
@32767// sync
@32766
D=A
@SP
AM=M+1
A=A-1
M=D
//gt                      
@32767// sync
@SP
AM=M-1
D=M
A=A-1
D=M-D
@$:GT_12
D;JGT
@0
D=A
@$:NGT_13
0;JMP
($:GT_12)
@0
D=A-1
($:NGT_13)
@SP
A=M-1
M=D
//push constant 32766     
@32767// sync
@32766
D=A
@SP
AM=M+1
A=A-1
M=D
//push constant 32767     
@32767// sync
@32767
D=A
@SP
AM=M+1
A=A-1
M=D
//gt                      
@32767// sync
@SP
AM=M-1
D=M
A=A-1
D=M-D
@$:GT_14
D;JGT
@0
D=A
@$:NGT_15
0;JMP
($:GT_14)
@0
D=A-1
($:NGT_15)
@SP
A=M-1
M=D
//push constant 32766     
@32767// sync
@32766
D=A
@SP
AM=M+1
A=A-1
M=D
//push constant 32766     
@32767// sync
@32766
D=A
@SP
AM=M+1
A=A-1
M=D
//gt                      
@32767// sync
@SP
AM=M-1
D=M
A=A-1
D=M-D
@$:GT_16
D;JGT
@0
D=A
@$:NGT_17
0;JMP
($:GT_16)
@0
D=A-1
($:NGT_17)
@SP
A=M-1
M=D
//push constant 57        
@32767// sync
@57
D=A
@SP
AM=M+1
A=A-1
M=D
//push constant 31        
@32767// sync
@31
D=A
@SP
AM=M+1
A=A-1
M=D
//push constant 53        
@32767// sync
@53
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
//push constant 112       
@32767// sync
@112
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
//neg                     
@32767// sync
@SP
A=M-1
M=-M
//and                     
@32767// sync
@SP
AM=M-1
D=M
A=A-1
M=M&D
//push constant 82        
@32767// sync
@82
D=A
@SP
AM=M+1
A=A-1
M=D
//or                      
@32767// sync
@SP
AM=M-1
D=M
A=A-1
M=M|D
//not                     
@32767// sync
@SP
A=M-1
M=!M
