package org.jkiss.dbeaver.ext.oracle.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class OracleDependencyTypeDiffblueTest {
  /**
   * Test {@link OracleDependencyType#getByType(String)}.
   *
   * <ul>
   *   <li>When {@code HARD}.
   *   <li>Then return {@code HARD}.
   * </ul>
   *
   * <p>Method under test: {@link OracleDependencyType#getByType(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"OracleDependencyType OracleDependencyType.getByType(String)"})
  public void testGetByType_whenHard_thenReturnHard() {
    // Arrange, Act and Assert
    assertEquals(OracleDependencyType.HARD, OracleDependencyType.getByType("HARD"));
  }

  /**
   * Test {@link OracleDependencyType#getByType(String)}.
   *
   * <ul>
   *   <li>When {@code REF}.
   *   <li>Then return {@code REF}.
   * </ul>
   *
   * <p>Method under test: {@link OracleDependencyType#getByType(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"OracleDependencyType OracleDependencyType.getByType(String)"})
  public void testGetByType_whenRef_thenReturnRef() {
    // Arrange, Act and Assert
    assertEquals(OracleDependencyType.REF, OracleDependencyType.getByType("REF"));
  }

  /**
   * Test {@link OracleDependencyType#getByType(String)}.
   *
   * <ul>
   *   <li>When {@code Type}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link OracleDependencyType#getByType(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"OracleDependencyType OracleDependencyType.getByType(String)"})
  public void testGetByType_whenType_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(OracleDependencyType.getByType("Type"));
  }

  /**
   * Test {@link OracleDependencyType#getType()}.
   *
   * <p>Method under test: {@link OracleDependencyType#getType()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String OracleDependencyType.getType()"})
  public void testGetType() {
    // Arrange, Act and Assert
    assertEquals("HARD", OracleDependencyType.valueOf("HARD").getType());
  }
}
