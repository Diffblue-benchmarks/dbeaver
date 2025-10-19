package org.jkiss.dbeaver.model.impl.auth;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.app.DBPWorkspace;
import org.jkiss.dbeaver.model.auth.SMAuthSpace;
import org.jkiss.dbeaver.model.auth.SMSession;
import org.jkiss.dbeaver.model.auth.SMSessionContext;
import org.jkiss.dbeaver.model.impl.app.LocalWorkspaceSession;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class SessionContextImplDiffblueTest {
  /**
   * Test {@link SessionContextImpl#SessionContextImpl(SMSessionContext)}.
   *
   * <p>Method under test: {@link SessionContextImpl#SessionContextImpl(SMSessionContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SessionContextImpl.<init>(SMSessionContext)"})
  public void testNewSessionContextImpl() {
    // Arrange, Act and Assert
    assertNull(new SessionContextImpl(null).getPrimaryAuthSpace());
  }

  /**
   * Test {@link SessionContextImpl#getSpaceSession(DBRProgressMonitor, SMAuthSpace, boolean)}.
   *
   * <p>Method under test: {@link SessionContextImpl#getSpaceSession(DBRProgressMonitor,
   * SMAuthSpace, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SMSession SessionContextImpl.getSpaceSession(DBRProgressMonitor, SMAuthSpace, boolean)"
  })
  public void testGetSpaceSession() throws DBException {
    // Arrange
    SMSessionContext parentContext = mock(SMSessionContext.class);
    LocalWorkspaceSession localWorkspaceSession =
        new LocalWorkspaceSession(mock(DBPWorkspace.class));
    when(parentContext.getSpaceSession(
            Mockito.<DBRProgressMonitor>any(), Mockito.<SMAuthSpace>any(), anyBoolean()))
        .thenReturn(localWorkspaceSession);

    SessionContextImpl sessionContextImpl = new SessionContextImpl(parentContext);
    sessionContextImpl.addSession(new LocalWorkspaceSession(mock(DBPWorkspace.class)));

    // Act
    SMSession actualSpaceSession =
        sessionContextImpl.getSpaceSession(
            new LoggingProgressMonitor(), mock(SMAuthSpace.class), true);

    // Assert
    verify(parentContext)
        .getSpaceSession(isA(DBRProgressMonitor.class), isA(SMAuthSpace.class), eq(false));
    assertSame(localWorkspaceSession, actualSpaceSession);
  }

  /**
   * Test {@link SessionContextImpl#getSpaceSession(DBRProgressMonitor, SMAuthSpace, boolean)}.
   *
   * <ul>
   *   <li>Then return {@link LocalWorkspaceSession#LocalWorkspaceSession(DBPWorkspace)} with
   *       workspace is {@link DBPWorkspace}.
   * </ul>
   *
   * <p>Method under test: {@link SessionContextImpl#getSpaceSession(DBRProgressMonitor,
   * SMAuthSpace, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SMSession SessionContextImpl.getSpaceSession(DBRProgressMonitor, SMAuthSpace, boolean)"
  })
  public void testGetSpaceSession_thenReturnLocalWorkspaceSessionWithWorkspaceIsDBPWorkspace()
      throws DBException {
    // Arrange
    SMSessionContext parentContext = mock(SMSessionContext.class);
    LocalWorkspaceSession localWorkspaceSession =
        new LocalWorkspaceSession(mock(DBPWorkspace.class));
    when(parentContext.getSpaceSession(
            Mockito.<DBRProgressMonitor>any(), Mockito.<SMAuthSpace>any(), anyBoolean()))
        .thenReturn(localWorkspaceSession);
    SessionContextImpl sessionContextImpl = new SessionContextImpl(parentContext);

    // Act
    SMSession actualSpaceSession =
        sessionContextImpl.getSpaceSession(
            new LoggingProgressMonitor(), mock(SMAuthSpace.class), true);

    // Assert
    verify(parentContext)
        .getSpaceSession(isA(DBRProgressMonitor.class), isA(SMAuthSpace.class), eq(false));
    assertSame(localWorkspaceSession, actualSpaceSession);
  }

  /**
   * Test {@link SessionContextImpl#getSpaceSession(DBRProgressMonitor, SMAuthSpace, boolean)}.
   *
   * <ul>
   *   <li>Then throw {@link DBException}.
   * </ul>
   *
   * <p>Method under test: {@link SessionContextImpl#getSpaceSession(DBRProgressMonitor,
   * SMAuthSpace, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SMSession SessionContextImpl.getSpaceSession(DBRProgressMonitor, SMAuthSpace, boolean)"
  })
  public void testGetSpaceSession_thenThrowDBException() throws DBException {
    // Arrange
    SMSessionContext parentContext = mock(SMSessionContext.class);
    when(parentContext.getSpaceSession(
            Mockito.<DBRProgressMonitor>any(), Mockito.<SMAuthSpace>any(), anyBoolean()))
        .thenThrow(new DBException("An error occurred"));
    SessionContextImpl sessionContextImpl = new SessionContextImpl(parentContext);

    // Act and Assert
    assertThrows(
        DBException.class,
        () ->
            sessionContextImpl.getSpaceSession(
                new LoggingProgressMonitor(), mock(SMAuthSpace.class), true));
    verify(parentContext)
        .getSpaceSession(isA(DBRProgressMonitor.class), isA(SMAuthSpace.class), eq(false));
  }

  /**
   * Test {@link SessionContextImpl#getPrimaryAuthSpace()}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SessionContextImpl#getPrimaryAuthSpace()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"SMAuthSpace SessionContextImpl.getPrimaryAuthSpace()"})
  public void testGetPrimaryAuthSpace_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(new SessionContextImpl(mock(SMSessionContext.class)).getPrimaryAuthSpace());
  }

  /**
   * Test {@link SessionContextImpl#findSpaceSession(SMAuthSpace)}.
   *
   * <p>Method under test: {@link SessionContextImpl#findSpaceSession(SMAuthSpace)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"SMSession SessionContextImpl.findSpaceSession(SMAuthSpace)"})
  public void testFindSpaceSession() {
    // Arrange
    SessionContextImpl parentContext = new SessionContextImpl(mock(SMSessionContext.class));

    SessionContextImpl sessionContextImpl = new SessionContextImpl(parentContext);
    sessionContextImpl.addSession(new LocalWorkspaceSession(null));

    // Act and Assert
    assertNull(sessionContextImpl.findSpaceSession(mock(SMAuthSpace.class)));
  }

  /**
   * Test {@link SessionContextImpl#findSpaceSession(SMAuthSpace)}.
   *
   * <p>Method under test: {@link SessionContextImpl#findSpaceSession(SMAuthSpace)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"SMSession SessionContextImpl.findSpaceSession(SMAuthSpace)"})
  public void testFindSpaceSession2() {
    // Arrange
    SessionContextImpl parentContext = new SessionContextImpl(mock(SMSessionContext.class));

    SessionContextImpl sessionContextImpl = new SessionContextImpl(parentContext);
    sessionContextImpl.addSession(new LocalWorkspaceSession(mock(DBPWorkspace.class)));

    // Act and Assert
    assertNull(sessionContextImpl.findSpaceSession(null));
  }

  /**
   * Test {@link SessionContextImpl#findSpaceSession(SMAuthSpace)}.
   *
   * <p>Method under test: {@link SessionContextImpl#findSpaceSession(SMAuthSpace)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"SMSession SessionContextImpl.findSpaceSession(SMAuthSpace)"})
  public void testFindSpaceSession3() {
    // Arrange
    SessionContextImpl parentContext = new SessionContextImpl(mock(SMSessionContext.class));

    SessionContextImpl sessionContextImpl = new SessionContextImpl(parentContext);
    sessionContextImpl.addSession(new LocalWorkspaceSession(mock(DBPWorkspace.class)));

    // Act and Assert
    assertNull(sessionContextImpl.findSpaceSession(mock(SMAuthSpace.class)));
  }

  /**
   * Test {@link SessionContextImpl#findSpaceSession(SMAuthSpace)}.
   *
   * <ul>
   *   <li>Then return {@link LocalWorkspaceSession#LocalWorkspaceSession(DBPWorkspace)} with
   *       workspace is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SessionContextImpl#findSpaceSession(SMAuthSpace)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"SMSession SessionContextImpl.findSpaceSession(SMAuthSpace)"})
  public void testFindSpaceSession_thenReturnLocalWorkspaceSessionWithWorkspaceIsNull() {
    // Arrange
    SessionContextImpl parentContext = new SessionContextImpl(mock(SMSessionContext.class));

    SessionContextImpl sessionContextImpl = new SessionContextImpl(parentContext);
    LocalWorkspaceSession session = new LocalWorkspaceSession(null);
    sessionContextImpl.addSession(session);

    // Act and Assert
    assertSame(session, sessionContextImpl.findSpaceSession(null));
  }

  /**
   * Test {@link SessionContextImpl#findSpaceSession(SMAuthSpace)}.
   *
   * <ul>
   *   <li>When {@link SMAuthSpace}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SessionContextImpl#findSpaceSession(SMAuthSpace)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"SMSession SessionContextImpl.findSpaceSession(SMAuthSpace)"})
  public void testFindSpaceSession_whenSMAuthSpace_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(
        new SessionContextImpl(mock(SMSessionContext.class))
            .findSpaceSession(mock(SMAuthSpace.class)));
  }

  /**
   * Test {@link SessionContextImpl#removeSession(SMSession)}.
   *
   * <p>Method under test: {@link SessionContextImpl#removeSession(SMSession)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SessionContextImpl.removeSession(SMSession)"})
  public void testRemoveSession() {
    // Arrange
    SessionContextImpl sessionContextImpl = new SessionContextImpl(mock(SMSessionContext.class));

    // Act
    boolean actualRemoveSessionResult =
        sessionContextImpl.removeSession(new LocalWorkspaceSession(mock(DBPWorkspace.class)));

    // Assert
    assertFalse(actualRemoveSessionResult);
  }
}
