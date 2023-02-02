import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Board extends JPanel {

    public int cx = -100;
    public int cy = -100;
    ArrayList<Integer> num1 = new ArrayList<>();

    public Board(int cx, int cy){
        this.cx = cx;
        this.cy = cy;

        System.out.println("cx: " + cx +"  cy: "+ cy);
    }

    public ArrayList<Integer> getNum1(){
        return num1;
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 50, 400, 450);
        //g.fillOval(0,0,200,200);

        //g.fillRect(10,10,20,20);

        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
//&& cx < i * 20 + 20 - 1 && cy >= j * 20 + 20 + 40 && cy <  j * 20 + 20 + 40 + 20 - 1
                //&& cy >= j * 20 + 20 + 50 && cy <  j * 20 + 50+ 19  &&  cy <  j * 20 + 50 + 57 + 19
                    if(cx >= i*20 && cx < i * 20 + 19 && cy >= j * 20 + 57 + 19 &&  cy <  j * 20 + 57 + 19 + 19){
                        g.setColor(Color.BLACK);
                        num1.add(i);
                        num1.add(j);

                    }else{
                        g.setColor(Color.WHITE);
                    }

                g.fillRect(i * 20, j * 20 + 50, 19, 19);
                this.repaint();
            }
        }
    }
}
