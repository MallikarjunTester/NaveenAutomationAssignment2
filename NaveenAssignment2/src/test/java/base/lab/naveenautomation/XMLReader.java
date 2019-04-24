package base.lab.naveenautomation;

import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLReader {
	
	//getChildNodes = method used to get the list of child nodes ; returs NodeList object
	//NodeList = is a class
	//Node = class ; element of NodeList is a Node . 
	
	Document doc;

	public XMLReader(String path) {
		
		try {
			
			File file = new File(path);

			DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

			doc = dBuilder.parse(file);
			
		}catch (Exception e) {
			
			System.out.println(e.getMessage());
		}
	}

	public List<Hashtable<String, String>> getDataAsList(String testName) {
		
		List<Hashtable<String, String>> list = new ArrayList<Hashtable<String, String>>();
		
		Hashtable<String, String> table = null;
		
		Node testCaseNode = this.getNode(testName);
		
		NodeList dataNodes = testCaseNode.getChildNodes();
		
		for (int count = 0; count < dataNodes.getLength(); count++){
			
			table = new Hashtable<String, String>();
			
			Node dataNode = dataNodes.item(count);

			if (dataNode.getNodeType() == Node.ELEMENT_NODE) {
				
				// System.out.println(dataNode.getNodeName());
				// System.out.println(dataNode.getTextContent());

				NodeList dataList = dataNode.getChildNodes();
				
				for (int c = 0; c < dataList.getLength(); c++) {
					
					Node data = dataList.item(c);
					
					if (data.getNodeType() == Node.ELEMENT_NODE) {
						
						// System.out.println(data.getNodeName() +" --- "+
						// data.getTextContent());
						
						table.put(data.getNodeName(), data.getTextContent());
					}
				}
				list.add(table);
			}
		}

		return list;
	}

	public Node getNode(String nodeName) {
		
		
		NodeList list = doc.getChildNodes();
		
		Node rootNode = list.item(0);
		
		NodeList testCasesList = rootNode.getChildNodes();

		for (int count = 0; count < testCasesList.getLength(); count++) {
			
			Node tempNode = testCasesList.item(count);

			if (tempNode.getNodeType() == Node.ELEMENT_NODE) {
				// System.out.println(tempNode.getNodeName());
				if (nodeName.equals(tempNode.getNodeName())) {
					// System.out.println(tempNode.getTextContent());
					return tempNode;
				}
			}
		}
		return null;
	}

}
