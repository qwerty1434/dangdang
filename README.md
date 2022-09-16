
# 협업 규칙 (그라운드룰)

## 브랜치

- main (메인브랜치)
    - develop (메인브랜치) (개발할때는 main이라고 생각)
        - front (메인브랜치)
            - feat-f/”Jira이슈번호”/”기능이름” (보조브랜치)
        - back (메인브랜치)
            - feat-b/”Jira이슈번호”/”기능이름” (보조브랜치)

## 커밋 메세지

| 구분자 | 작업 유형 | 예시 | 
| --- | --- | --- | 
| feat  | 새 기능 구현 | [#JIRA issue number] feat: 락커 회원 목록 검색 기능 추가 |  
| fix | 버그 수정 | [#JIRA issue number] fix: 상점 목록의 에러처리 예외케이스 대응 |  
| docs | 문서(또는 주석) 관련 작업 | [#JIRA issue number] docs: 데코레이터 옵션에 대한 문서 추가 |  
| refactor | 리팩터링 | [#JIRA issue number] refactor: createStore의 함수를 작은 함수로 분리 |  
| test | 테스트 관련 작업 | [#JIRA issue number] test: 상점 생성 테스트 추가 |  
| chore | 기타 작업 | [#JIRA issue number] chore: 프로덕션 빌드시 소스맵 생성하도록 변경  |  
| style  | 코드상 내용 변경 없음 (ex : 세미콜론 누락) | [#JIRA issue number] style : 프로필 페이지 수정 및 친구 프로필 페이지 수정   |

## MR 규칙

### 제목

- 커밋 메시지 그대로

### 템플릿

```markdown
## MR Description :page_facing_up:

### 목적

- 무슨 이유로 코드를 변경했는지
  
### 주요 변경사항
  
- [x] 변경사항(1)
- [x] 변경사항(2)
- [x] 변경사항(3)
  
### 리뷰 요청

- 어떤 부분에 리뷰어가 집중하면 좋을지
  
### 이슈 (없으면 삭제)
  
- 어떤 위험이나 장애가 발견됐는지
  
### 참고사항(없으면 삭제)
  
['참고한 레퍼런스명'](https://edu.ssafy.com/edu/main/index.do)
  
### 관련 스크린샷(없으면 삭제)

Closes 이슈넘버(ex -> S07P21A306-17)
```

![Untitled](/uploads/165d1dec735c8bd3c0de1dac689f6923/Untitled.png)

## Label 작성

- FE
- BE
- 기능별

![Untitled_1](/uploads/5b64e282ba242312779e46d6c5659dba/Untitled_1.png)

## Jira

- 매주 월요일 스프린트 등록
- 오전 스크럼 때 상기시켜주기
- 작업 후 done
- 용어 정리 - [https://velog.io/@kjh1551/JIRA-학습-정리](https://velog.io/@kjh1551/JIRA-%ED%95%99%EC%8A%B5-%EC%A0%95%EB%A6%AC)

### 코드 컨벤션

- 컴포넌트명은 `파스칼 케이스`로 진행한다.
    - ex) PascalCase
- 그외 변수명은 `카멜 케이스`로 진행한다.
    - ex) camelCase
- 함수는 전부 `function 함수명 (){ return() }`형태로 ES5 함수표현식으로 진행한다. (ES6 화살표 함수를 사용하지 않는다.)
- 컴포넌트를 작성할 때 상위는 스타일을 작성하고 함수의 위치는 export default 뒤에 작성하므로 함수의 위치를 통일시킨다.
- Prettier를 통해 코드 형태를 통일시킨다.
