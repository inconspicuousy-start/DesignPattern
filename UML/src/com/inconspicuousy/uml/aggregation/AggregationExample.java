package com.inconspicuousy.uml.aggregation;

class Computer {
    private Mouse mouse;
    private KeyBoard keyBoard;

    public void setMouse(Mouse mouse) {
        this.mouse = mouse;
    }

    public void setKeyBoard(KeyBoard keyBoard) {
        this.keyBoard = keyBoard;
    }
}
class Mouse {}
class KeyBoard {}

/**
 * 聚合关系
 * @author peng.yi
 */
public class AggregationExample {
}
