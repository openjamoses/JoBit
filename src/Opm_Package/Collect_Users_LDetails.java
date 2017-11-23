/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Opm_Package;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author john
 */
public class Collect_Users_LDetails {
    
    public void collectDetails(String projectName, String[] toks) throws ParseException{
        /// This is where we shall store the list of all the comits with other details
            ArrayList<String> shaaList = new ArrayList<>();
            ArrayList<String> nameList = new ArrayList<>();
            ArrayList<String> emailList = new ArrayList<>();
            ArrayList<String> loginList = new ArrayList<>();
            ArrayList<String> locationList = new ArrayList<>();
            ArrayList<String> nameMerged = new ArrayList<>();
           
            Set <String> nameSet = new LinkedHashSet<String>();
            Set <String> emailSet = new LinkedHashSet<String>();
            Set <String> loginSet = new LinkedHashSet<String>();
            Set <String> locationSet = new LinkedHashSet<String>();
            
             Set <String> allSet1 = new LinkedHashSet<String>();
             Set <String> allSet2 = new LinkedHashSet<String>();
           
         ///#########################################################################
            int p = 1; // Page number parameter
            int i = 0; // Commit Counter
            int ct=0;
            int count =0;
            while (true){////loop thru the pagess....
        	if (ct == (toks.length-1) ){/// the the index for the tokens array...
                    ct = 0; //// go back to the first index......
                }
              String jsonString = callURL("https://api.github.com/repos/"+projectName+"/commits?page="+p+"&per_page=100&access_token="+toks[ct++]);
	       String sha = null ;
               JSONParser parser = new JSONParser();
                JSONArray jsonArray = (JSONArray) parser.parse(jsonString);
                if (jsonArray.toString().equals("[]") || count == 5){
                    /// Break out of the loop, when empty array is found!
	                	break;
	                }
                
                for (Object jsonObj : jsonArray) {
                      count ++;
                      
                      /** Please remove this code, it was only used for testing...**/
                      if(count == 5){
                      //    break;
                      }
                      /** Remove Up to here  **/
                      JSONObject jsonObject = (JSONObject) jsonObj; 
                      String shaa = (String) jsonObject.get("sha");
                      JSONObject commitsObj = (JSONObject) jsonObject.get("commit");
                      JSONObject authorObj = (JSONObject) commitsObj.get("author");
                      ///..........................................
                      String name = (String) authorObj.get("name");
                      String email = (String) authorObj.get("email");
                      String date = (String) authorObj.get("date");
                      //......................................
                      /// Print something to the console.....
                      if (ct == (toks.length-1)){/// the the index for the tokens array...
                          ct = 0; //// go back to the first index......
                      }
                      /// Now we also need to get the Login Details,,the corresponding followes and following
                      JSONObject loginAuthorObj = (JSONObject) jsonObject.get("author");
                      
                      String login = "login####";
                      String loginURL = "";
                      JSONObject loginObj = null;
                      ///..........................................
                      String location = "location";
                      String created_at = "location";
                      
                      /// Checking for null objects...
                      if(loginAuthorObj != null){
                          login = (String) loginAuthorObj.get("login");
                          
                          loginURL = callURL("https://api.github.com/users/"+login+"?access_token="+toks[ct++]);
                          loginObj = (JSONObject)parser.parse(loginURL);
                          ///..........................................
                          location = (String) loginObj.get("location");
                          created_at = (String) loginObj.get("created_at");
                          
                      }
                      
                      //nameMerged.add(name+"/"+email+"/"+login+"/"+location+"/"+created_at);
                      allSet1.add(name+"/"+email+"/"+login+"/"+location+"/"+created_at);
                      allSet2.add(name+"/"+email+"/"+login+"/"+location+"/"+created_at);
                      
                      shaaList.add(sha);
                      nameList.add(name);
                      emailList.add(email);
                      loginList.add(login);
                      locationList.add(location);
                      
                      
                      nameSet.add(name);
                      emailSet.add(email);
                      loginSet.add(login);
                      locationSet.add(location);
                      
                      
                     //allSet.add(name+":"+email+":"+login+":"+location);
                     //System.out.println(count+"\tname : "+name+", Email: "+email+", login: "+login+", Location: "+location);
                     ///###########################################.  
                 }/// *** End of JSon Object.....
               p++;//// Goto the next Page.......
          } /// ******** End of while loop ......
            
            List<String> all1 = new ArrayList<>();
            List<String> all2 = new ArrayList<>();
           // create an iterator
            Iterator iterator1 = allSet1.iterator();
            Iterator iterator2 = allSet2.iterator();
            while(iterator1.hasNext()){
                all1.add((String) iterator1.next());
            }
             while(iterator2.hasNext()){
                all2.add((String) iterator2.next());
            }
            for(int x=0;x<all1.size(); x++){
                
               for(int y=0; y<all2.size(); y++){
                   String allDetails1 = all1.get(x);
                   String allDetails2 = all2.get(y);
                   // Splitting the strings...
                   String[] splits1 = allDetails1.split("/");
                   String[] splits2 = allDetails2.split("/");
                   /// Matching the two String Now.......
                   String name1 = splits1[0];
                   String email_prefix1 = splits1[1].substring(0,splits1[1].indexOf("@"));
                   String location1 = splits1[2];
                   
                   String name2 = splits2[0];
                   String email_prefix2 = splits2[1].substring(0,splits2[1].indexOf("@"));
                   String location2 = splits2[2];
                   
                   
                   System.out.println(x+"\t: "+allDetails1+" \t,"+y+"\t: "+allDetails2);
                   
                   if(name1.equals(name2) && email_prefix1.equals(email_prefix2) && location1.equals(location2)){
                       
                       System.out.println("all the same\t\t: "+name1+"\t"+email_prefix1+"\t"+location1);
                       
                   }else if(name1.equals(name2) && email_prefix1.equals(email_prefix2) && !location1.equals(location2)){
                       System.out.println("Location differs\t\t: "+name1+"\t"+email_prefix1+"\t"+location1);
                       
                   }else if(name1.equals(name2) && !email_prefix1.equals(email_prefix2) && location1.equals(location2)){
                       System.out.println("Email prefix differs\t\t: "+name1+"\t"+email_prefix1+"\t"+location1);
                       
                   }else if(!name1.equals(name2) && email_prefix1.equals(email_prefix2) && location1.equals(location2)){
                       System.out.println("Name Differs \t\t: "+name1+"\t"+email_prefix1+"\t"+location1);
                       
                   }else if(!name1.equals(name2) && !email_prefix1.equals(email_prefix2) && !location1.equals(location2)){
                       System.out.println("All Differs \t\t: "+name1+"\t"+email_prefix1+"\t"+location1);
                       System.out.println("\t\t \t\t: "+name2+"\t"+email_prefix2+"\t"+location2);
                       
                   }
                   System.out.println("\n\n");
               }
            }
             
            
    }
    /**
     * 
     * @param myURL
     * @return 
     */
    public  String callURL(String myURL) {
       // System.out.println("Requested URL:" + myURL);
        StringBuilder sb = new StringBuilder();
        URLConnection urlConn = null;
        InputStreamReader in = null;
        try {
            URL url = new URL(myURL);
            urlConn = url.openConnection();
            if (urlConn != null) {
                urlConn.setReadTimeout(60 * 1000);
            }
            if (urlConn != null && urlConn.getInputStream() != null) {
                in = new InputStreamReader(urlConn.getInputStream(),
                        Charset.defaultCharset());
                BufferedReader bufferedReader = new BufferedReader(in);
                if (bufferedReader != null) {
                    int cp;
                    while ((cp = bufferedReader.read()) != -1) {
                        sb.append((char) cp);
                    }
                    bufferedReader.close();
                }
            }
            in.close();
        } catch (Exception e) {
            throw new RuntimeException("Exception while calling URL:" + myURL, e);
        }

        return sb.toString();
    }

    
}
