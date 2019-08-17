package com.data.magration.persist.impl;

import com.data.magration.connector.MySqlConnector;
import com.data.magration.dto.MysqlThreadData;
import com.data.magration.persist.MysqlThreadHolder;
import com.data.magration.persist.ConnectionDao;
import com.data.magration.persist.InfoSchemaDao;
import org.springframework.util.StringUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class ConnectionDaoImpl extends MysqlThreadHolder implements ConnectionDao{


    @Override
    public boolean connectionVerify(String url, String port, String username, String password, String schema) throws SQLException, ClassNotFoundException {
        String fullUrl = url + ":" + port + "/" + schema;
        Connection connection = MySqlConnector.getConnection(fullUrl, username, password);
        String verifySql = "select 1";
        if (connection != null) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(verifySql);
            while (resultSet.next()) {
                String result = resultSet.getString("1");
                if (!StringUtils.isEmpty(result) && "1".equals(result) ? true : false) {
                    MysqlThreadData mysqlThreadData = new MysqlThreadData();
                    mysqlThreadData.setConnection(connection).setPassword(password).setUsername(username).setSchema(schema);
                    MysqlThreadHolder.setInfo(mysqlThreadData);
                    return true;
                }
            }
        }
        return false;
    }


    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ConnectionDao connectionDao = new ConnectionDaoImpl();
        boolean b = connectionDao.connectionVerify("rm-wz986i60p423gcku3qo.mysql.rds.aliyuncs.com", "3306", "root", "Cao8231669@", "db_mblog");
        InfoSchemaDao infoSchemaDao = new InfoSchemaDaoImpl();
        List<String> strings = infoSchemaDao.qryAllTableNames();
        strings.forEach(s -> System.out.println(s));
    }
}
