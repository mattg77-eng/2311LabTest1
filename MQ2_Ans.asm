c1: DC "Monday Lab Section"
c2: DC "Input:"
c3: DC "Factorial="


	addi x1, x0, c1
	ecall x0, x1, 4
	addi x2, x0, c2
	ecall x0, x2, 4
	ecall x10, x0, 5
	addi x15, x0, c3
	
	addi x12, x0, 1
	add x9, x0, x10

recufact:   sub x5, x10, x12
	addi x12, x12, 1
	beq x5, x0, end
	mul x7, x9, x5
	add x9, x0, x7
	jal x0, recufact

end:   
	ecall x0, x15, 4
	ecall x0, x7, 0
	ebreak x0, x0, 0