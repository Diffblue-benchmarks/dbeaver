package org.jkiss.dbeaver.runtime.ui.console;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.jobs.Job;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.model.DBPEvent;
import org.jkiss.dbeaver.model.navigator.DBNEmptyNode;
import org.jkiss.dbeaver.model.navigator.DBNNode;
import org.jkiss.dbeaver.model.runtime.DBRProcessDescriptor;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.DBRRunnableWithProgress;
import org.jkiss.dbeaver.model.runtime.DBRRunnableWithResult;
import org.jkiss.dbeaver.model.runtime.DBRRunnableWithReturn;
import org.jkiss.dbeaver.model.runtime.load.ILoadService;
import org.jkiss.dbeaver.model.runtime.load.ILoadVisualizer;
import org.jkiss.dbeaver.model.struct.DBSDocumentConstraint;
import org.jkiss.dbeaver.model.struct.DBSDocumentContainer;
import org.jkiss.dbeaver.model.struct.DBSObject;
import org.jkiss.dbeaver.runtime.ui.DBPPlatformUI;
import org.jkiss.dbeaver.runtime.ui.DBPPlatformUI.UserResponse;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class ConsoleUserInterfaceDiffblueTest {
  /**
   * Test {@link ConsoleUserInterface#showError(String, String, Throwable)} with {@code title},
   * {@code message}, {@code e}.
   *
   * <ul>
   *   <li>When {@code Not all who wander are lost}.
   *   <li>Then return {@code OK}.
   * </ul>
   *
   * <p>Method under test: {@link ConsoleUserInterface#showError(String, String, Throwable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBPPlatformUI.UserResponse ConsoleUserInterface.showError(String, String, Throwable)"
  })
  public void testShowErrorWithTitleMessageE_whenNotAllWhoWanderAreLost_thenReturnOk() {
    // Arrange
    ConsoleUserInterface consoleUserInterface = new ConsoleUserInterface();

    // Act and Assert
    assertEquals(
        UserResponse.OK,
        consoleUserInterface.showError("Dr", "Not all who wander are lost", new Throwable()));
  }

  /**
   * Test {@link ConsoleUserInterface#showError(String, String, Throwable)} with {@code title},
   * {@code message}, {@code e}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code OK}.
   * </ul>
   *
   * <p>Method under test: {@link ConsoleUserInterface#showError(String, String, Throwable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBPPlatformUI.UserResponse ConsoleUserInterface.showError(String, String, Throwable)"
  })
  public void testShowErrorWithTitleMessageE_whenNull_thenReturnOk() {
    // Arrange
    ConsoleUserInterface consoleUserInterface = new ConsoleUserInterface();

    // Act and Assert
    assertEquals(UserResponse.OK, consoleUserInterface.showError("Dr", null, new Throwable()));
  }

  /**
   * Test {@link ConsoleUserInterface#showError(String, String, IStatus)} with {@code title}, {@code
   * message}, {@code status}.
   *
   * <ul>
   *   <li>When {@link Job#ASYNC_FINISH}.
   *   <li>Then return {@code OK}.
   * </ul>
   *
   * <p>Method under test: {@link ConsoleUserInterface#showError(String, String, IStatus)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBPPlatformUI.UserResponse ConsoleUserInterface.showError(String, String, IStatus)"
  })
  public void testShowErrorWithTitleMessageStatus_whenAsync_finish_thenReturnOk() {
    // Arrange, Act and Assert
    assertEquals(
        UserResponse.OK,
        new ConsoleUserInterface()
            .showError("Dr", "Not all who wander are lost", Job.ASYNC_FINISH));
  }

  /**
   * Test {@link ConsoleUserInterface#showError(String, String, IStatus)} with {@code title}, {@code
   * message}, {@code status}.
   *
   * <ul>
   *   <li>When {@code Object}.
   *   <li>Then return {@code OK}.
   * </ul>
   *
   * <p>Method under test: {@link ConsoleUserInterface#showError(String, String, IStatus)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBPPlatformUI.UserResponse ConsoleUserInterface.showError(String, String, IStatus)"
  })
  public void testShowErrorWithTitleMessageStatus_whenJavaLangObject_thenReturnOk() {
    // Arrange
    ConsoleUserInterface consoleUserInterface = new ConsoleUserInterface();
    Class<Object> caller = Object.class;

    // Act and Assert
    assertEquals(
        UserResponse.OK,
        consoleUserInterface.showError(
            "Dr",
            "Not all who wander are lost",
            new MultiStatus(caller, 4, "Not all who wander are lost", new Throwable())));
  }

  /**
   * Test {@link ConsoleUserInterface#showError(String, String, IStatus)} with {@code title}, {@code
   * message}, {@code status}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code OK}.
   * </ul>
   *
   * <p>Method under test: {@link ConsoleUserInterface#showError(String, String, IStatus)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBPPlatformUI.UserResponse ConsoleUserInterface.showError(String, String, IStatus)"
  })
  public void testShowErrorWithTitleMessageStatus_whenNull_thenReturnOk() {
    // Arrange, Act and Assert
    assertEquals(
        UserResponse.OK, new ConsoleUserInterface().showError("Dr", null, Job.ASYNC_FINISH));
  }

  /**
   * Test {@link ConsoleUserInterface#showError(String, String)} with {@code title}, {@code
   * message}.
   *
   * <ul>
   *   <li>When {@code Not all who wander are lost}.
   * </ul>
   *
   * <p>Method under test: {@link ConsoleUserInterface#showError(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPPlatformUI.UserResponse ConsoleUserInterface.showError(String, String)"})
  public void testShowErrorWithTitleMessage_whenNotAllWhoWanderAreLost() {
    // Arrange, Act and Assert
    assertEquals(
        UserResponse.OK, new ConsoleUserInterface().showError("Dr", "Not all who wander are lost"));
  }

  /**
   * Test {@link ConsoleUserInterface#showError(String, String)} with {@code title}, {@code
   * message}.
   *
   * <ul>
   *   <li>When {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link ConsoleUserInterface#showError(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPPlatformUI.UserResponse ConsoleUserInterface.showError(String, String)"})
  public void testShowErrorWithTitleMessage_whenNull() {
    // Arrange, Act and Assert
    assertEquals(UserResponse.OK, new ConsoleUserInterface().showError("Dr", null));
  }

  /**
   * Test {@link ConsoleUserInterface#confirmAction(String, String)} with {@code title}, {@code
   * message}.
   *
   * <p>Method under test: {@link ConsoleUserInterface#confirmAction(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ConsoleUserInterface.confirmAction(String, String)"})
  public void testConfirmActionWithTitleMessage() {
    // Arrange, Act and Assert
    assertFalse(new ConsoleUserInterface().confirmAction("Dr", "Not all who wander are lost"));
  }

  /**
   * Test {@link ConsoleUserInterface#confirmAction(String, String, String, boolean)} with {@code
   * title}, {@code message}, {@code buttonLabel}, {@code isWarning}.
   *
   * <p>Method under test: {@link ConsoleUserInterface#confirmAction(String, String, String,
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ConsoleUserInterface.confirmAction(String, String, String, boolean)"})
  public void testConfirmActionWithTitleMessageButtonLabelIsWarning() {
    // Arrange, Act and Assert
    assertFalse(
        new ConsoleUserInterface()
            .confirmAction("Dr", "Not all who wander are lost", "Button Label", true));
  }

  /**
   * Test {@link ConsoleUserInterface#confirmAction(String, String, boolean)} with {@code title},
   * {@code message}, {@code isWarning}.
   *
   * <p>Method under test: {@link ConsoleUserInterface#confirmAction(String, String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ConsoleUserInterface.confirmAction(String, String, boolean)"})
  public void testConfirmActionWithTitleMessageIsWarning() {
    // Arrange, Act and Assert
    assertFalse(
        new ConsoleUserInterface().confirmAction("Dr", "Not all who wander are lost", true));
  }

  /**
   * Test {@link ConsoleUserInterface#showErrorStopRetryIgnore(String, Throwable, boolean)}.
   *
   * <ul>
   *   <li>When {@link Throwable#Throwable()}.
   *   <li>Then return {@code IGNORE}.
   * </ul>
   *
   * <p>Method under test: {@link ConsoleUserInterface#showErrorStopRetryIgnore(String, Throwable,
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBPPlatformUI.UserResponse ConsoleUserInterface.showErrorStopRetryIgnore(String, Throwable, boolean)"
  })
  public void testShowErrorStopRetryIgnore_whenThrowable_thenReturnIgnore() {
    // Arrange
    ConsoleUserInterface consoleUserInterface = new ConsoleUserInterface();

    // Act and Assert
    assertEquals(
        UserResponse.IGNORE,
        consoleUserInterface.showErrorStopRetryIgnore("Task", new Throwable(), true));
  }

  /**
   * Test {@link ConsoleUserInterface#promptUserCredentials(String, String, String, String, String,
   * String, boolean, boolean)} with {@code prompt}, {@code description}, {@code userNameLabel},
   * {@code userName}, {@code passwordLabel}, {@code userPassword}, {@code passwordOnly}, {@code
   * showSavePassword}.
   *
   * <p>Method under test: {@link ConsoleUserInterface#promptUserCredentials(String, String, String,
   * String, String, String, boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.connection.DBPAuthInfo ConsoleUserInterface.promptUserCredentials(String, String, String, String, String, String, boolean, boolean)"
  })
  public void
      testPromptUserCredentialsWithPromptDescriptionUserNameLabelUserNamePasswordLabelUserPasswordPasswordOnlyShowSavePassword() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class,
        () ->
            new ConsoleUserInterface()
                .promptUserCredentials(
                    "Prompt",
                    "The characteristics of someone or something",
                    "janedoe",
                    "janedoe",
                    "Password Label",
                    "iloveyou",
                    true,
                    true));
  }

  /**
   * Test {@link ConsoleUserInterface#promptUserCredentials(String, String, String, String, boolean,
   * boolean)} with {@code prompt}, {@code description}, {@code userName}, {@code userPassword},
   * {@code passwordOnly}, {@code showSavePassword}.
   *
   * <p>Method under test: {@link ConsoleUserInterface#promptUserCredentials(String, String, String,
   * String, boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.connection.DBPAuthInfo ConsoleUserInterface.promptUserCredentials(String, String, String, String, boolean, boolean)"
  })
  public void
      testPromptUserCredentialsWithPromptDescriptionUserNameUserPasswordPasswordOnlyShowSavePassword() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class,
        () ->
            new ConsoleUserInterface()
                .promptUserCredentials(
                    "Prompt",
                    "The characteristics of someone or something",
                    "janedoe",
                    "iloveyou",
                    true,
                    true));
  }

  /**
   * Test {@link ConsoleUserInterface#promptUserPasswordChange(String, String, String, boolean,
   * boolean)}.
   *
   * <p>Method under test: {@link ConsoleUserInterface#promptUserPasswordChange(String, String,
   * String, boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.access.DBAPasswordChangeInfo ConsoleUserInterface.promptUserPasswordChange(String, String, String, boolean, boolean)"
  })
  public void testPromptUserPasswordChange() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class,
        () ->
            new ConsoleUserInterface()
                .promptUserPasswordChange("Prompt", "janedoe", "iloveyou", true, true));
  }

  /**
   * Test {@link ConsoleUserInterface#promptProperty(String, String)}.
   *
   * <p>Method under test: {@link ConsoleUserInterface#promptProperty(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String ConsoleUserInterface.promptProperty(String, String)"})
  public void testPromptProperty() {
    // Arrange, Act and Assert
    assertNull(new ConsoleUserInterface().promptProperty("Prompt", "42"));
  }

  /**
   * Test {@link ConsoleUserInterface#acceptLicense(String, String)}.
   *
   * <p>Method under test: {@link ConsoleUserInterface#acceptLicense(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ConsoleUserInterface.acceptLicense(String, String)"})
  public void testAcceptLicense() {
    // Arrange, Act and Assert
    assertTrue(
        new ConsoleUserInterface().acceptLicense("Not all who wander are lost", "License Text"));
  }

  /**
   * Test {@link ConsoleUserInterface#selectObject(Object, String, DBNNode, DBNNode, Class[],
   * Class[], Class[])}.
   *
   * <p>Method under test: {@link ConsoleUserInterface#selectObject(Object, String, DBNNode,
   * DBNNode, Class[], Class[], Class[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBNNode ConsoleUserInterface.selectObject(Object, String, DBNNode, DBNNode, Class[], Class[], Class[])"
  })
  public void testSelectObject() {
    // Arrange
    ConsoleUserInterface consoleUserInterface = new ConsoleUserInterface();
    DBNEmptyNode rootNode = new DBNEmptyNode();
    DBNEmptyNode selectedNode = new DBNEmptyNode();
    Class<Object> forNameResult = Object.class;
    Class<Object> forNameResult2 = Object.class;
    Class<Object> forNameResult3 = Object.class;

    // Act and Assert
    assertNull(
        consoleUserInterface.selectObject(
            DBPEvent.RENAME,
            "Dr",
            rootNode,
            selectedNode,
            new Class[] {forNameResult},
            new Class[] {forNameResult2},
            new Class[] {forNameResult3}));
  }

  /**
   * Test {@link ConsoleUserInterface#executeProcess(DBRProcessDescriptor)}.
   *
   * <ul>
   *   <li>When {@link DBRProcessDescriptor} {@link DBRProcessDescriptor#execute()} does nothing.
   *   <li>Then calls {@link DBRProcessDescriptor#execute()}.
   * </ul>
   *
   * <p>Method under test: {@link ConsoleUserInterface#executeProcess(DBRProcessDescriptor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ConsoleUserInterface.executeProcess(DBRProcessDescriptor)"})
  public void testExecuteProcess_whenDBRProcessDescriptorExecuteDoesNothing_thenCallsExecute()
      throws DBException {
    // Arrange
    ConsoleUserInterface consoleUserInterface = new ConsoleUserInterface();

    DBRProcessDescriptor processDescriptor = mock(DBRProcessDescriptor.class);
    doNothing().when(processDescriptor).execute();

    // Act
    consoleUserInterface.executeProcess(processDescriptor);

    // Assert
    verify(processDescriptor).execute();
  }

  /**
   * Test {@link ConsoleUserInterface#executeWithProgress(DBRRunnableWithProgress)} with {@code
   * DBRRunnableWithProgress}.
   *
   * <p>Method under test: {@link ConsoleUserInterface#executeWithProgress(DBRRunnableWithProgress)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ConsoleUserInterface.executeWithProgress(DBRRunnableWithProgress)"})
  public void testExecuteWithProgressWithDBRRunnableWithProgress()
      throws InterruptedException, InvocationTargetException {
    // Arrange
    ConsoleUserInterface consoleUserInterface = new ConsoleUserInterface();

    DBRRunnableWithProgress runnable = mock(DBRRunnableWithProgress.class);
    doNothing().when(runnable).run(Mockito.<DBRProgressMonitor>any());

    // Act
    consoleUserInterface.executeWithProgress(runnable);

    // Assert
    verify(runnable).run(isA(DBRProgressMonitor.class));
  }

  /**
   * Test {@link ConsoleUserInterface#executeWithProgress(DBRRunnableWithProgress)} with {@code
   * DBRRunnableWithProgress}.
   *
   * <p>Method under test: {@link ConsoleUserInterface#executeWithProgress(DBRRunnableWithProgress)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ConsoleUserInterface.executeWithProgress(DBRRunnableWithProgress)"})
  public void testExecuteWithProgressWithDBRRunnableWithProgress2()
      throws InterruptedException, InvocationTargetException {
    // Arrange
    ConsoleUserInterface consoleUserInterface = new ConsoleUserInterface();

    DBRRunnableWithProgress runnable = mock(DBRRunnableWithProgress.class);
    doThrow(new IllegalStateException()).when(runnable).run(Mockito.<DBRProgressMonitor>any());

    // Act and Assert
    assertThrows(
        IllegalStateException.class, () -> consoleUserInterface.executeWithProgress(runnable));
    verify(runnable).run(isA(DBRProgressMonitor.class));
  }

  /**
   * Test {@link ConsoleUserInterface#executeWithProgress(Runnable)} with {@code Runnable}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link ConsoleUserInterface#executeWithProgress(Runnable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ConsoleUserInterface.executeWithProgress(Runnable)"})
  public void testExecuteWithProgressWithRunnable_thenThrowIllegalStateException() {
    // Arrange
    ConsoleUserInterface consoleUserInterface = new ConsoleUserInterface();

    Runnable runnable = mock(Runnable.class);
    doThrow(new IllegalStateException()).when(runnable).run();

    // Act and Assert
    assertThrows(
        IllegalStateException.class, () -> consoleUserInterface.executeWithProgress(runnable));
    verify(runnable).run();
  }

  /**
   * Test {@link ConsoleUserInterface#executeWithProgress(Runnable)} with {@code Runnable}.
   *
   * <ul>
   *   <li>When {@link Runnable} {@link Runnable#run()} does nothing.
   * </ul>
   *
   * <p>Method under test: {@link ConsoleUserInterface#executeWithProgress(Runnable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ConsoleUserInterface.executeWithProgress(Runnable)"})
  public void testExecuteWithProgressWithRunnable_whenRunnableRunDoesNothing() {
    // Arrange
    ConsoleUserInterface consoleUserInterface = new ConsoleUserInterface();

    Runnable runnable = mock(Runnable.class);
    doNothing().when(runnable).run();

    // Act
    consoleUserInterface.executeWithProgress(runnable);

    // Assert
    verify(runnable).run();
  }

  /**
   * Test {@link ConsoleUserInterface#executeWithProgressBlocking(String, DBRRunnableWithResult)}.
   *
   * <ul>
   *   <li>Given {@link IllegalStateException#IllegalStateException()}.
   *   <li>Then return Done.
   * </ul>
   *
   * <p>Method under test: {@link ConsoleUserInterface#executeWithProgressBlocking(String,
   * DBRRunnableWithResult)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Future ConsoleUserInterface.executeWithProgressBlocking(String, DBRRunnableWithResult)"
  })
  public void testExecuteWithProgressBlocking_givenIllegalStateException_thenReturnDone()
      throws InterruptedException, InvocationTargetException {
    // Arrange
    ConsoleUserInterface consoleUserInterface = new ConsoleUserInterface();

    DBRRunnableWithResult<Future<Object>> runnable = mock(DBRRunnableWithResult.class);
    doThrow(new IllegalStateException()).when(runnable).run(Mockito.<DBRProgressMonitor>any());

    // Act
    Future<Object> actualExecuteWithProgressBlockingResult =
        consoleUserInterface.executeWithProgressBlocking("Operation Description", runnable);

    // Assert
    verify(runnable).run(isA(DBRProgressMonitor.class));
    assertTrue(actualExecuteWithProgressBlockingResult instanceof CompletableFuture);
    assertTrue(actualExecuteWithProgressBlockingResult.isDone());
  }

  /**
   * Test {@link ConsoleUserInterface#executeWithProgressBlocking(String, DBRRunnableWithResult)}.
   *
   * <ul>
   *   <li>Then return {@link CompletableFuture#CompletableFuture()}.
   * </ul>
   *
   * <p>Method under test: {@link ConsoleUserInterface#executeWithProgressBlocking(String,
   * DBRRunnableWithResult)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Future ConsoleUserInterface.executeWithProgressBlocking(String, DBRRunnableWithResult)"
  })
  public void testExecuteWithProgressBlocking_thenReturnCompletableFuture()
      throws InterruptedException, InvocationTargetException {
    // Arrange
    ConsoleUserInterface consoleUserInterface = new ConsoleUserInterface();

    DBRRunnableWithResult<Future<Object>> runnable = mock(DBRRunnableWithResult.class);
    CompletableFuture<Object> completableFuture = new CompletableFuture<>();
    when(runnable.getResult()).thenReturn(completableFuture);
    doNothing().when(runnable).run(Mockito.<DBRProgressMonitor>any());

    // Act
    Future<Object> actualExecuteWithProgressBlockingResult =
        consoleUserInterface.executeWithProgressBlocking("Operation Description", runnable);

    // Assert
    verify(runnable).run(isA(DBRProgressMonitor.class));
    verify(runnable).getResult();
    assertTrue(actualExecuteWithProgressBlockingResult instanceof CompletableFuture);
    assertSame(completableFuture, actualExecuteWithProgressBlockingResult);
  }

  /**
   * Test {@link ConsoleUserInterface#runWithMonitor(DBRRunnableWithReturn)}.
   *
   * <ul>
   *   <li>Given {@link IllegalStateException#IllegalStateException()}.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link ConsoleUserInterface#runWithMonitor(DBRRunnableWithReturn)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object ConsoleUserInterface.runWithMonitor(DBRRunnableWithReturn)"})
  public void testRunWithMonitor_givenIllegalStateException_thenThrowIllegalStateException()
      throws DBException {
    // Arrange
    ConsoleUserInterface consoleUserInterface = new ConsoleUserInterface();

    DBRRunnableWithReturn<Object> runnable = mock(DBRRunnableWithReturn.class);
    when(runnable.runTask(Mockito.<DBRProgressMonitor>any()))
        .thenThrow(new IllegalStateException());

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> consoleUserInterface.runWithMonitor(runnable));
    verify(runnable).runTask(isA(DBRProgressMonitor.class));
  }

  /**
   * Test {@link ConsoleUserInterface#runWithMonitor(DBRRunnableWithReturn)}.
   *
   * <ul>
   *   <li>Given {@link DBPEvent#RENAME}.
   *   <li>When {@link DBRRunnableWithReturn} {@link
   *       DBRRunnableWithReturn#runTask(DBRProgressMonitor)} return {@link DBPEvent#RENAME}.
   * </ul>
   *
   * <p>Method under test: {@link ConsoleUserInterface#runWithMonitor(DBRRunnableWithReturn)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object ConsoleUserInterface.runWithMonitor(DBRRunnableWithReturn)"})
  public void testRunWithMonitor_givenRename_whenDBRRunnableWithReturnRunTaskReturnRename()
      throws DBException {
    // Arrange
    ConsoleUserInterface consoleUserInterface = new ConsoleUserInterface();

    DBRRunnableWithReturn<Object> runnable = mock(DBRRunnableWithReturn.class);
    when(runnable.runTask(Mockito.<DBRProgressMonitor>any())).thenReturn(DBPEvent.RENAME);

    // Act
    consoleUserInterface.runWithMonitor(runnable);

    // Assert
    verify(runnable).runTask(isA(DBRProgressMonitor.class));
  }

  /**
   * Test {@link ConsoleUserInterface#createLoadingService(ILoadService, ILoadVisualizer)}.
   *
   * <ul>
   *   <li>Given {@code Service Name}.
   *   <li>Then return Name is {@code Service Name}.
   * </ul>
   *
   * <p>Method under test: {@link ConsoleUserInterface#createLoadingService(ILoadService,
   * ILoadVisualizer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Job ConsoleUserInterface.createLoadingService(ILoadService, ILoadVisualizer)"
  })
  public void testCreateLoadingService_givenServiceName_thenReturnNameIsServiceName() {
    // Arrange
    ConsoleUserInterface consoleUserInterface = new ConsoleUserInterface();

    ILoadService<Object> loadingService = mock(ILoadService.class);
    when(loadingService.getServiceName()).thenReturn("Service Name");

    // Act
    Job actualCreateLoadingServiceResult =
        consoleUserInterface.createLoadingService(loadingService, mock(ILoadVisualizer.class));

    // Assert
    verify(loadingService).getServiceName();
    assertEquals("Service Name", actualCreateLoadingServiceResult.getName());
    assertNull(actualCreateLoadingServiceResult.getThread());
    assertNull(actualCreateLoadingServiceResult.getResult());
    assertNull(actualCreateLoadingServiceResult.getRule());
    assertNull(actualCreateLoadingServiceResult.getJobGroup());
    assertEquals(0, actualCreateLoadingServiceResult.getState());
    assertEquals(30, actualCreateLoadingServiceResult.getPriority());
    assertFalse(actualCreateLoadingServiceResult.isBlocking());
    assertFalse(actualCreateLoadingServiceResult.isSystem());
    assertFalse(actualCreateLoadingServiceResult.isUser());
  }

  /**
   * Test {@link ConsoleUserInterface#createLoadingService(ILoadService, ILoadVisualizer)}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link ConsoleUserInterface#createLoadingService(ILoadService,
   * ILoadVisualizer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Job ConsoleUserInterface.createLoadingService(ILoadService, ILoadVisualizer)"
  })
  public void testCreateLoadingService_thenThrowIllegalStateException() {
    // Arrange
    ConsoleUserInterface consoleUserInterface = new ConsoleUserInterface();

    ILoadService<Object> loadingService = mock(ILoadService.class);
    when(loadingService.getServiceName()).thenThrow(new IllegalStateException());

    // Act and Assert
    assertThrows(
        IllegalStateException.class,
        () ->
            consoleUserInterface.createLoadingService(loadingService, mock(ILoadVisualizer.class)));
    verify(loadingService).getServiceName();
  }

  /**
   * Test {@link ConsoleUserInterface#openFileSystemSelector(String, boolean, int, boolean,
   * String[], String)}.
   *
   * <p>Method under test: {@link ConsoleUserInterface#openFileSystemSelector(String, boolean, int,
   * boolean, String[], String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.navigator.fs.DBNPathBase ConsoleUserInterface.openFileSystemSelector(String, boolean, int, boolean, String[], String)"
  })
  public void testOpenFileSystemSelector() {
    // Arrange, Act and Assert
    assertNull(
        new ConsoleUserInterface()
            .openFileSystemSelector("Dr", true, 1, true, new String[] {"Filter Ext"}, "42"));
  }

  /**
   * Test {@link ConsoleUserInterface#readAndDispatchEvents()}.
   *
   * <p>Method under test: {@link ConsoleUserInterface#readAndDispatchEvents()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ConsoleUserInterface.readAndDispatchEvents()"})
  public void testReadAndDispatchEvents() {
    // Arrange, Act and Assert
    assertFalse(new ConsoleUserInterface().readAndDispatchEvents());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link ConsoleUserInterface}
   *   <li>{@link ConsoleUserInterface#copyTextToClipboard(String, boolean)}
   *   <li>{@link ConsoleUserInterface#initialize()}
   *   <li>{@link ConsoleUserInterface#openConnectionEditor(DBPDataSourceContainer)}
   *   <li>{@link ConsoleUserInterface#openEntityEditor(DBNNode, String)}
   *   <li>{@link ConsoleUserInterface#openEntityEditor(DBSObject)}
   *   <li>{@link ConsoleUserInterface#showInSystemExplorer(String)}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ConsoleUserInterface.<init>()",
    "void ConsoleUserInterface.copyTextToClipboard(String, boolean)",
    "void ConsoleUserInterface.initialize()",
    "void ConsoleUserInterface.openConnectionEditor(DBPDataSourceContainer)",
    "void ConsoleUserInterface.openEntityEditor(DBNNode, String)",
    "void ConsoleUserInterface.openEntityEditor(DBSObject)",
    "void ConsoleUserInterface.showInSystemExplorer(String)"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    ConsoleUserInterface actualConsoleUserInterface = new ConsoleUserInterface();
    actualConsoleUserInterface.copyTextToClipboard("Text", true);
    actualConsoleUserInterface.initialize();
    actualConsoleUserInterface.openConnectionEditor(mock(DBPDataSourceContainer.class));
    actualConsoleUserInterface.openEntityEditor(new DBNEmptyNode(), "42");
    actualConsoleUserInterface.openEntityEditor(
        new DBSDocumentConstraint(mock(DBSDocumentContainer.class)));
    actualConsoleUserInterface.showInSystemExplorer("Path");

    // Assert
    assertFalse(actualConsoleUserInterface.readAndDispatchEvents());
  }
}
