package com.juliettegonzalez.breakthroughapp.AI;

import android.util.Log;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by juliettegonzalez on 03/01/2017.
 */

public class Matrix{

    private int[][] whiteBoard;
    private int[][] blackBoard;
    private boolean currentPlayer;

    public Matrix(){
        int[][] whiteB = {{1,1,1,1,1,1,1,1},{1,1,1,1,1,1,1,1},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0}};
        int[][] blackB = {{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},{1,1,1,1,1,1,1,1},{1,1,1,1,1,1,1,1}};
        whiteBoard = whiteB;
        blackBoard = blackB;
        currentPlayer = false;
    }

    //constructeur de recopie
    public Matrix(Matrix mat){
        int[][] whiteB = new int[8][8];
        int[][] blackB = new int[8][8];
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                whiteB[i][j] = mat.getMatrix(true)[i][j];
                blackB[i][j] = mat.getMatrix(false)[i][j];
            }
        }
        whiteBoard = whiteB;
        blackBoard = blackB;
        if(mat.isComputerAI()){
            currentPlayer = true;
        }else{
            currentPlayer = false;
        }
    }

    public int toBitboard(int[][] board){
        byte[] array = new byte[64];

        for(int i = 0; i < 7; i++) {
            for(int j = 0; j < 7; j++){
                array[j+i*8] = (byte) board[i][j];
            }
        }
        ByteBuffer wrapper = ByteBuffer.wrap(array);
        int num = wrapper.getInt();
        return num;
    }

    public int[][] getMatrix(boolean player){
        if(player){
            return whiteBoard;
        }else{
            return blackBoard;
        }
    }

    public int getNumberPawns(boolean player){
        int[][] board = getMatrix(player);
        int result = 0;
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(board[i][j]==1){
                    result++;
                }
            }
        }
        return result;
    }

    public int[][] getPawns(boolean player){
        int pawns = getNumberPawns(player);
        int[][] board = getMatrix(player);
        int[][] result = new int[pawns][2];
        int index = 0;
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(board[i][j]==1){
                    result[index][0]=i;
                    result[index][1]=j;
                    index++;
                }
            }
        }
        return result;
    }

    public boolean isComputerAI(){
        return currentPlayer;
    }


    public void changePlayer(){
        currentPlayer = !currentPlayer;
    }


    public boolean validMove(int[][] move, boolean straight){
        //débordement en Y
        if((move[1][0] < 0)||(7 < move[1][0])){return false;}
        //pion personnel déjà présent
        int[][] board = getMatrix(currentPlayer);
        if(board[move[1][0]][move[1][1]] == 1){return false;}
        //si tout droit, pas de pion adverse
        if(straight){
            board = getMatrix(!currentPlayer);
            if(board[move[1][0]][move[1][1]] == 1){return false;}
        }
        return true;
    }

    public void applyMove(int[][] move){
        if(move==null){
            return;
        }
        int[][] board = getMatrix(currentPlayer);
        board[move[0][0]][move[0][1]] = 0;
        board[move[1][0]][move[1][1]] = 1;
        board = getMatrix(!currentPlayer);
        if(board[move[1][0]][move[1][1]] == 1){
            board[move[1][0]][move[1][1]] = 0;
        }
    }


    public double analyze(){
        //TODO : heuristique, appréciation de la position
        //Heuristique actuelle très naive
        if(winningPosition()){
            if(winner()==currentPlayer) {
                //Log.d("DEBUG", "Computer winning");
                return 1000.0;
            }else {
                //Log.d("DEBUG", "Player winning");
                return -1000.0;
            }
        }else {
            double score = 0.0;
            score += getNumberPawns(currentPlayer);
            score -= getNumberPawns(!currentPlayer);
            //score += Math.random()-0.5
            if(0 < score){
                //Log.d("DEBUG","Computer stronger");
            }else if(score < 0){
                //Log.d("DEBUG","Player stronger");
            }
            return score;
        }
    }

    public boolean winningPosition() {
        for(int j = 0; j < 8; j++){
            if((whiteBoard[7][j]==1)||(blackBoard[0][j]==1)) {
                return true;
            }
        }
        if(getNumberPawns(true) == 0 || getNumberPawns(false) == 0){
            return(true);
        }
        return false;
    }

    //on suppose que l'on sait déjà qu'il y a un vainqueur
    public boolean winner() {
        for(int j = 0; j < 8; j++){
            if(whiteBoard[7][j]==1) {
                return true;
            }else if(blackBoard[0][j]==1){
                return false;
            }
        }
        if(getNumberPawns(true) == 0){
            return false;
        }else{
            return true;
        }
    }

}
