package org.jkiss.dbeaver.ext.postgresql.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class PostgreAttributeIdentityDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link PostgreAttributeIdentity#getCode()}
   *   <li>{@link PostgreAttributeIdentity#getDefinitionClause()}
   *   <li>{@link PostgreAttributeIdentity#getName()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String PostgreAttributeIdentity.getCode()",
    "String PostgreAttributeIdentity.getDefinitionClause()",
    "String PostgreAttributeIdentity.getName()"
  })
  public void testGettersAndSetters() {
    // Arrange
    PostgreAttributeIdentity valueOfResult = PostgreAttributeIdentity.valueOf("GENERATED_ALWAYS");

    // Act
    String actualCode = valueOfResult.getCode();
    String actualDefinitionClause = valueOfResult.getDefinitionClause();

    // Assert
    assertEquals("Always", valueOfResult.getName());
    assertEquals("GENERATED ALWAYS AS IDENTITY", actualDefinitionClause);
    assertEquals("a", actualCode);
  }

  /**
   * Test {@link PostgreAttributeIdentity#getByCode(String)}.
   *
   * <ul>
   *   <li>When {@code a}.
   *   <li>Then return {@code GENERATED_ALWAYS}.
   * </ul>
   *
   * <p>Method under test: {@link PostgreAttributeIdentity#getByCode(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"PostgreAttributeIdentity PostgreAttributeIdentity.getByCode(String)"})
  public void testGetByCode_whenA_thenReturnGeneratedAlways() {
    // Arrange, Act and Assert
    assertEquals(
        PostgreAttributeIdentity.GENERATED_ALWAYS, PostgreAttributeIdentity.getByCode("a"));
  }

  /**
   * Test {@link PostgreAttributeIdentity#getByCode(String)}.
   *
   * <ul>
   *   <li>When {@code Code}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link PostgreAttributeIdentity#getByCode(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"PostgreAttributeIdentity PostgreAttributeIdentity.getByCode(String)"})
  public void testGetByCode_whenCode_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(PostgreAttributeIdentity.getByCode("Code"));
  }
}
