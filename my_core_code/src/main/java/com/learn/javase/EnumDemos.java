package com.learn.javase;

import org.junit.Test;

//枚举的演示
/**
 * JDK1.5引入了新的类型——枚举。在 Java 中它虽然算个“小”功能，却给我的开发带来了“大”方便。
 * 枚举（enum），是指一个经过排序的、被打包成一个单一实体的项列表。一个枚举的实例可以使用枚举项列表中任意单一项的值。
 * 枚举在各个语言当中都有着广泛的应用，通常用来表示诸如颜色、方式、类别、状态等等数目有限、形式离散、表达又极为明确的量。
 * Java从JDK5开始，引入了对枚举的支持。
 * @author Double
 *
 */
public class EnumDemos {

	public static boolean isRed(Color color) {
		if (Color.RED.equals(color)) {
			return true;
		}
		return false;
	}

	public static void showColor(Color color) {
		switch (color) {
		case BLANK:
			System.out.println(color);
			break;
		case RED:
			System.out.println(color);
			break;
		default:
			System.out.println(color);
			break;
		}
	}

	/**
	 *  1、常量的使用、Switch
	 */
	@Test
	public void test1() {
	    System.out.println( isRed( Color.BLANK ) ) ;  //结果： false
	    System.out.println( isRed( Color.RED ) ) ;    //结果： true

	    showColor( Color.RED );
	}

	/**
	 *  2、枚举中的方法
	 */
	@Test
	public void test2() {
		//输出某一枚举的值
        System.out.println( Color1.RED.getName() );
        System.out.println( Color1.RED.getIndex() );
        //遍历所有的枚举
        for( Color1 color : Color1.values()){
            System.out.println( color + "\t name: " + color.getName() +
            					"\t index: " + color.getIndex() );
        }
	}
}

/**
 * 1、常量的使用、Switch
	在JDK1.5之前，我们定义常量都是：public static fianl....。
 	现在好了，有了枚举，可以把相关的常量分组到一个枚举类型里，而且枚举提供了比常量更多的方法。
  	枚举的本质是类
 */
enum Color {
    RED, GREEN, BLANK, YELLOW
}

//所有的枚举都继承自java.lang.Enum类。由于Java 不支持多继承，所以枚举对象不能再继承其他类。
enum Color1 implements Behaviour{

	RED("红色", 1), GREEN("绿色", 2), BLANK("白色", 3), YELLO("黄色", 4);
	// 成员变量
	private String name ;
	private int index ;
	// 构造方法
	private Color1( String name , int index ){
       this.name = name ;
       this.index = index ;
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

	// 普通方法
    public static String getName(int index) {
        for (Color1 c : Color1.values()) {
            if (c.getIndex() == index) {
                return c.name;
            }
        }
        return null;
    }

    //覆盖方法
    @Override
    public String toString() {
        return this.index+"_"+this.name;
    }

	//接口方法
	@Override
	public void print() {
		System.out.println(this.index+":"+this.name);
	}

	//接口方法
	@Override
	public String getInfo() {
		return this.name;
	}
}

//枚举实现接口
interface Behaviour {
    void print();
    String getInfo();
}

//使用接口组织枚举
interface Food {
    enum Coffee implements Food{
        BLACK_COFFEE,DECAF_COFFEE,LATTE,CAPPUCCINO
    }
    enum Dessert implements Food{
        FRUIT, CAKE, GELATO
    }
}