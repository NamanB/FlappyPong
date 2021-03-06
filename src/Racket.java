import processing.core.PApplet;
import processing.core.PConstants;

public class Racket {
	PApplet game;
	public int racketWidth = 100;
	public int racketHeight = 15;
	public int racketColor = 0;
	public int racketBounceRate = 20;

	public Racket(PApplet p) {
		this.game = p;
		racketColor = game.color(0);
	}

	public Racket(PApplet p, int width, int height, int color, int bounceRate) {
		this.game = p;
		this.racketWidth = width;
		this.racketHeight = height;
		this.racketColor = game.color(color);
		this.racketBounceRate = bounceRate;
	}

	public void render() {
		game.fill(racketColor);
		game.arc(game.mouseX, game.mouseY, racketWidth, racketHeight, PConstants.PI + 
				PConstants.PI / 8, 2 * PConstants.PI - PConstants.PI / 8, PConstants.OPEN);
	}
}
