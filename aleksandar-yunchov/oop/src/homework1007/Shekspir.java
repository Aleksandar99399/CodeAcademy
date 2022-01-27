package homework1007;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Shekspir
{
  public static void main(String[] args)
  {
    Set<String> uniqueWords = new HashSet<>();
    Map<String,Integer> mostCommonWords = new TreeMap<>();
    try {
      List<String> strings = Files
          .readAllLines
              (Paths.get("D:\\IdeaProjects\\CodixMercurial\\aleksandar-yunchov\\oop\\src\\homework1007\\Hamlet.txt"));
      for (String string : strings) {
        String[] s = string.replaceAll("\\W", " ").split(" ");

        for (int j = 0; j < s.length; j++) {

          if (mostCommonWords.containsKey(s[j])) {
            mostCommonWords.put(s[j], mostCommonWords.get(s[j]) + 1);
          }
          else {
            mostCommonWords.put(s[j], 1);
          }
        }

        uniqueWords.addAll(Arrays.asList(s));
      }

    }
    catch (IOException e) {
      e.printStackTrace();
    }

    List<Map.Entry<String, Integer>> sortedList =
        mostCommonWords.entrySet()
            .stream()
            .sorted(Comparator.comparingInt(Map.Entry::getValue)).collect(Collectors.toList());

    for (int i = sortedList.size() - 1; i >= sortedList.size() - 10 ; i--) {
      System.out.println(sortedList.get(i).getKey() + " - "+ sortedList.get(i).getValue());
    }

    System.out.println(mostCommonWords.get("HAMLET"));
    System.out.println(mostCommonWords.get("OPHELIA"));


//    Comparator<Map<String,Integer>> comparator = new Comparator<Map<String,Integer>>()
//    {
//      @Override
//      public int compare(Map<String, Integer> o1, Map<String, Integer> o2)
//      {
//        return o1.ge - o2;
//      }
//    };
//    for (Map.Entry<String, Integer> words : mostCommonWords.entrySet()) {
//      System.out.println(words.getKey() + " - "+ words.getValue());
//    }


//    System.out.println(uniqueWords.size());

  }
}
