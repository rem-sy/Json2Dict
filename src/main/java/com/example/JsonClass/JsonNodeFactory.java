package com.example.JsonClass;


public class JsonNodeFactory {

    public ObjectNode objectNode() {
        return new ObjectNode();
    }

    public ArrayNode arrayNode() {
        return new ArrayNode();
    }

    public BoolNode boolNode(boolean value) {
        return new BoolNode(value);
    }

    public IntNode intNode() {
        return new IntNode();
    }

    public IntNode intNode(int value) {
        return new IntNode(value);
    }

    public DoubleNode doubleNode() {
        return new DoubleNode();
    }

    public DoubleNode doubleNode(double value) {
        return new DoubleNode(value);
    }

    public NullNode nullNode() {
        return new NullNode();
    }

    public StringNode stringNode() {
        return new StringNode();
    }

    public StringNode stringNode(String value) {
        return new StringNode(value);
    }
}