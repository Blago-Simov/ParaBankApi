package Utils;
import com.github.javafaker.Faker;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;

public class ReadAndWriteExelL {




   public String getCellValue(int currentCellValue,String currentSheet,String path) throws IOException {
       XSSFWorkbook workbook = new XSSFWorkbook(path);
       XSSFSheet sheet = workbook.getSheet(currentSheet);
       DataFormatter formatter = new DataFormatter();
       int cellCount = sheet.getRow(1).getLastCellNum() - sheet.getRow(1).getFirstCellNum();
       var cellValue = "";
       if(currentCellValue>cellCount-1){
           System.out.println("You accessed a non-existent table column!");
       }else{
           Cell cell = sheet.getRow(1).getCell(currentCellValue);
            cellValue = formatter.formatCellValue(cell);

       }
       workbook.close();
       System.out.println(cellValue);
     return  cellValue;

   }

   public void writeInCell(String path_) throws IOException {
       Faker faker = new Faker();
       String fakerUserName = faker.name().username();
       XSSFWorkbook workbook = new XSSFWorkbook();
       XSSFSheet sheet = workbook.createSheet("WriteIn");
       Row row = sheet.createRow((short)1);
       Cell cell = row.createCell(0);
       cell.setCellValue(fakerUserName);
       FileOutputStream fileOut = new FileOutputStream(path_);
       workbook.write(fileOut);
       fileOut.close();

   }


}
