module top (
    input  clk,
    input  start,
    input  reaction_btn,
    output reg led,
    output [6:0] hex0,
    output [6:0] hex1,
    output [6:0] hex2
);

    reg [18:0] clkdiv = 0;
    reg tick = 0;

    always @(posedge clk) begin
        if (clkdiv == 19'd499_999) begin
            clkdiv <= 0;
            tick <= 1;
        end else begin
            clkdiv <= clkdiv + 1;
            tick <= 0;
        end
    end

    wire [7:0] rand_val;
    lfsr rng (.clk(clk), .out(rand_val));

    localparam S_IDLE   = 0,
               S_WAIT   = 1,
               S_LIGHT  = 2,
               S_DONE   = 3;

    reg [1:0] state = S_IDLE;

    reg [15:0] wait_cnt = 0;
    reg [15:0] reaction_cnt = 0;
    reg [15:0] reaction_final = 0;

    always @(posedge clk) begin
        case (state)

            S_IDLE: begin
                led <= 0;
                if (start) begin
                    wait_cnt <= 16'd200 + (rand_val % 16'd201);
                    state <= S_WAIT;
                end
            end

            S_WAIT: begin
                led <= 0;

                if (tick) begin
                    if (wait_cnt > 0)
                        wait_cnt <= wait_cnt - 1;
                    else begin
                        reaction_cnt <= 0;
                        state <= S_LIGHT;
                    end
                end
            end

            S_LIGHT: begin
                led <= 1;

                if (reaction_btn) begin
                    reaction_final <= reaction_cnt;
                    state <= S_DONE;
                end else if (tick) begin
                    reaction_cnt <= reaction_cnt + 1;  
                end
            end

            S_DONE: begin
                led <= 0;
                if (start) begin
                    state <= S_IDLE;
                end
            end

        endcase
    end

    reg [3:0] d0, d1, d2;

    always @(*) begin
        d0 = (reaction_final % 10);
        d1 = (reaction_final / 10) % 10;
        d2 = (reaction_final / 100) % 10;  
    end

    sevenseg s0(d0, hex0);
    sevenseg s1(d1, hex1);
    sevenseg s2(d2, hex2);

endmodule




