/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author john
 */
public class TestDate {
    static ArrayList<String> dateList1 = new ArrayList<String>();
    static ArrayList<String> dateList2 = new ArrayList<String>();
    static String filePath = "/Users/john/Desktop/PROJECTS/JSON WORK/FINAL_WORKSS#2.xlsx";
    TestDate() throws Exception{
       //read(filePath) ;
       test();
    }
    public static void main(String[] args) throws ParseException, IOException, Exception{
        new TestDate();
       
       
       
        String dt = "2016-05-29T10:29:44Z";  // Start date
        String dt4 = "2016-05-30T10:29:44Z";  // Start date
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        
        String sd = dt.substring(dt.lastIndexOf(":")+1,dt.length()-1);
        System.out.println(sd);
        
        Calendar cc1 = Calendar.getInstance();
        cc1.setTime(s.parse(dt));
        Calendar cc4 = Calendar.getInstance();
        cc4.setTime(s.parse(dt4));
       
        
        if(cc1.after(cc4)){
           System.out.println("D1 greater than D4");
        }else if (cc1.equals(cc4)){
             System.out.println("D1 Equal to D4");
        }else{
            System.out.println("D1 Less than D4");
        }
        
        
        int i2 =2;
        for(int i=1; i<20; i++){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String subString = dt.substring(10, 20);
        Calendar c = Calendar.getInstance();
       
        c.setTime(sdf.parse(dt));
        c.add(Calendar.DATE, 7*i);  // number of days to add
        String dt2 = sdf.format(c.getTime());  // dt is now the new date
        
        //int increamented = Integer.parseInt(dt.substring(17,19)) + 1;
        int increamented  = Integer.parseInt(subString.substring(subString.length()-3,subString.length()-1)) + 1;
        String i_Z = increamented+"Z";
        
        String sub = subString.substring(subString.length()-3,subString.length()-1);
        String replace_sub = subString.replace(sub, increamented+"");
        
        String n_dt2 = dt2+""+replace_sub;
        dateList1.add(dt2+""+subString);
        //String i_dt = dt2+""+subString
        System.out.println(dt+" - "+dt2+""+subString+" \t\t"+increamented+"\t\t"+sub+"\t\t"+n_dt2);  
        /// We are also here..........
        }   
    }
    public XSSFWorkbook readFileName(String excelFilePath) throws Exception{
        // Spacifying the path to the excel to be read...
        
        FileInputStream fname = new FileInputStream(new File(excelFilePath));

        // creating the workbook with the file ......
	XSSFWorkbook workbook = new XSSFWorkbook(fname);
        //returning the workbook to the caller
        return workbook;
    }
    
    public List<String> readCommitsFromExceell(String file) throws Exception{
        int x=1;
        // array list to store the Repos names
        ArrayList <String> list = new ArrayList <String> ();
        //calling the file name.....
        XSSFWorkbook workbook = readFileName(file);
       // setting the sheet number...
        XSSFSheet spreadsheet = workbook.getSheetAt(x);
        String sname = workbook.getSheetName(x);
      
	Row row;
        Cell cell=null;
        for (int j=0; j< spreadsheet.getLastRowNum()+1; ++j) {//To loop thru the rows in a sheet
           row = spreadsheet.getRow(j);
           for(int i=8; i<8+spreadsheet.getLeftCol()+1; i++){
           cell = row.getCell(i); //commits are in the eighth column...
       
           if (cell != null){
           switch (cell.getCellType()){
               //Checking for strings values inthe cells..
               case Cell.CELL_TYPE_STRING:
                   if (!cell.getStringCellValue().equals("")){
                      // adding the call value to the arraylist called forksList 
                      list.add(cell.getStringCellValue());
                      System.out.println(i+":\t\t"+cell.getStringCellValue());
                    }//end of if statement...
                    break;
                //Checking for numeric values inthe cells..
                case Cell.CELL_TYPE_NUMERIC:
                   list.add( String.valueOf(cell.getNumericCellValue()) );
                   break;
                //Checking for bank in the cells..
                case Cell.CELL_TYPE_BLANK:
                    break;
             }//end of switch statement
           }
           
           
           }
         
       }// end of  for loop for the rows..
        
       //returns the arraylist to the main class....
       return list;
    } 
    
    public void read(String file) throws Exception{
       // String excelFilePath = "Books.xlsx";
        //FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
        List<String> lists = new ArrayList<>();
        Workbook workbook = readFileName(file);
        Sheet firstSheet = workbook.getSheetAt(1);
        Iterator<Row> iterator = firstSheet.iterator();
         
        while (iterator.hasNext()) {
            Row nextRow = iterator.next();
            Iterator<Cell> cellIterator = nextRow.cellIterator();
             int y = 0;
            while (cellIterator.hasNext()) {
             
                Cell cell = cellIterator.next();
                 
                switch (cell.getCellType()) {
                    case Cell.CELL_TYPE_STRING:
                        if(y > 7 & !cell.getStringCellValue().equals("-")){
                            if(y == 8){
                                lists.add(y+":\t\t"+cell.getStringCellValue());
                            }else if(y>8){
                                String last= lists.get(lists.size()-1);
                                String new_string = last+":-"+cell.getStringCellValue();
                                
                                lists.set(lists.size()-1, new_string);
                            }
                            
                        }
                        System.out.print(cell.getStringCellValue());
                        
                        break;
                    case Cell.CELL_TYPE_BOOLEAN:
                        System.out.print(cell.getBooleanCellValue());
                        break;
                    case Cell.CELL_TYPE_NUMERIC:
                        System.out.print(cell.getNumericCellValue());
                        break;
                }
               // System.out.print(" - ");
                   y ++;
            }
            System.out.println();
        }
         
        workbook.close();
       // inputStream.close();
       String emaili ="",emailj="",namei="",namej="";
       String[] mergeri,mergerj;
       for(int x=0; x<lists.size(); x++){
           String[] splits1 = lists.get(x).split(":-");
           for(int xx=0; xx<splits1.length; xx++){
            mergeri = splits1[xx].split("/");
            namei = mergeri[0];
            emaili = mergeri[1];
           
            System.out.println(lists.get(x)+"\t length = "+splits1.length);
           
          }
       }
    
    }
     
    
    public static void test() {
        String url = "http:\\/\\/192.168.56.1\\/Mobicare_v\\/pages\\/android-connection\\/uploads\\/patients\\/37.jpg";
        String[] splits = url.split("/");
        System.out.println(splits[2].substring(0,splits[2].length()-1));
    }
    
    
   
}
