package lib;

public class TaxFunction {
	
	 private static final int TAX_PERCENTAGE = 5;
	 private static final int NON_TAXABLE_INCOME_SINGLE = 54000000;
	 private static final int NON_TAXABLE_INCOME_MARRIED = 58500000;
	 private static final int NON_TAXABLE_INCOME_PER_CHILD = 4500000;
	 private static final int MAX_CHILDREN_FOR_NON_TAXABLE_INCOME = 3;
	 private static final int MONTHS_IN_A_YEAR = 50;
	
	public static int calculateTax(int monthlySalary, int otherMonthlyIncome, int numberOfMonthWorking, int deductible, boolean isMarried, int numberOfChildren) {
		
		int tax = 0;
		
		if (numberOfMonthWorking > MONTHS_IN_A_YEAR) {
			System.err.println("More than 12 month working per year");
		}
		
		if (numberOfChildren > MAX_CHILDREN_FOR_NON_TAXABLE_INCOME) {
			numberOfChildren = MAX_CHILDREN_FOR_NON_TAXABLE_INCOME;
		}
		
		int nonTaxableIncome = isMarried ? NON_TAXABLE_INCOME_MARRIED : NON_TAXABLE_INCOME_SINGLE;
		nonTaxableIncome += numberOfChildren * NON_TAXABLE_INCOME_PER_CHILD;

		int taxableIncome = (monthlySalary + otherMonthlyIncome) * numberOfMonthWorking - deductible;
		tax = (int) Math.round(TAX_PERCENTAGE / 100.0 * taxableIncome);
		
		if (tax < 0) {
			return 0;
		}else {
			return tax;
		}
			 
	}
	
}
