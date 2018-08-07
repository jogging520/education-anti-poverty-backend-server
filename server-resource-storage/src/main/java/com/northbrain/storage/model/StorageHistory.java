package com.northbrain.storage.model;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Accessors(chain=true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document
public class StorageHistory {
    @Id
    private String              id;                 //id编号
    @NotNull
    private String              operationType;      //操作类型
    @NotNull
    private String              storageId;          //存储编号
    @NotNull
    private String              type;               //类型
    @NotNull
    private String              name;               //名称
    @NotNull
    private String              category;           //类别（企业）
    @NotNull
    private Date                createTime;         //创建时间
    @NotNull
    private Date                timestamp;          //状态时间
    @NotNull
    private String              status;             //状态
    @NotNull
    private String              serialNo;           //操作流水号
    private String              description;        //描述
}
