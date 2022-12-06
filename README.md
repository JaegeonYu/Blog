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
  - [x] @ControllerAdvice 활용 ErrorController
   - [x] Exception 클래스 분리
- [ ] 게시글 저장 구현(POST)
  - [x] Repository, Service, Domain(Entity) 구현
  - [ ] PostRequestDTO Construct add @Builder
  - [ ] use ObjectMapper object to json string
  - [ ] Controller단에서 조회글 저장 확인
  - [ ] Service단에서 조회글 저장 확인
- [ ] 응답 클래스 분리
- [ ] 게시글 조회 구현(GET)
- [ ] 게시글 여러개 조회 구현(GET+LIST)
- [ ] 페이징 처리
- [ ] 게시글 수정
- [ ] 게시글 삭제
- [ ] 예외처리

## 문서화

## Vue.js

## 배포

## API 인증