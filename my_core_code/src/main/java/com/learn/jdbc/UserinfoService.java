package com.learn.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Scanner;

import com.learn.jdbc.DBUtil;
import com.learn.jdbc.dao.UserinfoDAO;
import com.learn.jdbc.dao.impl.UserinfoDAOImpl;
import com.learn.jdbc.entity.Userinfo;




/**
 * 使用DAO重构
 * 数据访问与业务逻辑分离 封装对数据的访问
 */
/**
 * 用户管理系统
 * @author double
 *
 */
public class UserinfoService {
	private static final int USER_REG=1;
	private static final int USER_LOGIN=USER_REG+1;
	private static final int USER_UPDATE=USER_LOGIN+1;
	private static final int USER_DEL=USER_UPDATE+1;
	private static final int USER_SEL=USER_DEL+1;
	private static final int USER_TRANS=USER_SEL+1;
	private static final int USER_LOGOUT=USER_TRANS+1;

	//private UserinfoDaoClass dao=null;
	private UserinfoDAO dao=null;
	/*
	 * 记录当前系统登录用户的信息：NULL则表示没有登录
	 * 登录方法在验证登录用户后会将该用户信息赋值到该属性上
	 */
	private Userinfo userinfo=null;
	/**
	 * 管理系统开始工作的方法
	 */
	public UserinfoService() {
		//this.dao=new UserinfoDaoClass();
		this.dao=new UserinfoDAOImpl();
	}
	private void start() {
		//显示提示信息
		showInfo();
		//获取功能序号
		int code=getCode();
		//分发任务
		execute(code);
	}
	/**
	 * 分发任务
	 * @param code
	 */
	private void execute(int code) {
		switch(code){
		case USER_REG:
			userReg();
			break;
		case USER_LOGIN:
			userLogin();
			break;
		case USER_UPDATE:
			userUpdate();
			break;
		case USER_DEL:
			userDel();
			break;
		case USER_SEL:
			userSel();
			break;
		case USER_TRANS:
			userTrans();
			break;
		default:
			userLogout();
		}
	}
	/**
	 * 用户注册
	 */
	private void userReg() {
		System.out.println("欢迎注册");
		Scanner sc=new Scanner(System.in);
		System.out.println("请输入用户名：");
		String username=sc.nextLine();
		System.out.println("请输入密码：");
		String password=sc.nextLine();
		System.out.println("请输入邮箱：");
		String email=sc.nextLine();
		System.out.println("请输入昵称：");
		String nickname=sc.nextLine();
		Userinfo userinfo=new Userinfo();
		userinfo.setUsername(username);
		userinfo.setPassword(password);;
		userinfo.setEmail(email);
		userinfo.setNickname(nickname);
		userinfo.setAccount(5000);
		boolean flag=dao.save(userinfo);
		if(flag){
			System.out.println("注册成功，您的id是："+userinfo.getId());
		}else{
			System.out.println("注册失败!");
		}
		start();//继续用户输入其他操作
	}
	/**
	 * 用户登录
	 */
	private void userLogin() {
		System.out.println("欢迎登录");
		Scanner sc=new Scanner(System.in);
		System.out.println("请输入用户名：");
		String username=sc.nextLine();
		System.out.println("请输入密码：");
		String password=sc.nextLine();
		Userinfo userinfo=dao.findByUsername(username);
		if(userinfo!=null&&password.equals(userinfo.getPassword())){
			System.out.println("登录成功!");
			this.userinfo=userinfo;
		}else{
			System.out.println("用户名或密码不正确!");
		}
		start();
	}
	/**
	 * 修改用户信息
	 */
	private void userUpdate() {

	}
	/**
	 * 删除用户
	 */
	private void userDel() {
		if(userinfo!=null){
			System.out.println("请输入想要删除的用户名：");
			Scanner sc=new Scanner(System.in);
			String username=sc.nextLine();
			if("admin".trim().equals(username.trim())){
				System.out.println(1);
				boolean flag = dao.deleteByUsername(username);
				if (flag) {
					System.out.println("删除成功");
				} else {
					System.out.println("删除失败");
				}
			}else{
				System.out.println("对不起，没有权限进行此操作");
			}
		}else{
			System.out.println("用户没有登录：不能进行删除操作");
		}
		start();
	}
	/**
	 * 用户查询
	 */
	private void userSel() {
		if(userinfo!=null){
			List<Userinfo> list=dao.findAll();
			for(Userinfo userinfo:list){
				System.out.println(userinfo);
			}
		}else{
			System.out.println("用户没有登录");
		}
		start();
	}
	/**
	 * 转账
	 */
	private void userTrans() {
		/*
		 * 转账业务中的事务控制
		 * A用户给B用户转账那个指定金额
		 */
		Scanner sc=new Scanner(System.in);
		System.out.println("请输入转出帐号的用户名：");
		String outName=sc.nextLine();
		System.out.println("请输入转入账户的用户名：");
		String inName=sc.nextLine();
		System.out.println("请输入转账的金额：");
		double money=Integer.parseInt(sc.nextLine());
		try {
			Connection conn=DBUtil.getConnection();
			/*
			 * JDBC中事务是默认自动提交的，即：每当执行一条DML语句后就自动COMMIT
			 */
			//开启事务(自行维护事务提交)
			DBUtil.TransBegin();
			//转出
			String outSql="SELECT account FROM userinfo_lqs "+"WHERE username=?";
			PreparedStatement ps=conn.prepareStatement(outSql);
			ps.setString(1,outName);
			ResultSet rs=ps.executeQuery();
			double account=0;
			if(rs.next()){
				account=rs.getDouble("account");
				System.out.println(account);
			}else{
				System.out.println("获取余额失败");
			}
			outSql="UPDATE userinfo_lqs SET account=account-? "+"WHERE username=?";
			PreparedStatement outPs=conn.prepareStatement(outSql);
			if(account<money){
				System.out.println("余额不足：转账失败");
				return;
			}
			outPs.setDouble(1,money);
			outPs.setString(2,outName);
			int out=outPs.executeUpdate();
			//System.out.println(out);
			if(out==0){
				System.out.println("转账失败!");
				return;
			}else{
				System.out.println("转出成功!");
			}
			//转入
			String inSql="UPDATE userinfo_lqs SET account=account+? "+"WHERE username=?";
			PreparedStatement inPs=conn.prepareStatement(inSql);
			inPs.setDouble(1,money);
			inPs.setString(2,inName);
			int in=inPs.executeUpdate();
			//System.out.println(in);
			if(in==0){
				System.out.println("转账失败");
				DBUtil.TransRollBack();
			}else{
				System.out.println("转账完毕");
				//提交事务
				DBUtil.TransCommit();
			}

		} catch (Exception e) {
			e.printStackTrace();
			DBUtil.TransRollBack();
		}finally{
			DBUtil.closeConnection();
		}
	}
	/**
	 * 登出
	 */
	private void userLogout() {

	}
	/**
	 * 获取用户输入的功能序号
	 * @return
	 */
	private int getCode() {
		Scanner sc=new Scanner(System.in);
		return Integer.parseInt(sc.nextLine());
	}
	/**
	 * 提示信息
	 */
	private void showInfo() {
		System.out.println("欢迎使用用户管理系统");
		System.out.println("请输入要操作的功能序号：");
		System.out.println("1：用户注册");
		System.out.println("2：用户登录");
		System.out.println("3：修改用户信息");
		System.out.println("4：删除用户");
		System.out.println("5：查看所有用户信息");
		System.out.println("6：转账业务");
		System.out.println("7：用户登出");
	}
	public static void main(String[] args) {
		UserinfoService us=new UserinfoService();
		us.start();
	}
}
