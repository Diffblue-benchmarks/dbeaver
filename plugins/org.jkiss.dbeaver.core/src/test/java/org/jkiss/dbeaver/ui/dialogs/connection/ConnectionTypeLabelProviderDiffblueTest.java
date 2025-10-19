package org.jkiss.dbeaver.ui.dialogs.connection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.DBPEvent;
import org.jkiss.dbeaver.model.connection.DBPConnectionType;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ConnectionTypeLabelProviderDiffblueTest {
  /**
   * Test {@link ConnectionTypeLabelProvider#getText(Object)}.
   *
   * <ul>
   *   <li>Given {@link ConnectionTypeLabelProvider} (default constructor).
   *   <li>Then return {@code Name}.
   * </ul>
   *
   * <p>Method under test: {@link ConnectionTypeLabelProvider#getText(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String ConnectionTypeLabelProvider.getText(Object)"})
  public void testGetText_givenConnectionTypeLabelProvider_thenReturnName() {
    // Arrange
    ConnectionTypeLabelProvider connectionTypeLabelProvider = new ConnectionTypeLabelProvider();
    DBPConnectionType dbpConnectionType =
        new DBPConnectionType(
            "42",
            "Name",
            "Color",
            "The characteristics of someone or something",
            true,
            true,
            true,
            true,
            true,
            true,
            1,
            true,
            1);

    // Act
    String actualText = connectionTypeLabelProvider.getText(dbpConnectionType);

    // Assert
    assertEquals("Name", actualText);
  }

  /**
   * Test {@link ConnectionTypeLabelProvider#getForeground(Object)}.
   *
   * <p>Method under test: {@link ConnectionTypeLabelProvider#getForeground(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.eclipse.swt.graphics.Color ConnectionTypeLabelProvider.getForeground(Object)"
  })
  public void testGetForeground() {
    // Arrange, Act and Assert
    assertNull(new ConnectionTypeLabelProvider().getForeground(DBPEvent.RENAME));
  }
}
