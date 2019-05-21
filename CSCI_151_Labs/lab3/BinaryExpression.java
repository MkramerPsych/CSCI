public class BinaryExpression implements Expression {

    // Instance Variables
    Expression leftOperand;
    Expression rightOperand;
    Operator operator;

    // Constructors
    public BinaryExpression(Operator operator, Expression leftOp, Expression rightOp) {
	this.operator = operator;
	this.leftOperand = leftOp;
	this.rightOperand = rightOp;
    }

    // Methods
    public int valueOf() { // return value of expression
	return operator.apply(leftOperand.valueOf(), rightOperand.valueOf());
    }

    public String toPrefix() { // return operator followed by prefix notation of each operand
	String op = operator.toString();
	String left = leftOperand.toPrefix();
	String right = rightOperand.toPrefix();
	return op + " " + left + " " + right;

    }

    public String toPostfix() { // return postfix notation of each expression followed by operator
	String op = operator.toString();
	String left = leftOperand.toPostfix();
	String right = rightOperand.toPostfix();
	return left + " " + right + " " + op;
    }

    public String toInfix() { // return fully parenthesized infix notation for entire expression
	String op = operator.toString();
	String left = "(" + leftOperand.toString();
	String right = rightOperand.toString() + ")";
	return (left) + op + (right);

    }

    public String toString() { // return expression as a string
	return toInfix();
    }

}
