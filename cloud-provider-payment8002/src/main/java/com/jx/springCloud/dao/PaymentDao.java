package com.jx.springCloud.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jx.springCloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author LDW
 * @date 2022/3/23 18:17
 */
@Mapper
public interface PaymentDao extends BaseMapper<Payment> {
    //mybatis-plus提供的插入操作会自动将主键值返回给实体对象
}
