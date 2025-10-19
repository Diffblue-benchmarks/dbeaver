package org.jkiss.dbeaver.model.navigator.meta;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.apache.commons.jexl3.JexlExpression;
import org.apache.commons.jexl3.internal.Script;
import org.jkiss.dbeaver.model.DBPImage;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBXTreeIconDiffblueTest {
  /**
   * Test {@link DBXTreeIcon#DBXTreeIcon(String, DBPImage)}.
   *
   * <ul>
   *   <li>Then return Expression ParsedText is {@code org.jkiss.dbeaver.model}.
   * </ul>
   *
   * <p>Method under test: {@link DBXTreeIcon#DBXTreeIcon(String, DBPImage)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBXTreeIcon.<init>(String, DBPImage)"})
  public void testNewDBXTreeIcon_thenReturnExpressionParsedTextIsOrgJkissDbeaverModel() {
    // Arrange and Act
    DBXTreeIcon actualDbxTreeIcon =
        new DBXTreeIcon("org.jkiss.dbeaver.model", mock(DBPImage.class));

    // Assert
    JexlExpression expression = actualDbxTreeIcon.getExpression();
    assertTrue(expression instanceof Script);
    assertEquals("org.jkiss.dbeaver.model", expression.getParsedText());
    assertEquals("org.jkiss.dbeaver.model", expression.getSourceText());
    assertEquals("org.jkiss.dbeaver.model", actualDbxTreeIcon.getExprString());
    assertEquals(1, ((Script) expression).getVariables().size());
  }

  /**
   * Test {@link DBXTreeIcon#DBXTreeIcon(String, DBPImage)}.
   *
   * <ul>
   *   <li>When {@code 42}.
   *   <li>Then return Expression ParsedText is {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link DBXTreeIcon#DBXTreeIcon(String, DBPImage)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBXTreeIcon.<init>(String, DBPImage)"})
  public void testNewDBXTreeIcon_when42_thenReturnExpressionParsedTextIs42() {
    // Arrange and Act
    DBXTreeIcon actualDbxTreeIcon = new DBXTreeIcon("42", mock(DBPImage.class));

    // Assert
    JexlExpression expression = actualDbxTreeIcon.getExpression();
    assertTrue(expression instanceof Script);
    assertEquals("42", expression.getParsedText());
    assertEquals("42", expression.getSourceText());
    assertEquals("42", actualDbxTreeIcon.getExprString());
    assertTrue(((Script) expression).getVariables().isEmpty());
  }

  /**
   * Test {@link DBXTreeIcon#DBXTreeIcon(String, DBPImage)}.
   *
   * <ul>
   *   <li>When {@code Expr String}.
   *   <li>Then return {@code Expr String}.
   * </ul>
   *
   * <p>Method under test: {@link DBXTreeIcon#DBXTreeIcon(String, DBPImage)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBXTreeIcon.<init>(String, DBPImage)"})
  public void testNewDBXTreeIcon_whenExprString_thenReturnExprString() {
    // Arrange
    DBPImage icon = mock(DBPImage.class);

    // Act
    DBXTreeIcon actualDbxTreeIcon = new DBXTreeIcon("Expr String", icon);

    // Assert
    assertEquals("Expr String", actualDbxTreeIcon.getExprString());
    assertNull(actualDbxTreeIcon.getExpression());
    assertSame(icon, actualDbxTreeIcon.getIcon());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DBXTreeIcon#dispose()}
   *   <li>{@link DBXTreeIcon#getExprString()}
   *   <li>{@link DBXTreeIcon#getExpression()}
   *   <li>{@link DBXTreeIcon#getIcon()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBXTreeIcon.dispose()",
    "String DBXTreeIcon.getExprString()",
    "JexlExpression DBXTreeIcon.getExpression()",
    "DBPImage DBXTreeIcon.getIcon()"
  })
  public void testGettersAndSetters() {
    // Arrange
    DBXTreeIcon dbxTreeIcon = new DBXTreeIcon("Expr String", mock(DBPImage.class));

    // Act
    dbxTreeIcon.dispose();
    String actualExprString = dbxTreeIcon.getExprString();
    JexlExpression actualExpression = dbxTreeIcon.getExpression();
    dbxTreeIcon.getIcon();

    // Assert
    assertEquals("Expr String", actualExprString);
    assertNull(actualExpression);
  }
}
