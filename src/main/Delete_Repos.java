/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import Opm_Package.OpenFileName;
import picks.Pick_Emails;
import picks.Pick_Names;
import picks.Pick_Login;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author john
 */
public class Delete_Repos {

    String file = "/Users/john/Desktop/Files/Results/percentage/Percentage-Collection_1-400-1.xlsx";
    String new_file1 = "/Users/john/Desktop/Files/Results/deleted/Deleted-Collection_1-400.xlsx";
    Object[] datas = null;
    Delete_Repos() throws Exception {
        deleteRepos();
    }
    public static void main(String[] g) throws Exception {
        new Delete_Repos();
    }
    private int getWorksheets() throws Exception {
        Workbook workbook = new OpenFileName().readFileName(file);
        int sheetCounts = workbook.getNumberOfSheets();
        return sheetCounts;
    }
    private String getWorksheetName(int sheet) throws Exception {
        Workbook workbook = new OpenFileName().readFileName(file);
        String sheetName = workbook.getSheetName(sheet);

        return sheetName;
    }
    private void deleteRepos() throws Exception {
        datas = new Object[]{"PROJECT NAME", "PR OPEN", "PR CLOSSED", "IS OPEN", "IS CLOSED", "FORKS", "NAME",
            "EMAIL", "LOGIN", "PERCENTAGE_COMITS( % )", "PERCENTAGE_CHANGED( % )", "80%", " ", "85%", "", "90%"
        };// end of assigning the header to the object..
        int numbers = getWorksheets();
        int count = 0;
        while (count < 2) {
            ArrayList< Object[]> allobj = new ArrayList<Object[]>();
            allobj.add(datas);

            List<String> nameList = new ArrayList<>();
            List<String> emailList = new ArrayList<>();
            List<String> loginList = new ArrayList<>();

            nameList = new Pick_Names().pickDatas(file, count);
            emailList = new Pick_Emails().pickDatas(file, count);
            loginList = new Pick_Login().pickDatas(file, count);

            for (int i = 0; i < loginList.size(); i++) {

                Set<String> nameSet = new LinkedHashSet<String>();
                Set<String> fullSet = new LinkedHashSet<String>();
                Set<String> androidSet = new LinkedHashSet<String>();

                List<String> nList = new ArrayList<>();
                List<String> fList = new ArrayList<>();

                List<Double> totList = new ArrayList<>();
                List<Double> tList = new ArrayList<>();
                System.out.println(i + ": \t" + nameList.get(i) + "\t" + emailList.get(i) + "\t" + loginList.get(i));

            }
            count++;
        }
    }
}
