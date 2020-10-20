
/**
 * We solved the Tower of Hanoi using recursion and iteration and also calculated the minimum number of moves to solve it.  We also solved the Tower of Hanoi making 
 *only adjacent moves and calculated the minimum number of moves to do so.
 * @Nick Cannone
 * @9/16/20
 */
import java.util.*;
public class Hanoi
{
    public static ArrayList<Integer> s = new ArrayList<Integer>();
    public static ArrayList<Integer> a = new ArrayList<Integer>();
    public static ArrayList<Integer> d = new ArrayList<Integer>();
    public static ArrayList<Integer> temp = new ArrayList<Integer>();
    public static void main(String[]args)
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Input the number of discs");
        int numDiscs = scan.nextInt();
        System.out.println("Using recursion: ");
        recHanoi(numDiscs, "source", "aux", "dest");
        System.out.println();
        System.out.println();
        System.out.println("Using iteration: ");
        itHanoi(numDiscs, "source", "aux", "dest");
        System.out.println();
        System.out.println();
        System.out.println("Minimum number of moves (non-adjacent): ");
        System.out.println(numMoves(numDiscs));
        System.out.println();
        System.out.println();
        System.out.println("Hanoi puzzle with only adjacent moves: ");
        hanoiAdj(numDiscs, "source", "aux", "dest");
        System.out.println();
        System.out.println();
        System.out.println("Minimum number of moves (adjacent): ");
        System.out.println(numMovesAdj(numDiscs));

    }

    public static void recHanoi(int numDiscs,String source, String aux, String dest)//reccursive algorithm
    {
        if(numDiscs == 1)//base case for the biggest disc
        {
            System.out.println(source  + " --> " + dest);
        }
        else
        {
            recHanoi(numDiscs-1, source, dest, aux);
            System.out.println(source + " --> " + dest); 
            recHanoi(numDiscs-1, aux, source, dest);
        }
    }

    public static void itHanoi(int numDiscs, String source, String aux, String dest)//iteration calculation
    {
        int numMoves = (int)(Math.pow(2, numDiscs) - 1);
        if(numDiscs % 2 == 0)
        {
            aux = "dest";
            dest = "aux";
        }
        for(int i = 1; i <= numDiscs; i ++)
            s.add(i);
        for(int i = 1; i <=numMoves; i ++)
        {
            if(numDiscs % 2 == 0)
            {
                if (i % 3 == 1) 
                    makeMove(s, a, source, dest);
                else if (i % 3 == 2)
                    makeMove(s, d, source, aux); 
                else if (i % 3 == 0)
                    makeMove(d, a, aux, dest);
            }
            else{
                if (i % 3 == 1) 
                    makeMove(s, d, source, dest);
                else if (i % 3 == 2)
                    makeMove(s, a, source, aux); 

                else if (i % 3 == 0) 
                    makeMove(a, d, aux, dest);
            }
        }

    }

    static void makeMove(ArrayList<Integer> l1, ArrayList<Integer> l2, String s1, String s2)//helper method for iteration program
    {
        if(l1.size() == 0)
        {
            System.out.println(s2 + "-->" + s1);
            if(l2.size() > 0)
            {
                l1.add(l2.get(l2.size()-1));
                l2.remove(l2.size()-1);
            } 
            else
            {
                l1.add(l2.get(l2.size()));
                l2.remove(l2.size());
            }
        }
        else if(l2.size() == 0)
        {
            System.out.println(s1 + "-->" + s2);
            if(l1.size() > 0)
            {
                l2.add(l1.get(l1.size()-1));
                l1.remove(l1.size()-1);
            }
            else
            {
                l2.add(l1.get(l1.size()));
                l1.remove(l1.size());
            }
        }
        else if(l1.get(l1.size()-1) < l2.get(l2.size()-1))
        {
            System.out.println(s2 + "-->" + s1);
            l1.add(l2.get(l2.size()-1));
            l2.remove(l2.size()-1);
        }
        else
        {
            System.out.println(s1 + "-->" + s2);
            l2.add(l1.get(l1.size()-1));
            l1.remove(l1.size()-1);
        }
    } 

    public static int numMoves(int numDiscs)//calculates min num of moves to solve the hanoi puzzle
    {
        int numMoves = (int)(Math.pow(2, numDiscs) - 1);
        return numMoves;
    }

    static void hanoiAdj(int numDisk,String source, String aux, String dest)//solves adjacent hanoi
    {
        if(numDisk == 1)
        {
            System.out.println(source + "-->" + aux);
            System.out.println(aux + "-->" + dest);
            return;
        }
        else
        {
            hanoiAdj(numDisk-1, source, aux, dest);
            System.out.println(source + "-->" + aux);
            hanoiAdj(numDisk-1, dest, aux, source);
            System.out.println(aux + "-->" + dest);
            hanoiAdj(numDisk-1, source, aux, dest);
        }
    }

    static int numMovesAdj(int numDiscs)//calculates min number of moves to solve adj hanoi
    {
        if(numDiscs == 1)
            return 2;
        return 3*numMovesAdj(numDiscs-1) + 2;
    }
}