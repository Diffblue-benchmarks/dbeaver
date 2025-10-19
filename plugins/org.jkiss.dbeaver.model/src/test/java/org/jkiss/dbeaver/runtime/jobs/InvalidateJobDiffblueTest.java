package org.jkiss.dbeaver.runtime.jobs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.Log;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.model.exec.DBCExecutionContext;
import org.jkiss.dbeaver.model.exec.DBCInvalidatePhase;
import org.jkiss.dbeaver.model.impl.SimpleExclusiveLock;
import org.jkiss.dbeaver.model.impl.net.HTTPTunnelImpl;
import org.jkiss.dbeaver.model.net.DBWNetworkHandler;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.DefaultProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LocalCacheProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.jkiss.dbeaver.model.runtime.SubTaskProgressMonitor;
import org.jkiss.dbeaver.model.runtime.VoidProgressMonitor;
import org.jkiss.dbeaver.model.struct.DBSInstance;
import org.jkiss.dbeaver.runtime.jobs.InvalidateJob.ContextInvalidateResult;
import org.jkiss.dbeaver.runtime.jobs.InvalidateJob.InvalidationFeedbackHandler;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class InvalidateJobDiffblueTest {
  /**
   * Test ContextInvalidateResult getters and setters.
   *
   * <ul>
   *   <li>Then return Exception is {@link Exception#Exception()}.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link ContextInvalidateResult#ContextInvalidateResult(DBPDataSource, Exception)}
   *   <li>{@link ContextInvalidateResult#getDataSource()}
   *   <li>{@link ContextInvalidateResult#getException()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ContextInvalidateResult.<init>(DBPDataSource)",
    "void ContextInvalidateResult.<init>(DBPDataSource, Exception)",
    "DBPDataSource ContextInvalidateResult.getDataSource()",
    "Exception ContextInvalidateResult.getException()"
  })
  public void testContextInvalidateResultGettersAndSetters_thenReturnExceptionIsException() {
    // Arrange
    DBPDataSource dataSource = mock(DBPDataSource.class);
    Exception exception = new Exception();

    // Act
    ContextInvalidateResult actualContextInvalidateResult =
        new ContextInvalidateResult(dataSource, exception);
    DBPDataSource actualDataSource = actualContextInvalidateResult.getDataSource();

    // Assert
    assertSame(exception, actualContextInvalidateResult.getException());
    assertSame(dataSource, actualDataSource);
  }

  /**
   * Test ContextInvalidateResult getters and setters.
   *
   * <ul>
   *   <li>Then return Exception is {@code null}.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link ContextInvalidateResult#ContextInvalidateResult(DBPDataSource)}
   *   <li>{@link ContextInvalidateResult#getDataSource()}
   *   <li>{@link ContextInvalidateResult#getException()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ContextInvalidateResult.<init>(DBPDataSource)",
    "void ContextInvalidateResult.<init>(DBPDataSource, Exception)",
    "DBPDataSource ContextInvalidateResult.getDataSource()",
    "Exception ContextInvalidateResult.getException()"
  })
  public void testContextInvalidateResultGettersAndSetters_thenReturnExceptionIsNull() {
    // Arrange
    DBPDataSource dataSource = mock(DBPDataSource.class);

    // Act
    ContextInvalidateResult actualContextInvalidateResult = new ContextInvalidateResult(dataSource);
    DBPDataSource actualDataSource = actualContextInvalidateResult.getDataSource();

    // Assert
    assertNull(actualContextInvalidateResult.getException());
    assertSame(dataSource, actualDataSource);
  }

  /**
   * Test ContextInvalidateResult {@link ContextInvalidateResult#isError()}.
   *
   * <ul>
   *   <li>Given newSuccess {@link DBPDataSource}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link ContextInvalidateResult#isError()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ContextInvalidateResult.isError()"})
  public void testContextInvalidateResultIsError_givenNewSuccessDBPDataSource_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(ContextInvalidateResult.newSuccess(mock(DBPDataSource.class)).isError());
  }

  /**
   * Test ContextInvalidateResult {@link ContextInvalidateResult#isError()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link ContextInvalidateResult#isError()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ContextInvalidateResult.isError()"})
  public void testContextInvalidateResultIsError_thenReturnTrue() {
    // Arrange
    DBPDataSource dataSource = mock(DBPDataSource.class);

    // Act and Assert
    assertTrue(ContextInvalidateResult.newError(dataSource, new Exception()).isError());
  }

  /**
   * Test ContextInvalidateResult {@link ContextInvalidateResult#isSuccess()}.
   *
   * <ul>
   *   <li>Given newSuccess {@link DBPDataSource}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link ContextInvalidateResult#isSuccess()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ContextInvalidateResult.isSuccess()"})
  public void testContextInvalidateResultIsSuccess_givenNewSuccessDBPDataSource_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(ContextInvalidateResult.newSuccess(mock(DBPDataSource.class)).isSuccess());
  }

  /**
   * Test ContextInvalidateResult {@link ContextInvalidateResult#isSuccess()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link ContextInvalidateResult#isSuccess()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ContextInvalidateResult.isSuccess()"})
  public void testContextInvalidateResultIsSuccess_thenReturnFalse() {
    // Arrange
    DBPDataSource dataSource = mock(DBPDataSource.class);

    // Act and Assert
    assertFalse(ContextInvalidateResult.newError(dataSource, new Exception()).isSuccess());
  }

  /**
   * Test ContextInvalidateResult {@link ContextInvalidateResult#newError(DBPDataSource,
   * Exception)}.
   *
   * <p>Method under test: {@link ContextInvalidateResult#newError(DBPDataSource, Exception)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ContextInvalidateResult ContextInvalidateResult.newError(DBPDataSource, Exception)"
  })
  public void testContextInvalidateResultNewError() {
    // Arrange
    DBPDataSource dataSource = mock(DBPDataSource.class);
    Exception exception = new Exception();

    // Act
    ContextInvalidateResult actualNewErrorResult =
        ContextInvalidateResult.newError(dataSource, exception);

    // Assert
    assertFalse(actualNewErrorResult.isSuccess());
    assertTrue(actualNewErrorResult.isError());
    assertSame(exception, actualNewErrorResult.getException());
    assertSame(dataSource, actualNewErrorResult.getDataSource());
  }

  /**
   * Test ContextInvalidateResult {@link ContextInvalidateResult#newSuccess(DBPDataSource)}.
   *
   * <p>Method under test: {@link ContextInvalidateResult#newSuccess(DBPDataSource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"ContextInvalidateResult ContextInvalidateResult.newSuccess(DBPDataSource)"})
  public void testContextInvalidateResultNewSuccess() {
    // Arrange
    DBPDataSource dataSource = mock(DBPDataSource.class);

    // Act
    ContextInvalidateResult actualNewSuccessResult = ContextInvalidateResult.newSuccess(dataSource);

    // Assert
    assertNull(actualNewSuccessResult.getException());
    assertFalse(actualNewSuccessResult.isError());
    assertTrue(actualNewSuccessResult.isSuccess());
    assertSame(dataSource, actualNewSuccessResult.getDataSource());
  }

  /**
   * Test {@link InvalidateJob#InvalidateJob(DBPDataSource)}.
   *
   * <ul>
   *   <li>Then return Name is {@code Invalidate Name}.
   * </ul>
   *
   * <p>Method under test: {@link InvalidateJob#InvalidateJob(DBPDataSource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void InvalidateJob.<init>(DBPDataSource)"})
  public void testNewInvalidateJob_thenReturnNameIsInvalidateName() {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getName()).thenReturn("Name");

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.isConnectionRefreshing()).thenReturn(false);

    DBPDataSource dbpDataSource2 = mock(DBPDataSource.class);
    when(dbpDataSource2.getContainer()).thenReturn(mock(DBPDataSourceContainer.class));

    DBCExecutionContext dbcExecutionContext = mock(DBCExecutionContext.class);
    when(dbcExecutionContext.getDataSource()).thenReturn(dbpDataSource2);

    DBSInstance dbsInstance = mock(DBSInstance.class);
    when(dbsInstance.getDataSource()).thenReturn(dbpDataSource);
    when(dbsInstance.getDefaultContext(Mockito.<DBRProgressMonitor>any(), anyBoolean()))
        .thenReturn(dbcExecutionContext);

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getDefaultInstance()).thenReturn(dbsInstance);
    when(dataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    // Act
    InvalidateJob actualInvalidateJob = new InvalidateJob(dataSource);

    // Assert
    verify(dataSource).getContainer();
    verify(dbpDataSource2).getContainer();
    verify(dbpDataSource).isConnectionRefreshing();
    verify(dbpDataSourceContainer).getName();
    verify(dbcExecutionContext).getDataSource();
    verify(dbsInstance).getDefaultContext(isA(DBRProgressMonitor.class), eq(false));
    verify(dataSource).getDefaultInstance();
    verify(dbsInstance, atLeast(1)).getDataSource();
    assertEquals("Invalidate Name", actualInvalidateJob.getName());
    assertNull(actualInvalidateJob.getThread());
    assertNull(actualInvalidateJob.getResult());
    assertNull(actualInvalidateJob.getRule());
    assertNull(actualInvalidateJob.getJobGroup());
    assertEquals(-1L, actualInvalidateJob.getCancelTimestamp());
    assertEquals(0, actualInvalidateJob.getState());
    assertEquals(30, actualInvalidateJob.getPriority());
    assertFalse(actualInvalidateJob.isBlocking());
    assertFalse(actualInvalidateJob.isSystem());
    assertFalse(actualInvalidateJob.isCanceled());
    assertFalse(actualInvalidateJob.isFinished());
    assertFalse(actualInvalidateJob.isRunDirectly());
    assertFalse(actualInvalidateJob.isActiveTask());
    assertTrue(actualInvalidateJob.getInvalidateResults().isEmpty());
    assertTrue(actualInvalidateJob.isUser());
    assertTrue(actualInvalidateJob.isForceCancel());
  }

  /**
   * Test {@link InvalidateJob#invalidateDataSource(DBRProgressMonitor, DBPDataSource, boolean,
   * boolean, InvalidationFeedbackHandler)}.
   *
   * <ul>
   *   <li>Then return size is five.
   * </ul>
   *
   * <p>Method under test: {@link InvalidateJob#invalidateDataSource(DBRProgressMonitor,
   * DBPDataSource, boolean, boolean, InvalidationFeedbackHandler)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List InvalidateJob.invalidateDataSource(DBRProgressMonitor, DBPDataSource, boolean, boolean, InvalidationFeedbackHandler)"
  })
  public void testInvalidateDataSource_thenReturnSizeIsFive() throws DBException {
    // Arrange
    LocalCacheProgressMonitor nestedMonitor =
        new LocalCacheProgressMonitor(new SubTaskProgressMonitor(new LoggingProgressMonitor()));
    DefaultProgressMonitor monitor = new DefaultProgressMonitor(nestedMonitor);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getId()).thenReturn("42");
    when(dbpDataSourceContainer.getName()).thenReturn("Name");
    when(dbpDataSourceContainer.getActiveNetworkHandlers())
        .thenReturn(new DBWNetworkHandler[] {new HTTPTunnelImpl()});

    DBCExecutionContext dbcExecutionContext = mock(DBCExecutionContext.class);
    when(dbcExecutionContext.getContextName()).thenReturn("Context Name");
    doNothing()
        .when(dbcExecutionContext)
        .invalidateContext(Mockito.<DBRProgressMonitor>any(), Mockito.<DBCInvalidatePhase>any());

    DBSInstance dbsInstance = mock(DBSInstance.class);
    when(dbsInstance.getExclusiveLock()).thenReturn(new SimpleExclusiveLock());
    when(dbsInstance.getAllContexts()).thenReturn(new DBCExecutionContext[] {dbcExecutionContext});

    DBCExecutionContext dbcExecutionContext2 = mock(DBCExecutionContext.class);
    when(dbcExecutionContext2.getContextName()).thenReturn("Context Name");
    doNothing()
        .when(dbcExecutionContext2)
        .invalidateContext(Mockito.<DBRProgressMonitor>any(), Mockito.<DBCInvalidatePhase>any());

    DBSInstance dbsInstance2 = mock(DBSInstance.class);
    when(dbsInstance2.getExclusiveLock()).thenReturn(new SimpleExclusiveLock());
    when(dbsInstance2.getAllContexts())
        .thenReturn(new DBCExecutionContext[] {dbcExecutionContext2});

    DBCExecutionContext dbcExecutionContext3 = mock(DBCExecutionContext.class);
    doNothing()
        .when(dbcExecutionContext3)
        .invalidateContext(Mockito.<DBRProgressMonitor>any(), Mockito.<DBCInvalidatePhase>any());
    when(dbcExecutionContext3.getContextName()).thenReturn("Context Name");

    DBSInstance dbsInstance3 = mock(DBSInstance.class);
    when(dbsInstance3.getExclusiveLock()).thenReturn(new SimpleExclusiveLock());
    when(dbsInstance3.getAllContexts())
        .thenReturn(new DBCExecutionContext[] {dbcExecutionContext3});

    DBCExecutionContext dbcExecutionContext4 = mock(DBCExecutionContext.class);
    doNothing()
        .when(dbcExecutionContext4)
        .invalidateContext(Mockito.<DBRProgressMonitor>any(), Mockito.<DBCInvalidatePhase>any());
    when(dbcExecutionContext4.getContextName()).thenReturn("Context Name");

    DBSInstance dbsInstance4 = mock(DBSInstance.class);
    when(dbsInstance4.getExclusiveLock()).thenReturn(new SimpleExclusiveLock());
    when(dbsInstance4.getAllContexts())
        .thenReturn(new DBCExecutionContext[] {dbcExecutionContext4});

    ArrayList<DBSInstance> dbsInstanceList = new ArrayList<>();
    dbsInstanceList.add(dbsInstance4);
    dbsInstanceList.add(dbsInstance3);
    dbsInstanceList.add(dbsInstance2);
    dbsInstanceList.add(dbsInstance);

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    Mockito.<Collection<? extends DBSInstance>>when(dbpDataSource.getAvailableInstances())
        .thenReturn(dbsInstanceList);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBPDataSourceContainer dbpDataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer2.getDataSource()).thenReturn(dbpDataSource);
    when(dbpDataSourceContainer2.getName()).thenReturn("Name");
    when(dbpDataSourceContainer2.getExclusiveLock()).thenReturn(new SimpleExclusiveLock());
    when(dbpDataSourceContainer2.getActiveNetworkHandlers())
        .thenReturn(new DBWNetworkHandler[] {new HTTPTunnelImpl()});

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getContainer()).thenReturn(dbpDataSourceContainer2);

    // Act
    List<ContextInvalidateResult> actualInvalidateDataSourceResult =
        InvalidateJob.invalidateDataSource(
            monitor, dataSource, true, true, new DefaultInvalidationFeedbackHandler());

    // Assert
    verify(dataSource).getContainer();
    verify(dbpDataSource, atLeast(1)).getContainer();
    verify(dbpDataSourceContainer2).getActiveNetworkHandlers();
    verify(dbpDataSourceContainer, atLeast(1)).getActiveNetworkHandlers();
    verify(dbpDataSourceContainer2, atLeast(1)).getDataSource();
    verify(dbpDataSourceContainer2, atLeast(1)).getExclusiveLock();
    verify(dbpDataSourceContainer, atLeast(1)).getId();
    verify(dbpDataSourceContainer2).getName();
    verify(dbpDataSourceContainer, atLeast(1)).getName();
    verify(dbcExecutionContext4, atLeast(1)).getContextName();
    verify(dbcExecutionContext3, atLeast(1)).getContextName();
    verify(dbcExecutionContext2, atLeast(1)).getContextName();
    verify(dbcExecutionContext, atLeast(1)).getContextName();
    verify(dbcExecutionContext4, atLeast(1))
        .invalidateContext(isA(DBRProgressMonitor.class), Mockito.<DBCInvalidatePhase>any());
    verify(dbcExecutionContext3, atLeast(1))
        .invalidateContext(isA(DBRProgressMonitor.class), Mockito.<DBCInvalidatePhase>any());
    verify(dbcExecutionContext2, atLeast(1))
        .invalidateContext(isA(DBRProgressMonitor.class), Mockito.<DBCInvalidatePhase>any());
    verify(dbcExecutionContext, atLeast(1))
        .invalidateContext(isA(DBRProgressMonitor.class), Mockito.<DBCInvalidatePhase>any());
    verify(dbsInstance4, atLeast(1)).getAllContexts();
    verify(dbsInstance3, atLeast(1)).getAllContexts();
    verify(dbsInstance2, atLeast(1)).getAllContexts();
    verify(dbsInstance, atLeast(1)).getAllContexts();
    verify(dbsInstance4, atLeast(1)).getExclusiveLock();
    verify(dbsInstance3, atLeast(1)).getExclusiveLock();
    verify(dbsInstance2, atLeast(1)).getExclusiveLock();
    verify(dbsInstance, atLeast(1)).getExclusiveLock();
    verify(dbpDataSource, atLeast(1)).getAvailableInstances();
    assertEquals(5, actualInvalidateDataSourceResult.size());
  }

  /**
   * Test {@link InvalidateJob#invalidateDataSource(DBRProgressMonitor, DBPDataSource, boolean,
   * boolean, InvalidationFeedbackHandler)}.
   *
   * <ul>
   *   <li>Then return size is four.
   * </ul>
   *
   * <p>Method under test: {@link InvalidateJob#invalidateDataSource(DBRProgressMonitor,
   * DBPDataSource, boolean, boolean, InvalidationFeedbackHandler)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List InvalidateJob.invalidateDataSource(DBRProgressMonitor, DBPDataSource, boolean, boolean, InvalidationFeedbackHandler)"
  })
  public void testInvalidateDataSource_thenReturnSizeIsFour() throws DBException {
    // Arrange
    DefaultProgressMonitor monitor =
        new DefaultProgressMonitor(new LocalCacheProgressMonitor(new LoggingProgressMonitor()));

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getId()).thenReturn("42");
    when(dbpDataSourceContainer.getName()).thenReturn("Name");
    when(dbpDataSourceContainer.getActiveNetworkHandlers())
        .thenReturn(new DBWNetworkHandler[] {new HTTPTunnelImpl()});

    DBCExecutionContext dbcExecutionContext = mock(DBCExecutionContext.class);
    when(dbcExecutionContext.getContextName()).thenReturn("Context Name");
    doNothing()
        .when(dbcExecutionContext)
        .invalidateContext(Mockito.<DBRProgressMonitor>any(), Mockito.<DBCInvalidatePhase>any());

    DBSInstance dbsInstance = mock(DBSInstance.class);
    when(dbsInstance.getExclusiveLock()).thenReturn(new SimpleExclusiveLock());
    when(dbsInstance.getAllContexts()).thenReturn(new DBCExecutionContext[] {dbcExecutionContext});

    DBCExecutionContext dbcExecutionContext2 = mock(DBCExecutionContext.class);
    when(dbcExecutionContext2.getContextName()).thenReturn("Context Name");
    doNothing()
        .when(dbcExecutionContext2)
        .invalidateContext(Mockito.<DBRProgressMonitor>any(), Mockito.<DBCInvalidatePhase>any());

    DBSInstance dbsInstance2 = mock(DBSInstance.class);
    when(dbsInstance2.getExclusiveLock()).thenReturn(new SimpleExclusiveLock());
    when(dbsInstance2.getAllContexts())
        .thenReturn(new DBCExecutionContext[] {dbcExecutionContext2});

    DBCExecutionContext dbcExecutionContext3 = mock(DBCExecutionContext.class);
    doNothing()
        .when(dbcExecutionContext3)
        .invalidateContext(Mockito.<DBRProgressMonitor>any(), Mockito.<DBCInvalidatePhase>any());
    when(dbcExecutionContext3.getContextName()).thenReturn("Context Name");

    DBSInstance dbsInstance3 = mock(DBSInstance.class);
    when(dbsInstance3.getExclusiveLock()).thenReturn(new SimpleExclusiveLock());
    when(dbsInstance3.getAllContexts())
        .thenReturn(new DBCExecutionContext[] {dbcExecutionContext3});

    ArrayList<DBSInstance> dbsInstanceList = new ArrayList<>();
    dbsInstanceList.add(dbsInstance3);
    dbsInstanceList.add(dbsInstance2);
    dbsInstanceList.add(dbsInstance);

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    Mockito.<Collection<? extends DBSInstance>>when(dbpDataSource.getAvailableInstances())
        .thenReturn(dbsInstanceList);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBPDataSourceContainer dbpDataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer2.getDataSource()).thenReturn(dbpDataSource);
    when(dbpDataSourceContainer2.getName()).thenReturn("Name");
    when(dbpDataSourceContainer2.getExclusiveLock()).thenReturn(new SimpleExclusiveLock());
    when(dbpDataSourceContainer2.getActiveNetworkHandlers())
        .thenReturn(new DBWNetworkHandler[] {new HTTPTunnelImpl()});

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getContainer()).thenReturn(dbpDataSourceContainer2);

    // Act
    List<ContextInvalidateResult> actualInvalidateDataSourceResult =
        InvalidateJob.invalidateDataSource(
            monitor, dataSource, true, true, new DefaultInvalidationFeedbackHandler());

    // Assert
    verify(dataSource).getContainer();
    verify(dbpDataSource, atLeast(1)).getContainer();
    verify(dbpDataSourceContainer2).getActiveNetworkHandlers();
    verify(dbpDataSourceContainer, atLeast(1)).getActiveNetworkHandlers();
    verify(dbpDataSourceContainer2, atLeast(1)).getDataSource();
    verify(dbpDataSourceContainer2, atLeast(1)).getExclusiveLock();
    verify(dbpDataSourceContainer, atLeast(1)).getId();
    verify(dbpDataSourceContainer2).getName();
    verify(dbpDataSourceContainer, atLeast(1)).getName();
    verify(dbcExecutionContext3, atLeast(1)).getContextName();
    verify(dbcExecutionContext2, atLeast(1)).getContextName();
    verify(dbcExecutionContext, atLeast(1)).getContextName();
    verify(dbcExecutionContext3, atLeast(1))
        .invalidateContext(isA(DBRProgressMonitor.class), Mockito.<DBCInvalidatePhase>any());
    verify(dbcExecutionContext2, atLeast(1))
        .invalidateContext(isA(DBRProgressMonitor.class), Mockito.<DBCInvalidatePhase>any());
    verify(dbcExecutionContext, atLeast(1))
        .invalidateContext(isA(DBRProgressMonitor.class), Mockito.<DBCInvalidatePhase>any());
    verify(dbsInstance3, atLeast(1)).getAllContexts();
    verify(dbsInstance2, atLeast(1)).getAllContexts();
    verify(dbsInstance, atLeast(1)).getAllContexts();
    verify(dbsInstance3, atLeast(1)).getExclusiveLock();
    verify(dbsInstance2, atLeast(1)).getExclusiveLock();
    verify(dbsInstance, atLeast(1)).getExclusiveLock();
    verify(dbpDataSource, atLeast(1)).getAvailableInstances();
    assertEquals(4, actualInvalidateDataSourceResult.size());
  }

  /**
   * Test {@link InvalidateJob#invalidateDataSource(DBRProgressMonitor, DBPDataSource, boolean,
   * boolean, InvalidationFeedbackHandler)}.
   *
   * <ul>
   *   <li>Then return size is six.
   * </ul>
   *
   * <p>Method under test: {@link InvalidateJob#invalidateDataSource(DBRProgressMonitor,
   * DBPDataSource, boolean, boolean, InvalidationFeedbackHandler)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List InvalidateJob.invalidateDataSource(DBRProgressMonitor, DBPDataSource, boolean, boolean, InvalidationFeedbackHandler)"
  })
  public void testInvalidateDataSource_thenReturnSizeIsSix() throws DBException {
    // Arrange
    SubProgressMonitor nestedMonitor =
        new SubProgressMonitor(new LocalCacheProgressMonitor(new LoggingProgressMonitor()), 1);
    DefaultProgressMonitor monitor = new DefaultProgressMonitor(nestedMonitor);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getId()).thenReturn("42");
    when(dbpDataSourceContainer.getName()).thenReturn("Name");
    when(dbpDataSourceContainer.getActiveNetworkHandlers())
        .thenReturn(new DBWNetworkHandler[] {new HTTPTunnelImpl()});

    DBCExecutionContext dbcExecutionContext = mock(DBCExecutionContext.class);
    when(dbcExecutionContext.getContextName()).thenReturn("Context Name");
    doNothing()
        .when(dbcExecutionContext)
        .invalidateContext(Mockito.<DBRProgressMonitor>any(), Mockito.<DBCInvalidatePhase>any());

    DBSInstance dbsInstance = mock(DBSInstance.class);
    when(dbsInstance.getExclusiveLock()).thenReturn(new SimpleExclusiveLock());
    when(dbsInstance.getAllContexts()).thenReturn(new DBCExecutionContext[] {dbcExecutionContext});

    DBCExecutionContext dbcExecutionContext2 = mock(DBCExecutionContext.class);
    when(dbcExecutionContext2.getContextName()).thenReturn("Context Name");
    doNothing()
        .when(dbcExecutionContext2)
        .invalidateContext(Mockito.<DBRProgressMonitor>any(), Mockito.<DBCInvalidatePhase>any());

    DBSInstance dbsInstance2 = mock(DBSInstance.class);
    when(dbsInstance2.getExclusiveLock()).thenReturn(new SimpleExclusiveLock());
    when(dbsInstance2.getAllContexts())
        .thenReturn(new DBCExecutionContext[] {dbcExecutionContext2});

    DBCExecutionContext dbcExecutionContext3 = mock(DBCExecutionContext.class);
    doNothing()
        .when(dbcExecutionContext3)
        .invalidateContext(Mockito.<DBRProgressMonitor>any(), Mockito.<DBCInvalidatePhase>any());
    when(dbcExecutionContext3.getContextName()).thenReturn("Context Name");

    DBSInstance dbsInstance3 = mock(DBSInstance.class);
    when(dbsInstance3.getExclusiveLock()).thenReturn(new SimpleExclusiveLock());
    when(dbsInstance3.getAllContexts())
        .thenReturn(new DBCExecutionContext[] {dbcExecutionContext3});

    DBCExecutionContext dbcExecutionContext4 = mock(DBCExecutionContext.class);
    doNothing()
        .when(dbcExecutionContext4)
        .invalidateContext(Mockito.<DBRProgressMonitor>any(), Mockito.<DBCInvalidatePhase>any());
    when(dbcExecutionContext4.getContextName()).thenReturn("Context Name");

    DBSInstance dbsInstance4 = mock(DBSInstance.class);
    when(dbsInstance4.getExclusiveLock()).thenReturn(new SimpleExclusiveLock());
    when(dbsInstance4.getAllContexts())
        .thenReturn(new DBCExecutionContext[] {dbcExecutionContext4});

    DBCExecutionContext dbcExecutionContext5 = mock(DBCExecutionContext.class);
    doNothing()
        .when(dbcExecutionContext5)
        .invalidateContext(Mockito.<DBRProgressMonitor>any(), Mockito.<DBCInvalidatePhase>any());
    when(dbcExecutionContext5.getContextName()).thenReturn("Context Name");

    DBSInstance dbsInstance5 = mock(DBSInstance.class);
    when(dbsInstance5.getExclusiveLock()).thenReturn(new SimpleExclusiveLock());
    when(dbsInstance5.getAllContexts())
        .thenReturn(new DBCExecutionContext[] {dbcExecutionContext5});

    ArrayList<DBSInstance> dbsInstanceList = new ArrayList<>();
    dbsInstanceList.add(dbsInstance5);
    dbsInstanceList.add(dbsInstance4);
    dbsInstanceList.add(dbsInstance3);
    dbsInstanceList.add(dbsInstance2);
    dbsInstanceList.add(dbsInstance);

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    Mockito.<Collection<? extends DBSInstance>>when(dbpDataSource.getAvailableInstances())
        .thenReturn(dbsInstanceList);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBPDataSourceContainer dbpDataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer2.getDataSource()).thenReturn(dbpDataSource);
    when(dbpDataSourceContainer2.getName()).thenReturn("Name");
    when(dbpDataSourceContainer2.getExclusiveLock()).thenReturn(new SimpleExclusiveLock());
    when(dbpDataSourceContainer2.getActiveNetworkHandlers())
        .thenReturn(new DBWNetworkHandler[] {new HTTPTunnelImpl()});

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getContainer()).thenReturn(dbpDataSourceContainer2);

    // Act
    List<ContextInvalidateResult> actualInvalidateDataSourceResult =
        InvalidateJob.invalidateDataSource(
            monitor, dataSource, true, true, new DefaultInvalidationFeedbackHandler());

    // Assert
    verify(dataSource).getContainer();
    verify(dbpDataSource, atLeast(1)).getContainer();
    verify(dbpDataSourceContainer2).getActiveNetworkHandlers();
    verify(dbpDataSourceContainer, atLeast(1)).getActiveNetworkHandlers();
    verify(dbpDataSourceContainer2, atLeast(1)).getDataSource();
    verify(dbpDataSourceContainer2, atLeast(1)).getExclusiveLock();
    verify(dbpDataSourceContainer, atLeast(1)).getId();
    verify(dbpDataSourceContainer2).getName();
    verify(dbpDataSourceContainer, atLeast(1)).getName();
    verify(dbcExecutionContext5, atLeast(1)).getContextName();
    verify(dbcExecutionContext4, atLeast(1)).getContextName();
    verify(dbcExecutionContext3, atLeast(1)).getContextName();
    verify(dbcExecutionContext2, atLeast(1)).getContextName();
    verify(dbcExecutionContext, atLeast(1)).getContextName();
    verify(dbcExecutionContext5, atLeast(1))
        .invalidateContext(isA(DBRProgressMonitor.class), Mockito.<DBCInvalidatePhase>any());
    verify(dbcExecutionContext4, atLeast(1))
        .invalidateContext(isA(DBRProgressMonitor.class), Mockito.<DBCInvalidatePhase>any());
    verify(dbcExecutionContext3, atLeast(1))
        .invalidateContext(isA(DBRProgressMonitor.class), Mockito.<DBCInvalidatePhase>any());
    verify(dbcExecutionContext2, atLeast(1))
        .invalidateContext(isA(DBRProgressMonitor.class), Mockito.<DBCInvalidatePhase>any());
    verify(dbcExecutionContext, atLeast(1))
        .invalidateContext(isA(DBRProgressMonitor.class), Mockito.<DBCInvalidatePhase>any());
    verify(dbsInstance5, atLeast(1)).getAllContexts();
    verify(dbsInstance4, atLeast(1)).getAllContexts();
    verify(dbsInstance3, atLeast(1)).getAllContexts();
    verify(dbsInstance2, atLeast(1)).getAllContexts();
    verify(dbsInstance, atLeast(1)).getAllContexts();
    verify(dbsInstance5, atLeast(1)).getExclusiveLock();
    verify(dbsInstance4, atLeast(1)).getExclusiveLock();
    verify(dbsInstance3, atLeast(1)).getExclusiveLock();
    verify(dbsInstance2, atLeast(1)).getExclusiveLock();
    verify(dbsInstance, atLeast(1)).getExclusiveLock();
    verify(dbpDataSource, atLeast(1)).getAvailableInstances();
    assertEquals(6, actualInvalidateDataSourceResult.size());
    ContextInvalidateResult getResult = actualInvalidateDataSourceResult.get(5);
    assertNull(getResult.getException());
    assertFalse(getResult.isError());
    assertTrue(getResult.isSuccess());
  }

  /**
   * Test {@link InvalidateJob#invalidateDataSource(DBRProgressMonitor, DBPDataSource, boolean,
   * boolean, InvalidationFeedbackHandler)}.
   *
   * <ul>
   *   <li>Then return third Exception is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link InvalidateJob#invalidateDataSource(DBRProgressMonitor,
   * DBPDataSource, boolean, boolean, InvalidationFeedbackHandler)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List InvalidateJob.invalidateDataSource(DBRProgressMonitor, DBPDataSource, boolean, boolean, InvalidationFeedbackHandler)"
  })
  public void testInvalidateDataSource_thenReturnThirdExceptionIsNull() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getId()).thenReturn("42");
    when(dbpDataSourceContainer.getName()).thenReturn("Name");
    when(dbpDataSourceContainer.getActiveNetworkHandlers())
        .thenReturn(new DBWNetworkHandler[] {new HTTPTunnelImpl()});

    DBCExecutionContext dbcExecutionContext = mock(DBCExecutionContext.class);
    when(dbcExecutionContext.getContextName()).thenReturn("Context Name");
    doNothing()
        .when(dbcExecutionContext)
        .invalidateContext(Mockito.<DBRProgressMonitor>any(), Mockito.<DBCInvalidatePhase>any());

    DBSInstance dbsInstance = mock(DBSInstance.class);
    when(dbsInstance.getExclusiveLock()).thenReturn(new SimpleExclusiveLock());
    when(dbsInstance.getAllContexts()).thenReturn(new DBCExecutionContext[] {dbcExecutionContext});

    DBCExecutionContext dbcExecutionContext2 = mock(DBCExecutionContext.class);
    when(dbcExecutionContext2.getContextName()).thenReturn("Context Name");
    doNothing()
        .when(dbcExecutionContext2)
        .invalidateContext(Mockito.<DBRProgressMonitor>any(), Mockito.<DBCInvalidatePhase>any());

    DBSInstance dbsInstance2 = mock(DBSInstance.class);
    when(dbsInstance2.getExclusiveLock()).thenReturn(new SimpleExclusiveLock());
    when(dbsInstance2.getAllContexts())
        .thenReturn(new DBCExecutionContext[] {dbcExecutionContext2});

    ArrayList<DBSInstance> dbsInstanceList = new ArrayList<>();
    dbsInstanceList.add(dbsInstance2);
    dbsInstanceList.add(dbsInstance);

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    Mockito.<Collection<? extends DBSInstance>>when(dbpDataSource.getAvailableInstances())
        .thenReturn(dbsInstanceList);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBPDataSourceContainer dbpDataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer2.getDataSource()).thenReturn(dbpDataSource);
    when(dbpDataSourceContainer2.getName()).thenReturn("Name");
    when(dbpDataSourceContainer2.getExclusiveLock()).thenReturn(new SimpleExclusiveLock());
    when(dbpDataSourceContainer2.getActiveNetworkHandlers())
        .thenReturn(new DBWNetworkHandler[] {new HTTPTunnelImpl()});

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getContainer()).thenReturn(dbpDataSourceContainer2);

    // Act
    List<ContextInvalidateResult> actualInvalidateDataSourceResult =
        InvalidateJob.invalidateDataSource(
            monitor, dataSource, true, true, new DefaultInvalidationFeedbackHandler());

    // Assert
    verify(dataSource).getContainer();
    verify(dbpDataSource, atLeast(1)).getContainer();
    verify(dbpDataSourceContainer2).getActiveNetworkHandlers();
    verify(dbpDataSourceContainer, atLeast(1)).getActiveNetworkHandlers();
    verify(dbpDataSourceContainer2, atLeast(1)).getDataSource();
    verify(dbpDataSourceContainer2, atLeast(1)).getExclusiveLock();
    verify(dbpDataSourceContainer, atLeast(1)).getId();
    verify(dbpDataSourceContainer2).getName();
    verify(dbpDataSourceContainer, atLeast(1)).getName();
    verify(dbcExecutionContext2, atLeast(1)).getContextName();
    verify(dbcExecutionContext, atLeast(1)).getContextName();
    verify(dbcExecutionContext2, atLeast(1))
        .invalidateContext(isA(DBRProgressMonitor.class), Mockito.<DBCInvalidatePhase>any());
    verify(dbcExecutionContext, atLeast(1))
        .invalidateContext(isA(DBRProgressMonitor.class), Mockito.<DBCInvalidatePhase>any());
    verify(dbsInstance2, atLeast(1)).getAllContexts();
    verify(dbsInstance, atLeast(1)).getAllContexts();
    verify(dbsInstance2, atLeast(1)).getExclusiveLock();
    verify(dbsInstance, atLeast(1)).getExclusiveLock();
    verify(dbpDataSource, atLeast(1)).getAvailableInstances();
    assertEquals(3, actualInvalidateDataSourceResult.size());
    ContextInvalidateResult getResult = actualInvalidateDataSourceResult.get(2);
    assertNull(getResult.getException());
    assertFalse(getResult.isError());
    assertTrue(getResult.isSuccess());
  }

  /**
   * Test {@link InvalidateJob#invalidateDataSource(DBRProgressMonitor, DBPDataSource, boolean,
   * boolean, InvalidationFeedbackHandler)}.
   *
   * <ul>
   *   <li>When {@code Object}.
   *   <li>Then return size is two.
   * </ul>
   *
   * <p>Method under test: {@link InvalidateJob#invalidateDataSource(DBRProgressMonitor,
   * DBPDataSource, boolean, boolean, InvalidationFeedbackHandler)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List InvalidateJob.invalidateDataSource(DBRProgressMonitor, DBPDataSource, boolean, boolean, InvalidationFeedbackHandler)"
  })
  public void testInvalidateDataSource_whenJavaLangObject_thenReturnSizeIsTwo() throws DBException {
    // Arrange
    Class<Object> forClass = Object.class;
    LoggingProgressMonitor monitor = new LoggingProgressMonitor(Log.getLog(forClass));

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getId()).thenReturn("42");
    when(dbpDataSourceContainer.getName()).thenReturn("Name");
    when(dbpDataSourceContainer.getActiveNetworkHandlers())
        .thenReturn(new DBWNetworkHandler[] {new HTTPTunnelImpl()});

    DBCExecutionContext dbcExecutionContext = mock(DBCExecutionContext.class);
    when(dbcExecutionContext.getContextName()).thenReturn("Context Name");
    doNothing()
        .when(dbcExecutionContext)
        .invalidateContext(Mockito.<DBRProgressMonitor>any(), Mockito.<DBCInvalidatePhase>any());

    DBSInstance dbsInstance = mock(DBSInstance.class);
    when(dbsInstance.getExclusiveLock()).thenReturn(new SimpleExclusiveLock());
    when(dbsInstance.getAllContexts()).thenReturn(new DBCExecutionContext[] {dbcExecutionContext});

    ArrayList<DBSInstance> dbsInstanceList = new ArrayList<>();
    dbsInstanceList.add(dbsInstance);

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    Mockito.<Collection<? extends DBSInstance>>when(dbpDataSource.getAvailableInstances())
        .thenReturn(dbsInstanceList);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBPDataSourceContainer dbpDataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer2.getDataSource()).thenReturn(dbpDataSource);
    when(dbpDataSourceContainer2.getName()).thenReturn("Name");
    when(dbpDataSourceContainer2.getExclusiveLock()).thenReturn(new SimpleExclusiveLock());
    when(dbpDataSourceContainer2.getActiveNetworkHandlers())
        .thenReturn(new DBWNetworkHandler[] {new HTTPTunnelImpl()});

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getContainer()).thenReturn(dbpDataSourceContainer2);

    // Act
    List<ContextInvalidateResult> actualInvalidateDataSourceResult =
        InvalidateJob.invalidateDataSource(
            monitor, dataSource, true, true, new DefaultInvalidationFeedbackHandler());

    // Assert
    verify(dataSource).getContainer();
    verify(dbpDataSource, atLeast(1)).getContainer();
    verify(dbpDataSourceContainer2).getActiveNetworkHandlers();
    verify(dbpDataSourceContainer, atLeast(1)).getActiveNetworkHandlers();
    verify(dbpDataSourceContainer2, atLeast(1)).getDataSource();
    verify(dbpDataSourceContainer2, atLeast(1)).getExclusiveLock();
    verify(dbpDataSourceContainer, atLeast(1)).getId();
    verify(dbpDataSourceContainer2).getName();
    verify(dbpDataSourceContainer, atLeast(1)).getName();
    verify(dbcExecutionContext, atLeast(1)).getContextName();
    verify(dbcExecutionContext, atLeast(1))
        .invalidateContext(isA(DBRProgressMonitor.class), Mockito.<DBCInvalidatePhase>any());
    verify(dbsInstance, atLeast(1)).getAllContexts();
    verify(dbsInstance, atLeast(1)).getExclusiveLock();
    verify(dbpDataSource, atLeast(1)).getAvailableInstances();
    assertEquals(2, actualInvalidateDataSourceResult.size());
    ContextInvalidateResult getResult = actualInvalidateDataSourceResult.get(1);
    assertNull(getResult.getException());
    assertFalse(getResult.isError());
    assertTrue(getResult.isSuccess());
  }

  /**
   * Test {@link InvalidateJob#invalidateDataSource(DBRProgressMonitor, DBPDataSource, boolean,
   * boolean, InvalidationFeedbackHandler)}.
   *
   * <ul>
   *   <li>When {@link LoggingProgressMonitor#LoggingProgressMonitor()}.
   *   <li>Then return size is two.
   * </ul>
   *
   * <p>Method under test: {@link InvalidateJob#invalidateDataSource(DBRProgressMonitor,
   * DBPDataSource, boolean, boolean, InvalidationFeedbackHandler)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List InvalidateJob.invalidateDataSource(DBRProgressMonitor, DBPDataSource, boolean, boolean, InvalidationFeedbackHandler)"
  })
  public void testInvalidateDataSource_whenLoggingProgressMonitor_thenReturnSizeIsTwo()
      throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getId()).thenReturn("42");
    when(dbpDataSourceContainer.getName()).thenReturn("Name");
    when(dbpDataSourceContainer.getActiveNetworkHandlers())
        .thenReturn(new DBWNetworkHandler[] {new HTTPTunnelImpl()});

    DBCExecutionContext dbcExecutionContext = mock(DBCExecutionContext.class);
    when(dbcExecutionContext.getContextName()).thenReturn("Context Name");
    doNothing()
        .when(dbcExecutionContext)
        .invalidateContext(Mockito.<DBRProgressMonitor>any(), Mockito.<DBCInvalidatePhase>any());

    DBSInstance dbsInstance = mock(DBSInstance.class);
    when(dbsInstance.getExclusiveLock()).thenReturn(new SimpleExclusiveLock());
    when(dbsInstance.getAllContexts()).thenReturn(new DBCExecutionContext[] {dbcExecutionContext});

    ArrayList<DBSInstance> dbsInstanceList = new ArrayList<>();
    dbsInstanceList.add(dbsInstance);

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    Mockito.<Collection<? extends DBSInstance>>when(dbpDataSource.getAvailableInstances())
        .thenReturn(dbsInstanceList);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBPDataSourceContainer dbpDataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer2.getDataSource()).thenReturn(dbpDataSource);
    when(dbpDataSourceContainer2.getName()).thenReturn("Name");
    when(dbpDataSourceContainer2.getExclusiveLock()).thenReturn(new SimpleExclusiveLock());
    when(dbpDataSourceContainer2.getActiveNetworkHandlers())
        .thenReturn(new DBWNetworkHandler[] {new HTTPTunnelImpl()});

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getContainer()).thenReturn(dbpDataSourceContainer2);

    // Act
    List<ContextInvalidateResult> actualInvalidateDataSourceResult =
        InvalidateJob.invalidateDataSource(
            monitor, dataSource, true, true, new DefaultInvalidationFeedbackHandler());

    // Assert
    verify(dataSource).getContainer();
    verify(dbpDataSource, atLeast(1)).getContainer();
    verify(dbpDataSourceContainer2).getActiveNetworkHandlers();
    verify(dbpDataSourceContainer, atLeast(1)).getActiveNetworkHandlers();
    verify(dbpDataSourceContainer2, atLeast(1)).getDataSource();
    verify(dbpDataSourceContainer2, atLeast(1)).getExclusiveLock();
    verify(dbpDataSourceContainer, atLeast(1)).getId();
    verify(dbpDataSourceContainer2).getName();
    verify(dbpDataSourceContainer, atLeast(1)).getName();
    verify(dbcExecutionContext, atLeast(1)).getContextName();
    verify(dbcExecutionContext, atLeast(1))
        .invalidateContext(isA(DBRProgressMonitor.class), Mockito.<DBCInvalidatePhase>any());
    verify(dbsInstance, atLeast(1)).getAllContexts();
    verify(dbsInstance, atLeast(1)).getExclusiveLock();
    verify(dbpDataSource, atLeast(1)).getAvailableInstances();
    assertEquals(2, actualInvalidateDataSourceResult.size());
    ContextInvalidateResult getResult = actualInvalidateDataSourceResult.get(1);
    assertNull(getResult.getException());
    assertFalse(getResult.isError());
    assertTrue(getResult.isSuccess());
  }

  /**
   * Test {@link InvalidateJob#invalidateDataSource(DBRProgressMonitor, DBPDataSource, boolean,
   * boolean, InvalidationFeedbackHandler)}.
   *
   * <ul>
   *   <li>When {@link VoidProgressMonitor} (default constructor).
   *   <li>Then return size is two.
   * </ul>
   *
   * <p>Method under test: {@link InvalidateJob#invalidateDataSource(DBRProgressMonitor,
   * DBPDataSource, boolean, boolean, InvalidationFeedbackHandler)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List InvalidateJob.invalidateDataSource(DBRProgressMonitor, DBPDataSource, boolean, boolean, InvalidationFeedbackHandler)"
  })
  public void testInvalidateDataSource_whenVoidProgressMonitor_thenReturnSizeIsTwo()
      throws DBException {
    // Arrange
    VoidProgressMonitor monitor = new VoidProgressMonitor();

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getId()).thenReturn("42");
    when(dbpDataSourceContainer.getName()).thenReturn("Name");
    when(dbpDataSourceContainer.getActiveNetworkHandlers())
        .thenReturn(new DBWNetworkHandler[] {new HTTPTunnelImpl()});

    DBCExecutionContext dbcExecutionContext = mock(DBCExecutionContext.class);
    when(dbcExecutionContext.getContextName()).thenReturn("Context Name");
    doNothing()
        .when(dbcExecutionContext)
        .invalidateContext(Mockito.<DBRProgressMonitor>any(), Mockito.<DBCInvalidatePhase>any());

    DBSInstance dbsInstance = mock(DBSInstance.class);
    when(dbsInstance.getExclusiveLock()).thenReturn(new SimpleExclusiveLock());
    when(dbsInstance.getAllContexts()).thenReturn(new DBCExecutionContext[] {dbcExecutionContext});

    ArrayList<DBSInstance> dbsInstanceList = new ArrayList<>();
    dbsInstanceList.add(dbsInstance);

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    Mockito.<Collection<? extends DBSInstance>>when(dbpDataSource.getAvailableInstances())
        .thenReturn(dbsInstanceList);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBPDataSourceContainer dbpDataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer2.getDataSource()).thenReturn(dbpDataSource);
    when(dbpDataSourceContainer2.getName()).thenReturn("Name");
    when(dbpDataSourceContainer2.getExclusiveLock()).thenReturn(new SimpleExclusiveLock());
    when(dbpDataSourceContainer2.getActiveNetworkHandlers())
        .thenReturn(new DBWNetworkHandler[] {new HTTPTunnelImpl()});

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getContainer()).thenReturn(dbpDataSourceContainer2);

    // Act
    List<ContextInvalidateResult> actualInvalidateDataSourceResult =
        InvalidateJob.invalidateDataSource(
            monitor, dataSource, true, true, new DefaultInvalidationFeedbackHandler());

    // Assert
    verify(dataSource).getContainer();
    verify(dbpDataSource, atLeast(1)).getContainer();
    verify(dbpDataSourceContainer2).getActiveNetworkHandlers();
    verify(dbpDataSourceContainer, atLeast(1)).getActiveNetworkHandlers();
    verify(dbpDataSourceContainer2, atLeast(1)).getDataSource();
    verify(dbpDataSourceContainer2, atLeast(1)).getExclusiveLock();
    verify(dbpDataSourceContainer, atLeast(1)).getId();
    verify(dbpDataSourceContainer2).getName();
    verify(dbpDataSourceContainer, atLeast(1)).getName();
    verify(dbcExecutionContext, atLeast(1)).getContextName();
    verify(dbcExecutionContext, atLeast(1))
        .invalidateContext(isA(DBRProgressMonitor.class), Mockito.<DBCInvalidatePhase>any());
    verify(dbsInstance, atLeast(1)).getAllContexts();
    verify(dbsInstance, atLeast(1)).getExclusiveLock();
    verify(dbpDataSource, atLeast(1)).getAvailableInstances();
    assertEquals(2, actualInvalidateDataSourceResult.size());
    ContextInvalidateResult getResult = actualInvalidateDataSourceResult.get(1);
    assertNull(getResult.getException());
    assertFalse(getResult.isError());
    assertTrue(getResult.isSuccess());
  }

  /**
   * Test {@link InvalidateJob#invalidateTransaction(DBRProgressMonitor, DBCExecutionContext)} with
   * {@code monitor}, {@code context}.
   *
   * <ul>
   *   <li>Given {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link InvalidateJob#invalidateTransaction(DBRProgressMonitor,
   * DBCExecutionContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void InvalidateJob.invalidateTransaction(DBRProgressMonitor, DBCExecutionContext)"
  })
  public void testInvalidateTransactionWithMonitorContext_givenFalse() {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBCExecutionContext context = mock(DBCExecutionContext.class);
    when(context.isConnected()).thenReturn(false);

    // Act
    InvalidateJob.invalidateTransaction(monitor, context);

    // Assert
    verify(context).isConnected();
  }

  /**
   * Test {@link InvalidateJob#invalidateTransaction(DBRProgressMonitor, DBCExecutionContext)} with
   * {@code monitor}, {@code context}.
   *
   * <ul>
   *   <li>Given {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link InvalidateJob#invalidateTransaction(DBRProgressMonitor,
   * DBCExecutionContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void InvalidateJob.invalidateTransaction(DBRProgressMonitor, DBCExecutionContext)"
  })
  public void testInvalidateTransactionWithMonitorContext_givenTrue() {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBCExecutionContext context = mock(DBCExecutionContext.class);
    when(context.isConnected()).thenReturn(true);

    // Act
    InvalidateJob.invalidateTransaction(monitor, context);

    // Assert
    verify(context).isConnected();
  }

  /**
   * Test {@link InvalidateJob#invalidateTransaction(DBRProgressMonitor, DBPDataSource,
   * DBCExecutionContext)} with {@code monitor}, {@code dataSource}, {@code executionContext}.
   *
   * <p>Method under test: {@link InvalidateJob#invalidateTransaction(DBRProgressMonitor,
   * DBPDataSource, DBCExecutionContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void InvalidateJob.invalidateTransaction(DBRProgressMonitor, DBPDataSource, DBCExecutionContext)"
  })
  public void testInvalidateTransactionWithMonitorDataSourceExecutionContext() {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBPDataSource dataSource = mock(DBPDataSource.class);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getName()).thenReturn("Name");

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBCExecutionContext executionContext = mock(DBCExecutionContext.class);
    when(executionContext.isConnected()).thenReturn(true);
    when(executionContext.getContextName()).thenReturn("Context Name");
    when(executionContext.getDataSource()).thenReturn(dbpDataSource);

    // Act
    InvalidateJob.invalidateTransaction(monitor, dataSource, executionContext);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).getName();
    verify(executionContext).getContextName();
    verify(executionContext).getDataSource();
    verify(executionContext).isConnected();
  }

  /**
   * Test {@link InvalidateJob#invalidateTransaction(DBRProgressMonitor, DBPDataSource,
   * DBCExecutionContext)} with {@code monitor}, {@code dataSource}, {@code executionContext}.
   *
   * <p>Method under test: {@link InvalidateJob#invalidateTransaction(DBRProgressMonitor,
   * DBPDataSource, DBCExecutionContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void InvalidateJob.invalidateTransaction(DBRProgressMonitor, DBPDataSource, DBCExecutionContext)"
  })
  public void testInvalidateTransactionWithMonitorDataSourceExecutionContext2() {
    // Arrange
    Class<Object> forClass = Object.class;
    LoggingProgressMonitor monitor = new LoggingProgressMonitor(Log.getLog(forClass));
    DBPDataSource dataSource = mock(DBPDataSource.class);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getName()).thenReturn("Name");

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBCExecutionContext executionContext = mock(DBCExecutionContext.class);
    when(executionContext.isConnected()).thenReturn(true);
    when(executionContext.getContextName()).thenReturn("Context Name");
    when(executionContext.getDataSource()).thenReturn(dbpDataSource);

    // Act
    InvalidateJob.invalidateTransaction(monitor, dataSource, executionContext);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).getName();
    verify(executionContext).getContextName();
    verify(executionContext).getDataSource();
    verify(executionContext).isConnected();
  }

  /**
   * Test {@link InvalidateJob#invalidateTransaction(DBRProgressMonitor, DBPDataSource,
   * DBCExecutionContext)} with {@code monitor}, {@code dataSource}, {@code executionContext}.
   *
   * <p>Method under test: {@link InvalidateJob#invalidateTransaction(DBRProgressMonitor,
   * DBPDataSource, DBCExecutionContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void InvalidateJob.invalidateTransaction(DBRProgressMonitor, DBPDataSource, DBCExecutionContext)"
  })
  public void testInvalidateTransactionWithMonitorDataSourceExecutionContext3() {
    // Arrange
    VoidProgressMonitor monitor = new VoidProgressMonitor();
    DBPDataSource dataSource = mock(DBPDataSource.class);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getName()).thenReturn("Name");

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBCExecutionContext executionContext = mock(DBCExecutionContext.class);
    when(executionContext.isConnected()).thenReturn(true);
    when(executionContext.getContextName()).thenReturn("Context Name");
    when(executionContext.getDataSource()).thenReturn(dbpDataSource);

    // Act
    InvalidateJob.invalidateTransaction(monitor, dataSource, executionContext);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).getName();
    verify(executionContext).getContextName();
    verify(executionContext).getDataSource();
    verify(executionContext).isConnected();
  }

  /**
   * Test {@link InvalidateJob#invalidateTransaction(DBRProgressMonitor, DBPDataSource,
   * DBCExecutionContext)} with {@code monitor}, {@code dataSource}, {@code executionContext}.
   *
   * <p>Method under test: {@link InvalidateJob#invalidateTransaction(DBRProgressMonitor,
   * DBPDataSource, DBCExecutionContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void InvalidateJob.invalidateTransaction(DBRProgressMonitor, DBPDataSource, DBCExecutionContext)"
  })
  public void testInvalidateTransactionWithMonitorDataSourceExecutionContext4() {
    // Arrange
    DefaultProgressMonitor monitor =
        new DefaultProgressMonitor(new LocalCacheProgressMonitor(new LoggingProgressMonitor()));
    DBPDataSource dataSource = mock(DBPDataSource.class);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getName()).thenReturn("Name");

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBCExecutionContext executionContext = mock(DBCExecutionContext.class);
    when(executionContext.isConnected()).thenReturn(true);
    when(executionContext.getContextName()).thenReturn("Progress sub task without start");
    when(executionContext.getDataSource()).thenReturn(dbpDataSource);

    // Act
    InvalidateJob.invalidateTransaction(monitor, dataSource, executionContext);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).getName();
    verify(executionContext).getContextName();
    verify(executionContext).getDataSource();
    verify(executionContext).isConnected();
  }

  /**
   * Test {@link InvalidateJob#invalidateTransaction(DBRProgressMonitor, DBPDataSource,
   * DBCExecutionContext)} with {@code monitor}, {@code dataSource}, {@code executionContext}.
   *
   * <ul>
   *   <li>Given {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link InvalidateJob#invalidateTransaction(DBRProgressMonitor,
   * DBPDataSource, DBCExecutionContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void InvalidateJob.invalidateTransaction(DBRProgressMonitor, DBPDataSource, DBCExecutionContext)"
  })
  public void testInvalidateTransactionWithMonitorDataSourceExecutionContext_givenFalse() {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBPDataSource dataSource = mock(DBPDataSource.class);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getName()).thenReturn("Name");

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBCExecutionContext executionContext = mock(DBCExecutionContext.class);
    when(executionContext.isConnected()).thenReturn(false);
    when(executionContext.getContextName()).thenReturn("Context Name");
    when(executionContext.getDataSource()).thenReturn(dbpDataSource);

    // Act
    InvalidateJob.invalidateTransaction(monitor, dataSource, executionContext);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).getName();
    verify(executionContext).getContextName();
    verify(executionContext).getDataSource();
    verify(executionContext).isConnected();
  }

  /**
   * Test {@link InvalidateJob#allSucceeded(Collection)}.
   *
   * <ul>
   *   <li>Given newError {@link DBPDataSource} and {@link Exception#Exception()}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link InvalidateJob#allSucceeded(Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean InvalidateJob.allSucceeded(Collection)"})
  public void testAllSucceeded_givenNewErrorDBPDataSourceAndException_thenReturnFalse() {
    // Arrange
    ArrayList<ContextInvalidateResult> results = new ArrayList<>();
    DBPDataSource dataSource = mock(DBPDataSource.class);
    results.add(ContextInvalidateResult.newError(dataSource, new Exception()));

    // Act and Assert
    assertFalse(InvalidateJob.allSucceeded(results));
  }

  /**
   * Test {@link InvalidateJob#allSucceeded(Collection)}.
   *
   * <ul>
   *   <li>Given newSuccess {@link DBPDataSource}.
   * </ul>
   *
   * <p>Method under test: {@link InvalidateJob#allSucceeded(Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean InvalidateJob.allSucceeded(Collection)"})
  public void testAllSucceeded_givenNewSuccessDBPDataSource() {
    // Arrange
    ArrayList<ContextInvalidateResult> results = new ArrayList<>();
    results.add(ContextInvalidateResult.newSuccess(mock(DBPDataSource.class)));

    // Act and Assert
    assertTrue(InvalidateJob.allSucceeded(results));
  }

  /**
   * Test {@link InvalidateJob#allSucceeded(Collection)}.
   *
   * <ul>
   *   <li>Given newSuccess {@link DBPDataSource}.
   * </ul>
   *
   * <p>Method under test: {@link InvalidateJob#allSucceeded(Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean InvalidateJob.allSucceeded(Collection)"})
  public void testAllSucceeded_givenNewSuccessDBPDataSource2() {
    // Arrange
    ArrayList<ContextInvalidateResult> results = new ArrayList<>();
    results.add(ContextInvalidateResult.newSuccess(mock(DBPDataSource.class)));
    results.add(ContextInvalidateResult.newSuccess(mock(DBPDataSource.class)));

    // Act and Assert
    assertTrue(InvalidateJob.allSucceeded(results));
  }

  /**
   * Test {@link InvalidateJob#allSucceeded(Collection)}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link InvalidateJob#allSucceeded(Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean InvalidateJob.allSucceeded(Collection)"})
  public void testAllSucceeded_whenArrayList_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(InvalidateJob.allSucceeded(new ArrayList<>()));
  }

  /**
   * Test {@link InvalidateJob#anySucceeded(Collection)}.
   *
   * <ul>
   *   <li>Given newError {@link DBPDataSource} and {@link Exception#Exception()}.
   * </ul>
   *
   * <p>Method under test: {@link InvalidateJob#anySucceeded(Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean InvalidateJob.anySucceeded(Collection)"})
  public void testAnySucceeded_givenNewErrorDBPDataSourceAndException() {
    // Arrange
    ArrayList<ContextInvalidateResult> results = new ArrayList<>();
    DBPDataSource dataSource = mock(DBPDataSource.class);
    results.add(ContextInvalidateResult.newError(dataSource, new Exception()));
    results.add(ContextInvalidateResult.newSuccess(mock(DBPDataSource.class)));

    // Act and Assert
    assertTrue(InvalidateJob.anySucceeded(results));
  }

  /**
   * Test {@link InvalidateJob#anySucceeded(Collection)}.
   *
   * <ul>
   *   <li>Given newError {@link DBPDataSource} and {@link Exception#Exception()}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link InvalidateJob#anySucceeded(Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean InvalidateJob.anySucceeded(Collection)"})
  public void testAnySucceeded_givenNewErrorDBPDataSourceAndException_thenReturnFalse() {
    // Arrange
    ArrayList<ContextInvalidateResult> results = new ArrayList<>();
    DBPDataSource dataSource = mock(DBPDataSource.class);
    results.add(ContextInvalidateResult.newError(dataSource, new Exception()));

    // Act and Assert
    assertFalse(InvalidateJob.anySucceeded(results));
  }

  /**
   * Test {@link InvalidateJob#anySucceeded(Collection)}.
   *
   * <ul>
   *   <li>Given newSuccess {@link DBPDataSource}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link InvalidateJob#anySucceeded(Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean InvalidateJob.anySucceeded(Collection)"})
  public void testAnySucceeded_givenNewSuccessDBPDataSource_thenReturnTrue() {
    // Arrange
    ArrayList<ContextInvalidateResult> results = new ArrayList<>();
    results.add(ContextInvalidateResult.newSuccess(mock(DBPDataSource.class)));

    // Act and Assert
    assertTrue(InvalidateJob.anySucceeded(results));
  }

  /**
   * Test {@link InvalidateJob#anySucceeded(Collection)}.
   *
   * <ul>
   *   <li>Given newSuccess {@link DBPDataSource}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link InvalidateJob#anySucceeded(Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean InvalidateJob.anySucceeded(Collection)"})
  public void testAnySucceeded_givenNewSuccessDBPDataSource_thenReturnTrue2() {
    // Arrange
    ArrayList<ContextInvalidateResult> results = new ArrayList<>();
    results.add(ContextInvalidateResult.newSuccess(mock(DBPDataSource.class)));
    results.add(ContextInvalidateResult.newSuccess(mock(DBPDataSource.class)));

    // Act and Assert
    assertTrue(InvalidateJob.anySucceeded(results));
  }

  /**
   * Test {@link InvalidateJob#anySucceeded(Collection)}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link InvalidateJob#anySucceeded(Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean InvalidateJob.anySucceeded(Collection)"})
  public void testAnySucceeded_whenArrayList_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(InvalidateJob.anySucceeded(new ArrayList<>()));
  }

  /**
   * Test {@link InvalidateJob#allFailed(Collection)}.
   *
   * <ul>
   *   <li>Given newError {@link DBPDataSource} and {@link Exception#Exception()}.
   * </ul>
   *
   * <p>Method under test: {@link InvalidateJob#allFailed(Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean InvalidateJob.allFailed(Collection)"})
  public void testAllFailed_givenNewErrorDBPDataSourceAndException() {
    // Arrange
    ArrayList<ContextInvalidateResult> results = new ArrayList<>();
    DBPDataSource dataSource = mock(DBPDataSource.class);
    results.add(ContextInvalidateResult.newError(dataSource, new Exception()));
    results.add(ContextInvalidateResult.newSuccess(mock(DBPDataSource.class)));

    // Act and Assert
    assertFalse(InvalidateJob.allFailed(results));
  }

  /**
   * Test {@link InvalidateJob#allFailed(Collection)}.
   *
   * <ul>
   *   <li>Given newError {@link DBPDataSource} and {@link Exception#Exception()}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link InvalidateJob#allFailed(Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean InvalidateJob.allFailed(Collection)"})
  public void testAllFailed_givenNewErrorDBPDataSourceAndException_thenReturnTrue() {
    // Arrange
    ArrayList<ContextInvalidateResult> results = new ArrayList<>();
    DBPDataSource dataSource = mock(DBPDataSource.class);
    results.add(ContextInvalidateResult.newError(dataSource, new Exception()));

    // Act and Assert
    assertTrue(InvalidateJob.allFailed(results));
  }

  /**
   * Test {@link InvalidateJob#allFailed(Collection)}.
   *
   * <ul>
   *   <li>Given newSuccess {@link DBPDataSource}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link InvalidateJob#allFailed(Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean InvalidateJob.allFailed(Collection)"})
  public void testAllFailed_givenNewSuccessDBPDataSource_thenReturnFalse() {
    // Arrange
    ArrayList<ContextInvalidateResult> results = new ArrayList<>();
    results.add(ContextInvalidateResult.newSuccess(mock(DBPDataSource.class)));

    // Act and Assert
    assertFalse(InvalidateJob.allFailed(results));
  }

  /**
   * Test {@link InvalidateJob#allFailed(Collection)}.
   *
   * <ul>
   *   <li>Given newSuccess {@link DBPDataSource}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link InvalidateJob#allFailed(Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean InvalidateJob.allFailed(Collection)"})
  public void testAllFailed_givenNewSuccessDBPDataSource_thenReturnFalse2() {
    // Arrange
    ArrayList<ContextInvalidateResult> results = new ArrayList<>();
    results.add(ContextInvalidateResult.newSuccess(mock(DBPDataSource.class)));
    results.add(ContextInvalidateResult.newSuccess(mock(DBPDataSource.class)));

    // Act and Assert
    assertFalse(InvalidateJob.allFailed(results));
  }

  /**
   * Test {@link InvalidateJob#allFailed(Collection)}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link InvalidateJob#allFailed(Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean InvalidateJob.allFailed(Collection)"})
  public void testAllFailed_whenArrayList_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(InvalidateJob.allFailed(new ArrayList<>()));
  }

  /**
   * Test {@link InvalidateJob#anyFailed(Collection)}.
   *
   * <ul>
   *   <li>Given newError {@link DBPDataSource} and {@link Exception#Exception()}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link InvalidateJob#anyFailed(Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean InvalidateJob.anyFailed(Collection)"})
  public void testAnyFailed_givenNewErrorDBPDataSourceAndException_thenReturnTrue() {
    // Arrange
    ArrayList<ContextInvalidateResult> results = new ArrayList<>();
    DBPDataSource dataSource = mock(DBPDataSource.class);
    results.add(ContextInvalidateResult.newError(dataSource, new Exception()));

    // Act and Assert
    assertTrue(InvalidateJob.anyFailed(results));
  }

  /**
   * Test {@link InvalidateJob#anyFailed(Collection)}.
   *
   * <ul>
   *   <li>Given newSuccess {@link DBPDataSource}.
   * </ul>
   *
   * <p>Method under test: {@link InvalidateJob#anyFailed(Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean InvalidateJob.anyFailed(Collection)"})
  public void testAnyFailed_givenNewSuccessDBPDataSource() {
    // Arrange
    ArrayList<ContextInvalidateResult> results = new ArrayList<>();
    results.add(ContextInvalidateResult.newSuccess(mock(DBPDataSource.class)));

    // Act and Assert
    assertFalse(InvalidateJob.anyFailed(results));
  }

  /**
   * Test {@link InvalidateJob#anyFailed(Collection)}.
   *
   * <ul>
   *   <li>Given newSuccess {@link DBPDataSource}.
   * </ul>
   *
   * <p>Method under test: {@link InvalidateJob#anyFailed(Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean InvalidateJob.anyFailed(Collection)"})
  public void testAnyFailed_givenNewSuccessDBPDataSource2() {
    // Arrange
    ArrayList<ContextInvalidateResult> results = new ArrayList<>();
    results.add(ContextInvalidateResult.newSuccess(mock(DBPDataSource.class)));
    results.add(ContextInvalidateResult.newSuccess(mock(DBPDataSource.class)));

    // Act and Assert
    assertFalse(InvalidateJob.anyFailed(results));
  }

  /**
   * Test {@link InvalidateJob#anyFailed(Collection)}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link InvalidateJob#anyFailed(Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean InvalidateJob.anyFailed(Collection)"})
  public void testAnyFailed_whenArrayList_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(InvalidateJob.anyFailed(new ArrayList<>()));
  }

  /**
   * Test {@link InvalidateJob#getSucceededCount(Collection)}.
   *
   * <ul>
   *   <li>Given newError {@link DBPDataSource} and {@link Exception#Exception()}.
   * </ul>
   *
   * <p>Method under test: {@link InvalidateJob#getSucceededCount(Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int InvalidateJob.getSucceededCount(Collection)"})
  public void testGetSucceededCount_givenNewErrorDBPDataSourceAndException() {
    // Arrange
    ArrayList<ContextInvalidateResult> results = new ArrayList<>();
    DBPDataSource dataSource = mock(DBPDataSource.class);
    results.add(ContextInvalidateResult.newError(dataSource, new Exception()));

    // Act and Assert
    assertEquals(0, InvalidateJob.getSucceededCount(results));
  }

  /**
   * Test {@link InvalidateJob#getSucceededCount(Collection)}.
   *
   * <ul>
   *   <li>Given newSuccess {@link DBPDataSource}.
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link InvalidateJob#getSucceededCount(Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int InvalidateJob.getSucceededCount(Collection)"})
  public void testGetSucceededCount_givenNewSuccessDBPDataSource_thenReturnOne() {
    // Arrange
    ArrayList<ContextInvalidateResult> results = new ArrayList<>();
    results.add(ContextInvalidateResult.newSuccess(mock(DBPDataSource.class)));

    // Act and Assert
    assertEquals(1, InvalidateJob.getSucceededCount(results));
  }

  /**
   * Test {@link InvalidateJob#getSucceededCount(Collection)}.
   *
   * <ul>
   *   <li>Given newSuccess {@link DBPDataSource}.
   *   <li>Then return two.
   * </ul>
   *
   * <p>Method under test: {@link InvalidateJob#getSucceededCount(Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int InvalidateJob.getSucceededCount(Collection)"})
  public void testGetSucceededCount_givenNewSuccessDBPDataSource_thenReturnTwo() {
    // Arrange
    ArrayList<ContextInvalidateResult> results = new ArrayList<>();
    results.add(ContextInvalidateResult.newSuccess(mock(DBPDataSource.class)));
    results.add(ContextInvalidateResult.newSuccess(mock(DBPDataSource.class)));

    // Act and Assert
    assertEquals(2, InvalidateJob.getSucceededCount(results));
  }

  /**
   * Test {@link InvalidateJob#getSucceededCount(Collection)}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link InvalidateJob#getSucceededCount(Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int InvalidateJob.getSucceededCount(Collection)"})
  public void testGetSucceededCount_whenArrayList_thenReturnZero() {
    // Arrange, Act and Assert
    assertEquals(0, InvalidateJob.getSucceededCount(new ArrayList<>()));
  }
}
