package javaschoolexam;

import java.util.Arrays;
import java.util.List;

public class Subsequence {
    public static void main(String[] args) {
        Subsequence s = new Subsequence();
        boolean b = s.find(Arrays.asList("A", "B", "C", "D"),
                Arrays.asList("BD", "A", "ABC", "B", "M", "D", "M", "C", "DC", "D"));
        System.out.println(b); // Result: true
    }

    private boolean find(List<Object> shortLine, List<Object> longLine) {
        int okCount = 0;
        for (Object obj : longLine) {
            if (shortLine.get(okCount).equals(obj)) {
                okCount++;
            }
        }
        return (shortLine.size() == okCount);
    }
}
