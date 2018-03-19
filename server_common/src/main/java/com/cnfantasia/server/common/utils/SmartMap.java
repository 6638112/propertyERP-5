package com.cnfantasia.server.common.utils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;

/**
 * 可直接得到对象的具体类型Map
 * 
 * @author liyulin
 * @version 1.0 2016年8月26日 下午8:02:53
 */
public class SmartMap<K, V> extends HashMap<K, V> {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	public <T> T getObjectByType(String key, Class<T> cls) {
		Object obj = this.get(key);
		if (obj == null) {
			return null;
		} else if (obj.getClass() == cls) {
			return (T) obj;
		} else {
			return getObj(obj, cls);
		}
	}

	private <T> T getObj(Object obj, Class<T> cls) {
		try {
			if (obj.toString().toString().length() == 0) {
				return null;
			}
			return cls.getConstructor(String.class).newInstance(obj.toString());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public <T> T getPrimaryObject(String key, Class<T> cls) {
		Object v = this.get(key);
		if (v == null) {
			return getDefaultValue(cls);
		} else if (cls == int.class) {
			return (T) new Integer(v.toString());
		} else if (cls == long.class) {
			return (T) new Long(v.toString());
		} else if (cls == byte[].class) {
			return (T) v;
		} else if (cls == double.class) {
			return (T) new Double(v.toString());
		} else if (cls == float.class) {
			return (T) new Float(v.toString());
		} else if (cls == boolean.class) {
			return (T) new Boolean(v.toString());
		} else if (cls == short.class) {
			return (T) new Short(Short.parseShort(v.toString()));
		}
		return null;
	}

	public <T> T getDefaultValue(Class<T> cls) {
		if (cls == int.class) {
			return (T) new Integer(0);
		} else if (cls == long.class) {
			return (T) new Long(0);
		} else if (cls == byte.class) {
			return (T) new byte[] { 0 };
		} else if (cls == double.class) {
			return (T) new Double(0);
		} else if (cls == float.class) {
			return (T) new Float(0);
		} else if (cls == boolean.class) {
			return (T) new Boolean(false);
		} else if (cls == char.class) {
			return (T) new Character('\0');
		} else if (cls == short.class) {
			return (T) new Short((short) 0);
		}
		return null;
	}

	// 基本类型
	public int getint(String key) {
		return getPrimaryObject(key, int.class);
	}

	public float getfloat(String key) {
		return getPrimaryObject(key, float.class);
	}

	public double getdoulbe(String key) {
		return getPrimaryObject(key, double.class);
	}

	public long getlong(String key) {
		return getPrimaryObject(key, long.class);
	}

	public boolean getboolean(String key) {
		return getPrimaryObject(key, boolean.class);
	}

	// 对象类型
	public Integer getInteger(String key) {
		return getObjectByType(key, Integer.class);
	}

	public Long getLong(String key) {
		return getObjectByType(key, Long.class);
	}

	public Double getDouble(String key) {
		return getObjectByType(key, Double.class);
	}

	public Float getFloat(String key) {
		return getObjectByType(key, Float.class);
	}

	public Boolean getBoolean(String key) {
		return getObjectByType(key, Boolean.class);
	}

	public String getString(String key) {
		return getObjectByType(key, String.class);
	}

	public BigDecimal getBigDecimal(String key) {
		return getObjectByType(key, BigDecimal.class);
	}

	public BigInteger getBigInteger(String key) {
		return getObjectByType(key, BigInteger.class);
	}

	public Date getDate(String key) {
		return getObjectByType(key, Date.class);
	}
}
