package amar.ds;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by amarendra on 16/02/16.
 */
public class PermutationFinder {
    public static Set<String> permutationFinder(String str) {
        Set<String> perm = new HashSet<>();
        //Handling error scenarios
        if (str == null) {
            return null;
        } else if (str.length() == 0) {
            perm.add("");
            return perm;
        }
        char initial = str.charAt(0); // first character
        String rem = str.substring(1); // Full string without first character
        Set<String> words = permutationFinder(rem);
        for (String strNew : words) {
            for (int i = 0;i<=strNew.length();i++){
                perm.add(charInsert(strNew, initial, i));
            }
        }
        return perm;
    }

    public static String charInsert(String str, char c, int j) {
        String begin = str.substring(0, j);
        String end = str.substring(j);
        return begin + c + end;
    }

    public static void main(String[] args) {
        String s = "AMARENDRA";
        String s1 = "ABC";
        String s2 = "ABCD";
        long startTime = new Date().getTime();
        System.out.println("\nPermutations for " + s + " are: \n" + permutationFinder(s));
        long endTime = new Date().getTime();
        System.out.println("Time taken by " + s+ " is " + (endTime - startTime));

        startTime = new Date().getTime();
        System.out.println("\nPermutations for " + s1 + " are: \n" + permutationFinder(s1));
        endTime = new Date().getTime();
        System.out.println("Time taken by " + s1+ " is " + (endTime - startTime));

        startTime = new Date().getTime();
        System.out.println("\nPermutations for " + s2 + " are: \n" + permutationFinder(s2));
        endTime = new Date().getTime();
        System.out.println("Time taken by " + s2+ " is " + (endTime - startTime));
    }
}