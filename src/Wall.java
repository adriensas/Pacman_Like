import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;


public class Wall implements Constantes{
	int x;
	int y;
	Rectangle bounds;
	Wall(int pos_x,int pos_y){
		x=pos_x;
		y=pos_y;
		bounds= new Rectangle(x,y,PACMAN_WIDTH,PACMAN_WIDTH);
	}
	
	public Rectangle getBounds(){
		return bounds;
	}
	
	public void draw(Graphics2D g2d){
		g2d.setColor(Color.BLUE);
		g2d.drawRect(x, y, PACMAN_WIDTH, PACMAN_WIDTH);
	}
	 
	public void update(){
		 
	}

}
