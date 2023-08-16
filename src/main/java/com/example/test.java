package com.example;

import JsonP.JsonParser;
import JsonP.JsonVisitor;
import com.alibaba.fastjson.JSON;
import com.example.JsonClass.JsonNode;
import com.example.JsonP.MyJsonVisitor;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import com.alibaba.fastjson.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class test {
    public static void main(String[] args) throws IOException{
        InputStream is = test.class.getClassLoader().getResourceAsStream("demo.json");

        JsonP.JsonLexer lexer = new JsonP.JsonLexer(CharStreams.fromStream(is));
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        JsonP.JsonParser parser = new JsonParser(tokens);
        ParseTree jsonTree = parser.json();

        MyJsonVisitor jsonVisitor = new MyJsonVisitor();
//        JsonVisitor jsonVisitor = new JsonVisitor();
        jsonVisitor.visit(jsonTree);
        JsonNode jsonNode = jsonVisitor.jsonNode;
        System.out.println(jsonNode.toDict());
        jsonNode.toDict().forEach(
                (k,v) -> System.out.println(k + ": " + v.toString()));
        System.out.println(jsonNode.getNodeType());
        JSONObject data = JSON.parseObject(jsonNode.toString());
        System.out.println(data.getJSONArray("c").get(0));
        System.out.println(data.getJSONObject("d").get("email"));
    }
}
