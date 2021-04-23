package test.june.file.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@RestController
public class FileUploadController {
    SimpleDateFormat sdf = new SimpleDateFormat("yyy/MM/dd/");
    @PostMapping("/upload")
    public String upload(MultipartFile uploadFile, HttpServletRequest req) {
        String realPath = req.getSession().getServletContext().getRealPath("/uploadFile/");
        String format = sdf.format(new Date());
        //file 这里是我自己想要的文件夹
        String file = "D:\\Codings\\paper_esrgan\\LR";
        File folder = new File(file);

/*        String file = realPath + format;
        File folder = new File(realPath + format);*/
        if(!folder.isDirectory()) {
            folder.mkdirs();
        }
        String oldName = uploadFile.getOriginalFilename();
        String newName = UUID.randomUUID().toString() +
                oldName.substring(oldName.lastIndexOf("."), oldName.length());
        try {
            // 文件保存操作
            uploadFile.transferTo(new File(folder, newName));
            return file;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "上传失败!";
    }

    // 后端代码实现（前端就用a标签完成就可以，此处忽略）
    @RequestMapping("/download")
    public void download(HttpServletResponse response) {
        // 这部分用来写文件路径、文件名获取逻辑，本例中直接使用了上传到项目中的文件
        String fileName = "D:\\Codings\\paper_esrgan\\results";
        // 设置响应头
        response.setHeader("content-type","application/octet-stream");
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition","attachment;filename="+fileName);
        // 文件传输
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            os = response.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(fileName));
            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff,0,buff.length);
                os.flush();
                i = bis.read(buff);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (os != null) {
                try {
                    os.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

