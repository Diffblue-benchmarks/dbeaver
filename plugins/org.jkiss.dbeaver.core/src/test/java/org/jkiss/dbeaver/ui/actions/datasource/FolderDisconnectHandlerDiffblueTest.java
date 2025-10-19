package org.jkiss.dbeaver.ui.actions.datasource;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class FolderDisconnectHandlerDiffblueTest {
  /**
   * Test {@link FolderDisconnectHandler#execute(ExecutionEvent)}.
   *
   * <ul>
   *   <li>Given {@link FolderDisconnectHandler} (default constructor).
   *   <li>When {@link ExecutionEvent#ExecutionEvent()}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link FolderDisconnectHandler#execute(ExecutionEvent)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.Object FolderDisconnectHandler.execute(ExecutionEvent)"})
  public void testExecute_givenFolderDisconnectHandler_whenExecutionEvent_thenReturnNull()
      throws ExecutionException {
    // Arrange
    FolderDisconnectHandler folderDisconnectHandler = new FolderDisconnectHandler();

    // Act and Assert
    assertNull(folderDisconnectHandler.execute(new ExecutionEvent()));
  }

  /**
   * Test new {@link FolderDisconnectHandler} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link FolderDisconnectHandler}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void FolderDisconnectHandler.<init>()"})
  public void testNewFolderDisconnectHandler() {
    // Arrange, Act and Assert
    assertTrue(new FolderDisconnectHandler().isEnabled());
  }
}
