package com.example.JsonClass;

import java.util.*;

public class ArrayNode extends JsonNode {

    private final List<JsonNode> _children;

    public ArrayNode() {
        _children = new ArrayList<>();
    }

    @Override
    public boolean isArray() {
        return true;
    }

    @Override
    public NodeType getNodeType() {
        return null;
    }

    public JsonNode get(int index) {
        return _children.get(index);
    }

    public void add(JsonNode value) {
        _children.add(value);
    }

    @Override
    public String toString() {
//        System.out.println("ARRAY_S");
        StringBuilder buf = new StringBuilder();
        buf.append("[");
        if (!_children.isEmpty()) {
            buf.append(_children.get(0));
        }
        for (int i = 1; i < _children.size(); i++) {
            buf.append(",").append(_children.get(i));
        }
        buf.append("]");
//        System.out.println("ARRAY_E");
        return buf.toString();
    }

    @Override
    public Map toDict() {
//        System.out.println("ARRAY_S");
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> dict = new LinkedHashMap<>();
//        if (!_children.isEmpty()) {
////            list.add(_children.get(0).toDict());
//            _children.get(0).toDict().forEach(
//                    (key, value) -> dict.put(key, value));
////            dict.put(_children.get(0).toDict())
//        }
        for (JsonNode child : _children) {
            list.add(child.toDict());
//            child.toDict().forEach(
//                    (key, value) -> dict.put(key.toString(), value));
        }
        dict.put("ARRAY", list);
//        System.out.println("ARRAY_E");
        return dict;
    }

}