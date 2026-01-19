module Lab2(num, seg);

input [3:0] num;
output [6:0] seg;

wire w,x,y,z;
wire w_bar,x_bar,y_bar,z_bar;

assign w = num[3]; 
assign x = num[2];
assign y = num[1];
assign z = num[0];
assign w_bar = ~w;
assign x_bar = ~x;
assign y_bar = ~y;
assign z_bar = ~z;

assign seg[0] = (w_bar&x_bar&y_bar&z | w_bar&x&y_bar&z_bar | w&x_bar&y&z | w&x&y_bar&z);
assign seg[1] = (w&y&z | x&y&z_bar | w&x&z_bar | w_bar&x&y_bar&z);
assign seg[2] = (w&x&y | w&x&z_bar | w_bar&x_bar&y&z_bar);
assign seg[3] = (w_bar&x_bar&y_bar&z | w_bar&x&y_bar&z_bar | x&y&z | w&x_bar&y&z_bar);
assign seg[4] = (w_bar&z | x_bar&y_bar&z | w_bar&x&y_bar);
assign seg[5] = (w_bar&x_bar&z | w_bar&x_bar&y | w_bar&y&z | w&x&y_bar&z);
assign seg[6] = (w_bar&x_bar&y_bar | w_bar&x&y&z | w&x&y_bar&z_bar);

endmodule