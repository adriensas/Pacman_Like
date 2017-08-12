import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;


public class Pacman implements Constantes{
	int x;
	int y;
	int vx=0;
	int vy=0;
	int wanted_vx=0;
	int wanted_vy=0;
	Game game;
	Rectangle bounds;
	boolean alive=true;
	Pacman(int x_pos, int y_pos, Game get_game){
		x=x_pos;
		y=y_pos;
		game=get_game;
		update();
	}
	public void draw(Graphics2D g2d){
		if(alive){
			g2d.setColor(Color.YELLOW);
			g2d.fillOval(x, y, PACMAN_WIDTH, PACMAN_WIDTH);
		}
	}
	public Rectangle getBounds(){
		return bounds;
	}
	public void move(int direction){
		wanted_vx=0;
		wanted_vy=0;
		switch (direction)
		{
			case 0://up
				wanted_vy=-SPEED_DEFAULT;
			break;
			case 1://down
				wanted_vy=SPEED_DEFAULT;
			break;
			case 2://left
				wanted_vx=-SPEED_DEFAULT;
			break;
			case 3://right
				wanted_vx=SPEED_DEFAULT;
			break;
		}
	}
	public void update(){
		ArrayList<Wall> walls= game.getWalls();
		bounds=new Rectangle(x,y,PACMAN_WIDTH,PACMAN_WIDTH);
		boolean can_wanted=false;
		if(wanted_vx!=0 || wanted_vy!=0){
			can_wanted=true;
		}
		for(Wall wall : walls){
			if(wall.getBounds().intersects(new Rectangle(x,y+wanted_vy,PACMAN_WIDTH,PACMAN_WIDTH))){
				can_wanted=false;
			}
			if(wall.getBounds().intersects(new Rectangle(x+wanted_vx,y,PACMAN_WIDTH,PACMAN_WIDTH))){
				can_wanted=false;
			}
			if(wall.getBounds().intersects(new Rectangle(x+vx,y,PACMAN_WIDTH,PACMAN_WIDTH))){
				vx=0;
			}
			if(wall.getBounds().intersects(new Rectangle(x,y+vy,PACMAN_WIDTH,PACMAN_WIDTH))){
				vy=0;
			}
		}
		if(can_wanted){
			vy=wanted_vy;
			vx=wanted_vx;
			wanted_vx=0;
			wanted_vy=0;
		}
		x=x+vx;
		y=y+vy;
	}
	
	public void setPosition(int pos_x, int pos_y){
		x=pos_x;
		y=pos_y;
	}
}
