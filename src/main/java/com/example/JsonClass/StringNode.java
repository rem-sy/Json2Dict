package com.example.JsonClass;

import java.util.LinkedHashMap;
import java.util.Map;

public class StringNode extends JsonNode {

    private String value;

    public StringNode() {
    }

    public StringNode(String value) {
        this.value = value;
    }

    @Override
    public boolean isString() {
        return true;
    }

    @Override
    public NodeType getNodeType() {
        return NodeType.STRING;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
//        System.out.println("STRING");
        return value;
    }

    @Override
    public Map toDict() {
        Map<String, Object> dict = new LinkedHashMap<>();
        dict.put("STRING", value);
        return dict;
    }
}