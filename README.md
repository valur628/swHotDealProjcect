# S/W Hot Deal Project
### _소프트웨어 핫딜 프로젝트_

박주철, 황승현, 손승우

- 해당 클라이언트는 안드로이드 스튜디오(Java)를 사용함
- 커밋 잘못해서 프로젝트 터질 수 있으니 별도의 백업본은 각자 지니고 있을 것
- 가급적이면 branche를 만들고 그곳에 올린 다음, 중복이나 문제 있는지를 확인하고 합병 여부를 선택할 것
- 여긴 클라이언트 쪽 부분을 담당하는 곳이고, 서버 쪽은 별도의 리포지토리가 없을 것
- 계획은 다음과 같음
 ![이미지 이름](https://i.imgur.com/XJedsLg.png)
- DB 관련 정보

|    | Hangul Name   | in DB Name     | Type          | Key |
|----|---------------|----------------|---------------|-----|
| 1  | 갱신순서      | DB_LoadNumber  | integer       | P.K |
| 2  | 소프트웨어 명 | DB_SWName      | Text string   |     |
| 3  | 개발사 명     | DB_DevName     | Text string   |     |
| 4  | 할인 기간     | DB_DisPeriod   | Date and time |     |
| 5  | 통화          | DB_Currency    | Text string   |     |
| 6  | 원가          | DB_Cost        | integer       |     |
| 7  | 할인가        | DB_DisPrice    | integer       |     |
| 8  | 할인율        | DB_DisRate     | integer       |     |
| 9  | 플랫폼 주소   | DB_PlatAddress | Text string   |     |
| 10 | 플랫폼 이름   | DB_PlatName    | Text string   |     |
| 11 | 대표 사진     | DB_RepPicture  | Text string   |     |
| 12 | 기타 사진     | DB_OthPicture  | Text string   |     |

