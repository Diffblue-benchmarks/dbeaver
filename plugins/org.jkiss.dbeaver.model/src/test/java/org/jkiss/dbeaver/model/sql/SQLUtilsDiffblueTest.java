package org.jkiss.dbeaver.model.sql;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.anyChar;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.DBPDataKind;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.model.DBPEvent;
import org.jkiss.dbeaver.model.DBPIdentifierCase;
import org.jkiss.dbeaver.model.data.DBDAttributeBindingCustom;
import org.jkiss.dbeaver.model.data.DBDAttributeConstraint;
import org.jkiss.dbeaver.model.data.DBDContent;
import org.jkiss.dbeaver.model.data.DBDDataFilter;
import org.jkiss.dbeaver.model.data.DBDDisplayFormat;
import org.jkiss.dbeaver.model.data.DBDValueHandler;
import org.jkiss.dbeaver.model.edit.DBEPersistAction;
import org.jkiss.dbeaver.model.edit.DBEPersistAction.ActionType;
import org.jkiss.dbeaver.model.exec.DBCExecutionContext;
import org.jkiss.dbeaver.model.exec.DBCSession;
import org.jkiss.dbeaver.model.impl.SimpleTypedObject;
import org.jkiss.dbeaver.model.impl.data.DBDDocumentXML;
import org.jkiss.dbeaver.model.impl.data.DefaultValueHandler;
import org.jkiss.dbeaver.model.impl.data.StringContent;
import org.jkiss.dbeaver.model.impl.edit.SQLDatabasePersistAction;
import org.jkiss.dbeaver.model.impl.sql.AbstractSQLDialect;
import org.jkiss.dbeaver.model.impl.sql.BasicSQLDialect;
import org.jkiss.dbeaver.model.runtime.DBRFinder;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.jkiss.dbeaver.model.struct.DBSDocumentConstraint;
import org.jkiss.dbeaver.model.struct.DBSDocumentContainer;
import org.jkiss.dbeaver.model.struct.DBSEntity;
import org.jkiss.dbeaver.model.struct.DBSObject;
import org.jkiss.dbeaver.model.struct.DBSTypedObject;
import org.jkiss.dbeaver.model.virtual.DBVContainer;
import org.jkiss.dbeaver.model.virtual.DBVEntity;
import org.jkiss.dbeaver.model.virtual.DBVEntityAttribute;
import org.jkiss.dbeaver.model.virtual.DBVEntityForeignKey;
import org.jkiss.dbeaver.model.virtual.DBVModel;
import org.jkiss.utils.Pair;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class SQLUtilsDiffblueTest {
  /**
   * Test {@link SQLUtils#stripTransformations(String)}.
   *
   * <p>Method under test: {@link SQLUtils#stripTransformations(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLUtils.stripTransformations(String)"})
  public void testStripTransformations() {
    // Arrange, Act and Assert
    assertEquals("Query", SQLUtils.stripTransformations("Query"));
  }

  /**
   * Test {@link SQLUtils#isCommentLine(SQLDialect, String)}.
   *
   * <ul>
   *   <li>Given array of {@link String} with empty string.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#isCommentLine(SQLDialect, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLUtils.isCommentLine(SQLDialect, String)"})
  public void testIsCommentLine_givenArrayOfStringWithEmptyString_thenReturnTrue() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getSingleLineComments()).thenReturn(new String[] {""});

    // Act
    boolean actualIsCommentLineResult = SQLUtils.isCommentLine(dialect, "Line");

    // Assert
    verify(dialect).getSingleLineComments();
    assertTrue(actualIsCommentLineResult);
  }

  /**
   * Test {@link SQLUtils#isCommentLine(SQLDialect, String)}.
   *
   * <ul>
   *   <li>When {@link BasicSQLDialect#INSTANCE}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#isCommentLine(SQLDialect, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLUtils.isCommentLine(SQLDialect, String)"})
  public void testIsCommentLine_whenInstance_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(SQLUtils.isCommentLine(BasicSQLDialect.INSTANCE, "Line"));
  }

  /**
   * Test {@link SQLUtils#stripComments(SQLDialect, String)} with {@code dialect}, {@code query}.
   *
   * <p>Method under test: {@link SQLUtils#stripComments(SQLDialect, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLUtils.stripComments(SQLDialect, String)"})
  public void testStripCommentsWithDialectQuery() {
    // Arrange
    Pair<String, String> pair = AbstractSQLDialect.IN_CLAUSE_PARENTHESES;
    pair.setFirst("Multi Line Comments");

    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getMultiLineComments()).thenReturn(pair);
    when(dialect.getSingleLineComments()).thenReturn(new String[] {"Single Line Comments"});

    // Act
    String actualStripCommentsResult = SQLUtils.stripComments(dialect, "Query");

    // Assert
    verify(dialect).getMultiLineComments();
    verify(dialect).getSingleLineComments();
    assertEquals("Query", actualStripCommentsResult);
  }

  /**
   * Test {@link SQLUtils#stripComments(SQLDialect, String)} with {@code dialect}, {@code query}.
   *
   * <ul>
   *   <li>Given array of {@link String} with {@code foo} and empty string.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#stripComments(SQLDialect, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLUtils.stripComments(SQLDialect, String)"})
  public void testStripCommentsWithDialectQuery_givenArrayOfStringWithFooAndEmptyString() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getMultiLineComments()).thenReturn(null);
    when(dialect.getSingleLineComments()).thenReturn(new String[] {"foo", ""});

    // Act
    String actualStripCommentsResult = SQLUtils.stripComments(dialect, "Query");

    // Assert
    verify(dialect).getMultiLineComments();
    verify(dialect).getSingleLineComments();
    assertEquals("", actualStripCommentsResult);
  }

  /**
   * Test {@link SQLUtils#stripComments(SQLDialect, String)} with {@code dialect}, {@code query}.
   *
   * <ul>
   *   <li>Given {@code null}.
   *   <li>Then return {@code Query}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#stripComments(SQLDialect, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLUtils.stripComments(SQLDialect, String)"})
  public void testStripCommentsWithDialectQuery_givenNull_thenReturnQuery() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getMultiLineComments()).thenReturn(null);
    when(dialect.getSingleLineComments()).thenReturn(new String[] {"Single Line Comments"});

    // Act
    String actualStripCommentsResult = SQLUtils.stripComments(dialect, "Query");

    // Assert
    verify(dialect).getMultiLineComments();
    verify(dialect).getSingleLineComments();
    assertEquals("Query", actualStripCommentsResult);
  }

  /**
   * Test {@link SQLUtils#stripComments(SQLDialect, String)} with {@code dialect}, {@code query}.
   *
   * <ul>
   *   <li>When empty string.
   *   <li>Then return empty string.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#stripComments(SQLDialect, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLUtils.stripComments(SQLDialect, String)"})
  public void testStripCommentsWithDialectQuery_whenEmptyString_thenReturnEmptyString() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getMultiLineComments()).thenReturn(null);
    when(dialect.getSingleLineComments()).thenReturn(new String[] {"Single Line Comments"});

    // Act
    String actualStripCommentsResult = SQLUtils.stripComments(dialect, "");

    // Assert
    verify(dialect).getMultiLineComments();
    verify(dialect).getSingleLineComments();
    assertEquals("", actualStripCommentsResult);
  }

  /**
   * Test {@link SQLUtils#stripComments(SQLDialect, String)} with {@code dialect}, {@code query}.
   *
   * <ul>
   *   <li>When {@link BasicSQLDialect#INSTANCE}.
   *   <li>Then return {@code Query}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#stripComments(SQLDialect, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLUtils.stripComments(SQLDialect, String)"})
  public void testStripCommentsWithDialectQuery_whenInstance_thenReturnQuery() {
    // Arrange, Act and Assert
    assertEquals("Query", SQLUtils.stripComments(BasicSQLDialect.INSTANCE, "Query"));
  }

  /**
   * Test {@link SQLUtils#stripComments(String, String, String, String[])} with {@code query},
   * {@code mlCommentStart}, {@code mlCommentEnd}, {@code slComments}.
   *
   * <p>Method under test: {@link SQLUtils#stripComments(String, String, String, String[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLUtils.stripComments(String, String, String, String[])"})
  public void testStripCommentsWithQueryMlCommentStartMlCommentEndSlComments() {
    // Arrange and Act
    String actualStripCommentsResult =
        SQLUtils.stripComments(
            "Query", "Ml Comment Start", "Ml Comment End", new String[] {"Sl Comments", ""});

    // Assert
    assertEquals("", actualStripCommentsResult);
  }

  /**
   * Test {@link SQLUtils#stripComments(String, String, String, String[])} with {@code query},
   * {@code mlCommentStart}, {@code mlCommentEnd}, {@code slComments}.
   *
   * <p>Method under test: {@link SQLUtils#stripComments(String, String, String, String[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLUtils.stripComments(String, String, String, String[])"})
  public void testStripCommentsWithQueryMlCommentStartMlCommentEndSlComments2() {
    // Arrange and Act
    String actualStripCommentsResult =
        SQLUtils.stripComments(
            "", "Ml Comment Start", "Ml Comment End", new String[] {"Sl Comments"});

    // Assert
    assertEquals("", actualStripCommentsResult);
  }

  /**
   * Test {@link SQLUtils#stripComments(String, String, String, String[])} with {@code query},
   * {@code mlCommentStart}, {@code mlCommentEnd}, {@code slComments}.
   *
   * <ul>
   *   <li>Then return {@code Query}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#stripComments(String, String, String, String[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLUtils.stripComments(String, String, String, String[])"})
  public void testStripCommentsWithQueryMlCommentStartMlCommentEndSlComments_thenReturnQuery() {
    // Arrange and Act
    String actualStripCommentsResult =
        SQLUtils.stripComments(
            "Query", "Ml Comment Start", "Ml Comment End", new String[] {"Sl Comments"});

    // Assert
    assertEquals("Query", actualStripCommentsResult);
  }

  /**
   * Test {@link SQLUtils#stripComments(String, String, String, String[])} with {@code query},
   * {@code mlCommentStart}, {@code mlCommentEnd}, {@code slComments}.
   *
   * <ul>
   *   <li>Then return {@code Query}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#stripComments(String, String, String, String[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLUtils.stripComments(String, String, String, String[])"})
  public void testStripCommentsWithQueryMlCommentStartMlCommentEndSlComments_thenReturnQuery2() {
    // Arrange and Act
    String actualStripCommentsResult =
        SQLUtils.stripComments("Query", null, "Ml Comment End", new String[] {"Sl Comments"});

    // Assert
    assertEquals("Query", actualStripCommentsResult);
  }

  /**
   * Test {@link SQLUtils#stripComments(String, String, String, String[])} with {@code query},
   * {@code mlCommentStart}, {@code mlCommentEnd}, {@code slComments}.
   *
   * <ul>
   *   <li>Then return {@code Query}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#stripComments(String, String, String, String[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLUtils.stripComments(String, String, String, String[])"})
  public void testStripCommentsWithQueryMlCommentStartMlCommentEndSlComments_thenReturnQuery3() {
    // Arrange and Act
    String actualStripCommentsResult =
        SQLUtils.stripComments("Query", "Ml Comment Start", null, new String[] {"Sl Comments"});

    // Assert
    assertEquals("Query", actualStripCommentsResult);
  }

  /**
   * Test {@link SQLUtils#extractComments(SQLDialect, String)}.
   *
   * <ul>
   *   <li>Given array of {@link String} with empty string.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#extractComments(SQLDialect, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String[] SQLUtils.extractComments(SQLDialect, String)"})
  public void testExtractComments_givenArrayOfStringWithEmptyString() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getSingleLineComments()).thenReturn(new String[] {""});
    when(dialect.getMultiLineComments()).thenReturn(AbstractSQLDialect.IN_CLAUSE_PARENTHESES);

    // Act
    String[] actualExtractCommentsResult = SQLUtils.extractComments(dialect, "Query");

    // Assert
    verify(dialect).getMultiLineComments();
    verify(dialect).getSingleLineComments();
    assertArrayEquals(new String[] {"Query"}, actualExtractCommentsResult);
  }

  /**
   * Test {@link SQLUtils#extractComments(SQLDialect, String)}.
   *
   * <ul>
   *   <li>Given array of {@link String} with {@code foo} and {@code 42}.
   *   <li>Then return array of {@link String} with {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#extractComments(SQLDialect, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String[] SQLUtils.extractComments(SQLDialect, String)"})
  public void testExtractComments_givenArrayOfStringWithFooAnd42_thenReturnArrayOfStringWith42() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getSingleLineComments()).thenReturn(new String[] {"foo", "42"});
    when(dialect.getMultiLineComments()).thenReturn(AbstractSQLDialect.IN_CLAUSE_PARENTHESES);

    // Act
    String[] actualExtractCommentsResult = SQLUtils.extractComments(dialect, "42");

    // Assert
    verify(dialect).getMultiLineComments();
    verify(dialect).getSingleLineComments();
    assertArrayEquals(new String[] {"42"}, actualExtractCommentsResult);
  }

  /**
   * Test {@link SQLUtils#extractComments(SQLDialect, String)}.
   *
   * <ul>
   *   <li>Given {@link AbstractSQLDialect#IN_CLAUSE_PARENTHESES} First is empty string.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#extractComments(SQLDialect, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String[] SQLUtils.extractComments(SQLDialect, String)"})
  public void testExtractComments_givenIn_clause_parenthesesFirstIsEmptyString() {
    // Arrange
    Pair<String, String> pair = AbstractSQLDialect.IN_CLAUSE_PARENTHESES;
    pair.setFirst("");

    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getSingleLineComments()).thenReturn(null);
    when(dialect.getMultiLineComments()).thenReturn(pair);

    // Act
    String[] actualExtractCommentsResult = SQLUtils.extractComments(dialect, "Query");

    // Assert
    verify(dialect).getMultiLineComments();
    verify(dialect).getSingleLineComments();
    assertArrayEquals(new String[] {"Query"}, actualExtractCommentsResult);
  }

  /**
   * Test {@link SQLUtils#extractComments(SQLDialect, String)}.
   *
   * <ul>
   *   <li>Given {@link AbstractSQLDialect#IN_CLAUSE_PARENTHESES} Second is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#extractComments(SQLDialect, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String[] SQLUtils.extractComments(SQLDialect, String)"})
  public void testExtractComments_givenIn_clause_parenthesesSecondIsNull() {
    // Arrange
    Pair<String, String> pair = AbstractSQLDialect.IN_CLAUSE_PARENTHESES;
    pair.setSecond(null);
    pair.setFirst("");

    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getSingleLineComments()).thenReturn(null);
    when(dialect.getMultiLineComments()).thenReturn(pair);

    // Act
    String[] actualExtractCommentsResult = SQLUtils.extractComments(dialect, "Query");

    // Assert
    verify(dialect).getMultiLineComments();
    verify(dialect).getSingleLineComments();
    assertArrayEquals(new String[] {"Query"}, actualExtractCommentsResult);
  }

  /**
   * Test {@link SQLUtils#extractComments(SQLDialect, String)}.
   *
   * <ul>
   *   <li>Then return array of {@link String} with empty string.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#extractComments(SQLDialect, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String[] SQLUtils.extractComments(SQLDialect, String)"})
  public void testExtractComments_thenReturnArrayOfStringWithEmptyString() {
    // Arrange
    Pair<String, String> pair = new Pair<>("First", "42");
    pair.setFirst("");

    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getSingleLineComments()).thenReturn(null);
    when(dialect.getMultiLineComments()).thenReturn(pair);

    // Act
    String[] actualExtractCommentsResult = SQLUtils.extractComments(dialect, "42");

    // Assert
    verify(dialect).getMultiLineComments();
    verify(dialect).getSingleLineComments();
    assertArrayEquals(new String[] {""}, actualExtractCommentsResult);
  }

  /**
   * Test {@link SQLUtils#extractComments(SQLDialect, String)}.
   *
   * <ul>
   *   <li>When empty string.
   *   <li>Then return array length is zero.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#extractComments(SQLDialect, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String[] SQLUtils.extractComments(SQLDialect, String)"})
  public void testExtractComments_whenEmptyString_thenReturnArrayLengthIsZero() {
    // Arrange, Act and Assert
    assertEquals(0, SQLUtils.extractComments(BasicSQLDialect.INSTANCE, "").length);
  }

  /**
   * Test {@link SQLUtils#extractComments(SQLDialect, String)}.
   *
   * <ul>
   *   <li>When {@link BasicSQLDialect#INSTANCE}.
   *   <li>Then return array length is zero.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#extractComments(SQLDialect, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String[] SQLUtils.extractComments(SQLDialect, String)"})
  public void testExtractComments_whenInstance_thenReturnArrayLengthIsZero() {
    // Arrange, Act and Assert
    assertEquals(0, SQLUtils.extractComments(BasicSQLDialect.INSTANCE, "Query").length);
  }

  /**
   * Test {@link SQLUtils#extractComments(SQLDialect, String)}.
   *
   * <ul>
   *   <li>When {@link SQLDialect} {@link SQLDialect#getMultiLineComments()} return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#extractComments(SQLDialect, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String[] SQLUtils.extractComments(SQLDialect, String)"})
  public void testExtractComments_whenSQLDialectGetMultiLineCommentsReturnNull() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getSingleLineComments()).thenReturn(null);
    when(dialect.getMultiLineComments()).thenReturn(null);

    // Act
    String[] actualExtractCommentsResult = SQLUtils.extractComments(dialect, "Query");

    // Assert
    verify(dialect).getMultiLineComments();
    verify(dialect).getSingleLineComments();
    assertEquals(0, actualExtractCommentsResult.length);
  }

  /**
   * Test {@link SQLUtils#splitFilter(String)}.
   *
   * <ul>
   *   <li>When empty string.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#splitFilter(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List SQLUtils.splitFilter(String)"})
  public void testSplitFilter_whenEmptyString_thenReturnEmpty() {
    // Arrange and Act
    List<String> actualSplitFilterResult = SQLUtils.splitFilter("");

    // Assert
    assertTrue(actualSplitFilterResult.isEmpty());
  }

  /**
   * Test {@link SQLUtils#splitFilter(String)}.
   *
   * <ul>
   *   <li>When {@code Filter}.
   *   <li>Then return size is one.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#splitFilter(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List SQLUtils.splitFilter(String)"})
  public void testSplitFilter_whenFilter_thenReturnSizeIsOne() {
    // Arrange and Act
    List<String> actualSplitFilterResult = SQLUtils.splitFilter("Filter");

    // Assert
    assertEquals(1, actualSplitFilterResult.size());
    assertEquals("Filter", actualSplitFilterResult.get(0));
  }

  /**
   * Test {@link SQLUtils#splitFilter(String)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#splitFilter(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List SQLUtils.splitFilter(String)"})
  public void testSplitFilter_whenNull_thenReturnEmpty() {
    // Arrange and Act
    List<String> actualSplitFilterResult = SQLUtils.splitFilter(null);

    // Assert
    assertTrue(actualSplitFilterResult.isEmpty());
  }

  /**
   * Test {@link SQLUtils#matchesAnyLike(String, Collection)}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>When {@link ArrayList#ArrayList()} add {@code 42}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#matchesAnyLike(String, Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLUtils.matchesAnyLike(String, Collection)"})
  public void testMatchesAnyLike_given42_whenArrayListAdd42_thenReturnFalse() {
    // Arrange
    ArrayList<String> likes = new ArrayList<>();
    likes.add("42");
    likes.add("foo");

    // Act and Assert
    assertFalse(SQLUtils.matchesAnyLike("String", likes));
  }

  /**
   * Test {@link SQLUtils#matchesAnyLike(String, Collection)}.
   *
   * <ul>
   *   <li>Given {@code Likes}.
   *   <li>When {@code likes}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#matchesAnyLike(String, Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLUtils.matchesAnyLike(String, Collection)"})
  public void testMatchesAnyLike_givenLikes_whenLikes_thenReturnTrue() {
    // Arrange
    LinkedHashSet<String> likes = new LinkedHashSet<>();
    likes.add("Likes");

    // Act and Assert
    assertTrue(SQLUtils.matchesAnyLike("likes", likes));
  }

  /**
   * Test {@link SQLUtils#matchesAnyLike(String, Collection)}.
   *
   * <ul>
   *   <li>Given {@code Likes}.
   *   <li>When {@link LinkedHashSet#LinkedHashSet()} add {@code Likes}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#matchesAnyLike(String, Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLUtils.matchesAnyLike(String, Collection)"})
  public void testMatchesAnyLike_givenLikes_whenLinkedHashSetAddLikes_thenReturnFalse() {
    // Arrange
    LinkedHashSet<String> likes = new LinkedHashSet<>();
    likes.add("Likes");

    // Act and Assert
    assertFalse(SQLUtils.matchesAnyLike("String", likes));
  }

  /**
   * Test {@link SQLUtils#matchesAnyLike(String, Collection)}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#matchesAnyLike(String, Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLUtils.matchesAnyLike(String, Collection)"})
  public void testMatchesAnyLike_whenArrayList_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(SQLUtils.matchesAnyLike("String", new ArrayList<>()));
  }

  /**
   * Test {@link SQLUtils#isLikePattern(String)}.
   *
   * <ul>
   *   <li>When {@code .*}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#isLikePattern(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLUtils.isLikePattern(String)"})
  public void testIsLikePattern_whenDotAsterisk_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(SQLUtils.isLikePattern(".*"));
  }

  /**
   * Test {@link SQLUtils#isLikePattern(String)}.
   *
   * <ul>
   *   <li>When {@code =?}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#isLikePattern(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLUtils.isLikePattern(String)"})
  public void testIsLikePattern_whenEqualsSignQuestionMark_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(SQLUtils.isLikePattern("=?"));
  }

  /**
   * Test {@link SQLUtils#isLikePattern(String)}.
   *
   * <ul>
   *   <li>When {@code Like}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#isLikePattern(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLUtils.isLikePattern(String)"})
  public void testIsLikePattern_whenLike_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(SQLUtils.isLikePattern("Like"));
  }

  /**
   * Test {@link SQLUtils#isLikePattern(String)}.
   *
   * <ul>
   *   <li>When {@code %}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#isLikePattern(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLUtils.isLikePattern(String)"})
  public void testIsLikePattern_whenPercentSign_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(SQLUtils.isLikePattern("%"));
  }

  /**
   * Test {@link SQLUtils#isLikePattern(String)}.
   *
   * <ul>
   *   <li>When {@code _}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#isLikePattern(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLUtils.isLikePattern(String)"})
  public void testIsLikePattern_whenUnderscore_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(SQLUtils.isLikePattern("_"));
  }

  /**
   * Test {@link SQLUtils#makeLikePattern(String)}.
   *
   * <ul>
   *   <li>When {@code .*}.
   *   <li>Then return {@code \..*}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#makeLikePattern(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLUtils.makeLikePattern(String)"})
  public void testMakeLikePattern_whenDotAsterisk_thenReturnBackslashDotDotAsterisk() {
    // Arrange, Act and Assert
    assertEquals("\\..*", SQLUtils.makeLikePattern(".*"));
  }

  /**
   * Test {@link SQLUtils#makeLikePattern(String)}.
   *
   * <ul>
   *   <li>When {@code =?}.
   *   <li>Then return {@code =.}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#makeLikePattern(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLUtils.makeLikePattern(String)"})
  public void testMakeLikePattern_whenEqualsSignQuestionMark_thenReturnEqualsSignDot() {
    // Arrange, Act and Assert
    assertEquals("=.", SQLUtils.makeLikePattern("=?"));
  }

  /**
   * Test {@link SQLUtils#makeLikePattern(String)}.
   *
   * <ul>
   *   <li>When {@code (}.
   *   <li>Then return {@code \(}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#makeLikePattern(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLUtils.makeLikePattern(String)"})
  public void testMakeLikePattern_whenLeftParenthesis_thenReturnBackslashLeftParenthesis() {
    // Arrange, Act and Assert
    assertEquals("\\(", SQLUtils.makeLikePattern("("));
  }

  /**
   * Test {@link SQLUtils#makeLikePattern(String)}.
   *
   * <ul>
   *   <li>When {@code Like}.
   *   <li>Then return {@code Like}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#makeLikePattern(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLUtils.makeLikePattern(String)"})
  public void testMakeLikePattern_whenLike_thenReturnLike() {
    // Arrange, Act and Assert
    assertEquals("Like", SQLUtils.makeLikePattern("Like"));
  }

  /**
   * Test {@link SQLUtils#makeLikePattern(String)}.
   *
   * <ul>
   *   <li>When {@code %}.
   *   <li>Then return {@code .*}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#makeLikePattern(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLUtils.makeLikePattern(String)"})
  public void testMakeLikePattern_whenPercentSign_thenReturnDotAsterisk() {
    // Arrange, Act and Assert
    assertEquals(".*", SQLUtils.makeLikePattern("%"));
  }

  /**
   * Test {@link SQLUtils#makeLikePattern(String)}.
   *
   * <ul>
   *   <li>When {@code )}.
   *   <li>Then return {@code \)}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#makeLikePattern(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLUtils.makeLikePattern(String)"})
  public void testMakeLikePattern_whenRightParenthesis_thenReturnBackslashRightParenthesis() {
    // Arrange, Act and Assert
    assertEquals("\\)", SQLUtils.makeLikePattern(")"));
  }

  /**
   * Test {@link SQLUtils#makeLikePattern(String)}.
   *
   * <ul>
   *   <li>When {@code _}.
   *   <li>Then return {@link SQLConstants#DOT}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#makeLikePattern(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLUtils.makeLikePattern(String)"})
  public void testMakeLikePattern_whenUnderscore_thenReturnDot() {
    // Arrange, Act and Assert
    assertEquals(SQLConstants.DOT, SQLUtils.makeLikePattern("_"));
  }

  /**
   * Test {@link SQLUtils#makeRegexFromLike(String)}.
   *
   * <ul>
   *   <li>When {@code Clause}.
   *   <li>Then return {@code ^Clause$}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#makeRegexFromLike(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLUtils.makeRegexFromLike(String)"})
  public void testMakeRegexFromLike_whenClause_thenReturnClause() {
    // Arrange, Act and Assert
    assertEquals("^Clause$", SQLUtils.makeRegexFromLike("Clause"));
  }

  /**
   * Test {@link SQLUtils#makeRegexFromLike(String)}.
   *
   * <ul>
   *   <li>When {@code Clause%}.
   *   <li>Then return {@code ^Clause}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#makeRegexFromLike(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLUtils.makeRegexFromLike(String)"})
  public void testMakeRegexFromLike_whenClause_thenReturnClause2() {
    // Arrange, Act and Assert
    assertEquals("^Clause", SQLUtils.makeRegexFromLike("Clause%"));
  }

  /**
   * Test {@link SQLUtils#makeRegexFromLike(String)}.
   *
   * <ul>
   *   <li>When {@code %}.
   *   <li>Then return empty string.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#makeRegexFromLike(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLUtils.makeRegexFromLike(String)"})
  public void testMakeRegexFromLike_whenPercentSign_thenReturnEmptyString() {
    // Arrange, Act and Assert
    assertEquals("", SQLUtils.makeRegexFromLike("%"));
  }

  /**
   * Test {@link SQLUtils#makeRegexFromLike(String)}.
   *
   * <ul>
   *   <li>When {@code _}.
   *   <li>Then return {@code ^.$}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#makeRegexFromLike(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLUtils.makeRegexFromLike(String)"})
  public void testMakeRegexFromLike_whenUnderscore_thenReturnCircumflexAccentDotDollarSign() {
    // Arrange, Act and Assert
    assertEquals("^.$", SQLUtils.makeRegexFromLike("_"));
  }

  /**
   * Test {@link SQLUtils#makeSQLLike(String)}.
   *
   * <p>Method under test: {@link SQLUtils#makeSQLLike(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLUtils.makeSQLLike(String)"})
  public void testMakeSQLLike() {
    // Arrange, Act and Assert
    assertEquals("Like", SQLUtils.makeSQLLike("Like"));
  }

  /**
   * Test {@link SQLUtils#makeGlobFromSqlLikePattern(String)}.
   *
   * <ul>
   *   <li>Then return {@code =\?}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#makeGlobFromSqlLikePattern(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLUtils.makeGlobFromSqlLikePattern(String)"})
  public void testMakeGlobFromSqlLikePattern_thenReturnEqualsSignBackslashQuestionMark() {
    // Arrange, Act and Assert
    assertEquals("=\\?", SQLUtils.makeGlobFromSqlLikePattern("=?"));
  }

  /**
   * Test {@link SQLUtils#makeGlobFromSqlLikePattern(String)}.
   *
   * <ul>
   *   <li>When {@code %}.
   *   <li>Then return {@link SQLConstants#ASTERISK}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#makeGlobFromSqlLikePattern(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLUtils.makeGlobFromSqlLikePattern(String)"})
  public void testMakeGlobFromSqlLikePattern_whenPercentSign_thenReturnAsterisk() {
    // Arrange, Act and Assert
    assertEquals(SQLConstants.ASTERISK, SQLUtils.makeGlobFromSqlLikePattern("%"));
  }

  /**
   * Test {@link SQLUtils#makeGlobFromSqlLikePattern(String)}.
   *
   * <ul>
   *   <li>When {@code Sql Like Pattern}.
   *   <li>Then return {@code Sql Like Pattern}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#makeGlobFromSqlLikePattern(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLUtils.makeGlobFromSqlLikePattern(String)"})
  public void testMakeGlobFromSqlLikePattern_whenSqlLikePattern_thenReturnSqlLikePattern() {
    // Arrange, Act and Assert
    assertEquals("Sql Like Pattern", SQLUtils.makeGlobFromSqlLikePattern("Sql Like Pattern"));
  }

  /**
   * Test {@link SQLUtils#makeGlobFromSqlLikePattern(String)}.
   *
   * <ul>
   *   <li>When {@code _}.
   *   <li>Then return {@link SQLConstants#QUESTION}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#makeGlobFromSqlLikePattern(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLUtils.makeGlobFromSqlLikePattern(String)"})
  public void testMakeGlobFromSqlLikePattern_whenUnderscore_thenReturnQuestion() {
    // Arrange, Act and Assert
    assertEquals(SQLConstants.QUESTION, SQLUtils.makeGlobFromSqlLikePattern("_"));
  }

  /**
   * Test {@link SQLUtils#matchesLike(String, String)}.
   *
   * <ul>
   *   <li>When {@code .*}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#matchesLike(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLUtils.matchesLike(String, String)"})
  public void testMatchesLike_whenDotAsterisk_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(SQLUtils.matchesLike("String", ".*"));
  }

  /**
   * Test {@link SQLUtils#matchesLike(String, String)}.
   *
   * <ul>
   *   <li>When {@code =?}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#matchesLike(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLUtils.matchesLike(String, String)"})
  public void testMatchesLike_whenEqualsSignQuestionMark_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(SQLUtils.matchesLike("String", "=?"));
  }

  /**
   * Test {@link SQLUtils#matchesLike(String, String)}.
   *
   * <ul>
   *   <li>When {@code (}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#matchesLike(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLUtils.matchesLike(String, String)"})
  public void testMatchesLike_whenLeftParenthesis_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(SQLUtils.matchesLike("String", "("));
  }

  /**
   * Test {@link SQLUtils#matchesLike(String, String)}.
   *
   * <ul>
   *   <li>When {@code Like}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#matchesLike(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLUtils.matchesLike(String, String)"})
  public void testMatchesLike_whenLike_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(SQLUtils.matchesLike("String", "Like"));
  }

  /**
   * Test {@link SQLUtils#matchesLike(String, String)}.
   *
   * <ul>
   *   <li>When {@code like}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#matchesLike(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLUtils.matchesLike(String, String)"})
  public void testMatchesLike_whenLike_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(SQLUtils.matchesLike("like", "Like"));
  }

  /**
   * Test {@link SQLUtils#matchesLike(String, String)}.
   *
   * <ul>
   *   <li>When {@code %}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#matchesLike(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLUtils.matchesLike(String, String)"})
  public void testMatchesLike_whenPercentSign_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(SQLUtils.matchesLike("String", "%"));
  }

  /**
   * Test {@link SQLUtils#matchesLike(String, String)}.
   *
   * <ul>
   *   <li>When {@code )}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#matchesLike(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLUtils.matchesLike(String, String)"})
  public void testMatchesLike_whenRightParenthesis_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(SQLUtils.matchesLike("String", ")"));
  }

  /**
   * Test {@link SQLUtils#matchesLike(String, String)}.
   *
   * <ul>
   *   <li>When {@code _}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#matchesLike(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLUtils.matchesLike(String, String)"})
  public void testMatchesLike_whenUnderscore_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(SQLUtils.matchesLike("String", "_"));
  }

  /**
   * Test {@link SQLUtils#appendValue(StringBuilder, DBSTypedObject, Object)}.
   *
   * <ul>
   *   <li>Given {@code BOOLEAN}.
   *   <li>Then calls {@link DBDAttributeBindingCustom#getDataKind()}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#appendValue(StringBuilder, DBSTypedObject, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLUtils.appendValue(StringBuilder, DBSTypedObject, Object)"})
  public void testAppendValue_givenBoolean_thenCallsGetDataKind() {
    // Arrange
    StringBuilder buffer = new StringBuilder("foo");

    DBDAttributeBindingCustom type = mock(DBDAttributeBindingCustom.class);
    when(type.getDataKind()).thenReturn(DBPDataKind.BOOLEAN);

    // Act
    SQLUtils.appendValue(buffer, type, DBPEvent.RENAME);

    // Assert
    verify(type, atLeast(1)).getDataKind();
  }

  /**
   * Test {@link SQLUtils#appendValue(StringBuilder, DBSTypedObject, Object)}.
   *
   * <ul>
   *   <li>Given {@code NUMERIC}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#appendValue(StringBuilder, DBSTypedObject, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLUtils.appendValue(StringBuilder, DBSTypedObject, Object)"})
  public void testAppendValue_givenNumeric() {
    // Arrange
    StringBuilder buffer = new StringBuilder("foo");

    DBDAttributeBindingCustom type = mock(DBDAttributeBindingCustom.class);
    when(type.getDataKind()).thenReturn(DBPDataKind.NUMERIC);

    // Act
    SQLUtils.appendValue(buffer, type, DBPEvent.RENAME);

    // Assert
    verify(type).getDataKind();
  }

  /**
   * Test {@link SQLUtils#isStringQuoted(DBSObject, String)}.
   *
   * <ul>
   *   <li>Then calls {@link DBSDocumentContainer#getDataSource()}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#isStringQuoted(DBSObject, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLUtils.isStringQuoted(DBSObject, String)"})
  public void testIsStringQuoted_thenCallsGetDataSource() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);
    when(entity.getDataSource()).thenReturn(dbpDataSource);

    // Act
    boolean actualIsStringQuotedResult =
        SQLUtils.isStringQuoted(new DBSDocumentConstraint(entity), "String");

    // Assert
    verify(dbpDataSource).getSQLDialect();
    verify(entity).getDataSource();
    assertFalse(actualIsStringQuotedResult);
  }

  /**
   * Test {@link SQLUtils#isStringQuoted(DBSObject, String)}.
   *
   * <ul>
   *   <li>Then calls {@link DBVContainer#getDataSource()}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#isStringQuoted(DBSObject, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLUtils.isStringQuoted(DBSObject, String)"})
  public void testIsStringQuoted_thenCallsGetDataSource2() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");

    // Act
    boolean actualIsStringQuotedResult =
        SQLUtils.isStringQuoted(new DBVEntityForeignKey(entity), "String");

    // Assert
    verify(dbpDataSource).getSQLDialect();
    verify(parent).getDataSource();
    assertFalse(actualIsStringQuotedResult);
  }

  /**
   * Test {@link SQLUtils#quoteString(DBPDataSource, String)} with {@code dataSource}, {@code
   * string}.
   *
   * <p>Method under test: {@link SQLUtils#quoteString(DBPDataSource, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLUtils.quoteString(DBPDataSource, String)"})
  public void testQuoteStringWithDataSourceString() {
    // Arrange
    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    // Act
    String actualQuoteStringResult = SQLUtils.quoteString(dataSource, "String");

    // Assert
    verify(dataSource).getSQLDialect();
    assertEquals("'String'", actualQuoteStringResult);
  }

  /**
   * Test {@link SQLUtils#quoteString(DBSObject, String)} with {@code object}, {@code string}.
   *
   * <ul>
   *   <li>Then calls {@link DBSDocumentContainer#getDataSource()}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#quoteString(DBSObject, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLUtils.quoteString(DBSObject, String)"})
  public void testQuoteStringWithObjectString_thenCallsGetDataSource() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);
    when(entity.getDataSource()).thenReturn(dbpDataSource);

    // Act
    String actualQuoteStringResult =
        SQLUtils.quoteString(new DBSDocumentConstraint(entity), "String");

    // Assert
    verify(dbpDataSource).getSQLDialect();
    verify(entity).getDataSource();
    assertEquals("'String'", actualQuoteStringResult);
  }

  /**
   * Test {@link SQLUtils#quoteString(DBSObject, String)} with {@code object}, {@code string}.
   *
   * <ul>
   *   <li>Then calls {@link DBVContainer#getDataSource()}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#quoteString(DBSObject, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLUtils.quoteString(DBSObject, String)"})
  public void testQuoteStringWithObjectString_thenCallsGetDataSource2() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, SQLConstants.STR_QUOTE_SINGLE);
    DBVEntity entity =
        new DBVEntity(container, SQLConstants.STR_QUOTE_SINGLE, SQLConstants.STR_QUOTE_SINGLE);

    // Act
    String actualQuoteStringResult =
        SQLUtils.quoteString(new DBVEntityForeignKey(entity), "String");

    // Assert
    verify(dbpDataSource).getSQLDialect();
    verify(parent).getDataSource();
    assertEquals("'String'", actualQuoteStringResult);
  }

  /**
   * Test {@link SQLUtils#escapeString(DBPDataSource, String)}.
   *
   * <p>Method under test: {@link SQLUtils#escapeString(DBPDataSource, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLUtils.escapeString(DBPDataSource, String)"})
  public void testEscapeString() {
    // Arrange
    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    // Act
    String actualEscapeStringResult = SQLUtils.escapeString(dataSource, "String");

    // Assert
    verify(dataSource).getSQLDialect();
    assertEquals("String", actualEscapeStringResult);
  }

  /**
   * Test {@link SQLUtils#unQuoteString(DBPDataSource, String)}.
   *
   * <ul>
   *   <li>When empty string.
   *   <li>Then return empty string.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#unQuoteString(DBPDataSource, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLUtils.unQuoteString(DBPDataSource, String)"})
  public void testUnQuoteString_whenEmptyString_thenReturnEmptyString() {
    // Arrange, Act and Assert
    assertEquals("", SQLUtils.unQuoteString(mock(DBPDataSource.class), ""));
  }

  /**
   * Test {@link SQLUtils#unQuoteString(DBPDataSource, String)}.
   *
   * <ul>
   *   <li>When {@code String}.
   *   <li>Then return {@code String}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#unQuoteString(DBPDataSource, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLUtils.unQuoteString(DBPDataSource, String)"})
  public void testUnQuoteString_whenString_thenReturnString() {
    // Arrange, Act and Assert
    assertEquals("String", SQLUtils.unQuoteString(mock(DBPDataSource.class), "String"));
  }

  /**
   * Test {@link SQLUtils#getFirstKeyword(SQLDialect, String)}.
   *
   * <ul>
   *   <li>Given {@code null}.
   *   <li>When empty string.
   *   <li>Then return empty string.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#getFirstKeyword(SQLDialect, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLUtils.getFirstKeyword(SQLDialect, String)"})
  public void testGetFirstKeyword_givenNull_whenEmptyString_thenReturnEmptyString() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getSingleLineComments()).thenReturn(new String[] {"Single Line Comments"});
    when(dialect.getMultiLineComments()).thenReturn(null);

    // Act
    String actualFirstKeyword = SQLUtils.getFirstKeyword(dialect, "");

    // Assert
    verify(dialect).getMultiLineComments();
    verify(dialect).getSingleLineComments();
    assertEquals("", actualFirstKeyword);
  }

  /**
   * Test {@link SQLUtils#getFirstKeyword(SQLDialect, String)}.
   *
   * <ul>
   *   <li>Given {@code null}.
   *   <li>When {@code %}.
   *   <li>Then return {@code %}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#getFirstKeyword(SQLDialect, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLUtils.getFirstKeyword(SQLDialect, String)"})
  public void testGetFirstKeyword_givenNull_whenPercentSign_thenReturnPercentSign() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getSingleLineComments()).thenReturn(new String[] {"Single Line Comments"});
    when(dialect.getMultiLineComments()).thenReturn(null);

    // Act
    String actualFirstKeyword = SQLUtils.getFirstKeyword(dialect, "%");

    // Assert
    verify(dialect).getMultiLineComments();
    verify(dialect).getSingleLineComments();
    assertEquals("%", actualFirstKeyword);
  }

  /**
   * Test {@link SQLUtils#getFirstKeyword(SQLDialect, String)}.
   *
   * <ul>
   *   <li>Given {@link Pair#Pair(Object, Object)} with {@code First} and {@code Second}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#getFirstKeyword(SQLDialect, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLUtils.getFirstKeyword(SQLDialect, String)"})
  public void testGetFirstKeyword_givenPairWithFirstAndSecond() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getSingleLineComments()).thenReturn(new String[] {"foo", ""});
    when(dialect.getMultiLineComments()).thenReturn(new Pair<>("First", "Second"));

    // Act
    String actualFirstKeyword = SQLUtils.getFirstKeyword(dialect, "Query");

    // Assert
    verify(dialect).getMultiLineComments();
    verify(dialect).getSingleLineComments();
    assertEquals("", actualFirstKeyword);
  }

  /**
   * Test {@link SQLUtils#getFirstKeyword(SQLDialect, String)}.
   *
   * <ul>
   *   <li>Given {@link Pair#Pair(Object, Object)} with {@code First} and second is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#getFirstKeyword(SQLDialect, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLUtils.getFirstKeyword(SQLDialect, String)"})
  public void testGetFirstKeyword_givenPairWithFirstAndSecondIsNull() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getSingleLineComments()).thenReturn(new String[] {"foo", ""});
    Pair<String, String> pair = new Pair<>("First", null);
    when(dialect.getMultiLineComments()).thenReturn(pair);

    // Act
    String actualFirstKeyword = SQLUtils.getFirstKeyword(dialect, "Query");

    // Assert
    verify(dialect).getMultiLineComments();
    verify(dialect).getSingleLineComments();
    assertEquals("", actualFirstKeyword);
  }

  /**
   * Test {@link SQLUtils#getFirstKeyword(SQLDialect, String)}.
   *
   * <ul>
   *   <li>When {@link BasicSQLDialect#INSTANCE}.
   *   <li>Then return {@code Query}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#getFirstKeyword(SQLDialect, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLUtils.getFirstKeyword(SQLDialect, String)"})
  public void testGetFirstKeyword_whenInstance_thenReturnQuery() {
    // Arrange, Act and Assert
    assertEquals("Query", SQLUtils.getFirstKeyword(BasicSQLDialect.INSTANCE, "Query"));
  }

  /**
   * Test {@link SQLUtils#getFirstKeyword(SQLDialect, String)}.
   *
   * <ul>
   *   <li>When {@link SQLDialect} {@link SQLDialect#getMultiLineComments()} return {@code null}.
   *   <li>Then return {@code Query}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#getFirstKeyword(SQLDialect, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLUtils.getFirstKeyword(SQLDialect, String)"})
  public void testGetFirstKeyword_whenSQLDialectGetMultiLineCommentsReturnNull_thenReturnQuery() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getSingleLineComments()).thenReturn(new String[] {"Single Line Comments"});
    when(dialect.getMultiLineComments()).thenReturn(null);

    // Act
    String actualFirstKeyword = SQLUtils.getFirstKeyword(dialect, "Query");

    // Assert
    verify(dialect).getMultiLineComments();
    verify(dialect).getSingleLineComments();
    assertEquals("Query", actualFirstKeyword);
  }

  /**
   * Test {@link SQLUtils#getQueryOutputParameter(DBCSession, String)}.
   *
   * <ul>
   *   <li>Then return {@link SQLConstants#QUESTION}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#getQueryOutputParameter(DBCSession, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLUtils.getQueryOutputParameter(DBCSession, String)"})
  public void testGetQueryOutputParameter_thenReturnQuestion() {
    // Arrange, Act and Assert
    assertEquals(
        SQLConstants.QUESTION, SQLUtils.getQueryOutputParameter(mock(DBCSession.class), "? :="));
  }

  /**
   * Test {@link SQLUtils#getQueryOutputParameter(DBCSession, String)}.
   *
   * <ul>
   *   <li>When {@code Query}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#getQueryOutputParameter(DBCSession, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLUtils.getQueryOutputParameter(DBCSession, String)"})
  public void testGetQueryOutputParameter_whenQuery_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(SQLUtils.getQueryOutputParameter(mock(DBCSession.class), "Query"));
  }

  /**
   * Test {@link SQLUtils#makeUnifiedLineFeeds(DBPDataSource, String)}.
   *
   * <ul>
   *   <li>Given {@link BasicSQLDialect#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#makeUnifiedLineFeeds(DBPDataSource, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLUtils.makeUnifiedLineFeeds(DBPDataSource, String)"})
  public void testMakeUnifiedLineFeeds_givenInstance() {
    // Arrange
    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    // Act
    String actualMakeUnifiedLineFeedsResult = SQLUtils.makeUnifiedLineFeeds(dataSource, "Query");

    // Assert
    verify(dataSource).getSQLDialect();
    assertEquals("Query", actualMakeUnifiedLineFeedsResult);
  }

  /**
   * Test {@link SQLUtils#makeUnifiedLineFeeds(DBPDataSource, String)}.
   *
   * <ul>
   *   <li>Then calls {@link SQLDialect#isCRLFBroken()}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#makeUnifiedLineFeeds(DBPDataSource, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLUtils.makeUnifiedLineFeeds(DBPDataSource, String)"})
  public void testMakeUnifiedLineFeeds_thenCallsIsCRLFBroken() {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isCRLFBroken()).thenReturn(true);

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(sqlDialect);

    // Act
    String actualMakeUnifiedLineFeedsResult = SQLUtils.makeUnifiedLineFeeds(dataSource, "Query");

    // Assert
    verify(dataSource).getSQLDialect();
    verify(sqlDialect).isCRLFBroken();
    assertEquals("Query", actualMakeUnifiedLineFeedsResult);
  }

  /**
   * Test {@link SQLUtils#makeUnifiedLineFeeds(DBPDataSource, String)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code Query}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#makeUnifiedLineFeeds(DBPDataSource, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLUtils.makeUnifiedLineFeeds(DBPDataSource, String)"})
  public void testMakeUnifiedLineFeeds_whenNull_thenReturnQuery() {
    // Arrange, Act and Assert
    assertEquals("Query", SQLUtils.makeUnifiedLineFeeds(null, "Query"));
  }

  /**
   * Test {@link SQLUtils#appendLikeCondition(StringBuilder, String, boolean, SQLDialect)}.
   *
   * <ul>
   *   <li>Then {@link StringBuilder#StringBuilder(String)} with {@code foo} toString is {@code foo
   *       NOT LIKE ?}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#appendLikeCondition(StringBuilder, String, boolean,
   * SQLDialect)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLUtils.appendLikeCondition(StringBuilder, String, boolean, SQLDialect)"
  })
  public void testAppendLikeCondition_thenStringBuilderWithFooToStringIsFooNotLike() {
    // Arrange
    StringBuilder sql = new StringBuilder("foo");

    // Act
    SQLUtils.appendLikeCondition(sql, SQLConstants.ASTERISK, true, BasicSQLDialect.INSTANCE);

    // Assert
    assertEquals("foo NOT LIKE ?", sql.toString());
  }

  /**
   * Test {@link SQLUtils#appendLikeCondition(StringBuilder, String, boolean, SQLDialect)}.
   *
   * <ul>
   *   <li>When {@code 42}.
   *   <li>Then {@link StringBuilder#StringBuilder(String)} with {@code foo} toString is {@code
   *       foo<>?}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#appendLikeCondition(StringBuilder, String, boolean,
   * SQLDialect)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLUtils.appendLikeCondition(StringBuilder, String, boolean, SQLDialect)"
  })
  public void testAppendLikeCondition_when42_thenStringBuilderWithFooToStringIsFoo() {
    // Arrange
    StringBuilder sql = new StringBuilder("foo");

    // Act
    SQLUtils.appendLikeCondition(sql, "42", true, BasicSQLDialect.INSTANCE);

    // Assert
    assertEquals("foo<>?", sql.toString());
  }

  /**
   * Test {@link SQLUtils#appendLikeCondition(StringBuilder, String, boolean, SQLDialect)}.
   *
   * <ul>
   *   <li>When {@code false}.
   *   <li>Then {@link StringBuilder#StringBuilder(String)} with {@code foo} toString is {@code
   *       foo=?}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#appendLikeCondition(StringBuilder, String, boolean,
   * SQLDialect)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLUtils.appendLikeCondition(StringBuilder, String, boolean, SQLDialect)"
  })
  public void testAppendLikeCondition_whenFalse_thenStringBuilderWithFooToStringIsFoo() {
    // Arrange
    StringBuilder sql = new StringBuilder("foo");

    // Act
    SQLUtils.appendLikeCondition(sql, "42", false, BasicSQLDialect.INSTANCE);

    // Assert
    assertEquals("foo=?", sql.toString());
  }

  /**
   * Test {@link SQLUtils#appendLikeCondition(StringBuilder, String, boolean, SQLDialect)}.
   *
   * <ul>
   *   <li>When {@code false}.
   *   <li>Then {@link StringBuilder#StringBuilder(String)} with {@code foo} toString is {@code foo
   *       LIKE ?}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#appendLikeCondition(StringBuilder, String, boolean,
   * SQLDialect)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLUtils.appendLikeCondition(StringBuilder, String, boolean, SQLDialect)"
  })
  public void testAppendLikeCondition_whenFalse_thenStringBuilderWithFooToStringIsFooLike() {
    // Arrange
    StringBuilder sql = new StringBuilder("foo");

    // Act
    SQLUtils.appendLikeCondition(sql, SQLConstants.ASTERISK, false, BasicSQLDialect.INSTANCE);

    // Assert
    assertEquals("foo LIKE ?", sql.toString());
  }

  /**
   * Test {@link SQLUtils#appendLikeCondition(StringBuilder, String, boolean, SQLDialect)}.
   *
   * <ul>
   *   <li>When {@link SQLConstants#QUESTION}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#appendLikeCondition(StringBuilder, String, boolean,
   * SQLDialect)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLUtils.appendLikeCondition(StringBuilder, String, boolean, SQLDialect)"
  })
  public void testAppendLikeCondition_whenQuestion() {
    // Arrange
    StringBuilder sql = new StringBuilder("foo");

    // Act
    SQLUtils.appendLikeCondition(sql, SQLConstants.QUESTION, true, BasicSQLDialect.INSTANCE);

    // Assert
    assertEquals("foo NOT LIKE ?", sql.toString());
  }

  /**
   * Test {@link SQLUtils#appendLikeCondition(StringBuilder, String, boolean, SQLDialect)}.
   *
   * <ul>
   *   <li>When {@link SQLDialect}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#appendLikeCondition(StringBuilder, String, boolean,
   * SQLDialect)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLUtils.appendLikeCondition(StringBuilder, String, boolean, SQLDialect)"
  })
  public void testAppendLikeCondition_whenSQLDialect() {
    // Arrange
    StringBuilder sql = new StringBuilder("foo");

    // Act
    SQLUtils.appendLikeCondition(sql, SQLConstants.ASTERISK, true, mock(SQLDialect.class));

    // Assert
    assertEquals("foo NOT LIKE ?", sql.toString());
  }

  /**
   * Test {@link SQLUtils#appendFirstClause(StringBuilder, boolean)}.
   *
   * <ul>
   *   <li>Then {@link StringBuilder#StringBuilder(String)} with {@code foo} toString is {@code foo
   *       WHERE}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#appendFirstClause(StringBuilder, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLUtils.appendFirstClause(StringBuilder, boolean)"})
  public void testAppendFirstClause_thenStringBuilderWithFooToStringIsFooWhere() {
    // Arrange
    StringBuilder sql = new StringBuilder("foo");

    // Act
    boolean actualAppendFirstClauseResult = SQLUtils.appendFirstClause(sql, true);

    // Assert
    assertEquals("foo WHERE ", sql.toString());
    assertFalse(actualAppendFirstClauseResult);
  }

  /**
   * Test {@link SQLUtils#appendFirstClause(StringBuilder, boolean)}.
   *
   * <ul>
   *   <li>When {@code false}.
   *   <li>Then {@link StringBuilder#StringBuilder(String)} with {@code foo} toString is {@code foo
   *       AND}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#appendFirstClause(StringBuilder, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLUtils.appendFirstClause(StringBuilder, boolean)"})
  public void testAppendFirstClause_whenFalse_thenStringBuilderWithFooToStringIsFooAnd() {
    // Arrange
    StringBuilder sql = new StringBuilder("foo");

    // Act
    boolean actualAppendFirstClauseResult = SQLUtils.appendFirstClause(sql, false);

    // Assert
    assertEquals("foo AND ", sql.toString());
    assertFalse(actualAppendFirstClauseResult);
  }

  /**
   * Test {@link SQLUtils#trimQueryStatement(SQLSyntaxManager, String, boolean)}.
   *
   * <ul>
   *   <li>When empty string.
   *   <li>Then return empty string.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#trimQueryStatement(SQLSyntaxManager, String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLUtils.trimQueryStatement(SQLSyntaxManager, String, boolean)"})
  public void testTrimQueryStatement_whenEmptyString_thenReturnEmptyString() {
    // Arrange and Act
    String actualTrimQueryStatementResult = SQLUtils.trimQueryStatement(null, "", true);

    // Assert
    assertEquals("", actualTrimQueryStatementResult);
  }

  /**
   * Test {@link SQLUtils#trimQueryStatement(SQLSyntaxManager, String, boolean)}.
   *
   * <ul>
   *   <li>When {@code false}.
   *   <li>Then return {@code Sql}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#trimQueryStatement(SQLSyntaxManager, String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLUtils.trimQueryStatement(SQLSyntaxManager, String, boolean)"})
  public void testTrimQueryStatement_whenFalse_thenReturnSql() {
    // Arrange and Act
    String actualTrimQueryStatementResult = SQLUtils.trimQueryStatement(null, "Sql", false);

    // Assert
    assertEquals("Sql", actualTrimQueryStatementResult);
  }

  /**
   * Test {@link SQLUtils#isBlockStartKeyword(SQLDialect, String)}.
   *
   * <ul>
   *   <li>Given empty array of {@link String}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#isBlockStartKeyword(SQLDialect, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLUtils.isBlockStartKeyword(SQLDialect, String)"})
  public void testIsBlockStartKeyword_givenEmptyArrayOfString() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getBlockBoundStrings()).thenReturn(new String[][] {new String[] {}});

    // Act
    boolean actualIsBlockStartKeywordResult = SQLUtils.isBlockStartKeyword(dialect, "Keyword");

    // Assert
    verify(dialect).getBlockBoundStrings();
    assertFalse(actualIsBlockStartKeywordResult);
  }

  /**
   * Test {@link SQLUtils#isBlockStartKeyword(SQLDialect, String)}.
   *
   * <ul>
   *   <li>Given {@code null}.
   *   <li>When {@link SQLDialect} {@link SQLDialect#getBlockBoundStrings()} return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#isBlockStartKeyword(SQLDialect, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLUtils.isBlockStartKeyword(SQLDialect, String)"})
  public void testIsBlockStartKeyword_givenNull_whenSQLDialectGetBlockBoundStringsReturnNull() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getBlockBoundStrings()).thenReturn(null);

    // Act
    boolean actualIsBlockStartKeywordResult = SQLUtils.isBlockStartKeyword(dialect, "Keyword");

    // Assert
    verify(dialect).getBlockBoundStrings();
    assertFalse(actualIsBlockStartKeywordResult);
  }

  /**
   * Test {@link SQLUtils#isBlockStartKeyword(SQLDialect, String)}.
   *
   * <ul>
   *   <li>When {@link BasicSQLDialect#INSTANCE}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#isBlockStartKeyword(SQLDialect, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLUtils.isBlockStartKeyword(SQLDialect, String)"})
  public void testIsBlockStartKeyword_whenInstance_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(SQLUtils.isBlockStartKeyword(BasicSQLDialect.INSTANCE, "Keyword"));
  }

  /**
   * Test {@link SQLUtils#isBlockEndKeyword(SQLDialect, String)}.
   *
   * <ul>
   *   <li>Given array of {@link String} with {@code Block Bound Strings}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#isBlockEndKeyword(SQLDialect, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLUtils.isBlockEndKeyword(SQLDialect, String)"})
  public void testIsBlockEndKeyword_givenArrayOfStringWithBlockBoundStrings() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getBlockBoundStrings())
        .thenReturn(new String[][] {new String[] {"Block Bound Strings"}});

    // Act
    boolean actualIsBlockEndKeywordResult = SQLUtils.isBlockEndKeyword(dialect, "Keyword");

    // Assert
    verify(dialect).getBlockBoundStrings();
    assertFalse(actualIsBlockEndKeywordResult);
  }

  /**
   * Test {@link SQLUtils#isBlockEndKeyword(SQLDialect, String)}.
   *
   * <ul>
   *   <li>Given array of {@link String} with {@code foo} and {@code 42}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#isBlockEndKeyword(SQLDialect, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLUtils.isBlockEndKeyword(SQLDialect, String)"})
  public void testIsBlockEndKeyword_givenArrayOfStringWithFooAnd42_thenReturnTrue() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getBlockBoundStrings()).thenReturn(new String[][] {new String[] {"foo", "42"}});

    // Act
    boolean actualIsBlockEndKeywordResult = SQLUtils.isBlockEndKeyword(dialect, "42");

    // Assert
    verify(dialect).getBlockBoundStrings();
    assertTrue(actualIsBlockEndKeywordResult);
  }

  /**
   * Test {@link SQLUtils#isBlockEndKeyword(SQLDialect, String)}.
   *
   * <ul>
   *   <li>Given {@code null}.
   *   <li>When {@link SQLDialect} {@link SQLDialect#getBlockBoundStrings()} return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#isBlockEndKeyword(SQLDialect, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLUtils.isBlockEndKeyword(SQLDialect, String)"})
  public void testIsBlockEndKeyword_givenNull_whenSQLDialectGetBlockBoundStringsReturnNull() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getBlockBoundStrings()).thenReturn(null);

    // Act
    boolean actualIsBlockEndKeywordResult = SQLUtils.isBlockEndKeyword(dialect, "Keyword");

    // Assert
    verify(dialect).getBlockBoundStrings();
    assertFalse(actualIsBlockEndKeywordResult);
  }

  /**
   * Test {@link SQLUtils#isBlockEndKeyword(SQLDialect, String)}.
   *
   * <ul>
   *   <li>When {@link BasicSQLDialect#INSTANCE}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#isBlockEndKeyword(SQLDialect, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLUtils.isBlockEndKeyword(SQLDialect, String)"})
  public void testIsBlockEndKeyword_whenInstance_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(SQLUtils.isBlockEndKeyword(BasicSQLDialect.INSTANCE, "Keyword"));
  }

  /**
   * Test {@link SQLUtils#appendConditionString(DBDDataFilter, List, DBPDataSource, String,
   * StringBuilder, boolean, boolean)} with {@code filter}, {@code constraints}, {@code dataSource},
   * {@code conditionTable}, {@code query}, {@code inlineCriteria}, {@code subQuery}.
   *
   * <p>Method under test: {@link SQLUtils#appendConditionString(DBDDataFilter, List, DBPDataSource,
   * String, StringBuilder, boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLUtils.appendConditionString(DBDDataFilter, List, DBPDataSource, String, StringBuilder, boolean, boolean)"
  })
  public void
      testAppendConditionStringWithFilterConstraintsDataSourceConditionTableQueryInlineCriteriaSubQuery()
          throws DBException {
    // Arrange
    DBDDataFilter filter = new DBDDataFilter();
    ArrayList<DBDAttributeConstraint> constraints = new ArrayList<>();

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);
    StringBuilder query = new StringBuilder("foo");

    // Act
    SQLUtils.appendConditionString(
        filter, constraints, dataSource, "Condition Table", query, true, true);

    // Assert that nothing has changed
    verify(dataSource).getSQLDialect();
    assertEquals("foo", query.toString());
  }

  /**
   * Test {@link SQLUtils#appendConditionString(DBDDataFilter, List, DBPDataSource, String,
   * StringBuilder, boolean, boolean)} with {@code filter}, {@code constraints}, {@code dataSource},
   * {@code conditionTable}, {@code query}, {@code inlineCriteria}, {@code subQuery}.
   *
   * <p>Method under test: {@link SQLUtils#appendConditionString(DBDDataFilter, List, DBPDataSource,
   * String, StringBuilder, boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLUtils.appendConditionString(DBDDataFilter, List, DBPDataSource, String, StringBuilder, boolean, boolean)"
  })
  public void
      testAppendConditionStringWithFilterConstraintsDataSourceConditionTableQueryInlineCriteriaSubQuery2()
          throws DBException {
    // Arrange
    DBDDataFilter filter = new DBDDataFilter(new ArrayList<>());
    ArrayList<DBDAttributeConstraint> constraints = new ArrayList<>();

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);
    StringBuilder query = new StringBuilder("foo");

    // Act
    SQLUtils.appendConditionString(
        filter, constraints, dataSource, "Condition Table", query, true, true);

    // Assert that nothing has changed
    verify(dataSource).getSQLDialect();
    assertEquals("foo", query.toString());
  }

  /**
   * Test {@link SQLUtils#appendConditionString(DBDDataFilter, List, DBPDataSource, String,
   * StringBuilder, boolean, boolean)} with {@code filter}, {@code constraints}, {@code dataSource},
   * {@code conditionTable}, {@code query}, {@code inlineCriteria}, {@code subQuery}.
   *
   * <p>Method under test: {@link SQLUtils#appendConditionString(DBDDataFilter, List, DBPDataSource,
   * String, StringBuilder, boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLUtils.appendConditionString(DBDDataFilter, List, DBPDataSource, String, StringBuilder, boolean, boolean)"
  })
  public void
      testAppendConditionStringWithFilterConstraintsDataSourceConditionTableQueryInlineCriteriaSubQuery3()
          throws DBException {
    // Arrange
    DBDDataFilter filter = new DBDDataFilter();

    ArrayList<DBDAttributeConstraint> constraints = new ArrayList<>();
    constraints.add(new DBDAttributeConstraint(" AND ", 1));

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);
    StringBuilder query = new StringBuilder("foo");

    // Act
    SQLUtils.appendConditionString(
        filter, constraints, dataSource, "Condition Table", query, true, true);

    // Assert
    verify(dataSource, atLeast(1)).getSQLDialect();
    assertEquals("foo\" AND \" null", query.toString());
  }

  /**
   * Test {@link SQLUtils#appendConditionString(DBDDataFilter, List, DBPDataSource, String,
   * StringBuilder, boolean, boolean)} with {@code filter}, {@code constraints}, {@code dataSource},
   * {@code conditionTable}, {@code query}, {@code inlineCriteria}, {@code subQuery}.
   *
   * <p>Method under test: {@link SQLUtils#appendConditionString(DBDDataFilter, List, DBPDataSource,
   * String, StringBuilder, boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLUtils.appendConditionString(DBDDataFilter, List, DBPDataSource, String, StringBuilder, boolean, boolean)"
  })
  public void
      testAppendConditionStringWithFilterConstraintsDataSourceConditionTableQueryInlineCriteriaSubQuery4()
          throws DBException {
    // Arrange
    DBDDataFilter filter = new DBDDataFilter();

    ArrayList<DBDAttributeConstraint> constraints = new ArrayList<>();
    constraints.add(new DBDAttributeConstraint(" AND ", 3));
    constraints.add(new DBDAttributeConstraint(" AND ", 1));

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);
    StringBuilder query = new StringBuilder("foo");

    // Act
    SQLUtils.appendConditionString(
        filter, constraints, dataSource, "Condition Table", query, true, true);

    // Assert
    verify(dataSource, atLeast(1)).getSQLDialect();
    assertEquals("foo(\" AND \" null) AND (\" AND \" null)", query.toString());
  }

  /**
   * Test {@link SQLUtils#appendConditionString(DBDDataFilter, List, DBPDataSource, String,
   * StringBuilder, boolean, boolean)} with {@code filter}, {@code constraints}, {@code dataSource},
   * {@code conditionTable}, {@code query}, {@code inlineCriteria}, {@code subQuery}.
   *
   * <p>Method under test: {@link SQLUtils#appendConditionString(DBDDataFilter, List, DBPDataSource,
   * String, StringBuilder, boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLUtils.appendConditionString(DBDDataFilter, List, DBPDataSource, String, StringBuilder, boolean, boolean)"
  })
  public void
      testAppendConditionStringWithFilterConstraintsDataSourceConditionTableQueryInlineCriteriaSubQuery5()
          throws DBException {
    // Arrange
    DBDDataFilter filter = new DBDDataFilter();
    filter.setWhere(" AND ");
    ArrayList<DBDAttributeConstraint> constraints = new ArrayList<>();

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);
    StringBuilder query = new StringBuilder("foo");

    // Act
    SQLUtils.appendConditionString(
        filter, constraints, dataSource, "Condition Table", query, true, true);

    // Assert
    verify(dataSource).getSQLDialect();
    assertEquals("foo AND ", query.toString());
  }

  /**
   * Test {@link SQLUtils#appendConditionString(DBDDataFilter, List, DBPDataSource, String,
   * StringBuilder, boolean, boolean)} with {@code filter}, {@code constraints}, {@code dataSource},
   * {@code conditionTable}, {@code query}, {@code inlineCriteria}, {@code subQuery}.
   *
   * <p>Method under test: {@link SQLUtils#appendConditionString(DBDDataFilter, List, DBPDataSource,
   * String, StringBuilder, boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLUtils.appendConditionString(DBDDataFilter, List, DBPDataSource, String, StringBuilder, boolean, boolean)"
  })
  public void
      testAppendConditionStringWithFilterConstraintsDataSourceConditionTableQueryInlineCriteriaSubQuery6()
          throws DBException {
    // Arrange
    DBDDataFilter filter = new DBDDataFilter();
    ArrayList<DBDAttributeConstraint> constraints = new ArrayList<>();

    SQLQueryGenerator sqlQueryGenerator = mock(SQLQueryGenerator.class);
    doNothing()
        .when(sqlQueryGenerator)
        .appendConditionString(
            Mockito.<DBDDataFilter>any(),
            Mockito.<List<DBDAttributeConstraint>>any(),
            Mockito.<DBPDataSource>any(),
            Mockito.<String>any(),
            Mockito.<StringBuilder>any(),
            anyBoolean(),
            anyBoolean());

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getQueryGenerator()).thenReturn(sqlQueryGenerator);

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(sqlDialect);
    StringBuilder query = new StringBuilder("foo");

    // Act
    SQLUtils.appendConditionString(
        filter, constraints, dataSource, "Condition Table", query, true, true);

    // Assert that nothing has changed
    verify(dataSource).getSQLDialect();
    verify(sqlDialect).getQueryGenerator();
    verify(sqlQueryGenerator)
        .appendConditionString(
            isA(DBDDataFilter.class),
            isA(List.class),
            isA(DBPDataSource.class),
            eq("Condition Table"),
            isA(StringBuilder.class),
            eq(true),
            eq(true));
    assertEquals("foo", query.toString());
  }

  /**
   * Test {@link SQLUtils#appendConditionString(DBDDataFilter, DBPDataSource, String, StringBuilder,
   * boolean)} with {@code filter}, {@code dataSource}, {@code conditionTable}, {@code query},
   * {@code inlineCriteria}.
   *
   * <p>Method under test: {@link SQLUtils#appendConditionString(DBDDataFilter, DBPDataSource,
   * String, StringBuilder, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLUtils.appendConditionString(DBDDataFilter, DBPDataSource, String, StringBuilder, boolean)"
  })
  public void testAppendConditionStringWithFilterDataSourceConditionTableQueryInlineCriteria()
      throws DBException {
    // Arrange
    DBDDataFilter filter = new DBDDataFilter();

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);
    StringBuilder query = new StringBuilder("foo");

    // Act
    SQLUtils.appendConditionString(filter, dataSource, "Condition Table", query, true);

    // Assert that nothing has changed
    verify(dataSource).getSQLDialect();
    assertEquals("foo", query.toString());
  }

  /**
   * Test {@link SQLUtils#appendConditionString(DBDDataFilter, DBPDataSource, String, StringBuilder,
   * boolean)} with {@code filter}, {@code dataSource}, {@code conditionTable}, {@code query},
   * {@code inlineCriteria}.
   *
   * <p>Method under test: {@link SQLUtils#appendConditionString(DBDDataFilter, DBPDataSource,
   * String, StringBuilder, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLUtils.appendConditionString(DBDDataFilter, DBPDataSource, String, StringBuilder, boolean)"
  })
  public void testAppendConditionStringWithFilterDataSourceConditionTableQueryInlineCriteria2()
      throws DBException {
    // Arrange
    DBDDataFilter filter = new DBDDataFilter();
    filter.setWhere(" AND ");

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);
    StringBuilder query = new StringBuilder("foo");

    // Act
    SQLUtils.appendConditionString(filter, dataSource, "Condition Table", query, true);

    // Assert
    verify(dataSource).getSQLDialect();
    assertEquals("foo AND ", query.toString());
  }

  /**
   * Test {@link SQLUtils#appendConditionString(DBDDataFilter, DBPDataSource, String, StringBuilder,
   * boolean)} with {@code filter}, {@code dataSource}, {@code conditionTable}, {@code query},
   * {@code inlineCriteria}.
   *
   * <p>Method under test: {@link SQLUtils#appendConditionString(DBDDataFilter, DBPDataSource,
   * String, StringBuilder, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLUtils.appendConditionString(DBDDataFilter, DBPDataSource, String, StringBuilder, boolean)"
  })
  public void testAppendConditionStringWithFilterDataSourceConditionTableQueryInlineCriteria3()
      throws DBException {
    // Arrange
    DBDDataFilter filter = new DBDDataFilter();

    SQLQueryGenerator sqlQueryGenerator = mock(SQLQueryGenerator.class);
    doNothing()
        .when(sqlQueryGenerator)
        .appendConditionString(
            Mockito.<DBDDataFilter>any(),
            Mockito.<DBPDataSource>any(),
            Mockito.<String>any(),
            Mockito.<StringBuilder>any(),
            anyBoolean(),
            anyBoolean());

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getQueryGenerator()).thenReturn(sqlQueryGenerator);

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(sqlDialect);
    StringBuilder query = new StringBuilder("foo");

    // Act
    SQLUtils.appendConditionString(filter, dataSource, "Condition Table", query, true);

    // Assert that nothing has changed
    verify(dataSource).getSQLDialect();
    verify(sqlDialect).getQueryGenerator();
    verify(sqlQueryGenerator)
        .appendConditionString(
            isA(DBDDataFilter.class),
            isA(DBPDataSource.class),
            eq("Condition Table"),
            isA(StringBuilder.class),
            eq(true),
            eq(false));
    assertEquals("foo", query.toString());
  }

  /**
   * Test {@link SQLUtils#appendConditionString(DBDDataFilter, DBPDataSource, String, StringBuilder,
   * boolean, boolean)} with {@code filter}, {@code dataSource}, {@code conditionTable}, {@code
   * query}, {@code inlineCriteria}, {@code subQuery}.
   *
   * <p>Method under test: {@link SQLUtils#appendConditionString(DBDDataFilter, DBPDataSource,
   * String, StringBuilder, boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLUtils.appendConditionString(DBDDataFilter, DBPDataSource, String, StringBuilder, boolean, boolean)"
  })
  public void
      testAppendConditionStringWithFilterDataSourceConditionTableQueryInlineCriteriaSubQuery()
          throws DBException {
    // Arrange
    DBDDataFilter filter = new DBDDataFilter();

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);
    StringBuilder query = new StringBuilder("foo");

    // Act
    SQLUtils.appendConditionString(filter, dataSource, "Condition Table", query, true, true);

    // Assert that nothing has changed
    verify(dataSource).getSQLDialect();
    assertEquals("foo", query.toString());
  }

  /**
   * Test {@link SQLUtils#appendConditionString(DBDDataFilter, DBPDataSource, String, StringBuilder,
   * boolean, boolean)} with {@code filter}, {@code dataSource}, {@code conditionTable}, {@code
   * query}, {@code inlineCriteria}, {@code subQuery}.
   *
   * <p>Method under test: {@link SQLUtils#appendConditionString(DBDDataFilter, DBPDataSource,
   * String, StringBuilder, boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLUtils.appendConditionString(DBDDataFilter, DBPDataSource, String, StringBuilder, boolean, boolean)"
  })
  public void
      testAppendConditionStringWithFilterDataSourceConditionTableQueryInlineCriteriaSubQuery2()
          throws DBException {
    // Arrange
    DBDDataFilter filter = new DBDDataFilter();
    filter.setWhere(" AND ");

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);
    StringBuilder query = new StringBuilder("foo");

    // Act
    SQLUtils.appendConditionString(filter, dataSource, "Condition Table", query, true, true);

    // Assert
    verify(dataSource).getSQLDialect();
    assertEquals("foo AND ", query.toString());
  }

  /**
   * Test {@link SQLUtils#appendConditionString(DBDDataFilter, DBPDataSource, String, StringBuilder,
   * boolean, boolean)} with {@code filter}, {@code dataSource}, {@code conditionTable}, {@code
   * query}, {@code inlineCriteria}, {@code subQuery}.
   *
   * <p>Method under test: {@link SQLUtils#appendConditionString(DBDDataFilter, DBPDataSource,
   * String, StringBuilder, boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLUtils.appendConditionString(DBDDataFilter, DBPDataSource, String, StringBuilder, boolean, boolean)"
  })
  public void
      testAppendConditionStringWithFilterDataSourceConditionTableQueryInlineCriteriaSubQuery3()
          throws DBException {
    // Arrange
    DBDDataFilter filter = new DBDDataFilter();

    SQLQueryGenerator sqlQueryGenerator = mock(SQLQueryGenerator.class);
    doNothing()
        .when(sqlQueryGenerator)
        .appendConditionString(
            Mockito.<DBDDataFilter>any(),
            Mockito.<DBPDataSource>any(),
            Mockito.<String>any(),
            Mockito.<StringBuilder>any(),
            anyBoolean(),
            anyBoolean());

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getQueryGenerator()).thenReturn(sqlQueryGenerator);

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(sqlDialect);
    StringBuilder query = new StringBuilder("foo");

    // Act
    SQLUtils.appendConditionString(filter, dataSource, "Condition Table", query, true, true);

    // Assert that nothing has changed
    verify(dataSource).getSQLDialect();
    verify(sqlDialect).getQueryGenerator();
    verify(sqlQueryGenerator)
        .appendConditionString(
            isA(DBDDataFilter.class),
            isA(DBPDataSource.class),
            eq("Condition Table"),
            isA(StringBuilder.class),
            eq(true),
            eq(true));
    assertEquals("foo", query.toString());
  }

  /**
   * Test {@link SQLUtils#appendOrderString(DBDDataFilter, DBPDataSource, String, boolean,
   * StringBuilder)}.
   *
   * <ul>
   *   <li>Given {@code Order}.
   *   <li>Then {@link StringBuilder#StringBuilder(String)} with {@code foo} toString is {@code
   *       fooOrder}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#appendOrderString(DBDDataFilter, DBPDataSource, String,
   * boolean, StringBuilder)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLUtils.appendOrderString(DBDDataFilter, DBPDataSource, String, boolean, StringBuilder)"
  })
  public void testAppendOrderString_givenOrder_thenStringBuilderWithFooToStringIsFooOrder() {
    // Arrange
    DBDDataFilter filter = new DBDDataFilter();
    filter.setOrder("Order");

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);
    StringBuilder query = new StringBuilder("foo");

    // Act
    SQLUtils.appendOrderString(filter, dataSource, "Condition Table", true, query);

    // Assert
    verify(dataSource).getSQLDialect();
    assertEquals("fooOrder", query.toString());
  }

  /**
   * Test {@link SQLUtils#appendOrderString(DBDDataFilter, DBPDataSource, String, boolean,
   * StringBuilder)}.
   *
   * <ul>
   *   <li>Then calls {@link SQLDialect#getQueryGenerator()}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#appendOrderString(DBDDataFilter, DBPDataSource, String,
   * boolean, StringBuilder)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLUtils.appendOrderString(DBDDataFilter, DBPDataSource, String, boolean, StringBuilder)"
  })
  public void testAppendOrderString_thenCallsGetQueryGenerator() {
    // Arrange
    DBDDataFilter filter = new DBDDataFilter();

    SQLQueryGenerator sqlQueryGenerator = mock(SQLQueryGenerator.class);
    doNothing()
        .when(sqlQueryGenerator)
        .appendOrderString(
            Mockito.<DBDDataFilter>any(),
            Mockito.<DBPDataSource>any(),
            Mockito.<String>any(),
            anyBoolean(),
            Mockito.<StringBuilder>any());

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getQueryGenerator()).thenReturn(sqlQueryGenerator);

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(sqlDialect);
    StringBuilder query = new StringBuilder("foo");

    // Act
    SQLUtils.appendOrderString(filter, dataSource, "Condition Table", true, query);

    // Assert that nothing has changed
    verify(dataSource).getSQLDialect();
    verify(sqlDialect).getQueryGenerator();
    verify(sqlQueryGenerator)
        .appendOrderString(
            isA(DBDDataFilter.class),
            isA(DBPDataSource.class),
            eq("Condition Table"),
            eq(true),
            isA(StringBuilder.class));
    assertEquals("foo", query.toString());
  }

  /**
   * Test {@link SQLUtils#appendOrderString(DBDDataFilter, DBPDataSource, String, boolean,
   * StringBuilder)}.
   *
   * <ul>
   *   <li>When {@link DBDDataFilter#DBDDataFilter()}.
   *   <li>Then {@link StringBuilder#StringBuilder(String)} with {@code foo} toString is {@code
   *       foo}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#appendOrderString(DBDDataFilter, DBPDataSource, String,
   * boolean, StringBuilder)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLUtils.appendOrderString(DBDDataFilter, DBPDataSource, String, boolean, StringBuilder)"
  })
  public void testAppendOrderString_whenDBDDataFilter_thenStringBuilderWithFooToStringIsFoo() {
    // Arrange
    DBDDataFilter filter = new DBDDataFilter();

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);
    StringBuilder query = new StringBuilder("foo");

    // Act
    SQLUtils.appendOrderString(filter, dataSource, "Condition Table", true, query);

    // Assert that nothing has changed
    verify(dataSource).getSQLDialect();
    assertEquals("foo", query.toString());
  }

  /**
   * Test {@link SQLUtils#getConstraintCondition(DBPDataSource, DBDAttributeConstraint, String,
   * boolean)}.
   *
   * <ul>
   *   <li>Given {@link BasicSQLDialect#INSTANCE}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#getConstraintCondition(DBPDataSource,
   * DBDAttributeConstraint, String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String SQLUtils.getConstraintCondition(DBPDataSource, DBDAttributeConstraint, String, boolean)"
  })
  public void testGetConstraintCondition_givenInstance_thenReturnNull() {
    // Arrange
    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    // Act
    String actualConstraintCondition =
        SQLUtils.getConstraintCondition(
            dataSource, new DBDAttributeConstraint("Attribute Name", 1), "Condition Table", true);

    // Assert
    verify(dataSource).getSQLDialect();
    assertNull(actualConstraintCondition);
  }

  /**
   * Test {@link SQLUtils#getConstraintCondition(DBPDataSource, DBDAttributeConstraint, String,
   * boolean)}.
   *
   * <ul>
   *   <li>Then return {@code Constraint Condition}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#getConstraintCondition(DBPDataSource,
   * DBDAttributeConstraint, String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String SQLUtils.getConstraintCondition(DBPDataSource, DBDAttributeConstraint, String, boolean)"
  })
  public void testGetConstraintCondition_thenReturnConstraintCondition() {
    // Arrange
    SQLQueryGenerator sqlQueryGenerator = mock(SQLQueryGenerator.class);
    when(sqlQueryGenerator.getConstraintCondition(
            Mockito.<DBPDataSource>any(),
            Mockito.<DBDAttributeConstraint>any(),
            Mockito.<String>any(),
            anyBoolean()))
        .thenReturn("Constraint Condition");

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getQueryGenerator()).thenReturn(sqlQueryGenerator);

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(sqlDialect);

    // Act
    String actualConstraintCondition =
        SQLUtils.getConstraintCondition(
            dataSource, new DBDAttributeConstraint("Attribute Name", 1), "Condition Table", true);

    // Assert
    verify(dataSource).getSQLDialect();
    verify(sqlDialect).getQueryGenerator();
    verify(sqlQueryGenerator)
        .getConstraintCondition(
            isA(DBPDataSource.class),
            isA(DBDAttributeConstraint.class),
            eq("Condition Table"),
            eq(true));
    assertEquals("Constraint Condition", actualConstraintCondition);
  }

  /**
   * Test {@link SQLUtils#getConstraintOrderIndex(DBDDataFilter, DBDAttributeConstraint)}.
   *
   * <ul>
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#getConstraintOrderIndex(DBDDataFilter,
   * DBDAttributeConstraint)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int SQLUtils.getConstraintOrderIndex(DBDDataFilter, DBDAttributeConstraint)"})
  public void testGetConstraintOrderIndex_thenReturnOne() {
    // Arrange
    ArrayList<DBDAttributeConstraint> constraints = new ArrayList<>();
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    DBDDataFilter dataFilter = new DBDDataFilter(constraints);

    // Act
    int actualConstraintOrderIndex =
        SQLUtils.getConstraintOrderIndex(
            dataFilter, new DBDAttributeConstraint("Attribute Name", 1));

    // Assert
    assertEquals(1, actualConstraintOrderIndex);
  }

  /**
   * Test {@link SQLUtils#getConstraintOrderIndex(DBDDataFilter, DBDAttributeConstraint)}.
   *
   * <ul>
   *   <li>When {@link DBDDataFilter#DBDDataFilter()}.
   *   <li>Then return {@link SQLState#ERROR_CODE_NONE}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#getConstraintOrderIndex(DBDDataFilter,
   * DBDAttributeConstraint)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int SQLUtils.getConstraintOrderIndex(DBDDataFilter, DBDAttributeConstraint)"})
  public void testGetConstraintOrderIndex_whenDBDDataFilter_thenReturnError_code_none() {
    // Arrange
    DBDDataFilter dataFilter = new DBDDataFilter();

    // Act
    int actualConstraintOrderIndex =
        SQLUtils.getConstraintOrderIndex(
            dataFilter, new DBDAttributeConstraint("Attribute Name", 1));

    // Assert
    assertEquals(SQLState.ERROR_CODE_NONE, actualConstraintOrderIndex);
  }

  /**
   * Test {@link SQLUtils#convertValueToSQL(DBPDataSource, DBSTypedObject, DBDValueHandler, Object,
   * DBDDisplayFormat, boolean)} with {@code dataSource}, {@code attribute}, {@code valueHandler},
   * {@code value}, {@code displayFormat}, {@code isInCondition}.
   *
   * <p>Method under test: {@link SQLUtils#convertValueToSQL(DBPDataSource, DBSTypedObject,
   * DBDValueHandler, Object, DBDDisplayFormat, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String SQLUtils.convertValueToSQL(DBPDataSource, DBSTypedObject, DBDValueHandler, Object, DBDDisplayFormat, boolean)"
  })
  public void
      testConvertValueToSQLWithDataSourceAttributeValueHandlerValueDisplayFormatIsInCondition() {
    // Arrange
    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    // Act
    SQLUtils.convertValueToSQL(
        dataSource,
        SimpleTypedObject.DEFAULT_TYPE,
        DefaultValueHandler.INSTANCE,
        DBPEvent.RENAME,
        DBDDisplayFormat.UI,
        true);

    // Assert
    verify(dataSource, atLeast(1)).getSQLDialect();
  }

  /**
   * Test {@link SQLUtils#convertValueToSQL(DBPDataSource, DBSTypedObject, DBDValueHandler, Object,
   * DBDDisplayFormat, boolean)} with {@code dataSource}, {@code attribute}, {@code valueHandler},
   * {@code value}, {@code displayFormat}, {@code isInCondition}.
   *
   * <p>Method under test: {@link SQLUtils#convertValueToSQL(DBPDataSource, DBSTypedObject,
   * DBDValueHandler, Object, DBDDisplayFormat, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String SQLUtils.convertValueToSQL(DBPDataSource, DBSTypedObject, DBDValueHandler, Object, DBDDisplayFormat, boolean)"
  })
  public void
      testConvertValueToSQLWithDataSourceAttributeValueHandlerValueDisplayFormatIsInCondition2() {
    // Arrange
    DBPDataSource dataSource = mock(DBPDataSource.class);

    // Act
    String actualConvertValueToSQLResult =
        SQLUtils.convertValueToSQL(
            dataSource,
            SimpleTypedObject.DEFAULT_TYPE,
            DefaultValueHandler.INSTANCE,
            new DBDDocumentXML(null),
            DBDDisplayFormat.UI,
            true);

    // Assert
    assertEquals(SQLConstants.KEYWORD_NULL, actualConvertValueToSQLResult);
  }

  /**
   * Test {@link SQLUtils#convertValueToSQL(DBPDataSource, DBSTypedObject, DBDValueHandler, Object,
   * DBDDisplayFormat, boolean)} with {@code dataSource}, {@code attribute}, {@code valueHandler},
   * {@code value}, {@code displayFormat}, {@code isInCondition}.
   *
   * <p>Method under test: {@link SQLUtils#convertValueToSQL(DBPDataSource, DBSTypedObject,
   * DBDValueHandler, Object, DBDDisplayFormat, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String SQLUtils.convertValueToSQL(DBPDataSource, DBSTypedObject, DBDValueHandler, Object, DBDDisplayFormat, boolean)"
  })
  public void
      testConvertValueToSQLWithDataSourceAttributeValueHandlerValueDisplayFormatIsInCondition3() {
    // Arrange and Act
    String actualConvertValueToSQLResult =
        SQLUtils.convertValueToSQL(
            mock(DBPDataSource.class),
            SimpleTypedObject.DEFAULT_TYPE,
            DefaultValueHandler.INSTANCE,
            null,
            DBDDisplayFormat.UI,
            true);

    // Assert
    assertEquals(SQLConstants.KEYWORD_NULL, actualConvertValueToSQLResult);
  }

  /**
   * Test {@link SQLUtils#convertValueToSQL(DBPDataSource, DBSTypedObject, DBDValueHandler, Object,
   * DBDDisplayFormat, boolean)} with {@code dataSource}, {@code attribute}, {@code valueHandler},
   * {@code value}, {@code displayFormat}, {@code isInCondition}.
   *
   * <p>Method under test: {@link SQLUtils#convertValueToSQL(DBPDataSource, DBSTypedObject,
   * DBDValueHandler, Object, DBDDisplayFormat, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String SQLUtils.convertValueToSQL(DBPDataSource, DBSTypedObject, DBDValueHandler, Object, DBDDisplayFormat, boolean)"
  })
  public void
      testConvertValueToSQLWithDataSourceAttributeValueHandlerValueDisplayFormatIsInCondition4() {
    // Arrange
    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    // Act
    String actualConvertValueToSQLResult =
        SQLUtils.convertValueToSQL(
            dataSource,
            SimpleTypedObject.DEFAULT_TYPE,
            DefaultValueHandler.INSTANCE,
            new StringContent(mock(DBCExecutionContext.class), "java.lang"),
            DBDDisplayFormat.UI,
            true);

    // Assert
    verify(dataSource, atLeast(1)).getSQLDialect();
    assertEquals("java.lang", actualConvertValueToSQLResult);
  }

  /**
   * Test {@link SQLUtils#convertStreamToSQL(DBSTypedObject, DBDContent, DBDValueHandler,
   * DBPDataSource)}.
   *
   * <ul>
   *   <li>When {@link SimpleTypedObject#DEFAULT_TYPE}.
   *   <li>Then return {@code Data}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#convertStreamToSQL(DBSTypedObject, DBDContent,
   * DBDValueHandler, DBPDataSource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String SQLUtils.convertStreamToSQL(DBSTypedObject, DBDContent, DBDValueHandler, DBPDataSource)"
  })
  public void testConvertStreamToSQL_whenDefault_type_thenReturnData() {
    // Arrange and Act
    String actualConvertStreamToSQLResult =
        SQLUtils.convertStreamToSQL(
            SimpleTypedObject.DEFAULT_TYPE,
            new StringContent(mock(DBCExecutionContext.class), "Data"),
            DefaultValueHandler.INSTANCE,
            mock(DBPDataSource.class));

    // Assert
    assertEquals("Data", actualConvertStreamToSQLResult);
  }

  /**
   * Test {@link SQLUtils#convertStreamToSQL(DBSTypedObject, DBDContent, DBDValueHandler,
   * DBPDataSource)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@link SQLConstants#KEYWORD_NULL}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#convertStreamToSQL(DBSTypedObject, DBDContent,
   * DBDValueHandler, DBPDataSource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String SQLUtils.convertStreamToSQL(DBSTypedObject, DBDContent, DBDValueHandler, DBPDataSource)"
  })
  public void testConvertStreamToSQL_whenNull_thenReturnKeyword_null() {
    // Arrange and Act
    String actualConvertStreamToSQLResult =
        SQLUtils.convertStreamToSQL(
            SimpleTypedObject.DEFAULT_TYPE,
            null,
            DefaultValueHandler.INSTANCE,
            mock(DBPDataSource.class));

    // Assert
    assertEquals(SQLConstants.KEYWORD_NULL, actualConvertStreamToSQLResult);
  }

  /**
   * Test {@link SQLUtils#convertStreamToSQL(DBSTypedObject, DBDContent, DBDValueHandler,
   * DBPDataSource)}.
   *
   * <ul>
   *   <li>When {@link SimpleTypedObject#SimpleTypedObject(String)} with typeName is {@code
   *       text/plain}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#convertStreamToSQL(DBSTypedObject, DBDContent,
   * DBDValueHandler, DBPDataSource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String SQLUtils.convertStreamToSQL(DBSTypedObject, DBDContent, DBDValueHandler, DBPDataSource)"
  })
  public void testConvertStreamToSQL_whenSimpleTypedObjectWithTypeNameIsTextPlain() {
    // Arrange
    SimpleTypedObject attribute = new SimpleTypedObject("text/plain");

    // Act
    String actualConvertStreamToSQLResult =
        SQLUtils.convertStreamToSQL(
            attribute,
            new StringContent(mock(DBCExecutionContext.class), "Data"),
            DefaultValueHandler.INSTANCE,
            mock(DBPDataSource.class));

    // Assert
    assertEquals("Data", actualConvertStreamToSQLResult);
  }

  /**
   * Test {@link SQLUtils#getColumnTypeModifiers(DBPDataSource, DBSTypedObject, String,
   * DBPDataKind)}.
   *
   * <ul>
   *   <li>Given {@link BasicSQLDialect#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#getColumnTypeModifiers(DBPDataSource, DBSTypedObject,
   * String, DBPDataKind)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String SQLUtils.getColumnTypeModifiers(DBPDataSource, DBSTypedObject, String, DBPDataKind)"
  })
  public void testGetColumnTypeModifiers_givenInstance() {
    // Arrange
    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    // Act
    String actualColumnTypeModifiers =
        SQLUtils.getColumnTypeModifiers(
            dataSource, SimpleTypedObject.DEFAULT_TYPE, "Type Name", DBPDataKind.BOOLEAN);

    // Assert
    verify(dataSource).getSQLDialect();
    assertNull(actualColumnTypeModifiers);
  }

  /**
   * Test {@link SQLUtils#getColumnTypeModifiers(DBPDataSource, DBSTypedObject, String,
   * DBPDataKind)}.
   *
   * <ul>
   *   <li>Then return {@code Column Type Modifiers}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#getColumnTypeModifiers(DBPDataSource, DBSTypedObject,
   * String, DBPDataKind)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String SQLUtils.getColumnTypeModifiers(DBPDataSource, DBSTypedObject, String, DBPDataKind)"
  })
  public void testGetColumnTypeModifiers_thenReturnColumnTypeModifiers() {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getColumnTypeModifiers(
            Mockito.<DBPDataSource>any(),
            Mockito.<DBSTypedObject>any(),
            Mockito.<String>any(),
            Mockito.<DBPDataKind>any()))
        .thenReturn("Column Type Modifiers");

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(sqlDialect);

    // Act
    String actualColumnTypeModifiers =
        SQLUtils.getColumnTypeModifiers(
            dataSource, SimpleTypedObject.DEFAULT_TYPE, "Type Name", DBPDataKind.BOOLEAN);

    // Assert
    verify(dataSource).getSQLDialect();
    verify(sqlDialect)
        .getColumnTypeModifiers(
            isA(DBPDataSource.class),
            isA(DBSTypedObject.class),
            eq("Type Name"),
            eq(DBPDataKind.BOOLEAN));
    assertEquals("Column Type Modifiers", actualColumnTypeModifiers);
  }

  /**
   * Test {@link SQLUtils#getColumnTypeModifiers(DBPDataSource, DBSTypedObject, String,
   * DBPDataKind)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#getColumnTypeModifiers(DBPDataSource, DBSTypedObject,
   * String, DBPDataKind)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String SQLUtils.getColumnTypeModifiers(DBPDataSource, DBSTypedObject, String, DBPDataKind)"
  })
  public void testGetColumnTypeModifiers_whenNull_thenReturnNull() {
    // Arrange and Act
    String actualColumnTypeModifiers =
        SQLUtils.getColumnTypeModifiers(null, null, "Type Name", DBPDataKind.BOOLEAN);

    // Assert
    assertNull(actualColumnTypeModifiers);
  }

  /**
   * Test {@link SQLUtils#getColumnTypeModifiers(DBPDataSource, DBSTypedObject, String,
   * DBPDataKind)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#getColumnTypeModifiers(DBPDataSource, DBSTypedObject,
   * String, DBPDataKind)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String SQLUtils.getColumnTypeModifiers(DBPDataSource, DBSTypedObject, String, DBPDataKind)"
  })
  public void testGetColumnTypeModifiers_whenNull_thenReturnNull2() {
    // Arrange and Act
    String actualColumnTypeModifiers =
        SQLUtils.getColumnTypeModifiers(
            null, SimpleTypedObject.DEFAULT_TYPE, "Type Name", DBPDataKind.BOOLEAN);

    // Assert
    assertNull(actualColumnTypeModifiers);
  }

  /**
   * Test {@link SQLUtils#getScriptDescription(String)}.
   *
   * <ul>
   *   <li>When {@code create :or replaceU}.
   *   <li>Then return {@code create :or replaceU}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#getScriptDescription(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLUtils.getScriptDescription(String)"})
  public void testGetScriptDescription_whenCreateOrReplaceU_thenReturnCreateOrReplaceU() {
    // Arrange, Act and Assert
    assertEquals(" create :or replaceU", SQLUtils.getScriptDescription(" create :or replaceU"));
  }

  /**
   * Test {@link SQLUtils#getScriptDescription(String)}.
   *
   * <ul>
   *   <li>When {@code create :or replaceU}.
   *   <li>Then return {@code U}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#getScriptDescription(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLUtils.getScriptDescription(String)"})
  public void testGetScriptDescription_whenCreateOrReplaceU_thenReturnU() {
    // Arrange, Act and Assert
    assertEquals("U", SQLUtils.getScriptDescription("create :or replaceU"));
  }

  /**
   * Test {@link SQLUtils#getScriptDescription(String)}.
   *
   * <ul>
   *   <li>When {@code create :or replaceU}.
   *   <li>Then return {@code U}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#getScriptDescription(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLUtils.getScriptDescription(String)"})
  public void testGetScriptDescription_whenCreateOrReplaceU_thenReturnU2() {
    // Arrange, Act and Assert
    assertEquals("U ", SQLUtils.getScriptDescription("create :or replaceU "));
  }

  /**
   * Test {@link SQLUtils#getScriptDescription(String)}.
   *
   * <ul>
   *   <li>When {@code +}.
   *   <li>Then return {@code +}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#getScriptDescription(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLUtils.getScriptDescription(String)"})
  public void testGetScriptDescription_whenPlusSign_thenReturnPlusSign() {
    // Arrange, Act and Assert
    assertEquals(" +", SQLUtils.getScriptDescription("  +"));
  }

  /**
   * Test {@link SQLUtils#getScriptDescription(String)}.
   *
   * <ul>
   *   <li>When space space.
   *   <li>Then return space.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#getScriptDescription(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLUtils.getScriptDescription(String)"})
  public void testGetScriptDescription_whenSpaceSpace_thenReturnSpace() {
    // Arrange, Act and Assert
    assertEquals(" ", SQLUtils.getScriptDescription("  "));
  }

  /**
   * Test {@link SQLUtils#getScriptDescription(String)}.
   *
   * <ul>
   *   <li>When space.
   *   <li>Then return space.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#getScriptDescription(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLUtils.getScriptDescription(String)"})
  public void testGetScriptDescription_whenSpace_thenReturnSpace() {
    // Arrange, Act and Assert
    assertEquals(" ", SQLUtils.getScriptDescription(" "));
  }

  /**
   * Test {@link SQLUtils#getScriptDescription(String)}.
   *
   * <ul>
   *   <li>When {@code Sql}.
   *   <li>Then return {@code Sql}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#getScriptDescription(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLUtils.getScriptDescription(String)"})
  public void testGetScriptDescription_whenSql_thenReturnSql() {
    // Arrange, Act and Assert
    assertEquals("Sql", SQLUtils.getScriptDescription("Sql"));
  }

  /**
   * Test {@link SQLUtils#generateEntityAlias(DBSEntity, DBRFinder)}.
   *
   * <p>Method under test: {@link SQLUtils#generateEntityAlias(DBSEntity, DBRFinder)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLUtils.generateEntityAlias(DBSEntity, DBRFinder)"})
  public void testGenerateEntityAlias() {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.validIdentifierStart(anyChar())).thenReturn(true);

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(sqlDialect);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity entity = new DBVEntity(container, ",", "Description Column Names");

    DBRFinder<Boolean, String> aliasFinder = mock(DBRFinder.class);
    when(aliasFinder.findObject(Mockito.<String>any())).thenReturn(true);

    // Act
    String actualGenerateEntityAliasResult = SQLUtils.generateEntityAlias(entity, aliasFinder);

    // Assert
    verify(dbpDataSource).getSQLDialect();
    verify(aliasFinder, atLeast(1)).findObject(Mockito.<String>any());
    verify(sqlDialect).validIdentifierStart(',');
    verify(parent).getDataSource();
    assertEquals("t", actualGenerateEntityAliasResult);
  }

  /**
   * Test {@link SQLUtils#generateEntityAlias(DBSEntity, DBRFinder)}.
   *
   * <ul>
   *   <li>Given {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#generateEntityAlias(DBSEntity, DBRFinder)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLUtils.generateEntityAlias(DBSEntity, DBRFinder)"})
  public void testGenerateEntityAlias_givenFalse() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, "t");
    DBVEntity entity = new DBVEntity(container, "t", "t");

    DBRFinder<Boolean, String> aliasFinder = mock(DBRFinder.class);
    when(aliasFinder.findObject(Mockito.<String>any())).thenReturn(false);

    // Act
    String actualGenerateEntityAliasResult = SQLUtils.generateEntityAlias(entity, aliasFinder);

    // Assert
    verify(dbpDataSource).getSQLDialect();
    verify(aliasFinder).findObject("t");
    verify(parent).getDataSource();
    assertEquals("t", actualGenerateEntityAliasResult);
  }

  /**
   * Test {@link SQLUtils#generateEntityAlias(DBSEntity, DBRFinder)}.
   *
   * <ul>
   *   <li>Given {@link SQLDialect} {@link SQLDialect#validIdentifierStart(char)} return {@code
   *       false}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#generateEntityAlias(DBSEntity, DBRFinder)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLUtils.generateEntityAlias(DBSEntity, DBRFinder)"})
  public void testGenerateEntityAlias_givenSQLDialectValidIdentifierStartReturnFalse() {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.validIdentifierStart(anyChar())).thenReturn(false);

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(sqlDialect);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");

    DBRFinder<Boolean, String> aliasFinder = mock(DBRFinder.class);
    when(aliasFinder.findObject(Mockito.<String>any())).thenReturn(true);

    // Act
    String actualGenerateEntityAliasResult = SQLUtils.generateEntityAlias(entity, aliasFinder);

    // Assert
    verify(dbpDataSource).getSQLDialect();
    verify(aliasFinder, atLeast(1)).findObject(Mockito.<String>any());
    verify(sqlDialect, atLeast(1)).validIdentifierStart(anyChar());
    verify(parent).getDataSource();
    assertEquals("t", actualGenerateEntityAliasResult);
  }

  /**
   * Test {@link SQLUtils#generateEntityAlias(DBSEntity, DBRFinder)}.
   *
   * <ul>
   *   <li>Then calls {@link SQLDialect#getReservedWords()}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#generateEntityAlias(DBSEntity, DBRFinder)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLUtils.generateEntityAlias(DBSEntity, DBRFinder)"})
  public void testGenerateEntityAlias_thenCallsGetReservedWords() {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.validIdentifierPart(anyChar(), anyBoolean())).thenReturn(false);
    when(sqlDialect.getReservedWords()).thenReturn(new ArrayList<>());
    when(sqlDialect.validIdentifierStart(anyChar())).thenReturn(true);

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(sqlDialect);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");

    DBRFinder<Boolean, String> aliasFinder = mock(DBRFinder.class);
    when(aliasFinder.findObject(Mockito.<String>any())).thenReturn(true);

    // Act
    String actualGenerateEntityAliasResult = SQLUtils.generateEntityAlias(entity, aliasFinder);

    // Assert
    verify(dbpDataSource).getSQLDialect();
    verify(aliasFinder, atLeast(1)).findObject(Mockito.<String>any());
    verify(sqlDialect).getReservedWords();
    verify(sqlDialect, atLeast(1)).validIdentifierPart(anyChar(), eq(false));
    verify(sqlDialect).validIdentifierStart('N');
    verify(parent).getDataSource();
    assertEquals("n", actualGenerateEntityAliasResult);
  }

  /**
   * Test {@link SQLUtils#generateEntityAlias(DBSEntity, DBRFinder)}.
   *
   * <ul>
   *   <li>Then return empty string.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#generateEntityAlias(DBSEntity, DBRFinder)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLUtils.generateEntityAlias(DBSEntity, DBRFinder)"})
  public void testGenerateEntityAlias_thenReturnEmptyString() {
    // Arrange
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "", "Description Column Names");

    // Act and Assert
    assertEquals("", SQLUtils.generateEntityAlias(entity, mock(DBRFinder.class)));
  }

  /**
   * Test {@link SQLUtils#generateEntityAlias(DBSEntity, DBRFinder)}.
   *
   * <ul>
   *   <li>Then return {@code n}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#generateEntityAlias(DBSEntity, DBRFinder)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLUtils.generateEntityAlias(DBSEntity, DBRFinder)"})
  public void testGenerateEntityAlias_thenReturnN() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");

    DBRFinder<Boolean, String> aliasFinder = mock(DBRFinder.class);
    when(aliasFinder.findObject(Mockito.<String>any())).thenReturn(true);

    // Act
    String actualGenerateEntityAliasResult = SQLUtils.generateEntityAlias(entity, aliasFinder);

    // Assert
    verify(dbpDataSource).getSQLDialect();
    verify(aliasFinder, atLeast(1)).findObject(Mockito.<String>any());
    verify(parent).getDataSource();
    assertEquals("n", actualGenerateEntityAliasResult);
  }

  /**
   * Test {@link SQLUtils#generateEntityAlias(DBSEntity, DBRFinder)}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#generateEntityAlias(DBSEntity, DBRFinder)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLUtils.generateEntityAlias(DBSEntity, DBRFinder)"})
  public void testGenerateEntityAlias_thenReturnNull() {
    // Arrange
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, null, "Description Column Names");

    // Act and Assert
    assertNull(SQLUtils.generateEntityAlias(entity, mock(DBRFinder.class)));
  }

  /**
   * Test {@link SQLUtils#generateEntityAlias(DBSEntity, DBRFinder)}.
   *
   * <ul>
   *   <li>Then return {@code ojdmsdb}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#generateEntityAlias(DBSEntity, DBRFinder)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLUtils.generateEntityAlias(DBSEntity, DBRFinder)"})
  public void testGenerateEntityAlias_thenReturnOjdmsdb() {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);
    when(dbpDataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, "t");

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    DBVModel targetModel = new DBVModel(dataSourceContainer);
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "t");
    DBVEntity copy =
        new DBVEntity(container2, "org.jkiss.dbeaver.model.struct.DBSAttributeBase", "t");

    DBVEntity entity = new DBVEntity(container, copy, targetModel);
    DBVContainer container3 = new DBVContainer(mock(DBVContainer.class), "t");
    DBVEntity entity2 = new DBVEntity(container3, "t", "t");
    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity2, mock(DBVEntityAttribute.class), "Name");
    entity.addVirtualAttribute(attribute);

    DBRFinder<Boolean, String> aliasFinder = mock(DBRFinder.class);
    when(aliasFinder.findObject(Mockito.<String>any())).thenReturn(false);

    // Act
    String actualGenerateEntityAliasResult = SQLUtils.generateEntityAlias(entity, aliasFinder);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSource).getSQLDialect();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(aliasFinder).findObject("ojdmsdb");
    verify(parent, atLeast(1)).getDataSource();
    assertEquals("ojdmsdb", actualGenerateEntityAliasResult);
  }

  /**
   * Test {@link SQLUtils#generateCommentLine(DBPDataSource, String)}.
   *
   * <p>Method under test: {@link SQLUtils#generateCommentLine(DBPDataSource, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLUtils.generateCommentLine(DBPDataSource, String)"})
  public void testGenerateCommentLine() {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getSingleLineComments()).thenReturn(new String[] {});

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(sqlDialect);

    // Act
    String actualGenerateCommentLineResult = SQLUtils.generateCommentLine(dataSource, "Comment");

    // Assert
    verify(dataSource).getSQLDialect();
    verify(sqlDialect).getSingleLineComments();
    assertEquals("-- Comment\n", actualGenerateCommentLineResult);
  }

  /**
   * Test {@link SQLUtils#generateCommentLine(DBPDataSource, String)}.
   *
   * <ul>
   *   <li>Given {@link BasicSQLDialect#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#generateCommentLine(DBPDataSource, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLUtils.generateCommentLine(DBPDataSource, String)"})
  public void testGenerateCommentLine_givenInstance() {
    // Arrange
    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    // Act
    String actualGenerateCommentLineResult = SQLUtils.generateCommentLine(dataSource, "Comment");

    // Assert
    verify(dataSource).getSQLDialect();
    assertEquals("-- Comment\n", actualGenerateCommentLineResult);
  }

  /**
   * Test {@link SQLUtils#generateCommentLine(DBPDataSource, String)}.
   *
   * <ul>
   *   <li>Then calls {@link SQLDialect#getSingleLineComments()}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#generateCommentLine(DBPDataSource, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLUtils.generateCommentLine(DBPDataSource, String)"})
  public void testGenerateCommentLine_thenCallsGetSingleLineComments() {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getSingleLineComments()).thenReturn(null);

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(sqlDialect);

    // Act
    String actualGenerateCommentLineResult = SQLUtils.generateCommentLine(dataSource, "Comment");

    // Assert
    verify(dataSource).getSQLDialect();
    verify(sqlDialect).getSingleLineComments();
    assertEquals("-- Comment\n", actualGenerateCommentLineResult);
  }

  /**
   * Test {@link SQLUtils#generateCommentLine(DBPDataSource, String)}.
   *
   * <ul>
   *   <li>When empty string.
   *   <li>Then return {@code --}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#generateCommentLine(DBPDataSource, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLUtils.generateCommentLine(DBPDataSource, String)"})
  public void testGenerateCommentLine_whenEmptyString_thenReturnDashDash() {
    // Arrange, Act and Assert
    assertEquals("-- \n", SQLUtils.generateCommentLine(null, ""));
  }

  /**
   * Test {@link SQLUtils#generateParamList(int)}.
   *
   * <ul>
   *   <li>Then return {@code ?,?,?}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#generateParamList(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLUtils.generateParamList(int)"})
  public void testGenerateParamList_thenReturnQuestionMarkCommaQuestionMarkCommaQuestionMark() {
    // Arrange, Act and Assert
    assertEquals("?,?,?", SQLUtils.generateParamList(3));
  }

  /**
   * Test {@link SQLUtils#generateParamList(int)}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then return {@link SQLConstants#QUESTION}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#generateParamList(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLUtils.generateParamList(int)"})
  public void testGenerateParamList_whenOne_thenReturnQuestion() {
    // Arrange, Act and Assert
    assertEquals(SQLConstants.QUESTION, SQLUtils.generateParamList(1));
  }

  /**
   * Test {@link SQLUtils#generateParamList(int)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return empty string.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#generateParamList(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLUtils.generateParamList(int)"})
  public void testGenerateParamList_whenZero_thenReturnEmptyString() {
    // Arrange, Act and Assert
    assertEquals("", SQLUtils.generateParamList(0));
  }

  /**
   * Test {@link SQLUtils#fixLineFeeds(String)}.
   *
   * <p>Method under test: {@link SQLUtils#fixLineFeeds(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLUtils.fixLineFeeds(String)"})
  public void testFixLineFeeds() {
    // Arrange, Act and Assert
    assertEquals("Sql", SQLUtils.fixLineFeeds("Sql"));
  }

  /**
   * Test {@link SQLUtils#compareAliases(String, String)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#compareAliases(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLUtils.compareAliases(String, String)"})
  public void testCompareAliases_whenNull_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(SQLUtils.compareAliases(null, null));
  }

  /**
   * Test {@link SQLUtils#compareAliases(String, String)}.
   *
   * <ul>
   *   <li>When {@code Str1}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#compareAliases(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLUtils.compareAliases(String, String)"})
  public void testCompareAliases_whenStr1_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(SQLUtils.compareAliases("Str1", "Str2"));
  }

  /**
   * Test {@link SQLUtils#compareAliases(String, String)}.
   *
   * <ul>
   *   <li>When {@code Str1}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#compareAliases(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLUtils.compareAliases(String, String)"})
  public void testCompareAliases_whenStr1_thenReturnFalse2() {
    // Arrange, Act and Assert
    assertFalse(SQLUtils.compareAliases("Str1", null));
  }

  /**
   * Test {@link SQLUtils#compareAliases(String, String)}.
   *
   * <ul>
   *   <li>When {@code Str2}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#compareAliases(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLUtils.compareAliases(String, String)"})
  public void testCompareAliases_whenStr2_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(SQLUtils.compareAliases(null, "Str2"));
  }

  /**
   * Test {@link SQLUtils#removeExtraSpaces(String)}.
   *
   * <p>Method under test: {@link SQLUtils#removeExtraSpaces(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLUtils.removeExtraSpaces(String)"})
  public void testRemoveExtraSpaces() {
    // Arrange, Act and Assert
    assertEquals("Str", SQLUtils.removeExtraSpaces("Str"));
  }

  /**
   * Test {@link SQLUtils#generateScript(DBPDataSource, DBEPersistAction[], boolean)}.
   *
   * <p>Method under test: {@link SQLUtils#generateScript(DBPDataSource, DBEPersistAction[],
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLUtils.generateScript(DBPDataSource, DBEPersistAction[], boolean)"})
  public void testGenerateScript() {
    // Arrange
    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    // Act
    String actualGenerateScriptResult =
        SQLUtils.generateScript(
            dataSource, new DBEPersistAction[] {new SQLDatabasePersistAction(null)}, false);

    // Assert
    verify(dataSource).getSQLDialect();
    assertEquals("", actualGenerateScriptResult);
  }

  /**
   * Test {@link SQLUtils#generateScript(DBPDataSource, DBEPersistAction[], boolean)}.
   *
   * <p>Method under test: {@link SQLUtils#generateScript(DBPDataSource, DBEPersistAction[],
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLUtils.generateScript(DBPDataSource, DBEPersistAction[], boolean)"})
  public void testGenerateScript2() {
    // Arrange
    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    // Act
    String actualGenerateScriptResult =
        SQLUtils.generateScript(
            dataSource, new DBEPersistAction[] {new SQLDatabasePersistAction("")}, false);

    // Assert
    verify(dataSource).getSQLDialect();
    assertEquals("", actualGenerateScriptResult);
  }

  /**
   * Test {@link SQLUtils#generateScript(DBPDataSource, DBEPersistAction[], boolean)}.
   *
   * <ul>
   *   <li>Given {@link BasicSQLDialect#INSTANCE}.
   *   <li>Then return {@code Script;}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#generateScript(DBPDataSource, DBEPersistAction[],
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLUtils.generateScript(DBPDataSource, DBEPersistAction[], boolean)"})
  public void testGenerateScript_givenInstance_thenReturnScript() {
    // Arrange
    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    // Act
    String actualGenerateScriptResult =
        SQLUtils.generateScript(
            dataSource, new DBEPersistAction[] {new SQLDatabasePersistAction("Script")}, false);

    // Assert
    verify(dataSource).getSQLDialect();
    assertEquals("Script;\n", actualGenerateScriptResult);
  }

  /**
   * Test {@link SQLUtils#generateScript(DBPDataSource, DBEPersistAction[], boolean)}.
   *
   * <ul>
   *   <li>Given {@link BasicSQLDialect#INSTANCE}.
   *   <li>When {@code null}.
   *   <li>Then return empty string.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#generateScript(DBPDataSource, DBEPersistAction[],
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLUtils.generateScript(DBPDataSource, DBEPersistAction[], boolean)"})
  public void testGenerateScript_givenInstance_whenNull_thenReturnEmptyString() {
    // Arrange
    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    // Act
    String actualGenerateScriptResult = SQLUtils.generateScript(dataSource, null, false);

    // Assert
    verify(dataSource).getSQLDialect();
    assertEquals("", actualGenerateScriptResult);
  }

  /**
   * Test {@link SQLUtils#generateScript(DBPDataSource, DBEPersistAction[], boolean)}.
   *
   * <ul>
   *   <li>Given {@link SQLDialect} {@link SQLDialect#getScriptDelimiters()} return empty array of
   *       {@link String}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#generateScript(DBPDataSource, DBEPersistAction[],
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLUtils.generateScript(DBPDataSource, DBEPersistAction[], boolean)"})
  public void testGenerateScript_givenSQLDialectGetScriptDelimitersReturnEmptyArrayOfString() {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getScriptDelimiterRedefiner()).thenReturn("Script Delimiter Redefiner");
    when(sqlDialect.getScriptDelimiters()).thenReturn(new String[] {});

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(sqlDialect);

    // Act
    String actualGenerateScriptResult =
        SQLUtils.generateScript(
            dataSource, new DBEPersistAction[] {new SQLDatabasePersistAction("Script")}, false);

    // Assert
    verify(dataSource).getSQLDialect();
    verify(sqlDialect).getScriptDelimiterRedefiner();
    verify(sqlDialect).getScriptDelimiters();
    assertEquals("Script;\n", actualGenerateScriptResult);
  }

  /**
   * Test {@link SQLUtils#generateScript(DBPDataSource, DBEPersistAction[], boolean)}.
   *
   * <ul>
   *   <li>Given {@link SQLDialect} {@link SQLDialect#getScriptDelimiters()} return {@code null}.
   *   <li>Then return {@code Script;}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#generateScript(DBPDataSource, DBEPersistAction[],
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLUtils.generateScript(DBPDataSource, DBEPersistAction[], boolean)"})
  public void testGenerateScript_givenSQLDialectGetScriptDelimitersReturnNull_thenReturnScript() {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getScriptDelimiterRedefiner()).thenReturn("Script Delimiter Redefiner");
    when(sqlDialect.getScriptDelimiters()).thenReturn(null);

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(sqlDialect);

    // Act
    String actualGenerateScriptResult =
        SQLUtils.generateScript(
            dataSource, new DBEPersistAction[] {new SQLDatabasePersistAction("Script")}, false);

    // Assert
    verify(dataSource).getSQLDialect();
    verify(sqlDialect).getScriptDelimiterRedefiner();
    verify(sqlDialect).getScriptDelimiters();
    assertEquals("Script;\n", actualGenerateScriptResult);
  }

  /**
   * Test {@link SQLUtils#generateScript(DBPDataSource, DBEPersistAction[], boolean)}.
   *
   * <ul>
   *   <li>Then return lf lf.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#generateScript(DBPDataSource, DBEPersistAction[],
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLUtils.generateScript(DBPDataSource, DBEPersistAction[], boolean)"})
  public void testGenerateScript_thenReturnLfLf() {
    // Arrange
    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    // Act
    String actualGenerateScriptResult =
        SQLUtils.generateScript(
            dataSource, new DBEPersistAction[] {new SQLDatabasePersistAction("\n")}, false);

    // Assert
    verify(dataSource).getSQLDialect();
    assertEquals("\n\n", actualGenerateScriptResult);
  }

  /**
   * Test {@link SQLUtils#generateScript(DBPDataSource, DBEPersistAction[], boolean)}.
   *
   * <ul>
   *   <li>Then return {@code Script}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#generateScript(DBPDataSource, DBEPersistAction[],
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLUtils.generateScript(DBPDataSource, DBEPersistAction[], boolean)"})
  public void testGenerateScript_thenReturnScript() {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getScriptDelimiterRedefiner()).thenReturn("Script Delimiter Redefiner");
    when(sqlDialect.getScriptDelimiters()).thenReturn(new String[] {""});

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(sqlDialect);

    // Act
    String actualGenerateScriptResult =
        SQLUtils.generateScript(
            dataSource, new DBEPersistAction[] {new SQLDatabasePersistAction("Script")}, false);

    // Assert
    verify(dataSource).getSQLDialect();
    verify(sqlDialect).getScriptDelimiterRedefiner();
    verify(sqlDialect).getScriptDelimiters();
    assertEquals("Script\n", actualGenerateScriptResult);
  }

  /**
   * Test {@link SQLUtils#generateScript(DBPDataSource, DBEPersistAction[], boolean)}.
   *
   * <ul>
   *   <li>Then return {@code Script Script Delimiters}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#generateScript(DBPDataSource, DBEPersistAction[],
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLUtils.generateScript(DBPDataSource, DBEPersistAction[], boolean)"})
  public void testGenerateScript_thenReturnScriptScriptDelimiters() {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getScriptDelimiterRedefiner()).thenReturn("Script Delimiter Redefiner");
    when(sqlDialect.getScriptDelimiters()).thenReturn(new String[] {"Script Delimiters"});

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(sqlDialect);

    // Act
    String actualGenerateScriptResult =
        SQLUtils.generateScript(
            dataSource, new DBEPersistAction[] {new SQLDatabasePersistAction("Script")}, false);

    // Assert
    verify(dataSource).getSQLDialect();
    verify(sqlDialect).getScriptDelimiterRedefiner();
    verify(sqlDialect).getScriptDelimiters();
    assertEquals("Script Script Delimiters\n", actualGenerateScriptResult);
  }

  /**
   * Test {@link SQLUtils#generateComments(DBPDataSource, DBEPersistAction[], boolean)}.
   *
   * <p>Method under test: {@link SQLUtils#generateComments(DBPDataSource, DBEPersistAction[],
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String SQLUtils.generateComments(DBPDataSource, DBEPersistAction[], boolean)"
  })
  public void testGenerateComments() {
    // Arrange
    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    // Act
    String actualGenerateCommentsResult =
        SQLUtils.generateComments(
            dataSource, new DBEPersistAction[] {new SQLDatabasePersistAction("Script")}, false);

    // Assert
    verify(dataSource).getSQLDialect();
    assertEquals("", actualGenerateCommentsResult);
  }

  /**
   * Test {@link SQLUtils#generateComments(DBPDataSource, DBEPersistAction[], boolean)}.
   *
   * <p>Method under test: {@link SQLUtils#generateComments(DBPDataSource, DBEPersistAction[],
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String SQLUtils.generateComments(DBPDataSource, DBEPersistAction[], boolean)"
  })
  public void testGenerateComments2() {
    // Arrange
    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    // Act
    String actualGenerateCommentsResult =
        SQLUtils.generateComments(
            dataSource,
            new DBEPersistAction[] {new SQLDatabasePersistAction(null, "line.separator")},
            false);

    // Assert
    verify(dataSource).getSQLDialect();
    assertEquals("", actualGenerateCommentsResult);
  }

  /**
   * Test {@link SQLUtils#generateComments(DBPDataSource, DBEPersistAction[], boolean)}.
   *
   * <p>Method under test: {@link SQLUtils#generateComments(DBPDataSource, DBEPersistAction[],
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String SQLUtils.generateComments(DBPDataSource, DBEPersistAction[], boolean)"
  })
  public void testGenerateComments3() {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getSingleLineComments()).thenReturn(new String[] {});

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(sqlDialect);

    // Act
    String actualGenerateCommentsResult =
        SQLUtils.generateComments(
            dataSource, new DBEPersistAction[] {new SQLDatabasePersistAction("Script")}, false);

    // Assert
    verify(dataSource).getSQLDialect();
    verify(sqlDialect).getSingleLineComments();
    assertEquals("", actualGenerateCommentsResult);
  }

  /**
   * Test {@link SQLUtils#generateComments(DBPDataSource, DBEPersistAction[], boolean)}.
   *
   * <p>Method under test: {@link SQLUtils#generateComments(DBPDataSource, DBEPersistAction[],
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String SQLUtils.generateComments(DBPDataSource, DBEPersistAction[], boolean)"
  })
  public void testGenerateComments4() {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getSingleLineComments()).thenReturn(new String[] {});

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(sqlDialect);
    SQLDatabasePersistAction sqlDatabasePersistAction =
        new SQLDatabasePersistAction("Dr", null, ActionType.COMMENT);

    // Act
    String actualGenerateCommentsResult =
        SQLUtils.generateComments(
            dataSource, new DBEPersistAction[] {sqlDatabasePersistAction}, false);

    // Assert
    verify(dataSource).getSQLDialect();
    verify(sqlDialect).getSingleLineComments();
    assertEquals("", actualGenerateCommentsResult);
  }

  /**
   * Test {@link SQLUtils#generateComments(DBPDataSource, DBEPersistAction[], boolean)}.
   *
   * <ul>
   *   <li>Given {@link BasicSQLDialect#INSTANCE}.
   *   <li>When {@code null}.
   *   <li>Then return empty string.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#generateComments(DBPDataSource, DBEPersistAction[],
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String SQLUtils.generateComments(DBPDataSource, DBEPersistAction[], boolean)"
  })
  public void testGenerateComments_givenInstance_whenNull_thenReturnEmptyString() {
    // Arrange
    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    // Act
    String actualGenerateCommentsResult = SQLUtils.generateComments(dataSource, null, false);

    // Assert
    verify(dataSource).getSQLDialect();
    assertEquals("", actualGenerateCommentsResult);
  }

  /**
   * Test {@link SQLUtils#generateComments(DBPDataSource, DBEPersistAction[], boolean)}.
   *
   * <ul>
   *   <li>Given {@link SQLDialect} {@link SQLDialect#getSingleLineComments()} return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#generateComments(DBPDataSource, DBEPersistAction[],
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String SQLUtils.generateComments(DBPDataSource, DBEPersistAction[], boolean)"
  })
  public void testGenerateComments_givenSQLDialectGetSingleLineCommentsReturnNull() {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getSingleLineComments()).thenReturn(null);

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(sqlDialect);

    // Act
    String actualGenerateCommentsResult =
        SQLUtils.generateComments(
            dataSource,
            new DBEPersistAction[] {
              new SQLDatabasePersistAction("Dr", "line.separator", ActionType.COMMENT)
            },
            false);

    // Assert
    verify(dataSource).getSQLDialect();
    verify(sqlDialect).getSingleLineComments();
    assertEquals("line.separator\n", actualGenerateCommentsResult);
  }

  /**
   * Test {@link SQLUtils#generateComments(DBPDataSource, DBEPersistAction[], boolean)}.
   *
   * <ul>
   *   <li>Then return {@code -- Dr}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#generateComments(DBPDataSource, DBEPersistAction[],
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String SQLUtils.generateComments(DBPDataSource, DBEPersistAction[], boolean)"
  })
  public void testGenerateComments_thenReturnDr() {
    // Arrange
    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    // Act
    String actualGenerateCommentsResult =
        SQLUtils.generateComments(
            dataSource,
            new DBEPersistAction[] {new SQLDatabasePersistAction("Dr", "line.separator")},
            false);

    // Assert
    verify(dataSource).getSQLDialect();
    assertEquals("-- Dr\n", actualGenerateCommentsResult);
  }

  /**
   * Test {@link SQLUtils#generateComments(DBPDataSource, DBEPersistAction[], boolean)}.
   *
   * <ul>
   *   <li>Then return {@code line.separator}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#generateComments(DBPDataSource, DBEPersistAction[],
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String SQLUtils.generateComments(DBPDataSource, DBEPersistAction[], boolean)"
  })
  public void testGenerateComments_thenReturnLineSeparator() {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getSingleLineComments()).thenReturn(new String[] {});

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(sqlDialect);

    // Act
    String actualGenerateCommentsResult =
        SQLUtils.generateComments(
            dataSource,
            new DBEPersistAction[] {
              new SQLDatabasePersistAction("Dr", "line.separator", ActionType.COMMENT)
            },
            false);

    // Assert
    verify(dataSource).getSQLDialect();
    verify(sqlDialect).getSingleLineComments();
    assertEquals("line.separator\n", actualGenerateCommentsResult);
  }

  /**
   * Test {@link SQLUtils#getScriptLineDelimiter(SQLDialect)}.
   *
   * <ul>
   *   <li>Given empty array of {@link String}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#getScriptLineDelimiter(SQLDialect)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLUtils.getScriptLineDelimiter(SQLDialect)"})
  public void testGetScriptLineDelimiter_givenEmptyArrayOfString() {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getScriptDelimiters()).thenReturn(new String[] {});

    // Act
    String actualScriptLineDelimiter = SQLUtils.getScriptLineDelimiter(sqlDialect);

    // Assert
    verify(sqlDialect).getScriptDelimiters();
    assertEquals(SQLConstants.DEFAULT_STATEMENT_DELIMITER, actualScriptLineDelimiter);
  }

  /**
   * Test {@link SQLUtils#getScriptLineDelimiter(SQLDialect)}.
   *
   * <ul>
   *   <li>Given {@code null}.
   *   <li>When {@link SQLDialect} {@link SQLDialect#getScriptDelimiters()} return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#getScriptLineDelimiter(SQLDialect)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLUtils.getScriptLineDelimiter(SQLDialect)"})
  public void testGetScriptLineDelimiter_givenNull_whenSQLDialectGetScriptDelimitersReturnNull() {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getScriptDelimiters()).thenReturn(null);

    // Act
    String actualScriptLineDelimiter = SQLUtils.getScriptLineDelimiter(sqlDialect);

    // Assert
    verify(sqlDialect).getScriptDelimiters();
    assertEquals(SQLConstants.DEFAULT_STATEMENT_DELIMITER, actualScriptLineDelimiter);
  }

  /**
   * Test {@link SQLUtils#getScriptLineDelimiter(SQLDialect)}.
   *
   * <ul>
   *   <li>Then return empty string.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#getScriptLineDelimiter(SQLDialect)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLUtils.getScriptLineDelimiter(SQLDialect)"})
  public void testGetScriptLineDelimiter_thenReturnEmptyString() {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getScriptDelimiters()).thenReturn(new String[] {""});

    // Act
    String actualScriptLineDelimiter = SQLUtils.getScriptLineDelimiter(sqlDialect);

    // Assert
    verify(sqlDialect).getScriptDelimiters();
    assertEquals("", actualScriptLineDelimiter);
  }

  /**
   * Test {@link SQLUtils#getScriptLineDelimiter(SQLDialect)}.
   *
   * <ul>
   *   <li>Then return {@code Script Delimiters}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#getScriptLineDelimiter(SQLDialect)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLUtils.getScriptLineDelimiter(SQLDialect)"})
  public void testGetScriptLineDelimiter_thenReturnScriptDelimiters() {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getScriptDelimiters()).thenReturn(new String[] {"Script Delimiters"});

    // Act
    String actualScriptLineDelimiter = SQLUtils.getScriptLineDelimiter(sqlDialect);

    // Assert
    verify(sqlDialect).getScriptDelimiters();
    assertEquals(" Script Delimiters", actualScriptLineDelimiter);
  }

  /**
   * Test {@link SQLUtils#getScriptLineDelimiter(SQLDialect)}.
   *
   * <ul>
   *   <li>When {@link BasicSQLDialect#INSTANCE}.
   *   <li>Then return {@link SQLConstants#DEFAULT_STATEMENT_DELIMITER}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#getScriptLineDelimiter(SQLDialect)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLUtils.getScriptLineDelimiter(SQLDialect)"})
  public void testGetScriptLineDelimiter_whenInstance_thenReturnDefault_statement_delimiter() {
    // Arrange, Act and Assert
    assertEquals(
        SQLConstants.DEFAULT_STATEMENT_DELIMITER,
        SQLUtils.getScriptLineDelimiter(BasicSQLDialect.INSTANCE));
  }

  /**
   * Test {@link SQLUtils#splitFullIdentifier(String, char, String[][])} with {@code fullName},
   * {@code nameSeparator}, {@code quoteStrings}.
   *
   * <p>Method under test: {@link SQLUtils#splitFullIdentifier(String, char, String[][])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String[] SQLUtils.splitFullIdentifier(String, char, String[][])"})
  public void testSplitFullIdentifierWithFullNameNameSeparatorQuoteStrings() {
    // Arrange, Act and Assert
    assertArrayEquals(
        new String[] {"Dr Jane Doe"},
        SQLUtils.splitFullIdentifier(
            "Dr Jane Doe", 'A', new String[][] {new String[] {"Quote Strings", ""}}));
  }

  /**
   * Test {@link SQLUtils#splitFullIdentifier(String, char, String[][])} with {@code fullName},
   * {@code nameSeparator}, {@code quoteStrings}.
   *
   * <p>Method under test: {@link SQLUtils#splitFullIdentifier(String, char, String[][])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String[] SQLUtils.splitFullIdentifier(String, char, String[][])"})
  public void testSplitFullIdentifierWithFullNameNameSeparatorQuoteStrings2() {
    // Arrange, Act and Assert
    assertArrayEquals(
        new String[] {"Dr Jane Doe"},
        SQLUtils.splitFullIdentifier("Dr Jane Doe", 'A', new String[][] {}));
  }

  /**
   * Test {@link SQLUtils#splitFullIdentifier(String, char, String[][])} with {@code fullName},
   * {@code nameSeparator}, {@code quoteStrings}.
   *
   * <p>Method under test: {@link SQLUtils#splitFullIdentifier(String, char, String[][])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String[] SQLUtils.splitFullIdentifier(String, char, String[][])"})
  public void testSplitFullIdentifierWithFullNameNameSeparatorQuoteStrings3() {
    // Arrange, Act and Assert
    assertArrayEquals(
        new String[] {"Prof ", "lbert Einstein"},
        SQLUtils.splitFullIdentifier(
            "Prof Albert Einstein", 'A', new String[][] {new String[] {"Quote Strings", ""}}));
  }

  /**
   * Test {@link SQLUtils#splitFullIdentifier(String, char, String[][])} with {@code fullName},
   * {@code nameSeparator}, {@code quoteStrings}.
   *
   * <p>Method under test: {@link SQLUtils#splitFullIdentifier(String, char, String[][])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String[] SQLUtils.splitFullIdentifier(String, char, String[][])"})
  public void testSplitFullIdentifierWithFullNameNameSeparatorQuoteStrings4() {
    // Arrange, Act and Assert
    assertArrayEquals(
        new String[] {"Mr John Smith"},
        SQLUtils.splitFullIdentifier(
            "Mr John Smith", 'A', new String[][] {new String[] {"Quote Strings", ""}}));
  }

  /**
   * Test {@link SQLUtils#splitFullIdentifier(String, char, String[][])} with {@code fullName},
   * {@code nameSeparator}, {@code quoteStrings}.
   *
   * <p>Method under test: {@link SQLUtils#splitFullIdentifier(String, char, String[][])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String[] SQLUtils.splitFullIdentifier(String, char, String[][])"})
  public void testSplitFullIdentifierWithFullNameNameSeparatorQuoteStrings5() {
    // Arrange, Act and Assert
    assertArrayEquals(
        new String[] {"Dr Jane Doe"},
        SQLUtils.splitFullIdentifier("Dr Jane Doe", 'A', new String[][] {new String[] {"", ""}}));
  }

  /**
   * Test {@link SQLUtils#splitFullIdentifier(String, char, String[][])} with {@code fullName},
   * {@code nameSeparator}, {@code quoteStrings}.
   *
   * <p>Method under test: {@link SQLUtils#splitFullIdentifier(String, char, String[][])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String[] SQLUtils.splitFullIdentifier(String, char, String[][])"})
  public void testSplitFullIdentifierWithFullNameNameSeparatorQuoteStrings6() {
    // Arrange, Act and Assert
    assertArrayEquals(
        new String[] {"Prof ", "lbert Einstein"},
        SQLUtils.splitFullIdentifier(
            "Prof Albert Einstein", 'A', new String[][] {new String[] {"", ""}}));
  }

  /**
   * Test {@link SQLUtils#splitFullIdentifier(String, char, String[][])} with {@code fullName},
   * {@code nameSeparator}, {@code quoteStrings}.
   *
   * <p>Method under test: {@link SQLUtils#splitFullIdentifier(String, char, String[][])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String[] SQLUtils.splitFullIdentifier(String, char, String[][])"})
  public void testSplitFullIdentifierWithFullNameNameSeparatorQuoteStrings7() {
    // Arrange, Act and Assert
    assertArrayEquals(
        new String[] {"Prof ", "lbert Einstein"},
        SQLUtils.splitFullIdentifier(
            "Prof Albert Einstein",
            'A',
            new String[][] {new String[] {"Quote Strings", "Quote Strings"}}));
  }

  /**
   * Test {@link SQLUtils#splitFullIdentifier(String, char, String[][])} with {@code fullName},
   * {@code nameSeparator}, {@code quoteStrings}.
   *
   * <p>Method under test: {@link SQLUtils#splitFullIdentifier(String, char, String[][])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String[] SQLUtils.splitFullIdentifier(String, char, String[][])"})
  public void testSplitFullIdentifierWithFullNameNameSeparatorQuoteStrings8() {
    // Arrange, Act and Assert
    assertArrayEquals(
        new String[] {"Dr Jane Doe"},
        SQLUtils.splitFullIdentifier("Dr Jane Doe", 'A', new String[][] {new String[] {"", "42"}}));
  }

  /**
   * Test {@link SQLUtils#splitFullIdentifier(String, String, String[][], boolean)} with {@code
   * fullName}, {@code nameSeparator}, {@code quoteStrings}, {@code keepQuotes}.
   *
   * <p>Method under test: {@link SQLUtils#splitFullIdentifier(String, String, String[][], boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String[] SQLUtils.splitFullIdentifier(String, String, String[][], boolean)"})
  public void testSplitFullIdentifierWithFullNameNameSeparatorQuoteStringsKeepQuotes() {
    // Arrange, Act and Assert
    assertArrayEquals(
        new String[] {"Dr Jane Doe"},
        SQLUtils.splitFullIdentifier(
            "Dr Jane Doe",
            "Name Separator",
            new String[][] {new String[] {"Quote Strings"}},
            true));
  }

  /**
   * Test {@link SQLUtils#splitFullIdentifier(String, String, String[][], boolean)} with {@code
   * fullName}, {@code nameSeparator}, {@code quoteStrings}, {@code keepQuotes}.
   *
   * <p>Method under test: {@link SQLUtils#splitFullIdentifier(String, String, String[][], boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String[] SQLUtils.splitFullIdentifier(String, String, String[][], boolean)"})
  public void testSplitFullIdentifierWithFullNameNameSeparatorQuoteStringsKeepQuotes2() {
    // Arrange, Act and Assert
    assertArrayEquals(
        new String[] {"Dr Jane Doe"},
        SQLUtils.splitFullIdentifier(
            "Dr Jane Doe",
            "Name Separator",
            new String[][] {new String[] {"Quote Strings", ""}},
            false));
  }

  /**
   * Test {@link SQLUtils#splitFullIdentifier(String, String, String[][], boolean)} with {@code
   * fullName}, {@code nameSeparator}, {@code quoteStrings}, {@code keepQuotes}.
   *
   * <p>Method under test: {@link SQLUtils#splitFullIdentifier(String, String, String[][], boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String[] SQLUtils.splitFullIdentifier(String, String, String[][], boolean)"})
  public void testSplitFullIdentifierWithFullNameNameSeparatorQuoteStringsKeepQuotes3() {
    // Arrange, Act and Assert
    assertArrayEquals(
        new String[] {"D", "r", " ", "J", "a", "n", "e", " ", "D", "o", "e"},
        SQLUtils.splitFullIdentifier("Dr Jane Doe", "", new String[][] {}, true));
  }

  /**
   * Test {@link SQLUtils#splitFullIdentifier(String, String, String[][], boolean)} with {@code
   * fullName}, {@code nameSeparator}, {@code quoteStrings}, {@code keepQuotes}.
   *
   * <p>Method under test: {@link SQLUtils#splitFullIdentifier(String, String, String[][], boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String[] SQLUtils.splitFullIdentifier(String, String, String[][], boolean)"})
  public void testSplitFullIdentifierWithFullNameNameSeparatorQuoteStringsKeepQuotes4() {
    // Arrange, Act and Assert
    assertEquals(
        0,
        SQLUtils.splitFullIdentifier("", "", new String[][] {new String[] {"Quote Strings"}}, true)
            .length);
  }

  /**
   * Test {@link SQLUtils#splitFullIdentifier(String, String, String[][], boolean)} with {@code
   * fullName}, {@code nameSeparator}, {@code quoteStrings}, {@code keepQuotes}.
   *
   * <p>Method under test: {@link SQLUtils#splitFullIdentifier(String, String, String[][], boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String[] SQLUtils.splitFullIdentifier(String, String, String[][], boolean)"})
  public void testSplitFullIdentifierWithFullNameNameSeparatorQuoteStringsKeepQuotes5() {
    // Arrange, Act and Assert
    assertArrayEquals(
        new String[] {"Mr John Smith"},
        SQLUtils.splitFullIdentifier(
            "Mr John Smith",
            "Name Separator",
            new String[][] {new String[] {"Quote Strings", ""}},
            false));
  }

  /**
   * Test {@link SQLUtils#splitFullIdentifier(String, String, String[][], boolean)} with {@code
   * fullName}, {@code nameSeparator}, {@code quoteStrings}, {@code keepQuotes}.
   *
   * <p>Method under test: {@link SQLUtils#splitFullIdentifier(String, String, String[][], boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String[] SQLUtils.splitFullIdentifier(String, String, String[][], boolean)"})
  public void testSplitFullIdentifierWithFullNameNameSeparatorQuoteStringsKeepQuotes6() {
    // Arrange, Act and Assert
    assertArrayEquals(
        new String[] {"Dr Jane Doe"},
        SQLUtils.splitFullIdentifier(
            "Dr Jane Doe", "Name Separator", new String[][] {new String[] {"", ""}}, false));
  }

  /**
   * Test {@link SQLUtils#splitFullIdentifier(String, String, String[][], boolean)} with {@code
   * fullName}, {@code nameSeparator}, {@code quoteStrings}, {@code keepQuotes}.
   *
   * <p>Method under test: {@link SQLUtils#splitFullIdentifier(String, String, String[][], boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String[] SQLUtils.splitFullIdentifier(String, String, String[][], boolean)"})
  public void testSplitFullIdentifierWithFullNameNameSeparatorQuoteStringsKeepQuotes7() {
    // Arrange, Act and Assert
    assertArrayEquals(
        new String[] {"Dr Jane Doe"},
        SQLUtils.splitFullIdentifier(
            "Dr Jane Doe", "Name Separator", new String[][] {new String[] {"", "42"}}, false));
  }

  /**
   * Test {@link SQLUtils#splitFullIdentifier(String, String, String[][], boolean)} with {@code
   * fullName}, {@code nameSeparator}, {@code quoteStrings}, {@code keepQuotes}.
   *
   * <ul>
   *   <li>When {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#splitFullIdentifier(String, String, String[][], boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String[] SQLUtils.splitFullIdentifier(String, String, String[][], boolean)"})
  public void testSplitFullIdentifierWithFullNameNameSeparatorQuoteStringsKeepQuotes_whenNull() {
    // Arrange and Act
    String[] actualSplitFullIdentifierResult =
        SQLUtils.splitFullIdentifier("Dr Jane Doe", "Name Separator", null, false);

    // Assert
    assertArrayEquals(new String[] {"Dr Jane Doe"}, actualSplitFullIdentifierResult);
  }

  /**
   * Test {@link SQLUtils#splitFullIdentifier(String, char, String[][])} with {@code fullName},
   * {@code nameSeparator}, {@code quoteStrings}.
   *
   * <ul>
   *   <li>When {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#splitFullIdentifier(String, char, String[][])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String[] SQLUtils.splitFullIdentifier(String, char, String[][])"})
  public void testSplitFullIdentifierWithFullNameNameSeparatorQuoteStrings_whenNull() {
    // Arrange, Act and Assert
    assertArrayEquals(
        new String[] {"Dr Jane Doe"}, SQLUtils.splitFullIdentifier("Dr Jane Doe", 'A', null));
  }

  /**
   * Test {@link SQLUtils#generateTableJoin(DBRProgressMonitor, DBSEntity, String, DBSEntity,
   * String)}.
   *
   * <ul>
   *   <li>Then throw {@link DBException}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#generateTableJoin(DBRProgressMonitor, DBSEntity, String,
   * DBSEntity, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String SQLUtils.generateTableJoin(DBRProgressMonitor, DBSEntity, String, DBSEntity, String)"
  })
  public void testGenerateTableJoin_thenThrowDBException() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getRealContainer(Mockito.<DBRProgressMonitor>any()))
        .thenThrow(new DBException("An error occurred"));
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity leftTable = new DBVEntity(container, "Name", "Description Column Names");
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity rightTable = new DBVEntity(container2, "Name", "Description Column Names");

    // Act and Assert
    assertThrows(
        DBException.class,
        () ->
            SQLUtils.generateTableJoin(
                monitor, leftTable, "Left Alias", rightTable, "Right Alias"));
    verify(parent).getRealContainer(isA(DBRProgressMonitor.class));
  }

  /**
   * Test {@link SQLUtils#getTableAlias(DBSEntity)}.
   *
   * <ul>
   *   <li>Then return {@code Name}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#getTableAlias(DBSEntity)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLUtils.getTableAlias(DBSEntity)"})
  public void testGetTableAlias_thenReturnName() {
    // Arrange
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity table = new DBVEntity(container, "Name", "Description Column Names");

    // Act and Assert
    assertEquals("Name", SQLUtils.getTableAlias(table));
  }

  /**
   * Test {@link SQLUtils#getTableAlias(DBSEntity)}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#getTableAlias(DBSEntity)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLUtils.getTableAlias(DBSEntity)"})
  public void testGetTableAlias_thenReturnNull() {
    // Arrange
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity table = new DBVEntity(container, null, "Description Column Names");

    // Act and Assert
    assertNull(SQLUtils.getTableAlias(table));
  }

  /**
   * Test {@link SQLUtils#getTableAlias(DBSEntity)}.
   *
   * <ul>
   *   <li>Then return {@code _}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#getTableAlias(DBSEntity)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLUtils.getTableAlias(DBSEntity)"})
  public void testGetTableAlias_thenReturnUnderscore() {
    // Arrange
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity table = new DBVEntity(container, ",", "Description Column Names");

    // Act and Assert
    assertEquals("_", SQLUtils.getTableAlias(table));
  }

  /**
   * Test {@link SQLUtils#appendQueryConditions(DBPDataSource, StringBuilder, String,
   * DBDDataFilter)}.
   *
   * <ul>
   *   <li>Then {@link StringBuilder#StringBuilder(String)} with {@code foo} toString is {@code
   *       foo}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#appendQueryConditions(DBPDataSource, StringBuilder,
   * String, DBDDataFilter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLUtils.appendQueryConditions(DBPDataSource, StringBuilder, String, DBDDataFilter)"
  })
  public void testAppendQueryConditions_thenStringBuilderWithFooToStringIsFoo() throws DBException {
    // Arrange
    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);
    StringBuilder query = new StringBuilder("foo");

    // Act
    SQLUtils.appendQueryConditions(dataSource, query, "Table Alias", new DBDDataFilter());

    // Assert that nothing has changed
    verify(dataSource).getSQLDialect();
    assertEquals("foo", query.toString());
  }

  /**
   * Test {@link SQLUtils#appendQueryConditions(DBPDataSource, StringBuilder, String,
   * DBDDataFilter)}.
   *
   * <ul>
   *   <li>Then {@link StringBuilder#StringBuilder(String)} with {@code foo} toString is {@code foo
   *       WHERE Where}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#appendQueryConditions(DBPDataSource, StringBuilder,
   * String, DBDDataFilter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLUtils.appendQueryConditions(DBPDataSource, StringBuilder, String, DBDDataFilter)"
  })
  public void testAppendQueryConditions_thenStringBuilderWithFooToStringIsFooWhereWhere()
      throws DBException {
    // Arrange
    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);
    StringBuilder query = new StringBuilder("foo");

    DBDDataFilter dataFilter = new DBDDataFilter();
    dataFilter.setWhere("Where");

    // Act
    SQLUtils.appendQueryConditions(dataSource, query, "Table Alias", dataFilter);

    // Assert
    verify(dataSource).getSQLDialect();
    assertEquals("foo\nWHERE Where", query.toString());
  }

  /**
   * Test {@link SQLUtils#appendQueryConditions(DBPDataSource, StringBuilder, String,
   * DBDDataFilter)}.
   *
   * <ul>
   *   <li>Then throw {@link DBException}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#appendQueryConditions(DBPDataSource, StringBuilder,
   * String, DBDDataFilter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLUtils.appendQueryConditions(DBPDataSource, StringBuilder, String, DBDDataFilter)"
  })
  public void testAppendQueryConditions_thenThrowDBException() throws DBException {
    // Arrange
    SQLQueryGenerator sqlQueryGenerator = mock(SQLQueryGenerator.class);
    doThrow(new DBException("An error occurred"))
        .when(sqlQueryGenerator)
        .appendQueryConditions(
            Mockito.<DBPDataSource>any(),
            Mockito.<StringBuilder>any(),
            Mockito.<String>any(),
            Mockito.<DBDDataFilter>any());

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getQueryGenerator()).thenReturn(sqlQueryGenerator);

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(sqlDialect);
    StringBuilder query = new StringBuilder("foo");

    // Act and Assert
    assertThrows(
        DBException.class,
        () ->
            SQLUtils.appendQueryConditions(dataSource, query, "Table Alias", new DBDDataFilter()));
    verify(dataSource).getSQLDialect();
    verify(sqlDialect).getQueryGenerator();
    verify(sqlQueryGenerator)
        .appendQueryConditions(
            isA(DBPDataSource.class),
            isA(StringBuilder.class),
            eq("Table Alias"),
            isA(DBDDataFilter.class));
  }

  /**
   * Test {@link SQLUtils#appendQueryConditions(DBPDataSource, StringBuilder, String,
   * DBDDataFilter)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then {@link StringBuilder#StringBuilder(String)} with {@code foo} toString is {@code
   *       foo}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#appendQueryConditions(DBPDataSource, StringBuilder,
   * String, DBDDataFilter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLUtils.appendQueryConditions(DBPDataSource, StringBuilder, String, DBDDataFilter)"
  })
  public void testAppendQueryConditions_whenNull_thenStringBuilderWithFooToStringIsFoo()
      throws DBException {
    // Arrange
    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);
    StringBuilder query = new StringBuilder("foo");

    // Act
    SQLUtils.appendQueryConditions(dataSource, query, "Table Alias", null);

    // Assert that nothing has changed
    verify(dataSource).getSQLDialect();
    assertEquals("foo", query.toString());
  }

  /**
   * Test {@link SQLUtils#appendQueryOrder(DBPDataSource, StringBuilder, String, DBDDataFilter)}.
   *
   * <ul>
   *   <li>Then {@link StringBuilder#StringBuilder(String)} with {@code foo} toString is {@code foo
   *       ORDER BY Order}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#appendQueryOrder(DBPDataSource, StringBuilder, String,
   * DBDDataFilter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLUtils.appendQueryOrder(DBPDataSource, StringBuilder, String, DBDDataFilter)"
  })
  public void testAppendQueryOrder_thenStringBuilderWithFooToStringIsFooOrderByOrder() {
    // Arrange
    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);
    StringBuilder query = new StringBuilder("foo");

    DBDDataFilter dataFilter = new DBDDataFilter();
    dataFilter.setOrder("Order");

    // Act
    SQLUtils.appendQueryOrder(dataSource, query, "Table Alias", dataFilter);

    // Assert
    verify(dataSource).getSQLDialect();
    assertEquals("foo\nORDER BY Order", query.toString());
  }

  /**
   * Test {@link SQLUtils#appendQueryOrder(DBPDataSource, StringBuilder, String, DBDDataFilter)}.
   *
   * <ul>
   *   <li>When {@link DBDDataFilter#DBDDataFilter()}.
   *   <li>Then {@link StringBuilder#StringBuilder(String)} with {@code foo} toString is {@code
   *       foo}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#appendQueryOrder(DBPDataSource, StringBuilder, String,
   * DBDDataFilter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLUtils.appendQueryOrder(DBPDataSource, StringBuilder, String, DBDDataFilter)"
  })
  public void testAppendQueryOrder_whenDBDDataFilter_thenStringBuilderWithFooToStringIsFoo() {
    // Arrange
    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);
    StringBuilder query = new StringBuilder("foo");

    // Act
    SQLUtils.appendQueryOrder(dataSource, query, "Table Alias", new DBDDataFilter());

    // Assert that nothing has changed
    verify(dataSource).getSQLDialect();
    assertEquals("foo", query.toString());
  }

  /**
   * Test {@link SQLUtils#appendQueryOrder(DBPDataSource, StringBuilder, String, DBDDataFilter)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then {@link StringBuilder#StringBuilder(String)} with {@code foo} toString is {@code
   *       foo}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#appendQueryOrder(DBPDataSource, StringBuilder, String,
   * DBDDataFilter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLUtils.appendQueryOrder(DBPDataSource, StringBuilder, String, DBDDataFilter)"
  })
  public void testAppendQueryOrder_whenNull_thenStringBuilderWithFooToStringIsFoo() {
    // Arrange
    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);
    StringBuilder query = new StringBuilder("foo");

    // Act
    SQLUtils.appendQueryOrder(dataSource, query, "Table Alias", null);

    // Assert that nothing has changed
    verify(dataSource).getSQLDialect();
    assertEquals("foo", query.toString());
  }

  /**
   * Test {@link SQLUtils#isExecQuery(SQLDialect, String)}.
   *
   * <ul>
   *   <li>Given array of {@link String} with {@code foo} and empty string.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#isExecQuery(SQLDialect, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLUtils.isExecQuery(SQLDialect, String)"})
  public void testIsExecQuery_givenArrayOfStringWithFooAndEmptyString() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getSingleLineComments()).thenReturn(new String[] {"foo", ""});
    when(dialect.getMultiLineComments()).thenReturn(new Pair<>("First", "Second"));
    when(dialect.getExecuteKeywords()).thenReturn(new String[] {"Execute Keywords"});

    // Act
    boolean actualIsExecQueryResult = SQLUtils.isExecQuery(dialect, "Query");

    // Assert
    verify(dialect, atLeast(1)).getExecuteKeywords();
    verify(dialect).getMultiLineComments();
    verify(dialect).getSingleLineComments();
    assertFalse(actualIsExecQueryResult);
  }

  /**
   * Test {@link SQLUtils#isExecQuery(SQLDialect, String)}.
   *
   * <ul>
   *   <li>Given {@code null}.
   *   <li>When {@link SQLDialect} {@link SQLDialect#getExecuteKeywords()} return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#isExecQuery(SQLDialect, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLUtils.isExecQuery(SQLDialect, String)"})
  public void testIsExecQuery_givenNull_whenSQLDialectGetExecuteKeywordsReturnNull() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getExecuteKeywords()).thenReturn(null);

    // Act
    boolean actualIsExecQueryResult = SQLUtils.isExecQuery(dialect, "Query");

    // Assert
    verify(dialect).getExecuteKeywords();
    assertFalse(actualIsExecQueryResult);
  }

  /**
   * Test {@link SQLUtils#isExecQuery(SQLDialect, String)}.
   *
   * <ul>
   *   <li>Given {@code null}.
   *   <li>When {@link SQLDialect} {@link SQLDialect#getMultiLineComments()} return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#isExecQuery(SQLDialect, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLUtils.isExecQuery(SQLDialect, String)"})
  public void testIsExecQuery_givenNull_whenSQLDialectGetMultiLineCommentsReturnNull() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getSingleLineComments()).thenReturn(new String[] {"Single Line Comments"});
    when(dialect.getMultiLineComments()).thenReturn(null);
    when(dialect.getExecuteKeywords()).thenReturn(new String[] {"Execute Keywords"});

    // Act
    boolean actualIsExecQueryResult = SQLUtils.isExecQuery(dialect, "Query");

    // Assert
    verify(dialect, atLeast(1)).getExecuteKeywords();
    verify(dialect).getMultiLineComments();
    verify(dialect).getSingleLineComments();
    assertFalse(actualIsExecQueryResult);
  }

  /**
   * Test {@link SQLUtils#isExecQuery(SQLDialect, String)}.
   *
   * <ul>
   *   <li>Given {@link Pair#Pair(Object, Object)} with {@code First} and {@code Second}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#isExecQuery(SQLDialect, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLUtils.isExecQuery(SQLDialect, String)"})
  public void testIsExecQuery_givenPairWithFirstAndSecond() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getSingleLineComments()).thenReturn(new String[] {"Single Line Comments"});
    when(dialect.getMultiLineComments()).thenReturn(new Pair<>("First", "Second"));
    when(dialect.getExecuteKeywords()).thenReturn(new String[] {"Execute Keywords"});

    // Act
    boolean actualIsExecQueryResult = SQLUtils.isExecQuery(dialect, "Query");

    // Assert
    verify(dialect, atLeast(1)).getExecuteKeywords();
    verify(dialect).getMultiLineComments();
    verify(dialect).getSingleLineComments();
    assertFalse(actualIsExecQueryResult);
  }

  /**
   * Test {@link SQLUtils#isExecQuery(SQLDialect, String)}.
   *
   * <ul>
   *   <li>Given {@link Pair#Pair(Object, Object)} with {@code First} and second is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#isExecQuery(SQLDialect, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLUtils.isExecQuery(SQLDialect, String)"})
  public void testIsExecQuery_givenPairWithFirstAndSecondIsNull() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getSingleLineComments()).thenReturn(new String[] {"Single Line Comments"});
    Pair<String, String> pair = new Pair<>("First", null);
    when(dialect.getMultiLineComments()).thenReturn(pair);
    when(dialect.getExecuteKeywords()).thenReturn(new String[] {"Execute Keywords"});

    // Act
    boolean actualIsExecQueryResult = SQLUtils.isExecQuery(dialect, "Query");

    // Assert
    verify(dialect, atLeast(1)).getExecuteKeywords();
    verify(dialect).getMultiLineComments();
    verify(dialect).getSingleLineComments();
    assertFalse(actualIsExecQueryResult);
  }

  /**
   * Test {@link SQLUtils#isExecQuery(SQLDialect, String)}.
   *
   * <ul>
   *   <li>Given {@link Pair#Pair(Object, Object)} with {@code First} and {@code Second}.
   *   <li>When empty string.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#isExecQuery(SQLDialect, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLUtils.isExecQuery(SQLDialect, String)"})
  public void testIsExecQuery_givenPairWithFirstAndSecond_whenEmptyString() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getSingleLineComments()).thenReturn(new String[] {"Single Line Comments"});
    when(dialect.getMultiLineComments()).thenReturn(new Pair<>("First", "Second"));
    when(dialect.getExecuteKeywords()).thenReturn(new String[] {"Execute Keywords"});

    // Act
    boolean actualIsExecQueryResult = SQLUtils.isExecQuery(dialect, "");

    // Assert
    verify(dialect, atLeast(1)).getExecuteKeywords();
    verify(dialect).getMultiLineComments();
    verify(dialect).getSingleLineComments();
    assertFalse(actualIsExecQueryResult);
  }

  /**
   * Test {@link SQLUtils#isExecQuery(SQLDialect, String)}.
   *
   * <ul>
   *   <li>Given {@link Pair#Pair(Object, Object)} with {@code First} and {@code Second}.
   *   <li>When {@code %}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#isExecQuery(SQLDialect, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLUtils.isExecQuery(SQLDialect, String)"})
  public void testIsExecQuery_givenPairWithFirstAndSecond_whenPercentSign() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getSingleLineComments()).thenReturn(new String[] {"Single Line Comments"});
    when(dialect.getMultiLineComments()).thenReturn(new Pair<>("First", "Second"));
    when(dialect.getExecuteKeywords()).thenReturn(new String[] {"Execute Keywords"});

    // Act
    boolean actualIsExecQueryResult = SQLUtils.isExecQuery(dialect, "%");

    // Assert
    verify(dialect, atLeast(1)).getExecuteKeywords();
    verify(dialect).getMultiLineComments();
    verify(dialect).getSingleLineComments();
    assertFalse(actualIsExecQueryResult);
  }

  /**
   * Test {@link SQLUtils#isExecQuery(SQLDialect, String)}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#isExecQuery(SQLDialect, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLUtils.isExecQuery(SQLDialect, String)"})
  public void testIsExecQuery_thenReturnTrue() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getSingleLineComments()).thenReturn(new String[] {"foo", ""});
    when(dialect.getMultiLineComments()).thenReturn(new Pair<>("First", "Second"));
    when(dialect.getExecuteKeywords()).thenReturn(new String[] {"foo", ""});

    // Act
    boolean actualIsExecQueryResult = SQLUtils.isExecQuery(dialect, "Query");

    // Assert
    verify(dialect, atLeast(1)).getExecuteKeywords();
    verify(dialect).getMultiLineComments();
    verify(dialect).getSingleLineComments();
    assertTrue(actualIsExecQueryResult);
  }

  /**
   * Test {@link SQLUtils#isExecQuery(SQLDialect, String)}.
   *
   * <ul>
   *   <li>When {@link BasicSQLDialect#INSTANCE}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#isExecQuery(SQLDialect, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLUtils.isExecQuery(SQLDialect, String)"})
  public void testIsExecQuery_whenInstance_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(SQLUtils.isExecQuery(BasicSQLDialect.INSTANCE, "Query"));
  }

  /**
   * Test {@link SQLUtils#isExecKeyword(SQLDialect, String)}.
   *
   * <ul>
   *   <li>Given array of {@link String} with {@code Execute Keywords}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#isExecKeyword(SQLDialect, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLUtils.isExecKeyword(SQLDialect, String)"})
  public void testIsExecKeyword_givenArrayOfStringWithExecuteKeywords() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getExecuteKeywords()).thenReturn(new String[] {"Execute Keywords"});

    // Act
    boolean actualIsExecKeywordResult = SQLUtils.isExecKeyword(dialect, "Word");

    // Assert
    verify(dialect).getExecuteKeywords();
    assertFalse(actualIsExecKeywordResult);
  }

  /**
   * Test {@link SQLUtils#isExecKeyword(SQLDialect, String)}.
   *
   * <ul>
   *   <li>Given array of {@link String} with {@code foo} and {@code 42}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#isExecKeyword(SQLDialect, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLUtils.isExecKeyword(SQLDialect, String)"})
  public void testIsExecKeyword_givenArrayOfStringWithFooAnd42_thenReturnTrue() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getExecuteKeywords()).thenReturn(new String[] {"foo", "42"});

    // Act
    boolean actualIsExecKeywordResult = SQLUtils.isExecKeyword(dialect, "42");

    // Assert
    verify(dialect).getExecuteKeywords();
    assertTrue(actualIsExecKeywordResult);
  }

  /**
   * Test {@link SQLUtils#isExecKeyword(SQLDialect, String)}.
   *
   * <ul>
   *   <li>Given {@code null}.
   *   <li>When {@link SQLDialect} {@link SQLDialect#getExecuteKeywords()} return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#isExecKeyword(SQLDialect, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLUtils.isExecKeyword(SQLDialect, String)"})
  public void testIsExecKeyword_givenNull_whenSQLDialectGetExecuteKeywordsReturnNull() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getExecuteKeywords()).thenReturn(null);

    // Act
    boolean actualIsExecKeywordResult = SQLUtils.isExecKeyword(dialect, "Word");

    // Assert
    verify(dialect).getExecuteKeywords();
    assertFalse(actualIsExecKeywordResult);
  }

  /**
   * Test {@link SQLUtils#isExecKeyword(SQLDialect, String)}.
   *
   * <ul>
   *   <li>When {@link BasicSQLDialect#INSTANCE}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#isExecKeyword(SQLDialect, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLUtils.isExecKeyword(SQLDialect, String)"})
  public void testIsExecKeyword_whenInstance_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(SQLUtils.isExecKeyword(BasicSQLDialect.INSTANCE, "Word"));
  }

  /**
   * Test {@link SQLUtils#stripColumnTypeModifiers(String)}.
   *
   * <ul>
   *   <li>Then return empty string.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#stripColumnTypeModifiers(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLUtils.stripColumnTypeModifiers(String)"})
  public void testStripColumnTypeModifiers_thenReturnEmptyString() {
    // Arrange, Act and Assert
    assertEquals("", SQLUtils.stripColumnTypeModifiers("()"));
  }

  /**
   * Test {@link SQLUtils#stripColumnTypeModifiers(String)}.
   *
   * <ul>
   *   <li>When {@code (}.
   *   <li>Then return {@code (}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#stripColumnTypeModifiers(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLUtils.stripColumnTypeModifiers(String)"})
  public void testStripColumnTypeModifiers_whenLeftParenthesis_thenReturnLeftParenthesis() {
    // Arrange, Act and Assert
    assertEquals("(", SQLUtils.stripColumnTypeModifiers("("));
  }

  /**
   * Test {@link SQLUtils#stripColumnTypeModifiers(String)}.
   *
   * <ul>
   *   <li>When {@code Type}.
   *   <li>Then return {@code Type}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#stripColumnTypeModifiers(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLUtils.stripColumnTypeModifiers(String)"})
  public void testStripColumnTypeModifiers_whenType_thenReturnType() {
    // Arrange, Act and Assert
    assertEquals("Type", SQLUtils.stripColumnTypeModifiers("Type"));
  }

  /**
   * Test {@link SQLUtils#fillQueryParameters(SQLQuery, List)}.
   *
   * <p>Method under test: {@link SQLUtils#fillQueryParameters(SQLQuery, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLUtils.fillQueryParameters(SQLQuery, List)"})
  public void testFillQueryParameters() {
    // Arrange
    SQLQuery sqlStatement = mock(SQLQuery.class);
    when(sqlStatement.getText()).thenReturn("Text");
    doNothing().when(sqlStatement).setText(Mockito.<String>any());

    ArrayList<SQLQueryParameter> parameters = new ArrayList<>();
    SQLQueryParameter sqlQueryParameter = new SQLQueryParameter(null, 1, "Name", "Original Name");
    parameters.add(sqlQueryParameter);

    // Act
    SQLUtils.fillQueryParameters(sqlStatement, parameters);

    // Assert
    verify(sqlStatement).getText();
    verify(sqlStatement).setText("NULLText");
  }

  /**
   * Test {@link SQLUtils#fillQueryParameters(SQLQuery, List)}.
   *
   * <p>Method under test: {@link SQLUtils#fillQueryParameters(SQLQuery, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLUtils.fillQueryParameters(SQLQuery, List)"})
  public void testFillQueryParameters2() {
    // Arrange
    SQLQuery sqlStatement = mock(SQLQuery.class);
    when(sqlStatement.getText()).thenReturn("Text");
    doNothing().when(sqlStatement).setText(Mockito.<String>any());

    SQLQueryParameter sqlQueryParameter = mock(SQLQueryParameter.class);
    when(sqlQueryParameter.getValue()).thenReturn("");
    when(sqlQueryParameter.getTokenLength()).thenReturn(3);
    when(sqlQueryParameter.getTokenOffset()).thenReturn(1);

    ArrayList<SQLQueryParameter> parameters = new ArrayList<>();
    SQLQueryParameter sqlQueryParameter2 =
        new SQLQueryParameter(null, 1, SQLConstants.KEYWORD_NULL, SQLConstants.KEYWORD_NULL);
    parameters.add(sqlQueryParameter2);
    parameters.add(sqlQueryParameter);

    // Act
    SQLUtils.fillQueryParameters(sqlStatement, parameters);

    // Assert
    verify(sqlStatement).getText();
    verify(sqlStatement).setText("NULLTNULL");
    verify(sqlQueryParameter).getTokenLength();
    verify(sqlQueryParameter, atLeast(1)).getTokenOffset();
    verify(sqlQueryParameter).getValue();
  }

  /**
   * Test {@link SQLUtils#fillQueryParameters(SQLQuery, List)}.
   *
   * <ul>
   *   <li>Given {@link SQLQueryParameter} {@link SQLQueryParameter#getValue()} return {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#fillQueryParameters(SQLQuery, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLUtils.fillQueryParameters(SQLQuery, List)"})
  public void testFillQueryParameters_givenSQLQueryParameterGetValueReturn42() {
    // Arrange
    SQLQuery sqlStatement = mock(SQLQuery.class);
    when(sqlStatement.getText()).thenReturn("Text");
    doNothing().when(sqlStatement).setText(Mockito.<String>any());

    SQLQueryParameter sqlQueryParameter = mock(SQLQueryParameter.class);
    when(sqlQueryParameter.getTokenLength()).thenReturn(3);
    when(sqlQueryParameter.getTokenOffset()).thenReturn(1);
    when(sqlQueryParameter.getValue()).thenReturn("42");

    ArrayList<SQLQueryParameter> parameters = new ArrayList<>();
    parameters.add(sqlQueryParameter);

    // Act
    SQLUtils.fillQueryParameters(sqlStatement, parameters);

    // Assert
    verify(sqlStatement).getText();
    verify(sqlStatement).setText("T42");
    verify(sqlQueryParameter).getTokenLength();
    verify(sqlQueryParameter, atLeast(1)).getTokenOffset();
    verify(sqlQueryParameter).getValue();
  }

  /**
   * Test {@link SQLUtils#fillQueryParameters(SQLQuery, List)}.
   *
   * <ul>
   *   <li>Given {@code Text}.
   *   <li>When {@link ArrayList#ArrayList()}.
   *   <li>Then calls {@link SQLQuery#getText()}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#fillQueryParameters(SQLQuery, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLUtils.fillQueryParameters(SQLQuery, List)"})
  public void testFillQueryParameters_givenText_whenArrayList_thenCallsGetText() {
    // Arrange
    SQLQuery sqlStatement = mock(SQLQuery.class);
    when(sqlStatement.getText()).thenReturn("Text");
    doNothing().when(sqlStatement).setText(Mockito.<String>any());

    // Act
    SQLUtils.fillQueryParameters(sqlStatement, new ArrayList<>());

    // Assert
    verify(sqlStatement).getText();
    verify(sqlStatement).setText("Text");
  }

  /**
   * Test {@link SQLUtils#fillQueryParameters(SQLQuery, List)}.
   *
   * <ul>
   *   <li>Then calls {@link SQLQueryParameter#getTokenLength()}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#fillQueryParameters(SQLQuery, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLUtils.fillQueryParameters(SQLQuery, List)"})
  public void testFillQueryParameters_thenCallsGetTokenLength() {
    // Arrange
    SQLQuery sqlStatement = mock(SQLQuery.class);
    when(sqlStatement.getText()).thenReturn("Text");
    doNothing().when(sqlStatement).setText(Mockito.<String>any());

    SQLQueryParameter sqlQueryParameter = mock(SQLQueryParameter.class);
    when(sqlQueryParameter.getValue()).thenReturn("");
    when(sqlQueryParameter.getTokenLength()).thenReturn(3);
    when(sqlQueryParameter.getTokenOffset()).thenReturn(1);

    ArrayList<SQLQueryParameter> parameters = new ArrayList<>();
    parameters.add(sqlQueryParameter);

    // Act
    SQLUtils.fillQueryParameters(sqlStatement, parameters);

    // Assert
    verify(sqlStatement).getText();
    verify(sqlStatement).setText("TNULL");
    verify(sqlQueryParameter).getTokenLength();
    verify(sqlQueryParameter, atLeast(1)).getTokenOffset();
    verify(sqlQueryParameter).getValue();
  }

  /**
   * Test {@link SQLUtils#needQueryDelimiter(SQLDialect, String)}.
   *
   * <ul>
   *   <li>Given array of {@link String} with empty string.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#needQueryDelimiter(SQLDialect, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLUtils.needQueryDelimiter(SQLDialect, String)"})
  public void testNeedQueryDelimiter_givenArrayOfStringWithEmptyString() {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getScriptDelimiters()).thenReturn(new String[] {""});

    // Act
    boolean actualNeedQueryDelimiterResult = SQLUtils.needQueryDelimiter(sqlDialect, "Query");

    // Assert
    verify(sqlDialect).getScriptDelimiters();
    assertFalse(actualNeedQueryDelimiterResult);
  }

  /**
   * Test {@link SQLUtils#needQueryDelimiter(SQLDialect, String)}.
   *
   * <ul>
   *   <li>Given array of {@link String} with {@code Script Delimiters}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#needQueryDelimiter(SQLDialect, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLUtils.needQueryDelimiter(SQLDialect, String)"})
  public void testNeedQueryDelimiter_givenArrayOfStringWithScriptDelimiters() {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getScriptDelimiters()).thenReturn(new String[] {"Script Delimiters"});

    // Act
    boolean actualNeedQueryDelimiterResult = SQLUtils.needQueryDelimiter(sqlDialect, "Query");

    // Assert
    verify(sqlDialect).getScriptDelimiters();
    assertFalse(actualNeedQueryDelimiterResult);
  }

  /**
   * Test {@link SQLUtils#needQueryDelimiter(SQLDialect, String)}.
   *
   * <ul>
   *   <li>When {@link BasicSQLDialect#INSTANCE}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#needQueryDelimiter(SQLDialect, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLUtils.needQueryDelimiter(SQLDialect, String)"})
  public void testNeedQueryDelimiter_whenInstance_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(SQLUtils.needQueryDelimiter(BasicSQLDialect.INSTANCE, "Query"));
  }

  /**
   * Test {@link SQLUtils#removeQueryDelimiter(SQLDialect, String)}.
   *
   * <ul>
   *   <li>Then calls {@link SQLDialect#getScriptDelimiters()}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#removeQueryDelimiter(SQLDialect, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLUtils.removeQueryDelimiter(SQLDialect, String)"})
  public void testRemoveQueryDelimiter_thenCallsGetScriptDelimiters() {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getScriptDelimiters()).thenReturn(new String[] {""});

    // Act
    String actualRemoveQueryDelimiterResult = SQLUtils.removeQueryDelimiter(sqlDialect, "Query");

    // Assert
    verify(sqlDialect).getScriptDelimiters();
    assertEquals("Query", actualRemoveQueryDelimiterResult);
  }

  /**
   * Test {@link SQLUtils#removeQueryDelimiter(SQLDialect, String)}.
   *
   * <ul>
   *   <li>When {@link BasicSQLDialect#INSTANCE}.
   *   <li>Then return {@code Query}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#removeQueryDelimiter(SQLDialect, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLUtils.removeQueryDelimiter(SQLDialect, String)"})
  public void testRemoveQueryDelimiter_whenInstance_thenReturnQuery() {
    // Arrange, Act and Assert
    assertEquals("Query", SQLUtils.removeQueryDelimiter(BasicSQLDialect.INSTANCE, "Query"));
  }

  /**
   * Test {@link SQLUtils#getDefaultScriptDelimiter(SQLDialect)}.
   *
   * <ul>
   *   <li>Given empty array of {@link String}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#getDefaultScriptDelimiter(SQLDialect)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLUtils.getDefaultScriptDelimiter(SQLDialect)"})
  public void testGetDefaultScriptDelimiter_givenEmptyArrayOfString() {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getScriptDelimiters()).thenReturn(new String[] {});

    // Act
    String actualDefaultScriptDelimiter = SQLUtils.getDefaultScriptDelimiter(sqlDialect);

    // Assert
    verify(sqlDialect).getScriptDelimiters();
    assertEquals(SQLConstants.DEFAULT_STATEMENT_DELIMITER, actualDefaultScriptDelimiter);
  }

  /**
   * Test {@link SQLUtils#getDefaultScriptDelimiter(SQLDialect)}.
   *
   * <ul>
   *   <li>Given {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#getDefaultScriptDelimiter(SQLDialect)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLUtils.getDefaultScriptDelimiter(SQLDialect)"})
  public void testGetDefaultScriptDelimiter_givenNull() {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getScriptDelimiters()).thenReturn(null);

    // Act
    String actualDefaultScriptDelimiter = SQLUtils.getDefaultScriptDelimiter(sqlDialect);

    // Assert
    verify(sqlDialect).getScriptDelimiters();
    assertEquals(SQLConstants.DEFAULT_STATEMENT_DELIMITER, actualDefaultScriptDelimiter);
  }

  /**
   * Test {@link SQLUtils#getDefaultScriptDelimiter(SQLDialect)}.
   *
   * <ul>
   *   <li>When {@link BasicSQLDialect#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#getDefaultScriptDelimiter(SQLDialect)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLUtils.getDefaultScriptDelimiter(SQLDialect)"})
  public void testGetDefaultScriptDelimiter_whenInstance() {
    // Arrange, Act and Assert
    assertEquals(
        SQLConstants.DEFAULT_STATEMENT_DELIMITER,
        SQLUtils.getDefaultScriptDelimiter(BasicSQLDialect.INSTANCE));
  }

  /**
   * Test {@link SQLUtils#isLatinLetter(int)}.
   *
   * <p>Method under test: {@link SQLUtils#isLatinLetter(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLUtils.isLatinLetter(int)"})
  public void testIsLatinLetter() {
    // Arrange, Act and Assert
    assertFalse(SQLUtils.isLatinLetter(1));
  }

  /**
   * Test {@link SQLUtils#identifierToCanonicalForm(SQLDialect, String, boolean, boolean)}.
   *
   * <ul>
   *   <li>Given {@code false}.
   *   <li>Then calls {@link SQLDialect#storesUnquotedCase()}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#identifierToCanonicalForm(SQLDialect, String, boolean,
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String SQLUtils.identifierToCanonicalForm(SQLDialect, String, boolean, boolean)"
  })
  public void testIdentifierToCanonicalForm_givenFalse_thenCallsStoresUnquotedCase() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.isQuotedIdentifier(Mockito.<String>any())).thenReturn(false);
    when(dialect.getUnquotedIdentifier(Mockito.<String>any(), anyBoolean())).thenReturn("42");
    when(dialect.storesUnquotedCase()).thenReturn(DBPIdentifierCase.UPPER);

    // Act
    String actualIdentifierToCanonicalFormResult =
        SQLUtils.identifierToCanonicalForm(dialect, "Raw Identifier String", true, false);

    // Assert
    verify(dialect).getUnquotedIdentifier("Raw Identifier String", true);
    verify(dialect).isQuotedIdentifier("Raw Identifier String");
    verify(dialect).storesUnquotedCase();
    assertEquals("42", actualIdentifierToCanonicalFormResult);
  }

  /**
   * Test {@link SQLUtils#identifierToCanonicalForm(SQLDialect, String, boolean, boolean)}.
   *
   * <ul>
   *   <li>Given {@code LOWER}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#identifierToCanonicalForm(SQLDialect, String, boolean,
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String SQLUtils.identifierToCanonicalForm(SQLDialect, String, boolean, boolean)"
  })
  public void testIdentifierToCanonicalForm_givenLower() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.storesQuotedCase()).thenReturn(DBPIdentifierCase.LOWER);
    when(dialect.isQuotedIdentifier(Mockito.<String>any())).thenReturn(true);
    when(dialect.getUnquotedIdentifier(Mockito.<String>any(), anyBoolean())).thenReturn("42");

    // Act
    String actualIdentifierToCanonicalFormResult =
        SQLUtils.identifierToCanonicalForm(dialect, "Raw Identifier String", true, false);

    // Assert
    verify(dialect).getUnquotedIdentifier("Raw Identifier String", true);
    verify(dialect).isQuotedIdentifier("Raw Identifier String");
    verify(dialect).storesQuotedCase();
    assertEquals("42", actualIdentifierToCanonicalFormResult);
  }

  /**
   * Test {@link SQLUtils#identifierToCanonicalForm(SQLDialect, String, boolean, boolean)}.
   *
   * <ul>
   *   <li>Given {@code MIXED}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#identifierToCanonicalForm(SQLDialect, String, boolean,
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String SQLUtils.identifierToCanonicalForm(SQLDialect, String, boolean, boolean)"
  })
  public void testIdentifierToCanonicalForm_givenMixed() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.storesQuotedCase()).thenReturn(DBPIdentifierCase.MIXED);
    when(dialect.isQuotedIdentifier(Mockito.<String>any())).thenReturn(true);
    when(dialect.getUnquotedIdentifier(Mockito.<String>any(), anyBoolean())).thenReturn("42");

    // Act
    String actualIdentifierToCanonicalFormResult =
        SQLUtils.identifierToCanonicalForm(dialect, "Raw Identifier String", true, false);

    // Assert
    verify(dialect).getUnquotedIdentifier("Raw Identifier String", true);
    verify(dialect).isQuotedIdentifier("Raw Identifier String");
    verify(dialect).storesQuotedCase();
    assertEquals("42", actualIdentifierToCanonicalFormResult);
  }

  /**
   * Test {@link SQLUtils#identifierToCanonicalForm(SQLDialect, String, boolean, boolean)}.
   *
   * <ul>
   *   <li>Then calls {@link SQLDialect#getQuotedIdentifier(String, boolean, boolean)}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#identifierToCanonicalForm(SQLDialect, String, boolean,
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String SQLUtils.identifierToCanonicalForm(SQLDialect, String, boolean, boolean)"
  })
  public void testIdentifierToCanonicalForm_thenCallsGetQuotedIdentifier() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getQuotedIdentifier(Mockito.<String>any(), anyBoolean(), anyBoolean()))
        .thenReturn("42");
    when(dialect.storesQuotedCase()).thenReturn(DBPIdentifierCase.UPPER);
    when(dialect.isQuotedIdentifier(Mockito.<String>any())).thenReturn(true);
    when(dialect.getUnquotedIdentifier(Mockito.<String>any(), anyBoolean())).thenReturn("42");

    // Act
    String actualIdentifierToCanonicalFormResult =
        SQLUtils.identifierToCanonicalForm(dialect, "Raw Identifier String", false, false);

    // Assert
    verify(dialect).getQuotedIdentifier("42", true, false);
    verify(dialect).getUnquotedIdentifier("Raw Identifier String", true);
    verify(dialect).isQuotedIdentifier("Raw Identifier String");
    verify(dialect).storesQuotedCase();
    assertEquals("42", actualIdentifierToCanonicalFormResult);
  }

  /**
   * Test {@link SQLUtils#identifierToCanonicalForm(SQLDialect, String, boolean, boolean)}.
   *
   * <ul>
   *   <li>When {@link BasicSQLDialect#INSTANCE}.
   *   <li>Then return {@code Raw Identifier String}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#identifierToCanonicalForm(SQLDialect, String, boolean,
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String SQLUtils.identifierToCanonicalForm(SQLDialect, String, boolean, boolean)"
  })
  public void testIdentifierToCanonicalForm_whenInstance_thenReturnRawIdentifierString() {
    // Arrange and Act
    String actualIdentifierToCanonicalFormResult =
        SQLUtils.identifierToCanonicalForm(
            BasicSQLDialect.INSTANCE, "Raw Identifier String", true, true);

    // Assert
    assertEquals("Raw Identifier String", actualIdentifierToCanonicalFormResult);
  }

  /**
   * Test {@link SQLUtils#identifierToCanonicalForm(SQLDialect, String, boolean, boolean)}.
   *
   * <ul>
   *   <li>When {@link BasicSQLDialect#INSTANCE}.
   *   <li>Then return {@code "RAW IDENTIFIER STRING"}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#identifierToCanonicalForm(SQLDialect, String, boolean,
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String SQLUtils.identifierToCanonicalForm(SQLDialect, String, boolean, boolean)"
  })
  public void testIdentifierToCanonicalForm_whenInstance_thenReturnRawIdentifierString2() {
    // Arrange and Act
    String actualIdentifierToCanonicalFormResult =
        SQLUtils.identifierToCanonicalForm(
            BasicSQLDialect.INSTANCE, "Raw Identifier String", false, false);

    // Assert
    assertEquals("\"RAW IDENTIFIER STRING\"", actualIdentifierToCanonicalFormResult);
  }

  /**
   * Test {@link SQLUtils#identifierToCanonicalForm(SQLDialect, String, boolean, boolean)}.
   *
   * <ul>
   *   <li>When {@link BasicSQLDialect#INSTANCE}.
   *   <li>Then return {@code RAW IDENTIFIER STRING}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#identifierToCanonicalForm(SQLDialect, String, boolean,
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String SQLUtils.identifierToCanonicalForm(SQLDialect, String, boolean, boolean)"
  })
  public void testIdentifierToCanonicalForm_whenInstance_thenReturnRawIdentifierString3() {
    // Arrange and Act
    String actualIdentifierToCanonicalFormResult =
        SQLUtils.identifierToCanonicalForm(
            BasicSQLDialect.INSTANCE, "Raw Identifier String", true, false);

    // Assert
    assertEquals("RAW IDENTIFIER STRING", actualIdentifierToCanonicalFormResult);
  }

  /**
   * Test {@link SQLUtils#identifierToCanonicalForm(SQLDialect, String, boolean, boolean)}.
   *
   * <ul>
   *   <li>When {@link SQLDialect} {@link SQLDialect#storesQuotedCase()} return {@code UPPER}.
   * </ul>
   *
   * <p>Method under test: {@link SQLUtils#identifierToCanonicalForm(SQLDialect, String, boolean,
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String SQLUtils.identifierToCanonicalForm(SQLDialect, String, boolean, boolean)"
  })
  public void testIdentifierToCanonicalForm_whenSQLDialectStoresQuotedCaseReturnUpper() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.storesQuotedCase()).thenReturn(DBPIdentifierCase.UPPER);
    when(dialect.isQuotedIdentifier(Mockito.<String>any())).thenReturn(true);
    when(dialect.getUnquotedIdentifier(Mockito.<String>any(), anyBoolean())).thenReturn("42");

    // Act
    String actualIdentifierToCanonicalFormResult =
        SQLUtils.identifierToCanonicalForm(dialect, "Raw Identifier String", true, false);

    // Assert
    verify(dialect).getUnquotedIdentifier("Raw Identifier String", true);
    verify(dialect).isQuotedIdentifier("Raw Identifier String");
    verify(dialect).storesQuotedCase();
    assertEquals("42", actualIdentifierToCanonicalFormResult);
  }
}
