package com.data.migration.persist.impl;

import com.data.migration.connector.MySqlConnector;
import com.data.migration.dto.DataStructureDto;
import com.data.migration.dto.MysqlConnDto;
import com.data.migration.dto.MysqlThreadData;
import com.data.migration.common.MysqlThreadHolder;
import com.data.migration.persist.ConnectionDao;
import com.data.migration.persist.DataStructureDao;
import com.data.migration.persist.InfoSchemaDao;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Repository
public class ConnectionDaoImpl implements ConnectionDao{


    @Override
    public boolean connectionVerify(MysqlConnDto mysqlConnDto) throws SQLException, ClassNotFoundException {
        String fullUrl = mysqlConnDto.getUrl() + ":" + mysqlConnDto.getPort() + "/" + mysqlConnDto.getSchema();
        Connection connection = MySqlConnector.getConnection(fullUrl, mysqlConnDto.getUsername(), mysqlConnDto.getPassword());
        String verifySql = "select 1";
        if (connection != null) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(verifySql);
            while (resultSet.next()) {
                String result = resultSet.getString("1");
                if (!StringUtils.isEmpty(result) && "1".equals(result)) {
                    MysqlThreadData mysqlThreadData = new MysqlThreadData();
                    mysqlConnDto.setConnection(connection);
                    if (MysqlConnDto.TYPE_MASTER.equals(mysqlConnDto.getType())) {
                        mysqlThreadData.setMasterConnDto(mysqlConnDto);
                    } else {
                        mysqlThreadData.setHostConnDto(mysqlConnDto);
                    }
                    MysqlThreadHolder.setInfo(mysqlThreadData);
                    return true;
                }
            }
        }
        return false;
    }


    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ConnectionDao connectionDao = new ConnectionDaoImpl();
        InfoSchemaDao infoSchemaDao = new InfoSchemaDaoImpl();
        List<String> strings = infoSchemaDao.qryAllTableNames(MysqlConnDto.TYPE_HOST);
        strings.forEach(s -> System.out.println(s));
        DataStructureDao dataStructureDao = new DataStructureDaoImpl();
        List<DataStructureDto> dataStructureDtoList = dataStructureDao.qryDataStructure(strings.get(1),MysqlConnDto.TYPE_HOST);
        System.out.println(dataStructureDtoList);
    }
}
