package br.com.systemsgs.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AwsSQSConfiguration {

    @Value("${cloud.aws.region.static}")
    private String region;

    @Value("${cloud.aws.credentials.acces-key}")
    private String awsAccesKey;

    @Value("${cloud.aws.credentials.secret-key}")
    private String awsSecretKey;

    @Bean
    public QueueMessagingTemplate queueMessagingTemplate(){
        return new QueueMessagingTemplate(amazonSQSaSYNC());
    }

    @Primary
    @Bean
    public AmazonSQSAsync amazonSQSaSYNC() {
        return AmazonSQSAsyncClientBuilder.standard().withRegion(Regions.US_EAST_1)
        .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(awsAccesKey, awsSecretKey))).build();
    }

}
