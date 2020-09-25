package com.inconspicuousy.pattern.command;

// 命令模式。 主要是将命令抽象化成对象，将命令的发起者和命令的执行者解耦，两者之间通过命令对象进行交互，这样方便对命令进行管理。
// 常用于将命令保存到队列中，队列依次执行相应的命令
// 简单来说，就是将执行命令抽象成对象进行管理。

import java.util.concurrent.ConcurrentLinkedQueue;

// 命令抽象化
interface Command {
    void execute();
}

// 命令接收者（命令实际的执行者）
abstract class Receiptor {
    // 具体的执行操作
    public abstract void action();
}

// 实际名的命令对象
class ConcreteCommandA implements Command{
    // 实际命令对象，一般命令都会与执行者进行组合依赖
    private final Receiptor receiptor;
    public ConcreteCommandA(Receiptor receiptor) {
        this.receiptor = receiptor;
    }

    @Override
    public void execute() {
        System.out.println("ConcreteCommandA 开始执行命令");
        receiptor.action();
        System.out.println("ConcreteCommandA 命令执行结束");
    }
}

// 实际命令的接收者（执行者）
class ConcreteReceiptor extends Receiptor {
    @Override
    public void action() {
        System.out.println("ConcreteReceiptor 执行命令");
    }
}

// 命令的调用者，负责发起命令
class Invoker {

    // 模拟中间件, 线程安全的链表队列
    private static final ConcurrentLinkedQueue<Command> QUEUE = new ConcurrentLinkedQueue();
    static {
        // 开启一个线程模拟从队列中取出命令并执行
        new Thread(() -> {
            while (true) {
                Command poll = QUEUE.poll();
                if (poll != null) {
                    poll.execute();
                }
            }
        }).start();
    }

    // 指定对应的命令
    private Command command;
    public Invoker(Command command) {
        this.command = command;
    }
    public void setCommand(Command command) {
        this.command = command;
    }
    // 当发起命令时， 将命令存储到队列中
    public void call() {
        QUEUE.add(command);
    }

}

/**
 * 命令模式-代码示例
 * @author peng.yi
 */
public class CommandTest {
    public static void main(String[] args) {
        ConcreteReceiptor concreteReceiptor = new ConcreteReceiptor();
        ConcreteCommandA Command = new ConcreteCommandA(concreteReceiptor);
        Invoker invoker = new Invoker(Command);
        invoker.call();
        // 在创建2个命令
        ConcreteCommandA Command1 = new ConcreteCommandA(concreteReceiptor);
        ConcreteCommandA  Command2= new ConcreteCommandA(concreteReceiptor);
        invoker.setCommand(Command1);
        invoker.call();
        invoker.setCommand(Command2);
        invoker.call();
    }
}
