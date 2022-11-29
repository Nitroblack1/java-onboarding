package onboarding;

import java.util.Arrays;
import java.util.Objects;

public class Problem3 {

    public static int solution(int number) {
        StringBuilder answer = new StringBuilder();
        for (int i = 1; i <= number; i++) {
            answer.append(i);
        }
        return (int) Arrays.stream(answer.toString().split(""))
                .filter(e -> Objects.equals(e, "3") || Objects.equals(e, "6") || Objects.equals(e,
                        "9"))
                .count();
    }
}
