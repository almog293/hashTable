import java.util.Random;

public class AQPHashTable extends OAHashTable {
	ModHash func;
	public AQPHashTable(int m, long p) {
		super(m);
		this.func = ModHash.GetFunc(m,p);
	}
	
	@Override
	public int Hash(long x, int i) {
		if (i%2 == 0){
			return (this.func.Hash(x) + i*i)%this.size;
		}
		else{
			int num = (this.func.Hash(x) - (i*i))%this.size;
			if(num < 0){
				num += this.size;
			}
			return num;
		}
	}
}
