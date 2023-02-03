import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class BFSGUI extends JPanel {
    public int [][] matrix;
    public boolean [][] visited;
    List<Integer> start;

    public Queue<List<Integer>> l;

    List<Integer> end;

    List<List<Integer>> k = new ArrayList<>();

    public BFSGUI(int [][] matrix, List<Integer> start, List<Integer> end){
        this.setBounds(0,50,810,720);
        this.matrix = matrix;
        this.start = start;
        this.end = end;
        visited = new boolean[50][50];
        l =  new LinkedList<>();

        l.add(start);


        bfs(matrix, l, k,end);
    }
    public void bfs(int[][] arr, Queue<List<Integer>> l, List<List<Integer>> k, List<Integer> sol){
        SwingWorker<Void, Void> Worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                if (k.contains(sol) || l.contains(sol)) {
                    return null;
                }
//                if(getNeighbors(arr, l.peek().get(0), l.peek().get(1)).size() == 0){
//
//                    return null;
//                }
                List<Integer> node;
                node = l.poll();

                k.add(node);

                arr[node.get(0)][node.get(1)] = 1;
                visited[node.get(0)][node.get(1)] = true;
                try {
                    Thread.sleep(2);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                revalidate();
                repaint();

                for (List<Integer> neighbor : getNeighbors(arr, node.get(0), node.get(1))) {
                    l.add(neighbor);

                }
                bfs(arr, l, k, sol);

                return null;
            }
        };
        Worker.execute();
    }
    public List<List<Integer>> getNeighbors(int[][] arr, int i, int j){
        List<List<Integer>> neighbors = new ArrayList<>();

        //UP
        if(j > 0 && arr[i][j-1] == 0 && visited[i][j-1] == false){
            visited[i][j-1] = true;
            neighbors.add(new ArrayList<>(Arrays.asList(i,j-1)));
        }
        //RIGHT
        if(i < arr.length-1 && arr[i+1][j] == 0 && visited[i+1][j] == false){
            visited[i+1][j] = true;
            neighbors.add(new ArrayList<>(Arrays.asList(i+1,j)));
        }
        //DOWN
        if(j < arr.length-1 && arr[i][j+1] == 0 && visited[i][j+1] == false){
            visited[i][j+1] = true;
            neighbors.add(new ArrayList<>(Arrays.asList(i,j+1)));
        }
        //LEFT
        if(i > 0 && arr[i - 1][j] == 0 && visited[i-1][j] == false){
            visited[i-1][j] = true;
            neighbors.add(new ArrayList<>(Arrays.asList(i - 1,j)));
        }

        return neighbors;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, 810, 720);


        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                if(start.get(0) == i && start.get(1) == j){
                    g.setColor(Color.BLUE);
                }

                else if(end.get(0) == i && end.get(1) == j && (!l.contains(end))){
                    g.setColor(Color.BLUE);
                }
                else if(end.get(0) == i && end.get(1) == j && (l.contains(end))){
                    g.setColor(Color.CYAN);
                }
                else if(k.contains(Arrays.asList(i,j))){
                    g.setColor(Color.RED);
                }
                else if(l.contains(Arrays.asList(i,j))){
                    g.setColor(Color.GREEN);
                }

                else{
                    g.setColor(Color.WHITE);
                }
                g.fillRect(i * 16, j * 14, 15, 13);
                //this.repaint();
            }
        }
    }
}
