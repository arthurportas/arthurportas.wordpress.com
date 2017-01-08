package com.arthurportas.jasperreports;

import net.sf.jasperreports.engine.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;

/*
* Sample hello world pdf generation
* */
public class JasperReportsApplication {

    private static final Logger logger = LoggerFactory.getLogger(JasperReportsApplication.class);

    /*
     * There are basically four steps to a finished product:
     * 		<X> Design a report with Jaspersoft iReport Designer to produce a JRXML file and add it to application classpath.
     * 		<X> Compile the report to a binary, serialized JasperReport (.jasper) file and save to disk.
     * 		<X> Fill the compiled report with data.
     * 		<X> Export it to pdf.
     */
    public static void main(String[] args) throws Exception {

        logger.debug("Starting up...");
//		first, we open the XML template
        logger.info("Opening the JRXML template file.");
        InputStream template = JasperReportsApplication.class
                .getResourceAsStream("/hello.jrxml");

//		now, fill out the report
        logger.info("Compiling report and filling it out.");
        JasperReport report = JasperCompileManager.compileReport(template);
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("hello", "hello world");

//		it's important to provide an instance of JREmptyDataSource otherwise by default the report will have no pages
        JasperPrint print = JasperFillManager.fillReport(report, parameters, new JREmptyDataSource());

//		finally, export it to PDF
        logger.info("Exporting the filled out report to a file.");
//      for test purposes we should create a temp tile with File.createTempFile(),
//      but in Mac Os this goes to /var/folder and it's not that accessible...
//      adjust the file path according to your file system
        File pdfOutputFile = new File("/Users/arthurportas/tmp/jasper.pdf");
        pdfOutputFile.deleteOnExit();
        JasperExportManager.exportReportToPdfStream(print, new FileOutputStream(pdfOutputFile));

//		now sleep indefinitely to allow for preview but to wipe the files on JVM exit
        logger.info("Done with export. Find the file at \"{}\" then end this process when done.",
                pdfOutputFile.getAbsolutePath());

        while (true) {
            try {
                Thread.sleep(Long.MAX_VALUE);
            } catch (InterruptedException e) {
                logger.info("Exiting now.");
            }
        }
    }
}
