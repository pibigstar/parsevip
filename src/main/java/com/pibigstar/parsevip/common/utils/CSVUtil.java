package com.pibigstar.parsevip.common.utils;

import com.univocity.parsers.common.IterableResult;
import com.univocity.parsers.common.ParsingContext;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import com.univocity.parsers.csv.CsvWriter;
import com.univocity.parsers.csv.CsvWriterSettings;

import java.io.InputStream;
import java.util.Arrays;

/**
 * @author pibigstar
 * @create 2018-11-29 11:40
 * @desc CSV解析工具
 **/
public class CSVUtil {

    public static void parseCSV(String filePath) {
        InputStream is = getReader(filePath);
        CsvParserSettings settings = new CsvParserSettings();
        settings.getFormat().setLineSeparator("\n");
        CsvParser parser = new CsvParser(settings);
        IterableResult<String[], ParsingContext> iterate = parser.iterate(is);
        for (String[] it:iterate) {
            System.out.println(Arrays.toString(it));
        }
    }

    public static void genderCSV(){
        CsvWriterSettings csvWriterSettings = new CsvWriterSettings();
        csvWriterSettings.setHeaders("1","2","3");
        CsvWriter cw = new CsvWriter(csvWriterSettings);
        cw.writeHeaders();
    }

    private static InputStream getReader(String path){
        return CSVUtil.class.getClassLoader().getResourceAsStream(path);
    }

}
