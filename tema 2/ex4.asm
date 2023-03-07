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
    ;a-byte; b-word; c-doubleword; x-qword
    ; AL -BYTE, AX - WORD, EAX - DW, EDX:EAX - QW
    a db 102
    b dw 1506
    c dd 2000000
    x dq 5147483647

; our code starts here
segment code use32 class=code
    start:
        ; ...
        ;;x+(2-a*b)/(a*3)-a+c;
        
        ; a*b
        mov AL, byte[a]; move in AL the byte a
        cbw; convert AL to AX
        imul word[b]; DX:AX stores a*b
        
        push DX
        push AX; push the DX:AX on the stack
        pop ECX; pop the ECX register to move the a*b
        
        mov EBX, 2
        sub EBX, ECX; compute the 2-a*b
        ;EBX = 2-a*b
 
        ;a*3
        mov AL, 3
        imul byte[a]; AX = AL*a = a*3
        mov CX, AX; CX = a*3
        
        push EBX; we push EBX on the stack
        pop AX
        pop DX; we pop the DX:AX for the division
        ;DX:AX = EBX
        
        idiv CX
        ;AX = (2-a*b)/(a*3)
        ;DX = (2-a*b)%(a*3)
        
        mov EBX, [x+0]
        mov ECX, [x+4]; ECX:EBX = x
        
        ;from here it will take the unsigned result
        
        cwde; chage AX to EAX
        add EBX, EAX;x+(2-a*b)/(a*3)
        
        mov AL, byte[a]
        cbw; AL-> AX
        cwde; chage AX to EAX
        
        
        sub EBX, EAX; x+(2-a*b)/(a*3)-a
        
        
        mov EAX, [c]
        add EBX, EAX;x+(2-a*b)/(a*3)-a+c
        
        ;ecx = 2h
        ;ebx = 32EEE024h
    
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
