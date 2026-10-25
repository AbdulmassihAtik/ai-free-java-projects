import java.awt.*;
public class Main {
    static MyFrame frame;
    static Tile[][] map;
    static int scale=1,di=100,size=50,dif=1,m=0;
    static Player player;

    public static void main(String[] args){
        frame = new MyFrame();
        map = new Tile[size][size];
        player = new Player(scale);
        //sleep(30);
        frame.add(player.label0);
        frame.repaint();
        //print("1");
        for(int iy=0;iy<size;iy++){
            for(int ix=0;ix<size;ix++){
                map[iy][ix] = new Tile(ix,iy);
                if((int)(Math.random()*10)<dif && iy>0 && ix>0 && iy<size-1 && ix<size-1){
                    map[iy][ix].isMine = true;
                    m++;
                }
            }
        }
        //print("2");
        for(int iy=1;iy<size-1;iy++){
            for(int ix=1;ix<size-1;ix++){
                if(!map[iy][ix].isMine()){
                    if(map[iy-1][ix-1].isMine()){map[iy][ix].num++;}
                    if(map[iy-1][ix].isMine()){map[iy][ix].num++;}
                    if(map[iy-1][ix+1].isMine()){map[iy][ix].num++;}
                    if(map[iy][ix-1].isMine()){map[iy][ix].num++;}
                    if(map[iy][ix+1].isMine()){map[iy][ix].num++;}
                    if(map[iy+1][ix-1].isMine()){map[iy][ix].num++;}
                    if(map[iy+1][ix].isMine()){map[iy][ix].num++;}
                    if(map[iy+1][ix+1].isMine()){map[iy][ix].num++;}
                }
            }
        }
        for(int i=0;i<size;i++){map[0][i].isClear = true;}
        for(int i=0;i<size;i++){map[size-1][i].isClear = true;}
        for(int i=1;i<size;i++){map[i][0].isClear = true;}
        for(int i=1;i<size;i++){map[i][size-1].isClear = true;}
        checkClear();
        //out();
        System.out.println(m);
    }
    public static void number(int pX, int pY, int pN){
        if(pN==1){block(pX,pY,false,false,false,false,true,false,true);}
        if(pN==2){block(pX,pY,true,true,true,false,true,true,false);}
        if(pN==3){block(pX,pY,true,true,true,false,true,false,true);}
        if(pN==4){block(pX,pY,false,true,false,true,true,false,true);}
        if(pN==5){block(pX,pY,true,true,true,true,false,false,true);}
        if(pN==6){block(pX,pY,false,true,true,true,false,true,true);}
        if(pN==7){block(pX,pY,true,false,false,false,true,false,true);}
        if(pN==8){block(pX,pY,true,true,true,true,true,true,true);}
        if(pN==9){block(pX,pY,true,true,true,true,true,false,true);}
    }
    public static void block(int pX,int pY,boolean a,boolean b,boolean c,boolean d,boolean e,boolean f,boolean g){
        Color co = new Color(0,255,0);
        if(a){label(pX*26+9,pY*26+2,8,2,co);}
        if(b){label(pX*26+9,pY*26+12,8,2,co);}
        if(c){label(pX*26+9,pY*26+22,8,2,co);}
        if(d){label(pX*26+7,pY*26+4,2,8,co);}
        if(e){label(pX*26+17,pY*26+4,2,8,co);}
        if(f){label(pX*26+7,pY*26+14,2,8,co);}
        if(g){label(pX*26+17,pY*26+14,2,8,co);}
    }
    public static void label(int pX, int pY, int pW, int pH, Color c){
        Label label= new Label();
        label.setVisible(true);
        label.setBounds(pX*scale,pY*scale+23,pW*scale,pH*scale);
        label.setBackground(c);
        frame.add(label);
    }
    public static void block(int pX,int pY,Color c){
        label(pX*26,pY*26,26,26,c);
    }
    public static void enter(int ix,int iy){
        //checkClear();
        if(map[iy][ix].isMine()){
            drawMine(ix,iy);
            map[iy][ix].isClear=true;
        } else if(map[iy][ix].num>0){
            number(map[iy][ix].getX() , map[iy][ix].getY() , map[iy][ix].num);
            map[iy][ix].isClear=true;
        } else {
            map[iy][ix].isClear=true;
            space(ix,iy);
        }
    }
    public static void space(int ix,int iy){
        block(ix,iy,new Color(0x00000000));
        if(!map[iy][ix+1].isClear){enter(ix+1,iy);}
        if(!map[iy+1][ix+1].isClear){enter(ix+1,iy+1);}
        if(!map[iy+1][ix].isClear){enter(ix,iy+1);}
        if(!map[iy+1][ix-1].isClear){enter(ix-1,iy+1);}
        if(!map[iy][ix-1].isClear){enter(ix-1,iy);}
        if(!map[iy-1][ix-1].isClear){enter(ix-1,iy-1);}
        if(!map[iy-1][ix].isClear){enter(ix,iy-1);}
        if(!map[iy-1][ix+1].isClear){enter(ix+1,iy-1);}
    }
    public static void drawMine(int pX,int pY){
        block(pX,pY,new Color(128,0,0));
    }
    public static void drawFlag(int pX,int pY){
        block(pX,pY,new Color(128,128,0));
    }
    public static void out(){
        for(int iy=0;iy<size;iy++){
            for(int ix=0;ix<size;ix++){
                if(map[iy][ix].isMine()){
                    drawMine(ix,iy);
                }
            }
        }
        for(int iy=1;iy<size;iy++){
            for(int ix=1;ix<size;ix++){
                if(map[iy][ix].num>0){
                    number(map[iy][ix].getX() , map[iy][ix].getY() , map[iy][ix].num);
                }
            }
        }
    }
    public static void sleep(int pS){
        print("1");
        try{
            Thread.sleep(pS);
        } catch (InterruptedException e){}
    }

    public static void print(String pS){
        System.out.println(pS);
    }
    public static void checkClear(){
        for(int iy=0;iy<size;iy++){
            for(int ix=0;ix<size;ix++){
                if(map[iy][ix].isClear){
                    block(ix,iy,new Color(0,43,0));
                }
            }
        }
    }
}
