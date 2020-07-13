package com.hx.auto;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.*;

/**
 * 自动生成Service and ServiceImpl
 * 
 * @author chenjiahe 2020-06-28
 * 
 */
public class GeneratorReadXmlUtil {

	/**
	 *
	 * @param targetFile 文件路径
	 * @return 返回自定义的sql
	 */
	public static String readMapperXml(String  targetFile) throws ParserConfigurationException, IOException, SAXException {
		String temp = "";

		//映射文件的文件夹
		File file = new File(targetFile);
		if(!file.exists()){
			return "";
		}

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(file);
		Node Node= doc.getLastChild();
		NodeList nodeList = Node.getChildNodes();

		String comment = "";
		for(int i=0;i<nodeList.getLength();i++) {
			//从节点集中获取i个book节点
			Node childNode = nodeList.item(i);
			//获取子节点内的文本内容
			String content = childNode.getTextContent();
			//获取节点名
			String name = childNode.getNodeName();
			//System.out.println("content:"+content+";name:"+name);
			//System.out.println("name....:"+name);
			if(!"#text".equals(name)&&!"#comment".equals(name)){
				//获取第一个节点内的所有属性
				NamedNodeMap nameNodeMap = childNode.getAttributes();
				//获取节点内名为id的属性的值
				String id = nameNodeMap.getNamedItem("id").getTextContent();
				if(isCustom(nameNodeMap.getNamedItem("id").getTextContent())){
					String con = NodetoString(childNode);
					//去掉附带的内容
					con = con.replace("<?xml version=\"1.0\" encoding=\"UTF-8\"?>","");
					temp+=comment+con;
				}
				comment = "";
			}else{
				String con = NodetoString(childNode);
				//去掉附带的内容
				con = con.replace("<?xml version=\"1.0\" encoding=\"UTF-8\"?>","");
				comment+= con;
			}
		}
		System.out.println("temp:...:"+temp);
		return temp;
	}

	public static boolean isCustom(String id){
		boolean custom = true;
		Set<String> noCustomIds = new HashSet<String>();
		noCustomIds.add("BaseResultMap");
		noCustomIds.add("Entity_Id");
		noCustomIds.add("Table_Id");
		noCustomIds.add("Table_Name");
		noCustomIds.add("Base_Column_List");
		noCustomIds.add("Blob_Column_List");
		noCustomIds.add("Insert_Column_All");
		noCustomIds.add("Update_Column_All");
		noCustomIds.add("Update_Column_NoNull");
		noCustomIds.add("keyFind");
		noCustomIds.add("insert");
		noCustomIds.add("selectList");
		noCustomIds.add("selectListBlob");
		noCustomIds.add("selectOne");
		noCustomIds.add("selectOneBlob");
		noCustomIds.add("selectOneByKey");
		noCustomIds.add("selectOneByKeyBlob");
		noCustomIds.add("updateWhere");
		noCustomIds.add("updateAll");
		noCustomIds.add("updateByNoNull");
		noCustomIds.add("deleteWhere");
		noCustomIds.add("deleteById");
		noCustomIds.add("selectCount");
		noCustomIds.add("Insert_Values_All");
		noCustomIds.add("selectListMap");
		noCustomIds.add("selectOneMap");
		if (noCustomIds.contains(id)){
			custom = false;
		}
		return custom;
	}



	/*
	 * 把dom文件转换为xml字符串
	 */
	public static String toStringFromDoc(Document document) throws IOException {
		String result = null;

		if (document != null) {
			StringWriter strWtr = new StringWriter();
			StreamResult strResult = new StreamResult(strWtr);
			TransformerFactory tfac = TransformerFactory.newInstance();
			try {
				javax.xml.transform.Transformer t = tfac.newTransformer();
				t.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
				t.setOutputProperty(OutputKeys.INDENT, "yes");
				t.setOutputProperty(OutputKeys.METHOD, "xml"); // xml, html,
// text
				t.setOutputProperty(
						"{http://xml.apache.org/xslt}indent-amount", "4");
				t.transform(new DOMSource(document.getDocumentElement()),
						strResult);
			} catch (Exception e) {
				System.err.println("XML.toString(Document): " + e);
			}
			result = strResult.getWriter().toString();
			strWtr.close();
		}
		return result;
	}


	/**
	 * 将传入的一个DOM Node对象输出成字符串。如果失败则返回一个空字符串""。
	 *
	 * @param node
	 *            DOM Node 对象。
	 * @return a XML String from node
	 */
	public static String NodetoString(Node node) {
		Transformer transformer = null;
		String result = null;
		if (node == null) {
			throw new IllegalArgumentException();
		}
		try {
			transformer = TransformerFactory.newInstance().newTransformer();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		if (transformer != null) {
			try {
				StringWriter sw = new StringWriter();
				transformer.transform(new DOMSource(node), new StreamResult(sw));
				return sw.toString();
			} catch (TransformerException te) {
				throw new RuntimeException(te.getMessage());
			}
		}
		return result;
	}


}