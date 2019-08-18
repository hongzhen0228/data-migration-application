package com.data.migration.service.common;

import com.data.migration.constant.DataStructureEnum;
import com.data.migration.dto.DataStructureDto;
import org.springframework.cglib.beans.BeanGenerator;
import org.springframework.cglib.beans.BeanMap;

import java.util.*;

public class DynamicBeanGenerator {

    private Object object;

    private BeanMap beanMap;


    public Object generateBean(Map propertyMap) throws ClassNotFoundException {
        BeanGenerator generator = new BeanGenerator();
        Set set = propertyMap.keySet();
        for (Iterator i = set.iterator(); i.hasNext();) {
            String key = (String) i.next();
            generator.addProperty(key,Class.forName((String)propertyMap.get(key)));
        }
        return generator.create();
    }

    public void setValue(Object property, Object value) {
        beanMap.put(property,value);
    }

    public void getValue(Object property){
        beanMap.get(property);
    }

    public Object getObject() {
        return this.object;
    }

    public static Map generteTableMap(List<DataStructureDto> dataStructureDtoList) {
        Map map = new HashMap();
        dataStructureDtoList.forEach(dataStructureDto -> {
            map.put(dataStructureDto.getField(), DataStructureEnum.getJavaType(dataStructureDto.getType()));
        });
        return map;
    }
}
