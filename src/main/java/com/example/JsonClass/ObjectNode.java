package com.example.JsonClass;

import java.util.LinkedHashMap;
import java.util.Map;

public class ObjectNode extends JsonNode {

    private Map<String, JsonNode> _children;

    public ObjectNode() {
        _children = new LinkedHashMap<>();
    }

    @Override
    public boolean isObject() {
        return true;
    }

    @Override
    public NodeType getNodeType() {
        return NodeType.OBJECT;
    }

    public JsonNode get(String key) {
        return _children.get(key);
    }

    public void set(String key, JsonNode value) {
        _children.put(key, value);
    }

    @Override
    public String toString() {
//        System.out.println("OBJECT_S");
        StringBuilder buf = new StringBuilder();
        buf.append("{\n");
        _children.forEach((k, v) -> buf.append(k).append(": ").append(v).append(",\n"));
        if (!_children.isEmpty()) {
            buf.deleteCharAt(buf.length() - 2);
        }
        buf.append("}");
//        System.out.println("OBJECT_E");
        return buf.toString();
    }

    @Override
    public Map toDict() {
        Map<String, Object> dict = new LinkedHashMap<>();
        Map<String, Object> dicts = new LinkedHashMap<>();
        _children.forEach((k, v) -> dicts.put(k,v.toDict()));
        dict.put("OBJECT", dicts);
        return dict;
    }
}