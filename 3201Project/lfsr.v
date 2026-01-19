module lfsr(
    input clk,
    output reg [7:0] out = 8'hA5
);
    wire feedback;
    assign feedback = out[7] ^ out[5] ^ out[4] ^ out[3];

    always @(posedge clk) begin
        out <= {out[6:0], feedback};
    end
endmodule



