package config;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import org.springframework.cloud.aws.context.config.annotation.EnableContextCredentials;
import org.springframework.cloud.aws.context.config.annotation.EnableContextResourceLoader;
import org.springframework.cloud.aws.context.support.io.ResourceLoaderBeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@EnableContextResourceLoader
@EnableContextCredentials
public class AppConfiguration {


    //TODO: these values should be read from config
    private static final String ACCESS_KEY = "XXX";

    private static final String SECRET_KEY = "YYY";


    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public AmazonS3Client amazonS3Client() {
        return new AmazonS3Client(new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY));
    }


    @Bean
    public ResourceLoaderBeanPostProcessor resourceLoaderBeanPostProcessor() {
        return new ResourceLoaderBeanPostProcessor(amazonS3Client());
    }

}
