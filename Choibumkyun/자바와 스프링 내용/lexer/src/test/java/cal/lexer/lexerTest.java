package cal.lexer;

import cal.token.Token;
import cal.token.TokenType;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 간단 계산기 인터프리터
 * let a1 = 10.5
 * let a2 = 3
 * a1 + 2 * 3 - (4 + 5) / a2
 * let a = 10, a + 20 -> 렉서(Lexer) -> ID(a), LET... -> 파서(Parser) -> 평가 - > 20
 * 렉서 구현, 문자열을 토큰으로 변환 (let a1 = 10.1 -> Token(LET, "let") Token(ID, "a1") Token(ASSIGN, "=") Token(NUMBER, "10.1")
 * let -> LET
 * + - * / () -> PLUS, MINUS, MULTIPLY, DIVIDE, LPAREN, RPAREN
 * ** -> POW
 * = -> ASSIGN
 * 10.1 -> NUMBER
 * abc -> ID
 * \n -> NEWLINE
 */

public class lexerTest {

//    하나의 단어를 활용한 테스트
    @Test
    void singleChar() {
        String input = "+-*/ ()=\n";
        List<Token> expectedTokens = List.of(
                new Token(TokenType.PLUS, "+"),
                new Token(TokenType.MINUS, "-"),
                new Token(TokenType.MULTIPLY, "*"),
                new Token(TokenType.DIVIDE, "/"),
                new Token(TokenType.LPAREN, "("),
                new Token(TokenType.RPAREN, ")"),
                new Token(TokenType.ASSIGN, "="),
                new Token(TokenType.NEWLINE, "\n"),
                new Token(TokenType.EOF, null)
        );

//        아래의 방법을 반복문을 사용한 메소드로 정리
        assertTokens(input, expectedTokens);

//        Token token = lexer.nextToken();
//        assertThat(token).isEqualTo(new Token(TokenType.PLUS, "+"));
//        Token token1 = lexer.nextToken();
//        assertThat(token1).isEqualTo(new Token(TokenType.EOF, null));
    }
    
    private void assertTokens(String input, List<Token> expectedTokens) {
        Lexer lexer = new Lexer(input);

        for (Token expected : expectedTokens) {
            assertThat(lexer.nextToken()).isEqualTo(expected);
        }
    }

//    해당 ID 값이 (예. "abc")가 전달될 경우
    @Test
    void id() {
        assertTokens("abc def g1 let", List.of(
                new Token(TokenType.ID, "abc"),
                new Token(TokenType.ID, "def"),
                new Token(TokenType.ID, "g1"),
                new Token(TokenType.LET, "let"),
                new Token(TokenType.EOF, null))
        );
    }

//    숫자 테스트
    @Test
    void numberToken() {
        assertTokens("10 1.234", List.of(
                new Token(TokenType.NUMBER, "10"),
                new Token(TokenType.NUMBER, "1.234"),
                new Token(TokenType.EOF, null))
        );
    }

//    **(pow) 테스트
    @Test
    void pow() {
        assertTokens("**", List.of(
                new Token(TokenType.POW, "**"),
                new Token(TokenType.EOF, null))
        );
    }

//    잘못된 값 테스트
    @Test
    void illegal() {
        assertTokens("1@", List.of(
                new Token(TokenType.NUMBER, "1"),
                new Token(TokenType.ILLEGAL, "@"),
                new Token(TokenType.EOF, null))
        );
    }

//    최종 테스트
    @Test
    void composite() {
        assertTokens("let a1 = 10\n1 + 2 * a1 ** 2", List.of(
                new Token(TokenType.LET, "let"),
                new Token(TokenType.ID, "a1"),
                new Token(TokenType.ASSIGN, "="),
                new Token(TokenType.NUMBER, "10"),
                new Token(TokenType.NEWLINE, "\n"),
                new Token(TokenType.NUMBER, "1"),
                new Token(TokenType.PLUS, "+"),
                new Token(TokenType.NUMBER, "2"),
                new Token(TokenType.MULTIPLY, "*"),
                new Token(TokenType.ID, "a1"),
                new Token(TokenType.POW, "**"),
                new Token(TokenType.NUMBER, "2"),
                new Token(TokenType.EOF, null))
        );
    }
}
