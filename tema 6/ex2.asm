bits 32

;A file name and a text (defined in the data segment) are given. The text contains lowercase letters, uppercase letters, digits and special characters. Transform all the uppercase letters from the given text in lowercase. Create a file with the given name and write the generated text to file.

global start

extern exit, fopen, fclose, fprintf

import exit msvcrt.dll
import fopen msvcrt.dll
import fclose msvcrt.dll
import fprintf msvcrt.dll

segment data use32 class=data
    ;...
    file_name db "Random.txt", 0
    dif db 'a'-'A' ; for transorming a letter from upper case to lower case
    access_mode db "w", 0; w - create an empty file for writing
    file_descriptor dd -1       ; variable to hold the file descriptor
    text db "The 1 coNTenT Is RAndoM. ;).", 0
    len equ $ - text ; the length of the text
    
segment code use32 class=code
    start:
        ;...
        
        mov ecx, len
        JECXZ Skip ; skip if the string is empty
        
        mov esi, text
        
        MY_LOOP:
            mov al, [esi]
            ;see if the character is an uppercase one
            cmp al, 'A'
            jb NO ;if it lower than A
            cmp al, 'Z'
            ja NO ; if it is grater than Z
            add al, byte [dif] ; transform the letter
            mov [esi], al
            NO:
            inc esi
        loop MY_LOOP
       
        Skip:
        
        
        ;creating a file
        ;call fopen() to create the file
        ;fopen() will return a file descriptor in the eax or 0 in case of error
        ;eax = fopen(file_name, access_mode)
        
        push dword access_mode
        push dword file_name
        call [fopen] ; open the file
        add esp, 4*2
        
        mov [file_descriptor], eax  ; store the file descriptor returned by fopen
        
        ; check if fopen() has successfully created the file (EAX != 0)
        cmp eax, 0
        je final
        
        ; fprintf(file_descriptor, text)
        push dword text
        push dword [file_descriptor]
        call [fprintf] ; print the text into the file
        add esp, 4*2
        
        push dword [file_descriptor]
        call [fclose] ; close the file
        add esp, 4
        
        final:
        
        ;exit(0)
        push dword 0
        call [exit]