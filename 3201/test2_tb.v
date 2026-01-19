`timescale 1ns/1ps

module test2_tb;

    reg clk;
    reg a;
    wire [3:0] r;

    test2 dut (
        .clk(clk),
        .a(a),
        .r(r)
    );

    initial begin
        clk = 1'b0;
    end

    always #10 clk = ~clk;

    initial begin
        a = 1'b0;   
        #100;
        a = 1'b1;  
        #200;
        a = 1'b0;   
        #200;     
        $finish;
    end

    always @(posedge clk) begin
        if (r == 4'b0000) begin
            $display("All outputs are zero at time %0t, r = %b", $time, r);
        end
    end

endmodule

