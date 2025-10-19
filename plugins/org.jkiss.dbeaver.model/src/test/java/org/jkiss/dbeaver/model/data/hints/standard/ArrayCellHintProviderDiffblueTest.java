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
import org.jkiss.dbeaver.model.impl.data.DBDDocumentXML;
import org.jkiss.dbeaver.model.impl.data.DBDValueError;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ArrayCellHintProviderDiffblueTest {
  /**
   * Test {@link ArrayCellHintProvider#getCellHints(DBDResultSetModel, DBDAttributeBinding,
   * DBDValueRow, Object, EnumSet, int)}.
   *
   * <ul>
   *   <li>When {@link DBDDocumentXML#DBDDocumentXML(Document)} with document is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link ArrayCellHintProvider#getCellHints(DBDResultSetModel,
   * DBDAttributeBinding, DBDValueRow, Object, EnumSet, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.data.hints.DBDValueHint[] ArrayCellHintProvider.getCellHints(DBDResultSetModel, DBDAttributeBinding, DBDValueRow, Object, EnumSet, int)"
  })
  public void testGetCellHints_whenDBDDocumentXMLWithDocumentIsNull() {
    // Arrange
    ArrayCellHintProvider arrayCellHintProvider = new ArrayCellHintProvider();
    DBDResultSetModel model = mock(DBDResultSetModel.class);
    DBDValueRow row = mock(DBDValueRow.class);

    // Act and Assert
    assertNull(
        arrayCellHintProvider.getCellHints(model, null, row, new DBDDocumentXML(null), null, 1));
  }

  /**
   * Test {@link ArrayCellHintProvider#getCellHints(DBDResultSetModel, DBDAttributeBinding,
   * DBDValueRow, Object, EnumSet, int)}.
   *
   * <ul>
   *   <li>When {@link DBDValueError#DBDValueError(Throwable)} with error is {@link
   *       Throwable#Throwable()}.
   * </ul>
   *
   * <p>Method under test: {@link ArrayCellHintProvider#getCellHints(DBDResultSetModel,
   * DBDAttributeBinding, DBDValueRow, Object, EnumSet, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.data.hints.DBDValueHint[] ArrayCellHintProvider.getCellHints(DBDResultSetModel, DBDAttributeBinding, DBDValueRow, Object, EnumSet, int)"
  })
  public void testGetCellHints_whenDBDValueErrorWithErrorIsThrowable() {
    // Arrange
    ArrayCellHintProvider arrayCellHintProvider = new ArrayCellHintProvider();
    DBDResultSetModel model = mock(DBDResultSetModel.class);
    DBDValueRow row = mock(DBDValueRow.class);

    // Act and Assert
    assertNull(
        arrayCellHintProvider.getCellHints(
            model, null, row, new DBDValueError(new Throwable()), null, 1));
  }

  /**
   * Test {@link ArrayCellHintProvider#getCellHints(DBDResultSetModel, DBDAttributeBinding,
   * DBDValueRow, Object, EnumSet, int)}.
   *
   * <ul>
   *   <li>When one.
   * </ul>
   *
   * <p>Method under test: {@link ArrayCellHintProvider#getCellHints(DBDResultSetModel,
   * DBDAttributeBinding, DBDValueRow, Object, EnumSet, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.data.hints.DBDValueHint[] ArrayCellHintProvider.getCellHints(DBDResultSetModel, DBDAttributeBinding, DBDValueRow, Object, EnumSet, int)"
  })
  public void testGetCellHints_whenOne() {
    // Arrange, Act and Assert
    assertNull(
        new ArrayCellHintProvider()
            .getCellHints(
                mock(DBDResultSetModel.class), null, mock(DBDValueRow.class), null, null, 1));
  }

  /**
   * Test {@link ArrayCellHintProvider#getCellHints(DBDResultSetModel, DBDAttributeBinding,
   * DBDValueRow, Object, EnumSet, int)}.
   *
   * <ul>
   *   <li>When {@link DBPEvent#RENAME}.
   * </ul>
   *
   * <p>Method under test: {@link ArrayCellHintProvider#getCellHints(DBDResultSetModel,
   * DBDAttributeBinding, DBDValueRow, Object, EnumSet, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.data.hints.DBDValueHint[] ArrayCellHintProvider.getCellHints(DBDResultSetModel, DBDAttributeBinding, DBDValueRow, Object, EnumSet, int)"
  })
  public void testGetCellHints_whenRename() {
    // Arrange, Act and Assert
    assertNull(
        new ArrayCellHintProvider()
            .getCellHints(
                mock(DBDResultSetModel.class),
                null,
                mock(DBDValueRow.class),
                DBPEvent.RENAME,
                null,
                1));
  }

  /**
   * Test {@link ArrayCellHintProvider#getCellHints(DBDResultSetModel, DBDAttributeBinding,
   * DBDValueRow, Object, EnumSet, int)}.
   *
   * <ul>
   *   <li>When {@link Short#SIZE}.
   * </ul>
   *
   * <p>Method under test: {@link ArrayCellHintProvider#getCellHints(DBDResultSetModel,
   * DBDAttributeBinding, DBDValueRow, Object, EnumSet, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.data.hints.DBDValueHint[] ArrayCellHintProvider.getCellHints(DBDResultSetModel, DBDAttributeBinding, DBDValueRow, Object, EnumSet, int)"
  })
  public void testGetCellHints_whenSize() {
    // Arrange, Act and Assert
    assertNull(
        new ArrayCellHintProvider()
            .getCellHints(
                mock(DBDResultSetModel.class),
                null,
                mock(DBDValueRow.class),
                DBPEvent.RENAME,
                null,
                Short.SIZE));
  }
}
