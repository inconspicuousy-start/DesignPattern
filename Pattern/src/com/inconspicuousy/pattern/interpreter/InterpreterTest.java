package com.inconspicuousy.pattern.interpreter;

// 解释器模式（Interpreter Pattern）指的是给定一个表达式，定义它的文法的一种表示，并定义个解释器，使用该解释器来解释表达式。
// 比如，Mybaties、hibernate都有自己的文法，使用者需要编写类似的SQL文法的语句，然后交给ORM框架进行解析然后执行。

// 相关角色
// 1、Abstrat Expression: 抽象表达式解释器， 定义解释器的接口，主要就是声明interpret()方法。
// 2、Terminal Expression： 终结符表达式解释器，解释器接口的实现类，用来实现文法中与终结符相关的操作。
// 终结符表示的是当前的表达式不可被分割， 已经达到了最小可解析程度，比如4则运算符中， 最小可解析程度是表达式不含有一个运算符
// 3、NonTerminal Expression: 非终结符表达式解释器，解释器接口的实现类，用来实现文法中与非终结符相关的操作。非终结符表示就是当前表达式还可以被分割。
// 4、Context： 环境角色，通常包含各个解释器需要的数据或是公共的功能，一般作数据初始化，并且定义处理表达式的方法。

// ===================================================
// 当前我们就以四则简单运算解析，不包含小数点，包括小括号
// 文法:  类似于 a+b-c*d/e * (d+f)
// 注意： 当前我们只是计算，没有对计算表达式作校验， 应该传递满足文法（也就是普通的四则运算表达式）规则的表达式
// 当前终结符就是表达式不包含运算符， 也就是直接可以返回结果了。

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

// 抽象表达式解析器
interface Expression {
    // 对表达式进行解析
    int interpret();
}

// 终止表达式解析器
class TerminalExpression implements Expression {

    private final int value;

    public TerminalExpression(int value) {
        this.value = value;
    }

    @Override
    // 终止表达式解析器此时因为不包含运算符, 直接将结果返回即可
    // 这里只是简答的举个例子, 实际解析器中, 可能有时候需要对结果进行进一步处理.
    public int interpret() {
        return value;
    }
}

// 非终止表达式解析器
class NonTerminalExpression implements Expression {

    private final int left;
    private final int right;
    private final char operator;


    public NonTerminalExpression(int left, int right, char operator) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    @Override
    // 解析方法就是对传过来的数据进行四则运算
    public int interpret() {
        switch (operator) {
            case '+':
                return left + right;
            case '-':
                return left - right;
            case '*':
                return left * right;
            case '/':
                return left / right;
            default:
                throw new UnsupportedOperationException();
        }
    }
}

// 解析器共享数据, 实际就是用来数据的接收与处理. 将解析器整合到该类中, 利用解析器对数据进行处理
class Context {
    private String expression;
    // 将相同的表达式和结果缓存起来， 重复利用
    private static final Map<String, Integer> VALUES = new HashMap<>();

    // 初始化就将表达式传进来
    public Context(String expression) {
        expression = expression.replaceAll("\\s+", "");
        System.out.println("表达式初始值： " + expression);
        this.expression = expression;
    }

    // 开始计算expression
    // 遵循先算括号里面的， 再算乘除， 再算加减
    public int calculator() {
        System.out.println("=================当前处理的表达式为： " + expression);
        handlerBracketOperation();
        return handlerSimpleOperation();
    }

    // 处理带括号的表达式
    private void handlerBracketOperation() {
        // 将带括号的表达式提取出来单独计算
        Pattern pattern = Pattern.compile("\\(([^()]+)\\)");
        while (true) {
            Matcher matcher = pattern.matcher(expression);
            if (!matcher.find()) {
                // 匹配不到符合条件的括号， 结束循环
                break;
            } else {
                // 因为find会影响接下来的操作， 需要重置指针，
                matcher.reset();
            }
            while (matcher.find()) {
                // 匹配到符合条件的表达式（括号里面不含有括号）
                // 匹配到的带括号的表达式
                String bracketExpression = matcher.group(0);
                // 获取到不带括号的表达式
                String group = matcher.group(1);
                if (!VALUES.containsKey(group)) {
                    // 将结果添加到Map集合中
                    VALUES.put(group, new Context(group).calculator());
                }
                // 将表达式中带括号的表达式都替换成计算结果
                expression = expression.replace(bracketExpression, String.valueOf(VALUES.get(group)));
            }
        }
    }

    // 操作不带有括号的表达式
    private int handlerSimpleOperation() {
        // 如果该表达式已经被计算过， 直接返回
        if (VALUES.containsKey(expression)) {
            return VALUES.get(expression);
        }
        while (true) {
            System.out.println("当前无括号的表达式为： " + expression);
            if (expression.matches("\\d+")) {
                // 只含有整数, 利用终止表达式返回结果
                int interpret = new TerminalExpression(Integer.parseInt(expression)).interpret();
                // 把该表达式计算的结果丢到共享集合中
                VALUES.put(expression, interpret);
                return interpret;
            }
            // 先将表达式进行参数提取
            String[] lists = expression.split("[+\\-*/]");
            List<Integer> args = Arrays.stream(lists).map(Integer::parseInt).collect(Collectors.toList());
            // 获取到所有的表达式
            String[] split = expression.split("\\d+");
            List<Character> operators = Arrays.stream(split).filter(s -> !s.isEmpty()).map(s -> s.charAt(0)).collect(Collectors.toList());
            if (operators.size() <= 1) {
                // 利用非终止表达式计算获取到结果
                return new NonTerminalExpression(args.get(0), args.get(1), operators.get(0)).interpret();
            } else {
                // 找到第二个表达式， 判断第二个表达式的类型
                Character character = operators.get(1);
                int left,right;
                if (character == '*' || character == '/') {
                    left = args.get(1);
                    right = args.get(2);
                } else {
                    left = args.get(0);
                    right = args.get(1);
                    character = operators.get(0);
                }
                int interpret = new NonTerminalExpression(left, right, character).interpret();
                // 每次只计算一次就重置表达式, 这里防止*乘号对正则有影响, 采用正则表达式的字面量进行替换
                String regex = "" + left + character + right;
                expression = Pattern.compile(regex, Pattern.LITERAL).matcher(expression).replaceFirst(String.valueOf(interpret));
            }
        }
    }
}


/**
 * 解释器模式
 *
 * @author peng.yi
 */
public class InterpreterTest {
    public static void main(String[] args) {
        String expression = "12 + 2 * 3 / (4 + 5) - 2 * 3 - (8 + 9)";
        int input = 12 + 2 * 3 / (4 + 5) - 2 * 3 - (8 + 9);
        System.out.println(input);
        System.out.println(new Context(expression).calculator());
    }
}
