package cal.lexer;

import cal.token.Token;
import cal.token.TokenType;

public class Lexer {
    private String input;

//    현재 위치 변수
    private char curChar;
//    현재 위치 인덱스
    private int curIdx;
//    마지막 위치 인덱스
    private int peekIdx;

    public Lexer(String input) {
        this.input = input;
        readChar();
    }

//    토큰을 한 글자씩 읽기 위한
    private void readChar() {
        if (peekIdx == input.length()) {
            curChar = 0;
            curIdx = peekIdx;
        } else {
            curIdx = peekIdx;
            curChar = input.charAt(curIdx);
            peekIdx++;
        }
    }

    public Token nextToken() {
        skipSpaces();
        Token t = null;

//        전달받은 Input이 아래 중에서 해당할 경우 열거형 전달
        if (curChar == 0) {
            t = new Token(TokenType.EOF, null);
        } else if (curChar == '+') {
            t = new Token(TokenType.PLUS, String.valueOf(curChar));
        } else if (curChar == '-') {
            if (peekCh() == '*') {
                t = new Token(TokenType.POW, "**");
            } else {
                t = new Token(TokenType.MINUS, String.valueOf(curChar));
            }
        } else if (curChar == '*') {
            t = new Token(TokenType.MULTIPLY, String.valueOf(curChar));
        } else if (curChar == '/') {
            t = new Token(TokenType.DIVIDE, String.valueOf(curChar));
        } else if (curChar == '(') {
            t = new Token(TokenType.LPAREN, String.valueOf(curChar));
        } else if (curChar == ')') {
            t = new Token(TokenType.RPAREN, String.valueOf(curChar));
        } else if (curChar == '=') {
            t = new Token(TokenType.ASSIGN, String.valueOf(curChar));
        } else if (curChar == '\n') {
            t = new Token(TokenType.NEWLINE, String.valueOf(curChar));
        } else {
            if (isAlpah(curChar)) {
                String str = readString();

                if ("let".equals(str)) {
                    return new Token(TokenType.LET, str);
                } else {
                    return new Token(TokenType.ID, str);
                }
            } else if (isDigit(curChar)) {
                Token nt = readNumberToken();

                if (nt != null) {
                    return nt;
                }
            }
        }
        if (t == null) {
            t = new Token(TokenType.ILLEGAL, String.valueOf(curChar));
        }

        readChar();
        return t;
    }

    private char peekCh() {
        return peekIdx == input.length() ? 0 : input.charAt(peekIdx);
    }

    //    Token 값이 숫자일 경우
    private Token readNumberToken() {
        int start = curIdx;

//        알파벳 계속 읽기
        while (isDigit(curChar)) {
            readChar();
        }
        
//        input 값이 .(소수점)일 경우 계속 읽기
        if (curChar == '.') {
            readChar();
            while (isDigit(curChar)) {
                readChar();
            }
        }

        int end = curIdx;

        return new Token(TokenType.NUMBER, input.substring(start, end));
    }

//    알파벳이면 계속 읽기

    private String readString() {
        int start = curIdx;

//        알파벳 계속 읽기
        while (isAlpah(curChar) || isDigit(curChar)) {
            readChar();
        }

        int end = curIdx;

//        문자형 ID값 모두 가져오기
        String str = input.substring(start, end);
        return str;
    }

    //    ID값에 숫자가 있는 경우
    private boolean isDigit(char curChar) {
        return '0' <= curChar && curChar <= '9';
    }

    //    알파벳인지 확인
    private boolean isAlpah(char curChar) {
        return ('a' <= curChar && curChar <= 'z') || ('A' <= curChar && curChar <= 'Z');
    }

    //    빈 공간(' ')이 감지된다면 다음으로 넘어가도록
    private void skipSpaces() {
        while (curChar == ' ') {
            readChar();
        }
    }
}
