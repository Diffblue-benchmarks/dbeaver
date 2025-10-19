package org.jkiss.dbeaver.ext.oracle.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class OracleDataTypeModifierDiffblueTest {
  /**
   * Test {@link OracleDataTypeModifier#resolveTypeModifier(String)}.
   *
   * <ul>
   *   <li>When empty string.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link OracleDataTypeModifier#resolveTypeModifier(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"OracleDataTypeModifier OracleDataTypeModifier.resolveTypeModifier(String)"})
  public void testResolveTypeModifier_whenEmptyString_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(OracleDataTypeModifier.resolveTypeModifier(""));
  }

  /**
   * Test {@link OracleDataTypeModifier#resolveTypeModifier(String)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link OracleDataTypeModifier#resolveTypeModifier(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"OracleDataTypeModifier OracleDataTypeModifier.resolveTypeModifier(String)"})
  public void testResolveTypeModifier_whenNull_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(OracleDataTypeModifier.resolveTypeModifier(null));
  }

  /**
   * Test {@link OracleDataTypeModifier#resolveTypeModifier(String)}.
   *
   * <ul>
   *   <li>When {@code REF}.
   *   <li>Then return {@code REF}.
   * </ul>
   *
   * <p>Method under test: {@link OracleDataTypeModifier#resolveTypeModifier(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"OracleDataTypeModifier OracleDataTypeModifier.resolveTypeModifier(String)"})
  public void testResolveTypeModifier_whenRef_thenReturnRef() {
    // Arrange, Act and Assert
    assertEquals(OracleDataTypeModifier.REF, OracleDataTypeModifier.resolveTypeModifier("REF"));
  }

  /**
   * Test {@link OracleDataTypeModifier#resolveTypeModifier(String)}.
   *
   * <ul>
   *   <li>When {@code Type Mod}.
   *   <li>Then return {@code POINTER}.
   * </ul>
   *
   * <p>Method under test: {@link OracleDataTypeModifier#resolveTypeModifier(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"OracleDataTypeModifier OracleDataTypeModifier.resolveTypeModifier(String)"})
  public void testResolveTypeModifier_whenTypeMod_thenReturnPointer() {
    // Arrange, Act and Assert
    assertEquals(
        OracleDataTypeModifier.POINTER, OracleDataTypeModifier.resolveTypeModifier("Type Mod"));
  }
}
