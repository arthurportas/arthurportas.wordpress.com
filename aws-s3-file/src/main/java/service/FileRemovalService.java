package service;

import com.amazonaws.services.s3.AmazonS3Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public class FileRemovalService {

	private static final String S3_PREFIX = "s3://";

    @Autowired
    private AmazonS3Client amazonS3Client;

    @Override
    public void removeFile(String fileName, String bucketName) throws Exception {

        this.amazonS3Client.deleteObject(bucketName, fileName);
    }
}
