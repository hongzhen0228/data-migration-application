package com.data.migration.service.impl;

import com.data.migration.dto.DataStructureDto;
import com.data.migration.persist.DataStructureDao;
import com.data.migration.service.api.IDataStructureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class DataStructureServiceImpl implements IDataStructureService {

    @Autowired
    private DataStructureDao dataStructureDao;
    @Override
    public List<DataStructureDto> qryDataStructure(String tableName, String type) throws SQLException {
        return dataStructureDao.qryDataStructure(tableName, type);
    }


    public static void main(String[] args) {

    }
}
