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


    public void ReadExcelTemps(String XlsPath, String XmlOutPath, int template_num) { //reads excel templates and creates XML files
        if (template_num != 0 && template_num != -1) {
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
                int numHeaderRows = 2;
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

                            default:
                        }
                        i++;
                    }

                    if (k > numHeaderRows) {
                        isAppended = true;
                    }
                    if (k >= numHeaderRows) {
                        Create_Points(params, XmlOutPath, isAppended, template_num);
                        //System.out.println("hit " + k);
                    }

                    for (int j = 0; j < params.length; j++) {
                        params[j] = null;
                    }

                    k++;
                }

                wb.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (template_num == 0) {
            File src = new File(getClass().getResource("/tools/xml_tool/Excel_Template_Empty.xlsx").toString().replace("file:/", ""));
            File dest = new File(XmlOutPath + "/Excel_Template_Empty.xlsx");
            InputStream is = null;
            OutputStream os = null;
            try {
                is = new FileInputStream(src);
                os = new FileOutputStream(dest); // buffer size 1K
                byte[] buf = new byte[1024];
                int bytesRead;
                while ((bytesRead = is.read(buf)) > 0) {
                    os.write(buf, 0, bytesRead);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    is.close();
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        } else {
            //Generate button pressed with no selection, do nothing
        }
    }


    private void Create_Points(String[] params, String XmlOutPath, boolean isAppended, int template_num) {
        String template_res;
        String XML_Path;
        switch (template_num) {
            case 1:

            case 2:
                template_res = "DIO_Template.xml";
                XML_Path = getClass().getResource("/tools/xml_tool/"+template_res).toString().replace("file:/","");
                SubXml_Temp( XML_Path, XmlOutPath, params, isAppended,template_num,template_res);
                if(template_num ==2)
                    break;
            case 3:
                template_res = "DIO2_Template.xml";
                XML_Path = getClass().getResource("/tools/xml_tool/"+template_res).toString().replace("file:/","");
                SubXml_Temp( XML_Path, XmlOutPath, params, isAppended,template_num,template_res);
                if(template_num ==3)
                    break;
            case 4:
                template_res = "AIO_Int_Template.xml";
                XML_Path = getClass().getResource("/tools/xml_tool/"+template_res).toString().replace("file:/","");
                SubXml_Temp( XML_Path, XmlOutPath, params, isAppended,template_num,template_res);
                if(template_num ==4)
                    break;
            case 5:
                template_res = "AIO_Float_Template.xml";
                XML_Path = getClass().getResource("/tools/xml_tool/"+template_res).toString().replace("file:/","");
                SubXml_Temp( XML_Path, XmlOutPath, params, isAppended,template_num,template_res);
                if(template_num ==5)
                    break;
            default:
                break;

        }




    }


    private void SubXml_Temp(String XmlInPath, String XmlOutPath, String[] params, boolean isAppended,int template_num,String template_res) { // Substitutes values to XML D2IO template

        try {
            Xml_In_FR = new FileReader(XmlInPath);
            bufferedReader = new BufferedReader(Xml_In_FR);
            String outputFilePath = XmlOutPath + "\\" + template_res;
            fileWriter = new FileWriter(outputFilePath,isAppended);
            bufferedWriter = new BufferedWriter(fileWriter);
            String line = null;
            //System.out.println(test + "\n");
            String[] params_Temp = chooseParams(template_num);

                while ((line = bufferedReader.readLine()) != null) {
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

    private String[] chooseParams(int template_num) {
        String[] Params_temp = null;

        return Params_temp;
    }


}