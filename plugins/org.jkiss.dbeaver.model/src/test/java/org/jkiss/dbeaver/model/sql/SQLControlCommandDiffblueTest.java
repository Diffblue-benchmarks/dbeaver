package org.jkiss.dbeaver.model.sql;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashMap;
import java.util.Map;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.model.DBPEvent;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SQLControlCommandDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link SQLControlCommand#SQLControlCommand(DBPDataSource, String, String, int, int, Map)}
   *   <li>{@link SQLControlCommand#setData(Object)}
   *   <li>{@link SQLControlCommand#reset()}
   *   <li>{@link SQLControlCommand#getCommand()}
   *   <li>{@link SQLControlCommand#getCommandId()}
   *   <li>{@link SQLControlCommand#getData()}
   *   <li>{@link SQLControlCommand#getDataSource()}
   *   <li>{@link SQLControlCommand#getLength()}
   *   <li>{@link SQLControlCommand#getOffset()}
   *   <li>{@link SQLControlCommand#getOriginalText()}
   *   <li>{@link SQLControlCommand#getParameter()}
   *   <li>{@link SQLControlCommand#getParameters()}
   *   <li>{@link SQLControlCommand#getText()}
   *   <li>{@link SQLControlCommand#isEmptyCommand()}
   *   <li>{@link SQLControlCommand#toString()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLControlCommand.<init>(DBPDataSource, String, String, int, int, Map)",
    "String SQLControlCommand.getCommand()",
    "String SQLControlCommand.getCommandId()",
    "Object SQLControlCommand.getData()",
    "DBPDataSource SQLControlCommand.getDataSource()",
    "int SQLControlCommand.getLength()",
    "int SQLControlCommand.getOffset()",
    "String SQLControlCommand.getOriginalText()",
    "String SQLControlCommand.getParameter()",
    "Map SQLControlCommand.getParameters()",
    "String SQLControlCommand.getText()",
    "boolean SQLControlCommand.isEmptyCommand()",
    "void SQLControlCommand.reset()",
    "void SQLControlCommand.setData(Object)",
    "String SQLControlCommand.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange
    DBPDataSource dataSource = mock(DBPDataSource.class);
    HashMap<String, String> parameters = new HashMap<>();

    // Act
    SQLControlCommand actualSqlControlCommand =
        new SQLControlCommand(dataSource, "Text", "42", 2, 3, parameters);
    Object object = DBPEvent.RENAME;
    actualSqlControlCommand.setData(object);
    actualSqlControlCommand.reset();
    String actualCommand = actualSqlControlCommand.getCommand();
    String actualCommandId = actualSqlControlCommand.getCommandId();
    Object actualData = actualSqlControlCommand.getData();
    DBPDataSource actualDataSource = actualSqlControlCommand.getDataSource();
    int actualLength = actualSqlControlCommand.getLength();
    int actualOffset = actualSqlControlCommand.getOffset();
    String actualOriginalText = actualSqlControlCommand.getOriginalText();
    String actualParameter = actualSqlControlCommand.getParameter();
    Map<String, String> actualParameters = actualSqlControlCommand.getParameters();
    String actualText = actualSqlControlCommand.getText();
    boolean actualIsEmptyCommandResult = actualSqlControlCommand.isEmptyCommand();

    // Assert
    assertEquals("42", actualCommand);
    assertEquals("42", actualCommandId);
    assertEquals("42", actualOriginalText);
    assertEquals("Text", actualText);
    assertEquals("Text", actualSqlControlCommand.toString());
    assertNull(actualParameter);
    assertEquals(2, actualOffset);
    assertEquals(3, actualLength);
    assertFalse(actualIsEmptyCommandResult);
    assertTrue(actualParameters.isEmpty());
    assertSame(parameters, actualParameters);
    assertSame(object, actualData);
    assertSame(dataSource, actualDataSource);
  }

  /**
   * Test {@link SQLControlCommand#SQLControlCommand(DBPDataSource, SQLSyntaxManager, String,
   * String, int, int, boolean)}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>Then return Command is empty string.
   * </ul>
   *
   * <p>Method under test: {@link SQLControlCommand#SQLControlCommand(DBPDataSource,
   * SQLSyntaxManager, String, String, int, int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLControlCommand.<init>(DBPDataSource, SQLSyntaxManager, String, String, int, int, boolean)"
  })
  public void testNewSQLControlCommand_given42_thenReturnCommandIsEmptyString() {
    // Arrange
    DBPDataSource dataSource = mock(DBPDataSource.class);

    SQLSyntaxManager syntaxManager = mock(SQLSyntaxManager.class);
    when(syntaxManager.getControlCommandPrefix()).thenReturn("42");

    // Act
    SQLControlCommand actualSqlControlCommand =
        new SQLControlCommand(dataSource, syntaxManager, "42", "42", 2, 3, true);

    // Assert
    verify(syntaxManager, atLeast(1)).getControlCommandPrefix();
    assertEquals("", actualSqlControlCommand.getCommand());
    assertEquals("", actualSqlControlCommand.getOriginalText());
    assertEquals("42", actualSqlControlCommand.getText());
    assertEquals("42", actualSqlControlCommand.toString());
  }

  /**
   * Test {@link SQLControlCommand#SQLControlCommand(DBPDataSource, SQLSyntaxManager, String,
   * String, int, int, boolean)}.
   *
   * <ul>
   *   <li>Given {@code Control Command Prefix}.
   *   <li>Then return CommandId is {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link SQLControlCommand#SQLControlCommand(DBPDataSource,
   * SQLSyntaxManager, String, String, int, int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLControlCommand.<init>(DBPDataSource, SQLSyntaxManager, String, String, int, int, boolean)"
  })
  public void testNewSQLControlCommand_givenControlCommandPrefix_thenReturnCommandIdIs42() {
    // Arrange
    DBPDataSource dataSource = mock(DBPDataSource.class);

    SQLSyntaxManager syntaxManager = mock(SQLSyntaxManager.class);
    when(syntaxManager.getControlCommandPrefix()).thenReturn("Control Command Prefix");

    // Act
    SQLControlCommand actualSqlControlCommand =
        new SQLControlCommand(dataSource, syntaxManager, "Text", "42", 2, 3, true);

    // Assert
    verify(syntaxManager, atLeast(1)).getControlCommandPrefix();
    assertEquals("42", actualSqlControlCommand.getCommandId());
    assertEquals("Text", actualSqlControlCommand.getCommand());
    assertEquals("Text", actualSqlControlCommand.getOriginalText());
    assertEquals("Text", actualSqlControlCommand.getText());
    assertEquals("Text", actualSqlControlCommand.toString());
  }

  /**
   * Test {@link SQLControlCommand#SQLControlCommand(DBPDataSource, SQLSyntaxManager, String,
   * String, int, int, boolean)}.
   *
   * <ul>
   *   <li>Given {@code Control Command Prefix}.
   *   <li>Then return CommandId is {@code Text}.
   * </ul>
   *
   * <p>Method under test: {@link SQLControlCommand#SQLControlCommand(DBPDataSource,
   * SQLSyntaxManager, String, String, int, int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLControlCommand.<init>(DBPDataSource, SQLSyntaxManager, String, String, int, int, boolean)"
  })
  public void testNewSQLControlCommand_givenControlCommandPrefix_thenReturnCommandIdIsText() {
    // Arrange
    DBPDataSource dataSource = mock(DBPDataSource.class);

    SQLSyntaxManager syntaxManager = mock(SQLSyntaxManager.class);
    when(syntaxManager.getControlCommandPrefix()).thenReturn("Control Command Prefix");

    // Act
    SQLControlCommand actualSqlControlCommand =
        new SQLControlCommand(dataSource, syntaxManager, "Text", null, 2, 3, true);

    // Assert
    verify(syntaxManager, atLeast(1)).getControlCommandPrefix();
    assertEquals("Text", actualSqlControlCommand.getCommand());
    assertEquals("Text", actualSqlControlCommand.getCommandId());
    assertEquals("Text", actualSqlControlCommand.getOriginalText());
    assertEquals("Text", actualSqlControlCommand.getText());
    assertEquals("Text", actualSqlControlCommand.toString());
  }

  /**
   * Test {@link SQLControlCommand#SQLControlCommand(DBPDataSource, SQLSyntaxManager, String,
   * String, int, int, boolean)}.
   *
   * <ul>
   *   <li>Given empty string.
   * </ul>
   *
   * <p>Method under test: {@link SQLControlCommand#SQLControlCommand(DBPDataSource,
   * SQLSyntaxManager, String, String, int, int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLControlCommand.<init>(DBPDataSource, SQLSyntaxManager, String, String, int, int, boolean)"
  })
  public void testNewSQLControlCommand_givenEmptyString() {
    // Arrange
    DBPDataSource dataSource = mock(DBPDataSource.class);

    SQLSyntaxManager syntaxManager = mock(SQLSyntaxManager.class);
    when(syntaxManager.getControlCommandPrefix()).thenReturn("");

    // Act
    SQLControlCommand actualSqlControlCommand =
        new SQLControlCommand(dataSource, syntaxManager, "Text", "42", 2, 3, true);

    // Assert
    verify(syntaxManager).getControlCommandPrefix();
    assertEquals("42", actualSqlControlCommand.getCommandId());
    assertEquals("Text", actualSqlControlCommand.getCommand());
    assertEquals("Text", actualSqlControlCommand.getOriginalText());
    assertEquals("Text", actualSqlControlCommand.getText());
    assertEquals("Text", actualSqlControlCommand.toString());
  }

  /**
   * Test {@link SQLControlCommand#getDataSourceContainer()}.
   *
   * <ul>
   *   <li>Then calls {@link DBPDataSource#getContainer()}.
   * </ul>
   *
   * <p>Method under test: {@link SQLControlCommand#getDataSourceContainer()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPDataSourceContainer SQLControlCommand.getDataSourceContainer()"})
  public void testGetDataSourceContainer_thenCallsGetContainer() {
    // Arrange
    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getContainer()).thenReturn(mock(DBPDataSourceContainer.class));
    SQLControlCommand sqlControlCommand =
        new SQLControlCommand(dataSource, "Text", "42", 2, 3, new HashMap<>());

    // Act
    sqlControlCommand.getDataSourceContainer();

    // Assert
    verify(dataSource).getContainer();
  }

  /**
   * Test {@link SQLControlCommand#getDataSourceContainer()}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SQLControlCommand#getDataSourceContainer()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPDataSourceContainer SQLControlCommand.getDataSourceContainer()"})
  public void testGetDataSourceContainer_thenReturnNull() {
    // Arrange
    SQLControlCommand sqlControlCommand =
        new SQLControlCommand(null, "Text", "42", 2, 3, new HashMap<>());

    // Act and Assert
    assertNull(sqlControlCommand.getDataSourceContainer());
  }
}
