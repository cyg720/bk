

package com.skill.framewook.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * xml 转换工具类
 * @author  
 *
 */
public class XMLUtil {
       
    private Marshaller marshaller;
    private Unmarshaller unmarshaller;

    /**
     * 参数types为所有需要序列化的Root对象的类型.
     */
    public XMLUtil(Class<?>... types) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(types);
            marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty("jaxb.formatted.output", Boolean.TRUE);
            unmarshaller = jaxbContext.createUnmarshaller();
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Java->Xml
     */
    public String marshal(Object root) {
        try {
            StringWriter writer = new StringWriter();
            marshaller.marshal(root, writer);
            return writer.toString();
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Xml->Java
     */
    @SuppressWarnings("unchecked")
    public <T> T unmarshal(String xml) {
        try {
            StringReader reader = new StringReader(xml);
            return (T) unmarshaller.unmarshal(reader);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Xml->Java
     */
    @SuppressWarnings("unchecked")
    public <T> T unmarshal(InputStream in) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(in, "GBK"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

        try {
            return (T) unmarshaller.unmarshal(br);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }
}