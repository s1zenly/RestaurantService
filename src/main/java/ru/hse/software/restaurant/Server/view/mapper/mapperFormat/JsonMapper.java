package ru.hse.software.restaurant.Server.view.mapper.mapperFormat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import ru.hse.software.restaurant.Server.view.mapper.interfaces.DataFormatMapper;

@RequiredArgsConstructor
public class JsonMapper<T> implements DataFormatMapper<String, T> {

    private final ObjectMapper objectMapper;

    @Override
    public String inFormat(T object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }

    @Override
    public T inObject(String json, Class<T> valueType) throws JsonProcessingException {
        return objectMapper.readValue(json, valueType);
    }
}
