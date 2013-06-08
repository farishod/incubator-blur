package org.apache.blur.agent.collectors.blur.query;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;

import org.apache.blur.MiniCluster;
import org.apache.blur.agent.connections.blur.BlurDatabaseConnection;
import org.apache.blur.agent.test.BlurAgentBaseTestClass;
import org.apache.blur.thirdparty.thrift_0_9_0.TException;
import org.apache.blur.thrift.BlurClient;
import org.apache.blur.thrift.generated.AnalyzerDefinition;
import org.apache.blur.thrift.generated.Blur.Iface;
import org.apache.blur.thrift.generated.BlurException;
import org.apache.blur.thrift.generated.BlurQuery;
import org.apache.blur.thrift.generated.ScoreType;
import org.apache.blur.thrift.generated.SimpleQuery;
import org.apache.blur.thrift.generated.TableDescriptor;
import org.junit.Test;


public class QueryCollectorTest extends BlurAgentBaseTestClass {
	private static BlurDatabaseConnection database = new BlurDatabaseConnection(jdbc);

	@Test
	public void shouldAddQueriesToDB() throws BlurException, TException, IOException {
		Iface blurConnection = BlurClient.getClient(MiniCluster.getControllerConnectionStr());


		TableDescriptor td = new TableDescriptor(); 
		td.setTableUri(MiniCluster.getFileSystemUri() + "/blur-tables/test-table");
		td.setAnalyzerDefinition(new AnalyzerDefinition());
		td.setName("test");

		blurConnection.createTable(td);
		
		BlurQuery query = new BlurQuery();
		query.setSimpleQuery(new SimpleQuery("test.col:*", true, ScoreType.SUPER, null, null));
		blurConnection.query("test", query);
		
		Thread testQueryCollector = new Thread(new QueryCollector(BlurClient.getClient(MiniCluster.getControllerConnectionStr()), "test",
				1, database), "Query Test Thread");
		testQueryCollector.start();
		try {
			testQueryCollector.join();
		} catch (InterruptedException e) {
			fail("The test QueryCollector failed while waiting for it to finish!");
		}
		int query_count = jdbc.queryForInt("select count(id) from blur_queries");
		assertEquals(1, query_count);
	}
}
