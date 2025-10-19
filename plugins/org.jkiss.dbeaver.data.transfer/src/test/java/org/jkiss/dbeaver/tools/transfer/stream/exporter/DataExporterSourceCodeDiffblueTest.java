package org.jkiss.dbeaver.tools.transfer.stream.exporter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashMap;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.tools.transfer.stream.IStreamDataExporterSite;
import org.jkiss.dbeaver.tools.transfer.stream.exporter.DataExporterSourceCode.ProgramLanguages;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DataExporterSourceCodeDiffblueTest {
  /**
   * Test {@link DataExporterSourceCode#init(IStreamDataExporterSite)}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@code formatDateISOPHP} is {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link DataExporterSourceCode#init(IStreamDataExporterSite)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataExporterSourceCode.init(IStreamDataExporterSite)"})
  public void testInit_givenHashMapFormatDateISOPHPIs42() throws DBException {
    // Arrange
    DataExporterSourceCode dataExporterSourceCode = new DataExporterSourceCode();

    HashMap<String, Object> stringObjectMap = new HashMap<>();
    stringObjectMap.put("formatDateISOPHP", "42");
    stringObjectMap.put("quoteChar", "\"");

    IStreamDataExporterSite site = mock(IStreamDataExporterSite.class);
    when(site.getProperties()).thenReturn(stringObjectMap);

    // Act
    dataExporterSourceCode.init(site);

    // Assert
    verify(site, atLeast(1)).getProperties();
    assertNull(dataExporterSourceCode.getOutputStream());
    assertNull(dataExporterSourceCode.getWriter());
    assertSame(site, dataExporterSourceCode.getSite());
  }

  /**
   * Test {@link DataExporterSourceCode#init(IStreamDataExporterSite)}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@code formatDateISOPHP} is {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link DataExporterSourceCode#init(IStreamDataExporterSite)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataExporterSourceCode.init(IStreamDataExporterSite)"})
  public void testInit_givenHashMapFormatDateISOPHPIs422() throws DBException {
    // Arrange
    DataExporterSourceCode dataExporterSourceCode = new DataExporterSourceCode();

    HashMap<String, Object> stringObjectMap = new HashMap<>();
    stringObjectMap.put("language", "42");
    stringObjectMap.put("formatDateISOPHP", "42");
    stringObjectMap.put("quoteChar", "\"");

    IStreamDataExporterSite site = mock(IStreamDataExporterSite.class);
    when(site.getProperties()).thenReturn(stringObjectMap);

    // Act
    dataExporterSourceCode.init(site);

    // Assert
    verify(site, atLeast(1)).getProperties();
    assertNull(dataExporterSourceCode.getOutputStream());
    assertNull(dataExporterSourceCode.getWriter());
    assertSame(site, dataExporterSourceCode.getSite());
  }

  /**
   * Test {@link DataExporterSourceCode#init(IStreamDataExporterSite)}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@code formatDateISOPHP} is empty string.
   * </ul>
   *
   * <p>Method under test: {@link DataExporterSourceCode#init(IStreamDataExporterSite)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataExporterSourceCode.init(IStreamDataExporterSite)"})
  public void testInit_givenHashMapFormatDateISOPHPIsEmptyString() throws DBException {
    // Arrange
    DataExporterSourceCode dataExporterSourceCode = new DataExporterSourceCode();

    HashMap<String, Object> stringObjectMap = new HashMap<>();
    stringObjectMap.put("language", "42");
    stringObjectMap.put("formatDateISOPHP", "");
    stringObjectMap.put("quoteChar", "\"");

    IStreamDataExporterSite site = mock(IStreamDataExporterSite.class);
    when(site.getProperties()).thenReturn(stringObjectMap);

    // Act
    dataExporterSourceCode.init(site);

    // Assert
    verify(site, atLeast(1)).getProperties();
    assertNull(dataExporterSourceCode.getOutputStream());
    assertNull(dataExporterSourceCode.getWriter());
    assertSame(site, dataExporterSourceCode.getSite());
  }

  /**
   * Test {@link DataExporterSourceCode#init(IStreamDataExporterSite)}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@code formatDateISOPHP} is {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DataExporterSourceCode#init(IStreamDataExporterSite)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataExporterSourceCode.init(IStreamDataExporterSite)"})
  public void testInit_givenHashMapFormatDateISOPHPIsTrue() throws DBException {
    // Arrange
    DataExporterSourceCode dataExporterSourceCode = new DataExporterSourceCode();

    HashMap<String, Object> stringObjectMap = new HashMap<>();
    stringObjectMap.put("language", "42");
    stringObjectMap.put("formatDateISOPHP", true);
    stringObjectMap.put("quoteChar", "\"");

    IStreamDataExporterSite site = mock(IStreamDataExporterSite.class);
    when(site.getProperties()).thenReturn(stringObjectMap);

    // Act
    dataExporterSourceCode.init(site);

    // Assert
    verify(site, atLeast(1)).getProperties();
    assertNull(dataExporterSourceCode.getOutputStream());
    assertNull(dataExporterSourceCode.getWriter());
    assertSame(site, dataExporterSourceCode.getSite());
  }

  /**
   * Test {@link DataExporterSourceCode#init(IStreamDataExporterSite)}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@code language} is one.
   *   <li>Then {@link DataExporterSourceCode} (default constructor) OutputStream is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DataExporterSourceCode#init(IStreamDataExporterSite)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataExporterSourceCode.init(IStreamDataExporterSite)"})
  public void testInit_givenHashMapLanguageIsOne_thenDataExporterSourceCodeOutputStreamIsNull()
      throws DBException {
    // Arrange
    DataExporterSourceCode dataExporterSourceCode = new DataExporterSourceCode();

    HashMap<String, Object> stringObjectMap = new HashMap<>();
    stringObjectMap.put("language", 1);
    stringObjectMap.put("rowDelimiter", "42");
    stringObjectMap.put("quoteChar", "\"");

    IStreamDataExporterSite site = mock(IStreamDataExporterSite.class);
    when(site.getProperties()).thenReturn(stringObjectMap);

    // Act
    dataExporterSourceCode.init(site);

    // Assert
    verify(site, atLeast(1)).getProperties();
    assertNull(dataExporterSourceCode.getOutputStream());
    assertNull(dataExporterSourceCode.getWriter());
    assertSame(site, dataExporterSourceCode.getSite());
  }

  /**
   * Test {@link DataExporterSourceCode#init(IStreamDataExporterSite)}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@code language} is {@code PHP < 5.4}.
   * </ul>
   *
   * <p>Method under test: {@link DataExporterSourceCode#init(IStreamDataExporterSite)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataExporterSourceCode.init(IStreamDataExporterSite)"})
  public void testInit_givenHashMapLanguageIsPhp54() throws DBException {
    // Arrange
    DataExporterSourceCode dataExporterSourceCode = new DataExporterSourceCode();

    HashMap<String, Object> stringObjectMap = new HashMap<>();
    stringObjectMap.put("language", "PHP < 5.4");
    stringObjectMap.put("rowDelimiter", "42");
    stringObjectMap.put("quoteChar", "\"");

    IStreamDataExporterSite site = mock(IStreamDataExporterSite.class);
    when(site.getProperties()).thenReturn(stringObjectMap);

    // Act
    dataExporterSourceCode.init(site);

    // Assert
    verify(site, atLeast(1)).getProperties();
    assertNull(dataExporterSourceCode.getOutputStream());
    assertNull(dataExporterSourceCode.getWriter());
    assertSame(site, dataExporterSourceCode.getSite());
  }

  /**
   * Test {@link DataExporterSourceCode#init(IStreamDataExporterSite)}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@code quoteChar} is one.
   *   <li>Then {@link DataExporterSourceCode} (default constructor) OutputStream is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DataExporterSourceCode#init(IStreamDataExporterSite)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataExporterSourceCode.init(IStreamDataExporterSite)"})
  public void testInit_givenHashMapQuoteCharIsOne_thenDataExporterSourceCodeOutputStreamIsNull()
      throws DBException {
    // Arrange
    DataExporterSourceCode dataExporterSourceCode = new DataExporterSourceCode();

    HashMap<String, Object> stringObjectMap = new HashMap<>();
    stringObjectMap.put("quoteChar", 1);

    IStreamDataExporterSite site = mock(IStreamDataExporterSite.class);
    when(site.getProperties()).thenReturn(stringObjectMap);

    // Act
    dataExporterSourceCode.init(site);

    // Assert
    verify(site, atLeast(1)).getProperties();
    assertNull(dataExporterSourceCode.getOutputStream());
    assertNull(dataExporterSourceCode.getWriter());
    assertSame(site, dataExporterSourceCode.getSite());
  }

  /**
   * Test {@link DataExporterSourceCode#init(IStreamDataExporterSite)}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@code quoteChar} is {@code "}.
   * </ul>
   *
   * <p>Method under test: {@link DataExporterSourceCode#init(IStreamDataExporterSite)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataExporterSourceCode.init(IStreamDataExporterSite)"})
  public void testInit_givenHashMapQuoteCharIsQuotationMark() throws DBException {
    // Arrange
    DataExporterSourceCode dataExporterSourceCode = new DataExporterSourceCode();

    HashMap<String, Object> stringObjectMap = new HashMap<>();
    stringObjectMap.put("quoteChar", "\"");

    IStreamDataExporterSite site = mock(IStreamDataExporterSite.class);
    when(site.getProperties()).thenReturn(stringObjectMap);

    // Act
    dataExporterSourceCode.init(site);

    // Assert
    verify(site, atLeast(1)).getProperties();
    assertNull(dataExporterSourceCode.getOutputStream());
    assertNull(dataExporterSourceCode.getWriter());
    assertSame(site, dataExporterSourceCode.getSite());
  }

  /**
   * Test {@link DataExporterSourceCode#init(IStreamDataExporterSite)}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@code rowDelimiter} is {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link DataExporterSourceCode#init(IStreamDataExporterSite)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataExporterSourceCode.init(IStreamDataExporterSite)"})
  public void testInit_givenHashMapRowDelimiterIs42() throws DBException {
    // Arrange
    DataExporterSourceCode dataExporterSourceCode = new DataExporterSourceCode();

    HashMap<String, Object> stringObjectMap = new HashMap<>();
    stringObjectMap.put("language", "42");
    stringObjectMap.put("rowDelimiter", "42");
    stringObjectMap.put("quoteChar", "\"");

    IStreamDataExporterSite site = mock(IStreamDataExporterSite.class);
    when(site.getProperties()).thenReturn(stringObjectMap);

    // Act
    dataExporterSourceCode.init(site);

    // Assert
    verify(site, atLeast(1)).getProperties();
    assertNull(dataExporterSourceCode.getOutputStream());
    assertNull(dataExporterSourceCode.getWriter());
    assertSame(site, dataExporterSourceCode.getSite());
  }

  /**
   * Test {@link DataExporterSourceCode#init(IStreamDataExporterSite)}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@code rowDelimiter} is {@code default}.
   * </ul>
   *
   * <p>Method under test: {@link DataExporterSourceCode#init(IStreamDataExporterSite)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataExporterSourceCode.init(IStreamDataExporterSite)"})
  public void testInit_givenHashMapRowDelimiterIsDefault() throws DBException {
    // Arrange
    DataExporterSourceCode dataExporterSourceCode = new DataExporterSourceCode();

    HashMap<String, Object> stringObjectMap = new HashMap<>();
    stringObjectMap.put("language", "PHP < 5.4");
    stringObjectMap.put("rowDelimiter", "default");
    stringObjectMap.put("quoteChar", "\"");

    IStreamDataExporterSite site = mock(IStreamDataExporterSite.class);
    when(site.getProperties()).thenReturn(stringObjectMap);

    // Act
    dataExporterSourceCode.init(site);

    // Assert
    verify(site, atLeast(1)).getProperties();
    assertNull(dataExporterSourceCode.getOutputStream());
    assertNull(dataExporterSourceCode.getWriter());
    assertSame(site, dataExporterSourceCode.getSite());
  }

  /**
   * Test {@link DataExporterSourceCode#init(IStreamDataExporterSite)}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@code rowDelimiter} is one.
   * </ul>
   *
   * <p>Method under test: {@link DataExporterSourceCode#init(IStreamDataExporterSite)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataExporterSourceCode.init(IStreamDataExporterSite)"})
  public void testInit_givenHashMapRowDelimiterIsOne() throws DBException {
    // Arrange
    DataExporterSourceCode dataExporterSourceCode = new DataExporterSourceCode();

    HashMap<String, Object> stringObjectMap = new HashMap<>();
    stringObjectMap.put("language", "42");
    stringObjectMap.put("rowDelimiter", 1);
    stringObjectMap.put("quoteChar", "\"");

    IStreamDataExporterSite site = mock(IStreamDataExporterSite.class);
    when(site.getProperties()).thenReturn(stringObjectMap);

    // Act
    dataExporterSourceCode.init(site);

    // Assert
    verify(site, atLeast(1)).getProperties();
    assertNull(dataExporterSourceCode.getOutputStream());
    assertNull(dataExporterSourceCode.getWriter());
    assertSame(site, dataExporterSourceCode.getSite());
  }

  /**
   * Test new {@link DataExporterSourceCode} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link DataExporterSourceCode}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataExporterSourceCode.<init>()"})
  public void testNewDataExporterSourceCode() {
    // Arrange, Act and Assert
    assertNull(new DataExporterSourceCode().getSite());
  }

  /**
   * Test ProgramLanguages {@link ProgramLanguages#fromValue(String)}.
   *
   * <ul>
   *   <li>When {@code foo}.
   * </ul>
   *
   * <p>Method under test: {@link ProgramLanguages#fromValue(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"ProgramLanguages ProgramLanguages.fromValue(String)"})
  public void testProgramLanguagesFromValue_whenFoo() {
    // Arrange, Act and Assert
    assertEquals(ProgramLanguages.PHP_VERSION_LESS_5_and_4, ProgramLanguages.fromValue("foo"));
  }

  /**
   * Test ProgramLanguages {@link ProgramLanguages#fromValue(String)}.
   *
   * <ul>
   *   <li>When {@code PHP < 5.4}.
   * </ul>
   *
   * <p>Method under test: {@link ProgramLanguages#fromValue(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"ProgramLanguages ProgramLanguages.fromValue(String)"})
  public void testProgramLanguagesFromValue_whenPhp54() {
    // Arrange, Act and Assert
    assertEquals(
        ProgramLanguages.PHP_VERSION_LESS_5_and_4, ProgramLanguages.fromValue("PHP < 5.4"));
  }

  /**
   * Test ProgramLanguages {@link ProgramLanguages#value()}.
   *
   * <p>Method under test: {@link ProgramLanguages#value()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String ProgramLanguages.value()"})
  public void testProgramLanguagesValue() {
    // Arrange, Act and Assert
    assertEquals("PHP < 5.4", ProgramLanguages.valueOf("PHP_VERSION_LESS_5_and_4").value());
  }
}
