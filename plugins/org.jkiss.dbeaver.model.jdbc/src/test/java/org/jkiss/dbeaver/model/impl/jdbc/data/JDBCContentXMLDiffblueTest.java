package org.jkiss.dbeaver.model.impl.jdbc.data;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.FileDescriptor;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.sql.SQLXML;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.app.DBPPlatform;
import org.jkiss.dbeaver.model.data.DBDContentStorage;
import org.jkiss.dbeaver.model.data.DBDDisplayFormat;
import org.jkiss.dbeaver.model.data.storage.StreamContentStorage;
import org.jkiss.dbeaver.model.data.storage.StringContentStorage;
import org.jkiss.dbeaver.model.data.storage.TemporaryContentStorage;
import org.jkiss.dbeaver.model.exec.DBCException;
import org.jkiss.dbeaver.model.exec.DBCExecutionContext;
import org.jkiss.dbeaver.model.exec.jdbc.JDBCPreparedStatement;
import org.jkiss.dbeaver.model.exec.jdbc.JDBCSession;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.jkiss.dbeaver.model.struct.DBSTypedObject;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class JDBCContentXMLDiffblueTest {
  @Mock private DBCExecutionContext dBCExecutionContext;

  @InjectMocks private JDBCContentXML jDBCContentXML;

  @Mock private SQLXML sQLXML;

  /**
   * Test {@link JDBCContentXML#getContents(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Given {@link SQLXML} {@link SQLXML#free()} throw {@link SQLException#SQLException()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCContentXML#getContents(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBDContentStorage JDBCContentXML.getContents(DBRProgressMonitor)"})
  public void testGetContents_givenSqlxmlFreeThrowSQLException()
      throws IOException, SQLException, DBCException {
    // Arrange
    doThrow(new SQLException()).when(sQLXML).free();
    when(sQLXML.getCharacterStream()).thenReturn(new StringReader("foo"));

    // Act
    DBDContentStorage actualContents = jDBCContentXML.getContents(new LoggingProgressMonitor());

    // Assert
    verify(sQLXML).free();
    verify(sQLXML).getCharacterStream();
    DBDContentStorage dbdContentStorage = jDBCContentXML.storage;
    assertTrue(dbdContentStorage instanceof StringContentStorage);
    assertEquals("UTF-8", dbdContentStorage.getCharset());
    assertEquals("foo", ((StringContentStorage) dbdContentStorage).getCachedValue());
    assertNull(jDBCContentXML.getRawValue());
    byte[] byteArray = new byte[3];
    assertEquals(3, dbdContentStorage.getContentStream().read(byteArray));
    assertEquals(3L, dbdContentStorage.getContentLength());
    assertEquals(3L, jDBCContentXML.getContentLength());
    assertSame(jDBCContentXML.storage, actualContents);
    assertArrayEquals("foo".getBytes("UTF-8"), byteArray);
  }

  /**
   * Test {@link JDBCContentXML#getContents(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Given {@link SQLXML} {@link SQLXML#getCharacterStream()} return {@link
   *       FileReader#FileReader(FileDescriptor)} with {@link FileDescriptor#FileDescriptor()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCContentXML#getContents(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBDContentStorage JDBCContentXML.getContents(DBRProgressMonitor)"})
  public void testGetContents_givenSqlxmlGetCharacterStreamReturnFileReaderWithFileDescriptor()
      throws SQLException, DBCException {
    // Arrange
    when(sQLXML.getCharacterStream()).thenReturn(new FileReader(new FileDescriptor()));

    // Act and Assert
    assertThrows(
        DBCException.class, () -> jDBCContentXML.getContents(new LoggingProgressMonitor()));
    verify(sQLXML).getCharacterStream();
  }

  /**
   * Test {@link JDBCContentXML#getContents(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Then calls {@link DBCExecutionContext#getDataSource()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCContentXML#getContents(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBDContentStorage JDBCContentXML.getContents(DBRProgressMonitor)"})
  public void testGetContents_thenCallsGetDataSource() throws SQLException, DBCException {
    // Arrange
    when(dBCExecutionContext.getDataSource()).thenReturn(mock(DBPDataSource.class));
    when(sQLXML.getCharacterStream()).thenThrow(new SQLException());

    // Act and Assert
    assertThrows(
        DBCException.class, () -> jDBCContentXML.getContents(new LoggingProgressMonitor()));
    verify(sQLXML).getCharacterStream();
    verify(dBCExecutionContext).getDataSource();
  }

  /**
   * Test {@link JDBCContentXML#getContents(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Then {@link JDBCContentXML} {@link JDBCContentLOB#storage} {@link StringContentStorage}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCContentXML#getContents(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBDContentStorage JDBCContentXML.getContents(DBRProgressMonitor)"})
  public void testGetContents_thenJDBCContentXMLStorageStringContentStorage()
      throws IOException, SQLException, DBCException {
    // Arrange
    doNothing().when(sQLXML).free();
    when(sQLXML.getCharacterStream()).thenReturn(new StringReader("foo"));

    // Act
    DBDContentStorage actualContents = jDBCContentXML.getContents(new LoggingProgressMonitor());

    // Assert
    verify(sQLXML).free();
    verify(sQLXML).getCharacterStream();
    DBDContentStorage dbdContentStorage = jDBCContentXML.storage;
    assertTrue(dbdContentStorage instanceof StringContentStorage);
    assertEquals("UTF-8", dbdContentStorage.getCharset());
    assertEquals("foo", ((StringContentStorage) dbdContentStorage).getCachedValue());
    assertNull(jDBCContentXML.getRawValue());
    byte[] byteArray = new byte[3];
    assertEquals(3, dbdContentStorage.getContentStream().read(byteArray));
    assertEquals(3L, dbdContentStorage.getContentLength());
    assertEquals(3L, jDBCContentXML.getContentLength());
    assertSame(jDBCContentXML.storage, actualContents);
    assertArrayEquals("foo".getBytes("UTF-8"), byteArray);
  }

  /**
   * Test {@link JDBCContentXML#release()}.
   *
   * <p>Method under test: {@link JDBCContentXML#release()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCContentXML.release()"})
  public void testRelease() throws SQLException {
    // Arrange
    JDBCSQLXMLImpl xml = mock(JDBCSQLXMLImpl.class);
    doThrow(new SQLException()).when(xml).free();
    JDBCContentXML jdbcContentXML = new JDBCContentXML(null, xml);

    // Act
    jdbcContentXML.release();

    // Assert
    verify(xml).free();
    assertNull(jdbcContentXML.getRawValue());
    assertTrue(jdbcContentXML.isNull());
  }

  /**
   * Test {@link JDBCContentXML#release()}.
   *
   * <ul>
   *   <li>Given {@link ByteArrayInputStream#ByteArrayInputStream(byte[])} with {@code AXAXAXAX}
   *       Bytes is {@code UTF-8}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCContentXML#release()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCContentXML.release()"})
  public void testRelease_givenByteArrayInputStreamWithAxaxaxaxBytesIsUtf8()
      throws UnsupportedEncodingException {
    // Arrange
    StreamContentStorage storage =
        new StreamContentStorage(new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")));
    JDBCSQLXMLImpl xml = new JDBCSQLXMLImpl(storage);
    JDBCContentXML jdbcContentXML = new JDBCContentXML(null, xml);

    // Act
    jdbcContentXML.release();

    // Assert
    assertNull(jdbcContentXML.getRawValue());
    assertTrue(jdbcContentXML.isNull());
  }

  /**
   * Test {@link JDBCContentXML#release()}.
   *
   * <ul>
   *   <li>Given {@link DataInputStream} {@link DataInputStream#close()} throw {@link
   *       IOException#IOException()}.
   *   <li>Then calls {@link DataInputStream#close()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCContentXML#release()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCContentXML.release()"})
  public void testRelease_givenDataInputStreamCloseThrowIOException_thenCallsClose()
      throws IOException {
    // Arrange
    DataInputStream stream = mock(DataInputStream.class);
    doThrow(new IOException()).when(stream).close();
    StreamContentStorage storage = new StreamContentStorage(stream);
    JDBCSQLXMLImpl xml = new JDBCSQLXMLImpl(storage);
    JDBCContentXML jdbcContentXML = new JDBCContentXML(null, xml);

    // Act
    jdbcContentXML.release();

    // Assert
    verify(stream).close();
    assertNull(jdbcContentXML.getRawValue());
    assertTrue(jdbcContentXML.isNull());
  }

  /**
   * Test {@link JDBCContentXML#release()}.
   *
   * <ul>
   *   <li>Given {@link JDBCContentBytes#JDBCContentBytes(DBCExecutionContext)} with
   *       executionContext is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCContentXML#release()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCContentXML.release()"})
  public void testRelease_givenJDBCContentBytesWithExecutionContextIsNull() {
    // Arrange
    JDBCSQLXMLImpl xml = new JDBCSQLXMLImpl(new JDBCContentBytes(null));
    JDBCContentXML jdbcContentXML = new JDBCContentXML(null, xml);

    // Act
    jdbcContentXML.release();

    // Assert
    assertNull(jdbcContentXML.getRawValue());
    assertTrue(jdbcContentXML.isNull());
  }

  /**
   * Test {@link JDBCContentXML#release()}.
   *
   * <ul>
   *   <li>Given {@link JDBCSQLXMLImpl#JDBCSQLXMLImpl(DBDContentStorage)} with storage is {@code
   *       null}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCContentXML#release()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCContentXML.release()"})
  public void testRelease_givenJDBCSQLXMLImplWithStorageIsNull() {
    // Arrange
    JDBCContentXML jdbcContentXML = new JDBCContentXML(null, new JDBCSQLXMLImpl(null));

    // Act
    jdbcContentXML.release();

    // Assert
    assertNull(jdbcContentXML.getRawValue());
    assertTrue(jdbcContentXML.isNull());
  }

  /**
   * Test {@link JDBCContentXML#release()}.
   *
   * <ul>
   *   <li>Given Property is {@code java.io.tmpdir}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCContentXML#release()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCContentXML.release()"})
  public void testRelease_givenPropertyIsJavaIoTmpdir() {
    // Arrange
    System.getProperty("java.io.tmpdir");
    TemporaryContentStorage storage =
        new TemporaryContentStorage(mock(DBPPlatform.class), null, "UTF-8", true);
    JDBCContentXML jdbcContentXML = new JDBCContentXML(null, new JDBCSQLXMLImpl(storage));

    // Act
    jdbcContentXML.release();

    // Assert
    assertNull(jdbcContentXML.getRawValue());
    assertTrue(jdbcContentXML.isNull());
  }

  /**
   * Test {@link JDBCContentXML#release()}.
   *
   * <ul>
   *   <li>Given {@link StreamContentStorage#StreamContentStorage(InputStream)} with stream is
   *       {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCContentXML#release()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCContentXML.release()"})
  public void testRelease_givenStreamContentStorageWithStreamIsNull() {
    // Arrange
    JDBCSQLXMLImpl xml = new JDBCSQLXMLImpl(new StreamContentStorage(null));
    JDBCContentXML jdbcContentXML = new JDBCContentXML(null, xml);

    // Act
    jdbcContentXML.release();

    // Assert
    assertNull(jdbcContentXML.getRawValue());
    assertTrue(jdbcContentXML.isNull());
  }

  /**
   * Test {@link JDBCContentXML#release()}.
   *
   * <ul>
   *   <li>Given {@link StringContentStorage#StringContentStorage(String)} with {@code Data}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCContentXML#release()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCContentXML.release()"})
  public void testRelease_givenStringContentStorageWithData() {
    // Arrange
    JDBCSQLXMLImpl xml = new JDBCSQLXMLImpl(new StringContentStorage("Data"));
    JDBCContentXML jdbcContentXML = new JDBCContentXML(null, xml);

    // Act
    jdbcContentXML.release();

    // Assert
    assertNull(jdbcContentXML.getRawValue());
    assertTrue(jdbcContentXML.isNull());
  }

  /**
   * Test {@link JDBCContentXML#release()}.
   *
   * <ul>
   *   <li>Then {@link JDBCContentXML#JDBCContentXML(DBCExecutionContext, SQLXML)} with
   *       executionContext is {@code null} and xml is {@code null} Null.
   * </ul>
   *
   * <p>Method under test: {@link JDBCContentXML#release()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCContentXML.release()"})
  public void testRelease_thenJDBCContentXMLWithExecutionContextIsNullAndXmlIsNullNull() {
    // Arrange
    JDBCContentXML jdbcContentXML = new JDBCContentXML(null, null);

    // Act
    jdbcContentXML.release();

    // Assert that nothing has changed
    assertTrue(jdbcContentXML.isNull());
  }

  /**
   * Test {@link JDBCContentXML#bindParameter(JDBCSession, JDBCPreparedStatement, DBSTypedObject,
   * int)}.
   *
   * <ul>
   *   <li>When {@link JDBCPreparedStatement} {@link JDBCPreparedStatement#setSQLXML(int, SQLXML)}
   *       does nothing.
   * </ul>
   *
   * <p>Method under test: {@link JDBCContentXML#bindParameter(JDBCSession, JDBCPreparedStatement,
   * DBSTypedObject, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCContentXML.bindParameter(JDBCSession, JDBCPreparedStatement, DBSTypedObject, int)"
  })
  public void testBindParameter_whenJDBCPreparedStatementSetSQLXMLDoesNothing()
      throws SQLException, DBCException {
    // Arrange
    JDBCSession session = mock(JDBCSession.class);

    JDBCPreparedStatement preparedStatement = mock(JDBCPreparedStatement.class);
    doNothing().when(preparedStatement).setSQLXML(anyInt(), Mockito.<SQLXML>any());

    // Act
    jDBCContentXML.bindParameter(session, preparedStatement, null, 1);

    // Assert
    verify(preparedStatement).setSQLXML(eq(1), isA(SQLXML.class));
  }

  /**
   * Test {@link JDBCContentXML#getRawValue()}.
   *
   * <p>Method under test: {@link JDBCContentXML#getRawValue()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"SQLXML JDBCContentXML.getRawValue()"})
  public void testGetRawValue() {
    // Arrange
    JDBCSQLXMLImpl xml = new JDBCSQLXMLImpl(new JDBCContentBytes(null));
    JDBCContentXML jdbcContentXML = new JDBCContentXML(null, xml);

    // Act
    SQLXML actualRawValue = jdbcContentXML.getRawValue();

    // Assert
    assertSame(jdbcContentXML.xml, actualRawValue);
  }

  /**
   * Test {@link JDBCContentXML#isNull()}.
   *
   * <ul>
   *   <li>Given {@link JDBCContentBytes#JDBCContentBytes(DBCExecutionContext)} with
   *       executionContext is {@code null}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCContentXML#isNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCContentXML.isNull()"})
  public void testIsNull_givenJDBCContentBytesWithExecutionContextIsNull_thenReturnFalse() {
    // Arrange
    JDBCSQLXMLImpl xml = new JDBCSQLXMLImpl(new JDBCContentBytes(null));
    JDBCContentXML jdbcContentXML = new JDBCContentXML(null, xml);

    // Act and Assert
    assertFalse(jdbcContentXML.isNull());
  }

  /**
   * Test {@link JDBCContentXML#isNull()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCContentXML#isNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCContentXML.isNull()"})
  public void testIsNull_thenReturnTrue() {
    // Arrange
    JDBCContentXML jdbcContentXML = new JDBCContentXML(null, null);

    // Act and Assert
    assertTrue(jdbcContentXML.isNull());
  }

  /**
   * Test {@link JDBCContentXML#createNewContent()}.
   *
   * <p>Method under test: {@link JDBCContentXML#createNewContent()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JDBCContentLOB JDBCContentXML.createNewContent()"})
  public void testCreateNewContent() throws DBCException {
    // Arrange
    JDBCSQLXMLImpl xml = new JDBCSQLXMLImpl(new JDBCContentBytes(null));
    JDBCContentXML jdbcContentXML = new JDBCContentXML(null, xml);

    // Act
    JDBCContentLOB actualCreateNewContentResult = jdbcContentXML.createNewContent();

    // Assert
    assertTrue(actualCreateNewContentResult instanceof JDBCContentXML);
    assertEquals("text/xml", actualCreateNewContentResult.getContentType());
    assertNull(actualCreateNewContentResult.getRawValue());
    assertNull(((JDBCContentXML) actualCreateNewContentResult).storage);
    assertEquals(-1L, actualCreateNewContentResult.getContentLength());
    assertEquals(-1L, actualCreateNewContentResult.getLOBLength());
    assertFalse(actualCreateNewContentResult.isModified());
    assertTrue(actualCreateNewContentResult.isNull());
  }

  /**
   * Test {@link JDBCContentXML#getDisplayString(DBDDisplayFormat)}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCContentXML#getDisplayString(DBDDisplayFormat)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String JDBCContentXML.getDisplayString(DBDDisplayFormat)"})
  public void testGetDisplayString_thenReturnNull() {
    // Arrange
    JDBCContentXML jdbcContentXML = new JDBCContentXML(null, null);

    // Act and Assert
    assertNull(jdbcContentXML.getDisplayString(DBDDisplayFormat.UI));
  }

  /**
   * Test {@link JDBCContentXML#getDisplayString(DBDDisplayFormat)}.
   *
   * <ul>
   *   <li>Then return {@code [XML]}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCContentXML#getDisplayString(DBDDisplayFormat)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String JDBCContentXML.getDisplayString(DBDDisplayFormat)"})
  public void testGetDisplayString_thenReturnXml() {
    // Arrange
    JDBCSQLXMLImpl xml = new JDBCSQLXMLImpl(new JDBCContentBytes(null));
    JDBCContentXML jdbcContentXML = new JDBCContentXML(null, xml);

    // Act and Assert
    assertEquals("[XML]", jdbcContentXML.getDisplayString(DBDDisplayFormat.UI));
  }
}
