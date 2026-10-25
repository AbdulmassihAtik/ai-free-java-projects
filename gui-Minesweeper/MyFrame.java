import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyFrame extends Frame implements KeyListener {
    public MyFrame(){
        this.setSize(1440,1400);
        this.setVisible(true);
        this.addKeyListener(this);
        this.setLayout(null);
        this.setBackground(new Color(0x00002b));
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()){
            case 37: Main.player.l();
                break;
            case 38: Main.player.u();
                break;
            case 39: Main.player.r();
                break;
            case 40: Main.player.d();
                break;
            case 69: Main.enter(Main.player.x,Main.player.y);
                break;
            case 70: if(Main.map[Main.player.y][Main.player.x].isMine()){
                Main.drawFlag(Main.player.x,Main.player.y);
            } else Main.drawMine(Main.player.x,Main.player.y);
                break;
            case 10: Main.out();
        }
        //Main.checkClear();
        //System.out.println(keyEvent.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}
