package com.lrs.common.hamcrest;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasXPath;
import static org.junit.Assert.assertThat;

/**
 * 使用XPath进行断言
 *
 * @author Swedish-li
 */
public class XmlAssertTest {

    private final static DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

    private String bookStoreXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
            + "<bookstore>"
            + "<book>"
            + "<title lang=\"eng\">Harry Potter</title>"
            + "<price>29.99</price>"
            + "</book>"
            + "<book>"
            + "<title lang=\"eng\">Learning XML</title>"
            + "<price>39.95</price>"
            + "</book>"
            + "</bookstore>";

    @Test
    public void bookStoreXmlTest() throws Exception {
        Node node = parse(bookStoreXml);
        assertThat(node, hasXPath("/bookstore/book[1]"));
        assertThat(node, hasXPath("/bookstore/book[2]/title", equalTo("Learning XML")));
        assertThat(node, hasXPath("/bookstore/book[2]/title[@lang='eng']"));

    }

    @Test
    public void bookStoreXmlAttrTest() throws Exception {
        Node node = parse(bookStoreXml);
        // 属性选择
        assertThat(node, hasXPath("/bookstore/book[2]/title//@lang", equalTo("eng")));
    }

    private Node parse(String xml) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilder builder = documentBuilderFactory.newDocumentBuilder();
        Document doc = builder.parse(new InputSource(new StringReader(bookStoreXml)));
        return doc;
    }
}
