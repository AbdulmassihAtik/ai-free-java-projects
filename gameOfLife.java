import java.util.Scanner;

public class gameOfLife {
    
    static boolean[][] p;
    static byte[][] n;
    static byte d,it;
    static int x = 0, y = 0;
    static boolean edit = true;
    
    public static void main(String[] args) {
    
        Scanner s = new Scanner(System.in);
        System.out.println("Dimensions:");
        d = s.nextByte();
        //d=20;
        n = new byte[d][d];
        p = new boolean[d][d];
        for(int iy=0;iy<d;iy++){
            for(int ix=0;ix<d;ix++){
                p[ix][iy] = false;
                n[ix][iy] = 0;
            }
        }
        System.out.println("Ileterations:");
        it = s.nextByte();
        //it = 40;
        p[1][0] = true;
        p[2][1] = true;
        p[0][2] = true;
        p[1][2] = true;
        p[2][2] = true;
        
        //p[4][3] = true;
        //p[4][4] = true;
        //p[4][5] = true;
        
        //System.out.println(1);
        //System.out.print("\033[H\033[2J");
        //System.out.flush();
	
	edit();
    
    }

    public static void edit(){
      Scanner scanner = new Scanner(System.in);
      while(edit){
        out();
        System.out.println("Use w a s d to move, e to toggle cell, q to start the game");
        System.out.println("Current position: (" + x + ", " + y + ")");
        String input = scanner.nextLine();
        switch(input){
          case "w":
            if(y>0){y--;}
            break;
          case "a":
            if(x>0){x--;}
            break;
          case "s": 
            if(y<d-1){y++;}
            break;
          case "d":
            if(x<d-1){x++;}
            break;
          case "e":
            p[x][y] = !p[x][y];
            break;
          case "q":
            edit = false;
            break;
          default:
            System.out.println("Invalid input");
            break;
        }
      }

		
	    start();
    }

    public static void start(){
        for(int i=0;i<it;i++){
            frame();
            sleep(200);
        }
        out();
    }
    
    public static void frame(){
        out();
        check();
        
    }
    
    public static void check(){
	//updating neighbor count
        if(p[0][0]==true)  {          n[1][0]++;
                            n[0][1]++;n[1][1]++;}
        if(p[d-1][0]==true) {n[d-2][0]++;
                             n[d-2][1]++;n[d-1][1]++;}
        if(p[0][d-1]==true) {n[0][d-2]++;n[1][d-2]++;
                                         n[1][d-1]++;}
        if(p[d-1][d-1]==true){n[d-2][d-2]++;n[d-1][d-2]++;
                              n[d-2][d-1]++;}
                              
        for(int ix=1;ix<d-1;ix++){if(p[ix][0]==true){n[ix-1][0]++;                n[ix+1][0]++;
                                                     n[ix-1][1]++;n[ix][1]++;n[ix+1][1]++;
                                  }
        }
        for(int ix=1;ix<d-1;ix++){if(p[ix][d-1]==true){n[ix-1][d-2]++;n[ix][d-2]++;n[ix+1][d-2]++;
                                                     n[ix-1][d-1]++;             n[ix+1][d-1]++;
                                  }
        }
        for(int iy=1;iy<d-1;iy++){if(p[0][iy]==true){n[0][iy-1]++;n[1][iy-1]++;
                                                                  n[1][iy]++;
                                                     n[0][iy+1]++;n[1][iy+1]++;
                                  }
        }
        for(int iy=1;iy<d-1;iy++){if(p[iy][d-1]==true){n[d-2][iy-1]++;n[d-1][iy-1]++;
                                                       n[d-2][iy]++;
                                                       n[d-2][iy+1]++;n[d-1][iy+1]++;
                                  }
        }
        
        for(int iy=1;iy<d-1;iy++){
            for(int ix=1;ix<d-1;ix++){
                if(p[ix][iy]==true){
                n[ix-1][iy-1]++;n[ix][iy-1]++;n[ix+1][iy-1]++;
                n[ix-1][iy]++;                n[ix+1][iy]++;
                n[ix-1][iy+1]++;n[ix][iy+1]++;n[ix+1][iy+1]++;
                }
            }
        }
        
		
        for(int iy=0;iy<d;iy++){
            for(int ix=0;ix<d;ix++){
	                  if(p[ix][iy]==true){
                      if(n[ix][iy]<2){p[ix][iy]=false;}
                      if(n[ix][iy]>3){p[ix][iy]=false;}
                    } else {
                    if(n[ix][iy]==3){p[ix][iy]=true;}
                }
            }
       
        } 
    }
    
    public static void out(){
	String out = "";
        for(int iy=0;iy<d;iy++){
            for(int ix=0;ix<d;ix++){
              if(edit && x==ix && y==iy){out += "🟩";} else {
                if(p[ix][iy]==true){out += "⬜";} else {out += "⬛";}
              }
            } //□■
            out += "\n";
        }
        System.out.print("\033[H\033[2J");
        System.out.flush();
	System.out.println(out);
        //System.out.println("----------------------------------------"); //40
        for(int iy=0;iy<d;iy++){
            for(int ix=0;ix<d;ix++){
                n[ix][iy] = 0;
            }
        } 
        
    }
    
    public static void sleep(int pT){
        try{
            Thread.sleep(pT);
        } catch (InterruptedException e){}
    }
}
