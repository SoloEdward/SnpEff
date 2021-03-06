package org.snpeff.snpEffect.testCases.unity;

import junit.framework.Assert;

import org.junit.Test;
import org.snpeff.interval.Exon;
import org.snpeff.interval.Variant;
import org.snpeff.snpEffect.HgvsDna;
import org.snpeff.snpEffect.VariantEffect;
import org.snpeff.snpEffect.VariantEffects;
import org.snpeff.util.Gpr;
import org.snpeff.util.GprSeq;

/**
 * Test cases for HGVS's 'dup' on the negative strand
 */
public class TestCasesHgvsDnaDupNegative extends TestCasesBase {

	public TestCasesHgvsDnaDupNegative() {
		super();
	}

	@Override
	protected void init() {
		super.init();
		onlyMinusStrand = true;
		onlyPlusStrand = false;
		shiftHgvs = true;
	}

	@Test
	public void test_01() {
		Gpr.debug("Test");

		if (verbose) {
			Exon exFirst = transcript.sorted().get(0);
			String exFirstSeq = exFirst.isStrandPlus() ? exFirst.getSequence() : GprSeq.reverseWc(exFirst.getSequence());
			Gpr.debug(transcript + "\n\tSequence: " + exFirstSeq);
		}

		// Create variant
		Variant variant = new Variant(chromosome, 1001, "", "C", "");
		if (verbose) Gpr.debug("Variant: " + variant);

		// Analyze variant
		VariantEffects effs = snpEffectPredictor.variantEffect(variant);

		// Calculate HGVS
		VariantEffect eff = effs.get();
		HgvsDna hgvsc = new HgvsDna(eff);
		String hgvsDna = hgvsc.toString();

		// Check result
		if (verbose) Gpr.debug("HGVS (DNA): '" + hgvsDna + "'");
		Assert.assertEquals("c.1dupG", hgvsDna);
	}

	@Test
	public void test_02() {
		Gpr.debug("Test");

		if (verbose) {
			Exon exFirst = transcript.sorted().get(0);
			String exFirstSeq = exFirst.isStrandPlus() ? exFirst.getSequence() : GprSeq.reverseWc(exFirst.getSequence());
			Gpr.debug(transcript + "\n\tSequence: " + exFirstSeq);
		}

		// Create variant
		Variant variant = new Variant(chromosome, 997, "", "G", "");
		if (verbose) Gpr.debug("Variant: " + variant);

		// Analyze variant
		VariantEffects effs = snpEffectPredictor.variantEffect(variant);

		// Calculate HGVS
		VariantEffect eff = effs.get();
		HgvsDna hgvsc = new HgvsDna(eff);
		String hgvsDna = hgvsc.toString();

		// Check result
		if (verbose) Gpr.debug("HGVS (DNA): '" + hgvsDna + "'");
		Assert.assertEquals("c.5dupC", hgvsDna);
	}

	/**
	 * Dup on the reverse strand: More than one base
	 */
	@Test
	public void test_03() {
		Gpr.debug("Test");
		if (verbose) Gpr.debug(transcript);

		if (verbose) {
			Exon exFirst = transcript.sorted().get(0);
			String exFirstSeq = exFirst.isStrandPlus() ? exFirst.getSequence() : GprSeq.reverseWc(exFirst.getSequence());
			Gpr.debug(transcript + "\n\tSequence: " + exFirstSeq);
		}

		// Create variant
		Variant variant = new Variant(chromosome, 996, "", "CG", "");
		if (verbose) Gpr.debug("Variant: " + variant);

		// Analyze variant
		VariantEffects effs = snpEffectPredictor.variantEffect(variant);

		// Calculate HGVS
		VariantEffect eff = effs.get();
		HgvsDna hgvsc = new HgvsDna(eff);
		String hgvsDna = hgvsc.toString();

		// Check result
		if (verbose) Gpr.debug("HGVS (DNA): '" + hgvsDna + "'");
		Assert.assertEquals("c.5_6dupCG", hgvsDna);
	}

	/**
	 * Dup on the reverse strand: More than one base
	 */
	@Test
	public void test_04() {
		Gpr.debug("Test");
		if (verbose) Gpr.debug(transcript);

		if (verbose) {
			Exon exFirst = transcript.sorted().get(0);
			String exFirstSeq = exFirst.isStrandPlus() ? exFirst.getSequence() : GprSeq.reverseWc(exFirst.getSequence());
			Gpr.debug(transcript + "\n\tSequence: " + exFirstSeq);
		}

		// Create variant
		Variant variant = new Variant(chromosome, 984, "", "CAT", "");
		if (verbose) Gpr.debug("Variant: " + variant);

		// Analyze variant
		VariantEffects effs = snpEffectPredictor.variantEffect(variant);

		// Calculate HGVS
		VariantEffect eff = effs.get();
		HgvsDna hgvsc = new HgvsDna(eff);
		String hgvsDna = hgvsc.toString();

		// Check result
		if (verbose) Gpr.debug("HGVS (DNA): '" + hgvsDna + "'");
		Assert.assertEquals("c.16_18dupATG", hgvsDna);
	}

}
