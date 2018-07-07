import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PConstants;

public class Wall {
	PApplet game;
	
	public int wallSpeed = 3;
	
	public int wallColor = 0;
	public int gapWallX;
	public int gapWallY;
	public int wallWidth = 80;
	public int gapWallHeight;
	
	public ArrayList<Ball> scoredBalls = new ArrayList<Ball>();
	
	public Wall(PApplet p, int gapWallX, int gapWallY,  int gapWallHeight) {
		game = p;
		this.wallColor = game.color(37, 104, 85);
		this.gapWallX = gapWallX;
		this.gapWallY = gapWallY;
		this.gapWallHeight = gapWallHeight;
	}
	
	public void makeWall(PApplet p, int speed, int gapWallX, int gapWallY, int gapWallWidth, int gapWallHeight, int color) {
		new Wall(p, gapWallX, gapWallY, gapWallHeight);
		this.wallSpeed = speed;
		this.wallWidth = gapWallWidth;
		this.wallColor = game.color(color);
	}
	
	public void render() {
 		game.rectMode(PConstants.CORNER);
		game.fill(wallColor);
		game.rect(gapWallX, 0, wallWidth, gapWallY);
		int gapBottom = gapWallY + gapWallHeight;
		game.rect(gapWallX, gapBottom, wallWidth, game.height - gapBottom);
	}
	
	public void updateWall() {
		gapWallX -= wallSpeed;
	}
	
	public void addBallScored(Ball b) {
		scoredBalls.add(b);
	}
	
}
