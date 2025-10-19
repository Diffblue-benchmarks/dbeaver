package org.jkiss.dbeaver.model.auth;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SMAuthConfigurationReferenceDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link SMAuthConfigurationReference#SMAuthConfigurationReference(String, String)}
   *   <li>{@link SMAuthConfigurationReference#toString()}
   *   <li>{@link SMAuthConfigurationReference#getAuthProviderConfigurationId()}
   *   <li>{@link SMAuthConfigurationReference#getAuthProviderId()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SMAuthConfigurationReference.<init>(String, String)",
    "String SMAuthConfigurationReference.getAuthProviderConfigurationId()",
    "String SMAuthConfigurationReference.getAuthProviderId()",
    "String SMAuthConfigurationReference.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    SMAuthConfigurationReference actualSmAuthConfigurationReference =
        new SMAuthConfigurationReference("42", "42");
    String actualToStringResult = actualSmAuthConfigurationReference.toString();
    String actualAuthProviderConfigurationId =
        actualSmAuthConfigurationReference.getAuthProviderConfigurationId();

    // Assert
    assertEquals("42", actualAuthProviderConfigurationId);
    assertEquals("42", actualSmAuthConfigurationReference.getAuthProviderId());
    assertEquals("42:42", actualToStringResult);
  }

  /**
   * Test {@link SMAuthConfigurationReference#equals(Object)}, and {@link
   * SMAuthConfigurationReference#hashCode()}.
   *
   * <ul>
   *   <li>When other is equal.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link SMAuthConfigurationReference#equals(Object)}
   *   <li>{@link SMAuthConfigurationReference#hashCode()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean SMAuthConfigurationReference.equals(Object)",
    "int SMAuthConfigurationReference.hashCode()"
  })
  public void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    SMAuthConfigurationReference smAuthConfigurationReference =
        new SMAuthConfigurationReference("42", "42");
    SMAuthConfigurationReference smAuthConfigurationReference2 =
        new SMAuthConfigurationReference("42", "42");

    // Act and Assert
    assertEquals(smAuthConfigurationReference, smAuthConfigurationReference2);
    assertEquals(smAuthConfigurationReference.hashCode(), smAuthConfigurationReference2.hashCode());
  }

  /**
   * Test {@link SMAuthConfigurationReference#equals(Object)}, and {@link
   * SMAuthConfigurationReference#hashCode()}.
   *
   * <ul>
   *   <li>When other is same.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link SMAuthConfigurationReference#equals(Object)}
   *   <li>{@link SMAuthConfigurationReference#hashCode()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean SMAuthConfigurationReference.equals(Object)",
    "int SMAuthConfigurationReference.hashCode()"
  })
  public void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    SMAuthConfigurationReference smAuthConfigurationReference =
        new SMAuthConfigurationReference("42", "42");

    // Act and Assert
    assertEquals(smAuthConfigurationReference, smAuthConfigurationReference);
    int expectedHashCodeResult = smAuthConfigurationReference.hashCode();
    assertEquals(expectedHashCodeResult, smAuthConfigurationReference.hashCode());
  }

  /**
   * Test {@link SMAuthConfigurationReference#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link SMAuthConfigurationReference#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean SMAuthConfigurationReference.equals(Object)",
    "int SMAuthConfigurationReference.hashCode()"
  })
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    SMAuthConfigurationReference smAuthConfigurationReference =
        new SMAuthConfigurationReference("Auth Provider Id", "42");

    // Act and Assert
    assertNotEquals(smAuthConfigurationReference, new SMAuthConfigurationReference("42", "42"));
  }

  /**
   * Test {@link SMAuthConfigurationReference#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link SMAuthConfigurationReference#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean SMAuthConfigurationReference.equals(Object)",
    "int SMAuthConfigurationReference.hashCode()"
  })
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    SMAuthConfigurationReference smAuthConfigurationReference =
        new SMAuthConfigurationReference("42", "Auth Provider Configuration Id");

    // Act and Assert
    assertNotEquals(smAuthConfigurationReference, new SMAuthConfigurationReference("42", "42"));
  }

  /**
   * Test {@link SMAuthConfigurationReference#equals(Object)}.
   *
   * <ul>
   *   <li>When other is {@code null}.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link SMAuthConfigurationReference#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean SMAuthConfigurationReference.equals(Object)",
    "int SMAuthConfigurationReference.hashCode()"
  })
  public void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new SMAuthConfigurationReference("42", "42"), null);
  }

  /**
   * Test {@link SMAuthConfigurationReference#equals(Object)}.
   *
   * <ul>
   *   <li>When other is wrong type.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link SMAuthConfigurationReference#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean SMAuthConfigurationReference.equals(Object)",
    "int SMAuthConfigurationReference.hashCode()"
  })
  public void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(
        new SMAuthConfigurationReference("42", "42"),
        "Different type to SMAuthConfigurationReference");
  }
}
