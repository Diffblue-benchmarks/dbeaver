package org.jkiss.dbeaver.model.fs.lock;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.fs.lock.FileLockInfo.Builder;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class FileLockInfoDiffblueTest {
  /**
   * Test Builder {@link Builder#build()}.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link Builder#build()}
   *   <li>{@link Builder#Builder(String)}
   *   <li>{@link Builder#setApplicationId(String)}
   *   <li>{@link Builder#setOperationName(String)}
   *   <li>{@link Builder#setOperationStartTime(long)}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void Builder.<init>(String)",
    "FileLockInfo Builder.build()",
    "Builder Builder.setApplicationId(String)",
    "Builder Builder.setOperationName(String)",
    "Builder Builder.setOperationStartTime(long)"
  })
  public void testBuilderBuild() {
    // Arrange and Act
    FileLockInfo actualFileLockInfo =
        new Builder("42")
            .setApplicationId("42")
            .setOperationName("Operation Name")
            .setOperationStartTime(1L)
            .build();

    // Assert
    assertEquals("42", actualFileLockInfo.getApplicationId());
    assertEquals("42", actualFileLockInfo.getOperationId());
    assertEquals("Operation Name", actualFileLockInfo.getOperationName());
    assertEquals(1L, actualFileLockInfo.getOperationStartTime());
    assertFalse(actualFileLockInfo.isBlank());
  }

  /**
   * Test {@link FileLockInfo#emptyLock()}.
   *
   * <p>Method under test: {@link FileLockInfo#emptyLock()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"FileLockInfo FileLockInfo.emptyLock()"})
  public void testEmptyLock() {
    // Arrange and Act
    FileLockInfo actualEmptyLockResult = FileLockInfo.emptyLock();

    // Assert
    assertEquals("", actualEmptyLockResult.getApplicationId());
    assertEquals("", actualEmptyLockResult.getOperationId());
    assertEquals("", actualEmptyLockResult.getOperationName());
    assertTrue(actualEmptyLockResult.isBlank());
  }

  /**
   * Test {@link FileLockInfo#isBlank()}.
   *
   * <ul>
   *   <li>Given emptyLock.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link FileLockInfo#isBlank()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean FileLockInfo.isBlank()"})
  public void testIsBlank_givenEmptyLock_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(FileLockInfo.emptyLock().isBlank());
  }

  /**
   * Test {@link FileLockInfo#isBlank()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link FileLockInfo#isBlank()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean FileLockInfo.isBlank()"})
  public void testIsBlank_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(
        new Builder("42")
            .setApplicationId("42")
            .setOperationName("Operation Name")
            .setOperationStartTime(1L)
            .build()
            .isBlank());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link FileLockInfo#getApplicationId()}
   *   <li>{@link FileLockInfo#getOperationId()}
   *   <li>{@link FileLockInfo#getOperationName()}
   *   <li>{@link FileLockInfo#getOperationStartTime()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String FileLockInfo.getApplicationId()",
    "String FileLockInfo.getOperationId()",
    "String FileLockInfo.getOperationName()",
    "long FileLockInfo.getOperationStartTime()"
  })
  public void testGettersAndSetters() {
    // Arrange
    FileLockInfo fileLockInfo =
        new Builder("42")
            .setApplicationId("42")
            .setOperationName("Operation Name")
            .setOperationStartTime(1L)
            .build();

    // Act
    String actualApplicationId = fileLockInfo.getApplicationId();
    String actualOperationId = fileLockInfo.getOperationId();
    String actualOperationName = fileLockInfo.getOperationName();

    // Assert
    assertEquals("42", actualApplicationId);
    assertEquals("42", actualOperationId);
    assertEquals("Operation Name", actualOperationName);
    assertEquals(1L, fileLockInfo.getOperationStartTime());
  }
}
