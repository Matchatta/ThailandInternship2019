import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class convertData {
    File f;
    String gen;
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    DocumentBuilder db = null;
    Document doc = null;
    public convertData(File f){
        this.f = f;
        try {
            db = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        gen = null;
    }
    public void process(){
        gen = "{\n";
        try {
            doc = db.parse(f);
            Element e = doc.getDocumentElement();
            generate(e);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        gen+= "}";
        File w = new File("weather.json");
        try {
            if(w.createNewFile()){
                FileWriter fw = new FileWriter(w);
                fw.append(gen);
                System.out.println("Finish");
                fw.close();
            }
            else{
            	FileWriter fw = new FileWriter(w);
                fw.append(gen);
                System.out.println("Finish");
                fw.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void generate(Element E){
        if(E.hasChildNodes()) {
            NodeList l = E.getChildNodes();
            for (int i = 1; i < l.getLength(); i += 2) {
                Element r = (Element) l.item(i);
                if (r.hasAttributes() || (r.hasChildNodes() && r.getChildNodes().getLength() > 1)) {
                    if (gen.charAt(gen.length() - 1) == '\"') {
                        gen += ",\n  \"" + r.getNodeName() + "\" :{";
                    } else {
                        gen += "  \"" + r.getNodeName() + "\" :{";
                    }
                    for (int j = 0; j < r.getAttributes().getLength(); j++) {
                        gen += "\n  \"" + r.getAttributes().item(j).getNodeName() + "\" : " + "\"" + r.getAttributes().item(j).getTextContent() + "\"";
                        if (j < r.getAttributes().getLength() - 1) {
                            gen += ",";
                        } else {
                            gen += "";
                        }
                    }
                    generate(r);
                    if (i < l.getLength() - 2) {
                        gen += "\n  },\n";
                    } else {
                        gen += "\n  }\n";
                    }
                } else {
                    gen += "  \"" + r.getNodeName() + "\" : " + "\"" + r.getTextContent() + "\"";
                    generate(r);
                    if (i < l.getLength() - 2) {
                        gen += ",\n";
                    } else {
                        gen += "\n";
                    }
                }

            }
        }
    }
}
