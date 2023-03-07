bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start  

;Read an integer (positive number) n from keyboard. Then read n sentences containing at least n words (no validation needed).
; Print the string containing the concatenation of the word i of the sentence i, for i=1,n (separated by a space).
; Example: n=5
; We read the following 5 sentences:
; We read the following 5 sentences.
; Today is monday and it is raining.
; My favorite book is the one I just showed you.
; It is pretty cold today.
; Tomorrow I am going shopping.

; The string printed on the screen should be:
; We is book cold shopping.
      

; declare external functions needed by our program
extern exit, printf, gets, perror, concat, scanf
import exit msvcrt.dll
import printf msvcrt.dll
import perror msvcrt.dll
import gets msvcrt.dll
import scanf msvcrt.dll

; read 2 strings from stdin and concatenate them
;data
segment data use32 class=data
    n dd 0
    message_n  db "n = ", 0
    s1 times 101 db 0
    s2 times 101 db 0
    result resb 200
    result_temp resb 200
    format_d db "%d", 0
    format_string db "%s", 0
    rest_text times 101 db 0
    space db " ", 0
    poz db 0
    poz_line db 0
;code
segment code use32 class=code
start:
    
    ;printf("n = ")
    push dword message_n ; print a message n = 
    call [printf] ; call the print function
    add esp, 4*1 ; cleaning the parameters from the stack
    
    ;scanf("%d", n) to read n 
    push dword n ; push the variable n
    push dword format_d ; push the format for reading
    call [scanf] ; call the scanf
    add esp, 4*2 ; cleaning the parameters from the stack
        
    push s1 ; read the first line
    call [gets]
    add esp, 4
    
    push s1    
    push result     
    push result
    call concat  ;push EIP + 6; jmp concat
    add esp, 4 * 3 ; clear the stack
    ; concat(result, s1, s2)
        
    mov ecx, dword [n]
    jecxz Pas
    my_loop2:
        push ecx
        
        push s2
        call [gets]
        add esp, 4
        
        ;add a space between the strings
        push space  
        push result 
        push result 
        call concat  ;push EIP + 6; jmp concat
        add esp, 4 * 3 ; clear the stack
        ; concat(result, s1, s2)
            
        push s2    ; push parameters as per CDECL
        push result    ; 
        push result; 
        call concat  ;push EIP + 6; jmp concat
        add esp, 4 * 3 ; clear the stack
        ; concat(result, s1, s2)
        
        pop ecx
    loop my_loop2
        
    Pas:
    
    ; print the concatenated string
    push result
    call [printf]
    add esp, 4
    
    push dword 0
    call [exit]
