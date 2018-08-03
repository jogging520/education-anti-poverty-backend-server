package com.northbrain.storage.model;

public class Constants {
    /**
     * 枚举值定义，如状态、类型
     */
    public final static String  STORAGE_STATUS_ACTIVE                           =   "ACTIVE";   //存储状态：在用
    public final static String  STORAGE_HISTORY_CREATE                          =   "CREATE";   //历史归档：创建
    public final static String  STORAGE_HISTORY_UPDATE                          =   "UPDATE";   //历史归档：更新
    public final static String  STORAGE_HISTORY_DELETE                          =   "DELETE";   //历史归档：删除

    public final static String  STORAGE_CONTENT_CHARSET                         =   "UTF-8";   //字符集
    public final static String  STORAGE_AUTO_DESCRIPTION                        =   "nb auto.";  //自动填写description
    /**
     * restful资源定义
     */
    public final static String  STORAGE_HTTP_REQUEST_MAPPING                    =   "/storage";       //存储rest
    public final static String  STORAGE_FILE_HTTP_REQUEST_MAPPING               =   "/storage/files";       //文件上传rest
    public final static String  STORAGE_CONTENT_HTTP_REQUEST_MAPPING            =   "/storage/contents";    //内容上传rest

    /**
     * 操作定义
     */
    public final static String  STORAGE_OPERATION_SERIAL_NO                     =   "本次操作存储实体的流水号为：";

    /**
     * 错误码定义
     */
    public final static String  STORAGE_ERRORCODE_SUCCESS                       =   "SUCCESS";
    public final static String  STORAGE_ERRORCODE_STORE_FAILURE                 =   "ERROR_RESOURCE_STORAGE_STORE_FAILURE";              //存储失败
    public final static String  STORAGE_ERRORCODE_REMOVE_FAILURE                =   "ERROR_RESOURCE_STORAGE_REMOVE_FAILURE";              //移除失败
}
