package onboarding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Problem1 {

    public static int solution(List<Integer> pobiPages, List<Integer> crongPages) {
        try {
            Competitor pobi = new Competitor(pobiPages);
            Competitor crong = new Competitor(crongPages);
            return Competition.findWinner(pobi.findMaximum(), crong.findMaximum());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }
}

class Competitor {

    List<List<String>> competitorPages;

    Competitor(List<Integer> pageInput) {
        Validation.validatePageInput(pageInput);
        competitorPages = new ArrayList<>();
        competitorPages.add(Arrays.asList(pageInput.get(0).toString().split("")));
        competitorPages.add(Arrays.asList(pageInput.get(1).toString().split("")));
    }

    public int findMaximum() {
        FindMaximum findMaximum = new FindMaximum();
        return findMaximum.findPageMaximum(competitorPages);
    }
}

class FindMaximum {

    int max;
    int plusTemp;
    int mulTemp;

    FindMaximum() {
        max = 0;
        plusTemp = 0;
        mulTemp = 1;
    }

    public int findPageMaximum(List<List<String>> target) {
        for (List<String> page : target) {
            for (String element : page) {
                plusTemp += Integer.parseInt(element);
                mulTemp *= Integer.parseInt(element);
            }
            max = Math.max(max, Math.max(plusTemp, mulTemp));
        }
        return max;
    }
}

class Competition {

    public static int findWinner(int pobi, int crong) {
        if (pobi > crong) {
            return 1;
        }
        if (pobi < crong) {
            return 2;
        }
        return 0;
    }
}

class Validation {
    private static final int LEFT_PAGE_INDEX = 0;
    private static final int RIGHT_PAGE_INDEX = 1;
    private static final int PAGES_LIST_SIZE = 2;

    public static void validatePageInput(List<Integer> pageInput) {
        if (pageInput.size() != PAGES_LIST_SIZE) {
            throw new IllegalArgumentException("Wrong size input");
        }
        if (pageInput.get(RIGHT_PAGE_INDEX) - pageInput.get(LEFT_PAGE_INDEX) != 1) {
            throw new IllegalArgumentException("Wrong page input");
        }
    }
}