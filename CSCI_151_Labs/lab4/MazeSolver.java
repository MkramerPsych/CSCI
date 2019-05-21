import java.util.*;

class MazeSolver {
    public static final int STACK=1,QUEUE=2;
    private Worklist<Square> worklist;
    protected Maze maze;
    protected boolean finished=false;
    protected boolean pathFound=false;
    
    MazeSolver(Maze maze, Worklist<Square> worklist){
	this.maze = maze;
	this.worklist = worklist;
	this.worklist.clear();
	this.worklist.add(maze.getStart());
    }

    public boolean isFinished(){
	return finished;
    }
    
    public boolean pathFound(){
	return pathFound;
    }
    
    /* makes a list of the squares on a path from the start square
       to the exit square */
    public List<Square> getPath(){
    ArrayList<Square> path = new ArrayList<Square>();
    if (pathFound == false) { //return empty list if failure to find exit
    	return path;
    } else {
    	Square temp = maze.getExit(); //get exit square
    	 while (temp.getBack() != null) { //for every square back to start from finish
    	    	path.add(temp); //add to path
    	    	temp = temp.getBack(); //move back one step
    	 }
    }
	return path; // return path
    }
    
    /* performs one step of the maze solver algorithm */
    public void step(){
    	ArrayList<Square> path = new ArrayList<Square>();
        	if (worklist.isEmpty()) { //if worklist is empty, terminate [UNSOLVABLE]
        		pathFound = false; //fail to find path
        		finished = true; //finish maze
        		System.out.println("Maze unsolvable");
        	} else {
        	Square temp = worklist.remove(); //get top of worklist and store in temp
        	if (temp.getType() == Square.EXIT) { //if temp is the exit
        		pathFound = true; //successfully found path
        		finished = true; //finish maze
        		System.out.println("Solution Reached");
        	} else if (!temp.isMarked()){
        		temp.mark(); //mark current square
        		for (Square i : maze.getNeighbors(temp)) {
        			if (i.getType() != 1) {
        				worklist.add(i);
        			}	
        		}
        	}
        }	
    }
}
