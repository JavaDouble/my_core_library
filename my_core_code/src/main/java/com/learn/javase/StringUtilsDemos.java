package com.learn.javase;

import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

//StringUtils类常用方法的演示
/**
 *  commons-lang3是Apache Commons 项目中的一个组件，
 *  commons-lang3针对JDK官方的java.lang包中的类提供了一些实用的补充，在实践中应用很广。
 *  StringUtils是commons-lang3包中提供的，针对字符串操作的工具类，提供了一系列静态实用方法
 *
 * @author Double
 *
 */
public class StringUtilsDemos {
	/*
	 * repeat(String str,int repeat) 重复字符串若干次
	 */
	@Test
	public void test1() {
		System.gc();
		String str="f";
		StringUtils su = new StringUtils();
		System.out.println(su.repeat(str,10));
	}

	/*
	 * 空字符串检查 boolean isBlank(testString)
	 * 当testString为空,长度为零或者仅由空白字符(whitespace)组成时,返回True;否则返回False
	 *
	 * boolean isNotBlank(testString)的功能与StringUtils.isBlank(testString)相反.
	 */
	@Test
	public void test2() {
		String test = "";
	    String test2 = "\n\n\t";
	    String test3 = null;
	    String test4 = "Test";

	    System.out.println( "test blank? " + StringUtils.isBlank( test ) );
	    //test blank? true
	    System.out.println( "test2 blank? " + StringUtils.isBlank( test2 ) );
	    //test2 blank? true
	    System.out.println( "test3 blank? " + StringUtils.isBlank( test3 ) );
	    //test3 blank? true
	    System.out.println( "test4 blank? " + StringUtils.isBlank( test4 ) );
	    //test4 blank? false
	}

	/*
	 * 清除空白字符 String trimToNull(testString)
	 * 清除掉testString首尾的空白字符,如果仅testString全由空白字符(whitespace)组成则返回null
	 *
	 * 注意：trim(testString)与trimToNull(testString)功能类似，
	 * 但testString由空白字符(whitespace)组成时返回零长度字符串。
	 */
	@Test
	public void test3() {
		String test1 = "\t";
		String test2 = "  A  Test  ";
		String test3 = null;
		String test4="";
		String test5=" ";

		System.out.println("test1 trimToNull: " + StringUtils.trimToNull(test1));
		//test1 trimToNull: null
		System.out.println("test2 trimToNull: " + StringUtils.trimToNull(test2));
		//test2 trimToNull: A  Test
		System.out.println("test3 trimToNull: " + StringUtils.trimToNull(test3));
		//test3 trimToNull: null
		System.out.println("test4:"+StringUtils.trimToNull(test4));//test4:null
		System.out.println("test5:"+StringUtils.trimToNull(test5));//test5:null
	}

	/*
	 * StringUtils类在操作字符串时，即使操作的为null值也是安全的，不会报NullPointerException
	 * 在操作字符串时使用StringUtils相比使用原生的String会更加安全。
	 *
	 * 一、判空:StringUtils中判断字符串是否为空的方法主要有以下几个：
	    1）boolean StringUtils.isBlank(String str)
	    2）boolean StringUtils.isEmpty(String str)
	    3）boolean StringUtils.isNotBlank(String str)
	    4）boolean StringUtils.isNotEmpty(String str)
	    5）boolean StringUtils.isAnyBlank(CharSequence… css)
	    6）boolean StringUtils.isAnyEmpty(CharSequence… css)
	    7）boolean StringUtils.isNoneBlank(CharSequence… css)
	    8）boolean StringUtils.isNoneEmpty(CharSequence… css)
	    9）boolean StringUtils.isWhitespace(CharSequence cs)
	 */
	@Test
	public void test4() {
		StringUtils.isBlank(""); // true
	    StringUtils.isBlank(" "); // true
	    StringUtils.isBlank("     "); // true
	    StringUtils.isBlank("\t"); // true
	    StringUtils.isBlank("\r"); // true
	    StringUtils.isBlank("\n"); // true
	    StringUtils.isBlank(null); // true

	    StringUtils.isEmpty(""); // true
	    StringUtils.isEmpty(" "); // false
	    StringUtils.isEmpty("     "); // false
	    StringUtils.isEmpty("\t"); // false
	    StringUtils.isEmpty("\r"); // false
	    StringUtils.isEmpty("\n"); // false
	    StringUtils.isEmpty(null); // true

	    StringUtils.isWhitespace(""); // true
	    StringUtils.isWhitespace(" "); // true
	    StringUtils.isWhitespace("    "); // true
	    StringUtils.isWhitespace("\t"); // true
	    StringUtils.isWhitespace("\r"); // true
	    StringUtils.isWhitespace("\n"); // true
	    StringUtils.isWhitespace(null); // false
	    /*
	     * blank：代表的是空串("")、空白符(空格""，" "，制表符"\t"，回车符"\r"，"\n"等)以及null值；
	     * empty：代表的是空串("")和null值，不包含空白符；whitespace：包含空串("")和空白符，不包含null值.
	     *
	     *  isAnyBlank，isNoneBlank，isAnyEmpty，isNoneEmpty四个方法是用于判断多个字符串是否为空；
	     *  对于isAnyBlank和isAnyEmpty来说，只要有一个字符串为空，结果即为true；
	     *  对于isNoneBlank和isNoneEmpty，只要存在一个字符串为空，结果即为false
	     */
	    StringUtils.isAnyBlank("titanic", "jack", "rose"); // false
	    StringUtils.isAnyBlank("", "jack", "rose"); // true
	    StringUtils.isAnyBlank(" ", "jack", "rose"); // true
	    StringUtils.isAnyBlank(null, "jack", "rose"); // true

	    StringUtils.isAnyEmpty("titanic", "jack", "rose"); // false
	    StringUtils.isAnyEmpty("", "jack", "rose"); // true
	    StringUtils.isAnyEmpty(" ", "jack", "rose"); // false
	    StringUtils.isAnyEmpty(null, "jack", "rose"); // true

	    StringUtils.isNoneBlank("titanic", "jack", "rose"); // true
	    StringUtils.isNoneBlank("", "jack", "rose"); // false
	    StringUtils.isNoneBlank(" ", "jack", "rose"); // false
	    StringUtils.isNoneBlank(null, "jack", "rose"); // false

	    StringUtils.isNoneEmpty("titanic", "jack", "rose"); // true
	    StringUtils.isNoneEmpty("", "jack", "rose"); // false
	    StringUtils.isNoneEmpty(" ", "jack", "rose"); // true
	    StringUtils.isNoneEmpty(null, "jack", "rose"); // false
	}

	/*
	 * 二、转换  StringUtils中涉及大小写转换以及判断字符串大小写的方法
	 *  1）StringUtils.capitalize(String str)
	    2）StringUtils.uncapitalize(String str)
	    3）StringUtils.upperCase(String str)
	    4）StringUtils.upperCase(String str,Locale locale)
	    5）StringUtils.lowerCase(String str)
	    6）StringUtils.lowerCase(String str,Locale locale)
	    7）StringUtils.swapCase(String str)
	    8）StringUtils.isAllUpperCase(CharSequence cs)
	    9）StringUtils.isAllLowerCase(CharSequence cs)
	 */
	@Test
	public void test5() {
	    //（1）字符串首字母大小写转换
		StringUtils.capitalize(null); // null (注意此处不会报异常)
	    StringUtils.capitalize("china"); // China (首字母转大写)
	    StringUtils.uncapitalize(null); // null
	    StringUtils.uncapitalize("CHINA"); // cHINA (首字母转小写)

	    //（2）字符串整体大小写转换
	    StringUtils.upperCase(null); // null
	    StringUtils.upperCase("china"); // CHINA (全部转为大写)
	    StringUtils.upperCase("china", Locale.ENGLISH); // CHINA (按照指定规则转换为大写)
	    StringUtils.lowerCase(null); // null
	    StringUtils.lowerCase("CHINA"); // china (全部转换为小写)
	    StringUtils.lowerCase("CHINA", Locale.ENGLISH); // china (按照指定转换规则转换为小写)

	    //3）字符串大小写互换
	    StringUtils.swapCase(null); // null
	    StringUtils.swapCase("chINA"); // CHina

	    //（4）判断字符串是否全部是大写或小写(空或空白符均为false)
	    StringUtils.isAllUpperCase(null); // false
	    StringUtils.isAllUpperCase(""); // false
	    StringUtils.isAllUpperCase(" "); // false
	    StringUtils.isAllUpperCase("CHINA"); // true
	    StringUtils.isAllLowerCase(null); // false
	    StringUtils.isAllLowerCase(""); // false
	    StringUtils.isAllLowerCase(" "); // false
	    StringUtils.isAllLowerCase("china"); // true
	}

	/*
	 * 三、移除 从字符串中移除匹配的字符或字符序列，如果要移除的字符或字符序列在字符串中不存在，即无匹配，则不进行移除.
	 *  1）StringUtils.remove(String str, char remove)
	    2）StringUtils.remove(String str, String remove)
	    3）StringUtils.removeStart(String str, String remove)
	    4）StringUtils.removeStartIgnoreCase(String str, String remove)
	    5）StringUtils.removeEnd(String str, String remove)
	    6）StringUtils.removeEndIgnoreCase(String str, String remove)
	    7）StringUtils.deleteWhitespace(String str)
	 */
	@Test
	public void test6() {
		//（1）移除单个字符
	    StringUtils.remove(null, 'a'); // null (注意此处及下一行为null)
	    StringUtils.remove("china", null); // china
	    StringUtils.remove("china", 'i'); // chna
	    StringUtils.remove("china", 'b'); // china (如果要移除的字符不存在，则返回原字符串)

		//（2）移除指定字符序列
	    StringUtils.remove("china", "in"); // cha
	    StringUtils.remove("china", "nin"); // china

		//（3）移除开头匹配的字符序列
	    StringUtils.removeStart("china", "ch"); // ina
	    StringUtils.removeStartIgnoreCase("china", "CHI"); // na (忽略大小写)

	    //（4）移除结尾匹配的字符序列
	    StringUtils.removeEnd("china", "na"); // chi
	    StringUtils.removeEndIgnoreCase("china", "NA"); // chi (忽略大小写)

	    //（5）移除空白字符
	    StringUtils.deleteWhitespace(null); //null
	    StringUtils.deleteWhitespace(" c h  i\tn\ra"); // china
	}

	/*
	 * 四、替换  StringUtils中常用的替换方法有如下几种
	 *  1）replace(String text, String searchString, String replacement)
	    2）replace(String text, String searchString, String replacement, int max)
	    3）replaceChars(String str, char searchChar, char replaceChar)
	    4）replaceChars(String str, String searchChars, String replaceChars)
	    5）replaceOnce(String text, String searchString, String replacement)
	    6）overlay(String str,String overlay,int start,int end)
	    7）replaceEach(String text, String[] searchList, String[] replacementList)
	    8）replaceEachRepeatedly(String text, String[] searchList, String[]replacementList)

	      需要注意的是，若被替换的字符串为null，或者被替换的字符或字符序列为null，又或者替换的字符或字符序列为null，
	    	那么此次替换都会被忽略，返回原字符串
	 */
	@Test
	public void test7() {

		//（1）替换单个字符或字符序列 （a）replace方法 replace方法可以替换单个字符序列
	    StringUtils.replace("china", null, "z");
	    // china (此处被替换字符序列为null，因此替换会被忽略，返回原字符串)
	    StringUtils.replace("china", "c", null);
	    // china (此处替换字符序列为null，因此替换也被忽略，返回原字符串)
	    StringUtils.replace("china", "a", "ese"); // chinese
	    StringUtils.replace("china", "a", ""); // chin

	    // replace方法还可以指定最大替换的个数
	    StringUtils.replace("aabaaa", "a", "z", 0); // aabaaa (0表示替换的个数为0，也就是不替换)
	    StringUtils.replace("aabaaa", "a", "z", 1); // zabaaa (1表示最多替换1个)
	    StringUtils.replace("aabaaa", "a", "z", 2); // zzbaaa (2表示最多替换2个)
	    StringUtils.replace("aabaaa", "a", "z", 3); // zzbzaa (3表示最多替换3个)
	    StringUtils.replace("aabaaa", "a", "z", -1); // zzbzaa (-1表示全部替换)

	    //（b）replaceChars方法  replaceChars方法可以替换单个字符或者单个字符序列
	    StringUtils.replaceChars("china", 'a', 'z'); // chinz
	    StringUtils.replaceChars("china", "a", "z"); // chinz

	    //（c）replaceOnce方法
	    //replaceOnce方法只会替换一次，也就是只会替换第一个要替换的字符序列，后面即使有匹配的字符序列也不会被替换
	    StringUtils.replaceOnce("abaa", "a", "z"); // zbaa

	    //（d）overlay方法 overlay(String str,String overlay,int start,int end)
	    //方法可以在指定位置进行字符序列替换，从start索引处开始(包含)到end-1索引处为止进行替换
	    StringUtils.overlay("abcdef", "zzzz", 2, 4); // abzzzzef

	    //这里有一些特殊情况：1）起始索引start小于结束索引end，这时会将end作为起始索引，start作为结束索引
	    StringUtils.overlay("abcdef", "zzzz", 4, 2); // abzzzzef
	    StringUtils.overlay("abcdef", "zzzz", 4, 3); // abczzzzef
	    StringUtils.overlay("abcdef", "zzzz", 4, 4); // abcdzzzzef
	    StringUtils.overlay("abcdef", "zzzz", 4, 5); // abcdzzzzf

	    //2）起始索引start为负数，这时start会被当作0处理
	    StringUtils.overlay("abcdef", "zzzz", -1, 2); // abcdzz
	    StringUtils.overlay("abcdef", "zzzz", -2, -3); // zzzzabcdef

	    //3）结束索引end大于原始字符串的长度，这时end会被当作原始字符串长度处理
	    StringUtils.overlay("abcdef", "zzzz", 8, 10); // abcdefzzzz

	    //（2）同时替换多个字符序列	（a）replaceEach方法
	    //replaceEach(String text, String[] searchList, String[] replacementList)
	    //方法可以同时替换多个字符序列，但被替换和替换的字符序列的个数应该对应，否则会报IllegalArgumentException
	    StringUtils.replaceEach
	    	("china", new String[] { "ch", "a" }, new String[] { "x", "z" });
	    	// xhinz (将ch和a分别替换为x和z)
	    StringUtils.replaceEach("china", null, new String[] { "x", "z" });
	    // china (存在null，不进行替换)
	    StringUtils.replaceEach("china", new String[] { "ch", "a" }, new String[]
	    		{ "x", "z", "y" }); // IllegalArgumentException (被替换和替换的个数不对应)

	    //（b）replaceEachRepeatedly方法
	    // replaceEachRepeatedly(String text, String[] searchList, String[]
	    //replacementList)方法可以循环进行替换
	    StringUtils.replaceEachRepeatedly("china", new String[] { "c", "x" },
	    		  new String[] { "x", "z" }); // zhina (c被替换为x，x又被替换为z)
	    //但如果替换是一个死循环，则会报IllegalStateException
	       StringUtils.replaceEachRepeatedly("china", new String[] { "c", "x" },
	    		   new String[] { "x", "c" });
	    		   // IllegalStateException (c被替换为x，x又被替换为c)
	}

	/*
	 * 五、反转  StringUtils中有关反转的方法如下：
	    1）reverse(String str)
	    2）reverseDelimited(String str, char separatorChar)
	 */
	@Test
	public void test8() {
		//（1）简单反转		  reverse(String str)方法
		StringUtils.reverse("china"); // anihc

		//（2）根据指定分隔符进行反转，分隔符之间的字符不进行反转
		StringUtils.reverseDelimited("china", ','); // china
		StringUtils.reverseDelimited("cxhinxa", 'x'); // axhinxz
		StringUtils.reverseDelimited("c.hin.a", '.'); // a.hin.c
		StringUtils.reverseDelimited("c.hina", '.'); // hina.c
	}

	/*
	 * 六、截取  StringUtils中常用的截取字符串的方法如下：
		substring(String str,int start)
		substring(String str,int start, int end)
		substringAfter(String str,String separator)
		substringAfterLast(String str,String separator)
		substringBefore(String str,String separator)
		substringBeforeLast(String str,String separator)
		substringBetween(String str,String tag)

		需要注意的是，截取字符串时，若被截取的字符串为null或""，则截取之后的返回的字符串也为null和""。
	 */
	@Test
	public void test9() {
		/*
		 *（1）根据指定位置截取字符串，当指定的截取位置为非负数时，则从左往右开始截取，第一位为0，后面依次类推，
		 * 但当索引值为负数时，则从右往左截取，注意此时右侧第一位为-1：
		 * a）只指定了起始位置，则截取至字符串末尾：
		 */
		StringUtils.substring(null, 2); // "" null和""截取后都返回null和""
		StringUtils.substring(null, 2); // null
		StringUtils.substring("china", 0); // china 指定的起始截取位置为0，则从第一位开始截取，也就是不截取
		StringUtils.substring("china", 2); // ina 指定的截取位置为2，则从左往右第三位开始截取
		StringUtils.substring("china", -2); // na 指定的截取位置为-2，则从右往左第二位开始截取

		//b）指定了起始位置和结束位置，则从起始位置开始截取到结束位置（但不包含结束位置）：
		StringUtils.substring(null, 2, 4); // null null和""截取后都返回null和""
		StringUtils.substring("", 2, 4); // ""
		StringUtils.substring("china", 0, 0); // ""
		StringUtils.substring("china", 2, 4); // in
		StringUtils.substring("china", -2, -4); // in
		StringUtils.substring("china", 2, -3); // ""
		StringUtils.substring("china", 2, -1); // in

		//（2）根据指定的分隔符进行截取（不包含该分隔符）：  a）从分隔符第一次出现的位置向后截取：
		StringUtils.substringAfter("china", "i"); // na 从第一次出现"i"的位置向后截取，不包含第一次出现的"i"
		StringUtils.substringAfter("china", "hi"); // na
		StringUtils.substringAfter("chinachina","h"); // inachina
		StringUtils.substringAfter("china", "a"); // ""
		StringUtils.substringAfter("china", "d"); // "" 分隔符在要截取的字符串中不存在，则返回""
		StringUtils.substringAfter("china", ""); // china 分隔符为""，则返回原字符串
		StringUtils.substringAfter("china", null); // "" 分隔符为null，则返回""

		//b）从分隔符最后一次出现的位置向后截取：
		StringUtils.substringAfterLast("china", "i"); // na
		StringUtils.substringAfterLast("chinachina", "i"); // na "i"最后出现的位置向后截取

		// c）从分隔符第一次出现的位置向前截取：
		StringUtils.substringBefore("china", "i"); // ch
		StringUtils.substringBefore("chinachina", "i"); // ch 从"i"第一次出现的位置向前截取

		//d）从分隔符最后一次出现的位置向前截取：
		StringUtils.substringBeforeLast("china", "i");
		StringUtils.substringBeforeLast("chinachina", "i"); // chinach

		//e）截取指定标记字符串之间的字符序列：
		StringUtils.substringBetween(null, "ch"); // null
		StringUtils.substringBetween("", ""); // ""
		StringUtils.substringBetween("tagabctag", ""); // "" 标记字符串为""，则截取后返回""
		StringUtils.substringBetween("", "tag"); // null // 注意此处返回的是null
		StringUtils.substringBetween("tagabctag", null);
		// null 标记字符串为null，则截取后返回null
		StringUtils.substringBetween("tagabctag", "tag"); // "abc"
	}

	/*
	 * 七、去除空白：去除字符串中的空白符是我们在处理字符串时经常遇到的问题，
	 * 			StringUtils中也封装了一些非常好用的方法来帮助我们解决这个问题：
	 * 	trim(String str)
		trimToEmpty(String str)
		trimToNull(String str)
		strip(String str)
		stripToEmpty(String str)
		stripToNull(String str)
		deleteWhitespace(String str)
	 */
	@Test
	public void test10() {
	/*
	 *  （1）去除字符串首尾的控制符（char ≤ 32）
	    a）trim(String str)：如果被去除的字符串的为null或""，则返回null和"":
	 */
		StringUtils.trim(null); // null
		StringUtils.trim(""); // ""
		StringUtils.trim("     ");// ""
		StringUtils.trim("abc"); // abc
		StringUtils.trim("    abc    "); // abc
		StringUtils.trim(" a b c "); // "a b c" 注意此处字符串内部的控制符是不去除的

		// b）trimToEmpty(String str)：如果被去除的字符串的为null或""，则都返回"
		StringUtils.trimToEmpty(null); // "" 此处返回的是""
		StringUtils.trimToEmpty(""); // ""
		StringUtils.trimToEmpty("     ");// ""
		StringUtils.trimToEmpty("abc"); // abc
		StringUtils.trimToEmpty("    abc    "); // abc
		StringUtils.trimToEmpty(" a b c "); // a b c

		//c）trimToNull(String str)：如果被去除的字符串的为null或""，则都返回null:
		StringUtils.trimToNull(null); // null
		StringUtils.trimToNull(""); // null
		StringUtils.trimToNull("     ");// null
		StringUtils.trimToNull("abc"); // abc
		StringUtils.trimToNull(" \t\r\nabc    "); // abc
		StringUtils.trimToNull(" a b c "); // "a b c"

		/*
		 * （2）去除字符串首尾的空白符(空白符主要包括' '，'\t'，'\r'，'\n'等等，
		 * 具体的空白符可以参考Java API中Character类中isWhiteSpace()方法中的描述)：
		 * a）trim(String str)：如果被去除的字符串的为null或""，则返回null和"":
		 */
		StringUtils.strip(null); // null
		StringUtils.strip(""); // ""
		StringUtils.strip("     ");// ""
		StringUtils.strip("abc"); // abc
		StringUtils.strip(" \t\r\n abc    "); // abc
		StringUtils.strip(" a b c "); // a b c

		// b）trimToEmpty(String str)：如果被去除的字符串的为null或""，则都返回"":
		StringUtils.stripToEmpty(null); // null
		StringUtils.stripToEmpty(""); // nulld
		StringUtils.stripToEmpty("     ");// null
		StringUtils.stripToEmpty("abc"); // abc
		StringUtils.stripToEmpty(" \t\r\n abc    "); // abc
		StringUtils.stripToEmpty(" a b c "); // "a b c"

		//c）trimToNull(String str)：如果被去除的字符串的为null或""，则都返回null:
		StringUtils.stripToNull(null); // null
		StringUtils.stripToNull(""); // nulld
		StringUtils.stripToNull("     ");// null
		StringUtils.stripToNull("abc"); // abc
		StringUtils.stripToNull(" \t\r\n abc    "); // abc
		StringUtils.stripToNull(" a b c "); // "a b c"

		//（2）去除字符串中所有的空白符：
		StringUtils.deleteWhitespace(null); // null
		StringUtils.deleteWhitespace(""); // ""
		StringUtils.deleteWhitespace("abc"); // "abc"
		StringUtils.deleteWhitespace("   ab  c  "); // "abc"
	}

	/*
	 * 八、包含：  StringUtils中判断是否包含的方法主要有：
	 * 	contains(CharSequence seq, int searchChar)
		contains(CharSequence seq, CharSequence searchSeq)
		containsIgnoreCase(CharSequence str, CharSequence searchStr)
		containsAny(CharSequence cs, char... searchChars)
		containsAny(CharSequence cs, CharSequence searchChars)
		containsOnly(CharSequence cs,char… valid)
		containsOnly(CharSequence cs, String validChars)
		containsNone(CharSequence cs,char… searchChars)
		containsNone(CharSequence cs, String invalidChars)
		startsWith(CharSequence str,CharSequence prefix)
		startsWithIgnoreCase(CharSequence str,CharSequence prefix)
		startsWithAny(CharSequence string,CharSequence… searchStrings)
	 */
	@Test
	public void test11() {
		/*
		 *  （1）判断字符串中是否包含指定的字符或字符序列：
   			 a）区分大小写：
		 */
		StringUtils.contains(null, 'a'); // false
		StringUtils.contains("china", null); // false
		StringUtils.contains("", 'a'); // false
		StringUtils.contains("china", 'a');// true
		StringUtils.contains("china", 'z');//false
		StringUtils.contains(null, "a"); // false
		StringUtils.contains("china", null); // false
		StringUtils.contains("", ""); // true
		StringUtils.contains("abc", "");// true
		StringUtils.contains("china", "na");// true
		StringUtils.contains("abc", "z"); // false

		// b）不区分大小写：
		System.out.println(StringUtils.containsIgnoreCase("china", "a"));
		StringUtils.containsIgnoreCase("china", "a");// true
		StringUtils.containsIgnoreCase("china", "A");// true
		StringUtils.containsIgnoreCase("china", "Z");//false
		StringUtils.containsIgnoreCase(null, "A"); // false
		StringUtils.containsIgnoreCase("china", null); // false
		StringUtils.containsIgnoreCase("", ""); // true
		StringUtils.containsIgnoreCase("abc", "");// true
		StringUtils.containsIgnoreCase("china", "na");// true
		StringUtils.containsIgnoreCase("china", "Na");// true
		StringUtils.containsIgnoreCase("abc", "Z"); // false

		//（2）判断字符串中是否包含指定字符集合中或指定字符串中任一字符，区分大小写：
		StringUtils.containsAny(null, 'a', 'b');// false
		StringUtils.containsAny("", 'a', 'b');// false
		StringUtils.containsAny("abc", 'a', 'z');// true
		StringUtils.containsAny("abc", 'x', 'y');// false
		StringUtils.containsAny("abc", 'A', 'z');// false
		StringUtils.containsAny(null, "a");// false
		StringUtils.containsAny("", "a");// false
		StringUtils.containsAny("abc", "ab");// true
		StringUtils.containsAny("abc", "ax");// true
		StringUtils.containsAny("abc", "xy");// false
		StringUtils.containsAny("abc", "Ax");// false

		// （3）判断字符串中是否不包含指定的字符或指定的字符串中的字符，区分大小写：
		StringUtils.containsNone(null, 'a'); // true
		StringUtils.containsNone("", 'a'); // true 注意这里，空串总是返回true
		StringUtils.containsNone("china", ' '); // true 注意包含空白符为true
		StringUtils.containsNone("china", '\t'); // true
		StringUtils.containsNone("china", '\r'); // true
		StringUtils.containsNone("china", 'x', 'y', 'z'); // true
		StringUtils.containsNone("china", 'c', 'y', 'z'); // false
		StringUtils.containsNone("china", 'C', 'y', 'z'); // true
		StringUtils.containsNone(null, "a"); // true
		StringUtils.containsNone("", "a"); // true
		StringUtils.containsNone("china", ""); // true
		StringUtils.containsNone("china", "xyz"); // true
		StringUtils.containsNone("china", "cyz"); // false
		StringUtils.containsNone("china", "Cyz"); // true

		// （4）判断字符串中的字符是否都是出自所指定的字符数组或字符串，区分大小写：
		StringUtils.containsOnly(null, 'a');// false
		StringUtils.containsOnly("", "a");// true
		StringUtils.containsOnly("ab", ' ');// false
		StringUtils.containsOnly("abab", 'a', 'b', 'c');// true
		StringUtils.containsOnly("abcd", 'a', 'b', 'c');// false
		StringUtils.containsOnly("Abab", 'a', 'b', 'c');// false
		StringUtils.containsOnly(null, "a");// false
		StringUtils.containsOnly("", "a"); // true
		StringUtils.containsOnly("abab", "abc");// true
		StringUtils.containsOnly("abcd", "abc"); // false
		StringUtils.containsOnly("Abab", "abc");// false

		/*
		 * （5）判断字符串是否以指定的字符序列开头：    a）区分大小写：
		 */
		StringUtils.startsWith(null, null); // true
		StringUtils.startsWith(null, "abc"); // false
		StringUtils.startsWith("abcdef", null); // false
		StringUtils.startsWith("abcdef", "abc"); // true
		StringUtils.startsWith("ABCDEF", "abc"); // false

		// b）不区分大小写：
		StringUtils.startsWithIgnoreCase(null, null);// true
		StringUtils.startsWithIgnoreCase(null, "abc");// false
		StringUtils.startsWithIgnoreCase("abcdef", null);// false
		StringUtils.startsWithIgnoreCase("abcdef", "abc");// true
		StringUtils.startsWithIgnoreCase("ABCDEF", "abc");// true

		//（6）判断字符串是否以指定的字符序列数组中任意一个开头，区分大小写：
		StringUtils.startsWithAny(null, null);// false
		StringUtils.startsWithAny(null, new String[] { "abc" });// false
		StringUtils.startsWithAny("abcxyz", null);// false
		StringUtils.startsWithAny("abcxyz", new String[] { "" });// true
		StringUtils.startsWithAny("abcxyz", new String[] { "abc" });// true
		StringUtils.startsWithAny("abcxyz", new String[] { null, "xyz", "abc" });// true
		StringUtils.startsWithAny("abcxyz", null, "xyz", "ABCX");// false
		StringUtils.startsWithAny("ABCXYZ", null, "xyz", "abc");// false
	}

	/*
	 * 九、查询索引：  StringUtils中获取字符或字符序列在字符串中出现的索引下标的方法主要有：
		indexOf(CharSequence seq, int searchChar)
		indexOf(CharSequence seq,CharSequence searchSeq)
		indexOfIgnoreCase
		indexOf(CharSequence seq,CharSequence searchSeq,int startPos)
		lastIndexOf(CharSequence seq,int searchChar)
		lastIndexOfIgnoreCase(CharSequence str,CharSequence searchStr)
	 */
	@Test
	public void test12() {
		/*
		 * （1）获取指定字符或字符序列在字符串中第一次出现的索引，若字符串中不包含该字符或字符序列，则返回-1，
		 * 若字符串或字符序列为""或null，也返回-1（（但字符串和字符序列都为""的情况下，则返回0））：
		 *     a）区分大小写：
		 */
		StringUtils.indexOf(null, 'a');// -1
		StringUtils.indexOf("", 'a');// -1
		StringUtils.indexOf("abca", 'a');// 0
		StringUtils.indexOf("abca", 'b');// 1
		StringUtils.indexOf("abca", 'A');// -1
		StringUtils.indexOf(null, "a"); // -1
		StringUtils.indexOf("abc", null); // -1
		StringUtils.indexOf("", ""); // 0
		StringUtils.indexOf("", "a"); // -1  注意这里第二个参数为""时则为0
		StringUtils.indexOf("abc", "a"); // 0
		StringUtils.indexOf("abc", "b"); // 1
		StringUtils.indexOf("abc", "ab"); // 0
		StringUtils.indexOf("abc", ""); // 0

		// b）不区分大小写：
		StringUtils.indexOfIgnoreCase(null, "a"); // -1
		StringUtils.indexOfIgnoreCase("abc", null); // -1
		StringUtils.indexOfIgnoreCase("", ""); // 0
		StringUtils.indexOfIgnoreCase("", "a");// -1
		StringUtils.indexOfIgnoreCase("abc", "b");// 1
		StringUtils.indexOfIgnoreCase("abc", "B"); // 1

		/*
		 *  （1）获取字符序列在字符串中指定位置之后第一次出现的索引，若字符串中指定位置之后不包含该字符序列，则返回-1，
		 * 若字符串或字符序列为""或null，也返回-1（但字符串和字符序列都为""的情况下，结果就有点怪异，有时返回0，
		 * 有时返回1，有时返回-1，根据指定的起始位置会有变化）：
		 *     a）区分大小写：
		 */
		StringUtils.indexOf(null, "a", 2); // -1
		StringUtils.indexOf("abc", null, 2); // -1
		StringUtils.indexOf("", "", 0); // 0 注意此处和下一行都返回0，对比忽略大小写的情形，就有点不一样
		StringUtils.indexOf("", "", 1); // 0
		StringUtils.indexOf("", "", 2); // 0
		StringUtils.indexOf("", "a", 0); // -1 不包括第二个参数为""的情况
		StringUtils.indexOf("abac", "a", 1); // 2
		StringUtils.indexOf("abcab", "ab", 2); // 3
		StringUtils.indexOf("abc", "a", -1); // 0 -1被当作是0
		StringUtils.indexOf("abc", "a", 2); // -1

		// b）不区分大小写：
		StringUtils.indexOfIgnoreCase("", "", 0); // 0
		StringUtils.indexOfIgnoreCase("", "", 0); // 1 与不忽略大小写的情况不同，下面也是
		StringUtils.indexOfIgnoreCase("", "", 0); //-1
		StringUtils.indexOfIgnoreCase("abac", "A", 1); // 2
		StringUtils.indexOfIgnoreCase("abcab", "AB", 2); // 3
		StringUtils.indexOfIgnoreCase("abc", "B", -1); // 1 -1被当作是0

		/*
		 * （2）获取指定字符或字符序列在字符串中最后一次出现的索引，若字符串中不包含该字符序列，则返回-1，
		 * 若字符串或字符序列为""或null，也返回-1（但字符串和字符序列都为""的情况下，返回0）：
		 *     a）区分大小写：
		 */
		StringUtils.lastIndexOf(null, 'a');// -1
		StringUtils.lastIndexOf("", 'a');// -1
		StringUtils.lastIndexOf("abccba", 'a');// 5
		StringUtils.lastIndexOf("abccba", 'z');// -1
		StringUtils.lastIndexOf(null, "a");// -1
		StringUtils.lastIndexOf("abc", null);// -1
		StringUtils.lastIndexOf("", "");// 0
		StringUtils.lastIndexOf("abc", "b");// 1
		StringUtils.lastIndexOf("abc", "ab");// 0
		StringUtils.lastIndexOf("abc", "");// 3 返回字符串的长度

		//b）不区分大小写：
		StringUtils.lastIndexOfIgnoreCase(null, "a");// -1
		StringUtils.lastIndexOfIgnoreCase("abc", null);// -1
		StringUtils.lastIndexOfIgnoreCase("", "");// 0
		StringUtils.lastIndexOfIgnoreCase("abc", "B");// 1
		StringUtils.lastIndexOfIgnoreCase("abc", "AB");// 0
		StringUtils.lastIndexOfIgnoreCase("abc", "");// 3  返回字符串的长度
	}


}
