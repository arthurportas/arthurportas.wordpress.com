package controller;

import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class FileController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractFileController.class);

    @Autowired
    private FileUploadService fileUploadService;

    @Autowired
    private FileDownloadService fileDownloadService;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public void uploadFile(@RequestParam("file") MultipartFile file, String bucketName) {
        S3Object s3Object = null;
        if (!file.isEmpty()) {
            LOGGER.debug("File name:" + file.getOriginalFilename());
            try {
                s3Object = fileUploadService.writeFile(file.getInputStream(), bucketName);
                LOGGER.debug("File uploaded with name:" + s3Object.getKey());
            } catch (IOException ioe) {
                LOGGER.debug(ioe.getMessage());
            }
        }
    }


    @RequestMapping(value = "/download", method = RequestMethod.GET)
    @ResponseBody
    public void downloadFile(@RequestParam("name") String awsFileName, HttpServletResponse response, String bucketName) {


        LOGGER.debug("Downloading file with name:" + awsFileName);

        try {
            addDownloadResponseHeaders(response, awsFileName);
            IOUtils.copy(fileDownloadService.readFile(awsFileName, bucketName), response.getOutputStream());

        } catch (IOException ioe) {
            LOGGER.debug(ioe.getMessage());
        }
    }

    private void addDownloadResponseHeaders(HttpServletResponse response, String clientKnownFileName) {
        response.setHeader("Content-type", "text/csv");//this use case is only for csv files
        response.setHeader("Content-Disposition", "attachment; filename=\"" + clientKnownFileName + "\"");
        response.setHeader("Content-Transfer-Encoding", "binary");
    }
}
