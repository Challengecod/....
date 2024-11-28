import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        File f = new File("/Users/andyfang/IdeaProjects/practice/src/Input.txt");
        Scanner s = new Scanner(f);

        ArrayList<String> lines = new ArrayList<>();
        while (s.hasNext()) {
            String line = s.nextLine();
            lines.add(line);
        }

        char[][] grid = new char[lines.size()][lines.get(0).length()];
        for (int i = 0; i < grid.length; i++) {
            String oneLine = lines.get(i);
            for (int j = 0; j < oneLine.length(); j++) {
                char c = oneLine.charAt(j);
                grid[i][j] = c;
            }
        }

        ArrayList<Character> ans = new ArrayList<>();
        ArrayList<Character> total = new ArrayList<>();

        int sum = 0;
        int m = grid[0].length;
        String num = "";

            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (Character.isDigit(grid[i][j])) {

                        total.add(grid[i][j]);
                        ans = getAdjacent(grid, i, j);
                        System.out.println(total);
                        System.out.println(ans);

                        if(!checkSymbol(ans)) {
                            if (j == m-1) {
                                total.clear();
                            }

                            else if (!Character.isDigit(grid[i][j + 1])) {
                                    total.clear();
                                }
                        }

                        if (checkSymbol(ans)) {
                            System.out.println(total);
                            if (!Character.isDigit(grid[i][j + 1])) {
                                num = toString(total);
                                System.out.println(num);
                                sum += toInteger(num);
                            }

                            if (Character.isDigit(grid[i][j + 1])) {
                                try {
                                    while (Character.isDigit(grid[i][j + 1])) {
                                        char c = grid[i][j + 1];
                                        j = j + 1;
                                        total.add(c);
                                    }
                                }
                                catch (ArrayIndexOutOfBoundsException e) {

                                }

                                num = toString(total);
                                System.out.println(num);
                                sum += toInteger(num);
                            }

                            total.clear();
                        }

                    }

                }
            }

        System.out.println(sum);

    }

    public static ArrayList<Character> getAdjacent(char[][] arr, int i, int j) {
        // Size of given 2d array
        int n = arr.length;
        int m = arr[0].length;
        // Initialising a vector array where
        // adjacent elements will be stored
        ArrayList<Character> v = new ArrayList<>();
        // top left [0][0]
        if (i - 1 > 0 && j - 1 > 0) {
            v.add(arr[i - 1][j - 1]);
        }
        //top [0][1]
        if (i - 1 > 0 && j > 0) {
            v.add(arr[i - 1][j]);
        }
        //top right [0][2]
        if (i - 1 > 0 && j + 1 < m - 1) {
            v.add(arr[i - 1][j + 1]);
        }
        // left [1,0]
        if (j - 1 > 0) {
            v.add(arr[i][j - 1]);
        }
        // right [1,2]
        if (i > 0 && j + 1 < m - 1) {
            v.add(arr[i][j + 1]);
        }
        // bottom left [2,0]
        if (i + 1 < n -1 && j - 1 > 0) {
            v.add(arr[i + 1][j - 1]);
        }

        // bottom [2,1]
        if (i + 1 < n -1) {
            v.add(arr[i + 1][j]);
        }
        // bottom right [2,2]
        if (i + 1 < n -1 && j + 1 < m -1) {
            v.add(arr[i + 1][j + 1]);
        }
        // Returning the vector array
        return v;
    }

    public static boolean checkSymbol(ArrayList<Character> ans) {
        for(int i =0; i < ans.size(); i++){
            if(ans.get(i) != '.' && !Character.isDigit(ans.get(i))){
                return true;
            }
        }
        return false;
    }

    public static String toString(ArrayList<Character> a)
    {

        String myString2 = "";
        for ( int i =0; i < a.size(); i++){
             myString2 += Character.toString(a.get(i));
        }

        return myString2;
    }

    public static int toInteger(String a)
    {
        return Integer.parseInt(a);
    }

}

