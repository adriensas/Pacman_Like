import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;


public class BadGuy implements Constantes{
	int x,y;
	int vx=SPEED_DEFAULT,vy=0;
	int agressivity=80;
	Game game;
	Rectangle bounds;
	
	BadGuy(int get_x, int get_y, Game get_game){
		x=get_x;
		y=get_y;
		game=get_game;
		update();
	}
	
	public void draw(Graphics2D g2d){
		g2d.setColor(Color.RED);
        g2d.fillOval(x, y, PACMAN_WIDTH, PACMAN_WIDTH);
	}
	
	public void update(){
		bounds = new Rectangle(x,y,PACMAN_WIDTH,PACMAN_WIDTH);
		Pacman pacman= game.getPacman();
		if(bounds.intersects(pacman.getBounds())){
			pacman.alive=false;//moyen
		}
		ArrayList<String> map = game.getMap();
		if(x%30==0 && y%30==0){
			int[][] directions = {{1,0},{-1,0},{0,1},{0,-1}};
			ArrayList<int[]> possible_dir = new ArrayList<int[]>();
			for(int[] dir : directions){
				if(map.get(y/30+dir[1]).charAt(x/30+dir[0])!='X'){
					possible_dir.add(dir);
				}
			}
			boolean condition = false;
			if(possible_dir.size()>=2){
			condition = (vx==SPEED_DEFAULT*possible_dir.get(0)[0] && vy==SPEED_DEFAULT*possible_dir.get(0)[1]) 
					|| (vx==SPEED_DEFAULT*possible_dir.get(1)[0] && vy==SPEED_DEFAULT*possible_dir.get(1)[1]);
			}
			if(possible_dir.size()==2 && condition){
			}
			else{
				int[] dir = null;
				Random decision=new Random();
				int levelAgressivity = decision.nextInt(101);
				if(levelAgressivity<agressivity){
					Random rand=new Random();
					dir = possible_dir.get(rand.nextInt(possible_dir.size()));
					agressivity--;
					
				}
				else{
					int dist=0;
					for(int[] direction : possible_dir){
						int new_dist=(int) (Math.pow(x+30*direction[0]-pacman.x, 2) + Math.pow(y+30*direction[1]-pacman.y, 2));
						if(dist!=0){
							if(new_dist<dist){
								dist=new_dist;
								dir=direction;
							}
						}
						else{
							dist=new_dist;
							dir=direction;
						}
					}
				}
				vx=SPEED_DEFAULT*dir[0];
				vy=SPEED_DEFAULT*dir[1];
			}
		}
		x=vx+x;
		y=vy+y;
	}
}
