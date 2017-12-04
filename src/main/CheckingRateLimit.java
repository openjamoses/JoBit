package main;

import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class CheckingRateLimit {

    public static boolean checkingRateLimit(String projectname, String[] token) {

        int count = 0;;
        //Github github = new RtGithub("Personal Token b8b44c99bc659ed954234db7cfcd1ae6348b0217");
        try {

            //URL obj = new URL("https://api.github.com/repos/atom/atom/commits?per_page=50&since=2016-04-05T00:18:59Z&until=2016-04-18T22:20:18Z");
            //int i = 0;
            for (int i = 0; i < token.length; i++) {
                URL obj = new URL("https://api.github.com/repos/" + projectname + "/commits?access_token=" + token[i]);
                //URL obj = new URL("https://api.github.com/repos/BilingualAphasia/AndroidBilingualAphasiaTest/commits?page="+p+"&per_page=100");

                URLConnection conn = obj.openConnection();
                Map<String, List<String>> map = conn.getHeaderFields();

                //System.out.println("Printing All Response Header for URL: "+ obj.toString() + "\n");
                String str = "";
                int x = 0;
                for (Map.Entry<String, List<String>> entry : map.entrySet()) {
                    if (entry.getKey() != null && entry.getKey().equals("X-RateLimit-Remaining")) {
                        x = Integer.parseInt(entry.getValue().get(0));
                        if (x < 60) {
                            count++;
                        }
                        System.out.println(entry.getKey() + " : " + entry.getValue().get(0));
                        break;
                    }

                }
//	            if (i == 0)
//	            	break;
            }
// 
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (count == 0) {
            return true;
        } else {
            for (int i = 0; i < 100000; i++)
        		;

            return true;
        }
    }

    public static Long repeat() {
        long total = 0;
        long x = 10000;
        while (x > 1) {
            for (int i = 0; i < 1000000000; i++) {
                total += i;
            }
            x--;
        }

        return total;
    }

    public static void main(String[] args) {

        String projectname = "BilingualAphasia/AndroidBilingualAphasiaTest";
        String[] tokens = {
        };

        boolean check = checkingRateLimit(projectname, tokens);

    }

}
