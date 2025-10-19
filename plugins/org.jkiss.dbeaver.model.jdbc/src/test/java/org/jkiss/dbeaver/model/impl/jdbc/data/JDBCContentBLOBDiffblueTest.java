package org.jkiss.dbeaver.model.impl.jdbc.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Blob;
import java.sql.SQLException;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.model.connection.DBPDriver;
import org.jkiss.dbeaver.model.data.DBDDisplayFormat;
import org.jkiss.dbeaver.model.exec.DBCException;
import org.jkiss.dbeaver.model.exec.DBCExecutionContext;
import org.jkiss.dbeaver.model.exec.jdbc.JDBCPreparedStatement;
import org.jkiss.dbeaver.model.exec.jdbc.JDBCSession;
import org.jkiss.dbeaver.model.impl.jdbc.JDBCDataSource;
import org.jkiss.dbeaver.model.impl.jdbc.JDBCExecutionContext;
import org.jkiss.dbeaver.model.impl.jdbc.JDBCRemoteInstance;
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
public class JDBCContentBLOBDiffblueTest {
  @Mock private Blob blob;

  @Mock private DBCExecutionContext dBCExecutionContext;

  @InjectMocks private JDBCContentBLOB jDBCContentBLOB;

  /**
   * Test {@link JDBCContentBLOB#getLOBLength()}.
   *
   * <ul>
   *   <li>Given {@link JDBCDataSource} {@link JDBCDataSource#getName()} return {@code Name}.
   *   <li>Then calls {@link SerialBlob#length()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCContentBLOB#getLOBLength()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long JDBCContentBLOB.getLOBLength()"})
  public void testGetLOBLength_givenJDBCDataSourceGetNameReturnName_thenCallsLength()
      throws SerialException, DBCException {
    // Arrange
    JDBCDataSource jdbcDataSource = mock(JDBCDataSource.class);
    when(jdbcDataSource.getName()).thenReturn("Name");

    JDBCRemoteInstance instance = mock(JDBCRemoteInstance.class);
    when(instance.getDataSource()).thenReturn(jdbcDataSource);
    JDBCExecutionContext dataSource = new JDBCExecutionContext(instance, true);

    SerialBlob blob = mock(SerialBlob.class);
    when(blob.length()).thenThrow(new UnsupportedOperationException());

    JDBCContentBLOB jdbcContentBLOB = new JDBCContentBLOB(dataSource, blob);

    // Act and Assert
    assertThrows(DBCException.class, () -> jdbcContentBLOB.getLOBLength());
    verify(blob).length();
    verify(jdbcDataSource).getName();
    verify(instance).getDataSource();
  }

  /**
   * Test {@link JDBCContentBLOB#getLOBLength()}.
   *
   * <ul>
   *   <li>Given {@link SerialBlob#SerialBlob(byte[])} with b is {@code AXAXAXAX} Bytes is {@code
   *       UTF-8}.
   *   <li>Then return eight.
   * </ul>
   *
   * <p>Method under test: {@link JDBCContentBLOB#getLOBLength()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long JDBCContentBLOB.getLOBLength()"})
  public void testGetLOBLength_givenSerialBlobWithBIsAxaxaxaxBytesIsUtf8_thenReturnEight()
      throws UnsupportedEncodingException, SQLException, DBCException {
    // Arrange
    JDBCContentBLOB jdbcContentBLOB =
        new JDBCContentBLOB(null, new SerialBlob("AXAXAXAX".getBytes("UTF-8")));

    // Act and Assert
    assertEquals(8L, jdbcContentBLOB.getLOBLength());
  }

  /**
   * Test {@link JDBCContentBLOB#getLOBLength()}.
   *
   * <ul>
   *   <li>Then calls {@link Blob#length()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCContentBLOB#getLOBLength()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long JDBCContentBLOB.getLOBLength()"})
  public void testGetLOBLength_thenCallsLength() throws SQLException, DBCException {
    // Arrange
    when(dBCExecutionContext.getDataSource()).thenReturn(mock(DBPDataSource.class));
    when(blob.length()).thenThrow(new SQLException());

    // Act and Assert
    assertThrows(DBCException.class, () -> jDBCContentBLOB.getLOBLength());
    verify(blob).length();
    verify(dBCExecutionContext).getDataSource();
  }

  /**
   * Test {@link JDBCContentBLOB#getContents(DBRProgressMonitor)}.
   *
   * <p>Method under test: {@link JDBCContentBLOB#getContents(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.data.DBDContentStorage JDBCContentBLOB.getContents(DBRProgressMonitor)"
  })
  public void testGetContents() throws SQLException, DBCException {
    // Arrange
    when(dBCExecutionContext.isConnected()).thenReturn(true);
    when(dBCExecutionContext.getContextAttribute(Mockito.<String>any()))
        .thenReturn("Context Attribute");
    when(dBCExecutionContext.getDataSource()).thenReturn(mock(DBPDataSource.class));
    when(blob.length()).thenThrow(new SQLException());

    // Act and Assert
    assertThrows(
        DBCException.class, () -> jDBCContentBLOB.getContents(new LoggingProgressMonitor()));
    verify(blob).length();
    verify(dBCExecutionContext).getContextAttribute("lob-reading-error-message-is-shown");
    verify(dBCExecutionContext).getDataSource();
    verify(dBCExecutionContext).isConnected();
  }

  /**
   * Test {@link JDBCContentBLOB#getContents(DBRProgressMonitor)}.
   *
   * <p>Method under test: {@link JDBCContentBLOB#getContents(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.data.DBDContentStorage JDBCContentBLOB.getContents(DBRProgressMonitor)"
  })
  public void testGetContents2() throws SQLException, DBCException {
    // Arrange
    JDBCDataSource jdbcDataSource = mock(JDBCDataSource.class);
    when(jdbcDataSource.getName()).thenReturn("Name");

    JDBCRemoteInstance instance = mock(JDBCRemoteInstance.class);
    when(instance.getName()).thenReturn("lob-reading-error-message-is-shown");
    when(instance.getDataSource()).thenReturn(jdbcDataSource);
    JDBCExecutionContext jdbcExecutionContext = new JDBCExecutionContext(instance, true);
    when(dBCExecutionContext.isConnected()).thenReturn(true);
    when(dBCExecutionContext.getContextAttribute(Mockito.<String>any()))
        .thenReturn(jdbcExecutionContext);
    when(dBCExecutionContext.getDataSource()).thenReturn(mock(DBPDataSource.class));
    when(blob.length()).thenThrow(new SQLException());

    // Act and Assert
    assertThrows(
        DBCException.class, () -> jDBCContentBLOB.getContents(new LoggingProgressMonitor()));
    verify(blob).length();
    verify(dBCExecutionContext).getContextAttribute("lob-reading-error-message-is-shown");
    verify(dBCExecutionContext).getDataSource();
    verify(dBCExecutionContext).isConnected();
    verify(jdbcDataSource, atLeast(1)).getName();
    verify(instance).getDataSource();
    verify(instance, atLeast(1)).getName();
  }

  /**
   * Test {@link JDBCContentBLOB#getContents(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Given {@link Blob} {@link Blob#length()} throw {@link
   *       UnsupportedOperationException#UnsupportedOperationException()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCContentBLOB#getContents(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.data.DBDContentStorage JDBCContentBLOB.getContents(DBRProgressMonitor)"
  })
  public void testGetContents_givenBlobLengthThrowUnsupportedOperationException()
      throws SQLException, DBCException {
    // Arrange
    when(dBCExecutionContext.isConnected()).thenReturn(true);
    when(dBCExecutionContext.getContextAttribute(Mockito.<String>any()))
        .thenReturn("Context Attribute");
    when(dBCExecutionContext.getDataSource()).thenReturn(mock(DBPDataSource.class));
    when(blob.length()).thenThrow(new UnsupportedOperationException());

    // Act and Assert
    assertThrows(
        DBCException.class, () -> jDBCContentBLOB.getContents(new LoggingProgressMonitor()));
    verify(blob).length();
    verify(dBCExecutionContext).getContextAttribute("lob-reading-error-message-is-shown");
    verify(dBCExecutionContext).getDataSource();
    verify(dBCExecutionContext).isConnected();
  }

  /**
   * Test {@link JDBCContentBLOB#getContents(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Given {@link DBCExecutionContext} {@link DBCExecutionContext#getContextAttribute(String)}
   *       return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCContentBLOB#getContents(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.data.DBDContentStorage JDBCContentBLOB.getContents(DBRProgressMonitor)"
  })
  public void testGetContents_givenDBCExecutionContextGetContextAttributeReturnNull()
      throws SQLException, DBCException {
    // Arrange
    when(dBCExecutionContext.isConnected()).thenReturn(true);
    when(dBCExecutionContext.getContextAttribute(Mockito.<String>any())).thenReturn(null);
    when(dBCExecutionContext.getDataSource()).thenReturn(mock(DBPDataSource.class));
    when(blob.length()).thenThrow(new SQLException());

    // Act and Assert
    assertThrows(
        DBCException.class, () -> jDBCContentBLOB.getContents(new LoggingProgressMonitor()));
    verify(blob).length();
    verify(dBCExecutionContext).getContextAttribute("lob-reading-error-message-is-shown");
    verify(dBCExecutionContext).getDataSource();
    verify(dBCExecutionContext).isConnected();
  }

  /**
   * Test {@link JDBCContentBLOB#getContents(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Given {@link DBCExecutionContext} {@link DBCExecutionContext#getContextAttribute(String)}
   *       return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCContentBLOB#getContents(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.data.DBDContentStorage JDBCContentBLOB.getContents(DBRProgressMonitor)"
  })
  public void testGetContents_givenDBCExecutionContextGetContextAttributeReturnTrue()
      throws SQLException, DBCException {
    // Arrange
    when(dBCExecutionContext.isConnected()).thenReturn(true);
    when(dBCExecutionContext.getContextAttribute(Mockito.<String>any())).thenReturn(true);
    when(dBCExecutionContext.getDataSource()).thenReturn(mock(DBPDataSource.class));
    when(blob.length()).thenThrow(new SQLException());

    // Act and Assert
    assertThrows(
        DBCException.class, () -> jDBCContentBLOB.getContents(new LoggingProgressMonitor()));
    verify(blob).length();
    verify(dBCExecutionContext).getContextAttribute("lob-reading-error-message-is-shown");
    verify(dBCExecutionContext).getDataSource();
    verify(dBCExecutionContext).isConnected();
  }

  /**
   * Test {@link JDBCContentBLOB#getContents(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Given {@link DBCExecutionContext} {@link DBCExecutionContext#isConnected()} return {@code
   *       false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCContentBLOB#getContents(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.data.DBDContentStorage JDBCContentBLOB.getContents(DBRProgressMonitor)"
  })
  public void testGetContents_givenDBCExecutionContextIsConnectedReturnFalse()
      throws SQLException, DBCException {
    // Arrange
    when(dBCExecutionContext.isConnected()).thenReturn(false);
    when(dBCExecutionContext.getContextAttribute(Mockito.<String>any()))
        .thenReturn("Context Attribute");
    when(dBCExecutionContext.getDataSource()).thenReturn(mock(DBPDataSource.class));
    when(blob.length()).thenThrow(new SQLException());

    // Act and Assert
    assertThrows(
        DBCException.class, () -> jDBCContentBLOB.getContents(new LoggingProgressMonitor()));
    verify(blob).length();
    verify(dBCExecutionContext).getContextAttribute("lob-reading-error-message-is-shown");
    verify(dBCExecutionContext).getDataSource();
    verify(dBCExecutionContext).isConnected();
  }

  /**
   * Test {@link JDBCContentBLOB#getContents(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Given {@link JDBCDataSource} {@link JDBCDataSource#getName()} return {@code null}.
   *   <li>Then calls {@link JDBCDataSource#getName()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCContentBLOB#getContents(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.data.DBDContentStorage JDBCContentBLOB.getContents(DBRProgressMonitor)"
  })
  public void testGetContents_givenJDBCDataSourceGetNameReturnNull_thenCallsGetName()
      throws SQLException, DBCException {
    // Arrange
    JDBCDataSource jdbcDataSource = mock(JDBCDataSource.class);
    when(jdbcDataSource.getName()).thenReturn(null);

    JDBCRemoteInstance instance = mock(JDBCRemoteInstance.class);
    when(instance.getName()).thenReturn("Name");
    when(instance.getDataSource()).thenReturn(jdbcDataSource);
    JDBCExecutionContext jdbcExecutionContext = new JDBCExecutionContext(instance, true);
    when(dBCExecutionContext.isConnected()).thenReturn(true);
    when(dBCExecutionContext.getContextAttribute(Mockito.<String>any()))
        .thenReturn(jdbcExecutionContext);
    when(dBCExecutionContext.getDataSource()).thenReturn(mock(DBPDataSource.class));
    when(blob.length()).thenThrow(new SQLException());

    // Act and Assert
    assertThrows(
        DBCException.class, () -> jDBCContentBLOB.getContents(new LoggingProgressMonitor()));
    verify(blob).length();
    verify(dBCExecutionContext).getContextAttribute("lob-reading-error-message-is-shown");
    verify(dBCExecutionContext).getDataSource();
    verify(dBCExecutionContext).isConnected();
    verify(jdbcDataSource, atLeast(1)).getName();
    verify(instance).getDataSource();
    verify(instance, atLeast(1)).getName();
  }

  /**
   * Test {@link JDBCContentBLOB#getContents(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Given {@link JDBCRemoteInstance} {@link JDBCRemoteInstance#getName()} return {@code
   *       Name}.
   *   <li>Then calls {@link JDBCDataSource#getName()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCContentBLOB#getContents(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.data.DBDContentStorage JDBCContentBLOB.getContents(DBRProgressMonitor)"
  })
  public void testGetContents_givenJDBCRemoteInstanceGetNameReturnName_thenCallsGetName()
      throws SQLException, DBCException {
    // Arrange
    JDBCDataSource jdbcDataSource = mock(JDBCDataSource.class);
    when(jdbcDataSource.getName()).thenReturn("Name");

    JDBCRemoteInstance instance = mock(JDBCRemoteInstance.class);
    when(instance.getName()).thenReturn("Name");
    when(instance.getDataSource()).thenReturn(jdbcDataSource);
    JDBCExecutionContext jdbcExecutionContext = new JDBCExecutionContext(instance, true);
    when(dBCExecutionContext.isConnected()).thenReturn(true);
    when(dBCExecutionContext.getContextAttribute(Mockito.<String>any()))
        .thenReturn(jdbcExecutionContext);
    when(dBCExecutionContext.getDataSource()).thenReturn(mock(DBPDataSource.class));
    when(blob.length()).thenThrow(new SQLException());

    // Act and Assert
    assertThrows(
        DBCException.class, () -> jDBCContentBLOB.getContents(new LoggingProgressMonitor()));
    verify(blob).length();
    verify(dBCExecutionContext).getContextAttribute("lob-reading-error-message-is-shown");
    verify(dBCExecutionContext).getDataSource();
    verify(dBCExecutionContext).isConnected();
    verify(jdbcDataSource, atLeast(1)).getName();
    verify(instance).getDataSource();
    verify(instance).getName();
  }

  /**
   * Test {@link JDBCContentBLOB#getContents(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Given {@link JDBCRemoteInstance} {@link JDBCRemoteInstance#getName()} return {@code
   *       null}.
   *   <li>Then calls {@link JDBCDataSource#getName()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCContentBLOB#getContents(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.data.DBDContentStorage JDBCContentBLOB.getContents(DBRProgressMonitor)"
  })
  public void testGetContents_givenJDBCRemoteInstanceGetNameReturnNull_thenCallsGetName()
      throws SQLException, DBCException {
    // Arrange
    JDBCDataSource jdbcDataSource = mock(JDBCDataSource.class);
    when(jdbcDataSource.getName()).thenReturn("Name");

    JDBCRemoteInstance instance = mock(JDBCRemoteInstance.class);
    when(instance.getName()).thenReturn(null);
    when(instance.getDataSource()).thenReturn(jdbcDataSource);
    JDBCExecutionContext jdbcExecutionContext = new JDBCExecutionContext(instance, true);
    when(dBCExecutionContext.isConnected()).thenReturn(true);
    when(dBCExecutionContext.getContextAttribute(Mockito.<String>any()))
        .thenReturn(jdbcExecutionContext);
    when(dBCExecutionContext.getDataSource()).thenReturn(mock(DBPDataSource.class));
    when(blob.length()).thenThrow(new SQLException());

    // Act and Assert
    assertThrows(
        DBCException.class, () -> jDBCContentBLOB.getContents(new LoggingProgressMonitor()));
    verify(blob).length();
    verify(dBCExecutionContext).getContextAttribute("lob-reading-error-message-is-shown");
    verify(dBCExecutionContext).getDataSource();
    verify(dBCExecutionContext).isConnected();
    verify(jdbcDataSource, atLeast(1)).getName();
    verify(instance).getDataSource();
    verify(instance, atLeast(1)).getName();
  }

  /**
   * Test {@link JDBCContentBLOB#getContents(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCContentBLOB#getContents(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.data.DBDContentStorage JDBCContentBLOB.getContents(DBRProgressMonitor)"
  })
  public void testGetContents_thenReturnNull() throws DBCException {
    // Arrange
    JDBCContentBLOB jdbcContentBLOB = new JDBCContentBLOB(null, null);

    // Act and Assert
    assertNull(jdbcContentBLOB.getContents(new LoggingProgressMonitor()));
  }

  /**
   * Test {@link JDBCContentBLOB#getContents(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Then throw {@link UnsupportedOperationException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCContentBLOB#getContents(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.data.DBDContentStorage JDBCContentBLOB.getContents(DBRProgressMonitor)"
  })
  public void testGetContents_thenThrowUnsupportedOperationException()
      throws SQLException, DBCException {
    // Arrange
    JDBCDataSource jdbcDataSource = mock(JDBCDataSource.class);
    when(jdbcDataSource.getName()).thenReturn("Name");

    JDBCRemoteInstance instance = mock(JDBCRemoteInstance.class);
    when(instance.getName()).thenThrow(new UnsupportedOperationException());
    when(instance.getDataSource()).thenReturn(jdbcDataSource);
    JDBCExecutionContext jdbcExecutionContext = new JDBCExecutionContext(instance, true);
    when(dBCExecutionContext.isConnected()).thenReturn(true);
    when(dBCExecutionContext.getContextAttribute(Mockito.<String>any()))
        .thenReturn(jdbcExecutionContext);
    when(dBCExecutionContext.getDataSource()).thenReturn(mock(DBPDataSource.class));
    when(blob.length()).thenThrow(new SQLException());

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> jDBCContentBLOB.getContents(new LoggingProgressMonitor()));
    verify(blob).length();
    verify(dBCExecutionContext).getContextAttribute("lob-reading-error-message-is-shown");
    verify(dBCExecutionContext).getDataSource();
    verify(dBCExecutionContext).isConnected();
    verify(jdbcDataSource).getName();
    verify(instance).getDataSource();
    verify(instance).getName();
  }

  /**
   * Test {@link JDBCContentBLOB#release()}.
   *
   * <p>Method under test: {@link JDBCContentBLOB#release()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCContentBLOB.release()"})
  public void testRelease() throws UnsupportedEncodingException, SQLException, DBCException {
    // Arrange
    JDBCContentBLOB jdbcContentBLOB =
        new JDBCContentBLOB(null, new SerialBlob("AXAXAXAX".getBytes("UTF-8")));

    // Act
    jdbcContentBLOB.release();

    // Assert
    assertNull(jdbcContentBLOB.getRawValue());
    assertEquals(0L, jdbcContentBLOB.getLOBLength());
    assertEquals(0L, jdbcContentBLOB.getContentLength());
    assertTrue(jdbcContentBLOB.isNull());
  }

  /**
   * Test {@link JDBCContentBLOB#release()}.
   *
   * <p>Method under test: {@link JDBCContentBLOB#release()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCContentBLOB.release()"})
  public void testRelease2() throws SQLException, DBCException {
    // Arrange
    SerialBlob blob = mock(SerialBlob.class);
    doThrow(new SQLException()).when(blob).free();
    JDBCContentBLOB jdbcContentBLOB = new JDBCContentBLOB(null, blob);

    // Act
    jdbcContentBLOB.release();

    // Assert
    verify(blob).free();
    assertNull(jdbcContentBLOB.getRawValue());
    assertEquals(0L, jdbcContentBLOB.getLOBLength());
    assertEquals(0L, jdbcContentBLOB.getContentLength());
    assertTrue(jdbcContentBLOB.isNull());
  }

  /**
   * Test {@link JDBCContentBLOB#release()}.
   *
   * <ul>
   *   <li>Then calls {@link JDBCPreparedStatement#setBinaryStream(int, InputStream)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCContentBLOB#release()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCContentBLOB.release()"})
  public void testRelease_thenCallsSetBinaryStream()
      throws UnsupportedEncodingException, SQLException, DBCException {
    // Arrange
    JDBCPreparedStatement preparedStatement = mock(JDBCPreparedStatement.class);
    doNothing().when(preparedStatement).setBinaryStream(anyInt(), Mockito.<InputStream>any());

    JDBCContentBLOB jdbcContentBLOB =
        new JDBCContentBLOB(null, new SerialBlob("AXAXAXAX".getBytes("UTF-8")));
    jdbcContentBLOB.bindParameter(mock(JDBCSession.class), preparedStatement, null, 1);

    // Act
    jdbcContentBLOB.release();

    // Assert
    verify(preparedStatement).setBinaryStream(eq(1), isA(InputStream.class));
    assertNull(jdbcContentBLOB.getRawValue());
    assertEquals(0L, jdbcContentBLOB.getLOBLength());
    assertEquals(0L, jdbcContentBLOB.getContentLength());
    assertTrue(jdbcContentBLOB.isNull());
  }

  /**
   * Test {@link JDBCContentBLOB#release()}.
   *
   * <ul>
   *   <li>Then {@link JDBCContentBLOB#JDBCContentBLOB(DBCExecutionContext, Blob)} with dataSource
   *       is {@code null} and blob is {@code null} LOBLength is zero.
   * </ul>
   *
   * <p>Method under test: {@link JDBCContentBLOB#release()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCContentBLOB.release()"})
  public void testRelease_thenJDBCContentBLOBWithDataSourceIsNullAndBlobIsNullLOBLengthIsZero()
      throws DBCException {
    // Arrange
    JDBCContentBLOB jdbcContentBLOB = new JDBCContentBLOB(null, null);

    // Act
    jdbcContentBLOB.release();

    // Assert that nothing has changed
    assertEquals(0L, jdbcContentBLOB.getLOBLength());
    assertEquals(0L, jdbcContentBLOB.getContentLength());
    assertTrue(jdbcContentBLOB.isNull());
  }

  /**
   * Test {@link JDBCContentBLOB#bindParameter(JDBCSession, JDBCPreparedStatement, DBSTypedObject,
   * int)}.
   *
   * <ul>
   *   <li>Given {@link Blob} {@link Blob#getBinaryStream()} throw {@link
   *       UnsupportedOperationException#UnsupportedOperationException()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCContentBLOB#bindParameter(JDBCSession, JDBCPreparedStatement,
   * DBSTypedObject, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCContentBLOB.bindParameter(JDBCSession, JDBCPreparedStatement, DBSTypedObject, int)"
  })
  public void testBindParameter_givenBlobGetBinaryStreamThrowUnsupportedOperationException()
      throws SQLException, DBCException {
    // Arrange
    when(blob.getBinaryStream()).thenThrow(new UnsupportedOperationException());
    JDBCSession session = mock(JDBCSession.class);

    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isThreadSafeDriver()).thenReturn(true);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getDriver()).thenReturn(dbpDriver);

    JDBCDataSource jdbcDataSource = mock(JDBCDataSource.class);
    when(jdbcDataSource.getName()).thenReturn("Name");
    when(jdbcDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    JDBCRemoteInstance instance = mock(JDBCRemoteInstance.class);
    when(instance.getDataSource()).thenReturn(jdbcDataSource);
    new JDBCExecutionContext(instance, "Purpose");

    // Act and Assert
    assertThrows(
        DBCException.class,
        () -> jDBCContentBLOB.bindParameter(session, mock(JDBCPreparedStatement.class), null, 1));
    verify(blob).getBinaryStream();
    verify(dbpDataSourceContainer).getDriver();
    verify(dbpDriver).isThreadSafeDriver();
    verify(jdbcDataSource).getContainer();
    verify(jdbcDataSource).getName();
    verify(instance, atLeast(1)).getDataSource();
  }

  /**
   * Test {@link JDBCContentBLOB#isNull()}.
   *
   * <ul>
   *   <li>Given {@link JDBCContentBLOB#JDBCContentBLOB(DBCExecutionContext, Blob)} with dataSource
   *       is {@code null} and blob is {@code null}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCContentBLOB#isNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCContentBLOB.isNull()"})
  public void testIsNull_givenJDBCContentBLOBWithDataSourceIsNullAndBlobIsNull_thenReturnTrue() {
    // Arrange
    JDBCContentBLOB jdbcContentBLOB = new JDBCContentBLOB(null, null);

    // Act and Assert
    assertTrue(jdbcContentBLOB.isNull());
  }

  /**
   * Test {@link JDBCContentBLOB#isNull()}.
   *
   * <ul>
   *   <li>Given {@link SerialBlob#SerialBlob(byte[])} with b is {@code AXAXAXAX} Bytes is {@code
   *       UTF-8}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCContentBLOB#isNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCContentBLOB.isNull()"})
  public void testIsNull_givenSerialBlobWithBIsAxaxaxaxBytesIsUtf8_thenReturnFalse()
      throws UnsupportedEncodingException, SQLException {
    // Arrange
    JDBCContentBLOB jdbcContentBLOB =
        new JDBCContentBLOB(null, new SerialBlob("AXAXAXAX".getBytes("UTF-8")));

    // Act and Assert
    assertFalse(jdbcContentBLOB.isNull());
  }

  /**
   * Test {@link JDBCContentBLOB#createNewContent()}.
   *
   * <p>Method under test: {@link JDBCContentBLOB#createNewContent()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JDBCContentLOB JDBCContentBLOB.createNewContent()"})
  public void testCreateNewContent()
      throws UnsupportedEncodingException, SQLException, DBCException {
    // Arrange
    JDBCContentBLOB jdbcContentBLOB =
        new JDBCContentBLOB(null, new SerialBlob("AXAXAXAX".getBytes("UTF-8")));

    // Act
    JDBCContentLOB actualCreateNewContentResult = jdbcContentBLOB.createNewContent();

    // Assert
    assertTrue(actualCreateNewContentResult instanceof JDBCContentBLOB);
    assertEquals("application/octet-stream", actualCreateNewContentResult.getContentType());
    assertNull(actualCreateNewContentResult.getRawValue());
    assertNull(((JDBCContentBLOB) actualCreateNewContentResult).storage);
    assertEquals(0L, actualCreateNewContentResult.getContentLength());
    assertEquals(0L, actualCreateNewContentResult.getLOBLength());
    assertFalse(actualCreateNewContentResult.isModified());
    assertTrue(actualCreateNewContentResult.isNull());
  }

  /**
   * Test {@link JDBCContentBLOB#getDisplayString(DBDDisplayFormat)}.
   *
   * <ul>
   *   <li>Given {@link SerialBlob#SerialBlob(byte[])} with b is {@code AXAXAXAX} Bytes is {@code
   *       UTF-8}.
   *   <li>Then return {@code [BLOB]}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCContentBLOB#getDisplayString(DBDDisplayFormat)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCContentBLOB.getDisplayString(DBDDisplayFormat)"})
  public void testGetDisplayString_givenSerialBlobWithBIsAxaxaxaxBytesIsUtf8_thenReturnBlob()
      throws UnsupportedEncodingException, SQLException {
    // Arrange
    JDBCContentBLOB jdbcContentBLOB =
        new JDBCContentBLOB(null, new SerialBlob("AXAXAXAX".getBytes("UTF-8")));

    // Act and Assert
    assertEquals("[BLOB]", jdbcContentBLOB.getDisplayString(DBDDisplayFormat.UI));
  }

  /**
   * Test {@link JDBCContentBLOB#getDisplayString(DBDDisplayFormat)}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCContentBLOB#getDisplayString(DBDDisplayFormat)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCContentBLOB.getDisplayString(DBDDisplayFormat)"})
  public void testGetDisplayString_thenReturnNull() {
    // Arrange
    JDBCContentBLOB jdbcContentBLOB = new JDBCContentBLOB(null, null);

    // Act and Assert
    assertNull(jdbcContentBLOB.getDisplayString(DBDDisplayFormat.UI));
  }
}
