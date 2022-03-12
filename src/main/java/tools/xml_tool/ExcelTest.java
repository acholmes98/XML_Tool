package tools.xml_tool;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.net.URL;
import java.util.Iterator;

public class ExcelTest {

    public void test() {
        File file = new File(getClass().getResource("/tools/xml_tool/Test.xlsx").getFile());
        try {
            FileInputStream fis = new FileInputStream(file);
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workbook.getSheetAt(0);

            int temp_num_char;
            int num_space = 10;
            Iterator<Row> rowItr = sheet.iterator();
            while (rowItr.hasNext()) {
                Row row = rowItr.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();

                    switch (cell.getCellType()) {
                        case STRING -> {
                            System.out.print(cell.getStringCellValue());
                            temp_num_char = cell.getStringCellValue().length();
                            for (int i = 0; i < (num_space - temp_num_char); i++) {
                                System.out.print(" ");
                            }
                        }
                        case NUMERIC -> {
                            temp_num_char = ("" + cell.getNumericCellValue()).length();
                            System.out.print(cell.getNumericCellValue());
                            for (int i = 0; i < (num_space - temp_num_char); i++) {
                                System.out.print(" ");

                            }
                        }

                        default -> {
                        }
                    }
                }
                System.out.println("");

            }

            workbook.close();


            Workbook workbook1 = new XSSFWorkbook();
            //File testFile = new File(file.getParent().toString() + "\\Test.xlsx");
            String fileLoc = file.getParent() + "\\Test1.xlsx";
            OutputStream fileout = new FileOutputStream(fileLoc);
            //System.out.println(testFile.getAbsolutePath());

            Sheet sheet1 = workbook1.createSheet("Test");
            workbook1.write(fileout);
            fileout.close();
            workbook1.close();


        } catch (FileNotFoundException e) {
            System.out.println("failed");
            e.printStackTrace();

        } catch (IOException e) {
            System.out.println("failed");
            e.printStackTrace();
        }


    }



}
