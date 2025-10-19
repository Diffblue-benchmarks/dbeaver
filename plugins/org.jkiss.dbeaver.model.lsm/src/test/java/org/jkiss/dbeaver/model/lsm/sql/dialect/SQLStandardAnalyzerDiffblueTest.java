package org.jkiss.dbeaver.model.lsm.sql.dialect;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.jkiss.dbeaver.model.lsm.LSMAnalyzerParameters;
import org.jkiss.dbeaver.model.stm.STMSource;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SQLStandardAnalyzerDiffblueTest {
  /**
   * Test {@link SQLStandardAnalyzer#createParser(STMSource, LSMAnalyzerParameters)}.
   *
   * <p>Method under test: {@link SQLStandardAnalyzer#createParser(STMSource,
   * LSMAnalyzerParameters)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.utils.Pair SQLStandardAnalyzer.createParser(STMSource, LSMAnalyzerParameters)"
  })
  public void testCreateParser() {
    // Arrange
    HashMap<String, String> knownIdentifierQuotes = new HashMap<>();
    LSMAnalyzerParameters parameters =
        new LSMAnalyzerParameters(knownIdentifierQuotes, true, true, 'A', new ArrayList<>(), true);
    SQLStandardAnalyzer sqlStandardAnalyzer = new SQLStandardAnalyzer(parameters);

    STMSource source = mock(STMSource.class);
    when(source.getStream()).thenReturn(new ANTLRInputStream("Input"));
    HashMap<String, String> knownIdentifierQuotes2 = new HashMap<>();
    LSMAnalyzerParameters parameters2 =
        new LSMAnalyzerParameters(knownIdentifierQuotes2, true, true, 'A', new ArrayList<>(), true);

    // Act
    sqlStandardAnalyzer.createParser(source, parameters2);

    // Assert
    verify(source).getStream();
  }

  /**
   * Test {@link SQLStandardAnalyzer#createParser(STMSource, LSMAnalyzerParameters)}.
   *
   * <p>Method under test: {@link SQLStandardAnalyzer#createParser(STMSource,
   * LSMAnalyzerParameters)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.utils.Pair SQLStandardAnalyzer.createParser(STMSource, LSMAnalyzerParameters)"
  })
  public void testCreateParser2() {
    // Arrange
    HashMap<String, String> knownIdentifierQuotes = new HashMap<>();
    LSMAnalyzerParameters parameters =
        new LSMAnalyzerParameters(knownIdentifierQuotes, true, true, 'A', new ArrayList<>(), true);
    SQLStandardAnalyzer sqlStandardAnalyzer = new SQLStandardAnalyzer(parameters);

    STMSource source = mock(STMSource.class);
    when(source.getStream()).thenReturn(new ANTLRInputStream("Input"));
    HashMap<String, String> knownIdentifierQuotes2 = new HashMap<>();
    LSMAnalyzerParameters parameters2 =
        new LSMAnalyzerParameters(
            knownIdentifierQuotes2, false, true, 'A', new ArrayList<>(), true);

    // Act
    sqlStandardAnalyzer.createParser(source, parameters2);

    // Assert
    verify(source).getStream();
  }

  /**
   * Test {@link SQLStandardAnalyzer#createParser(STMSource, LSMAnalyzerParameters)}.
   *
   * <p>Method under test: {@link SQLStandardAnalyzer#createParser(STMSource,
   * LSMAnalyzerParameters)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.utils.Pair SQLStandardAnalyzer.createParser(STMSource, LSMAnalyzerParameters)"
  })
  public void testCreateParser3() {
    // Arrange
    HashMap<String, String> knownIdentifierQuotes = new HashMap<>();
    LSMAnalyzerParameters parameters =
        new LSMAnalyzerParameters(knownIdentifierQuotes, true, true, 'A', new ArrayList<>(), true);
    SQLStandardAnalyzer sqlStandardAnalyzer = new SQLStandardAnalyzer(parameters);

    STMSource source = mock(STMSource.class);
    when(source.getStream()).thenReturn(new ANTLRInputStream("Input"));
    HashMap<String, String> knownIdentifierQuotes2 = new HashMap<>();
    LSMAnalyzerParameters parameters2 =
        new LSMAnalyzerParameters(
            knownIdentifierQuotes2, true, false, 'A', new ArrayList<>(), true);

    // Act
    sqlStandardAnalyzer.createParser(source, parameters2);

    // Assert
    verify(source).getStream();
  }

  /**
   * Test {@link SQLStandardAnalyzer#createParser(STMSource, LSMAnalyzerParameters)}.
   *
   * <p>Method under test: {@link SQLStandardAnalyzer#createParser(STMSource,
   * LSMAnalyzerParameters)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.utils.Pair SQLStandardAnalyzer.createParser(STMSource, LSMAnalyzerParameters)"
  })
  public void testCreateParser4() {
    // Arrange
    HashMap<String, String> knownIdentifierQuotes = new HashMap<>();
    LSMAnalyzerParameters parameters =
        new LSMAnalyzerParameters(knownIdentifierQuotes, true, true, 'A', new ArrayList<>(), true);
    SQLStandardAnalyzer sqlStandardAnalyzer = new SQLStandardAnalyzer(parameters);

    STMSource source = mock(STMSource.class);
    when(source.getStream()).thenReturn(new ANTLRInputStream("Input"));
    HashMap<String, String> knownIdentifierQuotes2 = new HashMap<>();
    LSMAnalyzerParameters parameters2 =
        new LSMAnalyzerParameters(knownIdentifierQuotes2, true, true, '?', new ArrayList<>(), true);

    // Act
    sqlStandardAnalyzer.createParser(source, parameters2);

    // Assert
    verify(source).getStream();
  }

  /**
   * Test {@link SQLStandardAnalyzer#createParser(STMSource, LSMAnalyzerParameters)}.
   *
   * <p>Method under test: {@link SQLStandardAnalyzer#createParser(STMSource,
   * LSMAnalyzerParameters)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.utils.Pair SQLStandardAnalyzer.createParser(STMSource, LSMAnalyzerParameters)"
  })
  public void testCreateParser5() {
    // Arrange
    HashMap<String, String> knownIdentifierQuotes = new HashMap<>();
    LSMAnalyzerParameters parameters =
        new LSMAnalyzerParameters(knownIdentifierQuotes, true, true, 'A', new ArrayList<>(), true);
    SQLStandardAnalyzer sqlStandardAnalyzer = new SQLStandardAnalyzer(parameters);

    STMSource source = mock(STMSource.class);
    when(source.getStream()).thenReturn(new ANTLRInputStream("Input"));
    HashMap<String, String> knownIdentifierQuotes2 = new HashMap<>();
    LSMAnalyzerParameters parameters2 =
        new LSMAnalyzerParameters(knownIdentifierQuotes2, true, true, 'A', new ArrayList<>(), true);

    // Act
    sqlStandardAnalyzer.createParser(source, parameters2);

    // Assert
    verify(source).getStream();
  }

  /**
   * Test {@link SQLStandardAnalyzer#createParser(STMSource, LSMAnalyzerParameters)}.
   *
   * <p>Method under test: {@link SQLStandardAnalyzer#createParser(STMSource,
   * LSMAnalyzerParameters)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.utils.Pair SQLStandardAnalyzer.createParser(STMSource, LSMAnalyzerParameters)"
  })
  public void testCreateParser6() {
    // Arrange
    HashMap<String, String> knownIdentifierQuotes = new HashMap<>();
    LSMAnalyzerParameters parameters =
        new LSMAnalyzerParameters(knownIdentifierQuotes, true, true, 'A', new ArrayList<>(), true);
    SQLStandardAnalyzer sqlStandardAnalyzer = new SQLStandardAnalyzer(parameters);

    STMSource source = mock(STMSource.class);
    when(source.getStream()).thenReturn(new ANTLRInputStream("Input"));
    HashMap<String, String> knownIdentifierQuotes2 = new HashMap<>();
    LSMAnalyzerParameters parameters2 =
        new LSMAnalyzerParameters(
            knownIdentifierQuotes2, false, true, 'A', new ArrayList<>(), true);

    // Act
    sqlStandardAnalyzer.createParser(source, parameters2);

    // Assert
    verify(source).getStream();
  }

  /**
   * Test {@link SQLStandardAnalyzer#createParser(STMSource, LSMAnalyzerParameters)}.
   *
   * <p>Method under test: {@link SQLStandardAnalyzer#createParser(STMSource,
   * LSMAnalyzerParameters)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.utils.Pair SQLStandardAnalyzer.createParser(STMSource, LSMAnalyzerParameters)"
  })
  public void testCreateParser7() {
    // Arrange
    HashMap<String, String> knownIdentifierQuotes = new HashMap<>();
    LSMAnalyzerParameters parameters =
        new LSMAnalyzerParameters(knownIdentifierQuotes, true, true, 'A', new ArrayList<>(), true);
    SQLStandardAnalyzer sqlStandardAnalyzer = new SQLStandardAnalyzer(parameters);

    STMSource source = mock(STMSource.class);
    when(source.getStream()).thenReturn(new ANTLRInputStream("Input"));
    HashMap<String, String> knownIdentifierQuotes2 = new HashMap<>();
    LSMAnalyzerParameters parameters2 =
        new LSMAnalyzerParameters(
            knownIdentifierQuotes2, true, false, 'A', new ArrayList<>(), true);

    // Act
    sqlStandardAnalyzer.createParser(source, parameters2);

    // Assert
    verify(source).getStream();
  }

  /**
   * Test {@link SQLStandardAnalyzer#createParser(STMSource, LSMAnalyzerParameters)}.
   *
   * <p>Method under test: {@link SQLStandardAnalyzer#createParser(STMSource,
   * LSMAnalyzerParameters)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.utils.Pair SQLStandardAnalyzer.createParser(STMSource, LSMAnalyzerParameters)"
  })
  public void testCreateParser8() {
    // Arrange
    HashMap<String, String> knownIdentifierQuotes = new HashMap<>();
    LSMAnalyzerParameters parameters =
        new LSMAnalyzerParameters(knownIdentifierQuotes, true, true, 'A', new ArrayList<>(), true);
    SQLStandardAnalyzer sqlStandardAnalyzer = new SQLStandardAnalyzer(parameters);

    STMSource source = mock(STMSource.class);
    when(source.getStream()).thenReturn(new ANTLRInputStream("Input"));
    HashMap<String, String> knownIdentifierQuotes2 = new HashMap<>();
    LSMAnalyzerParameters parameters2 =
        new LSMAnalyzerParameters(knownIdentifierQuotes2, true, true, '?', new ArrayList<>(), true);

    // Act
    sqlStandardAnalyzer.createParser(source, parameters2);

    // Assert
    verify(source).getStream();
  }

  /**
   * Test {@link SQLStandardAnalyzer#createParser(STMSource, LSMAnalyzerParameters)}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>When {@link HashMap#HashMap()} {@code 42} is {@code 42}.
   *   <li>Then calls {@link STMSource#getStream()}.
   * </ul>
   *
   * <p>Method under test: {@link SQLStandardAnalyzer#createParser(STMSource,
   * LSMAnalyzerParameters)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.utils.Pair SQLStandardAnalyzer.createParser(STMSource, LSMAnalyzerParameters)"
  })
  public void testCreateParser_given42_whenHashMap42Is42_thenCallsGetStream() {
    // Arrange
    HashMap<String, String> knownIdentifierQuotes = new HashMap<>();
    LSMAnalyzerParameters parameters =
        new LSMAnalyzerParameters(knownIdentifierQuotes, true, true, 'A', new ArrayList<>(), true);
    SQLStandardAnalyzer sqlStandardAnalyzer = new SQLStandardAnalyzer(parameters);

    STMSource source = mock(STMSource.class);
    when(source.getStream()).thenReturn(new ANTLRInputStream("Input"));

    HashMap<String, String> knownIdentifierQuotes2 = new HashMap<>();
    knownIdentifierQuotes2.put("42", "42");
    knownIdentifierQuotes2.put("foo", "foo");
    LSMAnalyzerParameters parameters2 =
        new LSMAnalyzerParameters(knownIdentifierQuotes2, true, true, 'A', new ArrayList<>(), true);

    // Act
    sqlStandardAnalyzer.createParser(source, parameters2);

    // Assert
    verify(source).getStream();
  }

  /**
   * Test {@link SQLStandardAnalyzer#createParser(STMSource, LSMAnalyzerParameters)}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>When {@link HashMap#HashMap()} {@code 42} is {@code 42}.
   *   <li>Then calls {@link STMSource#getStream()}.
   * </ul>
   *
   * <p>Method under test: {@link SQLStandardAnalyzer#createParser(STMSource,
   * LSMAnalyzerParameters)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.utils.Pair SQLStandardAnalyzer.createParser(STMSource, LSMAnalyzerParameters)"
  })
  public void testCreateParser_given42_whenHashMap42Is42_thenCallsGetStream2() {
    // Arrange
    HashMap<String, String> knownIdentifierQuotes = new HashMap<>();
    LSMAnalyzerParameters parameters =
        new LSMAnalyzerParameters(knownIdentifierQuotes, true, true, 'A', new ArrayList<>(), true);
    SQLStandardAnalyzer sqlStandardAnalyzer = new SQLStandardAnalyzer(parameters);

    STMSource source = mock(STMSource.class);
    when(source.getStream()).thenReturn(new ANTLRInputStream("Input"));

    HashMap<String, String> knownIdentifierQuotes2 = new HashMap<>();
    knownIdentifierQuotes2.put("42", "42");
    knownIdentifierQuotes2.put("foo", "foo");
    LSMAnalyzerParameters parameters2 =
        new LSMAnalyzerParameters(knownIdentifierQuotes2, true, true, 'A', new ArrayList<>(), true);

    // Act
    sqlStandardAnalyzer.createParser(source, parameters2);

    // Assert
    verify(source).getStream();
  }

  /**
   * Test {@link SQLStandardAnalyzer#createParser(STMSource, LSMAnalyzerParameters)}.
   *
   * <ul>
   *   <li>Given {@code foo}.
   *   <li>When {@link HashMap#HashMap()} {@code foo} is {@code foo}.
   *   <li>Then calls {@link STMSource#getStream()}.
   * </ul>
   *
   * <p>Method under test: {@link SQLStandardAnalyzer#createParser(STMSource,
   * LSMAnalyzerParameters)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.utils.Pair SQLStandardAnalyzer.createParser(STMSource, LSMAnalyzerParameters)"
  })
  public void testCreateParser_givenFoo_whenHashMapFooIsFoo_thenCallsGetStream() {
    // Arrange
    HashMap<String, String> knownIdentifierQuotes = new HashMap<>();
    LSMAnalyzerParameters parameters =
        new LSMAnalyzerParameters(knownIdentifierQuotes, true, true, 'A', new ArrayList<>(), true);
    SQLStandardAnalyzer sqlStandardAnalyzer = new SQLStandardAnalyzer(parameters);

    STMSource source = mock(STMSource.class);
    when(source.getStream()).thenReturn(new ANTLRInputStream("Input"));

    HashMap<String, String> knownIdentifierQuotes2 = new HashMap<>();
    knownIdentifierQuotes2.put("foo", "foo");
    LSMAnalyzerParameters parameters2 =
        new LSMAnalyzerParameters(knownIdentifierQuotes2, true, true, 'A', new ArrayList<>(), true);

    // Act
    sqlStandardAnalyzer.createParser(source, parameters2);

    // Assert
    verify(source).getStream();
  }

  /**
   * Test {@link SQLStandardAnalyzer#createParser(STMSource, LSMAnalyzerParameters)}.
   *
   * <ul>
   *   <li>Given {@code foo}.
   *   <li>When {@link HashMap#HashMap()} {@code foo} is {@code foo}.
   *   <li>Then calls {@link STMSource#getStream()}.
   * </ul>
   *
   * <p>Method under test: {@link SQLStandardAnalyzer#createParser(STMSource,
   * LSMAnalyzerParameters)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.utils.Pair SQLStandardAnalyzer.createParser(STMSource, LSMAnalyzerParameters)"
  })
  public void testCreateParser_givenFoo_whenHashMapFooIsFoo_thenCallsGetStream2() {
    // Arrange
    HashMap<String, String> knownIdentifierQuotes = new HashMap<>();
    LSMAnalyzerParameters parameters =
        new LSMAnalyzerParameters(knownIdentifierQuotes, true, true, 'A', new ArrayList<>(), true);
    SQLStandardAnalyzer sqlStandardAnalyzer = new SQLStandardAnalyzer(parameters);

    STMSource source = mock(STMSource.class);
    when(source.getStream()).thenReturn(new ANTLRInputStream("Input"));

    HashMap<String, String> knownIdentifierQuotes2 = new HashMap<>();
    knownIdentifierQuotes2.put("foo", "foo");
    LSMAnalyzerParameters parameters2 =
        new LSMAnalyzerParameters(knownIdentifierQuotes2, true, true, 'A', new ArrayList<>(), true);

    // Act
    sqlStandardAnalyzer.createParser(source, parameters2);

    // Assert
    verify(source).getStream();
  }

  /**
   * Test {@link SQLStandardAnalyzer#createParser(STMSource, LSMAnalyzerParameters)}.
   *
   * <ul>
   *   <li>Given {@link HashSet#HashSet()} add {@code foo}.
   * </ul>
   *
   * <p>Method under test: {@link SQLStandardAnalyzer#createParser(STMSource,
   * LSMAnalyzerParameters)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.utils.Pair SQLStandardAnalyzer.createParser(STMSource, LSMAnalyzerParameters)"
  })
  public void testCreateParser_givenHashSetAddFoo() {
    // Arrange
    HashMap<String, String> knownIdentifierQuotes = new HashMap<>();
    LSMAnalyzerParameters parameters =
        new LSMAnalyzerParameters(knownIdentifierQuotes, true, true, 'A', new ArrayList<>(), true);
    SQLStandardAnalyzer sqlStandardAnalyzer = new SQLStandardAnalyzer(parameters);

    STMSource source = mock(STMSource.class);
    when(source.getStream()).thenReturn(new ANTLRInputStream("Input"));

    HashSet<String> stringSet = new HashSet<>();
    stringSet.add("foo");
    SimpleEntry<Integer, Set<String>> simpleEntry = new SimpleEntry<>(-1, stringSet);

    ArrayList<Entry<Integer, Set<String>>> namedParameterPrefixes = new ArrayList<>();
    namedParameterPrefixes.add(simpleEntry);
    LSMAnalyzerParameters parameters2 =
        new LSMAnalyzerParameters(new HashMap<>(), true, true, 'A', namedParameterPrefixes, true);

    // Act
    sqlStandardAnalyzer.createParser(source, parameters2);

    // Assert
    verify(source).getStream();
  }

  /**
   * Test {@link SQLStandardAnalyzer#createParser(STMSource, LSMAnalyzerParameters)}.
   *
   * <ul>
   *   <li>Given {@link HashSet#HashSet()} add {@code foo}.
   * </ul>
   *
   * <p>Method under test: {@link SQLStandardAnalyzer#createParser(STMSource,
   * LSMAnalyzerParameters)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.utils.Pair SQLStandardAnalyzer.createParser(STMSource, LSMAnalyzerParameters)"
  })
  public void testCreateParser_givenHashSetAddFoo2() {
    // Arrange
    HashMap<String, String> knownIdentifierQuotes = new HashMap<>();
    LSMAnalyzerParameters parameters =
        new LSMAnalyzerParameters(knownIdentifierQuotes, true, true, 'A', new ArrayList<>(), true);
    SQLStandardAnalyzer sqlStandardAnalyzer = new SQLStandardAnalyzer(parameters);

    STMSource source = mock(STMSource.class);
    when(source.getStream()).thenReturn(new ANTLRInputStream("Input"));

    HashSet<String> stringSet = new HashSet<>();
    stringSet.add("foo");
    SimpleEntry<Integer, Set<String>> simpleEntry = new SimpleEntry<>(-1, stringSet);

    ArrayList<Entry<Integer, Set<String>>> namedParameterPrefixes = new ArrayList<>();
    namedParameterPrefixes.add(simpleEntry);
    LSMAnalyzerParameters parameters2 =
        new LSMAnalyzerParameters(new HashMap<>(), true, true, 'A', namedParameterPrefixes, true);

    // Act
    sqlStandardAnalyzer.createParser(source, parameters2);

    // Assert
    verify(source).getStream();
  }

  /**
   * Test {@link SQLStandardAnalyzer#createParser(STMSource, LSMAnalyzerParameters)}.
   *
   * <ul>
   *   <li>Given {@link SimpleEntry#SimpleEntry(Object, Object)} with minus one and {@link
   *       HashSet#HashSet()}.
   * </ul>
   *
   * <p>Method under test: {@link SQLStandardAnalyzer#createParser(STMSource,
   * LSMAnalyzerParameters)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.utils.Pair SQLStandardAnalyzer.createParser(STMSource, LSMAnalyzerParameters)"
  })
  public void testCreateParser_givenSimpleEntryWithMinusOneAndHashSet() {
    // Arrange
    HashMap<String, String> knownIdentifierQuotes = new HashMap<>();
    LSMAnalyzerParameters parameters =
        new LSMAnalyzerParameters(knownIdentifierQuotes, true, true, 'A', new ArrayList<>(), true);
    SQLStandardAnalyzer sqlStandardAnalyzer = new SQLStandardAnalyzer(parameters);

    STMSource source = mock(STMSource.class);
    when(source.getStream()).thenReturn(new ANTLRInputStream("Input"));

    ArrayList<Entry<Integer, Set<String>>> namedParameterPrefixes = new ArrayList<>();
    SimpleEntry<Integer, Set<String>> simpleEntry = new SimpleEntry<>(-1, new HashSet<>());
    namedParameterPrefixes.add(simpleEntry);
    LSMAnalyzerParameters parameters2 =
        new LSMAnalyzerParameters(new HashMap<>(), true, true, 'A', namedParameterPrefixes, true);

    // Act
    sqlStandardAnalyzer.createParser(source, parameters2);

    // Assert
    verify(source).getStream();
  }

  /**
   * Test {@link SQLStandardAnalyzer#createParser(STMSource, LSMAnalyzerParameters)}.
   *
   * <ul>
   *   <li>Given {@link SimpleEntry#SimpleEntry(Object, Object)} with minus one and {@link
   *       HashSet#HashSet()}.
   * </ul>
   *
   * <p>Method under test: {@link SQLStandardAnalyzer#createParser(STMSource,
   * LSMAnalyzerParameters)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.utils.Pair SQLStandardAnalyzer.createParser(STMSource, LSMAnalyzerParameters)"
  })
  public void testCreateParser_givenSimpleEntryWithMinusOneAndHashSet2() {
    // Arrange
    HashMap<String, String> knownIdentifierQuotes = new HashMap<>();
    LSMAnalyzerParameters parameters =
        new LSMAnalyzerParameters(knownIdentifierQuotes, true, true, 'A', new ArrayList<>(), true);
    SQLStandardAnalyzer sqlStandardAnalyzer = new SQLStandardAnalyzer(parameters);

    STMSource source = mock(STMSource.class);
    when(source.getStream()).thenReturn(new ANTLRInputStream("Input"));

    ArrayList<Entry<Integer, Set<String>>> namedParameterPrefixes = new ArrayList<>();
    SimpleEntry<Integer, Set<String>> simpleEntry = new SimpleEntry<>(-1, new HashSet<>());
    namedParameterPrefixes.add(simpleEntry);
    SimpleEntry<Integer, Set<String>> simpleEntry2 = new SimpleEntry<>(-1, new HashSet<>());
    namedParameterPrefixes.add(simpleEntry2);
    LSMAnalyzerParameters parameters2 =
        new LSMAnalyzerParameters(new HashMap<>(), true, true, 'A', namedParameterPrefixes, true);

    // Act
    sqlStandardAnalyzer.createParser(source, parameters2);

    // Assert
    verify(source).getStream();
  }

  /**
   * Test {@link SQLStandardAnalyzer#createParser(STMSource, LSMAnalyzerParameters)}.
   *
   * <ul>
   *   <li>Given {@link SimpleEntry#SimpleEntry(Object, Object)} with minus one and {@link
   *       HashSet#HashSet()}.
   * </ul>
   *
   * <p>Method under test: {@link SQLStandardAnalyzer#createParser(STMSource,
   * LSMAnalyzerParameters)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.utils.Pair SQLStandardAnalyzer.createParser(STMSource, LSMAnalyzerParameters)"
  })
  public void testCreateParser_givenSimpleEntryWithMinusOneAndHashSet3() {
    // Arrange
    HashMap<String, String> knownIdentifierQuotes = new HashMap<>();
    LSMAnalyzerParameters parameters =
        new LSMAnalyzerParameters(knownIdentifierQuotes, true, true, 'A', new ArrayList<>(), true);
    SQLStandardAnalyzer sqlStandardAnalyzer = new SQLStandardAnalyzer(parameters);

    STMSource source = mock(STMSource.class);
    when(source.getStream()).thenReturn(new ANTLRInputStream("Input"));

    ArrayList<Entry<Integer, Set<String>>> namedParameterPrefixes = new ArrayList<>();
    SimpleEntry<Integer, Set<String>> simpleEntry = new SimpleEntry<>(-1, new HashSet<>());
    namedParameterPrefixes.add(simpleEntry);
    LSMAnalyzerParameters parameters2 =
        new LSMAnalyzerParameters(new HashMap<>(), true, true, 'A', namedParameterPrefixes, true);

    // Act
    sqlStandardAnalyzer.createParser(source, parameters2);

    // Assert
    verify(source).getStream();
  }

  /**
   * Test {@link SQLStandardAnalyzer#createParser(STMSource, LSMAnalyzerParameters)}.
   *
   * <ul>
   *   <li>Given {@link SimpleEntry#SimpleEntry(Object, Object)} with minus one and {@link
   *       HashSet#HashSet()}.
   * </ul>
   *
   * <p>Method under test: {@link SQLStandardAnalyzer#createParser(STMSource,
   * LSMAnalyzerParameters)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.utils.Pair SQLStandardAnalyzer.createParser(STMSource, LSMAnalyzerParameters)"
  })
  public void testCreateParser_givenSimpleEntryWithMinusOneAndHashSet4() {
    // Arrange
    HashMap<String, String> knownIdentifierQuotes = new HashMap<>();
    LSMAnalyzerParameters parameters =
        new LSMAnalyzerParameters(knownIdentifierQuotes, true, true, 'A', new ArrayList<>(), true);
    SQLStandardAnalyzer sqlStandardAnalyzer = new SQLStandardAnalyzer(parameters);

    STMSource source = mock(STMSource.class);
    when(source.getStream()).thenReturn(new ANTLRInputStream("Input"));

    ArrayList<Entry<Integer, Set<String>>> namedParameterPrefixes = new ArrayList<>();
    SimpleEntry<Integer, Set<String>> simpleEntry = new SimpleEntry<>(-1, new HashSet<>());
    namedParameterPrefixes.add(simpleEntry);
    SimpleEntry<Integer, Set<String>> simpleEntry2 = new SimpleEntry<>(-1, new HashSet<>());
    namedParameterPrefixes.add(simpleEntry2);
    LSMAnalyzerParameters parameters2 =
        new LSMAnalyzerParameters(new HashMap<>(), true, true, 'A', namedParameterPrefixes, true);

    // Act
    sqlStandardAnalyzer.createParser(source, parameters2);

    // Assert
    verify(source).getStream();
  }
}
