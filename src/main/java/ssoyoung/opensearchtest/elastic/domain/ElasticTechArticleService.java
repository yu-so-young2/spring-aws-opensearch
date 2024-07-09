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
}