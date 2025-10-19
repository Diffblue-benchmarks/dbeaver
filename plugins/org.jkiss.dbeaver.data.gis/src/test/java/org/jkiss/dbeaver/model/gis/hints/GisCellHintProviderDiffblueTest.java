package org.jkiss.dbeaver.model.gis.hints;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.EnumSet;
import org.jkiss.dbeaver.model.data.DBDAttributeBinding;
import org.jkiss.dbeaver.model.data.DBDResultSetModel;
import org.jkiss.dbeaver.model.data.DBDValueRow;
import org.jkiss.dbeaver.model.data.hints.DBDValueHint;
import org.jkiss.dbeaver.model.data.hints.DBDValueHint.HintStyle;
import org.jkiss.dbeaver.model.data.hints.DBDValueHint.HintType;
import org.jkiss.dbeaver.model.data.hints.ValueHintText;
import org.jkiss.dbeaver.model.gis.DBGeometry;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class GisCellHintProviderDiffblueTest {
  /**
   * Test {@link GisCellHintProvider#getCellHints(DBDResultSetModel, DBDAttributeBinding,
   * DBDValueRow, Object, EnumSet, int)}.
   *
   * <ul>
   *   <li>Then first element return {@link ValueHintText}.
   * </ul>
   *
   * <p>Method under test: {@link GisCellHintProvider#getCellHints(DBDResultSetModel,
   * DBDAttributeBinding, DBDValueRow, Object, EnumSet, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBDValueHint[] GisCellHintProvider.getCellHints(DBDResultSetModel, DBDAttributeBinding, DBDValueRow, Object, EnumSet, int)"
  })
  public void testGetCellHints_thenFirstElementReturnValueHintText() {
    // Arrange
    GisCellHintProvider gisCellHintProvider = new GisCellHintProvider();
    DBDResultSetModel model = mock(DBDResultSetModel.class);
    DBDValueRow row = mock(DBDValueRow.class);

    // Act
    DBDValueHint[] actualCellHints =
        gisCellHintProvider.getCellHints(model, null, row, new DBGeometry("Raw Value", 1), null, 1);

    // Assert
    DBDValueHint dbdValueHint = actualCellHints[0];
    assertTrue(dbdValueHint instanceof ValueHintText);
    assertEquals("SRID", dbdValueHint.getHintDescription());
    assertEquals("SRS:1", dbdValueHint.getHintText());
    assertNull(dbdValueHint.getHintIcon());
    assertEquals(0, dbdValueHint.getHintOptions());
    assertEquals(1, actualCellHints.length);
    assertEquals(HintStyle.NORMAL, dbdValueHint.getHintStyle());
    assertEquals(HintType.STRING, dbdValueHint.getHintType());
  }

  /**
   * Test {@link GisCellHintProvider#getCellHints(DBDResultSetModel, DBDAttributeBinding,
   * DBDValueRow, Object, EnumSet, int)}.
   *
   * <ul>
   *   <li>When {@link DBGeometry#DBGeometry()}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link GisCellHintProvider#getCellHints(DBDResultSetModel,
   * DBDAttributeBinding, DBDValueRow, Object, EnumSet, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBDValueHint[] GisCellHintProvider.getCellHints(DBDResultSetModel, DBDAttributeBinding, DBDValueRow, Object, EnumSet, int)"
  })
  public void testGetCellHints_whenDBGeometry_thenReturnNull() {
    // Arrange
    GisCellHintProvider gisCellHintProvider = new GisCellHintProvider();
    DBDResultSetModel model = mock(DBDResultSetModel.class);
    DBDValueRow row = mock(DBDValueRow.class);

    // Act and Assert
    assertNull(gisCellHintProvider.getCellHints(model, null, row, new DBGeometry(), null, 1));
  }

  /**
   * Test {@link GisCellHintProvider#getCellHints(DBDResultSetModel, DBDAttributeBinding,
   * DBDValueRow, Object, EnumSet, int)}.
   *
   * <ul>
   *   <li>When {@code Value}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link GisCellHintProvider#getCellHints(DBDResultSetModel,
   * DBDAttributeBinding, DBDValueRow, Object, EnumSet, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBDValueHint[] GisCellHintProvider.getCellHints(DBDResultSetModel, DBDAttributeBinding, DBDValueRow, Object, EnumSet, int)"
  })
  public void testGetCellHints_whenValue_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(
        new GisCellHintProvider()
            .getCellHints(
                mock(DBDResultSetModel.class), null, mock(DBDValueRow.class), "Value", null, 1));
  }
}
