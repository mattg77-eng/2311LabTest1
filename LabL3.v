module LabL3;
integer i, j, k;
parameter SIZE = 32;
wire [SIZE-1:0] z;
reg c;
reg[SIZE-1:0] a,b; 


yMux M[SIZE-1:0](z,a,b,c);

initial
begin
	repeat(100)
	begin
		a = $random;
		b = $random;
		c= $random % 2;
		#1;
		if(c === 1 && z === b)
		$display("PASS a=%b b=%b c=%b z=%b", a, b, c, z);
		else if(c === 0 && z === a) 
		$display("PASS a=%b b=%b c=%b z=%b", a, b, c, z);
		else
		$display("FAIL! a=%b b=%b c=%b z=%b", a, b, c, z );
end

$finish;
end
endmodule
