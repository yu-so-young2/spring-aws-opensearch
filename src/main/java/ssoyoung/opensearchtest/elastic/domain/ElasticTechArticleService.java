package ssoyoung.opensearchtest.elastic.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ElasticTechArticleService {

    private final ElasticTechArticleRepository elasticTechArticleRepository;

    public ElasticTechArticle findById(String id) {
        return elasticTechArticleRepository.findById(id).orElseThrow();
    }

    public ElasticTechArticle insert() {
        ElasticTechArticle elasticTechArticle = ElasticTechArticle.builder()
                .title("테스트2")
                .build();

        return elasticTechArticleRepository.save(elasticTechArticle);
    }

    public void findAll() {
        Iterable<ElasticTechArticle> all = elasticTechArticleRepository.findAll();

        for(ElasticTechArticle article : all) {
            System.out.println(article.getTitle());
        }
    }
}