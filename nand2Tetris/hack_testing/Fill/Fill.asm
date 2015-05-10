// Runs an infinite loop that listens to the keyboard input. 
// When a key is pressed (any key), the program blackens the screen,
// i.e. writes "black" in every pixel. When no key is pressed, the
// program clears the screen, i.e. writes "white" in every pixel.

// Screen contents are 8K and start at @16384
// each of 256 rows are represented by 32 consecutive 16-bit words
// pixel at row r from the top and column c from the left is mapped on the
// c%16 bit of the word at RAM[16384 + 32r + c/16]
// 1=black 0=white

// @SCREEN // sets the A register to point to the memory word that is mapped to the
//			  16 left-most pixels of the top row of the screen
// M=1 		  blacken the left-most pixel

// keyboard is a single-word memory map located in RAM[24576]
// when a key is pressed, its 16-bit ASCII code appears here - otherwise, it is 0

@status
    M=-1        // status=0xFFFF
    D=0         // Argument - what to set screen bits to
    @SETSCREEN
    0;JMP

(WHILE)
    @KBD
    D=M         // D = current keyboard character
    @SETSCREEN
    D;JEQ       // If no key was pressed, clear the screen (white)
    D=-1        // else, fill the screen (black)
    
(SETSCREEN)
    @ARG
    M=D         // ARG = status
    @status
    D=D-M       // D = new status - old status
    @WHILE
    D;JEQ       // if no change, jump to WHILE
    
    @ARG 		// else,
    D=M
    @status
    M=D         // update status = ARG
    
    @SCREEN
    D=A         // D = first screen address
    @8192
    D=D+A       // D = last screen address (first screen address + 8K)
    @i
    M=D         // i = last screen address
    
(FILL)    
    @i
    D=M-1 		// D = last screen address - 1
    M=D         // i = last screen address - 1
    @WHILE
    D;JLT       // if (i < 0) jump to WHILE
    
    @status
    D=M         // D = status
    @i
    A=M         // RAM[i] = last screen address - 1
    M=D         // i = status
    @FILL
    0;JMP 		// loop