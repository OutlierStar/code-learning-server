package com.example.practicalwork.Controller;


import com.example.practicalwork.service.Impl.Msg;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/file")
@CrossOrigin //允许所有ip跨域
public class FileController {

    @RequestMapping("/set")
    public Msg oneFileUpload(
            @RequestParam("file") String file,
            @RequestParam("fileName")  String fileName,
            @RequestParam("end")  String end,
            @RequestParam("studentNo") String studentNo,
            @RequestParam("questionId") String questionId,
            @RequestParam("dir") String dir,
            HttpServletRequest request, ModelMap model) throws IOException {

        // 获得原始文件名
//        String fileName = file.getOriginalFilename();

//        System.out.println("原始文件名:" + fileName);


        // 新文件名
        String newFileName = fileName+"."+end;

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
//		        windows文件路径
		        String path=realPath+"static\\"+end+"\\";

//		        linux文件路径
//        String path=realPath+"static/images/";

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
//                InputStream in = file.getInputStream();
//                int b = 0;
//                while ((b = in.read()) != -1) {
//                    fos.write(b);
//                }
                fos.write(file.getBytes());

                fos.close();
//                in.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("f:---+++++" + f.getPath());
        System.out.println("上传图片到:" + path +""+ newFileName);
        // 保存文件地址，用于JSP页面回显
        model.addAttribute("fileUrl", basePath +""+ newFileName);

        String src = basePath +"static/"+end+"/"+ newFileName;
        return Msg.success().add("path", src);
    }

}
