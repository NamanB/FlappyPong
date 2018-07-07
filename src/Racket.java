import processing.core.PApplet;
import processing.core.PConstants;

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

	public void draw() {
		game.fill(racketColor);
		game.arc(game.mouseX, game.mouseY, racketWidth, racketHeight, PConstants.PI + 
				PConstants.PI / 8, 2 * PConstants.PI - PConstants.PI / 8, PConstants.OPEN);
	}
}
