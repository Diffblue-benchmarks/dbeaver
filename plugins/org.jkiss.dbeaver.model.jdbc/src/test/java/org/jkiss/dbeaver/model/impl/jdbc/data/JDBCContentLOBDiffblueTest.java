package org.jkiss.dbeaver.model.impl.jdbc.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import javax.sql.rowset.serial.SerialBlob;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.data.DBDContentStorage;
import org.jkiss.dbeaver.model.data.DBDValueCloneable;
import org.jkiss.dbeaver.model.exec.DBCException;
import org.jkiss.dbeaver.model.exec.DBCExecutionContext;
import org.jkiss.dbeaver.model.impl.jdbc.JDBCDataSource;
import org.jkiss.dbeaver.model.impl.jdbc.JDBCExecutionContext;
import org.jkiss.dbeaver.model.impl.jdbc.JDBCRemoteInstance;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class JDBCContentLOBDiffblueTest {
  @Mock private DBCExecutionContext dBCExecutionContext;

  @Mock private DBDContentStorage dBDContentStorage;

  @InjectMocks private JDBCContentBLOB jDBCContentBLOB;

  /**
   * Test {@link JDBCContentLOB#getContentLength()}.
   *
   * <p>Method under test: {@link JDBCContentLOB#getContentLength()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long JDBCContentLOB.getContentLength()"})
  public void testGetContentLength()
      throws UnsupportedEncodingException, SQLException, DBCException {
    // Arrange
    JDBCContentBLOB jdbcContentBLOB =
        new JDBCContentBLOB(null, new SerialBlob("AXAXAXAX".getBytes("UTF-8")));

    // Act and Assert
    assertEquals(8L, jdbcContentBLOB.getContentLength());
  }

  /**
   * Test {@link JDBCContentLOB#updateContents(DBRProgressMonitor, DBDContentStorage)}.
   *
   * <p>Method under test: {@link JDBCContentLOB#updateContents(DBRProgressMonitor,
   * DBDContentStorage)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean JDBCContentLOB.updateContents(DBRProgressMonitor, DBDContentStorage)"
  })
  public void testUpdateContents() {
    // Arrange and Act
    boolean actualUpdateContentsResult =
        jDBCContentBLOB.updateContents(new LoggingProgressMonitor(), dBDContentStorage);

    // Assert
    assertTrue(jDBCContentBLOB.isModified());
    assertTrue(actualUpdateContentsResult);
  }

  /**
   * Test {@link JDBCContentLOB#release()}.
   *
   * <p>Method under test: {@link JDBCContentLOB#release()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCContentLOB.release()"})
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
   * Test {@link JDBCContentLOB#cloneValue(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Then return {@link JDBCContentBLOB}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCContentLOB#cloneValue(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBDValueCloneable JDBCContentLOB.cloneValue(DBRProgressMonitor)"})
  public void testCloneValue_thenReturnJDBCContentBLOB() throws DBCException {
    // Arrange
    JDBCContentBLOB jdbcContentBLOB = new JDBCContentBLOB(null, null);

    // Act
    DBDValueCloneable actualCloneValueResult =
        jdbcContentBLOB.cloneValue(new LoggingProgressMonitor());

    // Assert
    assertTrue(actualCloneValueResult instanceof JDBCContentBLOB);
    assertEquals(
        "application/octet-stream", ((JDBCContentBLOB) actualCloneValueResult).getContentType());
    assertNull(actualCloneValueResult.getRawValue());
    assertNull(((JDBCContentBLOB) actualCloneValueResult).storage);
    assertEquals(0L, ((JDBCContentBLOB) actualCloneValueResult).getLOBLength());
    assertEquals(0L, ((JDBCContentBLOB) actualCloneValueResult).getContentLength());
    assertFalse(actualCloneValueResult.isModified());
    assertTrue(actualCloneValueResult.isNull());
  }

  /**
   * Test {@link JDBCContentLOB#handleContentReadingException(DBCException)}.
   *
   * <p>Method under test: {@link JDBCContentLOB#handleContentReadingException(DBCException)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCContentLOB.handleContentReadingException(DBCException)"})
  public void testHandleContentReadingException()
      throws UnsupportedEncodingException, SQLException, DBCException {
    // Arrange
    JDBCDataSource jdbcDataSource = mock(JDBCDataSource.class);
    when(jdbcDataSource.getName()).thenReturn("Name");

    JDBCRemoteInstance instance = mock(JDBCRemoteInstance.class);
    when(instance.getDataSource()).thenReturn(jdbcDataSource);
    JDBCExecutionContext dataSource = new JDBCExecutionContext(instance, true);
    JDBCContentBLOB jdbcContentBLOB =
        new JDBCContentBLOB(dataSource, new SerialBlob("AXAXAXAX".getBytes("UTF-8")));

    // Act and Assert
    assertThrows(
        DBCException.class,
        () -> jdbcContentBLOB.handleContentReadingException(new DBCException(null)));
    verify(jdbcDataSource).getName();
    verify(instance).getDataSource();
  }

  /**
   * Test {@link JDBCContentLOB#handleContentReadingException(DBCException)}.
   *
   * <p>Method under test: {@link JDBCContentLOB#handleContentReadingException(DBCException)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCContentLOB.handleContentReadingException(DBCException)"})
  public void testHandleContentReadingException2()
      throws UnsupportedEncodingException, SQLException, DBCException {
    // Arrange
    JDBCDataSource jdbcDataSource = mock(JDBCDataSource.class);
    when(jdbcDataSource.getName()).thenReturn("Name");

    JDBCRemoteInstance instance = mock(JDBCRemoteInstance.class);
    when(instance.getDataSource()).thenReturn(jdbcDataSource);
    JDBCExecutionContext dataSource = new JDBCExecutionContext(instance, true);
    JDBCContentBLOB jdbcContentBLOB =
        new JDBCContentBLOB(dataSource, new SerialBlob("AXAXAXAX".getBytes("UTF-8")));

    // Act and Assert
    assertThrows(
        DBCException.class,
        () -> jdbcContentBLOB.handleContentReadingException(new DBCException("An error occurred")));
    verify(jdbcDataSource).getName();
    verify(instance).getDataSource();
  }

  /**
   * Test {@link JDBCContentLOB#handleContentReadingException(DBCException)}.
   *
   * <p>Method under test: {@link JDBCContentLOB#handleContentReadingException(DBCException)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCContentLOB.handleContentReadingException(DBCException)"})
  public void testHandleContentReadingException3() throws DBCException {
    // Arrange
    when(dBCExecutionContext.isConnected()).thenReturn(true);
    when(dBCExecutionContext.getContextAttribute(Mockito.<String>any()))
        .thenReturn("Context Attribute");

    // Act and Assert
    assertThrows(
        DBCException.class,
        () -> jDBCContentBLOB.handleContentReadingException(new DBCException("An error occurred")));
    verify(dBCExecutionContext).getContextAttribute("lob-reading-error-message-is-shown");
    verify(dBCExecutionContext).isConnected();
  }

  /**
   * Test {@link JDBCContentLOB#handleContentReadingException(DBCException)}.
   *
   * <p>Method under test: {@link JDBCContentLOB#handleContentReadingException(DBCException)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCContentLOB.handleContentReadingException(DBCException)"})
  public void testHandleContentReadingException4() throws DBCException {
    // Arrange
    when(dBCExecutionContext.getDataSource()).thenReturn(mock(DBPDataSource.class));
    when(dBCExecutionContext.isConnected()).thenReturn(true);
    when(dBCExecutionContext.getContextAttribute(Mockito.<String>any())).thenReturn(true);

    // Act and Assert
    assertThrows(
        DBCException.class,
        () ->
            jDBCContentBLOB.handleContentReadingException(
                new DBCException(new Throwable(), dBCExecutionContext)));
    verify(dBCExecutionContext).getContextAttribute("lob-reading-error-message-is-shown");
    verify(dBCExecutionContext).getDataSource();
    verify(dBCExecutionContext).isConnected();
  }

  /**
   * Test {@link JDBCContentLOB#handleContentReadingException(DBCException)}.
   *
   * <p>Method under test: {@link JDBCContentLOB#handleContentReadingException(DBCException)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCContentLOB.handleContentReadingException(DBCException)"})
  public void testHandleContentReadingException5() throws DBCException {
    // Arrange
    JDBCDataSource jdbcDataSource = mock(JDBCDataSource.class);
    when(jdbcDataSource.getName()).thenReturn("Name");

    JDBCRemoteInstance instance = mock(JDBCRemoteInstance.class);
    when(instance.getName()).thenReturn("lob-reading-error-message-is-shown");
    when(instance.getDataSource()).thenReturn(jdbcDataSource);
    JDBCExecutionContext jdbcExecutionContext = new JDBCExecutionContext(instance, true);
    when(dBCExecutionContext.getDataSource()).thenReturn(mock(DBPDataSource.class));
    when(dBCExecutionContext.isConnected()).thenReturn(true);
    when(dBCExecutionContext.getContextAttribute(Mockito.<String>any()))
        .thenReturn(jdbcExecutionContext);

    // Act and Assert
    assertThrows(
        DBCException.class,
        () ->
            jDBCContentBLOB.handleContentReadingException(
                new DBCException(new Throwable(), dBCExecutionContext)));
    verify(dBCExecutionContext).getContextAttribute("lob-reading-error-message-is-shown");
    verify(dBCExecutionContext).getDataSource();
    verify(dBCExecutionContext).isConnected();
    verify(jdbcDataSource, atLeast(1)).getName();
    verify(instance).getDataSource();
    verify(instance, atLeast(1)).getName();
  }

  /**
   * Test {@link JDBCContentLOB#handleContentReadingException(DBCException)}.
   *
   * <ul>
   *   <li>Given {@link JDBCDataSource} {@link JDBCDataSource#getName()} return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCContentLOB#handleContentReadingException(DBCException)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCContentLOB.handleContentReadingException(DBCException)"})
  public void testHandleContentReadingException_givenJDBCDataSourceGetNameReturnNull()
      throws DBCException {
    // Arrange
    JDBCDataSource jdbcDataSource = mock(JDBCDataSource.class);
    when(jdbcDataSource.getName()).thenReturn(null);

    JDBCRemoteInstance instance = mock(JDBCRemoteInstance.class);
    when(instance.getName()).thenReturn("Name");
    when(instance.getDataSource()).thenReturn(jdbcDataSource);
    JDBCExecutionContext jdbcExecutionContext = new JDBCExecutionContext(instance, true);
    when(dBCExecutionContext.getDataSource()).thenReturn(mock(DBPDataSource.class));
    when(dBCExecutionContext.isConnected()).thenReturn(true);
    when(dBCExecutionContext.getContextAttribute(Mockito.<String>any()))
        .thenReturn(jdbcExecutionContext);

    // Act and Assert
    assertThrows(
        DBCException.class,
        () ->
            jDBCContentBLOB.handleContentReadingException(
                new DBCException(new Throwable(), dBCExecutionContext)));
    verify(dBCExecutionContext).getContextAttribute("lob-reading-error-message-is-shown");
    verify(dBCExecutionContext).getDataSource();
    verify(dBCExecutionContext).isConnected();
    verify(jdbcDataSource, atLeast(1)).getName();
    verify(instance).getDataSource();
    verify(instance, atLeast(1)).getName();
  }

  /**
   * Test {@link JDBCContentLOB#handleContentReadingException(DBCException)}.
   *
   * <ul>
   *   <li>Given {@link JDBCRemoteInstance} {@link JDBCRemoteInstance#getName()} return {@code
   *       Name}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCContentLOB#handleContentReadingException(DBCException)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCContentLOB.handleContentReadingException(DBCException)"})
  public void testHandleContentReadingException_givenJDBCRemoteInstanceGetNameReturnName()
      throws DBCException {
    // Arrange
    JDBCDataSource jdbcDataSource = mock(JDBCDataSource.class);
    when(jdbcDataSource.getName()).thenReturn("Name");

    JDBCRemoteInstance instance = mock(JDBCRemoteInstance.class);
    when(instance.getName()).thenReturn("Name");
    when(instance.getDataSource()).thenReturn(jdbcDataSource);
    JDBCExecutionContext jdbcExecutionContext = new JDBCExecutionContext(instance, true);
    when(dBCExecutionContext.getDataSource()).thenReturn(mock(DBPDataSource.class));
    when(dBCExecutionContext.isConnected()).thenReturn(true);
    when(dBCExecutionContext.getContextAttribute(Mockito.<String>any()))
        .thenReturn(jdbcExecutionContext);

    // Act and Assert
    assertThrows(
        DBCException.class,
        () ->
            jDBCContentBLOB.handleContentReadingException(
                new DBCException(new Throwable(), dBCExecutionContext)));
    verify(dBCExecutionContext).getContextAttribute("lob-reading-error-message-is-shown");
    verify(dBCExecutionContext).getDataSource();
    verify(dBCExecutionContext).isConnected();
    verify(jdbcDataSource, atLeast(1)).getName();
    verify(instance).getDataSource();
    verify(instance).getName();
  }

  /**
   * Test {@link JDBCContentLOB#handleContentReadingException(DBCException)}.
   *
   * <ul>
   *   <li>Given {@link JDBCRemoteInstance} {@link JDBCRemoteInstance#getName()} return {@code
   *       null}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCContentLOB#handleContentReadingException(DBCException)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCContentLOB.handleContentReadingException(DBCException)"})
  public void testHandleContentReadingException_givenJDBCRemoteInstanceGetNameReturnNull()
      throws DBCException {
    // Arrange
    JDBCDataSource jdbcDataSource = mock(JDBCDataSource.class);
    when(jdbcDataSource.getName()).thenReturn("Name");

    JDBCRemoteInstance instance = mock(JDBCRemoteInstance.class);
    when(instance.getName()).thenReturn(null);
    when(instance.getDataSource()).thenReturn(jdbcDataSource);
    JDBCExecutionContext jdbcExecutionContext = new JDBCExecutionContext(instance, true);
    when(dBCExecutionContext.getDataSource()).thenReturn(mock(DBPDataSource.class));
    when(dBCExecutionContext.isConnected()).thenReturn(true);
    when(dBCExecutionContext.getContextAttribute(Mockito.<String>any()))
        .thenReturn(jdbcExecutionContext);

    // Act and Assert
    assertThrows(
        DBCException.class,
        () ->
            jDBCContentBLOB.handleContentReadingException(
                new DBCException(new Throwable(), dBCExecutionContext)));
    verify(dBCExecutionContext).getContextAttribute("lob-reading-error-message-is-shown");
    verify(dBCExecutionContext).getDataSource();
    verify(dBCExecutionContext).isConnected();
    verify(jdbcDataSource, atLeast(1)).getName();
    verify(instance).getDataSource();
    verify(instance, atLeast(1)).getName();
  }
}
