package org.jkiss.dbeaver.ext.oracle.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.struct.rdb.DBSProcedureParameterKind;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class OracleParameterModeDiffblueTest {
  /**
   * Test {@link OracleParameterMode#getMode(String)}.
   *
   * <ul>
   *   <li>When empty string.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link OracleParameterMode#getMode(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"OracleParameterMode OracleParameterMode.getMode(String)"})
  public void testGetMode_whenEmptyString_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(OracleParameterMode.getMode(""));
  }

  /**
   * Test {@link OracleParameterMode#getMode(String)}.
   *
   * <ul>
   *   <li>When {@code IN}.
   *   <li>Then return {@code IN}.
   * </ul>
   *
   * <p>Method under test: {@link OracleParameterMode#getMode(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"OracleParameterMode OracleParameterMode.getMode(String)"})
  public void testGetMode_whenIn_thenReturnIn() {
    // Arrange, Act and Assert
    assertEquals(OracleParameterMode.IN, OracleParameterMode.getMode("IN"));
  }

  /**
   * Test {@link OracleParameterMode#getMode(String)}.
   *
   * <ul>
   *   <li>When {@code Mode Name}.
   *   <li>Then return {@code INOUT}.
   * </ul>
   *
   * <p>Method under test: {@link OracleParameterMode#getMode(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"OracleParameterMode OracleParameterMode.getMode(String)"})
  public void testGetMode_whenModeName_thenReturnInout() {
    // Arrange, Act and Assert
    assertEquals(OracleParameterMode.INOUT, OracleParameterMode.getMode("Mode Name"));
  }

  /**
   * Test {@link OracleParameterMode#getMode(String)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link OracleParameterMode#getMode(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"OracleParameterMode OracleParameterMode.getMode(String)"})
  public void testGetMode_whenNull_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(OracleParameterMode.getMode(null));
  }

  /**
   * Test {@link OracleParameterMode#getMode(String)}.
   *
   * <ul>
   *   <li>When {@code OUT}.
   *   <li>Then return {@code OUT}.
   * </ul>
   *
   * <p>Method under test: {@link OracleParameterMode#getMode(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"OracleParameterMode OracleParameterMode.getMode(String)"})
  public void testGetMode_whenOut_thenReturnOut() {
    // Arrange, Act and Assert
    assertEquals(OracleParameterMode.OUT, OracleParameterMode.getMode("OUT"));
  }

  /**
   * Test {@link OracleParameterMode#getParameterKind()}.
   *
   * <p>Method under test: {@link OracleParameterMode#getParameterKind()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBSProcedureParameterKind OracleParameterMode.getParameterKind()"})
  public void testGetParameterKind() {
    // Arrange, Act and Assert
    assertEquals(
        DBSProcedureParameterKind.IN, OracleParameterMode.valueOf("IN").getParameterKind());
  }
}
