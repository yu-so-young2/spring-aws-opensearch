//package ssoyoung.opensearchtest.elastic.config;
//
//import org.elasticsearch.client.RestHighLevelClient;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.elasticsearch.client.ClientConfiguration;
//import org.springframework.data.elasticsearch.client.RestClients;
//import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
//import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
//import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
//import org.springframework.data.elasticsearch.core.convert.ElasticsearchConverter;
//import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
//import org.springframework.http.HttpHeaders;
//
//@Configuration
//@EnableElasticsearchRepositories
//public class ElasticsearchConfig extends AbstractElasticsearchConfiguration {
//
//    @Value("${cloud.aws.opensearch.domain.host}")
//    private String host;
//
//    @Value("${cloud.aws.opensearch.credentials.username}")
//    private String username;
//
//    @Value("${cloud.aws.opensearch.credentials.password}")
//    private String password;
//
//    @Override
//    public RestHighLevelClient elasticsearchClient() {
//        HttpHeaders compatibilityHeaders = new HttpHeaders();
//        compatibilityHeaders.add("Accept", "application/vnd.elasticsearch+json;compatible-with=7");
//        compatibilityHeaders.add("Content-Type", "application/vnd.elasticsearch+json;"
//                + "compatible-with=7");
//
//
//        ClientConfiguration configuration = ClientConfiguration.builder()
//                .connectedTo(host)
//                .usingSsl()
//                .withBasicAuth(username, password)
////                .withDefaultHeaders(compatibilityHeaders)    // this variant for imperative code
//                .withHeaders(() -> compatibilityHeaders)     // this variant for reactive code
//                .build();
//        return RestClients.create(configuration).rest();
//    }
//
//    @Override
//    public ElasticsearchOperations elasticsearchOperations(ElasticsearchConverter elasticsearchConverter,
//                                                           RestHighLevelClient elasticsearchClient) {
//        return new ElasticsearchRestTemplate(elasticsearchClient());
//    }
//
//}