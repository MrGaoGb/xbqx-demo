package com.mrgao.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author Mr.Gao
 * @date 2024/1/8 15:39
 * @apiNote:
 */
@Mapper
public interface StorkMapper {

    /**
     * 获取库存数
     *
     * @param pid
     * @return
     */
    @Select("select  stork_count from prd_stork where pid = #{pid}")
    Integer getStorkCountByPid(@Param("pid") String pid);

    /**
     * 更新库存
     *
     * @param pid
     */
    @Update("update prd_stork set stork_count = stork_count -1 where pid = #{pid}")
    void updateStorkByPid(@Param("pid") String pid);
}
