package org.jkiss.dbeaver.model.data.hints.standard;

import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.EnumSet;
import org.jkiss.dbeaver.model.DBPEvent;
import org.jkiss.dbeaver.model.data.DBDAttributeBinding;
import org.jkiss.dbeaver.model.data.DBDResultSetModel;
import org.jkiss.dbeaver.model.data.DBDValueRow;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class VoidHintProviderDiffblueTest {
  /**
   * Test {@link VoidHintProvider#getCellHints(DBDResultSetModel, DBDAttributeBinding, DBDValueRow,
   * Object, EnumSet, int)}.
   *
   * <p>Method under test: {@link VoidHintProvider#getCellHints(DBDResultSetModel,
   * DBDAttributeBinding, DBDValueRow, Object, EnumSet, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.data.hints.DBDValueHint[] VoidHintProvider.getCellHints(DBDResultSetModel, DBDAttributeBinding, DBDValueRow, Object, EnumSet, int)"
  })
  public void testGetCellHints() {
    // Arrange, Act and Assert
    assertNull(
        VoidHintProvider.INSTANCE.getCellHints(
            mock(DBDResultSetModel.class),
            null,
            mock(DBDValueRow.class),
            DBPEvent.RENAME,
            null,
            1));
  }
}
