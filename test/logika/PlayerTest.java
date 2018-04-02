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
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author MarleyWins
 */
public class PlayerTest {
    
    private logika.Player player1;
    private logika.Item item1;
    private logika.Item item2;

    /**
     * Default constructor for test class PlayerTest
     */
    public PlayerTest()
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
        player1 = new logika.Player();
        item1 = new logika.Item("ponozka", "cervena");
        item2 = new logika.Item("ponozka", "cervena");
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
    public void testInventare()
    {
        player1.setInventorySize(1);
        assertEquals(true, player1.insertItem(item1));
        assertEquals(false, player1.insertItem(item2));
        player1.setInventorySize(2);
        assertEquals(false, player1.insertItem(item2));
        assertEquals(true, player1.isInInventory("ponozka"));
        assertEquals(item1, player1.removeItem("ponozka"));
        assertEquals(false, player1.isInInventory("ponozka"));
    }

    
    
}
