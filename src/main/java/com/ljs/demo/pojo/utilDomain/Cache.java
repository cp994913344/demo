package com.ljs.demo.pojo.utilDomain;

public class Cache<T> {

    T value;

    public Object getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    /**
     * 与普通的 Object 代替一切类型这样简单粗暴而言，泛型使得数据的类别可以像参数一样由外部传递进来。它提供了一种扩展能力。它更符合面向抽象开发的软件编程宗旨。
     * 当具体的类型确定后，泛型又提供了一种类型检测的机制，只有相匹配的数据才能正常的赋值，否则编译器就不通过。所以说，它是一种类型安全检测机制，一定程度上提高了软件的安全性防止出现低级的失误。
     * 泛型提高了程序代码的可读性，不必要等到运行的时候才去强制转换，在定义或者实例化阶段，因为 Cache<String> 这个类型显化的效果，程序员能够一目了然猜测出代码要操作的数据类型。
     * @param args
     */
    public static void main(String[] args) {
        Cache<String> cache1 = new Cache<String>();
        cache1.setValue("123");
        String value1 = (String) cache1.getValue();
        System.out.println(value1);

        Cache<Integer> cache2 = new Cache<Integer>();
        cache2.setValue(123);
        int value2 = (int) cache2.getValue();
        System.out.println(value2);

    }
}
