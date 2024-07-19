package ssoyoung.opensearchtest.elastic;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ssoyoung.opensearchtest.elastic.domain.ElasticTechArticle;
import ssoyoung.opensearchtest.elastic.domain.ElasticTechArticleService;

@RestController
@RequiredArgsConstructor
public class Controller {

    private final ElasticTechArticleService elasticTechArticleService;

    @GetMapping("/")
    public ElasticTechArticle hello() {
        return elasticTechArticleService.insert();
    }

    @GetMapping("/test")
    public void hello2() {
        elasticTechArticleService.findAll();
    }
}
