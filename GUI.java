import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class GUI extends JFrame {
    public int [][] grid = new int[50][50];
    public int spacing = 5;

    public int cx = -100;
    public int cy = -100;
    ArrayList<Integer> nums1;
    ArrayList<Integer> nums2;
    int clickCounter = 0;

    JButton bfs = new JButton();
    JButton dfs = new JButton();
    Board board;
    public GUI(){
        nums1 = new ArrayList<>();
        nums1.add(25);
        nums1.add(25);

        nums2 = new ArrayList<>();
        nums2.add(0);
        nums2.add(49);


        bfs.setBounds(0,10,150,30);
        bfs.setText("BFS");
        bfs.setFocusable(false);

        dfs.setBounds(180,10,150,30);
        dfs.setText("DFS");
        dfs.setFocusable(false);

        this.setTitle("Grid");
        this.setSize(815,800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setBackground(Color.BLACK);
        this.getContentPane().setBackground(Color.BLACK);
        this.add(bfs);
        this.add(dfs);
        this.setVisible(true);
        //this.setResizable(false);
        board = new Board(cx,cy);
        this.add(board);

        bfs.addActionListener(e ->{

            this.remove(board);
            BFSGUI test1 = new BFSGUI(grid, nums1, nums2);
            this.add(test1);
            //entered = true;
            //SwingUtilities.updateComponentTreeUI(this);
            //this.remove(bs);
        });

        dfs.addActionListener(e ->{

            this.remove(board);
            DFSGUI test = new DFSGUI(grid, nums1, nums2);
            this.add(test);
        });

        Click click1 = new Click();
        this.addMouseListener(click1);



//        if(clickCounter == 2){
//            DFSGUI test = new DFSGUI(grid, nums1, nums2);
//            this.add(test);
//        }
        //DFSGUI test = new DFSGUI(grid, nums1, nums2);


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
