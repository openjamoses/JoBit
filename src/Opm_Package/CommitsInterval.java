package Opm_Package;

import read.Repos_Isissues;
import read.Repos_Forks;
import read.Repos_PullRequests;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
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
public class CommitsInterval {
    /**
     * 
     * @param projectName
     * @param allobj 
     */
   ArrayList < Object[]  > allobj2 = new ArrayList <Object[] > ();
    XSSFRow rows;
    int rowid = 0;
    XSSFWorkbook workbook2;
    XSSFSheet[] sheet;
    String projectName;
    Object[] datas = null;
    OpenFileName openOldfile;
    int interval = 14;
    
    public void useTagDatesInterval(String projectName,String sheetName, int number, XSSFSheet[] sheet, ArrayList<Object[]> allobj,String[] toks, int ct,String toDay) throws ParseException, org.json.simple.parser.ParseException, Exception {
        //if(allobj.size() >1){
        //int ct = 0;
            this.sheet = sheet;
            /// Writing the Headers of the excell documents..
             datas =    new Object[]{"Tag Date","PR Open",
                     "PR Closed","IS Open","IS Clossed","Forks","Project",
                     "Name/email/login/Location/Created_at/Updated_at/Public_repos/Public_gists/Followers/Following/Commits_Changed_Added_Deleted"
                                 };// end of assigning the header to the object..
             /// putting the header in to the arraylist..
             allobj2.add(datas);
        
        List<String> pullsList = new ArrayList<>();
        List<String> forksList = new ArrayList<>();
        List<String> issuesList = new ArrayList<>();
        
        pullsList = new Repos_PullRequests().getDatas(projectName, toks,ct);
        ct = Integer.parseInt(pullsList.get(pullsList.size()-1));
        pullsList.remove(pullsList.size()-1);
        
        forksList = new Repos_Forks().getDatas(projectName, toks,ct);
        ct = Integer.parseInt(forksList.get(forksList.size()-1));
        forksList.remove(forksList.size()-1);
        
        issuesList = new Repos_Isissues().getDatas(projectName, toks,ct);
        ct = Integer.parseInt(issuesList.get(issuesList.size()-1));
        issuesList.remove(issuesList.size()-1);
        
        
        
        long pl=0,pc=0,ff=0,is=0,ic=0,s1=0,w=0;
            // Pullrequests
            for(int a=0; a<pullsList.size(); a++){
                String[] pull_split = pullsList.get(a).split(":");
                String state = pull_split[0];
                String pulls_date = pull_split[1];
                
                SimpleDateFormat ss = new SimpleDateFormat("yyyy-MM-dd");
        
                Calendar cc1 = Calendar.getInstance();
                cc1.setTime(ss.parse(pulls_date));
                
                Calendar cc4 = Calendar.getInstance();
                cc4.setTime(ss.parse(toDay));
                   
                if( (cc4.after(cc1) || cc4.equals(cc1))  ){
                     if(state.equals("open")){
                         pl += 1;
                     }else if(state.equals("closed")){
                         pc += 1;
                     }
                }
       
                
            }
            
            /// Issuess
            for(int a=0; a<issuesList.size(); a++){
                String[] issues_split = issuesList.get(a).split(":");
                String state = issues_split[0];
                String issues_date = issues_split[1];
                
                SimpleDateFormat ss = new SimpleDateFormat("yyyy-MM-dd");
        
                Calendar cc1 = Calendar.getInstance();
                cc1.setTime(ss.parse(issues_date));
                
                Calendar cc4 = Calendar.getInstance();
                cc4.setTime(ss.parse(toDay));
                
                if( (cc4.after(cc1) || cc4.equals(cc1))  ){
                     if(state.equals("open")){
                         is += 1;
                     }else if(state.equals("closed")){
                         ic += 1;
                     }
                }
       
            }
            
            /// Forks
            for(int a=0; a<forksList.size(); a++){
                String forks = forksList.get(a);
                SimpleDateFormat ss = new SimpleDateFormat("yyyy-MM-dd");
        
                Calendar cc1 = Calendar.getInstance();
                cc1.setTime(ss.parse(forks));
                
                Calendar cc4 = Calendar.getInstance();
                cc4.setTime(ss.parse(toDay));
                
                if( (cc4.after(cc1) || cc4.equals(cc1))  ){
                     ff += 1;
                }
       
            }
            
            
            
            
        
       
        for(int x=0;x<allobj.size();x++){//Looping thru the array list to pick the objects...
             /// Getting the all the Objects in the arrayList...
             Object [] objectArr = allobj.get(x);
             Object tagDateObj = objectArr[1];
             ////Excell Header goes here....
            
             
             /** **************************************
              ** Getting the first and the last tag Date here
              **/
             
             Object [] firstObject = allobj.get((allobj.size()-1));
             Object [] lastObject = allobj.get(1);
             Object fDate = firstObject[0];
             Object lDate = lastObject[0];
             
             ArrayList<String> check = new ArrayList<>();
             
             /// Checking if Both First Date and the Last are all String.....
             if(fDate instanceof String && lDate instanceof String  ){
                 ///Preparing to add date by 7 after removing Z from the String
                 check.add(lDate.toString());
                 int i2 = 1,i = 0,next = 0;
                 SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
                 String date2 = fDate.toString();
                 
                 
                 Calendar c3 = Calendar.getInstance();
                     c3.setTime(s.parse(lDate.toString()));
                     c3.add(Calendar.DATE, (interval - 1));  // number of days to add
                     String dt33 = s.format(c3.getTime());  // dt is now the new date
                 
                     Date dateTODAY = s.parse(toDay);
                 
                 Date Ddate1 = s.parse(date2);
                 Date Ddate2 = s.parse(dt33);
                 
                 if(Ddate2.after(dateTODAY)){
                     Ddate2 = dateTODAY;
                 }
                 
                int com_index = 0;
                if(check.size() == 1){
                 RUN: do {
                     
                     //System.out.println(fDate.toString());
                     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                   
                     String subString = fDate.toString().substring(10, (fDate.toString().length() - 1));
                     /// First Dates...
                     Calendar c = Calendar.getInstance();
                     c.setTime(sdf.parse(fDate.toString()));
                     c.add(Calendar.DATE, interval*i);  // number of days to add
                     String dt2 = sdf.format(c.getTime());  // dt is now the new date
                     /// Second Date
                     
                     Calendar c2 = Calendar.getInstance();
                     c2.setTime(sdf.parse(fDate.toString()));
                     c2.add(Calendar.DATE, interval*i2);  // number of days to add
                     String dt22 = sdf.format(c2.getTime());  // dt is now the new date
                     
                     //int increamented = Integer.parseInt(dt.substring(17,19)) + 1;
                     int increamented  = Integer.parseInt(subString.substring(subString.length()-2,subString.length())) + 1;///  index number 17 - 19 from "2016-11-13T10:31:35Z" is 35  increament the min by 1
                     String i_Z = increamented+"Z";
                     /// 
                     String sub = subString.substring(subString.length()-3,subString.length()-1);/// index number 17 - 19 from "2016-11-13T10:31:35Z" is 35
                     String replace_sub = subString.replace(sub, increamented+"");/// Replace the Last string which is the minutes..
        
                     String n_dt2 = dt22+""+replace_sub+"";// Concate to the date to make it full
                    
                     /// Now we can use the right variable names for the two dates interval
                     String date1 = dt2+""+subString+"Z";
                     date2 = dt22+""+subString+"Z";
                     
                     /// Now we assigns to the next method to get the commits within the two dates above....
                     
                     Ddate1 = s.parse(date2);
                     
        
                    /// We have to check whether the last date is reached!...
                    //Calendar cc1 = Calendar.getInstance();
                    //cc1.setTime(s.parse(date2));
                    //Calendar cc4 = Calendar.getInstance();
                    //cc4.setTime(s.parse(lDate.toString()));
                    if (Ddate2.compareTo(Ddate1) > 0) {
                       // System.out.println("***********************");
                         ///Calling the interval details...
                         New_CommitsInterval interval = new New_CommitsInterval();
                         datas =  interval.getCommitsNow(projectName,date1,date2,i,toks,dt33,pl,pc,is,ic,ff,ct,com_index);
                        /// Add to the List...
                        com_index ++;
                        allobj2.add(datas);
                       }else{
                        next ++;
                        check.add(date2);
                        //System.out.print(check);
                        break RUN;
                        
                       } 
                    
                   
                     if(Ddate2.compareTo(Ddate1) < 0){
                        check.add(date2);
                        
                     }
                     i2 ++;
                     i ++;
                     
                     
                 
                     
                 }while( Ddate2.compareTo(Ddate1) > 0 && check.size() <= 1 ) ; 
                 
                 if(next > 0){
                     check.add(date2);
                     break;
                 }
                 }else if(check.size() >1){
                     break;
                 }
             }
             /** 
              ** End of Getting the first and the last tag Date here
              ******************************************************/
          }//End of for loop for arraylist of object....
        
        
         String filePath2 = "collection_missings_projects_2.xlsx";
         createExcel(allobj2,number,filePath2,sheetName);
       // }
        
        
         
    }
    
     public void createExcel(ArrayList<Object[]> allobj,int number,String excelFilePath, String sheetName)
        throws IOException {
        FileOutputStream fos = null;
        try {
             XSSFWorkbook workbook = null;
        if (new File(excelFilePath).createNewFile()) {
            workbook = new XSSFWorkbook();
        } else {
            FileInputStream pfs = new FileInputStream(new File(excelFilePath));
            workbook = new XSSFWorkbook(pfs);
        }
        if (workbook.getSheet(sheetName) == null) {
            fos = new FileOutputStream(excelFilePath);
            sheet[number]= workbook.createSheet(sheetName);
            ///
        int rowid2 = sheet[number].getLastRowNum();
        int x;
        for(x=0;x<allobj.size();x++){//Looping thru the array list to pick the objects...
             rows = sheet[number].createRow(rowid2++);
             Object [] objectArr = allobj.get(x);
             int cellid = 0;
             for (Object obj : objectArr){//Looping inside the object...
                  Cell cells = rows.createCell(cellid++);
                  if (obj instanceof String){
                       cells.setCellValue((String)obj);
                   }else if (obj instanceof Integer){
                        cells.setCellValue((int)obj);
                   }else if (obj instanceof Double){
                        cells.setCellValue((double)obj);
                   }
              } // End of for loop for object
          }//End of for loop for arraylist of object....
         
            workbook.write(fos);
            System.out.println("Excel File Created is : "+excelFilePath+" With sheet name :"+sheetName+ " For repos_number: "+number);
            System.out.println("********************************************************************************************************");   
        }

    } catch (IOException e) {
        throw e;
    } finally {
        if (fos != null) {
            fos.close();
        }
    }
}
}
