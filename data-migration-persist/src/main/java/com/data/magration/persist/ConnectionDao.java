package com.data.magration.persist;

import java.sql.SQLException;

public interface ConnectionDao {

    boolean connectionVerify(String url, String port, String username, String password, String schema) throws SQLException, ClassNotFoundException;
}
