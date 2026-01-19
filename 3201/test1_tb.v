`timescale 1ns/1ps

module test1_tb;

    reg a, b, c;
    wire x, y;

    test1 dut (
        .a(a),
        .b(b),
        .c(c),
        .x(x),
        .y(y)
    );

    always @(x or y) begin
        if (x == 1'b0 && y == 1'b0) begin
            $display("All outputs are zero");
        end
    end

    integer i;
    initial begin
        {a, b, c} = 3'b000;

        for (i = 0; i < 8; i = i + 1) begin
            {a, b, c} = i[2:0]; 
            #20;                 
        end

        #20;
        $finish;
    end

endmodule

