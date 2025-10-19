package org.jkiss.dbeaver.model.exec;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBCExecutionPurposeDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DBCExecutionPurpose#getId()}
   *   <li>{@link DBCExecutionPurpose#getTitle()}
   *   <li>{@link DBCExecutionPurpose#isUser()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "int DBCExecutionPurpose.getId()",
    "String DBCExecutionPurpose.getTitle()",
    "boolean DBCExecutionPurpose.isUser()"
  })
  public void testGettersAndSetters() {
    // Arrange
    DBCExecutionPurpose valueOfResult = DBCExecutionPurpose.valueOf("USER");

    // Act
    int actualId = valueOfResult.getId();
    String actualTitle = valueOfResult.getTitle();

    // Assert
    assertEquals("User", actualTitle);
    assertEquals(0, actualId);
    assertTrue(valueOfResult.isUser());
  }

  /**
   * Test {@link DBCExecutionPurpose#getById(int)}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then return {@code USER_FILTERED}.
   * </ul>
   *
   * <p>Method under test: {@link DBCExecutionPurpose#getById(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBCExecutionPurpose DBCExecutionPurpose.getById(int)"})
  public void testGetById_whenOne_thenReturnUserFiltered() {
    // Arrange, Act and Assert
    assertEquals(DBCExecutionPurpose.USER_FILTERED, DBCExecutionPurpose.getById(1));
  }

  /**
   * Test {@link DBCExecutionPurpose#getById(int)}.
   *
   * <ul>
   *   <li>When six.
   *   <li>Then return {@code USER}.
   * </ul>
   *
   * <p>Method under test: {@link DBCExecutionPurpose#getById(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBCExecutionPurpose DBCExecutionPurpose.getById(int)"})
  public void testGetById_whenSix_thenReturnUser() {
    // Arrange, Act and Assert
    assertEquals(DBCExecutionPurpose.USER, DBCExecutionPurpose.getById(6));
  }
}
