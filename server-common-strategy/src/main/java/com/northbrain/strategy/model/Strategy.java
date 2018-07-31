package com.northbrain.strategy.model;

import com.sun.javafx.beans.IDProperty;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Map;

/**
 * @author
 * @create 2018-07-30 15:38
 **/
@Data
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document
public class Strategy {
    @Id
    private     String                  id;                 //策略编号id
    @NotNull
    private     String                  type;               //类型
    @NotNull
    private     String                  name;               //策略名称
    @NotNull
    private     String                  appType;            //应用类型
    private     Map<String, String>     parameters;         //参数
    @NotNull
    private     Date                    createTime;         //创建时间
    @NotNull
    private     Date                    timestamp;          //状态时间
    @NotNull
    private     String                  status;             //状态
    @NotNull
    private     String                  serialNo;           //操作流水号
    private     String                  description;        //描述

}
