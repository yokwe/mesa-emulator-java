package yokwe.majuro.symbol.model;

import java.util.regex.Pattern;

import yokwe.majuro.UnexpectedException;

public class QName implements Comparable<QName> {
	private static final yokwe.majuro.util.FormatLogger logger = yokwe.majuro.util.FormatLogger.getLogger();
	
	public static final String PREDEFINED_MODULE = "@PREDEFINED@";

	public static final String getQName(String module, String name) {
		QName ret = new QName(module, name);
		return ret.qName;
	}
	public static final String getQName(Type type, String name) {
		return getQName(type.qName.module, name);
	}	
	
	public final String module;
	public final String name;
	public final String qName;
	
	public QName(String name) {
		if (Type.isPredefined(name)) {
			// special for predefined class name
			this.module = PREDEFINED_MODULE;
			this.name   = name;
			this.qName  = name;
		} else {
			logger.error("Unexpected");
			logger.error("  name   %s", name);
			throw new UnexpectedException();
		}
	}
	public QName(String module, String name) {
		if (name.contains(".")) {
			String[] names = name.split(Pattern.quote("."));
			if (names.length == 2) {
				// name is already qualified
				this.module = names[0];
				this.name   = names[1];
				this.qName  = this.module + "." + this.name;
			} else {
				logger.error("Unexpected");
				logger.error("  module %s", module);
				logger.error("  name   %s", name);
				throw new UnexpectedException();
			}
		} else {
			// name is not qualified
			if (Type.isPredefined(name)) {
				this.module = PREDEFINED_MODULE;
				this.name   = name;
				this.qName  = name;
			} else {
				this.module = module;
				this.name   = name;
				this.qName  = this.module + "." + this.name;
			}
		}
	}
	public QName(QName qName, String name) {
		this(qName.module, name);
	}
	
	public QName appendSuffix(String suffix) {
		return new QName(module, name + suffix);
	}
	public QName localName(String newValue) {
		return new QName(module, newValue);
	}
	
	public boolean sameModule(String module) {
		return this.module.equals(module);
	}
	
	public String simpleName(String module) {
		if (this.module.equals(module)) {
			return name;
		} else {
			return qName;
		}
	}

	@Override
	public int compareTo(QName that) {
		return this.qName.compareTo(that.qName);
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof QName) {
			QName that = (QName)o;
			return compareTo(that) == 0;
		}
		if (o instanceof String) {
			String that = (String)o;
			return qName.equals(that);
		}
		return false;
	}
	public boolean equals(String that) {
		return qName.equals(that);
	}
	
	@Override
	public String toString() {
		return qName;
	}
	
}
