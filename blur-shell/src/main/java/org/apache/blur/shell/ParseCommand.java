/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.blur.shell;

import java.io.PrintWriter;

import org.apache.blur.thirdparty.thrift_0_9_0.TException;
import org.apache.blur.thrift.generated.Blur;
import org.apache.blur.thrift.generated.BlurException;
import org.apache.blur.thrift.generated.SimpleQuery;

public class ParseCommand extends Command {
  @Override
  public void doit(PrintWriter out, Blur.Iface client, String[] args) throws CommandException, TException,
      BlurException {
    if (args.length < 3) {
      throw new CommandException("Invalid args: " + help());
    }
    String tablename = args[1];
    String queryStr = "";
    for (int i = 2; i < args.length; i++) {
      queryStr += args[i] + " ";
    }

    SimpleQuery simpleQuery = new SimpleQuery();
    simpleQuery.setQueryStr(queryStr);
    String query = client.parseQuery(tablename, simpleQuery);
    out.println(query);
  }

  @Override
  public String help() {
    return "parse a query and return string representation, args; tablename query";
  }
}