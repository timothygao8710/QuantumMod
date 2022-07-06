package com.diptanshu.quantummod2.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class QubitBlock extends Block {

    public QubitBlock(Properties properties) {
        super(properties);
    }

    //HadamardBlock hadamardGate = new HadamardBlock(false, properties);
    //NotBlock notGate = new NotBlock(false, properties);
    public double alpha = 1.0;
    public double beta = 0.0;
    public double[] stateVector = {alpha, beta};

    /*
    public void findGate (BlockPlaceContext pContext, BlockPos positionClicked) {
        Block thisBlock = pContext.getLevel().getBlockState(positionClicked.north(1)).getBlock();

        if (thisBlock instanceof HadamardBlock) {
            stateVector = updateState(stateVector, hadamardGate.getMatrix());
        } else if (thisBlock instanceof NotBlock) {
            stateVector = updateState(stateVector, notGate.getMatrix());
        }
    }
     */

    public static void updateStateVector(double[][] gateMatrixInput) {
        //quantumState = matrixMult(quantumState, gateMatrixInput);
        //stateVector[0] = 0;
        //stateVector[1] = 1;
    }

    public static double[] matrixMult(double[] vector, double[][] matrix) {
        double[] resultant = new double[vector.length];

        /**
        for (int i=0; i<matrix.length; i++) {
            double tempSum = 0.0;
            for (int j=0; j<matrix[0].length; j++) {
                tempSum += matrix[i][j]*vector[j];
            }
            resultant[i] = tempSum;
        }
         */

        resultant[0] = matrix[0][0]*vector[0] + matrix[0][1]*vector[1];
        resultant[1] = matrix[1][0]*vector[0] + matrix[1][1]*vector[1];

        return resultant;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        BlockPos positionClicked = pContext.getClickedPos();
        Player player = pContext.getPlayer();
        outputQuantumState(stateVector, player);
        return super.getStateForPlacement(pContext);
    }

    public static void outputQuantumState(double[] quantumState, Player player) {
        player.sendMessage(new TextComponent((quantumState[0]) + " |0>   AND   " + (quantumState[1]) + " |1>"), player.getUUID());
    }

    /**
    public static double[] scalarMult(double k, double[] vector) {
        double[] resultant = new double[vector.length];
        for (int i=0; i<vector.length; i++){
            resultant[i] = k*vector[i];
        }
        return resultant;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        BlockPos positionClicked = pContext.getClickedPos();
        Player player = pContext.getPlayer();
        outputQuantumState(player);
        findGate(pContext, positionClicked);
        return super.getStateForPlacement(pContext);
    }
    */
}