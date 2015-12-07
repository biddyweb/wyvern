package wyvern.target.corewyvernIL.astvisitor;

import wyvern.target.corewyvernIL.Environment;
import wyvern.target.corewyvernIL.FormalArg;
import wyvern.target.corewyvernIL.decl.DefDeclaration;
import wyvern.target.corewyvernIL.decl.DelegateDeclaration;
import wyvern.target.corewyvernIL.decl.ValDeclaration;
import wyvern.target.corewyvernIL.decl.VarDeclaration;
import wyvern.target.corewyvernIL.decltype.AbstractTypeMember;
import wyvern.target.corewyvernIL.decltype.DefDeclType;
import wyvern.target.corewyvernIL.decltype.ValDeclType;
import wyvern.target.corewyvernIL.decltype.VarDeclType;
import wyvern.target.corewyvernIL.expression.Cast;
import wyvern.target.corewyvernIL.expression.FieldGet;
import wyvern.target.corewyvernIL.expression.FieldSet;
import wyvern.target.corewyvernIL.expression.IntegerLiteral;
import wyvern.target.corewyvernIL.expression.Let;
import wyvern.target.corewyvernIL.expression.Match;
import wyvern.target.corewyvernIL.expression.MethodCall;
import wyvern.target.corewyvernIL.expression.New;
import wyvern.target.corewyvernIL.expression.RationalLiteral;
import wyvern.target.corewyvernIL.expression.StringLiteral;
import wyvern.target.corewyvernIL.expression.Variable;
import wyvern.target.corewyvernIL.type.NominalType;
import wyvern.target.corewyvernIL.type.StructuralType;
import wyvern.target.oir.OIREnvironment;

public abstract class ASTVisitor<T> {
	public abstract T visit(Environment env, OIREnvironment oirenv, New newExpr);
	public abstract T visit(Environment env, OIREnvironment oirenv, MethodCall methodCall);
	public abstract T visit(Environment env, OIREnvironment oirenv, Match match);
	public abstract T visit(Environment env, OIREnvironment oirenv, FieldGet fieldGet);
	public abstract T visit(Environment env, OIREnvironment oirenv, Let let);
	public abstract T visit(Environment env, OIREnvironment oirenv, FieldSet fieldSet);
	public abstract T visit(Environment env, OIREnvironment oirenv, Variable variable);
	public abstract T visit(Environment env, OIREnvironment oirenv, Cast cast);
	public abstract T visit(Environment env, OIREnvironment oirenv, VarDeclaration varDecl);
	public abstract T visit(Environment env, OIREnvironment oirenv, DefDeclaration defDecl);
	public abstract T visit(Environment env, OIREnvironment oirenv, ValDeclaration valDecl);
	public abstract T visit(Environment env, OIREnvironment oirenv, IntegerLiteral integerLiteral);
	public abstract T visit(Environment env, OIREnvironment oirenv, RationalLiteral rational);
	public abstract T visit(Environment env, OIREnvironment oirenv, FormalArg formalArg);
	public abstract T visit(Environment env, OIREnvironment oirenv, VarDeclType varDeclType);
	public abstract T visit(Environment env, OIREnvironment oirenv, ValDeclType valDeclType);
	//public abstract T visit(Environment env, OIREnvironment oirenv, DependentType dependentType);
	public abstract T visit(Environment env, OIREnvironment oirenv, DefDeclType defDeclType);
	public abstract T visit(Environment env, OIREnvironment oirenv, AbstractTypeMember abstractDeclType);
	public abstract T visit(Environment env, OIREnvironment oirenv, NominalType nominalType);
	public abstract T visit(Environment env, OIREnvironment oirenv, StructuralType structuralType);
	public abstract T visit(Environment env, OIREnvironment oirenv, StringLiteral stringLiteral);
	public abstract T visit(Environment env, OIREnvironment oirenv, DelegateDeclaration delegateDecl);
}