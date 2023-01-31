package com.ruoyi.system.service.impl;

import java.util.Arrays;
import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.GsRegionMapper;
import com.ruoyi.common.core.domain.entity.GsRegion;
import com.ruoyi.system.service.IGsRegionService;

/**
 * 行政区Service业务层处理
 *
 * @author ruoyi
 * @date 2022-12-13
 */
@Service
public class GsRegionServiceImpl implements IGsRegionService
{
    @Autowired
    private GsRegionMapper gsRegionMapper;


    @Override
    public GsRegion selectGsRegionByCode(Long code)
    {
        GsRegion result = gsRegionMapper.selectGsRegionByCode(code);
        List<String> codes = Arrays.asList(result.getForefathers().split(","));
        if(codes.size() > 1)
            result.setProvinceCode(Long.parseLong(codes.get(1).trim()));
        if(codes.size() > 2)
            result.setCityCode(Long.parseLong(codes.get(2).trim()));
        if(codes.size() > 3)
            result.setDistrictCode(Long.parseLong(codes.get(3).trim()));
        if(codes.size() > 4)
            result.setStreetCode(Long.parseLong(codes.get(4).trim()));
        return result;
    }

    /**
     * 查询行政区列表
     *
     * @param gsRegion 行政区
     * @return 行政区
     */
    @Override
    public List<GsRegion> selectGsRegionList(GsRegion gsRegion)
    {
        return gsRegionMapper.selectGsRegionList(gsRegion);
    }

    /**
     * 新增行政区
     *
     * @param gsRegion 行政区
     * @return 结果
     */
    @Override
    public int insertGsRegion(GsRegion gsRegion)
    {
        gsRegion.setCreateTime(DateUtils.getNowDate());
        String forefathers = "0";
        if(gsRegion.getProvinceCode() != null)
            forefathers += "," + gsRegion.getProvinceCode();
        if(gsRegion.getCityCode() != null)
            forefathers += "," + gsRegion.getCityCode();
        if(gsRegion.getDistrictCode() != null)
            forefathers += "," + gsRegion.getDistrictCode();
        if(gsRegion.getStreetCode() != null)
            forefathers += "," + gsRegion.getStreetCode();
        gsRegion.setForefathers(forefathers);
        return gsRegionMapper.insertGsRegion(gsRegion);
    }

    /**
     * 修改行政区
     *
     * @param gsRegion 行政区
     * @return 结果
     */
    @Override
    public int updateGsRegion(GsRegion gsRegion)
    {
        gsRegion.setOldForefathers(gsRegion.getForefathers());
        String forefathers = "0";
        if(gsRegion.getProvinceCode() != null)
            forefathers += "," + gsRegion.getProvinceCode();
        if(gsRegion.getCityCode() != null)
            forefathers += "," + gsRegion.getCityCode();
        if(gsRegion.getDistrictCode() != null)
            forefathers += "," + gsRegion.getDistrictCode();
        if(gsRegion.getStreetCode() != null)
            forefathers += "," + gsRegion.getStreetCode();
        gsRegion.setForefathers(forefathers);
        gsRegionMapper.updateForefathers(gsRegion.getOldForefathers() + ", " + gsRegion.getOldCode().toString(), gsRegion.getForefathers() + ", " + gsRegion.getCode().toString());
        return gsRegionMapper.updateGsRegion(gsRegion);
    }

    /**
     * 删除行政区信息
     *
     * @param code 行政区编码
     * @return 结果
     */
    @Override
    public int deleteGsRegionByCode(Long code)
    {
        return gsRegionMapper.deleteGsRegionByCode(code);
    }

    @Override
    public boolean hasChildByRegionCode(Long regionCode) {
        int result = gsRegionMapper.hasChildByRegionCode(regionCode);
        return result > 0;
    }

    @Override
    public int repealGsRegion(GsRegion gsRegion) {
        return gsRegionMapper.updateGsRegion(gsRegion);
    }

    @Override
    public boolean uncheckedAuditState(Long regionCode) {
        int result = gsRegionMapper.uncheckedAuditState(regionCode);
        return result == 0;
    }

    @Override
    public int auditGsRegion(GsRegion gsRegion) {
        return gsRegionMapper.updateGsRegion(gsRegion);
    }

    @Override
    public boolean existsByCode(Long code) {
        return gsRegionMapper.existsByCode(code) > 0;
    }

    @Override
    public String selectNameByRegionCode(Long code) {
        return gsRegionMapper.selectNameByRegionCode(code);
    }

    @Override
    public String selectForeFathersByRegionId(Long regionId) {
        return gsRegionMapper.selectForeFathersByRegionId(regionId);
    }
}
