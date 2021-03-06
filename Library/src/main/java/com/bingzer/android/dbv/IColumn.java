/**
 * Copyright 2013 Ricky Tobing
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
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

/**
 * Represents a column
 *
 * Created by Ricky Tobing on 7/16/13.
 */
public interface IColumn {

    /**
     * Model to open a column
     */
    public static interface Model {

        /**
         * Returns the name of this column
         * @return the name of this columns
         */
        String getName();

        /**
         * Returns the data type
         * @return data type
         */
        String getDataType();

        /**
         * Returns the definition if any
         * @return column definition
         */
        String getDefinition();
    }
}
