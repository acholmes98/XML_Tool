package tools.xml_tool;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.Iterator;


public class PointCreation {

    private FileReader Xml_In_FR;
    private BufferedReader bufferedReader;
    private FileWriter fileWriter;
    private BufferedWriter bufferedWriter;


    private void CreateDIO2_Points(String[] params, boolean isAppended) {

        String XML_Path = getClass().getResource("/tools/xml_tool/D2IO_Template.xml").toString().replace("file:/","");
        System.out.println(XML_Path);
        SubXml_Temp( XML_Path,  params, isAppended);

    }

    public void ReadDIO2_Excel(String XlsPath) {
        String[] params = new String[15];
        try {
            File file = new File(XlsPath);   //creating a new file instance
            FileInputStream fis = new FileInputStream(file);   //obtaining bytes from the file
            //creating Workbook instance that refers to .xlsx file
            XSSFWorkbook wb = new XSSFWorkbook(fis);
            XSSFSheet sheet = wb.getSheetAt(0);     //creating a Sheet object to retrieve object
            Iterator<Row> itr = sheet.iterator();    //iterating over excel file
            boolean isAppended = false;
            int k = 0;
            int numHeaderRows = 1;
            while (itr.hasNext()) {
                boolean isBlank = false;

                Row row = itr.next();
                Iterator<Cell> cellIterator = row.cellIterator();   //iterating over each column
                int i = 0;
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    switch (cell.getCellType()) {
                        case STRING:    //field that represents string cell type
                            //System.out.print(cell.getStringCellValue() + "\t\t\t");
                            params[i] = cell.getStringCellValue();
                            break;
                        case NUMERIC:    //field that represents number cell type
                            cell.setCellType(CellType.STRING);
                            //System.out.print(cell.getStringCellValue() + " num" + "\t\t\t");
                            params[i] = cell.getStringCellValue();
                            break;
                        case BLANK:
                            System.out.println("blank");
                            isBlank = true;

                        default:
                    }
                    i++;
                }

                if (k > numHeaderRows) {
                    isAppended = true;
                }
                if (k >= numHeaderRows && !isBlank) {
                    CreateDIO2_Points(params,isAppended);
                    System.out.println("hit " + k);
                }

                test_itr(params);

                k++;
                System.out.println(k);
            }
            wb.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void test_itr(String[] params) {
        System.out.println("");
        System.out.print(params[0]);
        for (int j = 1; j < 12; j++) {
            System.out.print("\t" + params[j]);
        }
    }

    private void SubXml_Temp(String XmlInPath, String[] params, boolean isAppended) { // Substitutes values to XML D2IO template

        try {
            Xml_In_FR = new FileReader(XmlInPath);
            bufferedReader = new BufferedReader(Xml_In_FR);
            String test = new File(XmlInPath).getParent() + "\\DIO2_Output.xml";
            fileWriter = new FileWriter(test,isAppended);
            bufferedWriter = new BufferedWriter(fileWriter);
            String line = null;
            System.out.println(test + "\n");

                while ((line = bufferedReader.readLine()) != null) {
                    //System.out.println("inside loop");
                    //System.out.println(line);
                    line = line.replace("XXPointNameXX", params[0]);
                    line = line.replace("XXDescrXX", params[1]);
                    line = line.replace("XXDigDevXX", params[2]);
                    line = line.replace("XXControllerXX", params[3]);
                    line = line.replace("XXInpWord1XX", params[4]);
                    line = line.replace("XXInpBit1XX", params[5]);
                    line = line.replace("XXInpWord2XX", params[6]);
                    line = line.replace("XXInpBit2XX", params[7]);
                    line = line.replace("XXOutWord1XX", params[8]);
                    line = line.replace("XXOutBit1XX", params[9]);
                    line = line.replace("XXOutWord2XX", params[10]);
                    line = line.replace("XXOutBit2XX", params[11]);
                    bufferedWriter.write(line);
                    bufferedWriter.newLine();

                }
                bufferedWriter.newLine();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                Xml_In_FR.close();
                bufferedReader.close();
                bufferedWriter.close();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }


}