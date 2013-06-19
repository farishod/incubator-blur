package org.apache.blur.manager.writer;

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

import static org.apache.blur.lucene.LuceneVersionConstant.LUCENE_VERSION;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.blur.analysis.BlurAnalyzer;
import org.apache.blur.server.ShardContext;
import org.apache.blur.server.TableContext;
import org.apache.blur.store.buffer.BufferStore;
import org.apache.blur.store.hdfs.HdfsDirectory;
import org.apache.blur.thrift.generated.AnalyzerDefinition;
import org.apache.blur.thrift.generated.Column;
import org.apache.blur.thrift.generated.Record;
import org.apache.blur.thrift.generated.TableDescriptor;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.NRTManager.TrackingIndexWriter;
import org.apache.lucene.store.Directory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class IndexImporterTest {

  private static final Path TMPDIR = new Path("target/tmp");

  private Path base;
  private Configuration configuration;
  private IndexWriter commitWriter;
  private IndexImporter indexImporter;
  private Random random = new Random();
  private Path path;
  private Path badRowIdsPath;
  private IndexWriter mainWriter;
  private FileSystem fileSystem;

  @Before
  public void setup() throws IOException {
    TableContext.clear();
    configuration = new Configuration();
    base = new Path(TMPDIR, "blur-index-importer-test");
    fileSystem = base.getFileSystem(configuration);
    fileSystem.delete(base, true);
    fileSystem.mkdirs(base);
    setupWriter(configuration);
  }

  private void setupWriter(Configuration configuration) throws IOException {
    TableDescriptor tableDescriptor = new TableDescriptor();
    tableDescriptor.setName("test-table");
    String uuid = UUID.randomUUID().toString();
    
    tableDescriptor.setTableUri(new Path(base, "table-table").toUri().toString());
    tableDescriptor.setAnalyzerDefinition(new AnalyzerDefinition());
    tableDescriptor.setShardCount(2);
    
    TableContext tableContext = TableContext.create(tableDescriptor);
    ShardContext shardContext = ShardContext.create(tableContext, "shard-00000000");
    Path tablePath = new Path(base, "table-table");
    Path shardPath = new Path(tablePath, "shard-00000000");
    String indexDirName = "index_" + uuid;
    path = new Path(shardPath, indexDirName + ".commit");
    fileSystem.mkdirs(path);
    badRowIdsPath = new Path(shardPath, indexDirName + ".bad_rowids");
    Directory commitDirectory = new HdfsDirectory(configuration, path);
    Directory mainDirectory = new HdfsDirectory(configuration, shardPath);
    IndexWriterConfig conf = new IndexWriterConfig(LUCENE_VERSION, tableContext.getAnalyzer());
    commitWriter = new IndexWriter(commitDirectory, conf);
    
    mainWriter = new IndexWriter(mainDirectory, conf);
    BufferStore.init(128, 128);
    
    indexImporter = new IndexImporter(new TrackingIndexWriter(mainWriter), new ReentrantReadWriteLock(), shardContext,
        TimeUnit.MINUTES, 10);
  }

  @After
  public void tearDown() throws IOException {
    mainWriter.close();
    indexImporter.close();
    base.getFileSystem(configuration).delete(base, true);
  }


  @Test
  public void testIndexImporterWithCorrectRowIdShardCombination() throws IOException {
    
    Document document = TransactionRecorder.convert("1", genRecord("1"), new BlurAnalyzer());
    commitWriter.addDocument(document);
    commitWriter.commit();
    commitWriter.close();
    indexImporter.run();
    assertFalse(fileSystem.exists(path));
    assertFalse(fileSystem.exists(badRowIdsPath));
  }

//  private void debug(Path file) throws IOException {
//    if (!fileSystem.exists(file)) {
//      return;
//    }
//    System.out.println(file);
//    if (!fileSystem.isFile(file)) {
//      FileStatus[] listStatus = fileSystem.listStatus(file);
//      for (FileStatus f : listStatus) {
//        debug(f.getPath());
//      }
//    }
//  }

  @Test
  public void testIndexImporterWithWrongRowIdShardCombination() throws IOException {
    setupWriter(configuration);
    Document document = TransactionRecorder.convert("2", genRecord("1"), new BlurAnalyzer());
    commitWriter.addDocument(document);
    commitWriter.commit();
    commitWriter.close();
    indexImporter.run();
    assertFalse(fileSystem.exists(path));
    assertTrue(fileSystem.exists(badRowIdsPath));
  }

  private Record genRecord(String recordId) {
    Record record = new Record();
    record.setFamily("testing");
    record.setRecordId(recordId);
    for (int i = 0; i < 10; i++) {
      record.addToColumns(new Column("col" + i, Long.toString(random.nextLong())));
    }
    return record;
  }

}
