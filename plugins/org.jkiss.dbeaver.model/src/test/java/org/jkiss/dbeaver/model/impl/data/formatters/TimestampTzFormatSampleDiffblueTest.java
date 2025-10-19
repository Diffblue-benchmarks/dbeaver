package org.jkiss.dbeaver.model.impl.data.formatters;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.Locale;
import java.util.Map;
import org.jkiss.dbeaver.model.impl.sql.AbstractSQLDialect;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class TimestampTzFormatSampleDiffblueTest {
  /**
   * Test {@link TimestampTzFormatSample#getDefaultProperties(Locale)}.
   *
   * <p>Method under test: {@link TimestampTzFormatSample#getDefaultProperties(Locale)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map TimestampTzFormatSample.getDefaultProperties(Locale)"})
  public void testGetDefaultProperties() {
    // Arrange and Act
    Map<String, Object> actualDefaultProperties =
        new TimestampTzFormatSample().getDefaultProperties(AbstractSQLDialect.DEF_LOCALE);

    // Assert
    assertEquals(1, actualDefaultProperties.size());
    assertEquals(
        "yyyy-MM-dd HH:mm:ss.SSS Z",
        actualDefaultProperties.get(DateTimeDataFormatter.PROP_PATTERN));
  }
}
