package org.snpeff.snpEffect.testCases.unity;

import org.junit.Test;
import org.snpeff.interval.Variant;
import org.snpeff.snpEffect.HgvsDna;
import org.snpeff.snpEffect.VariantEffect;
import org.snpeff.snpEffect.VariantEffects;
import org.snpeff.util.Gpr;

import junit.framework.Assert;

/**
 *
 * Test case
 */
public class TestCasesHgvsDnaDup extends TestCasesBase {

	public TestCasesHgvsDnaDup() {
		super();
	}

	@Override
	protected void init() {
		super.init();
		shiftHgvs = true;
	}

	@Test
	public void test_01() {
		Gpr.debug("Test");

		if (verbose) Gpr.debug(transcript);

		// Create variant
		Variant variant = new Variant(chromosome, 881, "", "T", "");
		if (verbose) Gpr.debug("Variant: " + variant);

		// Analyze variant
		VariantEffects effs = snpEffectPredictor.variantEffect(variant);

		// Calculate HGVS
		VariantEffect eff = effs.get();
		HgvsDna hgvsc = new HgvsDna(eff);
		String hgvsDna = hgvsc.toString();

		// Check result
		if (verbose) Gpr.debug("HGVS (DNA): '" + hgvsDna + "'");
		Assert.assertEquals("c.1dupT", hgvsDna);
	}

	@Test
	public void test_02() {
		Gpr.debug("Test");
		if (verbose) Gpr.debug(transcript);

		// Create variant
		Variant variant = new Variant(chromosome, 883, "", "A", "");
		if (verbose) Gpr.debug("Variant: " + variant);

		// Analyze variant
		VariantEffects effs = snpEffectPredictor.variantEffect(variant);

		// Calculate HGVS
		VariantEffect eff = effs.get();
		HgvsDna hgvsc = new HgvsDna(eff);
		String hgvsDna = hgvsc.toString();

		// Check result
		if (verbose) Gpr.debug("HGVS (DNA): '" + hgvsDna + "'");
		Assert.assertEquals("c.3dupA", hgvsDna);
	}

	/**
	 * Test case from http://www.hgvs.org/mutnomen/recs-DNA.html
	 * 		g.5dupT (or g.5dup, not g.5_6insT) denotes a duplication ("insertion") of the T nucleotide
	 *      at position 5 in the genomic reference sequence changing ACTCTGTGCC to ACTCTTGTGCC
	 */
	@Test
	public void test_03() {
		Gpr.debug("Test");
		String prepend = "ACTCTGTGCC";

		prependSequenceToFirstExon(prepend);
		if (verbose) Gpr.debug(transcript);

		// Create variant
		Variant variant = new Variant(chromosome, 885, "", "T", "");
		if (verbose) Gpr.debug("Variant: " + variant);

		// Analyze variant
		VariantEffects effs = snpEffectPredictor.variantEffect(variant);

		// Calculate HGVS
		VariantEffect eff = effs.get();
		HgvsDna hgvsc = new HgvsDna(eff);
		String hgvsDna = hgvsc.toString();

		// Check result
		if (verbose) Gpr.debug("HGVS (DNA): '" + hgvsDna + "'");
		Assert.assertEquals("c.5dupT", hgvsDna);
	}

	/**
	 * Test case from http://www.hgvs.org/mutnomen/recs-DNA.html
	 * 		g.7dupT (or g.7dup, not g.5dupT, not g.7_8insT) denotes a duplication ("insertion") of
	 * 		the T nucleotide at position 7 in the genomic reference sequence changing AGACTTTGTGCC to AGACTTTTGTGCC
	 */
	@Test
	public void test_04() {
		Gpr.debug("Test");
		String prepend = "AGACTTTGTGCC";

		prependSequenceToFirstExon(prepend);
		if (verbose) Gpr.debug(transcript);

		// Create variant
		Variant variant = new Variant(chromosome, 887, "", "T", "");
		if (verbose) Gpr.debug("Variant: " + variant);

		// Analyze variant
		VariantEffects effs = snpEffectPredictor.variantEffect(variant);

		// Calculate HGVS
		VariantEffect eff = effs.get();
		HgvsDna hgvsc = new HgvsDna(eff);
		String hgvsDna = hgvsc.toString();

		// Check result
		if (verbose) Gpr.debug("HGVS (DNA): '" + hgvsDna + "'");
		Assert.assertEquals("c.7dupT", hgvsDna);
	}

	/**
	 * Test case from http://www.hgvs.org/mutnomen/recs-DNA.html
	 * 		g.7_8dup (or g.7_8dupTG, not g.5_6dup, not g.8_9insTG) denotes a TG duplication
	 * 		in the TG-tandem repeat sequence changing ACTTTGTGCC to ACTTTGTGTGCC
	 */
	@Test
	public void test_05() {
		Gpr.debug("Test");

		String prepend = "ACTTTGTGCC";

		prependSequenceToFirstExon(prepend);
		if (verbose) Gpr.debug(transcript);

		// Create variant
		Variant variant = new Variant(chromosome, 888, "", "TG", "");
		if (verbose) Gpr.debug("Variant: " + variant);

		// Analyze variant
		VariantEffects effs = snpEffectPredictor.variantEffect(variant);

		// Calculate HGVS
		VariantEffect eff = effs.get();
		HgvsDna hgvsc = new HgvsDna(eff);
		String hgvsDna = hgvsc.toString();

		// Check result
		if (verbose) Gpr.debug("HGVS (DNA): '" + hgvsDna + "'");
		Assert.assertEquals("c.7_8dupTG", hgvsDna);
	}

	@Test
	public void test_06() {
		Gpr.debug("Test");
		String prepend = "ACTTTGTGCC";

		prependSequenceToFirstExon(prepend);
		if (verbose) Gpr.debug(transcript);

		// Create variant
		Variant variant = new Variant(chromosome, 888, "", "GTG", "");
		if (verbose) Gpr.debug("Variant: " + variant);

		// Analyze variant
		VariantEffects effs = snpEffectPredictor.variantEffect(variant);

		// Calculate HGVS
		VariantEffect eff = effs.get();
		HgvsDna hgvsc = new HgvsDna(eff);
		String hgvsDna = hgvsc.toString();

		// Check result
		if (verbose) Gpr.debug("HGVS (DNA): '" + hgvsDna + "'");
		Assert.assertEquals("c.6_8dupGTG", hgvsDna);
	}

	/**
	 * Duplication with variant shifted towards 3-prime
	 */
	@Test
	public void test_07() {
		Gpr.debug("Test");

		if (verbose) Gpr.debug(transcript);

		// Create variant
		Variant variant = new Variant(chromosome, 882, "", "A", "");
		if (verbose) Gpr.debug("Variant: " + variant);

		// Analyze variant
		VariantEffects effs = snpEffectPredictor.variantEffect(variant);

		// Calculate HGVS
		VariantEffect eff = effs.get();
		HgvsDna hgvsc = new HgvsDna(eff);
		String hgvsDna = hgvsc.toString();

		// Check result
		if (verbose) Gpr.debug("HGVS (DNA): '" + hgvsDna + "'");
		Assert.assertEquals("c.3dupA", hgvsDna);
	}

	/**
	 * Duplication with variant shifted towards 3-prime
	 */
	@Test
	public void test_08() {
		Gpr.debug("Test");

		if (verbose) Gpr.debug(transcript);

		// Create variant
		Variant variant = new Variant(chromosome, 884, "", "C", "");
		if (verbose) Gpr.debug("Variant: " + variant);

		// Analyze variant
		VariantEffects effs = snpEffectPredictor.variantEffect(variant);

		// Calculate HGVS
		VariantEffect eff = effs.get();
		HgvsDna hgvsc = new HgvsDna(eff);
		String hgvsDna = hgvsc.toString();

		// Check result
		if (verbose) Gpr.debug("HGVS (DNA): '" + hgvsDna + "'");
		Assert.assertEquals("c.7dupC", hgvsDna);
	}

	/**
	 * Dup in intron
	 */
	@Test
	public void test_09() {
		Gpr.debug("Test");

		// We need at least 2 exons for this test case
		minExons = 2;
		initSnpEffPredictor();

		if (verbose) Gpr.debug(transcript + "\nChromosome: " + chromoSequence);

		// Create variant
		Variant variant = new Variant(chromosome, 485, "", "TA", "");
		if (verbose) Gpr.debug("Variant: " + variant);

		// Analyze variant
		VariantEffects effs = snpEffectPredictor.variantEffect(variant);

		// Calculate HGVS
		VariantEffect eff = effs.get();
		HgvsDna hgvsc = new HgvsDna(eff);
		String hgvsDna = hgvsc.toString();

		// Check result
		if (verbose) Gpr.debug("HGVS (DNA): '" + hgvsDna + "'");
		Assert.assertEquals("c.49+3_49+4dupTA", hgvsDna);
	}

	/**
	 * Dup in exon-intron boundary
	 */
	@Test
	public void test_10() {
		Gpr.debug("Test");

		// We need at least 2 exons for this test case
		minExons = 2;
		shiftHgvs = false;
		initSnpEffPredictor();

		if (verbose) Gpr.debug(transcript + "\nChromosome: " + chromoSequence);

		// Create variant
		Variant variant = new Variant(chromosome, 483, "", "TATA", "");
		if (verbose) Gpr.debug("Variant: " + variant);

		// Analyze variant
		VariantEffects effs = snpEffectPredictor.variantEffect(variant);

		// Calculate HGVS
		VariantEffect eff = effs.get();
		HgvsDna hgvsc = new HgvsDna(eff);
		String hgvsDna = hgvsc.toString();

		// Check result
		if (verbose) Gpr.debug("HGVS (DNA): '" + hgvsDna + "'");
		Assert.assertEquals("c.48_49+2dupTATA", hgvsDna);
	}

	/**
	 * Dup in intron-exon boundary
	 */
	@Test
	public void test_11() {
		Gpr.debug("Test");

		// We need at least 2 exons for this test case
		minExons = 2;

		initSnpEffPredictor();

		if (verbose) Gpr.debug(transcript + "\nChromosome: " + chromoSequence);

		// Create variant
		Variant variant = new Variant(chromosome, 739, "", "AAAG", "");
		if (verbose) Gpr.debug("Variant: " + variant);

		// Analyze variant
		VariantEffects effs = snpEffectPredictor.variantEffect(variant);

		// Calculate HGVS
		VariantEffect eff = effs.get();
		HgvsDna hgvsc = new HgvsDna(eff);
		String hgvsDna = hgvsc.toString();

		// Check result
		if (verbose) Gpr.debug("HGVS (DNA): '" + hgvsDna + "'" + "\nEffects:" + effs);
		Assert.assertEquals("c.50-2_51dupAAAG", hgvsDna);
	}

}
