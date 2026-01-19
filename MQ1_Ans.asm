c1: DC "Student number: 220521654"
b1: DC "Input a float:" 
b2: DC "Result="

	addi x1, x0, c1
	ecall x0, x1, 4
	addi x12, x0, b2
        addi x2, x0, b1
        ecall x0, x2, 4
	ecall f3, x0, 6
	;ecall f1, x0, 6
	;ecall f2, x0, 6
	
loop1: flt.d x3, f3, f2




end: 	ecall x0, x12, 4
	ecall x0, f3, 1
	

