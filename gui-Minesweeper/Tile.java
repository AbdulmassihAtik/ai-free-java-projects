public class Tile {
    boolean isMine=false,isClear=false;
    int x,y,num=0;
    public Tile(int pX, int pY){
        x=pX;
        y=pY;
    }
    public boolean isMine(){
        return isMine;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
}
