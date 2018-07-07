import processing.core.PApplet;

public class Wall {
	PApplet game;
	
	int minGapHeight = 200;
	int maxGapHeight = 300;
	int wallWidth = 80;
	int wallColor = game.color(37, 104, 85);
	
	public Wall(PApplet p) {
		game = p;
	}
	
	public Wall(PApplet p, int minGapHeight, int maxGapHeight, int width, int color) {
		game = p;
		this.minGapHeight = minGapHeight;
		this.maxGapHeight = maxGapHeight;
		this.wallWidth = width;
		this.wallColor = color;
	}
}
