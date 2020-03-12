import com.alibaba.fastjson.JSON;
import org.dom4j.*;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CodingErrorAction;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class XmlUtil {



    public String xml2Json(String xml) throws DocumentException {

        SAXReader reader = new SAXReader();
        InputStream is = new ByteArrayInputStream(xml.getBytes());
        Document document = reader.read(is);
        Element root = document.getRootElement();



        List<Map<String, String>> mapList = new ArrayList<>();
        for (Iterator<Element> it = root.elementIterator("WFItem"); it.hasNext();) {
            Element wfItem = it.next();
            Map<String, String> map = new HashMap<>();
            String name = wfItem.attribute("name").getValue();
            String text = wfItem.getText();
            map.put("name", name);
            map.put("text", text);
            if (wfItem.hasContent() && wfItem.node(0).getNodeType()==Node.CDATA_SECTION_NODE) {
                map.put("cdata", "true");
            }
            mapList.add(map);
        }

        return JSON.toJSONString(mapList);
    }
    
    
    public  String json2Xml(String json) throws IOException {
        List<Map<String, String>> mapList = (List<Map<String, String>>) JSON.parse(json);
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement(new QName("Items"));

        mapList.forEach(map->{
            Element wfItem = root.addElement("WFItem").addAttribute("name", map.get("name"));
            if ("true".equals(map.get("cdata"))) {
                wfItem.addCDATA(map.get("text"));
            } else {
                wfItem.setText(map.get("text"));
            }
        });

        System.out.println(document.getRootElement().asXML());

        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding("utf-8");
        format.setIndent(true);
        format.setIndent(" ");
        format.setIndentSize(4);
        format.setXHTML(true);
        format.setNewlines(true);
        format.setExpandEmptyElements(true);
        XMLWriter writer = new XMLWriter(System.out, format);
        writer.write( document.getRootElement().asXML() );
        writer.close();
        return null;
    }

}
