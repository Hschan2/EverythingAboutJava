# 엘리의 Linux Tips

## Unix Shell 배우는 장점
Linux 계열의 Mac OS에서 사용이 가능하고 현재 윈도우에서도 사용이 가능하다. 특히, 윈도우에서는 다른 프로그램을 사용하고 싶지 않다면 PowerShell (aliases 제공 => Linux에서 사용가능한 언어)을 사용하는 것이 좋다. (CMD 비추천)   

## 명령어 정리 (파일 탐색기)
* man (Manual): 매뉴얼 명령어
* clear: 터미널 청소
* pwd (print Working Directory): 현재 경로
* ls: 현재 경로의 폴더 및 파일 보기 (ls -l: 자세히 보기, ls -a: 숨겨진 파일도 보기)
* open .: 현재 경로 열기
* cd (Change Directory): 경로 변경 (cd ..: 상위 경로로 이동, cd ~: 최상위 경로로 이동, cd -: 이전 경로로 이동)
* find: 현재 경로에서 특정한 파일이나 디렉토리 찾기 (find . -type file -name "*.txt": 현재 경로에서 모든 파일 중 모든 txt 파일 찾기), (윈도우: Get-childItem 명령어 사용)
* which: 사용하고 있는 프로그램의 경로 확인, (윈도우: get-command(gcm) 명령어 사용)

## 명령어 정리 (파일 생성 및 관리)
* touch: 새로운 파일 생성
* cat: 파일 안에 내용 확인
* echo: 뒤에 작성된 문자열을 터미널에 출력 (echo "Hello World" > new_file.txt: new_file에 "Hello World"를 작성된 파일 생성, >>: 덧붙이지 않고 새로 생성된 것처럼 만들기)
* mkdir (Make Directory): 현재 혹은 원하는 경로에서 폴더 생성
* cp (Copy): 원하는 파일과 원하는 경로에 복사
* mv (Move): 원하는 파일을 원하는 경로에 이동 (원하는 파일을 복사하여 다른 경로에 붙여넣기 가능)
* rm (Remove): 원하는 파일 삭제
* grep (Global Regular Expression Print): 키워드로 검색 (특정 파일에 검색 가능: 뒤에 파일 작성), (grep -n: 몇 번째 줄인지 확인, grep -ni: 대소문자 구별 안하고 키워드 검색, grep -nir "키워드" .: 현재 경로에 키워드가 들어있는 모든 파일 검색), (윈도우: select-string 명령어 사용)

## 환경 변수 설정하기
* export: 환경 변수 설정 (export MY_DIR="폴더 이름"으로 변수 설정 가능)
* env: 모든 환경 변수 출력 (cd $MY_DIR: 해당 환경 변수로 이동, unset MY_DIR: 해당 환경 변수 삭제)

## VIM (VI): 텍스트 에디터
* vim 원하는 파일명: 새로운 파일 생성 그리고 에디터 모드로 변환
    * i 버튼 클릭: 글자 수정 및 삭제
    * esc 버튼 클릭
        * :q (:q!) - 현재 Vim에서 나가기 (강제로 나가기)
        * :wq - 현재 작성 내용 저장하고 나가기

[엘리의 Linux Tips](https://www.youtube.com/watch?v=EL6AQl-e3AQ&t=9s)