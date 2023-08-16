package com.example.JsonClass;

import java.util.LinkedHashMap;
import java.util.Map;

public class NullNode extends JsonNode{


    public NullNode() {

    }

    @Override
    public boolean isNull() {
        return true;
    }

    @Override
    public NodeType getNodeType() {
        return NodeType.NULL;
    }


    @Override
    public String toString() {
//        System.out.println("NULL");
        return "null";
    }

    @Override
    public Map toDict() {
        Map<String, Object> dict = new LinkedHashMap<>();
        dict.put("NULL", null);
        return dict;
    }
}
