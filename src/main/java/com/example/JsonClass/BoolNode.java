package com.example.JsonClass;

import java.util.LinkedHashMap;
import java.util.Map;

public class BoolNode extends JsonNode{
    private boolean value;

    public BoolNode(){
    }

    public BoolNode(boolean value) {
        this.value = value;
    }


    @Override
    public boolean isBool() {
        return true;
    }

    @Override
    public NodeType getNodeType() {
        return NodeType.BOOL;
    }

    public boolean getValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    @Override
    public String toString() {
//        System.out.println("BOOL");
        return Boolean.toString(value);
    }
    @Override
    public Map toDict() {
        Map<String, Object> dict = new LinkedHashMap<>();
        dict.put("BOOL", value);
        return dict;
    }

}
