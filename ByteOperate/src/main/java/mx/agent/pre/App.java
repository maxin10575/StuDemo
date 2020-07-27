package mx.agent.pre;

/**
 * @program: StuDemo
 * @description:avaAgent技术
 *
 * JavaAgent是从JDK1.5及以后引入的，在1.5之前无法使用，也可以叫做java代理。利用 java代理，即 java.lang.instrument 做动态 Instrumentation 是 Java SE 5 的新特性，它把 Java 的 instrument 功能从本地代码中解放出来，使之可以用 Java 代码的方式解决问题。
 *
 * 使用 Instrumentation，开发者可以构建一个独立于应用程序的代理程序（Agent），用来监测和协助运行在 JVM 上的程序，甚至能够替换和修改某些类的定义。有了这样的功能，开发者就可以实现更为灵活的运行时虚拟机监控和 Java 类操作了，这样的特性实际上提供了一种虚拟机级别支持的 AOP 实现方式，使得开发者无需对 JDK 做任何升级和改动，就可以实现某些 AOP 的功能了。在 Java SE 6 里面，instrumentation 包被赋予了更强大的功能：启动后的 instrument、本地代码（native code）instrument，以及动态改变 classpath 等等。这些改变，意味着 Java 具有了更强的动态控制、解释能力，它使得 Java 语言变得更加灵活多变。Instrumentation 的最大作用，就是类定义动态改变和操作。
 *
 * 开发者可以在一个普通 Java 程序（带有 main 函数的 Java 类）运行时，通过 -javaagent参数指定一个特定的 jar 文件（包含 Instrumentation 代理）来启动 Instrumentation 的代理程序。开发者可以让 Instrumentation 代理在 main 函数运行前执行premain函数。
 *
 * @author:
 * @create: 2020-07-07 19:44
 * @Modified By:
 * @Version: 1.0
 **/
public class App {
    public static void main(String[] args) {
        System.out.println("main start");
        new TestPre().run();
    }
}
