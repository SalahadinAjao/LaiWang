package com.hlt.dao;

import com.hlt.entity.UserCouponEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author: houlintao
 * @Date:2020/7/18 下午3:54
 * @email 437547058@qq.com
 * @Version 1.0
 */
@Repository
public interface UserCouponDao extends BaseDao<UserCouponEntity> {



    UserCouponEntity queryByCouponNumber(@Param("coupon_number") String coupon_number);

    @Override
    List<UserCouponEntity> queryList(Map<String, Object> map);

    @Override
    int save(UserCouponEntity userCouponEntity);
}
