package org.jkiss.dbeaver.model.data.hints.standard;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.EnumSet;
import org.jkiss.dbeaver.model.DBPDataKind;
import org.jkiss.dbeaver.model.DBPEvent;
import org.jkiss.dbeaver.model.data.DBDAttributeBinding;
import org.jkiss.dbeaver.model.data.DBDAttributeBindingCustom;
import org.jkiss.dbeaver.model.data.DBDResultSetModel;
import org.jkiss.dbeaver.model.data.DBDValueRow;
import org.jkiss.dbeaver.model.data.hints.DBDValueHint;
import org.jkiss.dbeaver.model.data.hints.DBDValueHint.HintStyle;
import org.jkiss.dbeaver.model.data.hints.DBDValueHint.HintType;
import org.jkiss.dbeaver.model.data.hints.ValueHintText;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class TimeZoneCellHintProviderDiffblueTest {
  /**
   * Test {@link TimeZoneCellHintProvider#getCellHints(DBDResultSetModel, DBDAttributeBinding,
   * DBDValueRow, Object, EnumSet, int)}.
   *
   * <ul>
   *   <li>Given {@code BOOLEAN}.
   * </ul>
   *
   * <p>Method under test: {@link TimeZoneCellHintProvider#getCellHints(DBDResultSetModel,
   * DBDAttributeBinding, DBDValueRow, Object, EnumSet, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBDValueHint[] TimeZoneCellHintProvider.getCellHints(DBDResultSetModel, DBDAttributeBinding, DBDValueRow, Object, EnumSet, int)"
  })
  public void testGetCellHints_givenBoolean() {
    // Arrange
    TimeZoneCellHintProvider timeZoneCellHintProvider = new TimeZoneCellHintProvider();
    DBDResultSetModel model = mock(DBDResultSetModel.class);

    DBDAttributeBindingCustom attribute = mock(DBDAttributeBindingCustom.class);
    when(attribute.getDataKind()).thenReturn(DBPDataKind.BOOLEAN);

    // Act
    DBDValueHint[] actualCellHints =
        timeZoneCellHintProvider.getCellHints(
            model, attribute, mock(DBDValueRow.class), DBPEvent.RENAME, null, 1);

    // Assert
    verify(attribute).getDataKind();
    assertNull(actualCellHints);
  }

  /**
   * Test {@link TimeZoneCellHintProvider#getCellHints(DBDResultSetModel, DBDAttributeBinding,
   * DBDValueRow, Object, EnumSet, int)}.
   *
   * <ul>
   *   <li>Given {@code DATETIME}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link TimeZoneCellHintProvider#getCellHints(DBDResultSetModel,
   * DBDAttributeBinding, DBDValueRow, Object, EnumSet, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBDValueHint[] TimeZoneCellHintProvider.getCellHints(DBDResultSetModel, DBDAttributeBinding, DBDValueRow, Object, EnumSet, int)"
  })
  public void testGetCellHints_givenDatetime_thenReturnNull() {
    // Arrange
    TimeZoneCellHintProvider timeZoneCellHintProvider = new TimeZoneCellHintProvider();
    DBDResultSetModel model = mock(DBDResultSetModel.class);

    DBDAttributeBindingCustom attribute = mock(DBDAttributeBindingCustom.class);
    when(attribute.getDataKind()).thenReturn(DBPDataKind.DATETIME);

    // Act
    DBDValueHint[] actualCellHints =
        timeZoneCellHintProvider.getCellHints(
            model, attribute, mock(DBDValueRow.class), DBPEvent.RENAME, null, 1);

    // Assert
    verify(attribute).getDataKind();
    assertNull(actualCellHints);
  }

  /**
   * Test {@link TimeZoneCellHintProvider#getCellHints(DBDResultSetModel, DBDAttributeBinding,
   * DBDValueRow, Object, EnumSet, int)}.
   *
   * <ul>
   *   <li>Then first element return {@link ValueHintText}.
   * </ul>
   *
   * <p>Method under test: {@link TimeZoneCellHintProvider#getCellHints(DBDResultSetModel,
   * DBDAttributeBinding, DBDValueRow, Object, EnumSet, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBDValueHint[] TimeZoneCellHintProvider.getCellHints(DBDResultSetModel, DBDAttributeBinding, DBDValueRow, Object, EnumSet, int)"
  })
  public void testGetCellHints_thenFirstElementReturnValueHintText() {
    // Arrange
    TimeZoneCellHintProvider timeZoneCellHintProvider = new TimeZoneCellHintProvider();
    DBDResultSetModel model = mock(DBDResultSetModel.class);

    DBDAttributeBindingCustom attribute = mock(DBDAttributeBindingCustom.class);
    when(attribute.getDataKind()).thenReturn(DBPDataKind.DATETIME);
    DBDValueRow row = mock(DBDValueRow.class);

    // Act
    DBDValueHint[] actualCellHints =
        timeZoneCellHintProvider.getCellHints(
            model,
            attribute,
            row,
            Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()),
            null,
            1);

    // Assert
    verify(attribute).getDataKind();
    DBDValueHint dbdValueHint = actualCellHints[0];
    assertTrue(dbdValueHint instanceof ValueHintText);
    assertEquals("Timezone", dbdValueHint.getHintDescription());
    assertNull(dbdValueHint.getHintIcon());
    assertEquals(0, dbdValueHint.getHintOptions());
    assertEquals(1, actualCellHints.length);
    assertEquals(HintStyle.NORMAL, dbdValueHint.getHintStyle());
    assertEquals(HintType.STRING, dbdValueHint.getHintType());
  }

  /**
   * Test {@link TimeZoneCellHintProvider#toCustomID(int)}.
   *
   * <ul>
   *   <li>When {@code 60000}.
   *   <li>Then return {@code GMT+00:01}.
   * </ul>
   *
   * <p>Method under test: {@link TimeZoneCellHintProvider#toCustomID(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String TimeZoneCellHintProvider.toCustomID(int)"})
  public void testToCustomID_when60000_thenReturnGmt0001() {
    // Arrange, Act and Assert
    assertEquals("GMT+00:01", TimeZoneCellHintProvider.toCustomID(60000));
  }

  /**
   * Test {@link TimeZoneCellHintProvider#toCustomID(int)}.
   *
   * <ul>
   *   <li>When {@link Integer#MIN_VALUE}.
   *   <li>Then return {@code GMT-k6:31}.
   * </ul>
   *
   * <p>Method under test: {@link TimeZoneCellHintProvider#toCustomID(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String TimeZoneCellHintProvider.toCustomID(int)"})
  public void testToCustomID_whenMin_value_thenReturnGmtK631() {
    // Arrange, Act and Assert
    assertEquals("GMT-k6:31", TimeZoneCellHintProvider.toCustomID(Integer.MIN_VALUE));
  }

  /**
   * Test {@link TimeZoneCellHintProvider#toCustomID(int)}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then return {@code GMT+00:00}.
   * </ul>
   *
   * <p>Method under test: {@link TimeZoneCellHintProvider#toCustomID(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String TimeZoneCellHintProvider.toCustomID(int)"})
  public void testToCustomID_whenOne_thenReturnGmt0000() {
    // Arrange, Act and Assert
    assertEquals("GMT+00:00", TimeZoneCellHintProvider.toCustomID(1));
  }
}
