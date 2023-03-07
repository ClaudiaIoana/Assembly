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
    ;c-b-(a+a)-b
    ;a - byte, b - word, c - double word, d - qword - Unsigned representation
    ; AL -BYTE, AX - WORD, EAX - DW, EDX:EAX - QW
    a db -24
    b dw 456
    c dd 740847
    

; our code starts here
segment code use32 class=code
    start:
        ; ...
        ;c-b-(a+a)-b
        
        ;c-b
        mov AX, word[c+0]
        mov DX, word[c+2]
        sub AX, word[b]
        sbb DX, 0
        ;result in DX:AX
        ;AX = 19495
        ;DX = 11
        
        ;a+a
        mov BL, byte[a]
        add BL, byte[a]
        
        ;c-b - (a+a)
        sub AL, BL
        sbb AH, 0
        sbb DX, 0
        
        ;c-b-(a+a)-b
        sub AX,word [b]
        sbb DX, 0
        ;AX = 18831
        ;DX = 11
        
        
    
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
