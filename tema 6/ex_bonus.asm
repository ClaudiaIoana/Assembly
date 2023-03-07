bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start  

;A. Read from a file a number n and then an array of n positive numbers. Print the elements of the array in base 8 on the console (standard output).
;Ex. File.in

;5
;10 20 15 8 33
;Will print:  12, 24, 17, 10, 41      

; declare external functions needed by our program
extern exit, printf, fscanf, fopen, fclose              ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll
import printf msvcrt.dll
import fscanf msvcrt.dll
import fopen msvcrt.dll
import fclose msvcrt.dll

; our data is declared here (the variables needed by our program)
segment data use32 class=data
    ; ...
    len db 0
    number dd 0
    format db "%d", 0
    format_octa db "%o ", 0
    file db "File.txt", 0
    file_descriptor dd -1
    acces_mode db "r", 0
    

; our code starts here
segment code use32 class=code
    start:
        ; ...
        ;call fopen to create the file
        push dword acces_mode
        push dword file
        call [fopen]
        add esp, 4*2 
        
        mov [file_descriptor], eax
        
        push dword len
        push dword format
        push dword [file_descriptor]
        call [fscanf]
        add esp, 4*3
        
        mov ecx, 0
        mov ecx, dword [len]
        jecxz Stop
        
        My_loop:
            push ecx
            
            push dword number
            push dword format
            push dword [file_descriptor]
            call [fscanf]
            add esp, 4*3
            
            mov eax, dword[number]
            
            push eax
            push dword format_octa
            call [printf]
            add esp, 4*2
            pop ecx
        
        loop My_loop
        
        Stop:
        
        push dword [file_descriptor]
        call [fclose] ; close the file
        add esp, 4
        
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
