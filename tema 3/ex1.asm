bits 32 ; assembling for the 32 bits architecture

;Given the doubleword A, obtain the integer number n represented on the bits 14-17 of A. Then obtain the doubleword B by rotating A n positions to the left. Finally, obtain the byte C as follows:
;the bits 0-5 of C are the same as the bits 1-6 of B
;the bits 6-7 of C are the same as the bits 17-18 of B

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
segment data use32 class=data
    ; ...
    a dd 10111111001110100101001101110100b
    n db 0
    b dd 0
    c db 0

; our code starts here
segment code use32 class=code
    start:
        ; ...
        ; for representing n we need a byte (4 bits)
        ; n = 1001b

        mov  bx, 0 ; we compute the result in bx

        mov  eax, [a] ; we isolate bits 10-12 of B
        and  eax, 00000000000000111100000000000000b ; select the bits 14-17 from a
        
        mov  cl, 14 ; we move in cl the number of steps with which we want to rotate
        ror  eax, cl ; we rotate 14 positions to the right
        mov [n], al; move the result in the variable n
        ; n = 9
        
        mov eax, [a]; move a in eax
        mov cl, [n]; prepare to rotate a with n positions
        rol eax, cl; rotate a with n positions
        ;eax = 01110100101001101110100101111110
        
        mov dword[b], eax; move the result in b
        
        and eax, 00000000000000000000000000111110b; separate the 1-5 bits from b
        mov cl, 1
        ror eax, cl; rotate the number with 1 position
        mov ebx, eax; the bits 0-5 of C are the same as the bits 1-6 of B
        ;ebx= 11111b = 1Fh
        
        ;the bits 6-7 of C are the same as the bits 17-18 of B
        mov eax, [b]; move b in eax
        and eax, 00000000000001100000000000000000b; isolate bits 17-18
        mov cl, 17
        ror eax, cl; rotate eax with 17 positions
        ;eax = ...011b = ...03h
        
        mov cl, 6
        rol eax, cl; rotate the number with 6 positions so that bits in c corespond with eax
        xor ebx, eax; move the bits from eax to ebx
        ;ebx = ...011111111b = ..0DFh
        
        mov [c], bl; store the result in c
        
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
