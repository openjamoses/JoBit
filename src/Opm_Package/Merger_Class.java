/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Opm_Package;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.apache.poi.ss.usermodel.Workbook;


/**
 *
 * @author john
 */
public class Merger_Class {
    
    Object[] datas = null;
    OpenFileName openOldfile;
    
    String file = "/Users/john/Downloads/Apps-forks/Collection_1600-1805.xlsx";
    String New_file = "/Users/john/Downloads/Apps-forks/Results/Merged-Collection_1600-1805-1.xlsx";
    String New_file2 = "/Users/john/Downloads/Apps-forks/Results/Merged-Collection_1600-1805-2.xlsx";
    OpenFileName openFile = new OpenFileName();
    Excell_Dublicating excel = new Excell_Dublicating();
    Merger_Class() throws Exception{
        doNameMerging();
    }
    /**
     * 
     * @param g 
     */
    public static void main(String[] g) throws Exception{
        new Merger_Class();
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

    private void doNameMerging() throws Exception {
       int numbers = getWorksheets();
       int count = 164;
       while(count < numbers ){ 
           
           int m_count = 0;
       ArrayList < Object[]  > allobj = new ArrayList <Object[] > ();
       ArrayList < Object[]  > allobj2 = new ArrayList <Object[] > ();
       datas =    new Object[]{"Tag Date","PR Open",
                     "PR Closed","IS Open","IS Clossed","Forks","Watched","Project",
                     "Name/email/login/Location/Created_at/Updated_at/Public_repos/Public_gists/Followers/Following/Commits_Changed_Added_Deleted", " ","","Merged Repos"
                                 };// end of assigning the header to the object..
        /// putting the header in to the arraylist..
        allobj.add(datas);
        allobj2.add(datas);
            
        List<String> lists = new ArrayList<>();
        List<String> dateslists = new ArrayList<>();
        List< List<String> > allLists = new ArrayList<>();
         
        List<String> prOpen = new ArrayList<>();
        List<String> prClosed = new ArrayList<>();
        List<String> isOpen = new ArrayList<>();
        List<String> isClosed = new ArrayList<>();
        List<String> forks = new ArrayList<>();
        List<String> watch = new ArrayList<>();
        
        List<String> projectlist = new ArrayList<>();
       
      
        Set <String> mergedlist = new LinkedHashSet<String>();
      
        allLists = openFile.readCommits2(file,count);
        lists = allLists.get(0);
        
        dateslists = allLists.get(1);
        prOpen = allLists.get(2);
        prClosed = allLists.get(3);
        isOpen = allLists.get(4);
        isClosed = allLists.get(5);
        forks = allLists.get(6);
        watch = allLists.get(7);
        projectlist = allLists.get(8);
        String proj_name = "Project_Name";
        if(projectlist.size() > 0){
            proj_name = projectlist.get(0);
        }
        //System.out.println(proj_name);
        
        datas =    new Object[]{"","",
                     "","","","","",proj_name,
                     ""
                                 };// end of assigning the header to the object..
        
        allobj.add(datas);
        allobj2.add(datas);

     
       String[] nameMerger_i = null;
       String[] nameMerger_j = null;
       String[][] nameMerger_new = null;
       String name_i = "", name_j = "", email_prefix_i = "", email_prefix_j = "", login_i = "", login_j = "", location_i = "", location_j = "", created_at_i = "", created_at_j = "",date_i="",date_j="";
		
       for(int i=0; i<lists.size(); i++){
           
           if(!lists.get(i).equals("") && lists.get(i).length() >10){
               //System.out.println(lists.get(i));
               String[] splits1 = lists.get(i).split(":-");
               date_i = dateslists.get(i);
               for(int c=0;  c<splits1.length; c++){
                   nameMerger_i = splits1[c].split("/");
               }
               
               name_i = nameMerger_i[0];
               if(nameMerger_i[1].contains("@")){
                   email_prefix_i = nameMerger_i[1].substring(0,nameMerger_i[1].indexOf("@"));
               }else{
                   email_prefix_i = nameMerger_i[1];
               }
               login_i = nameMerger_i[2];
              // if(nameMerger_i.length>3){
                   location_i = nameMerger_i[3];
               //}
           }
           for (int j=0; j<lists.size(); j++){
              
               if(!lists.get(j).equals("") && lists.get(i).length() >10 ){
                  // String[] splits2 = lists.get(0).split(":-");
                   nameMerger_j = lists.get(j).split("/");
                   date_j = dateslists.get(j);
                   if(nameMerger_j.length >3) {
                   name_j = nameMerger_j[0];
                   if(nameMerger_j[1].contains("@")){
                       email_prefix_j = nameMerger_j[1].substring(0,nameMerger_j[1].indexOf("@"));
                    }else{
                       email_prefix_j = nameMerger_j[1];
                    }
                    //System.out.println(j+"\t\t\t"+nameMerger_j.length+" \t"+lists.get(j));
                    login_j = nameMerger_j[2];
                    location_j = nameMerger_j[3];
                    
                    String str1 = "",commits1 ="";
		    String str2 = "",commits2 = "";
                    String str3 = "",commits3 = "";
                    //
                
               
		   if (login_i.equals(login_j) && !login_i.equals("login######") && location_j.equals("location")  &&  !location_i.equals("location") ){// || name_i.equals(name_j) || email_prefix_i.equals(email_prefix_j)){
		         str1 = lists.get(i).substring(0,lists.get(i).lastIndexOf("/"))+"/";
			 str2 = lists.get(j).substring(lists.get(j).lastIndexOf("/"));
                         //System.out.println("1: "+login_i+" , "+login_j);
                         //System.out.println(i+" , "+j+"\t"+date_j+"\t"+lists.get(j));
                         m_count ++;
                         
                         mergedlist.add(name_j+" : "+email_prefix_j+" : \t"+location_j+" -> "+location_i);
                         
                         if(date_i.equals(date_j)){
                             String[] sp1 = lists.get(i).substring( lists.lastIndexOf("/")+1,lists.get(i).length() ).split("_");
                             String[] sp2 = lists.get(j).substring( lists.lastIndexOf("/")+1,lists.get(j).length() ).split("_");
                             long com = 0;
                             long cha = 0;
                             long add = 0;
                             long del = 0;
                             if(isLong(sp1[0]) && isLong(sp2[0])){
                                 com = Long.parseLong(sp1[0])+Long.parseLong(sp2[0]);
                                 cha = Long.parseLong(sp1[1])+Long.parseLong(sp2[1]);
                                 add = Long.parseLong(sp1[2])+Long.parseLong(sp2[2]);
                                 del = Long.parseLong(sp1[3])+Long.parseLong(sp2[3]);
                             }
                             str3 = com+"_"+cha+"_"+add+"_"+del;
                             lists.set(i, str1+str3);
                             /// Delete from the list..
                             
                             lists.remove(j);
                             
                             dateslists.remove(j);
                             prOpen.remove(j);
                             prClosed.remove(j);
                             isOpen.remove(j);
                             isClosed.remove(j);
                             forks.remove(j);
                             watch .remove(j);
                             
                             
                         }else{
                             lists.set(j, str1+str2);
                         }
                         //System.out.println("1: "+login_i+" , "+login_j);
		    }
		    else if (login_i.equals(login_j) && !login_j.equals("login######") && location_i.equals("location")  &&  !location_j.equals("location") ){// || name_i.equals(name_j) || email_prefix_i.equals(email_prefix_j)){
		         str1 = lists.get(j).substring(0,lists.get(j).lastIndexOf("/"))+"/";
			 str2 = lists.get(i).substring(lists.get(i).lastIndexOf("/"));
                         //System.out.println("1: "+login_i+" , "+login_j);
                         //System.out.println(i+" , "+j+"\t"+date_j+"\t"+lists.get(j));
                         m_count ++;
                         mergedlist.add(name_j+" : "+email_prefix_j+" : \t"+location_i+" -> "+location_j);
                         if(date_i.equals(date_j)){
                             String[] sp1 = lists.get(i).substring( lists.lastIndexOf("/")+1,lists.get(i).length() ).split("_");
                             String[] sp2 = lists.get(j).substring( lists.lastIndexOf("/")+1,lists.get(j).length() ).split("_");
                             long com = 0;
                             long cha = 0;
                             long add = 0;
                             long del = 0;
                             if(isLong(sp1[0]) && isLong(sp2[0])){
                                 com = Long.parseLong(sp1[0])+Long.parseLong(sp2[0]);
                                 cha = Long.parseLong(sp1[1])+Long.parseLong(sp2[1]);
                                 add = Long.parseLong(sp1[2])+Long.parseLong(sp2[2]);
                                 del = Long.parseLong(sp1[3])+Long.parseLong(sp2[3]);
                             }
                             
                             str3 = com+"_"+cha+"_"+add+"_"+del;
                             lists.set(i, str1+str3);
                             /// Delete from the list..
                             lists.remove(j);
                            
                             dateslists.remove(j);
                             
                             prOpen.remove(j);
                             prClosed.remove(j);
                             isOpen.remove(j);
                             isClosed.remove(j);
                             forks.remove(j);
                             watch .remove(j);
                             
                             
                         }else{
                             lists.set(i, str1+str2);
                         }
                         //System.out.println("2: "+login_i+" , "+login_j);
		    } 
//                   else if (!login_i.equals(login_j) && !login_i.equals("login######")){// || name_i.equals(name_j) || email_prefix_i.equals(email_prefix_j)){
//			//System.out.println(i+" , "+j+"\t"+date_i+"\t"+lists.get(j));
//                        str1 = lists.get(i).substring(0,lists.get(i).lastIndexOf("/"));
//			str2 = lists.get(j).substring(lists.get(j).lastIndexOf("/"));
//                        if(date_i.equals(date_j)){
//                             String[] sp1 = lists.get(i).substring( lists.get(i).lastIndexOf("/")+1,lists.get(i).length() ).split("_");
//                             String[] sp2 = lists.get(j).substring( lists.get(j).lastIndexOf("/")+1,lists.get(j).length() ).split("_");
//                             long com = 0;
//                             long cha = 0;
//                             long add = 0;
//                             long del = 0;
//                             if(isLong(sp1[0]) && isLong(sp2[0])){
//                                 com = Long.parseLong(sp1[0])+Long.parseLong(sp2[0]);
//                                 cha = Long.parseLong(sp1[1])+Long.parseLong(sp2[1]);
//                                 add = Long.parseLong(sp1[2])+Long.parseLong(sp2[2]);
//                                 del = Long.parseLong(sp1[3])+Long.parseLong(sp2[3]);
//                             }
//                            // System.out.println(lists.get(i).substring( lists.get(i).lastIndexOf("/")+1,lists.get(i).length() ));
//                             //String[] sp2 = );
//                             str3 = com+"_"+cha+"_"+add+"_"+del;
//                             lists.set(i, str1+str3);
//                             lists.remove(j);
//                             dateslists.remove(j);
//                         }else{
//			     lists.set(j, str1+str2);
//                         }
//                        System.out.println("3: "+login_i+" , "+login_j);
//                         
//		    }  
                   else if(name_i.equals(name_j) && !login_j.equals("login######") && login_i.equals("login######") ){
			str1 = lists.get(j).substring(0,lists.get(j).lastIndexOf("/"))+"/";
			str2 = lists.get(i).substring(lists.get(i).lastIndexOf("/"));
                        //System.out.println(i+" , "+j+"\t"+date_i+"\t"+lists.get(j));
                        m_count ++;
                        mergedlist.add(name_j+" : "+email_prefix_j+" : \t"+login_i+" -> "+login_j);
                       
                        if(date_i.equals(date_j)){
                             String[] sp1 = lists.get(i).substring( lists.get(i).lastIndexOf("/")+1,lists.get(i).length() ).split("_");
                             String[] sp2 = lists.get(j).substring( lists.get(j).lastIndexOf("/")+1,lists.get(j).length() ).split("_");
                             long com = 0;
                             long cha = 0;
                             long add = 0;
                             long del = 0;
                             if(isLong(sp1[0]) && isLong(sp2[0])){
                                 com = Long.parseLong(sp1[0])+Long.parseLong(sp2[0]);
                                 cha = Long.parseLong(sp1[1])+Long.parseLong(sp2[1]);
                                 add = Long.parseLong(sp1[2])+Long.parseLong(sp2[2]);
                                 del = Long.parseLong(sp1[3])+Long.parseLong(sp2[3]);
                             }
                             str3 = com+"_"+cha+"_"+add+"_"+del;
                             lists.set(i, str1+str3);
                             /// Delete from the list..
                             lists.remove(j);
                             dateslists.remove(j);
                             prOpen.remove(j);
                             prClosed.remove(j);
                             isOpen.remove(j);
                             isClosed.remove(j);
                             forks.remove(j);
                             watch .remove(j);
                             
                         }else{
			     lists.set(i, str1+str2);
                         }
                        //System.out.println("4: "+login_i+" , "+login_j);
                         
		    }
                   else if(name_i.equals(name_j) && !login_i.equals("login######") && login_j.equals("login######") ){
			str1 = lists.get(i).substring(0,lists.get(i).lastIndexOf("/"))+"/";
			str2 = lists.get(j).substring(lists.get(j).lastIndexOf("/"));
                        m_count ++;
                        mergedlist.add(name_j+" : "+email_prefix_j+" : \t"+login_j+" -> "+login_i);
                        
                        if(date_i.equals(date_j)){
                             String[] sp1 = lists.get(i).substring( lists.get(i).lastIndexOf("/")+1,lists.get(i).length() ).split("_");
                             String[] sp2 = lists.get(j).substring( lists.get(j).lastIndexOf("/")+1,lists.get(j).length() ).split("_");
                             long com = 0;
                             long cha = 0;
                             long add = 0;
                             long del = 0;
                             
                             if(isLong(sp1[0]) && isLong(sp2[0])){
                                 com = Long.parseLong(sp1[0])+Long.parseLong(sp2[0]);
                                 cha = Long.parseLong(sp1[1])+Long.parseLong(sp2[1]);
                                 add = Long.parseLong(sp1[2])+Long.parseLong(sp2[2]);
                                 del = Long.parseLong(sp1[3])+Long.parseLong(sp2[3]);
                             }
                             
                             str3 = com+"_"+cha+"_"+add+"_"+del;
                             lists.set(i, str1+str3);
                             /// Delete from the list..
                             lists.remove(j);
                             dateslists.remove(j);
                             prOpen.remove(j);
                             prClosed.remove(j);
                             isOpen.remove(j);
                             isClosed.remove(j);
                             forks.remove(j);
                             watch .remove(j);
                             
                             
                         }else{
			     lists.set(j, str1+str2);
                         }
                         /////System.out.println("5: "+login_i+" , "+login_j);
                         
			
		    }
                   else if(email_prefix_i.equals(email_prefix_j) && !login_j.equals("login######") && login_i.equals("login######") ){
			str1 = lists.get(j).substring(0,lists.get(j).lastIndexOf("/"))+"/";
			str2 = lists.get(i).substring(lists.get(i).lastIndexOf("/"));
                        //System.out.println(i+" , "+j+"\t"+date_i+"\t"+lists.get(j));
                        m_count ++;
                        mergedlist.add(name_j+" : "+email_prefix_j+"\t"+login_i+" -> "+login_j);
                       
                        date_i = dateslists.get(i);
                        date_j = dateslists.get(j);
                        if(date_i.equals(date_j)){
                             String[] sp1 = lists.get(i).substring( lists.get(i).lastIndexOf("/")+1,lists.get(i).length() ).split("_");
                             String[] sp2 = lists.get(j).substring( lists.get(j).lastIndexOf("/")+1,lists.get(j).length() ).split("_");
                             
                             long com = 0;
                             long cha = 0;
                             long add = 0;
                             long del = 0;
                             if(isLong(sp1[0]) && isLong(sp2[0])){
                                 com = Long.parseLong(sp1[0])+Long.parseLong(sp2[0]);
                                 cha = Long.parseLong(sp1[1])+Long.parseLong(sp2[1]);
                                 add = Long.parseLong(sp1[2])+Long.parseLong(sp2[2]);
                                 del = Long.parseLong(sp1[3])+Long.parseLong(sp2[3]);
                             }
                             str3 = com+"_"+cha+"_"+add+"_"+del;
                             lists.set(i, str1+str3);
                             /// Delete from the list..
                             lists.remove(j);
                             dateslists.remove(j);
                             prOpen.remove(j);
                             prClosed.remove(j);
                             isOpen.remove(j);
                             isClosed.remove(j);
                             forks.remove(j);
                             watch .remove(j);
                             
                         }else{
			     lists.set(i, str1+str2);
                         }
                        //System.out.println("6: "+login_i+" , "+login_j+" \t "+date_i+" , "+date_j);
                         
	//		
		    }
                   else if(email_prefix_i.equals(email_prefix_j) && !login_i.equals("login######") && login_j.equals("login######")){
			str1 = lists.get(i).substring(0,lists.get(i).lastIndexOf("/"))+"/";
			str2 = lists.get(j).substring(lists.get(j).lastIndexOf("/"));
                        //System.out.println(i+" , "+j+"\t"+date_i+"\t"+lists.get(j));
                         m_count ++;
                         mergedlist.add(name_j+" : "+email_prefix_j+":\t"+login_j+" -> "+login_i);
                       
                        if(date_i.equals(date_j)){
                             String[] sp1 = lists.get(i).substring( lists.get(i).lastIndexOf("/")+1,lists.get(i).length() ).split("_");
                             String[] sp2 = lists.get(j).substring( lists.get(j).lastIndexOf("/")+1,lists.get(j).length() ).split("_");
                             long com = 0;
                             long cha = 0;
                             long add = 0;
                             long del = 0;
                             if(isLong(sp1[0]) && isLong(sp2[0])){
                                 com = Long.parseLong(sp1[0])+Long.parseLong(sp2[0]);
                                 cha = Long.parseLong(sp1[1])+Long.parseLong(sp2[1]);
                                 add = Long.parseLong(sp1[2])+Long.parseLong(sp2[2]);
                                 del = Long.parseLong(sp1[3])+Long.parseLong(sp2[3]);
                             }
                             str3 = com+"_"+cha+"_"+add+"_"+del;
                             lists.set(i, str1+str3);
                             /// Delete from the list..
                             lists.remove(j);
                             dateslists.remove(j);
                             prOpen.remove(j);
                             prClosed.remove(j);
                             isOpen.remove(j);
                             isClosed.remove(j);
                             forks.remove(j);
                             watch .remove(j);
                             
                             
                         }else{
			     lists.set(j, str1+str2);
                         }
                        //System.out.println("7: "+login_i+" , "+login_j);
                         
	//	        
		    }else if(email_prefix_i.equals(email_prefix_j) && login_i.equals(login_j) && name_i.equals(name_j) && date_i.equals(date_j) && j != i ){
			str1 = lists.get(i).substring(0,lists.get(i).lastIndexOf("/"))+"/";
			str2 = lists.get(j).substring(lists.get(j).lastIndexOf("/"));
                        //System.out.println(i+" , "+j+"\t"+date_i+"\t"+lists.get(j));
                         
                        if(date_i.equals(date_j)){
                             String[] sp1 = lists.get(i).substring( lists.get(i).lastIndexOf("/")+1,lists.get(i).length() ).split("_");
                             String[] sp2 = lists.get(j).substring( lists.get(j).lastIndexOf("/")+1,lists.get(j).length() ).split("_");
                             long com = 0;
                             long cha = 0;
                             long add = 0;
                             long del = 0;
                             if(isLong(sp1[0]) && isLong(sp2[0])){
                                 com = Long.parseLong(sp1[0])+Long.parseLong(sp2[0]);
                                 cha = Long.parseLong(sp1[1])+Long.parseLong(sp2[1]);
                                 add = Long.parseLong(sp1[2])+Long.parseLong(sp2[2]);
                                 del = Long.parseLong(sp1[3])+Long.parseLong(sp2[3]);
                             }
                            
                             str3 = com+"_"+cha+"_"+add+"_"+del;
                             
                             lists.set(i, str1+str3);
                             /// Delete from the list..
                             lists.remove(j);
                             dateslists.remove(j);
                             prOpen.remove(j);
                             prClosed.remove(j);
                             isOpen.remove(j);
                             isClosed.remove(j);
                             forks.remove(j);
                             watch .remove(j);
                             
                             
                         }
                            
		    }
                }
                   
                    
               }
           
           }
               
       } 
       
       List<String> allList = new ArrayList<>();
       
       List<String> datList = new ArrayList<>();
       List<String> mgList = new ArrayList<>();
       Set <String> dateSets = new LinkedHashSet<String>();
      
       Iterator iterator = mergedlist.iterator();  ///
       while(iterator.hasNext()){
           mgList.add((String) iterator.next());
       }
       
       for(int i=0; i<dateslists.size(); i++){
           datList.add(dateslists.get(i));
           dateSets.add(dateslists.get(i));
           
           
       }
       int x =1;
       
       Iterator iterator_date = dateSets.iterator();  ///
       List<String> d_l = new ArrayList<>();
       List<String> d_l2 = new ArrayList<>();
       while(iterator_date.hasNext()){
           d_l.add((String) iterator_date.next());
        }
       
       for(int i=0; i<d_l.size(); i++){
           allList.add("NULL");
       }
       
       //for(int i=0; i<datList.size(); i++){
           for(int j=0; j<d_l.size(); j++){
               int number = Collections.frequency(dateslists,d_l.get(j) );
               String date_1 = d_l.get(j);
               
               if(number == 1){
                   int index_of = dateslists.indexOf(date_1);
                   allList.set(j,lists.get(index_of));
               }else if(number > 1){
                   
                   for(int i=0; i<dateslists.size(); i++ ){
                       //int index_of = dateslists.indexOf(date_1);
                       if(date_1.equalsIgnoreCase(dateslists.get(i))){
                           if(allList.get(j).equalsIgnoreCase("NULL")){
                                allList.set(j, lists.get(i));
                           }else{
                              allList.set(j, allList.get(j)+":-"+lists.get(i));
                           }
                       }
                   }
               }
               // System.out.println(d_l.get(j)+"\t"+number);
           }
           
          
         //}
      // System.out.println(dateSets.size()+"\t"+d_l2.size()+"\t"+allList.size());
       
       
      if(lists.size()>0){
       if(lists.get(0).equals("-")){
           
           for(int i=1; i<d_l.size(); i++){
             List<String> llList = new ArrayList<>();
          
           llList.add(d_l.get(i));
           if(i == 1){
               llList.add(prOpen.get(i));
               llList.add(prClosed.get(i));
               llList.add(isOpen.get(i));
               llList.add(isClosed.get(i));
               llList.add(forks.get(i));
           }else{
               llList.add("");
               llList.add("");
               llList.add("");
               llList.add("");
               llList.add("");
           }
           
           llList.add("");
           llList.add("");
           String [] splits = allList.get(i).split(":-");
           for(int a=0;a < splits.length; a++ ){
               llList.add(splits[a]);
           }
           
           if(i<mergedlist.size()){
               llList.add("");
               llList.add("");
               llList.add(mgList.get(i));
           }
           datas = new Object[llList.size()];
           datas = llList.toArray(datas);
           allobj.add(datas);
            //System.out.println(i+"\t\t"+lists.get(i));
       }
       }else{
        for(int i=0; i<d_l.size(); i++){
           List<String> llList = new ArrayList<>();
        
           llList.add(d_l.get(i));
           
           
           if(i == 0){
               llList.add(prOpen.get(i));
               llList.add(prClosed.get(i));
               llList.add(isOpen.get(i));
               llList.add(isClosed.get(i));
               llList.add(forks.get(i));
           }else{
               llList.add("");
                llList.add("");
               llList.add("");
               llList.add("");
               llList.add("");
           }
           
           llList.add("");
           llList.add("");
           String [] splits = allList.get(i).split(":-");
           for(int a=0;a < splits.length; a++ ){
               llList.add(splits[a]);
           }
           if(i<mergedlist.size()){
               llList.add("");
               llList.add("");
               llList.add(mgList.get(i));
           }
           datas = new Object[llList.size()];
           datas = llList.toArray(datas);
             /// putting the header in to the arraylist..
             allobj.add(datas);
            //System.out.println(i+"\t\t"+lists.get(i));
          }
       }
       //System.out.println(m_count+ "\t" +mergedlist.size()+ "\t"+allList.size());
       //Second Excelll
       
        if(lists.get(0).equals("-")){
           
           for(int i=1; i<lists.size(); i++){
            datas = new Object[]{dateslists.get(i),Integer.parseInt(prOpen.get(i)),Integer.parseInt(prClosed.get(i)),
                Integer.parseInt(isOpen.get(i)),Integer.parseInt(isClosed.get(i)),Integer.parseInt(forks.get(i)),"","",lists.get(i)};
               
             allobj2.add(datas);
            //System.out.println(i+"\t\t"+lists.get(i));
       }
       }else{
           for(int i=0; i<lists.size(); i++){
              datas = new Object[]{dateslists.get(i),Integer.parseInt(prOpen.get(i)),Integer.parseInt(prClosed.get(i)),
                     Integer.parseInt(isOpen.get(i)),Integer.parseInt(isClosed.get(i)),Integer.parseInt(forks.get(i)),"","",lists.get(i)};
               allobj2.add(datas);
           }
       }
       
         excel.createExcel(allobj,count,New_file,getWorksheetName(count));
         excel.createExcel(allobj2,count,New_file2,getWorksheetName(count));  
      }
       count ++;
      }// end of while loop...
    }
    
    
    public boolean isLong( String input ) {
    try {
        Long.parseLong( input );
        return true;
    }
    catch( Exception e ) {
        return false;
    }
}
}
