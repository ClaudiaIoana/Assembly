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
    ;a,b,c,d - byte
    ;bytes are smaller than 255 in the unsigned interpretation
    ;(a+d-c)-(b+b)
    a db 12
    b db 4
    c db 7
    d db 10
    ; 7

; our code starts here
segment code use32 class=code
    start:
        ; ...
        mov AL, byte[a] ; AL = a
        add AL, byte[d] ; AL = a + d
        sub AL, byte[c] ; AL = a + d - c
        
        mov BL, byte[b] ; BL = b
        add BL, byte[b] ; BL = b + b
        
        sub AL, BL ; AL = AL + BL = (a + d - c) + (b + b)
    
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
