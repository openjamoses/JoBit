/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Opm_Package;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.apache.poi.ss.usermodel.Workbook;
/**
 *
 * @author john
 */
public class Check_Missimg_Projects {
    String file = "/Users/john/Desktop/code/Server/PROMISE.xlsx";
    String old_file = "/Users/john/Desktop/code/Server/AppNames.xlsx";
    String file2 = "/Users/john/Desktop/code/Server/promise_missing.xlsx";
    Check_Missimg_Projects() throws Exception{
        checkMissing();
    }
    public static void main(String[] g) throws Exception{
        new Check_Missimg_Projects();
    }
    private int getWorksheets() throws Exception{
         Workbook workbook = new OpenFileName().readFileName(file);
         int sheetCounts = workbook.getNumberOfSheets();
         return sheetCounts;
    }
    private void checkMissing() throws Exception{
        Object[] datas = null;
        ArrayList < Object[]  > allobj = new ArrayList <Object[] > ();
        int number = getWorksheets();
        int count = 0;
        List<String> lists1 = new ArrayList<>();
        List<String> lists2 = new ArrayList<>();
        
        lists1 = new OpenFileName().readReposNames(old_file);
        while(count < number){
            String project = new Pick_ProjectName().setProjectName(file, count);
            if(project != null){
            lists2.add(project);
            }
            System.out.println(count+"\t"+project);
            count ++;
        }
        System.out.println();
        lists1.removeAll(lists2);
        
        System.out.println(lists1.size());
        for(int i=0; i<lists1.size(); i++){
            System.out.println(lists1.get(i));
            datas = new Object[]{lists1.get(i)};
            allobj.add(datas);
        }
        new Excell_Dublicating().createExcel(allobj, 0,file2,"missings");
    } 
}
