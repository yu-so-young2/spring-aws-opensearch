# Spring-AWS-OpenSearch

### application.properties
- `host` : 오픈서치주소URL:443
- `username`, `password` : 마스터 사용자 생성
```
cloud.aws.opensearch.credentials.username=${username}
cloud.aws.opensearch.credentials.password=${password}
cloud.aws.opensearch.domain.host=${endpoint}:443
```


---
[참고](https://velog.io/@jhva/CQRS-%EB%8F%84%EC%9E%85%EA%B8%B0-ELK-%EA%B5%AC%EC%B6%95#log-stash-%EA%B5%AC%EC%B6%95)