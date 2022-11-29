package onboarding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Problem2 {

    public static String solution(String cryptogram) {
        StringBuilder answer = new StringBuilder();
        List<String> current = new ArrayList<>();
        List<String> cryptoList = new ArrayList<>(Arrays.asList(cryptogram.split("")));
        boolean isDuplicated = false;
        while (cryptoList.size() != current.size()) {
            current = List.copyOf(cryptoList);
            int index = 0;
            while (index < cryptoList.size() - 1) {
                while (cryptoList.size() > 1 && Objects.equals(cryptoList.get(index),
                        cryptoList.get(index + 1))) {
                    cryptoList.remove(index + 1);
                    isDuplicated = true;
                }
                if (isDuplicated) {
                    cryptoList.remove(index);
                    isDuplicated = false;
                    if (cryptoList.size() == 0) {
                        return "";
                    }
                } else {
                    index++;
                }
            }
        }
        for (String element : cryptoList) {
            answer.append(element);
        }
        return answer.toString();
    }
}