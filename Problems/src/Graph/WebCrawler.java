package Graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebCrawler {

  public static void main(String[] args) {
    discoverWeb("https://www.interviewcake.com/");
  }

  static void discoverWeb(String root) {
    Queue<String> queue = new LinkedList<>();
    Set<String> discoveredWebsiteList = new HashSet<>();

    int count = 0;
    queue.add(root);
    discoveredWebsiteList.add(root);

    while (!queue.isEmpty()) {

      String v = queue.poll();
      String rowHtml = readURL(v);

      String regexp = "http://(\\w+\\.)*(\\w+)";
      Pattern pattern = Pattern.compile(regexp);
      Matcher matcher = pattern.matcher(rowHtml);

      while (matcher.find()) {
        count++;
        String w = matcher.group();

        if (!discoveredWebsiteList.contains(w)) {
          discoveredWebsiteList.add(w);
          System.out.println(count + ", set.size(): " + discoveredWebsiteList.size() + " Website found with URL: " + w);
          queue.add(w);
        }
      }
    }
  }

  static String readURL(String v) {
    StringBuilder inputLine = new StringBuilder();

    try {
      URL url = new URL(v);
      BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

      String aux = "";

      while ((aux = in.readLine()) != null) {
        inputLine.append(aux);
      }

      in.close();
    } catch (Exception e) {
      // e.printStackTrace();
    }

    return inputLine.toString();
  }

}