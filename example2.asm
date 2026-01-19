c1: DC "Friday Lab Section"
c2: DC "Input1:"
c3: DC "Input2:"
c4: DC "GCD:"

    addi x30, x0, c1
    ecall x0, x30, 4
    addi x31, x0, c2
    ecall x0, x31, 4
    ecall x1, x0, 5
    addi x28, x0, c3
    ecall x0, x28, 4
    ecall x2, x0, 5
    addi x12, x0, 0


gcd: beq x2, x0, end2
     rem x8, x1, x2 ;51    (gcd x1, 0)
     addi x12, x8, 0
     addi x1, x2, 0
     addi x2, x12, 0
     beq x12, x0, end
     jal x0, gcd


end: addi x6, x0, c4
     ecall x0, x6, 4
     ecall x0, x1, 0 ;where blank is the register that holds the result of the GCD
     ebreak x0, x0, 0

end2: ecall x0,x1, 0
      ebreak x0, x0, 0
