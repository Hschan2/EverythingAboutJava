package cal.parser;

import cal.ast.IdExp;
import cal.ast.LetStmt;
import cal.ast.PreFixOpExp;
import cal.ast.inFixOpExp;
import cal.lexer.Lexer;
import cal.token.Token;
import cal.token.TokenType;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    private Lexer lexer;
    private Token curToken;
    private Token peekToken;

    public Parser(Lexer lexer) {
        this.lexer = lexer;
    }

//    다음 토큰으로 이동
    private void nextToken() {
        curToken = peekToken;
        peekToken = lexer.nextToken();
    }

    public Program parserProgram() {
//        두 번 하는 이유는 첫 번째 토큰으로 이동하기 위해
        nextToken();
        nextToken();
        List<Stmt> stmts = new ArrayList<>();
//        전달받은 input의 길이가 1이 아닐수도 있다는 조건
        while (curToken.getType() != TokenType.EOF) {
            Stmt stmt = parseStmt();
            if (stmt != null) {
                stmts.add(stmt);
            }
            nextToken();

            if (curToken.getType() == TokenType.NEWLINE) {
                nextToken();
            }
        }

        return new Program(stmts.toArray(new Stmt[stmts.size()]));
    }

//    input 상태 parsing
    private Stmt parseStmt() {
        Stmt stmt = null;
        if (curToken.getType() == TokenType.LET) {
            stmt = parseLetStmt();
        } else {
            stmt = parseExpStmt();
        }
        stmt = parseExpStmt();
        return stmt;
    }

//    TokenType이 LET일 경우 (input이 let일 경우)
    private Stmt parseLetStmt() {
        if (!expectPeek(TokenType.ID)) {
            return null;
        }
        IdExp idExp = new IdExp(curToken.getLiteral());
        if (!expectPeek(TokenType.ASSIGN)) {
            return null;
        }
        nextToken();
        Exp exp = parseExp(0);
        if (exp == null) {
            return null;
        }

        return new LetStmt(idExp, exp);
    }

//    peek이 맞으면 진행 아니면 정지
    private boolean expectPeek(TokenType type) {
        if (peekToken.getType() != type) {
            return false;
        }
        nextToken();

        return true;
    }

//    다음 exp 파싱
    private Stmt parseExpStmt() {
        Exp exp = null;
        exp = parseExp(0);
        return new ExpStmt(exp);
    }

//    number, id 일 경우 파싱
    private Exp parseExp(int prec) {
        Exp exp = null;
        if (curToken.getType() == TokenType.NUMBER) {
            exp = new NumberExp(curToken.getLiteral());
        }
        if (curToken.getType() == TokenType.ID) {
            exp = new IdExp(curToken.getLiteral());
        }
        if (curToken.getType() == TokenType.LPAREN) {
            exp = parseParan();
        }
        if (curToken.getType() == TokenType.MINUS) {
            exp = parsePreFixOp();
        }

//        중위 연산자가 올 경우, precEdence는 ()가 들어갔을 때 우선순위를 결정하기 위해
        while (inFixOp(peekToken.getType()) && prec < precEdence(peekToken.getType())) {
            nextToken();
            String operator = curToken.getLiteral();
            int curPrec = precEdence(curToken.getType());
            nextToken();
            Exp right = parseExp(curPrec);
            exp = new inFixOpExp(operator, exp, right);
        }
        return exp;
    }

    private Exp parsePreFixOp() {
        String operator = curToken.getLiteral();
        nextToken();
        Exp exp = parseExp(5);
        if (exp == null) {
            return null;
        }
        return new PreFixOpExp(operator, exp);
    }

    private Exp parseParan() {
        nextToken();
        Exp exp = parseExp(0);
        if (exp == null) {
            return null;
        }
        if (!expectPeek(TokenType.RPAREN)) {
            return null;
        }

        return exp;
    }

    private int precEdence(TokenType type) {
        return switch (type) {
            case PLUS, MINUS -> 1;
            case MULTIPLY, DIVIDE -> 3;
            case POW -> 7;
            default -> 0;
        };
    }

    private boolean inFixOp(TokenType type) {
        return type == TokenType.PLUS ||
                type == TokenType.MINUS ||
                type == TokenType.MULTIPLY ||
                type == TokenType.DIVIDE ||
                type == TokenType.POW;
    }
}
