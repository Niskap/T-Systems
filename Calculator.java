package javaschoolexam;


import java.util.Arrays;
import java.util.LinkedList;

public class Calculator {
    public static void main(String[] args) {
        Calculator c = new Calculator();
        System.out.println(c.evaluate("(1+38)*4-5")); // Result: 151
        System.out.println(c.evaluate("7*6/2+8")); // Result: 29
        System.out.println(c.evaluate("-12)1//(")); // Result: null

    }
    public String evaluate (String s){
        LinkedList<String> withParentheses = new LinkedList<>();
        for (int i = 0; i < findNumbers(s).length; i++){
            if (findNumbers(s)[i] != null){
                withParentheses.add(findNumbers(s)[i]);
            }
        }
        while (withParentheses.contains("(") || withParentheses.contains(")")){
            String [] arr = new String[withParentheses.size()];
            withParentheses.toArray(arr);
            withParentheses = removeParentheses(arr);
        }
        String result = calculate(withParentheses);
        if (result != null){
            double d = Double.parseDouble(result);
            int i = (int) d;
            if((d - i) < 0.000001)
                return "Result: " + i;
            else {
                return "Result: " + (double) Math.round(d*10000)/10000;
            }
        } else return "Result: null";
    }

    private String [] findNumbers (String s){
        char [] charArray = s.toCharArray ();
        String [] numsAndDelimiters = new String[s.length()];
        int k = 1;
        numsAndDelimiters [0] = String.valueOf(charArray [0]);
        for (int i = 1; i < charArray.length; i++) {
            if ((Character.isDigit(charArray[i])||(charArray[i] == '.')) && (Character.isDigit(charArray[i-1])||(charArray[i-1] == '.'))) {
                numsAndDelimiters[k-1] = numsAndDelimiters[k-1] + String.valueOf(charArray[i]);
            } else {
                numsAndDelimiters [k] = String.valueOf(charArray[i]);
                k++;
            }
        }
        return numsAndDelimiters;
    }

    private LinkedList <String> removeParentheses (String [] s){
        LinkedList <String> inPar = new LinkedList<>();
        LinkedList <String> allStr = new LinkedList<>();
            int left = 0;
            int right = 0;
            for (int i = 0; i < s.length; i++) {
                if ("(".equals(s[i])) {
                    left = i;
                }
            }
            for (int i = left; i < s.length; i++) {
                if (")".equals(s[i])) {
                    right = i;
                    break;
                }
            }
            if (left > right) return allStr;

        inPar.addAll(Arrays.asList(s).subList(left + 1, right));
        allStr.addAll(Arrays.asList(s).subList(0, left));
        allStr.add(calculate(inPar));
            for (int i = right + 1; i < s.length; i++){
                if(s[i] != null)
                allStr.add(s[i]);
            }
        return allStr;
    }

    private String calculate (LinkedList <String> noParenthese) {
        try {
            for (int i = 0; i < noParenthese.size(); i++) {
                if (noParenthese.get(i).equals("*")) {
                    noParenthese.set(i + 1, String.valueOf(Double.parseDouble(noParenthese.get(i - 1)) * Double.parseDouble(noParenthese.get(i + 1))));
                    noParenthese.remove(i-1);
                    noParenthese.remove(i-1);
                    i--;
                }
                if (noParenthese.get(i).equals("/")) {
                    noParenthese.set(i + 1, String.valueOf(Double.parseDouble(noParenthese.get(i - 1)) / Double.parseDouble(noParenthese.get(i + 1))));
                    noParenthese.remove(i-1);
                    noParenthese.remove(i-1);
                    i--;
                }
            }

            for (int i = 0; i < noParenthese.size(); i++) {
                if (noParenthese.get(i).equals("+")) {
                    noParenthese.set(i + 1, String.valueOf(Double.parseDouble(noParenthese.get(i - 1)) + Double.parseDouble(noParenthese.get(i + 1))));
                    noParenthese.remove(i-1);
                    noParenthese.remove(i-1);
                    i--;
                }
                if (noParenthese.get(i).equals("-")) {
                    noParenthese.set(i + 1, String.valueOf(Double.parseDouble(noParenthese.get(i - 1)) - Double.parseDouble(noParenthese.get(i + 1))));
                    noParenthese.remove(i-1);
                    noParenthese.remove(i-1);
                    i--;
                }
            }
            return noParenthese.get(0);
        } catch (Exception e){
            return null;
        }
    }
}
