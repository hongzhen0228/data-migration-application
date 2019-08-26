package com.data.migration.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import static java.sql.Types.*;

/**
 * Created by hongzhen.cao on 2019/8/21.
 */
@Data
public class DataContainer {

    private List<MySqlData> mySqlDataList = new ArrayList<>();

    @Data
    public static class MySqlData{

        private String name;

        private String value;

        private Integer type;

        public MySqlData(String name, String value, Integer type) {
            this.name = name;
            this.value = value;
            this.type = type;
        }
    }

    public boolean putValue(MySqlData mySqlData){
        Integer type = mySqlData.getType();
        if (mySqlData.getValue() != null && !mySqlData.getValue().equals("null") && (type == CHAR || type == VARCHAR || type == DATE || type == DATALINK || type == TIMESTAMP || type == TIME || type == OTHER)) {
            mySqlData.setValue("\'" + mySqlData.getValue() + "\'");
        }

        return mySqlDataList.add(mySqlData);
    }

}
