/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vicky.common.utils.service;

import com.vicky.common.utils.page.Condition;
import com.vicky.common.utils.page.Order;
import com.vicky.common.utils.page.Page;
import com.vicky.common.utils.statusmsg.StatusMsgException;
import com.vicky.common.utils.update.UpdateProperty;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ibatis.session.RowBounds;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

/**
 * Mybatis通用BaseService
 *
 * @author Vicky
 * @param <T>
 * @param <PrimaryKey>
 */
public abstract class MybatisBaseService<T, PrimaryKey> implements BaseService<T, PrimaryKey> {

    /**
     * 注入Mapper,子类必须实现该方法注入bean
     * <p>
     * @return 一个Mapper或者其子类,该类所有方法依赖这个返回值
     */
    protected abstract Mapper<T> getMapper();

    /**
     * 反射获得第一个泛型参数具体类型
     * <p>
     * @return 该类第一个泛型参数具体类型，如果没有指定泛型则为Object.class
     */
    private Class getTClass() {
        try {
            Method method = this.getClass().getDeclaredMethod("getMapper");
            Type returnType = method.getGenericReturnType();
            if (returnType instanceof ParameterizedType) {
                ParameterizedType type = (ParameterizedType) returnType;
                Type[] typeArguments = type.getActualTypeArguments();
                return (Class) typeArguments[0];
            }
        } catch (NoSuchMethodException | SecurityException e) {
        }
        return Object.class;
    }

    /**
     * 根据ID查找实体
     * <p>
     * @param id 实体类ID
     * @return 实体对象
     * @see tk.mybatis.mapper.common.Mapper#selectByPrimaryKey
     */
    @Override
    public T selectByPrimaryKey(PrimaryKey id) {
        return this.getMapper().selectByPrimaryKey(id);
    }

    /**
     * 根据实体属性获得实体,实体属性相当于sql语句where的条件
     * <p>
     * @param t 实体类
     * @return 实体对象
     * @see tk.mybatis.mapper.common.Mapper#selectOne
     */
    @Override
    public T selectOne(T t) {
        return this.getMapper().selectOne(t);
    }

    /**
     * 根据实体属性获得实体总数,实体属性相当于sql语句where的条件
     * <p>
     * @param t 实体类
     * @return 实体总数
     * @see tk.mybatis.mapper.common.Mapper#selectCount
     */
    public int selectCount(T t) {
        return this.getMapper().selectCount(t);
    }

    /**
     * 根据实体属性和分页条件获得实体集合,实体属性相当于sql语句where的条件
     * <p>
     * @param t 实体类
     * @param rowBounds 分页条件类
     * @return 实体集合,List类型
     * @see tk.mybatis.mapper.common.Mapper#selectByRowBounds
     * @see org.apache.ibatis.session.RowBounds
     */
    public List<T> selectByRowBounds(T t, RowBounds rowBounds) {
        return this.getMapper().selectByRowBounds(t, rowBounds);
    }

    /**
     * 根据实体属性和分页条件获得实体集合以及实体总数,实体属性相当于sql语句where的条件
     * <p>
     * @param t 实体类
     * @param rowBounds 分页条件类
     * @return 实体集合,Map类型,包含两个键值对,一个为total：总数,一个为data：List
     * @see org.apache.ibatis.session.RowBounds
     */
    public Map<String, Object> getPageData(T t, RowBounds rowBounds) {
        Map<String, Object> map = new HashMap<>();
        map.put(Page.DATA_KEY, this.selectByRowBounds(t, rowBounds));
        map.put(Page.TOTAL_KEY, this.selectCount(t));
        return map;
    }

    /**
     * 根据example获得实体总数
     * <p>
     * @param example 实体类
     * @return 实体总数
     * @see tk.mybatis.mapper.common.Mapper#selectCountByExample
     * @see tk.mybatis.mapper.entity.Example
     */
    public int selectCountByExample(Example example) {
        return this.getMapper().selectCountByExample(example);
    }

    /**
     * 根据example和分页条件获得实体集合
     * <p>
     * @param example 实体类
     * @param rowBounds 分页条件类
     * @return 实体集合,List类型
     * @see tk.mybatis.mapper.common.Mapper#selectByExampleAndRowBounds
     * @see tk.mybatis.mapper.entity.Example
     * @see org.apache.ibatis.session.RowBounds
     */
    public List<T> selectByExampleAndRowBounds(Example example, RowBounds rowBounds) {
        return this.getMapper().selectByExampleAndRowBounds(example, rowBounds);
    }

    /**
     * 根据example和分页条件获得实体集合以及实体总数
     * <p>
     * @param example 实体类
     * @param rowBounds 分页条件类
     * @return 实体集合,Map类型,包含两个键值对,一个为total：总数,一个为data：List
     * @see org.apache.ibatis.session.RowBounds
     * @see tk.mybatis.mapper.entity.Example
     */
    public Map<String, Object> getPageData(Example example, RowBounds rowBounds) {
        Map<String, Object> map = new HashMap<>();
        map.put(Page.DATA_KEY, this.selectByExampleAndRowBounds(example, rowBounds));
        map.put(Page.TOTAL_KEY, this.selectCountByExample(example));
        return map;
    }

    /**
     * 插入实体
     * <p>
     * @param t 实体类
     * @see tk.mybatis.mapper.common.Mapper#insertSelective
     */
    @Override
    public void save(T t) {
        this.getMapper().insertSelective(t);
    }

    /**
     * 更新实体
     * <p>
     * @param t 实体类
     * @see tk.mybatis.mapper.common.Mapper#updateByPrimaryKey
     */
    public void update(T t) {
        this.getMapper().updateByPrimaryKey(t);
    }

    /**
     * 更新实体
     * <p>
     * @param t 实体类
     * @see tk.mybatis.mapper.common.Mapper#updateByPrimaryKey
     */
    public void updateSelective(T t) {
        this.getMapper().updateByPrimaryKeySelective(t);
    }

    /**
     * 更新实体,解析HttpServletRequest获得要更新的属性
     * <p>
     * @param id 实体主键
     * @param request HttpServletRequest
     * @throws java.text.ParseException
     * @throws java.lang.NoSuchMethodException
     * @throws java.lang.IllegalAccessException
     * @throws java.lang.reflect.InvocationTargetException
     * @see tk.mybatis.mapper.common.Mapper#updateByPrimaryKey
     */
    @Override
    public void update(PrimaryKey id, HttpServletRequest request) throws Exception {
        List<UpdateProperty> updatePropertys = UpdateProperty.getUpdatePropertyListFromHttpRequest(request);
        this.update(id, updatePropertys);
    }

    /**
     * 更新实体,根据UpdateProperty集合，通过反射设置值
     * <p>
     * @param id 实体主键
     * @param updatePropertys List集合
     * @throws java.lang.NoSuchMethodException
     * @throws java.lang.IllegalAccessException
     * @throws java.lang.reflect.InvocationTargetException
     * @see tk.mybatis.mapper.common.Mapper#updateByPrimaryKey
     */
    public void update(PrimaryKey id, List<UpdateProperty> updatePropertys)
            throws Exception {
        T t = this.selectByPrimaryKey(id);
        boolean haveUpdate = false;
        for (UpdateProperty updateProperty : updatePropertys) {
            String property = updateProperty.getProperty();
            String methodString = "set" + property.substring(0, 1).toUpperCase() + property.substring(1);
            try {
                Method method = this.getTClass().getMethod(methodString, updateProperty.getValue().getClass());
                method.invoke(t, updateProperty.getValue());
                haveUpdate = true;
            } catch (Exception exception) {
                throw new StatusMsgException("实体属性参数错误,请检查参数是否正确");
            }
        }
        if (haveUpdate) {
            this.updateSelective(t);
        } else {
            throw new StatusMsgException("实体没有任何属性被修改,请检查参数是否正确");
        }
    }

    /**
     * 删除实体
     * <p>
     * @param t 实体类
     * @see tk.mybatis.mapper.common.Mapper#delete
     */
    public void delete(T t) {
        this.getMapper().delete(t);
    }

    /**
     * 删除实体
     * <p>
     * @param id 实体ID
     * @see tk.mybatis.mapper.common.Mapper#deleteByPrimaryKey
     */
    @Override
    public void deleteById(PrimaryKey id) {
        this.selectByPrimaryKey(id);
        this.getMapper().deleteByPrimaryKey(id);
    }

    /**
     * 删除实体
     * <p>
     * @param example Example
     * @return 删除总数
     * @see tk.mybatis.mapper.common.Mapper#deleteByExample
     */
    public int deleteByExample(Example example) {
        return this.getMapper().deleteByExample(example);
    }

    /**
     * 解析HttpServletRequest获得条件，进行分页查询
     * <p>
     * @param request HttpServletRequest
     * @return 实体集合,Map类型,包含两个键值对,一个为total：总数,一个为data：List
     * @throws java.text.ParseException
     * @see com.vicky.common.utils.page.Condition
     * @see com.vicky.common.utils.page.Order
     * @see com.vicky.common.utils.page.Page
     * @see com.vicky.pageutils.Condition#getConditionListFromHttpRequest
     */
    @Override
    public Map<String, Object> getPageData(HttpServletRequest request) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put(Page.DATA_KEY, this.list(request));
        map.put(Page.TOTAL_KEY, this.listCount(request));
        return map;
    }

    /**
     * 解析HttpServletRequest获得条件，获得分页查询总数
     * <p>
     * @param request HttpServletRequest
     * @return 分页查询总数
     * @throws java.text.ParseException
     * @see com.vicky.common.utils.page.Condition
     * @see com.vicky.common.utils.page.Order
     * @see com.vicky.common.utils.page.Page
     * @see com.vicky.pageutils.Condition#getConditionListFromHttpRequest
     */
    public int listCount(HttpServletRequest request) throws Exception {
        List<Condition> conditions = Condition.getConditionListFromHttpRequest(request);
        return this.listCount(conditions);
    }

    /**
     * 解析HttpServletRequest获得条件，获得分页查询的当前页实体数
     * <p>
     * @param request HttpServletRequest
     * @return 实体List集合
     * @throws java.text.ParseException
     * @see com.vicky.common.utils.page.Condition
     * @see com.vicky.common.utils.page.Order
     * @see com.vicky.common.utils.page.Page
     * @see com.vicky.pageutils.Condition#getConditionListFromHttpRequest
     * @see com.vicky.pageutils.Page#getPageFromHttpRequest
     * @see com.vicky.pageutils.Order#getOrderListFromHttpRequest
     */
    public List<T> list(HttpServletRequest request) throws Exception {
        Page page = Page.getPageFromHttpRequest(request);
        List<Condition> conditions = Condition.getConditionListFromHttpRequest(request);
        List<Order> orders = Order.getOrderListFromHttpRequest(request);
        return this.list(page, conditions, orders);
    }

    /**
     * 根据条件查询实体总数
     * <p>
     * @param conditions 根据条件查询实体总数
     * @return 实体总数
     * @throws java.lang.Exception
     * @see com.vicky.common.utils.page.Condition
     * @see com.vicky.pageutils.Condition#getConditionListFromHttpRequest
     */
    public int listCount(List<Condition> conditions) throws Exception {
        //构造分页条件
        Example example = new Example(this.getTClass());
        Example.Criteria criteria = example.createCriteria();
        for (Condition condition : conditions) {
            switch (condition.getCondition()) {
                case Condition.EQUAL:
                    criteria.andEqualTo(condition.getProperty(), condition.getValue());
                    break;
                case Condition.NOTEQUAL:
                    criteria.andNotEqualTo(condition.getProperty(), condition.getValue());
                    break;
                case Condition.GREATER_THAN:
                    criteria.andGreaterThan(condition.getProperty(), condition.getValue());
                    break;
                case Condition.GREATER_THAN_OR_EQUAL:
                    criteria.andGreaterThanOrEqualTo(condition.getProperty(), condition.getValue());
                    break;
                case Condition.LESS_THAN:
                    criteria.andLessThan(condition.getProperty(), condition.getValue());
                    break;
                case Condition.LESS_THAN_OR_EQUAL:
                    criteria.andLessThanOrEqualTo(condition.getProperty(), condition.getValue());
                    break;
                case Condition.LIKEC:
                    criteria.andLike(condition.getProperty(), "%" + (String) condition.getValue() + "%");
                    break;
                case Condition.LIKER:
                    criteria.andLike(condition.getProperty(), (String) condition.getValue());
                    break;
                case Condition.NOTLIKEC:
                    criteria.andNotLike(condition.getProperty(), "%" + (String) condition.getValue() + "%");
                    break;
                case Condition.NOTLIKER:
                    criteria.andNotLike(condition.getProperty(), (String) condition.getValue());
                    break;
                case Condition.IN:
                    criteria.andIn(condition.getProperty(), condition.getInSet());
                    break;
                case Condition.NOTIN:
                    criteria.andNotIn(condition.getProperty(), condition.getNotInSet());
                    break;
                case Condition.NULL:
                    criteria.andIsNull(condition.getProperty());
                    break;
                case Condition.NOTNULL:
                    criteria.andIsNotNull(condition.getProperty());
                    break;
                default:
                    throw new StatusMsgException("不支持的条件类型：" + condition.getCondition());
            }
        }

        return this.getMapper().selectCountByExample(example);
    }

    /**
     * 根据条件查询实体总数
     * <p>
     * @param page 分页参数
     * @param conditions 查询条件
     * @param orders 排序条件
     * @return 实体List集合
     * @throws java.lang.Exception
     */
    public List<T> list(Page page, List<Condition> conditions, List<Order> orders) throws Exception {
        //构造分页查询
        RowBounds rowBounds = new RowBounds(page.getPageOffset(), page.getPageSize());

        //构造分页条件
        Example example = new Example(this.getTClass());
        Example.Criteria criteria = example.createCriteria();
        for (Condition condition : conditions) {
            switch (condition.getCondition()) {
                case Condition.EQUAL:
                    criteria.andEqualTo(condition.getProperty(), condition.getValue());
                    break;
                case Condition.NOTEQUAL:
                    criteria.andNotEqualTo(condition.getProperty(), condition.getValue());
                    break;
                case Condition.GREATER_THAN:
                    criteria.andGreaterThan(condition.getProperty(), condition.getValue());
                    break;
                case Condition.GREATER_THAN_OR_EQUAL:
                    criteria.andGreaterThanOrEqualTo(condition.getProperty(), condition.getValue());
                    break;
                case Condition.LESS_THAN:
                    criteria.andLessThan(condition.getProperty(), condition.getValue());
                    break;
                case Condition.LESS_THAN_OR_EQUAL:
                    criteria.andLessThanOrEqualTo(condition.getProperty(), condition.getValue());
                    break;
                case Condition.LIKEC:
                    criteria.andLike(condition.getProperty(), "%" + (String) condition.getValue() + "%");
                    break;
                case Condition.LIKER:
                    criteria.andLike(condition.getProperty(), (String) condition.getValue());
                    break;
                case Condition.NOTLIKEC:
                    criteria.andNotLike(condition.getProperty(), "%" + (String) condition.getValue() + "%");
                    break;
                case Condition.NOTLIKER:
                    criteria.andNotLike(condition.getProperty(), (String) condition.getValue());
                    break;
                case Condition.IN:
                    criteria.andIn(condition.getProperty(), condition.getInSet());
                    break;
                case Condition.NOTIN:
                    criteria.andNotIn(condition.getProperty(), condition.getNotInSet());
                    break;
                case Condition.NULL:
                    criteria.andIsNull(condition.getProperty());
                    break;
                case Condition.NOTNULL:
                    criteria.andIsNotNull(condition.getProperty());
                    break;
                default:
                    throw new StatusMsgException("不支持的条件类型：" + condition.getCondition());
            }
        }

        for (Order order : orders) {
            if (order.getType().equals(Order.ASC)) {
                example.orderBy(order.getProperty()).asc();
            } else {
                example.orderBy(order.getProperty()).desc();
            }
        }

        return this.getMapper().selectByExampleAndRowBounds(example, rowBounds);
    }

}
