package org.jkiss.dbeaver.model.qm.filters;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import org.jkiss.dbeaver.model.exec.DBCExecutionPurpose;
import org.jkiss.dbeaver.model.qm.QMObjectType;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class QMEventCriteriaDiffblueTest {
  /**
   * Test {@link QMEventCriteria#hasSessionId()}.
   *
   * <ul>
   *   <li>Given {@link QMEventCriteria} (default constructor) SessionId is one.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link QMEventCriteria#hasSessionId()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean QMEventCriteria.hasSessionId()"})
  public void testHasSessionId_givenQMEventCriteriaSessionIdIsOne_thenReturnTrue() {
    // Arrange
    QMEventCriteria qmEventCriteria = new QMEventCriteria();
    qmEventCriteria.setSessionId(1L);

    // Act and Assert
    assertTrue(qmEventCriteria.hasSessionId());
  }

  /**
   * Test {@link QMEventCriteria#hasSessionId()}.
   *
   * <ul>
   *   <li>Given {@link QMEventCriteria} (default constructor) SessionId is zero.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link QMEventCriteria#hasSessionId()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean QMEventCriteria.hasSessionId()"})
  public void testHasSessionId_givenQMEventCriteriaSessionIdIsZero_thenReturnFalse() {
    // Arrange
    QMEventCriteria qmEventCriteria = new QMEventCriteria();
    qmEventCriteria.setSessionId(0L);

    // Act and Assert
    assertFalse(qmEventCriteria.hasSessionId());
  }

  /**
   * Test {@link QMEventCriteria#hasSessionId()}.
   *
   * <ul>
   *   <li>Given {@link QMEventCriteria} (default constructor).
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link QMEventCriteria#hasSessionId()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean QMEventCriteria.hasSessionId()"})
  public void testHasSessionId_givenQMEventCriteria_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(new QMEventCriteria().hasSessionId());
  }

  /**
   * Test {@link QMEventCriteria#hasObjectTypes()}.
   *
   * <p>Method under test: {@link QMEventCriteria#hasObjectTypes()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean QMEventCriteria.hasObjectTypes()"})
  public void testHasObjectTypes() {
    // Arrange
    QMEventCriteria qmEventCriteria = new QMEventCriteria();
    qmEventCriteria.setObjectTypes(
        new QMObjectType[] {QMObjectType.session, QMObjectType.txn, QMObjectType.session});

    // Act and Assert
    assertFalse(qmEventCriteria.hasObjectTypes());
  }

  /**
   * Test {@link QMEventCriteria#hasObjectTypes()}.
   *
   * <ul>
   *   <li>Given {@link QMEventCriteria} (default constructor).
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link QMEventCriteria#hasObjectTypes()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean QMEventCriteria.hasObjectTypes()"})
  public void testHasObjectTypes_givenQMEventCriteria_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(new QMEventCriteria().hasObjectTypes());
  }

  /**
   * Test {@link QMEventCriteria#hasObjectTypes()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link QMEventCriteria#hasObjectTypes()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean QMEventCriteria.hasObjectTypes()"})
  public void testHasObjectTypes_thenReturnTrue() {
    // Arrange
    QMEventCriteria qmEventCriteria = new QMEventCriteria();
    qmEventCriteria.setObjectTypes(new QMObjectType[] {QMObjectType.session});

    // Act and Assert
    assertTrue(qmEventCriteria.hasObjectTypes());
  }

  /**
   * Test {@link QMEventCriteria#hasObjectType(QMObjectType)}.
   *
   * <ul>
   *   <li>Given {@link QMEventCriteria} (default constructor).
   *   <li>When {@code session}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link QMEventCriteria#hasObjectType(QMObjectType)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean QMEventCriteria.hasObjectType(QMObjectType)"})
  public void testHasObjectType_givenQMEventCriteria_whenSession_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(new QMEventCriteria().hasObjectType(QMObjectType.session));
  }

  /**
   * Test {@link QMEventCriteria#hasObjectType(QMObjectType)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link QMEventCriteria#hasObjectType(QMObjectType)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean QMEventCriteria.hasObjectType(QMObjectType)"})
  public void testHasObjectType_whenNull_thenReturnFalse() {
    // Arrange
    QMEventCriteria qmEventCriteria = new QMEventCriteria();
    qmEventCriteria.setObjectTypes(new QMObjectType[] {QMObjectType.session});

    // Act and Assert
    assertFalse(qmEventCriteria.hasObjectType(null));
  }

  /**
   * Test {@link QMEventCriteria#hasObjectType(QMObjectType)}.
   *
   * <ul>
   *   <li>When {@code session}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link QMEventCriteria#hasObjectType(QMObjectType)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean QMEventCriteria.hasObjectType(QMObjectType)"})
  public void testHasObjectType_whenSession_thenReturnTrue() {
    // Arrange
    QMEventCriteria qmEventCriteria = new QMEventCriteria();
    qmEventCriteria.setObjectTypes(new QMObjectType[] {QMObjectType.session});

    // Act and Assert
    assertTrue(qmEventCriteria.hasObjectType(QMObjectType.session));
  }

  /**
   * Test {@link QMEventCriteria#hasObjectType(QMObjectType)}.
   *
   * <ul>
   *   <li>When {@code txn}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link QMEventCriteria#hasObjectType(QMObjectType)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean QMEventCriteria.hasObjectType(QMObjectType)"})
  public void testHasObjectType_whenTxn_thenReturnFalse() {
    // Arrange
    QMEventCriteria qmEventCriteria = new QMEventCriteria();
    qmEventCriteria.setObjectTypes(new QMObjectType[] {QMObjectType.session});

    // Act and Assert
    assertFalse(qmEventCriteria.hasObjectType(QMObjectType.txn));
  }

  /**
   * Test {@link QMEventCriteria#hasQueryTypes()}.
   *
   * <ul>
   *   <li>Given {@link QMEventCriteria} (default constructor).
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link QMEventCriteria#hasQueryTypes()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean QMEventCriteria.hasQueryTypes()"})
  public void testHasQueryTypes_givenQMEventCriteria_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(new QMEventCriteria().hasQueryTypes());
  }

  /**
   * Test {@link QMEventCriteria#hasQueryTypes()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link QMEventCriteria#hasQueryTypes()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean QMEventCriteria.hasQueryTypes()"})
  public void testHasQueryTypes_thenReturnTrue() {
    // Arrange
    QMEventCriteria qmEventCriteria = new QMEventCriteria();
    qmEventCriteria.setQueryTypes(new DBCExecutionPurpose[] {DBCExecutionPurpose.USER});

    // Act and Assert
    assertTrue(qmEventCriteria.hasQueryTypes());
  }

  /**
   * Test {@link QMEventCriteria#hasQueryType(DBCExecutionPurpose)}.
   *
   * <ul>
   *   <li>Given {@link QMEventCriteria} (default constructor).
   *   <li>When {@code USER}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link QMEventCriteria#hasQueryType(DBCExecutionPurpose)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean QMEventCriteria.hasQueryType(DBCExecutionPurpose)"})
  public void testHasQueryType_givenQMEventCriteria_whenUser_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(new QMEventCriteria().hasQueryType(DBCExecutionPurpose.USER));
  }

  /**
   * Test {@link QMEventCriteria#hasQueryType(DBCExecutionPurpose)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link QMEventCriteria#hasQueryType(DBCExecutionPurpose)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean QMEventCriteria.hasQueryType(DBCExecutionPurpose)"})
  public void testHasQueryType_whenNull_thenReturnFalse() {
    // Arrange
    QMEventCriteria qmEventCriteria = new QMEventCriteria();
    qmEventCriteria.setQueryTypes(new DBCExecutionPurpose[] {DBCExecutionPurpose.USER});

    // Act and Assert
    assertFalse(qmEventCriteria.hasQueryType(null));
  }

  /**
   * Test {@link QMEventCriteria#hasQueryType(DBCExecutionPurpose)}.
   *
   * <ul>
   *   <li>When {@code USER_FILTERED}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link QMEventCriteria#hasQueryType(DBCExecutionPurpose)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean QMEventCriteria.hasQueryType(DBCExecutionPurpose)"})
  public void testHasQueryType_whenUserFiltered_thenReturnFalse() {
    // Arrange
    QMEventCriteria qmEventCriteria = new QMEventCriteria();
    qmEventCriteria.setQueryTypes(new DBCExecutionPurpose[] {DBCExecutionPurpose.USER});

    // Act and Assert
    assertFalse(qmEventCriteria.hasQueryType(DBCExecutionPurpose.USER_FILTERED));
  }

  /**
   * Test {@link QMEventCriteria#hasQueryType(DBCExecutionPurpose)}.
   *
   * <ul>
   *   <li>When {@code USER}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link QMEventCriteria#hasQueryType(DBCExecutionPurpose)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean QMEventCriteria.hasQueryType(DBCExecutionPurpose)"})
  public void testHasQueryType_whenUser_thenReturnTrue() {
    // Arrange
    QMEventCriteria qmEventCriteria = new QMEventCriteria();
    qmEventCriteria.setQueryTypes(new DBCExecutionPurpose[] {DBCExecutionPurpose.USER});

    // Act and Assert
    assertTrue(qmEventCriteria.hasQueryType(DBCExecutionPurpose.USER));
  }

  /**
   * Test {@link QMEventCriteria#hasDriverIds()}.
   *
   * <ul>
   *   <li>Given {@link HashSet#HashSet()} add {@code foo}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link QMEventCriteria#hasDriverIds()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean QMEventCriteria.hasDriverIds()"})
  public void testHasDriverIds_givenHashSetAddFoo_thenReturnTrue() {
    // Arrange
    HashSet<String> driverIds = new HashSet<>();
    driverIds.add("foo");

    QMEventCriteria qmEventCriteria = new QMEventCriteria();
    qmEventCriteria.setDriverIds(driverIds);

    // Act and Assert
    assertTrue(qmEventCriteria.hasDriverIds());
  }

  /**
   * Test {@link QMEventCriteria#hasDriverIds()}.
   *
   * <ul>
   *   <li>Given {@link QMEventCriteria} (default constructor).
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link QMEventCriteria#hasDriverIds()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean QMEventCriteria.hasDriverIds()"})
  public void testHasDriverIds_givenQMEventCriteria_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(new QMEventCriteria().hasDriverIds());
  }

  /**
   * Test {@link QMEventCriteria#hasEventStatuses()}.
   *
   * <ul>
   *   <li>Given {@link HashSet#HashSet()} add {@code FAILED}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link QMEventCriteria#hasEventStatuses()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean QMEventCriteria.hasEventStatuses()"})
  public void testHasEventStatuses_givenHashSetAddFailed_thenReturnTrue() {
    // Arrange
    HashSet<QMEventStatus> eventStatuses = new HashSet<>();
    eventStatuses.add(QMEventStatus.FAILED);

    QMEventCriteria qmEventCriteria = new QMEventCriteria();
    qmEventCriteria.setEventStatuses(eventStatuses);

    // Act and Assert
    assertTrue(qmEventCriteria.hasEventStatuses());
  }

  /**
   * Test {@link QMEventCriteria#hasEventStatuses()}.
   *
   * <ul>
   *   <li>Given {@link HashSet#HashSet()} add {@code SUCCESS}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link QMEventCriteria#hasEventStatuses()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean QMEventCriteria.hasEventStatuses()"})
  public void testHasEventStatuses_givenHashSetAddSuccess_thenReturnFalse() {
    // Arrange
    HashSet<QMEventStatus> eventStatuses = new HashSet<>();
    eventStatuses.add(QMEventStatus.SUCCESS);
    eventStatuses.add(QMEventStatus.FAILED);

    QMEventCriteria qmEventCriteria = new QMEventCriteria();
    qmEventCriteria.setEventStatuses(eventStatuses);

    // Act and Assert
    assertFalse(qmEventCriteria.hasEventStatuses());
  }

  /**
   * Test {@link QMEventCriteria#hasEventStatuses()}.
   *
   * <ul>
   *   <li>Given {@link QMEventCriteria} (default constructor).
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link QMEventCriteria#hasEventStatuses()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean QMEventCriteria.hasEventStatuses()"})
  public void testHasEventStatuses_givenQMEventCriteria_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(new QMEventCriteria().hasEventStatuses());
  }

  /**
   * Test {@link QMEventCriteria#hasLastEventId()}.
   *
   * <ul>
   *   <li>Given {@link QMEventCriteria} (default constructor) LastEventId is one.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link QMEventCriteria#hasLastEventId()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean QMEventCriteria.hasLastEventId()"})
  public void testHasLastEventId_givenQMEventCriteriaLastEventIdIsOne_thenReturnTrue() {
    // Arrange
    QMEventCriteria qmEventCriteria = new QMEventCriteria();
    qmEventCriteria.setLastEventId(1L);

    // Act and Assert
    assertTrue(qmEventCriteria.hasLastEventId());
  }

  /**
   * Test {@link QMEventCriteria#hasLastEventId()}.
   *
   * <ul>
   *   <li>Given {@link QMEventCriteria} (default constructor).
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link QMEventCriteria#hasLastEventId()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean QMEventCriteria.hasLastEventId()"})
  public void testHasLastEventId_givenQMEventCriteria_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(new QMEventCriteria().hasLastEventId());
  }

  /**
   * Test {@link QMEventCriteria#hasProjectIds()}.
   *
   * <ul>
   *   <li>Given {@link HashSet#HashSet()} add {@code foo}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link QMEventCriteria#hasProjectIds()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean QMEventCriteria.hasProjectIds()"})
  public void testHasProjectIds_givenHashSetAddFoo_thenReturnTrue() {
    // Arrange
    HashSet<String> projectIds = new HashSet<>();
    projectIds.add("foo");

    QMEventCriteria qmEventCriteria = new QMEventCriteria();
    qmEventCriteria.setProjectIds(projectIds);

    // Act and Assert
    assertTrue(qmEventCriteria.hasProjectIds());
  }

  /**
   * Test {@link QMEventCriteria#hasProjectIds()}.
   *
   * <ul>
   *   <li>Given {@link QMEventCriteria} (default constructor).
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link QMEventCriteria#hasProjectIds()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean QMEventCriteria.hasProjectIds()"})
  public void testHasProjectIds_givenQMEventCriteria_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(new QMEventCriteria().hasProjectIds());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link QMEventCriteria#setCatalogs(Set)}
   *   <li>{@link QMEventCriteria#setContainerId(String)}
   *   <li>{@link QMEventCriteria#setDataSourceIds(Set)}
   *   <li>{@link QMEventCriteria#setDateRange(QMDateRange)}
   *   <li>{@link QMEventCriteria#setDesc(boolean)}
   *   <li>{@link QMEventCriteria#setDriverIds(Set)}
   *   <li>{@link QMEventCriteria#setEventStatuses(Set)}
   *   <li>{@link QMEventCriteria#setFetchingSize(int)}
   *   <li>{@link QMEventCriteria#setLastEventId(Long)}
   *   <li>{@link QMEventCriteria#setObjectTypes(QMObjectType[])}
   *   <li>{@link QMEventCriteria#setProjectIds(Set)}
   *   <li>{@link QMEventCriteria#setQueryTypes(DBCExecutionPurpose[])}
   *   <li>{@link QMEventCriteria#setSchemas(Set)}
   *   <li>{@link QMEventCriteria#setSearchString(String)}
   *   <li>{@link QMEventCriteria#setSessionId(Long)}
   *   <li>{@link QMEventCriteria#setSkipEmptyQueries(boolean)}
   *   <li>{@link QMEventCriteria#setSortField(QMSortField)}
   *   <li>{@link QMEventCriteria#getCatalogs()}
   *   <li>{@link QMEventCriteria#getContainerId()}
   *   <li>{@link QMEventCriteria#getDataSourceIds()}
   *   <li>{@link QMEventCriteria#getDateRange()}
   *   <li>{@link QMEventCriteria#getDriverIds()}
   *   <li>{@link QMEventCriteria#getEventStatuses()}
   *   <li>{@link QMEventCriteria#getFetchingSize()}
   *   <li>{@link QMEventCriteria#getLastEventId()}
   *   <li>{@link QMEventCriteria#getObjectTypes()}
   *   <li>{@link QMEventCriteria#getProjectIds()}
   *   <li>{@link QMEventCriteria#getQueryTypes()}
   *   <li>{@link QMEventCriteria#getSchemas()}
   *   <li>{@link QMEventCriteria#getSearchString()}
   *   <li>{@link QMEventCriteria#getSessionId()}
   *   <li>{@link QMEventCriteria#getSortField()}
   *   <li>{@link QMEventCriteria#isDesc()}
   *   <li>{@link QMEventCriteria#isSkipEmptyQueries()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Set QMEventCriteria.getCatalogs()",
    "String QMEventCriteria.getContainerId()",
    "Set QMEventCriteria.getDataSourceIds()",
    "QMDateRange QMEventCriteria.getDateRange()",
    "Set QMEventCriteria.getDriverIds()",
    "Set QMEventCriteria.getEventStatuses()",
    "int QMEventCriteria.getFetchingSize()",
    "Long QMEventCriteria.getLastEventId()",
    "QMObjectType[] QMEventCriteria.getObjectTypes()",
    "Set QMEventCriteria.getProjectIds()",
    "DBCExecutionPurpose[] QMEventCriteria.getQueryTypes()",
    "Set QMEventCriteria.getSchemas()",
    "String QMEventCriteria.getSearchString()",
    "Long QMEventCriteria.getSessionId()",
    "QMSortField QMEventCriteria.getSortField()",
    "boolean QMEventCriteria.isDesc()",
    "boolean QMEventCriteria.isSkipEmptyQueries()",
    "void QMEventCriteria.setCatalogs(Set)",
    "void QMEventCriteria.setContainerId(String)",
    "void QMEventCriteria.setDataSourceIds(Set)",
    "void QMEventCriteria.setDateRange(QMDateRange)",
    "void QMEventCriteria.setDesc(boolean)",
    "void QMEventCriteria.setDriverIds(Set)",
    "void QMEventCriteria.setEventStatuses(Set)",
    "void QMEventCriteria.setFetchingSize(int)",
    "void QMEventCriteria.setLastEventId(Long)",
    "void QMEventCriteria.setObjectTypes(QMObjectType[])",
    "void QMEventCriteria.setProjectIds(Set)",
    "void QMEventCriteria.setQueryTypes(DBCExecutionPurpose[])",
    "void QMEventCriteria.setSchemas(Set)",
    "void QMEventCriteria.setSearchString(String)",
    "void QMEventCriteria.setSessionId(Long)",
    "void QMEventCriteria.setSkipEmptyQueries(boolean)",
    "void QMEventCriteria.setSortField(QMSortField)"
  })
  public void testGettersAndSetters() {
    // Arrange
    QMEventCriteria qmEventCriteria = new QMEventCriteria();
    HashSet<String> catalogs = new HashSet<>();

    // Act
    qmEventCriteria.setCatalogs(catalogs);
    qmEventCriteria.setContainerId("42");
    HashSet<String> dataSourceIds = new HashSet<>();
    qmEventCriteria.setDataSourceIds(dataSourceIds);
    QMDateRange startDateRange =
        new QMDateRange(
            LocalDate.of(1970, 1, 1).atStartOfDay(), LocalDate.of(1970, 1, 1).atStartOfDay());
    qmEventCriteria.setDateRange(startDateRange);
    qmEventCriteria.setDesc(true);
    HashSet<String> driverIds = new HashSet<>();
    qmEventCriteria.setDriverIds(driverIds);
    HashSet<QMEventStatus> eventStatuses = new HashSet<>();
    qmEventCriteria.setEventStatuses(eventStatuses);
    qmEventCriteria.setFetchingSize(3);
    qmEventCriteria.setLastEventId(1L);
    QMObjectType[] objectTypes = new QMObjectType[] {QMObjectType.session};
    qmEventCriteria.setObjectTypes(objectTypes);
    HashSet<String> projectIds = new HashSet<>();
    qmEventCriteria.setProjectIds(projectIds);
    DBCExecutionPurpose[] queryTypes = new DBCExecutionPurpose[] {DBCExecutionPurpose.USER};
    qmEventCriteria.setQueryTypes(queryTypes);
    HashSet<String> schemas = new HashSet<>();
    qmEventCriteria.setSchemas(schemas);
    qmEventCriteria.setSearchString("Search String");
    qmEventCriteria.setSessionId(1L);
    qmEventCriteria.setSkipEmptyQueries(true);
    qmEventCriteria.setSortField(QMSortField.DATE);
    Set<String> actualCatalogs = qmEventCriteria.getCatalogs();
    String actualContainerId = qmEventCriteria.getContainerId();
    Set<String> actualDataSourceIds = qmEventCriteria.getDataSourceIds();
    QMDateRange actualDateRange = qmEventCriteria.getDateRange();
    Set<String> actualDriverIds = qmEventCriteria.getDriverIds();
    Set<QMEventStatus> actualEventStatuses = qmEventCriteria.getEventStatuses();
    int actualFetchingSize = qmEventCriteria.getFetchingSize();
    Long actualLastEventId = qmEventCriteria.getLastEventId();
    QMObjectType[] actualObjectTypes = qmEventCriteria.getObjectTypes();
    Set<String> actualProjectIds = qmEventCriteria.getProjectIds();
    DBCExecutionPurpose[] actualQueryTypes = qmEventCriteria.getQueryTypes();
    Set<String> actualSchemas = qmEventCriteria.getSchemas();
    String actualSearchString = qmEventCriteria.getSearchString();
    Long actualSessionId = qmEventCriteria.getSessionId();
    QMSortField actualSortField = qmEventCriteria.getSortField();
    boolean actualIsDescResult = qmEventCriteria.isDesc();
    boolean actualIsSkipEmptyQueriesResult = qmEventCriteria.isSkipEmptyQueries();

    // Assert
    assertEquals("42", actualContainerId);
    assertEquals("Search String", actualSearchString);
    assertEquals(1L, actualLastEventId.longValue());
    assertEquals(1L, actualSessionId.longValue());
    assertEquals(3, actualFetchingSize);
    assertEquals(QMSortField.DATE, actualSortField);
    assertTrue(actualCatalogs.isEmpty());
    assertTrue(actualDataSourceIds.isEmpty());
    assertTrue(actualDriverIds.isEmpty());
    assertTrue(actualEventStatuses.isEmpty());
    assertTrue(actualProjectIds.isEmpty());
    assertTrue(actualSchemas.isEmpty());
    assertTrue(actualIsDescResult);
    assertTrue(actualIsSkipEmptyQueriesResult);
    assertSame(catalogs, actualCatalogs);
    assertSame(dataSourceIds, actualDataSourceIds);
    assertSame(driverIds, actualDriverIds);
    assertSame(eventStatuses, actualEventStatuses);
    assertSame(projectIds, actualProjectIds);
    assertSame(schemas, actualSchemas);
    assertSame(startDateRange, actualDateRange);
    assertSame(queryTypes, actualQueryTypes);
    assertSame(objectTypes, actualObjectTypes);
    assertArrayEquals(new DBCExecutionPurpose[] {DBCExecutionPurpose.USER}, actualQueryTypes);
    assertArrayEquals(new QMObjectType[] {QMObjectType.session}, actualObjectTypes);
  }

  /**
   * Test new {@link QMEventCriteria} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link QMEventCriteria}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void QMEventCriteria.<init>()"})
  public void testNewQMEventCriteria() {
    // Arrange and Act
    QMEventCriteria actualQmEventCriteria = new QMEventCriteria();

    // Assert
    assertNull(actualQmEventCriteria.getLastEventId());
    assertNull(actualQmEventCriteria.getSessionId());
    assertNull(actualQmEventCriteria.getContainerId());
    assertNull(actualQmEventCriteria.getSearchString());
    assertNull(actualQmEventCriteria.getDateRange());
    assertEquals(0, actualQmEventCriteria.getObjectTypes().length);
    assertEquals(0, actualQmEventCriteria.getQueryTypes().length);
    assertEquals(200, actualQmEventCriteria.getFetchingSize());
    assertEquals(QMSortField.DATE, actualQmEventCriteria.getSortField());
    assertFalse(actualQmEventCriteria.hasDriverIds());
    assertFalse(actualQmEventCriteria.hasLastEventId());
    assertFalse(actualQmEventCriteria.hasProjectIds());
    assertFalse(actualQmEventCriteria.isSkipEmptyQueries());
    Set<String> catalogs = actualQmEventCriteria.getCatalogs();
    assertTrue(catalogs.isEmpty());
    assertTrue(actualQmEventCriteria.isDesc());
    assertSame(catalogs, actualQmEventCriteria.getDataSourceIds());
    assertSame(catalogs, actualQmEventCriteria.getDriverIds());
    assertSame(catalogs, actualQmEventCriteria.getEventStatuses());
    assertSame(catalogs, actualQmEventCriteria.getProjectIds());
    assertSame(catalogs, actualQmEventCriteria.getSchemas());
  }
}
