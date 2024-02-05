import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class dijkstra {
    public static void main(String[] args) {
        int[][] map = getMap();
        Scanner myObj = new Scanner(System.in);
        
        System.out.println("Enter a starting value:");
        int start = myObj.nextInt();
        System.out.println("Enter a ending value:");
        int end = myObj.nextInt();
        dj(start, end, map);
        LinkedList<Integer> distance = dj(start, end, map);
        printPath(distance);
        myObj.close();

        
    }

    public static int[][] getMap(){
        int[][] map = {{0, 53, 10, 12,  0,  0,  0,  0,  0,  0},
                       {53, 0, 33,  0,  2,  0,101,  0,  0,  0},
                       {10,33,  0,  9, 30, 18,  0,  0,  0,  0},
                       {12, 0,  9,  0,  0, 17,  0,  0, 6,   0},
                       {0,  2, 30,  0,  0, 14,123,122,  0,  0},
                       {0,  0, 18, 17, 14,  0,  0,137,  7,  0},
                       {0,101,  0,  0,123,  0,  0,  8,  0, 71},
                       {0,  0,  0,  0,122,137,  8,  0,145, 66},
                       {0,  0,  0,  6,  0,  7,  0,145,  0,212},
                       {0,  0,  0,  0,  0,  0, 71, 66,212, 0}};
        return map;
    }

    public static LinkedList<Integer> dj(int start, int end, int[][] matrix){
        int[] distance = new int[matrix.length];
        int[] last = new int[matrix.length];
        boolean[] visited = new boolean[matrix.length];
        int current = start;
        LinkedList<Integer> path = new LinkedList<>();
 
        while (current != end){
            visited[current] = true;
            for (int i = 0; i < matrix.length; i++) {
                if (matrix[current][i] != 0) {
                    int d = distance[current] + matrix[current][i];
                    if (d < distance[i] || distance[i] == 0 && i != start) {
                        distance[i] = d;
                        last[i] = current;
                    }
                }
            }

            int small = Integer.MAX_VALUE;
            int next = 0;
            for (int i = 0; i < distance.length; i++) {
                if(distance[i] < small && distance[i] > 0 && !visited[i]){
                    small = distance[i];
                    next = i;
                }
            }
            current = next;
        }
 
        while (current != start){
            path.add(0, current);
            int lastnode = last[current];
            current = lastnode;
        }
        return path;
    }

    public static void printPath(LinkedList<Integer> path){
        System.out.println();
        for(int i = 0; i < path.size(); i++){
            System.out.println(path.get(i));
        }
    }
    
    
}
