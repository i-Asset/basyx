package org.eclipse.basyx.testsuite.regression.aas.registration;

import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

/**
 * The main class that executes the concrete test suite of the registry provider
 * 
 * @author zhangzai
 *
 */
public class RegistryProviderTestApplication {


	/**
	 * Run the test suite of the registry provider with inserted url
	 * 
	 * @param args - URL inserted by the user
	 */
	public static void main(String[] args) {
		// First argument is the inserted url
		String url = args[0];

		// Run the junit tests
		JUnitCore junit = new JUnitCore();
		junit.addListener(new TextListener(System.out));

		RegistryProviderSuiteWithDefinedURL.url = url;
		Result result = junit.run(RegistryProviderSuiteWithDefinedURL.class);

		System.out.println("Finished. Result: Failures: " +
				result.getFailureCount() + ". Ignored: " +
				result.getIgnoreCount() + ". Tests run: " +
				result.getRunCount() + ". Time: " +
				result.getRunTime() + "ms.");
	}
}
