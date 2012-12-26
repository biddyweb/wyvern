package wyvern.tools.typedAST.visitors;

import wyvern.tools.typedAST.Application;
import wyvern.tools.typedAST.CoreASTVisitor;
import wyvern.tools.typedAST.Invocation;
import wyvern.tools.typedAST.extensions.BooleanConstant;
import wyvern.tools.typedAST.extensions.Fn;
import wyvern.tools.typedAST.extensions.IntegerConstant;
import wyvern.tools.typedAST.extensions.StringConstant;
import wyvern.tools.typedAST.extensions.UnitVal;
import wyvern.tools.typedAST.extensions.ValDeclaration;
import wyvern.tools.typedAST.extensions.Variable;

public class PrintVisitor implements CoreASTVisitor {

	@Override
	public void visit(Fn fn) {
		System.out.println("fn "+fn.getBinding().getName());
	}

	@Override
	public void visit(Invocation invocation) {
		System.out.println("Invoke "+invocation.getOperationName());
	}

	@Override
	public void visit(Application application) {
		System.out.println("Apply "+application.getFunction().toString());
	}

	@Override
	public void visit(ValDeclaration valDeclaration) {
		System.out.println("Val decl "+valDeclaration.toString());

	}

	@Override
	public void visit(IntegerConstant intConst) {
		System.out.println("Int const "+intConst.getValue());
	}

	@Override
	public void visit(StringConstant strConst) {
		System.out.println("Str const "+strConst.getValue());
	}

	@Override
	public void visit(BooleanConstant booleanConstant) {
		System.out.println("Bool const "+booleanConstant.getValue());
	}

	@Override
	public void visit(UnitVal unitVal) {
		System.out.println("unit");
	}

	@Override
	public void visit(Variable variable) {
		System.out.println("var "+variable.getName());
	}

}