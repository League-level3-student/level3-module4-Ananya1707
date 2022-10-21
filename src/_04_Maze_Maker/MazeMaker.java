package _04_Maze_Maker;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class MazeMaker {

    private static int rows;
    private static int cols;

    private static Maze maze;

    private static Random randGen = new Random();
    private static Stack<Cell> uncheckedCells = new Stack<Cell>();

    public static Maze generateMaze(int r, int c) {
        rows = r;
        cols = c;
        maze = new Maze(rows, cols);

        // 1. Pick a random cell along the border and remove its exterior wall.
        //    This will be the starting point. Then select a random cell along
        //    the opposite wall and remove its exterior wall. This will be the
        //    finish line.
        int startCellCol = 0;
        int startCellRow = randGen.nextInt(rows);
       
        
        if(startCellRow != 0 && startCellRow != rows-1) {
        	startCellCol = randGen.nextInt(2)*(cols-1);
        }
        else {
        	startCellCol = randGen.nextInt(cols);
        }
        
        System.out.println(startCellRow + " , " + startCellCol);

        
        Cell startCell = maze.cells[startCellRow][startCellCol];
        
        if(startCellRow == 0) {
        	startCell.setNorthWall(false);
        }
        else if(startCellRow == rows-1) {
        	startCell.setSouthWall(false);
        }
        else if(startCellCol == 0) {
        	startCell.setWestWall(false);
        }
        else if(startCellCol == cols-1) {
        	startCell.setEastWall(false);
        }
        
        int endCellRow = 0;
        int endCellCol = 0;
        
       if(startCellRow == 0) {
    	   endCellRow = rows - 1;
    	   endCellCol = randGen.nextInt(cols);
       }
       else if(startCellRow == rows - 1) {
    	   endCellRow = 0;
    	   endCellCol = randGen.nextInt(cols);
       }
       else if(startCellCol == 0) {
    	   endCellCol = cols - 1;
    	   endCellRow = randGen.nextInt(rows);
       }
       else if(startCellCol == cols - 1){
    	   endCellCol = 0;
    	   endCellRow = randGen.nextInt(rows);
       }
       
       Cell endCell = maze.cells[endCellRow][endCellCol];
       
       if(endCellRow == 0) {
    	   endCell.setNorthWall(false);
       }
       else if(endCellRow == rows-1) {
    	   endCell.setSouthWall(false);
       }
       else if(endCellCol == 0) {
    	   endCell.setWestWall(false);
       }
       else if(endCellCol == cols-1) {
    	   endCell.setEastWall(false);
       }
       
        
        // 2. select a random cell in the maze to start 
       Cell randomCell = maze.cells[randGen.nextInt(rows)][randGen.nextInt(cols)];
       
        
        // 3. call the selectNextPath method with the randomly selected cell
       selectNextPath(randomCell);

        return maze;
    }

    // 4. Complete the selectNextPathMethod
    private static void selectNextPath(Cell currentCell) {
        // A. SET currentCell as visited
    	currentCell.hasBeenVisited();

        // B. check for unvisited neighbors using the cell
    	ArrayList<Cell> list = getUnvisitedNeighbors(currentCell);

        // C. if has unvisited neighbors,
    	if(list.isEmpty() == false) {
    		 // C1. select one at random.
        	Cell randomCell = list.get(randGen.nextInt(list.size()));

            // C2. push it to the stack
        	uncheckedCells.push(randomCell);

            // C3. remove the wall between the two cells
        	removeWalls(currentCell,randomCell);

            // C4. make the new cell the current cell and SET it as visited
        	currentCell = randomCell;
        	randomCell.setBeenVisited(true);

            // C5. call the selectNextPath method with the current cell
        	selectNextPath(currentCell);
    	}

        // D. if all neighbors are visited
    	if(list.isEmpty() == true) {
    		// D1. if the stack is not empty
        	if(uncheckedCells.empty() == false) {
        		// D1a. pop a cell from the stack
                // D1b. make that the current cell
            	currentCell = uncheckedCells.pop();

                // D1c. call the selectNextPath method with the current cell
            	selectNextPath(currentCell);
        	}
    	}

    }

    // This method will check if c1 and c2 are adjacent.
    // If they are, the walls between them are removed.
    private static void removeWalls(Cell c1, Cell c2) {
        if (c1.getRow() == c2.getRow()) {
            if (c1.getCol() > c2.getCol()) {
                c1.setWestWall(false);
                c2.setEastWall(false);
            } else {
                c1.setEastWall(false);
                c2.setWestWall(false);
            }
        } else {
            if (c1.getRow() > c2.getRow()) {
                c1.setNorthWall(false);
                c2.setSouthWall(false);
            } else {
                c1.setSouthWall(false);
                c2.setNorthWall(false);
            }
        }
    }

    // This method returns a list of all the neighbors around the specified
    // cell that have not been visited. There are up to 4 neighbors per cell.
    //          1
    //       2 cell 3
    //          4
    private static ArrayList<Cell> getUnvisitedNeighbors(Cell c) {
        int row = c.getRow();
        int col = c.getCol();

        ArrayList<Cell> unvisitedNeighbors = new ArrayList<Cell>();

        if (row > 0 && !maze.getCell(row - 1, col).hasBeenVisited()) {
            unvisitedNeighbors.add(maze.getCell(row - 1, col));
        }

        if (col > 0 && !maze.getCell(row, col - 1).hasBeenVisited()) {
            unvisitedNeighbors.add(maze.getCell(row, col - 1));
        }

        if (row < rows - 1 && !maze.getCell(row + 1, col).hasBeenVisited()) {
            unvisitedNeighbors.add(maze.getCell(row + 1, col));
        }

        if (col < cols - 1 && !maze.getCell(row, col + 1).hasBeenVisited()) {
            unvisitedNeighbors.add(maze.getCell(row, col + 1));
        }

        return unvisitedNeighbors;
    }
}
