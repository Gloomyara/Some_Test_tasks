package ru.antonovmikhail.tinkoff;

import java.util.*;

class StringsPathDistinct {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        List<String> list = new ArrayList<>();
        Set<String> pathSet = new HashSet<>();
        for (int i = 0; i < n; i++) {
            list.add(sc.nextLine());
        }
        for (int i = 0; i < n; i++) {
            String[] strings = list.get(i).split("/");
            StringBuilder path = new StringBuilder(strings[0]);
            for (int j = 1; j < strings.length; j++) {
                path.append("/").append(strings[j]);
                if (pathSet.contains(path.toString())) continue;
                pathSet.add(path.toString());
            }
        }
        List<String> outList = new ArrayList<>(pathSet);
        outList.sort(String::compareTo);
        String root = list.get(0).split("/")[0];
        System.out.println(root);
        for (String str : outList) {
            String s = "  ";
            String[] dirName = str.split("/");
            System.out.println(s.repeat(dirName.length) + dirName[dirName.length - 1]);
        }
    }
}