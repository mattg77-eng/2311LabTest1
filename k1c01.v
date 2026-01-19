module testbench;
	reg [31:0] instruction;
	initial begin
	$display("Time: ", $time, ", Instruction: ", instruction);
	#10 instruction = 10;
	$display("Time: %5d Instruction (Decimal): %5d, Instruction (Binary): %16b, Instruction (Hex): %16h", $time, instruction, instruction, instruction);
	#10 instruction = 20;
	$display("Time: %5d Instruction (Decimal): %5d, Instruction (Binary): %16b, Instruction (Hex): %16h", $time, instruction, instruction, instruction);
	#10 instruction = 30;
	$display("Time: %5d Instruction (Decimal): %5d, Instruction (Binary): %16b, Instruction (Hex): %16h", $time, instruction, instruction, instruction);
	$finish;
  end
endmodule
