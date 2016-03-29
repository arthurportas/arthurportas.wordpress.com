package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

@Service
public class FileDownloadService {

	private static final String S3_PREFIX = "s3://";

    @Autowired
    private ResourceLoader resourceLoader;

    @Override
    public InputStream readFile(String fileName, String bucketName) throws IOException {
        Resource resource = this.resourceLoader.getResource(S3_PREFIX + bucketName + "/" + fileName);
        return resource.getInputStream();
    }

}
