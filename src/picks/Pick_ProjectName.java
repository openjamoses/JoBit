/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package picks;

import java.io.File;
import java.io.FileInputStream;
import static java.nio.ByteBuffer.wrap;
import static java.nio.ByteBuffer.wrap;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author john
 */
public class Pick_ProjectName {
   public  String setProjectName(String file, int loop) throws Exception{
	CellReference pname=new CellReference("H2");
	FileInputStream fisi = new FileInputStream(new File(file));
        XSSFWorkbook workbook = new XSSFWorkbook(fisi);
        FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
        XSSFSheet sheets =workbook.getSheetAt(loop);
        Row row=sheets.getRow(pname.getRow());
        String projectName = null;
        if(row != null){
        Cell cell = row.getCell(pname.getCol());
        CellValue cellValue= evaluator.evaluate(cell);
        
        switch (cell.getCellType()) 
        {
           case Cell.CELL_TYPE_NUMERIC:
    	        projectName=String.valueOf(cell.getNumericCellValue()) ; 
        
    	    break;
          case Cell.CELL_TYPE_STRING:
    	       projectName = cell.getStringCellValue();
          break;
         }
        }
   
     /////System.out.println(projectName);  
    
  
  return projectName;
  } 
   
}
