package com.tlf.user.server.entity;

import com.tlf.commondata.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = TUserInfo.TABLE_NAME)
@Data
@EqualsAndHashCode(callSuper = false)
public class TUserInfo extends BaseEntity {
    protected static final String TABLE_NAME = "t_user_info";

    private Long account;
    private String nickName;
    private String password;
}
