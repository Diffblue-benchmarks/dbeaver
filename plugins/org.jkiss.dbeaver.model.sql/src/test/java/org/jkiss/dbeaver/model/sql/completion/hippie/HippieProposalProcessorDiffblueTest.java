package org.jkiss.dbeaver.model.sql.completion.hippie;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.jkiss.dbeaver.model.text.parser.TPWordDetector;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class HippieProposalProcessorDiffblueTest {
  /**
   * Test {@link HippieProposalProcessor#HippieProposalProcessor()}.
   *
   * <p>Method under test: {@link HippieProposalProcessor#HippieProposalProcessor()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void HippieProposalProcessor.<init>()"})
  public void testNewHippieProposalProcessor() {
    // Arrange, Act and Assert
    assertNull(new HippieProposalProcessor().getErrorMessage());
  }

  /**
   * Test {@link HippieProposalProcessor#HippieProposalProcessor(TPWordDetector)}.
   *
   * <p>Method under test: {@link HippieProposalProcessor#HippieProposalProcessor(TPWordDetector)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void HippieProposalProcessor.<init>(TPWordDetector)"})
  public void testNewHippieProposalProcessor2() {
    // Arrange, Act and Assert
    assertNull(new HippieProposalProcessor(new TPWordDetector()).getErrorMessage());
  }

  /**
   * Test {@link HippieProposalProcessor#computeCompletionStrings(IDocument, int)}.
   *
   * <p>Method under test: {@link HippieProposalProcessor#computeCompletionStrings(IDocument, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String[] HippieProposalProcessor.computeCompletionStrings(IDocument, int)"})
  public void testComputeCompletionStrings() {
    // Arrange
    HippieProposalProcessor hippieProposalProcessor = new HippieProposalProcessor();

    // Act and Assert
    assertEquals(
        0,
        hippieProposalProcessor.computeCompletionStrings(
                new Document("Not all who wander are lost"), 2)
            .length);
  }

  /**
   * Test {@link HippieProposalProcessor#computeCompletionStrings(IDocument, int)}.
   *
   * <p>Method under test: {@link HippieProposalProcessor#computeCompletionStrings(IDocument, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String[] HippieProposalProcessor.computeCompletionStrings(IDocument, int)"})
  public void testComputeCompletionStrings2() {
    // Arrange
    HippieProposalProcessor hippieProposalProcessor =
        new HippieProposalProcessor(new TPWordDetector());

    // Act and Assert
    assertEquals(
        0,
        hippieProposalProcessor.computeCompletionStrings(
                new Document("[\\p{L}\\p{Mn}\\p{Pc}\\p{Nd}\\p{Nl}\\p{Sc}]+"), 2)
            .length);
  }

  /**
   * Test {@link HippieProposalProcessor#computeCompletionStrings(IDocument, int)}.
   *
   * <ul>
   *   <li>Given {@link HippieProposalProcessor#HippieProposalProcessor(TPWordDetector)} with
   *       wordDetector is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link HippieProposalProcessor#computeCompletionStrings(IDocument, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String[] HippieProposalProcessor.computeCompletionStrings(IDocument, int)"})
  public void testComputeCompletionStrings_givenHippieProposalProcessorWithWordDetectorIsNull() {
    // Arrange, Act and Assert
    assertEquals(0, new HippieProposalProcessor(null).computeCompletionStrings(null, 2).length);
  }

  /**
   * Test {@link HippieProposalProcessor#computeCompletionStrings(IDocument, int)}.
   *
   * <ul>
   *   <li>Then return array of {@link String} with {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link HippieProposalProcessor#computeCompletionStrings(IDocument, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String[] HippieProposalProcessor.computeCompletionStrings(IDocument, int)"})
  public void testComputeCompletionStrings_thenReturnArrayOfStringWith42() {
    // Arrange
    HippieProposalProcessor hippieProposalProcessor = new HippieProposalProcessor();

    // Act and Assert
    assertArrayEquals(
        new String[] {"42"},
        hippieProposalProcessor.computeCompletionStrings(new Document("42"), 0));
  }

  /**
   * Test {@link HippieProposalProcessor#computeCompletionStrings(IDocument, int)}.
   *
   * <ul>
   *   <li>Then return array of {@link String} with {@code Not}.
   * </ul>
   *
   * <p>Method under test: {@link HippieProposalProcessor#computeCompletionStrings(IDocument, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String[] HippieProposalProcessor.computeCompletionStrings(IDocument, int)"})
  public void testComputeCompletionStrings_thenReturnArrayOfStringWithNot() {
    // Arrange
    HippieProposalProcessor hippieProposalProcessor = new HippieProposalProcessor();

    // Act and Assert
    assertArrayEquals(
        new String[] {"Not"},
        hippieProposalProcessor.computeCompletionStrings(
            new Document("Not all who wander are lost"), 0));
  }

  /**
   * Test {@link HippieProposalProcessor#computeCompletionStrings(IDocument, int)}.
   *
   * <ul>
   *   <li>Then return array of {@link String} with {@code Position}.
   * </ul>
   *
   * <p>Method under test: {@link HippieProposalProcessor#computeCompletionStrings(IDocument, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String[] HippieProposalProcessor.computeCompletionStrings(IDocument, int)"})
  public void testComputeCompletionStrings_thenReturnArrayOfStringWithPosition() {
    // Arrange
    HippieProposalProcessor hippieProposalProcessor = new HippieProposalProcessor();

    // Act and Assert
    assertArrayEquals(
        new String[] {"Position"},
        hippieProposalProcessor.computeCompletionStrings(
            new Document(
                "Position did not change in loop (this would lead to recursion -- and should never happen)."),
            2));
  }

  /**
   * Test {@link HippieProposalProcessor#computeCompletionStrings(IDocument, int)}.
   *
   * <ul>
   *   <li>When {@link Document#Document(String)} with initialContent is {@code Not}.
   * </ul>
   *
   * <p>Method under test: {@link HippieProposalProcessor#computeCompletionStrings(IDocument, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String[] HippieProposalProcessor.computeCompletionStrings(IDocument, int)"})
  public void testComputeCompletionStrings_whenDocumentWithInitialContentIsNot() {
    // Arrange
    HippieProposalProcessor hippieProposalProcessor = new HippieProposalProcessor();

    // Act and Assert
    assertEquals(
        0, hippieProposalProcessor.computeCompletionStrings(new Document("Not"), 2).length);
  }

  /**
   * Test {@link HippieProposalProcessor#computeCompletionStrings(IDocument, int)}.
   *
   * <ul>
   *   <li>When {@link Document#Document(String)} with initialContent is {@code
   *       [\p{L}\p{Mn}\p{Pc}\p{Nd}\p{Nl}\p{Sc}]+}.
   * </ul>
   *
   * <p>Method under test: {@link HippieProposalProcessor#computeCompletionStrings(IDocument, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String[] HippieProposalProcessor.computeCompletionStrings(IDocument, int)"})
  public void testComputeCompletionStrings_whenDocumentWithInitialContentIsPLPMnPPcPNdPNlPSc() {
    // Arrange
    HippieProposalProcessor hippieProposalProcessor = new HippieProposalProcessor();

    // Act and Assert
    assertEquals(
        0,
        hippieProposalProcessor.computeCompletionStrings(
                new Document("[\\p{L}\\p{Mn}\\p{Pc}\\p{Nd}\\p{Nl}\\p{Sc}]+"), 2)
            .length);
  }

  /**
   * Test {@link HippieProposalProcessor#computeCompletionStrings(IDocument, int)}.
   *
   * <ul>
   *   <li>When {@link Document#Document()}.
   *   <li>Then return array length is zero.
   * </ul>
   *
   * <p>Method under test: {@link HippieProposalProcessor#computeCompletionStrings(IDocument, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String[] HippieProposalProcessor.computeCompletionStrings(IDocument, int)"})
  public void testComputeCompletionStrings_whenDocument_thenReturnArrayLengthIsZero() {
    // Arrange
    HippieProposalProcessor hippieProposalProcessor = new HippieProposalProcessor();

    // Act and Assert
    assertEquals(0, hippieProposalProcessor.computeCompletionStrings(new Document(), 2).length);
  }

  /**
   * Test {@link HippieProposalProcessor#computeCompletionStrings(IDocument, int)}.
   *
   * <ul>
   *   <li>When {@link Document#Document()}.
   *   <li>Then return array length is zero.
   * </ul>
   *
   * <p>Method under test: {@link HippieProposalProcessor#computeCompletionStrings(IDocument, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String[] HippieProposalProcessor.computeCompletionStrings(IDocument, int)"})
  public void testComputeCompletionStrings_whenDocument_thenReturnArrayLengthIsZero2() {
    // Arrange
    HippieProposalProcessor hippieProposalProcessor = new HippieProposalProcessor();

    // Act and Assert
    assertEquals(0, hippieProposalProcessor.computeCompletionStrings(new Document(), 0).length);
  }

  /**
   * Test {@link HippieProposalProcessor#computeCompletionStrings(IDocument, int)}.
   *
   * <ul>
   *   <li>When forty-six.
   *   <li>Then return array of {@link String} with {@code lead}.
   * </ul>
   *
   * <p>Method under test: {@link HippieProposalProcessor#computeCompletionStrings(IDocument, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String[] HippieProposalProcessor.computeCompletionStrings(IDocument, int)"})
  public void testComputeCompletionStrings_whenFortySix_thenReturnArrayOfStringWithLead() {
    // Arrange
    HippieProposalProcessor hippieProposalProcessor = new HippieProposalProcessor();

    // Act and Assert
    assertArrayEquals(
        new String[] {"lead"},
        hippieProposalProcessor.computeCompletionStrings(
            new Document(
                "Position did not change in loop (this would lead to recursion -- and should never happen)."),
            46));
  }

  /**
   * Test {@link HippieProposalProcessor#computeCompletionStrings(IDocument, int)}.
   *
   * <ul>
   *   <li>When three.
   *   <li>Then return array length is zero.
   * </ul>
   *
   * <p>Method under test: {@link HippieProposalProcessor#computeCompletionStrings(IDocument, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String[] HippieProposalProcessor.computeCompletionStrings(IDocument, int)"})
  public void testComputeCompletionStrings_whenThree_thenReturnArrayLengthIsZero() {
    // Arrange
    HippieProposalProcessor hippieProposalProcessor = new HippieProposalProcessor();

    // Act and Assert
    assertEquals(
        0,
        hippieProposalProcessor.computeCompletionStrings(
                new Document("Not all who wander are lost"), 3)
            .length);
  }

  /**
   * Test {@link HippieProposalProcessor#getErrorMessage()}.
   *
   * <p>Method under test: {@link HippieProposalProcessor#getErrorMessage()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String HippieProposalProcessor.getErrorMessage()"})
  public void testGetErrorMessage() {
    // Arrange, Act and Assert
    assertNull(new HippieProposalProcessor().getErrorMessage());
  }
}
