/*
 * Copyright (C) 2017 MarleyWins
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package logika;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author MarleyWins
 */
public class ProstorTest {
    
    /**
     * Default constructor for test class ProstorTest
     */
    public ProstorTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }

    @Test
    public void prostorTest()
    {
        logika.Prostor prostor2 = new logika.Prostor("hall", "big");
        logika.Item item3 = new logika.Item("stone", "black");
        logika.Item item4 = new logika.Item("picture", "cute");
        logika.Npc npc3 = new logika.Npc("Lois", 'h', "very pigeon", 5, true);
        logika.Npc npc4 = new logika.Npc("Zero", 'r', "zap zap", 5, false);
        assertEquals(true, prostor2.addItem(item3));
        assertEquals(true, prostor2.addNpc(npc3));
        assertEquals(true, prostor2.isItemIn("stone"));
        assertEquals(true, prostor2.isNpcIn("Lois"));
        assertEquals(false, prostor2.isItemIn("picture"));
        assertEquals(false, prostor2.isNpcIn("Zero"));
        assertEquals(true, prostor2.addItem(item4));
        assertEquals(true, prostor2.addNpc(npc4));
        prostor2.removeItem("stone");
        assertEquals(true, prostor2.isNpcIn("Zero"));
        assertFalse(prostor2.isItemIn("stone"));
    }
}
