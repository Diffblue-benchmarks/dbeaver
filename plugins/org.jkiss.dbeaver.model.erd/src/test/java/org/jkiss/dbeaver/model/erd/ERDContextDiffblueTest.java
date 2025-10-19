package org.jkiss.dbeaver.model.erd;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.model.DBPImage;
import org.jkiss.dbeaver.model.app.DBPPlatform;
import org.jkiss.dbeaver.model.navigator.DBNModel;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ERDContextDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link ERDContext#ERDContext(DBRProgressMonitor, DBPDataSourceContainer, DBNModel)}
   *   <li>{@link ERDContext#getDataSourceContainer()}
   *   <li>{@link ERDContext#getIcons()}
   *   <li>{@link ERDContext#getMonitor()}
   *   <li>{@link ERDContext#getNavigatorModel()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ERDContext.<init>(DBRProgressMonitor, DBPDataSourceContainer, DBNModel)",
    "DBPDataSourceContainer ERDContext.getDataSourceContainer()",
    "List ERDContext.getIcons()",
    "DBRProgressMonitor ERDContext.getMonitor()",
    "DBNModel ERDContext.getNavigatorModel()"
  })
  public void testGettersAndSetters() {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    DBPPlatform platform = mock(DBPPlatform.class);
    DBNModel navigatorModel = new DBNModel(platform, new ArrayList<>());

    // Act
    ERDContext actualErdContext = new ERDContext(monitor, dataSourceContainer, navigatorModel);
    DBPDataSourceContainer actualDataSourceContainer = actualErdContext.getDataSourceContainer();
    List<String> actualIcons = actualErdContext.getIcons();
    DBRProgressMonitor actualMonitor = actualErdContext.getMonitor();
    DBNModel actualNavigatorModel = actualErdContext.getNavigatorModel();

    // Assert
    assertTrue(actualIcons.isEmpty());
    assertSame(navigatorModel, actualNavigatorModel);
    assertSame(monitor, actualMonitor);
    assertSame(dataSourceContainer, actualDataSourceContainer);
  }

  /**
   * Test {@link ERDContext#getIconIndex(DBPImage)}.
   *
   * <p>Method under test: {@link ERDContext#getIconIndex(DBPImage)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int ERDContext.getIconIndex(DBPImage)"})
  public void testGetIconIndex() {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    DBPPlatform platform = mock(DBPPlatform.class);
    DBNModel navigatorModel = new DBNModel(platform, new ArrayList<>());

    ERDContext erdContext = new ERDContext(monitor, dataSourceContainer, navigatorModel);

    DBPImage image = mock(DBPImage.class);
    when(image.getLocation()).thenReturn("Location");

    // Act
    int actualIconIndex = erdContext.getIconIndex(image);

    // Assert
    verify(image).getLocation();
    List<String> icons = erdContext.getIcons();
    assertEquals(1, icons.size());
    assertEquals("Location", icons.get(0));
    assertEquals(0, actualIconIndex);
  }

  /**
   * Test {@link ERDContext#addElementInfo(ERDElement)}.
   *
   * <p>Method under test: {@link ERDContext#addElementInfo(ERDElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int ERDContext.addElementInfo(ERDElement)"})
  public void testAddElementInfo() {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    DBPPlatform platform = mock(DBPPlatform.class);
    DBNModel navigatorModel = new DBNModel(platform, new ArrayList<>());

    ERDContext erdContext = new ERDContext(monitor, dataSourceContainer, navigatorModel);

    // Act
    int actualAddElementInfoResult =
        erdContext.addElementInfo(new ERDEntity(mock(DBPDataSource.class)));

    // Assert
    assertEquals(0, actualAddElementInfoResult);
  }

  /**
   * Test {@link ERDContext#getElementInfo(ERDElement)}.
   *
   * <p>Method under test: {@link ERDContext#getElementInfo(ERDElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int ERDContext.getElementInfo(ERDElement)"})
  public void testGetElementInfo() {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    DBPPlatform platform = mock(DBPPlatform.class);
    DBNModel navigatorModel = new DBNModel(platform, new ArrayList<>());

    ERDContext erdContext = new ERDContext(monitor, dataSourceContainer, navigatorModel);

    // Act
    int actualElementInfo = erdContext.getElementInfo(new ERDEntity(mock(DBPDataSource.class)));

    // Assert
    assertEquals(-1, actualElementInfo);
  }
}
