import processing.core.PApplet;

public class Racket {
	PApplet game;
	int racketWidth = 100;
	int racketHeight = 15;
	int racketColor = game.color(0);
	int racketBounceRate = 20;

	
	public Racket(PApplet p) {
		this.game = p;
	}
	
	public Racket(PApplet p, int width, int height, int color, int bounceRate) {
		this.game = p;
		this.racketWidth = width;
		this.racketHeight = height;
		this.racketColor = color;
		this.racketBounceRate = bounceRate;
	}
}
