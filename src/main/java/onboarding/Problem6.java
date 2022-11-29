package onboarding;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Problem6 {

    public static List<String> solution(List<List<String>> forms) {
        List<String> answer = new ArrayList<>();
        for (List<String> user : forms) {
            List<String> duplicateTools = new ArrayList<>();
            for (int i = 0; i < user.get(1).length() - 1; i++) {
                duplicateTools.add(user.get(1).substring(i, i + 2));
            }
            for (List<String> userInfo : forms) {
                for (String duplicateTool : duplicateTools) {
                    if (userInfo != user && userInfo.get(1)
                            .contains(duplicateTool)) {
                        answer.add(userInfo.get(0));
                    }
                }
            }
        }
        return answer.stream().distinct().sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());
    }
}
