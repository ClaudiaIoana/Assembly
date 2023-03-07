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
    ;a,b,c,d = byte     e,f,g,h = word
    ;a*d*e/(f-5)
    a db 32
    d db 34
    e dw 1342
    f dw 456
    
    ;3237 rest 209

; our code starts here
segment code use32 class=code
    start:
        ; ...
        
        mov AL, byte[a] ; AL = a
        mul byte[d] ; AL * d = AX => AX = a*d
        
        mul word[e] ; AX * e = DX:AX => DX:AX = a*d*e
        
        ;DX:AX is used for devision
        mov BX, word[f] ; BX = f
        sub BX, 5 ; BX = f-5
        
        div BX ; DX:AX / BX => AX = DX:AX  / BX, DX =  DX:AX % BX
        ;AX = a*d*e/(f-5)
        ;DX = a*d*e%(f-5)
    
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
