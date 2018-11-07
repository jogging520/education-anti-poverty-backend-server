package com.northbrain.policy.model;

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
public class Policy {
    @Id
    private String                  id;                 //id编号
    @NotNull
    private String                  type;               //类型
    @NotNull
    private String[]                appTypes;            //终端类型
    @NotNull
    private String                  category;           //企业类型
    @NotNull
    @Indexed(unique = true)
    private String                  name;               //标题
    @NotNull
    private Date                    createTime;         //创建时间
    @NotNull
    private Date                    timestamp;          //状态时间
    @NotNull
    private String                  status;             //状态
    @NotNull
    private String                  serialNo;           //操作流水号
    private String                  description;        //内容

}