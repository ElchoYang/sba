package com.elcho.stockexchange.Utils;

import com.elcho.stockexchange.model.StockPrice;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Time;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class ExcelUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelUtils.class);
    public static List<StockPrice> excelToShopIdList(InputStream inputStream) {
        List<StockPrice> list = new ArrayList<>();
        Workbook workbook = null;
        try {
            workbook = WorkbookFactory.create(inputStream);
            inputStream.close();
            //工作表对象
            Sheet sheet = workbook.getSheetAt(0);
            //总行数
            int rowLength = sheet.getLastRowNum() -1;
            LOGGER.info("总行数有多少行"+rowLength);
            //工作表的列
            Row row = sheet.getRow(0);

            //总列数
            int colLength = row.getLastCellNum();
            LOGGER.info("总列数有多少列"+colLength);
            //得到指定的单元格
            Cell cell = row.getCell(0);
            for (int i = 1; i < rowLength+1; i++) {
                row = sheet.getRow(i);

                StockPrice _stockPrice = new StockPrice();
                int a = 0;
                _stockPrice.setCompanyCode(row.getCell(a++).getStringCellValue().trim());
                _stockPrice.setStockExchange(row.getCell(a++).getStringCellValue().trim());
                _stockPrice.setCurrentPrice((new BigDecimal(row.getCell(a++).getNumericCellValue())));

                SimpleDateFormat dateFormate = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

                _stockPrice.setDate(row.getCell(a++).getDateCellValue());
                _stockPrice.setTime(new java.sql.Time(timeFormat.parse(row.getCell(a++).getStringCellValue().trim()).getTime()));

                list.add(_stockPrice);

            }
        } catch (Exception e) {
            LOGGER.error("parse excel file error :", e);
        }
        return list ;
    }

    public static java.sql.Time strToTime(String strDate) {
        String str = strDate;
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        java.util.Date d = null;
        try {
            d = format.parse(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        java.sql.Time time = new java.sql.Time(d.getTime());
        return time;
    }


}
