bits 32 ; assembling for the 32 bits architecture

;Given an array S of doublewords, build the array of bytes D formed from bytes of doublewords sorted as unsigned numbers in ascending order.
;Example:
 ;s DD 12345607h, 1A2B3C15h
 ;d DB 07h, 12h, 15h, 1Ah, 2Bh, 34h, 3Ch, 56h

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit               ; tell nasm that exit exists even if we won't be defining it
extern printf
import exit msvcrt.dll  
import printf msvcrt.dll
  ; exit is a function that ends the calling process. It is defined in msvcrt.dll
                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
segment data use32 class=data
    ; ...
    s dd 12345607h, 1A2B3C15h
    len equ $-s
    len2 equ $-s-1
    sir resb len; we reserve len bytes
    rez resb len; we reserve len bytes
    format db "%d ", 0

; our code starts here
segment code use32 class=code
    start:
        ; ...
        mov ecx, len
        JECXZ Skip ; we skip to the end if the string has no elements
        
        mov esi, s
        mov edi, sir; sir contains the bytes of the doublewords
        ;movsb
        
        MY_LOOP:
            movsb; edi = esi
            ;we store the bites from the numbers in the rez string
        loop MY_LOOP
        
        
        ;SORT:
        ;for(i = 0; i< len-1; i++)
        ;   for(j = i+1; j < len; j++)
        ;       if(a[i] > a[j]){
        ;           aux = a[i]
        ;           a[i] = a[j]
        ;           a[j] = aux
        
        
        mov ecx, len
        sub ecx, 1 ;we set the counter for len-1 steps
        JECXZ Skip ; we skip to the end if the string had only 1 elem
        
        ;we are going to use esi to point at the i element
        mov esi, sir
        
        for_loop:
            mov edi, esi
            inc edi ; edi will point at the j elem
            mov al, [esi]; eax = s[i]
            push ecx ; we push ecx on the stack to preserve the value of ecx
            ;in the second for we also use ecx
            JECXZ End_loop
                for_loop2:
                    mov bl, [edi]; ebx = s[j]
                    cmp al, bl;if(a[i] > a[j])
                    jbe Sorted
                        STOSB	;a[j] = a[i], edi = al
                        mov [esi], bl;a[i] = a[j]
                        mov al, [esi];we save in al the position for the future comparisons
                        jmp Skip2
                    Sorted:
                        inc edi; we move a[j]
                    Skip2:
                loop for_loop2
            End_loop:
            inc esi;we move a[i]
            pop ecx; we pop the value of ecx for the first loop
        loop for_loop
        
        mov esi, sir
        mov ecx, len
        
        for_loop3:
            pushad
            mov eax, 0
            mov al, [esi]
            push dword eax
            push format
            call [printf]
            add esp, 4*2
            popad
            inc esi
        loop for_loop3
            
        
        Skip:
    
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
