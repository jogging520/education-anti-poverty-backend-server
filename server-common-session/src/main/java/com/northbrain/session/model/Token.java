package com.northbrain.session.model;

import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * 类名：令牌
 * 用途：返回JWT信息
 */
@Data
@Accessors(chain=true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Token {
    @NotNull
    private String  session;                    //会话编号
    @NotNull
    private String  user;                       //用户编号
    @NotNull
    private Long    lifeTime;                   //寿命时长
    @NotNull
    private String  jwt;                        //jwt
    @NotNull
    private String  downPublicKey;              //下行加密公钥
    @NotNull
    private String  downPublicKeyExponent;       //下行加密公钥指数Exponent
    @NotNull
    private String  downPublicKeyModulus;       //下行加密公钥系数Modulus
    @NotNull
    private String  upPrivateKey;               //上行解密私钥
    @NotNull
    private String  upPrivateKeyExponent;       //上行解密私钥指数Exponent
    @NotNull
    private String  upPrivateKeyModulus;        //上行解密私钥系数Modulus
    @NotNull
    private String  upPrivateKeyPrimeP;         //上行解密私钥p值
    @NotNull
    private String  upPrivateKeyPrimeQ;         //上行解密私钥q值
    @NotNull
    private String  status;                     //状态
}