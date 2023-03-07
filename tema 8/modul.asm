bits 32 ; assembling for the 32 bits architecture

;concat(result, s1, s2)
;will return result

global concat

;data
segment data use32 class=data
    s1 dd 0  ; store here the start address for each of the strings
    s2 dd 0
    result dd 0
    space dd " ", 0
;code
segment code use32 class=code
concat:
    ; [esp] is the return address that was pushed by the call instruction
    mov eax, [esp + 4] ; first parameter is the last one pushed onto the stack
    mov [result], eax
    mov eax, [esp + 8] ; each subsequent parameter is 4 bytes "lower" in the stack
    mov [s1], eax
    mov eax, [esp + 12]
    mov [s2], eax
    
    mov esi, [s1]
    mov edi, [result]
    .loop_s1:
        lodsb ;al = esi, inc esi
        cmp al, 0 ; store all characters until \n
        je .done_s1
        stosb ; edi = al, inc edi
    jmp .loop_s1
    .done_s1:
    
    mov esi, [s2]
    .loop_s2:
        lodsb
        stosb  ; store the first \n
        cmp al, 0
        je .done_s2
    jmp .loop_s2
    .done_s2:
    ret ;pops the return address from the stack and moves the execution point to that location