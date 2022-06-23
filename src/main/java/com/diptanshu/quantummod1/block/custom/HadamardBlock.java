package com.diptanshu.quantummod1.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import java.lang.Math;

public class HadamardBlock extends Block {
    public HadamardBlock(Properties properties) {
        super(properties);
    }

    double k = 1/(Math.sqrt(2));
    double[][] hadamardMatrix = { {k, k}, {k, -k}};

    public static double[] qstate = QubitBlock.stateVector;

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {

        BlockPos positionClicked = pContext.getClickedPos();
        Player player = pContext.getPlayer();

        /**
        Block surroundingBlock = pContext.getLevel().getBlockState(positionClicked.north(1)).getBlock();

        int count = 2;
        while (!(surroundingBlock instanceof QubitBlock)) {
            surroundingBlock = pContext.getLevel().getBlockState(positionClicked.north(count)).getBlock();
            count += 1;
        }

        if (surroundingBlock instanceof QubitBlock) {
            player.sendMessage(new TextComponent("Hello"), player.getUUID());
            QubitBlock newBloch = QubitBlock.class.cast(surroundingBlock);
            qstate = matrixMult(hadamardMatrix, qstate);
            player.sendMessage(new TextComponent((Math.pow(qstate[0],2)*100) + "% 0  AND  " + (Math.pow(qstate[1],2)*100) + "% 1"), player.getUUID());
        }
         */

        qstate = matrixMult(hadamardMatrix, qstate);
        player.sendMessage(new TextComponent((Math.pow(qstate[0],2)*100) + "% 0  AND  " + (Math.pow(qstate[1],2)*100) + "% 1"), player.getUUID());

        return super.getStateForPlacement(pContext);
    }

    public double[] scalarMult(double k, double[] vector) {
        double[] resultant = new double[vector.length];
        for (int i=0; i<vector.length; i++){
            resultant[i] = k*vector[i];
        }
        return resultant;
    }

    public double[] matrixMult(double[][] matrix, double[] vector) {
        double[] resultant = new double[vector.length];

        for (int i=0; i<matrix.length; i++) {
            double tempSum = 0.0;
            for (int j=0; j<matrix[0].length; j++) {
                tempSum += matrix[i][j]*vector[j];
            }
            resultant[i] = tempSum;
        }

        return resultant;
    }

    public static double[] returnQState() {
        return qstate;
    }
}
