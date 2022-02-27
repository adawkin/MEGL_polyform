/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author James Serrano
 */
import java.util.*;
import java.lang.*;

public class FixedPolyominoeArray {
    
    int size, initialCapacity, row, column, polySize;
    int[][][] data;
    
    //static int poly = 7;
    
    public FixedPolyominoeArray(int row, int column, int polySize){
        size = 0;
        initialCapacity = 1;
        this.row = row;
        this.column = column;
        data = new int[1][row][column];
        this.polySize = polySize;
        generatePermutations();
		}
    
    public int getSize(){
        return size;
		}
    
    //increase capacity of array
    public void increaseCapacity(){
        int[][][] data2 = new int[2*data.length][row][column];
        
        System.arraycopy(data, 0, data2, 0, data.length);
        data = data2;
		}
    
    //checks if first column is empty
    //also checks if first block it comes accross is connected to poly number
    //of bricks
    public boolean firstColumnEmpty(int[][] piece){

        for (int i = 0; i < piece.length; i++){
            if (piece[i][0] == 1){
                boolean[] filledSpaces = new boolean[piece.length * piece[0].length];
                return (isConnected(piece, i, 0, filledSpaces) == polySize);
						}
        }
        
        return false;
		}
    
    //recursively checks how many bricks are connected
    public static int isConnected(int[][] piece, int y, int x, boolean[] filledSpaces){
        int total = 1; //total number of blocks connected to orginal block
        filledSpaces[y * piece[0].length + x] = true; //not to double check
        
				// up
        if (y != 0 && piece[y-1][x] == 1 && filledSpaces[(y-1) * piece[0].length + x] == false)
				{
            total += isConnected(piece, y-1, x, filledSpaces);
				}
				
				//left
        if (y + 1 != piece.length && piece[y + 1][x] == 1 && filledSpaces[(y + 1) * piece[0].length + x] == false)
				{
            total += isConnected(piece, y + 1, x, filledSpaces);
				}

				//right
        if (x != 0 && piece[y][x - 1] == 1 && filledSpaces[(y) * piece[0].length + x - 1] == false)
				{
            total += isConnected(piece, y, x - 1, filledSpaces);
				}

				//down
        if (x + 1 != piece[0].length && piece[y][x + 1] == 1 && filledSpaces[(y) * piece[0].length + x + 1] == false)
				{
            total += isConnected(piece, y, x + 1, filledSpaces);
				}
        
        return total;
		}
    
    //is the first row empty
    public static boolean firstRowEmpty(int[][] piece){
        for (int i = 0; i < piece[0].length; i++){
            if (piece[0][i] == 1){
                return true;}
        }
        
        return false;}
    
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
        if (firstRowEmpty(piece) == false){//premptively removes duplicates
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
        if (checkPrev(piece) == true){
            return true;} //check if piece already in list
        int[][] newPiece = transform(piece);
        if (checkPrev(newPiece) == true){
            return true;} //rotates it
        newPiece = transform(newPiece);
        if (checkPrev(newPiece) == true){
            return true;} //rotates again
        newPiece = transform(newPiece);
        return checkPrev(newPiece) == true;} //
    
    //prints individual polyminoe
    public void printPiece(int[][] piece){
        for (int[] piece1 : piece) {
            for (int j = 0; j < piece[0].length; j++) {
                System.out.print(piece1[j]);}
            System.out.println();}
        System.out.println();}
    
    //adds piece to dynamic array
    public void add(int[][] piece) {

				// if this isn't a valid piece, don't bother adding it
        if (isValidPiece(piece) == false){
            return;
				}


				// remove empty rows and columns
        piece = trim(piece);

				// TODO: figure out why we have this
        if (piece.length > piece[0].length){
            return;
				} 

				//guaranteed to be a rotated version of another piece
        if (isDuplicate(piece) == true){
            return;
				}

				// finally, increase the capacity of the array we're about to insert into
        if(size >= data.length-1) 
				{
					increaseCapacity();
				}

				// TODO: change the storage mechanism to something with hashing etc., maybe a HashSet or a BTreeSet?
        data[size] = piece;
        size++;
		}
    
    
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

				long start = System.nanoTime();
        List<int[]> permutations = generate(n, polySize);
				long end = System.nanoTime();
				System.out.println("time: "+((end-start)/1000000)+" ms");
        
        for (int[] permutation: permutations){
            int[][] piece = new int[row][column];
            for (int i = 0; i < permutation.length; i++){
                piece[permutation[i]/column][permutation[i]%column] = 1;}
            add(piece);
				}

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
        
        return combinations;
		}
    
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
    
    
    public static void main(String[] args) {

				int poly = Integer.parseInt(args[0]);
        
        //Creates array in chunks to not overload the system with permutations
        FixedPolyominoeArray main = new FixedPolyominoeArray(1, poly, poly);

        for (int i = 1; 2 * i < poly; i++){
            FixedPolyominoeArray sub = new FixedPolyominoeArray(1 + i, poly - i, poly);
            for (int j = 0; j < sub.size; j++) {
                    main.add(sub.data[j]);
						}
				}

        System.out.println(main.getSize());
		}
}
