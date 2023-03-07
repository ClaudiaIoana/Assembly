bits 32 ; assembling for the 32 bits architecture

;A byte string S is given. Obtain the string D1 which contains all the positive numbers of S and the string D2 which contains all the negative numbers of S.
;Example:
;S: 1, 3, -2, -5, 3, -8, 5, 0
;D1: 1, 3, 3, 5, 0
;D2: -2, -5, -8

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
segment data use32 class=data
    ; ...
    S db 1, 3, -2, -5, 3, -8, 5, 0
    lenS equ $-S ; to determine the length of the string S
    D1 resb lenS; we reserve lenS bytes
    D2 resb lenS; we reserve lenS bytes

; our code starts here
segment code use32 class=code
    start:
        ; ...
        mov ecx, lenS
        mov esi, D1 ; for the positive numbers
        mov edi, D2 ; for the negative numbers
        mov ebx, 0; ebx will be used as a counter
        
        JECXZ Skip
        MY_LOOP:
            mov al, [S+ebx]; we put in al the first element of the array
            inc ebx; we move the counter to the next element
            
            cmp al, 0
            JGE Poz ; we jump if the number is positive
                ; we enter if the number is negative
                mov [edi], al
                inc edi
                jmp Stop
            Poz:
                mov [esi], al
                inc esi
            Stop:
           
        loop MY_LOOP ; we want to know if the zero flag was triggered
        Skip: ; if we don't have any elements in the array we leave the program
    
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
