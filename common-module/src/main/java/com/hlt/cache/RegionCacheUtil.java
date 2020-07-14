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
 * InitializingBean接口为bean提供了初始化方法的方式，它只有一个afterPropertiesSet方法，
 * 凡是继承该接口的类，在初始化bean的时候都会执行该方法，即在spring初始化bean的时候如
 * 果bean实现了InitializingBean接口会自动调用afterPropertiesSet方法。
 * 此接口的实现类需要<bean></bean>配置.
 *
 * 地理区域缓存工具类，实现InitializingBean接口，保证在spring框架刚启动后就能保证sysRegionEntityList
 * 中有全国区域数据。
 */

public class RegionCacheUtil implements InitializingBean {

    /**
     * 这是一个静态变量，一旦赋值就会一直存在
     */
    public static List<SysRegionEntity> sysRegionEntityList;

    @Override
    public void afterPropertiesSet() throws Exception {
        init();
    }

    public void init(){
        /**
         * 通过SpringContextUtils获取到sysRegionDao bean对象。
         */
        SysRegionDao sysRegionDao = SpringContextUtils.getBean(SysRegionDao.class);
        if (sysRegionDao != null){
            //通过 sysRegionDao对象查询数据库得到所有地区列表
            sysRegionEntityList = sysRegionDao.queryList(new HashMap<String, Object>());
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
            /**
             * 这一步待优化，4万多个数据一个个遍历代价有点大
             */
            for (SysRegionEntity country : sysRegionEntityList) {
                if (country.getType().equals(0)) {
                    resultObj.add(country);
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
            for (SysRegionEntity province : sysRegionEntityList) {
                if (province.getType().equals(1)) {
                    resultObj.add(province);
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
            for (SysRegionEntity city : sysRegionEntityList) {
                if (city.getType().equals(2)) {
                    resultObj.add(city);
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
    public static List<SysRegionEntity> getAllProviceByParentId(Integer countryId) {
        List<SysRegionEntity> resultObj = new ArrayList<SysRegionEntity>();
        if (null == countryId) {
            return resultObj;
        }
        if (null != sysRegionEntityList) {
            for (SysRegionEntity province : sysRegionEntityList) {
                if (null != province.getParentId() && province.getType().equals(1) && countryId.equals(province.getParentId())) {
                    resultObj.add(province);
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
    public static List<SysRegionEntity> getChildrenCity(Integer cityId) {
        List<SysRegionEntity> resultObj = new ArrayList<SysRegionEntity>();
        if (null == cityId) {
            return resultObj;
        }
        if (null != sysRegionEntityList) {
            //遍历
            for (SysRegionEntity city : sysRegionEntityList) {
                if (city.getParentId() != null && city.getType().equals(2) && cityId.equals(city.getParentId())) {
                    resultObj.add(city);
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

        List<SysRegionEntity> resultObj = new ArrayList<SysRegionEntity>();

        if (proviceName == null) {
            return resultObj;
        }

        if (sysRegionEntityList != null) {

            for (SysRegionEntity city : sysRegionEntityList) {

                if (city.getParentId() != null && city.getType().equals(2) &&
                        city.getParentName().equals(proviceName)) {
                    resultObj.add(city);
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
    public static List<SysRegionEntity> getChildrenDistrict(Integer cityId) {
        List<SysRegionEntity> resultObj = new ArrayList<SysRegionEntity>();
        if (null == cityId) {
            return resultObj;
        }
        if (sysRegionEntityList != null) {
            for (SysRegionEntity district : sysRegionEntityList) {
                if (null != district.getParentId() && district.getType().equals(3) && cityId.equals(district.getParentId())) {
                    resultObj.add(district);
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
            return resultObj;
        }
       // System.out.println("sysRegionEntityList是否为null? " + sysRegionEntityList.isEmpty());
        if (null != sysRegionEntityList) {
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
