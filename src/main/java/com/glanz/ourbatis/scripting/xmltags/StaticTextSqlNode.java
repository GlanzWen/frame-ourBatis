package com.glanz.ourbatis.scripting.xmltags;

/**
 * @Author GlanzWen
 * @Description 静态文本SQL节点
 * @github
 */
public class StaticTextSqlNode implements SqlNode{

    private String text;

    public StaticTextSqlNode(String text) {
        this.text = text;
    }

    @Override
    public boolean apply(DynamicContext context) {
        //将文本加入context
        context.appendSql(text);
        return true;
    }

}
