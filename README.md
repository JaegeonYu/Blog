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
  - [x] 예외처리 추상 클래스 생성 > Exception Controller method 줄이기
  - [x] 추상 클래스 상속 > status 추가를 위해 Response Entity 사용
  

## 문서화
- [x] 기본 설정
  - [x] gradle Spring REST Docs dependency 추가, (요구 스프링 버젼 확인할 것)
  - [x] API DOC 생성을 위한 테스트 메소드 생성
  - [x] build bootJar copy 위치 변경
- [x] 요청 응답필드 생성
  - [x] asciiDocs 작성
  - [x] 기본 url 변경 / @AutoConfigureRestDocs
  - [x] 글 조회, 등록 API 문서 작성

## Vue.js
- [x] Vue 설치
  - [x] Vue.js init, 확장 devtool install
  - [x] router, views, components, App.vue 정리
  - [x] Element plus, Boot strap  install
  - [x] axios install
- [x] 글 작성 화면 만들기
  - [x] title, content element-plus, css apply
  - [x] button axios apply
- [x] CORS 해결
  - [x] Server : Java - WebConfig CORS Mapping
  - [x] Front : Proxy set
- [x] 글 리스트 화면 구현
  - [x] axios get and forEach Posts render
  - [x] write and replace to Home
- [x] 글 내용 화면 구현
  - [x] ReadView, Router 추가
  - [x] from router-link, to onMounted Props 관리
- [x] 글 수정 화면 구현

## 배포

## API 인증
- [ ] Spring Interceptor 사용
  - [x] Interceptor 구현체, WebMvcConfig 구현체 생성
  - [x] Unauthorized 예외 클래스 생성
  - [x] WebMvcConfig 에서 exclude path 설정 
- [ ] Spring ArgumentResolver 사용 
  - [ ] Controller 전에 request 내용을 이용해 DTO로 전달해줌
  - [ ] WebMvcConfig 에 추가
  - [ ] add Interceptor 주석화 => ArgumentResolver만 사용
- [ ] ArgumentResolver 파라미터 인증 => 헤더 인증으로 변경
  - [ ] Argument request getter 변경
  - [ ] Intellij http 설정
- [ ] 고정인증 로그인 구현
  - [ ] Login Request DTO, User Entity, UserCRUDRepository, AuthController 생성
  - [ ] sql.data + application.yml 테스트 시에 DB값 init
- [ ] 세션토큰 발급
  - [x] UserService class, sign in
  - [x] Session Entity 생성, with User 연관관계 mapping
  - [x] Session Repository 생성
  - [x] User <--> Session 연관관계 Repository 저장
- [x] 데이터베이스를 통한 토큰 검증
  - [x] AuthResolver 데이터 베이스 인증추가 및 Session Repository 주입
     