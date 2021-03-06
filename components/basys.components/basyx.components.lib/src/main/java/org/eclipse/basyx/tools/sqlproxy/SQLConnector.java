package org.eclipse.basyx.tools.sqlproxy;

import org.eclipse.basyx.components.sqlprovider.driver.ISQLDriver;
import org.eclipse.basyx.components.sqlprovider.driver.SQLDriver;

/**
 * Base class for classes that connect to SQL databases
 * 
 * @author kuhn
 *
 */
public abstract class SQLConnector {
	
	/**
	 * ID of table for this element object
	 */
	private String sqlTableID = null;
	
	/**
	 * SQL Driver for the connector
	 */
	private ISQLDriver driver;
	
	
	/**
	 * Constructor
	 * 
	 * @param user        SQL user name
	 * @param pass        SQL password
	 * @param url         SQL server URL
	 * @param driver      SQL driver
	 * @param prefix      JDBC SQL driver prefix
	 * @param tableID     ID of table for this element in database
	 */
	public SQLConnector(String user, String pass, String url, String driver, String prefix, String tableID) {
		// ID of table hat contains elements of this element
		sqlTableID = tableID;
		
		// Instantiate a driver for the SQL Connector
		this.driver = new SQLDriver(url, user, pass, prefix, driver);
	}
	
	/**
	 * Constructor
	 *
	 * @param driver      SQL Driver to connect with the database
	 * @param tableID     ID of table for this element in database
	 */
	public SQLConnector(ISQLDriver driver, String tableID) {
		// Store variables
		this.driver = driver;
		
		// ID of table hat contains elements of this element
		sqlTableID = tableID;
	}

	public String getSqlTableID() {
		return sqlTableID;
	}

	public ISQLDriver getDriver() {
		return driver;
	}
	
}
