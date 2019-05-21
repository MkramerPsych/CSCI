import javax.swing.*;
import java.io.*;
import java.util.*;
import java.awt.*;

public class Maze {
   private static int MAXSQSIZE = 30;
   private int height;
   private int width;
   private Square[][] grid;
   private Square startSquare;
   private Square exitSquare;

   public int getHeight(){
      return height;
   }
   
   public int getWidth(){
      return width;
   }
   
   public Square getStart(){
      return startSquare;
   }
   
   public Square getExit(){
      return exitSquare;
   }
   
   public Square getSquare(int row, int col){
      return grid[row][col];
   }

    /* resets the maze by resetting all of its squares  */   
   public void reset(){
      for(int r=0; r<height; r++)
         for(int c=0; c<width; c++)
         grid[r][c].reset();
   }
   
   
   
   
   public static Maze loadMaze(String fname) throws FileNotFoundException {
       //TODO:  write the loadMaze method
	   Maze maze = new Maze(); //create maze using empty constructor
	   File file = new File(fname); //read in file from file name
	   @SuppressWarnings("resource")
	   Scanner scanner = new Scanner(file); //read in from fname
	   int r = 0; //initialize rows
	   Square sq; //declare sq
	   maze.height = scanner.nextInt(); //set first int to height [r]
	   maze.width = scanner.nextInt(); //set second int to width [c]
	   System.out.println("numRows: " + maze.height + " " + "numColumns: " + maze.width);
	   maze.grid = new Square[maze.height][maze.width];
	   while(r<maze.height) { //for every row in maze
		   for(int c=0; c<maze.width; c++) { //iterate over columns
			   int temp = scanner.nextInt(); //grab next int in line
			   if (temp == 0) { //test if square is type 0
					  sq = new Square(0, r, c);
				  } else if (temp == 1) { //test if square is type 1
					  sq = new Square(1, r, c);
				  } else if (temp == 2) { //test if square is type 2
					  sq = new Square(2, r, c);
					  maze.startSquare = sq; //set as start
				  } else if (temp == 3) { //test if square is type 3
					  sq = new Square(3, r, c);
					  maze.exitSquare = sq; //set as finish
				  } else {
					  throw new RuntimeException("invalid character in maze");
				  }
			   System.out.println("Rows: " + r + " " + "Columns: " + c);
			   maze.grid[r][c] = sq; //read square into maze
			   
		   	}	
		   r++; //next row
	   }
       return maze; // return the maze
   }
   
   
   public ArrayList<Square> getNeighbors(Square square){
       //TODO:  write the getNeighbors method
	   ArrayList<Square> neighbors = new ArrayList<Square>();
	   int row = square.getRow();
	   int col = square.getColumn();
	   if (col > 0) { //find west neighbor
		   neighbors.add(this.grid[row][col - 1]);
	   }
	   if(row < height - 1) { //find south neighbor
		   neighbors.add(this.grid[row + 1][col]);
	   }
	   if(col < width - 1) { //find east neighbor
		   neighbors.add(this.grid[row][col + 1]);
	   }
	   if(row > 0) { //find north neighbor
		   neighbors.add(this.grid[row - 1][col]);
	   }
       return neighbors; // return ArrayList of neighbors
   }
                   
   public String toString(){
      String mstring = "";
      for(int i=0; i<height; i++){
        for(int j=0; j<width; j++){
           mstring += grid[i][j];
        }
        if(i<height-1)
           mstring+="\n";
      }
      return mstring;
   }

    /* draws the maze on the GUI.  DO NOT CHANGE! */   
   public void draw(Graphics g, Dimension d){
      int sqsize = Math.min(Math.min(d.width/width,d.height/height),MAXSQSIZE);
      int top=(d.height-sqsize*height)/2;
      int left=(d.width-sqsize*width)/2;
      for(int i=0; i<height; i++){
         for(int j=0; j<width; j++){
            Square sq = grid[i][j];
            Color c = Color.white;
            switch(sq.getType()){
               case Square.START: c = Color.green; break;
               case Square.EXIT: c = Color.red; break;
               case Square.WALL: c = Color.black; break;
               case Square.SPACE: c = Color.white;
               if(sq.isMarked())
                  c = Color.lightGray;
               if(sq.isOnPath())
                  c = Color.yellow;
               break;
            }
            g.setColor(c);
            g.fillRect(left+j*sqsize,top+i*sqsize,sqsize,sqsize);
         }
      }
      g.setColor(Color.black);
      for(int i=0; i<height; i++){         
         for(int j=0; j<width; j++){
            g.drawRect(left+j*sqsize,top+i*sqsize,sqsize,sqsize);
         }
      }
   }
}

