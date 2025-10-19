package org.jkiss.dbeaver.model.qm.meta;

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
import java.nio.file.Paths;
import java.util.UUID;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.model.app.DBPProject;
import org.jkiss.dbeaver.model.app.DBPWorkspace;
import org.jkiss.dbeaver.model.auth.SMSessionContext;
import org.jkiss.dbeaver.model.connection.DBPConnectionConfiguration;
import org.jkiss.dbeaver.model.connection.DBPDriver;
import org.jkiss.dbeaver.model.exec.DBCExecutionContext;
import org.jkiss.dbeaver.model.exec.DBCExecutionPurpose;
import org.jkiss.dbeaver.model.exec.DBCResultSet;
import org.jkiss.dbeaver.model.exec.DBCSavepoint;
import org.jkiss.dbeaver.model.exec.DBCSession;
import org.jkiss.dbeaver.model.exec.DBCStatement;
import org.jkiss.dbeaver.model.impl.app.LocalWorkspaceSession;
import org.jkiss.dbeaver.model.impl.auth.SessionContextImpl;
import org.jkiss.dbeaver.model.impl.local.LocalResultSet;
import org.jkiss.dbeaver.model.impl.local.LocalStatement;
import org.jkiss.dbeaver.model.struct.DBSInstance;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class QMMConnectionInfoDiffblueTest {
  /**
   * Test {@link QMMConnectionInfo#QMMConnectionInfo(DBCExecutionContext, boolean)}.
   *
   * <p>Method under test: {@link QMMConnectionInfo#QMMConnectionInfo(DBCExecutionContext, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void QMMConnectionInfo.<init>(DBCExecutionContext, boolean)"})
  public void testNewQMMConnectionInfo() {
    // Arrange
    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.getFullId()).thenReturn("42");

    SessionContextImpl sessionContextImpl = new SessionContextImpl(mock(SMSessionContext.class));
    sessionContextImpl.addSession(new LocalWorkspaceSession(mock(DBPWorkspace.class)));

    DBPProject dbpProject = mock(DBPProject.class);
    when(dbpProject.getSessionContext()).thenReturn(sessionContextImpl);
    when(dbpProject.getId()).thenReturn("42");
    when(dbpProject.getName()).thenReturn("Name");
    when(dbpProject.getAbsolutePath())
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));
    when(dbpProject.getProjectID()).thenReturn(UUID.randomUUID());

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getName()).thenReturn("Name");
    when(dbpDataSourceContainer.getConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());
    when(dbpDataSourceContainer.getProject()).thenReturn(dbpProject);
    when(dbpDataSourceContainer.getId()).thenReturn("42");
    when(dbpDataSourceContainer.getDriver()).thenReturn(dbpDriver);

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBSInstance dbsInstance = mock(DBSInstance.class);
    when(dbsInstance.getName()).thenReturn("Name");

    DBCExecutionContext context = mock(DBCExecutionContext.class);
    when(context.getContextName()).thenReturn("Context Name");
    when(context.getOwnerInstance()).thenReturn(dbsInstance);
    when(context.getDataSource()).thenReturn(dbpDataSource);

    // Act
    QMMConnectionInfo actualQmmConnectionInfo = new QMMConnectionInfo(context, true);

    // Assert
    verify(dbpDataSource, atLeast(1)).getContainer();
    verify(dbpDataSourceContainer).getConnectionConfiguration();
    verify(dbpDataSourceContainer).getDriver();
    verify(dbpDataSourceContainer).getId();
    verify(dbpDataSourceContainer).getProject();
    verify(dbpDataSourceContainer).getName();
    verify(dbsInstance).getName();
    verify(dbpProject).getAbsolutePath();
    verify(dbpProject).getId();
    verify(dbpProject).getName();
    verify(dbpProject).getProjectID();
    verify(dbpProject).getSessionContext();
    verify(dbpDriver).getFullId();
    verify(context).getContextName();
    verify(context, atLeast(1)).getDataSource();
    verify(context).getOwnerInstance();
    QMMTransactionInfo transaction = actualQmmConnectionInfo.getTransaction();
    assertEquals("Name - Context Name", transaction.getText());
    assertNull(transaction.getPrevious());
    assertEquals(-1L, transaction.getDuration());
    assertEquals(0L, transaction.getCloseTime());
    assertEquals(QMMetaObjectType.TRANSACTION_INFO, transaction.getObjectType());
    assertFalse(transaction.isClosed());
    assertFalse(transaction.isUpdated());
    assertFalse(transaction.isCommitted());
    assertTrue(actualQmmConnectionInfo.isTransactional());
    assertSame(actualQmmConnectionInfo, transaction.getConnection());
  }

  /**
   * Test {@link QMMConnectionInfo#QMMConnectionInfo(DBCExecutionContext, boolean)}.
   *
   * <p>Method under test: {@link QMMConnectionInfo#QMMConnectionInfo(DBCExecutionContext, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void QMMConnectionInfo.<init>(DBCExecutionContext, boolean)"})
  public void testNewQMMConnectionInfo2() {
    // Arrange
    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.getFullId()).thenReturn("42");

    SessionContextImpl sessionContextImpl = new SessionContextImpl(mock(SMSessionContext.class));
    sessionContextImpl.addSession(new LocalWorkspaceSession(mock(DBPWorkspace.class)));
    sessionContextImpl.addSession(new LocalWorkspaceSession(mock(DBPWorkspace.class)));

    DBPProject dbpProject = mock(DBPProject.class);
    when(dbpProject.getSessionContext()).thenReturn(sessionContextImpl);
    when(dbpProject.getId()).thenReturn("42");
    when(dbpProject.getName()).thenReturn("Name");
    when(dbpProject.getAbsolutePath())
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));
    when(dbpProject.getProjectID()).thenReturn(UUID.randomUUID());

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getName()).thenReturn("Name");
    when(dbpDataSourceContainer.getConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());
    when(dbpDataSourceContainer.getProject()).thenReturn(dbpProject);
    when(dbpDataSourceContainer.getId()).thenReturn("42");
    when(dbpDataSourceContainer.getDriver()).thenReturn(dbpDriver);

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBSInstance dbsInstance = mock(DBSInstance.class);
    when(dbsInstance.getName()).thenReturn("Name");

    DBCExecutionContext context = mock(DBCExecutionContext.class);
    when(context.getContextName()).thenReturn("Context Name");
    when(context.getOwnerInstance()).thenReturn(dbsInstance);
    when(context.getDataSource()).thenReturn(dbpDataSource);

    // Act
    QMMConnectionInfo actualQmmConnectionInfo = new QMMConnectionInfo(context, true);

    // Assert
    verify(dbpDataSource, atLeast(1)).getContainer();
    verify(dbpDataSourceContainer).getConnectionConfiguration();
    verify(dbpDataSourceContainer).getDriver();
    verify(dbpDataSourceContainer).getId();
    verify(dbpDataSourceContainer).getProject();
    verify(dbpDataSourceContainer).getName();
    verify(dbsInstance).getName();
    verify(dbpProject).getAbsolutePath();
    verify(dbpProject).getId();
    verify(dbpProject).getName();
    verify(dbpProject).getProjectID();
    verify(dbpProject).getSessionContext();
    verify(dbpDriver).getFullId();
    verify(context).getContextName();
    verify(context, atLeast(1)).getDataSource();
    verify(context).getOwnerInstance();
    QMMTransactionInfo transaction = actualQmmConnectionInfo.getTransaction();
    assertEquals("Name - Context Name", transaction.getText());
    assertNull(transaction.getPrevious());
    assertEquals(-1L, transaction.getDuration());
    assertEquals(0L, transaction.getCloseTime());
    assertEquals(QMMetaObjectType.TRANSACTION_INFO, transaction.getObjectType());
    assertFalse(transaction.isClosed());
    assertFalse(transaction.isUpdated());
    assertFalse(transaction.isCommitted());
    assertTrue(actualQmmConnectionInfo.isTransactional());
    assertSame(actualQmmConnectionInfo, transaction.getConnection());
  }

  /**
   * Test {@link QMMConnectionInfo#QMMConnectionInfo(long, long, QMMProjectInfo, String, String,
   * String, DBPConnectionConfiguration, String, String, boolean)}.
   *
   * <ul>
   *   <li>Then return ContainerId is {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link QMMConnectionInfo#QMMConnectionInfo(long, long, QMMProjectInfo,
   * String, String, String, DBPConnectionConfiguration, String, String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void QMMConnectionInfo.<init>(long, long, QMMProjectInfo, String, String, String, DBPConnectionConfiguration, String, String, boolean)"
  })
  public void testNewQMMConnectionInfo_thenReturnContainerIdIs42() {
    // Arrange
    QMMProjectInfo projectInfo =
        QMMProjectInfo.builder()
            .setId("42")
            .setAnonymous(true)
            .setName("Name")
            .setPath("Path")
            .build();

    // Act
    QMMConnectionInfo actualQmmConnectionInfo =
        new QMMConnectionInfo(
            1L,
            1L,
            projectInfo,
            "42",
            "Container Name",
            "42",
            new DBPConnectionConfiguration(),
            "Instance ID",
            "Context Name",
            true);

    // Assert
    assertEquals("42", actualQmmConnectionInfo.getContainerId());
    assertEquals("42", actualQmmConnectionInfo.getDriverId());
    assertEquals("42", actualQmmConnectionInfo.getProjectId());
    assertEquals("Container Name - Context Name", actualQmmConnectionInfo.getText());
    assertEquals("Container Name", actualQmmConnectionInfo.getContainerName());
    assertEquals("Context Name", actualQmmConnectionInfo.getContextName());
    assertEquals("Instance ID", actualQmmConnectionInfo.getInstanceId());
    assertNull(actualQmmConnectionInfo.getConnectionUrl());
    assertNull(actualQmmConnectionInfo.getConnectionUserName());
    assertNull(actualQmmConnectionInfo.getExecutionStack());
    assertNull(actualQmmConnectionInfo.getStatementStack());
    assertNull(actualQmmConnectionInfo.getTransaction());
    assertEquals(0L, actualQmmConnectionInfo.getDuration());
    assertEquals(1L, actualQmmConnectionInfo.getCloseTime());
    assertEquals(1L, actualQmmConnectionInfo.getOpenTime());
    assertEquals(QMMetaObjectType.CONNECTION_INFO, actualQmmConnectionInfo.getObjectType());
    assertFalse(actualQmmConnectionInfo.isUpdated());
    assertTrue(actualQmmConnectionInfo.isTransactional());
    assertTrue(actualQmmConnectionInfo.isClosed());
    assertSame(projectInfo, actualQmmConnectionInfo.getProjectInfo());
  }

  /**
   * Test {@link QMMConnectionInfo#QMMConnectionInfo(DBCExecutionContext, boolean)}.
   *
   * <ul>
   *   <li>Then return Transaction Text is {@code Name - Context Name}.
   * </ul>
   *
   * <p>Method under test: {@link QMMConnectionInfo#QMMConnectionInfo(DBCExecutionContext, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void QMMConnectionInfo.<init>(DBCExecutionContext, boolean)"})
  public void testNewQMMConnectionInfo_thenReturnTransactionTextIsNameContextName() {
    // Arrange
    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.getFullId()).thenReturn("42");

    DBPProject dbpProject = mock(DBPProject.class);
    when(dbpProject.getSessionContext())
        .thenReturn(new SessionContextImpl(mock(SMSessionContext.class)));
    when(dbpProject.getId()).thenReturn("42");
    when(dbpProject.getName()).thenReturn("Name");
    when(dbpProject.getAbsolutePath())
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));
    when(dbpProject.getProjectID()).thenReturn(UUID.randomUUID());

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getName()).thenReturn("Name");
    when(dbpDataSourceContainer.getConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());
    when(dbpDataSourceContainer.getProject()).thenReturn(dbpProject);
    when(dbpDataSourceContainer.getId()).thenReturn("42");
    when(dbpDataSourceContainer.getDriver()).thenReturn(dbpDriver);

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBSInstance dbsInstance = mock(DBSInstance.class);
    when(dbsInstance.getName()).thenReturn("Name");

    DBCExecutionContext context = mock(DBCExecutionContext.class);
    when(context.getContextName()).thenReturn("Context Name");
    when(context.getOwnerInstance()).thenReturn(dbsInstance);
    when(context.getDataSource()).thenReturn(dbpDataSource);

    // Act
    QMMConnectionInfo actualQmmConnectionInfo = new QMMConnectionInfo(context, true);

    // Assert
    verify(dbpDataSource, atLeast(1)).getContainer();
    verify(dbpDataSourceContainer).getConnectionConfiguration();
    verify(dbpDataSourceContainer).getDriver();
    verify(dbpDataSourceContainer).getId();
    verify(dbpDataSourceContainer).getProject();
    verify(dbpDataSourceContainer).getName();
    verify(dbsInstance).getName();
    verify(dbpProject).getAbsolutePath();
    verify(dbpProject).getId();
    verify(dbpProject).getName();
    verify(dbpProject).getProjectID();
    verify(dbpProject).getSessionContext();
    verify(dbpDriver).getFullId();
    verify(context).getContextName();
    verify(context, atLeast(1)).getDataSource();
    verify(context).getOwnerInstance();
    QMMTransactionInfo transaction = actualQmmConnectionInfo.getTransaction();
    assertEquals("Name - Context Name", transaction.getText());
    assertNull(transaction.getPrevious());
    assertEquals(-1L, transaction.getDuration());
    assertEquals(0L, transaction.getCloseTime());
    assertEquals(QMMetaObjectType.TRANSACTION_INFO, transaction.getObjectType());
    assertFalse(transaction.isClosed());
    assertFalse(transaction.isUpdated());
    assertFalse(transaction.isCommitted());
    assertTrue(actualQmmConnectionInfo.isTransactional());
    assertSame(actualQmmConnectionInfo, transaction.getConnection());
  }

  /**
   * Test {@link QMMConnectionInfo#QMMConnectionInfo(DBCExecutionContext, boolean)}.
   *
   * <ul>
   *   <li>When {@code false}.
   *   <li>Then return ContainerId is {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link QMMConnectionInfo#QMMConnectionInfo(DBCExecutionContext, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void QMMConnectionInfo.<init>(DBCExecutionContext, boolean)"})
  public void testNewQMMConnectionInfo_whenFalse_thenReturnContainerIdIs42() {
    // Arrange
    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.getFullId()).thenReturn("42");

    DBPProject dbpProject = mock(DBPProject.class);
    when(dbpProject.getSessionContext())
        .thenReturn(new SessionContextImpl(mock(SMSessionContext.class)));
    when(dbpProject.getId()).thenReturn("42");
    when(dbpProject.getName()).thenReturn("Name");
    when(dbpProject.getAbsolutePath())
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));
    when(dbpProject.getProjectID()).thenReturn(UUID.randomUUID());

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getName()).thenReturn("Name");
    when(dbpDataSourceContainer.getConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());
    when(dbpDataSourceContainer.getProject()).thenReturn(dbpProject);
    when(dbpDataSourceContainer.getId()).thenReturn("42");
    when(dbpDataSourceContainer.getDriver()).thenReturn(dbpDriver);

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBSInstance dbsInstance = mock(DBSInstance.class);
    when(dbsInstance.getName()).thenReturn("Name");

    DBCExecutionContext context = mock(DBCExecutionContext.class);
    when(context.getContextName()).thenReturn("Context Name");
    when(context.getOwnerInstance()).thenReturn(dbsInstance);
    when(context.getDataSource()).thenReturn(dbpDataSource);

    // Act
    QMMConnectionInfo actualQmmConnectionInfo = new QMMConnectionInfo(context, false);

    // Assert
    verify(dbpDataSource, atLeast(1)).getContainer();
    verify(dbpDataSourceContainer).getConnectionConfiguration();
    verify(dbpDataSourceContainer).getDriver();
    verify(dbpDataSourceContainer).getId();
    verify(dbpDataSourceContainer).getProject();
    verify(dbpDataSourceContainer).getName();
    verify(dbsInstance).getName();
    verify(dbpProject).getAbsolutePath();
    verify(dbpProject).getId();
    verify(dbpProject).getName();
    verify(dbpProject).getProjectID();
    verify(dbpProject).getSessionContext();
    verify(dbpDriver).getFullId();
    verify(context).getContextName();
    verify(context, atLeast(1)).getDataSource();
    verify(context).getOwnerInstance();
    assertEquals("42", actualQmmConnectionInfo.getContainerId());
    assertEquals("42", actualQmmConnectionInfo.getDriverId());
    assertEquals("42", actualQmmConnectionInfo.getProjectId());
    assertEquals("Context Name", actualQmmConnectionInfo.getContextName());
    assertEquals("Name - Context Name", actualQmmConnectionInfo.getText());
    assertEquals("Name", actualQmmConnectionInfo.getContainerName());
    assertEquals("Name", actualQmmConnectionInfo.getInstanceId());
    assertNull(actualQmmConnectionInfo.getConnectionUrl());
    assertNull(actualQmmConnectionInfo.getConnectionUserName());
    assertNull(actualQmmConnectionInfo.getExecutionStack());
    assertNull(actualQmmConnectionInfo.getStatementStack());
    assertNull(actualQmmConnectionInfo.getTransaction());
    assertEquals(-1L, actualQmmConnectionInfo.getDuration());
    assertEquals(0L, actualQmmConnectionInfo.getCloseTime());
    assertEquals(QMMetaObjectType.CONNECTION_INFO, actualQmmConnectionInfo.getObjectType());
    assertFalse(actualQmmConnectionInfo.isTransactional());
    assertFalse(actualQmmConnectionInfo.isClosed());
    assertFalse(actualQmmConnectionInfo.isUpdated());
  }

  /**
   * Test {@link QMMConnectionInfo#close()}.
   *
   * <p>Method under test: {@link QMMConnectionInfo#close()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void QMMConnectionInfo.close()"})
  public void testClose() {
    // Arrange
    QMMProjectInfo projectInfo =
        QMMProjectInfo.builder()
            .setId("42")
            .setAnonymous(true)
            .setName("Name")
            .setPath("Path")
            .build();
    QMMConnectionInfo qmmConnectionInfo =
        new QMMConnectionInfo(
            1L,
            1L,
            projectInfo,
            "42",
            "Container Name",
            "42",
            new DBPConnectionConfiguration(),
            "Instance ID",
            "Context Name",
            true);

    // Act
    qmmConnectionInfo.close();

    // Assert
    assertNull(qmmConnectionInfo.getStatementStack());
  }

  /**
   * Test {@link QMMConnectionInfo#close()}.
   *
   * <p>Method under test: {@link QMMConnectionInfo#close()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void QMMConnectionInfo.close()"})
  public void testClose2() {
    // Arrange
    DBCSession session = mock(DBCSession.class);
    when(session.getPurpose()).thenReturn(DBCExecutionPurpose.USER);
    LocalStatement statement = new LocalStatement(session, "Text");
    QMMProjectInfo projectInfo =
        QMMProjectInfo.builder()
            .setId("42")
            .setAnonymous(true)
            .setName("Name")
            .setPath("Path")
            .build();

    QMMConnectionInfo qmmConnectionInfo =
        new QMMConnectionInfo(
            1L,
            1L,
            projectInfo,
            "42",
            "Container Name",
            "42",
            new DBPConnectionConfiguration(),
            "Instance ID",
            "Context Name",
            true);
    qmmConnectionInfo.openStatement(statement);

    // Act
    qmmConnectionInfo.close();

    // Assert
    verify(session).getPurpose();
    assertNull(qmmConnectionInfo.getStatementStack());
    assertTrue(qmmConnectionInfo.isClosed());
  }

  /**
   * Test {@link QMMConnectionInfo#reopen(DBCExecutionContext)} with {@code DBCExecutionContext}.
   *
   * <p>Method under test: {@link QMMConnectionInfo#reopen(DBCExecutionContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void QMMConnectionInfo.reopen(DBCExecutionContext)"})
  public void testReopenWithDBCExecutionContext() {
    // Arrange
    QMMProjectInfo projectInfo =
        QMMProjectInfo.builder()
            .setId("42")
            .setAnonymous(true)
            .setName("Name")
            .setPath("Path")
            .build();
    QMMConnectionInfo qmmConnectionInfo =
        new QMMConnectionInfo(
            1L,
            1L,
            projectInfo,
            "42",
            "Container Name",
            "42",
            new DBPConnectionConfiguration(),
            "Instance ID",
            "Context Name",
            true);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getName()).thenReturn("Name");
    when(dbpDataSourceContainer.getConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBSInstance dbsInstance = mock(DBSInstance.class);
    when(dbsInstance.getName()).thenReturn("Name");

    DBCExecutionContext context = mock(DBCExecutionContext.class);
    when(context.getContextName()).thenReturn("Context Name");
    when(context.getOwnerInstance()).thenReturn(dbsInstance);
    when(context.isConnected()).thenReturn(false);
    when(context.getDataSource()).thenReturn(dbpDataSource);

    // Act
    qmmConnectionInfo.reopen(context);

    // Assert
    verify(dbpDataSource, atLeast(1)).getContainer();
    verify(dbpDataSourceContainer).getConnectionConfiguration();
    verify(dbpDataSourceContainer).getName();
    verify(dbsInstance).getName();
    verify(context).getContextName();
    verify(context, atLeast(1)).getDataSource();
    verify(context).getOwnerInstance();
    verify(context).isConnected();
    assertEquals("Name - Context Name", qmmConnectionInfo.getText());
    assertEquals("Name", qmmConnectionInfo.getContainerName());
    assertEquals("Name", qmmConnectionInfo.getInstanceId());
    assertEquals(-1L, qmmConnectionInfo.getDuration());
    assertEquals(0L, qmmConnectionInfo.getCloseTime());
    assertFalse(qmmConnectionInfo.isTransactional());
    assertFalse(qmmConnectionInfo.isClosed());
    assertTrue(qmmConnectionInfo.isUpdated());
  }

  /**
   * Test {@link QMMConnectionInfo#reopen(DBCExecutionContext)} with {@code DBCExecutionContext}.
   *
   * <ul>
   *   <li>Given {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link QMMConnectionInfo#reopen(DBCExecutionContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void QMMConnectionInfo.reopen(DBCExecutionContext)"})
  public void testReopenWithDBCExecutionContext_givenTrue() {
    // Arrange
    QMMProjectInfo projectInfo =
        QMMProjectInfo.builder()
            .setId("42")
            .setAnonymous(true)
            .setName("Name")
            .setPath("Path")
            .build();
    QMMConnectionInfo qmmConnectionInfo =
        new QMMConnectionInfo(
            1L,
            1L,
            projectInfo,
            "42",
            "Container Name",
            "42",
            new DBPConnectionConfiguration(),
            "Instance ID",
            "Context Name",
            true);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getName()).thenReturn("Name");
    when(dbpDataSourceContainer.getConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBSInstance dbsInstance = mock(DBSInstance.class);
    when(dbsInstance.getName()).thenReturn("Name");

    DBCExecutionContext context = mock(DBCExecutionContext.class);
    when(context.getContextName()).thenReturn("Context Name");
    when(context.getOwnerInstance()).thenReturn(dbsInstance);
    when(context.isConnected()).thenReturn(true);
    when(context.getDataSource()).thenReturn(dbpDataSource);

    // Act
    qmmConnectionInfo.reopen(context);

    // Assert
    verify(dbpDataSource, atLeast(1)).getContainer();
    verify(dbpDataSourceContainer).getConnectionConfiguration();
    verify(dbpDataSourceContainer).getName();
    verify(dbsInstance).getName();
    verify(context).getContextName();
    verify(context, atLeast(1)).getDataSource();
    verify(context).getOwnerInstance();
    verify(context).isConnected();
    assertEquals("Name - Context Name", qmmConnectionInfo.getText());
    assertEquals("Name", qmmConnectionInfo.getContainerName());
    assertEquals("Name", qmmConnectionInfo.getInstanceId());
    assertEquals(-1L, qmmConnectionInfo.getDuration());
    assertEquals(0L, qmmConnectionInfo.getCloseTime());
    assertFalse(qmmConnectionInfo.isTransactional());
    assertFalse(qmmConnectionInfo.isClosed());
    assertTrue(qmmConnectionInfo.isUpdated());
  }

  /**
   * Test {@link QMMConnectionInfo#getText()}.
   *
   * <ul>
   *   <li>Then return {@code Container Name - Context Name}.
   * </ul>
   *
   * <p>Method under test: {@link QMMConnectionInfo#getText()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String QMMConnectionInfo.getText()"})
  public void testGetText_thenReturnContainerNameContextName() {
    // Arrange
    QMMProjectInfo projectInfo =
        QMMProjectInfo.builder()
            .setId("42")
            .setAnonymous(true)
            .setName("Name")
            .setPath("Path")
            .build();
    QMMConnectionInfo qmmConnectionInfo =
        new QMMConnectionInfo(
            1L,
            1L,
            projectInfo,
            "42",
            "Container Name",
            "42",
            new DBPConnectionConfiguration(),
            "Instance ID",
            "Context Name",
            true);

    // Act and Assert
    assertEquals("Container Name - Context Name", qmmConnectionInfo.getText());
  }

  /**
   * Test {@link QMMConnectionInfo#getConnection()}.
   *
   * <p>Method under test: {@link QMMConnectionInfo#getConnection()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"QMMConnectionInfo QMMConnectionInfo.getConnection()"})
  public void testGetConnection() {
    // Arrange
    QMMProjectInfo projectInfo =
        QMMProjectInfo.builder()
            .setId("42")
            .setAnonymous(true)
            .setName("Name")
            .setPath("Path")
            .build();
    QMMConnectionInfo qmmConnectionInfo =
        new QMMConnectionInfo(
            1L,
            1L,
            projectInfo,
            "42",
            "Container Name",
            "42",
            new DBPConnectionConfiguration(),
            "Instance ID",
            "Context Name",
            true);

    // Act
    QMMConnectionInfo actualConnection = qmmConnectionInfo.getConnection();

    // Assert
    assertSame(qmmConnectionInfo, actualConnection);
  }

  /**
   * Test {@link QMMConnectionInfo#changeTransactional(boolean)}.
   *
   * <p>Method under test: {@link QMMConnectionInfo#changeTransactional(boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"QMMTransactionInfo QMMConnectionInfo.changeTransactional(boolean)"})
  public void testChangeTransactional() {
    // Arrange
    QMMProjectInfo projectInfo =
        QMMProjectInfo.builder()
            .setId("42")
            .setAnonymous(true)
            .setName("Name")
            .setPath("Path")
            .build();
    QMMConnectionInfo qmmConnectionInfo =
        new QMMConnectionInfo(
            1L,
            1L,
            projectInfo,
            "42",
            "Container Name",
            "42",
            new DBPConnectionConfiguration(),
            "Instance ID",
            "Context Name",
            false);

    // Act
    qmmConnectionInfo.changeTransactional(true);

    // Assert
    QMMTransactionInfo transaction = qmmConnectionInfo.getTransaction();
    assertEquals("Container Name - Context Name", transaction.getText());
    assertNull(transaction.getPrevious());
    assertEquals(-1L, transaction.getDuration());
    assertEquals(0L, transaction.getCloseTime());
    assertEquals(QMMetaObjectType.TRANSACTION_INFO, transaction.getObjectType());
    assertFalse(transaction.isClosed());
    assertFalse(transaction.isUpdated());
    assertFalse(transaction.isCommitted());
    assertTrue(qmmConnectionInfo.isTransactional());
    assertSame(qmmConnectionInfo, transaction.getConnection());
  }

  /**
   * Test {@link QMMConnectionInfo#changeTransactional(boolean)}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link QMMConnectionInfo#changeTransactional(boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"QMMTransactionInfo QMMConnectionInfo.changeTransactional(boolean)"})
  public void testChangeTransactional_thenReturnNull() {
    // Arrange
    QMMProjectInfo projectInfo =
        QMMProjectInfo.builder()
            .setId("42")
            .setAnonymous(true)
            .setName("Name")
            .setPath("Path")
            .build();
    QMMConnectionInfo qmmConnectionInfo =
        new QMMConnectionInfo(
            1L,
            1L,
            projectInfo,
            "42",
            "Container Name",
            "42",
            new DBPConnectionConfiguration(),
            "Instance ID",
            "Context Name",
            true);

    // Act and Assert
    assertNull(qmmConnectionInfo.changeTransactional(true));
    assertTrue(qmmConnectionInfo.isTransactional());
  }

  /**
   * Test {@link QMMConnectionInfo#commit()}.
   *
   * <p>Method under test: {@link QMMConnectionInfo#commit()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"QMMTransactionInfo QMMConnectionInfo.commit()"})
  public void testCommit() {
    // Arrange
    QMMProjectInfo projectInfo =
        QMMProjectInfo.builder()
            .setId("42")
            .setAnonymous(true)
            .setName("Name")
            .setPath("Path")
            .build();
    QMMConnectionInfo qmmConnectionInfo =
        new QMMConnectionInfo(
            1L,
            1L,
            projectInfo,
            "42",
            "Container Name",
            "42",
            new DBPConnectionConfiguration(),
            "Instance ID",
            "Context Name",
            true);

    // Act
    qmmConnectionInfo.commit();

    // Assert
    QMMTransactionInfo transaction = qmmConnectionInfo.getTransaction();
    assertEquals("Container Name - Context Name", transaction.getText());
    assertNull(transaction.getPrevious());
    assertEquals(-1L, transaction.getDuration());
    assertEquals(0L, transaction.getCloseTime());
    assertEquals(QMMetaObjectType.TRANSACTION_INFO, transaction.getObjectType());
    assertFalse(transaction.isClosed());
    assertFalse(transaction.isUpdated());
    assertFalse(transaction.isCommitted());
    assertSame(qmmConnectionInfo, transaction.getConnection());
  }

  /**
   * Test {@link QMMConnectionInfo#commit()}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link QMMConnectionInfo#commit()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"QMMTransactionInfo QMMConnectionInfo.commit()"})
  public void testCommit_thenReturnNull() {
    // Arrange
    QMMProjectInfo projectInfo =
        QMMProjectInfo.builder()
            .setId("42")
            .setAnonymous(true)
            .setName("Name")
            .setPath("Path")
            .build();
    QMMConnectionInfo qmmConnectionInfo =
        new QMMConnectionInfo(
            1L,
            1L,
            projectInfo,
            "42",
            "Container Name",
            "42",
            new DBPConnectionConfiguration(),
            "Instance ID",
            "Context Name",
            false);

    // Act and Assert
    assertNull(qmmConnectionInfo.commit());
  }

  /**
   * Test {@link QMMConnectionInfo#rollback(DBCSavepoint)}.
   *
   * <p>Method under test: {@link QMMConnectionInfo#rollback(DBCSavepoint)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.qm.meta.QMMObject QMMConnectionInfo.rollback(DBCSavepoint)"
  })
  public void testRollback() {
    // Arrange
    QMMProjectInfo projectInfo =
        QMMProjectInfo.builder()
            .setId("42")
            .setAnonymous(true)
            .setName("Name")
            .setPath("Path")
            .build();
    QMMConnectionInfo qmmConnectionInfo =
        new QMMConnectionInfo(
            1L,
            1L,
            projectInfo,
            "42",
            "Container Name",
            "42",
            new DBPConnectionConfiguration(),
            "Instance ID",
            "Context Name",
            false);

    // Act and Assert
    assertNull(qmmConnectionInfo.rollback(mock(DBCSavepoint.class)));
  }

  /**
   * Test {@link QMMConnectionInfo#rollback(DBCSavepoint)}.
   *
   * <p>Method under test: {@link QMMConnectionInfo#rollback(DBCSavepoint)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.qm.meta.QMMObject QMMConnectionInfo.rollback(DBCSavepoint)"
  })
  public void testRollback2() {
    // Arrange
    QMMProjectInfo projectInfo =
        QMMProjectInfo.builder()
            .setId("42")
            .setAnonymous(true)
            .setName("Name")
            .setPath("Path")
            .build();
    QMMConnectionInfo qmmConnectionInfo =
        new QMMConnectionInfo(
            1L,
            1L,
            projectInfo,
            "42",
            "Container Name",
            "42",
            new DBPConnectionConfiguration(),
            "Instance ID",
            "Context Name",
            true);

    // Act
    qmmConnectionInfo.rollback(null);

    // Assert
    QMMTransactionInfo transaction = qmmConnectionInfo.getTransaction();
    assertEquals("Container Name - Context Name", transaction.getText());
    assertNull(transaction.getPrevious());
    assertEquals(-1L, transaction.getDuration());
    assertEquals(0L, transaction.getCloseTime());
    assertEquals(QMMetaObjectType.TRANSACTION_INFO, transaction.getObjectType());
    assertFalse(transaction.isClosed());
    assertFalse(transaction.isUpdated());
    assertFalse(transaction.isCommitted());
    assertSame(qmmConnectionInfo, transaction.getConnection());
  }

  /**
   * Test {@link QMMConnectionInfo#rollback(DBCSavepoint)}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link QMMConnectionInfo#rollback(DBCSavepoint)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.qm.meta.QMMObject QMMConnectionInfo.rollback(DBCSavepoint)"
  })
  public void testRollback_thenReturnNull() {
    // Arrange
    QMMProjectInfo projectInfo =
        QMMProjectInfo.builder()
            .setId("42")
            .setAnonymous(true)
            .setName("Name")
            .setPath("Path")
            .build();
    QMMConnectionInfo qmmConnectionInfo =
        new QMMConnectionInfo(
            1L,
            1L,
            projectInfo,
            "42",
            "Container Name",
            "42",
            new DBPConnectionConfiguration(),
            "Instance ID",
            "Context Name",
            true);

    // Act and Assert
    assertNull(qmmConnectionInfo.rollback(mock(DBCSavepoint.class)));
  }

  /**
   * Test {@link QMMConnectionInfo#openStatement(DBCStatement)}.
   *
   * <ul>
   *   <li>Given {@code USER}.
   *   <li>Then Reference return {@link LocalStatement}.
   * </ul>
   *
   * <p>Method under test: {@link QMMConnectionInfo#openStatement(DBCStatement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"QMMStatementInfo QMMConnectionInfo.openStatement(DBCStatement)"})
  public void testOpenStatement_givenUser_thenReferenceReturnLocalStatement() {
    // Arrange
    QMMProjectInfo projectInfo =
        QMMProjectInfo.builder()
            .setId("42")
            .setAnonymous(true)
            .setName("Name")
            .setPath("Path")
            .build();
    QMMConnectionInfo qmmConnectionInfo =
        new QMMConnectionInfo(
            1L,
            1L,
            projectInfo,
            "42",
            "Container Name",
            "42",
            new DBPConnectionConfiguration(),
            "Instance ID",
            "Context Name",
            true);

    DBCSession session = mock(DBCSession.class);
    when(session.getPurpose()).thenReturn(DBCExecutionPurpose.USER);
    LocalStatement statement = new LocalStatement(session, "Text");

    // Act
    QMMStatementInfo actualOpenStatementResult = qmmConnectionInfo.openStatement(statement);

    // Assert
    verify(session).getPurpose();
    DBCStatement reference = actualOpenStatementResult.getReference();
    assertTrue(reference instanceof LocalStatement);
    assertEquals("Container Name - Context Name", actualOpenStatementResult.getText());
    assertNull(actualOpenStatementResult.getPrevious());
    assertEquals(-1L, actualOpenStatementResult.getDuration());
    assertEquals(0L, actualOpenStatementResult.getCloseTime());
    assertEquals(DBCExecutionPurpose.USER, actualOpenStatementResult.getPurpose());
    assertEquals(QMMetaObjectType.STATEMENT_INFO, actualOpenStatementResult.getObjectType());
    assertFalse(actualOpenStatementResult.isClosed());
    assertFalse(actualOpenStatementResult.isUpdated());
    assertSame(statement, reference);
    assertSame(qmmConnectionInfo, actualOpenStatementResult.getConnection());
  }

  /**
   * Test {@link QMMConnectionInfo#closeStatement(DBCStatement, long)}.
   *
   * <p>Method under test: {@link QMMConnectionInfo#closeStatement(DBCStatement, long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"QMMStatementInfo QMMConnectionInfo.closeStatement(DBCStatement, long)"})
  public void testCloseStatement() {
    // Arrange
    QMMProjectInfo projectInfo =
        QMMProjectInfo.builder()
            .setId("42")
            .setAnonymous(true)
            .setName("Name")
            .setPath("Path")
            .build();
    QMMConnectionInfo qmmConnectionInfo =
        new QMMConnectionInfo(
            1L,
            1L,
            projectInfo,
            "42",
            "Container Name",
            "42",
            new DBPConnectionConfiguration(),
            "Instance ID",
            "Context Name",
            true);

    // Act and Assert
    assertNull(
        qmmConnectionInfo.closeStatement(new LocalStatement(mock(DBCSession.class), "Text"), 1L));
  }

  /**
   * Test {@link QMMConnectionInfo#closeStatement(DBCStatement, long)}.
   *
   * <ul>
   *   <li>Given {@link DBCSession} {@link DBCSession#getPurpose()} return {@code USER}.
   *   <li>Then calls {@link DBCSession#getPurpose()}.
   * </ul>
   *
   * <p>Method under test: {@link QMMConnectionInfo#closeStatement(DBCStatement, long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"QMMStatementInfo QMMConnectionInfo.closeStatement(DBCStatement, long)"})
  public void testCloseStatement_givenDBCSessionGetPurposeReturnUser_thenCallsGetPurpose() {
    // Arrange
    DBCSession session = mock(DBCSession.class);
    when(session.getPurpose()).thenReturn(DBCExecutionPurpose.USER);
    LocalStatement statement = new LocalStatement(session, "Text");
    QMMProjectInfo projectInfo =
        QMMProjectInfo.builder()
            .setId("42")
            .setAnonymous(true)
            .setName("Name")
            .setPath("Path")
            .build();

    QMMConnectionInfo qmmConnectionInfo =
        new QMMConnectionInfo(
            1L,
            1L,
            projectInfo,
            "42",
            "Container Name",
            "42",
            new DBPConnectionConfiguration(),
            "Instance ID",
            "Context Name",
            true);
    qmmConnectionInfo.openStatement(statement);

    // Act
    QMMStatementInfo actualCloseStatementResult =
        qmmConnectionInfo.closeStatement(new LocalStatement(mock(DBCSession.class), "Text"), 1L);

    // Assert
    verify(session).getPurpose();
    assertNull(actualCloseStatementResult);
  }

  /**
   * Test {@link QMMConnectionInfo#getStatement(DBCStatement)}.
   *
   * <p>Method under test: {@link QMMConnectionInfo#getStatement(DBCStatement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"QMMStatementInfo QMMConnectionInfo.getStatement(DBCStatement)"})
  public void testGetStatement() {
    // Arrange
    QMMProjectInfo projectInfo =
        QMMProjectInfo.builder()
            .setId("42")
            .setAnonymous(true)
            .setName("Name")
            .setPath("Path")
            .build();
    QMMConnectionInfo qmmConnectionInfo =
        new QMMConnectionInfo(
            1L,
            1L,
            projectInfo,
            "42",
            "Container Name",
            "42",
            new DBPConnectionConfiguration(),
            "Instance ID",
            "Context Name",
            true);

    // Act
    QMMStatementInfo actualStatement =
        qmmConnectionInfo.getStatement(new LocalStatement(mock(DBCSession.class), "Text"));

    // Assert
    assertNull(actualStatement);
  }

  /**
   * Test {@link QMMConnectionInfo#getStatement(DBCStatement)}.
   *
   * <ul>
   *   <li>Given {@link DBCSession} {@link DBCSession#getPurpose()} return {@code USER}.
   *   <li>Then calls {@link DBCSession#getPurpose()}.
   * </ul>
   *
   * <p>Method under test: {@link QMMConnectionInfo#getStatement(DBCStatement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"QMMStatementInfo QMMConnectionInfo.getStatement(DBCStatement)"})
  public void testGetStatement_givenDBCSessionGetPurposeReturnUser_thenCallsGetPurpose() {
    // Arrange
    DBCSession session = mock(DBCSession.class);
    when(session.getPurpose()).thenReturn(DBCExecutionPurpose.USER);
    LocalStatement statement = new LocalStatement(session, "Text");
    QMMProjectInfo projectInfo =
        QMMProjectInfo.builder()
            .setId("42")
            .setAnonymous(true)
            .setName("Name")
            .setPath("Path")
            .build();

    QMMConnectionInfo qmmConnectionInfo =
        new QMMConnectionInfo(
            1L,
            1L,
            projectInfo,
            "42",
            "Container Name",
            "42",
            new DBPConnectionConfiguration(),
            "Instance ID",
            "Context Name",
            true);
    qmmConnectionInfo.openStatement(statement);

    // Act
    QMMStatementInfo actualStatement =
        qmmConnectionInfo.getStatement(new LocalStatement(mock(DBCSession.class), "Text"));

    // Assert
    verify(session).getPurpose();
    assertNull(actualStatement);
  }

  /**
   * Test {@link QMMConnectionInfo#getExecution(DBCStatement)}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link QMMConnectionInfo#getExecution(DBCStatement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"QMMStatementExecuteInfo QMMConnectionInfo.getExecution(DBCStatement)"})
  public void testGetExecution_thenReturnNull() {
    // Arrange
    QMMProjectInfo projectInfo =
        QMMProjectInfo.builder()
            .setId("42")
            .setAnonymous(true)
            .setName("Name")
            .setPath("Path")
            .build();
    QMMConnectionInfo qmmConnectionInfo =
        new QMMConnectionInfo(
            1L,
            1L,
            projectInfo,
            "42",
            "Container Name",
            "42",
            new DBPConnectionConfiguration(),
            "Instance ID",
            "Context Name",
            true);

    // Act
    QMMStatementExecuteInfo actualExecution =
        qmmConnectionInfo.getExecution(new LocalStatement(mock(DBCSession.class), "Text"));

    // Assert
    assertNull(actualExecution);
  }

  /**
   * Test {@link QMMConnectionInfo#beginExecution(DBCStatement)}.
   *
   * <p>Method under test: {@link QMMConnectionInfo#beginExecution(DBCStatement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"QMMStatementExecuteInfo QMMConnectionInfo.beginExecution(DBCStatement)"})
  public void testBeginExecution() {
    // Arrange
    QMMProjectInfo projectInfo =
        QMMProjectInfo.builder()
            .setId("42")
            .setAnonymous(true)
            .setName("Name")
            .setPath("Path")
            .build();
    QMMConnectionInfo qmmConnectionInfo =
        new QMMConnectionInfo(
            1L,
            1L,
            projectInfo,
            "42",
            "Container Name",
            "42",
            new DBPConnectionConfiguration(),
            "Instance ID",
            "Context Name",
            true);

    // Act
    QMMStatementExecuteInfo actualBeginExecutionResult =
        qmmConnectionInfo.beginExecution(new LocalStatement(mock(DBCSession.class), "Text"));

    // Assert
    assertNull(actualBeginExecutionResult);
  }

  /**
   * Test {@link QMMConnectionInfo#beginExecution(DBCStatement)}.
   *
   * <ul>
   *   <li>Given {@link DBCSession} {@link DBCSession#getPurpose()} return {@code USER}.
   *   <li>Then calls {@link DBCSession#getPurpose()}.
   * </ul>
   *
   * <p>Method under test: {@link QMMConnectionInfo#beginExecution(DBCStatement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"QMMStatementExecuteInfo QMMConnectionInfo.beginExecution(DBCStatement)"})
  public void testBeginExecution_givenDBCSessionGetPurposeReturnUser_thenCallsGetPurpose() {
    // Arrange
    DBCSession session = mock(DBCSession.class);
    when(session.getPurpose()).thenReturn(DBCExecutionPurpose.USER);
    LocalStatement statement = new LocalStatement(session, "Text");
    QMMProjectInfo projectInfo =
        QMMProjectInfo.builder()
            .setId("42")
            .setAnonymous(true)
            .setName("Name")
            .setPath("Path")
            .build();

    QMMConnectionInfo qmmConnectionInfo =
        new QMMConnectionInfo(
            1L,
            1L,
            projectInfo,
            "42",
            "Container Name",
            "42",
            new DBPConnectionConfiguration(),
            "Instance ID",
            "Context Name",
            true);
    qmmConnectionInfo.openStatement(statement);

    // Act
    QMMStatementExecuteInfo actualBeginExecutionResult =
        qmmConnectionInfo.beginExecution(new LocalStatement(mock(DBCSession.class), "Text"));

    // Assert
    verify(session).getPurpose();
    assertNull(actualBeginExecutionResult);
  }

  /**
   * Test {@link QMMConnectionInfo#endExecution(DBCStatement, long, Throwable)}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link QMMConnectionInfo#endExecution(DBCStatement, long, Throwable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "QMMStatementExecuteInfo QMMConnectionInfo.endExecution(DBCStatement, long, Throwable)"
  })
  public void testEndExecution_thenReturnNull() {
    // Arrange
    QMMProjectInfo projectInfo =
        QMMProjectInfo.builder()
            .setId("42")
            .setAnonymous(true)
            .setName("Name")
            .setPath("Path")
            .build();
    QMMConnectionInfo qmmConnectionInfo =
        new QMMConnectionInfo(
            1L,
            1L,
            projectInfo,
            "42",
            "Container Name",
            "42",
            new DBPConnectionConfiguration(),
            "Instance ID",
            "Context Name",
            true);
    LocalStatement statement = new LocalStatement(mock(DBCSession.class), "Text");

    // Act and Assert
    assertNull(qmmConnectionInfo.endExecution(statement, 3L, new Throwable()));
  }

  /**
   * Test {@link QMMConnectionInfo#beginFetch(DBCResultSet)}.
   *
   * <ul>
   *   <li>Given {@link DBCSession} {@link DBCSession#getPurpose()} return {@code USER}.
   *   <li>Then calls {@link DBCSession#getPurpose()}.
   * </ul>
   *
   * <p>Method under test: {@link QMMConnectionInfo#beginFetch(DBCResultSet)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"QMMStatementExecuteInfo QMMConnectionInfo.beginFetch(DBCResultSet)"})
  public void testBeginFetch_givenDBCSessionGetPurposeReturnUser_thenCallsGetPurpose() {
    // Arrange
    DBCSession session = mock(DBCSession.class);
    when(session.getPurpose()).thenReturn(DBCExecutionPurpose.USER);
    LocalStatement statement = new LocalStatement(session, "Text");
    QMMProjectInfo projectInfo =
        QMMProjectInfo.builder()
            .setId("42")
            .setAnonymous(true)
            .setName("Name")
            .setPath("Path")
            .build();

    QMMConnectionInfo qmmConnectionInfo =
        new QMMConnectionInfo(
            1L,
            1L,
            projectInfo,
            "42",
            "Container Name",
            "42",
            new DBPConnectionConfiguration(),
            "Instance ID",
            "Context Name",
            true);
    qmmConnectionInfo.openStatement(statement);
    DBCSession session2 = mock(DBCSession.class);
    LocalStatement localStatement = new LocalStatement(mock(DBCSession.class), "Text");

    LocalResultSet<DBCStatement> resultSet = new LocalResultSet<>(session2, localStatement);

    // Act
    QMMStatementExecuteInfo actualBeginFetchResult = qmmConnectionInfo.beginFetch(resultSet);

    // Assert
    verify(session).getPurpose();
    assertNull(actualBeginFetchResult);
  }

  /**
   * Test {@link QMMConnectionInfo#beginFetch(DBCResultSet)}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link QMMConnectionInfo#beginFetch(DBCResultSet)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"QMMStatementExecuteInfo QMMConnectionInfo.beginFetch(DBCResultSet)"})
  public void testBeginFetch_thenReturnNull() {
    // Arrange
    QMMProjectInfo projectInfo =
        QMMProjectInfo.builder()
            .setId("42")
            .setAnonymous(true)
            .setName("Name")
            .setPath("Path")
            .build();
    QMMConnectionInfo qmmConnectionInfo =
        new QMMConnectionInfo(
            1L,
            1L,
            projectInfo,
            "42",
            "Container Name",
            "42",
            new DBPConnectionConfiguration(),
            "Instance ID",
            "Context Name",
            true);
    DBCSession session = mock(DBCSession.class);
    LocalStatement localStatement = new LocalStatement(mock(DBCSession.class), "Text");

    LocalResultSet<DBCStatement> resultSet = new LocalResultSet<>(session, localStatement);

    // Act
    QMMStatementExecuteInfo actualBeginFetchResult = qmmConnectionInfo.beginFetch(resultSet);

    // Assert
    assertNull(actualBeginFetchResult);
  }

  /**
   * Test {@link QMMConnectionInfo#endFetch(DBCResultSet, long)}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link QMMConnectionInfo#endFetch(DBCResultSet, long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"QMMStatementExecuteInfo QMMConnectionInfo.endFetch(DBCResultSet, long)"})
  public void testEndFetch_thenReturnNull() {
    // Arrange
    QMMProjectInfo projectInfo =
        QMMProjectInfo.builder()
            .setId("42")
            .setAnonymous(true)
            .setName("Name")
            .setPath("Path")
            .build();
    QMMConnectionInfo qmmConnectionInfo =
        new QMMConnectionInfo(
            1L,
            1L,
            projectInfo,
            "42",
            "Container Name",
            "42",
            new DBPConnectionConfiguration(),
            "Instance ID",
            "Context Name",
            true);
    DBCSession session = mock(DBCSession.class);
    LocalStatement localStatement = new LocalStatement(mock(DBCSession.class), "Text");

    LocalResultSet<DBCStatement> resultSet = new LocalResultSet<>(session, localStatement);

    // Act and Assert
    assertNull(qmmConnectionInfo.endFetch(resultSet, 3L));
  }

  /**
   * Test {@link QMMConnectionInfo#getProjectId()}.
   *
   * <ul>
   *   <li>Then return {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link QMMConnectionInfo#getProjectId()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String QMMConnectionInfo.getProjectId()"})
  public void testGetProjectId_thenReturn42() {
    // Arrange
    QMMProjectInfo projectInfo =
        QMMProjectInfo.builder()
            .setId("42")
            .setAnonymous(true)
            .setName("Name")
            .setPath("Path")
            .build();
    QMMConnectionInfo qmmConnectionInfo =
        new QMMConnectionInfo(
            1L,
            1L,
            projectInfo,
            "42",
            "Container Name",
            "42",
            new DBPConnectionConfiguration(),
            "Instance ID",
            "Context Name",
            true);

    // Act and Assert
    assertEquals("42", qmmConnectionInfo.getProjectId());
  }
}
