package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.common.core.domain.entity.GsRegion;

/**
 * 行政区Mapper接口
 *
 * @author ruoyi
 * @date 2022-12-13
 */
public interface GsRegionMapper
{
    /**
     * 查询行政区
     *
     * @param code 行政区编码
     * @return 行政区
     */
    public GsRegion selectGsRegionByCode(Long code);

    /**
     * 查询行政区列表
     *
     * @param gsRegion 行政区
     * @return 行政区集合
     */
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
     * 删除行政区
     *
     * @param code 行政区编码
     * @return 结果
     */
    public int deleteGsRegionByCode(Long code);

    /**
     * 是否存在子节点
     * 
     * @param RegionCode 地区编码
     * @return 结果
     */
    public int hasChildByRegionCode(Long RegionCode);

    public int updateForefathers(String oldForefathers, String forefathers);

    public int uncheckedAuditState(Long regionCode);

    public int existsByCode(Long code);

    public String selectForeFathersByRegionId(Long regionId);
    public String selectNameByRegionCode(Long code);
}
