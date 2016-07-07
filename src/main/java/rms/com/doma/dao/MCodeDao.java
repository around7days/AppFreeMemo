package rms.com.doma.dao;

import org.seasar.doma.Dao;
import org.seasar.doma.Delete;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;
import rms.com.doma.entity.MCode;
import org.seasar.doma.boot.ConfigAutowireable;

/**
 * MCodeDaoクラス
 */
@Dao
@ConfigAutowireable
public interface MCodeDao {

    /**
     * selectById
     * @param codeKbn
     * @param code
     * @return the MCode entity
     */
    @Select(ensureResult = true)
    MCode selectById(String codeKbn, String code);

    /**
     * insert
     * @param entity
     * @return affected rows
     */
    @Insert(excludeNull = true)
    int insert(MCode entity);

    /**
     * udpate
     * @param entity
     * @return affected rows
     */
    @Update(excludeNull = true)
    int update(MCode entity);

    /**
     * delete
     * @param entity
     * @return affected rows
     */
    @Delete
    int delete(MCode entity);
}