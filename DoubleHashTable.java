import java.util.Random;

public class DoubleHashTable extends OAHashTable {
	ModHash func1;
	ModHash func2;
	public DoubleHashTable(int m, long p) {
		super(m);
		this.func1 = ModHash.GetFunc(m,p);
		double ratio = 1;
		ModHash opFunc2 = ModHash.GetFunc(m,p);
		int counter = 0;
		while(ratio > (1/m)){
			opFunc2 = ModHash.GetFunc(m,p);
			for(long i = 0; i < p; i++){
				if(opFunc2.Hash(i) == this.func1.Hash(i)){
					counter++;
				}
			}
			ratio = counter/p;
		}
		this.func2 = opFunc2;

	}
	
	@Override
	public int Hash(long x, int i) {
		long j = i;
		return (int) ((func1.Hash(x) + j* func2.Hash(x)) %this.size);
	}
	
}
