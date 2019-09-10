package org.eclipse.basyx.testsuite.regression.vab.provider.xml.transformers.tests;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.basyx.aas.impl.metamodel.hashmap.aas.parts.Asset;
import org.eclipse.basyx.vab.provider.xml.XmlParser;
import org.eclipse.basyx.vab.provider.xml.transformers.TransformAsset;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;
/**
 * Test case to test TransformAsset class
 * 
 * @author rajashek
 *
 */
public class TestTransformAsset {
	private Map<String, Object> rootObj = new HashMap<>();

	
	static final String xmlTestContent = "  <aas:asset>\r\n" + 
			"            <aas:idShort>3s7plfdrs35</aas:idShort>\r\n" + 
			"            <aas:description>\r\n" + 
			"                <aas:langString lang=\"EN\">Festo Controller</aas:langString>\r\n" + 
			"            </aas:description>\r\n" + 
			"            <aas:identification idType=\"URI\">http://pk.festo.com/3s7plfdrs35</aas:identification>\r\n" + 
			"            <aas:kind>Instance</aas:kind>\r\n" + 
			"        </aas:asset>";
	@Before
	public void TestBuildXmlMap() throws Exception {
		rootObj.putAll(XmlParser.buildXmlMap(xmlTestContent));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testAssetSingleObject() throws ParserConfigurationException, SAXException, IOException {
		Asset transformAsset = TransformAsset.transformAsset((Map<String, Object>) rootObj.get("aas:asset"));
		assertEquals(transformAsset.getIdshort(),"3s7plfdrs35");
		assertEquals(transformAsset.getDescription(),"Festo Controller");
		assertEquals(transformAsset.getIdentification().getIdType(),"URI");
		assertEquals((String)transformAsset.get("kind"),"Instance");
	}

}