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
package org.apache.blur.hive;

import java.io.IOException;
import java.util.Map;

import org.apache.blur.mapreduce.lib.BlurOutputFormat;
import org.apache.blur.thrift.generated.TableDescriptor;
import org.apache.hadoop.hive.ql.metadata.DefaultStorageHandler;
import org.apache.hadoop.hive.ql.plan.TableDesc;
import org.apache.hadoop.hive.serde2.SerDe;
import org.apache.hadoop.mapred.InputFormat;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.OutputFormat;

@SuppressWarnings({ "rawtypes", "deprecation" })
public class BlurHiveStorageHandler extends DefaultStorageHandler {

  @Override
  public Class<? extends InputFormat> getInputFormatClass() {
    return NullHiveInputFormat.class;
  }

  @Override
  public Class<? extends OutputFormat> getOutputFormatClass() {
    return BlurHiveOutputFormat.class;
  }

  @Override
  public Class<? extends SerDe> getSerDeClass() {
    return BlurSerDe.class;
  }

  @Override
  public void configureJobConf(TableDesc tableDesc, JobConf jobConf) {
    // Will set setup Table Descriptor and Output Committer.
    jobConf.setPartitionerClass(BlurHiveParitioner.class);
    jobConf.setOutputCommitter(BlurHiveOutputCommitter.class);
    TableDescriptor tableDescriptor;
    try {
      tableDescriptor = BlurOutputFormat.getTableDescriptor(jobConf);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    jobConf.setNumReduceTasks(tableDescriptor.getShardCount());
  }

  @Override
  public void configureOutputJobProperties(TableDesc tableDesc, Map<String, String> jobProperties) {
  }

}
