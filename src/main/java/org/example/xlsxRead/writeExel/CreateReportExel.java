package org.example.xlsxRead.writeExel;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.Comparator;
import java.util.List;

public class CreateReportExel {

    public CreateReportExel( List<String[]> list, String downloadsPath) {

        list.sort(new Comparator<String[]>() {
            @Override
            public int compare(String[] o1, String[] o2) {
                return o1[1].compareTo(o2[1]);
            }
        });

        //create no find article
        XSSFWorkbook workbook = new CreateExel().createExel(list);

       new WriteExelXlsx(workbook, downloadsPath);

    }

}
