package chuyou.jiang.mybatis.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 查询助手
 */
public class QueryHelper {

    /**
     * form
     */
    private String formClause = "";

    /**
     * where
     */
    private String whereClause = "";

    /**
     * order by
     */
    private String orderbyClause = "";

    /**
     * jdbc中？占位符的值
     */
    private List<Object> objectList;

    public static final String ORDER_BY_ASC = "asc";

    public static final String ORDER_BY_DESC = "desc";

    /**
     * from 查询
     * @param aClass 用户想要操作的类型
     * @param aliase 别名
     */
    public QueryHelper(Class aClass, String aliase) {
        formClause = " FROM " + aClass.getSimpleName() + " " + aliase;
    }

    /**
     * 添加查询条件
     * @param condition
     * @param objects
     * @return
     */
    public QueryHelper addCondition(String condition, Object... objects) {
        //如果有字符串了，说明已经有WHERE关键字了
        if (whereClause.length() > 0) {
            whereClause += " AND " + condition;
        } else {
            whereClause = " WHERE " + condition;
        }

        //批量添加where and 条件
        if(objects == null) {
            objectList = new ArrayList<Object>();
        }

        for (Object object : objects) {
            objectList.add(object);
        }
        return this;
    }

    /**
     * order By 查询拼接
     * @param property
     * @param order
     * @return
     */
    public QueryHelper orderBy(String property, String order) {
        //如果有字符了，就使用orderBy
        if(orderbyClause.length() > 0) {
            orderbyClause += " , " + property + " " + order;
        } else {
            orderbyClause += " ORDER BY " + property + " " + order;
        }
        return this;
    }

    /**
     * 拿到最终的HQL语句
     * @return
     */
    public String returnHQL() {
        return formClause + whereClause + orderbyClause;
    }

    /**
     * 拿到参数列表
     * @return
     */
    public List<Object> getObjectList() {
        return this.objectList;
    }

}
