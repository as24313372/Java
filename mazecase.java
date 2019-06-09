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
	static boolean dircheck = false,secfile = false,gameover = false;
	static int saveboxp[][];
	static int saveboxkey[][];
	static int boxcount,chance;
	static sg sg1,sg2,sg3,sg4;
	
    public canvas() {
    	
    }
    
    public canvas(int f) {
        fps = f;
    }
    
    public void setRes(String[] res) {
        //ï¿½]ï¿½wres
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
    			try{
    				fileRes = this.res[Integer.parseInt(strmaze[x][y])];
		    		image = ImageIO.read(new File(fileRes));
			        g.drawImage(image, y*50, x*50, 50, 50,null);
			    }catch (Exception ex) {
			    }
    		}
    	}
		g.drawString(Integer.toString(timecount)+"",10, pony*50+10);
    }
    
    public void updateMaze(int[][] m){
		intmaze = m;
		//ï¿½ï¿½sï¿½gï¿½c
		for(int i=0; i<m.length; i++){
    		for(int j=0; j<m[i].length; j++){
    			maze.add(m[i][j]+"");
    		}
    	}
    }
    
    public void initMaze(int[][] m,int a,int b,int box[][],int key[][]){
		saveboxkey = key;
		saveboxp = box;
		boxcount = box.length;
		thiefx = a;thiefy = b;
		intmaze = m;
    	//ï¿½ï¿½lï¿½Æ°gï¿½c
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
		sgspawn();
		timer = new Timer(250, new ActionListener() {
		      @Override
		      public void actionPerformed(ActionEvent e) {
				timecount--;
				sgcontrol();
				updateMaze(intmaze);
				if(gameover){
					timer.stop();
					JOptionPane.showMessageDialog(null, "§A³Q«O¥þµo²{¤F","°T®§",JOptionPane.WARNING_MESSAGE);
					for(int i=0;i<intmaze.length;i++){
						for(int j=0;j<intmaze[0].length;j++)
							System.out.printf("%2d",intmaze[i][j]);
						System.out.println();
					}
					System.exit(0);
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
			case 0://down
				if(thiefy+1 != intmaze.length){
					if(dir != 0){
						intmaze[thiefy][thiefx] = 6;
						dir = 0;
					}else if(thiefy+1 != intmaze.length && intmaze[thiefy+1][thiefx]==0){
						intmaze[thiefy+1][thiefx] = 6;
						intmaze[thiefy][thiefx] = 0;
						thiefy++;
					}
				}
				break;
			case 1://left
				if(thiefx-1 != -1){
					if(dir != 1){
						intmaze[thiefy][thiefx] = 7;
						dir = 1;
					}else if(thiefx-1 != -1 && intmaze[thiefy][thiefx-1]==0){
						intmaze[thiefy][thiefx-1] = 7;
						intmaze[thiefy][thiefx] = 0;
						thiefx--;
					}
				}
				break;
			case 2://up
				if(thiefy-1 != -1){
					if(dir != 2){
						intmaze[thiefy][thiefx] = 8;
						dir = 2;
					}else if(thiefy-1 != -1 && intmaze[thiefy-1][thiefx]==0){
						intmaze[thiefy-1][thiefx] = 8;
						intmaze[thiefy][thiefx] = 0;
						thiefy--;
					}
				}
				break;
			case 3://right
				if(thiefx+1 != intmaze[0].length){
					if(dir != 3){
						intmaze[thiefy][thiefx] = 9;
						dir = 3;
					}else if(thiefx+1 != intmaze[0].length && intmaze[thiefy][thiefx+1]==0){
						intmaze[thiefy][thiefx+1] = 9;
						intmaze[thiefy][thiefx] = 0;
						thiefx++;
					}
				}
				break;
		}
		updateMaze(intmaze);
	}
	public void spacecheck(){
		int checkbox;
		if(dircheck == false){
			System.out.println("½Ð¶}©l°Ê§@¡I");
		}else{
			if(thiefy != 0 && thiefx != 0 && thiefy != intmaze.length-1 && thiefx != intmaze[0].length-1){
				switch(dir){
					case 0://0:down
						if(intmaze[thiefy+1][thiefx] == 12){
							if(boxcheck(thiefy+1,thiefx) != -1){
								System.out.printf("Get the savecase %d\n",saveboxp[boxcheck(thiefy+1,thiefx)][2]);
								intmaze[thiefy+1][thiefx] = 16;
								boxcount--;
							}
						}
						if(intmaze[thiefy+1][thiefx] == 3){
							saveboxkeycheck(thiefy+1,thiefx);
							intmaze[thiefy+1][thiefx] = 0;
						}
						System.out.println(intmaze[thiefy+1][thiefx]);
						break;
					case 1://1:left
						if(intmaze[thiefy][thiefx-1] == 13){
							if(boxcheck(thiefy,thiefx-1) != -1){
								System.out.printf("Get the savecase %d\n",saveboxp[boxcheck(thiefy,thiefx-1)][2]);
								intmaze[thiefy][thiefx-1] = 17;
								boxcount--;
							}
						}
						if(intmaze[thiefy][thiefx-1] == 3){
							saveboxkeycheck(thiefy,thiefx-1);
							intmaze[thiefy][thiefx-1] = 0;
						}
						System.out.println(intmaze[thiefy][thiefx-1]);
						break;
					case 2://2:up
						if(intmaze[thiefy-1][thiefx] == 10){
							if(boxcheck(thiefy-1,thiefx) != -1){
								System.out.printf("Get the savecase %d\n",saveboxp[boxcheck(thiefy-1,thiefx)][2]);
								intmaze[thiefy-1][thiefx] = 14;
								boxcount--;
							}
						}
						if(intmaze[thiefy-1][thiefx] == 3){
							saveboxkeycheck(thiefy-1,thiefx);
							intmaze[thiefy-1][thiefx] = 0;
						}
						System.out.println(intmaze[thiefy-1][thiefx]);
						break;
					case 3://3:right
						if(intmaze[thiefy][thiefx+1] == 11){
							if(boxcheck(thiefy,thiefx+1) != -1){
								System.out.printf("Get the savecase %d\n",saveboxp[boxcheck(thiefy,thiefx+1)][2]);
								intmaze[thiefy][thiefx+1] = 15;
								boxcount--;
							}
						}
						if(intmaze[thiefy][thiefx+1] == 3){
							saveboxkeycheck(thiefy,thiefx+1);
							intmaze[thiefy][thiefx+1] = 0;
						}
						System.out.println(intmaze[thiefy][thiefx+1]);
						break;
				}
			}else{
				if(boxcount == 0){
					System.out.println("pass");
					JOptionPane.showMessageDialog(null, "®¥³ß§A§¹¦¨¥Ø¼Ð","°T®§",JOptionPane.INFORMATION_MESSAGE);
					System.exit(0);
				}else
					System.out.println("not pass");
			}
			updateMaze(intmaze);
		}
	}
	public int boxcheck(int y,int x){
		boolean keynotget = false;
		for(int i=0;i<saveboxkey.length;i++){
			if(y == saveboxkey[i][3] && x == saveboxkey[i][4] && saveboxkey[i][2] == 1){
				keynotget = true;
				return i;
			}
		}
		if(keynotget == false){
			for(int i=0;i<saveboxp.length;i++){
				if(y == saveboxp[i][0] && x == saveboxp[i][1] && (saveboxp[i][2] == 1 || saveboxp[i][2] == 4))
					return i;
			}
		}
		return -1;
	}
	public void saveboxkeycheck(int y,int x){
		for(int i=0;i<saveboxkey.length;i++){
			if(y == saveboxkey[i][0] && x == saveboxkey[i][1]){
				saveboxkey[i][2] = 1;
				System.out.println(saveboxkey[i][3] + " " + saveboxkey[i][4]);
			}
		}
	}
	public void sgspawn(){
		sg1 = new sg(intmaze);
		sg2 = new sg(intmaze);
		sg3 = new sg(intmaze);
		sg4 = new sg(intmaze);
		sg1.start();
		intmaze = sg1.intmaze;
		chance = (int)(Math.random() * 1000000 % 100);
		if(chance < 30){
			sg2.intmaze = intmaze;
			sg2.start();
			intmaze = sg2.intmaze;
		}
		if(chance < 20){
			sg3.intmaze = intmaze;
			sg3.start();
			intmaze = sg3.intmaze;
		}
		if(chance < 10){
			sg4.intmaze = intmaze;
			sg4.start();
			intmaze = sg4.intmaze;
		}
	}
	public void sgcontrol(){//thief 4 5V 6< 7^ 8>
		intmaze = sg1.intmaze;
		sgcheck(sg1.y,sg1.x,sg1.sgdir);
		sg1.intmaze = intmaze;
		if(chance < 30){
			intmaze = sg2.intmaze;
			sgcheck(sg2.y,sg2.x,sg2.sgdir);
			sg2.intmaze = intmaze;
		}if(chance < 20){
			intmaze = sg3.intmaze;
			sgcheck(sg3.y,sg3.x,sg3.sgdir);
			sg3.intmaze = intmaze;
		}if(chance < 10){
			intmaze = sg4.intmaze;
			sgcheck(sg4.y,sg4.x,sg4.sgdir);
			sg4.intmaze = intmaze;
		}
		if(gameover == true){
			sg1.check = false;
			if(chance < 30)
				sg2.check = false;
			if(chance < 20)
				sg3.check = false;
			if(chance < 10)
				sg4.check = false;
		}
	}
	public void sgcheck(int y,int x,int sgdirc){
		if(sgdirc == 0){
			intmaze[y][x] =19;
			for(int i=4;i<9;i++){
				if(i != 5 && intmaze[y+1][x] == i)gameover = true;
			}
		}else if(sgdirc == 1){
			intmaze[y][x] =20;
			for(int i=4;i<9;i++){
				if(i != 5 && intmaze[y][x-1] == i)gameover = true;
			}
		}else if(sgdirc == 2){
			intmaze[y][x] =21;
			for(int i=4;i<9;i++){
				if(i != 5 && intmaze[y-1][x] == i)gameover = true;
			}
		}else if(sgdirc == 3){
			intmaze[y][x] =22;
			for(int i=4;i<9;i++){
				if(i != 5 && intmaze[y][x+1] == i)gameover = true;
			}
		}
		System.out.println(y + " " + x + " " + sgdirc);
	}
}
/*	 2
* 1	 	3
*	 0	*/
class sg extends Thread{
	static boolean check = true;
	static int countsg = 0;
	static int sgdir = 0;
	static int x,y;
	static int intmaze[][];
	sg(int m[][]){
		intmaze = m;
	}
	public void run(){
		while(true){
			y = (int)(Math.random() * 1000000 % intmaze.length-1);
			x = (int)(Math.random() * 1000000 % intmaze[0].length-1);
			if(intmaze[y][x] == 0)break;
		}
		while(check){
			System.out.println(countsg++);
			try{
				sleep(1000);
				intmaze[y][x] = 0;
				if((int)(Math.random() * 1000000 % 2) == 1){
					if((int)(Math.random() * 1000000 % 2) == 1){
						if(sgdir == 0){
							if(y != intmaze.length-2 && intmaze[y+1][x] == 0)
								y++;
						}else
							sgdir = 0;
					}else{
						if(sgdir == 2){
							if(y != 1 && intmaze[y-1][x] == 0)
								y--;
						}else
							sgdir = 2;
					}
				}else{
					if((int)(Math.random() * 1000000 % 2) == 1){
						if(sgdir == 3){
							if(x != intmaze[0].length-2 && intmaze[y][x+1] == 0)
								x++;
						}else
							sgdir = 3;
					}else{
						if(sgdir == 1){
							if(x != 1 && intmaze[y][x-1] == 0)
								x--;
						}else
							sgdir = 1;
					}
				}
			}catch(Exception e){
				
			}
		}
	}
}

public class mazecase extends canvas{

	static int saveboxkey[][];//y x getkey?=>(0not,1yes) boxpx boxpy
	static int saveboxp[][];//y x open=>[0key,1password,2secfile=>(3key,4password)]
	static int a; //a and b is start position
	static int b; //a = x,b = y
    static canvas can = new canvas();
    //setRes ï¿½]ï¿½wres
    //initMaze ï¿½ï¿½lï¿½Æ°gï¿½c
	//updateMaze ï¿½ï¿½sï¿½gï¿½c
	
    public static void main(String[] args) {
    	String[] res = {
    		"res/floor.png",//road0
    		"res/wall.png",//wall1
    		"res/savebox.png",//2
    		"res/key/key.png",//key3
    		"res/thief/thief.png",//thief4
			"res/wall.png",//wall5
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
			"res/securityguard/sg.png",//18---------------
			"res/securityguard/sgdown.png",//19
			"res/securityguard/sgleft.png",//20
			"res/securityguard/sgup.png",//21
			"res/securityguard/sgright.png",//22
			"res/key/key1.png"//23
    	};
    	can.setRes(res);

        Scanner sca=new Scanner(System.in);
		int maze[][];
		while(true){
			System.out.print("½Ð¿é¤J³õ¦a¤j¤p¡A¿é¤J¨â­Ó¼Æ¦r¥HªÅ±Æ¹j¶}(¨â­Ó¼Æ¦r»Ý¤j©ó5)¡G");
			int mazey = sca.nextInt();int mazex = sca.nextInt();
			if(mazey > 5 && mazex >5){
				maze = new int[mazey+2][mazex+2];break;
			}else
				System.out.println("½Ð¿é¤J¤j©ó5ªº¼Æ¦r");
		}
		buildfence(maze);
		System.out.println(maze.length+"*"+maze[0].length+"¤w«Ø¥ß§¹¦¨");
		can.initMaze(maze,a,b,saveboxp,saveboxkey);
		
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
    		int x,y,wallcount = 0;//countï¿½ï¿½jï¿½ï¿½ï¿½Oï¿½_ï¿½iï¿½Hï¿½ï¿½ï¿½ï¿½ï¿½Aï¿½Ã¥Bï¿½ï¿½ï¿½Æ°ï¿½ï¿½ï¿½C
    		while(true){//ï¿½Hï¿½ï¿½ï¿½Dï¿½ï¿½inwall
    			y = (int)(Math.random() * 1000000 % maze.length-1);
    			x = (int)(Math.random() * 1000000 % maze[0].length-1);
    			if(x != 0 && y !=0 && (x % 2 == 0 || y % 2 == 0) && maze[y][x] == 5){
    				maze[y][x] = -1;
    				break;
    			}
    		}
    		for(int i=0;i<maze.length;i++){//ï¿½Tï¿½{ï¿½ï¿½ï¿½ä¦³ï¿½Lï¿½ï¿½
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
		System.out.println("spawn wall finish");
    	buildsavebox(maze);
		System.out.println("spawn savebox finish");
		spawnkey(maze);
		System.out.println("spawn key finish");
    }//1:wall,0:road,4:start,2:testsavecase,3:walkbefore
	static int checkmaze(int x,int y,int [][]m,int wallfloor){
    	if(m[y][x] == wallfloor && m[y-1][x] == 0 && m[y+1][x] == 0){//ï¿½Wï¿½ï¿½ï¿½U
			if(m[y][x-1] == 0 && m[y][x+1] == 0){//ï¿½ï¿½ï¿½k
				if(m[y+1][x+1] == 0 && m[y+1][x-1] == 0 && m[y-1][x+1] == 0 && m[y-1][x-1] == 0){//ï¿½ï¿½ï¿½Wï¿½ï¿½ï¿½Uï¿½kï¿½Wï¿½kï¿½U
					return 5;
				}
			}
		}
    	return 0;
	}
	static void buildsavebox(int [][]m){
		int x,y,countnum = 0;
		int boxcount = (int)(Math.random() * 1000000 % 3);
		System.out.println("boxcount:" + boxcount);
		saveboxp = new int [boxcount+1][3];//y(0),x(1),key0 or password1 or important file2(3)
		for(int i=0;i<=boxcount;i++){
			while(true){
				y = (int)(Math.random() * 1000000 % m.length);
				x = (int)(Math.random() * 1000000 % m[0].length);
				if(m[y][x] == 0 && Math.abs(a - x) >= 3 && Math.abs(b - y) >= 3 && (x < 5 || y < 5)){
					if(countnum == 0){
						if(checksavebox(m,y,x) == 1){
							m[y][x] = 2;saveboxdir(m,x,y);saveboxp[i][0] = y;saveboxp[i][1] = x;
							saveboxp[i][2] = 2;countnum++;
							break;}
					}else if(countnum == 1){
						//if(Math.abs((int)(saveboxp[0][0]) - y) > 2 || Math.abs((int)(saveboxp[0][1]) - x) > 2){
							if(checksavebox(m,y,x) == 1){
							m[y][x] = 2;saveboxdir(m,x,y);saveboxp[i][0] = y;saveboxp[i][1] = x;
							saveboxp[i][2] = (int)(Math.random() * 1000000 % 2);countnum++;
							break;}
						//}
					}else if(countnum == 2){
						//if(Math.abs((int)(saveboxp[0][0]) - y) > 1 || Math.abs((int)(saveboxp[0][1]) - x) > 1){
							//if(Math.abs((int)(saveboxp[1][0]) - y) > 1 || Math.abs((int)(saveboxp[1][1]) - x) > 1){
								if(checksavebox(m,y,x) == 1){
								m[y][x] = 2;saveboxdir(m,x,y);saveboxp[i][0] = y;saveboxp[i][1] = x;
								saveboxp[i][2] = (int)(Math.random() * 1000000 % 2);countnum++;
								break;}
							//}
						//}
					}
				}
			}
		}
	}
	static int checksavebox(int m[][],int y,int x){
		int checkdir = 0;
		if(m[y-1][x] == 0){
			//if(m[y-2][x] == 0 || m[y-1][x-1] == 0 || m[y-1][x+1] == 0)checkdir =1;
			if(m[y-1][x-1] == 0 && m[y][x-1] == 0)checkdir = 1;
			if(m[y-1][x+1] == 0 && m[y][x+1] == 0)checkdir = 1;
		}
		if(m[y+1][x] == 0){
			//if(m[y+2][x] == 0 || m[y+1][x-1] == 0 || m[y+1][x+1] == 0)checkdir =1;
			if(m[y+1][x-1] == 0 && m[y][x-1] == 0)checkdir = 1;
			if(m[y+1][x+1] == 0 && m[y][x+1] == 0)checkdir = 1;
		}
		if(m[y][x-1] == 0){
			//if(m[y][x-2] == 0 || m[y-1][x-1] == 0 || m[y+1][x-1] == 0)checkdir =1;
			if(m[y-1][x-1] == 0 && m[y-1][x] == 0)checkdir = 1;
			if(m[y+1][x-1] == 0 && m[y+1][x] == 0)checkdir = 1;
		}
		if(m[y][x+1] == 0){
			//if(m[y][x+2] == 0 || m[y-1][x+1] == 0 || m[y+1][x+1] == 0)checkdir =1;
			if(m[y-1][x+1] == 0 && m[y-1][x] == 0)checkdir = 1;
			if(m[y+1][x+1] == 0 && m[y+1][x] == 0)checkdir = 1;
		}
		return checkdir;
	}
	static void saveboxdir(int [][]m,int boxmx,int boxmy){
		if(m[boxmy][boxmx] == 2){
			if(m[boxmy+1][boxmx] == 0){//down
				if(m[boxmy+1][boxmx+1] == 0 || m[boxmy+1][boxmx-1] == 0|| m[boxmy+2][boxmx] == 0)
					m[boxmy][boxmx] = 10;
			}else if(m[boxmy][boxmx-1] == 0){//left
				if(m[boxmy+1][boxmx-1] == 0 || m[boxmy-1][boxmx-1] == 0|| m[boxmy][boxmx-2] == 0)
					m[boxmy][boxmx] = 11;
			}else if(m[boxmy-1][boxmx] == 0){//up
				if(m[boxmy-1][boxmx+1] == 0 || m[boxmy-1][boxmx-1] == 0|| m[boxmy-2][boxmx] == 0)
					m[boxmy][boxmx] = 12;
			}else if(m[boxmy][boxmx+1] == 0){//right
				if(m[boxmy+1][boxmx+1] == 0 || m[boxmy-1][boxmx+1] == 0|| m[boxmy][boxmx+2] == 0)
					m[boxmy][boxmx] = 13;
			}
		}else
			System.out.println("saveboxdir Error");
	}
	static void spawnkey(int m[][]){
		saveboxp[0][2] = (int)(Math.random() * 1000000 % 2) + 3;//3secfilekey,4secfilepassword
		saveboxkey = new int [saveboxp.length][5];
		for(int i=0;i<saveboxkey.length;i++)
			for(int j=0;j<5;j++)
				saveboxkey[i][j] =0;//turn to zero
		for(int i=0;i<saveboxp.length;i++){
			if(saveboxp[i][2] == 0 || saveboxp[i][2] == 3){
				while(true){
					int y = (int)(Math.random() * 1000000 % m.length-1);
					int x = (int)(Math.random() * 1000000 % m[0].length-1);
					if(Math.abs(a - x) >= 2 && Math.abs(b - y) >= 2){
						if(m[y][x] == 0 && Math.abs(saveboxp[i][0] - y) > 2 && Math.abs(saveboxp[i][1] - x) > 2){
							saveboxkey[i][0] = y;saveboxkey[i][1] = x;
							saveboxkey[i][3] = saveboxp[i][0];saveboxkey[i][4] = saveboxp[i][1];
							m[y][x] = 3;
							break;
						}
					}
				}
			}
		}
	}
}