package com.learn.javaee.unit11;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

/**
 * 演示文件的上传、下载
 * @author Double
 *
 */
@MultipartConfig
public class MainServlet extends HttpServlet {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;



	/**
	 * 同意处理路径
	 */
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path=request.getServletPath();
		if("/upload.d".equals(path)){
			upload(request, response);
		}else if("/upload1.d".equals(path)){
			upload1(request, response);
		}else if("/upload2.d".equals(path)){
			upload2(request, response);
		}else if("/list.d".equals(path)){
			listFileServlet(request, response);
		}else if("/download.d".equals(path)){
			download(request, response);
		}else{
			throw new RuntimeException("查无此页");
		}
	}

	/**
	 * 处理文件上传的逻辑		基于Servlet+JSP 流的方式实现
	 * 注意：上传文件必须添加@MultipartConfig()可以设置上传文件的大小
	 *
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void upload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //获取文件描述信息
            String desc=request.getParameter("desc");
            //获取上传的文件
            Part part=request.getPart("file");
            //获取请求的信息
            String name=part.getHeader("content-disposition");
            //System.out.println(name);//测试使用
            System.out.println(desc);//

            //获取上传文件的目录
            String root="D:";
            System.out.println("测试上传文件的路径："+root);

            //获取文件的后缀
            String str=name.substring(name.lastIndexOf("."), name.length()-1);
            System.out.println("测试获取文件的后缀："+str);

            //生成一个新的文件名，不重复，数据库存储的就是这个文件名，不重复的
            String filename=root+"\\"+System.currentTimeMillis()+str;
            System.out.println("测试产生新的文件名："+filename);

            //上传文件到指定目录，不想上传文件就不调用这个
            part.write(filename);

            request.setAttribute("info", "上传文件成功");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("info", "上传文件失败");
        }
        request.getRequestDispatcher("upload.jsp").forward(request, response);
	}

	/**
	 * 处理文件上传的逻辑		基于apache.commons.fileupload实现
	 * 原始版本 有诸多问题
	 * 注意：上传文件必须添加@MultipartConfig()可以设置上传文件的大小
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void upload1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
        	//解析器缓冲区默认是10k
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            List<FileItem> list = upload.parseRequest(request);
            for(FileItem item : list){
                if(item.isFormField()){
                    //为普通输入项
                    String inputName = item.getFieldName();
                    String inputValue = item.getString();
                    System.out.println(inputName + "="  + inputValue);
                }else{
                    //代表当前处理的item里面封装的是上传文件
                    //C:\Documents and Settings\ThinkPad\桌面\a.txt    a.txt
                    String filename = item.getName().substring(item.getName().lastIndexOf("\\")+1);
                    //System.out.println(filename);
                    //获取文件的后缀
                    String str=filename.substring(filename.lastIndexOf("."), filename.length());
                    //生成一个新的文件名，不重复，数据库存储的就是这个文件名，不重复的
                    filename=System.currentTimeMillis()+str;
                    System.out.println(filename);
                    InputStream in = item.getInputStream();
                    int len = 0;
                    byte buffer[] = new byte[1024];
                    FileOutputStream out = new FileOutputStream("D:\\" + filename);
                    while((len=in.read(buffer))>0){
                        out.write(buffer, 0, len);
                    }
                    in.close();
                    out.close();
                }
            }
            request.setAttribute("info", "上传文件成功");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("info", "上传文件失败");
        }
        request.getRequestDispatcher("upload.jsp").forward(request, response);
	}

    public String generateSavePath(String path,String filename){
        int hashcode = filename.hashCode();  //121221
        int dir1 = hashcode&15;
        int dir2 = (hashcode>>4)&0xf;

        String savepath = path + File.separator + dir1 + File.separator + dir2;
        System.out.println(savepath);
        File file = new File(savepath);
        if(!file.exists()){
            file.mkdirs();
        }
        return savepath;
    }

	/**
	 * 处理文件上传的逻辑		基于apache.commons.fileupload实现
	 * 基于原始版本 改进
	 * 注意：上传文件必须添加@MultipartConfig()可以设置上传文件的大小
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void upload2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 重点是三个步骤：
		 * a 创建文件工厂
		 * b 使用servletFileUpload核心类解析Request
		 * c 使用FileItem类获取上传内容
		 */

		//注意，表单类型为multipart/form-data的时候，设置request的编码是无效的
		//request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("text/html;charset=uft-8");
		try {
			//1 创建磁盘文件工厂 解析器缓冲区默认是10k
            DiskFileItemFactory factory = new DiskFileItemFactory();
            //设置缓冲区大小，上传文件超过该值，将使用临时文件来存储  ，单位为byte
            factory.setSizeThreshold(1024*1024);
            //临时文件的存储目录，一定记得最好要关闭
            //磁盘缓存文件保存路径设定，这里设置为项目下temp文件夹绝对路径
            factory.setRepository(new File(this.getServletContext().getRealPath("/temp")));

            //2创建apache文件上传核心类
            ServletFileUpload upload = new ServletFileUpload(factory);

            //监听上传进度
            upload.setProgressListener(new ProgressListener(){
                public void update(long pBytesRead, long pContentLength, int pItems) {
                	//request.setAttribute("schedule", pBytesRead);
                	//获取文件大小，与pBytesRead比对，查看是否上传完毕
                    System.out.println("当前已解析：" + pBytesRead+"字节！");
                }
            });

            //设置最大文件5M
            upload.setFileSizeMax(1024*1024*5);

            //2.1判断form提交的是否是一个文件上传form
            if(!upload.isMultipartContent(request)){
            	//如果不是文件上传类型，则采用普通表单获取方式
                //按照传统方式获取表单数据
            	request.setCharacterEncoding("UTF-8");
                request.getParameter("username");
                return;
            }

            //2.2设置字符集
            upload.setHeaderEncoding("UTF-8");
            //2.3解析request为一个集合，元素为FileItem
            List<FileItem> list = upload.parseRequest(request);
            //2.4遍历解析集合
            for(FileItem item : list){
            	//判断是否文件，结果为“true”就是普通表单，如果为“false”则是文件
                if(item.isFormField()){
                    //为普通输入项 获取表单name属性名   获取表单value值,并设定编码方式
                    String inputName = item.getFieldName();
                    String inputValue = item.getString("UTF-8");
                    //inputValue = new String(inputValue.getBytes("iso8859-1"),"UTF-8");
                    System.out.println(inputName + "="  + inputValue);
                }else{
                	//获取所上传文件的名称 item.getName()
                    String filename = item.getName().substring(item.getName().lastIndexOf("\\")+1);  //""
                    //判空操作
                    if(filename==null || filename.trim().equals("")){
                        continue;//结束本次循环，进行下次循环
                    }

                    //处理上传文件名称  原则：文件名称唯一 在文件名称前加'System.currentTimeMillis()_'
                    //方便下载时还原会原文件名称。
                    //生成一个新的文件名，不重复，数据库存储的就是这个文件名，不重复的
                    filename=System.currentTimeMillis()+"_"+filename;
                    System.out.println(filename);

                    //获得上传文件字节流
                    InputStream in = item.getInputStream();

                    //文件保存到服务器的目录
                    String savepath = generateSavePath(this.getServletContext().getRealPath("/WEB-INF/upload"),filename);
                    FileOutputStream out = new FileOutputStream(savepath + File.separator + filename);

                    //使用IOUtils工具输出， 也就是copy文件
                    IOUtils.copy(in, out);
                    System.out.println("上传完毕");

                    //文件上传完毕，清空缓存资料夹内容
                    in.close();
                    out.close();
                    //删除临时文件    一定要位于关闭流的后面，因为必须关闭流才能删除，否则文件有流与之关联，是删除不掉的。
                    item.delete();
                }
            }
            request.setAttribute("info", "上传文件成功");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("info", "上传文件失败");
        }
        request.getRequestDispatcher("upload.jsp").forward(request, response);
	}

    public String getpath(String filename){
        int hashcode = filename.hashCode();  //121221
        int dir1 = hashcode&15;
        int dir2 = (hashcode>>4)&0xf;

        return dir1 + File.separator + dir2;  //   3/5
    }

	/**
	 * 处理文件下载的逻辑：1.列出下载列表   2.处理下载逻辑
	 * 1.要将Web应用系统中的文件资源提供给用户进行下载，首先我们要有一个页面列出上传文件目录下的所有文件，
	 * 当用户点击文件下载超链接时就进行下载操作，编写一个listFileServlet，用于列出Web应用系统中所有下载文件。
	 *
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void listFileServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取上传文件的目录
        String uploadFilePath = this.getServletContext().getRealPath("/WEB-INF/upload");
        //存储要下载的文件名
        Map<String,String> fileNameMap = new HashMap<String,String>();
        //递归遍历filepath目录下的所有文件和目录，将文件的文件名存储到map集合中
        //File既可以代表一个文件也可以代表一个目录
        listfile(new File(uploadFilePath),fileNameMap);
        //将Map集合发送到listfile.jsp页面进行显示
        request.setAttribute("fileNameMap", fileNameMap);
        request.getRequestDispatcher("download.jsp").forward(request, response);

	}

	/**
	 *
     * @Method: listfile
     * @Description: 递归遍历指定目录下的所有文件
     * @Anthor:Double
     * @param file 即代表一个文件，也代表一个文件目录
     * @param map 存储文件名的Map集合
	 */
    private void listfile(File file,Map<String,String> map){
        //如果file代表的不是一个文件，而是一个目录
        if(!file.isFile()){
            //列出该目录下的所有文件和目录
            File files[] = file.listFiles();
            //遍历files[]数组
            for(File f : files){
                //递归
                listfile(f,map);
            }
        }else{
            /*
             * 处理文件名，上传后的文件是以System.currentTimeMillis()_文件名的形式去重新命名的，
             * 去除文件名System.currentTimeMillis()的部分
               file.getName().indexOf("_")检索字符串中第一次出现"_"字符的位置，
               	如果文件名类似于：9349249849883438344_阿_凡_达.avi
                                         那么file.getName().substring(file.getName().indexOf("_")+1)
                                         处理之后就可以得到阿_凡_达.avi部分
             */
            String realName = file.getName().substring(file.getName().indexOf("_")+1);
            //file.getName()得到的是文件的原始名称，这个名称是唯一的，因此可以作为key，realName是处理过后的名称，有可能会重复
            map.put(file.getName(), realName);
        }
    }

	/**
	 * 处理文件下载的逻辑
	 * 2.处理下载逻辑
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void download(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    //得到要下载的文件名
        String fileName = request.getParameter("filename");  //23239283-92489-阿凡达.avi
        fileName = new String(fileName.getBytes("iso8859-1"),"UTF-8");
        //上传的文件都是保存在/WEB-INF/upload目录下的子目录当中
        String fileSaveRootPath=this.getServletContext().getRealPath("/WEB-INF/upload");
        //通过文件名找出文件的所在目录
        String path = findFileSavePathByFileName(fileName,fileSaveRootPath);
        //得到要下载的文件
        File file = new File(path + "\\" + fileName);
        //如果文件不存在
        if(!file.exists()){
            request.setAttribute("message", "您要下载的资源已被删除！！");
            //request.getRequestDispatcher("/message.jsp").forward(request, response);
            return;
        }
        //处理文件名
        String realname = fileName.substring(fileName.indexOf("_")+1);
        //设置响应头，控制浏览器下载该文件
        response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(realname, "UTF-8"));
        //读取要下载的文件，保存到文件输入流
        FileInputStream in = new FileInputStream(path + "\\" + fileName);
        //创建输出流
        OutputStream out = response.getOutputStream();
        //创建缓冲区
        byte buffer[] = new byte[1024];
        int len = 0;
        //循环将输入流中的内容读取到缓冲区当中
        while((len=in.read(buffer))>0){
            //输出缓冲区的内容到浏览器，实现文件下载
            out.write(buffer, 0, len);
        }
        //关闭文件输入流
        in.close();
        //关闭输出流
        out.close();

	}

    /**
    * @Method: findFileSavePathByFileName
    * @Description: 通过文件名和存储上传文件根目录找出要下载的文件的所在路径
    * @Anthor:Double
    * @param filename 要下载的文件名
    * @param saveRootPath 上传文件保存的根目录，也就是/WEB-INF/upload目录
    * @return 要下载的文件的存储目录
    */
    private String findFileSavePathByFileName(String filename,String saveRootPath){
        int hashcode = filename.hashCode();
        int dir1 = hashcode&0xf;  //0--15
        int dir2 = (hashcode&0xf0)>>4;  //0-15
        String dir = saveRootPath + "\\" + dir1 + "\\" + dir2;  //upload\2\3  upload\3\5
        File file = new File(dir);
        if(!file.exists()){
            //创建目录
            file.mkdirs();
        }
        return dir;
    }
}
