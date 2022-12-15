# Blog
자바, 스프링 공부하면서 구현할 블로그
- 참고 강의 : 인프런 호돌맨의 요절복통 개발쇼 (SpringBoot, Vue.JS, AWS)

## 블로그 기본 API
- [x] 프로젝트 생성
- [x] 컨트롤러 생성
    - [x] request param 체크
    - [x] receive to Request Body
- [x] POST 데이터 콘텐츠 타입
- [x] 데이터 검증
  - [x] @NotBlank, @Valid 활용 BingdingResult
  - [x] @NotBlank Message 변환
  - [x] @ControllerAdvice 활용한 ErrorController 구현
   - [x] Exception 클래스 분리
- [x] 게시글 저장 구현(POST)
  - [x] Repository, Service, Domain(Entity) 구현
  - [x] PostCreate 클래스 생성자 @Builder 추가
  - [x] ObjectMapper 사용해 객체를 json으로 변환
  - [x] Controller단에서 조회글 저장 테스트
  - [x] Service단에서 조회글 저장 테스트
- [x] 응답 클래스 분리
  - [x] 제목의 길이 제한이 10일 때, 응답 클래스 생성
- [x] 게시글 조회 구현(GET)
  - [x] Exception Message Enum Class 생성
- [x] 게시글 여러개 조회 구현(GET+LIST)
  - [x] getList 메서드 서비스, 컨트롤러에 구현
  - [x] h2 db 연결을 위한 application.yml 세팅
- [ ] 페이징 처리
  - [x] Service Pageable 처리
  - [x] Controller Pageable 처리
  - [x] QueryDsl import
  - [x] QueryDsl Config App 생성
  - [x] Custom Repository Interface 생성
  - [x] Custom Repository Impl 생성
  - [x] Query용 Reqeust DTO 생성
- [x] 게시글 수정
  - [x] PostEdit Request DTO 생성
  - [x] PostEditor Post Edit Class 생성
  - [x] Post 내 PostEditor 반환 메소드 추가
  - [x] PostService Edit 메소드 추가
  - [x] 수정 내용 외엔 내용 유지하게 짜기
- [x] 게시글 삭제
  - [x] PostService Delete 메소드 추가
- [ ] 예외처리
  - [x] IllegalArgumentException -> Exception 상속받는 PostNotFound 클래스 생성
  - [x] Service 내에서 포스트가 없을 때 예외처리 추가
  - [x] Exception Controller PostNotFound 핸들러 추가
  - [x] Controller 내에서 포스트가 없을 때 예외 처리 추가
  - [x] ErrorResponse @JsonInclude로 null값 제외
  - [x] 예외처리 추상 클래스 생성 > Exception Controller mehtod 줄이기
  - [x] 추상 클래스 상속 > status 추가를 위해 Response Entity 사용
  

## 문서화

## Vue.js

## 배포

## API 인증