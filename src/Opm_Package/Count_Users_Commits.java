/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Opm_Package;

import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author john
 */
public class Count_Users_Commits {
    /**
     * 
     * @param project
     * @param login
     * @param tockens 
     */
    int ct1 = 0;
    public List<String> countCommits(String project, String login,String[] tockens, int ct) throws ParseException{
            int p = 1; // Page number parameter
            int i = 0; // Commit Counter
            String status = "fine";
            List<String> c_return = new ArrayList<>();
            double count =0;
            int times = 0;
            int flag = 0;
            while (true){////loop thru the pagess....
        	if (ct == (tockens.length) ){/// the the index for the tokens array...
                    ct = 0; //// go back to the first index......
                }
                List<String> dateList = new ArrayList<>();
                List<String> nameList = new ArrayList<>();
                List<String> monthList = new ArrayList<>();
                String jsonString = new Call_URL(). callURL("https://api.github.com/repos/"+project+"/commits?page="+p+"&per_page=100&access_token="+tockens[ct++]);
	        String sha = null ;
                String dates = "date";
                String name = "name";
                if (jsonString.equals("Error")){
                    break;
                }
                JSONParser parser = new JSONParser();
                if(JSONUtils.isValidJSON(jsonString) == true){
                   
                JSONArray jsonArray = (JSONArray) parser.parse(jsonString);
                if (jsonArray.toString().equals("[]")){
                    /// Break out of the loop, when empty array is found!
	                	break;
	                }
                  for (Object jsonObj : jsonArray) {
                       
                      JSONObject jsonObject = (JSONObject) jsonObj; 
                      //String shaa = (String) jsonObject.get("sha");
                      if((JSONObject) jsonObject.get("commit") != null){
                        JSONObject commitsObj = (JSONObject) jsonObject.get("commit");
                        if((JSONObject) commitsObj.get("author") != null){
                           JSONObject author_Obj = (JSONObject) commitsObj.get("author");
                           name = (String) author_Obj.get("name");
                           dates = (String) author_Obj.get("date");
                          
                        }
                      }
                      JSONObject authorObj = (JSONObject) jsonObject.get("author");
                      if(authorObj != null){
                         String login2 = (String) authorObj.get("login");
                         if(login2 != null){
                           if(login2.equals(login)){
                              count ++;
                              
                              
                              ///// Starts **************************************************
                              
                               if(dates.length()>10){
                               String s_date1 = dates.substring(0,10);
                      
                              if(dateList.size()> 0){
                                   String date2 = dateList.get(dateList.size()-1);
                                   String name2 = nameList.get(dateList.size()-1);
                                   String s_date2 = date2.substring(0,10);
                                   if(s_date1.equals(s_date2) && name2.equals(name) ){
                                      dateList.add(dates);
                                      nameList.add(name);
                                   }else{
                                      dateList.clear();
                                      nameList.clear();
                                      dateList.add(dates);
                                      nameList.add(name);
                                   }
                     
                                if(dateList.size() >= 50){
                                    String month = dateList.get(dateList.size()-1).substring(0,6);
                                    
                                    if(monthList.size()>0){
                                       String month2 =  monthList.get(monthList.size()-1);
                                       if(month2.equals(month)){
                                           monthList.add(month);
                                       }else{
                                           monthList.clear();
                                           monthList.add(month);
                                       }
                                    }
                                    else if(monthList.size() == 0){
                                        monthList.add(month);
                                    }
                                    //times ++;
                                    //System.out.println(dateList);
                                    
                                    dateList.clear();
                                    nameList.clear();
                                }
                                
                                if( monthList.size() >= 5){
                                    flag = 1;
                                    
                                    break;
                                }
                              }else if(dateList.size() == 0){
                                  dateList.add(dates);
                                  nameList.add(name);
                              }
                              // System.out.println(dates);
                          }
                              
                              
                            //// Ends  ******************************************************
                           }
                         }
                        }//......................................
                      
                
                 }/// *** End of JSon Object.....  
                  
                  
                }
                if(flag ==1){
                      break;
                  }
               p++;//// Goto the next Page.......
          } /// ******** End of while loop ......
           
         
         if(flag == 1){
             status = "not-fine";
         }
         c_return.add(String.valueOf(count));
         c_return.add(login);
         c_return.add(status);
         c_return.add(String.valueOf(ct));
       
      return c_return; 
    }

}
