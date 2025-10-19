package org.jkiss.dbeaver.model.sql.generator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.anyChar;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.model.DBPKeywordType;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.jkiss.dbeaver.model.sql.SQLDialect;
import org.jkiss.dbeaver.model.struct.DBSEntity;
import org.jkiss.dbeaver.model.virtual.DBVContainer;
import org.jkiss.dbeaver.model.virtual.DBVEntity;
import org.jkiss.dbeaver.model.virtual.DBVModel;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class SQLGeneratorInnerJoinDiffblueTest {
  /**
   * Test {@link SQLGeneratorInnerJoin#run(DBRProgressMonitor)}.
   *
   * <p>Method under test: {@link SQLGeneratorInnerJoin#run(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLGeneratorInnerJoin.run(DBRProgressMonitor)"})
  public void testRun() throws InvocationTargetException {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.validIdentifierStart(anyChar())).thenReturn(true);
    when(sqlDialect.getStructSeparator()).thenReturn('A');
    when(sqlDialect.getQuotedIdentifier(Mockito.<String>any(), anyBoolean(), anyBoolean()))
        .thenReturn("42");
    when(sqlDialect.getKeywordType(Mockito.<String>any())).thenReturn(DBPKeywordType.KEYWORD);

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(sqlDialect);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity dbvEntity = new DBVEntity(container, "42", "Description Column Names");

    ArrayList<DBSEntity> objects = new ArrayList<>();
    objects.add(dbvEntity);

    SQLGeneratorInnerJoin sqlGeneratorInnerJoin = new SQLGeneratorInnerJoin();
    sqlGeneratorInnerJoin.initGenerator(objects);

    // Act
    sqlGeneratorInnerJoin.run(new LoggingProgressMonitor());

    // Assert
    verify(dbpDataSource, atLeast(1)).getSQLDialect();
    verify(sqlDialect, atLeast(1)).getKeywordType(Mockito.<String>any());
    verify(sqlDialect, atLeast(1)).getQuotedIdentifier(Mockito.<String>any(), eq(true), eq(false));
    verify(sqlDialect).getStructSeparator();
    verify(sqlDialect, atLeast(1)).validIdentifierStart(anyChar());
    verify(parent, atLeast(1)).getDataSource();
    assertEquals("SELECT t.*\nFROM 42A42 t", sqlGeneratorInnerJoin.getResult());
  }

  /**
   * Test {@link SQLGeneratorInnerJoin#run(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@code SELECT}.
   * </ul>
   *
   * <p>Method under test: {@link SQLGeneratorInnerJoin#run(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLGeneratorInnerJoin.run(DBRProgressMonitor)"})
  public void testRun_givenArrayListAddSelect() throws InvocationTargetException {
    // Arrange
    ArrayList<String> stringList = new ArrayList<>();
    stringList.add("SELECT ");

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.validIdentifierPart(anyChar(), anyBoolean())).thenReturn(true);
    when(sqlDialect.getReservedWords()).thenReturn(stringList);
    when(sqlDialect.validIdentifierStart(anyChar())).thenReturn(true);
    when(sqlDialect.getStructSeparator()).thenReturn('A');
    when(sqlDialect.getQuotedIdentifier(Mockito.<String>any(), anyBoolean(), anyBoolean()))
        .thenReturn("42");
    when(sqlDialect.getKeywordType(Mockito.<String>any())).thenReturn(DBPKeywordType.KEYWORD);

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(sqlDialect);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity dbvEntity = new DBVEntity(container, "Name", "Description Column Names");

    ArrayList<DBSEntity> objects = new ArrayList<>();
    objects.add(dbvEntity);

    SQLGeneratorInnerJoin sqlGeneratorInnerJoin = new SQLGeneratorInnerJoin();
    sqlGeneratorInnerJoin.initGenerator(objects);

    // Act
    sqlGeneratorInnerJoin.run(new LoggingProgressMonitor());

    // Assert
    verify(dbpDataSource, atLeast(1)).getSQLDialect();
    verify(sqlDialect, atLeast(1)).getKeywordType(Mockito.<String>any());
    verify(sqlDialect, atLeast(1)).getQuotedIdentifier("Name", true, false);
    verify(sqlDialect).getReservedWords();
    verify(sqlDialect).getStructSeparator();
    verify(sqlDialect, atLeast(1)).validIdentifierPart(anyChar(), eq(false));
    verify(sqlDialect).validIdentifierStart('N');
    verify(parent, atLeast(1)).getDataSource();
    assertEquals("SELECT n.*\nFROM 42A42 n", sqlGeneratorInnerJoin.getResult());
  }

  /**
   * Test {@link SQLGeneratorInnerJoin#run(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@code t}.
   *   <li>Then {@link SQLGeneratorInnerJoin} (default constructor) Result is {@code SELECT n.* FROM
   *       42A42 n}.
   * </ul>
   *
   * <p>Method under test: {@link SQLGeneratorInnerJoin#run(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLGeneratorInnerJoin.run(DBRProgressMonitor)"})
  public void testRun_givenArrayListAddT_thenSQLGeneratorInnerJoinResultIsSelectNFrom42a42N()
      throws InvocationTargetException {
    // Arrange
    ArrayList<String> stringList = new ArrayList<>();
    stringList.add("t");
    stringList.add("SELECT ");

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.validIdentifierPart(anyChar(), anyBoolean())).thenReturn(true);
    when(sqlDialect.getReservedWords()).thenReturn(stringList);
    when(sqlDialect.validIdentifierStart(anyChar())).thenReturn(true);
    when(sqlDialect.getStructSeparator()).thenReturn('A');
    when(sqlDialect.getQuotedIdentifier(Mockito.<String>any(), anyBoolean(), anyBoolean()))
        .thenReturn("42");
    when(sqlDialect.getKeywordType(Mockito.<String>any())).thenReturn(DBPKeywordType.KEYWORD);

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(sqlDialect);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity dbvEntity = new DBVEntity(container, "Name", "Description Column Names");

    ArrayList<DBSEntity> objects = new ArrayList<>();
    objects.add(dbvEntity);

    SQLGeneratorInnerJoin sqlGeneratorInnerJoin = new SQLGeneratorInnerJoin();
    sqlGeneratorInnerJoin.initGenerator(objects);

    // Act
    sqlGeneratorInnerJoin.run(new LoggingProgressMonitor());

    // Assert
    verify(dbpDataSource, atLeast(1)).getSQLDialect();
    verify(sqlDialect, atLeast(1)).getKeywordType(Mockito.<String>any());
    verify(sqlDialect, atLeast(1)).getQuotedIdentifier("Name", true, false);
    verify(sqlDialect).getReservedWords();
    verify(sqlDialect).getStructSeparator();
    verify(sqlDialect, atLeast(1)).validIdentifierPart(anyChar(), eq(false));
    verify(sqlDialect).validIdentifierStart('N');
    verify(parent, atLeast(1)).getDataSource();
    assertEquals("SELECT n.*\nFROM 42A42 n", sqlGeneratorInnerJoin.getResult());
  }

  /**
   * Test {@link SQLGeneratorInnerJoin#run(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Given {@link DBPDataSource} {@link DBPDataSource#getSQLDialect()} throw {@link
   *       RuntimeException#RuntimeException()}.
   * </ul>
   *
   * <p>Method under test: {@link SQLGeneratorInnerJoin#run(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLGeneratorInnerJoin.run(DBRProgressMonitor)"})
  public void testRun_givenDBPDataSourceGetSQLDialectThrowRuntimeException()
      throws InvocationTargetException {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenThrow(new RuntimeException());

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity dbvEntity = new DBVEntity(container, "Name", "Description Column Names");

    ArrayList<DBSEntity> objects = new ArrayList<>();
    objects.add(dbvEntity);

    SQLGeneratorInnerJoin sqlGeneratorInnerJoin = new SQLGeneratorInnerJoin();
    sqlGeneratorInnerJoin.initGenerator(objects);

    // Act and Assert
    assertThrows(
        RuntimeException.class, () -> sqlGeneratorInnerJoin.run(new LoggingProgressMonitor()));
    verify(dbpDataSource).getSQLDialect();
    verify(parent).getDataSource();
  }

  /**
   * Test {@link SQLGeneratorInnerJoin#run(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Given {@link DBVContainer} {@link DBVContainer#getDataSource()} throw {@link
   *       RuntimeException#RuntimeException()}.
   * </ul>
   *
   * <p>Method under test: {@link SQLGeneratorInnerJoin#run(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLGeneratorInnerJoin.run(DBRProgressMonitor)"})
  public void testRun_givenDBVContainerGetDataSourceThrowRuntimeException()
      throws InvocationTargetException {
    // Arrange
    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenThrow(new RuntimeException());
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity dbvEntity = new DBVEntity(container, "Name", "Description Column Names");

    ArrayList<DBSEntity> objects = new ArrayList<>();
    objects.add(dbvEntity);

    SQLGeneratorInnerJoin sqlGeneratorInnerJoin = new SQLGeneratorInnerJoin();
    sqlGeneratorInnerJoin.initGenerator(objects);

    // Act and Assert
    assertThrows(
        RuntimeException.class, () -> sqlGeneratorInnerJoin.run(new LoggingProgressMonitor()));
    verify(parent).getDataSource();
  }

  /**
   * Test {@link SQLGeneratorInnerJoin#run(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Given {@link DBVContainer#DBVContainer(DBVContainer, String)} with parent is {@link
   *       DBVContainer} and name is lf.
   * </ul>
   *
   * <p>Method under test: {@link SQLGeneratorInnerJoin#run(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLGeneratorInnerJoin.run(DBRProgressMonitor)"})
  public void testRun_givenDBVContainerWithParentIsDBVContainerAndNameIsLf()
      throws InvocationTargetException {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.validIdentifierPart(anyChar(), anyBoolean())).thenReturn(true);
    when(sqlDialect.getReservedWords()).thenReturn(new ArrayList<>());
    when(sqlDialect.validIdentifierStart(anyChar())).thenReturn(true);
    when(sqlDialect.getQuotedIdentifier(Mockito.<String>any(), anyBoolean(), anyBoolean()))
        .thenReturn("42");
    when(sqlDialect.getKeywordType(Mockito.<String>any())).thenReturn(DBPKeywordType.KEYWORD);

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(sqlDialect);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, "\n");
    DBVEntity dbvEntity = new DBVEntity(container, "Name", "Description Column Names");

    ArrayList<DBSEntity> objects = new ArrayList<>();
    objects.add(dbvEntity);

    SQLGeneratorInnerJoin sqlGeneratorInnerJoin = new SQLGeneratorInnerJoin();
    sqlGeneratorInnerJoin.initGenerator(objects);

    // Act
    sqlGeneratorInnerJoin.run(new LoggingProgressMonitor());

    // Assert
    verify(dbpDataSource, atLeast(1)).getSQLDialect();
    verify(sqlDialect, atLeast(1)).getKeywordType(Mockito.<String>any());
    verify(sqlDialect).getQuotedIdentifier("Name", true, false);
    verify(sqlDialect).getReservedWords();
    verify(sqlDialect, atLeast(1)).validIdentifierPart(anyChar(), eq(false));
    verify(sqlDialect).validIdentifierStart('N');
    verify(parent, atLeast(1)).getDataSource();
    assertEquals("SELECT n.*\nFROM 42 n", sqlGeneratorInnerJoin.getResult());
  }

  /**
   * Test {@link SQLGeneratorInnerJoin#run(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Given {@link DBVContainer#DBVContainer(DBVContainer, String)} with parent is {@link
   *       DBVContainer} and name is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SQLGeneratorInnerJoin#run(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLGeneratorInnerJoin.run(DBRProgressMonitor)"})
  public void testRun_givenDBVContainerWithParentIsDBVContainerAndNameIsNull()
      throws InvocationTargetException {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.validIdentifierPart(anyChar(), anyBoolean())).thenReturn(true);
    when(sqlDialect.getReservedWords()).thenReturn(new ArrayList<>());
    when(sqlDialect.validIdentifierStart(anyChar())).thenReturn(true);
    when(sqlDialect.getQuotedIdentifier(Mockito.<String>any(), anyBoolean(), anyBoolean()))
        .thenReturn("42");
    when(sqlDialect.getKeywordType(Mockito.<String>any())).thenReturn(DBPKeywordType.KEYWORD);

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(sqlDialect);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, null);
    DBVEntity dbvEntity = new DBVEntity(container, "Name", "Description Column Names");

    ArrayList<DBSEntity> objects = new ArrayList<>();
    objects.add(dbvEntity);

    SQLGeneratorInnerJoin sqlGeneratorInnerJoin = new SQLGeneratorInnerJoin();
    sqlGeneratorInnerJoin.initGenerator(objects);

    // Act
    sqlGeneratorInnerJoin.run(new LoggingProgressMonitor());

    // Assert
    verify(dbpDataSource, atLeast(1)).getSQLDialect();
    verify(sqlDialect, atLeast(1)).getKeywordType(Mockito.<String>any());
    verify(sqlDialect).getQuotedIdentifier("Name", true, false);
    verify(sqlDialect).getReservedWords();
    verify(sqlDialect, atLeast(1)).validIdentifierPart(anyChar(), eq(false));
    verify(sqlDialect).validIdentifierStart('N');
    verify(parent, atLeast(1)).getDataSource();
    assertEquals("SELECT n.*\nFROM 42 n", sqlGeneratorInnerJoin.getResult());
  }

  /**
   * Test {@link SQLGeneratorInnerJoin#run(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Given {@link SQLDialect} {@link SQLDialect#getKeywordType(String)} return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SQLGeneratorInnerJoin#run(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLGeneratorInnerJoin.run(DBRProgressMonitor)"})
  public void testRun_givenSQLDialectGetKeywordTypeReturnNull() throws InvocationTargetException {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.validIdentifierPart(anyChar(), anyBoolean())).thenReturn(true);
    when(sqlDialect.getReservedWords()).thenReturn(new ArrayList<>());
    when(sqlDialect.validIdentifierStart(anyChar())).thenReturn(true);
    when(sqlDialect.getStructSeparator()).thenReturn('A');
    when(sqlDialect.getQuotedIdentifier(Mockito.<String>any(), anyBoolean(), anyBoolean()))
        .thenReturn("42");
    when(sqlDialect.getKeywordType(Mockito.<String>any())).thenReturn(null);

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(sqlDialect);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity dbvEntity = new DBVEntity(container, "Name", "Description Column Names");

    ArrayList<DBSEntity> objects = new ArrayList<>();
    objects.add(dbvEntity);

    SQLGeneratorInnerJoin sqlGeneratorInnerJoin = new SQLGeneratorInnerJoin();
    sqlGeneratorInnerJoin.initGenerator(objects);

    // Act
    sqlGeneratorInnerJoin.run(new LoggingProgressMonitor());

    // Assert
    verify(dbpDataSource, atLeast(1)).getSQLDialect();
    verify(sqlDialect).getKeywordType("n");
    verify(sqlDialect, atLeast(1)).getQuotedIdentifier("Name", true, false);
    verify(sqlDialect).getReservedWords();
    verify(sqlDialect).getStructSeparator();
    verify(sqlDialect, atLeast(1)).validIdentifierPart(anyChar(), eq(false));
    verify(sqlDialect).validIdentifierStart('N');
    verify(parent, atLeast(1)).getDataSource();
    assertEquals("SELECT n.*\nFROM 42A42 n", sqlGeneratorInnerJoin.getResult());
  }

  /**
   * Test {@link SQLGeneratorInnerJoin#run(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Given {@link SQLDialect} {@link SQLDialect#getQuotedIdentifier(String, boolean, boolean)}
   *       throw {@link RuntimeException#RuntimeException()}.
   * </ul>
   *
   * <p>Method under test: {@link SQLGeneratorInnerJoin#run(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLGeneratorInnerJoin.run(DBRProgressMonitor)"})
  public void testRun_givenSQLDialectGetQuotedIdentifierThrowRuntimeException()
      throws InvocationTargetException {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getQuotedIdentifier(Mockito.<String>any(), anyBoolean(), anyBoolean()))
        .thenThrow(new RuntimeException());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(sqlDialect);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, "SELECT ");
    DBVEntity dbvEntity = new DBVEntity(container, "", "Description Column Names");

    ArrayList<DBSEntity> objects = new ArrayList<>();
    objects.add(dbvEntity);

    SQLGeneratorInnerJoin sqlGeneratorInnerJoin = new SQLGeneratorInnerJoin();
    sqlGeneratorInnerJoin.initGenerator(objects);

    // Act and Assert
    assertThrows(
        InvocationTargetException.class,
        () -> sqlGeneratorInnerJoin.run(new LoggingProgressMonitor()));
    verify(dbpDataSource, atLeast(1)).getSQLDialect();
    verify(sqlDialect).getQuotedIdentifier("SELECT ", true, false);
    verify(parent, atLeast(1)).getDataSource();
  }

  /**
   * Test {@link SQLGeneratorInnerJoin#run(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Given {@link SQLDialect} {@link SQLDialect#getReservedWords()} throw {@link
   *       RuntimeException#RuntimeException()}.
   * </ul>
   *
   * <p>Method under test: {@link SQLGeneratorInnerJoin#run(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLGeneratorInnerJoin.run(DBRProgressMonitor)"})
  public void testRun_givenSQLDialectGetReservedWordsThrowRuntimeException()
      throws InvocationTargetException {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getReservedWords()).thenThrow(new RuntimeException());
    when(sqlDialect.validIdentifierStart(anyChar())).thenReturn(true);

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(sqlDialect);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity dbvEntity = new DBVEntity(container, "t", "Description Column Names");

    ArrayList<DBSEntity> objects = new ArrayList<>();
    objects.add(dbvEntity);

    SQLGeneratorInnerJoin sqlGeneratorInnerJoin = new SQLGeneratorInnerJoin();
    sqlGeneratorInnerJoin.initGenerator(objects);

    // Act and Assert
    assertThrows(
        InvocationTargetException.class,
        () -> sqlGeneratorInnerJoin.run(new LoggingProgressMonitor()));
    verify(dbpDataSource, atLeast(1)).getSQLDialect();
    verify(sqlDialect).getReservedWords();
    verify(sqlDialect).validIdentifierStart('t');
    verify(parent, atLeast(1)).getDataSource();
  }

  /**
   * Test {@link SQLGeneratorInnerJoin#run(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Given {@link SQLDialect} {@link SQLDialect#validIdentifierPart(char, boolean)} return
   *       {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link SQLGeneratorInnerJoin#run(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLGeneratorInnerJoin.run(DBRProgressMonitor)"})
  public void testRun_givenSQLDialectValidIdentifierPartReturnFalse()
      throws InvocationTargetException {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.validIdentifierPart(anyChar(), anyBoolean())).thenReturn(false);
    when(sqlDialect.getReservedWords()).thenReturn(new ArrayList<>());
    when(sqlDialect.validIdentifierStart(anyChar())).thenReturn(true);
    when(sqlDialect.getStructSeparator()).thenReturn('A');
    when(sqlDialect.getQuotedIdentifier(Mockito.<String>any(), anyBoolean(), anyBoolean()))
        .thenReturn("42");
    when(sqlDialect.getKeywordType(Mockito.<String>any())).thenReturn(DBPKeywordType.KEYWORD);

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(sqlDialect);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity dbvEntity = new DBVEntity(container, "Name", "Description Column Names");

    ArrayList<DBSEntity> objects = new ArrayList<>();
    objects.add(dbvEntity);

    SQLGeneratorInnerJoin sqlGeneratorInnerJoin = new SQLGeneratorInnerJoin();
    sqlGeneratorInnerJoin.initGenerator(objects);

    // Act
    sqlGeneratorInnerJoin.run(new LoggingProgressMonitor());

    // Assert
    verify(dbpDataSource, atLeast(1)).getSQLDialect();
    verify(sqlDialect, atLeast(1)).getKeywordType(Mockito.<String>any());
    verify(sqlDialect, atLeast(1)).getQuotedIdentifier("Name", true, false);
    verify(sqlDialect).getReservedWords();
    verify(sqlDialect).getStructSeparator();
    verify(sqlDialect, atLeast(1)).validIdentifierPart(anyChar(), eq(false));
    verify(sqlDialect).validIdentifierStart('N');
    verify(parent, atLeast(1)).getDataSource();
    assertEquals("SELECT n.*\nFROM 42A42 n", sqlGeneratorInnerJoin.getResult());
  }

  /**
   * Test {@link SQLGeneratorInnerJoin#run(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Given {@link SQLDialect} {@link SQLDialect#validIdentifierPart(char, boolean)} throw
   *       {@link RuntimeException#RuntimeException()}.
   * </ul>
   *
   * <p>Method under test: {@link SQLGeneratorInnerJoin#run(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLGeneratorInnerJoin.run(DBRProgressMonitor)"})
  public void testRun_givenSQLDialectValidIdentifierPartThrowRuntimeException()
      throws InvocationTargetException {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.validIdentifierPart(anyChar(), anyBoolean())).thenThrow(new RuntimeException());
    when(sqlDialect.validIdentifierStart(anyChar())).thenReturn(true);

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(sqlDialect);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity dbvEntity = new DBVEntity(container, "Name", "Description Column Names");

    ArrayList<DBSEntity> objects = new ArrayList<>();
    objects.add(dbvEntity);

    SQLGeneratorInnerJoin sqlGeneratorInnerJoin = new SQLGeneratorInnerJoin();
    sqlGeneratorInnerJoin.initGenerator(objects);

    // Act and Assert
    assertThrows(
        InvocationTargetException.class,
        () -> sqlGeneratorInnerJoin.run(new LoggingProgressMonitor()));
    verify(dbpDataSource, atLeast(1)).getSQLDialect();
    verify(sqlDialect).validIdentifierPart('a', false);
    verify(sqlDialect).validIdentifierStart('N');
    verify(parent, atLeast(1)).getDataSource();
  }

  /**
   * Test {@link SQLGeneratorInnerJoin#run(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Given {@link SQLDialect} {@link SQLDialect#validIdentifierStart(char)} return {@code
   *       false}.
   * </ul>
   *
   * <p>Method under test: {@link SQLGeneratorInnerJoin#run(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLGeneratorInnerJoin.run(DBRProgressMonitor)"})
  public void testRun_givenSQLDialectValidIdentifierStartReturnFalse()
      throws InvocationTargetException {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.validIdentifierStart(anyChar())).thenReturn(false);
    when(sqlDialect.getStructSeparator()).thenReturn('A');
    when(sqlDialect.getQuotedIdentifier(Mockito.<String>any(), anyBoolean(), anyBoolean()))
        .thenReturn("42");
    when(sqlDialect.getKeywordType(Mockito.<String>any())).thenReturn(DBPKeywordType.KEYWORD);

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(sqlDialect);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity dbvEntity = new DBVEntity(container, "Name", "Description Column Names");

    ArrayList<DBSEntity> objects = new ArrayList<>();
    objects.add(dbvEntity);

    SQLGeneratorInnerJoin sqlGeneratorInnerJoin = new SQLGeneratorInnerJoin();
    sqlGeneratorInnerJoin.initGenerator(objects);

    // Act
    sqlGeneratorInnerJoin.run(new LoggingProgressMonitor());

    // Assert
    verify(dbpDataSource, atLeast(1)).getSQLDialect();
    verify(sqlDialect, atLeast(1)).getKeywordType(Mockito.<String>any());
    verify(sqlDialect, atLeast(1)).getQuotedIdentifier("Name", true, false);
    verify(sqlDialect).getStructSeparator();
    verify(sqlDialect, atLeast(1)).validIdentifierStart(anyChar());
    verify(parent, atLeast(1)).getDataSource();
    assertEquals("SELECT t.*\nFROM 42A42 t", sqlGeneratorInnerJoin.getResult());
  }

  /**
   * Test {@link SQLGeneratorInnerJoin#run(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Given {@link SQLDialect} {@link SQLDialect#validIdentifierStart(char)} throw {@link
   *       RuntimeException#RuntimeException()}.
   * </ul>
   *
   * <p>Method under test: {@link SQLGeneratorInnerJoin#run(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLGeneratorInnerJoin.run(DBRProgressMonitor)"})
  public void testRun_givenSQLDialectValidIdentifierStartThrowRuntimeException()
      throws InvocationTargetException {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.validIdentifierStart(anyChar())).thenThrow(new RuntimeException());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(sqlDialect);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity dbvEntity = new DBVEntity(container, "Name", "Description Column Names");

    ArrayList<DBSEntity> objects = new ArrayList<>();
    objects.add(dbvEntity);

    SQLGeneratorInnerJoin sqlGeneratorInnerJoin = new SQLGeneratorInnerJoin();
    sqlGeneratorInnerJoin.initGenerator(objects);

    // Act and Assert
    assertThrows(
        InvocationTargetException.class,
        () -> sqlGeneratorInnerJoin.run(new LoggingProgressMonitor()));
    verify(dbpDataSource, atLeast(1)).getSQLDialect();
    verify(sqlDialect).validIdentifierStart('N');
    verify(parent, atLeast(1)).getDataSource();
  }

  /**
   * Test {@link SQLGeneratorInnerJoin#run(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Then {@link SQLGeneratorInnerJoin} (default constructor) Result is {@code SELECT .*
   *       FROM}.
   * </ul>
   *
   * <p>Method under test: {@link SQLGeneratorInnerJoin#run(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLGeneratorInnerJoin.run(DBRProgressMonitor)"})
  public void testRun_thenSQLGeneratorInnerJoinResultIsSelectFrom()
      throws InvocationTargetException {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(mock(SQLDialect.class));

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    when(dataSourceContainer.getDataSource()).thenReturn(dbpDataSource);
    DBVModel container = new DBVModel(dataSourceContainer);
    DBVEntity dbvEntity = new DBVEntity(container, "", "Description Column Names");

    ArrayList<DBSEntity> objects = new ArrayList<>();
    objects.add(dbvEntity);

    SQLGeneratorInnerJoin sqlGeneratorInnerJoin = new SQLGeneratorInnerJoin();
    sqlGeneratorInnerJoin.initGenerator(objects);

    // Act
    sqlGeneratorInnerJoin.run(new LoggingProgressMonitor());

    // Assert
    verify(dbpDataSource, atLeast(1)).getSQLDialect();
    verify(dataSourceContainer, atLeast(1)).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
    assertEquals("SELECT .*\nFROM  ", sqlGeneratorInnerJoin.getResult());
  }

  /**
   * Test {@link SQLGeneratorInnerJoin#run(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Then {@link SQLGeneratorInnerJoin} (default constructor) Result is {@code SELECT .* FROM
   *       42}.
   * </ul>
   *
   * <p>Method under test: {@link SQLGeneratorInnerJoin#run(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLGeneratorInnerJoin.run(DBRProgressMonitor)"})
  public void testRun_thenSQLGeneratorInnerJoinResultIsSelectFrom42()
      throws InvocationTargetException {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getQuotedIdentifier(Mockito.<String>any(), anyBoolean(), anyBoolean()))
        .thenReturn("42");

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(sqlDialect);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity dbvEntity = new DBVEntity(container, "", "Description Column Names");

    ArrayList<DBSEntity> objects = new ArrayList<>();
    objects.add(dbvEntity);

    SQLGeneratorInnerJoin sqlGeneratorInnerJoin = new SQLGeneratorInnerJoin();
    sqlGeneratorInnerJoin.initGenerator(objects);

    // Act
    sqlGeneratorInnerJoin.run(new LoggingProgressMonitor());

    // Assert
    verify(dbpDataSource, atLeast(1)).getSQLDialect();
    verify(sqlDialect).getQuotedIdentifier("Name", true, false);
    verify(parent, atLeast(1)).getDataSource();
    assertEquals("SELECT .*\nFROM 42 ", sqlGeneratorInnerJoin.getResult());
  }

  /**
   * Test {@link SQLGeneratorInnerJoin#run(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Then {@link SQLGeneratorInnerJoin} (default constructor) Result is {@code SELECT n.* FROM
   *       42A42 n}.
   * </ul>
   *
   * <p>Method under test: {@link SQLGeneratorInnerJoin#run(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLGeneratorInnerJoin.run(DBRProgressMonitor)"})
  public void testRun_thenSQLGeneratorInnerJoinResultIsSelectNFrom42a42N()
      throws InvocationTargetException {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.validIdentifierPart(anyChar(), anyBoolean())).thenReturn(true);
    when(sqlDialect.getReservedWords()).thenReturn(new ArrayList<>());
    when(sqlDialect.validIdentifierStart(anyChar())).thenReturn(true);
    when(sqlDialect.getStructSeparator()).thenReturn('A');
    when(sqlDialect.getQuotedIdentifier(Mockito.<String>any(), anyBoolean(), anyBoolean()))
        .thenReturn("42");
    when(sqlDialect.getKeywordType(Mockito.<String>any())).thenReturn(DBPKeywordType.KEYWORD);

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(sqlDialect);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity dbvEntity = new DBVEntity(container, "Name", "Description Column Names");

    ArrayList<DBSEntity> objects = new ArrayList<>();
    objects.add(dbvEntity);

    SQLGeneratorInnerJoin sqlGeneratorInnerJoin = new SQLGeneratorInnerJoin();
    sqlGeneratorInnerJoin.initGenerator(objects);

    // Act
    sqlGeneratorInnerJoin.run(new LoggingProgressMonitor());

    // Assert
    verify(dbpDataSource, atLeast(1)).getSQLDialect();
    verify(sqlDialect, atLeast(1)).getKeywordType(Mockito.<String>any());
    verify(sqlDialect, atLeast(1)).getQuotedIdentifier("Name", true, false);
    verify(sqlDialect).getReservedWords();
    verify(sqlDialect).getStructSeparator();
    verify(sqlDialect, atLeast(1)).validIdentifierPart(anyChar(), eq(false));
    verify(sqlDialect).validIdentifierStart('N');
    verify(parent, atLeast(1)).getDataSource();
    assertEquals("SELECT n.*\nFROM 42A42 n", sqlGeneratorInnerJoin.getResult());
  }

  /**
   * Test {@link SQLGeneratorInnerJoin#run(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Then {@link SQLGeneratorInnerJoin} (default constructor) Result is {@code SELECT null.*
   *       FROM 42 null}.
   * </ul>
   *
   * <p>Method under test: {@link SQLGeneratorInnerJoin#run(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLGeneratorInnerJoin.run(DBRProgressMonitor)"})
  public void testRun_thenSQLGeneratorInnerJoinResultIsSelectNullFrom42Null()
      throws InvocationTargetException {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getQuotedIdentifier(Mockito.<String>any(), anyBoolean(), anyBoolean()))
        .thenReturn("42");

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(sqlDialect);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity dbvEntity = new DBVEntity(container, null, "Description Column Names");

    ArrayList<DBSEntity> objects = new ArrayList<>();
    objects.add(dbvEntity);

    SQLGeneratorInnerJoin sqlGeneratorInnerJoin = new SQLGeneratorInnerJoin();
    sqlGeneratorInnerJoin.initGenerator(objects);

    // Act
    sqlGeneratorInnerJoin.run(new LoggingProgressMonitor());

    // Assert
    verify(dbpDataSource, atLeast(1)).getSQLDialect();
    verify(sqlDialect).getQuotedIdentifier("Name", true, false);
    verify(parent, atLeast(1)).getDataSource();
    assertEquals("SELECT null.*\nFROM 42 null", sqlGeneratorInnerJoin.getResult());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link SQLGeneratorInnerJoin}
   *   <li>{@link SQLGeneratorInnerJoin#generateSQL(DBRProgressMonitor, StringBuilder, DBSEntity)}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLGeneratorInnerJoin.<init>()",
    "void SQLGeneratorInnerJoin.generateSQL(DBRProgressMonitor, StringBuilder, DBSEntity)"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    SQLGeneratorInnerJoin actualSqlGeneratorInnerJoin = new SQLGeneratorInnerJoin();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    StringBuilder sql = new StringBuilder("foo");
    DBVEntity object =
        new DBVEntity(new DBVContainer(null, "Name"), "Name", "Description Column Names");
    actualSqlGeneratorInnerJoin.generateSQL(monitor, sql, object);

    // Assert
    assertNull(actualSqlGeneratorInnerJoin.getResult());
    assertNull(actualSqlGeneratorInnerJoin.getObjects());
    assertFalse(actualSqlGeneratorInnerJoin.isCompactSQL());
    assertFalse(actualSqlGeneratorInnerJoin.isExcludeAutoGeneratedColumn());
    assertFalse(actualSqlGeneratorInnerJoin.isIncludePermissions());
    assertFalse(actualSqlGeneratorInnerJoin.isShowCastParams());
    assertFalse(actualSqlGeneratorInnerJoin.isShowFullDdl());
    assertFalse(actualSqlGeneratorInnerJoin.isShowPartitionsDDL());
    assertFalse(actualSqlGeneratorInnerJoin.isUseCustomDataFormat());
    assertTrue(actualSqlGeneratorInnerJoin.isFullyQualifiedNames());
    assertTrue(actualSqlGeneratorInnerJoin.isShowComments());
    assertTrue(actualSqlGeneratorInnerJoin.isUseSeparateForeignKeys());
  }
}
