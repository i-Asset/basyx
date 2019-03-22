package org.eclipse.basyx.aas.metamodel.facades;

import java.util.Map;

import org.eclipse.basyx.aas.api.metamodel.aas.dataspecification.IDataSpecification;
import org.eclipse.basyx.aas.metamodel.hashmap.aas.dataspecification.DataSpecification;


/**
 * Facade providing access to a map containing the DataSpecification structure
 * 
 * @author rajashek
 *
 */
public class DataSpecificationFacade implements IDataSpecification {
	
	private Map<String, Object> map;

	public DataSpecificationFacade(Map<String, Object> map) {
		super();
		this.map = map;
	}

	@Override
	public String getPreferredName() {
		if(map.get(DataSpecification.PREFERREDNAME)==null)
			return null;
		
		return (String)map.get(DataSpecification.PREFERREDNAME);
	}

	@Override
	public String getShortName() {
		if(map.get(DataSpecification.SHORTNAME)==null)
			return null;
		return (String)map.get(DataSpecification.SHORTNAME);
	}

	@Override
	public String getUnit() {
		if(map.get(DataSpecification.UNIT)==null)
			return null;
		return (String)map.get(DataSpecification.UNIT);
	}

	@Override
	public Object getUnitId() {
		if(map.get(DataSpecification.UNITID)==null)
			return null;
		return map.get(DataSpecification.UNITID);
	}

	@Override
	public String getSourceOfDefinition() {
		if(map.get(DataSpecification.SOURCEOFDEFINITION)==null)
			return null;
		return (String)map.get(DataSpecification.SOURCEOFDEFINITION);
	}

	@Override
	public String getSymbol() {
		if(map.get(DataSpecification.SYMBOL)==null)
			return null;
		return (String)map.get(DataSpecification.SYMBOL);
	}

	@Override
	public String getDataType() {
		if(map.get(DataSpecification.DATATYPE)==null)
			return null;
		return (String)map.get(DataSpecification.DATATYPE);
	}

	@Override
	public String getDefinition() {
		if(map.get(DataSpecification.DEFINITION)==null)
			return null;
		return (String)map.get(DataSpecification.DEFINITION);
	}

	@Override
	public String getValueFormat() {
		if(map.get(DataSpecification.VALUEFORMAT)==null)
			return null;
		return (String)map.get(DataSpecification.VALUEFORMAT);
	}

	@Override
	public String getValueList() {
		if(map.get(DataSpecification.VALUELIST)==null)
			return null;
		return (String)map.get(DataSpecification.VALUELIST);
	}

	@Override
	public String getCode() {
		if(map.get(DataSpecification.CODE)==null)
			return null;
		return (String)map.get(DataSpecification.CODE);
	}

	@Override
	public void setPreferredName(String preferredName) {
		map.put(DataSpecification.PREFERREDNAME, preferredName);
		
	}

	@Override
	public void setShortName(String shortName) {
		map.put(DataSpecification.SHORTNAME, shortName);
	}

	@Override
	public void setUnit(String uni) {
		map.put(DataSpecification.UNIT, uni);
		
	}

	@Override
	public void setUnitId(Object unitId) {
		map.put(DataSpecification.UNITID, unitId);
		
	}

	@Override
	public void setSourceOfDefinition(String sourceOfDefinition) {
		map.put(DataSpecification.SOURCEOFDEFINITION, sourceOfDefinition);
		
	}

	@Override
	public void setSymbol(String symbol) {
		map.put(DataSpecification.SYMBOL, symbol);
		
	}

	@Override
	public void setDataType(String dataType) {
		map.put(DataSpecification.DATATYPE, dataType);
		
	}

	@Override
	public void setDefinition(String definition) {
		map.put(DataSpecification.DEFINITION, definition);
		
	}

	@Override
	public void setValueFormat(String valueFormat) {
		map.put(DataSpecification.VALUEFORMAT, valueFormat);
		
	}

	@Override
	public void setValueList(Object obj) {
		map.put(DataSpecification.VALUELIST, obj);
		
	}

	@Override
	public void setCode(Object obj) {
		map.put(DataSpecification.CODE, obj);
		
	}


}
