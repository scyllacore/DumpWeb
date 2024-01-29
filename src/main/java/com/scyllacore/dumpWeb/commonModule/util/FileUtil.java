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

    public void uploadFile(MultipartFile file, long groupReportId) {
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
        } catch (IOException e) {
            log.error("Excepetion [" + e.getMessage() + "]");
        }

    }

    private String getMimeType(File file) throws IOException {
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        return fileNameMap.getContentTypeFor(file.getName());
    }


    public void getImageFile(HttpServletResponse response, int fileId) {

        FileDTO fileInfo = fileMapper.findFileInfoByGroupReportId(fileId);

        File downloadFile = new File(UPLOAD_PATH + fileInfo.getUuid());

        log.info(fileInfo.getFileName());

        try {
            String mime = this.getMimeType(downloadFile);
            response.setContentType(mime);
            response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(fileInfo.getFileName(), "UTF-8") + "\"");
            InputStream is = new FileInputStream(downloadFile);
            int len;
            byte[] buffer = new byte[1024];
            OutputStream os = response.getOutputStream();
            while ((len = is.read(buffer)) > -1) {
                os.write(buffer, 0, len);
            }

            os.flush();
            os.close();

        } catch (Exception e) {
            log.error("Excepetion [" + e.getMessage() + "]");
        }

    }

    public boolean deleteImageFile(int idx) {

        boolean result = false;

        FileDTO fileInfo = fileMapper.findFileInfoByGroupReportId(idx);

        File deleteFile = new File(UPLOAD_PATH + fileInfo.getUuid());

        log.info(fileInfo.getFileName());

        try {

            if (deleteFile.delete()) {
                result = true;
            } else {
                result = false;
            }


        } catch (Exception e) {
            log.error("Excepetion [" + e.getMessage() + "]");
        }

        return result;

    }

    public void updateImageFile(MultipartFile file, int fileId) {
        FileDTO fileInfo = fileMapper.findFileInfoByGroupReportId(fileId);
        File orgFile = new File(UPLOAD_PATH + fileInfo.getUuid());

        log.info(fileInfo.getFileName());

        try {
            String fileName = file.getOriginalFilename();
            String ext = getExtension(fileName);

            file.transferTo(orgFile);

            FileDTO fileDTO = new FileDTO();

            fileDTO.setFileName(fileName);
            fileDTO.setFileExt(ext);
            fileDTO.setGroupReportIdFk(fileInfo.getGroupReportIdFk());

            //fileMapper.updateFileInfoBySheetID(fileDTO);


        } catch (Exception e) {
            log.error("Excepetion [" + e.getMessage() + "]");
        }

    }

    public String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

}
