package javaschoolexam;

import java.util.Arrays;

public class Pyramid {
    public static void main(String[] args) throws CannotBuildPyramidException {
        int [] inputA = {2, 8, 9, 4, 4, 5, 5, 6, 6, 9};
        Pyramid p = new Pyramid();
        p.builtPyramid(inputA);
    }

    private void builtPyramid (int [] input) throws CannotBuildPyramidException {
        int lineCount = 1;
        int numsInLine = 1;
        Arrays.sort(input);
        int lCounter = 0;
        int check;
        try {
        for (int i = 1; i <= input.length; i++){
            lCounter +=i;
            if (lCounter == input.length) break;
            check = input[lCounter];
            lineCount++;
            numsInLine = numsInLine +2;
        }
        } catch (Exception e){
            throw new CannotBuildPyramidException (e);
        }
        int counter = 0;
        int lenghtCounter = 1;
        int [] withzero = new int[numsInLine];
        for (int i = 1; i <= lineCount; i++){
            System.arraycopy(input,counter,withzero,withzero.length/2-i+1,lenghtCounter);
            counter += i;
            lenghtCounter++;
            addZero(withzero);
        }
    }

    public void addZero (int [] nums){
        int [] newWithzero = new int[nums.length];
        int cnt = 0;
        for (int i = 0; i <= nums.length/2; i++){
            if (nums [i] == 0) {
                newWithzero [cnt] = nums [i];
                cnt++;
            } else {
                newWithzero [cnt] = nums [i];
                cnt = cnt + 2;
            }
        }
        System.out.println(Arrays.toString(newWithzero));
    }
}

class CannotBuildPyramidException extends Exception{
    public CannotBuildPyramidException (Throwable e) {
    }
}