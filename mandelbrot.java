import java.util.Scanner;

public class m {
     
    static double lim, mScale, mScale0;
    static double length, hight;
    static double rl, ud;

    public static void main(String[] args){

    	lim=16;
	mScale=50;
	mScale0=(int)(mScale/2.0);

	int screenScale = 4;
	if(args.length != 0){
		screenScale = Integer.parseInt(args[0]);
	}
	length = 64 * screenScale;
	hight = 18 * screenScale;
    	rl=-length/(2.0*mScale);
	ud=-(double)(hight/2)/mScale0;
	Scanner scanner=new Scanner(System.in);

        while(true){
            print();
            String s = scanner.nextLine();
            switch(s){
                case "a": rl-=25.0/mScale;break;
                case "d": rl+=25.0/mScale;break;
                case "w": ud-=25.0/mScale;break;
                case "s": ud+=25.0/mScale;break;
                case "e": mScale*=2;mScale0=(int)(mScale/2.0);rl+=length/(mScale*2);ud+=hight/(mScale0*2);break;
		case "q": mScale*=0.5;mScale0=(int)(mScale/2.0);rl-=length/(mScale*4);ud-=hight/(mScale0*4);break;
                case "r": lim*=2;break;
                case "f": if(lim>1){lim/=2;}break;
                case "z": System.exit(0);break;
            }
        }
    }
    
    public static void print(){
	String out = "";
        for(int iy=0;iy<hight;iy++){
            for(int ix=0;ix<length;ix++){
                if(in(ix,iy)){
                    out += "8";
                } else {
                    out += ".";
		}
            }
            out += iy;
            out += "\n";
        }
	clear();
	System.out.println(out);
    }
    
    public static boolean in(double ix, double iy){
        double a = (double)(ix/mScale)+rl;
        double b = (double)(iy/mScale0)+ud;
        double x=0,y=0,x0=0;
        for(int i=0;i<lim;i++){
            x0 = x*x-y*y+a;
            y = 2*x*y+b;
            x=x0;
            if(Math.sqrt(x*x+y*y) > 2){
                return false;
            }
        }
        return true;
    } 
    
    public static void sleep(){
        try {
          Thread.sleep(1000);
          } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            }
    }
    
    public static void clear(){
        System.out.print("\033[H\033[2J");  
        System.out.flush();
    }
   
}
