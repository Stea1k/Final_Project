
import java.util.Random;

public class Kibble {

	/** Identifies a random square to display a kibble
	 * Any square is ok, so long as it doesn't have any snake segments in it. 
	 * 
	 */
	
	private int kibbleX; //This is the square number (not pixel)
	private int kibbleY;  //This is the square number (not pixel)
	
	public Kibble(Snake s){
		//Kibble needs to know where the snake is, so it does not create a kibble in the snake
		//Pick a random location for kibble, check if it is in the snake
		//If in snake, try again
		//also checks if kibble is found in a block.
		int first = 1;
		//guarantees that the kibble does not start in the snake.
		if(first > 0){
			moveKibble(s);
			first--;
		}else moveKibble(s);
	}
	protected void moveKibble(Snake s){
		Random rng = new Random();
		boolean kibbleInSnake = true;
//		boolean kibbleInBlock = true;
		while (kibbleInSnake == true) {
			//Generate random kibble location
			kibbleX = rng.nextInt(SnakeGame.xSquares);
			kibbleY = rng.nextInt(SnakeGame.ySquares);
			kibbleInSnake = s.isSnakeSegment(kibbleX, kibbleY);
//			kibbleInBlock = s.isBlock(kibbleX, kibbleY);
		}
	}

	public int getKibbleX() {
		return kibbleX;
	}

	public int getKibbleY() {
		return kibbleY;
	}
}
