package cal.parser;

import cal.ast.IdExp;
import cal.ast.LetStmt;
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
        Exp exp = parseExp();
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
        exp = parseExp();
        return new ExpStmt(exp);
    }

//    number, id 일 경우 파싱
    private Exp parseExp() {
        Exp exp = null;
        if (curToken.getType() == TokenType.NUMBER) {
            exp = new NumberExp(curToken.getLiteral());
        }
        if (curToken.getType() == TokenType.ID) {
            exp = new IdExp(curToken.getLiteral());
        }
        return exp;
    }
}
