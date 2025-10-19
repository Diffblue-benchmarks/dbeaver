package org.jkiss.dbeaver.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.DBPErrorAssistant.ErrorPosition;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBPErrorAssistantDiffblueTest {
  /**
   * Test ErrorPosition new {@link ErrorPosition} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link ErrorPosition}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ErrorPosition.<init>()"})
  public void testErrorPositionNewErrorPosition() {
    // Arrange and Act
    ErrorPosition actualErrorPosition = new ErrorPosition();

    // Assert
    assertNull(actualErrorPosition.info);
    assertEquals(-1, actualErrorPosition.line);
    assertEquals(-1, actualErrorPosition.position);
  }

  /**
   * Test ErrorPosition {@link ErrorPosition#toString()}.
   *
   * <ul>
   *   <li>Given {@link ErrorPosition} (default constructor) {@link ErrorPosition#info} is {@code
   *       foo}.
   *   <li>Then return {@code -1:-1 (foo)}.
   * </ul>
   *
   * <p>Method under test: {@link ErrorPosition#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String ErrorPosition.toString()"})
  public void testErrorPositionToString_givenErrorPositionInfoIsFoo_thenReturn11Foo() {
    // Arrange
    ErrorPosition errorPosition = new ErrorPosition();
    errorPosition.info = "foo";

    // Act and Assert
    assertEquals("-1:-1 (foo)", errorPosition.toString());
  }

  /**
   * Test ErrorPosition {@link ErrorPosition#toString()}.
   *
   * <ul>
   *   <li>Given {@link ErrorPosition} (default constructor).
   *   <li>Then return {@code -1:-1}.
   * </ul>
   *
   * <p>Method under test: {@link ErrorPosition#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String ErrorPosition.toString()"})
  public void testErrorPositionToString_givenErrorPosition_thenReturn11() {
    // Arrange, Act and Assert
    assertEquals("-1:-1", new ErrorPosition().toString());
  }
}
