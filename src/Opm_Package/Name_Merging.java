/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Opm_Package;

import java.util.ArrayList;
import java.util.List;
import org.apache.poi.xssf.usermodel.XSSFSheet;

/**
 *
 * @author john
 */
public class Name_Merging {
    private static final String filePath = "/Users/john/Desktop/PROJECTS/JSON WORK/Repo_Names.xlsx";                 
    private static final String[] toks = {};
    
    Name_Merging() throws Exception{
        getReposName();
    }
    
    
    public void getReposName() throws Exception{
            OpenFileName fileName = new OpenFileName();
            List<String> list = fileName.readReposNames(filePath);
            List<String> sheetsList = new ArrayList<>();
            for (int i=0; i<list.size(); i++){
                 String[] splits = list.get(i).split("/");
                 sheetsList.add(splits[0]);
                 //System.out.println("Projects: "+list.get(i)+", \t \t Sheets: "+sheetsList.get(i));
           }
            // To convert List in to array of Strings..
            String[] repos_array = new String[list.size()];
            String[] sheet_array = new String[sheetsList.size()];
            
            repos_array = list.toArray(repos_array);
            sheet_array = sheetsList.toArray(sheet_array);
            XSSFSheet[] sheet = new XSSFSheet[repos_array.length];
            int count = 0;
            for(int i=1; i<repos_array.length; i++){
                Collect_Users_LDetails commit =  new Collect_Users_LDetails();
                commit.collectDetails(repos_array[1], toks);
                count ++;                      
           }  
        
        }
}
