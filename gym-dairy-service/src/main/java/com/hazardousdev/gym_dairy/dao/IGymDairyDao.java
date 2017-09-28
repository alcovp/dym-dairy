package com.hazardousdev.gym_dairy.dao;

/**
 * @author alcovp
 */
public interface IGymDairyDao extends
        IGymDairyDaoDDL,
        IGymDairyDaoSelect,
        IGymDairyDaoInsert,
        IGymDairyDaoUpdate,
        IGymDairyDaoJunction,
        IGymDairyDaoDelete {

    void close();
}
