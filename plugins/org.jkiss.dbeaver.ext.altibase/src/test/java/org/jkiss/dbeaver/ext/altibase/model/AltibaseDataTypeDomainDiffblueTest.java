package org.jkiss.dbeaver.ext.altibase.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class AltibaseDataTypeDomainDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link AltibaseDataTypeDomain#getTypeName()}
   *   <li>{@link AltibaseDataTypeDomain#getValueType()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String AltibaseDataTypeDomain.getTypeName()",
    "int AltibaseDataTypeDomain.getValueType()"
  })
  public void testGettersAndSetters() {
    // Arrange
    AltibaseDataTypeDomain valueOfResult = AltibaseDataTypeDomain.valueOf("CHAR");

    // Act
    String actualTypeName = valueOfResult.getTypeName();

    // Assert
    assertEquals("CHAR", actualTypeName);
    assertEquals(1, valueOfResult.getValueType());
  }

  /**
   * Test {@link AltibaseDataTypeDomain#getByTypeName(String)}.
   *
   * <ul>
   *   <li>When {@code BIGINT}.
   *   <li>Then return {@code BIGINT}.
   * </ul>
   *
   * <p>Method under test: {@link AltibaseDataTypeDomain#getByTypeName(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"AltibaseDataTypeDomain AltibaseDataTypeDomain.getByTypeName(String)"})
  public void testGetByTypeName_whenBigint_thenReturnBigint() {
    // Arrange, Act and Assert
    assertEquals(AltibaseDataTypeDomain.BIGINT, AltibaseDataTypeDomain.getByTypeName("BIGINT"));
  }

  /**
   * Test {@link AltibaseDataTypeDomain#getByTypeName(String)}.
   *
   * <ul>
   *   <li>When {@code Type Name}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link AltibaseDataTypeDomain#getByTypeName(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"AltibaseDataTypeDomain AltibaseDataTypeDomain.getByTypeName(String)"})
  public void testGetByTypeName_whenTypeName_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(AltibaseDataTypeDomain.getByTypeName("Type Name"));
  }
}
