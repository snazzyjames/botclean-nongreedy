/**
 * Created by Snazzy on 10/2/14.
 */


import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    static void next_move(int posr, int posc, String[] board){

        ArrayList<Integer []> dirtyCells = getDirtyCells(board);
        Integer[] closest = findClosest(posr, posc, dirtyCells);
        Integer verticalDifference;
        Integer horizontalDifference;

        //System.out.println("Closest is " + Arrays.toString(closest));

        //System.out.println("Position is [" + posr + "," + posc + "]");

        verticalDifference = posr - closest[0];
        horizontalDifference = closest[1] - posc;

        //System.out.println("vertDiff: " + verticalDifference);
        //System.out.println("horDiff: " + horizontalDifference);

        if (verticalDifference < 0) {
            System.out.println("DOWN");
            return;
        }
        if (verticalDifference > 0) {
            System.out.println("UP");
            return;
        }

        if (horizontalDifference < 0){
            System.out.println("LEFT");
            return;
        }
        if(horizontalDifference > 0){
            System.out.println("RIGHT");
            return;
        }

        if(verticalDifference == 0 && horizontalDifference == 0) {
            System.out.println("CLEAN");
        }

    }

    private static Integer[] findClosest(int posr, int posc, ArrayList<Integer []> dirtyCells){

        Integer[] closest = {};
        Integer[] closestDistance = {};
        ArrayList<ArrayList<Integer[]>> nodeDistances = new ArrayList<ArrayList<Integer[]>>();

        for(Integer[] dirtyCell : dirtyCells){
            ArrayList<Integer []> nodeDistance = new ArrayList<Integer[]>();
            nodeDistance.add(0, dirtyCell);
            nodeDistance.add(1,new Integer[]{(posr- dirtyCell[0]), dirtyCell[1] - posc});
            nodeDistances.add(nodeDistance);
        }

        for(ArrayList<Integer[]> nodeDistance : nodeDistances){

            //System.out.println("Node: " + Arrays.toString(nodeDistance.get(0)) + " Distance: " + (Arrays.toString(nodeDistance.get(1))));
            if(Arrays.equals(nodeDistance.get(1), new Integer[]{0,0})){
                return nodeDistance.get(0);
            }
            else if(closest.length == 0){
                closest = nodeDistance.get(0);
                closestDistance = nodeDistance.get(1);
            }

            Integer[] distance = nodeDistance.get(1);

            if(Math.abs(distance[0]) < closestDistance[0] && Math.abs(distance[1]) < closestDistance[1]){
                closest = nodeDistance.get(0);
            }
        }

        return closest;
    }

    private static ArrayList<Integer[]> getDirtyCells(String[] board){

        ArrayList<Integer[]> dirtyCells = new ArrayList<Integer []>();

        for(int i = 0; i < board.length; i++){
            int lastIndex = 0;
            String row = board[i];
            for(int j=0; j<row.length(); j++){
                if(row.charAt(j) == 'd'){
                    dirtyCells.add(new Integer[]{i,j});
                }
            }
        }

        return dirtyCells;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int [] pos = new int[2];
        String board[] = new String[5];
        for(int i=0;i<2;i++) pos[i] = in.nextInt();
        for(int i=0;i<5;i++) board[i] = in.next();
        next_move(pos[0], pos[1], board);
    }
}
