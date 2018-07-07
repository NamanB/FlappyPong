import processing.core.PApplet;

public class Wall {
	PApplet game;
	
	public int wallSpeed = 3;
	public int minGapHeight = 200;
	public int maxGapHeight = 300;
	public int wallWidth = 80;
	public int wallColor = game.color(37, 104, 85);
	
	public Wall(PApplet p) {
		game = p;
	}
	
	public Wall(PApplet p, int speed, int minGapHeight, int maxGapHeight, int width, int color) {
		game = p;
		this.wallSpeed = speed;
		this.minGapHeight = minGapHeight;
		this.maxGapHeight = maxGapHeight;
		this.wallWidth = width;
		this.wallColor = color;
	}
}
