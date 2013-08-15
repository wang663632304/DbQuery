/**
 * Copyright 2013 Ricky Tobing
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance insert the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.bingzer.android.dbv;

import android.database.Cursor;

import com.bingzer.android.dbv.queries.EntitySelectable;
import com.bingzer.android.dbv.queries.Joinable;
import com.bingzer.android.dbv.queries.Pagination;
import com.bingzer.android.dbv.queries.Selectable;

/**
 * Created by Ricky Tobing on 7/16/13.
 */
public interface IQuery<T> {

    /**
     * Build the sql and return a cursor
     * @return
     */
    T query();

    //////////////////////////////////////////////////////
    //////////////////////////////////////////////////////

    /**
     * Represents an insert statement
     *
     * @see InsertWith
     */
    public static interface Insert extends IQuery<Integer> {
    }

    /**
     * Represents an insert "with" statement
     */
    public static interface InsertWith extends IQuery<Integer> {

        /**
         * Values
         * @return
         */
        IQuery<Integer> val(Object... values);
    }

    /**
     * For select statement
     */
    public static interface Select extends IQuery<Cursor>, EntitySelectable, Pagination {

        /**
         * Specified the column to return.
         * default or null will produce SELECT * FROM
         * @param columns
         * @return
         */
        Select columns(String... columns);

        /**
         * Order by. To create multiple orderBy ASC or DESC or both,
         * this is possible
         * <code>
         * <pre>
         *   db.get("Table").select().orderBy("Id", "Name", "Price DESC");
         * </pre>
         * </code>
         * @param columns
         * @return
         */
        OrderBy orderBy(String... columns);

        /**
         * Order By
         */
        public static interface OrderBy extends IQuery<Cursor>, EntitySelectable, Pagination {

        }

    }

    /**
     * Represents a delete statement
     */
    public static interface Delete extends IQuery<Integer> {

    }

    /**
     * Represents an update statement
     */
    public static interface Update extends IQuery<Integer> {

    }

    /**
     * Represents an inner join statement
     *
     * @see OuterJoin
     */
    public static interface InnerJoin extends
            Joinable, Joinable.Inner, Joinable.Outer, Selectable, Select{

    }

    /**
     * Represents an outer join statement
     *
     * @see InnerJoin
     */
    public static interface OuterJoin extends
            Joinable, Joinable.Inner, Joinable.Outer, Selectable, Select{

    }

    ////////////////////////////////////////////////////
    ////////////////////////////////////////////////////

    /**
     * Represents a paging and select statement
     */
    public static interface Paging extends IQuery<Cursor>, EntitySelectable {

        /**
         * Returns the number of row set in the beginning.
         * This number is final
         * @return the number of row
         */
        int getRowLimit();

        /**
         * Returns the current page number.
         *
         * @return the current page number
         * @see #setPageNumber(int)
         */
        int getPageNumber();

        /**
         * Sets the page number.
         * If the pageNumber is under than zero it will throw an IllegalArgumentException.
         *
         * @param pageNumber the page number to set
         */
        void setPageNumber(int pageNumber);

        /**
         * Returns the number of page available.
         * This method will run SQL <code>"SELECT COUNT(*)"</code> query
         * once called. This is very expensive call, but it's useful
         * if you want to know ahead of time how many pages are available
         * @return the number of pages available with the given query
         */
        int getTotalPage();

        /**
         * Returns the current cursor.
         * This also append the pageNumber by one
         *
         * @return current cursor
         */
        Cursor query();

        /**
         * Returns the cursor on the <code>pageNumber</code>.
         * If pageNumber is under than zero it will throw an IllegalArgumentException.
         * If pageNumber is not found, cursor will be null.
         * If called, then {@link #getPageNumber()} will return pageNumber.
         *
         * @param pageNumber the number
         * @return cursor
         */
        Cursor query(int pageNumber);
    }

    ////////////////////////////////////////////////////
    ////////////////////////////////////////////////////

}
