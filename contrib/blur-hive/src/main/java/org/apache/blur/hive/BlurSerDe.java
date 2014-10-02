package org.apache.blur.hive;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.blur.BlurConfiguration;
import org.apache.blur.mapreduce.lib.BlurRecord;
import org.apache.blur.thirdparty.thrift_0_9_0.TException;
import org.apache.blur.thrift.BlurClient;
import org.apache.blur.thrift.generated.Blur.Iface;
import org.apache.blur.thrift.generated.BlurException;
import org.apache.blur.thrift.generated.ColumnDefinition;
import org.apache.blur.thrift.generated.Schema;
import org.apache.blur.thrift.generated.TableDescriptor;
import org.apache.blur.utils.BlurConstants;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.serde2.AbstractSerDe;
import org.apache.hadoop.hive.serde2.SerDeException;
import org.apache.hadoop.hive.serde2.SerDeStats;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfo;
import org.apache.hadoop.io.Writable;

/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with this
 * work for additional information regarding copyright ownership. The ASF
 * licenses this file to You under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

public class BlurSerDe extends AbstractSerDe {

  private static final String FAMILY = "blur.family";
  private static final String TABLE = "blur.table";
  private String _family;
  private Map<String, ColumnDefinition> _schema;
  private ObjectInspector _objectInspector;
  private List<String> _columnNames;
  private List<TypeInfo> _columnTypes;
  private BlurSerializer _serializer;

  @Override
  public void initialize(Configuration conf, Properties tbl) throws SerDeException {
    String table = tbl.getProperty(TABLE);
    _family = tbl.getProperty(FAMILY);
    BlurConfiguration configuration;
    try {
      configuration = new BlurConfiguration();
      configuration.set(BlurConstants.BLUR_ZOOKEEPER_CONNECTION,
          tbl.getProperty(BlurConstants.BLUR_ZOOKEEPER_CONNECTION));
    } catch (IOException e) {
      throw new SerDeException(e);
    }

    Iface client = BlurClient.getClient(configuration);
    // TableDescriptor tableDescriptor;
    Schema schema;
    try {
      List<String> tableList = client.tableList();
      if (!tableList.contains(table)) {
        throw new SerDeException("Table [" + table + "] does not exist.");
      }
      // tableDescriptor = client.describe(table);
      schema = client.schema(table);
    } catch (BlurException e) {
      throw new SerDeException(e);
    } catch (TException e) {
      throw new SerDeException(e);
    }

    Map<String, ColumnDefinition> columns = schema.getFamilies().get(_family);
    if (columns == null) {
      throw new SerDeException("Family [" + _family + "] does not exist in table [" + table + "]");
    }

    _schema = new HashMap<String, ColumnDefinition>();
    for (ColumnDefinition columnDefinition : columns.values()) {
      String subColumnName = columnDefinition.getSubColumnName();
      if (subColumnName == null) {
        _schema.put(columnDefinition.getColumnName(), columnDefinition);
      }
    }

    BlurObjectInspectorGenerator blurObjectInspectorGenerator = new BlurObjectInspectorGenerator(_schema);
    _objectInspector = blurObjectInspectorGenerator.getObjectInspector();
    _columnNames = blurObjectInspectorGenerator.getColumnNames();
    _columnTypes = blurObjectInspectorGenerator.getColumnTypes();

    _serializer = new BlurSerializer();
  }

  @Override
  public Writable serialize(Object o, ObjectInspector oi) throws SerDeException {
    return _serializer.serialize(o, oi, _columnNames, _columnTypes, _schema, _family);
  }

  @Override
  public Object deserialize(Writable writable) throws SerDeException {
    throw new RuntimeException("Not Implemented");
  }

  @Override
  public ObjectInspector getObjectInspector() throws SerDeException {
    return _objectInspector;
  }

  @Override
  public SerDeStats getSerDeStats() {
    return null;
  }

  @Override
  public Class<? extends Writable> getSerializedClass() {
    return BlurRecord.class;
  }

}