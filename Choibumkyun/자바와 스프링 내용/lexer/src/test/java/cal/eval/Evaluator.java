package cal.eval;

import cal.ast.IdExp;
import cal.ast.LetStmt;
import cal.ast.PreFixOpExp;
import cal.ast.inFixOpExp;
import cal.parser.*;

public class Evaluator {
    public EvalResult eval(Program program, EvalEnv env) {
        EvalResult result = null;

        Stmt[] stmts = program.getState();
//        가장 마지막 Statement를 result에 담기
        for (Stmt stmt : stmts) {
            result = evalStmt(stmt, env);
        }

        return result;
    }

    private EvalResult evalStmt(Stmt stmt, EvalEnv env) {
        if (stmt instanceof ExpStmt expStmt) { // 숫자만 있는 경우
            return evalExpStmt(expStmt, env);
        } else if (stmt instanceof LetStmt letStmt) { // Let 포함일 경우
            return evalLetStmt(letStmt, env);
        }

        return null;
    }

    private EvalResult evalLetStmt(LetStmt letStmt, EvalEnv env) {
        String id = letStmt.getIdExp().getId();
        EvalResult result = evalExp(letStmt.getExp(), env);
        env.put(id, result);
        return null;
    }

    private EvalResult evalExpStmt(ExpStmt expStmt, EvalEnv env) {
        return evalExp(expStmt.getExp(), env);
    }

//    값 처리
    private EvalResult evalExp(Exp exp, EvalEnv env) {
        if (exp instanceof NumberExp numberExp) { // 숫자인 경우
            return EvalResult.of(numberExp.getValue());
        } else if (exp instanceof inFixOpExp inFixOpExp) { // 연산자 포함인 경우
            return evalInFixOpExp(inFixOpExp, env);
        } else if (exp instanceof PreFixOpExp preFixOpExp) { // 음수가 포함인 경우
            return evalPreFixOpExp(preFixOpExp, env);
        } else if (exp instanceof IdExp idExp) { // 음수가 포함인 경우
            return evalIdExp(idExp, env);
        }

        return null;
    }

    private EvalResult evalIdExp(IdExp idExp, EvalEnv env) {
        String id = idExp.getId();

        return env.get(id);
    }

    //    음수가 포함되었을 경우 처리
    private EvalResult evalPreFixOpExp(PreFixOpExp preFixOpExp, EvalEnv env) {
        EvalResult result = evalExp(preFixOpExp.getExp(), env);
        return switch (preFixOpExp.getOperator()) {
            case "-" -> new EvalResult(result.getValue().negate());
            default -> null;
        };
    }

//    연산자가 포함되었을 경우 처리
    private EvalResult evalInFixOpExp(inFixOpExp inFixOpExp, EvalEnv env) {
        EvalResult left = evalExp(inFixOpExp.getLeft(), env);
        EvalResult right = evalExp(inFixOpExp.getRight(), env);
        return switch (inFixOpExp.getOperator()) {
            case "+" -> new EvalResult(left.getValue().add(right.getValue()));
            case "-" -> new EvalResult(left.getValue().subtract(right.getValue()));
            case "*" -> new EvalResult(left.getValue().multiply(right.getValue()));
            case "/" -> new EvalResult(left.getValue().divide(right.getValue()));
            case "**" -> new EvalResult(left.getValue().pow(right.getValue().intValue()));
            default -> null;
        };
    }
}
