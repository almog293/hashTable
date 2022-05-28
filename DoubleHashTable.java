import java.util.Random;

public class DoubleHashTable extends OAHashTable {
	ModHash func1;
	ModHash func2;
	public DoubleHashTable(int m, long p) {
		super(m);
		this.func1 = ModHash.GetFunc(m,p);
		this.func2 = ModHash.GetFunc(m,p);
	}
	
	@Override
	public int Hash(long x, int i) {
		return (func1.Hash(x) + i* func2.Hash(x))%this.size;
	}
	
}
