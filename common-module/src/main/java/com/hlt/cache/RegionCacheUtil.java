package com.hlt.cache;

import com.hlt.dao.SysRegionDao;
import com.hlt.entity.SysRegionEntity;
import com.hlt.utils.SpringContextUtils;
import org.springframework.beans.factory.InitializingBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: houlintao
 * @Date:2020/7/10 下午4:03
 * @email 437547058@qq.com
 * @Version 1.0
 * InitializingBean接口为bean提供了初始化方法的方式，它只包括afterPropertiesSet方法，
 * 凡是继承该接口的类，在初始化bean的时候都会执行该方法，即在spring初始化bean的时候，如
 * 果bean实现了InitializingBean接口，会自动调用afterPropertiesSet方法。
 * 此接口的实现类需要<bean></bean>配置.
 */

public class RegionCacheUtil implements InitializingBean {

    public static List<SysRegionEntity> sysRegionEntityList;

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("即将调用init()方法");
        init();
    }

    public  void init(){
        System.out.println("执行了init方法");
        SysRegionDao sysRegionDao = SpringContextUtils.getBean(SysRegionDao.class);
        System.out.println("SpringContextUtils返回的sysRegionDao="+sysRegionDao);
        if (sysRegionDao != null){
            System.out.println("sysRegionDao不为null，即将调用sysRegionDao的queryList方法");
            //出错点找到了，静态变量sysRegionEntityList赋值失败导致的空指针异常
           // List<SysRegionEntity> regionEntityList = sysRegionDao.queryList(new HashMap<String, Object>());
            sysRegionEntityList = sysRegionDao.queryList(new HashMap<String, Object>());
            System.out.println("调用sysRegionDao的queryList方法返回的值赋值给sysRegionEntityList后的长度="+sysRegionEntityList.size());
        }
    }
    /**
     * 获取所有国家
     *
     * @return
     */
    public static List<SysRegionEntity> getAllCountry() {
        List<SysRegionEntity> resultObj = new ArrayList<SysRegionEntity>();
        if (null != sysRegionEntityList) {
            for (SysRegionEntity areaVo : sysRegionEntityList) {
                if (areaVo.getType().equals(0)) {
                    resultObj.add(areaVo);
                }
            }
        }
        return resultObj;
    }

    /**
     * 获取全部省份
     *
     * @return
     */
    public static List<SysRegionEntity> getAllProvice() {
        System.out.println("执行获取所有省份的getAllProvince方法");
        List<SysRegionEntity> resultObj = new ArrayList<SysRegionEntity>();
        if (null != sysRegionEntityList) {
            for (SysRegionEntity regionEntity : sysRegionEntityList) {
                if (regionEntity.getType().equals(1)) {
                    resultObj.add(regionEntity);
                }
            }
        }
        return resultObj;
    }

    /**
     * 获取所有城市
     *
     * @return
     */
    public static List<SysRegionEntity> getAllCity() {
        System.out.println("执行获取所有城市的getAllCity方法");
        List<SysRegionEntity> resultObj = new ArrayList<SysRegionEntity>();
        if (null != sysRegionEntityList) {
            for (SysRegionEntity areaVo : sysRegionEntityList) {
                if (areaVo.getType().equals(2)) {
                    resultObj.add(areaVo);
                }
            }
        }
        return resultObj;
    }

    /**
     * 根据国家获取全部省份
     *
     * @return
     */
    public static List<SysRegionEntity> getAllProviceByParentId(Integer areaId) {
        List<SysRegionEntity> resultObj = new ArrayList<SysRegionEntity>();
        if (null == areaId) {
            return resultObj;
        }
        if (null != sysRegionEntityList) {
            for (SysRegionEntity areaVo : sysRegionEntityList) {
                if (null != areaVo.getParentId() && areaVo.getType().equals(1) && areaId.equals(areaVo.getParentId())) {
                    resultObj.add(areaVo);
                }
            }
        }
        return resultObj;
    }

    /**
     * 通过id获取地级市，例如上海，天津这种
     *
     * @return
     */
    public static List<SysRegionEntity> getChildrenCity(Integer areaId) {
        List<SysRegionEntity> resultObj = new ArrayList<SysRegionEntity>();
        if (null == areaId) {
            return resultObj;
        }
        if (null != sysRegionEntityList) {
            for (SysRegionEntity regionEntity : sysRegionEntityList) {
                if (null != regionEntity.getParentId() && regionEntity.getType().equals(2) && areaId.equals(regionEntity.getParentId())) {
                    resultObj.add(regionEntity);
                }
            }
        }
        return resultObj;
    }

    /**
     * 通过省名获取地市
     *
     * @return
     */
    public static List<SysRegionEntity> getChildrenCity(String proviceName) {
        System.out.println("proviceName = " + proviceName);
        System.out.println("sysRegionEntityList长度 = " + sysRegionEntityList.size());

        List<SysRegionEntity> resultObj = new ArrayList<SysRegionEntity>();

        if (null == proviceName) {
            return resultObj;
        }

        if (null != sysRegionEntityList) {

            for (SysRegionEntity regionEntity : sysRegionEntityList) {

                if (regionEntity.getParentId()!=null && regionEntity.getType().equals(2) &&
                        regionEntity.getParentName().equals(proviceName)) {
                    resultObj.add(regionEntity);
                    System.out.println("resultObj长度 = " + resultObj.size());
                }
            }
        }
        System.out.println("外部的resultObj长度 = " + resultObj.size());
        return resultObj;
    }

    /**
     * 获取区县
     *
     * @return
     */
    public static List<SysRegionEntity> getChildrenDistrict(Integer areaId) {
        List<SysRegionEntity> resultObj = new ArrayList<SysRegionEntity>();
        if (null == areaId) {
            return resultObj;
        }
        if (null != sysRegionEntityList) {
            for (SysRegionEntity areaVo : sysRegionEntityList) {
                if (null != areaVo.getParentId() && areaVo.getType().equals(3) && areaId.equals(areaVo.getParentId())) {
                    resultObj.add(areaVo);
                }
            }
        }
        return resultObj;
    }

    /**
     * 获取区县
     *
     * @return
     */
    public static List<SysRegionEntity> getChildrenDistrict(String provinceName, String cityName) {
        List<SysRegionEntity> resultObj = new ArrayList<SysRegionEntity>();
        if (null == provinceName || null == cityName) {
            return resultObj;
        }
        if (null != sysRegionEntityList) {
            for (SysRegionEntity areaVo : sysRegionEntityList) {
                if (null != areaVo.getParentId() && areaVo.getType().equals(3)
                        && cityName.equals(areaVo.getParentName())
                        && null != getAreaByAreaId(areaVo.getParentId())
                        && provinceName.equals(getAreaByAreaId(areaVo.getParentId()).getParentName())) {
                    resultObj.add(areaVo);
                }
            }
        }
        return resultObj;
    }


    /**
     * 获取区县
     *
     * @return
     */
    public static List<SysRegionEntity> getChildrenByParentId(Integer parentId) {
        List<SysRegionEntity> resultObj = new ArrayList<SysRegionEntity>();
        if (null == parentId) {
            System.out.println("内部parentId = " + parentId);
            return resultObj;
        }
        System.out.println("外部parentId = " + parentId);
       // System.out.println("sysRegionEntityList是否为null? " + sysRegionEntityList.isEmpty());
        if (null != sysRegionEntityList) {
            System.out.println("=============== = ");
            for (SysRegionEntity areaVo : sysRegionEntityList) {
                if (null != areaVo.getParentId() && parentId.equals(areaVo.getParentId())) {
                    resultObj.add(areaVo);
                }
            }
        }
        return resultObj;
    }

    /**
     * 获取区域名称
     *
     * @return
     */
    public static String getAreaNameByAreaId(Integer areaId) {
        if (null == areaId) {
            return "";
        }
        String resultObj = areaId.toString();
        if (null != sysRegionEntityList) {
            for (SysRegionEntity areaVo : sysRegionEntityList) {
                if (areaVo.getId().equals(areaId)) {
                    resultObj = areaVo.getName();
                    break;
                }
            }
        }
        return resultObj;
    }

    /**
     * 根据Id获取区域
     *
     * @return
     */
    public static SysRegionEntity getAreaByAreaId(Integer areaId) {
        SysRegionEntity resultObj = null;
        if (null == areaId) {
            return resultObj;
        }
        if (null != sysRegionEntityList) {
            for (SysRegionEntity areaVo : sysRegionEntityList) {
                if (areaVo.getId().equals(areaId)) {
                    resultObj = areaVo;
                    break;
                }
            }
        }
        return resultObj;
    }

    /**
     * 根据Id获取区域
     *
     * @return
     */
    public static Integer getProvinceIdByName(String areaName) {
        Integer resultObj = null;
        if (null == areaName) {
            return resultObj;
        }
        if (null != sysRegionEntityList) {
            for (SysRegionEntity areaVo : sysRegionEntityList) {
                if (areaVo.getType() == 1 && areaVo.getName().equals(areaName)) {
                    resultObj = areaVo.getId();
                    break;
                }
            }
        }
        return resultObj;
    }

    /**
     * 根据Id获取区域
     *
     * @return
     */
    public static Integer getCityIdByName(Integer provinceId, String areaName) {
        Integer resultObj = null;
        if (null == areaName) {
            return resultObj;
        }
        if (null != sysRegionEntityList) {
            for (SysRegionEntity areaVo : sysRegionEntityList) {
                if (areaVo.getType() == 2 && areaVo.getName().equals(areaName)
                        && areaVo.getParentId().equals(provinceId)) {
                    resultObj = areaVo.getId();
                    break;
                }
            }
        }
        return resultObj;
    }


    /**
     * 根据Id获取区域
     *
     * @return
     */
    public static Integer getDistrictIdByName(Integer provinceId, Integer cityId, String areaName) {
        Integer resultObj = null;
        if (null == areaName) {
            return resultObj;
        }
        if (null != sysRegionEntityList) {
            for (SysRegionEntity areaVo : sysRegionEntityList) {
                if (areaVo.getType() == 3 && areaVo.getName().equals(areaName)
                        && areaVo.getParentId().equals(cityId)
                        && null != getAreaByAreaId(areaVo.getParentId())
                        && null != getAreaByAreaId(areaVo.getParentId()).getParentId()
                        && getAreaByAreaId(areaVo.getParentId()).getParentId().equals(provinceId)) {
                    resultObj = areaVo.getId();
                    break;
                }
            }
        }
        return resultObj;
    }

}
