package mx.agent.pre;

import javassist.*;

import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;


public class JAgent {
/*    public static void main(String[] args) {
        System.out.println("main");
    }*/
    /**
     * 在这个 premain 函数中，开发者可以进行对类的各种操作。
     * @param agentOps
     *        agentArgs 是 premain 函数得到的程序参数，随同 “– javaagent”一起传入。与 main 函数不同的是，
     *        这个参数是一个字符串而不是一个字符串数组，如果程序参数有多个，程序将自行解析这个字符串。
     * @param inst
     *         是一个 java.lang.instrument.Instrumentation 的实例，
     *         由 JVM 自动传入。java.lang.instrument.Instrumentation 是 instrument 包中定义的一个接口，
     *         也是这个包的核心部分，集中了其中几乎所有的功能方法，例如类定义的转换和操作等等。
     */
    public static void premain(String agentOps, Instrumentation inst) {

        System.out.println("premain===="+agentOps);
        inst.addTransformer(new ClassFileTransformer() {
            @Override
            public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
                                    ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {

                //判断要埋点的类
                if(!"com/chy/JSercice".equals(className)) {
                    return null;
                }

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
                    ctMethod.insertBefore("System.out.println( \"埋点开始-2\" );");
                    ctMethod.insertBefore("System.out.println( \"埋点开始-1\" );");

                    ctMethod.insertAfter("end=System.currentTimeMillis();System.out.println(\"end=\"+end);");
                    ctMethod.insertAfter("System.out.println(\"性能:\"+(end-begin)+\"毫秒\");");

                    //后面插入：最后插入的放最下面
                    ctMethod.insertAfter("System.out.println( \"埋点结束-1\" );");
                    ctMethod.insertAfter("System.out.println( \"埋点结束-2\" );");
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
            }
        });
    }
}