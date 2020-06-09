package chuyou.jiang.mybatis.model;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("Student")
public class Student {
    private Integer id;
    private String name;
    private Double sal;
}
