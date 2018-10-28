package com.northbrain.policy.model;


public class Constants {
    /**
     * 枚举值定义，如状态、类型
     */
    public final static String  POLICY_STATUS_ACTIVE                          =   "ACTIVE";   //用户状态：在用
    public final static String  POLICY_HISTORY_CREATE                         =   "CREATE";   //历史归档：创建
    public final static String  POLICY_HISTORY_UPDATE                         =   "UPDATE";   //历史归档：更新
    public final static String  POLICY_HISTORY_DELETE                         =   "DELETE";   //历史归档：删除


    public final static String  POLICY_TYPE_COMMON                            =   "COMMON";   //普通类型用户

    /**
     * restful资源定义
     */
    public final static String  POLICY_HTTP_REQUEST_MAPPING                   =   "/policies";//用户rest资源
    public final static String  POLICY_HTTP_SPECIFIED_REQUEST_MAPPING         =   "/policies/{id}";          //指定用户id的rest资源

    /**
     * 操作定义
     */
    public final static String  POLICY_OPERATION_SERIAL_NO                    =   "本次操作用户实体的流水号为：";

    /**
     * 错误码定义
     */
    public final static String  POLICY_ERRORCODE_SUCCESS                      =   "SUCCESS";
    public final static String  POLICY_ERRORCODE_HAS_EXISTS                   =   "ERROR_PARTY_POLICY_HAS_EXISTS";              //郑策已经存在
    public final static String  POLICY_ERRORCODE_AUTHENTICATION_FAILURE       =   "ERROR_PARTY_POLICY_AUTHENTICATION_FAILURE";  //认证失败
}
