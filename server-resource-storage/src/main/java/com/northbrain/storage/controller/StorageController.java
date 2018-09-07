package com.northbrain.storage.controller;

import com.northbrain.storage.model.Constants;
import com.northbrain.storage.model.Storage;
import com.northbrain.storage.service.StorageService;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class StorageController {
    private final StorageService storageService;

    public StorageController(StorageService storageService) {
        this.storageService = storageService;
    }

    /**
     * 方法：上传图片至存储服务端
     * @param serialNo 流水号
     * @param type 类型
     * @param category 类别（企业）
     * @param fileParts 文件（图片）
     * @return 存储成功的文件名（全名）
     */
    @PostMapping(value = Constants.STORAGE_HTTP_REQUEST_MAPPING)
    public ResponseEntity<Flux<Storage>> uploadFile(@RequestParam String serialNo,
                                                    @RequestParam String type,
                                                    @RequestParam String category,
                                                    @RequestPart(Constants.STORAGE_FILE_ATTRIBUTE_NAME) Flux<FilePart> fileParts) {
        return ResponseEntity.ok()
                .body(this.storageService
                        .createFile(serialNo, type, category, fileParts));
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
    @PostMapping(Constants.STORAGE_CONTENT_HTTP_REQUEST_MAPPING)
    public ResponseEntity<Mono<Storage>> uploadFileContent(@RequestParam String serialNo,
                                                           @RequestParam String type,
                                                           @RequestParam String category,
                                                           @RequestParam String content,
                                                           @RequestParam String extension) {
        return ResponseEntity.ok()
                .body(this.storageService
                        .createFileContent(serialNo, type, category, content, extension));
    }

    /**
     * 方法：删除服务端文件
     * @param serialNo 流水号
     * @param type 文件类型
     * @param category 类别（企业）
     * @param storage 存储id
     * @param fileUrl 文件访问地址
     * @return 存储成功的文件名（全名）
     */
    @DeleteMapping(Constants.STORAGE_HTTP_REQUEST_MAPPING)
    public ResponseEntity<Mono<Storage>> deleteFile(@RequestParam String serialNo,
                                                    @RequestParam String type,
                                                    @RequestParam String category,
                                                    @RequestParam String storage,
                                                    @RequestParam String fileUrl) {
        return ResponseEntity.ok()
                .body(this.storageService
                        .deleteFile(serialNo, type, category, storage, fileUrl));
    }
}
