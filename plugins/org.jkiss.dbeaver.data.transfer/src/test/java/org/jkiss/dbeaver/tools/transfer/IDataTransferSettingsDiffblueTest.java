package org.jkiss.dbeaver.tools.transfer;

import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.tools.transfer.database.DatabaseConsumerSettings;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class IDataTransferSettingsDiffblueTest {
  /**
   * Test {@link IDataTransferSettings#prepareRuntimeParameters()}.
   *
   * <p>Method under test: {@link IDataTransferSettings#prepareRuntimeParameters()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.Object IDataTransferSettings.prepareRuntimeParameters()"})
  public void testPrepareRuntimeParameters() {
    // Arrange, Act and Assert
    assertNull(new DatabaseConsumerSettings().prepareRuntimeParameters());
  }
}
