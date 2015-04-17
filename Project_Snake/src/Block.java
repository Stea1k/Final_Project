
public class Block {
	private int[] X = {0,9,0,9};
	private int[] Y = {0,0,9,9};
	
	private int blockSquares[][] = null;
	
	public void fillBlockSquares(){
		for(int x: this.getBlockX()){
			for(int y: this.getBlockY()){
				blockSquares[x][y] = 9;
			}
		}
	}
	
	public boolean isBlock(int kibbleX, int kibbleY){
		if(blockSquares[kibbleX][kibbleY] == 9){
			return false;
		}
		return true;
	}
	
	public int[] getBlockX(){
		return this.X;
	}
	
	public int[] getBlockY(){
		return this.Y;
	}
	
	public Block(){
		this.X = X;
		this.Y = Y;
	}
}
