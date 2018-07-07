import processing.core.PApplet;
import processing.core.PConstants;

public class Ball {
	PApplet game;
	
	public int ballX = 0;
	public int ballY = 0;
	public int ballSize = 20;
	public int ballColor = game.color(0);
	
	public float verticalBallSpeed = 0;
	public float horizontalBallSpeed = 10;
	public float maxHealth = 100;
	public float health = 100;
	public float healthDecrease = 1;
	public float healthBarWidth = 60;
	
	public Ball(PApplet p) {
		this.game = p;
	}
	
	public Ball(PApplet p, int size ,int color) {
		this.game = p;
		this.ballSize = size;
		this.ballColor = game.color(color);
	}
	
	public void render() {
		game.fill(ballColor);
		game.ellipse(ballX, ballY, ballSize, ballSize);
		drawHealth();
	}
	
	public void drawHealth() {
		game.noStroke(); // no border
		game.fill(236, 240, 241);
		game.rectMode(PConstants.CORNER);
		game.rect(ballX - (healthBarWidth / 2), ballY - 30, healthBarWidth, 5);
		if (health > 60) {
			game.fill(46, 204, 113);
		} else if (health > 30) {
			game.fill(230, 126, 34);
		} else {
			game.fill(231, 76, 60);
		}
		game.rectMode(PConstants.CORNER);
		game.rect(ballX - (healthBarWidth / 2), ballY - 30, healthBarWidth * (health / maxHealth), 5);
	}
	
	/**
	 * Moves the ball
	 * @param gravity coefficient of gravity
	 * @param airFriction coefficient of air friction
	 */
	public void move(int gravity, float airFriction) {
		verticalBallSpeed += gravity;
		ballY += verticalBallSpeed;
		verticalBallSpeed -= (verticalBallSpeed * airFriction);
		
		ballX += horizontalBallSpeed;
		horizontalBallSpeed -= (horizontalBallSpeed * airFriction);
	}
	
	/**
	 * Bounces the ball 
	 * @param surface surface bounced on
	 * @param isVertical if the bounce is vertical or not (meaning horizontal)
	 * @param resetDistToSurface correction distance to surface to
	 * @param friction coefficient of friction
	 * @param gravity coefficient of gravity
	 */
	public void bounce(float surface, boolean isVertical, float resetDistToSurface, float friction, float gravity) {
		if (isVertical) {
			ballY = (int) (surface + resetDistToSurface);
			verticalBallSpeed *= -1;
			// corrects for constant gravity speed increase being larger than decrease on
			// bounce
			verticalBallSpeed -= (verticalBallSpeed * friction) - (gravity + (gravity * friction));
		} else {
			ballX = (int) (surface + resetDistToSurface);
			horizontalBallSpeed *= -1;
			horizontalBallSpeed -= horizontalBallSpeed * friction;
		}
	}
	
	public void bottomBounce(float surface, float friction, float gravity) {
		bounce(surface, true, -ballSize / 2, friction, gravity);
	}
	
	public void topBounce(float surface, float friction, float gravity) {
		bounce(surface, true, ballSize / 2, friction, gravity);
	}

	public void rightBounce(float surface, float friction, float gravity) {
		bounce(surface, false, -ballSize / 2, friction, gravity);
	}

	public void leftBounce(float surface, float friction, float gravity) {
		bounce(surface, false, ballSize / 2, friction, gravity);
	}
	
	/**
	 * Keeps ball in screen
	 * @param friction coefficient of friction
	 * @param gravity coefficient of gravity
	 */
	public void keepInScreen(float friction, float gravity) {
		if (ballY + (ballSize / 2) > game.height) {
			bottomBounce(game.height, friction, gravity);
		}
		if (ballY - (ballSize / 2) < 0) {
			topBounce(0, friction, gravity);
		}
		if (ballX + (ballSize / 2) > game.width) {
			rightBounce(game.width, friction, gravity);
		}
		if (ballX - (ballSize / 2) < 0) {
			leftBounce(0, friction, gravity);
		}
	}
}
