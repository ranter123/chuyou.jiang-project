package chuyou.jiang.mybatis.mapper;

import chuyou.jiang.mybatis.model.Banner;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BannerMapper {

    /**
     * 查找banner
     * @return
     */
    @Select("select * from banner")
    List<Banner> findBanner();

    /**
     * 插入banner数据
     * @param banner
     */
    int insertBanner(Banner banner);
}
