package com.gaohf.repository;

import com.gaohf.entity.UserEntity;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.swing.text.html.Option;
import java.util.Optional;

/**
 * com.gaohf.repository
 *
 * @Author : Gaohf
 * @Description :
 * @Date : 2017/12/22
 */
public interface UserRepository extends JpaRepository<UserEntity,Long>,JpaSpecificationExecutor<UserEntity>{

    Optional<UserEntity> findByUserName(String userName);

    UserEntity findByUsernameAndEnabled(String username, boolean enabled);

    UserEntity findByPhoneAndEnabled(String phone, boolean enabled);

    UserEntity findByEmailAndEnabled(String email, boolean enabled);

    UserEntity findByOpenIdAndEnabled(String openId, boolean enabled);

    UserEntity findByUserIdAndEnabled(long userId,boolean enabled);

    @Modifying
    @Query("update #{#entityName} a set a.accountNonLocked = '0', a.remarks = :remarks, a.updateDate = NOW() where a.username = :username ")
    int updateByUsername(@Param("username") String username, @Param("remarks") String remarks);

    @Modifying
    @Query("update #{#entityName} a set a.accountNonLocked = '0', a.remarks = :remarks, a.updateDate = NOW() where a.phone = :phone ")
    int updateByPhone(@Param("phone") String phone, @Param("remarks") String remarks);

    @Modifying
    @Query("update #{#entityName} a set a.accountNonLocked = '0', a.remarks = :remarks, a.updateDate = NOW() where a.email = :email ")
    int updateByEmail(@Param("email") String email, @Param("remarks") String remarks);

    @Modifying
    @Query("update #{#entityName} a set a.accountNonLocked = '0', a.remarks = :remarks, a.updateDate = NOW() where a.openId = :openId ")
    int updateByOpenId(@Param("openId") String openId, @Param("remarks") String remarks);

    @Modifying
    @Query("update #{#entityName} a set a.accountNonLocked = '0', a.remarks = :remarks, a.updateDate = NOW() where a.userId = :userId ")
    int updateByUserId(@Param("userId") long userId, @Param("remarks") String remarks);

    @Modifying
    @Query("update #{#entityName} a set a.password = :newPassword, a.updateDate = NOW() where a.userId = :userId ")
    int updatePassword(@Param("userId") long userId, @Param("newPassword") String newPassword);

    @Modifying
    @Query("update #{#entityName} a set a.password = :newPassword, a.updateDate = NOW() where a.phone = :phone ")
    int updatePassword(@Param("phone") String phone, @Param("newPassword") String newPassword);

    @Modifying
    @Query("update #{#entityName} a set a.openId = :openId, a.updateDate = NOW() where a.userId = :userId ")
    int bindThirdAccount(@Param("userId") long userId, @Param("openId") String openId);

    @Modifying
    @Query("update #{#entityName} a set a.openId = null, a.updateDate = NOW() where a.userId = :userId ")
    int unbindThirdAccount(long userId);
}
