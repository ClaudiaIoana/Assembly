bits 32 ; assembling for the 32 bits architecture

;Read two numbers a and b (in base 16) from the keyboard and calculate a+b. Display the result in base 10

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit, printf, scanf               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll
import printf msvcrt.dll
import scanf msvcrt.dll

; our data is declared here (the variables needed by our program)
segment data use32 class=data
    ; ...
    a dd 0
    b dd 0
    message_a db "a = ", 0
    message_b db "b = ", 0
    message_ab db "a+b = %d", 0
    format_hexa db "%x", 0

; our code starts here
segment code use32 class=code
    start:
        ; ...
        
        ;printf("a = ")
        push dword message_a ; print a message a = 
        call [printf] ; call the print function
        add esp, 4*1 ; cleaning the parameters from the stack
        
        ;scanf("%x", a) to read a hexa number
        push dword a ; push the variable a
        push dword format_hexa ; push the format for reading
        call [scanf] ; call the scanf
        add esp, 4*2 ; cleaning the parameters from the stack
        
        ;printf("b = ")
        push dword message_b ; print a message b = 
        call [printf] ; call the print function
        add esp, 4*1
        
        ;scanf("%x", b) to read a hexa number
        push dword b ; push the variable b on the stack
        push dword format_hexa ; push the format
        call [scanf] ; call the scanf
        add esp, 4*2
        
        ; a + b
        mov eax, dword[a]
        add eax, dword[b]
        
        ;printf ("a+b= %d", rez) print the number in decimal format
        push eax ; a+b
        push dword message_ab ; "a+b = %d"
        call [printf]
        add esp, 4*2

        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
