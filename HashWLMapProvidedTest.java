package F28DA_CW1_2;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

public class HashWLMapProvidedTest {

	/** Test 1: add an entry and number of entries is 1 */
	@Test
	public void test1() {
		try {
			StringHashCode sHC = new StringHashCode();
			float maxLF = (float) 0.5;
			HashWLMap h = new HashWLMap(sHC, maxLF);
			String word = "abc";
			String file = "f1";
			int line = 2;
			WLoc loc = new WLoc(file, line, word);
			List<ILocation> locs = new ArrayList<ILocation>();
			locs.add(loc);
			h.addNew(word, locs);
			assertTrue(h.numberOfEntries() == 1);
		} catch (WLException e) {
			fail();
		}
	}

	/** Test 2: add and find an entry */
	@Test
	public void test2() {
		try {
			StringHashCode sHC = new StringHashCode();
			float maxLF = (float) 0.5;
			HashWLMap h = new HashWLMap(sHC, maxLF);
			String word = "abc";
			String file = "f1";
			int line = 2;
			WLoc loc = new WLoc(file, line, word);
			List<ILocation> locs = new ArrayList<ILocation>();
			locs.add(loc);
			h.addNew(word, locs);
			Iterator<ILocation> locsOut = h.locations(word);
			ILocation locOut = locsOut.next();
			assertTrue(locOut.getFileName().equals(file) && locOut.getLine() == line);
		} catch (WLException e) {
			fail();
		}
	}

	/** Test 3: look for an inexistent key */
	@Test
	public void test3() {
		StringHashCode sHC = new StringHashCode();
		float maxLF = (float) 0.5;
		HashWLMap h = new HashWLMap(sHC, maxLF);
		String word = "abc";
		String file = "f1";
		int line = 2;
		WLoc loc = new WLoc(file, line, word);
		List<ILocation> locs = new ArrayList<ILocation>();
		locs.add(loc);
		try {
			h.addNew(word, locs);
		} catch (WLException e) {
			fail();
		}
		try {
			h.locations(word);
		} catch (WLException e) {
			assertTrue(true);
		}
	}

	/** Test 4: try to delete a nonexistent entry. Should throw an exception. */
	@Test
	public void test4() {
		StringHashCode sHC = new StringHashCode();
		float maxLF = (float) 0.5;
		HashWLMap h = new HashWLMap(sHC, maxLF);
		String word = "abc";
		String file = "f1";
		int line = 2;
		WLoc loc = new WLoc(file, line, word);
		List<ILocation> locs = new ArrayList<ILocation>();
		locs.add(loc);
		try {
			h.addNew(word, locs);
		} catch (WLException e1) {
			fail();
		}
		try {
			h.removeWord("other");
			fail();
		} catch (WLException e) {
			assertTrue(true);
		}
	}

	/** Test 5: delete an actual entry. Should not throw an exception. */
	@Test
	public void test5() {
		try {
			StringHashCode sHC = new StringHashCode();
			float maxLF = (float) 0.5;
			HashWLMap h = new HashWLMap(sHC, maxLF);
			String word1 = "abc";
			String file = "f1";
			int line = 2;
			WLoc loc = new WLoc(file, line, word1);
			List<ILocation> locs = new ArrayList<ILocation>();
			String word2 = "bcd";
			locs.add(loc);
			h.addLoc(word1, loc);
			h.addLoc(word2, loc);
			h.removeLoc(word2, loc);
			assertTrue(h.numberOfEntries() == 1);
			Iterator<ILocation> locsOut = h.locations(word2);
			assertTrue(locsOut.hasNext());
			assertEquals(locsOut.next(), loc);
		} catch (WLException e) {
			assertTrue(true);
		}
	}


	/** Test 6: insert 200 different values into the Map and check that all these values are in the Map */
	@Test
	public void test6() {
		StringHashCode sHC = new StringHashCode();
		float maxLF = (float) 0.5;
		HashWLMap h = new HashWLMap(sHC,maxLF);
		String word;
		int line;
		String file;
		WLoc loc;
		for (int k = 0; k < 200; k++) {
			word = "w" + k;
			line = k + 1;
			file = "f" + k;
			loc = new WLoc(file, line, word);
			h.addLoc(word, loc);
		}
		assertEquals(h.numberOfEntries(), 200);
		for (int k = 0; k < 200; ++k) {
			word = "w" + k;
			try {
				Iterator<ILocation> locs = h.locations(word);
				assertTrue(locs.hasNext());
			} catch (WLException e) {
				fail();
			}

		}
	}

	/** Test 7: Delete a lot of entries from the  Map */
	@Test
	public void test7() {
		try	{
			StringHashCode sHC = new StringHashCode();
			float maxLF = (float) 0.5;
			HashWLMap h = new HashWLMap(sHC,maxLF);
			String word;
			int line;
			String file;
			WLoc loc;
			for (int k = 0; k < 200; ++k) {
				word = "w" + k;
				line = k + 1;
				file = "f" + k;
				loc = new WLoc(file, line, word);
				h.addLoc(word, loc);
			}
			assertEquals(h.numberOfEntries(), 200);
			for ( int k = 0; k < 200; ++k )
			{
				word = "w" + k;
				line = k + 1;
				file = "f" + k;
				loc = new WLoc(file, line, word);
				h.removeLoc(word, loc);
			}
			assertEquals(h.numberOfEntries(), 0);
			for (int k = 0; k < 200; ++k) {
				word = "w" + k;
				try {
					h.locations(word);
					fail();
				} catch (WLException e) {
				}
			}
		} catch (WLException e) {
			fail();
		}
	}

	/** Test 8: Get iterator over all keys */
	@Test
	public void test8() {
		StringHashCode sHC = new StringHashCode();
		float maxLF = (float) 0.5;
		HashWLMap h = new HashWLMap(sHC,maxLF);
		String word;
		int line;
		String file;
		WLoc loc;
		try {
			for (int k = 0; k < 100; k++) {
				word = "w" + k;
				line = k + 1;
				file = "f" + k;
				loc = new WLoc(file, line, word);
				h.addLoc(word, loc);
			}

			for (int k = 10; k < 30; k++) {
				word = "w" + k;
				line = k + 1;
				file = "f" + k;
				loc = new WLoc(file, line, word);
				h.removeLoc(word, loc);
			}
		} catch(WLException e) {
			fail();
		}
		Iterator<String> it = h.words();
		int count = 0;

		while (it.hasNext()) {
			count++;
			it.next();
		}
		assertEquals(h.numberOfEntries(),80);
		assertEquals(count,80);
	}


}
