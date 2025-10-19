package org.jkiss.dbeaver.model.sql.format.tokenized;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class FormatterTokenDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>When {@code Arg String}.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link FormatterToken#FormatterToken(TokenType, String)}
   *   <li>{@link FormatterToken#setPos(int)}
   *   <li>{@link FormatterToken#setString(String)}
   *   <li>{@link FormatterToken#setType(TokenType)}
   *   <li>{@link FormatterToken#toString()}
   *   <li>{@link FormatterToken#getPos()}
   *   <li>{@link FormatterToken#getString()}
   *   <li>{@link FormatterToken#getType()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void FormatterToken.<init>(TokenType, String)",
    "void FormatterToken.<init>(TokenType, String, int)",
    "int FormatterToken.getPos()",
    "String FormatterToken.getString()",
    "TokenType FormatterToken.getType()",
    "void FormatterToken.setPos(int)",
    "void FormatterToken.setString(String)",
    "void FormatterToken.setType(TokenType)",
    "String FormatterToken.toString()"
  })
  public void testGettersAndSetters_whenArgString() {
    // Arrange and Act
    FormatterToken actualFormatterToken = new FormatterToken(TokenType.SPACE, "Arg String");
    actualFormatterToken.setPos(1);
    actualFormatterToken.setString("Arg String");
    actualFormatterToken.setType(TokenType.SPACE);
    String actualToStringResult = actualFormatterToken.toString();
    int actualPos = actualFormatterToken.getPos();
    String actualString = actualFormatterToken.getString();

    // Assert
    assertEquals("Arg String [SPACE]", actualToStringResult);
    assertEquals("Arg String", actualString);
    assertEquals(1, actualPos);
    assertEquals(TokenType.SPACE, actualFormatterToken.getType());
  }

  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>When one.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link FormatterToken#FormatterToken(TokenType, String, int)}
   *   <li>{@link FormatterToken#setPos(int)}
   *   <li>{@link FormatterToken#setString(String)}
   *   <li>{@link FormatterToken#setType(TokenType)}
   *   <li>{@link FormatterToken#toString()}
   *   <li>{@link FormatterToken#getPos()}
   *   <li>{@link FormatterToken#getString()}
   *   <li>{@link FormatterToken#getType()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void FormatterToken.<init>(TokenType, String)",
    "void FormatterToken.<init>(TokenType, String, int)",
    "int FormatterToken.getPos()",
    "String FormatterToken.getString()",
    "TokenType FormatterToken.getType()",
    "void FormatterToken.setPos(int)",
    "void FormatterToken.setString(String)",
    "void FormatterToken.setType(TokenType)",
    "String FormatterToken.toString()"
  })
  public void testGettersAndSetters_whenOne() {
    // Arrange and Act
    FormatterToken actualFormatterToken = new FormatterToken(TokenType.SPACE, "Arg String", 1);
    actualFormatterToken.setPos(1);
    actualFormatterToken.setString("Arg String");
    actualFormatterToken.setType(TokenType.SPACE);
    String actualToStringResult = actualFormatterToken.toString();
    int actualPos = actualFormatterToken.getPos();
    String actualString = actualFormatterToken.getString();

    // Assert
    assertEquals("Arg String [SPACE]", actualToStringResult);
    assertEquals("Arg String", actualString);
    assertEquals(1, actualPos);
    assertEquals(TokenType.SPACE, actualFormatterToken.getType());
  }
}
