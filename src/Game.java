import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Game extends JPanel{
	static Pacman pacman;
	static ArrayList<Coin> coins = new ArrayList<Coin>();
	static ArrayList<Wall> walls = new ArrayList<Wall>();
	static ArrayList<BadGuy> badGuys = new ArrayList<BadGuy>();
	static ArrayList<String> map = new ArrayList<String>();
	
	public Game() {
		KeyListener listener = new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				char touche = e.getKeyChar();
				switch(touche){
					case 'z':
						pacman.move(0);
					break;
					case 's':
						pacman.move(1);
					break;
					case 'q':
						pacman.move(2);
					break;
					case 'd':
						pacman.move(3);
					break;
					case 'a':
						pacman.setPosition(30,30);
					break;
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}};
		addKeyListener(listener);
		setFocusable(true);
	}

	public Pacman getPacman(){
		return pacman;
	}
	
	public ArrayList<Wall> getWalls(){
		return walls;
	}
	
	public ArrayList<Coin> getCoins(){
		return coins;
	}
	
	public ArrayList<String> getMap(){
		return map;
	}
	
	public void update(){
		pacman.update();
		for(Coin coin : coins){
			coin.update();
		}
		for(Wall wall : walls){
			wall.update();
		}
		for(BadGuy badGuy : badGuys){
			badGuy.update();
		}
	}
	
	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0,0,1000,1000);
		pacman.draw(g2d);
		for(Coin coin : coins){
			coin.draw(g2d);
		}
		for(Wall wall : walls){
			wall.draw(g2d);
		}
		for(BadGuy badGuy : badGuys){
			badGuy.draw(g2d);
		}
	}
	
	public static void loadMap(Game game) throws IOException{
		int i, j= 0;
		BufferedReader in = new BufferedReader(new FileReader("map.mp"));
		String line;
		while((line = in.readLine()) != null)
		{
			i=0;
			map.add(line);
			while(i<15){
				switch(line.charAt(i)){
					case 'X':
						walls.add(new Wall(i*30,j*30));
					break;
					case 'O':
						coins.add(new Coin(i*30+10,j*30+10,game));
					break;
					case 'D':
						pacman.setPosition(i*30,j*30);
					break;
				}
				i++;
			}
			j++;
		}
		in.close();
	}
	
	public static void main(String[] args) throws InterruptedException {
		JFrame frame = new JFrame("Pacman Like");
		Game game = new Game();
		pacman=new Pacman(30,60,game);
		try {
			loadMap(game);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//badGuys.add(new BadGuy(240,30,game));
		badGuys.add(new BadGuy(300,120,game));
		frame.add(game);
		frame.setSize(500, 500);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pacman.move(3);
		while (true) {
			//Game Loop
			game.update();
			game.repaint();
			Thread.sleep(10);
		}
	}
}
