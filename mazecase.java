import java.awt.*;
import java.awt.event.*;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.util.*;
import javax.swing.Timer;
import javax.swing.JFrame;
import javax.swing.*;

import java.io.*;
import javax.imageio.*;

import java.lang.Thread;

class canvas extends CanvasBase implements KeyListener{
    static int ponx = 400;
    static int pony = 400;
    static ArrayList<String> maze = new ArrayList<String>();
    static String[][] strmaze;
    static int con = 0;
    static int fps = 60;//speed
	static int intmaze[][];//int maze
	static int thiefx,thiefy,dir=5;//thief position now,0:down,1:left,2:up,3:right
    static String[] res;
    static String fileRes;
    Image image;
	static Timer timer;
	static int timecount = 240;
	static boolean dircheck = false;
	
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
		g.drawString(Integer.toString(timecount/60)+":"+Integer.toString(timecount%60),10, pony*50+10);
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
        jf.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		jf.addWindowListener(new WindowAdapter(){
   	    public void windowClosing(WindowEvent event){
   	    	int yesno = JOptionPane.showConfirmDialog(null, "Do you want close this window?","CloseWindow",JOptionPane.YES_NO_OPTION);
   	    	if(yesno == 0)System.exit(0);
   	     }
        });
        canvas canvas = new canvas();
        jf.getContentPane().add(canvas);
        System.out.println("FPS = " + fps);
        canvas.setFPS(fps);
        canvas.startPaint();
		
        canvas.addKeyListener(canvas);//add Key listener
		
        jf.setBounds(100, 100, ponx*50+100, pony*50+100);
        jf.setVisible(true);
		timer = new Timer(1000, new ActionListener() {
		      @Override
		      public void actionPerformed(ActionEvent e) {
		        timecount--;
		        if (timecount >= 0) {
		          	updateMaze(intmaze);
		        } else {
		          timer.stop();
		        }
		      }
		  	});
		timer.setInitialDelay(0);
	    timer.start();
	}
	@Override
	public void keyTyped(KeyEvent e) {
	}
	@Override
	public void keyPressed(KeyEvent e) {//keyPress
		int key = e.getKeyCode();
		switch(key){
			case KeyEvent.VK_DOWN:
				if(dircheck == false){dircheck = true;dir = 0;}
				goCheck(0);break;
			case KeyEvent.VK_LEFT:
				if(dircheck == false){dircheck = true;dir = 1;}
				goCheck(1);break;
			case KeyEvent.VK_UP:
				if(dircheck == false){dircheck = true;dir = 2;}
				goCheck(2);break;
			case KeyEvent.VK_RIGHT:
				if(dircheck == false){dircheck = true;dir = 3;}
				goCheck(3);break;
			case KeyEvent.VK_SPACE:
				spacecheck();break;
		}
	}
	/*	  2
	 * 1	 3
	 *	  0		*/
	@Override
	public void keyReleased(KeyEvent e) {
	}
	public void goCheck(int move){//character move action
		switch(move){
			case 0:
				if(dir != 0){
					intmaze[thiefy][thiefx] = 6;
					dir = 0;
				}else if(thiefy+1 != intmaze.length && intmaze[thiefy+1][thiefx]==0){
					intmaze[thiefy+1][thiefx] = 6;
					intmaze[thiefy][thiefx] = 0;
					thiefy++;
				}
				break;
			case 1:
				if(dir != 1){
					intmaze[thiefy][thiefx] = 7;
					dir = 1;
				}else if(thiefx-1 != -1 && intmaze[thiefy][thiefx-1]==0){
					intmaze[thiefy][thiefx-1] = 7;
					intmaze[thiefy][thiefx] = 0;
					thiefx--;
				}
				break;
			case 2:
				if(dir != 2){
					intmaze[thiefy][thiefx] = 8;
					dir = 2;
				}else if(thiefy-1 != -1 && intmaze[thiefy-1][thiefx]==0){
					intmaze[thiefy-1][thiefx] = 8;
					intmaze[thiefy][thiefx] = 0;
					thiefy--;
				}
				break;
			case 3:
				if(dir != 3){
					intmaze[thiefy][thiefx] = 9;
					dir = 3;
				}else if(thiefx+1 != intmaze[0].length && intmaze[thiefy][thiefx+1]==0){
					intmaze[thiefy][thiefx+1] = 9;
					intmaze[thiefy][thiefx] = 0;
					thiefx++;
				}
				break;
		}
		updateMaze(intmaze);
	}
	public void spacecheck(){
		if(dircheck == false){
			System.out.println("請開始動作");
		}else{
			switch(dir){
				case 0://0:down
					if(intmaze[thiefy+1][thiefx] == 2){
						System.out.println("Get the savecase");
						intmaze[thiefy+1][thiefx] = 0;
					}
					break;
				case 1://1:left
					if(intmaze[thiefy][thiefx-1] == 2){
						System.out.println("Get the savecase");
						intmaze[thiefy][thiefx-1] = 0;
					}
					break;
				case 2://2:up
					if(intmaze[thiefy-1][thiefx] == 2){
						System.out.println("Get the savecase");
						intmaze[thiefy-1][thiefx] = 0;
					}
					break;
				case 3://3:right
					if(intmaze[thiefy][thiefx+1] == 2){
						System.out.println("Get the savecase");
						intmaze[thiefy][thiefx+1] = 0;
					}
					break;
			}
			updateMaze(intmaze);
		}
	}
}

public class mazecase extends canvas{

	static int a; //a and b is start position
	static int b;
    static canvas can = new canvas();
    //setRes 設定res
    //initMaze 初始化迷宮
	//updateMaze 更新迷宮
	
    public static void main(String[] args) {
    	String[] res = {
    		"res/floor.png",//road0
    		"res/wall.png",//wall1
    		"res/savebox.png",//2
    		"",//走過的路3
    		"res/thief/thief.png",//thief4
			"res/wall.png",//5
			"res/thief/thiefdown.png",//6
			"res/thief/thiefleft.png",//7
			"res/thief/thiefup.png",//8
			"res/thief/thiefright.png",//9
			"res/savebox/sbdown.png",//10----------------
			"res/savebox/sbleft.png",//11
			"res/savebox/sbup.png",//12
			"res/savebox/sbright.png",//13
			"res/savebox/sbodown.png",//14---------------
			"res/savebox/sboleft.png",//15
			"res/savebox/sboup.png",//16
			"res/savebox/sboright.png",//17
			"res/sp/sp.png",//18---------------
			"res/sp/spdown.png",//19
			"res/sp/spleft.png",//20
			"res/sp/spup.png",//21
			"res/sp/spright.png",//22
			"res/key/key1.png"//23
    	};
    	can.setRes(res);
    	
        Scanner sca=new Scanner(System.in);
    	
    	System.out.print("請輸入迷宮的高跟寬(請以SPACE隔開)：");
    	int maze[][]=new int[sca.nextInt()+2][sca.nextInt()+2];
    	System.out.println(maze.length+"*"+maze[0].length+"迷宮已經建好了");
    	buildfence(maze);
		can.initMaze(maze,a,b);
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
    				maze[i][j] = -1;//beforeroad
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
    				maze[y][x] = -1;
    				break;
    			}
    		}
    		for(int i=0;i<maze.length;i++){//確認旁邊有無路
        		for(int j=0;j<maze[0].length;j++){
        			if(maze[i][j] == 0){
        				if(maze[i-1][j] == -1){
        					wallcount++;
        					maze[i-1][j] = 0;
        				}
        				if(maze[i+1][j] == -1){
        					wallcount++;
        					maze[i+1][j] = 0;
        				}
        				if(maze[i][j-1] == -1){
        					wallcount++;
        					maze[i][j-1] = 0;
        				}
        				if(maze[i][j+1] == -1){
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
        			if(checkmaze(j,i,maze,0) == 5)
        				maze[i][j] = 5;
        		}
        	}
    		for(int i=2;i<maze.length-2;i++){
        		for(int j=2;j<maze[0].length-2;j++){
        			if(checkmaze(j,i,maze,5) == 5){
        				if(checkmaze(j+2,i,maze,5) == 5)
        					maze[i][j+1] = 5;
        				if(checkmaze(j,i+2,maze,5) == 5)
        					maze[i+1][j] = 5;
        				if(checkmaze(j-2,i,maze,5) == 5)
        					maze[i][j-1] = 5;
        				if(checkmaze(j,i-2,maze,5) == 5)
        					maze[i-1][j] = 5;
        			}
        		}
			}
    		checkwall = true;
    		for(int [] i:maze){
    			for(int j:i){
    				if(j == -1)
    					checkwall = false;
    			}
    		}
    	}
		//--------buildstart
		int checkstart = (int)(Math.random() * 1000000 % 4);
		switch(checkstart){
			case 0://down
				while(true){
					int startposition = (int)(Math.random() * 1000000 % maze[0].length);
					if(startposition != 0 && startposition != maze[0].length-1 && maze[maze.length-2][startposition] == 0){
						maze[maze.length-1][startposition] = 4;a = startposition;b = maze.length-1;
						break;
					}
				}
				break;
			case 1://left
				while(true){
					int startposition = (int)(Math.random() * 1000000 % maze.length);
					if(startposition != 0 && startposition != maze.length-1 && maze[startposition][1] == 0){
						maze[startposition][0] = 4;a = 0;b = startposition;
						break;
					}
				}
				break;
			case 2://up
				while(true){
					int startposition = (int)(Math.random() * 1000000 % maze[0].length);
					if(startposition != 0 && startposition != maze[0].length-1 && maze[1][startposition] == 0){
						maze[0][startposition] = 4;a = startposition;b = 0;
						break;
					}
				}
				break;
			case 3://right
				while(true){
					int startposition = (int)(Math.random() * 1000000 % maze.length);
					if(startposition != 0 && startposition != maze.length-1 && maze[startposition][maze[0].length-2] == 0){
						maze[startposition][maze[0].length-1] = 4;a = maze[0].length-1;b = startposition;
						break;
					}
				}
				break;
		}//--------------
    	maze[maze.length-2][maze[0].length-2]=2;
    }//1:wall,0:road,4:start,2:testsavecase,3:walkbefore
	static int checkmaze(int x,int y,int [][]m,int wallfloor){
    	if(m[y][x] == wallfloor && m[y-1][x] == 0 && m[y+1][x] == 0){//上中下
			if(m[y][x-1] == 0 && m[y][x+1] == 0){//左右
				if(m[y+1][x+1] == 0 && m[y+1][x-1] == 0 && m[y-1][x+1] == 0 && m[y-1][x-1] == 0){//左上左下右上右下
					return 5;
				}
			}
		}
    	return 0;
    }
	/*static void buildsavebox(int [][]m){
		int x,y;
		int count = (int)(Math.random() * 1000000 % 3);
		switch(count){
			case 2://3 savebox
			while(true){
				y = (int)(Math.random() * 1000000 % maze.length-1);
    			x = (int)(Math.random() * 1000000 % maze[0].length-1);
				if(maze[y][x] == 0 &&)
			}
			case 1://2 savebox
			
			case 0://3 savebox
		}
	}*/
	static void saveboxdir(int [][]m,int boxmx,int boxmy){
		if(m[boxmy][boxmx] == 2){
			if(m[boxmy+1][boxmx] == 0)//down
				m[boxmy][boxmx] = 10;
			else if(m[boxmy+1][boxmx] == 0)//left
				m[boxmy][boxmx] = 11;
			else if(m[boxmy+1][boxmx] == 0)//up
				m[boxmy][boxmx] = 12;
			else if(m[boxmy+1][boxmx] == 0)//right
				m[boxmy][boxmx] = 13;
		}else
			System.out.println("Error");
	}
}