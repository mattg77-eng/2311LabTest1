package model;

import tests.Expression;
import tests.Operand;
import tests.Operator;
import tests.SLLNode;
import tests.TreeNode;

public class TreeUtilities {

	public TreeNode<Expression> getInfixTree(SLLNode<Expression> exprList) {
		SLLNode<TreeNode<Expression>> stack = null; 

		SLLNode<Expression> current = exprList;
		while (current != null) {
			Expression e = current.getElement();

			if (e instanceof Operand) {
				TreeNode<Expression> operandNode = new TreeNode<>(e);
				stack = new SLLNode<>(operandNode, stack);
			} else if (e instanceof Operator) {
				TreeNode<Expression> right = stack.getElement();
				stack = stack.getNext();
				TreeNode<Expression> left = stack.getElement();
				stack = stack.getNext();

				TreeNode<Expression> opNode = new TreeNode<>(e);
				opNode.addChild(left);
				opNode.addChild(right);
				left.setParent(opNode);
				right.setParent(opNode);

				stack = new SLLNode<>(opNode, stack);
			}

			current = current.getNext();
		}

		return stack.getElement();
	}

	public String getInfixSequence(SLLNode<Expression> exprList) {
		TreeNode<Expression> root = getInfixTree(exprList);
		return buildInfixString(root, true);
	}

	private String buildInfixString(TreeNode<Expression> node, boolean isRoot) {
		Expression e = node.getElement();

		if (e instanceof Operand) {
			return Integer.toString(((Operand) e).getValue());
		} else {
			SLLNode<TreeNode<Expression>> children = node.getChildren();
			TreeNode<Expression> left = children.getElement();
			TreeNode<Expression> right = children.getNext().getElement();

			String leftStr = buildInfixString(left, false);
			String rightStr = buildInfixString(right, false);
			String result = leftStr + " " + ((Operator) e).getOperator() + " " + rightStr;
			if (isRoot) {
				return "(" + result + ")";
			} else {
				return "(" + result + ")";
			}
		}
	}
}

