import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class ExpressionManager {

    public List<Token> infixToPostfix(List<Token> list) throws ArithmeticException {
	List<Token> postfixList = new ArrayList<Token>(); // initialize postfixList
	Stack<Operator> workStack = new Stack<Operator>(); // initialize working stack
	int i = 0; // initialize counting variable i
	int marker = 0; // initialize left parenthesis marker
	int leftCount = 0; // running count of left parenthesis
	int rightCount = 0; // running count of right parenthesis

	for (Token token : list) { // for every token in list
	    // System.out.println(workStack);
	    if (list.get(i).toString().equals("(")) { // if token is a left parenthesis
		Operator leftParen = (Operator) list.get(i); // cast to operator
		workStack.push(leftParen); // push to stack
		marker = i; // set marker for later pop method
		leftCount++; // increment left parenthesis
	    } else if (!list.get(i).toString().equals(")")) { // if the token is not a right parenthesis
		if (list.get(i).isNumber()) { // if token is a number
		    Number num = (Number) list.get(i); // cast token to number
		    postfixList.add(num); // add to postfixlist
		} else if (list.get(i).isOperator()) { // if token is an operator
		    Operator temp = (Operator) list.get(i); // cast token to operator
		    if (i == 0 && list.get(i).isOperator()) { // if first token is +-*/
			throw new ArithmeticException("Starting with non parenthesis operator");
		    }

		    if (i != 0 && list.get(i - 1).isOperator()) { // if i is operator and i-1 is operator that isn't
								  // parenthesis
			throw new ArithmeticException("Two operators with no number");
		    }
		    while (!workStack.isEmpty() && workStack.peek().getPriority() >= temp.getPriority()) {
			Operator op = workStack.pop(); // pop operator off the stack
			postfixList.add(op); // add to postfixList
		    }
		    workStack.push(temp); // push operator to the stack
		}
	    } else { // when right parenthesis is encountered
		rightCount++; // increment right parenthesis
		if (rightCount > leftCount) { // if there are more right than left parentheses
		    throw new ArithmeticException("Mismatched Parenthesis");
		}
		while (true) {
		    Operator ops = workStack.pop(); // pop operator off the stack
		    if (ops.toString().equals("(")) { // ignore left parenthesis when adding
			break;
		    } else { // move from stack to postfixList
			postfixList.add(ops); // add to postfixList
		    }
		    if (workStack.isEmpty()) { // if there is no
			throw new ArithmeticException("Mismatched Parenthesis");
		    }
		}
	    }
	    i++; // next token
	}
	if (leftCount > rightCount) { // if there are more left than right parentheses
	    throw new ArithmeticException("Mismatched Parenthesis");
	}
	if (workStack.peek().isOperator()) {
	    throw new ArithmeticException("End expression with non parenthesis operator");
	}
	while (!workStack.isEmpty()) { // while the stack isn't empty
	    Token temp2 = workStack.pop(); // pop each element off the stack
	    postfixList.add(temp2); // add to postfixList
	}
	return postfixList; // return the final postfix equation
    }

    public Expression buildExpression(List<Token> postfixList) throws ArithmeticException {
	Stack<Expression> workStack = new Stack<Expression>();
	int i = 0;
	for (Token token : postfixList) { // for every token in postfixList
	    if (postfixList.get(i).isNumber()) { // if postfix index i is a number
		Number num = (Number) postfixList.get(i); // cast to number
		workStack.push(num); // push to stack
	    } else if (postfixList.get(i).isOperator()) { // if postfix index i is an operator
		Operator op = (Operator) postfixList.get(i); // cast to operator
		Expression rightOp = workStack.pop(); // pop right operand off stack
		Expression leftOp = workStack.pop(); // pop left operand off stack
		BinaryExpression exp = new BinaryExpression(op, leftOp, rightOp); // make expression out of ops
		workStack.push(exp); // push expression to stack
	    }
	    i++; // next token in postfixList
	}
	Expression result = workStack.pop(); // pop final expression off stack
	return result; // return final expression
    }
}