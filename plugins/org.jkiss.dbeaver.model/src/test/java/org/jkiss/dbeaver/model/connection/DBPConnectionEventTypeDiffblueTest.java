package org.jkiss.dbeaver.model.connection;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBPConnectionEventTypeDiffblueTest {
  /**
   * Test {@link DBPConnectionEventType#getTitle()}.
   *
   * <p>Method under test: {@link DBPConnectionEventType#getTitle()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String DBPConnectionEventType.getTitle()"})
  public void testGetTitle() {
    // Arrange, Act and Assert
    assertEquals("Before Connect", DBPConnectionEventType.valueOf("BEFORE_CONNECT").getTitle());
  }
}
