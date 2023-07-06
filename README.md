# Spring-AWS-OpenSearch

### application.properties
- `host` : 오픈서치주소URL:443
- `username`, `password` : 마스터 사용자 생성
```
cloud.aws.opensearch.credentials.username=${username}
cloud.aws.opensearch.credentials.password=${password}
cloud.aws.opensearch.domain.host=${endpoint}:443
```
