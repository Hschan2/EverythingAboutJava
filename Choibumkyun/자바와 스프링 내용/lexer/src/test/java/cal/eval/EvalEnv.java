package cal.eval;

import java.util.HashMap;
import java.util.Map;

// Let 변수가 포함되었을 때
public class EvalEnv {
    private Map<String, EvalResult> values = new HashMap<>();

    public void put(String id, EvalResult result) {
        values.put(id, result);
    }

    public EvalResult get(String id) {
        return values.get(id);
    }
}
