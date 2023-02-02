import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class GUI extends JFrame {
    public int [][] grid = new int[20][20];
    public int spacing = 5;

    public int cx = -100;
    public int cy = -100;
    ArrayList<Integer> nums1;
    ArrayList<Integer> nums2;
    int clickCounter = 0;
    Board board;
    public GUI(){
        this.setTitle("Grid");
        this.setSize(415,500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);

        board = new Board(cx,cy);
        this.add(board);

        Click click1 = new Click();
        this.addMouseListener(click1);

        nums1 = new ArrayList<>();
        nums1.add(10);
        nums1.add(19);

        nums2 = new ArrayList<>();
        nums2.add(1);
        nums2.add(19);

//        if(clickCounter == 2){
//            DFSGUI test = new DFSGUI(grid, nums1, nums2);
//            this.add(test);
//        }
        //DFSGUI test = new DFSGUI(grid, nums1, nums2);
        this.remove(board);
        BFSGUI test1 = new BFSGUI(grid, nums1, nums2);
        this.add(test1);

    }

    public class Click implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {

            cx = e.getX();
            cy = e.getY();
            System.out.println("Mouse was clicked" );
            board = new Board(cx,cy);
            setContentPane(board);
            revalidate();
            repaint();
            nums1 = board.getNum1();

            if(clickCounter == 1){
                nums2 = board.getNum1();
            }

            clickCounter++;
            //
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }

    }



    public static void main(String[] args) {
        GUI name = new GUI();
    }
}
