# Vacation Program 

## 개발환경
- VueJs + npm
- Java8 +  SpringBoot
- JPA +  QueryDsl
- MySql with Docker
- Gradle, Lombok
- TuiGrid, Full-Calendar

## 빌드 및 실행
- FrontEnd 
  - 실행 dir : frontend
  - npm install
  - npm run dev
- BackEnd
  - 실행 dir : /app/app
  - gradlew build && java -jar app/libs/finance-0.0.1-SNAPSHOT.jar
- Database
  - 실행 dir : /database
  - docker-compose up -d


## 기능 요구사항
  - [x] 연차신청
    - 기간(FROM, TO)과 보유연차와 전일/반차 입력 받아 신청
    - 입력한 기간 보다 잔여 연차 부족 시, "잔여 연차 부족" 이라는 alert 표시
  - [x] 연차취소
  - [x] 연차통계 표시
    - 보유연차(일)/사용연차(일)/잔여연차(일)로 구성
    - 신청/취소시 자동 갱신
  - [x] 신청/취소된 연차목록 표시
    - 기간, 일수, 신청일, 상태 표시. 취소
    - 신청/취소시 자동 갱신
  - [x] 달력 UI 상에서 연차휴가 조회할 수 있는 화면
    - 신청된 휴가에 대해 달력 UI 상에 이름과 전일/반차 구분 표시
  - [x] Backend 와 Frontend 어플리케이션을 하나의 git repository 에서 관리
  - [x] README.md 파일에 간략한 실행방법 기재 필요

## 기술 요구사항
  - [x] Backend 와 Frontend 어플리케이션을 하나의 git repository 에서 관리
  - [x] README.md 파일에 간략한 실행방법 기재 필요
  - [x] VueJs + (css프레임워크) + (npm 패키지 구성 및 빌드)
  - [x] Java + SpringBoot + JPA or Mybatis + Maven or Gradle
  - [x] MySql with Docker + 테이블 정의서
