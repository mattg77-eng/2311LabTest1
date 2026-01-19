module shot_clock (
    input clk_1hz,  
    input reset_n, 
    input pause_n,   
    input mode,
    output reg [6:0] ones,
    output reg [6:0] tens
);

    reg [5:0] count_val;
    reg paused;

    initial begin
        count_val = 24;
        paused = 0;
    end

    always @(posedge clk_1hz or negedge reset_n) begin
        if (!reset_n)
            count_val <= (mode) ? 30 : 24;
        else if (!paused) begin
            if (count_val > 0)
                count_val <= count_val - 1;
        end
    end

    always @(negedge pause_n)
        paused <= ~paused;

    reg [3:0] ones_digit;
    reg [3:0] tens_digit;

    always @(*) begin
        tens_digit = count_val / 10;
        ones_digit = count_val - (tens_digit * 10);
    end

    always @(*) begin
        case (ones_digit)
            4'd0: ones = 7'b1000000;
            4'd1: ones = 7'b1111001;
            4'd2: ones = 7'b0100100;
            4'd3: ones = 7'b0110000;
            4'd4: ones = 7'b0011001;
            4'd5: ones = 7'b0010010;
            4'd6: ones = 7'b0000010;
            4'd7: ones = 7'b1111000;
            4'd8: ones = 7'b0000000;
            4'd9: ones = 7'b0010000;
            default: ones = 7'b1111111;
        endcase
    end

    always @(*) begin
        case (tens_digit)
            4'd0: tens = 7'b1000000;
            4'd1: tens = 7'b1111001;
            4'd2: tens = 7'b0100100;
            4'd3: tens = 7'b0110000;
            default: tens = 7'b1111111;
        endcase
    end
endmodule

