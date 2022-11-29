package onboarding;

public class Problem4 {
    public static String solution(String word) {
        char[] elements = word.toCharArray();
        String upperPattern = "^[A-Z]$";
        String lowerPattern = "^[a-z]$";

        for(int i = 0; i < elements.length; i++) {
            if (String.valueOf(elements[i]).matches(upperPattern)) {
                elements[i] = (char)(155 - elements[i]);
            }
            if (String.valueOf(elements[i]).matches(lowerPattern)) {
                elements[i] = (char)(219 - elements[i]);
            }
        }
        return String.valueOf(elements);
    }
}
