package org.jkiss.dbeaver.tools.transfer;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.IOException;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.jkiss.dbeaver.tools.transfer.database.DatabaseTransferConsumer;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class IDataTransferNodeDiffblueTest {
  /**
   * Test {@link IDataTransferNode#getObjectFullName(DBRProgressMonitor)}.
   *
   * <p>Method under test: {@link IDataTransferNode#getObjectFullName(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String IDataTransferNode.getObjectFullName(DBRProgressMonitor)"})
  public void testGetObjectFullName() throws IOException {
    // Arrange
    DatabaseTransferConsumer databaseTransferConsumer = new DatabaseTransferConsumer();

    // Act and Assert
    assertEquals("?", databaseTransferConsumer.getObjectFullName(new LoggingProgressMonitor()));
  }
}
