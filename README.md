# Planet Square API

## 빌드 & 실행 방법

프로젝트를 빌드하고 실행하기 위해 다음 명령어를 사용하세요.

```bash
./gradlew clean build
./gradlew bootRun
```

## Swagger UI

Swagger UI를 통해 API를 확인할 수 있습니다.

[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

---

## REST API 명세 요약

### 기본 URL

```
http://localhost:8080/api/v1
```

### API 엔드포인트

| 경로                 | HTTP 메서드 | 설명                             | 요청 파라미터                                                                                                 | 성공 응답 | 설명            |
| ------------------ | -------- | ------------------------------ | ------------------------------------------------------------------------------------------------------- | ----- | ------------- |
| `/health`          | GET      | 서비스 상태 확인                      | 없음                                                                                                      | 200   | 서비스 정상 여부 반환  |
| `/holiday/search`  | GET      | 연도·국가별 공휴일 조회                  | `year`(int, optional), `countryCode`(string, optional), `offset`(int, optional), `limit`(int, optional) | 200   | 조회된 공휴일 목록 반환 |
| `/holiday/refresh` | PUT      | 특정 연도·국가 공휴일 데이터 재동기화 (Upsert) | `year`(int, required), `countryCode`(string, required)                                                  | 200   | 재동기화 성공       |
| `/holiday`         | DELETE   | 특정 연도·국가 공휴일 전체 삭제             | `year`(int, required), `countryCode`(string, required)                                                  | 200   | 삭제 성공         |

### 비고

- /country/csv 경로는 추후에 google calendar를 이용하기 위해 넣어 두었습니다. 지금은 csv를 이용해 국가 정보를 단순히 주입하는건데, init 후 넣는거라서 오류가 날것으로 예상됩니다.
