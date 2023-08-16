package com.example;

import JsonP.JsonParser;
import com.alibaba.fastjson.JSON;
import com.example.JsonClass.JsonNode;
import com.example.JsonP.MyJsonVisitor;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import com.alibaba.fastjson.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;


public class JSON2DICT {

    public static Boolean isIN(String k){
        return k.equals("INT") ||
                k.equals("STRING") ||
                k.equals("BOOL") ||
                k.equals("DOUBLE") ||
                k.equals("NULL");

    }
//    private static String getKV(Map<String, Object> jsonNode){
//        StringBuilder buf = new StringBuilder();
//        buf.append("{\n");
//        jsonNode.forEach(
//                (k,v) -> {
////                    System.out.println(k);
//                    if (k.equals("OBJECT")){
//                        buf.append("{\n");
//                        buf.append(getKV(new ObjectMapper().convertValue(v, Map.class)) + "\n");
//                        buf.append("}\n");
//                    } else if (k.equals("ARRAY")) {
//                        buf.append("[\n");
//                        buf.append(getKV((Map<String, Object>) objectToMap(v)) + "\n");
//                        buf.append("]\n");
//                    }
//                    else if (isIN(k)){
//                        buf.append(v + "\n");
//                    }
//                    else {
//                        buf.append(k + ":\n");
//                        try {
//                            buf.append(getKV(getObjectToMap(v)) + "\n");
//                        } catch (IllegalAccessException e) {
//                            throw new RuntimeException(e);
//                        }
//                    }
//                });
//
//        buf.append("}");
//        return buf.toString();
//    }
    public static void main(String[] args) throws IOException{
        InputStream is = JSON2DICT.class.getClassLoader().getResourceAsStream("demo.json");

        JsonP.JsonLexer lexer = new JsonP.JsonLexer(CharStreams.fromStream(is));
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        JsonP.JsonParser parser = new JsonParser(tokens);
        ParseTree jsonTree = parser.json();

        MyJsonVisitor jsonVisitor = new MyJsonVisitor();
//        JsonVisitor jsonVisitor = new JsonVisitor();
        jsonVisitor.visit(jsonTree);
        JsonNode jsonNode = jsonVisitor.jsonNode;
        System.out.println(jsonNode.toDict().keySet());
        System.out.println(((Map<?, ?>)jsonNode.toDict().get("OBJECT")).get("\"d\""));
        jsonNode.toDict().forEach(
                (k,v) -> System.out.println(k + ": " + v.toString()));
        System.out.println(jsonNode.getNodeType());
        JSONObject data = JSON.parseObject(jsonNode.toString());
        System.out.println(data.getJSONArray("c").get(0));
        System.out.println(data.getJSONObject("d").get("email"));
    }
}
