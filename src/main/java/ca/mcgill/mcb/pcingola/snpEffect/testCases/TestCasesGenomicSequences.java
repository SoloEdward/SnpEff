package ca.mcgill.mcb.pcingola.snpEffect.testCases;

import junit.framework.Assert;
import junit.framework.TestCase;
import ca.mcgill.mcb.pcingola.binseq.GenomicSequences;
import ca.mcgill.mcb.pcingola.interval.Exon;
import ca.mcgill.mcb.pcingola.interval.Gene;
import ca.mcgill.mcb.pcingola.interval.Transcript;
import ca.mcgill.mcb.pcingola.snpEffect.commandLine.SnpEffCmdEff;
import ca.mcgill.mcb.pcingola.util.Gpr;

/**
 *
 * Test case for genomic sequences
 */
public class TestCasesGenomicSequences extends TestCase {

	boolean debug = false;
	boolean verbose = false || debug;

	public TestCasesGenomicSequences() {
		super();
	}

	/**
	 * Check that we can recover sequences from all exons using GneomicSequence class
	 */
	public void test_01() {
		Gpr.debug("Test");
		String genome = "zzz";

		// Create SnpEff
		String args[] = { genome };
		SnpEffCmdEff snpeff = new SnpEffCmdEff();
		snpeff.parseArgs(args);
		snpeff.setDebug(debug);
		snpeff.setVerbose(verbose);
		snpeff.setSupressOutput(!verbose);

		// Load genome
		snpeff.load();

		GenomicSequences genomicSequences = snpeff.getConfig().getGenome().getGenomicSequences();
		for (Gene g : snpeff.getConfig().getGenome().getGenes()) {
			for (Transcript tr : g) {
				for (Exon ex : tr) {

					String seq = genomicSequences.getSequence(ex);
					if (verbose) System.out.println(g.getGeneName() + "\t" + tr.getId() + "\t" + ex.getId() + "\n\t" + ex.getSequence() + "\n\t" + seq);

					// Sanity checks
					Assert.assertNotNull(seq == null);
					Assert.assertEquals(seq, ex.getSequence());
				}
			}
		}

	}

}
