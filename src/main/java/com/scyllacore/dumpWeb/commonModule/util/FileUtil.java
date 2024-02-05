package com.scyllacore.dumpWeb.commonModule.util;


import com.scyllacore.dumpWeb.commonModule.db.dto.manage.FileDTO;
import com.scyllacore.dumpWeb.commonModule.db.mapper.file.FileMapper;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileUtil {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final FileMapper fileMapper;


    @Value("${file.upload.path}")
    private String UPLOAD_PATH;

    public Long uploadFile(MultipartFile file, long groupReportId) {
        try {
            String fileName = file.getOriginalFilename();
            String uuid = UUID.randomUUID().toString();
            String ext = this.getExtension(fileName);
            String uploadFilePath = UPLOAD_PATH + uuid;

            File uploadFile = new File(uploadFilePath);
            FileDTO fileDTO = new FileDTO();

            file.transferTo(uploadFile);

            fileDTO.setFileName(fileName);
            fileDTO.setUuid(uuid);
            fileDTO.setFileExt(ext);
            fileDTO.setGroupReportIdFk(groupReportId);

            fileMapper.insertFileInfoByGroupReportId(fileDTO);
            log.info(fileName);

            return fileDTO.getFileId();

        } catch (IOException e) {
            log.error("Excepetion [" + e.getMessage() + "]");
            return null; // 수정할 것
        }

    }

    private String getMimeType(File file) throws IOException {
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        return fileNameMap.getContentTypeFor(file.getName());
    }


    public void getImageFile(HttpServletResponse response, int fileId) throws IOException {

        FileDTO fileInfo = fileMapper.findFileInfoByFileId(fileId);

        File storedFile = new File(UPLOAD_PATH + fileInfo.getUuid());

        log.info(fileInfo.getFileName());

        try (InputStream is = new FileInputStream(storedFile)) {
            //String mime = this.getMimeType(storedFile);
            //System.out.println("확인 : " + mime);
            //response.setContentType(mime);
            response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(fileInfo.getFileName(), "UTF-8") + "\"");

            int len;
            byte[] buffer = new byte[1024];
            OutputStream os = response.getOutputStream();
            while ((len = is.read(buffer)) > -1) {
                os.write(buffer, 0, len);
            }

            os.flush();
            os.close();

        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_BAD_GATEWAY);
            log.error("Excepetion [" + e.getMessage() + "]");
        }

    }

    public void deleteFile(int fileId) {
        this.deleteImageFile(fileId);
        this.deleteFileColumn(fileId);
    }


    public boolean deleteImageFile(int fileId) {

        boolean result = false;

        FileDTO fileInfo = fileMapper.findFileInfoByFileId(fileId);

        File file = new File(UPLOAD_PATH + fileInfo.getUuid());

        log.info(fileInfo.getFileName());

        System.out.println(fileInfo.getUuid());

        try {

            if (file.delete()) {
                result = true;
            } else {
                result = false;
            }
            System.out.println("삭제 결과 : " + result);

        } catch (Exception e) {
            log.error("Excepetion [" + e.getMessage() + "]");
        }

        return result;

    }

    public void deleteFileColumn(long fileId) {
        fileMapper.deleteFile(fileId);
    }

    public String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

}