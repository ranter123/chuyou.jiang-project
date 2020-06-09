package chuyou.jiang.mybatis.mapper;

import chuyou.jiang.mybatis.model.Student;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface StudentMapper {

    /**
     * 插入一条数据
     * @param student
     * @return
     */
    int insertOne(Student student);
}
