package org.jkiss.dbeaver.model.sql.parser.tokens;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SQLTokenTypeDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link SQLTokenType#getTokenType()}
   *   <li>{@link SQLTokenType#getTypeId()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "int SQLTokenType.getTokenType()",
    "java.lang.String SQLTokenType.getTypeId()"
  })
  public void testGettersAndSetters() {
    // Arrange
    SQLTokenType valueOfResult = SQLTokenType.valueOf("T_KEYWORD");

    // Act
    int actualTokenType = valueOfResult.getTokenType();

    // Assert
    assertNull(valueOfResult.getTypeId());
    assertEquals(500, actualTokenType);
  }
}
