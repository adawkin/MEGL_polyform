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

public class OneSidedPolyominoe {
    int size, initialCapacity, row, column, polySize;
    int[][][] data;

    static int poly = 3;
    
    public OneSidedPolyominoe(int row, int column, int polySize){
        size = 0;
        initialCapacity = 1;
        this.row = row;
        this.column = column;
        data = new int[0][row][column];
        this.polySize = polySize;
        generatePermutations();}

    public int getSize(){
        return size;}

    //increase capacity of array
    public void increaseCapacity(){
        int[][][] data2 = new int[data.length + 1][row][column];

        System.arraycopy(data, 0, data2, 0, data.length);
        data = data2;}

    //checks if first column is empty
    //also checks if first block it comes accross is connected to poly number
    //of bricks
    public boolean firstColumnEmpty(int[][] piece){
        for (int i = 0; i < piece.length; i++){
            if (piece[i][0] == 1){
                boolean[] filledSpaces = 
                        new boolean[piece.length * piece[0].length];
                return (isConnected(piece, i, 0, filledSpaces) == polySize);}
        }

        return false;}

    //recursively checks how many bricks are connected
    public static int isConnected(int[][] piece, int y, int x,
            boolean[] filledSpaces){
        int total = 1; //total number of blocks connected to orginal block
        filledSpaces[y * piece[0].length + x] = true; //not to double check

        if (y != 0 && piece[y-1][x] == 1  //up
            && filledSpaces[(y-1) * piece[0].length + x] == false){
            total += isConnected(piece, y-1, x, filledSpaces);}
        if (y + 1 != piece.length && piece[y + 1][x] == 1 //left
            && filledSpaces[(y + 1) * piece[0].length + x] == false){
            total += isConnected(piece, y + 1, x, filledSpaces);}
        if (x != 0 && piece[y][x - 1] == 1 //right
            && filledSpaces[(y) * piece[0].length + x - 1] == false){
            total += isConnected(piece, y, x - 1, filledSpaces);}
        if (x + 1 != piece[0].length && piece[y][x + 1] == 1 //down
            && filledSpaces[(y) * piece[0].length + x + 1] == false){
            total += isConnected(piece, y, x + 1, filledSpaces);}

        return total;}

    //is the first row empty
    public static boolean firstRowEmpty(int[][] piece){
        for (int i = 0; i < piece[0].length; i++){
            if (piece[0][i] == 1){
                return false;}
        }

        return true;}

    //is last column empty
    public static boolean lastColumnEmpty(int[][] piece){
        for (int[] piece1 : piece) {
            if (piece1[piece[0].length - 1] == 1) {
                return false;}
        }

        return true;}

    //removes last column
    public static int[][] removeLastColumn(int[][] piece){
        int[][] trimmedPiece = new int[piece.length][piece[0].length - 1];

        for (int i = 0; i < piece.length; i++){
           System.arraycopy(piece[i], 0, trimmedPiece[i], 0,
                trimmedPiece[0].length);}

        return trimmedPiece;}

    //checks if last row is empty
    public static boolean lastRowEmpty(int[][] piece){
        for (int i = 0; i < piece[0].length; i++){
            if (piece[piece.length - 1][i] == 1){
                return false;}
        }

        return true;}

    //removes last row
    public static int[][] removeLastRow(int[][] piece){
        int[][] trimmedPiece = new int[piece.length - 1][piece[0].length];

        for (int i = 0; i < piece.length - 1; i++){
           System.arraycopy(piece[i], 0, trimmedPiece[i], 0,
                trimmedPiece[0].length);}

        return trimmedPiece;}

    //gets rid of extra rows and columns
    public static int[][] trim(int[][] piece){
        while (lastColumnEmpty(piece) == true){
            piece = removeLastColumn(piece);}
        while (lastRowEmpty(piece) == true){
            piece = removeLastRow(piece);}

        return piece;}

    //returns false if piece is not connected
    public boolean isValidPiece(int[][] piece){
        if (firstRowEmpty(piece) == true){//premptively removes duplicates
            return false;}
        return firstColumnEmpty(piece) != false;}

    //rotates
    public static int[][] transform(int[][] piece){
        int[][] newPiece = new int[piece[0].length][piece.length];

        for (int i = 0; i < piece.length; i++){
            for (int j = 0; j < piece[0].length; j++){
                newPiece[piece[0].length - 1 - j][i] = piece[i][j];}
        }

        return newPiece;}

    //checks if two pieces are equal
    public static boolean checkEqual(int[][] newPiece, int[][] data){
        if (newPiece.length != data.length 
            || newPiece[0].length != data[0].length){
            return false;}
        for (int i = 0; i < data.length; i++){
            for (int j = 0; j < data[0].length; j++){
                if (newPiece[i][j] != data[i][j]){
                    return false;}
            }
        }

        return true;}

    //check all previous entries for current piece
    public boolean checkPrev(int[][] newPiece){
        for (int[][] data1 : data) {
            if (checkEqual(newPiece, data1) == true) {
                return true;}
        }

        return false;}

    //checks data list for duplicates before adding new piece
    public boolean isDuplicate(int[][] piece){
        //check if piece already in list
        //int[][] newPiece = transform(piece);
        //if (checkPrev(newPiece) == true){
        //return true;} //rotates it
        //newPiece = transform(newPiece);
        //if (checkPrev(newPiece) == true){
        //return true;} //rotates again
        //newPiece = transform(newPiece);
        //return checkPrev(newPiece) == true;
        
        return checkPrev(piece) == true;} //

    //prints individual polyminoe
    public void printPiece(int[][] piece){
        for (int[] piece1 : piece) {
            for (int j = 0; j < piece[0].length; j++) {
                System.out.print(piece1[j]);}
            System.out.println();}
        System.out.println();}

    //adds piece to dynamic array
    public void add(int[][] piece){
        if (isValidPiece(piece) == false){
            return;}
        piece = trim(piece);
        //if (piece.length > piece[0].length){
            //return;} //guaranteed to be a rotated version of another piece
        if (isDuplicate(piece) == true){
            return;}
        increaseCapacity();
        data[size] = piece;
        size++;}


    //no longer use function
    public int[][] createIdenticalPiece(int[][] piece){
        int col = piece[0].length;
        int r = piece.length;
        int[][] newPiece = new int[r][col];

        for (int i = 0; i < r; i++){
            System.arraycopy(piece[i], 0, newPiece[i], 0, col);
        }

        return newPiece;
    }

    //translates permutations into 2d array
    public void generatePermutations(){
        int n = row * column; //n(Choose)polysize
        List<int[]> permutations = generate(n, polySize);

        for (int[] permutation: permutations){
            int[][] piece = new int[row][column];
            for (int i = 0; i < permutation.length; i++){
                piece[permutation[i]/column][permutation[i]%column] = 1;}
            add(piece);
            addRotate(piece);}
    }
    
    public void addRotate(int[][] piece){
        int[][] rotate = transform(piece);
        add(rotate);
        rotate = transform(rotate);
        add(rotate);
        rotate = transform(rotate);
        add(rotate);
    }

    //prints the entire array of pieces
    public void printArray(){
        for (int[][] data1 : data) {
            printPiece(data1);}
    }

    //generates the list of permutations, not mine
    public static List<int[]> generate(int n, int r) {
        List<int[]> combinations = new ArrayList<>();
        helper(combinations, new int[r], 0, n-1, 0);

        return combinations;}

    //stolen from the internet, not mine
    private static void helper(List<int[]> combinations, int data[], int start,
        int end, int index) {
        if (index == data.length) {
            int[] combination = data.clone();
            combinations.add(combination);}
        else if (start <= end) {
            data[index] = start;
            helper(combinations, data, start + 1, end, index + 1);
            helper(combinations, data, start + 1, end, index);}
    }
    
    private double[][] buildTransitionMatrix(){
        double[][] matrix =new double[size][size];
        for (int i = 0; i < matrix.length; i++){
            matrix[i] = buildMatrixRow(i);
            //System.out.println(matrix[i][i]);
        }
        
        return matrix;}
    
    private double[] buildMatrixRow(int x){
        double[] row = new double[size];
        double[] numerators = new double[size];
        double denomenator = 0;
        int[][] tempPiece = createBorder(data[x]);
        int[] hits = findHits(tempPiece);
        
        for (int i = 0; i < poly; i++){
            int[][] newPiece = createIdenticalPiece(tempPiece);
            newPiece[hits[i] / newPiece[0].length][hits[i] % newPiece[0].length] = 0;
            for (int j = 0; j < newPiece.length; j++){
                for (int k = 0; k < newPiece[0].length; k++){
                    if (isPerimeter(newPiece, j, k)){
                        int[][] checkPiece = createIdenticalPiece(newPiece);
                        checkPiece[j][k] = 1;
                        checkPiece = removeBorder(checkPiece);
                        if (checkPrev(checkPiece)){
                            numerators[findIndexPiece(checkPiece)] += 1;}
                        else{
                            numerators[x] += 1;}
                        denomenator += 1;
                    }
                }
            }
            for (int index = 0; index < size; index++){
                row[index] += (numerators[index] / (denomenator * poly));
                numerators[index] = 0;}
            denomenator = 0;
        }
        
        return row;}
    
    private int findIndexPiece(int[][] checkPiece){
        for (int i = 0; i < data.length; i++) {
            if (checkEqual(checkPiece, data[i]) == true) {
                return i;}
        }
    return -1;}
    
    private int[][] removeBorder(int[][] newPiece){
        int[][] checkPiece = trim(newPiece);
        while (firstRowEmpty(checkPiece)){
            checkPiece = removeFirstRow(checkPiece);}
        while (isFirstColumnEmpty(checkPiece)){
            checkPiece = removeFirstColumn(checkPiece);}
        
    return checkPiece;}
    
    private int[][] removeFirstColumn(int[][] checkPiece){
        int[][] piece = new int[checkPiece.length][checkPiece[0].length - 1];
        
        for (int i = 0; i < checkPiece.length; i++){
            for (int j = 1; j < checkPiece[0].length; j++){
                piece[i][j - 1] = checkPiece[i][j];
            }
        }
        
        return piece;
    }
    
    private boolean isFirstColumnEmpty(int[][] piece){
        for (int i = 0; i < piece.length; i++){
            if (piece[i][0] == 1){
                return false;}
        }

        return true;}
    
    private int[][] removeFirstRow(int[][] checkPiece){
        int[][] piece = new int[checkPiece.length - 1][checkPiece[0].length];
        
        for (int i = 1; i < checkPiece.length; i++){
            for (int j = 0; j < checkPiece[0].length; j++){
                piece[i - 1][j] = checkPiece[i][j];
            }
        }
        
        return piece;
    }
    
    private boolean isPerimeter(int[][] newPiece, int j, int k){
        //System.out.println("j is " + j + " k is " + k);
        if (newPiece[j][k] == 1){
            return false;}
        if (j > 0 && newPiece[j - 1][k] == 1){
            return true;}
        if (k > 0 && newPiece[j][k - 1] == 1){
            return true;}
        if (j < newPiece.length - 1 && newPiece[j + 1][k] == 1){
            return true;}
        if (k < newPiece[0].length - 1 && newPiece[j][k + 1] == 1){
            return true;}
        return false;}
    
    private int[] findHits(int[][] tempPiece){
        int hits[] = new int[poly];
        int counter = 0;
        
        for (int i = 1; i < tempPiece.length - 1; i++){
            for (int j = 1; j < tempPiece[0].length - 1; j++){
                if (tempPiece[i][j] == 1){
                    hits[counter] = i * tempPiece[0].length + j;
                    counter++;}
            }
        }
        
        return hits;}
    
    private int[][] createBorder(int[][] piece){
        int[][] tempPiece = new int[piece.length + 2][piece[0].length + 2];
        for (int i = 0; i < piece.length; i++){
            for (int j = 0; j < piece[0].length; j++){
                tempPiece[i+ 1][j+1] = piece[i][j];
            }
        }
        return tempPiece;
    }
    
        public static void main(String[] args) {

        //Creates array in chunks to not overload the system with permutations
        OneSidedPolyominoe main = new OneSidedPolyominoe(1, poly, poly);
        for (int i = 1; i < poly; i++){
            OneSidedPolyominoe sub = new OneSidedPolyominoe(1 + i, poly - i, poly);
            for (int j = 0; j < sub.size; j++){
                    main.add(sub.data[j]);}
            }

        main.printArray();
        double[][] matrix = main.buildTransitionMatrix();
        for (int i = 0; i < matrix.length; i++){
            for (int j = 0; j < matrix[0].length; j++){
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println(" ");}
        
        }
}
