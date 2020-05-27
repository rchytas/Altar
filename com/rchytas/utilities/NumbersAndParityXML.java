package com.rchytas.utilities;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.ProcessingInstruction;

public class NumbersAndParityXML {

	public static DocumentBuilderFactory documentBuilderFactory;
	public static DocumentBuilder documentBuilder;
	public static Document document;
	public static Element rootElement;
	public static FileOutputStream fop;
	public static File xmlFile;
	public static String xmlFileName;

	/**
	 * initializeXML()
	 * 
	 * @throws ParserConfigurationException
	 */
	public static void initializeXML() throws ParserConfigurationException {
		String root = "Whole_Numbers";
		documentBuilderFactory = DocumentBuilderFactory.newInstance();
		documentBuilder = documentBuilderFactory.newDocumentBuilder();
		document = documentBuilder.newDocument();
		rootElement = document.createElement(root);
		ProcessingInstruction pi = document.createProcessingInstruction(
				"xml-stylesheet", "type=\"text/xsl\" href=\"numbers.xsl\"");
		document.appendChild(rootElement);
		document.insertBefore(pi, rootElement);
	}

	/**
	 * initializeDiskFile()
	 */
	public static void initializeDiskFile() {
		try {
			xmlFile = new File(xmlFileName);
			fop = new FileOutputStream(xmlFile);
			// if file doesn't exist, then create it
			if (!xmlFile.exists()) {
				xmlFile.createNewFile();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * createNumberElement(int, String)
	 * 
	 * @param value
	 * @param parity
	 */
	public static void createNumberElement(int value, String parity) {
		String number = "Number";
		String valueText = "Value";
		String parityText = "Parity";
		try {
			Element numberElement = document.createElement(number);
			rootElement.appendChild(numberElement);

			Element valueElement = document.createElement(valueText);
			valueElement.appendChild(document.createTextNode(Integer
					.toString(value)));
			numberElement.appendChild(valueElement);

			Element parityElement = document.createElement(parityText);
			parityElement.appendChild(document.createTextNode(parity));
			numberElement.appendChild(parityElement);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * writeXMLFile()
	 * 
	 * @throws TransformerException
	 * @throws IOException
	 */
	public static void writeXMLFile() throws TransformerException, IOException {
		try {
			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(document);
			StreamResult result = new StreamResult(new StringWriter());

			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(
					"{http://xml.apache.org/xslt}indent-amount", "5");
			transformer.transform(source, result);

			String xmlString = result.getWriter().toString();

			byte[] contentInBytes = xmlString.getBytes();
			fop.write(contentInBytes);
			fop.flush();
			fop.close();

			System.out.println("File Created");

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fop != null) {
					fop.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			BufferedReader bf = new BufferedReader(new InputStreamReader(
					System.in));
			System.out.print("Enter File Name and Path: ");
			xmlFileName = bf.readLine();
			initializeXML();
			initializeDiskFile();
			for (int i = 1; i <= 100; i++) {
				if (i % 2 == 0)
					createNumberElement(i, "even");
				else
					createNumberElement(i, "odd");
			}
			writeXMLFile();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
