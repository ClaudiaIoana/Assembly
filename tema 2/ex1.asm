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
    ;(a+d)-(c-b)+c
    ;a - byte, b - word, c - double word, d - qword - Unsigned representation
    ; AL -BYTE, AX - WORD, EAX - DW, EDX:EAX - QW
    
    a db 14
    b dw 456
    c dd 740847
    d dq 5147483647
    

; our code starts here
segment code use32 class=code
    start:
        ; ...
        ;(a+d)-(c-b)+c unsigned
        ;a + d = byte + quad
        
        mov EAX, 0; we need a qword for the addition so we create one
        mov AL, byte[a]; we move a in AL
        mov EDX, 0; we create a dq
        add EAX, dword[d+0]; a+d
        adc EDX, dword[d+4];the addition with a carry
        ;EDX:EAX = a+d
        ;EDX = 1
        ;EAX = 852516365
        
        mov BX, word[c+0]
        mov CX, word[c+2]
        sub BX, word [b]
        sbb CX, 0
        ;CX:BX = c - b
        ;BX = 19495
        ;CX = 11
        
        push CX
        push BX
        pop EBX; we pop the extended register which will store c-b
        
        sub EAX, EBX
        sbb EDX, 0; EDX = (a+d)-(c-b)
        
        add EAX, dword[c]
        adc EDX, 0
        ;EAX = 32D05FD5
        ;EDX = 1
        
        
        
        
    
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
