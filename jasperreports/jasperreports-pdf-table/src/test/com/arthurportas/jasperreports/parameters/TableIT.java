package com.arthurportas.jasperreports.parameters;

import com.arthurportas.jasperreports.order.OrderItem;
import com.google.common.collect.Lists;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
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
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TableIT {

    private static final Logger logger = LoggerFactory.getLogger(TableIT.class);

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
    public void withList() throws FileNotFoundException {

        try {
            JasperReport report = getJasperReportForFile("/table.jrxml");

            Map<String, Object> parameters = new HashMap<>();

            parameters.put("orderDetails", getOrderItemsDataSource());

            exportReportAndGenerateFile(report, parameters);
        } catch (Throwable t) {
            logger.error("error: " + t.getMessage());
            System.exit(2);
        }
    }

    private JRBeanCollectionDataSource getOrderItemsDataSource() {

        return new JRBeanCollectionDataSource(getOrderItems(), false);
    }

    private List<OrderItem> getOrderItems() {

        List<OrderItem> orderItems = Lists.newArrayList();

        OrderItem orderItem = new OrderItem();
        orderItem.setRowNumber("1");
        orderItem.setProductName("iPhone 7");
        orderItem.setSellerSku("sellerSKU");
        orderItem.setShopSku("shopSKU");
        orderItem.setPrice("600");
        orderItem.setPaidPrice("640");

        orderItems.add(orderItem);

        return orderItems;
    }

    private JasperReport getJasperReportForFile(String fileName) throws JRException {

        InputStream template = TableIT.class.getResourceAsStream(fileName);
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
