package framework.utilities;
import framework.constant.FrameworkConstant;
import framework.exception.ConfigFileReaderException;
import framework.exception.ReadExcelException;
import org.apache.hc.client5.http.impl.classic.RequestAbortedException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.*;

public class ReadExcel {

    public static Properties properties;
    public static Map<String,String> test_Data;

    static  {
        System.out.println("Reading config file");
        ConfigFileReader configFileReader = new ConfigFileReader();
        try {
            properties = configFileReader.configFile();
            System.out.println(properties.getProperty(FrameworkConstant.APP_ENV));
        } catch (ConfigFileReaderException e) {
            e.printStackTrace();
        }
        System.out.println("Reading config file completed");
    }


    public static Map<String,String> readExcelDataBasedOnEnvironment() throws IOException, InvalidFormatException, ReadExcelException {
        XSSFWorkbook workBook =null;
        test_Data = new HashMap<>();
        try {
            System.out.println("Reading file");
            String env = properties.getProperty(FrameworkConstant.APP_ENV);
            String excelSheetPath = properties.getProperty(FrameworkConstant.TEST_DATA);
            if (env != null) {
                File file = new File(excelSheetPath);
                workBook = new XSSFWorkbook(file);
                XSSFSheet sheet = workBook.getSheetAt(0);

                int noOfRows = sheet.getPhysicalNumberOfRows();
                int noOfColumn = sheet.getRow(0).getPhysicalNumberOfCells();

              //  System.out.println("Index is " + noOfRows + "---------column -------" + noOfColumn);

                int valueIndex = 0;
                int keyIndex = 0;

                //get index no based on environment
                for (int i = 0; i <= 0; i++) {
                    for (int j = 0; j < noOfColumn; j++) {

                        String valueString = sheet.getRow(i).getCell(j).toString().toUpperCase();
                        System.out.println("value index is " + valueString);
                        if (valueString.contains(env.toUpperCase())) {
                            valueIndex = j;
                            System.out.println("value index is " + valueIndex);
                        }
                    }
                    if (valueIndex == 0) {
                        throw new ReadExcelException("Invalid environment");
                    }
                }

                //read key and value
                for (int i = 0; i < noOfRows; i++) {
                    if (sheet.getRow(i) != null) {
                        String keyString = sheet.getRow(i).getCell(keyIndex).toString();
                        String valueString = sheet.getRow(i).getCell(valueIndex).toString();
                        System.out.println(keyString + "_____ValueString----" + valueString);
                        test_Data.put(keyString, valueString);
                    }
                }

            } else {
                throw new ReadExcelException("Environment cant be null");
            }
            System.out.println("Reading file completed");
            return test_Data;
        }finally{
            workBook.close();
        }
    }


    public void readTestData(){
//            Iterator<Row> rows = sheet.rowIterator();
//            System.out.println("rows --------" + rows.toString());
//
//    int physicalNoOfColumn =row.getPhysicalNumberOfCells();
//    int physicalNoOfRows = sheet.getPhysicalNumberOfRows();
//
//            for(int i =0;i<physicalNoOfColumn;i++){
//
//            }
//                while (cellIterator.hasNext()) {
//        Cell cellValue = cellIterator.next();
//
//        if(cellValue.getStringCellValue().toUpperCase().contains(FrameworkConstant.APP_ENV_DEV.toUpperCase())){
//            int envValues =cellValue.getColumnIndex();
//            System.out.println("celll value is " + envValues);
//
//            Cell valuesOfTestData = row.getCell(envValues);
//        }
//
//    }
//
//
//            while (rows.hasNext()) {
//        Row row = rows.next();
//        if (row != null) {
//
//            //  row.getCell(0);
//
//            Cell keyOfTestData= row.getCell(0);
//
//            while (cellIterator.hasNext()) {
//                Cell cellValue = cellIterator.next();
//
//                if(cellValue.getStringCellValue().toUpperCase().contains(FrameworkConstant.APP_ENV_DEV.toUpperCase())){
//                    int envValues =cellValue.getColumnIndex();
//                    System.out.println("celll value is " + envValues);
//
//                    Cell valuesOfTestData = row.getCell(envValues);
//                }
//
//                if (cellValue!= null) {
//                    if(cellValue.getCellType() == CellType.STRING){
//                        cellValue.getStringCellValue();
//                    } else if (cellValue.getCellType() == CellType.NUMERIC){
//                        cellValue.getNumericCellValue();
//                    } else if(cellValue.getCellType() == CellType.NUMERIC){
//                        cellValue.getNumericCellValue();
//                    } else if(cellValue.getCellType()==CellType.BOOLEAN){
//                        cellValue.getBooleanCellValue();
//                    } else if(cellValue.getCellType()==CellType.BLANK){
//                        cellValue.getStringCellValue();
//                    }
//
//                    System.out.println("cell --------" + cellValue.getStringCellValue());
//                }
//
//            }
//        }
    }

        public  static  void main(String args[]){
        try {
            ReadExcel.readExcelDataBasedOnEnvironment();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (ReadExcelException e) {
            e.printStackTrace();
        }

    }

}
