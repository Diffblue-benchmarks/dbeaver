package org.jkiss.dbeaver.model.struct;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBSObjectFilterDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DBSObjectFilter#DBSObjectFilter()}
   *   <li>{@link DBSObjectFilter#setCaseSensitive(boolean)}
   *   <li>{@link DBSObjectFilter#setDescription(String)}
   *   <li>{@link DBSObjectFilter#setEnabled(boolean)}
   *   <li>{@link DBSObjectFilter#setName(String)}
   *   <li>{@link DBSObjectFilter#getDescription()}
   *   <li>{@link DBSObjectFilter#getExclude()}
   *   <li>{@link DBSObjectFilter#getInclude()}
   *   <li>{@link DBSObjectFilter#getName()}
   *   <li>{@link DBSObjectFilter#isCaseSensitive()}
   *   <li>{@link DBSObjectFilter#isEnabled()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBSObjectFilter.<init>()",
    "String DBSObjectFilter.getDescription()",
    "List DBSObjectFilter.getExclude()",
    "List DBSObjectFilter.getInclude()",
    "String DBSObjectFilter.getName()",
    "boolean DBSObjectFilter.isCaseSensitive()",
    "boolean DBSObjectFilter.isEnabled()",
    "void DBSObjectFilter.setCaseSensitive(boolean)",
    "void DBSObjectFilter.setDescription(String)",
    "void DBSObjectFilter.setEnabled(boolean)",
    "void DBSObjectFilter.setName(String)"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    DBSObjectFilter actualDbsObjectFilter = new DBSObjectFilter();
    actualDbsObjectFilter.setCaseSensitive(true);
    actualDbsObjectFilter.setDescription("The characteristics of someone or something");
    actualDbsObjectFilter.setEnabled(true);
    actualDbsObjectFilter.setName("Name");
    String actualDescription = actualDbsObjectFilter.getDescription();
    List<String> actualExclude = actualDbsObjectFilter.getExclude();
    List<String> actualInclude = actualDbsObjectFilter.getInclude();
    String actualName = actualDbsObjectFilter.getName();
    boolean actualIsCaseSensitiveResult = actualDbsObjectFilter.isCaseSensitive();

    // Assert
    assertEquals("Name", actualName);
    assertEquals("The characteristics of someone or something", actualDescription);
    assertNull(actualExclude);
    assertNull(actualInclude);
    assertTrue(actualIsCaseSensitiveResult);
    assertTrue(actualDbsObjectFilter.isEnabled());
  }

  /**
   * Test {@link DBSObjectFilter#DBSObjectFilter(DBSObjectFilter)}.
   *
   * <p>Method under test: {@link DBSObjectFilter#DBSObjectFilter(DBSObjectFilter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBSObjectFilter.<init>(DBSObjectFilter)"})
  public void testNewDBSObjectFilter() {
    // Arrange
    DBSObjectFilter filter = new DBSObjectFilter("Include String", "Exclude String");

    // Act
    DBSObjectFilter actualDbsObjectFilter = new DBSObjectFilter(filter);

    // Assert
    assertEquals(filter, actualDbsObjectFilter);
  }

  /**
   * Test {@link DBSObjectFilter#DBSObjectFilter(DBSObjectFilter)}.
   *
   * <ul>
   *   <li>When {@link DBSObjectFilter#DBSObjectFilter()}.
   *   <li>Then return {@link DBSObjectFilter#DBSObjectFilter()}.
   * </ul>
   *
   * <p>Method under test: {@link DBSObjectFilter#DBSObjectFilter(DBSObjectFilter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBSObjectFilter.<init>(DBSObjectFilter)"})
  public void testNewDBSObjectFilter_whenDBSObjectFilter_thenReturnDBSObjectFilter() {
    // Arrange
    DBSObjectFilter filter = new DBSObjectFilter();

    // Act
    DBSObjectFilter actualDbsObjectFilter = new DBSObjectFilter(filter);

    // Assert
    assertEquals(filter, actualDbsObjectFilter);
  }

  /**
   * Test {@link DBSObjectFilter#DBSObjectFilter(String, String)}.
   *
   * <ul>
   *   <li>When empty string.
   *   <li>Then return Exclude Empty.
   * </ul>
   *
   * <p>Method under test: {@link DBSObjectFilter#DBSObjectFilter(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBSObjectFilter.<init>(String, String)"})
  public void testNewDBSObjectFilter_whenEmptyString_thenReturnExcludeEmpty() {
    // Arrange and Act
    DBSObjectFilter actualDbsObjectFilter = new DBSObjectFilter(null, "");

    // Assert
    assertNull(actualDbsObjectFilter.getSingleMask());
    assertNull(actualDbsObjectFilter.getInclude());
    assertTrue(actualDbsObjectFilter.getExclude().isEmpty());
    assertTrue(actualDbsObjectFilter.isEmpty());
    assertTrue(actualDbsObjectFilter.isNotApplicable());
  }

  /**
   * Test {@link DBSObjectFilter#DBSObjectFilter(String, String)}.
   *
   * <ul>
   *   <li>When {@code Include String}.
   *   <li>Then return Exclude size is one.
   * </ul>
   *
   * <p>Method under test: {@link DBSObjectFilter#DBSObjectFilter(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBSObjectFilter.<init>(String, String)"})
  public void testNewDBSObjectFilter_whenIncludeString_thenReturnExcludeSizeIsOne() {
    // Arrange and Act
    DBSObjectFilter actualDbsObjectFilter = new DBSObjectFilter("Include String", "Exclude String");

    // Assert
    List<String> exclude = actualDbsObjectFilter.getExclude();
    assertEquals(1, exclude.size());
    assertEquals("Exclude String", exclude.get(0));
    List<String> include = actualDbsObjectFilter.getInclude();
    assertEquals(1, include.size());
    assertEquals("Include String", include.get(0));
    assertEquals("Include String", actualDbsObjectFilter.getSingleMask());
    assertFalse(actualDbsObjectFilter.isEmpty());
    assertFalse(actualDbsObjectFilter.isNotApplicable());
  }

  /**
   * Test {@link DBSObjectFilter#DBSObjectFilter(DBSObjectFilter)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return Description is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBSObjectFilter#DBSObjectFilter(DBSObjectFilter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBSObjectFilter.<init>(DBSObjectFilter)"})
  public void testNewDBSObjectFilter_whenNull_thenReturnDescriptionIsNull() {
    // Arrange and Act
    DBSObjectFilter actualDbsObjectFilter = new DBSObjectFilter(null);

    // Assert
    assertNull(actualDbsObjectFilter.getDescription());
    assertNull(actualDbsObjectFilter.getName());
    assertNull(actualDbsObjectFilter.getSingleMask());
    assertNull(actualDbsObjectFilter.getExclude());
    assertNull(actualDbsObjectFilter.getInclude());
    assertFalse(actualDbsObjectFilter.hasSingleMask());
    assertFalse(actualDbsObjectFilter.isCaseSensitive());
    assertTrue(actualDbsObjectFilter.isEmpty());
    assertTrue(actualDbsObjectFilter.isEnabled());
    assertTrue(actualDbsObjectFilter.isNotApplicable());
  }

  /**
   * Test {@link DBSObjectFilter#DBSObjectFilter(String, String)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return Exclude is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBSObjectFilter#DBSObjectFilter(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBSObjectFilter.<init>(String, String)"})
  public void testNewDBSObjectFilter_whenNull_thenReturnExcludeIsNull() {
    // Arrange and Act
    DBSObjectFilter actualDbsObjectFilter = new DBSObjectFilter(null, null);

    // Assert
    assertNull(actualDbsObjectFilter.getSingleMask());
    assertNull(actualDbsObjectFilter.getExclude());
    assertNull(actualDbsObjectFilter.getInclude());
    assertTrue(actualDbsObjectFilter.isEmpty());
    assertTrue(actualDbsObjectFilter.isNotApplicable());
  }

  /**
   * Test {@link DBSObjectFilter#addInclude(String)}.
   *
   * <p>Method under test: {@link DBSObjectFilter#addInclude(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBSObjectFilter.addInclude(String)"})
  public void testAddInclude() {
    // Arrange
    DBSObjectFilter dbsObjectFilter = new DBSObjectFilter("Include String", "Exclude String");
    dbsObjectFilter.addInclude("Name");

    // Act
    dbsObjectFilter.addInclude("Name");

    // Assert that nothing has changed
    List<String> include = dbsObjectFilter.getInclude();
    assertEquals(2, include.size());
    assertEquals("Include String", include.get(0));
    assertEquals("Include String", dbsObjectFilter.getSingleMask());
    assertEquals("Name", include.get(1));
    assertFalse(dbsObjectFilter.hasSingleMask());
    assertFalse(dbsObjectFilter.isEmpty());
    assertFalse(dbsObjectFilter.isNotApplicable());
  }

  /**
   * Test {@link DBSObjectFilter#addInclude(String)}.
   *
   * <ul>
   *   <li>Given {@link DBSObjectFilter#DBSObjectFilter(String, String)} with {@code Include String}
   *       and {@code Exclude String}.
   * </ul>
   *
   * <p>Method under test: {@link DBSObjectFilter#addInclude(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBSObjectFilter.addInclude(String)"})
  public void testAddInclude_givenDBSObjectFilterWithIncludeStringAndExcludeString() {
    // Arrange
    DBSObjectFilter dbsObjectFilter = new DBSObjectFilter("Include String", "Exclude String");

    // Act
    dbsObjectFilter.addInclude("Name");

    // Assert
    List<String> include = dbsObjectFilter.getInclude();
    assertEquals(2, include.size());
    assertEquals("Include String", include.get(0));
    assertEquals("Include String", dbsObjectFilter.getSingleMask());
    assertEquals("Name", include.get(1));
    assertFalse(dbsObjectFilter.hasSingleMask());
    assertFalse(dbsObjectFilter.isEmpty());
    assertFalse(dbsObjectFilter.isNotApplicable());
  }

  /**
   * Test {@link DBSObjectFilter#addInclude(String)}.
   *
   * <ul>
   *   <li>Given {@link DBSObjectFilter#DBSObjectFilter()}.
   *   <li>Then {@link DBSObjectFilter#DBSObjectFilter()} Include size is one.
   * </ul>
   *
   * <p>Method under test: {@link DBSObjectFilter#addInclude(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBSObjectFilter.addInclude(String)"})
  public void testAddInclude_givenDBSObjectFilter_thenDBSObjectFilterIncludeSizeIsOne() {
    // Arrange
    DBSObjectFilter dbsObjectFilter = new DBSObjectFilter();

    // Act
    dbsObjectFilter.addInclude("Name");

    // Assert
    List<String> include = dbsObjectFilter.getInclude();
    assertEquals(1, include.size());
    assertEquals("Name", include.get(0));
    assertEquals("Name", dbsObjectFilter.getSingleMask());
    assertFalse(dbsObjectFilter.isEmpty());
    assertFalse(dbsObjectFilter.isNotApplicable());
    assertTrue(dbsObjectFilter.hasSingleMask());
  }

  /**
   * Test {@link DBSObjectFilter#setInclude(List)}.
   *
   * <p>Method under test: {@link DBSObjectFilter#setInclude(List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBSObjectFilter.setInclude(List)"})
  public void testSetInclude() {
    // Arrange
    DBSObjectFilter dbsObjectFilter = new DBSObjectFilter("Include String", "Exclude String");
    ArrayList<String> include = new ArrayList<>();

    // Act
    dbsObjectFilter.setInclude(include);

    // Assert
    assertNull(dbsObjectFilter.getSingleMask());
    assertSame(include, dbsObjectFilter.getInclude());
  }

  /**
   * Test {@link DBSObjectFilter#setInclude(List)}.
   *
   * <p>Method under test: {@link DBSObjectFilter#setInclude(List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBSObjectFilter.setInclude(List)"})
  public void testSetInclude2() {
    // Arrange
    DBSObjectFilter dbsObjectFilter = new DBSObjectFilter("Include String", "Exclude String");

    ArrayList<String> include = new ArrayList<>();
    include.add("foo");

    // Act
    dbsObjectFilter.setInclude(include);

    // Assert
    assertEquals("foo", dbsObjectFilter.getSingleMask());
    assertSame(include, dbsObjectFilter.getInclude());
  }

  /**
   * Test {@link DBSObjectFilter#setInclude(List)}.
   *
   * <p>Method under test: {@link DBSObjectFilter#setInclude(List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBSObjectFilter.setInclude(List)"})
  public void testSetInclude3() {
    // Arrange
    DBSObjectFilter dbsObjectFilter = new DBSObjectFilter("Include String", "Exclude String");

    ArrayList<String> include = new ArrayList<>();
    include.add("42");
    include.add("foo");

    // Act
    dbsObjectFilter.setInclude(include);

    // Assert
    assertEquals("42", dbsObjectFilter.getSingleMask());
    assertSame(include, dbsObjectFilter.getInclude());
  }

  /**
   * Test {@link DBSObjectFilter#addExclude(String)}.
   *
   * <p>Method under test: {@link DBSObjectFilter#addExclude(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBSObjectFilter.addExclude(String)"})
  public void testAddExclude() {
    // Arrange
    DBSObjectFilter dbsObjectFilter = new DBSObjectFilter("Include String", "Exclude String");
    dbsObjectFilter.addExclude("Name");

    // Act
    dbsObjectFilter.addExclude("Name");

    // Assert that nothing has changed
    List<String> exclude = dbsObjectFilter.getExclude();
    assertEquals(2, exclude.size());
    assertEquals("Exclude String", exclude.get(0));
    assertEquals("Name", exclude.get(1));
    assertFalse(dbsObjectFilter.isEmpty());
    assertFalse(dbsObjectFilter.isNotApplicable());
  }

  /**
   * Test {@link DBSObjectFilter#addExclude(String)}.
   *
   * <ul>
   *   <li>Given {@link DBSObjectFilter#DBSObjectFilter(String, String)} with {@code Include String}
   *       and {@code Exclude String}.
   * </ul>
   *
   * <p>Method under test: {@link DBSObjectFilter#addExclude(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBSObjectFilter.addExclude(String)"})
  public void testAddExclude_givenDBSObjectFilterWithIncludeStringAndExcludeString() {
    // Arrange
    DBSObjectFilter dbsObjectFilter = new DBSObjectFilter("Include String", "Exclude String");

    // Act
    dbsObjectFilter.addExclude("Name");

    // Assert
    List<String> exclude = dbsObjectFilter.getExclude();
    assertEquals(2, exclude.size());
    assertEquals("Exclude String", exclude.get(0));
    assertEquals("Name", exclude.get(1));
    assertFalse(dbsObjectFilter.isEmpty());
    assertFalse(dbsObjectFilter.isNotApplicable());
  }

  /**
   * Test {@link DBSObjectFilter#addExclude(String)}.
   *
   * <ul>
   *   <li>Given {@link DBSObjectFilter#DBSObjectFilter()}.
   *   <li>Then {@link DBSObjectFilter#DBSObjectFilter()} Exclude size is one.
   * </ul>
   *
   * <p>Method under test: {@link DBSObjectFilter#addExclude(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBSObjectFilter.addExclude(String)"})
  public void testAddExclude_givenDBSObjectFilter_thenDBSObjectFilterExcludeSizeIsOne() {
    // Arrange
    DBSObjectFilter dbsObjectFilter = new DBSObjectFilter();

    // Act
    dbsObjectFilter.addExclude("Name");

    // Assert
    List<String> exclude = dbsObjectFilter.getExclude();
    assertEquals(1, exclude.size());
    assertEquals("Name", exclude.get(0));
    assertFalse(dbsObjectFilter.isEmpty());
    assertFalse(dbsObjectFilter.isNotApplicable());
  }

  /**
   * Test {@link DBSObjectFilter#setExclude(List)}.
   *
   * <p>Method under test: {@link DBSObjectFilter#setExclude(List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBSObjectFilter.setExclude(List)"})
  public void testSetExclude() {
    // Arrange
    DBSObjectFilter dbsObjectFilter = new DBSObjectFilter("Include String", "Exclude String");

    ArrayList<String> exclude = new ArrayList<>();
    exclude.add("foo");

    // Act
    dbsObjectFilter.setExclude(exclude);

    // Assert
    assertFalse(dbsObjectFilter.hasSingleMask());
    assertSame(exclude, dbsObjectFilter.getExclude());
  }

  /**
   * Test {@link DBSObjectFilter#setExclude(List)}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>When {@link ArrayList#ArrayList()} add {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link DBSObjectFilter#setExclude(List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBSObjectFilter.setExclude(List)"})
  public void testSetExclude_given42_whenArrayListAdd42() {
    // Arrange
    DBSObjectFilter dbsObjectFilter = new DBSObjectFilter("Include String", "Exclude String");

    ArrayList<String> exclude = new ArrayList<>();
    exclude.add("42");
    exclude.add("foo");

    // Act
    dbsObjectFilter.setExclude(exclude);

    // Assert
    assertFalse(dbsObjectFilter.hasSingleMask());
    assertSame(exclude, dbsObjectFilter.getExclude());
  }

  /**
   * Test {@link DBSObjectFilter#setExclude(List)}.
   *
   * <ul>
   *   <li>Then {@link DBSObjectFilter#DBSObjectFilter(String, String)} with {@code Include String}
   *       and {@code Exclude String} hasSingleMask.
   * </ul>
   *
   * <p>Method under test: {@link DBSObjectFilter#setExclude(List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBSObjectFilter.setExclude(List)"})
  public void testSetExclude_thenDBSObjectFilterWithIncludeStringAndExcludeStringHasSingleMask() {
    // Arrange
    DBSObjectFilter dbsObjectFilter = new DBSObjectFilter("Include String", "Exclude String");
    ArrayList<String> exclude = new ArrayList<>();

    // Act
    dbsObjectFilter.setExclude(exclude);

    // Assert
    assertTrue(dbsObjectFilter.hasSingleMask());
    assertSame(exclude, dbsObjectFilter.getExclude());
  }

  /**
   * Test {@link DBSObjectFilter#isNotApplicable()}.
   *
   * <p>Method under test: {@link DBSObjectFilter#isNotApplicable()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBSObjectFilter.isNotApplicable()"})
  public void testIsNotApplicable() {
    // Arrange, Act and Assert
    assertFalse(new DBSObjectFilter("", "Exclude String").isNotApplicable());
  }

  /**
   * Test {@link DBSObjectFilter#isNotApplicable()}.
   *
   * <p>Method under test: {@link DBSObjectFilter#isNotApplicable()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBSObjectFilter.isNotApplicable()"})
  public void testIsNotApplicable2() {
    // Arrange
    DBSObjectFilter dbsObjectFilter = new DBSObjectFilter("Include String", "Exclude String");
    dbsObjectFilter.setEnabled(false);

    // Act and Assert
    assertTrue(dbsObjectFilter.isNotApplicable());
  }

  /**
   * Test {@link DBSObjectFilter#isNotApplicable()}.
   *
   * <ul>
   *   <li>Given {@link DBSObjectFilter#DBSObjectFilter()} addExclude {@code String}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DBSObjectFilter#isNotApplicable()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBSObjectFilter.isNotApplicable()"})
  public void testIsNotApplicable_givenDBSObjectFilterAddExcludeJavaLangString_thenReturnFalse() {
    // Arrange
    DBSObjectFilter dbsObjectFilter = new DBSObjectFilter();
    dbsObjectFilter.addExclude("java.lang.String");

    // Act and Assert
    assertFalse(dbsObjectFilter.isNotApplicable());
  }

  /**
   * Test {@link DBSObjectFilter#isNotApplicable()}.
   *
   * <ul>
   *   <li>Given {@link DBSObjectFilter#DBSObjectFilter(String, String)} with {@code Include String}
   *       and {@code Exclude String}.
   * </ul>
   *
   * <p>Method under test: {@link DBSObjectFilter#isNotApplicable()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBSObjectFilter.isNotApplicable()"})
  public void testIsNotApplicable_givenDBSObjectFilterWithIncludeStringAndExcludeString() {
    // Arrange, Act and Assert
    assertFalse(new DBSObjectFilter("Include String", "Exclude String").isNotApplicable());
  }

  /**
   * Test {@link DBSObjectFilter#isNotApplicable()}.
   *
   * <ul>
   *   <li>Given {@link DBSObjectFilter#DBSObjectFilter()}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DBSObjectFilter#isNotApplicable()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBSObjectFilter.isNotApplicable()"})
  public void testIsNotApplicable_givenDBSObjectFilter_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(new DBSObjectFilter().isNotApplicable());
  }

  /**
   * Test {@link DBSObjectFilter#isEmpty()}.
   *
   * <ul>
   *   <li>Given {@link DBSObjectFilter#DBSObjectFilter()} addExclude {@code String}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DBSObjectFilter#isEmpty()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBSObjectFilter.isEmpty()"})
  public void testIsEmpty_givenDBSObjectFilterAddExcludeJavaLangString_thenReturnFalse() {
    // Arrange
    DBSObjectFilter dbsObjectFilter = new DBSObjectFilter();
    dbsObjectFilter.addExclude("java.lang.String");

    // Act and Assert
    assertFalse(dbsObjectFilter.isEmpty());
  }

  /**
   * Test {@link DBSObjectFilter#isEmpty()}.
   *
   * <ul>
   *   <li>Given {@link DBSObjectFilter#DBSObjectFilter(String, String)} with {@code Include String}
   *       and {@code Exclude String}.
   * </ul>
   *
   * <p>Method under test: {@link DBSObjectFilter#isEmpty()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBSObjectFilter.isEmpty()"})
  public void testIsEmpty_givenDBSObjectFilterWithIncludeStringAndExcludeString() {
    // Arrange, Act and Assert
    assertFalse(new DBSObjectFilter("Include String", "Exclude String").isEmpty());
  }

  /**
   * Test {@link DBSObjectFilter#isEmpty()}.
   *
   * <ul>
   *   <li>Given {@link DBSObjectFilter#DBSObjectFilter(String, String)} with includeString is empty
   *       string and {@code Exclude String}.
   * </ul>
   *
   * <p>Method under test: {@link DBSObjectFilter#isEmpty()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBSObjectFilter.isEmpty()"})
  public void testIsEmpty_givenDBSObjectFilterWithIncludeStringIsEmptyStringAndExcludeString() {
    // Arrange, Act and Assert
    assertFalse(new DBSObjectFilter("", "Exclude String").isEmpty());
  }

  /**
   * Test {@link DBSObjectFilter#isEmpty()}.
   *
   * <ul>
   *   <li>Given {@link DBSObjectFilter#DBSObjectFilter()}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DBSObjectFilter#isEmpty()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBSObjectFilter.isEmpty()"})
  public void testIsEmpty_givenDBSObjectFilter_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(new DBSObjectFilter().isEmpty());
  }

  /**
   * Test {@link DBSObjectFilter#hasSingleMask()}.
   *
   * <p>Method under test: {@link DBSObjectFilter#hasSingleMask()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBSObjectFilter.hasSingleMask()"})
  public void testHasSingleMask() {
    // Arrange
    DBSObjectFilter dbsObjectFilter = new DBSObjectFilter("Include String", "Exclude String");
    dbsObjectFilter.addInclude("Name");

    // Act and Assert
    assertFalse(dbsObjectFilter.hasSingleMask());
  }

  /**
   * Test {@link DBSObjectFilter#hasSingleMask()}.
   *
   * <p>Method under test: {@link DBSObjectFilter#hasSingleMask()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBSObjectFilter.hasSingleMask()"})
  public void testHasSingleMask2() {
    // Arrange, Act and Assert
    assertTrue(new DBSObjectFilter("Include String", "").hasSingleMask());
  }

  /**
   * Test {@link DBSObjectFilter#hasSingleMask()}.
   *
   * <ul>
   *   <li>Given {@link DBSObjectFilter#DBSObjectFilter()} addInclude {@code Name}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DBSObjectFilter#hasSingleMask()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBSObjectFilter.hasSingleMask()"})
  public void testHasSingleMask_givenDBSObjectFilterAddIncludeName_thenReturnTrue() {
    // Arrange
    DBSObjectFilter dbsObjectFilter = new DBSObjectFilter();
    dbsObjectFilter.addInclude("Name");

    // Act and Assert
    assertTrue(dbsObjectFilter.hasSingleMask());
  }

  /**
   * Test {@link DBSObjectFilter#hasSingleMask()}.
   *
   * <ul>
   *   <li>Given {@link DBSObjectFilter#DBSObjectFilter(String, String)} with {@code Include String}
   *       and {@code Exclude String}.
   * </ul>
   *
   * <p>Method under test: {@link DBSObjectFilter#hasSingleMask()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBSObjectFilter.hasSingleMask()"})
  public void testHasSingleMask_givenDBSObjectFilterWithIncludeStringAndExcludeString() {
    // Arrange, Act and Assert
    assertFalse(new DBSObjectFilter("Include String", "Exclude String").hasSingleMask());
  }

  /**
   * Test {@link DBSObjectFilter#hasSingleMask()}.
   *
   * <ul>
   *   <li>Given {@link DBSObjectFilter#DBSObjectFilter()}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DBSObjectFilter#hasSingleMask()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBSObjectFilter.hasSingleMask()"})
  public void testHasSingleMask_givenDBSObjectFilter_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(new DBSObjectFilter().hasSingleMask());
  }

  /**
   * Test {@link DBSObjectFilter#getSingleMask()}.
   *
   * <p>Method under test: {@link DBSObjectFilter#getSingleMask()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBSObjectFilter.getSingleMask()"})
  public void testGetSingleMask() {
    // Arrange, Act and Assert
    assertNull(new DBSObjectFilter("", "Exclude String").getSingleMask());
  }

  /**
   * Test {@link DBSObjectFilter#getSingleMask()}.
   *
   * <ul>
   *   <li>Given {@link DBSObjectFilter#DBSObjectFilter()}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBSObjectFilter#getSingleMask()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBSObjectFilter.getSingleMask()"})
  public void testGetSingleMask_givenDBSObjectFilter_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(new DBSObjectFilter().getSingleMask());
  }

  /**
   * Test {@link DBSObjectFilter#getSingleMask()}.
   *
   * <ul>
   *   <li>Then return {@code Include String}.
   * </ul>
   *
   * <p>Method under test: {@link DBSObjectFilter#getSingleMask()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBSObjectFilter.getSingleMask()"})
  public void testGetSingleMask_thenReturnIncludeString() {
    // Arrange, Act and Assert
    assertEquals(
        "Include String", new DBSObjectFilter("Include String", "Exclude String").getSingleMask());
  }

  /**
   * Test {@link DBSObjectFilter#matches(String)}.
   *
   * <p>Method under test: {@link DBSObjectFilter#matches(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBSObjectFilter.matches(String)"})
  public void testMatches() {
    // Arrange
    DBSObjectFilter dbsObjectFilter = new DBSObjectFilter("Include String", "Exclude String");
    dbsObjectFilter.setCaseSensitive(true);
    dbsObjectFilter.addInclude("Name");

    // Act and Assert
    assertTrue(dbsObjectFilter.matches("Name"));
  }

  /**
   * Test {@link DBSObjectFilter#matches(String)}.
   *
   * <p>Method under test: {@link DBSObjectFilter#matches(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBSObjectFilter.matches(String)"})
  public void testMatches2() {
    // Arrange
    DBSObjectFilter dbsObjectFilter = new DBSObjectFilter("Include String", "Exclude String");
    dbsObjectFilter.addInclude("");

    // Act and Assert
    assertFalse(dbsObjectFilter.matches("Name"));
  }

  /**
   * Test {@link DBSObjectFilter#matches(String)}.
   *
   * <p>Method under test: {@link DBSObjectFilter#matches(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBSObjectFilter.matches(String)"})
  public void testMatches3() {
    // Arrange
    DBSObjectFilter dbsObjectFilter = new DBSObjectFilter("Include String", "Exclude String");
    dbsObjectFilter.addExclude("");
    dbsObjectFilter.addInclude("Name");

    // Act and Assert
    assertTrue(dbsObjectFilter.matches("Name"));
  }

  /**
   * Test {@link DBSObjectFilter#matches(String)}.
   *
   * <ul>
   *   <li>Given {@link DBSObjectFilter#DBSObjectFilter(String, String)} with {@code Include String}
   *       and {@code Exclude String}.
   * </ul>
   *
   * <p>Method under test: {@link DBSObjectFilter#matches(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBSObjectFilter.matches(String)"})
  public void testMatches_givenDBSObjectFilterWithIncludeStringAndExcludeString() {
    // Arrange, Act and Assert
    assertFalse(new DBSObjectFilter("Include String", "Exclude String").matches("Name"));
  }

  /**
   * Test {@link DBSObjectFilter#matches(String)}.
   *
   * <ul>
   *   <li>Given {@link DBSObjectFilter#DBSObjectFilter(String, String)} with {@code Include String}
   *       and {@code Exclude String} addExclude {@code Name}.
   * </ul>
   *
   * <p>Method under test: {@link DBSObjectFilter#matches(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBSObjectFilter.matches(String)"})
  public void testMatches_givenDBSObjectFilterWithIncludeStringAndExcludeStringAddExcludeName() {
    // Arrange
    DBSObjectFilter dbsObjectFilter = new DBSObjectFilter("Include String", "Exclude String");
    dbsObjectFilter.addExclude("Name");
    dbsObjectFilter.addInclude("Name");

    // Act and Assert
    assertFalse(dbsObjectFilter.matches("Name"));
  }

  /**
   * Test {@link DBSObjectFilter#matches(String)}.
   *
   * <ul>
   *   <li>Given {@link DBSObjectFilter#DBSObjectFilter(String, String)} with {@code Include String}
   *       and {@code Exclude String} addInclude {@code Name}.
   * </ul>
   *
   * <p>Method under test: {@link DBSObjectFilter#matches(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBSObjectFilter.matches(String)"})
  public void testMatches_givenDBSObjectFilterWithIncludeStringAndExcludeStringAddIncludeName() {
    // Arrange
    DBSObjectFilter dbsObjectFilter = new DBSObjectFilter("Include String", "Exclude String");
    dbsObjectFilter.addInclude("Name");

    // Act and Assert
    assertTrue(dbsObjectFilter.matches("Name"));
  }

  /**
   * Test {@link DBSObjectFilter#matches(String)}.
   *
   * <ul>
   *   <li>Given {@link DBSObjectFilter#DBSObjectFilter(String, String)} with includeString is empty
   *       string and {@code Exclude String}.
   * </ul>
   *
   * <p>Method under test: {@link DBSObjectFilter#matches(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBSObjectFilter.matches(String)"})
  public void testMatches_givenDBSObjectFilterWithIncludeStringIsEmptyStringAndExcludeString() {
    // Arrange, Act and Assert
    assertTrue(new DBSObjectFilter("", "Exclude String").matches("Name"));
  }

  /**
   * Test {@link DBSObjectFilter#matches(String)}.
   *
   * <ul>
   *   <li>Given {@link DBSObjectFilter#DBSObjectFilter()}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DBSObjectFilter#matches(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBSObjectFilter.matches(String)"})
  public void testMatches_givenDBSObjectFilter_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(new DBSObjectFilter().matches("Name"));
  }

  /**
   * Test {@link DBSObjectFilter#matchesAny(String[])}.
   *
   * <p>Method under test: {@link DBSObjectFilter#matchesAny(String[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBSObjectFilter.matchesAny(String[])"})
  public void testMatchesAny() {
    // Arrange
    DBSObjectFilter dbsObjectFilter = new DBSObjectFilter("Include String", "Exclude String");
    dbsObjectFilter.setCaseSensitive(true);

    // Act and Assert
    assertFalse(dbsObjectFilter.matchesAny("Names"));
  }

  /**
   * Test {@link DBSObjectFilter#matchesAny(String[])}.
   *
   * <p>Method under test: {@link DBSObjectFilter#matchesAny(String[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBSObjectFilter.matchesAny(String[])"})
  public void testMatchesAny2() {
    // Arrange
    DBSObjectFilter dbsObjectFilter = new DBSObjectFilter("Include String", "Exclude String");
    dbsObjectFilter.addInclude("");

    // Act and Assert
    assertFalse(dbsObjectFilter.matchesAny("Names"));
  }

  /**
   * Test {@link DBSObjectFilter#matchesAny(String[])}.
   *
   * <p>Method under test: {@link DBSObjectFilter#matchesAny(String[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBSObjectFilter.matchesAny(String[])"})
  public void testMatchesAny3() {
    // Arrange, Act and Assert
    assertTrue(new DBSObjectFilter("", "Exclude String").matchesAny("Names"));
  }

  /**
   * Test {@link DBSObjectFilter#matchesAny(String[])}.
   *
   * <ul>
   *   <li>Given {@link DBSObjectFilter#DBSObjectFilter()} addExclude empty string.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DBSObjectFilter#matchesAny(String[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBSObjectFilter.matchesAny(String[])"})
  public void testMatchesAny_givenDBSObjectFilterAddExcludeEmptyString_thenReturnTrue() {
    // Arrange
    DBSObjectFilter dbsObjectFilter = new DBSObjectFilter();
    dbsObjectFilter.addExclude("");

    // Act and Assert
    assertTrue(dbsObjectFilter.matchesAny("Names"));
  }

  /**
   * Test {@link DBSObjectFilter#matchesAny(String[])}.
   *
   * <ul>
   *   <li>Given {@link DBSObjectFilter#DBSObjectFilter()} addExclude {@code String}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DBSObjectFilter#matchesAny(String[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBSObjectFilter.matchesAny(String[])"})
  public void testMatchesAny_givenDBSObjectFilterAddExcludeJavaLangString_thenReturnTrue() {
    // Arrange
    DBSObjectFilter dbsObjectFilter = new DBSObjectFilter();
    dbsObjectFilter.addExclude("java.lang.String");

    // Act and Assert
    assertTrue(dbsObjectFilter.matchesAny("Names"));
  }

  /**
   * Test {@link DBSObjectFilter#matchesAny(String[])}.
   *
   * <ul>
   *   <li>Given {@link DBSObjectFilter#DBSObjectFilter()} addExclude {@code String}.
   *   <li>When {@code String}.
   * </ul>
   *
   * <p>Method under test: {@link DBSObjectFilter#matchesAny(String[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBSObjectFilter.matchesAny(String[])"})
  public void testMatchesAny_givenDBSObjectFilterAddExcludeJavaLangString_whenJavaLangString() {
    // Arrange
    DBSObjectFilter dbsObjectFilter = new DBSObjectFilter();
    dbsObjectFilter.addExclude("java.lang.String");

    // Act and Assert
    assertFalse(dbsObjectFilter.matchesAny("java.lang.String"));
  }

  /**
   * Test {@link DBSObjectFilter#matchesAny(String[])}.
   *
   * <ul>
   *   <li>Given {@link DBSObjectFilter#DBSObjectFilter()} addInclude {@code String}.
   *   <li>When {@code String}.
   * </ul>
   *
   * <p>Method under test: {@link DBSObjectFilter#matchesAny(String[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBSObjectFilter.matchesAny(String[])"})
  public void testMatchesAny_givenDBSObjectFilterAddIncludeJavaLangString_whenJavaLangString() {
    // Arrange
    DBSObjectFilter dbsObjectFilter = new DBSObjectFilter();
    dbsObjectFilter.addInclude("java.lang.String");
    dbsObjectFilter.addExclude("java.lang.String");

    // Act and Assert
    assertFalse(dbsObjectFilter.matchesAny("java.lang.String"));
  }

  /**
   * Test {@link DBSObjectFilter#matchesAny(String[])}.
   *
   * <ul>
   *   <li>Given {@link DBSObjectFilter#DBSObjectFilter(String, String)} with {@code Include String}
   *       and {@code Exclude String}.
   * </ul>
   *
   * <p>Method under test: {@link DBSObjectFilter#matchesAny(String[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBSObjectFilter.matchesAny(String[])"})
  public void testMatchesAny_givenDBSObjectFilterWithIncludeStringAndExcludeString() {
    // Arrange, Act and Assert
    assertFalse(new DBSObjectFilter("Include String", "Exclude String").matchesAny("Names"));
  }

  /**
   * Test {@link DBSObjectFilter#matchesAny(String[])}.
   *
   * <ul>
   *   <li>Given {@link DBSObjectFilter#DBSObjectFilter(String, String)} with {@code Include String}
   *       and {@code Exclude String}.
   * </ul>
   *
   * <p>Method under test: {@link DBSObjectFilter#matchesAny(String[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBSObjectFilter.matchesAny(String[])"})
  public void testMatchesAny_givenDBSObjectFilterWithIncludeStringAndExcludeString2() {
    // Arrange, Act and Assert
    assertFalse(new DBSObjectFilter("Include String", "Exclude String").matchesAny());
  }

  /**
   * Test {@link DBSObjectFilter#matchesAny(String[])}.
   *
   * <ul>
   *   <li>Given {@link DBSObjectFilter#DBSObjectFilter()}.
   *   <li>When {@code Names}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DBSObjectFilter#matchesAny(String[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBSObjectFilter.matchesAny(String[])"})
  public void testMatchesAny_givenDBSObjectFilter_whenNames_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(new DBSObjectFilter().matchesAny("Names"));
  }

  /**
   * Test {@link DBSObjectFilter#matchesAny(String[])}.
   *
   * <ul>
   *   <li>When {@code Names} and {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBSObjectFilter#matchesAny(String[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBSObjectFilter.matchesAny(String[])"})
  public void testMatchesAny_whenNamesAndNull() {
    // Arrange, Act and Assert
    assertFalse(new DBSObjectFilter("Include String", "Exclude String").matchesAny("Names", null));
  }

  /**
   * Test {@link DBSObjectFilter#equals(Object)}, and {@link DBSObjectFilter#hashCode()}.
   *
   * <ul>
   *   <li>When other is equal.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DBSObjectFilter#equals(Object)}
   *   <li>{@link DBSObjectFilter#hashCode()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBSObjectFilter.equals(Object)", "int DBSObjectFilter.hashCode()"})
  public void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    DBSObjectFilter dbsObjectFilter = new DBSObjectFilter("Include String", "Exclude String");
    DBSObjectFilter dbsObjectFilter2 = new DBSObjectFilter("Include String", "Exclude String");

    // Act and Assert
    assertEquals(dbsObjectFilter, dbsObjectFilter2);
    assertEquals(dbsObjectFilter.hashCode(), dbsObjectFilter2.hashCode());
  }

  /**
   * Test {@link DBSObjectFilter#equals(Object)}, and {@link DBSObjectFilter#hashCode()}.
   *
   * <ul>
   *   <li>When other is same.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DBSObjectFilter#equals(Object)}
   *   <li>{@link DBSObjectFilter#hashCode()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBSObjectFilter.equals(Object)", "int DBSObjectFilter.hashCode()"})
  public void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    DBSObjectFilter dbsObjectFilter = new DBSObjectFilter("Include String", "Exclude String");

    // Act and Assert
    assertEquals(dbsObjectFilter, dbsObjectFilter);
    int expectedHashCodeResult = dbsObjectFilter.hashCode();
    assertEquals(expectedHashCodeResult, dbsObjectFilter.hashCode());
  }

  /**
   * Test {@link DBSObjectFilter#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link DBSObjectFilter#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBSObjectFilter.equals(Object)", "int DBSObjectFilter.hashCode()"})
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    DBSObjectFilter dbsObjectFilter = new DBSObjectFilter();

    // Act and Assert
    assertNotEquals(dbsObjectFilter, new DBSObjectFilter("Include String", "Exclude String"));
  }

  /**
   * Test {@link DBSObjectFilter#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link DBSObjectFilter#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBSObjectFilter.equals(Object)", "int DBSObjectFilter.hashCode()"})
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    DBSObjectFilter dbsObjectFilter = new DBSObjectFilter("Include String", "Exclude String");
    dbsObjectFilter.addExclude("Name");

    // Act and Assert
    assertNotEquals(dbsObjectFilter, new DBSObjectFilter("Include String", "Exclude String"));
  }

  /**
   * Test {@link DBSObjectFilter#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link DBSObjectFilter#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBSObjectFilter.equals(Object)", "int DBSObjectFilter.hashCode()"})
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual3() {
    // Arrange
    DBSObjectFilter dbsObjectFilter = new DBSObjectFilter("Include String", "Exclude String");
    dbsObjectFilter.setName("Name");
    dbsObjectFilter.addExclude("Name");

    // Act and Assert
    assertNotEquals(dbsObjectFilter, new DBSObjectFilter("Include String", "Exclude String"));
  }

  /**
   * Test {@link DBSObjectFilter#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link DBSObjectFilter#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBSObjectFilter.equals(Object)", "int DBSObjectFilter.hashCode()"})
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual4() {
    // Arrange
    DBSObjectFilter dbsObjectFilter = new DBSObjectFilter("Include String", "Exclude String");
    dbsObjectFilter.setDescription("The characteristics of someone or something");
    dbsObjectFilter.addExclude("Name");

    // Act and Assert
    assertNotEquals(dbsObjectFilter, new DBSObjectFilter("Include String", "Exclude String"));
  }

  /**
   * Test {@link DBSObjectFilter#equals(Object)}.
   *
   * <ul>
   *   <li>When other is {@code null}.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link DBSObjectFilter#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBSObjectFilter.equals(Object)", "int DBSObjectFilter.hashCode()"})
  public void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new DBSObjectFilter("Include String", "Exclude String"), null);
  }

  /**
   * Test {@link DBSObjectFilter#equals(Object)}.
   *
   * <ul>
   *   <li>When other is wrong type.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link DBSObjectFilter#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBSObjectFilter.equals(Object)", "int DBSObjectFilter.hashCode()"})
  public void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(
        new DBSObjectFilter("Include String", "Exclude String"),
        "Different type to DBSObjectFilter");
  }
}
