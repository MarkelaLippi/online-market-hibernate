package com.gmail.roadtojob2019.repositorymodule.converters;

import com.gmail.roadtojob2019.repositorymodule.models.Status;

import javax.persistence.AttributeConverter;

public class StatusConverter implements AttributeConverter<Status, String> {
    @Override
    public String convertToDatabaseColumn(Status status) {
        if (status == null) {
            return null;
        }
        return status.toString();
    }

    @Override
    public Status convertToEntityAttribute(String string) {
        if (string == null) {
            return null;
        }
        try {
            return Status.valueOf(string);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
