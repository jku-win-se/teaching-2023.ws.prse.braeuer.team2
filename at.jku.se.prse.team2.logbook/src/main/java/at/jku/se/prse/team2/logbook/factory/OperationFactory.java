package at.jku.se.prse.team2.logbook.factory;

import at.jku.se.prse.team2.logbook.operators.NullOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import at.jku.se.prse.team2.logbook.SimpleCalculator.CalcAction;
import at.jku.se.prse.team2.logbook.operators.AddOperation;

/**
 * Factory class for {@link ICalculationOperation}.
 * 
 * @author Michael Vierhauser
 */
public class OperationFactory {

	private static final Logger LOGGER = LogManager.getLogger(OperationFactory.class);

	/**
	 * 
	 * @param
	 * @return Returns the respective implementation for the requested operation.
	 */

	public static ICalculationOperation getOperation(CalcAction action) {

		switch (action) {
		case POW:
			return new NullOperation();
		case ADD:
			return new AddOperation();
		case DIV:
			return new NullOperation();
		case MULT:
			return new NullOperation();
		case SUB:
			return new NullOperation();

		default:
			LOGGER.error("Sorry this operation is not yet implemented!");
			return new NullOperation();
		}

	}

}