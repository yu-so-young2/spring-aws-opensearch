package ssoyoung.opensearchtest.elastic.domain;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

@Getter
@Setter
@Document(indexName = "articles")
public class ElasticTechArticle {
    @Id
    private String id;

    @Field(type = FieldType.Text)
    private String title;

    @Builder
    private ElasticTechArticle(String id, String title) {
        this.id = id;
        this.title = title;
    }
}
