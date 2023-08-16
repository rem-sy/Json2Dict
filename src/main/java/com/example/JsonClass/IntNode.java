package com.example.JsonClass;


import java.util.LinkedHashMap;
import java.util.Map;

public class IntNode extends JsonNode {

    private int value;

    public IntNode() {

    }

    public IntNode(int value) {
        this.value = value;
    }

    @Override
    public boolean isInt() {
        return true;
    }

    @Override
    public NodeType getNodeType() {
        return NodeType.INT;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
//        System.out.println("INT");
        return Integer.toString(value);
    }

    @Override
    public Map toDict() {
        Map<String, Object> dict = new LinkedHashMap<>();
        dict.put("INT", value);
        return dict;
    }
}