package org.jkiss.dbeaver.model.exec.compile;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBCCompileErrorDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DBCCompileError#DBCCompileError(boolean, String, int, int)}
   *   <li>{@link DBCCompileError#getLine()}
   *   <li>{@link DBCCompileError#getMessage()}
   *   <li>{@link DBCCompileError#getPosition()}
   *   <li>{@link DBCCompileError#isError()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBCCompileError.<init>(boolean, String, int, int)",
    "int DBCCompileError.getLine()",
    "String DBCCompileError.getMessage()",
    "int DBCCompileError.getPosition()",
    "boolean DBCCompileError.isError()"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    DBCCompileError actualDbcCompileError =
        new DBCCompileError(true, "Not all who wander are lost", 2, 1);
    int actualLine = actualDbcCompileError.getLine();
    String actualMessage = actualDbcCompileError.getMessage();
    int actualPosition = actualDbcCompileError.getPosition();

    // Assert
    assertEquals("Not all who wander are lost", actualMessage);
    assertEquals(1, actualPosition);
    assertEquals(2, actualLine);
    assertTrue(actualDbcCompileError.isError());
  }

  /**
   * Test {@link DBCCompileError#toString()}.
   *
   * <ul>
   *   <li>Then return {@code Not all who wander are lost}.
   * </ul>
   *
   * <p>Method under test: {@link DBCCompileError#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBCCompileError.toString()"})
  public void testToString_thenReturnNotAllWhoWanderAreLost() {
    // Arrange
    DBCCompileError dbcCompileError =
        new DBCCompileError(true, "Not all who wander are lost", 0, 1);

    // Act and Assert
    assertEquals("Not all who wander are lost", dbcCompileError.toString());
  }

  /**
   * Test {@link DBCCompileError#toString()}.
   *
   * <ul>
   *   <li>Then return {@code Not all who wander are lost Compile error at line 2, column 1}.
   * </ul>
   *
   * <p>Method under test: {@link DBCCompileError#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBCCompileError.toString()"})
  public void testToString_thenReturnNotAllWhoWanderAreLostCompileErrorAtLine2Column1() {
    // Arrange
    DBCCompileError dbcCompileError =
        new DBCCompileError(true, "Not all who wander are lost", 2, 1);

    // Act and Assert
    assertEquals(
        "Not all who wander are lost\nCompile error at line 2, column 1",
        dbcCompileError.toString());
  }
}
