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
        this.setBounds(0,50,810,720);
    }

    public ArrayList<Integer> getNum1(){
        return num1;
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, 810, 720);
        //g.fillOval(0,0,200,200);

        //g.fillRect(10,10,20,20);

        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
//&& cx < i * 20 + 20 - 1 && cy >= j * 20 + 20 + 40 && cy <  j * 20 + 20 + 40 + 20 - 1
                //&& cy >= j * 20 + 20 + 50 && cy <  j * 20 + 50+ 19  &&  cy <  j * 20 + 50 + 57 + 19
                if(cx >= i*16 && cx < i * 16 + 15 && cy >= j * 14 + 57 + 13 &&  cy <  j * 14 + 57 + 13 + 13){
                    g.setColor(Color.BLACK);
                    num1.add(i);
                    num1.add(j);

                }else{
                    g.setColor(Color.WHITE);
                }

                g.fillRect(i * 16, j * 14, 15, 13);
                this.repaint();
            }
        }
    }
}
