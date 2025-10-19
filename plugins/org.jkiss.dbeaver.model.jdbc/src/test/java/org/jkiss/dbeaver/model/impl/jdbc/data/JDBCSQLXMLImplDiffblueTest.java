package org.jkiss.dbeaver.model.impl.jdbc.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import org.jkiss.dbeaver.model.app.DBPPlatform;
import org.jkiss.dbeaver.model.data.storage.BytesContentStorage;
import org.jkiss.dbeaver.model.data.storage.ExternalContentStorage;
import org.jkiss.dbeaver.model.data.storage.StreamContentStorage;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class JDBCSQLXMLImplDiffblueTest {
  /**
   * Test {@link JDBCSQLXMLImpl#free()}.
   *
   * <ul>
   *   <li>Given {@link DataInputStream} {@link DataInputStream#close()} throw {@link
   *       IOException#IOException()}.
   *   <li>Then calls {@link DataInputStream#close()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCSQLXMLImpl#free()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCSQLXMLImpl.free()"})
  public void testFree_givenDataInputStreamCloseThrowIOException_thenCallsClose()
      throws IOException, SQLException {
    // Arrange
    DataInputStream stream = mock(DataInputStream.class);
    doThrow(new IOException()).when(stream).close();
    StreamContentStorage storage = new StreamContentStorage(stream);

    // Act
    new JDBCSQLXMLImpl(storage).free();

    // Assert
    verify(stream).close();
  }

  /**
   * Test {@link JDBCSQLXMLImpl#getBinaryStream()}.
   *
   * <ul>
   *   <li>Then return read is minus one.
   * </ul>
   *
   * <p>Method under test: {@link JDBCSQLXMLImpl#getBinaryStream()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.io.InputStream JDBCSQLXMLImpl.getBinaryStream()"})
  public void testGetBinaryStream_thenReturnReadIsMinusOne() throws IOException, SQLException {
    // Arrange, Act and Assert
    int actualReadResult =
        new JDBCSQLXMLImpl(new JDBCContentBytes(null)).getBinaryStream().read(new byte[] {});
    assertEquals(-1, actualReadResult);
  }

  /**
   * Test {@link JDBCSQLXMLImpl#setBinaryStream()}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCSQLXMLImpl#setBinaryStream()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.io.OutputStream JDBCSQLXMLImpl.setBinaryStream()"})
  public void testSetBinaryStream_thenThrowSQLException() throws SQLException {
    // Arrange, Act and Assert
    assertThrows(
        SQLException.class, () -> new JDBCSQLXMLImpl(new JDBCContentBytes(null)).setBinaryStream());
  }

  /**
   * Test {@link JDBCSQLXMLImpl#getCharacterStream()}.
   *
   * <ul>
   *   <li>Then does not throw.
   * </ul>
   *
   * <p>Method under test: {@link JDBCSQLXMLImpl#getCharacterStream()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.io.Reader JDBCSQLXMLImpl.getCharacterStream()"})
  public void testGetCharacterStream_thenDoesNotThrow() throws SQLException {
    // Arrange and Act
    new JDBCSQLXMLImpl(new JDBCContentBytes(null)).getCharacterStream();

    // Assert
  }

  /**
   * Test {@link JDBCSQLXMLImpl#getCharacterStream()}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCSQLXMLImpl#getCharacterStream()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.io.Reader JDBCSQLXMLImpl.getCharacterStream()"})
  public void testGetCharacterStream_thenThrowSQLException() throws IOException, SQLException {
    // Arrange
    BytesContentStorage storage = mock(BytesContentStorage.class);
    when(storage.getContentReader()).thenThrow(new IOException());

    // Act and Assert
    assertThrows(SQLException.class, () -> new JDBCSQLXMLImpl(storage).getCharacterStream());
    verify(storage).getContentReader();
  }

  /**
   * Test {@link JDBCSQLXMLImpl#setCharacterStream()}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCSQLXMLImpl#setCharacterStream()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.io.Writer JDBCSQLXMLImpl.setCharacterStream()"})
  public void testSetCharacterStream_thenThrowSQLException() throws SQLException {
    // Arrange, Act and Assert
    assertThrows(
        SQLException.class,
        () -> new JDBCSQLXMLImpl(new JDBCContentBytes(null)).setCharacterStream());
  }

  /**
   * Test {@link JDBCSQLXMLImpl#getString()}.
   *
   * <p>Method under test: {@link JDBCSQLXMLImpl#getString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCSQLXMLImpl.getString()"})
  public void testGetString() throws SQLException {
    // Arrange
    Path file = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    ExternalContentStorage storage = new ExternalContentStorage(mock(DBPPlatform.class), file);

    // Act and Assert
    assertThrows(SQLException.class, () -> new JDBCSQLXMLImpl(storage).getString());
  }

  /**
   * Test {@link JDBCSQLXMLImpl#getString()}.
   *
   * <ul>
   *   <li>Given {@link BytesContentStorage#BytesContentStorage(byte[], String)} with data is {@code
   *       AXAXAXAX} Bytes is {@code UTF-8} and {@code Encoding}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCSQLXMLImpl#getString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCSQLXMLImpl.getString()"})
  public void testGetString_givenBytesContentStorageWithDataIsAxaxaxaxBytesIsUtf8AndEncoding()
      throws UnsupportedEncodingException, SQLException {
    // Arrange, Act and Assert
    assertThrows(
        SQLException.class,
        () ->
            new JDBCSQLXMLImpl(new BytesContentStorage("AXAXAXAX".getBytes("UTF-8"), "Encoding"))
                .getString());
  }

  /**
   * Test {@link JDBCSQLXMLImpl#getString()}.
   *
   * <ul>
   *   <li>Then return {@code AXAXAXAX}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCSQLXMLImpl#getString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCSQLXMLImpl.getString()"})
  public void testGetString_thenReturnAxaxaxax() throws UnsupportedEncodingException, SQLException {
    // Arrange, Act and Assert
    assertEquals(
        "AXAXAXAX",
        new JDBCSQLXMLImpl(new BytesContentStorage("AXAXAXAX".getBytes("UTF-8"), "UTF-8"))
            .getString());
  }

  /**
   * Test {@link JDBCSQLXMLImpl#getString()}.
   *
   * <ul>
   *   <li>Then return empty string.
   * </ul>
   *
   * <p>Method under test: {@link JDBCSQLXMLImpl#getString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCSQLXMLImpl.getString()"})
  public void testGetString_thenReturnEmptyString() throws SQLException {
    // Arrange, Act and Assert
    assertEquals("", new JDBCSQLXMLImpl(new JDBCContentBytes(null)).getString());
  }

  /**
   * Test {@link JDBCSQLXMLImpl#setString(String)}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCSQLXMLImpl#setString(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCSQLXMLImpl.setString(String)"})
  public void testSetString_thenThrowSQLException() throws SQLException {
    // Arrange, Act and Assert
    assertThrows(
        SQLException.class, () -> new JDBCSQLXMLImpl(new JDBCContentBytes(null)).setString("42"));
  }

  /**
   * Test {@link JDBCSQLXMLImpl#getSource(Class)}.
   *
   * <ul>
   *   <li>Given {@link JDBCContentBytes#JDBCContentBytes(DBCExecutionContext)} with
   *       executionContext is {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCSQLXMLImpl#getSource(Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Source JDBCSQLXMLImpl.getSource(Class)"})
  public void testGetSource_givenJDBCContentBytesWithExecutionContextIsNull_thenReturnNull()
      throws SQLException {
    // Arrange
    JDBCSQLXMLImpl jdbcsqlxmlImpl = new JDBCSQLXMLImpl(new JDBCContentBytes(null));
    Class<Source> sourceClass = Source.class;

    // Act and Assert
    assertNull(jdbcsqlxmlImpl.getSource(sourceClass));
  }

  /**
   * Test {@link JDBCSQLXMLImpl#setResult(Class)}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCSQLXMLImpl#setResult(Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Result JDBCSQLXMLImpl.setResult(Class)"})
  public void testSetResult_thenThrowSQLException() throws SQLException {
    // Arrange
    JDBCSQLXMLImpl jdbcsqlxmlImpl = new JDBCSQLXMLImpl(new JDBCContentBytes(null));
    Class<Result> resultClass = Result.class;

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcsqlxmlImpl.setResult(resultClass));
  }
}
