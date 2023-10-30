package com.glanz.ourbatis.builder;

import com.glanz.ourbatis.mapping.ResultMap;
import com.glanz.ourbatis.mapping.ResultMapping;

import java.util.List;


/**
 * @Author GlanzWen
 * @Description 结果映射解析器
 * @github:
 */
public class ResultMapResolver {

    private final MapperBuilderAssistant assistant;
    private String id;
    private Class<?> type;
    private List<ResultMapping> resultMappings;

    public ResultMapResolver(MapperBuilderAssistant assistant, String id, Class<?> type, List<ResultMapping> resultMappings) {
        this.assistant = assistant;
        this.id = id;
        this.type = type;
        this.resultMappings = resultMappings;
    }

    public ResultMap resolve() {
        return assistant.addResultMap(this.id, this.type, this.resultMappings);
    }

}

