module labN;
    wire [31:0] PCin, PC;
    reg RegWrite, clk, ALUSrc, MemRead, MemWrite, Mem2Reg, INT, isbranch, isjump;
    reg [2:0] op;
    reg [31:0] entryPoint;

    wire [31:0] wd, rd1, rd2, imm, ins, PCp4, z, branch;
    wire [31:0] jTarget;
    wire [31:0] memOut, wb;
    wire zero;

    yIF myIF(ins, PC, PCp4, PCin, clk);
    yID myID(rd1, rd2, imm, jTarget, branch, ins, wd, RegWrite, clk);
    yEX myEx(z, zero, rd1, rd2, imm, op, ALUSrc);
    yDM myDM(memOut, z, rd2, clk, MemRead, MemWrite);
    yWB myWB(wb, z, memOut, Mem2Reg);
    yPC myPC(PCin, PC, PCp4, INT, entryPoint, branch, jTarget, zero, isbranch, isjump);

    assign wd = wb;

    initial
    begin
        //------------------------------------Entry point
        INT = 1;
        entryPoint = 16'h28; #1;
        //------------------------------------Run program
        repeat (43)
        begin
            clk = 1; #1;
            INT = 0;
            isjump = 0;
            isbranch = 0;
            RegWrite = 0;
            ALUSrc = 1;
            op = 3'b010;
            MemRead = 0;
            MemWrite = 0;
            Mem2Reg = 0;


            if (ins[6:0] == 7'h33) 
            begin
                ALUSrc = 0;
                RegWrite = 1;
                op = 3'b010;
                MemRead = 0;
                MemWrite = 0;
                Mem2Reg = 0;
                if(ins[14:12] == 3'b110) 
                    op = 3'b001;
        
            end
            else if (ins[6:0] == 7'h6F) 
            begin
                RegWrite = 1;
                ALUSrc = 1; 
                MemRead = 0;
                MemWrite = 0;
                isjump = 1;
            end
            else if (ins[6:0] == 7'h3) 
            begin
                ALUSrc = 1;
                RegWrite = 1;
                MemRead = 1;
                MemWrite = 0;
                Mem2Reg = 1;
            end
            else if (ins[6:0] == 7'h13)
            begin
             
                ALUSrc = 1;
                RegWrite = 1;
                MemRead = 0;
                MemWrite = 0;
                Mem2Reg = 0;
            end
            else if (ins[6:0] == 7'h23) 
            begin
                ALUSrc = 1;
                RegWrite = 0;
                MemRead = 0;
                MemWrite = 1;
          
            end
            else if (ins[6:0] == 7'h63) 
            begin
              
                ALUSrc = 0;
                RegWrite = 0;
                op = 3'b110; 
                MemRead = 0;
                MemWrite = 0;
                isbranch = 1;
          
            end
            
            clk = 0; #1;
      
            #4 $display("%8h: rd1=%d rd2=%d imm=%d jTarget=%d z=%d zero=%b wb=%d", ins, rd1, rd2, imm, jTarget, z, zero, wb);

        end
        $finish;
    end
endmodule
