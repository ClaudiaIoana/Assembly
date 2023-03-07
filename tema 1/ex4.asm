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
    ;(d-b*c+b*2)/a
    ;a,b,c = byte,   d = word
    a db 10
    b db 23
    c db 7
    d dw 1234
    
    ;1110, rest 9
; our code starts here
segment code use32 class=code
    start:
        ; ...
        
        mov AL, byte[b] ; AL = b
        mul byte[c] ; AL * c = byte * byte => word => AX = b*c
        
        mov BX, AX ; BX = AX = b*c
        
        mov AL, 2 ; AL = 2
        mul byte[b] ; AL * b = byte * byte => word = AX = 2*b
        
        mov CX, AX ; CX = AX = 2*b
        
        mov AX, word[d] ; AX = d
        sub AX, BX ; AX = AX - BX = d-b*c
        add AX, CX ; AX =  AX + CX = d-b*c+b*2
        
        div byte[a] ; AL = AX / a = (d-b*c+b*2)/a and AH = AX % a = (d-b*c+b*2)%a
        
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
