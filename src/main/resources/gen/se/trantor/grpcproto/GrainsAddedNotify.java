// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: mc-service.proto

package se.trantor.grpcproto;

/**
 * Protobuf type {@code grpcproto.GrainsAddedNotify}
 */
public  final class GrainsAddedNotify extends
    com.google.protobuf.GeneratedMessage implements
    // @@protoc_insertion_point(message_implements:grpcproto.GrainsAddedNotify)
    GrainsAddedNotifyOrBuilder {
  // Use GrainsAddedNotify.newBuilder() to construct.
  private GrainsAddedNotify(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
    super(builder);
  }
  private GrainsAddedNotify() {
    id_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private GrainsAddedNotify(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry) {
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

            id_ = input.readInt32();
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw new RuntimeException(e.setUnfinishedMessage(this));
    } catch (java.io.IOException e) {
      throw new RuntimeException(
          new com.google.protobuf.InvalidProtocolBufferException(
              e.getMessage()).setUnfinishedMessage(this));
    } finally {
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return se.trantor.grpcproto.HelloWorldProto.internal_static_grpcproto_GrainsAddedNotify_descriptor;
  }

  protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return se.trantor.grpcproto.HelloWorldProto.internal_static_grpcproto_GrainsAddedNotify_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            se.trantor.grpcproto.GrainsAddedNotify.class, se.trantor.grpcproto.GrainsAddedNotify.Builder.class);
  }

  public static final int ID_FIELD_NUMBER = 1;
  private int id_;
  /**
   * <code>optional int32 id = 1;</code>
   */
  public int getId() {
    return id_;
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
    if (id_ != 0) {
      output.writeInt32(1, id_);
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (id_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, id_);
    }
    memoizedSize = size;
    return size;
  }

  private static final long serialVersionUID = 0L;
  public static se.trantor.grpcproto.GrainsAddedNotify parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static se.trantor.grpcproto.GrainsAddedNotify parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static se.trantor.grpcproto.GrainsAddedNotify parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static se.trantor.grpcproto.GrainsAddedNotify parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static se.trantor.grpcproto.GrainsAddedNotify parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return PARSER.parseFrom(input);
  }
  public static se.trantor.grpcproto.GrainsAddedNotify parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return PARSER.parseFrom(input, extensionRegistry);
  }
  public static se.trantor.grpcproto.GrainsAddedNotify parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return PARSER.parseDelimitedFrom(input);
  }
  public static se.trantor.grpcproto.GrainsAddedNotify parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return PARSER.parseDelimitedFrom(input, extensionRegistry);
  }
  public static se.trantor.grpcproto.GrainsAddedNotify parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return PARSER.parseFrom(input);
  }
  public static se.trantor.grpcproto.GrainsAddedNotify parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return PARSER.parseFrom(input, extensionRegistry);
  }

  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(se.trantor.grpcproto.GrainsAddedNotify prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessage.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code grpcproto.GrainsAddedNotify}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessage.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:grpcproto.GrainsAddedNotify)
      se.trantor.grpcproto.GrainsAddedNotifyOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return se.trantor.grpcproto.HelloWorldProto.internal_static_grpcproto_GrainsAddedNotify_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return se.trantor.grpcproto.HelloWorldProto.internal_static_grpcproto_GrainsAddedNotify_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              se.trantor.grpcproto.GrainsAddedNotify.class, se.trantor.grpcproto.GrainsAddedNotify.Builder.class);
    }

    // Construct using se.trantor.grpcproto.GrainsAddedNotify.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessage.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
      }
    }
    public Builder clear() {
      super.clear();
      id_ = 0;

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return se.trantor.grpcproto.HelloWorldProto.internal_static_grpcproto_GrainsAddedNotify_descriptor;
    }

    public se.trantor.grpcproto.GrainsAddedNotify getDefaultInstanceForType() {
      return se.trantor.grpcproto.GrainsAddedNotify.getDefaultInstance();
    }

    public se.trantor.grpcproto.GrainsAddedNotify build() {
      se.trantor.grpcproto.GrainsAddedNotify result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public se.trantor.grpcproto.GrainsAddedNotify buildPartial() {
      se.trantor.grpcproto.GrainsAddedNotify result = new se.trantor.grpcproto.GrainsAddedNotify(this);
      result.id_ = id_;
      onBuilt();
      return result;
    }

    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof se.trantor.grpcproto.GrainsAddedNotify) {
        return mergeFrom((se.trantor.grpcproto.GrainsAddedNotify)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(se.trantor.grpcproto.GrainsAddedNotify other) {
      if (other == se.trantor.grpcproto.GrainsAddedNotify.getDefaultInstance()) return this;
      if (other.getId() != 0) {
        setId(other.getId());
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
      se.trantor.grpcproto.GrainsAddedNotify parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (se.trantor.grpcproto.GrainsAddedNotify) e.getUnfinishedMessage();
        throw e;
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private int id_ ;
    /**
     * <code>optional int32 id = 1;</code>
     */
    public int getId() {
      return id_;
    }
    /**
     * <code>optional int32 id = 1;</code>
     */
    public Builder setId(int value) {
      
      id_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional int32 id = 1;</code>
     */
    public Builder clearId() {
      
      id_ = 0;
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


    // @@protoc_insertion_point(builder_scope:grpcproto.GrainsAddedNotify)
  }

  // @@protoc_insertion_point(class_scope:grpcproto.GrainsAddedNotify)
  private static final se.trantor.grpcproto.GrainsAddedNotify DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new se.trantor.grpcproto.GrainsAddedNotify();
  }

  public static se.trantor.grpcproto.GrainsAddedNotify getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<GrainsAddedNotify>
      PARSER = new com.google.protobuf.AbstractParser<GrainsAddedNotify>() {
    public GrainsAddedNotify parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      try {
        return new GrainsAddedNotify(input, extensionRegistry);
      } catch (RuntimeException e) {
        if (e.getCause() instanceof
            com.google.protobuf.InvalidProtocolBufferException) {
          throw (com.google.protobuf.InvalidProtocolBufferException)
              e.getCause();
        }
        throw e;
      }
    }
  };

  public static com.google.protobuf.Parser<GrainsAddedNotify> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<GrainsAddedNotify> getParserForType() {
    return PARSER;
  }

  public se.trantor.grpcproto.GrainsAddedNotify getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

