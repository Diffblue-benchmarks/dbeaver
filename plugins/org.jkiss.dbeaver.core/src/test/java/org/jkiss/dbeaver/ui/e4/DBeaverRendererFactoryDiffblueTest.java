package org.jkiss.dbeaver.ui.e4;

import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.eclipse.e4.ui.model.application.ui.MUIElement;
import org.jkiss.dbeaver.model.DBPEvent;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBeaverRendererFactoryDiffblueTest {
  /**
   * Test {@link DBeaverRendererFactory#getRenderer(MUIElement, Object)}.
   *
   * <ul>
   *   <li>When {@link MUIElement}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBeaverRendererFactory#getRenderer(MUIElement, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.eclipse.e4.ui.internal.workbench.swt.AbstractPartRenderer DBeaverRendererFactory.getRenderer(MUIElement, Object)"
  })
  public void testGetRenderer_whenMUIElement_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(new DBeaverRendererFactory().getRenderer(mock(MUIElement.class), DBPEvent.RENAME));
  }
}
