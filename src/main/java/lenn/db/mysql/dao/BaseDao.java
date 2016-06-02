package lenn.db.mysql.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lenn on 16/6/1.
 */
public class BaseDao<T> {
    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    // 根据ID加载实体
    public T get(Class<T> entityClazz, Serializable id) {
        return (T) getSessionFactory().getCurrentSession().get(entityClazz, id);
    }

    // 保存实体
    public Serializable save(T entity) {
        return getSessionFactory().getCurrentSession().save(entity);
    }

    // 更新实体
    public void update(T entity) {
        getSessionFactory().getCurrentSession().update(entity);
    }

    // 根据hql更新
    public void update(String hql) {
        getSessionFactory().getCurrentSession().createQuery(hql).executeUpdate();
    }

    // 更新或保存实体
    public void saveOrUpdate(T entity) {
        getSessionFactory().getCurrentSession().saveOrUpdate(entity);
    }

    // 删除实体
    public void delete(T entity) {
        getSessionFactory().getCurrentSession().delete(entity);
    }

    // 根据ID删除实体
    public void delete(Class<T> entityClazz, Serializable id) {
        getSessionFactory().getCurrentSession()
                .createQuery("delete " + entityClazz.getSimpleName() + " en where en.id=?")
                .setParameter(0, id)
                .executeUpdate();
    }

    // 获取所有实体
    public List<T> findAll(Class<T> entityClazz) {
        return find("select en from " + entityClazz.getSimpleName() + " en");
    }

    // 获取实体总数
    public long findCount(Class<T> entityClazz) {
        List<?> l = find("select count(*) from " + entityClazz.getSimpleName());
        if (l != null && l.size() == 1) {
            return (Long) l.get(0);
        }
        return 0;
    }

    public long findCount(String hql, Object... params) {
        List<?> l = find(hql, params);
        if (l != null && l.size() == 1) {
            return (Long) l.get(0);
        }
        return 0;
    }

    // 根据HQL查询实体
    public List<T> find(String hql) {
        return getSessionFactory().getCurrentSession().createQuery(hql).list();
    }

    // 根据带占位符参数的HQL查询实体
    public List<T> find(String hql, Object... params) {
        Session session = getSessionFactory().getCurrentSession();
        Query query = session.createQuery(hql);
        for (int i = 0, len = params.length; i < len; i++) {
            query.setParameter(String.valueOf(i + 1), params[i]);
        }
        List<T> l = query.list();
        return l;
    }
}
