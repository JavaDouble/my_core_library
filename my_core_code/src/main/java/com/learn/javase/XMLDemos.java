package com.learn.javase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.Test;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;
import org.xml.sax.helpers.DefaultHandler;



//读写XML文件的演示
public class XMLDemos {
	/**
	 * 使用DOM方式，写一个XML文档
	 */
	@Test
	public void test1() {
		XMLWriter writer=null;
		try {
			/*
			 * 写出XML文档的步骤：1.创建一个Document对象表示一个空白文档
			 * 2.向Document中添加根元素 3.按照XML的层级结构逐级向根元素中添加子元素。
			 * 4.创建XmlWriter 5.将Document通过XmlWriter写出成XML文档
			 */
			List<Emp> list = new ArrayList<Emp>();
			list.add(new Emp(1,"张三",34,"男",3000));
			list.add(new Emp(2,"李四",21,"女",4000));
			list.add(new Emp(3,"王五",46,"女",4000));
			list.add(new Emp(4,"赵六",28,"男",4400));
			list.add(new Emp(5,"钱七",53,"男",12000));
			//1.创建Document对象
			Document doc = DocumentHelper.createDocument();
			/*
			 * Document提供方法：Element addElement(String name)向当前文档中添加给
			 * 定名字的根元素，并以一个Element实例表示它然后将其返回，这样便于我们使用它
			 * 接着向根元素中追加子元素，或其他操作。
			 * 注意：该方法只能调用一次，因为一个文档中只能有一个根元素。
			 */
			//2.添加根元素
			Element root = doc.addElement("list");
			/*
			 * 将集合中的每个员工信息转换为一个<emp>标签并添加到根元素<list>中
			 */
			//3.添加子元素
			for(Emp emp:list){
				/*
				 * Element提供了方法：Element addElement(String name)向当前标签中
				 * 添加给定名字的子标签并将其返回。
				 */
				//添加emp标签
				Element eleEmps = root.addElement("emp");
				//添加id属性
				eleEmps.addAttribute("id",emp.getId()+"");
				//添加name标签
				eleEmps.addElement("name").addText(emp.getName());
				//添加age标签
				//eleEmps.addElement("age").addText(String.valueOf(emp.getAge()));
				eleEmps.addElement("age").addText(emp.getAge()+"");
				//添加gender标签
				eleEmps.addElement("gender").addText(emp.getGender());
				//添加salary标签
				//eleEmps.addElement("salary").addText(String.valueOf(emp.getSalary()));
				eleEmps.addElement("salary").addText(emp.getSalary()+"");
			}
			System.out.println("元素添加完毕");
			//4.创建XmlWriter 写出
			writer=new XMLWriter(OutputFormat.createPrettyPrint());
			//5.将Document通过XmlWriter写出成XML文档
			FileOutputStream fos = new FileOutputStream("empList.xml");
			writer.setOutputStream(fos);
			//第二种实现方式 4/5
			//writer= new XMLWriter(new FileOutputStream("WriterEmpList.xml"),OutputFormat.createPrettyPrint());
			writer.write(doc);
			System.out.println("写出完毕");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}


	/**
	 * 使用DOM方式，读取test1写的XML文档
	 */
	public static Document readXML(String emplist) throws DocumentException, FileNotFoundException {
		try {
			//1创建SAXReader 字符输入刘
			SAXReader sr = new SAXReader();
			//2读取指定文件
			Document doc = sr.read(new FileInputStream(emplist));
			return doc;
		} catch (DocumentException e) {
			e.printStackTrace();
			throw e;
		}
	}
	@Test
	public void test2() {
		try{
			/*
			 * 使用DOM解析XML的大致步骤：1.创建SAXReader
			 * 2.使用SAXReader读取要解析的XML
			 * 文档并返回一个Document对象 该步骤就是DOM解析耗时耗资源的地方,因为要先将
			 * XML文档全部内容读取完毕并载入内存
			 * 3.Document对象表示的就是该XML文档从Document中首先获取根元素
			 * 4.从根元素中按照XML的层级结构逐级获取子元素以达到遍历XML文档内容的目的
			 */
			/*
			 * 将emplist.xml文档中所有员工信息解析出来，并转化为若干Emp实例存入到一个集合中
			 * 默认是当前类所在项目的根目录，也叫类路径。
			 */
			Document doc = readXML("empList.xml");
			/*
			 * Element getRootElment()
			 * 该方法用来获取当前XML文档中的根元素 Element的每一个实例用于表示XML文档
			 * 中的一个元素（一对标签）
			 * 对于当前例子来讲，获取的就是EmpList.xml文档中的<list>标签
			 */
			//3.通过Document获取根元素
			Element root = doc.getRootElement();
			//System.out.println(root.getName());
			//System.out.println("日期："+root.attribute("date").getValue());
			//System.out.println("文件名称："+root.attribute("name").getValue());
			/*
			 * Element提供了获取子元素的相关方法：常用的：List elements()获取当前
			 * 标签下的所有子标签，放回的集合中为若干Element实例，而每一个实例就表示其中
			 * 的一个子标签
			 * List elements(String name)获取当前标签下所有同名子标签
			 * Element element(String name)获取当前标签下指定名字的子标签
			 */
			//获取当前根标签<list>下的所有子标签<emp>
			List<Element> elements= root.elements();
			//用来存入每个员工信息的集合
			List<Emp> list = new ArrayList<Emp>();
			//4.从根元素中按照XML的层级结构逐级获取子元素以达到遍历XML文档内容的目的
			for(Element e:elements){
				/*
				 * Element其他获取信息的常用方法 String getName()获取当前标签的名字
				 * Attribute attribute(String name)获取当前标签给定名字的属性
				 * Attribute的每一个实例用于表示一个标签中的属性，有两个常用方法：
				 * String getName()用于获取属性名  String getValue()用于获取属性值
				 * String getText()获取当前标签中的文本
				 * (前标签和后标签中间的文本信息，前提是确实是文本而不是子标签)
				 */
				//System.out.println(e.getName());
				//获取id
				int id = Integer.parseInt(e.attribute("id").getValue().trim());
				//System.out.println(id);
				//获取name
		/*		Element nameEle = e.element("name");
				String name = nameEle.getTextTrim();*/
				String name = e.elementText("name").trim();
				//System.out.println(name);
				//获取age
		/*		Element nameAge = e.element("age");
				int age = Integer.parseInt(nameAge.getTextTrim());*/
				int age = Integer.parseInt(e.elementText("age").trim());
				age = Integer.parseInt(e.elementTextTrim("age"));
				//System.out.println(age);
				//获取gender
		/*		Element nameGender = e.element("gender");
				String gender = nameGender.getTextTrim();*/
				String gender = e.elementText("gender");
				//System.out.println(gender);
				//获取salary
			/*	Element nameSalary = e.element("salary");
				int salary = Integer.parseInt(nameSalary.getTextTrim());*/
				double salary = Double.valueOf(e.elementText("salary").trim());
				//System.out.println(salary);
				Emp emp = new Emp(id, name, age, gender, salary);
				list.add(emp);
			}
			System.out.println("解析完毕");
			for(Emp emp:list){
				System.out.println(emp);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * 使用XPath检索xml文档中内容
	 */
	@Test
	public void test3() {
		try {
			SAXReader sr = new SAXReader();
			Document doc = sr.read(new FileInputStream("empList.xml"));
			/*
			 * Document支持使用xpath检索数据 前提是必须引入jaxen这个jar包
			 * List selectNodes(String xpath) 传入xpath路径，获取相应的信息
			 */
			String xpath="/list/emp[age>23 and gender='女']/name";
			//String xpath="/list/emp[age>23 or gender='女']/name";

			List<Node> list = doc.selectNodes(xpath);

			for(Node e:list){
				System.out.println(e.getText());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * 使用SAX方式，写一个XML文档
	 *
	 * @throws FileNotFoundException
	 * @throws TransformerConfigurationException
	 * @throws SAXException
	 */
	@Test
	public void test4() throws FileNotFoundException, TransformerConfigurationException, SAXException {
		List<Emp> list = new ArrayList<Emp>();
		list.add(new Emp(1,"张三",34,"男",3000));
		list.add(new Emp(2,"李四",21,"女",4000));
		list.add(new Emp(3,"王五",46,"女",4000));
		list.add(new Emp(4,"赵六",28,"男",4400));
		list.add(new Emp(5,"钱七",53,"男",12000));

	     // 生成xml
        // 1.创建一个TransformerFactory类的对象
        SAXTransformerFactory factory = (SAXTransformerFactory) SAXTransformerFactory.newInstance();
        TransformerHandler handler = factory.newTransformerHandler();
        Transformer info = handler.getTransformer();
        // 是否自动添加额外的空白
        info.setOutputProperty(OutputKeys.INDENT, "yes");
        // 设置字符编码
        info.setOutputProperty(OutputKeys.ENCODING, "utf-8");
        info.setOutputProperty(OutputKeys.VERSION, "1.0");
        // 保存创建的empList.xml
        StreamResult result = new StreamResult(new FileOutputStream("empList.xml"));
        handler.setResult(result);

        // 开始xml
        handler.startDocument();
        AttributesImpl impl = new AttributesImpl();
        impl.clear();

        handler.startElement("", "", "list", impl);
        for(int i=0;i<list.size();i++){
            Emp emp = list.get(i);
            //生成<emp id="xx">
            impl.clear(); //清空属性

            impl.addAttribute("", "", "id", "", String.valueOf(emp.getId()));//为emp元素添加id属性
            handler.startElement("", "", "emp", impl);

            handler.startElement("", "", "name", impl);
            String name = emp.getName();

            handler.characters(name.toCharArray(), 0, name.length()); //为title元素添加文本
            handler.endElement("", "", "name");

            //生成<age>xx</age>元素
            impl.clear();
            handler.startElement("", "", "age", impl);
            String age = String.valueOf(emp.getAge());
            handler.characters(age.toCharArray(), 0, age.length());
            handler.endElement("", "", "age");

            //生成<gender>xx</gender>元素
            impl.clear();
            handler.startElement("", "", "gender", impl);
            String gender = emp.getGender();
            handler.characters(gender.toCharArray(), 0, gender.length());
            handler.endElement("", "", "gender");

            //生成<salary>xx</salary>元素
            impl.clear();
            handler.startElement("", "", "salary", impl);
            String salary = String.valueOf(emp.getSalary());
            handler.characters(salary.toCharArray(), 0, salary.length());
            handler.endElement("", "", "salary");

            //生成</emp>
            handler.endElement("", "", "emp");
        }
        //生成</bookstore>
        handler.endElement("", "", "list");
        handler.endDocument();
	}

	/**
	 * 使用SAX方式，读取test4写的XML文档
	 */
	@Test
	public void test5() throws Exception{
		//1.创建SAXParser对象
		SAXParser parser=SAXParserFactory.newInstance().newSAXParser();
		//2.调用parse方法
		/*
		 * 参数一： xml文档	参数二： DefaultHandler的子类
		 */
		parser.parse("empList.xml", new DefaultHandler(){
			 //SAX处理程序（如何解析xml文档）
			/**
			 * 开始文档时调用
			 */
			@Override
			public void startDocument() throws SAXException {

			}

			/**
			 * 开始标签时调用
			 * @param qName: 表示开始标签的标签名
			 * @param attributes: 表示开始标签内包含的属性列表
			 */
			@Override
			public void startElement(String uri, String localName, String qName, Attributes attributes)
					throws SAXException {
				System.out.println("DefaultHandler.startElement()-->"+qName);
			}

			/**
			 * 读到文本内容的时调用
			 * @param ch: 表示当前读完的所有文本内容
			 * @param start： 表示当前文本内容的开始位置
			 * @param length: 表示当前文本内容的长度
			 * char[]（                                       张三              20）   100
			 *                              98 2
			 */
			@Override
			public void characters(char[] ch, int start, int length) throws SAXException {
				//得到当前文本内容
				String content = new String(ch,start,length);
				System.out.println("DefaultHandler.characters()-->"+content);
			}

			/**
			 * 结束标签时调用
			 * @param qName: 结束标签的标签名称
			 */
			@Override
			public void endElement(String uri, String localName, String qName) throws SAXException {
				System.out.println("DefaultHandler.endElement()-->"+qName);
			}

			/**
			 * 结束文档时调用
			 */
			@Override
			public void endDocument() throws SAXException {
				System.out.println("DefaultHandler.endDocument()");
			}
		});
	}

	/**
	 * 使用SAX方式，读取test4写的XML文档完整内容
	 */
	@Test
	public void test6() throws Exception{
		SAXParser parser=SAXParserFactory.newInstance().newSAXParser();
		MyDefaultHandler1  handler=new MyDefaultHandler1();
		parser.parse("empList.xml", handler);
		String content = handler.getContent();
		System.out.println(content);
	}

	/**
	 * 使用SAX方式，读取test4写的XML文档 	使用sax解析把 xml文档封装成对象
	 */
	@Test
	public void test7() throws Exception{
		SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
		MyDefaultHandler2 handler = new MyDefaultHandler2();
		parser.parse("empList.xml", handler);
		List<Emp> list = handler.getList();
		for (Emp emp : list) {
			System.out.println(emp);
		}
	}

	/**
	 * 该类用于表示xml文档中的一个员工信息
	 * @author Double
	 *
	 */
	class Emp{
		private int id;
		private String name;
		private int age;
		private String gender;
		private double salary;

		public Emp() {

		}

		public Emp(int id, String name, int age, String gender, double salary) {
			super();
			this.id = id;
			this.name = name;
			this.age = age;
			this.gender = gender;
			this.salary = salary;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

		public String getGender() {
			return gender;
		}

		public void setGender(String gender) {
			this.gender = gender;
		}

		public double getSalary() {
			return salary;
		}

		public void setSalary(double salary) {
			this.salary = salary;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + age;
			result = prime * result + ((gender == null) ? 0 : gender.hashCode());
			result = prime * result + id;
			result = prime * result + ((name == null) ? 0 : name.hashCode());
			long temp;
			temp = Double.doubleToLongBits(salary);
			result = prime * result + (int) (temp ^ (temp >>> 32));
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Emp other = (Emp) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (age != other.age)
				return false;
			if (gender == null) {
				if (other.gender != null)
					return false;
			} else if (!gender.equals(other.gender))
				return false;
			if (id != other.id)
				return false;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
			if (Double.doubleToLongBits(salary) != Double.doubleToLongBits(other.salary))
				return false;
			return true;
		}

		private XMLDemos getOuterType() {
			return XMLDemos.this;
		}

		@Override
		public String toString() {
			return "Emp [id=" + id + ", name=" + name + ", age=" + age + ", gender=" + gender + ", salary=" + salary
					+ "]";
		}

	}

	/**
	 * SAX处理器程序
	 * @author Double
	 */
	class MyDefaultHandler1 extends DefaultHandler {
		//存储xml文档信息
		private StringBuffer sb = new StringBuffer();

		//获取xml信息
		public String getContent(){
			return sb.toString();
		}


		/**
		 * 开始标签
		 */
		@Override
		public void startElement(String uri, String localName, String qName,
				Attributes attributes) throws SAXException {
			sb.append("<"+qName);
			//判断是否有属性
			if(attributes!=null){
				for(int i=0;i<attributes.getLength();i++){
					//得到属性名称
					String attrName = attributes.getQName(i);
					//得到属性值
					String attrValue = attributes.getValue(i);
					sb.append(" "+attrName+"=\""+attrValue+"\"");
				}
			}
			sb.append(">");
		}

		/**
		 * 文本内容
		 */
		@Override
		public void characters(char[] ch, int start, int length)
				throws SAXException {
			//得到当前读取的文本
			String content = new String(ch,start,length);
			sb.append(content);
		}

		/**
		 * 结束标签
		 */
		@Override
		public void endElement(String uri, String localName, String qName)
				throws SAXException {
			sb.append("</"+qName+">");
		}
	}
	/**
	 * SAX处理程序
	 * @author Double
	 *
	 */
	class MyDefaultHandler2 extends DefaultHandler {
		//存储所有员工对象
		private List<Emp> list = new ArrayList<Emp>();

		public List<Emp> getList(){
			return list;
		}
		//保存一个员工信息
		private Emp emp;
		/**
		 * 思路：
		 * 	1）创建Emp对象
		 *  2）把每个emp标签内容存入到Emp对象
		 *  3）把Emp对象放入List中
		 */
		//用于临时存储当前读到的标签名
		private String curTag;

		@Override
		public void startElement(String uri, String localName, String qName,
				Attributes attributes) throws SAXException {
			curTag = qName;
			//读取到emp的开始标签创建Emp对象
			if("emp".equals(qName)){
				emp = new Emp();

				//设置id值
				emp.setId(Integer.parseInt(attributes.getValue("id")));
			}
		}

		@Override
		public void characters(char[] ch, int start, int length)
				throws SAXException {
			//当前文本内容
			String content = new String(ch,start,length);

			if("name".equals(curTag)){
				emp.setName(content);
			}

			if("age".equals(curTag)){
				emp.setAge(Integer.valueOf(content));
			}

			if("gender".equals(curTag)){
				emp.setGender(content);
			}

			if("salary".equals(curTag)){
				emp.setSalary(Double.parseDouble(content));
			}

		}

		@Override
		public void endElement(String uri, String localName, String qName)
				throws SAXException {
			//设置空时为了避免空格换行设置到对象的属性中
			curTag = null;
			//读到emp的结束标签放入List中
			if("emp".equals(qName)){
				list.add(emp);
			}
		}
	}
}
