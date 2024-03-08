
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Creates new DatedHash tables for testing.
 * @author 
 */
public class DateHashTableTest
{
    //~ Setup .................................................................

    //~ Instance/static variables .............................................
    private DateHashTable tableA;
    private DateHashTable tableB;
    private DateHashTable tableC;
    private DatedEntry a;
    private DatedEntry b;
    private DatedEntry c;


    // -------------------------------------------------------------------------
    /**
     * Creates objects for unit testing DateHashTable.
     */
    @Before
    public void setUp()
    {
        tableA = new DateHashTable(100);
        tableB = new DateHashTable(12);
        tableC = new DateHashTable(12);
        a = new DatedEntry("2024/02/08", "Dear journal, I see some code in my future.");
        b = new DatedEntry("2024/03/09", "Dear journal, today I wrote some code.");
        c = new DatedEntry("2023/09/01", "Dear journal, oops I forgot to put this entry in");
        tableA.storeEntry(a);
        tableA.storeEntry(b);
        tableA.storeEntry(c);
    }

    //~ Public Methods --------------------------------------------------------
    // ------------------------------------------------------------------------
    /**
     * Checks bucketEndIdx() returns the proper end index.
     */
    @Test
    public void checkBucketEndIdx()
    {
        assertEquals(99, tableA.bucketEndIdx(11));
        assertEquals(15, tableA.bucketEndIdx(1));
        assertEquals(1, tableB.bucketEndIdx(1));
    }

    //-------------------------------------------------------------------------
    /**
     * Checks bucketStartIdx() returns the proper start index.
     */
    @Test
    public void checkBucketStartIdx()
    {
        assertEquals(8, tableA.bucketStartIdx(1));
        assertEquals(16, tableA.bucketStartIdx(2));
        assertEquals(1, tableB.bucketStartIdx(1));
    }

    // ----------------------------------------------------------------------
    /**
     * Checks getEntry() returns the correct entry.
     */
    @Test
    public void checkGetEntry()
    {
        assertEquals(a, tableA.getEntry("2024/02/08"));
        assertEquals(b, tableA.getEntry("2024/03/09"));
        assertEquals(null, tableB.getEntry("2023/12/1"));
    }

    //-------------------------------------------------------------------------
    /**
     * Checks getTableSize() returns the real size of the table.
     */
    @Test
    public void checkGetTableSize()
    {
        assertEquals(100, tableA.getTableSize());
        assertEquals(12, tableB.getTableSize());
    }

    //-------------------------------------------------------------------------
    /**
     * Checks getNumElements() returns the real number of elements in the table.
     */
    @Test
    public void checkGetNumElements()
    {
        assertEquals(3, tableA.getNumElements());
        assertEquals(0, tableB.getNumElements());
    }

    //------------------------------------------------------------------------
    /*
     * checks getNumElementsInMonth() returns the number of elements in a month.
     */
    @Test
    public void checkGetNumElementsInMonth()
    {
        assertEquals(1, tableA.getNumElementsInMonth(1));
        assertEquals(1, tableA.getNumElementsInMonth(2));
        assertEquals(0, tableB.getNumElementsInMonth(3));
    }

    //---------------------------------------------------------------------------
    /**
     * Checks hashKey() to make sure the proper hashkeys are returned.
     */
    @Test
    public void checkHashKey()
    {
        assertEquals(8, tableA.hashKey(a));
        assertEquals(2, tableB.hashKey(b));
    }

    // -------------------------------------------------------------------------
    /**
     * Checks storeEntry() properly stores the entry.
     */
    @Test
    public void checkStoreEntry()
    {
        assertEquals(tableA.storeEntry(a), true);
        assertEquals(tableA.storeEntry(a), true);
        assertEquals(tableB.storeEntry(a), false);
    }

    // ------------------------------------------------------------------------
    /**
     * Checks entryExists().
     */
    @Test
    public void checkEntryExists()
    {
        assertEquals(tableA.entryExists(a), true);
        assertEquals(tableA.entryExists(b), true);
        assertEquals(tableB.entryExists(a), false);
    }
}
