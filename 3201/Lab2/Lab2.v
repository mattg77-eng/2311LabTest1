module Lab2(num, seg);

input [3:0] num;
output [6:0] seg;

wire w,x,y,z;

assign w = num[3]; 
assign x = num[2];
assign y = num[1];
assign z = num[0];

assign seg[0] = (~w&~x&~y&z | ~w&x&~y&~z | w&~x&y&z | w&x&~y&z);
assign seg[1] = (w&y&z | x&y&~z | w&x&~z | ~w&x&~y&z);
assign seg[2] = (w&x&y | w&x&~z | ~w&~x&y&~z);
assign seg[3] = (~w&~x&~y&z | ~w&x&~y&~z | x&y&z | w&~x&y&~z);
assign seg[4] = (~w&z | ~x&~y&z | ~w&x&~y);
assign seg[5] = (~w&~x&z | ~w&~x&y | ~w&y&z | w&x&~y&z);
assign seg[6] = (~w&~x&~y | ~w&x&y&z | w&x&~y&~z);

endmodule