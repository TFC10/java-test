package chapter02;

public class Abstract {
    public static void main(String[] args) {
//        抽象类：不完整的类
//        abstract class 类名
//        因为类不明显，所以无法构建对象
//        抽象方法：只有声明，没有实现的方法
//        abstract 返回值类型 方法名（参数）
//        如果一个类含有抽象方法，那么一定是抽象类
//        抽象类无法直接构建对象。但是可以通过子类间接构建对象
//        如果抽象类中含有抽象方法，那么子类继承抽象类，需要将抽象方法重写，补充完整
//        abstract关键字不能和final一起使用
    }
}
abstract class Personal1 {
    public abstract void eat();
    public void test() {

    }
}
class Chinese extends Personal1 {
    public void eat(){
        System.out.println("中国人吃饭使用筷子");
    }
}