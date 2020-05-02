package com.gmail.roadtojob2019.repositorymodule.converters;

import com.gmail.roadtojob2019.repositorymodule.models.Unit;

import javax.persistence.AttributeConverter;

public class UnitConverter implements AttributeConverter<Unit, String> {
    @Override
    public String convertToDatabaseColumn(Unit unit) {
        if (unit == null) {
            return null;
        }
        return unit.toString();
    }

    @Override
    public Unit convertToEntityAttribute(String string) {
        if (string == null) {
            return null;
        }
        try {
            return Unit.valueOf(string);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
