package org.jkiss.dbeaver.tools.transfer.stream.exporter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DataExporterArrayFormatDiffblueTest {
  /**
   * Test {@link DataExporterArrayFormat#getArrayFormat(String)}.
   *
   * <ul>
   *   <li>When {@code Bracket Pair}.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link DataExporterArrayFormat#getArrayFormat(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DataExporterArrayFormat DataExporterArrayFormat.getArrayFormat(String)"})
  public void testGetArrayFormat_whenBracketPair_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class, () -> DataExporterArrayFormat.getArrayFormat("Bracket Pair"));
  }

  /**
   * Test {@link DataExporterArrayFormat#getArrayFormatOnPrefix(char)}.
   *
   * <ul>
   *   <li>When {@code A}.
   *   <li>Then return {@code CURLY_BRACKETS}.
   * </ul>
   *
   * <p>Method under test: {@link DataExporterArrayFormat#getArrayFormatOnPrefix(char)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DataExporterArrayFormat DataExporterArrayFormat.getArrayFormatOnPrefix(char)"
  })
  public void testGetArrayFormatOnPrefix_whenA_thenReturnCurlyBrackets() {
    // Arrange, Act and Assert
    assertEquals(
        DataExporterArrayFormat.CURLY_BRACKETS,
        DataExporterArrayFormat.getArrayFormatOnPrefix('A'));
  }

  /**
   * Test {@link DataExporterArrayFormat#getArrayFormatOnPrefix(char)}.
   *
   * <ul>
   *   <li>When {@code [}.
   *   <li>Then return {@code SQUARE_BRACKETS}.
   * </ul>
   *
   * <p>Method under test: {@link DataExporterArrayFormat#getArrayFormatOnPrefix(char)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DataExporterArrayFormat DataExporterArrayFormat.getArrayFormatOnPrefix(char)"
  })
  public void testGetArrayFormatOnPrefix_whenLeftSquareBracket_thenReturnSquareBrackets() {
    // Arrange, Act and Assert
    assertEquals(
        DataExporterArrayFormat.SQUARE_BRACKETS,
        DataExporterArrayFormat.getArrayFormatOnPrefix('['));
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DataExporterArrayFormat#getPrefix()}
   *   <li>{@link DataExporterArrayFormat#getSuffix()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "char DataExporterArrayFormat.getPrefix()",
    "char DataExporterArrayFormat.getSuffix()"
  })
  public void testGettersAndSetters() {
    // Arrange
    DataExporterArrayFormat valueOfResult = DataExporterArrayFormat.valueOf("SQUARE_BRACKETS");

    // Act
    char actualPrefix = valueOfResult.getPrefix();

    // Assert
    assertEquals('[', actualPrefix);
    assertEquals(']', valueOfResult.getSuffix());
  }
}
