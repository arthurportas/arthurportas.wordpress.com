package com.arthurportas.jasperreports.parameters;

import net.sf.jasperreports.engine.*;
import org.apache.commons.io.IOUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class ImageIT {

    private static final Logger logger = LoggerFactory.getLogger(ImageIT.class);

    @Before
    public void init() {
        logger.debug("Starting up...");
//		first, we open the XML template
        logger.info("Opening the JRXML template file.");
    }

    @After
    public void post() {
        while (true) {
            try {
                Thread.sleep(Long.MAX_VALUE);
            } catch (InterruptedException e) {
                logger.info("Exiting now.");
            }
        }
    }

    @Test
    public void byPath() throws FileNotFoundException, JRException {

        JasperReport report = getJasperReportForFile("/logo-by-path.jrxml");

        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("logo", ClassLoader.getSystemResource("logo.jpg").getPath());

        exportReportAndGenerateFile(report, parameters);
    }

    @Test
    public void byURL() throws FileNotFoundException, JRException {

        JasperReport report = getJasperReportForFile("/logo-by-url.jrxml");

        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("logo", ClassLoader.getSystemResource("logo.jpg"));

        exportReportAndGenerateFile(report, parameters);
    }

    @Test
    public void byInputStream() throws FileNotFoundException, JRException {

        JasperReport report = getJasperReportForFile("/logo-by-inputstream.jrxml");

        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("logo", ClassLoader.getSystemResourceAsStream("logo.jpg"));

        exportReportAndGenerateFile(report, parameters);
    }

    @Test
    public void byInputStreamFetchedFromEndpoint() throws IOException, JRException {

        JasperReport report = getJasperReportForFile("/logo-by-inputstream.jrxml");

        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("logo", getInputStreamFromResource());

        exportReportAndGenerateFile(report, parameters);
    }

    @Test
    public void byInputStreamFromByteArray() throws IOException, JRException {

        JasperReport report = getJasperReportForFile("/logo-by-inputstream.jrxml");

        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("logo", getInputStreamFromByteArray());

        exportReportAndGenerateFile(report, parameters);
    }

    private InputStream getInputStreamFromByteArray() throws IOException {

        String imageUrl = "https://placeholdit.imgix.net/~text?txtsize=33&txt=200%C3%9780&w=200&h=80";
        URL url = new URL(imageUrl);
        InputStream is = url.openStream();
        byte[] bytes = IOUtils.toByteArray(is);//save to byte[]
       return new ByteArrayInputStream(bytes);
    }

    private InputStream getInputStreamFromResource() throws IOException {

        String imageUrl = "https://placeholdit.imgix.net/~text?txtsize=33&txt=200%C3%9780&w=200&h=80";
        URL url = new URL(imageUrl);
        return url.openStream();
    }

    @Test
    public void byFile() throws FileNotFoundException, JRException, URISyntaxException {

        JasperReport report = getJasperReportForFile("/logo-by-file.jrxml");

        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("logo", new File(ClassLoader.getSystemResource("logo.jpg").toURI()));

        exportReportAndGenerateFile(report, parameters);
    }

    @Test
    public void byImage() throws IOException, JRException, URISyntaxException {

        JasperReport report = getJasperReportForFile("/logo-by-image.jrxml");

        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("logo", ImageIO.read(ClassLoader.getSystemResource("logo.jpg")));

        exportReportAndGenerateFile(report, parameters);
    }

    private JasperReport getJasperReportForFile(String fileName) throws JRException {

        InputStream template = ImageIT.class.getResourceAsStream(fileName);
        logger.info("Compiling report and filling it out.");
        JasperReport report = JasperCompileManager.compileReport(template);
        return report;
    }

    private void exportReportAndGenerateFile(JasperReport report, Map<String, Object> parameters) throws JRException, FileNotFoundException {

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

    }
}
