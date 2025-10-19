package org.jkiss.junit.osgi.delegate;

import static org.junit.Assert.assertThrows;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import javax.management.loading.MLet;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.notification.RunListener;

public class ClassTransferHandlerDiffblueTest {
  /**
   * Test {@link ClassTransferHandler#transfer(Object, ClassLoader)}.
   *
   * <ul>
   *   <li>When {@link RunListener} (default constructor).
   *   <li>Then throw {@link RuntimeException}.
   * </ul>
   *
   * <p>Method under test: {@link ClassTransferHandler#transfer(Object, ClassLoader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object ClassTransferHandler.transfer(Object, ClassLoader)"})
  public void testTransfer_whenRunListener_thenThrowRuntimeException() {
    // Arrange
    RunListener runListener = new RunListener();

    // Act and Assert
    assertThrows(
        RuntimeException.class, () -> ClassTransferHandler.transfer(runListener, new MLet()));
  }
}
