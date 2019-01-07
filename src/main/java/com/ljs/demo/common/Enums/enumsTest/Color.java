package com.ljs.demo.common.Enums.enumsTest;

/**
 * 所有的枚举都继承自java.lang.Enum类。由于Java 不支持多继承，所以枚举对象不能再继承其他类
 */
public enum Color implements Behaviour {

    RED("红色", 1), GREEN("绿色", 2), BLANK("白色", 3), YELLO("黄色", 4);

    // 成员变量
    private String name;
    private int index;

    // 构造方法
    private Color(String name, int index) {
        this.name = name;
        this.index = index;
    }

    // 普通方法
    public static String getName(int index) {
        for (Color c : Color.values()) {
            if (c.getIndex() == index) {
                return c.name;
            }
        }
        return null;
    }

    // get set 方法
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    //覆盖方法  重写toString方法
    @Override
    public String toString() {
        return this.index + "_" + this.name;
    }

    //接口方法
    @Override
    public String getInfo() {
        return this.name;
    }

    //接口方法
    @Override
    public void print() {
        System.out.println(this.index + ":" + this.name);
    }


    public static void main(String[] args) {
        System.out.println(Color.getName(1));

        for (Color color : Color.values()) {
            System.out.println(color.getName() + " " + color.getIndex());
        }

        System.out.println(Color.BLANK.ordinal() + " " + Color.BLANK.toString());
    }
}
