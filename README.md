# 영단어 학습 프로그램

## 프로젝트 소개
영어단어를 학습하는데 도움을 주는 프로그램입니다.

![image](https://user-images.githubusercontent.com/64728336/174473118-dd22d627-4dc3-4c18-a694-aabdfb786b15.png)
***
### 메뉴
소개\
![image](https://user-images.githubusercontent.com/64728336/174473138-066d69de-c4ed-481f-9d45-6d85b76408c4.png)

도움말\
![image](https://user-images.githubusercontent.com/64728336/174473151-f524599c-0119-4108-bfa9-d288984fc687.png)

-메시지 다이얼로그를 이용해 소개와 도움말 다이얼로그 설정.
***
### 툴바
![image](https://user-images.githubusercontent.com/64728336/174473182-9384ae5b-26c6-45fe-b763-3014c9a2685e.png)

-New 버튼 - JtextField에 적힌 이름의 단어장 생성(미구현)\
-ComboBox - 생성된 단어장 선택(미구현)\
-Set 버튼 - 단어장의 단어 전체보기\
***
### 단어 추가/삭제/검색/수정
![image](https://user-images.githubusercontent.com/64728336/174473268-fd71141e-8726-4d0b-bf14-329c8a89ad7c.png)

-추가 - textField에 영어 단어와 해석을 쓰고 추가 버튼 누르면 DB에 단어 추가\
-삭제 - textField에 영어 단어를 입력하면 단어 삭제\
-검색 - textField에 영어 단어를 입력하면 해당 단어와 뜻을 출력\
-수정 - textField에 영어 단어와 수정하려하는 해석을 입력하면 DB에 단어 수정\
***
### 단어 리스트
![image](https://user-images.githubusercontent.com/64728336/174473378-98a4d204-d143-45d8-ac03-8b67526bf2d7.png)
![image](https://user-images.githubusercontent.com/64728336/174473450-82f2af17-36c2-4f1d-911a-96ce7f7f97f4.png)

-감추기 버튼 - "영단어" Label과 "해석" Label 옆에 버튼은 각각 해당하는 리스트를 가리도록 함\
***
### 단어 테스트
전체 단어 갯수\
최근 점수\
최근 틀린 갯수\
테스트 점수 출력\
