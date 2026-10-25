import java.awt.*;

public class Player {
    Label label0;
    int x=0,y=0;
    public Player(int pS){
        label0 = new Label();
        label0.setVisible(true);
        label0.setBounds(6*pS , 23+6*pS , 14*pS , 14*pS);
        label0.setBackground(new Color(0,255,0));
    }
    public void l(){
        label0.move(label0.getX()-26*Main.scale,label0.getY());
        x--;
    }
    public void u(){
        label0.move(label0.getX(),label0.getY()-26*Main.scale);
        y--;
    }
    public void r(){
        label0.move(label0.getX()+26*Main.scale,label0.getY());
        x++;
    }
    public void d(){
        label0.move(label0.getX(),label0.getY()+26*Main.scale);
        y++;
    }
}
