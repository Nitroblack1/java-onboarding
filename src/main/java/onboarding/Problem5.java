package onboarding;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Problem5 {
    public static List<Integer> solution(int money) {
        int[] wallet = new int[9];
        int standard = 50_000;
        for(int i = 0; i < 8; i++) {
            wallet[i] = money / standard;
            money %= standard;
            if (i % 2 == 0) {
                standard /= 5;
                continue;
            }
            standard /= 2;
        }
        wallet[8] = money;
        return Arrays.stream(wallet).boxed().collect(Collectors.toList());
    }
}
