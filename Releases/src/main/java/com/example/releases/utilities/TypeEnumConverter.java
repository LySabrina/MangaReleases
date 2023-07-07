package com.example.releases.utilities;

import com.example.releases.model.Type;
import org.springframework.stereotype.Component;
import org.springframework.core.convert.converter.Converter;
@Component
public class TypeEnumConverter implements Converter<String, Type>{

    @Override
    public Type convert(String source) {
        return Type.valueOf(source.toUpperCase());
    }

    @Override
    public <U> Converter<String, U> andThen(Converter<? super Type, ? extends U> after) {
        return Converter.super.andThen(after);
    }
}
