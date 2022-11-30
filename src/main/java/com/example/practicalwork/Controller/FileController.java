package com.example.practicalwork.Controller;


import com.example.practicalwork.model.FileTreeNode;
import com.example.practicalwork.service.Impl.Msg;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

        //项目空间名
        String dirName = studentNo+questionId+"";

        // 新文件名

        String newFileName;
        if(fileName.length()>0&&end.length()>0){
            newFileName= fileName+"."+end;
        }else {
            newFileName="";
        }


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

        String src="";
        if (dir.length()>0){
            src = basePath +"static/"+dirName+"/"+dir+"/"+ newFileName;
        }else {
            src = basePath +"static/"+dirName+"/"+ newFileName;
        }

        File mkdirpath = new File(projectpath);
        List<FileTreeNode> fileTree = getFileTree(mkdirpath);

        return Msg.success().add("path", src).add("tree",fileTree);
    }

    public List<FileTreeNode> getFileTree(File file) {
        List<FileTreeNode> baseTreeNodes = new ArrayList<>();
        File[] childFiles = file.listFiles();
        if (childFiles != null) {
            for (File listFile : childFiles) {
                FileTreeNode baseTreeNode = new FileTreeNode();
                baseTreeNode.setName(listFile.getName());
                baseTreeNode.setIfDir(listFile.isDirectory());
                baseTreeNode.setPath(listFile.getAbsolutePath());
                baseTreeNode.setLength(listFile.length());
                baseTreeNode.getChildren().addAll(getFileTree(listFile));
                baseTreeNodes.add(baseTreeNode);
            }
        }

        return baseTreeNodes;
    }
}
