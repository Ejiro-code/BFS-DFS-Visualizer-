import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class DFSGUI extends JPanel {
    public int [][] matrix;
    public boolean [][] visited;
    List<Integer> start;

    public Stack<List<Integer>> l;

    public Stack<List<Integer>> maybe;
    List<Integer> end;

    List<List<Integer>> k = new ArrayList<>();

    public DFSGUI(int [][] matrix, List<Integer> start, List<Integer> end){

        this.matrix = matrix;
        this.start = start;
        this.end = end;
        visited = new boolean[30][30];
        l =  new Stack<>();
        maybe = new Stack<>();
        l.push(start);
        maybe.push(start);

        dfs(matrix, l, k,end,maybe);
    }
    public void dfs(int[][] arr, Stack<List<Integer>> l, List<List<Integer>> k, List<Integer> sol,Stack<List<Integer>> maybe){
        SwingWorker<Void, Void> Worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                if (k.contains(sol)) {

                    return null;
                }
                if(getNeighbors(arr, l.peek().get(0), l.peek().get(1)).size() == 0){

                    l.pop();
                    k.add(maybe.peek());
                    visited[maybe.peek().get(0)][maybe.peek().get(1)] = true;
                    l.push(maybe.pop());
                    dfs(arr,l,k,sol,maybe);
                    return null;
                }

                List<Integer> node;

                node = l.pop();

                k.add(node);

                arr[node.get(0)][node.get(1)] = 1;
                visited[node.get(0)][node.get(1)] = true;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                revalidate();
                repaint();

                for (List<Integer> neighbor : getNeighbors(arr, node.get(0), node.get(1))) {

                    l.push(neighbor);
                    dfs(arr, l, k, sol, maybe);
                    maybe.push(neighbor);

                }

                return null;
            }
        };
            Worker.execute();
        }


    public List<List<Integer>> getNeighbors(int[][] arr, int i, int j){
        List<List<Integer>> neighbors = new ArrayList<>();

        //UP
        if(j > 0 && arr[i][j-1] == 0 && visited[i][j-1] == false){
            neighbors.add(new ArrayList<>(Arrays.asList(i,j-1)));
        }
        //RIGHT
        else if(i < arr.length-1 && arr[i+1][j] == 0 && visited[i+1][j] == false){
            neighbors.add(new ArrayList<>(Arrays.asList(i+1,j)));
        }

        //DOWN
        else if(j < arr.length-1 && arr[i][j+1] == 0 && visited[i][j+1] == false){
            neighbors.add(new ArrayList<>(Arrays.asList(i,j+1)));
        }
        //LEFT
        else if(i > 0 && arr[i - 1][j] == 0 && visited[i-1][j] == false){
            neighbors.add(new ArrayList<>(Arrays.asList(i - 1,j)));
        }




        return neighbors;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 50, 400, 500);


        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                if(start.get(0) == i && start.get(1) == j){
                    g.setColor(Color.BLUE);
                }
//                else if(k.contains(end)){
//                    g.setColor(Color.GREEN);
//                }
                else if(end.get(0) == i && end.get(1) == j){
                    g.setColor(Color.BLUE);
                }

                else if(k.contains(Arrays.asList(i,j))){
                    g.setColor(Color.RED);
                }
//                else if(maybe.contains(Arrays.asList(i,j)) && k.contains(Arrays.asList(i,j))){
//                    g.setColor(Color.BLACK);
//                }

                else if(l.contains(Arrays.asList(i,j))){
                    g.setColor(Color.GREEN);

                }

                else{
                    g.setColor(Color.WHITE);
                }
                g.fillRect(i * 20, j * 20 + 50, 19, 19);
                this.repaint();
            }
        }
    }
}
