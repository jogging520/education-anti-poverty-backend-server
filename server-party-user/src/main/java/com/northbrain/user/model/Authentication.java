package com.northbrain.user.model;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Data
@Accessors(chain=true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document
public class Authentication {
    @NotNull
    private String user;     //用户编号
    @NotNull
    private int authType; //认证方式
    @NotNull
    private String status;   //状态
}
