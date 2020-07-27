package mx.agent.pre;

import javassist.*;

import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;


/**
 * @program: StuDemo
 * @description:
 * @author:
 * @create: 2020-07-06 15:27
 * @Modified By:
 * @Version: 1.0
 **/
public class PreMainTraceAgent {
    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("agentArgs : " + agentArgs);
        inst.addTransformer(new DefineTransformer(), true);
    }

    static class DefineTransformer implements ClassFileTransformer {
        @Override
        public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain
                protectionDomain,byte[] classfileBuffer) throws IllegalClassFormatException {

            //判断要埋点的类
            if(!"mx/agent/pre/TestPre".equals(className)) {
                return null;
            }
            System.out.println("premain load Class==:" + className);
            try {
                ClassPool classPool=new ClassPool();
                classPool.insertClassPath(new LoaderClassPath(loader));
                CtClass ctClass= classPool.get(className.replace("/","."));
                CtMethod ctMethod= ctClass.getDeclaredMethod("run");

                //插入本地变量
                ctMethod.addLocalVariable("begin",CtClass.longType);
                ctMethod.addLocalVariable("end",CtClass.longType);

                ctMethod.insertBefore("begin=System.currentTimeMillis();System.out.println(\"begin=\"+begin);");
                //前面插入：最后插入的放最上面
                ctMethod.insertBefore("System.out.println( \"====埋点开始====\" );");

                ctMethod.insertAfter("end=System.currentTimeMillis();System.out.println(\"end=\"+end);");
                ctMethod.insertAfter("System.out.println(\"性能:\"+(end-begin)+\"毫秒\");");

                //后面插入：最后插入的放最下面
                ctMethod.insertAfter("System.out.println( \"====埋点结束====\" );");
                return ctClass.toBytecode();
            } catch (NotFoundException e) {
                e.printStackTrace();
            } catch (CannotCompileException e) {
                e.printStackTrace();
            }
            catch (IOException e){
                e.printStackTrace();
            }

            return new byte[0];
//            return classfileBuffer;
        }
    }
}
