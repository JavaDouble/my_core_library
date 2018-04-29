package com.learn.javaee.unit11;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

import org.junit.Test;



/**
 * 演示国际化
 * @author Double
 *
 */
public class I18nDemos {

	/**
	 * 测试Java语言支持的国家和语言
	 */
	@Test
	public void test1() {
		//返回Java所支持的全部国家和语言的数组
		Locale[] localeList = Locale.getAvailableLocales();
		//遍历数组的每个元素，依次获取所支持的国家和语言
		for (int i = 1; i < localeList.length ; i++ )		{
			//打印出所支持的国家和语言
			System.out.println(
			localeList[i].getDisplayCountry() + "=" + localeList[i].getCountry()+ " " +
			localeList[i].getDisplayLanguage()+ "=" + localeList[i].getLanguage());
		}
	}

	/**
	 *
	 */
	@Test
	public void test2() {
        //创建一个本地语言环境对象(中文)
        Locale locale = new Locale("zh", "CN");//中国
        Locale locale1 = Locale.US;//美国
        Locale locale2 = Locale.getDefault();//zh 中国
        System.out.println(locale2.getLanguage());
	}

	/**
	 *	尝试使用ResourceBundle是读取资源文件
	 */
	@Test
	public void test3() {
	    //创建一个本地语言环境对象(中文)
        Locale locale = new Locale("zh", "CN");
        //通过ResourceBundle工具类绑定资源文件(包名.文件名(基本名称不包括语言部分和后缀部分))
        ResourceBundle res = ResourceBundle.getBundle("i18nTest.info", locale);
        String username = res.getString("username");
        System.out.println("username:"+username);
        String password = res.getString("passwd");
        System.out.println("password:"+password);
        String input = res.getString("input");
        System.out.println("input:"+input);
        String success = res.getString("info.success");
        System.out.println("info.success:"+success);
        String error = res.getString("info.error");
        System.out.println("info.error:"+error);

        System.out.println("=================分割线=====================");
	    //创建一个本地语言环境对象(中文)
        Locale locale1 = Locale.US;
        //通过ResourceBundle工具类绑定资源文件(包名.文件名(基本名称不包括语言部分和后缀部分))
        res = ResourceBundle.getBundle("i18nTest.info", locale1);
        username = res.getString("username");
        System.out.println("username:"+username);
        password = res.getString("passwd");
        System.out.println("password:"+password);
        input = res.getString("input");
        System.out.println("input:"+input);
        success = res.getString("info.success");
        System.out.println("info.success:"+success);
        error = res.getString("info.error");
        System.out.println("info.error:"+error);
	}

	/**
	 * 模拟用户登录 国际化
	 */
	@Test
	public void test4() {
	    //创建一个本地语言环境对象(中文)
        Locale locale = new Locale("zh", "CN");
        Locale locale1 = Locale.US;
        Locale locale2 = Locale.getDefault();

        //通过ResourceBundle工具类绑定资源文件(包名.文件名(基本名称不包括语言部分和后缀部分))
        ResourceBundle res = ResourceBundle.getBundle("i18nTest.info", locale);
        //ResourceBundle res = ResourceBundle.getBundle("internationalizationTest.info", locale1);
        //ResourceBundle res = ResourceBundle.getBundle("internationalizationTest.info", locale2);

        //模拟用户登陆
        Scanner in = new Scanner(System.in);

        //从属性文件中根据key获取value值
        String input = res.getString("input");
        String username = res.getString("username");
        String passwd = res.getString("passwd");
        String infoSuccess = res.getString("info.success");
        String infoError = res.getString("info.error");
        System.out.println(input + username);
        String userName = in.next();
        System.out.println(input + passwd) ;
        String password = in.next();

        if("admin".equals(userName) && "123".equals(password)){
            //处理动态文本(模式， 要替换的值……)
            String success = MessageFormat.format(infoSuccess, userName);
            System.out.println(success);
        }else {
            System.out.println(infoError);
        }
        in.close();
	}

	@Test
	public void test5() {
        ResourceBundle res1 = ResourceBundle.getBundle("i18nTest.info", Locale.CHINA);
        String username1 = res1.getString("username");
        System.out.println(username1);

        ResourceBundle res = ResourceBundle.getBundle("i18nTest.login", Locale.CHINA);
        String username = res.getString("username");
        String password = res.getString("password");
        String submit=res.getString("submit");
        System.out.println("username:"+username+",password:"+password+",submit:"+submit);


	}
}
