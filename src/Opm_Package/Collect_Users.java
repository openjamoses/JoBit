/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Opm_Package;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author john
 */
public class Collect_Users {
    XSSFRow rows;
    int rowid = 0;
    XSSFWorkbook workbook2;
    XSSFSheet sheet;
    String projectName;
    Object[] datas = null;
    int ct1 = 0;
    
    String file = "/Users/john/Desktop/Files/Results/changes/percentage.xlsx";
    String new_file1 = "Repos-Collection_400-800.xlsx";
    OpenFileName openFile = new OpenFileName();
    Collect_Users() throws Exception{
        //collectUsers();
    }
    public static void main(String[] g) throws Exception{
        
        System.out.println("Fetching Logins, pliz wait ..... ");
        new Collect_Users().collectUsers();
    }
    private int getWorksheets() throws Exception{
         Workbook workbook = openFile.readFileName(file);
         int sheetCounts = workbook.getNumberOfSheets();
         return sheetCounts;
    }
    private String getWorksheetName(int sheet) throws Exception{
         Workbook workbook = openFile.readFileName(file);
         String sheetName = workbook.getSheetName(sheet);
         
         return sheetName;
    }

    private  void collectUsers() throws Exception {
        
        
        String status_returned = "fine";
        ArrayList < Object[]  > allobj2 = new ArrayList <Object[] > ();
        ///Store the Header First here
        
        //allobj2.add(datas);
           
        
        
        String[] tokens = {"d9fa524eb803729fff1a327ad670f5615fefb822",
	            "727b16f55df64b49c961fb25784bd760cd9fd986",
	            "8a124ae16017a27bff55dba50e349d34b9c6e37e",
	            "da571ada56a1bff128274e53ccf7158b9af45ddc",
	            "3dff8fb276e625a5f13320b68b02785fd120d237",
	            "53df99027c4313db2fc4b4c02b413c0f9546a42e",
	            "d6a809e9a3b7f3cb31e23d257a40ae460a69505e",
	            "eb4a0e8b58b54f47ee7ebde3469baeaa1e53169c",
	            "ebc94d20b0e526b832e6fcf2407a1414115081b5",
	            "d87ddaaffaabe5e558fa7b65f5cadd85786663e5",
	            "4815cf21389f42bf07d8f523c7f0f2130b7db4b6",
	            "99dedffa7529ca10ee947d7509f97f7c4054cd0b",
	            "025d6c2df83c14621a9346d0002f0e5b71e8257a",
	            "bfd9c16567a847212a7c944cbf6e081d5a8d6f5b",
	            "a62c1f8a4348cc1467da1e2b12ee71d0b097b5b6",
	            "62d85139565c08ef24c81e9820c874846e2d9134",
	            "c08e7511725d91d1fe5b98ca10e7c6b07b8e171b",
	            "f87f413aa690ee7545dd71f346b3dc45d6e82838"
             };
        JSONParser parser = new JSONParser();
        double total = 0;
        
        int c_p  = 0;
        
        int num = 0;
        int ct=0;
        
        
        long stopTime = 0;
        long elapsedTime = 0;
        long startTime1 = System.currentTimeMillis();
        int numbers = getWorksheets();
        int count = 0;
        while(count < numbers){
            long startTime = System.currentTimeMillis();
             c_p ++;
             ArrayList < Object[]  > allobj = new ArrayList <Object[] > ();
             datas =    new Object[]{"N/S","PROJECT_NAME","NAME","EMAIL","LOGIN","FORK", "PROJECTS(General)","PROJECTS(Android)","COMMITS MADE"};// end of assigning the header to the object..
             allobj.add(datas);
             
             List<String> nameList = new ArrayList<>();
             List<String> emailList = new ArrayList<>();
             List<String> loginList = new ArrayList<>();
            
            nameList = new Pick_Names().pickDatas(file, count);
            emailList = new Pick_Emails().pickDatas(file, count);
            loginList = new Pick_Login().pickDatas(file, count);
            
            
            String project = new Pick_NewProject().setProjectName(file, count);
            
            System.out.println(project+"\t"+nameList+"\t"+emailList);
            
            for(int i=0; i<loginList.size(); i++){
                total ++;
                Set <String> nameSet = new LinkedHashSet<String>();
                Set <String> fullSet = new LinkedHashSet<String>();
                Set <String> invalidSet = new LinkedHashSet<String>();
                Set <String> androidSet = new LinkedHashSet<String>();
                
                Set <String> forkSet = new LinkedHashSet<String>();
                
                
                List<String> nList = new ArrayList<>();
                List<String> fList = new ArrayList<>();
                
                
                List<Double> totList = new ArrayList<>();
                List<Double> tList = new ArrayList<>();
                ///////
                if(!loginList.get(i).equals("login######")  ){
                
                int p = 1; // Page number parameter
                //int i = 0; // Commit Counter
                
                int count2 = 0;
            
                
                while (true){////loop thru the pagess....
        	    if (ct == (tokens.length) ){/// the the index for the tokens array...
                         ct = 0; //// go back to the first index......
                    }
                    String url = "https://api.github.com/users/"+loginList.get(i)+"/repos?page="+p+"&per_page=100&access_token="+tokens[ct++];
                    String reposObj = new Call_URL().callURL(url);
                
                    if(JSONUtils.isValidJSON(reposObj) == true){
                       
                 
                    JSONArray jsonArray = (JSONArray) parser.parse(reposObj);
                    if (jsonArray.toString().equals("[]")){
                       /// Break out of the loop, when empty array is found!
                      	break;
	            }
                    for (Object jsonObj : jsonArray) {
                        count2 ++;
                        JSONObject jsonObject = (JSONObject) jsonObj; 
                        
                        String name = (String) jsonObject.get("name");
                        
                        String full_name = (String) jsonObject.get("full_name");
                        boolean fork = (boolean) jsonObject.get("fork");
                        double total_commits = 0;
                        if(fork == true){
                            forkSet.add(full_name);
                            //totList.add(total_commits);
                            //System.out.println(fork+":\t"+full_name);
                        }
                        
                         String description = "description";
                        if((String) jsonObject.get("description") != null){
                            description = (String) jsonObject.get("description");
                        
                        }
                        if(fork == false){
                        //// Check the number of commits
                        List<String> c_returned = new ArrayList<>();
                        c_returned = new Count_Users_Commits().countCommits(full_name, loginList.get(i), tokens,ct);
                        /////add the values to the corresponding lists..
                        total_commits = Double.parseDouble(c_returned.get(0));
                        String login_returned = c_returned.get(1);
                        status_returned = c_returned.get(2);
                        ct = Integer.parseInt(c_returned.get(3));
                        
                        
                        
                        //if(status_returned.equals("fine")){
                           totList.add(total_commits);
                           nameSet.add(name);
                           fullSet.add(full_name);
                           nList.add(name);
                           fList.add(full_name);
                         
                        if(!description.equals("description")){
                             if(description.contains("Android") || full_name.contains("Android") || description.contains("android") || full_name.contains("android") ){
                                 androidSet.add(full_name);
                             }
                        }
                        
                        if(description.contains("Android") || full_name.contains("Android") || description.contains("android") || full_name.contains("android") ){
                           //System.out.println(count2+" : LOGIN: "+loginList.get(i)+"\t \t PROJECT: "+full_name+": \t Android \t Commits: "+total_commits);
                    
                        }else{
                          // System.out.println(count2+" : LOGIN: "+loginList.get(i)+"\t \t name: "+full_name+",\t\t\t Commits: "+total_commits);
                        }
                     //}
                     
                     
                    
                    }
                        if(status_returned.equals("not-fine")){
                            
                            //System.out.println(full_name+" : invalid");
                            invalidSet.add(full_name);
                            //break;
                        }
                     }
                    }
                
                p++;//// Goto the next Page.......
            }/// End of while loop for web pages...
                
                //System.out.println(nameSet);
                //System.out.println(fullSet);
                Iterator iterator = androidSet.iterator();
                Iterator iterator2 = fullSet.iterator();
                Iterator f_interator = forkSet.iterator();
                Iterator i_interator = invalidSet.iterator();
                List<String > lists1 = new ArrayList<>();
                List<String > lists2 = new ArrayList<>();
                
                List<String > forList = new ArrayList<>();
                List<String > inv_List = new ArrayList<>();
                List<String> androidList = new ArrayList<>();
                
                while(f_interator.hasNext()){
                    forList.add((String) f_interator.next());
                }
                 while(i_interator.hasNext()){
                    inv_List.add((String) i_interator.next());
                }
                double proj_count = 0;
                while(iterator.hasNext()){
                    androidList.add((String) iterator.next());
                }
                
                while(iterator2.hasNext()){
                    lists2.add((String) iterator2.next());
                }
                
                for(int y=0; y<lists2.size(); y++ ){
                    double tt = 0.0;
                    tList.add(tt);
                    lists1.add("Name###");
                }
                
                for(int a=0; a<fList.size(); a++){
                    String name1 = fList.get(a);
                    for(int b=0; b<lists2.size(); b++){
                        if(lists2.get(b).equals(name1)){
                            tList.set(b, tList.get(b)+totList.get(a));
                            lists1.set(b, nList.get(a));
                        }
                    }
                }
                
                
                for(int a=0; a<lists2.size(); a++){
                    proj_count ++;
                    
                    int flag = 0;
                    String developer = "human";
                    /// Store all the project on the rows.... 
                    String gen_str = lists2.get(a);
                    for(int b=0; b<androidList.size(); b++){
                        if(androidList.get(b).equals(gen_str)){
                            flag = 1;
                        }
                    }
                    
                    for (int c=0; c<inv_List.size(); c++){
                        if(inv_List.get(c).equals(gen_str)){
                            developer = "robot";
                        }
                       }
                    if(flag == 1){
                        if(a == 0){
                            datas =    new Object[]{total,project,nameList.get(i),emailList.get(i),loginList.get(i),developer, lists2.get(a),lists2.get(a),tList.get(a),""};// end of assigning the header to the object..
                      
                        }else{
                            datas =    new Object[]{"","","","","",developer, lists2.get(a),lists2.get(a),tList.get(a),""};// end of assigning the header to the object..
                      
                        }
                      
                    }else{
                        
                        if(a == 0){
                            
                            datas =    new Object[]{total,project,nameList.get(i),emailList.get(i),loginList.get(i),developer, lists2.get(a),"",tList.get(a),""};// end of assigning the header to the object..
                       
                        }else{
                            datas =    new Object[]{"","","","","",developer, lists2.get(a),"",tList.get(a),""};// end of assigning the header to the object..
                       
                        }
                       
                    }
                    allobj.add(datas);
                    
                    //allobj2.add(datas);
           
                }
                
                
                double f1 = forList.size();
                double f2 = lists2.size()-f1;
                datas =    new Object[]{"","","","","","Robot = "+inv_List.size(), lists2.size(),androidList.size(),""};// end of assigning the header to the object..
                allobj.add(datas);
                //allobj2.add(datas);
                 
           }
            
        }/// End of for loop
            new Excell_Percentage_Commits().createExcel(allobj, count, new_file1, getWorksheetName(count));
            
            stopTime = System.currentTimeMillis();
    	    elapsedTime = stopTime - startTime;
    	    System.out.println("ElapsedTime in minutes = "+elapsedTime/(1000*60));
              count ++;
           
    }//// End of the first while loop
        
           stopTime = System.currentTimeMillis();
           elapsedTime = stopTime - startTime1;
	   System.out.println("ElapsedTime in minutes = "+elapsedTime/(1000*60));
        
    
    }/// End of the Method..
    
}
