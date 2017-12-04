/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import Opm_Package.OpenFileName;
import Opm_Package.Sorts;
import picks.Pick_ISClosed;
import picks.Pick_Dates;
import picks.Pick_PROpen;
import picks.Pick_ProjectName;
import picks.Pick_Forks;
import picks.Pick_ISOpen;
import picks.Pick_Commits;
import picks.Pick_PRClosed;
import excel.Excell_Percentage_Commits;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author john
 */
public class Percentage_Commits_Checking {

    XSSFRow rows;
    int rowid = 0;
    XSSFWorkbook workbook2;
    XSSFSheet sheet;
    String projectName;
    Object[] datas = null;
    Object[] datas2 = null;
    Object[] datas3 = null;
    Object[] datas4 = null;

    String file = "/Users/john/Desktop/DESKTOP/Files/Results/Merged-Collection_1600-1805-2.xlsx";
    String new_file = "/Users/john/Desktop/DESKTOP/Files/Results/percentage2/Percentage-Collection_1600-1805_1.xlsx";
    //String new_file2 = "/Users/john/Downloads/Apps-forks/Results/Percentage-Collection_1-400-2.xlsx";
    String new_file3 = "/Users/john/Desktop/DESKTOP/Files/Results/percentage2/Percentage-Collection_1600-1805_2.xlsx";
    OpenFileName openFile = new OpenFileName();
    //Pick_Dates pDate = new Pick_Dates();
    //Pick_Commits pCommits = new Pick_Commits();
    Excell_Percentage_Commits percentage_commits = new Excell_Percentage_Commits();

    Percentage_Commits_Checking() throws Exception {
        percentage();
    }

    public static void main(String[] g) throws Exception {

        System.out.println("Checking percentage commits, pliz wait ..... ");
        new Percentage_Commits_Checking();
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

    public void percentage() throws Exception {

        //List< List<String> > allLists = new ArrayList<>();
        //List<String> projectlist = new ArrayList<>();
        ArrayList< Object[]> allobj2 = new ArrayList<Object[]>();
        ArrayList< Object[]> allobj3 = new ArrayList<Object[]>();
        ArrayList< Object[]> allobj4 = new ArrayList<Object[]>();
        /// Writting the header....
        //allobj.clear();
        /// Writting the header....
        datas2 = new Object[]{"PROJECT NAME", "NAME",
            "EMAIL", "LOGIN", "PR OPEN", "PR CLOSSED", "IS OPEN", "IS CLOSED", "FORKS", "",
            "COMMITS", "CHANGED", "ADDED", "DELETED", "PERCENTAGE_COMITS( % )", "PERCENTAGE_CHANGED( % )"
        };// end of assigning the header to the object..
        allobj2.add(datas2);

        datas3 = new Object[]{"PROJECT NAME", "PR OPEN", "PR CLOSSED", "IS OPEN", "IS CLOSED", "FORKS", "NAME",
            "EMAIL", "LOGIN", "PERCENTAGE_COMITS( % )", "PERCENTAGE_CHANGED( % )", "80%", " ", "85%", "", "90%"
        };// end of assigning the header to the object..
        allobj3.add(datas3);

        datas4 = new Object[]{"PROJECT NAME", "NAME", "PR OPEN", "PR CLOSSED", "IS OPEN", "IS CLOSED", "FORKS", "WATCH",
            "EMAIL", "LOGIN", "PERCENTAGE_COMITS( % )", "PERCENTAGE_CHANGED( % )", "80%"
        };// end of assigning the header to the object..
        allobj4.add(datas4);

        int numbers = getWorksheets();
        int count = 0;

        while (count < numbers) {
            // System.out.println();
            List< String> nameList = new ArrayList<>();
            List< String> emailList = new ArrayList<>();
            List< String> loginList = new ArrayList<>();
            List< String> commitsList = new ArrayList<>();

            Set<String> loginSet = new LinkedHashSet<String>();

            List<String> lists = new ArrayList<>();
            List<String> lists2 = new ArrayList<>();
            List<String> lists3 = new ArrayList<>();

            List<String> dateslists = new ArrayList<>();

            List<String> prOpen = new ArrayList<>();
            List<String> prClosed = new ArrayList<>();
            List<String> isOpen = new ArrayList<>();
            List<String> isClosed = new ArrayList<>();
            List<String> forks = new ArrayList<>();
            // List<String> watch = new ArrayList<>();

            ArrayList< Object[]> allobj = new ArrayList<Object[]>();
            /// Writting the header....
            //allobj.clear();
            /// Writting the header....
            datas = new Object[]{"PROJECT NAME", "NAME",
                "EMAIL", "LOGIN", "PR OPEN", "PR CLOSSED", "IS OPEN", "IS CLOSED", "FORKS", "WATCH",
                "COMMITS", "CHANGED", "ADDED", "DELETED", "PERCENTAGE_COMITS ( % )", "PERCENTAGE_CHANGED ( % )"
            };// end of assigning the header to the object..
            // allobj.add(datas);
            datas3 = new Object[]{"PROJECT NAME", "NAME",
                "EMAIL", "LOGIN", "PR OPEN", "PR CLOSSED", "IS OPEN", "IS CLOSED", "FORKS", "PERCENTAGE_COMITS( % )", "PERCENTAGE_CHANGED( % )", "80%", " ", "85%", "", "90%"
            };//

            allobj.add(datas3);

            //allLists = openFile.readCommits3(file,count);
            lists = new Pick_Commits().pickDatas(file, count);

            dateslists = new Pick_Dates().pickDatas(file, count);
            prOpen = new Pick_PROpen().pickDatas(file, count);
            prClosed = new Pick_PRClosed().pickDate(file, count);
            isOpen = new Pick_ISOpen().pickDatas(file, count);
            isClosed = new Pick_ISClosed().pickDatas(file, count);
            forks = new Pick_Forks().pickDatas(file, count);
            //  watch = new Pick_Watches().pickDatas(file, count);
            // projectlist = allLists.get(8);
            if (lists.size() == 0) {
                break;
            }

            String project = new Pick_ProjectName().setProjectName(file, count);

            System.out.println(project + "\t" + lists.size());
            datas = new Object[]{project, "",
                "", "", "", "", "", "", "", "",
                "", "", "", "", ""
            };// end of assigning the header to the object..
            ///allobj.add(datas);

            datas2 = new Object[]{project, "",
                "", "", "", "", "", "", "", "",
                "", "", "", "", ""
            };// end of assigning the header to the object..
            allobj2.add(datas2);

            datas3 = new Object[]{project, "",
                "", "", "", "", "", "", "", "",
                "", "", "", "", ""
            };// end of assigning the header to the object..
            allobj.add(datas3);
            allobj3.add(datas3);

            datas4 = new Object[]{project, "",
                "", "", "", "", "", "", "", "",
                "", "", "", "", ""
            };// end of assigning the header to the object..
            allobj4.add(datas4);

            String name, email, login, commits;
            for (int i = 0; i < lists.size(); i++) {
                //System.out.println(prOpen.get(i));
                if (!lists.get(i).equals("-")) {
                    String[] splits = lists.get(i).split("/");
                    name = splits[0];
                    email = splits[1];
                    login = splits[2];

                    nameList.add(name);
                    emailList.add(email);
                    loginList.add(login);

                    loginSet.add(login);

                    commits = lists.get(i).substring(lists.get(i).lastIndexOf("/") + 1, lists.get(i).length());
                    commitsList.add(commits);

                    //System.out.println(i+"\t\t"+name+"\t \t"+email+"\t \t"+login+"\t"+commits);
                }

            }

            //System.out.println("\n\n");
            List<String> iList = new ArrayList<>();
            List<String> nList = new ArrayList<>();
            List<String> eList = new ArrayList<>();
            Iterator iterator = loginSet.iterator();

            List< Double> com = new ArrayList<>();
            List< Double> cha = new ArrayList<>();
            List< Double> add = new ArrayList<>();
            List< Double> dell = new ArrayList<>();

            List< Double> prO = new ArrayList<>();
            List< Double> prC = new ArrayList<>();
            List< Double> isO = new ArrayList<>();
            List< Double> isC = new ArrayList<>();
            List< Double> frk = new ArrayList<>();
            List< Double> wac = new ArrayList<>();

            while (iterator.hasNext()) {
                iList.add((String) iterator.next());

                long c = 0;
                double d = 0;

                com.add(d);
                cha.add(d);
                add.add(d);
                dell.add(d);
                prO.add(d);
                prC.add(d);
                isO.add(d);
                isC.add(d);
                frk.add(d);

                nList.add("Name");
                eList.add("Email");
                // wac.add(d);
            }

            for (int i = 0; i < loginList.size(); i++) {
                for (int j = 0; j < iList.size(); j++) {
                    if (iList.get(j).equals(loginList.get(i))) {
                        nList.set(j, nameList.get(i));
                        eList.set(j, emailList.get(i));
                    }
                }
            }

            for (int i = 0; i < loginList.size(); i++) {
                commits = commitsList.get(i);
                double commit = 0;
                double changed = 0;
                double added = 0;
                double deleted = 0;

                double prOp = 0.0;
                double prCl = 0;
                double isOp = 0;
                double isCl = 0;
                double fork = 0;
                // double warc = 0;

                for (int j = 0; j < iList.size(); j++) {

                    if (iList.get(j).equals(loginList.get(i))) {
                        /////..................................

                        String[] splits2 = commits.split("_");
                        /**
                         *
                         */
                        ///....................................
                        if (splits2.length > 3) {

                            commit += Double.parseDouble(splits2[0]);
                            changed += Double.parseDouble(splits2[1]);
                            added += Double.parseDouble(splits2[2]);
                            deleted += Double.parseDouble(splits2[3]);

                            if (i == 0 && j == 0) {

                                prOp = Double.parseDouble(prOpen.get(i));
                                prCl = Double.parseDouble(prClosed.get(i));
                                isOp = Double.parseDouble(isOpen.get(i));
                                isCl = Double.parseDouble(isClosed.get(i));
                                fork = Double.parseDouble(forks.get(i));

                            }

                            com.set(j, com.get(j) + commit);
                            cha.set(j, cha.get(j) + changed);
                            add.set(j, add.get(j) + added);
                            dell.set(j, dell.get(j) + deleted);

                            prO.set(j, prO.get(j) + prOp);
                            prC.set(j, prC.get(j) + prCl);
                            isO.set(j, isO.get(j) + isOp);
                            isC.set(j, isC.get(j) + isCl);
                            frk.set(j, frk.get(j) + fork);
                            //wac.set(j, wac.get(j)+warc);

                            //System.out.println(Integer.parseInt(splits2[0])+"\t"+Integer.parseInt(splits2[1])+"\t"+Integer.parseInt(splits2[2])+"\t"+Integer.parseInt(splits2[3]));
                        } else {
                            // System.out.println("- ");
                        }
                    }
                }
            }
            int total = 0;
            int total2 = 0;
            for (int a = 0; a < cha.size(); a++) {
                ///
                total += cha.get(a);
                total2 += com.get(a);
            }

            List<Double> chagedpList = new ArrayList<>();
            List<Double> commitpList = new ArrayList<>();

            for (int j = 0; j < iList.size(); j++) {
                DecimalFormat df = new DecimalFormat("0.0");
                double percent = Double.parseDouble(df.format(cha.get(j) * 100 / total));

                double percent2 = Double.parseDouble(df.format(com.get(j) * 100 / total2));
                chagedpList.add(percent);
                commitpList.add(percent2);

                /**
                 *
                 * //System.out.println("%ge = "+percent+"% \t\t\t
                 * "+nList.size()+"\t ,"+eList.size()+"\t, "+iList.size()+"\t\t
                 * COMMITS: "+com.size()+"\t CHANGED: "+cha.size()+"\t ADDED:
                 * "+add.size()+"\t DELETED: "+dell.size()+"\t\t, PRO:
                 * "+prO.get(j)+" ,PRC:"+prC.size()+" ,ISO: "+isO.size()+" ,ISC:
                 * "+isC.size()+" ,FRK: "+frk.size() ); datas = new Object[]{" -
                 * ",nList.get(j),
                 * eList.get(j),iList.get(j),prO.get(j),prC.get(j),isO.get(j),isC.get(j),frk.get(j),"",
                 * com.get(j),cha.get(j), add.get(j), dell.get(j) ,percent2
                 * ,percent };// end of assigning the header to the object..
                 * allobj.add(datas);
                 *
                 *
                 * datas2 = new Object[]{"",nList.get(j),
                 * eList.get(j),iList.get(j),prO.get(j),prC.get(j),isO.get(j),isC.get(j),frk.get(j),"",
                 * com.get(j),cha.get(j), add.get(j), dell.get(j)
                 * ,percent2,percent };// end of assigning the header to the
                 * object.. allobj2.add(datas2); *
                 */
            }

            ///// Now we pass the all the list to the sorting class....
            Sorts sort = new Sorts();
            sort.insertionSort(chagedpList, commitpList, nList, eList, iList);

            ///***************************** For 80%
            List<String> nList_i = new ArrayList<>();
            List<String> eList_i = new ArrayList<>();
            List<String> iList_i = new ArrayList<>();
            List<Double> comList_i = new ArrayList<>();
            List<Double> chaList_i = new ArrayList<>();

            List<String> nList_j = new ArrayList<>();
            List<String> eList_j = new ArrayList<>();
            List<String> iList_j = new ArrayList<>();
            List<Double> comList_j = new ArrayList<>();
            List<Double> chaList_j = new ArrayList<>();

            ///***************************** For 85%
            List<String> nList_i2 = new ArrayList<>();
            List<String> eList_i2 = new ArrayList<>();
            List<String> iList_i2 = new ArrayList<>();
            List<Double> comList_i2 = new ArrayList<>();
            List<Double> chaList_i2 = new ArrayList<>();

            List<String> nList_j2 = new ArrayList<>();
            List<String> eList_j2 = new ArrayList<>();
            List<String> iList_j2 = new ArrayList<>();
            List<Double> comList_j2 = new ArrayList<>();
            List<Double> chaList_j2 = new ArrayList<>();

            ///***************************** For 90%
            List<String> nList_i3 = new ArrayList<>();
            List<String> eList_i3 = new ArrayList<>();
            List<String> iList_i3 = new ArrayList<>();
            List<Double> comList_i3 = new ArrayList<>();
            List<Double> chaList_i3 = new ArrayList<>();

            List<String> nList_j3 = new ArrayList<>();
            List<String> eList_j3 = new ArrayList<>();
            List<String> iList_j3 = new ArrayList<>();
            List<Double> comList_j3 = new ArrayList<>();
            List<Double> chaList_j3 = new ArrayList<>();

            /// major 80% starts here..
            double value = 0;
            if (chagedpList.get(0) >= 80) {
                value = chagedpList.get(0);
                chaList_i.add(value);
                comList_i.add(commitpList.get(0));
                nList_i.add(nList.get(0));
                eList_i.add(eList.get(0));
                iList_i.add(iList.get(0));

                for (int j = 1; j < chagedpList.size(); j++) {
                    value = chagedpList.get(j);
                    chaList_j.add(value);
                    comList_j.add(commitpList.get(j));
                    nList_j.add(nList.get(j));
                    eList_j.add(eList.get(j));
                    iList_j.add(iList.get(j));
                }

            } else {

                for (int j = 0; j < chagedpList.size(); j++) {
                    value += chagedpList.get(j);
                    if (value <= 80 || (value - chagedpList.get(j)) <= 80) {
                        chaList_i.add(chagedpList.get(j));
                        comList_i.add(commitpList.get(j));
                        nList_i.add(nList.get(j));
                        eList_i.add(eList.get(j));
                        iList_i.add(iList.get(j));

                    } else {
                        chaList_j.add(chagedpList.get(j));
                        comList_j.add(commitpList.get(j));
                        nList_j.add(nList.get(j));
                        eList_j.add(eList.get(j));
                        iList_j.add(iList.get(j));

                    }

                }

            }
            /// major 80% stops here..

            /// major 85% starts here..
            double value2 = 0;

            if (chagedpList.get(0) >= 85) {
                value2 = chagedpList.get(0);
                chaList_i2.add(value);
                comList_i2.add(commitpList.get(0));
                nList_i2.add(nList.get(0));
                eList_i2.add(eList.get(0));
                iList_i2.add(iList.get(0));

                for (int j = 1; j < chagedpList.size(); j++) {
                    value2 = chagedpList.get(j);
                    chaList_j2.add(value);
                    comList_j2.add(commitpList.get(j));
                    nList_j2.add(nList.get(j));
                    eList_j2.add(eList.get(j));
                    iList_j2.add(iList.get(j));
                }

            } else {

                double val_temp = 0;

                for (int j = 0; j < chagedpList.size(); j++) {
                    value2 += chagedpList.get(j);
                    if (value2 <= 85 || (value2 - chagedpList.get(j)) <= 85) {
                        chaList_i2.add(chagedpList.get(j));
                        comList_i2.add(commitpList.get(j));
                        nList_i2.add(nList.get(j));
                        eList_i2.add(eList.get(j));
                        iList_i2.add(iList.get(j));

                    } else {
                        chaList_j2.add(chagedpList.get(j));
                        comList_j2.add(commitpList.get(j));
                        nList_j2.add(nList.get(j));
                        eList_j2.add(eList.get(j));
                        iList_j2.add(iList.get(j));

                    }

                }

            }
            /// major 85% stops here..

            /// major 90% starts here..
            double value3 = 0;

            if (chagedpList.get(0) >= 90) {
                value3 = chagedpList.get(0);
                chaList_i3.add(value);
                comList_i3.add(commitpList.get(0));
                nList_i3.add(nList.get(0));
                eList_i3.add(eList.get(0));
                iList_i3.add(iList.get(0));

                for (int j = 1; j < chagedpList.size(); j++) {
                    value3 = chagedpList.get(j);
                    chaList_j3.add(value);
                    comList_j3.add(commitpList.get(j));
                    nList_j3.add(nList.get(j));
                    eList_j3.add(eList.get(j));
                    iList_j3.add(iList.get(j));
                }

            } else {

                for (int j = 0; j < chagedpList.size(); j++) {
                    value3 += chagedpList.get(j);
                    if (value3 <= 90 || (value3 - chagedpList.get(j)) <= 90) {
                        chaList_i3.add(chagedpList.get(j));
                        comList_i3.add(commitpList.get(j));
                        nList_i3.add(nList.get(j));
                        eList_i3.add(eList.get(j));
                        iList_i3.add(iList.get(j));

                    } else {
                        chaList_j3.add(chagedpList.get(j));
                        comList_j3.add(commitpList.get(j));
                        nList_j3.add(nList.get(j));
                        eList_j3.add(eList.get(j));
                        iList_j3.add(iList.get(j));

                    }

                }

            }
            /// major 85% stops here..

            for (int j = 0; j < iList.size(); j++) {

                /// Category 80%.....
                String category = "";
                String delete = "None";
                for (int a = 0; a < iList_i.size(); a++) {
                    if (iList.get(j).equals(iList_i.get(a))) {
                        category = "Major";
                        if (iList.get(j).equals("")) {
                            delete = "All";
                        }
                    }
                }
                for (int b = 0; b < iList_j.size(); b++) {
                    if (iList.get(j).equals(iList_j.get(b))) {
                        category = "Minor";
                        if (iList.get(j).equals("")) {
                            delete = "One";
                        }
                    }
                }
                ///******************************************************

                ///Category 85% 
                String category2 = "";
                String delete2 = "None";
                for (int a = 0; a < iList_i2.size(); a++) {
                    if (iList.get(j).equals(iList_i2.get(a))) {
                        category2 = "Major";
                        if (iList.get(j).equals("")) {
                            delete2 = "All";
                        }
                    }
                }
                for (int b = 0; b < iList_j2.size(); b++) {
                    if (iList.get(j).equals(iList_j2.get(b))) {
                        category2 = "Minor";
                        if (iList.get(j).equals("")) {
                            delete2 = "One";
                        }
                    }
                }
                ///******************************************************
                ///Category 90%
                String category3 = "";
                String delete3 = "None";
                for (int a = 0; a < iList_i3.size(); a++) {

                    if (iList.get(j).equals(iList_i3.get(a))) {
                        category3 = "Major";
                        if (iList.get(j).equals("")) {
                            delete3 = "All";
                        }
                    }
                }
                for (int b = 0; b < iList_j3.size(); b++) {
                    if (iList.get(j).equals(iList_j3.get(b))) {
                        category3 = "Minor";
                        if (iList.get(j).equals("")) {
                            delete3 = "One";
                        }
                    }
                }
                ///******************************************************

                datas3 = new Object[]{"", nList.get(j),
                    eList.get(j), iList.get(j), prO.get(j), prC.get(j), isO.get(j), isC.get(j), frk.get(j),
                    commitpList.get(j), chagedpList.get(j), category, "", category2, "", category3
                };// end of assigning the header to the object..

                allobj.add(datas3);
                allobj3.add(datas3);

            }
            //*********************************************************
            //System.out.println(count+"\t"+project);
            percentage_commits.createExcel(allobj, count, new_file, getWorksheetName(count));

            datas2 = new Object[]{"", "",
                "", "", "", "", "", "", "", "",
                "", "", "", "", ""
            };// end of assigning the header to the object..
            allobj2.add(datas2);

            datas3 = new Object[]{"", "",
                "", "", "", "", "", "", "", "",
                "", "", "", "", ""
            };// end of assigning the header to the object..
            allobj3.add(datas3);

            count++;
        } // End of while loop 

        //percentage_commits.createExcel(allobj2,count,new_file2,"all_sheets_together");
        // percentage_commits.createExcel(allobj3,count,new_file3,"all_sheets_together");
    }

}
