/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fixedpolyominoeproject;

/**
 *
 * @author james
 */

import java.util.*;
import java.lang.*;

public class PolyominoePieceDetails {
    char[][] shape;
    int[] hits;
    int row, column;
    static char BLANK = 'b';
    static char HIT = 'H';
    static char CORNER = 'c';
    static char OP = 'o';
    static char IP = 'i';
    
    public PolyominoePieceDetails(int row, int column, int[] hits){
        shape = new char[row][column];
        this.hits = hits;
        this.row = row;
        this.column = column;
        formShape();
        updateShape();
        findOuterPerimeter(0, 0);
    }
    
    //determines outer perimeter with non perimeter
    public boolean isBorderingHit(int x, int y){
        if (y != 0 && shape[y - 1][x] == HIT){
            return true;}
        if (x != 0 && shape[y][x - 1] == HIT){
            return true;}
        if (x != shape[0].length - 1 && shape[y][x + 1] == HIT){
            return true;}
        if (y != shape.length - 1 && shape[y + 1][x] == HIT){
            return true;}
        return false;}
    
    
    //recursively go through outer perimeter to find hits
    public void findOuterPerimeter(int x, int y){
        if (shape[y][x] == BLANK){
            shape[y][x] = CORNER;
            if (isBorderingHit(x, y) == true){
                shape[y][x] = OP;
            }
            if (y != 0 && shape[y - 1][x] == BLANK){
                findOuterPerimeter(x, y - 1);}
            if (x != 0 && shape[y][x - 1] == BLANK){
                findOuterPerimeter(x - 1, y);}
            if (x != shape[0].length - 1 && shape[y][x + 1] == BLANK){
                findOuterPerimeter(x + 1, y);}
            if (y != shape.length - 1 && shape[y + 1][x] == BLANK){
                findOuterPerimeter(x, y + 1);}

        }
        
    }
    
    //start with a blank canvas, add hits later
    public char[][] createBlankShape(char[][] shape){
        for (int i = 0; i < shape.length; i++){
            for (int j = 0; j < shape[0].length; j++){
                shape[i][j] = BLANK;}
        }
    return shape;}
    
    //updates shape to add a row and column buffer below and above
    public void updateShape(){
        char[][] newShape = new char[row + 2][column + 2];
        newShape = createBlankShape(newShape);
        for (int k = 0; k < hits.length; k++){
            newShape[hits[k]/column + 1][hits[k]%column + 1] = HIT;}
        shape = newShape;
        row += 2;
        column += 2;
    }
    
    //forms original polyminoe shape from hits array
    public void formShape(){
        shape = createBlankShape(shape);
        
        for (int k = 0; k < hits.length; k++){
            shape[hits[k]/column][hits[k]%column] = HIT;}
    }
    
    //prints shape
    public void printShape(){
        for (int i = 0; i < row; i++){
            System.out.println(shape[i]);
        }
    }
    
    public static void main(String[] args) {
        int[] hits = { 1, 4 , 6 ,7 ,8, 9, 11, 12, 13, 14};
        PolyominoePieceDetails test = new PolyominoePieceDetails(5, 3, hits);
        test.printShape();
        
    }
}
