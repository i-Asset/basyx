package org.eclipse.basyx.testsuite.regression.vab.provider.xml.transformers.tests;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.basyx.aas.impl.metamodel.hashmap.aas.AssetAdministrationShell;
import org.eclipse.basyx.aas.impl.metamodel.hashmap.aas.SubModel;
import org.eclipse.basyx.aas.impl.metamodel.hashmap.aas.parts.Asset;
import org.eclipse.basyx.aas.impl.metamodel.hashmap.aas.parts.ConceptDescription;
import org.eclipse.basyx.vab.provider.xml.XmlParser;
import org.eclipse.basyx.vab.provider.xml.transformers.TransformAsset;
import org.eclipse.basyx.vab.provider.xml.transformers.TransformAssetAdministrationShell;
import org.eclipse.basyx.vab.provider.xml.transformers.TransformConceptDescription;
import org.eclipse.basyx.vab.provider.xml.transformers.TransformSubmodel;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

/**
 * This is the class which has APIs for converting XML tags to corresponding
 * objects.
 * 
 * @author rajashek
 *
 */
public class TranformXMLtags {
	
	private Map<String, Object> rootObj = new HashMap<>();
	String xmlTestContent;
	
	@Before
	public void TestBuildXmlMap() throws Exception {
		String filePath = "src/test/java/org/eclipse/basyx/testsuite/regression/vab/provider/xml/transformers/tests/detail.xml";
		  try
	        {
			  xmlTestContent = new String ( Files.readAllBytes( Paths.get(filePath) ) );
			  rootObj.putAll(XmlParser.buildXmlMap(xmlTestContent));
	        }
	        catch (IOException e)
	        {
	            e.printStackTrace();
	        }
		
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void populateAssetAdministrationShell() throws ParserConfigurationException, SAXException, IOException {
		HashSet<AssetAdministrationShell> assetSet=new HashSet<AssetAdministrationShell>();
		//get the Asset admin shell info from root and start processing it one by one 
		Object administrationShellObj = TransformAssetAdministrationShell.getAssetAdminShellsFromRootObj(rootObj).get("aas:assetAdministrationShell");
		
		if (administrationShellObj instanceof Collection<?>){
			ArrayList<Object> administrationShellArrayList=(ArrayList<Object>)administrationShellObj;
			for (Object object : administrationShellArrayList) {
				assetSet.add(TransformAssetAdministrationShell.transformAssetAdministrationShell((Map<String, Object>) object));
			}
		}
		else{
			Map<String, Object>  administrationShellMapObj = (Map<String, Object>) administrationShellObj;	
			assetSet.add(TransformAssetAdministrationShell.transformAssetAdministrationShell(administrationShellMapObj));
		}
		
	}
	
	
	@SuppressWarnings("unchecked")
	@Test
	public void populateAsset() throws ParserConfigurationException, SAXException, IOException {
		HashSet<Asset> assetSet=new HashSet<Asset>();
		Object assetObject = TransformAsset.getAssetFromRootObj(rootObj).get("aas:asset");
		if (assetObject instanceof Collection<?>){
			ArrayList<Object> assetArrayList=(ArrayList<Object>)assetObject;
			for (Object object : assetArrayList) {
				assetSet.add(TransformAsset.transformAsset((Map<String, Object>) object));
			}
		}
		else {
			Map<String, Object>  assetMapObj = (Map<String, Object>) assetObject;
			assetSet.add(TransformAsset.transformAsset(assetMapObj));


			
		}
		
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void populateSubmodel() throws ParserConfigurationException, SAXException, IOException {
		HashSet<SubModel> submodelSet=new HashSet<SubModel>();
		Object submodelObj = TransformSubmodel.getSubmodelFromRootObj(rootObj).get("aas:submodel");
		if (submodelObj instanceof Collection<?>){
			ArrayList<Object> submodelArrayList=(ArrayList<Object>)submodelObj;
			for (Object object : submodelArrayList) {
				submodelSet.add(TransformSubmodel.transformSubmodel((Map<String, Object>) object));
			}
		}
		else {
			Map<String, Object>  subModelMapObj = (Map<String, Object>) submodelObj;
			submodelSet.add(TransformSubmodel.transformSubmodel(subModelMapObj));

		}
	}
	
	
	@SuppressWarnings("unchecked")
	@Test
	public void populateconceptDescription() throws ParserConfigurationException, SAXException, IOException {
		HashSet<ConceptDescription> ConceptDescriptionSet=new HashSet<ConceptDescription>();
		Object conceptDescriptionObj = TransformConceptDescription.getconceptDescriptionFromRootObj(rootObj).get("aas:conceptDescription");
		if(conceptDescriptionObj instanceof Collection<?> ) {
			ArrayList<Object> conceptDescriptionArrayList=(ArrayList<Object>)conceptDescriptionObj;
			for (Object object : conceptDescriptionArrayList) {
				ConceptDescriptionSet.add(TransformConceptDescription.transformConceptDescription((Map<String, Object>) object));
				System.out.println();
			}
		}else {
			Map<String, Object>  conceptDescriptionMapObj = (Map<String, Object>) conceptDescriptionObj;
			ConceptDescriptionSet.add(TransformConceptDescription.transformConceptDescription(conceptDescriptionMapObj));
			System.out.println();
		}
		
		
	}
	



	

	


}
