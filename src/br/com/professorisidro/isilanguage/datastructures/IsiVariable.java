package br.com.professorisidro.isilanguage.datastructures;

public class IsiVariable extends IsiSymbol {
	
	public static final int NUMBER=0;
	public static final int TEXT  =1;
	
	private int type;
	private String value;
	private int attrib;

	public IsiVariable(String name, int type, String value) {
		super(name);
		this.type = type;
		this.value = value;
		this.attrib = 0;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setAttrib(int value) {
		this.attrib = value;
	}

	public int getAttrib() {
		return attrib;
	}

	@Override
	public String toString() {
		return "IsiVariable [name=" + name + ", type=" + type + ", value=" + value + "]";
	}
	
	public String generateJavaCode() {
       String str;
       if (type == NUMBER) {
    	   str = "double ";
       }
       else {
    	   str = "String ";
       }
       return str + " "+super.name+";";
	}
	
	

}
