package org.jkiss.dbeaver.model.runtime;

import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.exec.DBCSession;
import org.jkiss.dbeaver.model.impl.local.LocalStatement;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBRBlockingObjectDiffblueTest {
  /**
   * Test {@link DBRBlockingObject#getBlockThread()}.
   *
   * <p>Method under test: {@link DBRBlockingObject#getBlockThread()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.Thread DBRBlockingObject.getBlockThread()"})
  public void testGetBlockThread() {
    // Arrange, Act and Assert
    assertNull(new LocalStatement(mock(DBCSession.class), "Text").getBlockThread());
  }
}
