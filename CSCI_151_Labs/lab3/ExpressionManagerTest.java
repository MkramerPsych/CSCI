import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class ExpressionManagerTest {

    @Test
    public void testInfixToPostfix() {
	ExpressionManager expressionManager = new ExpressionManager();
	List<Token> infix = new ArrayList<Token>();
	infix.add(new Number(20));
	infix.add(new Operator("-"));
	infix.add(new Number(13));
	List<Token> postfix = expressionManager.infixToPostfix(infix);
	Expression e1 = expressionManager.buildExpression(postfix);
	assertEquals("infix to postfix test 1", 7, e1.valueOf());
	infix.add(new Operator("+"));
	infix.add(new Number(3));
	postfix = expressionManager.infixToPostfix(infix);
	e1 = expressionManager.buildExpression(postfix);
	assertEquals("infix to postfix test 2", 10, e1.valueOf());
    }

    @Test
    public void testInfixToPostfixParen() {
	ExpressionManager expressionManager2 = new ExpressionManager();
	List<Token> infix2 = new ArrayList<Token>();
	infix2.add(new Number(10));
	infix2.add(new Operator("*"));
	infix2.add(new Operator("("));
	infix2.add(new Number(5));
	infix2.add(new Operator("+"));
	infix2.add(new Number(7));
	infix2.add(new Operator(")"));
	List<Token> postfix2 = expressionManager2.infixToPostfix(infix2);
	System.out.println(postfix2);
	Expression e2 = expressionManager2.buildExpression(postfix2);
	assertEquals("infix to postfix test with parenthesis", 120, e2.valueOf());
    }

    @Test
    public void testBuildExpression() {
	ExpressionManager expressionManager = new ExpressionManager();
	ArrayList<Token> postfix = new ArrayList<Token>();
	postfix.add(new Number(37));
	Expression e1 = expressionManager.buildExpression(postfix);
	assertEquals("build test 1", 37, e1.valueOf());
	postfix.clear();
	postfix.add(new Number(20));
	postfix.add(new Number(13));
	postfix.add(new Operator("*"));
	Expression e2 = expressionManager.buildExpression(postfix);
	assertEquals("build test 2", 260, e2.valueOf());
	assertEquals("build test 3", "(20*13)", e2.toString());
	assertEquals("build test 4", "* 20 13", e2.toPrefix());
    }
}
