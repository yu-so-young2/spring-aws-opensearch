package ssoyoung.opensearchtest.elastic.config.ver2;

import com.amazonaws.auth.AWS4Signer;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.opensearch.client.RestClient;
import org.opensearch.client.RestClientBuilder;
import org.opensearch.client.RestHighLevelClient;
//import org.opensearch.client.sniff.SniffOnFailureListener;
//import org.opensearch.client.sniff.Sniffer;
import org.opensearch.data.client.orhlc.AbstractOpenSearchConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import java.time.Duration;
import java.util.List;
import ssoyoung.opensearchtest.elastic.config.ver2.AWSRequestSigningApacheInterceptor;

/**
 * @author Seok Won Choi
 */
@Configuration
@EnableElasticsearchRepositories(basePackages = "ssoyoung.opensearchtest.elastic.domain")
@Slf4j
public class OpenSearchRestClientConfiguration extends AbstractOpenSearchConfiguration {

    @Value("${aws.os.endpoint}")
    private String endpoint;

    @Value("${aws.os.region}")
    private String region;

    @Value("${cloud.aws.credentials.access-key}")
    private String accessKey;

    @Value("${cloud.aws.credentials.secret-key}")
    private String secretKey;

    @Value("${aws.user.name}")
    private String username;

    @Value("${aws.user.password}")
    private String password;

    @Autowired
    private Environment environment;

    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    @ConditionalOnProperty(name = "spring.profiles.active", havingValue = "awsBasic")
    public CredentialsProvider credentialsProvider() {
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(username, password));
        return credentialsProvider;
    }

    @Bean
    @ConditionalOnProperty(name = "spring.profiles.active", havingValue = "awsIAM")
    public AWSCredentialsProvider awsCredentialsProvider() {
        return new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey));
    }

    @Override
    @Bean
    public RestHighLevelClient opensearchClient() {
        RestClientBuilder restClientBuilder = RestClient.builder(new HttpHost(endpoint, 443, "https"));

        List<String> activeProfiles = List.of(environment.getActiveProfiles());
        if (activeProfiles.contains("awsIAM")) {
            System.out.println("IAM start");
            AWS4Signer signer = new AWS4Signer();
            signer.setServiceName("es");
            signer.setRegionName(region);
            AWSCredentialsProvider awsCredentialsProvider = applicationContext.getBean(AWSCredentialsProvider.class);
            HttpRequestInterceptor interceptor = new AWSRequestSigningApacheInterceptor("es", signer, awsCredentialsProvider);
            restClientBuilder.setHttpClientConfigCallback(httpClientBuilder -> httpClientBuilder.addInterceptorLast(interceptor));
        } else if (activeProfiles.contains("awsBasic")) {
            System.out.println("basic start");
            CredentialsProvider credentialsProvider = applicationContext.getBean(CredentialsProvider.class);
            restClientBuilder.setHttpClientConfigCallback(httpClientBuilder -> httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider));
        } else {
            throw new IllegalArgumentException("Unknown authentication method: " + String.join(", ", environment.getActiveProfiles()));
        }

        restClientBuilder
                .setRequestConfigCallback(requestConfigBuilder -> requestConfigBuilder
                        .setConnectTimeout((int) Duration.ofSeconds(600).toMillis())
                        .setSocketTimeout((int) Duration.ofSeconds(300).toMillis()));

        RestHighLevelClient client = new RestHighLevelClient(restClientBuilder);

        return client;
    }
}