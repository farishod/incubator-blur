/**
 * Autogenerated by Thrift Compiler (0.9.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package org.apache.blur.thrift.generated;

/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */




import java.util.Map;
import java.util.HashMap;
import org.apache.blur.thirdparty.thrift_0_9_0.TEnum;

/**
 * <table class="table-bordered table-striped table-condensed">
 * <tr><td colspan="2">Specifies the type of Record mutation that should occur during a mutation of a given Record.</td></tr>
 * <tr><td>DELETE_ENTIRE_RECORD</td><td>Indicates the Record with the given recordId in the given Row is to be deleted.  If the target record does not exist, then no changes are made.</td></tr>
 * <tr><td>REPLACE_ENTIRE_RECORD</td><td>Indicates the Record with the given recordId in the given Row is to be deleted, and a new Record with the same id is to be added. If the specified record does not exist the new record is still added.</td></tr>
 * <tr><td>REPLACE_COLUMNS</td><td>Replace the columns that are specified in the Record mutation.  If the target record does not exist then this mutation will result in a BlurException.</td></tr>
 * <tr><td>APPEND_COLUMN_VALUES</td><td>Append the columns in the Record mutation to the Record that could already exist.  If the target record does not exist then this mutation will result in a BlurException.</td></tr>
 * </table>
 */
public enum RecordMutationType implements org.apache.blur.thirdparty.thrift_0_9_0.TEnum {
  DELETE_ENTIRE_RECORD(0),
  REPLACE_ENTIRE_RECORD(1),
  REPLACE_COLUMNS(2),
  APPEND_COLUMN_VALUES(3);

  private final int value;

  private RecordMutationType(int value) {
    this.value = value;
  }

  /**
   * Get the integer value of this enum value, as defined in the Thrift IDL.
   */
  public int getValue() {
    return value;
  }

  /**
   * Find a the enum type by its integer value, as defined in the Thrift IDL.
   * @return null if the value is not found.
   */
  public static RecordMutationType findByValue(int value) { 
    switch (value) {
      case 0:
        return DELETE_ENTIRE_RECORD;
      case 1:
        return REPLACE_ENTIRE_RECORD;
      case 2:
        return REPLACE_COLUMNS;
      case 3:
        return APPEND_COLUMN_VALUES;
      default:
        return null;
    }
  }
}
