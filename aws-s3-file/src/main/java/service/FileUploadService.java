package service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.WritableResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@Service
public class FileUploadService {

	private static final String S3_PREFIX = "s3://";

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private AmazonS3Client amazonS3Client;


    public S3Object writeFile(File file, String bucketName) throws IOException {
        String uniqueFileName = FileUtils.generateRandomFileId();
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, uniqueFileName, file);
        PutObjectResult putObjectResult =  this.amazonS3Client.putObject(putObjectRequest);
        return this.amazonS3Client.getObject(bucketName, uniqueFileName);
    }

    public S3Object writeFile(InputStream is, String bucketName) throws IOException {

        String uniqueFileName = FileUtils.generateRandomFileId();

        Resource resource = this.resourceLoader.getResource(S3_PREFIX + bucketName + "/" + uniqueFileName);

        WritableResource writableResource = (WritableResource) resource;
        try (OutputStream outputStream = writableResource.getOutputStream()) {
            outputStream.write(IOUtils.toByteArray(is));
        }
        return this.amazonS3Client.getObject(bucketName, uniqueFileName);
    }
}
