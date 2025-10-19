package org.jkiss.dbeaver.model.security.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashSet;
import java.util.Set;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SMAuthPermissionsDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link SMAuthPermissions#SMAuthPermissions(String, String, Set)}
   *   <li>{@link SMAuthPermissions#getPermissions()}
   *   <li>{@link SMAuthPermissions#getSessionId()}
   *   <li>{@link SMAuthPermissions#getUserId()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SMAuthPermissions.<init>(String, String, Set)",
    "Set SMAuthPermissions.getPermissions()",
    "String SMAuthPermissions.getSessionId()",
    "String SMAuthPermissions.getUserId()"
  })
  public void testGettersAndSetters() {
    // Arrange
    HashSet<String> permissions = new HashSet<>();

    // Act
    SMAuthPermissions actualSmAuthPermissions = new SMAuthPermissions("42", "42", permissions);
    Set<String> actualPermissions = actualSmAuthPermissions.getPermissions();
    String actualSessionId = actualSmAuthPermissions.getSessionId();

    // Assert
    assertEquals("42", actualSessionId);
    assertEquals("42", actualSmAuthPermissions.getUserId());
    assertTrue(actualPermissions.isEmpty());
    assertSame(permissions, actualPermissions);
  }

  /**
   * Test {@link SMAuthPermissions#equals(Object)}, and {@link SMAuthPermissions#hashCode()}.
   *
   * <ul>
   *   <li>When other is equal.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link SMAuthPermissions#equals(Object)}
   *   <li>{@link SMAuthPermissions#hashCode()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean SMAuthPermissions.equals(Object)",
    "int SMAuthPermissions.hashCode()"
  })
  public void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    SMAuthPermissions smAuthPermissions = new SMAuthPermissions("42", "42", new HashSet<>());
    SMAuthPermissions smAuthPermissions2 = new SMAuthPermissions("42", "42", new HashSet<>());

    // Act and Assert
    assertEquals(smAuthPermissions, smAuthPermissions2);
    assertEquals(smAuthPermissions.hashCode(), smAuthPermissions2.hashCode());
  }

  /**
   * Test {@link SMAuthPermissions#equals(Object)}, and {@link SMAuthPermissions#hashCode()}.
   *
   * <ul>
   *   <li>When other is same.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link SMAuthPermissions#equals(Object)}
   *   <li>{@link SMAuthPermissions#hashCode()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean SMAuthPermissions.equals(Object)",
    "int SMAuthPermissions.hashCode()"
  })
  public void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    SMAuthPermissions smAuthPermissions = new SMAuthPermissions("42", "42", new HashSet<>());

    // Act and Assert
    assertEquals(smAuthPermissions, smAuthPermissions);
    int expectedHashCodeResult = smAuthPermissions.hashCode();
    assertEquals(expectedHashCodeResult, smAuthPermissions.hashCode());
  }

  /**
   * Test {@link SMAuthPermissions#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link SMAuthPermissions#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean SMAuthPermissions.equals(Object)",
    "int SMAuthPermissions.hashCode()"
  })
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    SMAuthPermissions smAuthPermissions = new SMAuthPermissions("User Id", "42", new HashSet<>());

    // Act and Assert
    assertNotEquals(smAuthPermissions, new SMAuthPermissions("42", "42", new HashSet<>()));
  }

  /**
   * Test {@link SMAuthPermissions#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link SMAuthPermissions#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean SMAuthPermissions.equals(Object)",
    "int SMAuthPermissions.hashCode()"
  })
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    HashSet<String> permissions = new HashSet<>();
    permissions.add("foo");
    SMAuthPermissions smAuthPermissions = new SMAuthPermissions("42", "42", permissions);

    // Act and Assert
    assertNotEquals(smAuthPermissions, new SMAuthPermissions("42", "42", new HashSet<>()));
  }

  /**
   * Test {@link SMAuthPermissions#equals(Object)}.
   *
   * <ul>
   *   <li>When other is {@code null}.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link SMAuthPermissions#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean SMAuthPermissions.equals(Object)",
    "int SMAuthPermissions.hashCode()"
  })
  public void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new SMAuthPermissions("42", "42", new HashSet<>()), null);
  }

  /**
   * Test {@link SMAuthPermissions#equals(Object)}.
   *
   * <ul>
   *   <li>When other is wrong type.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link SMAuthPermissions#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean SMAuthPermissions.equals(Object)",
    "int SMAuthPermissions.hashCode()"
  })
  public void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(
        new SMAuthPermissions("42", "42", new HashSet<>()), "Different type to SMAuthPermissions");
  }
}
