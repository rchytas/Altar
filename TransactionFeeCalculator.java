import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TransactionFeeCalculator {

	static public void main(String[] argv) {
		double percentFee100K = 1.9;
		double percentFee10K = 2.2;
		double percentFee3K = 2.5;
		double percentFeeBase = 2.9;

		try {
			double TransactionAmount = 0;
			double NumberOfTransactions = 0;
			double TransactionFee = 0;

			// Obtain Transaction Amount
			System.out.print("Please enter monthly transaction amount: ");
			BufferedReader br = new BufferedReader(new InputStreamReader(
					System.in));
			TransactionAmount = Double.parseDouble(br.readLine());

			// Obtain total number of transactions
			System.out.print("Please enter monthly number of transactions: ");
			NumberOfTransactions = Double.parseDouble(br.readLine());

			// Calculate percent fee
			if (TransactionAmount > 0) {
				if (TransactionAmount > 100000.00) {
					TransactionFee = TransactionAmount
							* (percentFee100K / 100.0d);
				} else if (TransactionAmount > 10000.00) {
					TransactionFee = TransactionAmount
							* (percentFee10K / 100.0d);
				} else if (TransactionAmount > 3000.00) {
					TransactionFee = TransactionAmount
							* (percentFee3K / 100.0d);
				} else {
					TransactionFee = TransactionAmount
							* (percentFeeBase / 100.0d);
				}
			}

			// Add $0.30 per transaction to percent transaction fee
			if (NumberOfTransactions > 0)
				TransactionFee = TransactionFee + 0.30 * NumberOfTransactions;

			// Provide total transaction fee
			System.out
					.print("Transaciton Fee calculated is: " + TransactionFee);

		} catch (IOException ioe) {
			System.out.println("IO error reading transaction amount");
			System.exit(1);
		} catch (Exception exp) {
			exp.printStackTrace();
		}
	}
}
