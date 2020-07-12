package de.h_da.verteiltesysteme.zentrale.thrift;
/**
 * Autogenerated by Thrift Compiler (0.13.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.13.0)", date = "2020-07-12")
public class SensorDataThrift implements org.apache.thrift.TBase<SensorDataThrift, SensorDataThrift._Fields>, java.io.Serializable, Cloneable, Comparable<SensorDataThrift> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("SensorDataThrift");

  private static final org.apache.thrift.protocol.TField NAME_OF_SENSOR_FIELD_DESC = new org.apache.thrift.protocol.TField("nameOfSensor", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField TYPE_OF_SENSOR_FIELD_DESC = new org.apache.thrift.protocol.TField("typeOfSensor", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField TIMESTAMP_FIELD_DESC = new org.apache.thrift.protocol.TField("timestamp", org.apache.thrift.protocol.TType.I64, (short)3);
  private static final org.apache.thrift.protocol.TField SEQUENCE_NUMBER_FIELD_DESC = new org.apache.thrift.protocol.TField("sequenceNumber", org.apache.thrift.protocol.TType.I64, (short)4);
  private static final org.apache.thrift.protocol.TField VALUE_FIELD_DESC = new org.apache.thrift.protocol.TField("value", org.apache.thrift.protocol.TType.DOUBLE, (short)5);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new SensorDataThriftStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new SensorDataThriftTupleSchemeFactory();

  public @org.apache.thrift.annotation.Nullable java.lang.String nameOfSensor; // required
  public @org.apache.thrift.annotation.Nullable java.lang.String typeOfSensor; // required
  public long timestamp; // required
  public long sequenceNumber; // required
  public double value; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    NAME_OF_SENSOR((short)1, "nameOfSensor"),
    TYPE_OF_SENSOR((short)2, "typeOfSensor"),
    TIMESTAMP((short)3, "timestamp"),
    SEQUENCE_NUMBER((short)4, "sequenceNumber"),
    VALUE((short)5, "value");

    private static final java.util.Map<java.lang.String, _Fields> byName = new java.util.HashMap<java.lang.String, _Fields>();

    static {
      for (_Fields field : java.util.EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    @org.apache.thrift.annotation.Nullable
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // NAME_OF_SENSOR
          return NAME_OF_SENSOR;
        case 2: // TYPE_OF_SENSOR
          return TYPE_OF_SENSOR;
        case 3: // TIMESTAMP
          return TIMESTAMP;
        case 4: // SEQUENCE_NUMBER
          return SEQUENCE_NUMBER;
        case 5: // VALUE
          return VALUE;
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
      if (fields == null) throw new java.lang.IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    @org.apache.thrift.annotation.Nullable
    public static _Fields findByName(java.lang.String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final java.lang.String _fieldName;

    _Fields(short thriftId, java.lang.String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public java.lang.String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __TIMESTAMP_ISSET_ID = 0;
  private static final int __SEQUENCENUMBER_ISSET_ID = 1;
  private static final int __VALUE_ISSET_ID = 2;
  private byte __isset_bitfield = 0;
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.NAME_OF_SENSOR, new org.apache.thrift.meta_data.FieldMetaData("nameOfSensor", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.TYPE_OF_SENSOR, new org.apache.thrift.meta_data.FieldMetaData("typeOfSensor", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.TIMESTAMP, new org.apache.thrift.meta_data.FieldMetaData("timestamp", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.SEQUENCE_NUMBER, new org.apache.thrift.meta_data.FieldMetaData("sequenceNumber", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.VALUE, new org.apache.thrift.meta_data.FieldMetaData("value", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.DOUBLE)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(SensorDataThrift.class, metaDataMap);
  }

  public SensorDataThrift() {
  }

  public SensorDataThrift(
    java.lang.String nameOfSensor,
    java.lang.String typeOfSensor,
    long timestamp,
    long sequenceNumber,
    double value)
  {
    this();
    this.nameOfSensor = nameOfSensor;
    this.typeOfSensor = typeOfSensor;
    this.timestamp = timestamp;
    setTimestampIsSet(true);
    this.sequenceNumber = sequenceNumber;
    setSequenceNumberIsSet(true);
    this.value = value;
    setValueIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public SensorDataThrift(SensorDataThrift other) {
    __isset_bitfield = other.__isset_bitfield;
    if (other.isSetNameOfSensor()) {
      this.nameOfSensor = other.nameOfSensor;
    }
    if (other.isSetTypeOfSensor()) {
      this.typeOfSensor = other.typeOfSensor;
    }
    this.timestamp = other.timestamp;
    this.sequenceNumber = other.sequenceNumber;
    this.value = other.value;
  }

  public SensorDataThrift deepCopy() {
    return new SensorDataThrift(this);
  }

  @Override
  public void clear() {
    this.nameOfSensor = null;
    this.typeOfSensor = null;
    setTimestampIsSet(false);
    this.timestamp = 0;
    setSequenceNumberIsSet(false);
    this.sequenceNumber = 0;
    setValueIsSet(false);
    this.value = 0.0;
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.String getNameOfSensor() {
    return this.nameOfSensor;
  }

  public SensorDataThrift setNameOfSensor(@org.apache.thrift.annotation.Nullable java.lang.String nameOfSensor) {
    this.nameOfSensor = nameOfSensor;
    return this;
  }

  public void unsetNameOfSensor() {
    this.nameOfSensor = null;
  }

  /** Returns true if field nameOfSensor is set (has been assigned a value) and false otherwise */
  public boolean isSetNameOfSensor() {
    return this.nameOfSensor != null;
  }

  public void setNameOfSensorIsSet(boolean value) {
    if (!value) {
      this.nameOfSensor = null;
    }
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.String getTypeOfSensor() {
    return this.typeOfSensor;
  }

  public SensorDataThrift setTypeOfSensor(@org.apache.thrift.annotation.Nullable java.lang.String typeOfSensor) {
    this.typeOfSensor = typeOfSensor;
    return this;
  }

  public void unsetTypeOfSensor() {
    this.typeOfSensor = null;
  }

  /** Returns true if field typeOfSensor is set (has been assigned a value) and false otherwise */
  public boolean isSetTypeOfSensor() {
    return this.typeOfSensor != null;
  }

  public void setTypeOfSensorIsSet(boolean value) {
    if (!value) {
      this.typeOfSensor = null;
    }
  }

  public long getTimestamp() {
    return this.timestamp;
  }

  public SensorDataThrift setTimestamp(long timestamp) {
    this.timestamp = timestamp;
    setTimestampIsSet(true);
    return this;
  }

  public void unsetTimestamp() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __TIMESTAMP_ISSET_ID);
  }

  /** Returns true if field timestamp is set (has been assigned a value) and false otherwise */
  public boolean isSetTimestamp() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __TIMESTAMP_ISSET_ID);
  }

  public void setTimestampIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __TIMESTAMP_ISSET_ID, value);
  }

  public long getSequenceNumber() {
    return this.sequenceNumber;
  }

  public SensorDataThrift setSequenceNumber(long sequenceNumber) {
    this.sequenceNumber = sequenceNumber;
    setSequenceNumberIsSet(true);
    return this;
  }

  public void unsetSequenceNumber() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __SEQUENCENUMBER_ISSET_ID);
  }

  /** Returns true if field sequenceNumber is set (has been assigned a value) and false otherwise */
  public boolean isSetSequenceNumber() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __SEQUENCENUMBER_ISSET_ID);
  }

  public void setSequenceNumberIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __SEQUENCENUMBER_ISSET_ID, value);
  }

  public double getValue() {
    return this.value;
  }

  public SensorDataThrift setValue(double value) {
    this.value = value;
    setValueIsSet(true);
    return this;
  }

  public void unsetValue() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __VALUE_ISSET_ID);
  }

  /** Returns true if field value is set (has been assigned a value) and false otherwise */
  public boolean isSetValue() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __VALUE_ISSET_ID);
  }

  public void setValueIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __VALUE_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, @org.apache.thrift.annotation.Nullable java.lang.Object value) {
    switch (field) {
    case NAME_OF_SENSOR:
      if (value == null) {
        unsetNameOfSensor();
      } else {
        setNameOfSensor((java.lang.String)value);
      }
      break;

    case TYPE_OF_SENSOR:
      if (value == null) {
        unsetTypeOfSensor();
      } else {
        setTypeOfSensor((java.lang.String)value);
      }
      break;

    case TIMESTAMP:
      if (value == null) {
        unsetTimestamp();
      } else {
        setTimestamp((java.lang.Long)value);
      }
      break;

    case SEQUENCE_NUMBER:
      if (value == null) {
        unsetSequenceNumber();
      } else {
        setSequenceNumber((java.lang.Long)value);
      }
      break;

    case VALUE:
      if (value == null) {
        unsetValue();
      } else {
        setValue((java.lang.Double)value);
      }
      break;

    }
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case NAME_OF_SENSOR:
      return getNameOfSensor();

    case TYPE_OF_SENSOR:
      return getTypeOfSensor();

    case TIMESTAMP:
      return getTimestamp();

    case SEQUENCE_NUMBER:
      return getSequenceNumber();

    case VALUE:
      return getValue();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case NAME_OF_SENSOR:
      return isSetNameOfSensor();
    case TYPE_OF_SENSOR:
      return isSetTypeOfSensor();
    case TIMESTAMP:
      return isSetTimestamp();
    case SEQUENCE_NUMBER:
      return isSetSequenceNumber();
    case VALUE:
      return isSetValue();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null)
      return false;
    if (that instanceof SensorDataThrift)
      return this.equals((SensorDataThrift)that);
    return false;
  }

  public boolean equals(SensorDataThrift that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_nameOfSensor = true && this.isSetNameOfSensor();
    boolean that_present_nameOfSensor = true && that.isSetNameOfSensor();
    if (this_present_nameOfSensor || that_present_nameOfSensor) {
      if (!(this_present_nameOfSensor && that_present_nameOfSensor))
        return false;
      if (!this.nameOfSensor.equals(that.nameOfSensor))
        return false;
    }

    boolean this_present_typeOfSensor = true && this.isSetTypeOfSensor();
    boolean that_present_typeOfSensor = true && that.isSetTypeOfSensor();
    if (this_present_typeOfSensor || that_present_typeOfSensor) {
      if (!(this_present_typeOfSensor && that_present_typeOfSensor))
        return false;
      if (!this.typeOfSensor.equals(that.typeOfSensor))
        return false;
    }

    boolean this_present_timestamp = true;
    boolean that_present_timestamp = true;
    if (this_present_timestamp || that_present_timestamp) {
      if (!(this_present_timestamp && that_present_timestamp))
        return false;
      if (this.timestamp != that.timestamp)
        return false;
    }

    boolean this_present_sequenceNumber = true;
    boolean that_present_sequenceNumber = true;
    if (this_present_sequenceNumber || that_present_sequenceNumber) {
      if (!(this_present_sequenceNumber && that_present_sequenceNumber))
        return false;
      if (this.sequenceNumber != that.sequenceNumber)
        return false;
    }

    boolean this_present_value = true;
    boolean that_present_value = true;
    if (this_present_value || that_present_value) {
      if (!(this_present_value && that_present_value))
        return false;
      if (this.value != that.value)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ((isSetNameOfSensor()) ? 131071 : 524287);
    if (isSetNameOfSensor())
      hashCode = hashCode * 8191 + nameOfSensor.hashCode();

    hashCode = hashCode * 8191 + ((isSetTypeOfSensor()) ? 131071 : 524287);
    if (isSetTypeOfSensor())
      hashCode = hashCode * 8191 + typeOfSensor.hashCode();

    hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(timestamp);

    hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(sequenceNumber);

    hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(value);

    return hashCode;
  }

  @Override
  public int compareTo(SensorDataThrift other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.valueOf(isSetNameOfSensor()).compareTo(other.isSetNameOfSensor());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetNameOfSensor()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.nameOfSensor, other.nameOfSensor);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetTypeOfSensor()).compareTo(other.isSetTypeOfSensor());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetTypeOfSensor()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.typeOfSensor, other.typeOfSensor);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetTimestamp()).compareTo(other.isSetTimestamp());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetTimestamp()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.timestamp, other.timestamp);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetSequenceNumber()).compareTo(other.isSetSequenceNumber());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetSequenceNumber()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.sequenceNumber, other.sequenceNumber);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetValue()).compareTo(other.isSetValue());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetValue()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.value, other.value);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  @org.apache.thrift.annotation.Nullable
  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    scheme(iprot).read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    scheme(oprot).write(oprot, this);
  }

  @Override
  public java.lang.String toString() {
    java.lang.StringBuilder sb = new java.lang.StringBuilder("SensorDataThrift(");
    boolean first = true;

    sb.append("nameOfSensor:");
    if (this.nameOfSensor == null) {
      sb.append("null");
    } else {
      sb.append(this.nameOfSensor);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("typeOfSensor:");
    if (this.typeOfSensor == null) {
      sb.append("null");
    } else {
      sb.append(this.typeOfSensor);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("timestamp:");
    sb.append(this.timestamp);
    first = false;
    if (!first) sb.append(", ");
    sb.append("sequenceNumber:");
    sb.append(this.sequenceNumber);
    first = false;
    if (!first) sb.append(", ");
    sb.append("value:");
    sb.append(this.value);
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, java.lang.ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class SensorDataThriftStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public SensorDataThriftStandardScheme getScheme() {
      return new SensorDataThriftStandardScheme();
    }
  }

  private static class SensorDataThriftStandardScheme extends org.apache.thrift.scheme.StandardScheme<SensorDataThrift> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, SensorDataThrift struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // NAME_OF_SENSOR
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.nameOfSensor = iprot.readString();
              struct.setNameOfSensorIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // TYPE_OF_SENSOR
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.typeOfSensor = iprot.readString();
              struct.setTypeOfSensorIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // TIMESTAMP
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.timestamp = iprot.readI64();
              struct.setTimestampIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // SEQUENCE_NUMBER
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.sequenceNumber = iprot.readI64();
              struct.setSequenceNumberIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // VALUE
            if (schemeField.type == org.apache.thrift.protocol.TType.DOUBLE) {
              struct.value = iprot.readDouble();
              struct.setValueIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, SensorDataThrift struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.nameOfSensor != null) {
        oprot.writeFieldBegin(NAME_OF_SENSOR_FIELD_DESC);
        oprot.writeString(struct.nameOfSensor);
        oprot.writeFieldEnd();
      }
      if (struct.typeOfSensor != null) {
        oprot.writeFieldBegin(TYPE_OF_SENSOR_FIELD_DESC);
        oprot.writeString(struct.typeOfSensor);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(TIMESTAMP_FIELD_DESC);
      oprot.writeI64(struct.timestamp);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(SEQUENCE_NUMBER_FIELD_DESC);
      oprot.writeI64(struct.sequenceNumber);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(VALUE_FIELD_DESC);
      oprot.writeDouble(struct.value);
      oprot.writeFieldEnd();
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class SensorDataThriftTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public SensorDataThriftTupleScheme getScheme() {
      return new SensorDataThriftTupleScheme();
    }
  }

  private static class SensorDataThriftTupleScheme extends org.apache.thrift.scheme.TupleScheme<SensorDataThrift> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, SensorDataThrift struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetNameOfSensor()) {
        optionals.set(0);
      }
      if (struct.isSetTypeOfSensor()) {
        optionals.set(1);
      }
      if (struct.isSetTimestamp()) {
        optionals.set(2);
      }
      if (struct.isSetSequenceNumber()) {
        optionals.set(3);
      }
      if (struct.isSetValue()) {
        optionals.set(4);
      }
      oprot.writeBitSet(optionals, 5);
      if (struct.isSetNameOfSensor()) {
        oprot.writeString(struct.nameOfSensor);
      }
      if (struct.isSetTypeOfSensor()) {
        oprot.writeString(struct.typeOfSensor);
      }
      if (struct.isSetTimestamp()) {
        oprot.writeI64(struct.timestamp);
      }
      if (struct.isSetSequenceNumber()) {
        oprot.writeI64(struct.sequenceNumber);
      }
      if (struct.isSetValue()) {
        oprot.writeDouble(struct.value);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, SensorDataThrift struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(5);
      if (incoming.get(0)) {
        struct.nameOfSensor = iprot.readString();
        struct.setNameOfSensorIsSet(true);
      }
      if (incoming.get(1)) {
        struct.typeOfSensor = iprot.readString();
        struct.setTypeOfSensorIsSet(true);
      }
      if (incoming.get(2)) {
        struct.timestamp = iprot.readI64();
        struct.setTimestampIsSet(true);
      }
      if (incoming.get(3)) {
        struct.sequenceNumber = iprot.readI64();
        struct.setSequenceNumberIsSet(true);
      }
      if (incoming.get(4)) {
        struct.value = iprot.readDouble();
        struct.setValueIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

