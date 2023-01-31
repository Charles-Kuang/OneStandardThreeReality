package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.common.core.domain.entity.GsRegion;

/**
 * 行政区Service接口
 *
 * @author ruoyi
 * @date 2022-12-13
 */
public interface IGsRegionService
{
    public GsRegion selectGsRegionByCode(Long regionCode);

    public List<GsRegion> selectGsRegionList(GsRegion gsRegion);

    /**
     * 新增行政区
     *
     * @param gsRegion 行政区
     * @return 结果
     */
    public int insertGsRegion(GsRegion gsRegion);

    /**
     * 修改行政区
     *
     * @param gsRegion 行政区
     * @return 结果
     */
    public int updateGsRegion(GsRegion gsRegion);

    /**
     * 批量删除行政区
     *
     * @param code 需要删除的行政区编码
     * @return 结果
     */
    public int deleteGsRegionByCode(Long code);

    /**
     * 是否存在部门子节点
     * 
     * @param regionCode 地区Code
     * @return 结果
     */
    public boolean hasChildByRegionCode(Long regionCode);

    public int repealGsRegion(GsRegion gsRegion);

    public boolean uncheckedAuditState(Long regionCLong);

    public int auditGsRegion(GsRegion gsRegion);

    public boolean existsByCode(Long code);

    public String selectForeFathersByRegionId(Long regionId);
    public String selectNameByRegionCode(Long code);
}
