package com.nearinfinity.agent;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;

import com.nearinfinity.agent.collectors.HDFSCollector;
import com.nearinfinity.agent.collectors.LoggerCollector;

public class Agent {

	public static void main(String[] args) throws ParseException {
		Options options = new Options();
		options.addOption("H", false, "Turns on HDFS monitoring, HDFS uri required");
		options.addOption("l", true, "Turns on Logger server, Logger port to listen on required");
		
		//TODO: Figure out how to configure mysql instance
		
		CommandLineParser parser = new PosixParser();
		CommandLine cmd = parser.parse(options, args);
		
		new Agent(cmd);
	}
	
	public Agent(CommandLine cmd) {
		if (cmd.hasOption('H')) {
//			HDFSCollector.startCollecting(cmd.getOptionValue('H'));
			HDFSCollector.startCollecting();
		}
		
		if (cmd.hasOption('l')) {
			LoggerCollector.startCollecting(cmd.getOptionValue('l'));
		}
	}

}


/*
public class SampleHdfsCode {

    public static void main(String[] args) throws URISyntaxException, IOException {
//        URI uri = new URI("hdfs://namenode:9000/");
        URI uri = new URI("file:///");//local file system for testing without hdfs running
        FileSystem fileSystem = FileSystem.get(uri,new Configuration());
        FileStatus[] listStatus = fileSystem.listStatus(new Path("file:///Users"));
        for (FileStatus fileStatus : listStatus) {
            System.out.println(fileStatus.getPath().toString());
        }
    }
}
*/