package org.jkiss.dbeaver.ext.altibase.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class AltibaseTriggerTypeDiffblueTest {
  /**
   * Test {@link AltibaseTriggerType#getDisplayName()}.
   *
   * <p>Method under test: {@link AltibaseTriggerType#getDisplayName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String AltibaseTriggerType.getDisplayName()"})
  public void testGetDisplayName() {
    // Arrange, Act and Assert
    assertEquals("BEFORE INSERT", AltibaseTriggerType.BEFORE_INSERT.getDisplayName());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link AltibaseTriggerType#getType()}
   *   <li>{@link AltibaseTriggerType#isDbEvent()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "int AltibaseTriggerType.getType()",
    "boolean AltibaseTriggerType.isDbEvent()"
  })
  public void testGettersAndSetters() {
    // Arrange
    AltibaseTriggerType valueOfResult = AltibaseTriggerType.valueOf("BEFORE_INSERT");

    // Act
    int actualType = valueOfResult.getType();

    // Assert
    assertEquals(1, actualType);
    assertFalse(valueOfResult.isDbEvent());
  }

  /**
   * Test {@link AltibaseTriggerType#getByType(int)}.
   *
   * <ul>
   *   <li>When nineteen.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link AltibaseTriggerType#getByType(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"AltibaseTriggerType AltibaseTriggerType.getByType(int)"})
  public void testGetByType_whenNineteen_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(AltibaseTriggerType.getByType(19));
  }

  /**
   * Test {@link AltibaseTriggerType#getByType(int)}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then return {@code BEFORE_INSERT}.
   * </ul>
   *
   * <p>Method under test: {@link AltibaseTriggerType#getByType(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"AltibaseTriggerType AltibaseTriggerType.getByType(int)"})
  public void testGetByType_whenOne_thenReturnBeforeInsert() {
    // Arrange, Act and Assert
    assertEquals(AltibaseTriggerType.BEFORE_INSERT, AltibaseTriggerType.getByType(1));
  }
}
