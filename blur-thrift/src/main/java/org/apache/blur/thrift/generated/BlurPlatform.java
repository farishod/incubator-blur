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



import org.apache.blur.thirdparty.thrift_0_9_0.scheme.IScheme;
import org.apache.blur.thirdparty.thrift_0_9_0.scheme.SchemeFactory;
import org.apache.blur.thirdparty.thrift_0_9_0.scheme.StandardScheme;

import org.apache.blur.thirdparty.thrift_0_9_0.scheme.TupleScheme;
import org.apache.blur.thirdparty.thrift_0_9_0.protocol.TTupleProtocol;
import org.apache.blur.thirdparty.thrift_0_9_0.protocol.TProtocolException;
import org.apache.blur.thirdparty.thrift_0_9_0.EncodingUtils;
import org.apache.blur.thirdparty.thrift_0_9_0.TException;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.Arrays;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

public class BlurPlatform {

  public interface Iface {

    public BlurCommandResponse execute(BlurCommandRequest request) throws BlurException, org.apache.blur.thirdparty.thrift_0_9_0.TException;

  }

  public interface AsyncIface {

    public void execute(BlurCommandRequest request, org.apache.blur.thirdparty.thrift_0_9_0.async.AsyncMethodCallback<AsyncClient.execute_call> resultHandler) throws org.apache.blur.thirdparty.thrift_0_9_0.TException;

  }

  public static class Client extends org.apache.blur.thirdparty.thrift_0_9_0.TServiceClient implements Iface {
    public static class Factory implements org.apache.blur.thirdparty.thrift_0_9_0.TServiceClientFactory<Client> {
      public Factory() {}
      public Client getClient(org.apache.blur.thirdparty.thrift_0_9_0.protocol.TProtocol prot) {
        return new Client(prot);
      }
      public Client getClient(org.apache.blur.thirdparty.thrift_0_9_0.protocol.TProtocol iprot, org.apache.blur.thirdparty.thrift_0_9_0.protocol.TProtocol oprot) {
        return new Client(iprot, oprot);
      }
    }

    public Client(org.apache.blur.thirdparty.thrift_0_9_0.protocol.TProtocol prot)
    {
      super(prot, prot);
    }

    public Client(org.apache.blur.thirdparty.thrift_0_9_0.protocol.TProtocol iprot, org.apache.blur.thirdparty.thrift_0_9_0.protocol.TProtocol oprot) {
      super(iprot, oprot);
    }

    public BlurCommandResponse execute(BlurCommandRequest request) throws BlurException, org.apache.blur.thirdparty.thrift_0_9_0.TException
    {
      send_execute(request);
      return recv_execute();
    }

    public void send_execute(BlurCommandRequest request) throws org.apache.blur.thirdparty.thrift_0_9_0.TException
    {
      execute_args args = new execute_args();
      args.setRequest(request);
      sendBase("execute", args);
    }

    public BlurCommandResponse recv_execute() throws BlurException, org.apache.blur.thirdparty.thrift_0_9_0.TException
    {
      execute_result result = new execute_result();
      receiveBase(result, "execute");
      if (result.isSetSuccess()) {
        return result.success;
      }
      if (result.ex != null) {
        throw result.ex;
      }
      throw new org.apache.blur.thirdparty.thrift_0_9_0.TApplicationException(org.apache.blur.thirdparty.thrift_0_9_0.TApplicationException.MISSING_RESULT, "execute failed: unknown result");
    }

  }
  public static class AsyncClient extends org.apache.blur.thirdparty.thrift_0_9_0.async.TAsyncClient implements AsyncIface {
    public static class Factory implements org.apache.blur.thirdparty.thrift_0_9_0.async.TAsyncClientFactory<AsyncClient> {
      private org.apache.blur.thirdparty.thrift_0_9_0.async.TAsyncClientManager clientManager;
      private org.apache.blur.thirdparty.thrift_0_9_0.protocol.TProtocolFactory protocolFactory;
      public Factory(org.apache.blur.thirdparty.thrift_0_9_0.async.TAsyncClientManager clientManager, org.apache.blur.thirdparty.thrift_0_9_0.protocol.TProtocolFactory protocolFactory) {
        this.clientManager = clientManager;
        this.protocolFactory = protocolFactory;
      }
      public AsyncClient getAsyncClient(org.apache.blur.thirdparty.thrift_0_9_0.transport.TNonblockingTransport transport) {
        return new AsyncClient(protocolFactory, clientManager, transport);
      }
    }

    public AsyncClient(org.apache.blur.thirdparty.thrift_0_9_0.protocol.TProtocolFactory protocolFactory, org.apache.blur.thirdparty.thrift_0_9_0.async.TAsyncClientManager clientManager, org.apache.blur.thirdparty.thrift_0_9_0.transport.TNonblockingTransport transport) {
      super(protocolFactory, clientManager, transport);
    }

    public void execute(BlurCommandRequest request, org.apache.blur.thirdparty.thrift_0_9_0.async.AsyncMethodCallback<execute_call> resultHandler) throws org.apache.blur.thirdparty.thrift_0_9_0.TException {
      checkReady();
      execute_call method_call = new execute_call(request, resultHandler, this, ___protocolFactory, ___transport);
      this.___currentMethod = method_call;
      ___manager.call(method_call);
    }

    public static class execute_call extends org.apache.blur.thirdparty.thrift_0_9_0.async.TAsyncMethodCall {
      private BlurCommandRequest request;
      public execute_call(BlurCommandRequest request, org.apache.blur.thirdparty.thrift_0_9_0.async.AsyncMethodCallback<execute_call> resultHandler, org.apache.blur.thirdparty.thrift_0_9_0.async.TAsyncClient client, org.apache.blur.thirdparty.thrift_0_9_0.protocol.TProtocolFactory protocolFactory, org.apache.blur.thirdparty.thrift_0_9_0.transport.TNonblockingTransport transport) throws org.apache.blur.thirdparty.thrift_0_9_0.TException {
        super(client, protocolFactory, transport, resultHandler, false);
        this.request = request;
      }

      public void write_args(org.apache.blur.thirdparty.thrift_0_9_0.protocol.TProtocol prot) throws org.apache.blur.thirdparty.thrift_0_9_0.TException {
        prot.writeMessageBegin(new org.apache.blur.thirdparty.thrift_0_9_0.protocol.TMessage("execute", org.apache.blur.thirdparty.thrift_0_9_0.protocol.TMessageType.CALL, 0));
        execute_args args = new execute_args();
        args.setRequest(request);
        args.write(prot);
        prot.writeMessageEnd();
      }

      public BlurCommandResponse getResult() throws BlurException, org.apache.blur.thirdparty.thrift_0_9_0.TException {
        if (getState() != org.apache.blur.thirdparty.thrift_0_9_0.async.TAsyncMethodCall.State.RESPONSE_READ) {
          throw new IllegalStateException("Method call not finished!");
        }
        org.apache.blur.thirdparty.thrift_0_9_0.transport.TMemoryInputTransport memoryTransport = new org.apache.blur.thirdparty.thrift_0_9_0.transport.TMemoryInputTransport(getFrameBuffer().array());
        org.apache.blur.thirdparty.thrift_0_9_0.protocol.TProtocol prot = client.getProtocolFactory().getProtocol(memoryTransport);
        return (new Client(prot)).recv_execute();
      }
    }

  }

  public static class Processor<I extends Iface> extends org.apache.blur.thirdparty.thrift_0_9_0.TBaseProcessor<I> implements org.apache.blur.thirdparty.thrift_0_9_0.TProcessor {
    //private static final Logger LOGGER = LoggerFactory.getLogger(Processor.class.getName());
    public Processor(I iface) {
      super(iface, getProcessMap(new HashMap<String, org.apache.blur.thirdparty.thrift_0_9_0.ProcessFunction<I, ? extends org.apache.blur.thirdparty.thrift_0_9_0.TBase>>()));
    }

    protected Processor(I iface, Map<String,  org.apache.blur.thirdparty.thrift_0_9_0.ProcessFunction<I, ? extends  org.apache.blur.thirdparty.thrift_0_9_0.TBase>> processMap) {
      super(iface, getProcessMap(processMap));
    }

    private static <I extends Iface> Map<String,  org.apache.blur.thirdparty.thrift_0_9_0.ProcessFunction<I, ? extends  org.apache.blur.thirdparty.thrift_0_9_0.TBase>> getProcessMap(Map<String,  org.apache.blur.thirdparty.thrift_0_9_0.ProcessFunction<I, ? extends  org.apache.blur.thirdparty.thrift_0_9_0.TBase>> processMap) {
      processMap.put("execute", new execute());
      return processMap;
    }

    public static class execute<I extends Iface> extends org.apache.blur.thirdparty.thrift_0_9_0.ProcessFunction<I, execute_args> {
      public execute() {
        super("execute");
      }

      public execute_args getEmptyArgsInstance() {
        return new execute_args();
      }

      protected boolean isOneway() {
        return false;
      }

      public execute_result getResult(I iface, execute_args args) throws org.apache.blur.thirdparty.thrift_0_9_0.TException {
        execute_result result = new execute_result();
        try {
          result.success = iface.execute(args.request);
        } catch (BlurException ex) {
          result.ex = ex;
        }
        return result;
      }
    }

  }

  public static class execute_args implements org.apache.blur.thirdparty.thrift_0_9_0.TBase<execute_args, execute_args._Fields>, java.io.Serializable, Cloneable   {
    private static final org.apache.blur.thirdparty.thrift_0_9_0.protocol.TStruct STRUCT_DESC = new org.apache.blur.thirdparty.thrift_0_9_0.protocol.TStruct("execute_args");

    private static final org.apache.blur.thirdparty.thrift_0_9_0.protocol.TField REQUEST_FIELD_DESC = new org.apache.blur.thirdparty.thrift_0_9_0.protocol.TField("request", org.apache.blur.thirdparty.thrift_0_9_0.protocol.TType.STRUCT, (short)1);

    private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
    static {
      schemes.put(StandardScheme.class, new execute_argsStandardSchemeFactory());
      schemes.put(TupleScheme.class, new execute_argsTupleSchemeFactory());
    }

    public BlurCommandRequest request; // required

    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements org.apache.blur.thirdparty.thrift_0_9_0.TFieldIdEnum {
      REQUEST((short)1, "request");

      private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

      static {
        for (_Fields field : EnumSet.allOf(_Fields.class)) {
          byName.put(field.getFieldName(), field);
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, or null if its not found.
       */
      public static _Fields findByThriftId(int fieldId) {
        switch(fieldId) {
          case 1: // REQUEST
            return REQUEST;
          default:
            return null;
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, throwing an exception
       * if it is not found.
       */
      public static _Fields findByThriftIdOrThrow(int fieldId) {
        _Fields fields = findByThriftId(fieldId);
        if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
        return fields;
      }

      /**
       * Find the _Fields constant that matches name, or null if its not found.
       */
      public static _Fields findByName(String name) {
        return byName.get(name);
      }

      private final short _thriftId;
      private final String _fieldName;

      _Fields(short thriftId, String fieldName) {
        _thriftId = thriftId;
        _fieldName = fieldName;
      }

      public short getThriftFieldId() {
        return _thriftId;
      }

      public String getFieldName() {
        return _fieldName;
      }
    }

    // isset id assignments
    public static final Map<_Fields, org.apache.blur.thirdparty.thrift_0_9_0.meta_data.FieldMetaData> metaDataMap;
    static {
      Map<_Fields, org.apache.blur.thirdparty.thrift_0_9_0.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.blur.thirdparty.thrift_0_9_0.meta_data.FieldMetaData>(_Fields.class);
      tmpMap.put(_Fields.REQUEST, new org.apache.blur.thirdparty.thrift_0_9_0.meta_data.FieldMetaData("request", org.apache.blur.thirdparty.thrift_0_9_0.TFieldRequirementType.DEFAULT, 
          new org.apache.blur.thirdparty.thrift_0_9_0.meta_data.StructMetaData(org.apache.blur.thirdparty.thrift_0_9_0.protocol.TType.STRUCT, BlurCommandRequest.class)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      org.apache.blur.thirdparty.thrift_0_9_0.meta_data.FieldMetaData.addStructMetaDataMap(execute_args.class, metaDataMap);
    }

    public execute_args() {
    }

    public execute_args(
      BlurCommandRequest request)
    {
      this();
      this.request = request;
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public execute_args(execute_args other) {
      if (other.isSetRequest()) {
        this.request = new BlurCommandRequest(other.request);
      }
    }

    public execute_args deepCopy() {
      return new execute_args(this);
    }

    @Override
    public void clear() {
      this.request = null;
    }

    public BlurCommandRequest getRequest() {
      return this.request;
    }

    public execute_args setRequest(BlurCommandRequest request) {
      this.request = request;
      return this;
    }

    public void unsetRequest() {
      this.request = null;
    }

    /** Returns true if field request is set (has been assigned a value) and false otherwise */
    public boolean isSetRequest() {
      return this.request != null;
    }

    public void setRequestIsSet(boolean value) {
      if (!value) {
        this.request = null;
      }
    }

    public void setFieldValue(_Fields field, Object value) {
      switch (field) {
      case REQUEST:
        if (value == null) {
          unsetRequest();
        } else {
          setRequest((BlurCommandRequest)value);
        }
        break;

      }
    }

    public Object getFieldValue(_Fields field) {
      switch (field) {
      case REQUEST:
        return getRequest();

      }
      throw new IllegalStateException();
    }

    /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
      if (field == null) {
        throw new IllegalArgumentException();
      }

      switch (field) {
      case REQUEST:
        return isSetRequest();
      }
      throw new IllegalStateException();
    }

    @Override
    public boolean equals(Object that) {
      if (that == null)
        return false;
      if (that instanceof execute_args)
        return this.equals((execute_args)that);
      return false;
    }

    public boolean equals(execute_args that) {
      if (that == null)
        return false;

      boolean this_present_request = true && this.isSetRequest();
      boolean that_present_request = true && that.isSetRequest();
      if (this_present_request || that_present_request) {
        if (!(this_present_request && that_present_request))
          return false;
        if (!this.request.equals(that.request))
          return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      return 0;
    }

    public int compareTo(execute_args other) {
      if (!getClass().equals(other.getClass())) {
        return getClass().getName().compareTo(other.getClass().getName());
      }

      int lastComparison = 0;
      execute_args typedOther = (execute_args)other;

      lastComparison = Boolean.valueOf(isSetRequest()).compareTo(typedOther.isSetRequest());
      if (lastComparison != 0) {
        return lastComparison;
      }
      if (isSetRequest()) {
        lastComparison = org.apache.blur.thirdparty.thrift_0_9_0.TBaseHelper.compareTo(this.request, typedOther.request);
        if (lastComparison != 0) {
          return lastComparison;
        }
      }
      return 0;
    }

    public _Fields fieldForId(int fieldId) {
      return _Fields.findByThriftId(fieldId);
    }

    public void read(org.apache.blur.thirdparty.thrift_0_9_0.protocol.TProtocol iprot) throws org.apache.blur.thirdparty.thrift_0_9_0.TException {
      schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
    }

    public void write(org.apache.blur.thirdparty.thrift_0_9_0.protocol.TProtocol oprot) throws org.apache.blur.thirdparty.thrift_0_9_0.TException {
      schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder("execute_args(");
      boolean first = true;

      sb.append("request:");
      if (this.request == null) {
        sb.append("null");
      } else {
        sb.append(this.request);
      }
      first = false;
      sb.append(")");
      return sb.toString();
    }

    public void validate() throws org.apache.blur.thirdparty.thrift_0_9_0.TException {
      // check for required fields
      // check for sub-struct validity
    }

    private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
      try {
        write(new org.apache.blur.thirdparty.thrift_0_9_0.protocol.TCompactProtocol(new org.apache.blur.thirdparty.thrift_0_9_0.transport.TIOStreamTransport(out)));
      } catch (org.apache.blur.thirdparty.thrift_0_9_0.TException te) {
        throw new java.io.IOException(te);
      }
    }

    private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
      try {
        read(new org.apache.blur.thirdparty.thrift_0_9_0.protocol.TCompactProtocol(new org.apache.blur.thirdparty.thrift_0_9_0.transport.TIOStreamTransport(in)));
      } catch (org.apache.blur.thirdparty.thrift_0_9_0.TException te) {
        throw new java.io.IOException(te);
      }
    }

    private static class execute_argsStandardSchemeFactory implements SchemeFactory {
      public execute_argsStandardScheme getScheme() {
        return new execute_argsStandardScheme();
      }
    }

    private static class execute_argsStandardScheme extends StandardScheme<execute_args> {

      public void read(org.apache.blur.thirdparty.thrift_0_9_0.protocol.TProtocol iprot, execute_args struct) throws org.apache.blur.thirdparty.thrift_0_9_0.TException {
        org.apache.blur.thirdparty.thrift_0_9_0.protocol.TField schemeField;
        iprot.readStructBegin();
        while (true)
        {
          schemeField = iprot.readFieldBegin();
          if (schemeField.type == org.apache.blur.thirdparty.thrift_0_9_0.protocol.TType.STOP) { 
            break;
          }
          switch (schemeField.id) {
            case 1: // REQUEST
              if (schemeField.type == org.apache.blur.thirdparty.thrift_0_9_0.protocol.TType.STRUCT) {
                struct.request = new BlurCommandRequest();
                struct.request.read(iprot);
                struct.setRequestIsSet(true);
              } else { 
                org.apache.blur.thirdparty.thrift_0_9_0.protocol.TProtocolUtil.skip(iprot, schemeField.type);
              }
              break;
            default:
              org.apache.blur.thirdparty.thrift_0_9_0.protocol.TProtocolUtil.skip(iprot, schemeField.type);
          }
          iprot.readFieldEnd();
        }
        iprot.readStructEnd();

        // check for required fields of primitive type, which can't be checked in the validate method
        struct.validate();
      }

      public void write(org.apache.blur.thirdparty.thrift_0_9_0.protocol.TProtocol oprot, execute_args struct) throws org.apache.blur.thirdparty.thrift_0_9_0.TException {
        struct.validate();

        oprot.writeStructBegin(STRUCT_DESC);
        if (struct.request != null) {
          oprot.writeFieldBegin(REQUEST_FIELD_DESC);
          struct.request.write(oprot);
          oprot.writeFieldEnd();
        }
        oprot.writeFieldStop();
        oprot.writeStructEnd();
      }

    }

    private static class execute_argsTupleSchemeFactory implements SchemeFactory {
      public execute_argsTupleScheme getScheme() {
        return new execute_argsTupleScheme();
      }
    }

    private static class execute_argsTupleScheme extends TupleScheme<execute_args> {

      @Override
      public void write(org.apache.blur.thirdparty.thrift_0_9_0.protocol.TProtocol prot, execute_args struct) throws org.apache.blur.thirdparty.thrift_0_9_0.TException {
        TTupleProtocol oprot = (TTupleProtocol) prot;
        BitSet optionals = new BitSet();
        if (struct.isSetRequest()) {
          optionals.set(0);
        }
        oprot.writeBitSet(optionals, 1);
        if (struct.isSetRequest()) {
          struct.request.write(oprot);
        }
      }

      @Override
      public void read(org.apache.blur.thirdparty.thrift_0_9_0.protocol.TProtocol prot, execute_args struct) throws org.apache.blur.thirdparty.thrift_0_9_0.TException {
        TTupleProtocol iprot = (TTupleProtocol) prot;
        BitSet incoming = iprot.readBitSet(1);
        if (incoming.get(0)) {
          struct.request = new BlurCommandRequest();
          struct.request.read(iprot);
          struct.setRequestIsSet(true);
        }
      }
    }

  }

  public static class execute_result implements org.apache.blur.thirdparty.thrift_0_9_0.TBase<execute_result, execute_result._Fields>, java.io.Serializable, Cloneable   {
    private static final org.apache.blur.thirdparty.thrift_0_9_0.protocol.TStruct STRUCT_DESC = new org.apache.blur.thirdparty.thrift_0_9_0.protocol.TStruct("execute_result");

    private static final org.apache.blur.thirdparty.thrift_0_9_0.protocol.TField SUCCESS_FIELD_DESC = new org.apache.blur.thirdparty.thrift_0_9_0.protocol.TField("success", org.apache.blur.thirdparty.thrift_0_9_0.protocol.TType.STRUCT, (short)0);
    private static final org.apache.blur.thirdparty.thrift_0_9_0.protocol.TField EX_FIELD_DESC = new org.apache.blur.thirdparty.thrift_0_9_0.protocol.TField("ex", org.apache.blur.thirdparty.thrift_0_9_0.protocol.TType.STRUCT, (short)1);

    private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
    static {
      schemes.put(StandardScheme.class, new execute_resultStandardSchemeFactory());
      schemes.put(TupleScheme.class, new execute_resultTupleSchemeFactory());
    }

    public BlurCommandResponse success; // required
    public BlurException ex; // required

    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements org.apache.blur.thirdparty.thrift_0_9_0.TFieldIdEnum {
      SUCCESS((short)0, "success"),
      EX((short)1, "ex");

      private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

      static {
        for (_Fields field : EnumSet.allOf(_Fields.class)) {
          byName.put(field.getFieldName(), field);
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, or null if its not found.
       */
      public static _Fields findByThriftId(int fieldId) {
        switch(fieldId) {
          case 0: // SUCCESS
            return SUCCESS;
          case 1: // EX
            return EX;
          default:
            return null;
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, throwing an exception
       * if it is not found.
       */
      public static _Fields findByThriftIdOrThrow(int fieldId) {
        _Fields fields = findByThriftId(fieldId);
        if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
        return fields;
      }

      /**
       * Find the _Fields constant that matches name, or null if its not found.
       */
      public static _Fields findByName(String name) {
        return byName.get(name);
      }

      private final short _thriftId;
      private final String _fieldName;

      _Fields(short thriftId, String fieldName) {
        _thriftId = thriftId;
        _fieldName = fieldName;
      }

      public short getThriftFieldId() {
        return _thriftId;
      }

      public String getFieldName() {
        return _fieldName;
      }
    }

    // isset id assignments
    public static final Map<_Fields, org.apache.blur.thirdparty.thrift_0_9_0.meta_data.FieldMetaData> metaDataMap;
    static {
      Map<_Fields, org.apache.blur.thirdparty.thrift_0_9_0.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.blur.thirdparty.thrift_0_9_0.meta_data.FieldMetaData>(_Fields.class);
      tmpMap.put(_Fields.SUCCESS, new org.apache.blur.thirdparty.thrift_0_9_0.meta_data.FieldMetaData("success", org.apache.blur.thirdparty.thrift_0_9_0.TFieldRequirementType.DEFAULT, 
          new org.apache.blur.thirdparty.thrift_0_9_0.meta_data.StructMetaData(org.apache.blur.thirdparty.thrift_0_9_0.protocol.TType.STRUCT, BlurCommandResponse.class)));
      tmpMap.put(_Fields.EX, new org.apache.blur.thirdparty.thrift_0_9_0.meta_data.FieldMetaData("ex", org.apache.blur.thirdparty.thrift_0_9_0.TFieldRequirementType.DEFAULT, 
          new org.apache.blur.thirdparty.thrift_0_9_0.meta_data.FieldValueMetaData(org.apache.blur.thirdparty.thrift_0_9_0.protocol.TType.STRUCT)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      org.apache.blur.thirdparty.thrift_0_9_0.meta_data.FieldMetaData.addStructMetaDataMap(execute_result.class, metaDataMap);
    }

    public execute_result() {
    }

    public execute_result(
      BlurCommandResponse success,
      BlurException ex)
    {
      this();
      this.success = success;
      this.ex = ex;
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public execute_result(execute_result other) {
      if (other.isSetSuccess()) {
        this.success = new BlurCommandResponse(other.success);
      }
      if (other.isSetEx()) {
        this.ex = new BlurException(other.ex);
      }
    }

    public execute_result deepCopy() {
      return new execute_result(this);
    }

    @Override
    public void clear() {
      this.success = null;
      this.ex = null;
    }

    public BlurCommandResponse getSuccess() {
      return this.success;
    }

    public execute_result setSuccess(BlurCommandResponse success) {
      this.success = success;
      return this;
    }

    public void unsetSuccess() {
      this.success = null;
    }

    /** Returns true if field success is set (has been assigned a value) and false otherwise */
    public boolean isSetSuccess() {
      return this.success != null;
    }

    public void setSuccessIsSet(boolean value) {
      if (!value) {
        this.success = null;
      }
    }

    public BlurException getEx() {
      return this.ex;
    }

    public execute_result setEx(BlurException ex) {
      this.ex = ex;
      return this;
    }

    public void unsetEx() {
      this.ex = null;
    }

    /** Returns true if field ex is set (has been assigned a value) and false otherwise */
    public boolean isSetEx() {
      return this.ex != null;
    }

    public void setExIsSet(boolean value) {
      if (!value) {
        this.ex = null;
      }
    }

    public void setFieldValue(_Fields field, Object value) {
      switch (field) {
      case SUCCESS:
        if (value == null) {
          unsetSuccess();
        } else {
          setSuccess((BlurCommandResponse)value);
        }
        break;

      case EX:
        if (value == null) {
          unsetEx();
        } else {
          setEx((BlurException)value);
        }
        break;

      }
    }

    public Object getFieldValue(_Fields field) {
      switch (field) {
      case SUCCESS:
        return getSuccess();

      case EX:
        return getEx();

      }
      throw new IllegalStateException();
    }

    /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
      if (field == null) {
        throw new IllegalArgumentException();
      }

      switch (field) {
      case SUCCESS:
        return isSetSuccess();
      case EX:
        return isSetEx();
      }
      throw new IllegalStateException();
    }

    @Override
    public boolean equals(Object that) {
      if (that == null)
        return false;
      if (that instanceof execute_result)
        return this.equals((execute_result)that);
      return false;
    }

    public boolean equals(execute_result that) {
      if (that == null)
        return false;

      boolean this_present_success = true && this.isSetSuccess();
      boolean that_present_success = true && that.isSetSuccess();
      if (this_present_success || that_present_success) {
        if (!(this_present_success && that_present_success))
          return false;
        if (!this.success.equals(that.success))
          return false;
      }

      boolean this_present_ex = true && this.isSetEx();
      boolean that_present_ex = true && that.isSetEx();
      if (this_present_ex || that_present_ex) {
        if (!(this_present_ex && that_present_ex))
          return false;
        if (!this.ex.equals(that.ex))
          return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      return 0;
    }

    public int compareTo(execute_result other) {
      if (!getClass().equals(other.getClass())) {
        return getClass().getName().compareTo(other.getClass().getName());
      }

      int lastComparison = 0;
      execute_result typedOther = (execute_result)other;

      lastComparison = Boolean.valueOf(isSetSuccess()).compareTo(typedOther.isSetSuccess());
      if (lastComparison != 0) {
        return lastComparison;
      }
      if (isSetSuccess()) {
        lastComparison = org.apache.blur.thirdparty.thrift_0_9_0.TBaseHelper.compareTo(this.success, typedOther.success);
        if (lastComparison != 0) {
          return lastComparison;
        }
      }
      lastComparison = Boolean.valueOf(isSetEx()).compareTo(typedOther.isSetEx());
      if (lastComparison != 0) {
        return lastComparison;
      }
      if (isSetEx()) {
        lastComparison = org.apache.blur.thirdparty.thrift_0_9_0.TBaseHelper.compareTo(this.ex, typedOther.ex);
        if (lastComparison != 0) {
          return lastComparison;
        }
      }
      return 0;
    }

    public _Fields fieldForId(int fieldId) {
      return _Fields.findByThriftId(fieldId);
    }

    public void read(org.apache.blur.thirdparty.thrift_0_9_0.protocol.TProtocol iprot) throws org.apache.blur.thirdparty.thrift_0_9_0.TException {
      schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
    }

    public void write(org.apache.blur.thirdparty.thrift_0_9_0.protocol.TProtocol oprot) throws org.apache.blur.thirdparty.thrift_0_9_0.TException {
      schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
      }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder("execute_result(");
      boolean first = true;

      sb.append("success:");
      if (this.success == null) {
        sb.append("null");
      } else {
        sb.append(this.success);
      }
      first = false;
      if (!first) sb.append(", ");
      sb.append("ex:");
      if (this.ex == null) {
        sb.append("null");
      } else {
        sb.append(this.ex);
      }
      first = false;
      sb.append(")");
      return sb.toString();
    }

    public void validate() throws org.apache.blur.thirdparty.thrift_0_9_0.TException {
      // check for required fields
      // check for sub-struct validity
    }

    private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
      try {
        write(new org.apache.blur.thirdparty.thrift_0_9_0.protocol.TCompactProtocol(new org.apache.blur.thirdparty.thrift_0_9_0.transport.TIOStreamTransport(out)));
      } catch (org.apache.blur.thirdparty.thrift_0_9_0.TException te) {
        throw new java.io.IOException(te);
      }
    }

    private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
      try {
        read(new org.apache.blur.thirdparty.thrift_0_9_0.protocol.TCompactProtocol(new org.apache.blur.thirdparty.thrift_0_9_0.transport.TIOStreamTransport(in)));
      } catch (org.apache.blur.thirdparty.thrift_0_9_0.TException te) {
        throw new java.io.IOException(te);
      }
    }

    private static class execute_resultStandardSchemeFactory implements SchemeFactory {
      public execute_resultStandardScheme getScheme() {
        return new execute_resultStandardScheme();
      }
    }

    private static class execute_resultStandardScheme extends StandardScheme<execute_result> {

      public void read(org.apache.blur.thirdparty.thrift_0_9_0.protocol.TProtocol iprot, execute_result struct) throws org.apache.blur.thirdparty.thrift_0_9_0.TException {
        org.apache.blur.thirdparty.thrift_0_9_0.protocol.TField schemeField;
        iprot.readStructBegin();
        while (true)
        {
          schemeField = iprot.readFieldBegin();
          if (schemeField.type == org.apache.blur.thirdparty.thrift_0_9_0.protocol.TType.STOP) { 
            break;
          }
          switch (schemeField.id) {
            case 0: // SUCCESS
              if (schemeField.type == org.apache.blur.thirdparty.thrift_0_9_0.protocol.TType.STRUCT) {
                struct.success = new BlurCommandResponse();
                struct.success.read(iprot);
                struct.setSuccessIsSet(true);
              } else { 
                org.apache.blur.thirdparty.thrift_0_9_0.protocol.TProtocolUtil.skip(iprot, schemeField.type);
              }
              break;
            case 1: // EX
              if (schemeField.type == org.apache.blur.thirdparty.thrift_0_9_0.protocol.TType.STRUCT) {
                struct.ex = new BlurException();
                struct.ex.read(iprot);
                struct.setExIsSet(true);
              } else { 
                org.apache.blur.thirdparty.thrift_0_9_0.protocol.TProtocolUtil.skip(iprot, schemeField.type);
              }
              break;
            default:
              org.apache.blur.thirdparty.thrift_0_9_0.protocol.TProtocolUtil.skip(iprot, schemeField.type);
          }
          iprot.readFieldEnd();
        }
        iprot.readStructEnd();

        // check for required fields of primitive type, which can't be checked in the validate method
        struct.validate();
      }

      public void write(org.apache.blur.thirdparty.thrift_0_9_0.protocol.TProtocol oprot, execute_result struct) throws org.apache.blur.thirdparty.thrift_0_9_0.TException {
        struct.validate();

        oprot.writeStructBegin(STRUCT_DESC);
        if (struct.success != null) {
          oprot.writeFieldBegin(SUCCESS_FIELD_DESC);
          struct.success.write(oprot);
          oprot.writeFieldEnd();
        }
        if (struct.ex != null) {
          oprot.writeFieldBegin(EX_FIELD_DESC);
          struct.ex.write(oprot);
          oprot.writeFieldEnd();
        }
        oprot.writeFieldStop();
        oprot.writeStructEnd();
      }

    }

    private static class execute_resultTupleSchemeFactory implements SchemeFactory {
      public execute_resultTupleScheme getScheme() {
        return new execute_resultTupleScheme();
      }
    }

    private static class execute_resultTupleScheme extends TupleScheme<execute_result> {

      @Override
      public void write(org.apache.blur.thirdparty.thrift_0_9_0.protocol.TProtocol prot, execute_result struct) throws org.apache.blur.thirdparty.thrift_0_9_0.TException {
        TTupleProtocol oprot = (TTupleProtocol) prot;
        BitSet optionals = new BitSet();
        if (struct.isSetSuccess()) {
          optionals.set(0);
        }
        if (struct.isSetEx()) {
          optionals.set(1);
        }
        oprot.writeBitSet(optionals, 2);
        if (struct.isSetSuccess()) {
          struct.success.write(oprot);
        }
        if (struct.isSetEx()) {
          struct.ex.write(oprot);
        }
      }

      @Override
      public void read(org.apache.blur.thirdparty.thrift_0_9_0.protocol.TProtocol prot, execute_result struct) throws org.apache.blur.thirdparty.thrift_0_9_0.TException {
        TTupleProtocol iprot = (TTupleProtocol) prot;
        BitSet incoming = iprot.readBitSet(2);
        if (incoming.get(0)) {
          struct.success = new BlurCommandResponse();
          struct.success.read(iprot);
          struct.setSuccessIsSet(true);
        }
        if (incoming.get(1)) {
          struct.ex = new BlurException();
          struct.ex.read(iprot);
          struct.setExIsSet(true);
        }
      }
    }

  }

}
