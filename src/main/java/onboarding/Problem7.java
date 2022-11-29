package onboarding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class Problem7 {

    public static List<String> solution(String user, List<List<String>> friends,
            List<String> visitors) {
        FriendsOfUser friendsOfUser = new FriendsOfUser(friends, user);
        Friends allFriends = new Friends(friends, visitors);
        FriendAlgorithm friendAlgorithm = new FriendAlgorithm(allFriends, friendsOfUser, user);
        return friendAlgorithm.recommendFriends(friends, friendsOfUser, visitors);
    }
}

class Friends {

    private List<String> allFriends; // andole, jun, bedi

    Friends(List<List<String>> friends, List<String> visitors) {
        allFriends = new ArrayList<>();
        for (List<String> friend : friends) {
            allFriends.add(friend.get(0));
            allFriends.add(friend.get(1));
        }
        allFriends.addAll(visitors);
        allFriends = allFriends.stream().distinct().collect(Collectors.toList());
    }

    public List<String> getAllFriends() {
        return allFriends;
    }
}

class FriendsOfUser {

    private List<String> friendsOfUser; // donut, shakevan

    FriendsOfUser(List<List<String>> friends, String user) {
        friendsOfUser = new ArrayList<>();
        for (List<String> friend : friends) {
            if (friend.contains(user)) {
                this.friendsOfUser.add(friend.stream().filter(e -> !e.matches(user))
                        .collect(Collectors.joining()));
            }
        }
    }

    public List<String> getFriendsOfUser() {
        return friendsOfUser;
    }
}

class FriendAlgorithm {

    Map<String, Integer> unknownUsersScore;

    FriendAlgorithm(Friends allFriends, FriendsOfUser friendsOfUser, String user) {
        unknownUsersScore = new HashMap<>();
        for (String unknownUser : allFriends.getAllFriends().stream()
                .filter(e -> !e.equals(user) && !friendsOfUser.getFriendsOfUser().contains(e))
                .collect(Collectors.toList())) {
            unknownUsersScore.put(unknownUser, 0);
        }
    }

    public List<String> recommendFriends(List<List<String>> friends, FriendsOfUser friendsOfUser, List<String> visitors) {
        for (List<String> relation : friends) {
            for(String unknownUser : unknownUsersScore.keySet()) { // andole, jun. bedi
                for(String friendOfUser : friendsOfUser.getFriendsOfUser()) {
                    if (relation.contains(unknownUser) && relation.contains(friendOfUser)) {
                        unknownUsersScore.replace(unknownUser, unknownUsersScore.get(unknownUser) + 10);
                    }
                }
            }
        }
        for(String visitor : visitors) {
            if (unknownUsersScore.containsKey(visitor)) {
                unknownUsersScore.replace(visitor, unknownUsersScore.get(visitor) + 1);
            }
        }
        List<Map.Entry<String, Integer>> list = new LinkedList<>(unknownUsersScore.entrySet());
        list.sort((o1, o2) -> {
            int comparision = (o1.getValue() - o2.getValue()) * -1;
            return comparision == 0 ? o1.getKey().compareTo(o2.getKey()) : comparision;
        });

        Map<String, Integer> sortedMap = new LinkedHashMap<>();
        int index = 0;
        for (Entry<String, Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
            index++;
            if (index > 4) {
                break;
            }
        }
        return new ArrayList<>(sortedMap.keySet());
    }

}




