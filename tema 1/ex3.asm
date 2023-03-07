bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
segment data use32 class=data
    ; ...
    ;(c+d)+(a-b)+a
    ; words are bigger that 256 and smaller than 65535 for unsigned
    a dw 783
    b dw 1292
    c dw 1332
    d dw 5334
    
    ;(1332 + 5334)+(783 - 1292)+783
    ;6666+(-509)+783
    ;6940

; our code starts here
segment code use32 class=code
    start:
        ; ...
        ; for words we need to use AX
        mov AX, word[c] ; AX = c
        add AX, word[d] ; AX = c + d
        
        mov BX, word[a] ; BX = a
        sub BX, word[b] ; BX = a - b
        
        add AX, BX ; AX = AX + BX = (c + d) + (a - b)
        add AX, word[a] ; AX = AX + a
        
        ; the final result is stored in AX
    
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
