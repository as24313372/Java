import java.awt.*;
import java.awt.event.*;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.util.*;
import javax.swing.JFrame;

import java.io.*;
import javax.imageio.*;

class canvas extends CanvasBase implements KeyListener{
    static int ponx = 400;
    static int pony = 400;
    static ArrayList<String> maze = new ArrayList<String>();
    static String[][] strmaze;
    static int con = 0;
    static int fps = 60;//speed
	static int intmaze[][];//int maze
	static int thiefx,thiefy;//thief position now
    static String[] res;
    static String fileRes;
    Image image;
    public canvas() {
    	
    }
    
    public canvas(int f) {
        fps = f;
    }
    
    public void setRes(String[] res) {
        //設定res
        this.res = res;
    }
    
	public void paintCanvas(Graphics g) {
	    if(con<maze.size()){
	    	for(int x=0; x<ponx; x++){
	    		for(int y=0; y<pony; y++){
	    			strmaze[x][y] = maze.get(con);
	    			con+=1;
	    		}
	    	}
	    }
	    
	    for(int x=0; x<ponx; x++){
    		for(int y=0; y<pony; y++){
    			switch(strmaze[x][y]){
    				case "0":
    					try{
    						fileRes = this.res[Integer.parseInt(strmaze[x][y])];
		    				image = ImageIO.read(new File(fileRes));
			        		g.drawImage(image, y*50, x*50, 50, 50,null);
			        	}catch (Exception ex) {
			        	}
    					break;
    				case "1":
    					try{
    						fileRes = this.res[Integer.parseInt(strmaze[x][y])];
		    				image = ImageIO.read(new File(fileRes));
			        		g.drawImage(image, y*50, x*50, 50, 50,null);
			        	}catch (Exception ex) {
			        		g.fillRect(y*50,x*50,50,50);
			        	}
    					break;
    				case "2":
    					try{
    						fileRes = this.res[Integer.parseInt(strmaze[x][y])];
		    				image = ImageIO.read(new File(fileRes));
			        		g.drawImage(image, y*50, x*50, 50, 50,null);
			        	}catch (Exception ex) {
		    				g.fillOval(y*50+10,x*50+10,30,30);
			       		}
    					break;
    				case "3":
    					try{
    						fileRes = this.res[Integer.parseInt(strmaze[x][y])];
		    				image = ImageIO.read(new File(fileRes));
			        		g.drawImage(image, y*50, x*50, 50, 50,null);
			        	}catch (Exception ex) {
		    				g.fillRect(y*50+20,x*50+20,10,10);
			        	}
    					break;
    				case "4":
    					try{
    						fileRes = this.res[Integer.parseInt(strmaze[x][y])];
		    				image = ImageIO.read(new File(fileRes));
			        		g.drawImage(image, y*50, x*50, 50, 50,null);
			        	}catch (Exception ex) {
		    				g.fillOval(y*50+10,x*50+10,30,30);
			        	}
    					break;
					case "5":
						try{
    						fileRes = this.res[Integer.parseInt(strmaze[x][y])];
		    				image = ImageIO.read(new File(fileRes));
			        		g.drawImage(image, y*50, x*50, 50, 50,null);
			        	}catch (Exception ex) {
			        		g.fillRect(y*50,x*50,50,50);
			        	}
    					break;
    				default:
    					try{
    						fileRes = this.res[Integer.parseInt(strmaze[x][y])];
		    				image = ImageIO.read(new File(fileRes));
			        		g.drawImage(image, y*50, x*50, 50, 50,null);
			        	}catch (Exception ex) {
		    				g.fillOval(y*50+10,x*50+10,30,30);
			        	}
    					break;
    			}
    		}
    	}
    }
    
    public void updateMaze(int[][] m){
		intmaze = m;
		//更新迷宮
		for(int i=0; i<m.length; i++){
    		for(int j=0; j<m[i].length; j++){
    			maze.add(m[i][j]+"");
    		}
    	}
    }
    
    public void initMaze(int[][] m,int a,int b){
		thiefx = a;thiefy = b;
		intmaze = m;
    	//初始化迷宮
    	for(int i=0; i<m.length; i++){
    		for(int j=0; j<m[i].length; j++){
    			maze.add(m[i][j]+"");
    		}
    	}
			
    	ponx = m.length;
    	pony = m[0].length;
    	
        strmaze = new String[ponx][pony];
    	JFrame jf = new JFrame();
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        canvas canvas = new canvas();
        jf.getContentPane().add(canvas);
        System.out.println("FPS = " + fps);
        canvas.setFPS(fps);
        canvas.startPaint();
		
        canvas.addKeyListener(canvas);//add Key listener
		
        jf.setBounds(100, 100, ponx*50+100, pony*50+100);
        jf.setVisible(true);
	}
	@Override
	public void keyTyped(KeyEvent e) {
	}
	@Override
	public void keyPressed(KeyEvent e) {//keyPressed
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			goCheck(0);
		}else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			goCheck(1);
		}else if(e.getKeyCode() == KeyEvent.VK_UP) {
			goCheck(2);
		}else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			goCheck(3);
		}
	}
	/*	2
	 *1	  3
	 *	0
	 */
	@Override
	public void keyReleased(KeyEvent e) {
	}
	public void goCheck(int dir){//character move action
		switch(dir){
			case 0:
				if(thiefy+1 != intmaze.length && intmaze[thiefy+1][thiefx]==0){
					intmaze[thiefy+1][thiefx] = 4;
					intmaze[thiefy][thiefx] = 0;
					thiefy++;
				}break;
			case 1:
				if(thiefx-1 != -1 && intmaze[thiefy][thiefx-1]==0){
					intmaze[thiefy][thiefx-1] = 4;
					intmaze[thiefy][thiefx] = 0;
					thiefx--;
				}break;
			case 2:
				if(thiefy-1 != -1 && intmaze[thiefy-1][thiefx]==0){
					intmaze[thiefy-1][thiefx] = 4;
					intmaze[thiefy][thiefx] = 0;
					thiefy--;
				}break;
			case 3:
				if(thiefx+1 != intmaze[0].length && intmaze[thiefy][thiefx+1]==0){
					intmaze[thiefy][thiefx+1] = 4;
					intmaze[thiefy][thiefx] = 0;
					thiefx++;
				}break;
		}
		updateMaze(intmaze);
	}
}

public class mazecase extends canvas{

	static int a=1; //a and b is start position
	static int b=1;
    static canvas can = new canvas();
    //setRes 設定res
    //initMaze 初始化迷宮
	//updateMaze 更新迷宮
	
    public static void main(String[] args) {
    	String[] res = {
    		"",//路
    		"res/wall.PNG",//牆壁
    		"res/end.jpg",//終點
    		"",//走過的路
    		"res/mouse.png",//老鼠
			"res/wall.PNG"
    	};
    	can.setRes(res);
    	
        Scanner sca=new Scanner(System.in);
    	
    	System.out.print("請輸入迷宮的高跟寬(請以SPACE隔開)：");
    	int maze[][]=new int[sca.nextInt()+2][sca.nextInt()+2];
    	System.out.println(maze.length+"*"+maze[0].length+"迷宮已經建好了");
    	buildfence(maze);
		can.initMaze(maze,a,b);
    	//gomaze(maze);
        
	}
	
    public static void buildfence(int[][] maze)
    {
    	for(int i=0;i<maze.length;i++){
    		for(int j=0;j<maze[0].length;j++){
    			if(i==0 || j==0 || i==maze.length-1 || j==maze[0].length-1)
    				maze[i][j] = 1;//outwall			
    			else if(i%2==0 || j%2==0)
    				maze[i][j] = 5;//inwall
    			else
    				maze[i][j] = 8;//beforeroad
    		}
    	}
    	//--------------------
    	boolean checkwall = false;
    	int count = (int)(Math.random() * 1000000 % 4);
    	if(count == 0)maze[1][1] = 0;
    	else if(count == 1)maze[maze.length-2][1] = 0;
    	else if(count == 2)maze[maze.length-2][maze[0].length-2] = 0;
    	else maze[1][maze[0].length-2] = 0;
    	while(checkwall == false){
    		int x,y,wallcount = 0;//count算隔壁是否可以替換，並且重複執行。
    		while(true){//隨機挑選inwall
    			y = (int)(Math.random() * 1000000 % maze.length-1);
    			x = (int)(Math.random() * 1000000 % maze[0].length-1);
    			if(x != 0 && y !=0 && (x % 2 == 0 || y % 2 == 0) && maze[y][x] == 5){
    				maze[y][x] = 8;
    				break;
    			}
    		}
    		for(int i=0;i<maze.length;i++){//確認旁邊有無路
        		for(int j=0;j<maze[0].length;j++){
        			if(maze[i][j] == 0){
        				if(maze[i-1][j] == 8){
        					wallcount++;
        					maze[i-1][j] = 0;
        				}
        				if(maze[i+1][j] == 8){
        					wallcount++;
        					maze[i+1][j] = 0;
        				}
        				if(maze[i][j-1] == 8){
        					wallcount++;
        					maze[i][j-1] = 0;
        				}
        				if(maze[i][j+1] == 8){
        					wallcount++;
        					maze[i][j+1] = 0;
        				}
        			}
        			if(i == maze.length-1 && j == maze[0].length-1 && wallcount != 0){
        				i = 0;j = 0;
        				wallcount = 0;
        			}
        		}
        	}
    		for(int i=0;i<maze.length;i++){
        		for(int j=0;j<maze[0].length;j++){
        			if(maze[i][j] == 0 && maze[i-1][j] == 0 && maze[i+1][j] == 0){//up,mid.down
        				if(maze[i][j-1] == 0 && maze[i][j+1] == 0){//left,right
        					if(maze[i+1][j+1] == 0 && maze[i+1][j-1] == 0 && maze[i-1][j+1] == 0 && maze[i-1][j-1] == 0){//upleft,downleft,upright,downright
        						maze[i][j] = 1;
        					}
        				}
        			}
        		}
        	}
    		checkwall = true;
    		for(int [] i:maze){
    			for(int j:i){
    				if(j == 8)
    					checkwall = false;
    			}
    		}
    	}
		maze[1][1]=4;//start 
    	maze[maze.length-2][maze[0].length-2]=2;
    }//1:wall,0:road,4:start,2:end,3:walkbefore
}