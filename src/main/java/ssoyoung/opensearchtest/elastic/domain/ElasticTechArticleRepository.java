package ssoyoung.opensearchtest.elastic.domain;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.repository.CrudRepository;

public interface ElasticTechArticleRepository extends ElasticsearchRepository<ElasticTechArticle, String>,
        CrudRepository<ElasticTechArticle, String> {

}