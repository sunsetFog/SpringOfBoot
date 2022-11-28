package com.core.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
/**
 * study: uploadImg
 */
@Service
public class UploadService {
    private static final List<String> CONTENT_TYPES = Arrays.asList("image/jpeg", "image/png", "image/gif");

    public HashMap<String, String> picrureModify(MultipartFile avatorFile) {
        HashMap<String, String> Sites = new HashMap<>();



        // 校验文件的类型
//        String contentType = avatorFile.getContentType();
//        if (!CONTENT_TYPES.contains(contentType)){
//            Sites.put("msg", "文件类型不合法");
//            return Sites;
//        }



        if (avatorFile.isEmpty()) {
            Sites.put("msg", "文件上传失败！");
            return Sites;
        }
        long time1 = new Date().getTime();
        long time2 = System.currentTimeMillis();
        // 为了名不重复：一个时间（以上两个时间都行） + 文件名
        String fileName = time2 + avatorFile.getOriginalFilename();
        System.out.println("-fileName-: "+fileName);
        // 项目路径
        String PROJECT_PATH = System.getProperty("user.dir");
        // 斜杆   \
        String inclined_rod = System.getProperty("file.separator");

        System.out.println("-111-: "+PROJECT_PATH);
        System.out.println("-222-: "+inclined_rod);
        // 图片保存路径
        String filePath = PROJECT_PATH + inclined_rod + "img" + inclined_rod + "avatorImages";
        System.out.println("-filePath-: "+filePath);
        // 文件对象
        File file1 = new File(filePath);
        if (!file1.exists()) {
            file1.mkdir();
        }

        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        String storeAvatorPath = "/img/avatorImages/" + fileName;
        try {
            // 获取字节流
            byte[] bytes = avatorFile.getBytes();
            System.out.println("-bytes-: "+bytes);

            // 校验文件的内容
//            BufferedImage bufferedImage = ImageIO.read(avatorFile.getInputStream());
//            if (bufferedImage == null){
//                Sites.put("msg", "文件内容不合法");
//                return Sites;
//            }

            // 保存到服务器
            avatorFile.transferTo(dest);
            Sites.put("avator", storeAvatorPath);
            Sites.put("msg", "上传成功");
            return Sites;
        } catch (IOException e) {
            Sites.put("msg", "上传失败" + e.getMessage());
            return Sites;
        } finally {
            return Sites;
        }
    }
}
