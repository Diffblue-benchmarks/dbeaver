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
import java.sql.Clob;
import java.sql.SQLException;
import javax.sql.rowset.serial.SerialClob;
import org.jkiss.dbeaver.model.DBPDataSource;
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
public class JDBCContentCLOBDiffblueTest {
  @Mock private Clob clob;

  @Mock private DBCExecutionContext dBCExecutionContext;

  @InjectMocks private JDBCContentCLOB jDBCContentCLOB;

  /**
   * Test {@link JDBCContentCLOB#getLOBLength()}.
   *
   * <ul>
   *   <li>Given {@link SerialClob#SerialClob(char[])} with ch is {@code AZAZ} toCharArray.
   *   <li>Then return four.
   * </ul>
   *
   * <p>Method under test: {@link JDBCContentCLOB#getLOBLength()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long JDBCContentCLOB.getLOBLength()"})
  public void testGetLOBLength_givenSerialClobWithChIsAzazToCharArray_thenReturnFour()
      throws SQLException, DBCException {
    // Arrange
    SerialClob clob = new SerialClob("AZAZ".toCharArray());
    JDBCContentCLOB jdbcContentCLOB = new JDBCContentCLOB(null, clob);

    // Act and Assert
    assertEquals(4L, jdbcContentCLOB.getLOBLength());
  }

  /**
   * Test {@link JDBCContentCLOB#getLOBLength()}.
   *
   * <ul>
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link JDBCContentCLOB#getLOBLength()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long JDBCContentCLOB.getLOBLength()"})
  public void testGetLOBLength_thenReturnZero() throws DBCException {
    // Arrange
    JDBCContentCLOB jdbcContentCLOB = new JDBCContentCLOB(null, null);

    // Act and Assert
    assertEquals(0L, jdbcContentCLOB.getLOBLength());
  }

  /**
   * Test {@link JDBCContentCLOB#getLOBLength()}.
   *
   * <ul>
   *   <li>Then throw {@link DBCException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCContentCLOB#getLOBLength()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long JDBCContentCLOB.getLOBLength()"})
  public void testGetLOBLength_thenThrowDBCException() throws SQLException, DBCException {
    // Arrange
    when(dBCExecutionContext.getDataSource()).thenReturn(mock(DBPDataSource.class));
    when(clob.length()).thenThrow(new SQLException());

    // Act and Assert
    assertThrows(DBCException.class, () -> jDBCContentCLOB.getLOBLength());
    verify(clob).length();
    verify(dBCExecutionContext).getDataSource();
  }

  /**
   * Test {@link JDBCContentCLOB#getContents(DBRProgressMonitor)}.
   *
   * <p>Method under test: {@link JDBCContentCLOB#getContents(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.data.DBDContentStorage JDBCContentCLOB.getContents(DBRProgressMonitor)"
  })
  public void testGetContents() throws SQLException, DBCException {
    // Arrange
    when(dBCExecutionContext.isConnected()).thenReturn(true);
    when(dBCExecutionContext.getContextAttribute(Mockito.<String>any()))
        .thenReturn("Context Attribute");
    when(dBCExecutionContext.getDataSource()).thenReturn(mock(DBPDataSource.class));
    when(clob.length()).thenThrow(new SQLException());

    // Act and Assert
    assertThrows(
        DBCException.class, () -> jDBCContentCLOB.getContents(new LoggingProgressMonitor()));
    verify(clob).length();
    verify(dBCExecutionContext).getContextAttribute("lob-reading-error-message-is-shown");
    verify(dBCExecutionContext).getDataSource();
    verify(dBCExecutionContext).isConnected();
  }

  /**
   * Test {@link JDBCContentCLOB#getContents(DBRProgressMonitor)}.
   *
   * <p>Method under test: {@link JDBCContentCLOB#getContents(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.data.DBDContentStorage JDBCContentCLOB.getContents(DBRProgressMonitor)"
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
    when(clob.length()).thenThrow(new SQLException());

    // Act and Assert
    assertThrows(
        DBCException.class, () -> jDBCContentCLOB.getContents(new LoggingProgressMonitor()));
    verify(clob).length();
    verify(dBCExecutionContext).getContextAttribute("lob-reading-error-message-is-shown");
    verify(dBCExecutionContext).getDataSource();
    verify(dBCExecutionContext).isConnected();
    verify(jdbcDataSource, atLeast(1)).getName();
    verify(instance).getDataSource();
    verify(instance, atLeast(1)).getName();
  }

  /**
   * Test {@link JDBCContentCLOB#getContents(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Given {@link Clob} {@link Clob#length()} throw {@link
   *       RuntimeException#RuntimeException()}.
   *   <li>Then throw {@link DBCException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCContentCLOB#getContents(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.data.DBDContentStorage JDBCContentCLOB.getContents(DBRProgressMonitor)"
  })
  public void testGetContents_givenClobLengthThrowRuntimeException_thenThrowDBCException()
      throws SQLException, DBCException {
    // Arrange
    when(dBCExecutionContext.isConnected()).thenReturn(true);
    when(dBCExecutionContext.getContextAttribute(Mockito.<String>any()))
        .thenReturn("Context Attribute");
    when(dBCExecutionContext.getDataSource()).thenReturn(mock(DBPDataSource.class));
    when(clob.length()).thenThrow(new RuntimeException());

    // Act and Assert
    assertThrows(
        DBCException.class, () -> jDBCContentCLOB.getContents(new LoggingProgressMonitor()));
    verify(clob).length();
    verify(dBCExecutionContext).getContextAttribute("lob-reading-error-message-is-shown");
    verify(dBCExecutionContext).getDataSource();
    verify(dBCExecutionContext).isConnected();
  }

  /**
   * Test {@link JDBCContentCLOB#getContents(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Given {@link DBCExecutionContext} {@link DBCExecutionContext#getContextAttribute(String)}
   *       return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCContentCLOB#getContents(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.data.DBDContentStorage JDBCContentCLOB.getContents(DBRProgressMonitor)"
  })
  public void testGetContents_givenDBCExecutionContextGetContextAttributeReturnNull()
      throws SQLException, DBCException {
    // Arrange
    when(dBCExecutionContext.isConnected()).thenReturn(true);
    when(dBCExecutionContext.getContextAttribute(Mockito.<String>any())).thenReturn(null);
    when(dBCExecutionContext.getDataSource()).thenReturn(mock(DBPDataSource.class));
    when(clob.length()).thenThrow(new SQLException());

    // Act and Assert
    assertThrows(
        DBCException.class, () -> jDBCContentCLOB.getContents(new LoggingProgressMonitor()));
    verify(clob).length();
    verify(dBCExecutionContext).getContextAttribute("lob-reading-error-message-is-shown");
    verify(dBCExecutionContext).getDataSource();
    verify(dBCExecutionContext).isConnected();
  }

  /**
   * Test {@link JDBCContentCLOB#getContents(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Given {@link DBCExecutionContext} {@link DBCExecutionContext#getContextAttribute(String)}
   *       return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCContentCLOB#getContents(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.data.DBDContentStorage JDBCContentCLOB.getContents(DBRProgressMonitor)"
  })
  public void testGetContents_givenDBCExecutionContextGetContextAttributeReturnTrue()
      throws SQLException, DBCException {
    // Arrange
    when(dBCExecutionContext.isConnected()).thenReturn(true);
    when(dBCExecutionContext.getContextAttribute(Mockito.<String>any())).thenReturn(true);
    when(dBCExecutionContext.getDataSource()).thenReturn(mock(DBPDataSource.class));
    when(clob.length()).thenThrow(new SQLException());

    // Act and Assert
    assertThrows(
        DBCException.class, () -> jDBCContentCLOB.getContents(new LoggingProgressMonitor()));
    verify(clob).length();
    verify(dBCExecutionContext).getContextAttribute("lob-reading-error-message-is-shown");
    verify(dBCExecutionContext).getDataSource();
    verify(dBCExecutionContext).isConnected();
  }

  /**
   * Test {@link JDBCContentCLOB#getContents(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Given {@link DBCExecutionContext} {@link DBCExecutionContext#isConnected()} return {@code
   *       false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCContentCLOB#getContents(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.data.DBDContentStorage JDBCContentCLOB.getContents(DBRProgressMonitor)"
  })
  public void testGetContents_givenDBCExecutionContextIsConnectedReturnFalse()
      throws SQLException, DBCException {
    // Arrange
    when(dBCExecutionContext.isConnected()).thenReturn(false);
    when(dBCExecutionContext.getContextAttribute(Mockito.<String>any()))
        .thenReturn("Context Attribute");
    when(dBCExecutionContext.getDataSource()).thenReturn(mock(DBPDataSource.class));
    when(clob.length()).thenThrow(new SQLException());

    // Act and Assert
    assertThrows(
        DBCException.class, () -> jDBCContentCLOB.getContents(new LoggingProgressMonitor()));
    verify(clob).length();
    verify(dBCExecutionContext).getContextAttribute("lob-reading-error-message-is-shown");
    verify(dBCExecutionContext).getDataSource();
    verify(dBCExecutionContext).isConnected();
  }

  /**
   * Test {@link JDBCContentCLOB#getContents(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Given {@link JDBCDataSource} {@link JDBCDataSource#getName()} return {@code null}.
   *   <li>Then calls {@link JDBCDataSource#getName()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCContentCLOB#getContents(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.data.DBDContentStorage JDBCContentCLOB.getContents(DBRProgressMonitor)"
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
    when(clob.length()).thenThrow(new SQLException());

    // Act and Assert
    assertThrows(
        DBCException.class, () -> jDBCContentCLOB.getContents(new LoggingProgressMonitor()));
    verify(clob).length();
    verify(dBCExecutionContext).getContextAttribute("lob-reading-error-message-is-shown");
    verify(dBCExecutionContext).getDataSource();
    verify(dBCExecutionContext).isConnected();
    verify(jdbcDataSource, atLeast(1)).getName();
    verify(instance).getDataSource();
    verify(instance, atLeast(1)).getName();
  }

  /**
   * Test {@link JDBCContentCLOB#getContents(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Given {@link JDBCRemoteInstance} {@link JDBCRemoteInstance#getName()} return {@code
   *       Name}.
   *   <li>Then calls {@link JDBCDataSource#getName()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCContentCLOB#getContents(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.data.DBDContentStorage JDBCContentCLOB.getContents(DBRProgressMonitor)"
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
    when(clob.length()).thenThrow(new SQLException());

    // Act and Assert
    assertThrows(
        DBCException.class, () -> jDBCContentCLOB.getContents(new LoggingProgressMonitor()));
    verify(clob).length();
    verify(dBCExecutionContext).getContextAttribute("lob-reading-error-message-is-shown");
    verify(dBCExecutionContext).getDataSource();
    verify(dBCExecutionContext).isConnected();
    verify(jdbcDataSource, atLeast(1)).getName();
    verify(instance).getDataSource();
    verify(instance).getName();
  }

  /**
   * Test {@link JDBCContentCLOB#getContents(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Given {@link JDBCRemoteInstance} {@link JDBCRemoteInstance#getName()} return {@code
   *       null}.
   *   <li>Then calls {@link JDBCDataSource#getName()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCContentCLOB#getContents(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.data.DBDContentStorage JDBCContentCLOB.getContents(DBRProgressMonitor)"
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
    when(clob.length()).thenThrow(new SQLException());

    // Act and Assert
    assertThrows(
        DBCException.class, () -> jDBCContentCLOB.getContents(new LoggingProgressMonitor()));
    verify(clob).length();
    verify(dBCExecutionContext).getContextAttribute("lob-reading-error-message-is-shown");
    verify(dBCExecutionContext).getDataSource();
    verify(dBCExecutionContext).isConnected();
    verify(jdbcDataSource, atLeast(1)).getName();
    verify(instance).getDataSource();
    verify(instance, atLeast(1)).getName();
  }

  /**
   * Test {@link JDBCContentCLOB#getContents(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCContentCLOB#getContents(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.data.DBDContentStorage JDBCContentCLOB.getContents(DBRProgressMonitor)"
  })
  public void testGetContents_thenReturnNull() throws DBCException {
    // Arrange
    JDBCContentCLOB jdbcContentCLOB = new JDBCContentCLOB(null, null);

    // Act and Assert
    assertNull(jdbcContentCLOB.getContents(new LoggingProgressMonitor()));
  }

  /**
   * Test {@link JDBCContentCLOB#getContents(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Then throw {@link RuntimeException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCContentCLOB#getContents(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.data.DBDContentStorage JDBCContentCLOB.getContents(DBRProgressMonitor)"
  })
  public void testGetContents_thenThrowRuntimeException() throws SQLException, DBCException {
    // Arrange
    JDBCDataSource jdbcDataSource = mock(JDBCDataSource.class);
    when(jdbcDataSource.getName()).thenReturn("Name");

    JDBCRemoteInstance instance = mock(JDBCRemoteInstance.class);
    when(instance.getName()).thenThrow(new RuntimeException());
    when(instance.getDataSource()).thenReturn(jdbcDataSource);
    JDBCExecutionContext jdbcExecutionContext = new JDBCExecutionContext(instance, true);
    when(dBCExecutionContext.isConnected()).thenReturn(true);
    when(dBCExecutionContext.getContextAttribute(Mockito.<String>any()))
        .thenReturn(jdbcExecutionContext);
    when(dBCExecutionContext.getDataSource()).thenReturn(mock(DBPDataSource.class));
    when(clob.length()).thenThrow(new SQLException());

    // Act and Assert
    assertThrows(
        RuntimeException.class, () -> jDBCContentCLOB.getContents(new LoggingProgressMonitor()));
    verify(clob).length();
    verify(dBCExecutionContext).getContextAttribute("lob-reading-error-message-is-shown");
    verify(dBCExecutionContext).getDataSource();
    verify(dBCExecutionContext).isConnected();
    verify(jdbcDataSource).getName();
    verify(instance).getDataSource();
    verify(instance).getName();
  }

  /**
   * Test {@link JDBCContentCLOB#release()}.
   *
   * <p>Method under test: {@link JDBCContentCLOB#release()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCContentCLOB.release()"})
  public void testRelease() throws SQLException, DBCException {
    // Arrange
    SerialClob clob = new SerialClob("AZAZ".toCharArray());
    JDBCContentCLOB jdbcContentCLOB = new JDBCContentCLOB(null, clob);

    // Act
    jdbcContentCLOB.release();

    // Assert
    assertNull(jdbcContentCLOB.getRawValue());
    assertEquals(0L, jdbcContentCLOB.getLOBLength());
    assertEquals(0L, jdbcContentCLOB.getContentLength());
    assertTrue(jdbcContentCLOB.isNull());
  }

  /**
   * Test {@link JDBCContentCLOB#release()}.
   *
   * <p>Method under test: {@link JDBCContentCLOB#release()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCContentCLOB.release()"})
  public void testRelease2() throws SQLException, DBCException {
    // Arrange
    SerialClob clob = mock(SerialClob.class);
    doThrow(new SQLException()).when(clob).free();
    JDBCContentCLOB jdbcContentCLOB = new JDBCContentCLOB(null, clob);

    // Act
    jdbcContentCLOB.release();

    // Assert
    verify(clob).free();
    assertNull(jdbcContentCLOB.getRawValue());
    assertEquals(0L, jdbcContentCLOB.getLOBLength());
    assertEquals(0L, jdbcContentCLOB.getContentLength());
    assertTrue(jdbcContentCLOB.isNull());
  }

  /**
   * Test {@link JDBCContentCLOB#release()}.
   *
   * <p>Method under test: {@link JDBCContentCLOB#release()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCContentCLOB.release()"})
  public void testRelease3() throws DBCException {
    // Arrange
    JDBCContentCLOB jdbcContentCLOB = new JDBCContentCLOB(null, null);

    // Act
    jdbcContentCLOB.release();

    // Assert that nothing has changed
    assertEquals(0L, jdbcContentCLOB.getLOBLength());
    assertEquals(0L, jdbcContentCLOB.getContentLength());
    assertTrue(jdbcContentCLOB.isNull());
  }

  /**
   * Test {@link JDBCContentCLOB#bindParameter(JDBCSession, JDBCPreparedStatement, DBSTypedObject,
   * int)}.
   *
   * <ul>
   *   <li>When {@link JDBCPreparedStatement} {@link JDBCPreparedStatement#setClob(int, Clob)} does
   *       nothing.
   *   <li>Then calls {@link JDBCPreparedStatement#setClob(int, Clob)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCContentCLOB#bindParameter(JDBCSession, JDBCPreparedStatement,
   * DBSTypedObject, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCContentCLOB.bindParameter(JDBCSession, JDBCPreparedStatement, DBSTypedObject, int)"
  })
  public void testBindParameter_whenJDBCPreparedStatementSetClobDoesNothing_thenCallsSetClob()
      throws SQLException, DBCException {
    // Arrange
    JDBCSession session = mock(JDBCSession.class);

    JDBCPreparedStatement preparedStatement = mock(JDBCPreparedStatement.class);
    doNothing().when(preparedStatement).setClob(anyInt(), Mockito.<Clob>any());

    // Act
    jDBCContentCLOB.bindParameter(session, preparedStatement, null, 1);

    // Assert
    verify(preparedStatement).setClob(eq(1), isA(Clob.class));
  }

  /**
   * Test {@link JDBCContentCLOB#isNull()}.
   *
   * <ul>
   *   <li>Given {@link SerialClob#SerialClob(char[])} with ch is {@code AZAZ} toCharArray.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCContentCLOB#isNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCContentCLOB.isNull()"})
  public void testIsNull_givenSerialClobWithChIsAzazToCharArray_thenReturnFalse()
      throws SQLException {
    // Arrange
    SerialClob clob = new SerialClob("AZAZ".toCharArray());
    JDBCContentCLOB jdbcContentCLOB = new JDBCContentCLOB(null, clob);

    // Act and Assert
    assertFalse(jdbcContentCLOB.isNull());
  }

  /**
   * Test {@link JDBCContentCLOB#isNull()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCContentCLOB#isNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCContentCLOB.isNull()"})
  public void testIsNull_thenReturnTrue() {
    // Arrange
    JDBCContentCLOB jdbcContentCLOB = new JDBCContentCLOB(null, null);

    // Act and Assert
    assertTrue(jdbcContentCLOB.isNull());
  }

  /**
   * Test {@link JDBCContentCLOB#createNewContent()}.
   *
   * <p>Method under test: {@link JDBCContentCLOB#createNewContent()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JDBCContentLOB JDBCContentCLOB.createNewContent()"})
  public void testCreateNewContent() throws SQLException, DBCException {
    // Arrange
    SerialClob clob = new SerialClob("AZAZ".toCharArray());
    JDBCContentCLOB jdbcContentCLOB = new JDBCContentCLOB(null, clob);

    // Act
    JDBCContentLOB actualCreateNewContentResult = jdbcContentCLOB.createNewContent();

    // Assert
    assertTrue(actualCreateNewContentResult instanceof JDBCContentCLOB);
    assertEquals("text/plain", actualCreateNewContentResult.getContentType());
    assertNull(actualCreateNewContentResult.getRawValue());
    assertNull(((JDBCContentCLOB) actualCreateNewContentResult).storage);
    assertEquals(0L, actualCreateNewContentResult.getContentLength());
    assertEquals(0L, actualCreateNewContentResult.getLOBLength());
    assertFalse(actualCreateNewContentResult.isModified());
    assertTrue(actualCreateNewContentResult.isNull());
  }

  /**
   * Test {@link JDBCContentCLOB#getDisplayString(DBDDisplayFormat)}.
   *
   * <ul>
   *   <li>Given {@link SerialClob#SerialClob(char[])} with ch is {@code AZAZ} toCharArray.
   *   <li>Then return {@code [CLOB]}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCContentCLOB#getDisplayString(DBDDisplayFormat)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCContentCLOB.getDisplayString(DBDDisplayFormat)"})
  public void testGetDisplayString_givenSerialClobWithChIsAzazToCharArray_thenReturnClob()
      throws SQLException {
    // Arrange
    SerialClob clob = new SerialClob("AZAZ".toCharArray());
    JDBCContentCLOB jdbcContentCLOB = new JDBCContentCLOB(null, clob);

    // Act and Assert
    assertEquals("[CLOB]", jdbcContentCLOB.getDisplayString(DBDDisplayFormat.UI));
  }

  /**
   * Test {@link JDBCContentCLOB#getDisplayString(DBDDisplayFormat)}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCContentCLOB#getDisplayString(DBDDisplayFormat)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCContentCLOB.getDisplayString(DBDDisplayFormat)"})
  public void testGetDisplayString_thenReturnNull() {
    // Arrange
    JDBCContentCLOB jdbcContentCLOB = new JDBCContentCLOB(null, null);

    // Act and Assert
    assertNull(jdbcContentCLOB.getDisplayString(DBDDisplayFormat.UI));
  }
}
