package com.northbrain.family.model;

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
public class Family {
    @Id
    private String                  id;                 //id号
    @NotNull
    @Indexed
    private String                  category;           //类别（企业）
    @NotNull
    private String                  houseHolder;        //户主姓名
    @NotNull
    private String                  region;             //归属行政区域编号
    @NotNull
    private String                  masterIdCardNo;     //户主身份证号码
    @NotNull
    private String                  phone;              //电话号码
    @NotNull
    private Date                    createTime;         //创建时间
    @NotNull
    private Date                    timestamp;          //状态时间
    @NotNull
    private String                  status;             //状态
    @NotNull
    private String                  serialNo;           //操作流水号
    private String                  description;        //描述
}
