package cal.eval;

import cal.lexer.Lexer;
import cal.parser.Parser;
import cal.parser.Program;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 평가기
 * 파서가 생성한 AST를 순회하면서 값을 생성
 * 예시 기본 코드
 * Lexer lexer = new Lexer(input);
 * Parser parser = new Parser(lexer);
 * Program program = parser.parseProgram();
 * Evaluator evaluator = new Evaluator();
 * ResultValue value = evaluator.eval(program, new EvalEnv());
 * double value = result.getValueAsDouble();
 */

public class EvaluatorTest {

    @Test
    void program() {
        assertEvalResult("1", 1.0);
        assertEvalResult("5", 5.0);
        assertEvalResult("5 + 5", 10.0);
        assertEvalResult("1 + 3 * 2 - 4 / 2", 5.0);
        assertEvalResult("2 ** 3", 8.0);
        assertEvalResult("-1", -1.0);
        assertEvalResult("-2 * 4", -8.0);
        assertEvalResult("let a = 1\na + 3", 4.0); // let => EvalEnv
        assertEvalResult("let a = 2\nlet b = a + 3\n a * b", 10.0);
    }

    private void assertEvalResult(String input, double expected) {
        Lexer lexer = new Lexer(input);
        Parser parser = new Parser(lexer);
        Program program = parser.parserProgram();
        Evaluator evaluator = new Evaluator();
        EvalResult result = evaluator.eval(program, new EvalEnv());
        assertThat(result.getValueAsDouble()).isEqualTo(expected);
    }
}
