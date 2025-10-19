package org.jkiss.dbeaver.model.ai;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.Log;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.model.ai.engine.AIDatabaseContext;
import org.jkiss.dbeaver.model.ai.engine.AIDatabaseContext.Builder;
import org.jkiss.dbeaver.model.ai.impl.MessageChunk;
import org.jkiss.dbeaver.model.ai.impl.MessageChunk.Code;
import org.jkiss.dbeaver.model.ai.impl.MessageChunk.Text;
import org.jkiss.dbeaver.model.app.DBPDataSourceRegistry;
import org.jkiss.dbeaver.model.app.DBPProject;
import org.jkiss.dbeaver.model.exec.DBCExecutionContext;
import org.jkiss.dbeaver.model.impl.VoidExecutionContextDefaults;
import org.jkiss.dbeaver.model.logical.DBSLogicalDataSource;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.jkiss.dbeaver.model.runtime.SubTaskProgressMonitor;
import org.jkiss.dbeaver.model.sql.SQLDialect;
import org.jkiss.dbeaver.model.struct.DBSObject;
import org.jkiss.utils.Pair;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class AITextUtilsDiffblueTest {
  /**
   * Test {@link AITextUtils#extractCode(String)}.
   *
   * <p>Method under test: {@link AITextUtils#extractCode(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String AITextUtils.extractCode(String)"})
  public void testExtractCode() {
    // Arrange, Act and Assert
    assertEquals("Markdown", AITextUtils.extractCode("Markdown"));
  }

  /**
   * Test {@link AITextUtils#splitIntoChunks(SQLDialect, String)} with {@code dialect}, {@code
   * text}.
   *
   * <p>Method under test: {@link AITextUtils#splitIntoChunks(SQLDialect, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"MessageChunk[] AITextUtils.splitIntoChunks(SQLDialect, String)"})
  public void testSplitIntoChunksWithDialectText() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getSingleLineComments()).thenReturn(new String[] {"```", "Text"});

    // Act
    MessageChunk[] actualSplitIntoChunksResult = AITextUtils.splitIntoChunks(dialect, "Text");

    // Assert
    verify(dialect).getSingleLineComments();
    MessageChunk messageChunk = actualSplitIntoChunksResult[0];
    assertTrue(messageChunk instanceof Text);
    assertEquals("Text", messageChunk.toRawString());
    assertEquals("Text", ((Text) messageChunk).text());
    assertNull(messageChunk.getCallback());
    assertEquals(1, actualSplitIntoChunksResult.length);
  }

  /**
   * Test {@link AITextUtils#splitIntoChunks(SQLDialect, String)} with {@code dialect}, {@code
   * text}.
   *
   * <p>Method under test: {@link AITextUtils#splitIntoChunks(SQLDialect, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"MessageChunk[] AITextUtils.splitIntoChunks(SQLDialect, String)"})
  public void testSplitIntoChunksWithDialectText2() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getMultiLineComments()).thenReturn(new Pair<>("Text", ""));
    when(dialect.getSingleLineComments()).thenReturn(new String[] {"Single Line Comments"});

    // Act
    MessageChunk[] actualSplitIntoChunksResult = AITextUtils.splitIntoChunks(dialect, "Text");

    // Assert
    verify(dialect).getMultiLineComments();
    verify(dialect, atLeast(1)).getSingleLineComments();
    MessageChunk messageChunk = actualSplitIntoChunksResult[0];
    assertTrue(messageChunk instanceof Text);
    assertEquals("Text", messageChunk.toRawString());
    assertEquals("Text", ((Text) messageChunk).text());
    assertNull(messageChunk.getCallback());
    assertEquals(1, actualSplitIntoChunksResult.length);
  }

  /**
   * Test {@link AITextUtils#splitIntoChunks(SQLDialect, String)} with {@code dialect}, {@code
   * text}.
   *
   * <ul>
   *   <li>Given {@link Pair#Pair(Object, Object)} with {@code First} and {@code Second}.
   * </ul>
   *
   * <p>Method under test: {@link AITextUtils#splitIntoChunks(SQLDialect, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"MessageChunk[] AITextUtils.splitIntoChunks(SQLDialect, String)"})
  public void testSplitIntoChunksWithDialectText_givenPairWithFirstAndSecond() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getMultiLineComments()).thenReturn(new Pair<>("First", "Second"));
    when(dialect.getSingleLineComments()).thenReturn(new String[] {"Single Line Comments"});

    // Act
    MessageChunk[] actualSplitIntoChunksResult = AITextUtils.splitIntoChunks(dialect, "Text");

    // Assert
    verify(dialect).getMultiLineComments();
    verify(dialect, atLeast(1)).getSingleLineComments();
    MessageChunk messageChunk = actualSplitIntoChunksResult[0];
    assertTrue(messageChunk instanceof Text);
    assertEquals("Text", messageChunk.toRawString());
    assertEquals("Text", ((Text) messageChunk).text());
    assertNull(messageChunk.getCallback());
    assertEquals(1, actualSplitIntoChunksResult.length);
  }

  /**
   * Test {@link AITextUtils#splitIntoChunks(SQLDialect, String)} with {@code dialect}, {@code
   * text}.
   *
   * <ul>
   *   <li>Given {@link Pair#Pair(Object, Object)} with {@code First} and second is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link AITextUtils#splitIntoChunks(SQLDialect, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"MessageChunk[] AITextUtils.splitIntoChunks(SQLDialect, String)"})
  public void testSplitIntoChunksWithDialectText_givenPairWithFirstAndSecondIsNull() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    Pair<String, String> pair = new Pair<>("First", null);
    when(dialect.getMultiLineComments()).thenReturn(pair);
    when(dialect.getSingleLineComments()).thenReturn(new String[] {"Single Line Comments"});

    // Act
    MessageChunk[] actualSplitIntoChunksResult = AITextUtils.splitIntoChunks(dialect, "Text");

    // Assert
    verify(dialect).getMultiLineComments();
    verify(dialect, atLeast(1)).getSingleLineComments();
    MessageChunk messageChunk = actualSplitIntoChunksResult[0];
    assertTrue(messageChunk instanceof Text);
    assertEquals("Text", messageChunk.toRawString());
    assertEquals("Text", ((Text) messageChunk).text());
    assertNull(messageChunk.getCallback());
    assertEquals(1, actualSplitIntoChunksResult.length);
  }

  /**
   * Test {@link AITextUtils#splitIntoChunks(SQLDialect, String)} with {@code dialect}, {@code
   * text}.
   *
   * <ul>
   *   <li>Given {@link Pair#Pair(Object, Object)} with first is {@code null} and {@code Second}.
   * </ul>
   *
   * <p>Method under test: {@link AITextUtils#splitIntoChunks(SQLDialect, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"MessageChunk[] AITextUtils.splitIntoChunks(SQLDialect, String)"})
  public void testSplitIntoChunksWithDialectText_givenPairWithFirstIsNullAndSecond() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getMultiLineComments()).thenReturn(new Pair<>(null, "Second"));
    when(dialect.getSingleLineComments()).thenReturn(new String[] {"Single Line Comments"});

    // Act
    MessageChunk[] actualSplitIntoChunksResult = AITextUtils.splitIntoChunks(dialect, "Text");

    // Assert
    verify(dialect).getMultiLineComments();
    verify(dialect, atLeast(1)).getSingleLineComments();
    MessageChunk messageChunk = actualSplitIntoChunksResult[0];
    assertTrue(messageChunk instanceof Text);
    assertEquals("Text", messageChunk.toRawString());
    assertEquals("Text", ((Text) messageChunk).text());
    assertNull(messageChunk.getCallback());
    assertEquals(1, actualSplitIntoChunksResult.length);
  }

  /**
   * Test {@link AITextUtils#splitIntoChunks(SQLDialect, String)} with {@code dialect}, {@code
   * text}.
   *
   * <ul>
   *   <li>Given {@link Pair#Pair(Object, Object)} with first is {@code Text} and {@code Second}.
   * </ul>
   *
   * <p>Method under test: {@link AITextUtils#splitIntoChunks(SQLDialect, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"MessageChunk[] AITextUtils.splitIntoChunks(SQLDialect, String)"})
  public void testSplitIntoChunksWithDialectText_givenPairWithFirstIsTextAndSecond() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getMultiLineComments()).thenReturn(new Pair<>("Text", "Second"));
    when(dialect.getSingleLineComments()).thenReturn(new String[] {"Single Line Comments"});

    // Act
    MessageChunk[] actualSplitIntoChunksResult = AITextUtils.splitIntoChunks(dialect, "Text");

    // Assert
    verify(dialect).getMultiLineComments();
    verify(dialect, atLeast(1)).getSingleLineComments();
    MessageChunk messageChunk = actualSplitIntoChunksResult[0];
    assertTrue(messageChunk instanceof Text);
    assertEquals("Text", messageChunk.toRawString());
    assertEquals("Text", ((Text) messageChunk).text());
    assertNull(messageChunk.getCallback());
    assertEquals(1, actualSplitIntoChunksResult.length);
  }

  /**
   * Test {@link AITextUtils#splitIntoChunks(SQLDialect, String)} with {@code dialect}, {@code
   * text}.
   *
   * <ul>
   *   <li>Then return first element toRawString is {@code ;}.
   * </ul>
   *
   * <p>Method under test: {@link AITextUtils#splitIntoChunks(SQLDialect, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"MessageChunk[] AITextUtils.splitIntoChunks(SQLDialect, String)"})
  public void testSplitIntoChunksWithDialectText_thenReturnFirstElementToRawStringIsSemicolon() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getMultiLineComments()).thenReturn(new Pair<>("First", "Second"));
    when(dialect.getSingleLineComments()).thenReturn(new String[] {"Single Line Comments"});

    // Act
    MessageChunk[] actualSplitIntoChunksResult = AITextUtils.splitIntoChunks(dialect, ";");

    // Assert
    verify(dialect).getMultiLineComments();
    verify(dialect, atLeast(1)).getSingleLineComments();
    MessageChunk messageChunk = actualSplitIntoChunksResult[0];
    assertTrue(messageChunk instanceof Text);
    assertEquals(";", messageChunk.toRawString());
    assertEquals(";", ((Text) messageChunk).text());
    assertNull(messageChunk.getCallback());
    assertEquals(1, actualSplitIntoChunksResult.length);
  }

  /**
   * Test {@link AITextUtils#splitIntoChunks(SQLDialect, String)} with {@code dialect}, {@code
   * text}.
   *
   * <ul>
   *   <li>When {@link SQLDialect} {@link SQLDialect#getMultiLineComments()} return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link AITextUtils#splitIntoChunks(SQLDialect, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"MessageChunk[] AITextUtils.splitIntoChunks(SQLDialect, String)"})
  public void testSplitIntoChunksWithDialectText_whenSQLDialectGetMultiLineCommentsReturnNull() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getMultiLineComments()).thenReturn(null);
    when(dialect.getSingleLineComments()).thenReturn(new String[] {"Single Line Comments"});

    // Act
    MessageChunk[] actualSplitIntoChunksResult = AITextUtils.splitIntoChunks(dialect, "Text");

    // Assert
    verify(dialect).getMultiLineComments();
    verify(dialect, atLeast(1)).getSingleLineComments();
    MessageChunk messageChunk = actualSplitIntoChunksResult[0];
    assertTrue(messageChunk instanceof Text);
    assertEquals("Text", messageChunk.toRawString());
    assertEquals("Text", ((Text) messageChunk).text());
    assertNull(messageChunk.getCallback());
    assertEquals(1, actualSplitIntoChunksResult.length);
  }

  /**
   * Test {@link AITextUtils#splitIntoChunks(SQLDialect, String)} with {@code dialect}, {@code
   * text}.
   *
   * <ul>
   *   <li>When {@link SQLDialect}.
   *   <li>Then return array length is zero.
   * </ul>
   *
   * <p>Method under test: {@link AITextUtils#splitIntoChunks(SQLDialect, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"MessageChunk[] AITextUtils.splitIntoChunks(SQLDialect, String)"})
  public void testSplitIntoChunksWithDialectText_whenSQLDialect_thenReturnArrayLengthIsZero() {
    // Arrange, Act and Assert
    assertEquals(0, AITextUtils.splitIntoChunks(mock(SQLDialect.class), "```").length);
  }

  /**
   * Test {@link AITextUtils#splitIntoChunks(SQLDialect, String)} with {@code dialect}, {@code
   * text}.
   *
   * <ul>
   *   <li>When {@code SELECT}.
   *   <li>Then first element return {@link MessageChunk.Code}.
   * </ul>
   *
   * <p>Method under test: {@link AITextUtils#splitIntoChunks(SQLDialect, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"MessageChunk[] AITextUtils.splitIntoChunks(SQLDialect, String)"})
  public void testSplitIntoChunksWithDialectText_whenSelect_thenFirstElementReturnCode() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getMultiLineComments()).thenReturn(new Pair<>("First", "Second"));
    when(dialect.getSingleLineComments()).thenReturn(new String[] {"Single Line Comments"});

    // Act
    MessageChunk[] actualSplitIntoChunksResult = AITextUtils.splitIntoChunks(dialect, "SELECT");

    // Assert
    verify(dialect).getMultiLineComments();
    verify(dialect, atLeast(1)).getSingleLineComments();
    MessageChunk messageChunk = actualSplitIntoChunksResult[0];
    assertTrue(messageChunk instanceof Code);
    assertEquals("SELECT", ((Code) messageChunk).text());
    assertNull(messageChunk.getCallback());
    assertEquals(1, actualSplitIntoChunksResult.length);
    assertEquals(AITextUtils.SQL_LANGUAGE_ID, ((Code) messageChunk).language());
  }

  /**
   * Test {@link AITextUtils#splitIntoChunks(String)} with {@code text}.
   *
   * <ul>
   *   <li>Then return array length is zero.
   * </ul>
   *
   * <p>Method under test: {@link AITextUtils#splitIntoChunks(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"MessageChunk[] AITextUtils.splitIntoChunks(String)"})
  public void testSplitIntoChunksWithText_thenReturnArrayLengthIsZero() {
    // Arrange, Act and Assert
    assertEquals(0, AITextUtils.splitIntoChunks("```").length);
  }

  /**
   * Test {@link AITextUtils#splitIntoChunks(String)} with {@code text}.
   *
   * <ul>
   *   <li>Then return first element toRawString is {@code ;}.
   * </ul>
   *
   * <p>Method under test: {@link AITextUtils#splitIntoChunks(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"MessageChunk[] AITextUtils.splitIntoChunks(String)"})
  public void testSplitIntoChunksWithText_thenReturnFirstElementToRawStringIsSemicolon() {
    // Arrange and Act
    MessageChunk[] actualSplitIntoChunksResult = AITextUtils.splitIntoChunks(";");

    // Assert
    MessageChunk messageChunk = actualSplitIntoChunksResult[0];
    assertTrue(messageChunk instanceof Text);
    assertEquals(";", messageChunk.toRawString());
    assertEquals(";", ((Text) messageChunk).text());
    assertNull(messageChunk.getCallback());
    assertEquals(1, actualSplitIntoChunksResult.length);
  }

  /**
   * Test {@link AITextUtils#splitIntoChunks(String)} with {@code text}.
   *
   * <ul>
   *   <li>When {@code SELECT}.
   *   <li>Then first element return {@link MessageChunk.Code}.
   * </ul>
   *
   * <p>Method under test: {@link AITextUtils#splitIntoChunks(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"MessageChunk[] AITextUtils.splitIntoChunks(String)"})
  public void testSplitIntoChunksWithText_whenSelect_thenFirstElementReturnCode() {
    // Arrange and Act
    MessageChunk[] actualSplitIntoChunksResult = AITextUtils.splitIntoChunks("SELECT");

    // Assert
    MessageChunk messageChunk = actualSplitIntoChunksResult[0];
    assertTrue(messageChunk instanceof Code);
    assertEquals("SELECT", ((Code) messageChunk).text());
    assertNull(messageChunk.getCallback());
    assertEquals(1, actualSplitIntoChunksResult.length);
    assertEquals(AITextUtils.SQL_LANGUAGE_ID, ((Code) messageChunk).language());
  }

  /**
   * Test {@link AITextUtils#splitIntoChunks(String)} with {@code text}.
   *
   * <ul>
   *   <li>When {@code Text}.
   *   <li>Then return first element toRawString is {@code Text}.
   * </ul>
   *
   * <p>Method under test: {@link AITextUtils#splitIntoChunks(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"MessageChunk[] AITextUtils.splitIntoChunks(String)"})
  public void testSplitIntoChunksWithText_whenText_thenReturnFirstElementToRawStringIsText() {
    // Arrange and Act
    MessageChunk[] actualSplitIntoChunksResult = AITextUtils.splitIntoChunks("Text");

    // Assert
    MessageChunk messageChunk = actualSplitIntoChunksResult[0];
    assertTrue(messageChunk instanceof Text);
    assertEquals("Text", messageChunk.toRawString());
    assertEquals("Text", ((Text) messageChunk).text());
    assertNull(messageChunk.getCallback());
    assertEquals(1, actualSplitIntoChunksResult.length);
  }

  /**
   * Test {@link AITextUtils#loadCustomEntities(DBRProgressMonitor, DBPDataSource, Set)}.
   *
   * <p>Method under test: {@link AITextUtils#loadCustomEntities(DBRProgressMonitor, DBPDataSource,
   * Set)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List AITextUtils.loadCustomEntities(DBRProgressMonitor, DBPDataSource, Set)"})
  public void testLoadCustomEntities() {
    // Arrange
    SubTaskProgressMonitor monitor = new SubTaskProgressMonitor(new LoggingProgressMonitor());

    DBPDataSourceRegistry dbpDataSourceRegistry = mock(DBPDataSourceRegistry.class);
    when(dbpDataSourceRegistry.getDataSource(Mockito.<String>any()))
        .thenReturn(mock(DBPDataSourceContainer.class));

    DBPProject dbpProject = mock(DBPProject.class);
    when(dbpProject.getDataSourceRegistry()).thenReturn(dbpDataSourceRegistry);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getProject()).thenReturn(dbpProject);

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    HashSet<String> ids = new HashSet<>();
    ids.add("Load custom entities");

    // Act
    List<DBSObject> actualLoadCustomEntitiesResult =
        AITextUtils.loadCustomEntities(monitor, dataSource, ids);

    // Assert
    verify(dataSource).getContainer();
    verify(dbpDataSourceContainer).getProject();
    verify(dbpDataSourceRegistry).getDataSource("Load custom entities");
    verify(dbpProject).getDataSourceRegistry();
    assertEquals(1, actualLoadCustomEntitiesResult.size());
  }

  /**
   * Test {@link AITextUtils#loadCustomEntities(DBRProgressMonitor, DBPDataSource, Set)}.
   *
   * <p>Method under test: {@link AITextUtils#loadCustomEntities(DBRProgressMonitor, DBPDataSource,
   * Set)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List AITextUtils.loadCustomEntities(DBRProgressMonitor, DBPDataSource, Set)"})
  public void testLoadCustomEntities2() {
    // Arrange
    Class<Object> forClass = Object.class;
    LoggingProgressMonitor original = new LoggingProgressMonitor(Log.getLog(forClass));
    SubTaskProgressMonitor monitor = new SubTaskProgressMonitor(original);

    DBPDataSourceRegistry dbpDataSourceRegistry = mock(DBPDataSourceRegistry.class);
    when(dbpDataSourceRegistry.getDataSource(Mockito.<String>any()))
        .thenReturn(mock(DBPDataSourceContainer.class));

    DBPProject dbpProject = mock(DBPProject.class);
    when(dbpProject.getDataSourceRegistry()).thenReturn(dbpDataSourceRegistry);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getProject()).thenReturn(dbpProject);

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    HashSet<String> ids = new HashSet<>();
    ids.add("Load custom entities");

    // Act
    List<DBSObject> actualLoadCustomEntitiesResult =
        AITextUtils.loadCustomEntities(monitor, dataSource, ids);

    // Assert
    verify(dataSource).getContainer();
    verify(dbpDataSourceContainer).getProject();
    verify(dbpDataSourceRegistry).getDataSource("Load custom entities");
    verify(dbpProject).getDataSourceRegistry();
    assertEquals(1, actualLoadCustomEntitiesResult.size());
  }

  /**
   * Test {@link AITextUtils#loadCustomEntities(DBRProgressMonitor, DBPDataSource, Set)}.
   *
   * <ul>
   *   <li>Given {@code /}.
   *   <li>When {@link HashSet#HashSet()} add {@code /}.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link AITextUtils#loadCustomEntities(DBRProgressMonitor, DBPDataSource,
   * Set)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List AITextUtils.loadCustomEntities(DBRProgressMonitor, DBPDataSource, Set)"})
  public void testLoadCustomEntities_givenSlash_whenHashSetAddSlash_thenReturnEmpty() {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBPDataSourceRegistry dbpDataSourceRegistry = mock(DBPDataSourceRegistry.class);
    when(dbpDataSourceRegistry.getDataSource(Mockito.<String>any()))
        .thenReturn(mock(DBPDataSourceContainer.class));

    DBPProject dbpProject = mock(DBPProject.class);
    when(dbpProject.getDataSourceRegistry()).thenReturn(dbpDataSourceRegistry);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getProject()).thenReturn(dbpProject);

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    HashSet<String> ids = new HashSet<>();
    ids.add("/");
    ids.add("Load custom entities");

    // Act
    List<DBSObject> actualLoadCustomEntitiesResult =
        AITextUtils.loadCustomEntities(monitor, dataSource, ids);

    // Assert
    verify(dataSource).getContainer();
    verify(dbpDataSourceContainer).getProject();
    verify(dbpDataSourceRegistry).getDataSource("Load custom entities");
    verify(dbpProject, atLeast(1)).getDataSourceRegistry();
    assertTrue(actualLoadCustomEntitiesResult.isEmpty());
  }

  /**
   * Test {@link AITextUtils#loadCustomEntities(DBRProgressMonitor, DBPDataSource, Set)}.
   *
   * <ul>
   *   <li>When {@code Object}.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link AITextUtils#loadCustomEntities(DBRProgressMonitor, DBPDataSource,
   * Set)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List AITextUtils.loadCustomEntities(DBRProgressMonitor, DBPDataSource, Set)"})
  public void testLoadCustomEntities_whenJavaLangObject_thenReturnEmpty() {
    // Arrange
    Class<Object> forClass = Object.class;
    LoggingProgressMonitor monitor = new LoggingProgressMonitor(Log.getLog(forClass));

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getProject()).thenReturn(mock(DBPProject.class));

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    // Act
    List<DBSObject> actualLoadCustomEntitiesResult =
        AITextUtils.loadCustomEntities(monitor, dataSource, new HashSet<>());

    // Assert
    verify(dataSource).getContainer();
    verify(dbpDataSourceContainer).getProject();
    assertTrue(actualLoadCustomEntitiesResult.isEmpty());
  }

  /**
   * Test {@link AITextUtils#loadCustomEntities(DBRProgressMonitor, DBPDataSource, Set)}.
   *
   * <ul>
   *   <li>When {@link LoggingProgressMonitor#LoggingProgressMonitor()}.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link AITextUtils#loadCustomEntities(DBRProgressMonitor, DBPDataSource,
   * Set)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List AITextUtils.loadCustomEntities(DBRProgressMonitor, DBPDataSource, Set)"})
  public void testLoadCustomEntities_whenLoggingProgressMonitor_thenReturnEmpty() {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getProject()).thenReturn(mock(DBPProject.class));

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    // Act
    List<DBSObject> actualLoadCustomEntitiesResult =
        AITextUtils.loadCustomEntities(monitor, dataSource, new HashSet<>());

    // Assert
    verify(dataSource).getContainer();
    verify(dbpDataSourceContainer).getProject();
    assertTrue(actualLoadCustomEntitiesResult.isEmpty());
  }

  /**
   * Test {@link AITextUtils#loadCustomEntities(DBRProgressMonitor, DBPDataSource, Set)}.
   *
   * <ul>
   *   <li>When {@link LoggingProgressMonitor#LoggingProgressMonitor()}.
   *   <li>Then return size is one.
   * </ul>
   *
   * <p>Method under test: {@link AITextUtils#loadCustomEntities(DBRProgressMonitor, DBPDataSource,
   * Set)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List AITextUtils.loadCustomEntities(DBRProgressMonitor, DBPDataSource, Set)"})
  public void testLoadCustomEntities_whenLoggingProgressMonitor_thenReturnSizeIsOne() {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBPDataSourceRegistry dbpDataSourceRegistry = mock(DBPDataSourceRegistry.class);
    when(dbpDataSourceRegistry.getDataSource(Mockito.<String>any()))
        .thenReturn(mock(DBPDataSourceContainer.class));

    DBPProject dbpProject = mock(DBPProject.class);
    when(dbpProject.getDataSourceRegistry()).thenReturn(dbpDataSourceRegistry);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getProject()).thenReturn(dbpProject);

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    HashSet<String> ids = new HashSet<>();
    ids.add("Load custom entities");

    // Act
    List<DBSObject> actualLoadCustomEntitiesResult =
        AITextUtils.loadCustomEntities(monitor, dataSource, ids);

    // Assert
    verify(dataSource).getContainer();
    verify(dbpDataSourceContainer).getProject();
    verify(dbpDataSourceRegistry).getDataSource("Load custom entities");
    verify(dbpProject).getDataSourceRegistry();
    assertEquals(1, actualLoadCustomEntitiesResult.size());
  }

  /**
   * Test {@link AITextUtils#processAndSplitCompletion(DBRProgressMonitor, AIDatabaseContext,
   * AISqlFormatter, String)}.
   *
   * <p>Method under test: {@link AITextUtils#processAndSplitCompletion(DBRProgressMonitor,
   * AIDatabaseContext, AISqlFormatter, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "MessageChunk[] AITextUtils.processAndSplitCompletion(DBRProgressMonitor, AIDatabaseContext, AISqlFormatter, String)"
  })
  public void testProcessAndSplitCompletion() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getMultiLineComments()).thenReturn(new Pair<>("First", "Second"));
    when(sqlDialect.getSingleLineComments()).thenReturn(new String[] {"Single Line Comments"});

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(sqlDialect);

    DBCExecutionContext executionContext = mock(DBCExecutionContext.class);
    when(executionContext.getDataSource()).thenReturn(dbpDataSource);
    when(executionContext.getContextDefaults()).thenReturn(new VoidExecutionContextDefaults());
    DBSLogicalDataSource dataSource = new DBSLogicalDataSource(mock(DBPDataSourceContainer.class));

    Builder builder = new Builder(dataSource);
    AIDatabaseContext context =
        builder
            .setCustomEntities(new ArrayList<>())
            .setExecutionContext(executionContext)
            .setScope(AIDatabaseScope.CURRENT_SCHEMA)
            .build();

    AISqlFormatter sqlFormatter = mock(AISqlFormatter.class);
    when(sqlFormatter.formatGeneratedQuery(
            Mockito.<DBRProgressMonitor>any(), Mockito.<DBPDataSource>any(), Mockito.<String>any()))
        .thenReturn("Format Generated Query");

    // Act
    MessageChunk[] actualProcessAndSplitCompletionResult =
        AITextUtils.processAndSplitCompletion(monitor, context, sqlFormatter, "Text");

    // Assert
    verify(dbpDataSource).getSQLDialect();
    verify(sqlFormatter)
        .formatGeneratedQuery(isA(DBRProgressMonitor.class), isA(DBPDataSource.class), eq("Text"));
    verify(executionContext).getContextDefaults();
    verify(executionContext, atLeast(1)).getDataSource();
    verify(sqlDialect).getMultiLineComments();
    verify(sqlDialect, atLeast(1)).getSingleLineComments();
    MessageChunk messageChunk = actualProcessAndSplitCompletionResult[0];
    assertTrue(messageChunk instanceof Text);
    assertEquals("Format Generated Query", messageChunk.toRawString());
    assertEquals("Format Generated Query", ((Text) messageChunk).text());
    assertNull(messageChunk.getCallback());
    assertEquals(1, actualProcessAndSplitCompletionResult.length);
  }

  /**
   * Test {@link AITextUtils#processAndSplitCompletion(DBRProgressMonitor, AIDatabaseContext,
   * AISqlFormatter, String)}.
   *
   * <p>Method under test: {@link AITextUtils#processAndSplitCompletion(DBRProgressMonitor,
   * AIDatabaseContext, AISqlFormatter, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "MessageChunk[] AITextUtils.processAndSplitCompletion(DBRProgressMonitor, AIDatabaseContext, AISqlFormatter, String)"
  })
  public void testProcessAndSplitCompletion2() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getMultiLineComments()).thenReturn(new Pair<>("Format", "Second"));
    when(sqlDialect.getSingleLineComments()).thenReturn(new String[] {"Single Line Comments"});

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(sqlDialect);

    DBCExecutionContext executionContext = mock(DBCExecutionContext.class);
    when(executionContext.getDataSource()).thenReturn(dbpDataSource);
    when(executionContext.getContextDefaults()).thenReturn(new VoidExecutionContextDefaults());
    DBSLogicalDataSource dataSource = new DBSLogicalDataSource(mock(DBPDataSourceContainer.class));

    Builder builder = new Builder(dataSource);
    AIDatabaseContext context =
        builder
            .setCustomEntities(new ArrayList<>())
            .setExecutionContext(executionContext)
            .setScope(AIDatabaseScope.CURRENT_SCHEMA)
            .build();

    AISqlFormatter sqlFormatter = mock(AISqlFormatter.class);
    when(sqlFormatter.formatGeneratedQuery(
            Mockito.<DBRProgressMonitor>any(), Mockito.<DBPDataSource>any(), Mockito.<String>any()))
        .thenReturn("Format Generated Query");

    // Act
    MessageChunk[] actualProcessAndSplitCompletionResult =
        AITextUtils.processAndSplitCompletion(monitor, context, sqlFormatter, "Text");

    // Assert
    verify(dbpDataSource).getSQLDialect();
    verify(sqlFormatter)
        .formatGeneratedQuery(isA(DBRProgressMonitor.class), isA(DBPDataSource.class), eq("Text"));
    verify(executionContext).getContextDefaults();
    verify(executionContext, atLeast(1)).getDataSource();
    verify(sqlDialect).getMultiLineComments();
    verify(sqlDialect, atLeast(1)).getSingleLineComments();
    MessageChunk messageChunk = actualProcessAndSplitCompletionResult[0];
    assertTrue(messageChunk instanceof Text);
    assertEquals("Format Generated Query", messageChunk.toRawString());
    assertEquals("Format Generated Query", ((Text) messageChunk).text());
    assertNull(messageChunk.getCallback());
    assertEquals(1, actualProcessAndSplitCompletionResult.length);
  }

  /**
   * Test {@link AITextUtils#processAndSplitCompletion(DBRProgressMonitor, AIDatabaseContext,
   * AISqlFormatter, String)}.
   *
   * <p>Method under test: {@link AITextUtils#processAndSplitCompletion(DBRProgressMonitor,
   * AIDatabaseContext, AISqlFormatter, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "MessageChunk[] AITextUtils.processAndSplitCompletion(DBRProgressMonitor, AIDatabaseContext, AISqlFormatter, String)"
  })
  public void testProcessAndSplitCompletion3() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getMultiLineComments()).thenReturn(new Pair<>(null, "Second"));
    when(sqlDialect.getSingleLineComments()).thenReturn(new String[] {"Single Line Comments"});

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(sqlDialect);

    DBCExecutionContext executionContext = mock(DBCExecutionContext.class);
    when(executionContext.getDataSource()).thenReturn(dbpDataSource);
    when(executionContext.getContextDefaults()).thenReturn(new VoidExecutionContextDefaults());
    DBSLogicalDataSource dataSource = new DBSLogicalDataSource(mock(DBPDataSourceContainer.class));

    Builder builder = new Builder(dataSource);
    AIDatabaseContext context =
        builder
            .setCustomEntities(new ArrayList<>())
            .setExecutionContext(executionContext)
            .setScope(AIDatabaseScope.CURRENT_SCHEMA)
            .build();

    AISqlFormatter sqlFormatter = mock(AISqlFormatter.class);
    when(sqlFormatter.formatGeneratedQuery(
            Mockito.<DBRProgressMonitor>any(), Mockito.<DBPDataSource>any(), Mockito.<String>any()))
        .thenReturn("Format Generated Query");

    // Act
    MessageChunk[] actualProcessAndSplitCompletionResult =
        AITextUtils.processAndSplitCompletion(monitor, context, sqlFormatter, "Text");

    // Assert
    verify(dbpDataSource).getSQLDialect();
    verify(sqlFormatter)
        .formatGeneratedQuery(isA(DBRProgressMonitor.class), isA(DBPDataSource.class), eq("Text"));
    verify(executionContext).getContextDefaults();
    verify(executionContext, atLeast(1)).getDataSource();
    verify(sqlDialect).getMultiLineComments();
    verify(sqlDialect, atLeast(1)).getSingleLineComments();
    MessageChunk messageChunk = actualProcessAndSplitCompletionResult[0];
    assertTrue(messageChunk instanceof Text);
    assertEquals("Format Generated Query", messageChunk.toRawString());
    assertEquals("Format Generated Query", ((Text) messageChunk).text());
    assertNull(messageChunk.getCallback());
    assertEquals(1, actualProcessAndSplitCompletionResult.length);
  }

  /**
   * Test {@link AITextUtils#processAndSplitCompletion(DBRProgressMonitor, AIDatabaseContext,
   * AISqlFormatter, String)}.
   *
   * <p>Method under test: {@link AITextUtils#processAndSplitCompletion(DBRProgressMonitor,
   * AIDatabaseContext, AISqlFormatter, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "MessageChunk[] AITextUtils.processAndSplitCompletion(DBRProgressMonitor, AIDatabaseContext, AISqlFormatter, String)"
  })
  public void testProcessAndSplitCompletion4() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    SQLDialect sqlDialect = mock(SQLDialect.class);
    Pair<String, String> pair = new Pair<>("First", null);
    when(sqlDialect.getMultiLineComments()).thenReturn(pair);
    when(sqlDialect.getSingleLineComments()).thenReturn(new String[] {"Single Line Comments"});

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(sqlDialect);

    DBCExecutionContext executionContext = mock(DBCExecutionContext.class);
    when(executionContext.getDataSource()).thenReturn(dbpDataSource);
    when(executionContext.getContextDefaults()).thenReturn(new VoidExecutionContextDefaults());
    DBSLogicalDataSource dataSource = new DBSLogicalDataSource(mock(DBPDataSourceContainer.class));

    Builder builder = new Builder(dataSource);
    AIDatabaseContext context =
        builder
            .setCustomEntities(new ArrayList<>())
            .setExecutionContext(executionContext)
            .setScope(AIDatabaseScope.CURRENT_SCHEMA)
            .build();

    AISqlFormatter sqlFormatter = mock(AISqlFormatter.class);
    when(sqlFormatter.formatGeneratedQuery(
            Mockito.<DBRProgressMonitor>any(), Mockito.<DBPDataSource>any(), Mockito.<String>any()))
        .thenReturn("Format Generated Query");

    // Act
    MessageChunk[] actualProcessAndSplitCompletionResult =
        AITextUtils.processAndSplitCompletion(monitor, context, sqlFormatter, "Text");

    // Assert
    verify(dbpDataSource).getSQLDialect();
    verify(sqlFormatter)
        .formatGeneratedQuery(isA(DBRProgressMonitor.class), isA(DBPDataSource.class), eq("Text"));
    verify(executionContext).getContextDefaults();
    verify(executionContext, atLeast(1)).getDataSource();
    verify(sqlDialect).getMultiLineComments();
    verify(sqlDialect, atLeast(1)).getSingleLineComments();
    MessageChunk messageChunk = actualProcessAndSplitCompletionResult[0];
    assertTrue(messageChunk instanceof Text);
    assertEquals("Format Generated Query", messageChunk.toRawString());
    assertEquals("Format Generated Query", ((Text) messageChunk).text());
    assertNull(messageChunk.getCallback());
    assertEquals(1, actualProcessAndSplitCompletionResult.length);
  }

  /**
   * Test {@link AITextUtils#processAndSplitCompletion(DBRProgressMonitor, AIDatabaseContext,
   * AISqlFormatter, String)}.
   *
   * <p>Method under test: {@link AITextUtils#processAndSplitCompletion(DBRProgressMonitor,
   * AIDatabaseContext, AISqlFormatter, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "MessageChunk[] AITextUtils.processAndSplitCompletion(DBRProgressMonitor, AIDatabaseContext, AISqlFormatter, String)"
  })
  public void testProcessAndSplitCompletion5() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getSingleLineComments()).thenReturn(new String[] {"```", "Format"});

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(sqlDialect);

    DBCExecutionContext executionContext = mock(DBCExecutionContext.class);
    when(executionContext.getDataSource()).thenReturn(dbpDataSource);
    when(executionContext.getContextDefaults()).thenReturn(new VoidExecutionContextDefaults());
    DBSLogicalDataSource dataSource = new DBSLogicalDataSource(mock(DBPDataSourceContainer.class));

    Builder builder = new Builder(dataSource);
    AIDatabaseContext context =
        builder
            .setCustomEntities(new ArrayList<>())
            .setExecutionContext(executionContext)
            .setScope(AIDatabaseScope.CURRENT_SCHEMA)
            .build();

    AISqlFormatter sqlFormatter = mock(AISqlFormatter.class);
    when(sqlFormatter.formatGeneratedQuery(
            Mockito.<DBRProgressMonitor>any(), Mockito.<DBPDataSource>any(), Mockito.<String>any()))
        .thenReturn("Format Generated Query");

    // Act
    MessageChunk[] actualProcessAndSplitCompletionResult =
        AITextUtils.processAndSplitCompletion(monitor, context, sqlFormatter, "Text");

    // Assert
    verify(dbpDataSource).getSQLDialect();
    verify(sqlFormatter)
        .formatGeneratedQuery(isA(DBRProgressMonitor.class), isA(DBPDataSource.class), eq("Text"));
    verify(executionContext).getContextDefaults();
    verify(executionContext, atLeast(1)).getDataSource();
    verify(sqlDialect).getSingleLineComments();
    MessageChunk messageChunk = actualProcessAndSplitCompletionResult[0];
    assertTrue(messageChunk instanceof Text);
    assertEquals("Format Generated Query", messageChunk.toRawString());
    assertEquals("Format Generated Query", ((Text) messageChunk).text());
    assertNull(messageChunk.getCallback());
    assertEquals(1, actualProcessAndSplitCompletionResult.length);
  }

  /**
   * Test {@link AITextUtils#processAndSplitCompletion(DBRProgressMonitor, AIDatabaseContext,
   * AISqlFormatter, String)}.
   *
   * <p>Method under test: {@link AITextUtils#processAndSplitCompletion(DBRProgressMonitor,
   * AIDatabaseContext, AISqlFormatter, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "MessageChunk[] AITextUtils.processAndSplitCompletion(DBRProgressMonitor, AIDatabaseContext, AISqlFormatter, String)"
  })
  public void testProcessAndSplitCompletion6() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getMultiLineComments()).thenReturn(new Pair<>("Format", ""));
    when(sqlDialect.getSingleLineComments()).thenReturn(new String[] {"Single Line Comments"});

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(sqlDialect);

    DBCExecutionContext executionContext = mock(DBCExecutionContext.class);
    when(executionContext.getDataSource()).thenReturn(dbpDataSource);
    when(executionContext.getContextDefaults()).thenReturn(new VoidExecutionContextDefaults());
    DBSLogicalDataSource dataSource = new DBSLogicalDataSource(mock(DBPDataSourceContainer.class));

    Builder builder = new Builder(dataSource);
    AIDatabaseContext context =
        builder
            .setCustomEntities(new ArrayList<>())
            .setExecutionContext(executionContext)
            .setScope(AIDatabaseScope.CURRENT_SCHEMA)
            .build();

    AISqlFormatter sqlFormatter = mock(AISqlFormatter.class);
    when(sqlFormatter.formatGeneratedQuery(
            Mockito.<DBRProgressMonitor>any(), Mockito.<DBPDataSource>any(), Mockito.<String>any()))
        .thenReturn("Format Generated Query");

    // Act
    MessageChunk[] actualProcessAndSplitCompletionResult =
        AITextUtils.processAndSplitCompletion(monitor, context, sqlFormatter, "Text");

    // Assert
    verify(dbpDataSource).getSQLDialect();
    verify(sqlFormatter)
        .formatGeneratedQuery(isA(DBRProgressMonitor.class), isA(DBPDataSource.class), eq("Text"));
    verify(executionContext).getContextDefaults();
    verify(executionContext, atLeast(1)).getDataSource();
    verify(sqlDialect).getMultiLineComments();
    verify(sqlDialect, atLeast(1)).getSingleLineComments();
    MessageChunk messageChunk = actualProcessAndSplitCompletionResult[0];
    assertTrue(messageChunk instanceof Text);
    assertEquals("Format Generated Query", messageChunk.toRawString());
    assertEquals("Format Generated Query", ((Text) messageChunk).text());
    assertNull(messageChunk.getCallback());
    assertEquals(1, actualProcessAndSplitCompletionResult.length);
  }

  /**
   * Test {@link AITextUtils#processAndSplitCompletion(DBRProgressMonitor, AIDatabaseContext,
   * AISqlFormatter, String)}.
   *
   * <ul>
   *   <li>Given {@link SQLDialect} {@link SQLDialect#getMultiLineComments()} return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link AITextUtils#processAndSplitCompletion(DBRProgressMonitor,
   * AIDatabaseContext, AISqlFormatter, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "MessageChunk[] AITextUtils.processAndSplitCompletion(DBRProgressMonitor, AIDatabaseContext, AISqlFormatter, String)"
  })
  public void testProcessAndSplitCompletion_givenSQLDialectGetMultiLineCommentsReturnNull()
      throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getMultiLineComments()).thenReturn(null);
    when(sqlDialect.getSingleLineComments()).thenReturn(new String[] {"Single Line Comments"});

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(sqlDialect);

    DBCExecutionContext executionContext = mock(DBCExecutionContext.class);
    when(executionContext.getDataSource()).thenReturn(dbpDataSource);
    when(executionContext.getContextDefaults()).thenReturn(new VoidExecutionContextDefaults());
    DBSLogicalDataSource dataSource = new DBSLogicalDataSource(mock(DBPDataSourceContainer.class));

    Builder builder = new Builder(dataSource);
    AIDatabaseContext context =
        builder
            .setCustomEntities(new ArrayList<>())
            .setExecutionContext(executionContext)
            .setScope(AIDatabaseScope.CURRENT_SCHEMA)
            .build();

    AISqlFormatter sqlFormatter = mock(AISqlFormatter.class);
    when(sqlFormatter.formatGeneratedQuery(
            Mockito.<DBRProgressMonitor>any(), Mockito.<DBPDataSource>any(), Mockito.<String>any()))
        .thenReturn("Format Generated Query");

    // Act
    MessageChunk[] actualProcessAndSplitCompletionResult =
        AITextUtils.processAndSplitCompletion(monitor, context, sqlFormatter, "Text");

    // Assert
    verify(dbpDataSource).getSQLDialect();
    verify(sqlFormatter)
        .formatGeneratedQuery(isA(DBRProgressMonitor.class), isA(DBPDataSource.class), eq("Text"));
    verify(executionContext).getContextDefaults();
    verify(executionContext, atLeast(1)).getDataSource();
    verify(sqlDialect).getMultiLineComments();
    verify(sqlDialect, atLeast(1)).getSingleLineComments();
    MessageChunk messageChunk = actualProcessAndSplitCompletionResult[0];
    assertTrue(messageChunk instanceof Text);
    assertEquals("Format Generated Query", messageChunk.toRawString());
    assertEquals("Format Generated Query", ((Text) messageChunk).text());
    assertNull(messageChunk.getCallback());
    assertEquals(1, actualProcessAndSplitCompletionResult.length);
  }

  /**
   * Test {@link AITextUtils#processAndSplitCompletion(DBRProgressMonitor, AIDatabaseContext,
   * AISqlFormatter, String)}.
   *
   * <ul>
   *   <li>Given {@code SELECT}.
   *   <li>Then first element return {@link MessageChunk.Code}.
   * </ul>
   *
   * <p>Method under test: {@link AITextUtils#processAndSplitCompletion(DBRProgressMonitor,
   * AIDatabaseContext, AISqlFormatter, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "MessageChunk[] AITextUtils.processAndSplitCompletion(DBRProgressMonitor, AIDatabaseContext, AISqlFormatter, String)"
  })
  public void testProcessAndSplitCompletion_givenSelect_thenFirstElementReturnCode()
      throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getMultiLineComments()).thenReturn(new Pair<>("First", "Second"));
    when(sqlDialect.getSingleLineComments()).thenReturn(new String[] {"Single Line Comments"});

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(sqlDialect);

    DBCExecutionContext executionContext = mock(DBCExecutionContext.class);
    when(executionContext.getDataSource()).thenReturn(dbpDataSource);
    when(executionContext.getContextDefaults()).thenReturn(new VoidExecutionContextDefaults());
    DBSLogicalDataSource dataSource = new DBSLogicalDataSource(mock(DBPDataSourceContainer.class));

    Builder builder = new Builder(dataSource);
    AIDatabaseContext context =
        builder
            .setCustomEntities(new ArrayList<>())
            .setExecutionContext(executionContext)
            .setScope(AIDatabaseScope.CURRENT_SCHEMA)
            .build();

    AISqlFormatter sqlFormatter = mock(AISqlFormatter.class);
    when(sqlFormatter.formatGeneratedQuery(
            Mockito.<DBRProgressMonitor>any(), Mockito.<DBPDataSource>any(), Mockito.<String>any()))
        .thenReturn("SELECT");

    // Act
    MessageChunk[] actualProcessAndSplitCompletionResult =
        AITextUtils.processAndSplitCompletion(monitor, context, sqlFormatter, "Text");

    // Assert
    verify(dbpDataSource).getSQLDialect();
    verify(sqlFormatter)
        .formatGeneratedQuery(isA(DBRProgressMonitor.class), isA(DBPDataSource.class), eq("Text"));
    verify(executionContext).getContextDefaults();
    verify(executionContext, atLeast(1)).getDataSource();
    verify(sqlDialect).getMultiLineComments();
    verify(sqlDialect, atLeast(1)).getSingleLineComments();
    MessageChunk messageChunk = actualProcessAndSplitCompletionResult[0];
    assertTrue(messageChunk instanceof Code);
    assertEquals("SELECT", ((Code) messageChunk).text());
    assertNull(messageChunk.getCallback());
    assertEquals(1, actualProcessAndSplitCompletionResult.length);
    assertEquals(AITextUtils.SQL_LANGUAGE_ID, ((Code) messageChunk).language());
  }

  /**
   * Test {@link AITextUtils#processAndSplitCompletion(DBRProgressMonitor, AIDatabaseContext,
   * AISqlFormatter, String)}.
   *
   * <ul>
   *   <li>Then return array length is zero.
   * </ul>
   *
   * <p>Method under test: {@link AITextUtils#processAndSplitCompletion(DBRProgressMonitor,
   * AIDatabaseContext, AISqlFormatter, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "MessageChunk[] AITextUtils.processAndSplitCompletion(DBRProgressMonitor, AIDatabaseContext, AISqlFormatter, String)"
  })
  public void testProcessAndSplitCompletion_thenReturnArrayLengthIsZero() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(mock(SQLDialect.class));

    DBCExecutionContext executionContext = mock(DBCExecutionContext.class);
    when(executionContext.getDataSource()).thenReturn(dbpDataSource);
    when(executionContext.getContextDefaults()).thenReturn(new VoidExecutionContextDefaults());
    DBSLogicalDataSource dataSource = new DBSLogicalDataSource(mock(DBPDataSourceContainer.class));

    Builder builder = new Builder(dataSource);
    AIDatabaseContext context =
        builder
            .setCustomEntities(new ArrayList<>())
            .setExecutionContext(executionContext)
            .setScope(AIDatabaseScope.CURRENT_SCHEMA)
            .build();

    AISqlFormatter sqlFormatter = mock(AISqlFormatter.class);
    when(sqlFormatter.formatGeneratedQuery(
            Mockito.<DBRProgressMonitor>any(), Mockito.<DBPDataSource>any(), Mockito.<String>any()))
        .thenReturn("```");

    // Act
    MessageChunk[] actualProcessAndSplitCompletionResult =
        AITextUtils.processAndSplitCompletion(monitor, context, sqlFormatter, "Text");

    // Assert
    verify(dbpDataSource).getSQLDialect();
    verify(sqlFormatter)
        .formatGeneratedQuery(isA(DBRProgressMonitor.class), isA(DBPDataSource.class), eq("Text"));
    verify(executionContext).getContextDefaults();
    verify(executionContext, atLeast(1)).getDataSource();
    assertEquals(0, actualProcessAndSplitCompletionResult.length);
  }

  /**
   * Test {@link AITextUtils#processAndSplitCompletion(DBRProgressMonitor, AIDatabaseContext,
   * AISqlFormatter, String)}.
   *
   * <ul>
   *   <li>When {@link DBCExecutionContext} {@link DBCExecutionContext#getDataSource()} return
   *       {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link AITextUtils#processAndSplitCompletion(DBRProgressMonitor,
   * AIDatabaseContext, AISqlFormatter, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "MessageChunk[] AITextUtils.processAndSplitCompletion(DBRProgressMonitor, AIDatabaseContext, AISqlFormatter, String)"
  })
  public void testProcessAndSplitCompletion_whenDBCExecutionContextGetDataSourceReturnNull()
      throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBCExecutionContext executionContext = mock(DBCExecutionContext.class);
    when(executionContext.getDataSource()).thenReturn(null);
    when(executionContext.getContextDefaults()).thenReturn(new VoidExecutionContextDefaults());
    DBSLogicalDataSource dataSource = new DBSLogicalDataSource(mock(DBPDataSourceContainer.class));

    Builder builder = new Builder(dataSource);
    AIDatabaseContext context =
        builder
            .setCustomEntities(new ArrayList<>())
            .setExecutionContext(executionContext)
            .setScope(AIDatabaseScope.CURRENT_SCHEMA)
            .build();

    AISqlFormatter sqlFormatter = mock(AISqlFormatter.class);
    when(sqlFormatter.formatGeneratedQuery(
            Mockito.<DBRProgressMonitor>any(), Mockito.<DBPDataSource>any(), Mockito.<String>any()))
        .thenReturn("Format Generated Query");

    // Act
    MessageChunk[] actualProcessAndSplitCompletionResult =
        AITextUtils.processAndSplitCompletion(monitor, context, sqlFormatter, "Text");

    // Assert
    verify(sqlFormatter).formatGeneratedQuery(isA(DBRProgressMonitor.class), isNull(), eq("Text"));
    verify(executionContext).getContextDefaults();
    verify(executionContext, atLeast(1)).getDataSource();
    MessageChunk messageChunk = actualProcessAndSplitCompletionResult[0];
    assertTrue(messageChunk instanceof Text);
    assertEquals("Format Generated Query", messageChunk.toRawString());
    assertEquals("Format Generated Query", ((Text) messageChunk).text());
    assertNull(messageChunk.getCallback());
    assertEquals(1, actualProcessAndSplitCompletionResult.length);
  }
}
