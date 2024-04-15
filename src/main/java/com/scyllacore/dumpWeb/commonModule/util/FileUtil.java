package com.scyllacore.dumpWeb.commonModule.util;


import com.scyllacore.dumpWeb.commonModule.constant.ResponseType;
import com.scyllacore.dumpWeb.commonModule.db.dto.manage.FileDTO;
import com.scyllacore.dumpWeb.commonModule.db.mapper.file.FileMapper;
import com.scyllacore.dumpWeb.commonModule.exception.RestApiException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class FileUtil {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final FileMapper fileMapper;

    @Value("${file.upload.path}")
    private String UPLOAD_PATH;

    public Long uploadFile(MultipartFile file, long groupReportId) throws IOException {
        String fileName = file.getOriginalFilename();
        String uuid = UUID.randomUUID().toString();
        String ext = getExtension(fileName);
        String uploadFilePath = UPLOAD_PATH + uuid;

        File uploadFile = new File(uploadFilePath);
        file.transferTo(uploadFile);

        FileDTO fileDTO = FileDTO.builder()
                .fileName(fileName)
                .uuid(uuid)
                .fileExt(ext)
                .groupReportIdFk(groupReportId)
                .build();

        fileMapper.insertFileInfoByGroupReportId(fileDTO);
        log.info(fileName);

        return fileDTO.getFileId();
    }

    public String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    public void getImageFile(HttpServletResponse response, Long fileId) throws IOException {

        FileDTO fileInfo = fileMapper.findFileInfoByFileId(fileId);
        File storedFile = new File(UPLOAD_PATH + fileInfo.getUuid());

        log.info(fileInfo.getFileName());

        try (InputStream is = new FileInputStream(storedFile)) {
            String mime = getMimeType(fileInfo.getFileName());
            response.setContentType(mime);
            response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(fileInfo.getFileName(), "UTF-8") + "\"");

            int len;
            byte[] buffer = new byte[1024];
            OutputStream os = response.getOutputStream();
            while ((len = is.read(buffer)) > -1) {
                os.write(buffer, 0, len);
            }

            os.flush();
            os.close();
        }
    }

    private String getMimeType(String fileName) {
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        return fileNameMap.getContentTypeFor(fileName);
    }

    public void deleteFile(Long fileId) {
        if (deleteImageFile(fileId)) {
            throw new RestApiException(ResponseType.FILE_NOT_FOUND);
        }

        deleteFileRecord(fileId);
    }


    public boolean deleteImageFile(Long fileId) {
        boolean result = true;

        FileDTO fileInfo = fileMapper.findFileInfoByFileId(fileId);
        File file = new File(UPLOAD_PATH + fileInfo.getUuid());

        if (file.delete()) {
            log.info(fileInfo.getFileName());
            result = false;
        }

        return result;
    }

    public void deleteFileRecord(Long fileId) {
        fileMapper.deleteFile(fileId);
    }
}