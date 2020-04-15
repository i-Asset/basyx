package org.eclipse.basyx.submodel.factory.xml.converters.qualifier;

import java.util.Map;

import org.eclipse.basyx.submodel.factory.xml.converters.reference.ReferenceXMLConverter;
import org.eclipse.basyx.submodel.metamodel.api.qualifier.IHasSemantics;
import org.eclipse.basyx.submodel.metamodel.api.reference.IReference;
import org.eclipse.basyx.submodel.metamodel.map.qualifier.HasSemantics;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Handles the conversion between an IHasSemantics object and the XML tag &lt;aas:semanticId&gt; in both directions
 * 
 * @author conradi
 *
 */
public class HasSemanticsXMLConverter {

	public static final String SEMANTIC_ID = "aas:semanticId";
	

	/**
	 * Populates a given IHasSemantics object with the data form the given XML
	 * 
	 * @param xmlObject the XML map containing the &lt;aas:semanticId&gt; tag
	 * @param hasSemantics the IHasDataSpecification object to be populated -treated as Map here-
	 */
	@SuppressWarnings("unchecked")
	public static void populateHasSemantics(Map<String, Object> xmlObject, Map<String, Object> hasSemantics) {
		//The IHasSemantics object has to be treated as Map here, as the Interface has no Setters
		
		Map<String, Object> xmlSemanticIDObj = (Map<String, Object>) xmlObject.get(SEMANTIC_ID);
		hasSemantics.put(HasSemantics.SEMANTICID, ReferenceXMLConverter.parseReference(xmlSemanticIDObj));
	}
	
	
	
	
	/**
	 * Populates a given XML map with the data form a given IHasSemantics object<br>
	 * Creates the &lt;aas:semanticId&gt; tag in the given root
	 * 
	 * @param document the XML document
	 * @param root the XML root Element to be populated
	 * @param hasSemantics the IHasSemantics object to be converted to XML
	 */
	public static void populateHasSemanticsXML(Document document, Element root, IHasSemantics hasSemantics) {
		IReference semanticId = hasSemantics.getSemanticId();
		Element semanticIdRoot = document.createElement(SEMANTIC_ID);
		if(semanticId != null) {
			semanticIdRoot.appendChild(ReferenceXMLConverter.buildReferenceXML(document, semanticId)); 
		}
		root.appendChild(semanticIdRoot);
	}
}