package mx.agent;

import com.sun.tools.attach.*;

import java.io.IOException;
import java.util.List;

/**
 * @program: StuDemo
 * @description:
 * @author:
 * @create: 2020-07-06 17:41
 * @Modified By:
 * @Version: 1.0
 **/
public class TestAgentMain {
    public static void main(String[] args) throws IOException, AttachNotSupportedException,
            AgentLoadException, AgentInitializationException {
        //获取当前系统中所有 运行中的 虚拟机
        System.out.println("running JVM start ");
        List<VirtualMachineDescriptor> list = VirtualMachine.list();
        for (VirtualMachineDescriptor vmd : list) {
            //如果虚拟机的名称为 xxx 则 该虚拟机为目标虚拟机，获取该虚拟机的 pid
            //然后加载 agent.jar 发送给该虚拟机
            System.out.println("vmd.displayName()===="+ vmd.displayName());
            if (vmd.displayName().endsWith("mx.agent.TestAgentMain")) {
                VirtualMachine virtualMachine = VirtualMachine.attach(vmd.id());
                virtualMachine.loadAgent("/Users/maxin/Documents/GitHub/StuDemo/ByteOperate/target/ByteOperate-1.0-SNAPSHOT.jar");
                virtualMachine.detach();
            }
        }
    }
}
