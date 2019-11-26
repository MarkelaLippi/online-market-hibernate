package com.gmail.RoadToJob2019.repositorymodule.converters;

import com.gmail.RoadToJob2019.repositorymodule.models.Role;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class RoleConverter implements AttributeConverter<Role, String> {

    @Override
    public String convertToDatabaseColumn(Role priority) {
        if (priority == null) {
            return null;
        }
        return priority.toString();
    }

    @Override
    public Role convertToEntityAttribute(String string) {
        if (string == null) {
            return null;
        }
        try {
            return Role.valueOf(string);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

}