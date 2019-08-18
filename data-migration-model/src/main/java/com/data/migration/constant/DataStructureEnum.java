package com.data.migration.constant;

public enum DataStructureEnum {

    INT("int","java.lang.Integer"),
    CHAR("varchar","java.lang.String"),
    BLOB("blob","java.lang.byte[]"),
    TEXT("varchar","java.lang.String"),
    INTEGER("INTEGER UNSIGNED","java.lang.Long"),
    SMALLINT("SMALLINT UNSIGNED","java.lang.Integer"),
    TINYINT("TINYINT UNSIGNED","java.lang.Integer"),
    BIT("BIT","java.lang.Boolean"),
    BIGINT("BIGINT UNSIGNED","java.math.BigInteger"),
    FLOAT("FLOAT","java.lang.Float"),
    DOUBLE("DOUBLE","java.lang.Double"),
    DECIMAL("DECIMAL","java.math.BigDecimal"),
    BOOLEAN("TINYINT UNSIGNED",""),
    DATE("DATE","java.sql.Date"),
    TIME("TIME","java.sql.Time"),
    DATETIME("DATETIME","java.sql.Timestamp"),
    TIMESTAMP("TIMESTAMP","java.sql.Timestamp"),
    YEAR("YEAR","java.sql.Date");


    private String mysqlType;

    private String javaType;

    private DataStructureEnum(String mysqlType, String javaType) {
        this.mysqlType = mysqlType;
        this.javaType = javaType;
    }

    public static String getJavaType(String mysqlType) {
        for (DataStructureEnum dataStructureEnum : DataStructureEnum.values()) {
            if (mysqlType.equalsIgnoreCase(dataStructureEnum.mysqlType)) {
                return dataStructureEnum.javaType;
            }
        }
        return null;
    }
}
