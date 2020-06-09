package chuyou.jiang.mybatis.dao;

import chuyou.jiang.mybatis.model.Student;
import chuyou.jiang.mybatis.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentDao {

    /**
     * 添加单个student对象
     * @param student
     */
    public void add(Student student) {
        //得到连接对象
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        try {
            sqlSession.insert("chuyou.jiang.mybatis.mapper.StudentMapper.insertOne", student);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            MybatisUtil.closeSqlSession();
        }
    }


    public List<Student> pagination(int start, int end) {
        //获取SqlSession
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        List<Student> students = null;
        try {
            Map<String, Integer> map = new HashMap<String, Integer>();
            map.put("start", start);
            map.put("end", end);
            students = sqlSession.selectList("chuyou.jiang.mybatis.mapper.StudentMapper.pagination", map);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MybatisUtil.closeSqlSession();
        }
        return students;
    }


    public static void main(String[] args) {
        StudentDao studentDao = new StudentDao();
//        for (int i = 0; i < 100; i ++) {
//            Student student = new Student();
//            student.setName("yehao" + i);
//            student.setSal(1000D);
//            studentDao.add(student);
//        }

        List<Student> students = studentDao.pagination(1, 10);
        for (Student student : students) {
            System.out.println(student.getName());
        }
    }

}
