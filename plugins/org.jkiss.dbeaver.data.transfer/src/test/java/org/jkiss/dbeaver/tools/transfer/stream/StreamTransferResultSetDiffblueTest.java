package org.jkiss.dbeaver.tools.transfer.stream;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.jkiss.dbeaver.model.data.DBDValueMeta;
import org.jkiss.dbeaver.model.exec.DBCAttributeMetaData;
import org.jkiss.dbeaver.model.exec.DBCException;
import org.jkiss.dbeaver.model.exec.DBCResultSetMetaData;
import org.jkiss.dbeaver.model.exec.DBCSession;
import org.jkiss.dbeaver.model.exec.DBCStatement;
import org.jkiss.dbeaver.model.impl.local.LocalResultSetMeta;
import org.jkiss.dbeaver.model.impl.local.LocalStatement;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class StreamTransferResultSetDiffblueTest {
  /**
   * Test {@link StreamTransferResultSet#StreamTransferResultSet(DBCSession, DBCStatement,
   * StreamEntityMapping)}.
   *
   * <ul>
   *   <li>Then Meta return {@link LocalResultSetMeta}.
   * </ul>
   *
   * <p>Method under test: {@link StreamTransferResultSet#StreamTransferResultSet(DBCSession,
   * DBCStatement, StreamEntityMapping)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void StreamTransferResultSet.<init>(DBCSession, DBCStatement, StreamEntityMapping)"
  })
  public void testNewStreamTransferResultSet_thenMetaReturnLocalResultSetMeta()
      throws DBCException {
    // Arrange
    LocalStatement statement = new LocalStatement(null, "Text");
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    // Act
    StreamTransferResultSet actualStreamTransferResultSet =
        new StreamTransferResultSet(null, statement, new StreamEntityMapping(inputFile));

    // Assert
    DBCResultSetMetaData meta = actualStreamTransferResultSet.getMeta();
    assertTrue(meta instanceof LocalResultSetMeta);
    assertNull(actualStreamTransferResultSet.getResultSetName());
    assertNull(actualStreamTransferResultSet.getDateTimeFormat());
    assertNull(actualStreamTransferResultSet.getRowMeta());
    assertNull(actualStreamTransferResultSet.getSession());
    assertTrue(meta.getAttributes().isEmpty());
    assertTrue(actualStreamTransferResultSet.getAttributeMappings().isEmpty());
    assertSame(statement, actualStreamTransferResultSet.getSourceStatement());
  }

  /**
   * Test {@link StreamTransferResultSet#getAttributeValue(String)} with {@code name}.
   *
   * <p>Method under test: {@link StreamTransferResultSet#getAttributeValue(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object StreamTransferResultSet.getAttributeValue(String)"})
  public void testGetAttributeValueWithName() throws DBCException {
    // Arrange
    LocalStatement statement = new LocalStatement(null, "Text");
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamTransferResultSet streamTransferResultSet =
        new StreamTransferResultSet(null, statement, new StreamEntityMapping(inputFile));

    // Act and Assert
    assertNull(streamTransferResultSet.getAttributeValue("Name"));
  }

  /**
   * Test {@link StreamTransferResultSet#getAttributeValueMeta(int)}.
   *
   * <p>Method under test: {@link StreamTransferResultSet#getAttributeValueMeta(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBDValueMeta StreamTransferResultSet.getAttributeValueMeta(int)"})
  public void testGetAttributeValueMeta() throws DBCException {
    // Arrange
    LocalStatement statement = new LocalStatement(null, "Text");
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamTransferResultSet streamTransferResultSet =
        new StreamTransferResultSet(null, statement, new StreamEntityMapping(inputFile));

    // Act and Assert
    assertNull(streamTransferResultSet.getAttributeValueMeta(1));
  }

  /**
   * Test {@link StreamTransferResultSet#nextRow()}.
   *
   * <p>Method under test: {@link StreamTransferResultSet#nextRow()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean StreamTransferResultSet.nextRow()"})
  public void testNextRow() throws DBCException {
    // Arrange
    LocalStatement statement = new LocalStatement(null, "Text");
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamTransferResultSet streamTransferResultSet =
        new StreamTransferResultSet(null, statement, new StreamEntityMapping(inputFile));

    // Act and Assert
    assertFalse(streamTransferResultSet.nextRow());
  }

  /**
   * Test {@link StreamTransferResultSet#moveTo(int)}.
   *
   * <p>Method under test: {@link StreamTransferResultSet#moveTo(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean StreamTransferResultSet.moveTo(int)"})
  public void testMoveTo() throws DBCException {
    // Arrange
    LocalStatement statement = new LocalStatement(null, "Text");
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamTransferResultSet streamTransferResultSet =
        new StreamTransferResultSet(null, statement, new StreamEntityMapping(inputFile));

    // Act and Assert
    assertFalse(streamTransferResultSet.moveTo(1));
  }

  /**
   * Test {@link StreamTransferResultSet#getMeta()}.
   *
   * <p>Method under test: {@link StreamTransferResultSet#getMeta()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBCResultSetMetaData StreamTransferResultSet.getMeta()"})
  public void testGetMeta() throws DBCException {
    // Arrange
    LocalStatement statement = new LocalStatement(null, "Text");
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamTransferResultSet streamTransferResultSet =
        new StreamTransferResultSet(null, statement, new StreamEntityMapping(inputFile));

    // Act
    DBCResultSetMetaData actualMeta = streamTransferResultSet.getMeta();
    List<? extends DBCAttributeMetaData> actualAttributes = actualMeta.getAttributes();

    // Assert
    assertTrue(actualMeta instanceof LocalResultSetMeta);
    List<? extends DBCAttributeMetaData> attributes = actualMeta.getAttributes();
    assertTrue(attributes.isEmpty());
    assertSame(attributes, actualAttributes);
  }

  /**
   * Test {@link StreamTransferResultSet#getFeature(String)}.
   *
   * <p>Method under test: {@link StreamTransferResultSet#getFeature(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object StreamTransferResultSet.getFeature(String)"})
  public void testGetFeature() {
    // Arrange
    LocalStatement statement = new LocalStatement(null, "Text");
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamTransferResultSet streamTransferResultSet =
        new StreamTransferResultSet(null, statement, new StreamEntityMapping(inputFile));

    // Act and Assert
    assertNull(streamTransferResultSet.getFeature("Name"));
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link StreamTransferResultSet#setStreamRow(Object[])}
   *   <li>{@link StreamTransferResultSet#close()}
   *   <li>{@link StreamTransferResultSet#getAttributeMappings()}
   *   <li>{@link StreamTransferResultSet#getDateTimeFormat()}
   *   <li>{@link StreamTransferResultSet#getResultSetName()}
   *   <li>{@link StreamTransferResultSet#getRowMeta()}
   *   <li>{@link StreamTransferResultSet#getSession()}
   *   <li>{@link StreamTransferResultSet#getSourceStatement()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void StreamTransferResultSet.close()",
    "List StreamTransferResultSet.getAttributeMappings()",
    "DateTimeFormatter StreamTransferResultSet.getDateTimeFormat()",
    "String StreamTransferResultSet.getResultSetName()",
    "DBDValueMeta StreamTransferResultSet.getRowMeta()",
    "DBCSession StreamTransferResultSet.getSession()",
    "DBCStatement StreamTransferResultSet.getSourceStatement()",
    "void StreamTransferResultSet.setStreamRow(Object[])"
  })
  public void testGettersAndSetters() throws DBCException {
    // Arrange
    LocalStatement statement = new LocalStatement(null, "Text");
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamTransferResultSet streamTransferResultSet =
        new StreamTransferResultSet(null, statement, new StreamEntityMapping(inputFile));

    // Act
    streamTransferResultSet.setStreamRow(new Object[] {"Stream Row"});
    streamTransferResultSet.close();
    List<StreamDataImporterColumnInfo> actualAttributeMappings =
        streamTransferResultSet.getAttributeMappings();
    DateTimeFormatter actualDateTimeFormat = streamTransferResultSet.getDateTimeFormat();
    String actualResultSetName = streamTransferResultSet.getResultSetName();
    DBDValueMeta actualRowMeta = streamTransferResultSet.getRowMeta();
    DBCSession actualSession = streamTransferResultSet.getSession();
    DBCStatement actualSourceStatement = streamTransferResultSet.getSourceStatement();

    // Assert
    assertNull(actualResultSetName);
    assertNull(actualDateTimeFormat);
    assertNull(actualRowMeta);
    assertNull(actualSession);
    assertTrue(actualAttributeMappings.isEmpty());
    assertSame(statement, actualSourceStatement);
  }
}
