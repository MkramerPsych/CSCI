import java.io.FileNotFoundException;

import org.junit.Test;


public class MazeTest {

	@Test
	public void testLoadMaze() throws FileNotFoundException {
		@SuppressWarnings("unused")
		Maze maze = Maze.loadMaze("maze-1");
	}

	@Test
	public void testGetNeighbors() throws FileNotFoundException {
		Maze maze = Maze.loadMaze("maze-1");
		Square sq = maze.getSquare(0, 0); 
		maze.getNeighbors(sq);
		sq = maze.getSquare(1, 1);
		maze.getNeighbors(sq);
		sq = maze.getSquare(0, 1);
		maze.getNeighbors(sq);
	}

}
