package ca.mcgill.mcb.pcingola.snpEffect.testCases.integration;

import java.util.List;

import org.junit.Test;

import ca.mcgill.mcb.pcingola.snpEffect.commandLine.SnpEff;
import ca.mcgill.mcb.pcingola.snpEffect.commandLine.SnpEffCmdEff;
import ca.mcgill.mcb.pcingola.util.Gpr;
import ca.mcgill.mcb.pcingola.vcf.VcfEffect;
import ca.mcgill.mcb.pcingola.vcf.VcfEntry;

/**
 * Test random SNP changes
 *
 * @author pcingola
 */
public class TestCasesInsVep {

	public static boolean debug = false;
	public static boolean verbose = false || debug;

	/**
	 * Compare with results from ENSEMBL's VEP on transcript ENST00000268124
	 */
	public void compareVep(String genome, String vcf, String trId) {
		String args[] = { "-classic", genome, vcf };

		SnpEff cmd = new SnpEff(args);
		SnpEffCmdEff cmdEff = (SnpEffCmdEff) cmd.snpEffCmd();
		cmdEff.setSupressOutput(!verbose);

		List<VcfEntry> vcfEnties = cmdEff.run(true);
		for (VcfEntry ve : vcfEnties) {

			StringBuilder msg = new StringBuilder();

			// Check effects
			boolean ok = false;
			for (VcfEffect veff : ve.parseEffects()) {
				// Find transcript
				if (veff.getTranscriptId() != null && veff.getTranscriptId().equals(trId)) {
					// Check that reported effect is the same
					String vep = ve.getInfo("EFF_V");
					String eff = veff.getEffectType().toString();

					if (vep.equals(eff)) ok = true;
					else {
						if (vep.equals("CODON_INSERTION") && eff.equals("CODON_CHANGE_PLUS_CODON_INSERTION")) ok = true; // OK. I consider these the same
						else if (vep.equals("STOP_GAINED,CODON_INSERTION") && eff.equals("STOP_GAINED")) ok = true; // OK. I consider these the same
						else if (eff.equals("SPLICE_SITE_REGION")) ok = true; // OK. I'm not checking these
						else {
							String line = "\n" + ve + "\n\tSnpEff:" + veff + "\n\tVEP   :" + ve.getInfo("EFF_V") + "\t" + ve.getInfo("AA") + "\t" + ve.getInfo("CODON") + "\n";
							msg.append(line);
						}
					}
				}
			}

			if (!ok) throw new RuntimeException(msg.toString());
		}
	}

	@Test
	public void test_03_InsVep() {
		Gpr.debug("Test");
		compareVep("testENST00000268124", "tests/testENST00000268124_ins_vep.vcf", "ENST00000268124");
	}

	@Test
	public void test_04_InsVep() {
		Gpr.debug("Test");
		compareVep("testHg3770Chr22", "tests/testENST00000445220_ins_vep.vcf", "ENST00000445220");
	}

}
