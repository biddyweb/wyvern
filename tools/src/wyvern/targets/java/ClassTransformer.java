package wyvern.targets.java;

import org.objectweb.asm.*;
import static org.objectweb.asm.Opcodes.*;

import org.objectweb.asm.commons.GeneratorAdapter;
import wyvern.targets.Common.wyvernIL.IL.Def.*;
import wyvern.targets.Common.wyvernIL.IL.visitor.DefVisitor;
import wyvern.targets.java.annotations.Val;
import wyvern.targets.java.bindings.FieldBinding;

public class ClassTransformer implements DefVisitor {
	private GenerationContext ctx;
	private ClassDef classDef;
	private ClassVisitor output;
	private Type self;

	public ClassTransformer(GenerationContext ctx, ClassDef classDef) {
		this.ctx = ctx;
		this.classDef = classDef;
		self = ctx.getType(classDef.getType());
		output = ctx.newClass(classDef.getName());
		output.visit(V1_7, ACC_PUBLIC, classDef.getName(), null, Type.getInternalName(Object.class), new String[]{});
	}

	@Override
	public Object visit(VarDef varDef) {
		Type type = ctx.getType(varDef.getType());
		new FieldBinding(varDef.getName(), type);
		output.visitField(ACC_PUBLIC, varDef.getName(), type.getDescriptor(), null, null);
		return null;
	}

	@Override
	public Object visit(ValDef valDef) {
		Type type = ctx.getType(valDef.getType());
		new FieldBinding(valDef.getName(), type);
		output.visitField(ACC_PRIVATE, valDef.getName(), type.getDescriptor(), null, null);

		String name = "get" + valDef.getName();
		String descriptor = Type.getMethodType(type).getDescriptor();
		MethodVisitor mv = output.visitMethod(ACC_PUBLIC, name, descriptor, null, new String[]{});

		//Generate the val annotation for the getter
		AnnotationVisitor av = mv.visitAnnotation(Type.getDescriptor(Val.class), true);
		av.visit("name", valDef.getName());
		av.visitEnd();

		//Generate the body for the getter
		GeneratorAdapter generator = new GeneratorAdapter(mv, ACC_PUBLIC, name, descriptor);
		generator.loadThis();
		generator.getField(self, valDef.getName(), type);
		generator.returnValue();
		generator.endMethod();

		return null;
	}

	@Override
	public Object visit(Def def) {
		wyvern.tools.types.extensions.Arrow dType = def.getType();
		Type rType = ctx.getType(dType.getResult());

		Type methodType = Type.getMethodType(rType, ctx.getTypes(dType.getArgument()));
		//output.visitMethod()
		return null;
	}

	@Override
	public Object visit(TypeDef typeDef) {
		throw new RuntimeException();
	}

	@Override
	public Object visit(ClassDef classDef) {
		throw new RuntimeException();
	}

	public void finalize() {
		output.visitEnd();
	}
}
