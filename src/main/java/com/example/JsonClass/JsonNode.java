package com.example.JsonClass;

import java.util.Map;

public abstract class JsonNode {

    public boolean isObject() {
        return false;
    }

    public boolean isArray() {
        return false;
    }

    public boolean isBool() {
        return false;
    }

    public boolean isString() {
        return false;
    }

    public boolean isInt() {
        return false;
    }

    public boolean isDouble() {
        return false;
    }

    public boolean isNull() {
        return false;
    }

    public abstract NodeType getNodeType();

    public abstract Map toDict();
}