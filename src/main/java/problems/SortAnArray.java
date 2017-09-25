package problems;

import java.util.Arrays;

/**
 * Created by amarendra on 19/09/17.
 *
 * Sort an array of 0s, 1s and 2s
 *
 * Given an array A[] consisting 0s, 1s and 2s, write a function that sorts A[].
 * The functions should put all 0s first, then all 1s and all 2s in last.
 *
 * Example
 * Input = {0, 1, 1, 0, 1, 2, 1, 2, 0, 0, 0, 1};
 * Output = {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2}
 */
public class SortAnArray {

    public static void main(String[] args) {

        int[] input = {0, 1, 1, 0, 1, 2, 1, 2, 0, 0, 0, 1};
        System.out.println("Before Sort: "+ Arrays.toString(input));
        quickSort(input, 0, input.length-1);
        System.out.println("After Sort: "+ Arrays.toString(input));
    }

    private static void quickSort(int[] input, int start, int end) {
        if (start<end){

            //find partition point
            int pPoint = partition(input,start,end);
            quickSort(input,start,pPoint-1);
            quickSort(input,pPoint+1,end);

        }
    }

    private static int partition(int[] input, int start, int end) {
        int pivot = input[end];
        int pIndex = start;
        for (int i = start; i < end; i++) {
            if (input[i] <= pivot){
                //swap pIndex and input[i]
                int temp = input[pIndex];
                input[pIndex] = input[i];
                input[i] = temp;
                pIndex++;
            }
        }
        //Swap pivot and pIndex
        input[end] = input[pIndex];
        input[pIndex] = pivot;
        return pIndex;
    }
}
