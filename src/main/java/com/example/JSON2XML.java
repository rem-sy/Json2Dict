package com.example;
import JsonP.JsonParser;
import JsonP.JsonBaseListener;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.InputStream;

/***
 * 将json翻译成XML，非常经典和实用
 * 使用ParseTreeProperty暂存每个节点的内容，最后打印全部语法树
 ***/
public class JSON2XML {
    public static class XMLEmitter extends JsonBaseListener {
        ParseTreeProperty<String> xml = new ParseTreeProperty<String>();

        String getXML(ParseTree ctx) {
            return xml.get(ctx);
        }

        void setXML(ParseTree ctx, String s) {
            xml.put(ctx, s);
        }

        public void exitJson(JsonParser.JsonContext ctx) {
            setXML(ctx, getXML(ctx.getChild(0)));
        }

        public void exitAnObject(JsonParser.AnObjectContext ctx) {
            StringBuilder buf = new StringBuilder();
            buf.append("\n");
            for (JsonParser.PairContext pctx : ctx.pair()) {
                buf.append(getXML(pctx));
            }
            setXML(ctx, buf.toString());
        }

        public void exitEmptyObject(JsonParser.EmptyObjectContext ctx) {
            setXML(ctx, "");
        }

        public void exitArrayOfValues(JsonParser.ArrayOfValuesContext ctx) {
            StringBuilder buf = new StringBuilder();
            buf.append("\n");
            for (JsonParser.ValueContext vctx : ctx.value()) {
                buf.append("<element>"); // conjure up element for valid XML
                buf.append(getXML(vctx));
                buf.append("</element>");
                buf.append("\n");
            }
            setXML(ctx, buf.toString());
        }

        public void exitEmptyArray(JsonParser.EmptyArrayContext ctx) {
            setXML(ctx, "");
        }

        public void exitPair(JsonParser.PairContext ctx) {

            String tag = stripQuotes(ctx.STRING().getText());
            JsonParser.ValueContext vctx = ctx.value();
            String x = String.format("<%s>%s</%s>\n", tag, getXML(vctx), tag);
            setXML(ctx, x);
        }

        public void exitObjectValue(JsonParser.ObjectValueContext ctx) {
            // analogous to String value() {return object();}
            setXML(ctx, getXML(ctx.object()));
        }

        public void exitArrayValue(JsonParser.ArrayValueContext ctx) {
            setXML(ctx, getXML(ctx.array())); // String value() {return array();}
        }

        public void exitAtom(JsonParser.AtomContext ctx) {
            setXML(ctx, ctx.getText());
        }

        public void exitString(JsonParser.StringContext ctx) {
            setXML(ctx, stripQuotes(ctx.getText()));
        }

        //剥离json双引号
        public static String stripQuotes(String s) {
            if (s == null || s.charAt(0) != '"') return s;
            return s.substring(1, s.length() - 1);
        }
    }

    public static void main(String[] args) throws Exception {
        InputStream is = test.class.getClassLoader().getResourceAsStream("demo.json");

        CharStream input = CharStreams.fromStream(is);
        JsonP.JsonLexer lexer = new JsonP.JsonLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JsonParser parser = new JsonParser(tokens);
        parser.setBuildParseTree(true);
        ParseTree tree = parser.json();
        // show tree in text form
        System.out.println(tree.toStringTree(parser));

        ParseTreeWalker walker = new ParseTreeWalker();
        XMLEmitter converter = new XMLEmitter();
        walker.walk(converter, tree);
        System.out.println(converter.getXML(tree));
    }
}
