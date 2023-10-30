package at.jku.se.prse.team2.logbook.factory;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

import at.jku.se.prse.team2.logbook.operators.AddOperation;
import at.jku.se.prse.team2.logbook.SimpleCalculator;

/**
 * This test class performs tests for the {@link OperationFactory} class.
 * 
 * @author Michael Vierhauser
 */
public class TestOperationFactory {

	/**
	 * This test case tests the getOperation Method of the OperationFactory. A
	 * method is requested for the add operation and the respective implementation
	 * should be returned.
	 * 
	 */
	@org.junit.Test
	public void testAdditionOperation() {
		ICalculationOperation operation = OperationFactory.getOperation(SimpleCalculator.CalcAction.ADD);
		assertThat(operation, instanceOf(AddOperation.class));

	}

}