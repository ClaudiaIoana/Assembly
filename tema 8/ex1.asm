bits 32                      
segment code use32 public code
global one_word

one_word:
    push dword text
    push dword format_space
    call [printf]
    add esp, 4*2
