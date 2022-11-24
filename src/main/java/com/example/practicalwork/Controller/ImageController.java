package com.example.practicalwork.Controller;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.http.HttpServletRequest;
import com.example.practicalwork.service.Impl.Msg;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin //允许所有ip跨域
public class ImageController {

    @RequestMapping("/image/set")
    public Msg oneFileUpload(
            @RequestParam("file") MultipartFile file, @RequestParam("courseId")  String courseId,
            HttpServletRequest request, ModelMap model) throws IOException {

        // 获得原始文件名
        String fileName = file.getOriginalFilename();
        System.out.println("原始文件名:" + fileName);
        // 新文件名
        String newFileName = fileName;
        // 获得项目的路径
        //ServletContext sc = request.getSession().getServletContext();
        // 上传位置
        // String path = sc.getRealPath("/img") + "/"; // 设定文件保存的目录
//        String path1 = ClassUtils.getDefaultClassLoader().getResource("image").getPath();
        String contextPath = request.getContextPath();
        String realPath = request.getSession().
                getServletContext().getRealPath("/");
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+
                request.getServerPort()+contextPath+"/";


        String path="";
        if (System.getProperty("os.name").toLowerCase().contains("linux")){
//		        windows文件路径
            path=realPath+"static\\images\\";
        }else if (System.getProperty("os.name").toLowerCase().contains("windows")){
//		        linux文件路径
            path=realPath+"static/images/";
        }else{
            System.out.println("judge system occur error");
        }


        System.out.println("path:" + path);
//        System.out.println("path1:" + path1);
//        System.out.println("contextPath:" + contextPath);
        System.out.println("realPath:" + realPath);
//        System.out.println("basePath:" + basePath);

        File f = new File(path);
        System.out.println("f:---" + f.getPath());
        if (!f.exists())
            f.mkdirs();
        if (!file.isEmpty()) {
            try {
                FileOutputStream fos = new FileOutputStream(path +""+ newFileName);
                InputStream in = file.getInputStream();
                int b = 0;
                while ((b = in.read()) != -1) {
                    fos.write(b);
                }
                fos.close();
                in.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("f:---+++++" + f.getPath());
        System.out.println("上传图片到:" + path +""+ newFileName);
        model.addAttribute("fileUrl", basePath +""+ newFileName);

        String src = basePath +"static/images/"+ newFileName;
        return Msg.success().add("path", src);
    }




    @RequestMapping("/file/upLoadImage")
    public Msg FileUpload(
            @RequestParam("file") MultipartFile file,
            @RequestParam("studentNo") String studentNo,
            @RequestParam("questionId") String questionId,
            @RequestParam("dir") String dir,
            HttpServletRequest request, ModelMap model) throws IOException {

        // 获得原始文件名
        String newFileName = file.getOriginalFilename();
        System.out.println("原始文件名:" + newFileName);
        //项目空间名
        String dirName = studentNo+questionId+"";


        // 获得项目的路径
        //ServletContext sc = request.getSession().getServletContext();
        // 上传位置
        // String path = sc.getRealPath("/img") + "/"; // 设定文件保存的目录
//        String path1 = ClassUtils.getDefaultClassLoader().getResource("image").getPath();
        String contextPath = request.getContextPath();
        String realPath = request.getSession().
                getServletContext().getRealPath("/");
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+
                request.getServerPort()+contextPath+"/";

//        String path="";
////		windows文件路径
//        if (dir.length()>0){
//            path=realPath+"static\\"+dirName+"\\"+dir+"\\";
//            System.out.println("dir is not null!");
//        }else {
//            path=realPath+"static\\"+dirName+"\\";
//        }
//
//
////		linux文件路径
////      if (dir.length()>0){
////          path=realPath+"static/"+dirName+"/"+dir+"/";
////      }else{
////          path=realPath+"static/"+dirName+"/";
////      }
//


        String path="";
        String projectpath="";
        if (System.getProperty("os.name").toLowerCase().contains("linux")){
//          linux文件路径
            if (dir.length()>0){
                path=realPath+"static/"+dirName+"/"+dir+"/";
                projectpath=realPath+"static/"+dirName+"/";
            }else{
                path=realPath+"static/"+dirName+"/";
                projectpath=path;
            }
        }else if (System.getProperty("os.name").toLowerCase().contains("windows")){
//		    windows文件路径
            if (dir.length()>0){
                path=realPath+"static\\"+dirName+"\\"+dir+"\\";
                projectpath=realPath+"static\\"+dirName+"\\";
            }else {
                path=realPath+"static\\"+dirName+"\\";
                projectpath=path;
            }
        }else{
            System.out.println("judge system occur error");
        }


        File f = new File(path);
        System.out.println("f:---" + f.getPath());
        if (!f.exists())
            f.mkdirs();
        if (!file.isEmpty()) {
            try {
                FileOutputStream fos = new FileOutputStream(path +""+ newFileName);
                InputStream in = file.getInputStream();
                int b = 0;
                while ((b = in.read()) != -1) {
                    fos.write(b);
                }
                fos.close();
                in.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("f:---+++++" + f.getPath());
        System.out.println("上传图片到:" + path +""+ newFileName);
        // 保存文件地址，用于JSP页面回显
        model.addAttribute("fileUrl", basePath +""+ newFileName);

        String src="";
        if (dir.length()>0){
            src = basePath +"static/"+dirName+"/"+dir+"/"+ newFileName;
        }else {
            src = basePath +"static/"+dirName+"/"+ newFileName;
        }
        return Msg.success().add("path", src);
    }
}
