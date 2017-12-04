package main;

import Opm_Package.OpenFileName;
import Opm_Package.ReadCommits;
import static main.TestDate.filePath;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.xssf.usermodel.XSSFSheet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author john
 */
public class Main {

    private static int commits_interval = 14;
    private static String toDay = "2017-07-06T00:00:00Z";
    private static final String filePath = "missings_projects.xlsx";
    private static final String[] toks = {
    };

    /**
     * @param args the command line arguments
     */
    Main() throws Exception {
        getReposName();
    }

    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        System.out.println("Started Please wait ...");
        //System.out.println("\n");
        //new Collect_Users()._main(args);
        new Main();
    }

    public String[] getTokens() {

        return toks;
    }

    public void getReposName() throws Exception {
        OpenFileName fileName = new OpenFileName();
        List<String> list = fileName.readReposNames(filePath);
        List<String> sheetsList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            String[] splits = list.get(i).split("/");
            if (splits[0].length() < 25) {
                sheetsList.add(splits[0] + "_" + i);
            } else {
                String sheet = splits[0].subSequence(0, 25).toString();
                sheetsList.add(sheet + "_" + i);
            }

            //System.out.println("Projects: "+list.get(i)+", \t \t Sheets: "+sheetsList.get(i));
        }
        // To convert List in to array of Strings..
        String[] repos_array = new String[list.size()];
        String[] sheet_array = new String[sheetsList.size()];
        /////////             
        repos_array = list.toArray(repos_array);
        sheet_array = sheetsList.toArray(sheet_array);
        XSSFSheet[] sheet = new XSSFSheet[repos_array.length];
        int count = 0;

        long stopTime = 0;
        long elapsedTime = 0;
        long startTime1 = System.currentTimeMillis();

        for (int i = 771; i < repos_array.length; i++) {
            //if(i > 0){		long startTime = System.currentTimeMillis();

            long startTime = System.currentTimeMillis();
            ReadCommits readCommit = new ReadCommits();
            System.out.println("Reading Repos_number: " + i + " \t Repo_Name: " + repos_array[i] + "..\tPlease wait...");
            readCommit.readCommitsNow(repos_array[i], toks, sheet_array[i], count, sheet, toDay);
            //}
            count++;

            stopTime = System.currentTimeMillis();
            elapsedTime = stopTime - startTime;
            System.out.println("ElapsedTime in minutes = " + elapsedTime / (1000 * 60));
        }
        stopTime = System.currentTimeMillis();
        elapsedTime = stopTime - startTime1;
        System.out.println("ElapsedTime in minutes = " + elapsedTime / (1000 * 60));

    }
}
