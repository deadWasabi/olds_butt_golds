package com.accenture.SmartOffice.utilits;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class MappingHelper {

    @Autowired
    private static DozerBeanMapper dozerBeanMapper;

    public static <S, D> List<D> map(List<S> sourceList, Class<D> dClass) {
        if (sourceList == null) {
            return null;
        }

        ArrayList<D> arrayList = new ArrayList<>();
        sourceList.forEach( s -> arrayList.add(dozerBeanMapper.map(s, dClass)));
        return arrayList;
    }
}
