package org.jkiss.dbeaver.model.qm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.model.app.DBPProject;
import org.jkiss.dbeaver.model.app.DBPWorkspace;
import org.jkiss.dbeaver.model.auth.SMAuthSpace;
import org.jkiss.dbeaver.model.auth.SMSession;
import org.jkiss.dbeaver.model.auth.SMSessionContext;
import org.jkiss.dbeaver.model.exec.DBCExecutionContext;
import org.jkiss.dbeaver.model.impl.app.LocalWorkspaceSession;
import org.jkiss.dbeaver.model.impl.auth.SessionContextImpl;
import org.jkiss.dbeaver.model.impl.preferences.BundlePreferenceStore;
import org.jkiss.dbeaver.model.preferences.DBPPreferenceStore;
import org.jkiss.dbeaver.model.qm.QMUtils.EmptyCursorImpl;
import org.jkiss.dbeaver.model.qm.QMUtils.ListCursorImpl;
import org.jkiss.dbeaver.model.qm.meta.QMMObject;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class QMUtilsDiffblueTest {
  /**
   * Test EmptyCursorImpl getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link EmptyCursorImpl}
   *   <li>{@link EmptyCursorImpl#close()}
   *   <li>{@link EmptyCursorImpl#getTotalSize()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void EmptyCursorImpl.<init>()",
    "void EmptyCursorImpl.close()",
    "long EmptyCursorImpl.getTotalSize()"
  })
  public void testEmptyCursorImplGettersAndSetters() {
    // Arrange and Act
    EmptyCursorImpl actualEmptyCursorImpl = new EmptyCursorImpl();
    actualEmptyCursorImpl.close();

    // Assert
    assertEquals(0L, actualEmptyCursorImpl.getTotalSize());
  }

  /**
   * Test EmptyCursorImpl {@link EmptyCursorImpl#hasNextEvent(DBRProgressMonitor)}.
   *
   * <p>Method under test: {@link EmptyCursorImpl#hasNextEvent(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean EmptyCursorImpl.hasNextEvent(DBRProgressMonitor)"})
  public void testEmptyCursorImplHasNextEvent() throws DBException {
    // Arrange
    EmptyCursorImpl emptyCursorImpl = new EmptyCursorImpl();

    // Act and Assert
    assertFalse(emptyCursorImpl.hasNextEvent(new LoggingProgressMonitor()));
  }

  /**
   * Test EmptyCursorImpl {@link EmptyCursorImpl#nextEvent(DBRProgressMonitor)}.
   *
   * <p>Method under test: {@link EmptyCursorImpl#nextEvent(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"QMMetaEventEntity EmptyCursorImpl.nextEvent(DBRProgressMonitor)"})
  public void testEmptyCursorImplNextEvent() throws DBException {
    // Arrange
    EmptyCursorImpl emptyCursorImpl = new EmptyCursorImpl();

    // Act and Assert
    assertThrows(DBException.class, () -> emptyCursorImpl.nextEvent(new LoggingProgressMonitor()));
  }

  /**
   * Test EmptyCursorImpl {@link EmptyCursorImpl#scroll(int, DBRProgressMonitor)}.
   *
   * <p>Method under test: {@link EmptyCursorImpl#scroll(int, DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void EmptyCursorImpl.scroll(int, DBRProgressMonitor)"})
  public void testEmptyCursorImplScroll() throws DBException {
    // Arrange
    EmptyCursorImpl emptyCursorImpl = new EmptyCursorImpl();

    // Act and Assert
    assertThrows(DBException.class, () -> emptyCursorImpl.scroll(1, new LoggingProgressMonitor()));
  }

  /**
   * Test {@link QMUtils#getEventBrowser(boolean)}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link QMUtils#getEventBrowser(boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"QMEventBrowser QMUtils.getEventBrowser(boolean)"})
  public void testGetEventBrowser_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(QMUtils.getEventBrowser(true));
  }

  /**
   * Test {@link QMUtils#isTransactionActive(DBCExecutionContext, boolean)} with {@code
   * executionContext}, {@code checkQueries}.
   *
   * <ul>
   *   <li>When {@link DBCExecutionContext}.
   * </ul>
   *
   * <p>Method under test: {@link QMUtils#isTransactionActive(DBCExecutionContext, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean QMUtils.isTransactionActive(DBCExecutionContext, boolean)"})
  public void testIsTransactionActiveWithExecutionContextCheckQueries_whenDBCExecutionContext() {
    // Arrange, Act and Assert
    assertFalse(QMUtils.isTransactionActive(mock(DBCExecutionContext.class), true));
  }

  /**
   * Test {@link QMUtils#isTransactionActive(DBCExecutionContext, boolean)} with {@code
   * executionContext}, {@code checkQueries}.
   *
   * <ul>
   *   <li>When {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link QMUtils#isTransactionActive(DBCExecutionContext, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean QMUtils.isTransactionActive(DBCExecutionContext, boolean)"})
  public void testIsTransactionActiveWithExecutionContextCheckQueries_whenNull() {
    // Arrange, Act and Assert
    assertFalse(QMUtils.isTransactionActive(null, true));
  }

  /**
   * Test {@link QMUtils#isTransactionActive(DBCExecutionContext)} with {@code executionContext}.
   *
   * <ul>
   *   <li>When {@link DBCExecutionContext}.
   * </ul>
   *
   * <p>Method under test: {@link QMUtils#isTransactionActive(DBCExecutionContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean QMUtils.isTransactionActive(DBCExecutionContext)"})
  public void testIsTransactionActiveWithExecutionContext_whenDBCExecutionContext() {
    // Arrange, Act and Assert
    assertFalse(QMUtils.isTransactionActive(mock(DBCExecutionContext.class)));
  }

  /**
   * Test {@link QMUtils#isTransactionActive(DBCExecutionContext)} with {@code executionContext}.
   *
   * <ul>
   *   <li>When {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link QMUtils#isTransactionActive(DBCExecutionContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean QMUtils.isTransactionActive(DBCExecutionContext)"})
  public void testIsTransactionActiveWithExecutionContext_whenNull() {
    // Arrange, Act and Assert
    assertFalse(QMUtils.isTransactionActive(null));
  }

  /**
   * Test {@link QMUtils#getTransactionState(DBCExecutionContext)}.
   *
   * <ul>
   *   <li>When {@link DBCExecutionContext}.
   * </ul>
   *
   * <p>Method under test: {@link QMUtils#getTransactionState(DBCExecutionContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"QMTransactionState QMUtils.getTransactionState(DBCExecutionContext)"})
  public void testGetTransactionState_whenDBCExecutionContext() {
    // Arrange and Act
    QMTransactionState actualTransactionState =
        QMUtils.getTransactionState(mock(DBCExecutionContext.class));

    // Assert
    assertEquals(0, actualTransactionState.getExecuteCount());
    assertEquals(0, actualTransactionState.getUpdateCount());
    assertFalse(actualTransactionState.isTransactionMode());
  }

  /**
   * Test {@link QMUtils#getTransactionState(DBCExecutionContext)}.
   *
   * <ul>
   *   <li>When {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link QMUtils#getTransactionState(DBCExecutionContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"QMTransactionState QMUtils.getTransactionState(DBCExecutionContext)"})
  public void testGetTransactionState_whenNull() {
    // Arrange and Act
    QMTransactionState actualTransactionState = QMUtils.getTransactionState(null);

    // Assert
    assertEquals(0, actualTransactionState.getExecuteCount());
    assertEquals(0, actualTransactionState.getUpdateCount());
    assertFalse(actualTransactionState.isTransactionMode());
  }

  /**
   * Test {@link QMUtils#createDefaultCriteria(DBPPreferenceStore)}.
   *
   * <ul>
   *   <li>Given empty string.
   * </ul>
   *
   * <p>Method under test: {@link QMUtils#createDefaultCriteria(DBPPreferenceStore)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.qm.filters.QMEventCriteria QMUtils.createDefaultCriteria(DBPPreferenceStore)"
  })
  public void testCreateDefaultCriteria_givenEmptyString() {
    // Arrange
    BundlePreferenceStore store = mock(BundlePreferenceStore.class);
    when(store.getString(Mockito.<String>any())).thenReturn("");

    // Act
    QMUtils.createDefaultCriteria(store);

    // Assert
    verify(store, atLeast(1)).getString(Mockito.<String>any());
  }

  /**
   * Test {@link QMUtils#createDefaultCriteria(DBPPreferenceStore)}.
   *
   * <ul>
   *   <li>Given {@code null}.
   *   <li>Then calls {@link DBPPreferenceStore#getString(String)}.
   * </ul>
   *
   * <p>Method under test: {@link QMUtils#createDefaultCriteria(DBPPreferenceStore)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.qm.filters.QMEventCriteria QMUtils.createDefaultCriteria(DBPPreferenceStore)"
  })
  public void testCreateDefaultCriteria_givenNull_thenCallsGetString() {
    // Arrange
    DBPPreferenceStore store = mock(DBPPreferenceStore.class);
    when(store.getString(Mockito.<String>any())).thenReturn(null);

    // Act
    QMUtils.createDefaultCriteria(store);

    // Assert
    verify(store, atLeast(1)).getString(Mockito.<String>any());
  }

  /**
   * Test {@link QMUtils#createDefaultCriteria(DBPPreferenceStore)}.
   *
   * <ul>
   *   <li>Given {@code String}.
   * </ul>
   *
   * <p>Method under test: {@link QMUtils#createDefaultCriteria(DBPPreferenceStore)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.qm.filters.QMEventCriteria QMUtils.createDefaultCriteria(DBPPreferenceStore)"
  })
  public void testCreateDefaultCriteria_givenString() {
    // Arrange
    BundlePreferenceStore store = mock(BundlePreferenceStore.class);
    when(store.getString(Mockito.<String>any())).thenReturn("String");

    // Act
    QMUtils.createDefaultCriteria(store);

    // Assert
    verify(store, atLeast(1)).getString(Mockito.<String>any());
  }

  /**
   * Test {@link QMUtils#getQmSessionId(DBPDataSource)} with {@code dataSource}.
   *
   * <p>Method under test: {@link QMUtils#getQmSessionId(DBPDataSource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String QMUtils.getQmSessionId(DBPDataSource)"})
  public void testGetQmSessionIdWithDataSource() throws DBException {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getProject()).thenThrow(new IllegalArgumentException());

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> QMUtils.getQmSessionId(dataSource));
    verify(dataSource).getContainer();
    verify(dbpDataSourceContainer).getProject();
  }

  /**
   * Test {@link QMUtils#getQmSessionId(DBPDataSource)} with {@code dataSource}.
   *
   * <p>Method under test: {@link QMUtils#getQmSessionId(DBPDataSource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String QMUtils.getQmSessionId(DBPDataSource)"})
  public void testGetQmSessionIdWithDataSource2() throws DBException {
    // Arrange
    DBPProject dbpProject = mock(DBPProject.class);
    when(dbpProject.getSessionContext()).thenThrow(new IllegalArgumentException());

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getProject()).thenReturn(dbpProject);

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> QMUtils.getQmSessionId(dataSource));
    verify(dataSource).getContainer();
    verify(dbpDataSourceContainer).getProject();
    verify(dbpProject).getSessionContext();
  }

  /**
   * Test {@link QMUtils#getQmSessionId(DBPDataSource)} with {@code dataSource}.
   *
   * <p>Method under test: {@link QMUtils#getQmSessionId(DBPDataSource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String QMUtils.getQmSessionId(DBPDataSource)"})
  public void testGetQmSessionIdWithDataSource3() throws DBException {
    // Arrange
    DBPProject dbpProject = mock(DBPProject.class);
    when(dbpProject.getWorkspace()).thenThrow(new IllegalArgumentException());
    when(dbpProject.getSessionContext())
        .thenReturn(new SessionContextImpl(mock(SMSessionContext.class)));

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getProject()).thenReturn(dbpProject);

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> QMUtils.getQmSessionId(dataSource));
    verify(dataSource).getContainer();
    verify(dbpDataSourceContainer).getProject();
    verify(dbpProject).getSessionContext();
    verify(dbpProject).getWorkspace();
  }

  /**
   * Test {@link QMUtils#getQmSessionId(DBPDataSource)} with {@code dataSource}.
   *
   * <p>Method under test: {@link QMUtils#getQmSessionId(DBPDataSource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String QMUtils.getQmSessionId(DBPDataSource)"})
  public void testGetQmSessionIdWithDataSource4() throws DBException {
    // Arrange
    DBPWorkspace dbpWorkspace = mock(DBPWorkspace.class);
    when(dbpWorkspace.getAuthContext()).thenThrow(new IllegalArgumentException());

    DBPProject dbpProject = mock(DBPProject.class);
    when(dbpProject.getWorkspace()).thenReturn(dbpWorkspace);
    when(dbpProject.getSessionContext())
        .thenReturn(new SessionContextImpl(mock(SMSessionContext.class)));

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getProject()).thenReturn(dbpProject);

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> QMUtils.getQmSessionId(dataSource));
    verify(dataSource).getContainer();
    verify(dbpDataSourceContainer).getProject();
    verify(dbpProject).getSessionContext();
    verify(dbpProject).getWorkspace();
    verify(dbpWorkspace).getAuthContext();
  }

  /**
   * Test {@link QMUtils#getQmSessionId(DBPDataSource)} with {@code dataSource}.
   *
   * <p>Method under test: {@link QMUtils#getQmSessionId(DBPDataSource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String QMUtils.getQmSessionId(DBPDataSource)"})
  public void testGetQmSessionIdWithDataSource5() throws DBException {
    // Arrange
    SMSessionContext parentContext = mock(SMSessionContext.class);
    when(parentContext.getSpaceSession(
            Mockito.<DBRProgressMonitor>any(), Mockito.<SMAuthSpace>any(), anyBoolean()))
        .thenReturn(new LocalWorkspaceSession(mock(DBPWorkspace.class)));

    SessionContextImpl sessionContextImpl = new SessionContextImpl(parentContext);
    sessionContextImpl.addSession(new LocalWorkspaceSession(mock(DBPWorkspace.class)));

    DBPWorkspace dbpWorkspace = mock(DBPWorkspace.class);
    when(dbpWorkspace.getAuthContext()).thenReturn(sessionContextImpl);

    DBPProject dbpProject = mock(DBPProject.class);
    when(dbpProject.getWorkspace()).thenReturn(dbpWorkspace);
    when(dbpProject.getSessionContext())
        .thenReturn(new SessionContextImpl(mock(SMSessionContext.class)));

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getProject()).thenReturn(dbpProject);

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    // Act
    String actualQmSessionId = QMUtils.getQmSessionId(dataSource);

    // Assert
    verify(dataSource).getContainer();
    verify(dbpDataSourceContainer).getProject();
    verify(dbpProject).getSessionContext();
    verify(dbpProject).getWorkspace();
    verify(dbpWorkspace).getAuthContext();
    verify(parentContext)
        .getSpaceSession(isA(DBRProgressMonitor.class), isA(SMAuthSpace.class), eq(false));
    assertNull(actualQmSessionId);
  }

  /**
   * Test {@link QMUtils#getQmSessionId(DBPDataSource)} with {@code dataSource}.
   *
   * <p>Method under test: {@link QMUtils#getQmSessionId(DBPDataSource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String QMUtils.getQmSessionId(DBPDataSource)"})
  public void testGetQmSessionIdWithDataSource6() throws DBException {
    // Arrange
    SMSessionContext parentContext = mock(SMSessionContext.class);
    when(parentContext.getSpaceSession(
            Mockito.<DBRProgressMonitor>any(), Mockito.<SMAuthSpace>any(), anyBoolean()))
        .thenReturn(new LocalWorkspaceSession(mock(DBPWorkspace.class)));

    SessionContextImpl sessionContextImpl = new SessionContextImpl(parentContext);
    sessionContextImpl.addSession(new LocalWorkspaceSession(mock(DBPWorkspace.class)));
    sessionContextImpl.addSession(new LocalWorkspaceSession(mock(DBPWorkspace.class)));

    DBPWorkspace dbpWorkspace = mock(DBPWorkspace.class);
    when(dbpWorkspace.getAuthContext()).thenReturn(sessionContextImpl);

    DBPProject dbpProject = mock(DBPProject.class);
    when(dbpProject.getWorkspace()).thenReturn(dbpWorkspace);
    when(dbpProject.getSessionContext())
        .thenReturn(new SessionContextImpl(mock(SMSessionContext.class)));

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getProject()).thenReturn(dbpProject);

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    // Act
    String actualQmSessionId = QMUtils.getQmSessionId(dataSource);

    // Assert
    verify(dataSource).getContainer();
    verify(dbpDataSourceContainer).getProject();
    verify(dbpProject).getSessionContext();
    verify(dbpProject).getWorkspace();
    verify(dbpWorkspace).getAuthContext();
    verify(parentContext)
        .getSpaceSession(isA(DBRProgressMonitor.class), isA(SMAuthSpace.class), eq(false));
    assertNull(actualQmSessionId);
  }

  /**
   * Test {@link QMUtils#getQmSessionId(DBPDataSource)} with {@code dataSource}.
   *
   * <p>Method under test: {@link QMUtils#getQmSessionId(DBPDataSource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String QMUtils.getQmSessionId(DBPDataSource)"})
  public void testGetQmSessionIdWithDataSource7() throws DBException {
    // Arrange
    SessionContextImpl sessionContextImpl = new SessionContextImpl(mock(SMSessionContext.class));
    sessionContextImpl.addSession(new LocalWorkspaceSession(mock(DBPWorkspace.class)));

    DBPProject dbpProject = mock(DBPProject.class);
    when(dbpProject.getSessionContext()).thenReturn(sessionContextImpl);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getProject()).thenReturn(dbpProject);

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    // Act
    String actualQmSessionId = QMUtils.getQmSessionId(dataSource);

    // Assert
    verify(dataSource).getContainer();
    verify(dbpDataSourceContainer).getProject();
    verify(dbpProject, atLeast(1)).getSessionContext();
    assertNull(actualQmSessionId);
  }

  /**
   * Test {@link QMUtils#getQmSessionId(DBPDataSource)} with {@code dataSource}.
   *
   * <ul>
   *   <li>Given {@link IllegalArgumentException#IllegalArgumentException()}.
   * </ul>
   *
   * <p>Method under test: {@link QMUtils#getQmSessionId(DBPDataSource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String QMUtils.getQmSessionId(DBPDataSource)"})
  public void testGetQmSessionIdWithDataSource_givenIllegalArgumentException() throws DBException {
    // Arrange
    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getContainer()).thenThrow(new IllegalArgumentException());

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> QMUtils.getQmSessionId(dataSource));
    verify(dataSource).getContainer();
  }

  /**
   * Test {@link QMUtils#getQmSessionId(DBPDataSource)} with {@code dataSource}.
   *
   * <ul>
   *   <li>Given {@link SMSessionContext} {@link
   *       SMSessionContext#getSpaceSession(DBRProgressMonitor, SMAuthSpace, boolean)} return {@code
   *       null}.
   * </ul>
   *
   * <p>Method under test: {@link QMUtils#getQmSessionId(DBPDataSource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String QMUtils.getQmSessionId(DBPDataSource)"})
  public void testGetQmSessionIdWithDataSource_givenSMSessionContextGetSpaceSessionReturnNull()
      throws DBException {
    // Arrange
    SMSessionContext parentContext = mock(SMSessionContext.class);
    when(parentContext.getSpaceSession(
            Mockito.<DBRProgressMonitor>any(), Mockito.<SMAuthSpace>any(), anyBoolean()))
        .thenReturn(null);

    DBPWorkspace dbpWorkspace = mock(DBPWorkspace.class);
    when(dbpWorkspace.getAuthContext()).thenReturn(new SessionContextImpl(parentContext));

    DBPProject dbpProject = mock(DBPProject.class);
    when(dbpProject.getWorkspace()).thenReturn(dbpWorkspace);
    when(dbpProject.getSessionContext())
        .thenReturn(new SessionContextImpl(mock(SMSessionContext.class)));

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getProject()).thenReturn(dbpProject);

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    // Act
    String actualQmSessionId = QMUtils.getQmSessionId(dataSource);

    // Assert
    verify(dataSource).getContainer();
    verify(dbpDataSourceContainer).getProject();
    verify(dbpProject).getSessionContext();
    verify(dbpProject).getWorkspace();
    verify(dbpWorkspace).getAuthContext();
    verify(parentContext)
        .getSpaceSession(isA(DBRProgressMonitor.class), isA(SMAuthSpace.class), eq(false));
    assertNull(actualQmSessionId);
  }

  /**
   * Test {@link QMUtils#getQmSessionId(DBPDataSource)} with {@code dataSource}.
   *
   * <ul>
   *   <li>Then calls {@link SMSessionContext#getSpaceSession(DBRProgressMonitor, SMAuthSpace,
   *       boolean)}.
   * </ul>
   *
   * <p>Method under test: {@link QMUtils#getQmSessionId(DBPDataSource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String QMUtils.getQmSessionId(DBPDataSource)"})
  public void testGetQmSessionIdWithDataSource_thenCallsGetSpaceSession() throws DBException {
    // Arrange
    SMSessionContext parentContext = mock(SMSessionContext.class);
    when(parentContext.getSpaceSession(
            Mockito.<DBRProgressMonitor>any(), Mockito.<SMAuthSpace>any(), anyBoolean()))
        .thenReturn(new LocalWorkspaceSession(mock(DBPWorkspace.class)));

    DBPWorkspace dbpWorkspace = mock(DBPWorkspace.class);
    when(dbpWorkspace.getAuthContext()).thenReturn(new SessionContextImpl(parentContext));

    DBPProject dbpProject = mock(DBPProject.class);
    when(dbpProject.getWorkspace()).thenReturn(dbpWorkspace);
    when(dbpProject.getSessionContext())
        .thenReturn(new SessionContextImpl(mock(SMSessionContext.class)));

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getProject()).thenReturn(dbpProject);

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    // Act
    String actualQmSessionId = QMUtils.getQmSessionId(dataSource);

    // Assert
    verify(dataSource).getContainer();
    verify(dbpDataSourceContainer).getProject();
    verify(dbpProject).getSessionContext();
    verify(dbpProject).getWorkspace();
    verify(dbpWorkspace).getAuthContext();
    verify(parentContext)
        .getSpaceSession(isA(DBRProgressMonitor.class), isA(SMAuthSpace.class), eq(false));
    assertNull(actualQmSessionId);
  }

  /**
   * Test {@link QMUtils#getQmSessionId(DBPProject)} with {@code project}.
   *
   * <p>Method under test: {@link QMUtils#getQmSessionId(DBPProject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String QMUtils.getQmSessionId(DBPProject)"})
  public void testGetQmSessionIdWithProject() throws DBException {
    // Arrange
    DBPProject project = mock(DBPProject.class);
    when(project.getSessionContext()).thenThrow(new IllegalArgumentException());

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> QMUtils.getQmSessionId(project));
    verify(project).getSessionContext();
  }

  /**
   * Test {@link QMUtils#getQmSessionId(DBPProject)} with {@code project}.
   *
   * <p>Method under test: {@link QMUtils#getQmSessionId(DBPProject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String QMUtils.getQmSessionId(DBPProject)"})
  public void testGetQmSessionIdWithProject2() throws DBException {
    // Arrange
    DBPProject project = mock(DBPProject.class);
    when(project.getWorkspace()).thenThrow(new IllegalArgumentException());
    when(project.getSessionContext())
        .thenReturn(new SessionContextImpl(mock(SMSessionContext.class)));

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> QMUtils.getQmSessionId(project));
    verify(project).getSessionContext();
    verify(project).getWorkspace();
  }

  /**
   * Test {@link QMUtils#getQmSessionId(DBPProject)} with {@code project}.
   *
   * <p>Method under test: {@link QMUtils#getQmSessionId(DBPProject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String QMUtils.getQmSessionId(DBPProject)"})
  public void testGetQmSessionIdWithProject3() throws DBException {
    // Arrange
    DBPWorkspace dbpWorkspace = mock(DBPWorkspace.class);
    when(dbpWorkspace.getAuthContext()).thenThrow(new IllegalArgumentException());

    DBPProject project = mock(DBPProject.class);
    when(project.getWorkspace()).thenReturn(dbpWorkspace);
    when(project.getSessionContext())
        .thenReturn(new SessionContextImpl(mock(SMSessionContext.class)));

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> QMUtils.getQmSessionId(project));
    verify(project).getSessionContext();
    verify(project).getWorkspace();
    verify(dbpWorkspace).getAuthContext();
  }

  /**
   * Test {@link QMUtils#getQmSessionId(DBPProject)} with {@code project}.
   *
   * <p>Method under test: {@link QMUtils#getQmSessionId(DBPProject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String QMUtils.getQmSessionId(DBPProject)"})
  public void testGetQmSessionIdWithProject4() throws DBException {
    // Arrange
    SMSessionContext parentContext = mock(SMSessionContext.class);
    when(parentContext.getSpaceSession(
            Mockito.<DBRProgressMonitor>any(), Mockito.<SMAuthSpace>any(), anyBoolean()))
        .thenReturn(new LocalWorkspaceSession(mock(DBPWorkspace.class)));

    SessionContextImpl sessionContextImpl = new SessionContextImpl(parentContext);
    sessionContextImpl.addSession(new LocalWorkspaceSession(mock(DBPWorkspace.class)));

    DBPWorkspace dbpWorkspace = mock(DBPWorkspace.class);
    when(dbpWorkspace.getAuthContext()).thenReturn(sessionContextImpl);

    DBPProject project = mock(DBPProject.class);
    when(project.getWorkspace()).thenReturn(dbpWorkspace);
    when(project.getSessionContext())
        .thenReturn(new SessionContextImpl(mock(SMSessionContext.class)));

    // Act
    String actualQmSessionId = QMUtils.getQmSessionId(project);

    // Assert
    verify(project).getSessionContext();
    verify(project).getWorkspace();
    verify(dbpWorkspace).getAuthContext();
    verify(parentContext)
        .getSpaceSession(isA(DBRProgressMonitor.class), isA(SMAuthSpace.class), eq(false));
    assertNull(actualQmSessionId);
  }

  /**
   * Test {@link QMUtils#getQmSessionId(DBPProject)} with {@code project}.
   *
   * <p>Method under test: {@link QMUtils#getQmSessionId(DBPProject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String QMUtils.getQmSessionId(DBPProject)"})
  public void testGetQmSessionIdWithProject5() throws DBException {
    // Arrange
    SMSessionContext parentContext = mock(SMSessionContext.class);
    when(parentContext.getSpaceSession(
            Mockito.<DBRProgressMonitor>any(), Mockito.<SMAuthSpace>any(), anyBoolean()))
        .thenReturn(new LocalWorkspaceSession(mock(DBPWorkspace.class)));

    SessionContextImpl sessionContextImpl = new SessionContextImpl(parentContext);
    sessionContextImpl.addSession(new LocalWorkspaceSession(mock(DBPWorkspace.class)));
    sessionContextImpl.addSession(new LocalWorkspaceSession(mock(DBPWorkspace.class)));

    DBPWorkspace dbpWorkspace = mock(DBPWorkspace.class);
    when(dbpWorkspace.getAuthContext()).thenReturn(sessionContextImpl);

    DBPProject project = mock(DBPProject.class);
    when(project.getWorkspace()).thenReturn(dbpWorkspace);
    when(project.getSessionContext())
        .thenReturn(new SessionContextImpl(mock(SMSessionContext.class)));

    // Act
    String actualQmSessionId = QMUtils.getQmSessionId(project);

    // Assert
    verify(project).getSessionContext();
    verify(project).getWorkspace();
    verify(dbpWorkspace).getAuthContext();
    verify(parentContext)
        .getSpaceSession(isA(DBRProgressMonitor.class), isA(SMAuthSpace.class), eq(false));
    assertNull(actualQmSessionId);
  }

  /**
   * Test {@link QMUtils#getQmSessionId(DBPProject)} with {@code project}.
   *
   * <p>Method under test: {@link QMUtils#getQmSessionId(DBPProject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String QMUtils.getQmSessionId(DBPProject)"})
  public void testGetQmSessionIdWithProject6() throws DBException {
    // Arrange
    SessionContextImpl sessionContextImpl = new SessionContextImpl(mock(SMSessionContext.class));
    sessionContextImpl.addSession(new LocalWorkspaceSession(mock(DBPWorkspace.class)));

    DBPProject project = mock(DBPProject.class);
    when(project.getSessionContext()).thenReturn(sessionContextImpl);

    // Act
    String actualQmSessionId = QMUtils.getQmSessionId(project);

    // Assert
    verify(project, atLeast(1)).getSessionContext();
    assertNull(actualQmSessionId);
  }

  /**
   * Test {@link QMUtils#getQmSessionId(DBPProject)} with {@code project}.
   *
   * <ul>
   *   <li>Given {@link SMSessionContext} {@link
   *       SMSessionContext#getSpaceSession(DBRProgressMonitor, SMAuthSpace, boolean)} return {@code
   *       null}.
   * </ul>
   *
   * <p>Method under test: {@link QMUtils#getQmSessionId(DBPProject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String QMUtils.getQmSessionId(DBPProject)"})
  public void testGetQmSessionIdWithProject_givenSMSessionContextGetSpaceSessionReturnNull()
      throws DBException {
    // Arrange
    SMSessionContext parentContext = mock(SMSessionContext.class);
    when(parentContext.getSpaceSession(
            Mockito.<DBRProgressMonitor>any(), Mockito.<SMAuthSpace>any(), anyBoolean()))
        .thenReturn(null);

    DBPWorkspace dbpWorkspace = mock(DBPWorkspace.class);
    when(dbpWorkspace.getAuthContext()).thenReturn(new SessionContextImpl(parentContext));

    DBPProject project = mock(DBPProject.class);
    when(project.getWorkspace()).thenReturn(dbpWorkspace);
    when(project.getSessionContext())
        .thenReturn(new SessionContextImpl(mock(SMSessionContext.class)));

    // Act
    String actualQmSessionId = QMUtils.getQmSessionId(project);

    // Assert
    verify(project).getSessionContext();
    verify(project).getWorkspace();
    verify(dbpWorkspace).getAuthContext();
    verify(parentContext)
        .getSpaceSession(isA(DBRProgressMonitor.class), isA(SMAuthSpace.class), eq(false));
    assertNull(actualQmSessionId);
  }

  /**
   * Test {@link QMUtils#getQmSessionId(DBPProject)} with {@code project}.
   *
   * <ul>
   *   <li>Then calls {@link SMSessionContext#getSpaceSession(DBRProgressMonitor, SMAuthSpace,
   *       boolean)}.
   * </ul>
   *
   * <p>Method under test: {@link QMUtils#getQmSessionId(DBPProject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String QMUtils.getQmSessionId(DBPProject)"})
  public void testGetQmSessionIdWithProject_thenCallsGetSpaceSession() throws DBException {
    // Arrange
    SMSessionContext parentContext = mock(SMSessionContext.class);
    when(parentContext.getSpaceSession(
            Mockito.<DBRProgressMonitor>any(), Mockito.<SMAuthSpace>any(), anyBoolean()))
        .thenReturn(new LocalWorkspaceSession(mock(DBPWorkspace.class)));

    DBPWorkspace dbpWorkspace = mock(DBPWorkspace.class);
    when(dbpWorkspace.getAuthContext()).thenReturn(new SessionContextImpl(parentContext));

    DBPProject project = mock(DBPProject.class);
    when(project.getWorkspace()).thenReturn(dbpWorkspace);
    when(project.getSessionContext())
        .thenReturn(new SessionContextImpl(mock(SMSessionContext.class)));

    // Act
    String actualQmSessionId = QMUtils.getQmSessionId(project);

    // Assert
    verify(project).getSessionContext();
    verify(project).getWorkspace();
    verify(dbpWorkspace).getAuthContext();
    verify(parentContext)
        .getSpaceSession(isA(DBRProgressMonitor.class), isA(SMAuthSpace.class), eq(false));
    assertNull(actualQmSessionId);
  }

  /**
   * Test {@link QMUtils#getQmSessionId(SMSession)} with {@code session}.
   *
   * <p>Method under test: {@link QMUtils#getQmSessionId(SMSession)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String QMUtils.getQmSessionId(SMSession)"})
  public void testGetQmSessionIdWithSession() {
    // Arrange, Act and Assert
    assertNull(QMUtils.getQmSessionId(new LocalWorkspaceSession(mock(DBPWorkspace.class))));
  }

  /**
   * Test {@link QMUtils#getQmSessionId(SMSession)} with {@code session}.
   *
   * <ul>
   *   <li>When {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link QMUtils#getQmSessionId(SMSession)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String QMUtils.getQmSessionId(SMSession)"})
  public void testGetQmSessionIdWithSession_whenNull() {
    // Arrange, Act and Assert
    assertNull(QMUtils.getQmSessionId((SMSession) null));
  }

  /**
   * Test {@link QMUtils#getObjectEventTime(QMEvent)}.
   *
   * <ul>
   *   <li>Given one.
   *   <li>When {@link QMMObject} {@link QMMObject#getCloseTime()} return one.
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link QMUtils#getObjectEventTime(QMEvent)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long QMUtils.getObjectEventTime(QMEvent)"})
  public void testGetObjectEventTime_givenOne_whenQMMObjectGetCloseTimeReturnOne_thenReturnOne() {
    // Arrange
    QMMObject object = mock(QMMObject.class);
    when(object.getCloseTime()).thenReturn(1L);

    // Act
    long actualObjectEventTime =
        QMUtils.getObjectEventTime(
            new QMMetaEventEntity(object, QMEventAction.END, 1L, "42", mock(QMSessionInfo.class)));

    // Assert
    verify(object).getCloseTime();
    assertEquals(1L, actualObjectEventTime);
  }

  /**
   * Test {@link QMUtils#getObjectEventTime(QMEvent)}.
   *
   * <ul>
   *   <li>Given one.
   *   <li>When {@link QMMObject} {@link QMMObject#getOpenTime()} return one.
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link QMUtils#getObjectEventTime(QMEvent)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long QMUtils.getObjectEventTime(QMEvent)"})
  public void testGetObjectEventTime_givenOne_whenQMMObjectGetOpenTimeReturnOne_thenReturnOne() {
    // Arrange
    QMMObject object = mock(QMMObject.class);
    when(object.getOpenTime()).thenReturn(1L);

    // Act
    long actualObjectEventTime =
        QMUtils.getObjectEventTime(
            new QMMetaEventEntity(
                object, QMEventAction.BEGIN, 1L, "42", mock(QMSessionInfo.class)));

    // Assert
    verify(object).getOpenTime();
    assertEquals(1L, actualObjectEventTime);
  }

  /**
   * Test {@link QMUtils#getObjectEventTime(QMEvent)}.
   *
   * <ul>
   *   <li>Then return ten.
   * </ul>
   *
   * <p>Method under test: {@link QMUtils#getObjectEventTime(QMEvent)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long QMUtils.getObjectEventTime(QMEvent)"})
  public void testGetObjectEventTime_thenReturnTen() {
    // Arrange, Act and Assert
    assertEquals(
        10L,
        QMUtils.getObjectEventTime(
            new QMMetaEvent(mock(QMMObject.class), QMEventAction.BEGIN, 10L, "42")));
  }

  /**
   * Test {@link QMUtils#getObjectEventTime(QMEvent)}.
   *
   * <ul>
   *   <li>When {@link QMMObject} {@link QMMObject#getCloseTime()} throw {@link
   *       IllegalArgumentException#IllegalArgumentException()}.
   * </ul>
   *
   * <p>Method under test: {@link QMUtils#getObjectEventTime(QMEvent)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long QMUtils.getObjectEventTime(QMEvent)"})
  public void testGetObjectEventTime_whenQMMObjectGetCloseTimeThrowIllegalArgumentException() {
    // Arrange
    QMMObject object = mock(QMMObject.class);
    when(object.getCloseTime()).thenThrow(new IllegalArgumentException());

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            QMUtils.getObjectEventTime(
                new QMMetaEventEntity(
                    object, QMEventAction.END, 1L, "42", mock(QMSessionInfo.class))));
    verify(object).getCloseTime();
  }

  /**
   * Test {@link QMUtils#getObjectEventTime(QMEvent)}.
   *
   * <ul>
   *   <li>When {@link QMMObject} {@link QMMObject#getOpenTime()} throw {@link
   *       IllegalArgumentException#IllegalArgumentException()}.
   * </ul>
   *
   * <p>Method under test: {@link QMUtils#getObjectEventTime(QMEvent)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long QMUtils.getObjectEventTime(QMEvent)"})
  public void testGetObjectEventTime_whenQMMObjectGetOpenTimeThrowIllegalArgumentException() {
    // Arrange
    QMMObject object = mock(QMMObject.class);
    when(object.getOpenTime()).thenThrow(new IllegalArgumentException());

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            QMUtils.getObjectEventTime(
                new QMMetaEventEntity(
                    object, QMEventAction.BEGIN, 1L, "42", mock(QMSessionInfo.class))));
    verify(object).getOpenTime();
  }

  /**
   * Test ListCursorImpl {@link ListCursorImpl#getTotalSize()}.
   *
   * <p>Method under test: {@link ListCursorImpl#getTotalSize()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long ListCursorImpl.getTotalSize()"})
  public void testListCursorImplGetTotalSize() {
    // Arrange, Act and Assert
    assertEquals(0L, new ListCursorImpl(new ArrayList<>()).getTotalSize());
  }

  /**
   * Test ListCursorImpl getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link ListCursorImpl#ListCursorImpl(List)}
   *   <li>{@link ListCursorImpl#close()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ListCursorImpl.<init>(List)", "void ListCursorImpl.close()"})
  public void testListCursorImplGettersAndSetters() {
    // Arrange and Act
    ListCursorImpl actualListCursorImpl = new ListCursorImpl(new ArrayList<>());
    actualListCursorImpl.close();

    // Assert
    assertEquals(0L, actualListCursorImpl.getTotalSize());
  }

  /**
   * Test ListCursorImpl {@link ListCursorImpl#hasNextEvent(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link ListCursorImpl#hasNextEvent(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ListCursorImpl.hasNextEvent(DBRProgressMonitor)"})
  public void testListCursorImplHasNextEvent_thenReturnFalse() throws DBException {
    // Arrange
    ListCursorImpl listCursorImpl = new ListCursorImpl(new ArrayList<>());

    // Act and Assert
    assertFalse(listCursorImpl.hasNextEvent(new LoggingProgressMonitor()));
  }

  /**
   * Test ListCursorImpl {@link ListCursorImpl#hasNextEvent(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link ListCursorImpl#hasNextEvent(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ListCursorImpl.hasNextEvent(DBRProgressMonitor)"})
  public void testListCursorImplHasNextEvent_thenReturnTrue() throws DBException {
    // Arrange
    ArrayList<QMMetaEvent> events = new ArrayList<>();
    events.add(new QMMetaEvent(mock(QMMObject.class), QMEventAction.BEGIN, 10L, "42"));
    ListCursorImpl listCursorImpl = new ListCursorImpl(events);

    // Act and Assert
    assertTrue(listCursorImpl.hasNextEvent(new LoggingProgressMonitor()));
  }

  /**
   * Test ListCursorImpl {@link ListCursorImpl#nextEvent(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Then return SessionInfo is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link ListCursorImpl#nextEvent(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"QMMetaEventEntity ListCursorImpl.nextEvent(DBRProgressMonitor)"})
  public void testListCursorImplNextEvent_thenReturnSessionInfoIsNull() throws DBException {
    // Arrange
    ArrayList<QMMetaEvent> events = new ArrayList<>();
    events.add(new QMMetaEvent(mock(QMMObject.class), QMEventAction.BEGIN, 10L, "42"));
    ListCursorImpl listCursorImpl = new ListCursorImpl(events);

    // Act
    QMMetaEventEntity actualNextEventResult =
        listCursorImpl.nextEvent(new LoggingProgressMonitor());

    // Assert
    assertNull(actualNextEventResult.getSessionInfo());
    assertEquals(1L, actualNextEventResult.getId());
    assertEquals(QMEventAction.BEGIN, actualNextEventResult.getAction());
  }

  /**
   * Test ListCursorImpl {@link ListCursorImpl#scroll(int, DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Then does not throw.
   * </ul>
   *
   * <p>Method under test: {@link ListCursorImpl#scroll(int, DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ListCursorImpl.scroll(int, DBRProgressMonitor)"})
  public void testListCursorImplScroll_thenDoesNotThrow() throws DBException {
    // Arrange
    ArrayList<QMMetaEvent> events = new ArrayList<>();
    events.add(new QMMetaEvent(mock(QMMObject.class), QMEventAction.BEGIN, 10L, "42"));
    events.add(new QMMetaEvent(mock(QMMObject.class), QMEventAction.BEGIN, 10L, "42"));
    ListCursorImpl listCursorImpl = new ListCursorImpl(events);

    // Act and Assert
    listCursorImpl.scroll(1, new LoggingProgressMonitor());
  }

  /**
   * Test ListCursorImpl {@link ListCursorImpl#scroll(int, DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>When {@link QMConstants#EVENT_TYPE_UNKNOWN}.
   *   <li>Then throw {@link DBException}.
   * </ul>
   *
   * <p>Method under test: {@link ListCursorImpl#scroll(int, DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ListCursorImpl.scroll(int, DBRProgressMonitor)"})
  public void testListCursorImplScroll_whenEvent_type_unknown_thenThrowDBException()
      throws DBException {
    // Arrange
    ListCursorImpl listCursorImpl = new ListCursorImpl(new ArrayList<>());

    // Act and Assert
    assertThrows(
        DBException.class,
        () -> listCursorImpl.scroll(QMConstants.EVENT_TYPE_UNKNOWN, new LoggingProgressMonitor()));
  }

  /**
   * Test ListCursorImpl {@link ListCursorImpl#scroll(int, DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then throw {@link DBException}.
   * </ul>
   *
   * <p>Method under test: {@link ListCursorImpl#scroll(int, DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ListCursorImpl.scroll(int, DBRProgressMonitor)"})
  public void testListCursorImplScroll_whenOne_thenThrowDBException() throws DBException {
    // Arrange
    ListCursorImpl listCursorImpl = new ListCursorImpl(new ArrayList<>());

    // Act and Assert
    assertThrows(DBException.class, () -> listCursorImpl.scroll(1, new LoggingProgressMonitor()));
  }
}
