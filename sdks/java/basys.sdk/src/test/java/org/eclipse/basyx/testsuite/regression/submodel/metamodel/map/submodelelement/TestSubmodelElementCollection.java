package org.eclipse.basyx.testsuite.regression.submodel.metamodel.map.submodelelement;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.basyx.submodel.metamodel.api.identifier.IdentifierType;
import org.eclipse.basyx.submodel.metamodel.api.reference.IReference;
import org.eclipse.basyx.submodel.metamodel.api.reference.enums.KeyElements;
import org.eclipse.basyx.submodel.metamodel.api.reference.enums.KeyType;
import org.eclipse.basyx.submodel.metamodel.api.submodelelement.ISubmodelElement;
import org.eclipse.basyx.submodel.metamodel.api.submodelelement.dataelement.IDataElement;
import org.eclipse.basyx.submodel.metamodel.api.submodelelement.operation.IOperation;
import org.eclipse.basyx.submodel.metamodel.map.identifier.Identifier;
import org.eclipse.basyx.submodel.metamodel.map.qualifier.LangStrings;
import org.eclipse.basyx.submodel.metamodel.map.qualifier.Referable;
import org.eclipse.basyx.submodel.metamodel.map.qualifier.qualifiable.Formula;
import org.eclipse.basyx.submodel.metamodel.map.qualifier.qualifiable.Qualifiable;
import org.eclipse.basyx.submodel.metamodel.map.reference.Key;
import org.eclipse.basyx.submodel.metamodel.map.reference.Reference;
import org.eclipse.basyx.submodel.metamodel.map.submodelelement.SubmodelElementCollection;
import org.eclipse.basyx.submodel.metamodel.map.submodelelement.dataelement.property.Property;
import org.eclipse.basyx.submodel.metamodel.map.submodelelement.operation.Operation;
import org.eclipse.basyx.submodel.metamodel.map.submodelelement.operation.OperationVariable;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests constructor and getter of {@link SubmodelElementCollection} for their
 * correctness
 * 
 * @author haque
 *
 */
public class TestSubmodelElementCollection {
	private static final Reference REFERENCE = new Reference(new Identifier(IdentifierType.CUSTOM, "testValue"), KeyElements.ACCESSPERMISSIONRULE, false);
	private static final String OPERATION_ID = "testOpID";
	private static final String PROPERTY_ID = "testPropID";

	private Collection<ISubmodelElement> elements1;
	private Collection<ISubmodelElement> elements2;
	private SubmodelElementCollection elementCollection;
	
	@Before
	public void buildSubmodelElementCollection() {
		elements1 = new ArrayList<>();
		elements1.add(getProperty());
		elements1.add(getOperation());

		elements2 = new ArrayList<ISubmodelElement>();
		elements2.add(new Property("testId1"));
		elements2.add(new Property("testId2"));
		elementCollection = new SubmodelElementCollection(elements2, false, false);
	} 

	@Test
	public void testConstructor() {
		SubmodelElementCollection elementCollection = new SubmodelElementCollection(elements1, true, true);
		assertTrue(elementCollection.isAllowDuplicates());
		assertTrue(elementCollection.isOrdered());
		assertEquals(elements1, elementCollection.getValue());
	} 
	
	@Test
	public void testAddValue() {
		ISubmodelElement element = new Property("testIdNew");
		elementCollection.addElement(element);
		elements2.add(element);
		assertEquals(elements2, elementCollection.getValue());
	} 
	
	@Test
	public void testSetDataSpecificationReferences() {
		Collection<IReference> refs = Collections.singleton(REFERENCE);
		elementCollection.setDataSpecificationReferences(refs);
		assertEquals(refs, elementCollection.getDataSpecificationReferences());
	} 
	
	@Test
	public void testSetValue() {
		Collection<ISubmodelElement> elements = new ArrayList<ISubmodelElement>();
		elements.add(new Property("testId1"));
		elementCollection.setValue(elements);
		assertEquals(elements, elementCollection.getValue());
	} 
	
	@Test
	public void testSetOrdered() {
		elementCollection.setOrdered(false);
		assertTrue(!elementCollection.isOrdered());
	} 
	
	@Test
	public void testSetAllowDuplicates() {
		elementCollection.setAllowDuplicates(false);
		assertTrue(!elementCollection.isAllowDuplicates());
	} 
	
	@Test
	public void testSetElements() {
		String idShort = "testIdShort";
		Key key = new Key(KeyElements.BLOB, true, "TestValue", IdentifierType.IRI);
		Reference reference = new Reference(key);
		Formula formula = new Formula(Collections.singleton(reference));
		Qualifiable qualifiable = new Qualifiable(formula);
		ISubmodelElement element = new Property("testId1", new Referable(idShort, "Category", new LangStrings("DE", "Test")), REFERENCE, qualifiable);
		Map<String, ISubmodelElement> elementsMap = new HashMap<String, ISubmodelElement>();
		elementsMap.put(idShort, element);
		elementCollection.setElements(elementsMap);
		assertEquals(elementsMap, elementCollection.getSubmodelElements());
	} 


	@Test
	public void testConstructor1() {
		SubmodelElementCollection collection = new SubmodelElementCollection(elements1, false, false);

		Map<String, IDataElement> dataElements = new HashMap<String, IDataElement>();
		Map<String, IOperation> operations = new HashMap<String, IOperation>();
		Map<String, ISubmodelElement> submodels = new HashMap<String, ISubmodelElement>();
		dataElements.put(PROPERTY_ID, getProperty());
		operations.put(OPERATION_ID, getOperation());
		submodels.putAll(operations);
		submodels.putAll(dataElements);
		assertEquals(dataElements, collection.getProperties());
		assertEquals(operations, collection.getOperations());
		assertEquals(submodels, collection.getSubmodelElements());
	}

	@Test
	public void testAddSubModelElement() {
		SubmodelElementCollection collection = new SubmodelElementCollection(elements1, false, false);
		Property property = new Property("testValue");
		String newIdShort = "newIdShort";
		property.put(Referable.IDSHORT, newIdShort);
		collection.addElement(property);
		assertEquals(new Reference(new Key(KeyElements.SUBMODELELEMENTCOLLECTION, true, "", KeyType.IDSHORT)), property.getParent());
		Map<String, ISubmodelElement> submodelElements = new HashMap<String, ISubmodelElement>();
		submodelElements.put(PROPERTY_ID, getProperty());
		submodelElements.put(OPERATION_ID, getOperation());
		submodelElements.put(newIdShort, property);
		assertEquals(submodelElements, collection.getSubmodelElements());
	}

	/**
	 * Get a dummy property
	 * 
	 * @return property
	 */
	private Property getProperty() {
		Referable referable = new Referable(PROPERTY_ID, "testCategory", new LangStrings("DE", "test"));
		Reference semanticId = new Reference(new Key(KeyElements.ASSET, true, "testValue", IdentifierType.IRI));
		Qualifiable qualifiable = new Qualifiable(new Formula(Collections
				.singleton(new Reference(new Key(KeyElements.BLOB, true, "TestValue", IdentifierType.IRI)))));
		Property property = new Property("testValue", referable, semanticId, qualifiable);
		return property;
	}

	/**
	 * Get a dummy operation
	 * 
	 * @return operation
	 */
	private Operation getOperation() {
		List<OperationVariable> variable = Collections
				.singletonList(new OperationVariable(new Property("testOpVariableId")));
		Operation operation = new Operation(variable, variable, variable, null);
		operation.put(Referable.IDSHORT, OPERATION_ID);
		return operation;
	}
}
