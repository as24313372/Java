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
    static int fps = 120;//speed
	static int intmaze[][];//int maze
	static int thiefx,thiefy,dir=5;//thief position now,0:down,1:left,2:up,3:right
    static String[] res;
    static String fileRes;
	static JFrame jf;
    Image image;
	static Timer timer;
	static int timecount = 1000;
	static boolean dircheck = false,secfile = false,gameover = false,gamewin = false;
	static int saveboxp[][];
	static int saveboxkey[][];
	static int chance;
	static sg sg1,sg2,sg3,sg4;
	
    public canvas() {
    	
    }
    
    public canvas(int f) {
        fps = f;
    }
    
    public void setRes(String[] res) {
        //for res
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
		int drawkey = 10;
		for(int i=0;i<saveboxkey.length;i++){
			if(saveboxkey[i][0] != 0 && saveboxkey[i][2] == 1){
				g.drawString("You get a key : Y=" + saveboxkey[i][3] + ",X=" + saveboxkey[i][4],50, pony*50+drawkey);
				drawkey += 15;
			}
		}
		if(secfile)g.drawString("你已取得機密資料",200, pony*50+10);
		
    }
    
    public void updateMaze(int[][] m){
		intmaze = m;
		//
		for(int i=0; i<m.length; i++){
    		for(int j=0; j<m[i].length; j++){
    			maze.add(m[i][j]+"");
    		}
    	}
    }
    
    public void initMaze(int[][] m,int a,int b,int box[][],int key[][]){
		saveboxkey = key;
		saveboxp = box;
		thiefx = a;thiefy = b;
		intmaze = m;
    	//
    	for(int i=0; i<m.length; i++){
    		for(int j=0; j<m[i].length; j++){
    			maze.add(m[i][j]+"");
    		}
    	}
			
    	ponx = m.length;
    	pony = m[0].length;
        strmaze = new String[ponx][pony];
    	jf = new JFrame();
        jf.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		jf.addWindowListener(new WindowAdapter(){
   	    public void windowClosing(WindowEvent event){
   	    	int yesno = JOptionPane.showConfirmDialog(jf, "你確定要離開嗎？","CloseWindow",JOptionPane.YES_NO_OPTION);
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
		JOptionPane.showMessageDialog(jf, "目標：取得機密資料，並逃出。","訊息",JOptionPane.INFORMATION_MESSAGE);
		sgspawn();
		timer = new Timer(100, new ActionListener() {
		      @Override
		      public void actionPerformed(ActionEvent e) {
				timecount--;
				sgcontrol();
				updateMaze(intmaze);
				if(gameover){
					timer.stop();
					JOptionPane.showMessageDialog(jf, "你被保全發現了","訊息",JOptionPane.WARNING_MESSAGE);
					for(int i=0;i<intmaze.length;i++){
						for(int j=0;j<intmaze[0].length;j++)
							System.out.printf("%2d",(int)(intmaze[i][j]));
						System.out.println();
					}
					System.exit(0);
				}
				if(gamewin)
					timer.stop();
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
	 * 1	 	 3
	 *	  0		*/
	@Override
	public void keyReleased(KeyEvent e) {
	}
	public void goCheck(int move){//character move action
		boolean delag = false; 
		switch(move){
			case 0://down
				if(thiefy+1 != intmaze.length){
					if(dir != 0){
						intmaze[thiefy][thiefx] = 6;
						dir = 0;
					}else if(intmaze[thiefy+1][thiefx]==0 && thiefy+1 != intmaze.length){
						delag = true;
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
					}else if(intmaze[thiefy][thiefx-1]==0 && thiefx-1 != -1){
						delag = true;
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
					}else if(intmaze[thiefy-1][thiefx]==0 && thiefy-1 != -1){
						delag = true;
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
					}else if(intmaze[thiefy][thiefx+1]==0 && thiefx+1 != intmaze[0].length){
						delag = true;
						intmaze[thiefy][thiefx+1] = 9;
						intmaze[thiefy][thiefx] = 0;
						thiefx++;
					}
				}
				break;
		}
		if(delag)updateMaze(intmaze);
	}
	public void spacecheck(){
		int checkbox;
		boolean delagspace = false;
		if(dircheck == false){
			System.out.println("請開始動作！");
		}else{
			if(thiefy != 0 && thiefx != 0 && thiefy != intmaze.length-1 && thiefx != intmaze[0].length-1){
				switch(dir){
					case 0://0:down
						if(intmaze[thiefy+1][thiefx] == 12){
							int intboxcheck = boxcheck(thiefy+1,thiefx);
							if(intboxcheck != -1){
								delagspace = true;
								System.out.printf("Get the savecase %d\n",saveboxp[intboxcheck][2]);
								intmaze[thiefy+1][thiefx] = 16;
								if(saveboxp[intboxcheck][2] == 3 || saveboxp[intboxcheck][2] == 4)secfile = true;
							}
						}
						if(intmaze[thiefy+1][thiefx] == 3){
							delagspace = true;
							saveboxkeycheck(thiefy+1,thiefx);
							intmaze[thiefy+1][thiefx] = 0;
						}
						System.out.println(intmaze[thiefy+1][thiefx]);
						break;
					case 1://1:left
						if(intmaze[thiefy][thiefx-1] == 13){
							int intboxcheck = boxcheck(thiefy,thiefx-1);
							if(intboxcheck != -1){
								delagspace = true;
								System.out.printf("Get the savecase %d\n",saveboxp[intboxcheck][2]);
								intmaze[thiefy][thiefx-1] = 17;
								if(saveboxp[intboxcheck][2] == 3 || saveboxp[intboxcheck][2] == 4)secfile = true;
							}
						}
						if(intmaze[thiefy][thiefx-1] == 3){
							delagspace = true;
							saveboxkeycheck(thiefy,thiefx-1);
							intmaze[thiefy][thiefx-1] = 0;
						}
						System.out.println(intmaze[thiefy][thiefx-1]);
						break;
					case 2://2:up
						if(intmaze[thiefy-1][thiefx] == 10){
							int intboxcheck = boxcheck(thiefy-1,thiefx);
							if(intboxcheck != -1){
								delagspace = true;
								System.out.printf("Get the savecase %d\n",saveboxp[intboxcheck][2]);
								intmaze[thiefy-1][thiefx] = 14;
								if(saveboxp[intboxcheck][2] == 3 || saveboxp[intboxcheck][2] == 4)secfile = true;
							}
						}
						if(intmaze[thiefy-1][thiefx] == 3){
							delagspace = true;
							saveboxkeycheck(thiefy-1,thiefx);
							intmaze[thiefy-1][thiefx] = 0;
						}
						System.out.println(intmaze[thiefy-1][thiefx]);
						break;
					case 3://3:right
						if(intmaze[thiefy][thiefx+1] == 11){
							int intboxcheck = boxcheck(thiefy,thiefx+1);
							if(intboxcheck != -1){
								delagspace = true;
								System.out.printf("Get the savecase %d\n",saveboxp[intboxcheck][2]);
								intmaze[thiefy][thiefx+1] = 15;
								if(saveboxp[intboxcheck][2] == 3 || saveboxp[intboxcheck][2] == 4)secfile = true;
							}
						}
						if(intmaze[thiefy][thiefx+1] == 3){
							delagspace = true;
							saveboxkeycheck(thiefy,thiefx+1);
							intmaze[thiefy][thiefx+1] = 0;
						}
						System.out.println(intmaze[thiefy][thiefx+1]);
						break;
				}
			}else{
				if(secfile){
					System.out.println("pass");
					gamewin = true;
					JOptionPane.showMessageDialog(jf, "恭喜你完成目標","訊息",JOptionPane.INFORMATION_MESSAGE);
					System.out.println(sg1.warn);
					for(int i=0;i<intmaze.length;i++){
						for(int j=0;j<intmaze[0].length;j++)
							System.out.printf("%2d",(int)(intmaze[i][j]));
						System.out.println();
					}
					System.exit(0);
				}else{
					System.out.println("not pass");
					JOptionPane.showMessageDialog(jf, "你還沒拿到機密資料","訊息",JOptionPane.INFORMATION_MESSAGE);
				}
			}
			if(delagspace)updateMaze(intmaze);
		}
	}
	public int boxcheck(int y,int x){
		boolean keynotget = false;
		for(int i=0;i<saveboxkey.length;i++){
			if(y == saveboxkey[i][3] && x == saveboxkey[i][4] && saveboxkey[i][2] == 1){
				keynotget = true;
				return i;
			}
			if(y == saveboxkey[i][3] && x == saveboxkey[i][4]){
				System.out.println("You need key!");
				JOptionPane.showMessageDialog(jf, "你需要鑰匙才能解開","訊息",JOptionPane.INFORMATION_MESSAGE);
				return -1;
			}
		}
		if(keynotget == false){
			for(int i=0;i<saveboxp.length;i++){
				if(y == saveboxp[i][0] && x == saveboxp[i][1] && (saveboxp[i][2] == 1 || saveboxp[i][2] == 4)){
					if(password() == 1)return i;
				}
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
		sg1 = new sg(intmaze,thiefx,thiefy);
		sg1.start();
		intmaze = sg1.intmaze;
		chance = (int)(Math.random() * 1000000 % 100);
		sg2 = new sg(intmaze,thiefx,thiefy);
		if(chance < 30){
			sg2.start();
			intmaze = sg2.intmaze;
		}
		sg3 = new sg(intmaze,thiefx,thiefy);            
		if(chance < 20){
			sg3.start();
			intmaze = sg3.intmaze;
		}
		sg4 = new sg(intmaze,thiefx,thiefy);
		if(chance < 10){
			sg4.start();
			intmaze = sg4.intmaze;
		}
	}
	public void sgcontrol(){//thief 4 5V 6< 7^ 8>
		sg1.intmaze = intmaze;
		sgcheck(sg1.y,sg1.x,sg1.sgdir);
		if(chance < 30){
			sg2.intmaze = intmaze;
			sgcheck(sg2.y,sg2.x,sg2.sgdir);
		}if(chance < 20){
			sg3.intmaze = intmaze;
			sgcheck(sg3.y,sg3.x,sg3.sgdir);
		}if(chance < 10){
			sg4.intmaze = intmaze;
			sgcheck(sg4.y,sg4.x,sg4.sgdir);
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
		for(int i=4;i<9;i++){
			if(i != 5 && intmaze[y+1][x] == i){intmaze[y][x] =19;gameover = true;}
			if(i != 5 && intmaze[y][x-1] == i){intmaze[y][x] =20;gameover = true;}
			if(i != 5 && intmaze[y-1][x] == i){intmaze[y][x] =21;gameover = true;}
			if(i != 5 && intmaze[y][x+1] == i){intmaze[y][x] =22;gameover = true;}
		}
		System.out.println(y + " " + x + " " + sgdirc);
	}
	public int password(){
		String pq = "";
		int pqans,i,j;
		int style = (int)(Math.random() * 1000000 % 4);
		if(style == 0){// +
			i = (int)(Math.random() * 1000000 % 10000 + 1);
			j = (int)(Math.random() * 1000000 % 10000 + 1);
			pq = i + "+" + j + "= ?";
			pqans = i + j;
		}else if(style == 1){// -
			i = (int)(Math.random() * 1000000 % 10000 + 1);
			j = (int)(Math.random() * 1000000 % 10000 + 1);
			pq = i + "-" + j + "= ?";
			pqans = i - j;
		}else if(style == 2){// *
			i = (int)(Math.random() * 1000000 % 100 + 1);
			j = (int)(Math.random() * 1000000 % 100 + 1);
			pq = i + "*" + j + "= ?";
			pqans = i * j;
		}else{// /
			i = (int)(Math.random() * 1000000 % 100 + 1);
			while(true){
				j = (int)(Math.random() * 1000000 % 100 + 1);
				if((int)(i/j)>0)break;
			}
			pq = i + "/" + j + "= ?(取商)";
			pqans = (int)(i / j);
		}
		String ans = JOptionPane.showInputDialog(jf,pq,"Password",JOptionPane.INFORMATION_MESSAGE);
		try{
			if(Integer.parseInt(ans) == pqans){
				JOptionPane.showMessageDialog(jf, "O答對O","訊息",JOptionPane.INFORMATION_MESSAGE);
				return 1;
			}else{
				JOptionPane.showMessageDialog(jf, "X答錯X","訊息",JOptionPane.INFORMATION_MESSAGE);
				sg1.warn = true;
				if(chance < 30)sg2.warn = true;
				if(chance < 20)sg3.warn = true;
				if(chance < 10)sg4.warn = true;
				return -1;
			}
		}catch(Exception e){
			return -1;
		}
	}
}
/*	 2
* 1	 	3
*	 0	*/
class sg extends Thread{
	public boolean check = true,warn = false,storcheck = false;
	public int countsg = 0,ind = 0;
	public int sgdir = 0;
	public int x,y,a,b;
	public int intmaze[][],stor[][],intmazeforwarn[][];
	sg(int m[][],int tx,int ty){
		intmaze = m;
		a = tx;b = ty;
	}
	public void run(){
		while(true){
			y = (int)(Math.random() * 1000000 % intmaze.length-1);
			x = (int)(Math.random() * 1000000 % intmaze[0].length-1);
			if(intmaze[y][x] == 0)break;
		}
		while(check){
			System.out.println(countsg++);
			int randomtime = (int)(Math.random() * 1000000 % 4);
			try{
				if(randomtime == 0)sleep(333);
				else if(randomtime == 1)sleep(666);
				else if(randomtime == 2)sleep(999);
				else if(randomtime == 3)sleep(1324);
				intmaze[y][x] = 0;
				if(warn){
					if(!storcheck){
						stor = new int [(intmaze.length-2)*(intmaze[0].length-2)][3];
						storcheck = true;
					}
					sgwalksavebox();
				}else{
					intmazeforwarn = intmaze;
					storcheck = false;ind = 0;
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
				}
			}catch(Exception e){}
		}
	}
	public void sgwalksavebox(){
		for(int i=10;i<18;i++){
			if(intmaze[y-1][x] == i || intmaze[y+1][x] == i)//mid,up,down
				warn = false;
			if(intmaze[y][x-1] == i || intmaze[y][x+1] == i)//left,right
				warn = false;
			if(intmaze[y+1][x+1] == i || intmaze[y+1][x-1] == i || intmaze[y-1][x+1] == i || intmaze[y-1][x-1] == i)//downright,downleft,upright,upleft
				warn = false;
			if(!warn)return;
		}
		switch(sgdir){
			case 0://down0
				if((intmazeforwarn[y+1][x] == 0)&&(y+1 != intmaze.length-1)){
					intmazeforwarn[y][x] = 4;
					stor[ind][0]=y;
					stor[ind][1]=x;
					stor[ind++][2]=sgdir;
					y+= 1;
					sgdir = 0;
				}
				else{
					sgdir++;
				}
				break;
			case 1://left1
				if((intmazeforwarn[y][x-1] == 0)&&(x-1 != 0)){
					intmazeforwarn[y][x] = 4;
					stor[ind][0]=y;
					stor[ind][1]=x;
					stor[ind++][2]=sgdir;
					x-= 1;
					sgdir = 0;
				}
				else{
					sgdir++;
				}
				break;
			case 2://up2
				if((intmazeforwarn[y-1][x] == 0)&&(y-1 != 0)){
					intmazeforwarn[y][x] = 4;
					stor[ind][0]=y;
					stor[ind][1]=x;
					stor[ind++][2]=sgdir;
					y-= 1;
					sgdir = 0;
				}
				else{
					sgdir++;
				}
				break;
			case 3://right3
				if((intmazeforwarn[y][x+1] == 0)&&(x+1 != intmaze[0].length-1)){
					intmazeforwarn[y][x] = 4;
					stor[ind][0]=y;
					stor[ind][1]=x;
					stor[ind++][2]=sgdir;
					x+= 1;
					sgdir = 0;
				}
				else{
					sgdir++;
				}
				break;
			default:
				if(--ind < 0){
					warn = false;
					return;
				}
				intmazeforwarn[y][x] = 2;
				y=stor[ind][0];
				x=stor[ind][1];
				sgdir=stor[ind][2];
		}
	}
}

public class mazecase extends canvas{

	static int saveboxkey[][];//y x getkey?=>(0not,1yes) boxpx boxpy
	static int saveboxp[][];//y x open=>[0key,1password,2secfile=>(3key,4password)]
	static int a; //a and b is start position
	static int b; //a = x,b = y
    static canvas can = new canvas();
    //setRes for image in res
    //initMaze first time set
	//updateMaze update position in maze
	
    public static void main(String[] args) {
    	String[] res = {
    		"res/floor.png",//road0
    		"res/wall.png",//wall1
    		"res/floor.png",//road2
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
			System.out.print("請輸入場地大小，輸入兩個數字以空排隔開(兩個數字需大於9小於16)：");
			int mazey = sca.nextInt();int mazex = sca.nextInt();
			if(mazey > 5 && mazex > 5 && mazey < 16 && mazex < 16){
				maze = new int[mazey+2][mazex+2];break;
			}else
				System.out.println("請輸入大於9小於16的數字(建議數字都相同)");
		}
		buildfence(maze);
		System.out.println(maze.length+"*"+maze[0].length+"已建立完成");
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
    		int x,y,wallcount = 0;//wallcount for inwall count
    		while(true){//random pick inwall to road number
    			y = (int)(Math.random() * 1000000 % maze.length-1);
    			x = (int)(Math.random() * 1000000 % maze[0].length-1);
    			if(x != 0 && y !=0 && (x % 2 == 0 || y % 2 == 0) && maze[y][x] == 5){
    				maze[y][x] = -1;
    				break;
    			}
    		}
    		for(int i=0;i<maze.length;i++){//inwall to road
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
        			if(checkmaze(j,i,maze,0) == 5)maze[i][j] = 5;
        		}
        	}
    		for(int i=2;i<maze.length-2;i++){
        		for(int j=2;j<maze[0].length-2;j++){
        			if(checkmaze(j,i,maze,5) == 5){
        				if(checkmaze(j+2,i,maze,5) == 5)maze[i][j+1] = 5;
        				if(checkmaze(j,i+2,maze,5) == 5)maze[i+1][j] = 5;
        				if(checkmaze(j-2,i,maze,5) == 5)maze[i][j-1] = 5;
        				if(checkmaze(j,i-2,maze,5) == 5)maze[i-1][j] = 5;
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
    	if(m[y][x] == wallfloor && m[y-1][x] == 0 && m[y+1][x] == 0){//mid,up,down
			if(m[y][x-1] == 0 && m[y][x+1] == 0){//left,right
				if(m[y+1][x+1] == 0 && m[y+1][x-1] == 0 && m[y-1][x+1] == 0 && m[y-1][x-1] == 0){//downright,downleft,upright,upleft
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
			int buildsaveboxcount = 0;
			while(true){
				y = (int)(Math.random() * 1000000 % m.length);
				x = (int)(Math.random() * 1000000 % m[0].length);
				if(buildsaveboxcount > 20000){
					if(m[y][x] == 0 && (m[y+1][x] == 0 || m[y][x-1] == 0 || m[y-1][x] == 0 || m[y][x+1] == 0)){
						if(countnum == 0){
							saveboxdir(m,x,y);
							saveboxp[i][0] = y;saveboxp[i][1] = x;
							saveboxp[i][2] = 2;countnum++;
							break;
						}else if(countnum == 1){
							saveboxdir(m,x,y);
							saveboxp[i][0] = y;saveboxp[i][1] = x;
							saveboxp[i][2] = (int)(Math.random() * 1000000 % 2);countnum++;
							break;
						}else if(countnum == 2){
							saveboxdir(m,x,y);
							saveboxp[i][0] = y;saveboxp[i][1] = x;
							saveboxp[i][2] = (int)(Math.random() * 1000000 % 2);countnum++;
							break;
						}
					}
				}
				if(m[y][x] == 0 && Math.abs(a - x) >= 3 && Math.abs(b - y) >= 3 && (x < 5 || y < 5)){
					if(countnum == 0){
						if(checksavebox(m,y,x) == 1 && saveboxdir(m,x,y)){
							saveboxp[i][0] = y;saveboxp[i][1] = x;
							saveboxp[i][2] = 2;countnum++;
							break;}
					}else if(countnum == 1){
						if(Math.abs((int)(saveboxp[0][0]) - y) > 2 || Math.abs((int)(saveboxp[0][1]) - x) > 2){
							if(checksavebox(m,y,x) == 1 && saveboxdir(m,x,y)){
							saveboxp[i][0] = y;saveboxp[i][1] = x;
							saveboxp[i][2] = (int)(Math.random() * 1000000 % 2);countnum++;
							break;}
						}
					}else if(countnum == 2){
						if(Math.abs((int)(saveboxp[0][0]) - y) > 2 || Math.abs((int)(saveboxp[0][1]) - x) > 2){
							if(Math.abs((int)(saveboxp[1][0]) - y) > 2 || Math.abs((int)(saveboxp[1][1]) - x) > 2){
								if(checksavebox(m,y,x) == 1 && saveboxdir(m,x,y)){
								saveboxp[i][0] = y;saveboxp[i][1] = x;
								saveboxp[i][2] = (int)(Math.random() * 1000000 % 2);countnum++;
								break;}
							}
						}
					}
				}
				buildsaveboxcount++;
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
	static boolean saveboxdir(int [][]m,int boxmx,int boxmy){
		boolean checktrue = false;
		if(m[boxmy+1][boxmx] == 0){//down
			if(m[boxmy+1][boxmx+1] == 0 || m[boxmy+1][boxmx-1] == 0 || m[boxmy+2][boxmx] == 0){
				m[boxmy][boxmx] = 10;
				checktrue = true;
			}
		}else if(m[boxmy][boxmx-1] == 0){//left
			if(m[boxmy+1][boxmx-1] == 0 || m[boxmy-1][boxmx-1] == 0 || m[boxmy][boxmx-2] == 0){
				m[boxmy][boxmx] = 11;
				checktrue = true;
			}
		}else if(m[boxmy-1][boxmx] == 0){//up
			if(m[boxmy-1][boxmx+1] == 0 || m[boxmy-1][boxmx-1] == 0 || m[boxmy-2][boxmx] == 0){
				m[boxmy][boxmx] = 12;
				checktrue = true;
			}
		}else if(m[boxmy][boxmx+1] == 0){//right
			if(m[boxmy+1][boxmx+1] == 0 || m[boxmy-1][boxmx+1] == 0 || m[boxmy][boxmx+2] == 0){
				m[boxmy][boxmx] = 13;
				checktrue = true;
			}
		}
		return checktrue;
	}
	static void spawnkey(int m[][]){
		saveboxp[0][2] = (int)(Math.random() * 1000000 % 2) + 3;//3secfilekey,4secfilepassword
		saveboxkey = new int [saveboxp.length][5];
		for(int i=0;i<saveboxkey.length;i++)
			for(int j=0;j<5;j++)
				saveboxkey[i][j] =0;//turn to zero
		for(int i=0;i<saveboxp.length;i++){
			int spawnkeycount = 0;
			if(saveboxp[i][2] == 0 || saveboxp[i][2] == 3){
				while(true){
					int y = (int)(Math.random() * 1000000 % m.length-1);
					int x = (int)(Math.random() * 1000000 % m[0].length-1);
					if(spawnkeycount++ > 20000){
						if(m[y][x] == 0 && (m[y+1][x] == 0 || m[y][x-1] == 0 || m[y-1][x] == 0 || m[y][x+1] == 0)){
							saveboxkey[i][0] = y;saveboxkey[i][1] = x;
							saveboxkey[i][3] = saveboxp[i][0];saveboxkey[i][4] = saveboxp[i][1];
							m[y][x] = 3;
							break;
						}
					}
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
