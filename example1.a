c1: 	DC "Student's number: 220584159"
c2: 	DC "Input an integer:"
c3:     DC "Result:"
	

        addi x30, x0, c1 ;x30 has c1 now
        addi x29, x0, c2 ;x29 has c2 now
      
        ecall x0, x30, 4 ;print out c1
        ecall x0, x29, 4 ;print out c2
        ecall x1, x0, 5  ;Ask for user input, place in x1


        addi x7, x0, 1 ; set x7 = 1
        addi x3, x0, 0 ; set x3 = 0
        addi x4, x0, 0 ; set x4 = 0
        addi x9, x1, 1 ; set x9 = x1 + 1
      
loop1: addi x8, x0, 1

loop2: mul x3, x7, x8
       add x4, x3, x4
       addi x8, x8, 1
       blt x8, x1, loop2
       addi x4, x4, 10
       addi x7, x7, 1
       blt x7, x9, loop1
       beq x7, x9, end

end:  addi x28, x0, c3
      ecall x0, x28, 4
      ecall x0, x4, 0
      ebreak x0, x0, 0
