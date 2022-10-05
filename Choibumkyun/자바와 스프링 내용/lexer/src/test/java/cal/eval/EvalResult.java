package cal.eval;

import java.math.BigDecimal;

public class EvalResult {
    private BigDecimal value;

    public EvalResult(BigDecimal value) {
        this.value = value;
    }

    public static EvalResult of(String value) {
        return new EvalResult(new BigDecimal(value));
    }

    public double getValueAsDouble() {
        return value.doubleValue();
    }

    public BigDecimal getValue() {
        return value;
    }
}
