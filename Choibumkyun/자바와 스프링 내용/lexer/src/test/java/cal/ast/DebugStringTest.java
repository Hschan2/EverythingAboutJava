package cal.ast;

import cal.parser.Exp;
import cal.parser.NumberExp;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DebugStringTest {
    @Test
    void inFix() {
        Exp left = new NumberExp("1");
        Exp right = new IdExp("a");
        inFixOpExp inExp = new inFixOpExp("+", left, right);
        assertThat(inExp.DebugString()).isEqualTo("(1 + a)");

    }
}
