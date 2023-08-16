package com.example.JsonClass;


import java.util.LinkedHashMap;
import java.util.Map;

public class DoubleNode extends JsonNode {

    private double value;

    public DoubleNode() {
    }

    public DoubleNode(double value) {
        this.value = value;
    }

    @Override
    public boolean isDouble() {
        return true;
    }

    @Override
    public NodeType getNodeType() {
        return NodeType.DOUBLE;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
//        System.out.println("DOUBLE");
        return Double.toString(value);
    }
    @Override
    public Map toDict() {
        Map<String, Object> dict = new LinkedHashMap<>();
        dict.put("DOUBLE", value);
        return dict;
    }
}