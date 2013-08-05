
public class SCVector {
	
	
	
	static final public byte[] MASKS = {1, 2, 4, 8, 16, 32, 64, -128};
	
	static public SCVector xor(SCVector... operands) {
		
		int length = operands[0].getLength();
		SCVector result = new SCVector(length);
		
		for (int i=0; i<result.data.length; i++) {
			for (int j = 0; j < operands.length; j++) {
				result.data[i] ^= operands[j].data[i];
			}
		}
		
		return result;
	}
	
	static public SCVector sum(SCVector... operands) {
		
		int length = operands[0].getLength();
		SCVector result = new SCVector(length);
		
		for (int i=0; i<length; i++) {
			int bias = 0;
			for (int j = 0; j < operands.length; j++) {
				if (operands[j].get(i)) {
					bias++;
				} else {
					bias--;
				}
			}
			if (bias>0) {
				result.put(i, true);
			}
			else if (bias>0) {
				result.put(i, true);
			}
			else {
				result.put(i, Math.random()<.5);
			}
		}
		return result;
	}

	private byte[] data;
	private int length;
	
	public SCVector(int length) {
		this.length = length;
		data = new byte[length / 8];
	}
	
	public void randomize() {
		
	}
	
	public boolean get(int i) {
		return (data[i/8] & MASKS[i%8]) != 0;
	}
	
	public void put(int i, boolean value) {
		if (value) data[i/8] |= MASKS[i%8];
		else data[i/8] &= ~MASKS[i%8];
	}
	
	public int getLength() {
		return length;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(data.length * 8);
		for (int i=0; i<data.length*8; i++) {
			sb.append(get(i) ? '1' : '0');
		}
		return sb.toString();
	}
	
}