/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import Opm_Package.Call_URL;
import Opm_Package.Count_Users_Commits;
import Opm_Package.JSONUtils;
import Opm_Package.OpenFileName;
import picks.Pick_NewProject;
import picks.Pick_Emails;
import picks.Pick_Names;
import picks.Pick_Login;
import excel.Excell_Percentage_Commits;
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

    String file = "Percentage-Collection_1-400_1.xlsx";
    String new_file1 = "Repos-Collection_1-400_1.xlsx";
    OpenFileName openFile = new OpenFileName();

    Collect_Users() throws Exception {
        //collectUsers();
    }

    public static void main(String[] g) throws Exception {

        System.out.println("Fetching Logins, pliz wait ..... ");
        new Collect_Users().collectUsers();
    }

    private int getWorksheets() throws Exception {
        Workbook workbook = openFile.readFileName(file);
        int sheetCounts = workbook.getNumberOfSheets();
        return sheetCounts;
    }

    private String getWorksheetName(int sheet) throws Exception {
        Workbook workbook = openFile.readFileName(file);
        String sheetName = workbook.getSheetName(sheet);

        return sheetName;
    }

    private void collectUsers() throws Exception {

        String status_returned = "fine";
        ArrayList< Object[]> allobj2 = new ArrayList<Object[]>();
        ///Store the Header First here

        //allobj2.add(datas);
        String[] tokens = {
            
        };
        JSONParser parser = new JSONParser();
        double total = 0;

        int c_p = 0;
        int num = 0;
        int ct = 0;
        long stopTime = 0;
        long elapsedTime = 0;
        long startTime1 = System.currentTimeMillis();
        int numbers = getWorksheets();
        int count = 52;
        while (count < numbers) {
            long startTime = System.currentTimeMillis();
            c_p++;
            ArrayList< Object[]> allobj = new ArrayList<Object[]>();
            datas = new Object[]{"N/S", "PROJECT_NAME", "NAME", "EMAIL", "LOGIN", "FORK", "PROJECTS(General)", "PROJECTS(Android)", "COMMITS MADE"};// end of assigning the header to the object..
            allobj.add(datas);

            List<String> nameList = new ArrayList<>();
            List<String> emailList = new ArrayList<>();
            List<String> loginList = new ArrayList<>();

            nameList = new Pick_Names().pickDatas(file, count);
            emailList = new Pick_Emails().pickDatas(file, count);
            loginList = new Pick_Login().pickDatas(file, count);

            String project = new Pick_NewProject().setProjectName(file, count);
            //System.out.println(project + "\t" + nameList + "\t" + emailList);
            for (int i = 0; i < loginList.size(); i++) {
                total++;
                Set<String> nameSet = new LinkedHashSet<String>();
                Set<String> fullSet = new LinkedHashSet<String>();
                Set<String> invalidSet = new LinkedHashSet<String>();
                Set<String> androidSet = new LinkedHashSet<String>();

                Set<String> forkSet = new LinkedHashSet<String>();

                List<String> nList = new ArrayList<>();
                List<String> fList = new ArrayList<>();

                List<Double> totList = new ArrayList<>();
                List<Double> tList = new ArrayList<>();
                ///////
                if (!loginList.get(i).equals("login######")) {
                    int p = 1; // Page number parameter
                    //int i = 0; // Commit Counter
                    int count2 = 0;
                    while (true) {////loop thru the pagess....
                        if (ct == (tokens.length)) {/// the the index for the tokens array...
                            ct = 0; //// go back to the first index......
                        }
                        String url = "https://api.github.com/users/" + loginList.get(i) + "/repos?page=" + p + "&per_page=100&access_token=" + tokens[ct++];
                        String reposObj = new Call_URL().callURL(url);

                        if (JSONUtils.isValidJSON(reposObj) == true) {

                            JSONArray jsonArray = (JSONArray) parser.parse(reposObj);
                            if (jsonArray.toString().equals("[]")) {
                                /// Break out of the loop, when empty array is found!
                                break;
                            }
                            for (Object jsonObj : jsonArray) {
                                count2++;
                                JSONObject jsonObject = (JSONObject) jsonObj;

                                String name = (String) jsonObject.get("name");

                                String full_name = (String) jsonObject.get("full_name");
                                boolean fork = (boolean) jsonObject.get("fork");
                                double total_commits = 0;
                                if (!fork) {
                                    forkSet.add(full_name);
                                    //totList.add(total_commits);
                                    //System.out.println(fork+":\t"+full_name);
                                }

                                String description = "description";
                                if ((String) jsonObject.get("description") != null) {
                                    description = (String) jsonObject.get("description");
                                }
                                if (fork == false) {
                                    //// Check the number of commits
                                    List<String> c_returned = new ArrayList<>();
                                    c_returned = new Count_Users_Commits().countCommits(full_name, loginList.get(i), tokens, ct);
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

                                    if (!description.equals("description")) {
                                        if (description.contains("Android") || full_name.contains("Android") || description.contains("android") || full_name.contains("android")) {
                                            androidSet.add(full_name);
                                        }
                                    }

                                    if (description.contains("Android") || full_name.contains("Android") || description.contains("android") || full_name.contains("android")) {
                                        //System.out.println(count2+" : LOGIN: "+loginList.get(i)+"\t \t PROJECT: "+full_name+": \t Android \t Commits: "+total_commits);

                                    } else {
                                        // System.out.println(count2+" : LOGIN: "+loginList.get(i)+"\t \t name: "+full_name+",\t\t\t Commits: "+total_commits);
                                    }
                                    //}

                                }
                                if (status_returned.equals("not-fine")) {

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
                    List<String> lists1 = new ArrayList<>();
                    List<String> lists2 = new ArrayList<>();

                    List<String> forList = new ArrayList<>();
                    List<String> inv_List = new ArrayList<>();
                    List<String> androidList = new ArrayList<>();

                    while (f_interator.hasNext()) {
                        forList.add((String) f_interator.next());
                    }
                    while (i_interator.hasNext()) {
                        inv_List.add((String) i_interator.next());
                    }
                    double proj_count = 0;
                    while (iterator.hasNext()) {
                        androidList.add((String) iterator.next());
                    }

                    while (iterator2.hasNext()) {
                        lists2.add((String) iterator2.next());
                    }

                    for (int y = 0; y < lists2.size(); y++) {
                        double tt = 0.0;
                        tList.add(tt);
                        lists1.add("Name###");
                    }
                    for (int a = 0; a < fList.size(); a++) {
                        String name1 = fList.get(a);
                        for (int b = 0; b < lists2.size(); b++) {
                            if (lists2.get(b).equals(name1)) {
                                tList.set(b, tList.get(b) + totList.get(a));
                                lists1.set(b, nList.get(a));
                            }
                        }
                    }

                    for (int a = 0; a < lists2.size(); a++) {
                        proj_count++;

                        int flag = 0;
                        String developer = "human";
                        /// Store all the project on the rows.... 
                        String gen_str = lists2.get(a);
                        for (int b = 0; b < androidList.size(); b++) {
                            if (androidList.get(b).equals(gen_str)) {
                                flag = 1;
                            }
                        }

                        for (int c = 0; c < inv_List.size(); c++) {
                            if (inv_List.get(c).equals(gen_str)) {
                                developer = "robot";
                            }
                        }
                        if (flag == 1) {
                            if (a == 0) {
                                datas = new Object[]{total, project, nameList.get(i), emailList.get(i), loginList.get(i), developer, lists2.get(a), lists2.get(a), tList.get(a), ""};// end of assigning the header to the object..

                            } else {
                                datas = new Object[]{"", "", "", "", "", developer, lists2.get(a), lists2.get(a), tList.get(a), ""};// end of assigning the header to the object..

                            }

                        } else {

                            if (a == 0) {

                                datas = new Object[]{total, project, nameList.get(i), emailList.get(i), loginList.get(i), developer, lists2.get(a), "", tList.get(a), ""};// end of assigning the header to the object..

                            } else {
                                datas = new Object[]{"", "", "", "", "", developer, lists2.get(a), "", tList.get(a), ""};// end of assigning the header to the object..

                            }

                        }
                        allobj.add(datas);

                        //allobj2.add(datas);
                    }

                    double f1 = forList.size();
                    double f2 = lists2.size() - f1;
                    datas = new Object[]{"", "", "", "", "", "Robot = " + inv_List.size(), lists2.size(), androidList.size(), ""};// end of assigning the header to the object..
                    allobj.add(datas);
                    //allobj2.add(datas);

                }

            }/// End of for loop
            new Excell_Percentage_Commits().createExcel(allobj, count, new_file1, getWorksheetName(count));

            stopTime = System.currentTimeMillis();
            elapsedTime = stopTime - startTime;
            System.out.println("ElapsedTime in minutes = " + elapsedTime / (1000 * 60));
            count++;

        }//// End of the first while loop

        stopTime = System.currentTimeMillis();
        elapsedTime = stopTime - startTime1;
        System.out.println("ElapsedTime in minutes = " + elapsedTime / (1000 * 60));

    }/// End of the Method..

}
