import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;


public class Coin {
	int x;
	int y;
	boolean alive=true;
	Game game;
	Rectangle bounds;
	
	Coin(int pos_x,int pos_y, Game get_game){
		x=pos_x;
		y=pos_y;
		game=get_game;
		bounds=new Rectangle(x,y,10,10);
		update();
	}
	
	public Rectangle getBounds(){
		return bounds;
	}
	
	public void draw(Graphics2D g2d){
		if(alive){
			g2d.setColor(Color.WHITE);
	        g2d.fillOval(x, y, 10, 10);
		}
	}
	
	public void update(){
		Pacman pacman=game.getPacman();
		if(bounds.intersects(pacman.getBounds()) && alive){
			alive=false;
		}
	}
}
