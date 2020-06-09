package chuyou.jiang.mybatis.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;

/**
 * mybatis-jdbc工具类
 */
public class MybatisUtil {

    private static ThreadLocal<SqlSession> threadLocal = new ThreadLocal<SqlSession>();
    private static SqlSessionFactory sqlSessionFactory;

    /**
     * 加载mybatis-config配置文件
     */
    static{
        try{
            Reader reader = Resources.getResourceAsReader("mybatis/mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 禁止外界通过new方法创建
     */
    private MybatisUtil(){}

    /**
     * 获取Sqlsession
     */
    public static SqlSession getSqlSession() {
        //从当前线程中获取Sqlsession对象
        SqlSession sqlSession = threadLocal.get();
        //如果sqlSession对象为空
        if(sqlSession == null) {
            //在SqlSessionFactory非空的情况下，获取Sqlsession对象
            sqlSession = sqlSessionFactory.openSession();
            //将sqlSession对象与当前线程绑定在一起
            threadLocal.set(sqlSession);
        }
        return sqlSession;
    }

    /**
     * 关闭sqlSession与当前线程分开
     */
    public static void closeSqlSession() {
        //从当前线程中获取SqlSession对象
        SqlSession sqlSession = threadLocal.get();
        //如果sqlSession对像非空
        if(sqlSession != null) {
            //关闭sqlSession对象
            sqlSession.close();
            //分开当前线程与Sqlsession对象的关系。目的是让GC尽早回收
            threadLocal.remove();
        }
    }

    /**
     * 测试工具类
     * @param args
     */
    public static void main(String[] args) {
        Connection con = MybatisUtil.getSqlSession().getConnection();
        System.out.println(con != null ? "连接成功":"连接失败");
    }
}
