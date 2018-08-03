package com.northbrain.storage.service;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.exception.FdfsUnsupportStorePathException;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.northbrain.storage.model.Constants;
import com.northbrain.storage.model.Storage;
import com.northbrain.storage.repository.IStorageRepository;
import com.northbrain.util.timer.Clock;
import com.northbrain.util.tracer.StackTracer;
import lombok.extern.java.Log;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.Charset;

@Service
@Log
public class StorageService {
    private final FastFileStorageClient fastFileStorageClient;
    private final IStorageRepository storageRepository;

    public StorageService(FastFileStorageClient fastFileStorageClient,
                          IStorageRepository storageRepository) {
        this.fastFileStorageClient = fastFileStorageClient;
        this.storageRepository = storageRepository;
    }

    /**
     * 方法：上传图片至存储服务端
     * @param serialNo 流水号
     * @param type 文件类型
     * @param category 类别（企业）
     * @param multipartFile 文件
     * @return 存储成功的文件名（全名）
     */
    public Mono<Storage> uploadFile(String serialNo,
                                    String type,
                                    String category,
                                    MultipartFile multipartFile) {
        try {
            log.info(Constants.STORAGE_OPERATION_SERIAL_NO + serialNo);

            StorePath storePath = this.fastFileStorageClient
                    .uploadFile(multipartFile.getInputStream(), multipartFile.getSize(),
                            FilenameUtils.getExtension(multipartFile.getOriginalFilename()), null);

            return this.storageRepository
                    .save(Storage.builder()
                            .type(type)
                            .name(storePath.getFullPath())
                            .category(category)
                            .createTime(Clock.currentTime())
                            .timestamp(Clock.currentTime())
                            .status(Constants.STORAGE_STATUS_ACTIVE)
                            .serialNo(serialNo)
                            .description(Constants.STORAGE_AUTO_DESCRIPTION)
                            .build())
                    .map(storage -> storage.setStatus(Constants.STORAGE_ERRORCODE_SUCCESS));
        } catch (IOException e) {
            StackTracer.printException(e);
        }

        return Mono.just(Storage.builder()
                .status(Constants.STORAGE_ERRORCODE_STORE_FAILURE)
                .build());
    }

    /**
     * 方法：将文本内容存储至服务端文件
     * @param serialNo 流水号
     * @param type 文件类型
     * @param category 类别（企业）
     * @param content 文本内容
     * @param extension 扩展名
     * @return 存储成功的文件名（全名）
     */
    public Mono<Storage> uploadFileContent(String serialNo,
                                           String type,
                                           String category,
                                           String content,
                                           String extension) {
        log.info(Constants.STORAGE_OPERATION_SERIAL_NO + serialNo);

        byte[] contentBytes = content.getBytes(Charset.forName(Constants.STORAGE_CONTENT_CHARSET));
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(contentBytes);

        StorePath storePath = this.fastFileStorageClient.uploadFile(byteArrayInputStream,
                contentBytes.length, extension, null);

        if(storePath.getFullPath() != null) {
            return this.storageRepository
                    .save(Storage.builder()
                            .type(type)
                            .name(storePath.getFullPath())
                            .category(category)
                            .createTime(Clock.currentTime())
                            .timestamp(Clock.currentTime())
                            .status(Constants.STORAGE_STATUS_ACTIVE)
                            .serialNo(serialNo)
                            .description(Constants.STORAGE_AUTO_DESCRIPTION)
                            .build())
                    .map(storage -> storage.setStatus(Constants.STORAGE_ERRORCODE_SUCCESS));
        }

        return Mono.just(Storage.builder()
                .status(Constants.STORAGE_ERRORCODE_STORE_FAILURE)
                .build());
    }

    /**
     * 方法：删除服务端文件
     * @param serialNo 流水号
     * @param type 文件类型
     * @param category 类别（企业）
     * @param fileUrl 文件访问地址
     * @return 存储成功的文件名（全名）
     */
    public Mono<Storage> deleteFile(String serialNo,
                                    String type,
                                    String category,
                                    String fileUrl) {
        log.info(Constants.STORAGE_OPERATION_SERIAL_NO + serialNo);

        try {
            StorePath storePath = StorePath.praseFromUrl(fileUrl);

            this.fastFileStorageClient.deleteFile(storePath.getGroup(), storePath.getPath());

            return this.storageRepository
                    .save(Storage.builder()
                            .type(type)
                            .name(storePath.getFullPath())
                            .category(category)
                            .createTime(Clock.currentTime())
                            .timestamp(Clock.currentTime())
                            .status(Constants.STORAGE_STATUS_ACTIVE)
                            .serialNo(serialNo)
                            .description(Constants.STORAGE_AUTO_DESCRIPTION)
                            .build())
                    .map(storage -> storage.setStatus(Constants.STORAGE_ERRORCODE_SUCCESS));

        } catch (FdfsUnsupportStorePathException e) {
            StackTracer.printException(e);
        }

        return Mono.just(Storage.builder()
                .status(Constants.STORAGE_ERRORCODE_REMOVE_FAILURE)
                .build());
    }
}
