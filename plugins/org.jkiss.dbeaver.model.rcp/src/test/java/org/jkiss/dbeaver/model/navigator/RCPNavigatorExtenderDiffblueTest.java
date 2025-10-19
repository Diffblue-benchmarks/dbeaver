package org.jkiss.dbeaver.model.navigator;

import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.navigator.fs.DBNFileSystems;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class RCPNavigatorExtenderDiffblueTest {
  /**
   * Test {@link RCPNavigatorExtender#getExtraNodes(DBNNode)}.
   *
   * <p>Method under test: {@link RCPNavigatorExtender#getExtraNodes(DBNNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBNNode[] RCPNavigatorExtender.getExtraNodes(DBNNode)"})
  public void testGetExtraNodes() {
    // Arrange
    RCPNavigatorExtender rcpNavigatorExtender = new RCPNavigatorExtender();

    // Act
    DBNNode[] actualExtraNodes = rcpNavigatorExtender.getExtraNodes(new DBNFileSystems(null));

    // Assert
    assertNull(actualExtraNodes);
  }
}
