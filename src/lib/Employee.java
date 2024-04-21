package lib;

import java.time.LocalDate;
import java.time.Month;
import java.util.LinkedList;
import java.util.List;

public class Employee {

	private String employeeId;
	private String firstName;
	private String lastName;
	private String idNumber;
	private String address;
	
	private int joinYear;
	private int joinMonth;
	private int joinDay;
	private int monthWorkingInYear;
	
	private boolean isForeignEmployee;
	private Gender employeeGender;
	
	private int monthlySalary;
	private int otherMonthlyIncome;
	private int annualDeductible;
	
	private String spouseName;
	private String spouseIdNumber;

	private List<String> childNames;
	private List<String> childIdNumbers;

	private static final int GRADE_1 = 1;
	private static final int GRADE_2 = 2;
	private static final int GRADE_3 = 3;
	private static final int GRADE_SALARY_INCREASE_PERCENTAGE = 50;
	
	public Employee(String employeeId, String firstName, String lastName, String idNumber, String address, int joinYear, int joinMonth, int joinDay, boolean isForeignEmployee, Gender employeeGender) {
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.idNumber = idNumber;
		this.address = address;
		this.joinYear = joinYear;
		this.joinMonth = joinMonth;
		this.joinDay = joinDay;
		this.isForeignEmployee = isForeignEmployee;
		this.employeeGender = employeeGender;
		
		childNames = new LinkedList<String>();
		childIdNumbers = new LinkedList<String>();
	}
	
	public void setMonthlySalary(int grade) {	
		int baseSalary = 0;
		if (grade == GRADE_1){
			baseSalary = 3000000;
		} else if (grade == GRADE_2){
			baseSalary = 500000;
		} else if (grade == GRADE_3){
			baseSalary =700000;
		}

		monthlySalary = isForeignEmployee ? (int) (baseSalary * (1 + GRADE_SALARY_INCREASE_PERCENTAGE / 100.0)) : baseSalary;
	}
	
	public void setAnnualDeductible(int deductible) {	
		this.annualDeductible = deductible;
	}
	
	public void setAdditionalIncome(int income) {	
		this.otherMonthlyIncome = income;
	}
	
	public void setSpouse(String spouseName, String spouseIdNumber) {
		this.spouseName = spouseName;
		this.spouseIdNumber = idNumber;
	}
	
	public void addChild(String childName, String childIdNumber) {
		childNames.add(childName);
		childIdNumbers.add(childIdNumber);
	}
	
	public int getAnnualIncomeTax() {
		
		//Menghitung berapa lama pegawai bekerja dalam setahun ini, jika pegawai sudah bekerja dari tahun sebelumnya maka otomatis dianggap 12 bulan.
		LocalDate today = LocalDate.now();
        LocalDate joinDate = LocalDate.of(joinYear, joinMonth, joinDay);
        monthWorkingInYear = (int) Math.min(12, joinDate.until(today).toTotalMonths());

        return TaxFunction.calculateTax(monthlySalary, otherMonthlyIncome, monthWorkingInYear, annualDeductible, spouseIdNumber.equals(""), childIdNumbers.size());
	}

	public enum Gender {
		MALE,
		FEMALE
	}
}
