import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BinaryExpressionTest {

    @Test
    public void testBinaryExpression() {
	Expression e1 = new Number(46);
	assertEquals("e1", 46, e1.valueOf());
    }

    @Test
    public void testValueOf() {
	Expression e2 = new BinaryExpression(new Operator("/"), new Number(120), new Number(7));
	assertEquals("e2", 17, e2.valueOf());
	Expression e3 = new BinaryExpression(new Operator("-"), new Number(50), new Number(15));
	assertEquals("e3", 35, e3.valueOf());
	Expression e4 = new BinaryExpression(new Operator("*"), e2, e3);
	assertEquals("e4", 595, e4.valueOf());
    }

    @Test
    public void testToPrefix() {
	Expression e2 = new BinaryExpression(new Operator("*"), new Number(20), new Number(13));
	assertEquals("Prefix test", "* 20 13", e2.toPrefix());
    }

    @Test
    public void testToPostfix() {
	Expression e2 = new BinaryExpression(new Operator("*"), new Number(20), new Number(13));
	assertEquals("Postfix test", "20 13 *", e2.toPostfix());
    }

    @Test
    public void testToInfix() {
	Expression e2 = new BinaryExpression(new Operator("*"), new Number(20), new Number(13));
	assertEquals("Infix test", "(20*13)", e2.toInfix());
    }

    @Test
    public void testToString() {
	Expression e2 = new BinaryExpression(new Operator("*"), new Number(20), new Number(13));
	assertEquals("String conversion test", "(20*13)", e2.toString());
    }
}
