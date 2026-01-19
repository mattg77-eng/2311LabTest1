module top_module (
    input CLOCK_50,
    input [1:0] KEY,
    input SW0,
    output [6:0] HEX0,
    output [6:0] HEX1
);

    wire clk_1hz;

    ClockDivider u_div (
        .cin(CLOCK_50),
        .cout(clk_1hz)
    );

    shot_clock u_clock (
        .clk_1hz(clk_1hz),
        .reset_n(KEY[0]),
        .pause_n(KEY[1]),
        .mode(SW0),
        .ones(HEX0),
        .tens(HEX1)
    );
endmodule

