package chuyou.jiang.mybatis.model;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("Banner")
public class Banner {

    /**
     * ID
     */
    private Long id;

    /**
     * 标题
     */
    private String title;

}
