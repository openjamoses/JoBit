package Opm_Package;

import static Opm_Package.TestDate.filePath;
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
    private static final String[] toks = {"d9fa524eb803729fff1a327ad670f5615fefb822",
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
        "bfd9c16567a847212a7c944cbf6e081d5a8d6f5b"
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
