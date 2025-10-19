package org.jkiss.dbeaver.model.navigator;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import org.jkiss.dbeaver.model.app.DBPPlatform;
import org.jkiss.dbeaver.model.app.DBPProject;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DesktopNavigatorModelDiffblueTest {
  /**
   * Test {@link DesktopNavigatorModel#DesktopNavigatorModel(DBPPlatform, List)}.
   *
   * <p>Method under test: {@link DesktopNavigatorModel#DesktopNavigatorModel(DBPPlatform, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DesktopNavigatorModel.<init>(DBPPlatform, List)"})
  public void testNewDesktopNavigatorModel() {
    // Arrange
    DBPPlatform platform = mock(DBPPlatform.class);
    ArrayList<DBPProject> modelProjects = new ArrayList<>();

    // Act
    DesktopNavigatorModel actualDesktopNavigatorModel =
        new DesktopNavigatorModel(platform, modelProjects);

    // Assert
    assertNull(actualDesktopNavigatorModel.getModelAuthContext());
    assertNull(actualDesktopNavigatorModel.getRoot());
    List<? extends DBPProject> modelProjects2 = actualDesktopNavigatorModel.getModelProjects();
    assertTrue(modelProjects2.isEmpty());
    assertSame(modelProjects, modelProjects2);
    assertSame(platform, actualDesktopNavigatorModel.getPlatform());
  }
}
