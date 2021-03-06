package com.northbrain.user.service;

import com.northbrain.user.model.*;
import com.northbrain.util.security.Crypt;
import com.northbrain.util.security.Password;
import com.northbrain.util.timer.Clock;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Service;

import com.northbrain.user.repository.IUserHistoryRepository;
import com.northbrain.user.repository.IUserRepository;

import lombok.extern.java.Log;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Log
public class UserService {
    private final IUserRepository userRepository;
    private final IUserHistoryRepository userHistoryRepository;
    private final Crypt crypt;

    public UserService(IUserRepository userRepository,
                       IUserHistoryRepository userHistoryRepository,
                       Crypt crypt) {
        this.userRepository = userRepository;
        this.userHistoryRepository = userHistoryRepository;
        this.crypt = crypt;
    }

    /**
     * 方法：根据ID号查找用户信息
     * @param serialNo 流水号
     * @param appType 应用类型
     * @param category 类别（企业）
     * @param id 用户编号
     * @return 用户信息
     */
    public Mono<User> queryUserById(String serialNo,
                                    String appType,
                                    String category,
                                    String id) {
        return this.userRepository
                .findById(id)
                .filter(user -> user.getCategory().equalsIgnoreCase(category))
                .filter(user -> user.getStatus().equalsIgnoreCase(Constants.USER_STATUS_ACTIVE))
                .map(user -> {
                    log.info(Constants.USER_OPERATION_SERIAL_NO + serialNo);

                    return user
                            .setName(this.crypt.encrypt4UserUpStream(user.getName(), appType))
                            .setPassword(null)
                            .setSalt(null)
                            .setRealName(this.crypt.encrypt4UserUpStream(this.crypt.decrypt4System(user.getRealName()), appType));
                });
    }

    /**
     * 方法：根据名称查找用户信息
     * @param serialNo 流水号
     * @param appType 应用类型
     * @param category 类别（企业）
     * @param name 用户名称
     * @return 用户信息
     */
    public Mono<User> queryUserByName(String serialNo,
                                      String appType,
                                      String category,
                                      String name) {
        return this.userRepository
                .findByCategoryAndStatusAndName(category, Constants.USER_STATUS_ACTIVE,
                        this.crypt.decrypt4UserDownStream(this.crypt.urlDecode(name),
                                appType, false))
                .map(user -> {
                    log.info(Constants.USER_OPERATION_SERIAL_NO + serialNo);

                    return user
                            .setName(this.crypt.encrypt4UserUpStream(user.getName(), appType))
                            .setPassword(null)
                            .setSalt(null)
                            .setRealName(null);
                });
    }


    /**
     * 方法：查询全部用户信息
     * @param serialNo 流水号
     * @param appType 应用类型
     * @param category 类别（企业）
     * @return 用户信息
     */
    public Flux<User> queryUsers(String serialNo,
                                 String appType,
                                 String category) {
        return this.userRepository
                .findByCategoryAndStatus(category, Constants.USER_STATUS_ACTIVE)
                .map(user -> {
                    log.info(Constants.USER_OPERATION_SERIAL_NO + serialNo);

                    return user
                            .setName(this.crypt.encrypt4UserUpStream(user.getName(), appType))
                            .setPassword(null)
                            .setSalt(null)
                            .setRealName(this.crypt.encrypt4UserUpStream(this.crypt.decrypt4System(user.getRealName()), appType));
                });
    }

    /**
     * 方法：根据用户名+密码验证用户信息
     * @param serialNo 流水号
     * @param appType 应用类型
     * @param category 类别（企业）
     * @param name 用户名
     * @param password 密码
     * @return 校验结果
     */
    public Mono<Authentication> verifyUserByNameAndPassword(String serialNo,
                                                            String appType,
                                                            String category,
                                                            String name,
                                                            String password) {
        return this.userRepository
                .findByCategoryAndStatusAndName(category, Constants.USER_STATUS_ACTIVE,
                        this.crypt.decrypt4UserDownStream(name, appType, true))
                .flatMap(user -> {
                    log.info(Constants.USER_OPERATION_SERIAL_NO + serialNo);

                    if(user.getAppTypes() != null &&
                            ArrayUtils.contains(user.getAppTypes(), appType) &&
                            Password.verify(user.getPassword(),
                                    this.crypt.decrypt4UserDownStream(password, appType, true),
                                    this.crypt.decrypt4System(user.getSalt()))) {
                        return Mono.just(Authentication
                                .builder()
                                .user(user.getId())
                                .authType(Constants.USER_LOGGING_TYPE_PASSWORD)
                                .status(Constants.USER_ERRORCODE_SUCCESS)
                                .build());
                    }

                    return Mono.just(Authentication
                            .builder()
                            .status(Constants.USER_ERRORCODE_AUTHENTICATION_FAILURE)
                            .build());
                })
                .switchIfEmpty(Mono.just(Authentication
                        .builder()
                        .status(Constants.USER_ERRORCODE_AUTHENTICATION_FAILURE)
                        .build()));
    }

    /**
     * 方法：根据手机号码验证用户信息
     * @param serialNo 流水号
     * @param appType 应用类型
     * @param category 类别（企业）
     * @param mobile 手机号码
     * @return 校验结果
     */
    public Mono<Authentication> verifyByMobile(String serialNo,
                                               String appType,
                                               String category,
                                               String mobile) {
        return this.userRepository
                .findByCategoryAndStatusAndMobile(category,
                        Constants.USER_STATUS_ACTIVE, mobile)
                .flatMap(user -> {
                    log.info(Constants.USER_OPERATION_SERIAL_NO + serialNo);

                    if(user.getAppTypes() != null &&
                            ArrayUtils.contains(user.getAppTypes(), appType)) {
                        return Mono.just(Authentication
                                .builder()
                                .user(user.getId())
                                .authType(Constants.USER_LOGGING_TYPE_PASSWORD)
                                .status(Constants.USER_ERRORCODE_SUCCESS)
                                .build());
                    }

                    return Mono.just(Authentication
                            .builder()
                            .status(Constants.USER_ERRORCODE_AUTHENTICATION_FAILURE)
                            .build());
                })
                .switchIfEmpty(Mono.just(Authentication
                        .builder()
                        .status(Constants.USER_ERRORCODE_AUTHENTICATION_FAILURE)
                        .build()));
    }

    /**
     * 方法：创建新用户
     * @param serialNo 流水号
     * @param appType 应用类型
     * @param category 类别（企业）
     * @param user 用户
     * @return 新用户
     */
    public Mono<User> createUser(String serialNo,
                                 String appType,
                                 String category,
                                 User user) {
        String salt = Password.generateSalt();
        log.info(user.toString());

        return this.userRepository
                .findByCategoryAndStatusAndIdOrName(category, Constants.USER_STATUS_ACTIVE,
                        user.getId(), this.crypt.decrypt4UserDownStream(user.getName(), appType, false))
                .map(newUser -> newUser.setStatus(Constants.USER_ERRORCODE_HAS_EXISTS))
                .switchIfEmpty(this.userRepository
                        .save(user
                                .setName(this.crypt.decrypt4UserDownStream(user.getName(), appType, false))
                                .setPassword(Password.encrypt(this.crypt.decrypt4UserDownStream(user.getPassword(), appType, false), salt))
                                .setSalt(this.crypt.encrypt4System(salt))
                                .setRealName(this.crypt.encrypt4System(
                                        this.crypt.decrypt4UserDownStream(user.getRealName(), appType, false)))
                                .setCategory(category)
                                .setStatus(Constants.USER_STATUS_ACTIVE)
                                .setCreateTime(Clock.currentTime())
                                .setTimestamp(Clock.currentTime())
                                .setSerialNo(serialNo))
                        .map(newUser -> {
                            log.info(Constants.USER_OPERATION_SERIAL_NO + serialNo);

                            this.userHistoryRepository
                                    .save(UserHistory.builder()
                                            .operationType(Constants.USER_HISTORY_CREATE)
                                            .userId(newUser.getId())
                                            .type(newUser.getType())
                                            .name(newUser.getName())
                                            .password(newUser.getPassword())
                                            .realName(newUser.getRealName())
                                            .avatar(newUser.getAvatar())
                                            .appTypes(newUser.getAppTypes())
                                            .category(newUser.getCategory())
                                            .roles(newUser.getRoles())
                                            .permissions(newUser.getPermissions())
                                            .affiliations(newUser.getAffiliations())
                                            .mobile(newUser.getMobile())
                                            .email(newUser.getEmail())
                                            .weChat(newUser.getWeChat())
                                            .createTime(newUser.getCreateTime())
                                            .timestamp(Clock.currentTime())
                                            .status(newUser.getStatus())
                                            .serialNo(serialNo)
                                            .description(newUser.getDescription())
                                            .build())
                                    .subscribe(userHistory -> {
                                        log.info(Constants.USER_OPERATION_SERIAL_NO + serialNo);
                                        log.info(userHistory.toString());
                                    });

                            return newUser
                                    .setName(null)
                                    .setPassword(null)
                                    .setSalt(null)
                                    .setRealName(null)
                                    .setStatus(Constants.USER_ERRORCODE_SUCCESS);
                        }));
    }
}
