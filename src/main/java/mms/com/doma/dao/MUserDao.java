package mms.com.doma.dao;

import org.seasar.doma.Dao;
import org.seasar.doma.Delete;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;
import org.seasar.doma.boot.ConfigAutowireable;

import mms.com.doma.entity.MUser;

/**
 */
@Dao
@ConfigAutowireable
public interface MUserDao {

    /**
     * @param userId
     * @return the MUser entity
     */
    @Select
    MUser selectById(String userId);

    /**
     * @param entity
     * @return affected rows
     */
    @Insert
    int insert(MUser entity);

    /**
     * @param entity
     * @return affected rows
     */
    @Update
    int update(MUser entity);

    /**
     * @param entity
     * @return affected rows
     */
    @Delete
    int delete(MUser entity);
}