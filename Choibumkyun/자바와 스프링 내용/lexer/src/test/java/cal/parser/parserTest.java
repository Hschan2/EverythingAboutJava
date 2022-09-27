package cal.parser;

import cal.ast.IdExp;
import cal.ast.LetStmt;
import cal.lexer.Lexer;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 파서 구현
 * 토큰 스트림 -> SST(Abstract Syntax Tree, 추상 구문 트리)
 * 프로그램을 트리 구조로 구현
 * 표현식- 값, 1 -> 숫자식 | 1 + 2 -> 이항연산자식 (+ - * / **) | -10 -> 단항연산자식 | a1 -> 식별자식
 * 구문 - 명령, let a1 = 10 -> let 구문 | 1 + 1 -> 표현식을 담은 식구문
 * 연산자 우선순위, + - => * / => - 단항 => ** => 괄호
 */

public class parserTest {
//    숫자형 파싱
    @Test
    void numberExp() {
        String input = "1\n2 \n 3";
        Lexer lexer = new Lexer(input);
        Parser parser = new Parser(lexer);
        Program program = parser.parserProgram();
        Stmt[] stmts = program.getState();
//        Stmt stmt = stmts[0];
//        double expected = 1.0;
        assertStmtWithNumber(stmts[0], 1.0);
        assertStmtWithNumber(stmts[1], 2.0);
        assertStmtWithNumber(stmts[2], 3.0);
    }

//    ID 값 파싱
    @Test
    void idExp() {
        String input = "abc \n a123";
        Lexer lexer = new Lexer(input);
        Parser parser = new Parser(lexer);
        Program program = parser.parserProgram();
        Stmt[] stmts = program.getState();
        assertStmtWithId(stmts[0], "abc");
        assertStmtWithId(stmts[1], "a123");
    }

//    LET이 포함된 파싱
    @Test
    void letStmt() {
        String input = "let abc = 10";
        Lexer lexer = new Lexer(input);
        Parser parser = new Parser(lexer);
        Program program = parser.parserProgram();
        Stmt[] stmts = program.getState();
        Stmt stmt = stmts[0];
        assertThat(stmt).isInstanceOf(LetStmt.class);
        LetStmt letStmt = (LetStmt) stmt;
        assertIdExp(letStmt.getIdExp(), "abc");
        assertNumberExp(letStmt.getExp(), 10.0);
    }

//    ID 값 상태 파싱 확인
    private static void assertStmtWithId(Stmt stmt, String id) {
        assertThat(stmt).isInstanceOf(ExpStmt.class);
        ExpStmt expStmt = (ExpStmt) stmt;
        Exp exp = expStmt.getExp();
        assertIdExp(exp, id);
    }

//    ID 값 상태 체크
    private static void assertIdExp(Exp exp, String id) {
        assertThat(exp).isInstanceOf(IdExp.class);
        IdExp idExp = (IdExp) exp;
        assertThat(idExp.getId()).isEqualTo(id);
    }

//    숫자형 상태 파싱 확인
    private static void assertStmtWithNumber(Stmt stmt, double expected) {
        assertThat(stmt).isInstanceOf(ExpStmt.class);
        ExpStmt expStmt = (ExpStmt) stmt;
        Exp exp = expStmt.getExp();
        assertNumberExp(exp, expected);
    }

//    숫자형 상태 체크
    private static void assertNumberExp(Exp exp, double expected) {
        assertThat(exp).isInstanceOf(NumberExp.class);
        NumberExp numberExp = (NumberExp) exp;
        assertThat(numberExp.valueAsDouble()).isEqualTo(expected);
    }
}
