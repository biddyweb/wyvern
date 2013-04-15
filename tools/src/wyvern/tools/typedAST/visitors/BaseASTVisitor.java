package wyvern.tools.typedAST.visitors;

import wyvern.tools.typedAST.Application;
import wyvern.tools.typedAST.Assignment;
import wyvern.tools.typedAST.CoreAST;
import wyvern.tools.typedAST.CoreASTVisitor;
import wyvern.tools.typedAST.Declaration;
import wyvern.tools.typedAST.Invocation;
import wyvern.tools.typedAST.TypedAST;
import wyvern.tools.typedAST.extensions.DeclSequence;
import wyvern.tools.typedAST.extensions.Fn;
import wyvern.tools.typedAST.extensions.LetExpr;
import wyvern.tools.typedAST.extensions.New;
import wyvern.tools.typedAST.extensions.TupleObject;
import wyvern.tools.typedAST.extensions.TypeInstance;
import wyvern.tools.typedAST.extensions.Variable;
import wyvern.tools.typedAST.extensions.declarations.ClassDeclaration;
import wyvern.tools.typedAST.extensions.declarations.MethDeclaration;
import wyvern.tools.typedAST.extensions.declarations.PropDeclaration;
import wyvern.tools.typedAST.extensions.declarations.TypeDeclaration;
import wyvern.tools.typedAST.extensions.declarations.ValDeclaration;
import wyvern.tools.typedAST.extensions.declarations.VarDeclaration;
import wyvern.tools.typedAST.extensions.values.BooleanConstant;
import wyvern.tools.typedAST.extensions.values.IntegerConstant;
import wyvern.tools.typedAST.extensions.values.StringConstant;
import wyvern.tools.typedAST.extensions.values.UnitVal;

public abstract class BaseASTVisitor implements CoreASTVisitor { 
	
	@Override
	public void visit(TupleObject tuple) {
		for (TypedAST ast : tuple.getObjects()) {
			if (!(ast instanceof CoreAST))
				throw new RuntimeException("Something went wrong!");
			((CoreAST)ast).accept(this);
		}
	}
	
	@Override
	public void visit(Fn fn) {
		if (fn.getBody() instanceof CoreAST)
			((CoreAST)fn.getBody()).accept(this);
	}

	@Override
	public void visit(Invocation invocation) {
		TypedAST argument = invocation.getArgument();
		TypedAST receiver = invocation.getReceiver();
		
		if (argument instanceof CoreAST)
			((CoreAST) argument).accept(this);
		if (receiver instanceof CoreAST)
			((CoreAST) receiver).accept(this);
	}

	@Override
	public void visit(Application application) {
		TypedAST argument = application.getArgument();
		TypedAST function = application.getFunction();
		
		if (argument instanceof CoreAST)
			((CoreAST) argument).accept(this);
		if (function instanceof CoreAST)
			((CoreAST) function).accept(this);

	}

	@Override
	public void visit(ValDeclaration valDeclaration) {
		TypedAST definition = valDeclaration.getDefinition();
		
		if (definition instanceof CoreAST)
			((CoreAST) definition).accept(this);
		if (valDeclaration.getNextDecl() != null)
			((CoreAST) valDeclaration.getNextDecl()).accept(this);
	}

	@Override
	public void visit(PropDeclaration propDeclaration) {
	}

	@Override
	public void visit(VarDeclaration varDeclaration) {
	}

	@Override
	public void visit(TypeDeclaration interfaceDeclaration) {
	}

	@Override
	public void visit(TypeInstance typeInstance) {
	}

	@Override
	public void visit(Variable variable) {
	}

	@Override
	public void visit(IntegerConstant booleanConstant) {
	}

	@Override
	public void visit(StringConstant booleanConstant) {
	}

	@Override
	public void visit(BooleanConstant booleanConstant) {
	}

	@Override
	public void visit(UnitVal unitVal) {
	}

	@Override
	public void visit(ClassDeclaration clsDeclaration) {
		DeclSequence decls = clsDeclaration.getDecls();
		
		if (!(decls instanceof CoreAST))
			throw new RuntimeException("All visited elements must implement CoreAST.");
		((CoreAST)decls).accept(this);
	}

	@Override
	public void visit(New new1) {
	}

	@Override
	public void visit(LetExpr let) {
	}

	@Override
	public void visit(MethDeclaration meth) {
		TypedAST body = meth.getBody();
		
		if (!(body instanceof CoreAST))
			throw new RuntimeException("Codegenerated elements must implement CoreAST");
		
		((CoreAST)body).accept(this);
		
		if (meth.getNextDecl() != null)
			((CoreAST) meth.getNextDecl()).accept(this);

	}
	


	@Override
	public void visit(Assignment assignment) {
		TypedAST target = assignment.getTarget();
		TypedAST value = assignment.getValue();
		if (target instanceof CoreAST)
			((CoreAST) target).accept(this);
		if (value instanceof CoreAST)
			((CoreAST) value).accept(this);
		
	}

}