// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: mc-service.proto

package se.trantor.grpcproto;

/**
 * Protobuf type {@code grpcproto.StartStopRequest}
 */
public  final class StartStopRequest extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:grpcproto.StartStopRequest)
    StartStopRequestOrBuilder {
  // Use StartStopRequest.newBuilder() to construct.
  private StartStopRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private StartStopRequest() {
    startStop_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private StartStopRequest(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    int mutable_bitField0_ = 0;
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          default: {
            if (!input.skipField(tag)) {
              done = true;
            }
            break;
          }
          case 8: {
            int rawValue = input.readEnum();

            startStop_ = rawValue;
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return se.trantor.grpcproto.GrainbrainProto.internal_static_grpcproto_StartStopRequest_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return se.trantor.grpcproto.GrainbrainProto.internal_static_grpcproto_StartStopRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            se.trantor.grpcproto.StartStopRequest.class, se.trantor.grpcproto.StartStopRequest.Builder.class);
  }

  /**
   * Protobuf enum {@code grpcproto.StartStopRequest.StartStop}
   */
  public enum StartStop
      implements com.google.protobuf.ProtocolMessageEnum {
    /**
     * <code>START = 0;</code>
     */
    START(0),
    /**
     * <code>STOP = 1;</code>
     */
    STOP(1),
    /**
     * <code>ABORT = 2;</code>
     */
    ABORT(2),
    UNRECOGNIZED(-1),
    ;

    /**
     * <code>START = 0;</code>
     */
    public static final int START_VALUE = 0;
    /**
     * <code>STOP = 1;</code>
     */
    public static final int STOP_VALUE = 1;
    /**
     * <code>ABORT = 2;</code>
     */
    public static final int ABORT_VALUE = 2;


    public final int getNumber() {
      if (this == UNRECOGNIZED) {
        throw new java.lang.IllegalArgumentException(
            "Can't get the number of an unknown enum value.");
      }
      return value;
    }

    /**
     * @deprecated Use {@link #forNumber(int)} instead.
     */
    @java.lang.Deprecated
    public static StartStop valueOf(int value) {
      return forNumber(value);
    }

    public static StartStop forNumber(int value) {
      switch (value) {
        case 0: return START;
        case 1: return STOP;
        case 2: return ABORT;
        default: return null;
      }
    }

    public static com.google.protobuf.Internal.EnumLiteMap<StartStop>
        internalGetValueMap() {
      return internalValueMap;
    }
    private static final com.google.protobuf.Internal.EnumLiteMap<
        StartStop> internalValueMap =
          new com.google.protobuf.Internal.EnumLiteMap<StartStop>() {
            public StartStop findValueByNumber(int number) {
              return StartStop.forNumber(number);
            }
          };

    public final com.google.protobuf.Descriptors.EnumValueDescriptor
        getValueDescriptor() {
      return getDescriptor().getValues().get(ordinal());
    }
    public final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptorForType() {
      return getDescriptor();
    }
    public static final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptor() {
      return se.trantor.grpcproto.StartStopRequest.getDescriptor().getEnumTypes().get(0);
    }

    private static final StartStop[] VALUES = values();

    public static StartStop valueOf(
        com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
      if (desc.getType() != getDescriptor()) {
        throw new java.lang.IllegalArgumentException(
          "EnumValueDescriptor is not for this type.");
      }
      if (desc.getIndex() == -1) {
        return UNRECOGNIZED;
      }
      return VALUES[desc.getIndex()];
    }

    private final int value;

    private StartStop(int value) {
      this.value = value;
    }

    // @@protoc_insertion_point(enum_scope:grpcproto.StartStopRequest.StartStop)
  }

  public static final int START_STOP_FIELD_NUMBER = 1;
  private int startStop_;
  /**
   * <code>.grpcproto.StartStopRequest.StartStop start_stop = 1;</code>
   */
  public int getStartStopValue() {
    return startStop_;
  }
  /**
   * <code>.grpcproto.StartStopRequest.StartStop start_stop = 1;</code>
   */
  public se.trantor.grpcproto.StartStopRequest.StartStop getStartStop() {
    se.trantor.grpcproto.StartStopRequest.StartStop result = se.trantor.grpcproto.StartStopRequest.StartStop.valueOf(startStop_);
    return result == null ? se.trantor.grpcproto.StartStopRequest.StartStop.UNRECOGNIZED : result;
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (startStop_ != se.trantor.grpcproto.StartStopRequest.StartStop.START.getNumber()) {
      output.writeEnum(1, startStop_);
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (startStop_ != se.trantor.grpcproto.StartStopRequest.StartStop.START.getNumber()) {
      size += com.google.protobuf.CodedOutputStream
        .computeEnumSize(1, startStop_);
    }
    memoizedSize = size;
    return size;
  }

  private static final long serialVersionUID = 0L;
  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof se.trantor.grpcproto.StartStopRequest)) {
      return super.equals(obj);
    }
    se.trantor.grpcproto.StartStopRequest other = (se.trantor.grpcproto.StartStopRequest) obj;

    boolean result = true;
    result = result && startStop_ == other.startStop_;
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + START_STOP_FIELD_NUMBER;
    hash = (53 * hash) + startStop_;
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static se.trantor.grpcproto.StartStopRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static se.trantor.grpcproto.StartStopRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static se.trantor.grpcproto.StartStopRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static se.trantor.grpcproto.StartStopRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static se.trantor.grpcproto.StartStopRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static se.trantor.grpcproto.StartStopRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static se.trantor.grpcproto.StartStopRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static se.trantor.grpcproto.StartStopRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static se.trantor.grpcproto.StartStopRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static se.trantor.grpcproto.StartStopRequest parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(se.trantor.grpcproto.StartStopRequest prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code grpcproto.StartStopRequest}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:grpcproto.StartStopRequest)
      se.trantor.grpcproto.StartStopRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return se.trantor.grpcproto.GrainbrainProto.internal_static_grpcproto_StartStopRequest_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return se.trantor.grpcproto.GrainbrainProto.internal_static_grpcproto_StartStopRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              se.trantor.grpcproto.StartStopRequest.class, se.trantor.grpcproto.StartStopRequest.Builder.class);
    }

    // Construct using se.trantor.grpcproto.StartStopRequest.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    public Builder clear() {
      super.clear();
      startStop_ = 0;

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return se.trantor.grpcproto.GrainbrainProto.internal_static_grpcproto_StartStopRequest_descriptor;
    }

    public se.trantor.grpcproto.StartStopRequest getDefaultInstanceForType() {
      return se.trantor.grpcproto.StartStopRequest.getDefaultInstance();
    }

    public se.trantor.grpcproto.StartStopRequest build() {
      se.trantor.grpcproto.StartStopRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public se.trantor.grpcproto.StartStopRequest buildPartial() {
      se.trantor.grpcproto.StartStopRequest result = new se.trantor.grpcproto.StartStopRequest(this);
      result.startStop_ = startStop_;
      onBuilt();
      return result;
    }

    public Builder clone() {
      return (Builder) super.clone();
    }
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        Object value) {
      return (Builder) super.setField(field, value);
    }
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return (Builder) super.clearField(field);
    }
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return (Builder) super.clearOneof(oneof);
    }
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, Object value) {
      return (Builder) super.setRepeatedField(field, index, value);
    }
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        Object value) {
      return (Builder) super.addRepeatedField(field, value);
    }
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof se.trantor.grpcproto.StartStopRequest) {
        return mergeFrom((se.trantor.grpcproto.StartStopRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(se.trantor.grpcproto.StartStopRequest other) {
      if (other == se.trantor.grpcproto.StartStopRequest.getDefaultInstance()) return this;
      if (other.startStop_ != 0) {
        setStartStopValue(other.getStartStopValue());
      }
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      se.trantor.grpcproto.StartStopRequest parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (se.trantor.grpcproto.StartStopRequest) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private int startStop_ = 0;
    /**
     * <code>.grpcproto.StartStopRequest.StartStop start_stop = 1;</code>
     */
    public int getStartStopValue() {
      return startStop_;
    }
    /**
     * <code>.grpcproto.StartStopRequest.StartStop start_stop = 1;</code>
     */
    public Builder setStartStopValue(int value) {
      startStop_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>.grpcproto.StartStopRequest.StartStop start_stop = 1;</code>
     */
    public se.trantor.grpcproto.StartStopRequest.StartStop getStartStop() {
      se.trantor.grpcproto.StartStopRequest.StartStop result = se.trantor.grpcproto.StartStopRequest.StartStop.valueOf(startStop_);
      return result == null ? se.trantor.grpcproto.StartStopRequest.StartStop.UNRECOGNIZED : result;
    }
    /**
     * <code>.grpcproto.StartStopRequest.StartStop start_stop = 1;</code>
     */
    public Builder setStartStop(se.trantor.grpcproto.StartStopRequest.StartStop value) {
      if (value == null) {
        throw new NullPointerException();
      }
      
      startStop_ = value.getNumber();
      onChanged();
      return this;
    }
    /**
     * <code>.grpcproto.StartStopRequest.StartStop start_stop = 1;</code>
     */
    public Builder clearStartStop() {
      
      startStop_ = 0;
      onChanged();
      return this;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }


    // @@protoc_insertion_point(builder_scope:grpcproto.StartStopRequest)
  }

  // @@protoc_insertion_point(class_scope:grpcproto.StartStopRequest)
  private static final se.trantor.grpcproto.StartStopRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new se.trantor.grpcproto.StartStopRequest();
  }

  public static se.trantor.grpcproto.StartStopRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<StartStopRequest>
      PARSER = new com.google.protobuf.AbstractParser<StartStopRequest>() {
    public StartStopRequest parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new StartStopRequest(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<StartStopRequest> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<StartStopRequest> getParserForType() {
    return PARSER;
  }

  public se.trantor.grpcproto.StartStopRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

