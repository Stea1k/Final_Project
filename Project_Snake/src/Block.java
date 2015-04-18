public class Block {
	private int[] X = new int[4];
	private int[] Y = new int[4];
	
	public int[] getBlockX(){
		return this.X;
	}
	
	public int[] getBlockY(){
		return this.Y;
	}
	
	public Block(){
		this.X[0] = 0;
		this.X[1] = 9;
		this.X[2] = 0;
		this.X[3] = 9;
		this.Y[0] = 0;
		this.Y[1] = 0;
		this.Y[2] = 9;
		this.Y[3] = 9;
	}
}
