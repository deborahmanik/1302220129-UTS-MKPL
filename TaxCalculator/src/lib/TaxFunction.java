package lib;

public class TaxFunction {

	
	/**
	 * Fungsi untuk menghitung jumlah pajak penghasilan pegawai yang harus dibayarkan setahun.
	 * 
	 * Pajak dihitung sebagai 5% dari penghasilan bersih tahunan (gaji dan pemasukan bulanan lainnya dikalikan jumlah bulan bekerja dikurangi pemotongan) dikurangi penghasilan tidak kena pajak.
	 * 
	 * Jika pegawai belum menikah dan belum punya anak maka penghasilan tidak kena pajaknya adalah Rp 54.000.000.
	 * Jika pegawai sudah menikah maka penghasilan tidak kena pajaknya ditambah sebesar Rp 4.500.000.
	 * Jika pegawai sudah memiliki anak maka penghasilan tidak kena pajaknya ditambah sebesar Rp 4.500.000 per anak sampai anak ketiga.
	 * 
	 */
	private static final int BASIC_NON_TAXABLE = 54000000;
    private static final int MARRIED_ADDITION = 4500000;
    private static final int CHILD_ADDITION = 1500000;
    private static final double TAX_RATE = 0.05;
    private static final int MAX_CHILDREN = 3;
	
	public static int calculateTax(int monthlySalary, int otherMonthlyIncome, int numberOfMonthWorking, int deductible, boolean isMarried, int numberOfChildren) {
		
		if (numberOfMonthWorking > 12 || numberOfMonthWorking < 0) {
            throw new IllegalArgumentException("Invalid working months: must be between 0 and 12.");
        }

        if (monthlySalary < 0 || otherMonthlyIncome < 0 || deductible < 0) {
            throw new IllegalArgumentException("Income or deductible cannot be negative.");
        }

        if (numberOfChildren < 0) {
            throw new IllegalArgumentException("Number of children cannot be negative.");
        }
		
		numberOfChildren = Math.min(numberOfChildren, MAX_CHILDREN);
		
		int grossIncome = (monthlySalary + otherMonthlyIncome) * numberOfMonthWorking;

		int nonTaxableIncome = BASIC_NON_TAXABLE;
        if (isMarried) {
            nonTaxableIncome += MARRIED_ADDITION;
        }
        nonTaxableIncome += numberOfChildren * CHILD_ADDITION;
		
		int taxableIncome = grossIncome - deductible - nonTaxableIncome;

		int tax = (int) Math.round(TAX_RATE * taxableIncome);

        return Math.max(tax, 0);
			 
	}
	
}
